/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Add your docs here.
 */
public class DrivetrainIOReal implements DrivetrainIO {

    private static final double TICKS_TO_RAD = (2.0 * Math.PI) / 1440.0;

    TalonSRX leftLeaderMotor = new TalonSRX(1);
    TalonSRX rightLeaderMotor = new TalonSRX(3);
    TalonSRX leftFollowerMotor = new TalonSRX(2);
    TalonSRX rightFollowerMotor = new TalonSRX(4);

    @Override
    public void setup() {
        leftLeaderMotor.configFactoryDefault();
        rightLeaderMotor.configFactoryDefault();
        leftFollowerMotor.configFactoryDefault();
        rightFollowerMotor.configFactoryDefault();
        leftLeaderMotor.configVoltageCompSaturation(12);
        rightLeaderMotor.configVoltageCompSaturation(12);

        rightFollowerMotor.setInverted(InvertType.FollowMaster);
        rightLeaderMotor.setInverted(true);
        leftFollowerMotor.setInverted(InvertType.FollowMaster);
        leftLeaderMotor.setInverted(true);

        rightLeaderMotor.setInverted(true);
        leftLeaderMotor.setInverted(false);
        rightFollowerMotor.setInverted(true);
        leftFollowerMotor.setInverted(false);

        rightFollowerMotor.follow(rightLeaderMotor);
        leftFollowerMotor.follow(leftLeaderMotor);

        rightLeaderMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 100);
        leftLeaderMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 100);
        rightLeaderMotor.setSensorPhase(false);
        leftLeaderMotor.setSensorPhase(false);
        rightLeaderMotor.setSelectedSensorPosition(0);
        leftLeaderMotor.setSelectedSensorPosition(0);

    }

    @Override
    public void setOutputVolts(double leftVoltage, double rightVoltage) {
        leftLeaderMotor.set(ControlMode.PercentOutput, leftVoltage / 12);
        rightLeaderMotor.set(ControlMode.PercentOutput, rightVoltage / 12);
    }

    public double getLeftPositionRadians() {
        return leftLeaderMotor.getSelectedSensorPosition() * TICKS_TO_RAD;

    }

    public double getRightPositionRadians() {
        return rightLeaderMotor.getSelectedSensorPosition() * TICKS_TO_RAD;

    }
}
