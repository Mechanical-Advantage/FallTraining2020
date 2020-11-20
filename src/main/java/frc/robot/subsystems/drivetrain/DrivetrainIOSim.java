/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import frckit.simulation.devices.SimSimpleMotorController;
import frckit.simulation.devices.SimSmartMotorController;

public class DrivetrainIOSim implements DrivetrainIO {

    private SimSmartMotorController leftMotor = new SimSmartMotorController(0);
    private SimSmartMotorController rightMotor = new SimSmartMotorController(1);


    private SimpleMotorFeedforward leftModel = new SimpleMotorFeedforward(0, 0, 0);
    private SimpleMotorFeedforward rightModel = new SimpleMotorFeedforward(0, 0, 0);

    private static final double KP = 0;
    private static final double KD = 0;

    @Override
    public void setup() {
        leftMotor.setKp(KP);
        rightMotor.setKp(KP);
        leftMotor.setKd(KD);
        rightMotor.setKd(KD);
    }

    public void setOutputVolts(double leftVoltage, double rightVoltage) {
        leftMotor.setOutputVoltage(leftVoltage);
        rightMotor.setOutputVoltage(rightVoltage);
    }


    @Override
    public void setVelocityRadiansPerSecond(double leftVelocity, double rightVelocity) {
        double leftFFVolts = leftModel.calculate(leftVelocity);
        double rightFFVolts = rightModel.calculate(rightVelocity);
        leftMotor.setVelocitySetpoint(leftVelocity, leftFFVolts);
        rightMotor.setVelocitySetpoint(rightVelocity, rightFFVolts);
    }

}