/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.spinner;

import frckit.simulation.devices.SimSimpleMotorController;

/**
 * Simulator implementation of SpinnerIO
 */
public class SpinnerIOSim implements SpinnerIO {
    SimSimpleMotorController motor = new SimSimpleMotorController(2);

    @Override
    public void setup() {
    }

    @Override
    public void setOutputVolts(double voltage) {
        motor.setOutputVoltage(voltage);
    }
}
