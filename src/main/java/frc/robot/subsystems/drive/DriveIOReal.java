/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Add your docs here.
 */
public class DriveIOReal implements DriveIO {
  TalonSRX leftmotor = new TalonSRX(1);
  TalonSRX rightmotor = new TalonSRX(3);
  TalonSRX leftfollowermotor = new TalonSRX(2);
  TalonSRX rightfollowermotor = new TalonSRX(4);

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

  }

  @Override
  public void setOutputVolts(double leftVoltage, double rightVoltage) {
    leftmotor.set(ControlMode.PercentOutput, leftVoltage / 12);
    rightmotor.set(ControlMode.PercentOutput, rightVoltage / 12);
  }
}
