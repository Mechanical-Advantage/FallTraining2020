/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.spinner.Spinner;

public class JoystickSpinner extends CommandBase {
  private final Spinner spinner;
  private final DoubleSupplier speedSupplier;
  /**
   * Creates a new joystickspinner.
   */
  public JoystickSpinner(Spinner spinner, DoubleSupplier supplier ) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.spinner = spinner;
    addRequirements(spinner);
    speedSupplier = supplier;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { 
    spinner.setPercentOutput(speedSupplier.getAsDouble() * .1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    spinner.setPercentOutput(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
