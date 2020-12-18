/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivetrain;
import frckit.simulation.devices.SimSimpleMotorController;
/**
 * Add your docs here.
 */
public class DrivetrainIOSim implements DrivetrainIO {
    SimSimpleMotorController leftMotor=new SimSimpleMotorController (0);
    SimSimpleMotorController rightMotor=new SimSimpleMotorController (1);
    public void setOutputVolts (double left, double right) {
        leftMotor.setOutputVoltage (left);
        rightMotor.setOutputVoltage (right);
    } 
}
