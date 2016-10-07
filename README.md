# Basestation
KSURCT basestation code for our robot to compete in the 2016 Mercury Robotics competition.

Credit to https://github.com/TooTallNate/Java-WebSocket for WebSockets source.

[comment]: # "http://ksurct.herokuapp.com -- change to updated website URL on OrgSync."

## Getting Started

* Guaranteed to run in Linux and Windows.  Not sure for other OS.
* Download the latest [NetBeans and JDK cobundle](http://www.oracle.com/technetwork/java/javase/downloads/index.html).
* Locate the Main.java file and protobuf-proto folder via the help of Netbeans.
* Compile and run the program.

## Python-Rev Branch Notes

* Install Python 3.5 version.
* Run the `sudo pip install websocket-client` command.

## Usage

* The GUI contains several components:
  * Motor power levels
  * IR sensor readings
  * Error log
  * IP address box
    * Static IPs are hard-coded.
  * Connect & disconnect buttons
  * Hand servo degree labels
  * Up/Down camera position adjustment sliding scale
  * Left/Right camera position adjustment sliding scale

[comment]: # "Open terminal and type `avahi-browse -art | grep raspberry -A 3` to locate the IP."
* Start video streaming with `nc.traditional 10.243.81.158 9001 | mplayer -fps 60 -cache 768 -` code.
* Connect to the robot by pressing the connect button.
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
* The X key will open or close the claw (if it is closed or open, respectively).

#### Events
* In the case of a disconnection from the robot, the GUI will display an error message and allow a retry.
  * An invalid IP address will result in no action taken by the GUI.
