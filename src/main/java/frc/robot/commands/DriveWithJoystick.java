/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class DriveWithJoystick extends CommandBase {
  private Drivetrain drive;
  private DoubleSupplier leftstick;
  private DoubleSupplier rightstick;
  /**
   * Creates a new DriveWithJoystick.
   */
  public DriveWithJoystick(Drivetrain drive, DoubleSupplier leftstick, DoubleSupplier rightstick) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drive=drive;
    this.leftstick=leftstick;
    this.rightstick=rightstick;
    addRequirements (drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive.setPercentOut(leftstick.getAsDouble(), rightstick.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.setPercentOut (0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
