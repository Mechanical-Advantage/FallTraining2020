/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
  private DriveIO io;
  private double leftEncoderValue = 0;
  private double rightEncoderValue = 0;
  private static final double WHEELRADIUS = 3;

  /**
   * Creates a new Drive.
   */
  public Drive(DriveIO io) {
    this.io = io;
    io.setup();
  }

  public void setSpeed(double leftMotor, double rightMotor) {
    io.setOutputVolts(leftMotor * 12, rightMotor * 12);
  }

  public double getLeftPositionInches() {
    return leftEncoderValue * WHEELRADIUS;
  }

  public double getRightPositionInches() {
    return rightEncoderValue * WHEELRADIUS;
  }

  public void setVelocityInchesPerSecond(double leftVelocity, double rightVelocity) {
    io.setVelocityRadiansPerSecond(leftVelocity / WHEELRADIUS, rightVelocity / WHEELRADIUS);
  };

  @Override
  public void periodic() {

    leftEncoderValue = io.getLeftPositionRadians();
    rightEncoderValue = io.getRightPositionRadians();

    SmartDashboard.putNumber("leftPosition", leftEncoderValue);
    SmartDashboard.putNumber("rightPosition", rightEncoderValue);
    // This method will be called once per scheduler run
  }
}
