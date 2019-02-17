/**
 *  _____    _____     _____     _____   
 * |___  \  |___  \   /  _  \   /  _  \
 *  ___|  |  ___|  | |__| |  | |__| |  |
 * |___   | |___   |     /  /      /  /
 *  ___|  |  ___|  |   /  /__    /  /__
 * |_____/  |_____/   |______|  |______|
 *
 */

package frc.robot;

public class RobotMap {
    public static boolean isRobotP1 = false;

    public static class CAN {
        
        //TODO: add in P2 motor controller IDs 
        
        //drivetrain                                            P1  P2
        public static final int LEFT_FRONT_MOTOR = (isRobotP1 ? 37 : 43);
        public static final int LEFT_BACK_MOTOR = (isRobotP1 ? 35 : 44);
        public static final int RIGHT_FRONT_MOTOR = (isRobotP1 ? 36 : 41);
        public static final int RIGHT_BACK_MOTOR = (isRobotP1 ? 34 : 42);

        //hot mess
        public static final int HOTMESS_MOTOR1 = (isRobotP1 ? 39 : 45);
        public static final int HOTMESS_MOTOR2 = (isRobotP1 ? 40 : 46);

        //cargo
        public static final int L_SIDE_OUTTAKE = (isRobotP1 ? 20 : 20);
        public static final int R_SIDE_OUTTAKE = (isRobotP1 ? 21 : 21);
        public static final int INTAKE_MOTOR = (isRobotP1 ? 19 : 19);

        //elevator
        public static final int ELEVATOR_MOTOR_1 = (isRobotP1 ? 23 : 23);
        public static final int ELEVATOR_MOTOR_2 = (isRobotP1 ? 22 : 22);

    }

    public static class DIO {
        public static final int CARGO_DETECTOR_INTAKE = 4;
        public static final int CARGO_DETECTOR_OUTTAKE = 5;

        public static final int HATCH_DETECTOR = 10;

        public static final int LEFT_INFRARED = 1;
        public static final int RIGHT_INFRARED = 0;

      //elevator
      public static final int ELEVATOR_ENCODER_A = 2;
      public static final int ELEVATOR_ENCODER_B = 3;

    }

    public static class PCM {

        public static final int PCM_ID = 3;

        public static final int SHIFT_GEAR_1 = 5;
        public static final int SHIFT_GEAR_2 = 4;
        public static final int LOWER_MECHANISM = 0;
        public static final int RAISE_MECHANSIM = 1;
        public static final int GRAB_HATCH = 2;
        public static final int RELEASE_HATCH = 3;
        

        public static final int INTAKE_EXTEND = 6;
        public static final int INTAKE_RETRACT = 7;
 
    }
  
    public static class XBOX {
      // Buttons
        public static final int BUTTON_A = 1;
        public static final int BUTTON_B = 2;
        public static final int BUTTON_X = 3;
        public static final int BUTTON_Y = 4;
        public static final int BUMPER_LEFT = 5;
        public static final int BUMPER_RIGHT = 6;
        public static final int BUTTON_BACK = 7;
        public static final int BUTTON_START = 8;
        public static final int STICK_LEFT = 9;
        public static final int STICK_RIGHT = 10;

       // Axes
        public static final int STICK_L_X_AXIS = 0;
        public static final int STICK_L_Y_AXIS = 1;
        public static final int STICK_R_X_AXIS = 4;
        public static final int STICK_R_Y_AXIS = 5;
        public static final int TRIGGER_L_AXIS = 2;
        public static final int TRIGGER_R_AXIS = 3;
  }
  
}
