/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.spinner;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

/**
 * Add your docs here.
 */
public class SpinnerIORobot implements SpinnerIO {

    private VictorSPX motorController = new VictorSPX(10);

    @Override
    public void setup() {

    }

    public void setOutputVolts(double voltage) {

        motorController.set(ControlMode.PercentOutput, voltage / 12);

    }

}
