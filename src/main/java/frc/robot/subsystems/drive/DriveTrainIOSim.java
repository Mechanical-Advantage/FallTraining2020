package frc.robot.subsystems.drive;

import frckit.simulation.devices.SimSimpleMotorController;

public class DriveTrainIOSim implements DriveTrainIO {
    SimSimpleMotorController left = new SimSimpleMotorController(0);
    SimSimpleMotorController right = new SimSimpleMotorController(1);

    @Override
    public void setup() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setLeftOutputVolts(double voltage) {
        // TODO Auto-generated method stub
        left.setOutputVoltage(voltage);
    }

    @Override
    public void setRightOutputVolts(double voltage) {
        // TODO Auto-generated method stub
        right.setOutputVoltage(voltage);
    }

}