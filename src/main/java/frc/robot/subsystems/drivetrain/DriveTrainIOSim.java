/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import frckit.simulation.devices.SimIMU;
import frckit.simulation.devices.SimSmartMotorController;
import frckit.simulation.devices.SimTransmissionEncoder;

/**
 * Simulator implementation of DriveTrainIO
 */
public class DriveTrainIOSim implements DriveTrainIO {
    SimSmartMotorController left = new SimSmartMotorController(0);
    SimSmartMotorController right = new SimSmartMotorController(1);
    SimTransmissionEncoder leftEncoder = new SimTransmissionEncoder(0);
    SimTransmissionEncoder rightEncoder = new SimTransmissionEncoder(1);

    private SimIMU imu = new SimIMU(0);

    private SimpleMotorFeedforward leftModel = new SimpleMotorFeedforward(0.6, 0.172, 0.124);
    private SimpleMotorFeedforward rightModel = new SimpleMotorFeedforward(0.6, 0.172, 0.124);

    private static final double KP = 3.73;
    private static final double KD = 0;

    @Override
    public void setup() {
        imu.setYaw(0);

        leftEncoder.setPositionRadians(0);
        rightEncoder.setPositionRadians(0);

        left.setKp(KP);
        right.setKp(KP);
        left.setKd(KD);
        right.setKd(KD);
    }

    @Override
    public void setOutputVolts(double leftVoltage, double rightVoltage) {
        left.setOutputVoltage(leftVoltage);
        right.setOutputVoltage(rightVoltage);
    }

    @Override
    public void setVelocityRadiansPerSecond(double leftVelocity, double rightVelocity) {
        double leftFFVolts = leftModel.calculate(leftVelocity);
        double rightFFVolts = rightModel.calculate(rightVelocity);
        left.setVelocitySetpoint(leftVelocity, leftFFVolts);
        right.setVelocitySetpoint(rightVelocity, rightFFVolts);
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
    public double getGyroRadians() {
        return imu.getYawRadians() * -1;
    }
}
