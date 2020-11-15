/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivetrain;

import com.kauailabs.navx.frc.AHRS;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;

/**
 * Real implementation of SpinnerIO
 */
public class DriveTrainIOReal implements DriveTrainIO {
    TalonSRX leftMaster = new TalonSRX(1);
    TalonSRX leftFollower = new TalonSRX(2);
    TalonSRX rightMaster = new TalonSRX(3);
    TalonSRX rightFollower = new TalonSRX(4);

    private static final double navXWaitTime = 5; // Maximum number of seconds to wait for the navX to initialize
    private final AHRS ahrs = new AHRS(SerialPort.Port.kUSB);

    private static final double TICKS_TO_RAD = (2.0 * Math.PI) / 1440.0;
    private static final double HUNDRED_MS_TO_S = 10.0;
    private static final double ANGLE_TO_RAD = 360.0 / (2.0 * Math.PI);

    @Override
    public void setup() {
        leftMaster.configFactoryDefault();
        leftMaster.setInverted(true);
        leftMaster.configVoltageCompSaturation(12);

        leftFollower.configFactoryDefault();
        leftFollower.setInverted(InvertType.FollowMaster);
        leftFollower.follow(leftMaster);

        rightMaster.configFactoryDefault();
        rightMaster.setInverted(false);
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

        leftMaster.configNeutralDeadband(0, 100);
        rightMaster.configNeutralDeadband(0, 100);

        // Initialize NavX
        Timer navXTimer = new Timer();
        navXTimer.start();
        while (ahrs.getByteCount() == 0 && navXTimer.get() <= navXWaitTime) {
            System.out.println("waiting");
            Timer.delay(0.01);
        }
        if (navXTimer.get() >= navXWaitTime) {
            DriverStation.reportError("Timeout while waiting for NavX init", false);
        }

        ahrs.zeroYaw();
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

    @Override
    public double getGyroAngleRadians() {
        return ahrs.getAngle() * ANGLE_TO_RAD;
    }
}
