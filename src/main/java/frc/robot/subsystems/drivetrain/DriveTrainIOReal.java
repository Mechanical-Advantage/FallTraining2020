/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Real implementation of SpinnerIO
 */
public class DriveTrainIOReal implements DriveTrainIO {
    TalonSRX leftMaster = new TalonSRX(1);
    TalonSRX leftFollower = new TalonSRX(2);
    TalonSRX rightMaster = new TalonSRX(3);
    TalonSRX rightFollower = new TalonSRX(4);

    private static final double TICKS_TO_RAD = (2.0 * Math.PI) / 1440.0;
    private static final double HUNDRED_MS_TO_S = 10.0;

    @Override
    public void setup() {
        leftMaster.configFactoryDefault();
        leftMaster.setInverted(false);
        leftMaster.configVoltageCompSaturation(12);

        leftFollower.configFactoryDefault();
        leftFollower.setInverted(InvertType.FollowMaster);
        leftFollower.follow(leftMaster);

        rightMaster.configFactoryDefault();
        rightMaster.setInverted(true);
        rightMaster.configVoltageCompSaturation(12);

        rightFollower.configFactoryDefault();
        rightFollower.setInverted(InvertType.FollowMaster);
        rightFollower.follow(rightMaster);

        // Configure status frame rate
        leftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 5, 100);
        rightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 5, 100);

        // Configure velocity measurement period and window
        leftMaster.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_50Ms, 100);
        rightMaster.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_50Ms, 100);
        leftMaster.configVelocityMeasurementWindow(1, 100);
        rightMaster.configVelocityMeasurementWindow(1, 100);

        leftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 100);
        rightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 100);
        leftMaster.setSelectedSensorPosition(0);
        rightMaster.setSelectedSensorPosition(0);
        leftMaster.setSensorPhase(false);
        rightMaster.setSensorPhase(false);
    }

    @Override
    public void setOutputVolts(double leftVoltage, double rightVoltage) {
        leftMaster.set(ControlMode.PercentOutput, leftVoltage / 12);
        rightMaster.set(ControlMode.PercentOutput, rightVoltage / 12);
    }

    @Override
    public double getLeftPositionRadians() {
        return leftMaster.getSelectedSensorPosition() * TICKS_TO_RAD;
    }

    @Override
    public double getRightPositionRadians() {
        return rightMaster.getSelectedSensorPosition() * TICKS_TO_RAD;
    }

    @Override
    public double getLeftVelocityRadiansPerSecond() {
        return leftMaster.getSelectedSensorVelocity() * TICKS_TO_RAD * HUNDRED_MS_TO_S;
    }

    @Override
    public double getRightVelocityRadiansPerSecond() {
        return rightMaster.getSelectedSensorVelocity() * TICKS_TO_RAD * HUNDRED_MS_TO_S;
    }

    @Override
    public double getLeftOutputVoltage() {
        return leftMaster.getMotorOutputVoltage();
    }

    @Override
    public double getRightOutputVoltage() {
        return rightMaster.getMotorOutputVoltage();
    }
}
