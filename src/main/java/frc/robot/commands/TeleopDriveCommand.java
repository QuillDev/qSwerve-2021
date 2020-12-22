package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.controls.DriverControls;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class TeleopDriveCommand extends CommandBase {

    //Create pieces to be shared by all instances of the teleop drive command
    private static final double DEADBAND = 0.05;
    private static final SwerveDriveSubsystem DRIVE = RobotContainer.DRIVE;
    private static final DriverControls controls = RobotContainer.CONTROLS.getDriverControls();

    /**
     * Construct the teleop drive command
     */
    public TeleopDriveCommand(){
        addRequirements(DRIVE);
    }

    @Override
    public void execute(){

        //get fwd, str, and yaw from controller inputs
        double forward = deadband(controls.getForward());
        double strafe = deadband(controls.getStrafe());
        double yaw = deadband(controls.getYaw());

        //drive the swerve with the given input
        DRIVE.drive(-forward, -strafe, -yaw);
    }

    /**
     * Deadband inputs to make sure they're "real" (Eliminate controller stick drift)
     * @param value
     * @return
     */
    private double deadband(double value) {
        if (Math.abs(value) < DEADBAND) return 0.0;
        return value;
      }
}
