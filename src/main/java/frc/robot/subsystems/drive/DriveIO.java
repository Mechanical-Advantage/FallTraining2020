/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

/**
 * Add your docs here.
 */
public interface DriveIO {
    public default void setup() {
    };

    public default void setOutputVolts(double leftVoltage, double rightVoltage) {
    };
}
