/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//import frc.robot.PIDController;
import frc.robot.RobotMap;
import frc.robot.commands.ElevatorControl;

public class Elevator extends PIDSubsystem {

    // create constants

    private SpeedControllerGroup elevator;
    
    private int currentLevel = 1;
    private int desiredLevel;
    private double upSpeed = 1; // temp
    private double downSpeed = -1; // temp
    private double bottom = 0;
    private double firstLevel = 10; // temp
    private double secondLevel = 20; // temp
    private double thirdLevel = 30; // temp
    public double pidSpeed;

    Encoder elevatorMEncoder1;
    Encoder elevatorMEncoder2;
    Encoder elevatorTrackEncoder;
    WPI_TalonSRX elevatorMotor1; 
    WPI_TalonSRX elevatorMotor2 = new WPI_TalonSRX(RobotMap.CAN.ELEVATOR_MOTOR_2);
    
    DigitalInput hallEffectLevel0;
    DigitalInput hallEffectLevel1;
    DigitalInput hallEffectLevel2;
    DigitalInput hallEffectLevel3;

    public Elevator() {
        super("Elevator PID", 0, 0, 0, 0);
        setAbsoluteTolerance(0.05);
        getPIDController().setContinuous(false);
        // create elevator motors and assign to speed group for easy control

        elevatorMEncoder1 = new Encoder(RobotMap.DIO.ELEVATOR_M_ENCODER_1_A, RobotMap.DIO.ELEVATOR_M_ENCODER_1_B);
        elevatorMEncoder2 = new Encoder(RobotMap.DIO.ELEVATOR_M_ENCODER_2_A, RobotMap.DIO.ELEVATOR_M_ENCODER_2_B);
        elevatorTrackEncoder = new Encoder(RobotMap.DIO.ELEVATOR_TRACK_ENCODER_A, RobotMap.DIO.ELEVATOR_TRACK_ENCODER_B);
        elevatorMotor1 = new WPI_TalonSRX(RobotMap.CAN.ELEVATOR_MOTOR_1);
        elevatorMotor2 = new WPI_TalonSRX(RobotMap.CAN.ELEVATOR_MOTOR_2);
        
        hallEffectLevel0 = new DigitalInput(RobotMap.DIO.HALL_EFFECT_LEVEL_0);
        hallEffectLevel1 = new DigitalInput(RobotMap.DIO.HALL_EFFECT_LEVEL_1);
        hallEffectLevel2 = new DigitalInput(RobotMap.DIO.HALL_EFFECT_LEVEL_2);
        hallEffectLevel3 = new DigitalInput(RobotMap.DIO.HALL_EFFECT_LEVEL_3);

        elevator = new SpeedControllerGroup(elevatorMotor1, elevatorMotor2);
        elevator.setInverted(true);
        
    }

    public Elevator(double upSpeed, double downSpeed) { // set up and down speeds
        this();
        this.upSpeed = upSpeed;
        this.downSpeed = downSpeed;
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ElevatorControl()); // run elevator command in Commands
    }

    public boolean atLevel0() {
        return hallEffectLevel0.get();
    }

    public boolean atLevel1() {
        return hallEffectLevel1.get();
    }

    public boolean atLevel2() {
        return hallEffectLevel2.get();
    }

    public boolean atLevel3() {
        return hallEffectLevel3.get();
    }

    public void moveUp() { // move at current upSpeed
        move(upSpeed);
    }

    public void moveDown() { // move at current downSpeed
        move(-downSpeed);
    }

    public void move(double speed) { // make it move at specified speed
        // test if at top or bottom first, implement later
        elevator.set(speed);
    }

    public void stop() { // stop the elevator
        elevator.set(0);
    } // add more methods as needed

    public void goToLevel(int level) {
        switch (level) {
        case 0:
            desiredLevel = 0;
            setSetpoint(bottom);
            if(hallEffectLevel0.get() && desiredLevel == 0) {
                elevator.stopMotor();
            }
            currentLevel = 0;
            break;
        case 1:
            desiredLevel = 1;
            setSetpoint(firstLevel);
            if(hallEffectLevel1.get() && desiredLevel == 1) {
                elevator.stopMotor();
            }
            currentLevel = 1;
            break;
        case 2:
            desiredLevel = 2;
            setSetpoint(secondLevel);
            if(hallEffectLevel2.get() && desiredLevel == 2) {
                elevator.stopMotor();
            }
            currentLevel = 2;
            break;
        case 3:
            desiredLevel = 3;
            setSetpoint(thirdLevel);
            if(hallEffectLevel3.get() && desiredLevel == 3) {
                elevator.stopMotor();
            }
            currentLevel = 3;
            break;
        default:
            return;
        }
        elevator.set(pidSpeed);

    }

    @Override
    protected double returnPIDInput() {
        return elevatorTrackEncoder.getDistance();
    }

    @Override
    protected void usePIDOutput(double output) {
        pidSpeed = output;
    }

}
