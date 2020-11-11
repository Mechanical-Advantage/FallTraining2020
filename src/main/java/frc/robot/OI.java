/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * Interfaces to controllers
 */
public class OI {

    private Joystick controller;

    private Button runForwardsFastButton;
    private Button runBackwardsFastButton;
    private Button runForwardsSlowButton;
    private Button runBackwardsSlowButton;

    private static final double DEADBAND = .10;

    private double getScaledAxis(int axis, double scaleFactor) {
        double rawAxisValue = controller.getRawAxis(axis);
        if (Math.abs(rawAxisValue) < DEADBAND) {
            return 0;
        } else {
            return rawAxisValue / scaleFactor;

        }
    }

    private double getScaledAxis(int axis) {
        return getScaledAxis(axis, 1);
    }

    public OI(int id) {
        controller = new Joystick(id);

        runForwardsFastButton = new JoystickButton(controller, 1);
        runBackwardsFastButton = new JoystickButton(controller, 4);
        runForwardsSlowButton = new JoystickButton(controller, 2);
        runBackwardsSlowButton = new JoystickButton(controller, 3);

    }

    public double getSpinnerJoystick() {
        return getScaledAxis(0);
    }

    public double getLeftDrivetrain() {
        return getScaledAxis(1, 1.6);
    }

    public double getRightDrivetrain() {
        return getScaledAxis(5, 1.6);
    }

    public Trigger getRunForwardsFastButton() {
        return runForwardsFastButton;
    }

    public Trigger getRunBackwardsFastButton() {
        return runBackwardsFastButton;
    }

    public Trigger getRunForwardsSlowButton() {
        return runForwardsSlowButton;
    }

    public Trigger getRunBackwardsSlowButton() {
        return runBackwardsSlowButton;
    }
}
