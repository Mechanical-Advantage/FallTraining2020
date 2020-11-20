/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class DrivetrainIOReal implements DrivetrainIO {

    TalonSRX leftLeaderMotor = new TalonSRX(1);
    TalonSRX rightLeaderMotor = new TalonSRX(3);
    TalonSRX leftFollowerMotor = new TalonSRX(2);
    TalonSRX rightFollowerMotor = new TalonSRX(4);

    @Override
    public void setup() {
        leftLeaderMotor.configFactoryDefault();
        rightLeaderMotor.configFactoryDefault();
        leftFollowerMotor.configFactoryDefault();
        rightFollowerMotor.configFactoryDefault();
        leftLeaderMotor.configVoltageCompSaturation(12);
        rightLeaderMotor.configVoltageCompSaturation(12);

        rightFollowerMotor.setInverted(InvertType.FollowMaster);
        rightLeaderMotor.setInverted(true);
        leftFollowerMotor.setInverted(InvertType.FollowMaster);
        leftLeaderMotor.setInverted(true);

        rightLeaderMotor.setInverted(true);
        leftLeaderMotor.setInverted(false);
        rightFollowerMotor.setInverted(true);
        leftFollowerMotor.setInverted(false);

        rightFollowerMotor.follow(rightLeaderMotor);
        leftFollowerMotor.follow(leftLeaderMotor);

    }

    @Override
    public void setOutputVolts(double leftVoltage, double rightVoltage) {
        leftLeaderMotor.set(ControlMode.PercentOutput, leftVoltage / 12);
        rightLeaderMotor.set(ControlMode.PercentOutput, rightVoltage / 12);
    }
}