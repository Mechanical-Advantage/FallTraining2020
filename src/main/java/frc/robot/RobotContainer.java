/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.RunSpinner;
import frc.robot.commands.RunWithJoystick;
import frc.robot.subsystems.spinner.*;
import frckit.simulation.devices.SimTimer;
import frckit.util.StoredDoubleSupplier;
import frc.robot.subsystems.drive.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private OI oi;

  // The robot's subsystems and commands are defined here...
  private final Spinner spinner;
  private final Drive drive;
  private final StoredDoubleSupplier timestamp;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    oi = new OI(0);

    switch (Constants.getRobot()) {
      case SIM_NOTBOT:
        spinner = new Spinner(new SpinnerIOSim());
        timestamp = new StoredDoubleSupplier(SimTimer::getTimestampSeconds);
        drive = new Drive(new DriveIOSim());
        break;
      case ROBOT_NOTBOT:
        spinner = new Spinner(new SpinnerIOReal());
        timestamp = new StoredDoubleSupplier(Timer::getFPGATimestamp);
        drive = new Drive(new DriveIOReal()); // Change this to io real version
        break;

      default:
        spinner = new Spinner(new SpinnerIO() {
        });
        timestamp = new StoredDoubleSupplier(() -> 0.0);
        drive = new Drive(new DriveIO() {
        });
    }

    configureInputs();
  }

  public void update() {
    timestamp.update();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureInputs() {
    // spinner.setDefaultCommand(new RunSpinner(spinner));
    // oi.getRunForwardsFastButton().whenActive(new RunSpinner(spinner));//Maps a
    // button to a command
    oi.getRunForwardsFastButton().whileActiveContinuous(new RunSpinner(spinner, 1.0));
    oi.getRunForwardsSlowButton().whileActiveContinuous(new RunSpinner(spinner, 0.1));
    oi.getRunBackwardsFastButton().whileActiveContinuous(new RunSpinner(spinner, -1.0));
    oi.getRunBackwardsSlowButton().whileActiveContinuous(new RunSpinner(spinner, -0.1));
    spinner.setDefaultCommand(new RunWithJoystick(spinner, oi::getSpinnerAxis));
    drive.setDefaultCommand(new DriveWithJoysticks(drive, oi::getLeftAxis, oi::getRightAxis));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new SequentialCommandGroup(new RunSpinner(spinner, 0.1).withTimeout(5), new WaitCommand(5),
        new RunSpinner(spinner, -0.1).withTimeout(5));
    // return null;
  }
}
