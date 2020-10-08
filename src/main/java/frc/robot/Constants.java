/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final RobotType defaultRealRobot = RobotType.ROBOT_NOTBOT;
    public static final RobotType defaultSimRobot = RobotType.SIM_NOTBOT;
    private static RobotType robot;

    public static RobotType getRobot() {
        return robot;
    }

    static void setRobot(RobotType robot) {
        if (Constants.robot == null) {
            Constants.robot = robot;
        }
    }

    public enum RobotType {
        ROBOT_2020, ROBOT_2020_DRIVE, ROBOT_2019, ROBOT_REBOT, ROBOT_2018, ROBOT_NOTBOT, SIM_NOTBOT
    }
}
