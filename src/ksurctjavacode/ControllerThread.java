/*
 * The MIT License
 *
 * Copyright 2016 kurama.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package ksurctjavacode;

// Formatting the number to reduce polling spam.
import java.text.DecimalFormat;

// Controller libraries
import net.java.games.input.*;

/**
 * Defines a thread to monitor and poll the Xbox Controller.
 * @author Dan Wagner
 */
public class ControllerThread extends Thread {
    
    private final Controller _xbox;
    private final BasestationGUI _view;
    private volatile boolean _stop = false;
    
    // Joystick radial deadzone
    private final float _deadZone = 0.25f;
    
    // Format for the number on joysticks
    private final DecimalFormat _df = new DecimalFormat("#.##");
    
    /**
     * Creates new thread to monitor the Xbox controller feedback
     * @param xbox Xbox controller object
     * @param log EventLog to display information to (CHANGE TO ACTUAL GUI TO ACCESS BOXES AND FORM)
     */
    ControllerThread(Controller xbox, BasestationGUI gui)
    {
       this._xbox = xbox;
       this._view = gui;
       this.start();
    }
    
    /**
    * Begins polling on the Xbox controller.  Will stop when the thread is closed via the GUI.
    */
    @Override
    public void run()
    {
        Component prevComp = null;
        String prevValue = "";
        int x = 0;
        while (!_stop)
        {
            _xbox.poll();
            EventQueue queue = _xbox.getEventQueue();
            Event e = new Event();
            while (queue.getNextEvent(e))
            {
                StringBuilder buffer = new StringBuilder();
                Component comp = e.getComponent();
                String name = comp.getName();

               // Note: Analog sticks Y values are reversed (-1 is forward, 1 is backward)
               String value = _df.format(comp.getPollData());
               if (name == "x" || name == "y" || name == "rx" ||name == "ry")
               {
                   if (Math.abs(comp.getPollData()) < _deadZone) value = "0";
               }
            
               // TODO: Determine which button goes to what in order to update components
               if (!value.equals(prevValue) && prevComp != comp)
               {
                   name = getRobotName(comp.getName());
                   if (!name.equals("")) _view.sendUpdates(getRobotName(comp.getName()), Float.parseFloat(value));
               }
               
               prevValue = value;  
               prevComp = comp;
            }

      }
        
    }
    
    /**
     * Safely stops the current thread from executing (kills the thread).
     */
    public void halt()
    {
        _stop = true;
    }
    
    /**
     * Determines which component is in need of update based upon the controller's button name.
     * @param name Name of the Xbox controller part
     * @return Returns the name of the robot part.
     */
    private String getRobotName(String name)
    {
        switch(name)
        {
            case "x":
                name = "turn";
                break;
            case "y":
                name = "throttle";
                break;
            case "ry":
                name = "moveCamera";
                break;
            case "rx":
                name = "moveCamera";
                break;
            case "A":
                name = "claw";
                break;
            case "B":
                name = "brakes";
                break;
            case "Y":
                name = "headlights";
                break;
            case "rz":
                name = "arm";
                break;
            default:
                name = "";
                break; 
        }         
        return name;
    }

}
