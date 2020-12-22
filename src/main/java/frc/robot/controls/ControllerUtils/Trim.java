package frc.robot.controls.ControllerUtils;

public enum Trim {
    LEFT_Y_POS(7), LEFT_Y_NEG(6), LEFT_X_POS(8), LEFT_X_NEG(9), RIGHT_X_POS(10), RIGHT_X_NEG(11), RIGHT_Y_POS(12),
    RIGHT_Y_NEG(13);

    public final int id;

    Trim(int id) {
        this.id = id;
    }
}
