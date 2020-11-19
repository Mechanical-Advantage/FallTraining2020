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
    private SimSimpleMotorController leftMotor = new SimSimpleMotorController(0);
    private SimSimpleMotorController rightMotor = new SimSimpleMotorController(1);
    
    private SimTransmissionEncoder leftEncoder = new SimTransmissionEncoder(0);
    private SimTransmissionEncoder rightEncoder = new SimTransmissionEncoder(1);

    public double getLeftEncoderRad(){
        return leftEncoder.getPositionRadians();
    }
    public double getRightEncoderRad(){
        return rightEncoder.getPositionRadians();
    }
    
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
}

    


