package org.usfirst.frc.team3393.robot;


import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {
	Accelerometer accelerometer;
	double velocity;
	double displacement;
	double kp = 0.03;
	
	Spark pickup = new Spark(4);
	Victor climb = new Victor(5);
	
	RobotDrive myRobot = new RobotDrive(0, 3, 1, 2);
	Joystick right = new Joystick(0);
	Joystick left = new Joystick(1);
	Timer timer = new Timer(); //autonomous Timer
	Timer timerA = new Timer(); //accelerometer timer
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	
	
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		myRobot.setExpiration(0.1);
		gyro = new ADXRS450_Gyro();
		CameraServer.getInstance().startAutomaticCapture();
	}
	
	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	@Override
	public void autonomousInit() {
		timer.reset();
		timer.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	
	public void autonomousPeriodic() {
		double angle = gyro.getAngle();
			while (gyro.getAngle() <= 90) { //turn 90 degrees
				myRobot.tankDrive(0.5, -0.5);
			}
			
			 myRobot.tankDrive(0.0, 0.0);
			 
				Timer.delay(0.001);
		}
	
			  		
	


	

	/**
	 * This function is called once each time the robot enters tele-operated
	 * mode
	 */
	@Override
	public void teleopInit() {
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	
	public void teleopPeriodic() {
		if (right.getRawButton(1)){
			pickup.set(0.3);
		} else if (left.getRawButton(1)) {
			pickup.set(-1.0);	
	  	} else { 
			pickup.set(0.0);
	  	}
		
		if (right.getRawButton(3)) {
			climb.set(1.0);
		} else if (right.getRawButton(2)) {
			climb.set(-1.0);
		} else if (left.getRawButton(3)) {
			climb.set(0.5);
		} else if (left.getRawButton(2)) {
			climb.set(-0.5);
		} else {
			climb.set(0.0);
		}
		if(left.getRawButton(9) || left.getRawButton(9)){
			myRobot.tankDrive(right, right);
		} else if(right.getRawButton(8) || right.getRawButton(8)){
			myRobot.tankDrive(left, left);
		} else {
			myRobot.tankDrive(left, right);
		}
		Timer.delay(0.001);
	}
	
	/**
	 * This function is called periodically during test mode
	 */
//	@Override
//	public void testPeriodic() {
//		LiveWindow.run();
//		}
}
	

