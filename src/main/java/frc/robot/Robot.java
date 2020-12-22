/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * Robot class that is automatically executed upon starting the match
 */
public class Robot extends TimedRobot {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  //get the robot container
  private RobotContainer m_robotContainer;

  @Override
  public void robotInit(){
    m_robotContainer = new RobotContainer();
    RobotContainer.DRIVE.zeroGyro();
    logger.debug("Finished Initializing Robot!");
  }

  /**
   * Cancel all commands on test init
   */
  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * Run the command scheduler no matter what
   */
  @Override
  public void robotPeriodic(){
    CommandScheduler.getInstance().run();
  }
}
