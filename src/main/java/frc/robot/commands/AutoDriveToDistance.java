/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class AutoDriveToDistance extends CommandBase {
  private final Drivetrain drivetrain;
  private final PIDController pidcontrol;
  private final double distance;
  private double getAverageEncoders() {
    return (drivetrain.getLeftEncoderValueInInches() + drivetrain.getRightEncoderValueInInches()) /2;
  }

  private int doneCounter = 0;

  /**
   * Creates a new AutoDriveToDistance.
   */
  public AutoDriveToDistance(Drivetrain drivetrain, double distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    final double Kp;
    final double Ki;
    final double Kd;
    final double PIDTolerance;
      
    
    switch (Constants.getRobot()) {
      case SIM_NOTBOT:
        Kp = 3.0;
        Ki = 0.0;
        Kd = 0.0;

        PIDTolerance = .5;

        break;
      case ROBOT_NOTBOT:
        Kp = 1.0;
        Ki = 0.0;
        Kd = 0.0;

        PIDTolerance = .5;
        break;
      default:
        Kp = 1.0;
        Ki = 0.0;
        Kd = 0.0;

        PIDTolerance = .5;

    }

    pidcontrol = new PIDController(Kp, Ki, Kd);
    pidcontrol.setTolerance(PIDTolerance);

    this.distance = distance;
    this.drivetrain = drivetrain;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    doneCounter = 0;
    pidcontrol.reset();
    pidcontrol.setSetpoint(distance + getAverageEncoders());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double velocity = pidcontrol.calculate(getAverageEncoders());
    drivetrain.setInchesPerSec(velocity,velocity);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.setInchesPerSec(0.0,0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
  if(pidcontrol.atSetpoint()){
    doneCounter++;
  }
  else {
    doneCounter = 0;
  }
    return doneCounter >= 10;
  }
}
