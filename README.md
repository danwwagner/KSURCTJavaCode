# KSURCTJavaCode

KSURCT basestation code for our robot to compete in the Mercury Robotics competition.

Credit to https://github.com/TooTallNate/Java-WebSocket for Websockets source.

http://ksurct.herokuapp.com

## Getting Started

* Guaranteed to run in Linux.  Not sure for other OS.
* Download the [NetBeans IDE](https://netbeans.org/downloads/index.html).
* Download the ksurobot directory from the [Embedded Repository](https://github.com/ksurct/MercuryRoboticsEmbedded2016/tree/master/ksurobot).
* Make the protobuf files. Run `make`.
* Add the Main.java's file path as reference into the NetBeans project.
* Compile and run the program.

## Usage

* The GUI contains several components:
  * Motor power levels (obviously > 9000)
  * IR sensor readings labels
  * Error log
  * IP address box
  * Connect & disconnect buttons
  * Hand servo degree labels
  * Up/Down camera position adjustment sliding scale
  * Left/Right camera position adjustment sliding scale
* Open terminal and type `avahi-browse -art | grep raspberry -A 3` to locate the IP.
* Start video streaming with `nc.traditional 192.168.1.138 9001 | mplayer -fps 60 -cache 768 -` code.
* Connect to the robot by entering the IP in the text box and pressing Connect button.
  * Once connected, the error log will be cleared.
* Disconnect from the robot by pressing the Disconnect button.
* When a protobuf packet is received from the robot, the appropriate components are updated to reflect any changes in robot status.
* When a control is pressed, a protobuf packet is sent to the robot.
* To perform a turn, use the Q or E key.
* To perform a zero-point turn, use the A or D key.
* To open the claw, click the "Open Claw" button.

#### Controls

* The W, A, S, D, Q, E keys control motor power settings.
  * W increases the throttle on both motors
  * A sets up a zero-point left turn
  * S decreases the throttle on both motors
  * D sets up a zero-point right turn.
  * Q decreases the throttle on the left motor.
  * E decreases the throttle on the right motor.
* The L key toggles the headlights of the robot on and off.
  * Current status signified by the button on the GUI  
* The Z key causes the robot's arm to launch.
* The V key will increase the wrist angle.
* The C key will decrease the wrist angle.
* The R key causes the robot to initiate the ramp climb sequence.
* The P key will increase the throttle on the right motor.
* The I key will increase the throttle on the left motor.
* The Q key will decrease the throttle on the left motor.
* The E key will decrease the throttle on the right motor.

#### Events
* In the case of a disconnection from the robot, the GUI will display an error message and allow a retry.
  * An invalid IP address will result in no action taken by the GUI.
