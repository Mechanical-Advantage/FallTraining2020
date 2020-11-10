/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivetrain;

import frckit.simulation.devices.SimSimpleMotorController;

/**
 * Add your docs here.
 */
public class DrivetrainIOSim implements DrivetrainIO {
    SimSimpleMotorController motor = new SimSimpleMotorController(2);

    @Override
    public void setup() {
    }

    @Override
    public void setOutputVolts(double leftVoltage, double rightVoltage) {
        motor.setOutputVoltage(leftVoltage);
        motor.setOutputVoltage(rightVoltage);

    }
}
