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
  private double leftPositionRad = 0;
  private double rightPositionRad = 0;
  private DrivetrainIO io;

  private static final double WHEEL_RADIUS = 3;

  public void setInchesPerSec(double leftVelocity, double rightVelocity){
    io.setVelocityRadiansPerSecond(leftVelocity/WHEEL_RADIUS, rightVelocity/WHEEL_RADIUS);
  }

  public double getLeftEncoderValueInInches(){
    return leftPositionRad * WHEEL_RADIUS;
  }

  public double getRightEncoderValueInInches(){
    return rightPositionRad * WHEEL_RADIUS;
  }

  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain(DrivetrainIO io) {
    this.io = io;
    io.setup();
  }

  @Override
  public void periodic() {
     leftPositionRad = io.getLeftEncoderRad();
     rightPositionRad = io.getRightEncoderRad();


     SmartDashboard.putNumber("Left Encoder (inches)",leftPositionRad * WHEEL_RADIUS );
     SmartDashboard.putNumber("Right Encoder (inches)",rightPositionRad * WHEEL_RADIUS);

  }
  
  public void setMotorPercentOut(double leftMotor, double rightMotor) {
    io.setOutputVolts(leftMotor * 12, rightMotor * 12);
  }

}
