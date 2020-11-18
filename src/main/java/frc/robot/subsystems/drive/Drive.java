/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
  private DriveIO io;

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

  @Override
  public void periodic() {

    // This method will be called once per scheduler run
  }
}
