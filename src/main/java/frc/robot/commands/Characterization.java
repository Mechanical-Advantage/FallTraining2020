/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.DriveTrain;

public class Characterization extends CommandBase {
  private final DoubleSupplier timestamp;
  private final DriveTrain driveTrain;

  NetworkTableEntry autoSpeedEntry = NetworkTableInstance.getDefault().getEntry("/robot/autospeed");
  NetworkTableEntry telemetryEntry = NetworkTableInstance.getDefault().getEntry("/robot/telemetry");
  NetworkTableEntry rotateEntry = NetworkTableInstance.getDefault().getEntry("/robot/rotate");

  double[] numberArray = new double[10];

  /**
   * Creates a new Characterization.
   */
  public Characterization(DriveTrain driveTrain, DoubleSupplier timestamp) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
    this.driveTrain = driveTrain;
    this.timestamp = timestamp;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Retrieve values to send back before telling the motors to do something
    double now = timestamp.getAsDouble();

    double leftPosition = driveTrain.getLeftPositionRadians();
    double leftRate = driveTrain.getLeftVelocityRadiansPerSecond();

    double rightPosition = driveTrain.getRightPositionRadians();
    double rightRate = driveTrain.getRightVelocityRadiansPerSecond();

    double battery = RobotController.getBatteryVoltage();

    double leftMotorVolts = driveTrain.getLeftOutputVoltage();
    double rightMotorVolts = driveTrain.getRightOutputVoltage();

    double gyroAngleRadians = driveTrain.getGyroAngleRadians();

    double autoSpeed = autoSpeedEntry.getDouble(0);
    driveTrain.setPercentOutputs((rotateEntry.getBoolean(false) ? -1 : 1) * autoSpeed, autoSpeed);

    numberArray[0] = now;
    numberArray[1] = battery;
    numberArray[2] = autoSpeed;
    numberArray[3] = leftMotorVolts;
    numberArray[4] = rightMotorVolts;
    numberArray[5] = leftPosition;
    numberArray[6] = rightPosition;
    numberArray[7] = leftRate;
    numberArray[8] = rightRate;
    numberArray[9] = gyroAngleRadians;

    telemetryEntry.setDoubleArray(numberArray);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.setPercentOutputs(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
