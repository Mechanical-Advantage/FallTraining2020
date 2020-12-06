/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.DrivetrainWithJoystick;
import frc.robot.commands.RunSpinner;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.DrivetrainIO;
import frc.robot.subsystems.drivetrain.DrivetrainIOSim;
import frc.robot.subsystems.spinner.*;
import frckit.simulation.devices.SimTimer;
import frckit.util.StoredDoubleSupplier;
import frc.robot.commands.RunSpinnerWithJoystick;
import frc.robot.commands.TurnToAngle;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private final OI oi;

  // The robot's subsystems and commands are defined here...
  private final Spinner spinner;
  private final Drivetrain drivetrain;
  private final StoredDoubleSupplier timestamp;

  private final SendableChooser<Command> autoChooser = new SendableChooser<Command>();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    oi = new OI(0);

    switch (Constants.getRobot()) {
    case SIM_NOTBOT:
      spinner = new Spinner(new SpinnerIOSim());
      drivetrain = new Drivetrain(new DrivetrainIOSim());
      timestamp = new StoredDoubleSupplier(SimTimer::getTimestampSeconds);

      break;

    default:
      spinner = new Spinner(new SpinnerIO() {
      });
      drivetrain = new Drivetrain(new DrivetrainIOSim());
      timestamp = new StoredDoubleSupplier(() -> 0.0);

    }

    configureInputs();
  }

  public void update() {
    timestamp.update();
  }

  public void execute() {
    // spinner.setDefaultCommand(new RunSpinner(spinner,
    // percentOutput.getAsDouble()));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureInputs() {
    spinner.setDefaultCommand(new RunSpinnerWithJoystick(spinner, oi::getSpinnerAxis));
    // spinner.setDefaultCommand(new RunSpinner(spinner));
    oi.getRunBackwardsFastButton().whileActiveContinuous(new RunSpinner(spinner, -1.0));
    oi.getRunForwardsFastButton().whileActiveContinuous(new RunSpinner(spinner, 1.0));
    oi.getRunBackwardsSlowButton().whileActiveContinuous(new RunSpinner(spinner, -0.1));
    oi.getRunForwardsSlowButton().whileActiveContinuous(new RunSpinner(spinner, 0.1));
    drivetrain.setDefaultCommand(new DrivetrainWithJoystick(drivetrain, oi::getLeftDriveX, oi::getRightDriveX));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    // return null;
    // return new SequentialCommandGroup(new WaitCommand(3), new RunSpinner(spinner,
    // -0.1).withTimeout(3),new RunSpinner(spinner, 0.3));
    // return (new TurnToAngle(drivetrain, 1.04));
    // return new SequentialCommandGroup(new TurnToAngle(drivetrain, 1.04),
    // new ParallelCommandGroup(new RunSpinner(spinner, 0.3).perpetually()), new
    // DriveDistance(drivetrain, 60),
    // new TurnToAngle(drivetrain, 2.04), new DriveDistance(drivetrain, 60));
    return new ParallelCommandGroup(new RunSpinner(spinner, 0.8),
        new SequentialCommandGroup(new TurnToAngle(drivetrain, 1.58), new DriveDistance(drivetrain, 60),
            new TurnToAngle(drivetrain, 1.58), new DriveDistance(drivetrain, 60), new TurnToAngle(drivetrain, 1.58),
            new DriveDistance(drivetrain, 60))

    );
  }
}
