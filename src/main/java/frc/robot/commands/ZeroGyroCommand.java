package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class ZeroGyroCommand extends InstantCommand {

    private static final SwerveDriveSubsystem DRIVE = RobotContainer.DRIVE;

    /**
     * Constructor for a new gyro zeroing command 
     */
    public ZeroGyroCommand(){
        addRequirements(DRIVE);
    }

    //Zero the gyro on execution
    @Override
    public void initialize(){
        DRIVE.zeroGyro();
    }
}
