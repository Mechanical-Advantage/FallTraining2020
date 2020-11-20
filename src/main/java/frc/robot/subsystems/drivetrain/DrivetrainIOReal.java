/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;

public class DrivetrainIOReal implements DrivetrainIO {

    TalonSRX leftLeaderMotor = new TalonSRX(1);
    TalonSRX rightLeaderMotor = new TalonSRX(3);
    TalonSRX leftFollowerMotor = new TalonSRX(2);
    TalonSRX rightFollowerMotor = new TalonSRX(4);

    private static final double TICKS_TO_RAD = (2.0 * Math.PI) / 1440.0;


    private SimpleMotorFeedforward leftModel = new SimpleMotorFeedforward(0.641, 0.245, 0.0149);
    private SimpleMotorFeedforward rightModel = new SimpleMotorFeedforward(0.626, 0.242, 0.0072);

    private static final double KP = 5;
    private static final double KD = 40;

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
        leftLeaderMotor.setInverted(false);

        rightFollowerMotor.follow(rightLeaderMotor);
        leftFollowerMotor.follow(leftLeaderMotor);

        leftLeaderMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 1000);
        rightLeaderMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 1000);
        leftLeaderMotor.setSelectedSensorPosition(0);
        rightLeaderMotor.setSelectedSensorPosition(0);
        leftLeaderMotor.setSensorPhase(false);
        rightLeaderMotor.setSensorPhase(false);

        // Config closed loop
        leftLeaderMotor.config_kP(0, KP);
        rightLeaderMotor.config_kP(0, KP);
        leftLeaderMotor.config_kD(0, KD);
        rightLeaderMotor.config_kD(0, KD);

        // Configure status frame rate
        leftLeaderMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 5, 1000);
        rightLeaderMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 5, 1000);

        // Configure velocity measurement period and window
        leftLeaderMotor.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_50Ms, 1000);
        rightLeaderMotor.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_50Ms, 1000);
        leftLeaderMotor.configVelocityMeasurementWindow(1, 1000);
        rightLeaderMotor.configVelocityMeasurementWindow(1, 1000);

    }

    @Override
    public void setOutputVolts(double leftVoltage, double rightVoltage) {
        leftLeaderMotor.set(ControlMode.PercentOutput, leftVoltage / 12);
        rightLeaderMotor.set(ControlMode.PercentOutput, rightVoltage / 12);
    }

    @Override
    public void setVelocityRadiansPerSecond(double leftVelocity, double rightVelocity) {
        double leftFFVolts = leftModel.calculate(leftVelocity);
        double rightFFVolts = rightModel.calculate(rightVelocity);
        leftLeaderMotor.set(ControlMode.Velocity, (leftVelocity / TICKS_TO_RAD) / 10, DemandType.ArbitraryFeedForward,
                leftFFVolts / 12);
        rightLeaderMotor.set(ControlMode.Velocity, (rightVelocity / TICKS_TO_RAD) / 10, DemandType.ArbitraryFeedForward,
                rightFFVolts / 12);
    }

}