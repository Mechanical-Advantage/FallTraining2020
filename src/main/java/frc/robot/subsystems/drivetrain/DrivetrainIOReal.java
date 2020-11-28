/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivetrain;

import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;

import edu.wpi.first.wpilibj.PIDController;

/**
 * Add your docs here.
 */
public class DrivetrainIOReal implements DrivetrainIO {
    TalonSRX leftLeaderMotor = new TalonSRX(1);
    TalonSRX leftFollowerMotor = new TalonSRX(2);
    TalonSRX rightLeaderMotor = new TalonSRX(3);
    TalonSRX rightFollowerMotor = new TalonSRX(4);
    private final static double TICKS_TO_RAD = (2.0 * Math.PI) / 1440;
    private SimpleMotorFeedforward leftModel = new SimpleMotorFeedforward(0.641, 0.245, 0.0149);
    private SimpleMotorFeedforward rightModel = new SimpleMotorFeedforward(0.626, 0.242, 0.0072);

    @Override
    public void setup() {
        leftLeaderMotor.configFactoryDefault();
        leftFollowerMotor.configFactoryDefault();
        rightLeaderMotor.configFactoryDefault();
        rightFollowerMotor.configFactoryDefault();
        leftLeaderMotor.setInverted(false);
        leftFollowerMotor.setInverted(InvertType.FollowMaster);
        rightLeaderMotor.setInverted(true);
        rightFollowerMotor.setInverted(InvertType.FollowMaster);
        leftFollowerMotor.follow(leftLeaderMotor);
        rightFollowerMotor.follow(rightLeaderMotor);
        rightLeaderMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 100);
        leftLeaderMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 100);
        leftLeaderMotor.setSensorPhase(false);
        rightLeaderMotor.setSensorPhase(false);
        rightLeaderMotor.setSelectedSensorPosition(0);
        leftLeaderMotor.setSelectedSensorPosition(0);
    }

    @Override
    public void setOutputVolts(double leftVoltage, double rightVoltage) {
        leftLeaderMotor.set(ControlMode.PercentOutput, leftVoltage);
        rightLeaderMotor.set(ControlMode.PercentOutput, rightVoltage);

    }

    @Override
    public double getLeftPositionRadians() {
        return leftLeaderMotor.getSelectedSensorPosition() * TICKS_TO_RAD;
    }

    @Override
    public double getRightPositionRadians() {
        return rightLeaderMotor.getSelectedSensorPosition() * TICKS_TO_RAD;
    }

    public void setVelocityRadiansPerSecond(double leftVelocity, double rightVelocity) {
        double leftFFVolts = leftModel.calculate(leftVelocity);
        double rightFFVolts = rightModel.calculate(rightVelocity);
        leftLeaderMotor.set(ControlMode.Velocity, (leftVelocity / TICKS_TO_RAD) / 10, DemandType.ArbitraryFeedForward,
                leftFFVolts / 12);
        rightLeaderMotor.set(ControlMode.Velocity, (rightVelocity / TICKS_TO_RAD) / 10, DemandType.ArbitraryFeedForward,
                rightFFVolts / 12);
    }

}
