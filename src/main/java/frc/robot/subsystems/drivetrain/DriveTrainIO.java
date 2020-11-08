/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivetrain;

/**
 * Used in drive train subsystem to interface to real hardware or simulator
 */
public interface DriveTrainIO {
    public default void setup() {
    };

    public default void setOutputVolts(double leftVoltage, double rightVoltage) {
    };

    public default double getLeftPositionRadians() {
        return 0;
    };

    public default double getRightPositionRadians() {
        return 0;
    };

    public default double getLeftVelocityRadiansPerSecond() {
        return 0;
    };

    public default double getRightVelocityRadiansPerSecond() {
        return 0;
    };

    public default double getLeftOutputVoltage() {
        return 0;
    };

    public default double getRightOutputVoltage() {
        return 0;
    };

    public default double getGyroAngleRadians() {
        return 0;
    }
}
