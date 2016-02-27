# KSURCTJavaCode

KSURCT basestation code for our robot to compete in the Mercury Robotics competition.

Credit to https://github.com/TooTallNate/Java-WebSocket for Websockets source.

http://ksurct.herokuapp.com

## Getting Started
Guaranteed to run in Linux.  Not sure for other OS.
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
* Connect to the robot by entering it's IP in the text box and pressing Connect button.
* Disconnect from the robot by pressing the Disconnect button.
* When a protobuf packet is received from the robot, the appropriate components are updated to reflect any changes in robot status.
* When a control is pressed, a protobuf packet is sent to the robot.


#### Controls

* The W, A, S, D keys control motor power settings.
  * W increases the throttle on both motors
  * A sets up a left turn
  * S decreases the throttle on both motors
  * D sets up a right turn.
* The L key toggles the headlights of the robot on and off.
  * Current status signified by the button on the GUI  
* The Q key engages the servo on the robot's arm to open.

#### Events
* In the case of a disconnection from the robot, a timeout of ten seconds is initiated in order to prevent hammering the robot with connect requests.
* The GUI will attempt to connect to the robot if it has a valid IP address entered.
  * An invalid IP address will result in no action taken by the GUI.
