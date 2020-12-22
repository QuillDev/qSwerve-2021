package frc.team2767.swervetesting.control;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.controls.ControllerUtils.Axis;
import frc.robot.controls.ControllerUtils.Button;
import frc.team2767.swervetesting.command.ZeroGyroCommand;

public class DriverControls {

  private Joystick joystick;

  DriverControls(int portNumber) {
    joystick = new Joystick(portNumber);

    // Drive Commands
    new JoystickButton(joystick, Button.RESET.id).whenPressed(new ZeroGyroCommand());
  }

  /** Left stick X (up-down) axis. */
  public double getForward() {
    return -joystick.getRawAxis(Axis.LEFT_X.id);
  }

  /** Left stick Y (left-right) axis. */
  public double getStrafe() {
    return joystick.getRawAxis(Axis.LEFT_Y.id);
  }

  /** Right stick Y (left-right) axis. */
  public double getYaw() {
    return joystick.getRawAxis(Axis.RIGHT_Y.id);
  }
}
