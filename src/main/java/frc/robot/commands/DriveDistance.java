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
import frc.robot.subsystems.drivetrain.DriveTrain;

public class DriveDistance extends CommandBase {
  private final DriveTrain driveTrain;
  private final double targetDistanceInches;
  private final PIDController controller;

  /**
   * Creates a new DriveDistance.
   */
  public DriveDistance(DriveTrain driveTrain, double targetDistanceInches) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveTrain = driveTrain;
    this.targetDistanceInches = targetDistanceInches;
    addRequirements(driveTrain);

    final double kp;
    final double ki;
    final double kd;
    final double tolerance;
    switch (Constants.getRobot()) {
      case SIM_NOTBOT:
        kp = 6;
        ki = 0;
        kd = 2;
        tolerance = 0.5;
        break;

      default:
        kp = 0;
        ki = 0;
        kd = 0;
        tolerance = 0;
        break;
    }

    controller = new PIDController(kp, ki, kd);
    controller.setTolerance(tolerance);
  }

  private double getDistance() {
    return (driveTrain.getLeftPositionInches() + driveTrain.getLeftPositionInches()) / 2;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    controller.setSetpoint(getDistance() + targetDistanceInches);
    controller.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double velocity = controller.calculate(getDistance());
    driveTrain.setVelocityInchesPerSecond(velocity, velocity);
    SmartDashboard.putNumber("Drive Distance Error (inches)", controller.getPositionError());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.setVelocityInchesPerSecond(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return controller.atSetpoint();
  }
}
