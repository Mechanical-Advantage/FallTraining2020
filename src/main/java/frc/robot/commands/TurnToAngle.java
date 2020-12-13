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

public class TurnToAngle extends CommandBase {
  private final DriveTrain driveTrain;
  private final double targetAngleRadians;
  private final PIDController controller;

  /**
   * Creates a new DriveDistance.
   */
  public TurnToAngle(DriveTrain driveTrain, double targetDistanceInches) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveTrain = driveTrain;
    this.targetAngleRadians = targetDistanceInches;
    addRequirements(driveTrain);

    final double kp;
    final double ki;
    final double kd;
    final double tolerance;
    switch (Constants.getRobot()) {
      case SIM_NOTBOT:
        kp = 120;
        ki = 0;
        kd = 8;
        tolerance = 0.015;
        break;

      default:
        kp = 40;
        ki = 0;
        kd = 1;
        tolerance = 0.02;
        break;
    }

    controller = new PIDController(kp, ki, kd);
    controller.setTolerance(tolerance);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    controller.setSetpoint(driveTrain.getGyroRadians() + targetAngleRadians);
    controller.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double velocity = controller.calculate(driveTrain.getGyroRadians());
    driveTrain.setVelocityInchesPerSecond(velocity, velocity * -1);
    SmartDashboard.putNumber("Turn to Angle Error (radians)", controller.getPositionError());
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
