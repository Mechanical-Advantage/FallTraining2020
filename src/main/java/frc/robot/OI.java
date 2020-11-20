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
    private Button runForwardsSlowButton;
    private Button runBackwardsFastButton;
    private Button runBackwardsSlowButton;

    public OI(int id) {
        controller = new Joystick(id);

        runForwardsFastButton = new JoystickButton(controller, 2);
        runForwardsSlowButton = new JoystickButton(controller, 1);
        runBackwardsFastButton = new JoystickButton(controller, 4);
        runBackwardsSlowButton = new JoystickButton(controller, 3);
    }

    public Trigger getRunForwardsFastButton() {
        return runForwardsFastButton;
    }

    public Trigger getRunForwardsSlowButton() {
        return runForwardsSlowButton;
    }

    public Trigger getRunBackwardsFastButton() {
        return runBackwardsFastButton;
    }

    public Trigger getRunBackwardsSlowButton() {
        return runBackwardsSlowButton;
    }

    public double getSpinnerAxis() {
        return controller.getRawAxis(0);
    }

    public double getRightAxis() {
        return controller.getRawAxis(3);
    }

    public double getLeftAxis() {
        return controller.getRawAxis(1);
    }
}
