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

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import net.java.games.input.*;
/**
 * Defines a thread to monitor and poll the Xbox Controller.
 * @author Dan Wagner
 */
public class ControllerThread extends Thread {
    
    private Controller _xbox;
    private JTextArea _eventLog;
    private volatile boolean stop = false;
    
    ControllerThread(Controller xbox, JTextArea log)
    {
       this._xbox = xbox;
       this._eventLog = log;
       this.start();
    }
    
    public void run()
    {
        while (!stop)
        {
            boolean update = _xbox.poll();
            EventQueue queue = _xbox.getEventQueue();
            Event e = new Event();
            while (queue.getNextEvent(e))
            {
                StringBuilder buffer = new StringBuilder();
                Component comp = e.getComponent();
                buffer.append(comp.getName()).append(" changed to ");
                float value = e.getValue(); 
                if(comp.isAnalog()) {
                    buffer.append(value);
                } 
                else {
                  if(value==1.0f) {
                     buffer.append("On");
                  } else {
                     buffer.append("Off");
                  }
               }
               _eventLog.append(buffer.toString() + "\n");
               _eventLog.setCaretPosition(_eventLog.getDocument().getLength()); // polling too fast for display?
            }

      }
        
    }
    
    /**
     * Safely stops the current thread from executing (kills the thread).
     */
    public void halt()
    {
        stop = true;
    }
        

}
