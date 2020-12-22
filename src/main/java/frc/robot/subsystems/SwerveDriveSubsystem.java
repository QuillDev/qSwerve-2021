package frc.robot.subsystems;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.kauailabs.navx.frc.AHRS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.strykeforce.thirdcoast.swerve.SwerveDrive;
import org.strykeforce.thirdcoast.swerve.SwerveDriveConfig;
import org.strykeforce.thirdcoast.swerve.Wheel;
import org.strykeforce.thirdcoast.telemetry.TelemetryService;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class SwerveDriveSubsystem extends SubsystemBase {

    // Declare some constants for our swerve system
    private final double DRIVE_SETPOINT_MAX = 0.0;
    private final double ROBOT_LENGTH = 1.0; // TODO check the measurement unit we're using (likely metres!);
    private final double ROBOT_WIDTH = 1.0;

    // Create the swerves telemetry
    private final TelemetryService telemetryService = RobotContainer.TELEMETRY;
    private final SwerveDrive swerve = getSwerve(); //TODO implement

    //setup a logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Create a new swerve drive subsystem and set it's defualt properties
     */
    public SwerveDriveSubsystem(){
        this.swerve.setFieldOriented(true);
        this.zeroAzimuths();
    }

    /**
     * Drive the swerve drive using the fwd, str and yaw we need!
     * @param fwd speed on Y axis
     * @param str speed on X axis
     * @param yaw rotation
     */
    public void drive(double fwd, double str, double yaw){
        this.swerve.drive(fwd, str, yaw);
    }
    /**
     * Zero the azimuth encoders
     */
    public void zeroAzimuths(){
        this.swerve.zeroAzimuthEncoders();
    }

    /**
     * Zeroes the swerve drives gyro
     */
    public void zeroGyro(){

        //set the gyros adjusted angle to zero
        var gyro = this.swerve.getGyro();
        gyro.setAngleAdjustment(0);

        //calculate the adjusted angle adn set the angle adjustment to it
        var adjustedAngle = gyro.getAngle() % 360;
        gyro.setAngleAdjustment(-adjustedAngle);
        logger.info("Resetting Gyro: ({})", adjustedAngle);
    }
    
    /**
     * Get the swerve drive and configure it
     * @return the swerve drive
     */
    private SwerveDrive getSwerve(){
        var config = new SwerveDriveConfig();
        config.wheels = getWheels(4);
        config.gyro = new AHRS(SPI.Port.kMXP);
        config.length = ROBOT_LENGTH;
        config.width = ROBOT_WIDTH;
        config.gyroLoggingEnabled = true;
        config.summarizeTalonErrors = false;

        return new SwerveDrive(config);
    }

    /**
     * Method that generates the swerve drives wheel configurations "Automatically", returns the drives wheels!
     * @return the swerve drives swerve wheels
     */
    private Wheel[] getWheels(int wheelCount){

        //Config for azimuth motor on the Swerve.
        //TODO Encoders should be in phase with the motor spinning!
        var azimuthConfig = new TalonSRXConfiguration();
        azimuthConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;
        azimuthConfig.continuousCurrentLimit = 10;
        azimuthConfig.peakCurrentDuration = 0;
        azimuthConfig.peakCurrentLimit = 0;
        azimuthConfig.slot0.kP = 20;
        azimuthConfig.slot0.kI = 0.0;
        azimuthConfig.slot0.kD = 300.0;
        azimuthConfig.slot0.kF = 0.0;
        azimuthConfig.slot0.integralZone = 0;
        azimuthConfig.slot0.allowableClosedloopError = 0;
        azimuthConfig.motionAcceleration = 10_000;
        azimuthConfig.motionCruiseVelocity = 800;
        azimuthConfig.peakOutputForward = 0.75;
        azimuthConfig.peakOutputReverse = -0.75;

        //Setup the drive motor config
        //TODO Look and see if this is right, it doesn't really look like it is...
        var driveConfig = new TalonSRXConfiguration();
        driveConfig.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;
        driveConfig.continuousCurrentLimit = 40;
        driveConfig.peakCurrentDuration = 0;
        driveConfig.peakCurrentLimit = 0;

        //create the wheels
        ArrayList<Wheel> wheels = new ArrayList<>();

        //iterate through the wheels
        for(int wheelIndex = 0; wheelIndex < wheelCount; wheelIndex++){

            // ID Conventions for azimuth and drive motor
            // DRIVE_ID = WHEEL_ID + 10
            // EX: IF WHEEL_ID is 8 THEN THE DRIVE_ID WILL BE 18!

            //setup the wheel at the given index.
            var azimuthTalon = new TalonSRX(wheelIndex); //create the azimuth talon
            var driveTalon = new TalonSRX(wheelIndex + 10); //create the drive talon with an index of + 10 to the steering talon

            //configure the drive and azimuth talons with their respective configurations
            azimuthTalon.configAllSettings(azimuthConfig);
            driveTalon.configAllSettings(driveConfig);

            //setup the neutral (inactive) mode of the drive talon
            driveTalon.setNeutralMode(NeutralMode.Brake);

            //add them to the telemtry service
            this.telemetryService.register(driveTalon);
            this.telemetryService.register(azimuthTalon);
            
            //register the talons to the telemetry service
            //TODO See if we have to create an extension class of wheel for Spark Max's!
            var wheel = new Wheel(azimuthTalon, driveTalon, DRIVE_SETPOINT_MAX);
            wheels.add(wheel);
        }
        
        return (Wheel[]) wheels.toArray();
    }

}
