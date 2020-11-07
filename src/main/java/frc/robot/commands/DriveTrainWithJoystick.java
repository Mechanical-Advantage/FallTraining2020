/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.DriveTrain;

public class DriveTrainWithJoystick extends CommandBase {
    private final DriveTrain drivetrain;
    private final DoubleSupplier leftAxis;
    private final DoubleSupplier rightAxis;

    /**
     * Creates a new RunSpinnerWithJoystick.
     */
    public DriveTrainWithJoystick(DriveTrain drivetrain, DoubleSupplier leftAxis, DoubleSupplier rightAxis) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.drivetrain = drivetrain;
        this.leftAxis = leftAxis;
        this.rightAxis = rightAxis;
        addRequirements(drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        drivetrain.setMotorOutput(leftAxis.getAsDouble(), rightAxis.getAsDouble());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrain.setMotorOutput(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}