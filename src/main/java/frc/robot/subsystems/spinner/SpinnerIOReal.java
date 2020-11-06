/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.spinner;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


/**
 * REAL implementation of SpinnerIO
 */
public class SpinnerIOReal implements SpinnerIO {
    TalonSRX motor = new TalonSRX(2);

    @Override
    public void setup() {
        motor.configFactoryDefault();
        motor.configVoltageCompSaturation(12);
        motor.setInverted(false);
    }

    @Override
    public void setOutputVolts(double voltage) {
        motor.set(ControlMode.PercentOutput, voltage/12);
    }
}
