/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


/**
 * Add your docs here.
 */
public class DrivetrainIOReal implements DrivetrainIO {
    TalonSRX leftLeaderMotor = new TalonSRX(3);
    TalonSRX rightLeaderMotor = new TalonSRX(1);
    TalonSRX leftFollowerMotor = new TalonSRX(4);
    TalonSRX rightFollowerMotor = new TalonSRX(2);

    private static final double TICKS_TO_RAD = (2.0 * Math.PI) / 1440.0;

    public double getLeftEncoderRad(){
        return leftLeaderMotor.getSelectedSensorPosition() * TICKS_TO_RAD;
    }
    public double getRightEncoderRad(){
        return rightLeaderMotor.getSelectedSensorPosition() * TICKS_TO_RAD;
    }

    @Override
    public void setup() {
        leftLeaderMotor.configFactoryDefault();
        rightLeaderMotor.configFactoryDefault();
        leftFollowerMotor.configFactoryDefault();
        rightFollowerMotor.configFactoryDefault();
        leftLeaderMotor.configVoltageCompSaturation(12);
        rightLeaderMotor.configVoltageCompSaturation(12);
        rightLeaderMotor.setInverted(true);
        rightFollowerMotor.setInverted(InvertType.FollowMaster);
        leftLeaderMotor.setInverted(false);
        leftFollowerMotor.setInverted(InvertType.FollowMaster);
        rightFollowerMotor.follow(rightLeaderMotor);
        leftFollowerMotor.follow(leftLeaderMotor);
        
        leftLeaderMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 100);
        rightLeaderMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 100);
        leftLeaderMotor.setSensorPhase(false);
        rightLeaderMotor.setSensorPhase(false);
        leftLeaderMotor.setSelectedSensorPosition(0);
        rightLeaderMotor.setSelectedSensorPosition(0);
    }

    @Override
    public void setOutputVolts(double leftVoltage, double rightVoltage) {
        leftLeaderMotor.set(ControlMode.PercentOutput, leftVoltage/12);
        rightLeaderMotor.set(ControlMode.PercentOutput, rightVoltage/12);
    }
}
