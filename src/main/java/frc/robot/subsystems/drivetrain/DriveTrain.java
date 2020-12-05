/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  private DrivetrainIO io;
  private double leftRadians = 0;
  private double rightRadians = 0;
  private static final double WHEEL_RADIUS = 3;
  public double gyroYaw = 0;

  public Drivetrain(DrivetrainIO io) {
    this.io = io;
    io.setup();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    leftRadians = io.getLeftPositionRadians();
    rightRadians = io.getRightPositionRadians();
    gyroYaw = io.getGyroYawRadians();
    SmartDashboard.putNumber("Left Encoder", io.getLeftPositionRadians() * WHEEL_RADIUS);
    SmartDashboard.putNumber("Right Encoder", io.getRightPositionRadians() * WHEEL_RADIUS);
  }

  public void setMotorOutput(double leftOutput, double rightOutput) {
    // System.out.println(output);
    io.setOutputVolts(leftOutput * 12, rightOutput * 12);
  }

  public void setVelocityInchesPerSecond(double leftVelocity, double rightVelocity) {
    io.setVelocityRadiansPerSecond(leftVelocity, rightVelocity);
  };

  public double getLeftEncoderInches() {
    return leftRadians * WHEEL_RADIUS;
  }

  public double getRightEncoderInches() {
    return rightRadians * WHEEL_RADIUS;
  }

  public double getGyroYawRadians() {
    return gyroYaw;
  }

}
