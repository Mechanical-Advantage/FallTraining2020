/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
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

  }

  public void setMotorpercentOut(double leftMotor, double rightMotor) {
    io.setOutputVolts(leftMotor * 12, rightMotor * 12);
  }

}
