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

  public Drivetrain(DrivetrainIO io) {
    this.io = io;
    io.setup();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Left Encoder", io.getLeftPositionRadians());
    SmartDashboard.putNumber("Right Encoder", io.getLeftPositionRadians());
  }

  public void setMotorOutput(double leftOutput, double rightOutput) {
    // System.out.println(output);
    io.setOutputVolts(leftOutput * 12, rightOutput * 12);
  }
}
