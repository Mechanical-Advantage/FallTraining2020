package frc.robot.subsystems.drive;

public interface DriveTrainIO {
    void setup();

    void setLeftOutputVolts(double voltage);
    void setRightOutputVolts(double voltage);
}