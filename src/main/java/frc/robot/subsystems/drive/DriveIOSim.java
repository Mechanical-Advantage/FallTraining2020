/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import frckit.simulation.devices.SimSimpleMotorController;

/**
 * Add your docs here.
 */
public class DriveIOSim implements DriveIO {
    SimSimpleMotorController leftMotor = new SimSimpleMotorController(0);
    SimSimpleMotorController rightMotor = new SimSimpleMotorController(1);

    public void setup() {
    }

    @Override
    public void setOutputVolts(double leftVoltage, double rightVoltage) {
        leftMotor.setOutputVoltage(leftVoltage);
        rightMotor.setOutputVoltage(rightVoltage);
    }
}
