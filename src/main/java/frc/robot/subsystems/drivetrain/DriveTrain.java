/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.function.DoubleSupplier;

public class DriveTrain extends SubsystemBase {
  private DriveTrainIO io;

  private double leftPositionRadians;
  private double rightPositionRadians;
  private double leftVelocityRadiansPerSecond;
  private double rightVelocityRadiansPerSecond;
  private double leftOutputVoltage;
  private double rightOutputVoltage;

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
  }

  public void setPercentOutputs(double leftOutput, double rightOutput) {
    io.setOutputVolts(leftOutput * 12, rightOutput * 12);
  }

  public double getLeftPositionRadians() {
    return leftPositionRadians;
  }

  public double getRightPositionRadians() {
    return rightPositionRadians;
  }

  public double getLeftVelocityRadiansPerSecond() {
    return leftVelocityRadiansPerSecond;
  }

  public double getRightVelocityRadiansPerSecond() {
    return rightVelocityRadiansPerSecond;
  }

  public double getLeftOutputVoltage() {
    return leftOutputVoltage;
  }

  public double getRightOutputVoltage() {
    return rightOutputVoltage;
  }
}
