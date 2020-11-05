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
 * Real implementation of SpinnerIO
 */
public class SpinnerIOReal implements SpinnerIO {
    TalonSRX motor = new TalonSRX(0); // Need to update CAN id

    @Override
    public void setup() {
        motor.configFactoryDefault();
        motor.setInverted(false);
        motor.configVoltageCompSaturation(12);
    }

    @Override
    public void setOutputVolts(double voltage) {
        motor.set(ControlMode.PercentOutput, voltage / 12);
    }
}
