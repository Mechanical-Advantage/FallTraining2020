/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.DrivetrainControl;
import frc.robot.commands.RunSpinner;
import frc.robot.commands.RunSpinnerWithJoystick;
import frc.robot.commands.TurnToAngle;
import frc.robot.subsystems.spinner.*;
import frc.robot.subsystems.drivetrain.*;
import frckit.simulation.devices.SimTimer;
import frckit.util.StoredDoubleSupplier;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

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
  private final StoredDoubleSupplier timestamp;
  private final Drivetrain drivetrain;

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
    case ROBOT_NOTBOT:
      spinner = new Spinner(new SpinnerIORobot());
      drivetrain = new Drivetrain(new DrivetrainIOReal());
      timestamp = new StoredDoubleSupplier(Timer::getFPGATimestamp);
      break;

    default:
      spinner = new Spinner(new SpinnerIO() {
      });
      timestamp = new StoredDoubleSupplier(() -> 0.0);
      drivetrain = new Drivetrain(new DrivetrainIO() {

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
    oi.getRunForwardsFastButton().whileActiveContinuous(new RunSpinner(spinner, 1));
    oi.getRunBackwardsfastButton().whileActiveContinuous(new RunSpinner(spinner, -1));
    oi.getRunForwardsSlowButton().whileActiveContinuous(new RunSpinner(spinner, 0.4));
    oi.getRunBackwardsSlowButton().whileActiveContinuous(new RunSpinner(spinner, -0.4));
    oi.turn90DegreesButton().whenActive(new TurnToAngle(drivetrain, Math.PI / 2));

    drivetrain.setDefaultCommand(new DrivetrainControl(drivetrain, oi::getLeftDrivetrain, oi::getRightDrivetrain));
    spinner.setDefaultCommand(new RunSpinnerWithJoystick(spinner, oi::getSpinnerJoystick));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    // return new SequentialCommandGroup(new DriveDistance(drivetrain, 60), new
    return new TurnToAngle(drivetrain, Math.PI / 2);
    // return new DriveDistance(drivetrain, 60);
    // 1).withTimeout(5.0), new WaitCommand(5.0),
    // new RunSpinner(spinner, -1));
    // return new DriveDistance(drivetrain, 60);
    // return new TurnToAngle(drivetrain, Math.PI);
  }
}
