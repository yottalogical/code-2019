/**-------------------------------------------------------------------------------------
 *|  ______      ____       _______    __       ______    ----------------------------  |
 *| |   ___|    /    \     /   ____\  |  |     |   ___|  |.\=======-. ,~\  .-=======/.| |
 *| |  |_      /  /\  \   |   /  ___  |  |     |  |_     |....\======\_||_/======/....| |
 *| |   _|    /  /__\  \  |  |  |_  | |  |     |   _|    |........\\===##===// .......| |
 *| |  |___  |   ____   | |   \__/  | |  |___  |  |___   |............/||\............| |
 *| |______| |__|    |__|  \_______/  |______| |______|   ----------------------------  |
 *|  ______   __      __   ______    ______   ______    ______   __    __   __      __  |
 *| |_    _| |  \    /  | |   __ \  |   ___| |   __ \  |_    _| |  |  |  | |  \    /  | |
 *|   |  |   |   \  /   | |  |__| | |  |_    |  |__| |   |  |   |  |  |  | |   \  /   | |
 *|   |  |   |    \/    | |   ___/  |   _|   |      /    |  |   |  |  |  | |    \/    | |
 *|  _|  |_  |  |\__/|  | |  |      |  |___  |  |\  \   _|  |_  |   \/   | |  |\__/|  | |
 *| |______| |__|    |__| |__|      |______| |__| \__\ |______|  \______/  |__|    |__| |
 * -------------------------------------------------------------------------------------
 */

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  
    public static SideOuttake sideouttake;
    public static HatchManip hatchManip;
    public static Drivetrain drivetrain;
    public static Elevator elevator;
    public static HotMess hotMess;
    public static OI oi;
    public static Limelight limelight;
    public static WideIntake wideintake;
    public static Compressor compressor;

    Command m_autonomousCommand;
    SendableChooser<Command> m_chooser = new SendableChooser<>();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {

        sideouttake = new SideOuttake();
        hatchManip = new HatchManip();
        drivetrain = new Drivetrain();
        hotMess = new HotMess();
        limelight = new Limelight();
        elevator = new Elevator();
        wideintake = new WideIntake();
        compressor = new Compressor(RobotMap.PCM.PCM_ID);

        oi = new OI();

        hatchManip.grabberExtend();
        hatchManip.hatchGrab();

        drivetrain.updateDrivetrain();

        // chooser.addOption("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", m_chooser);
    }

    /**
     * This function is called every robot packet, no matter the mode. Use
     * this for items like diagnostics that you want ran during disabled,
     * autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        SmartDashboard.putNumber("Robot Angle", drivetrain.getAngle());
        sideouttake.update();
        drivetrain.updateDrivetrain();
        hatchManip.updateHatch();
        elevator.stopAtGoal();
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     *
     * <p>You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {
        m_autonomousCommand = m_chooser.getSelected();

        /*
         * String autoSelected = SmartDashboard.getString("Auto Selector",
         * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
         * = new MyAutoCommand(); break; case "Default Auto": default:
         * autonomousCommand = new ExampleCommand(); break; }
         */

        // schedule the autonomous command (example)
        if (m_autonomousCommand != null) {
            m_autonomousCommand.start();
        }
  }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();

        drivetrain.autoShift();
        /*
        SmartDashboard.putNumber("Left Back Encoder", drivetrain.getEncoder(0));
        SmartDashboard.putNumber("Right Back Encoder", drivetrain.getEncoder(1));
        SmartDashboard.putNumber("Left Front Encoder", drivetrain.getEncoder(2));
        SmartDashboard.putNumber("Right Front Encoder", drivetrain.getEncoder(3));

        SmartDashboard.putNumber("Left Back Velocity", drivetrain.getVelocity(0));
        SmartDashboard.putNumber("Right Back Velocity", drivetrain.getVelocity(1));
        SmartDashboard.putNumber("Left Front Velocity", drivetrain.getVelocity(2));
        SmartDashboard.putNumber("Right Front Velocity", drivetrain.getVelocity(3));

        SmartDashboard.putNumber("Left Back Motor Heat", drivetrain.getMotorHeat(0));
        SmartDashboard.putNumber("Right Back Motor Heat", drivetrain.getMotorHeat(1));
        SmartDashboard.putNumber("Left Front Motor Heat", drivetrain.getMotorHeat(2));
        SmartDashboard.putNumber("Right Front Motor Heat", drivetrain.getMotorHeat(3));

        SmartDashboard.putNumber("Left Back Encoder", drivetrain.getEncoder(0));
        SmartDashboard.putNumber("Right Back Encoder", drivetrain.getEncoder(1));
        SmartDashboard.putNumber("Left Front Encoder", drivetrain.getEncoder(2));
        SmartDashboard.putNumber("Right Front Encoder", drivetrain.getEncoder(3));

        SmartDashboard.putNumber("Left Back Voltage", drivetrain.getVoltage(0));
        SmartDashboard.putNumber("Right Back Voltage", drivetrain.getVoltage(1));
        SmartDashboard.putNumber("Left Front Voltage", drivetrain.getVoltage(2));
        SmartDashboard.putNumber("Right Front Voltage", drivetrain.getVoltage(3));

        SmartDashboard.putNumber("Left Back Output Current", drivetrain.getOutputCurrent(0));
        SmartDashboard.putNumber("Right Back Output Current", drivetrain.getOutputCurrent(1));
        SmartDashboard.putNumber("Left Front Output Current", drivetrain.getOutputCurrent(2));
        SmartDashboard.putNumber("Right Front Output Current", drivetrain.getOutputCurrent(3));
        */

    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }
}
