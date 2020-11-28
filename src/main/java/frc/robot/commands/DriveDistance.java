/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class DriveDistance extends CommandBase {
  /**
   * Creates a new DriveDistance.
   */
  private final Drivetrain drivetrain;
  private double avgPosition, leftVelocity, rightVelocity, distance;
  private PIDController distanceController;
  private int setPointCounter;

  private double getAverageEncoders() {
    return (drivetrain.getLeftEncoderInches() + drivetrain.getRightEncoderInches()) / 2;
  }

  public DriveDistance(Drivetrain drivetrain, double distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    final double Kp;
    final double Ki;
    final double Kd;
    final double tolerance;
    switch (Constants.getRobot()) {
    case SIM_NOTBOT:
      Kp = 2.0;
      Ki = 0.0;
      Kd = .5;
      tolerance = 1.0;

      break;
    case ROBOT_NOTBOT:
      Kp = 1.0;
      Ki = 0.0;
      Kd = 0.0;
      tolerance = 1.5;
      break;

    default:
      Kp = 1.0;
      Ki = 0.0;
      Kd = 0.0;
      tolerance = 2.0;

    }
    this.drivetrain = drivetrain;
    this.distance = distance;
    addRequirements(drivetrain);
    distanceController = new PIDController(Kp, Ki, Kd);
    distanceController.setTolerance(tolerance);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    setPointCounter = 0;
    distanceController.reset();
    distanceController.setSetpoint(getAverageEncoders() + distance);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double velocity = distanceController.calculate(getAverageEncoders());
    drivetrain.setVelocityInchesPerSecond(velocity, velocity);
    SmartDashboard.putNumber("Velocity ", velocity);
    SmartDashboard.putNumber("Distance error ", distanceController.getPositionError());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.setVelocityInchesPerSecond(0, 0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (distanceController.atSetpoint()) {
      setPointCounter++;
    } else {
      setPointCounter = 0;
    }
    if (setPointCounter > 6) {
      return distanceController.atSetpoint();
    } else {
      return false;
    }
  }
}
