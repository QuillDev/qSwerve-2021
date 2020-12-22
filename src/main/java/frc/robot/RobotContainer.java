/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.strykeforce.thirdcoast.telemetry.TelemetryController;
import org.strykeforce.thirdcoast.telemetry.TelemetryService;

import edu.wpi.first.wpilibj.RobotBase;
import frc.robot.commands.TeleopDriveCommand;
import frc.robot.controls.Controls;
import frc.robot.subsystems.SwerveDriveSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // create static vars
  public static TelemetryService TELEMETRY;
  public static SwerveDriveSubsystem DRIVE;
  public static Controls CONTROLS;

  public RobotContainer() {

    if (RobotBase.isReal()) {

      // Setup our static variables
      TELEMETRY = new TelemetryService(TelemetryController::new); // telemetry service

      DRIVE = new SwerveDriveSubsystem(); // setup the swerve drive subsystem
      CONTROLS = new Controls();

      // start the telemetry subsystem
      TELEMETRY.start();

      DRIVE.setDefaultCommand(new TeleopDriveCommand()); // set the default drive command
    }
  }

}
