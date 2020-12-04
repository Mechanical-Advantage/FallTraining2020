/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class DriveDistance extends CommandBase {
  private final Drivetrain drivetrain;
  private PIDController pidController;
  private final double driveDistance;

  private double getAverageEncoderValue() {
    return (drivetrain.getLeftEncoderValuesInches() + drivetrain.getRightEncoderValuesInches()) / 2;
  }

  /**
   * Creates a new DriveDistance.
   */
  public DriveDistance(Drivetrain drivetrain, double driveDistance) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    this.driveDistance = driveDistance;
    addRequirements(drivetrain);
    final double Kp;
    final double Ki;
    final double Kd;
    final double tolerance;

    switch (Constants.getRobot()) {
    case SIM_NOTBOT:
      Kp = 3.8;
      Ki = 100.0;
      Kd = 0.005;
      tolerance = 0.1;
      break;
    case ROBOT_NOTBOT:
      Kp = 1;
      Ki = 0;
      Kd = 0;
      tolerance = 0.0;
      break;
    default:
      Kp = 1;
      Ki = 0;
      Kd = 0;
      tolerance = 0.0;
    }

    pidController = new PIDController(Kp, Ki, Kd);

    pidController.setTolerance(tolerance);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    pidController.setSetpoint(getAverageEncoderValue() + driveDistance);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double calculatedVelocity = pidController.calculate(getAverageEncoderValue());

    drivetrain.setVelocityInchesPerSecond(calculatedVelocity, calculatedVelocity);
    SmartDashboard.putNumber("Drive Distance Error (inches)", pidController.getPositionError());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.setVelocityInchesPerSecond(0, 0);
    pidController.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return pidController.atSetpoint();
  }
}
