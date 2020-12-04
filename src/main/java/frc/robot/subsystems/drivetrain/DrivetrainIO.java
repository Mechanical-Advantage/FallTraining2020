/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivetrain;

/**
 * Add your docs here.
 */
public interface DrivetrainIO {
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

    public default void setVelocityRadiansPerSecond(double leftVelocity, double rightVelocity) {
    };

    public default double getGyroRads() {
        return 0;
    };
}
