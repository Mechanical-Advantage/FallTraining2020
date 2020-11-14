/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivetrain;

import frckit.simulation.devices.SimSimpleMotorController;
import frckit.simulation.devices.SimTransmissionEncoder;

/**
 * Add your docs here.
 */
public class DrivetrainIOSim implements DrivetrainIO {
    SimSimpleMotorController leftMotor = new SimSimpleMotorController(0);
    SimSimpleMotorController rightMotor = new SimSimpleMotorController(1);
    SimTransmissionEncoder leftEncoder = new SimTransmissionEncoder(0);
    SimTransmissionEncoder rightEncoder = new SimTransmissionEncoder(1);

    @Override
    public void setup() {
        leftEncoder.setPositionRadians(0);
        rightEncoder.setPositionRadians(0);
    }

    @Override
    public void setOutputVolts(double leftVoltage, double rightVoltage) {

        leftMotor.setOutputVoltage(leftVoltage);
        rightMotor.setOutputVoltage(rightVoltage);

    }

    @Override
    public double getLeftPositionRadians() {
        return leftEncoder.getPositionRadians();
    }

    @Override
    public double getRightPositionRadians() {
        return rightEncoder.getPositionRadians();
    }
}
