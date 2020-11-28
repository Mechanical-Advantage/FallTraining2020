/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Controller;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frckit.util.StoredDoubleSupplier;

/**
 * Interfaces to controllers
 */
public class OI {

    private final Joystick controller;

    private final Button runForwardsFastButton;
    private final Button runBackwardsFastButton;
    private final Button runForwardsSlowButton;
    private final Button runBackwardsSlowButton;
    private double DEAD_BAND = .05;

    public OI(final int id) {
        controller = new Joystick(id);

        runForwardsFastButton = new JoystickButton(controller, 4);
        runBackwardsFastButton = new JoystickButton(controller, 1);
        runForwardsSlowButton = new JoystickButton(controller, 3);
        runBackwardsSlowButton = new JoystickButton(controller, 2);

    }

    // throttleValue = new StoredDoubleSupplier(controller.getRawAxis(5));
    public double getRightDriveX() {
        if (Math.abs(0 - controller.getRawAxis(2)) > DEAD_BAND) {
            return controller.getRawAxis(2);
        } else {
            return 0;
        }
    }

    public double getLeftDriveX() {
        if (Math.abs(0 - controller.getRawAxis(1)) > DEAD_BAND) {
            return controller.getRawAxis(1) * -1;
        } else {
            return 0;
        }
    }

    public double getSpinnerAxis() {
        if (Math.abs(0 - controller.getRawAxis(3)) > DEAD_BAND) {
            return controller.getRawAxis(3);
        } else {
            return 0;
        }

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
