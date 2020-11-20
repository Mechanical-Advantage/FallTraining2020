/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import frckit.simulation.devices.SimSimpleMotorController;
import frckit.simulation.devices.SimTransmissionEncoder;

/**
 * Add your docs here.
 */
public class DriveIOSim implements DriveIO {
    SimSimpleMotorController leftMotor = new SimSimpleMotorController(0);
    SimSimpleMotorController rightMotor = new SimSimpleMotorController(1);
    SimTransmissionEncoder leftencoder = new SimTransmissionEncoder(0);
    SimTransmissionEncoder rightencoder = new SimTransmissionEncoder(1);

    public void setup() {
        leftencoder.setPositionRadians(0);
        rightencoder.setPositionRadians(0);
    }

    public double getLeftPositionRadians() {
        return leftencoder.getPositionRadians();
    }

    public double getRightPositionRadians() {
        return rightencoder.getPositionRadians();
    }

    @Override
    public void setOutputVolts(double leftVoltage, double rightVoltage) {
        leftMotor.setOutputVoltage(leftVoltage);
        rightMotor.setOutputVoltage(rightVoltage);
    }
}
