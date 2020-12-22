package frc.robot.controls.ControllerUtils;

public enum Button {
    RESET(3), HAMBURGER(14), X(15), UP(16), DOWN(17);

    public final int id;

    Button(int id) {
        this.id = id;
    }
}
