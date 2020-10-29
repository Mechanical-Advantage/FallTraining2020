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

    private Button runForwardsButton;

    private Button runBackwardsButton;

    private Button runBackwardsFastButton;

    public OI(int id) {
        controller = new Joystick(id);

        runForwardsFastButton = new JoystickButton(controller, 1);
        runForwardsButton = new JoystickButton(controller, 2);
        runBackwardsFastButton = new JoystickButton(controller, 3);
        runBackwardsButton = new JoystickButton(controller, 4);
        /* update robotcontainer file with the new buttons */
    }

    public Button getRunForwardsFastButton() {
        return runForwardsFastButton;
    }

    public Button getRunForwardsButton() {
        return runForwardsButton;
    }

    public Button getRunBackwardsButton() {
        return runBackwardsButton;
    }

    public Button getRunBackwardsFastButton() {
        return runBackwardsFastButton;
    }

}
