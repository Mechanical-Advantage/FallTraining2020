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
import frc.robot.subsystems.drive.Drive;

public class DriveDistance extends CommandBase {
  private Drive drive;
  private double distance;
  private PIDController pidController;

  /**
   * Creates a new DriveDistance.
   */
  public DriveDistance(Drive drive, double distance) {
    this.drive = drive;
    this.distance = distance;
    addRequirements(drive);
    final double kp;
    final double ki;
    final double kd;
    final double tolerance;
    switch (Constants.getRobot()) {
      case SIM_NOTBOT:
        kp = 30;
        kd = 5;
        ki = 0;
        tolerance = 0;

        break;

      default:
        kp = 1;
        kd = 0;
        ki = 0;
        tolerance = 0.5;
    }
    pidController = new PIDController(kp, ki, kd);
    pidController.setTolerance(tolerance);

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pidController.setSetpoint(getDistance() + distance);

  }

  private double getDistance() {
    return (drive.getLeftPositionInches() + drive.getRightPositionInches()) / 2;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double velocity = pidController.calculate(getDistance());
    drive.setVelocityInchesPerSecond(velocity, velocity);
    SmartDashboard.putNumber("Drive Distance Erorr", pidController.getPositionError());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.setVelocityInchesPerSecond(0, 0);
    pidController.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return pidController.atSetpoint();
  }
}
