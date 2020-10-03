package frc.robot.subsystems.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


public class DriveTrainIOCTRE implements DriveTrainIO {
    TalonSRX leftA = new TalonSRX(3);
    TalonSRX leftB = new TalonSRX(4);
    TalonSRX rightA = new TalonSRX(1);
    TalonSRX rightB = new TalonSRX(2);


    @Override
    public void setup() {
        // TODO Auto-generated method stub
        leftA.configFactoryDefault();
        leftB.configFactoryDefault();
        leftB.follow(leftA);
        leftA.setInverted(false);
        leftB.setInverted(InvertType.FollowMaster);

        rightA.configFactoryDefault();
        rightB.configFactoryDefault();
        rightB.follow(rightA);
        rightA.setInverted(true);
        rightB.setInverted(InvertType.FollowMaster);

        leftA.configVoltageCompSaturation(12.0);
        rightA.configVoltageCompSaturation(12.0);
    }

    @Override
    public void setLeftOutputVolts(double voltage) {
        // TODO Auto-generated method stub
        leftA.set(ControlMode.PercentOutput, voltage / 12);
    }

    @Override
    public void setRightOutputVolts(double voltage) {
        // TODO Auto-generated method stub
        rightA.set(ControlMode.PercentOutput, voltage / 12);
    }

}