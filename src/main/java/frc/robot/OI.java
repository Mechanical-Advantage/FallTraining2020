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
    private final double throttleValue;

    public OI(final int id) {
        controller = new Joystick(id);

        runForwardsFastButton = new JoystickButton(controller, 4);
        runBackwardsFastButton = new JoystickButton(controller, 1);
        runForwardsSlowButton = new JoystickButton(controller, 3);
        runBackwardsSlowButton = new JoystickButton(controller, 2);
        throttleValue = controller.getRawAxis(5);

    }

    // throttleValue = new StoredDoubleSupplier(controller.getRawAxis(5));
    public double getRightDriveX() {
        return controller.getRawAxis(2);
    }

    public double getLeftDriveX() {
        return controller.getRawAxis(1) * -1;
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
