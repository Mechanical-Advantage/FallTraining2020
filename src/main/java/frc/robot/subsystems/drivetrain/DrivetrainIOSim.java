/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivetrain;

import frckit.simulation.devices.SimSimpleMotorController;

public class DrivetrainIOSim implements DrivetrainIO {

    SimSimpleMotorController leftMotor = new SimSimpleMotorController(0);
    SimSimpleMotorController rightMotor = new SimSimpleMotorController(1);

    @Override
    public void setup() {

    }

    public void setOutputVolts(double leftVoltage, double rightVoltage) {
        leftMotor.setOutputVoltage(leftVoltage);
        rightMotor.setOutputVoltage(rightVoltage);
    }

}