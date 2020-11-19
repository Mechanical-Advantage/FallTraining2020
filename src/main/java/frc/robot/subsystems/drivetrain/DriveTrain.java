/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  private static final double WHEEL_RADIUS = 3;

  private DriveTrainIO io;

  private double leftPositionRadians;
  private double rightPositionRadians;
  private double leftVelocityRadiansPerSecond;
  private double rightVelocityRadiansPerSecond;
  private double leftOutputVoltage;
  private double rightOutputVoltage;
  private double leftVelocity;
  private double rightVelocity;

  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain(DriveTrainIO io) {
    this.io = io;
    io.setup();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // Read & store all sensor values
    leftPositionRadians = io.getLeftPositionRadians();
    rightPositionRadians = io.getRightPositionRadians();
    leftVelocityRadiansPerSecond = io.getLeftVelocityRadiansPerSecond();
    rightVelocityRadiansPerSecond = io.getRightVelocityRadiansPerSecond();
    leftOutputVoltage = io.getLeftOutputVoltage();
    rightOutputVoltage = io.getRightOutputVoltage();

    // Log encoder positions
    SmartDashboard.putNumber("Left Encoder Position (radians)", leftPositionRadians);
    SmartDashboard.putNumber("Right Encoder Position (radians)", rightPositionRadians);

    // Log encoder velocity & target
    SmartDashboard.putNumber("Left Encoder Velocity (radians/second)", leftVelocityRadiansPerSecond);
    SmartDashboard.putNumber("Right Encoder Velocity (radians/second)", rightVelocityRadiansPerSecond);
    SmartDashboard.putNumber("Left Target Velocity (radians/second)", leftVelocity);
    SmartDashboard.putNumber("Right Target Velocity (radians/second)", rightVelocity);
  }

  public void setPercentOutputs(double leftOutput, double rightOutput) {
    io.setOutputVolts(leftOutput * 12, rightOutput * 12);
  }

  public void setVelocityInchesPerSecond(double leftVelocity, double rightVelocity) {
    this.leftVelocity = leftVelocity / WHEEL_RADIUS;
    this.rightVelocity = rightVelocity / WHEEL_RADIUS;
    io.setVelocityRadiansPerSecond(this.leftVelocity, this.rightVelocity);
  }

  public double getLeftPositionInches() {
    return leftPositionRadians * WHEEL_RADIUS;
  }

  public double getRightPositionInches() {
    return rightPositionRadians * WHEEL_RADIUS;
  }

  public double getLeftVelocityInchesPerSecond() {
    return leftVelocityRadiansPerSecond * WHEEL_RADIUS;
  }

  public double getRightVelocityInchesPerSecond() {
    return rightVelocityRadiansPerSecond * WHEEL_RADIUS;
  }

  public double getLeftOutputVoltage() {
    return leftOutputVoltage;
  }

  public double getRightOutputVoltage() {
    return rightOutputVoltage;
  }
}
