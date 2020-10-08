/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.spinner;

/**
 * Dummy implementation of SpinnerIO if the subsystem doesn't exist
 */
public class SpinnerIODummy implements SpinnerIO {
    @Override
    public void setup() {
    }

    @Override
    public void setOutputVolts(double voltage) {
    }
}
