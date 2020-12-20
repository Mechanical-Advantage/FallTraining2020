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
import frc.robot.util.TunableNumber;

public class TurnToAngle extends CommandBase {

  private final Drivetrain drivetrain;
  private PIDController pidController;
  private final double turnAngle;

  private TunableNumber Kp = new TunableNumber("TurnToAngle/Kp");
  private TunableNumber Ki = new TunableNumber("TurnToAngle/Ki");
  private TunableNumber Kd = new TunableNumber("TurnToAngle/Kd");
  private TunableNumber tolerance = new TunableNumber("TurnToAngle/tolerance");

  /**
   * Creates a new TurnToAngle.
   */
  public TurnToAngle(Drivetrain drivetrain, double turnAngle) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    this.turnAngle = turnAngle;
    addRequirements(drivetrain);

    switch (Constants.getRobot()) {
    case SIM_NOTBOT:
      Kp.setDefault(120);
      Ki.setDefault(0);
      Kd.setDefault(8);
      tolerance.setDefault(0.01);
      break;
    case ROBOT_NOTBOT:
      Kp.setDefault(1);
      Ki.setDefault(0);
      Kd.setDefault(0);
      tolerance.setDefault(0);
      break;
    default:
      Kp.setDefault(0);
      Ki.setDefault(0);
      Kd.setDefault(0);
      tolerance.setDefault(0);
    }

    pidController = new PIDController(Kp.get(), Ki.get(), Kd.get());

    pidController.setTolerance(tolerance.get());

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    if (Constants.tuningMode) {
      pidController.setPID(Kp.get(), Ki.get(), Kd.get());
      pidController.setTolerance(tolerance.get());
    }
    pidController.setSetpoint(drivetrain.getGyroRadians() + turnAngle);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double calculatedVelocity = pidController.calculate(drivetrain.getGyroRadians());

    drivetrain.setVelocityInchesPerSecond(calculatedVelocity, -1 * calculatedVelocity);
    SmartDashboard.putNumber("Gyro Error", pidController.getPositionError());
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
