/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.spinner;

/**
 * Used in spinner subsystem to interface to real hardware or simulator
 */
public interface SpinnerIO {
    public void setup();

    public void setOutputVolts(double voltage);
}
