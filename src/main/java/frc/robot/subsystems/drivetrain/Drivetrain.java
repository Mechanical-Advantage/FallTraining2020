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

  private double rightEncoderValue;
  private double leftEncoderValue;

  private static final double wheelRadius = 3;

  private DrivetrainIO io;

  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain(DrivetrainIO io) {
    this.io = io;
    io.setup();
  }

  @Override
  public void periodic() {
    leftEncoderValue = io.getLeftPositionRadians();
    rightEncoderValue = io.getRightPositionRadians();
    SmartDashboard.putNumber("leftPosition", leftEncoderValue);
    SmartDashboard.putNumber("rightPosition", rightEncoderValue);
    // This method will be called once per scheduler run
  }

  public void setMotorPercentOut(double leftMotor, double rightMotor) {
    io.setOutputVolts(leftMotor * 12, rightMotor * 12);
  }

  public double getLeftEncoderValuesInches() {
    return leftEncoderValue * wheelRadius;
  }

  public double getRightEncoderValuesInches() {
    return rightEncoderValue * wheelRadius;
  }

  public void setVelocityInchesPerSecond(double leftVelocity, double rightVelocity) {
    io.setVelocityRadiansPerSecond(leftVelocity / wheelRadius, rightVelocity / wheelRadius);
  };
}
