/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.Drivetrain;
//import sun.nio.ch.IOUtil;

import com.kauailabs.navx.IMUProtocol.GyroUpdate;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnToAngle extends CommandBase {
  private final Drivetrain drivetrain;
  private final PIDController pidcontroller;
  private final double turnToAngle;

  /**
   * Creates a new TurnDirection.
   */
  public TurnToAngle(Drivetrain drivetrain, double turnToAngle) {
    this.drivetrain = drivetrain;
    addRequirements(drivetrain);
    this.turnToAngle = turnToAngle;
    final double kp;
    final double ki;
    final double kd;
    final double tolerance;

    switch (Constants.getRobot()) {
    case SIM_NOTBOT:
      kp = 100;
      ki = 0;
      kd = 10;
      tolerance = 0;
      break;

    case ROBOT_NOTBOT:
      kp = 1;
      ki = 0;
      kd = 0;
      tolerance = 0;
      break;

    default:
      kp = 1;
      ki = 0;
      kd = 0;
      tolerance = 0;
    }
    pidcontroller = new PIDController(kp, ki, kd);
    pidcontroller.setTolerance(tolerance);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pidcontroller.setSetpoint(drivetrain.getGyroRads() + turnToAngle);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double calculatedVelocity = pidcontroller.calculate(drivetrain.getGyroRads());
    drivetrain.setVelocityInchesPerSecond(-calculatedVelocity, calculatedVelocity);
    SmartDashboard.putNumber("Gyro Error (radians)", pidcontroller.getPositionError());
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
