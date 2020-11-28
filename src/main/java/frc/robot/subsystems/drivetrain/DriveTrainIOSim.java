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
import frckit.simulation.devices.SimTransmissionEncoder;

/**
 * Add your docs here.
 */
public class DrivetrainIOSim implements DrivetrainIO {
    SimSmartMotorController leftMotor = new SimSmartMotorController(0);
    SimSmartMotorController rightMotor = new SimSmartMotorController(1);
    SimTransmissionEncoder leftEncoder = new SimTransmissionEncoder(0);
    SimTransmissionEncoder rightEncoder = new SimTransmissionEncoder(1);
    private SimpleMotorFeedforward leftModel = new SimpleMotorFeedforward(0.6, 0.172, 0.124);
    private SimpleMotorFeedforward rightModel = new SimpleMotorFeedforward(0.6, 0.172, 0.124);

    private static final double KP = 3.73;
    private static final double KD = 0;

    @Override
    public void setup() {
        leftEncoder.setPositionRadians(0);
        rightEncoder.setPositionRadians(0);

        leftMotor.setKp(KP);
        rightMotor.setKp(KP);
        leftMotor.setKd(KD);
        rightMotor.setKd(KD);
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

    @Override
    public void setVelocityRadiansPerSecond(double leftVelocity, double rightVelocity) {
        double leftFFVolts = leftModel.calculate(leftVelocity);
        double rightFFVolts = rightModel.calculate(rightVelocity);
        leftMotor.setVelocitySetpoint(leftVelocity, leftFFVolts);
        rightMotor.setVelocitySetpoint(rightVelocity, rightFFVolts);

    }
}
