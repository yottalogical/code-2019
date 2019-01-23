/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DriveControl;

/**
 * Code for drive train
 */
public class Drivetrain extends Subsystem {
  
    private DifferentialDrive robotDrive;

    // private CANSparkMax leftBackMotor,
    //                     leftFrontMotor,
    //                     rightBackMotor,
    //                     rightFrontMotor;

    public Drivetrain(){

        // leftBackMotor = new CANSparkMax(RobotMap.CAN.LEFT_BACK_MOTOR, MotorType.kBrushless);
        // leftFrontMotor = new CANSparkMax(RobotMap.CAN.LEFT_FRONT_MOTOR, MotorType.kBrushless);
        // rightBackMotor = new CANSparkMax(RobotMap.CAN.RIGHT_BACK_MOTOR, MotorType.kBrushless);
        // rightFrontMotor = new CANSparkMax(RobotMap.CAN.RIGHT_FRONT_MOTOR, MotorType.kBrushless);
        
        Talon leftBackMotor = new Talon(RobotMap.CAN.LEFT_BACK_MOTOR);
        Talon leftFrontMotor = new Talon(RobotMap.CAN.LEFT_FRONT_MOTOR);
        Talon rightBackMotor = new Talon(RobotMap.CAN.RIGHT_BACK_MOTOR);
        Talon rightFrontMotor = new Talon(RobotMap.CAN.RIGHT_FRONT_MOTOR);

        SpeedControllerGroup leftGroup = new SpeedControllerGroup(leftBackMotor, leftFrontMotor);
        SpeedControllerGroup rightGroup = new SpeedControllerGroup(rightBackMotor, rightFrontMotor);

        robotDrive = new DifferentialDrive(leftGroup, rightGroup);

    }

    public void drive(double leftSpeed, double rightSpeed){

        robotDrive.tankDrive(leftSpeed, rightSpeed);

    }

    public void stop(){
        drive(0, 0);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DriveControl());
    }

}