/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;

/**
 * Add your docs here.
 */
public class DriveIOReal implements DriveIO {
  TalonSRX leftmotor = new TalonSRX(1);
  TalonSRX rightmotor = new TalonSRX(3);
  TalonSRX leftfollowermotor = new TalonSRX(2);
  TalonSRX rightfollowermotor = new TalonSRX(4);

  private SimpleMotorFeedforward leftModel = new SimpleMotorFeedforward(0.641, 0.245, 0.0149);
  private SimpleMotorFeedforward rightModel = new SimpleMotorFeedforward(0.626, 0.242, 0.0072);

  private static final double KP = 5;
  private static final double KD = 40;

  private static final double TICKS_TO_RAD = (2.0 * Math.PI) / 1440.0;


  public void setup() {
    leftmotor.configFactoryDefault();
    leftmotor.setInverted(false);
    leftmotor.configVoltageCompSaturation(12);
    rightmotor.configFactoryDefault();
    rightmotor.setInverted(true);
    rightmotor.configVoltageCompSaturation(12);

    leftfollowermotor.configFactoryDefault();
    leftfollowermotor.setInverted(InvertType.FollowMaster);
    leftfollowermotor.follow(leftmotor);

    rightfollowermotor.configFactoryDefault();
    rightfollowermotor.setInverted(InvertType.FollowMaster);
    rightfollowermotor.follow(rightmotor);

    leftmotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 1000);
    leftmotor.setSensorPhase(false);
    leftmotor.setSelectedSensorPosition(0);

    rightmotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 1000);
    rightmotor.setSensorPhase(false);
    rightmotor.setSelectedSensorPosition(0);


    // Config closed loop
    leftmotor.config_kP(0, KP);
    rightmotor.config_kP(0, KP);
    leftmotor.config_kD(0, KD);
    rightmotor.config_kD(0, KD);

    // Configure status frame rate
    leftmotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 5, 1000);
    rightmotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 5, 1000);

    // Configure velocity measurement period and window
    leftmotor.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_50Ms, 1000);
    rightmotor.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_50Ms, 1000);
    leftmotor.configVelocityMeasurementWindow(1, 1000);
    rightmotor.configVelocityMeasurementWindow(1, 1000);
  }

  public double getLeftPositionRadians() {
    leftmotor.getSelectedSensorPosition();
    return leftmotor.getSelectedSensorPosition() * TICKS_TO_RAD;

  }

  public double getRightPositionRadians() {
    rightmotor.getSelectedSensorPosition();
    return rightmotor.getSelectedSensorPosition() * TICKS_TO_RAD;

  }

  @Override
  public void setOutputVolts(double leftVoltage, double rightVoltage) {
    leftmotor.set(ControlMode.PercentOutput, leftVoltage / 12);
    rightmotor.set(ControlMode.PercentOutput, rightVoltage / 12);
  }

  @Override
  public void setVelocityRadiansPerSecond(double leftVelocity, double rightVelocity) {
    double leftFFVolts = leftModel.calculate(leftVelocity);
    double rightFFVolts = rightModel.calculate(rightVelocity);
    leftmotor.set(ControlMode.Velocity, (leftVelocity / TICKS_TO_RAD) / 10, DemandType.ArbitraryFeedForward,
            leftFFVolts / 12);
    rightmotor.set(ControlMode.Velocity, (rightVelocity / TICKS_TO_RAD) / 10, DemandType.ArbitraryFeedForward,
            rightFFVolts / 12);
  }
}
