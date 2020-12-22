package frc.robot.controls.ControllerUtils;

//Enum for Axis ids
public enum Axis {
    RIGHT_X(1), RIGHT_Y(0), LEFT_X(2), LEFT_Y(5), TUNER(6), LEFT_BACK(4), RIGHT_BACK(3);

    public final int id;

    Axis(int id) {
        this.id = id;
    }
}