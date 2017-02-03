package org.usfirst.frc.team3393.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
@SuppressWarnings("unused")
public class Robot extends IterativeRobot {
	Accelerometer accelerometer;
	double velocity;
	double displacement;
	double kp = 0.03;
	
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

	}

	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	@Override
	public void autonomousInit() {
		this.updateDistance();
		timer.reset();
		timer.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	
	public void autonomousPeriodic() {
		double angle = gyro.getAngle();
			while (gyro.getAngle() <= 90) { //turn 90 degrees
				
			}
			
			 myRobot.tankDrive(0.0, 0.0);
			 
				Timer.delay(0.001);
		}
	
			  		
	


	private double getDistance() {
		return Math.abs(this.displacement) * 10;
		
	}
	
	private void updateDistance() {
		this.velocity += this.accelerometer.getY() * this.timerA.get();
		this.displacement += this.velocity * this.timerA.get();
		this.timerA.reset();
	}

	/**
	 * This function is called once each time the robot enters tele-operated
	 * mode
	 */
	@Override
	public void teleopInit() {
		this.updateDistance();
	}

	/**
	 * This function is called periodically during operator control
	 */
	
	public void teleopPeriodic() {
		myRobot.tankDrive(left, right);
		Timer.delay(0.001);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
