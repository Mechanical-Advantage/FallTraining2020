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
  private final PIDController pidcontroller;
  private final double driveDistance;

  private double getAverageEncoderValues() {
    return (drivetrain.getLeftEncoderValuesInches() + drivetrain.getRightEncoderValuesInches()) / 2;
  }

  /**
   * Creates a new DriveDistance.
   */
  public DriveDistance(Drivetrain drivetrain, double driveDistance) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    addRequirements(drivetrain);
    this.driveDistance = driveDistance;
    final double kp;
    final double ki;
    final double kd;
    final double tolerance;

    switch (Constants.getRobot()) {
    case SIM_NOTBOT:
      kp = 8;
      ki = 0;
      kd = 2;
      tolerance = .1;
      break;

    case ROBOT_NOTBOT:
      kp = 1;
      ki = 0;
      kd = 0;
      tolerance = .1;
      break;

    default:
      kp = 1;
      ki = 0;
      kd = 0;
      tolerance = .5;
    }
    pidcontroller = new PIDController(kp, ki, kd);
    pidcontroller.setTolerance(tolerance);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pidcontroller.setSetpoint(getAverageEncoderValues() + driveDistance);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double calculatedVelocity = pidcontroller.calculate(getAverageEncoderValues());
    drivetrain.setVelocityInchesPerSecond(calculatedVelocity, calculatedVelocity);
    SmartDashboard.putNumber("Drive Distance Error (inches)", pidcontroller.getPositionError());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.setVelocityInchesPerSecond(0, 0);
    pidcontroller.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return pidcontroller.atSetpoint();
  }
}
