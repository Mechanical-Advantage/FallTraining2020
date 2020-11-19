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
import frc.robot.commands.DriveDistance;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.RunSpinner;
import frc.robot.commands.RunSpinnerWithJoystick;
import frc.robot.subsystems.spinner.*;
import frc.robot.subsystems.drivetrain.*;
import frckit.simulation.devices.SimTimer;
import frckit.util.StoredDoubleSupplier;

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
  private final DriveTrain driveTrain;
  private final StoredDoubleSupplier timestamp;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    oi = new OI(0);

    switch (Constants.getRobot()) {
      case SIM_NOTBOT:
        timestamp = new StoredDoubleSupplier(SimTimer::getTimestampSeconds);
        spinner = new Spinner(new SpinnerIOSim());
        driveTrain = new DriveTrain(new DriveTrainIOSim());
        break;

      case ROBOT_NOTBOT:
        timestamp = new StoredDoubleSupplier(Timer::getFPGATimestamp);
        spinner = new Spinner(new SpinnerIOReal());
        driveTrain = new DriveTrain(new DriveTrainIOReal());
        break;

      default:
        timestamp = new StoredDoubleSupplier(() -> 0.0);
        spinner = new Spinner(new SpinnerIO() {
        });
        driveTrain = new DriveTrain(new DriveTrainIO() {
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
    spinner.setDefaultCommand(new RunSpinnerWithJoystick(spinner, oi::getSpinnerDriveAxis));
    driveTrain.setDefaultCommand(new DriveWithJoysticks(driveTrain, oi::getLeftDriveAxis, oi::getRightDriveAxis));

    oi.getRunForwardsFastButton().whileActiveContinuous(new RunSpinner(spinner, 1));
    oi.getRunBackwardsFastButton().whileActiveContinuous(new RunSpinner(spinner, -1));
    oi.getRunForwardsSlowButton().whileActiveContinuous(new RunSpinner(spinner, 0.1));
    oi.getRunBackwardsSlowButton().whileActiveContinuous(new RunSpinner(spinner, -0.1));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new DriveDistance(driveTrain, 48);
  }
}