/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class HotMess extends Subsystem {
    
    private CANSparkMax motor1,
                        motor2;
    
    private CANEncoder encoder1,
                       encoder2;
    
    private SpeedControllerGroup motorGroup;

    public HotMess(){

        motor1 = new CANSparkMax(RobotMap.CAN.HOTMESS_MOTOR1, MotorType.kBrushless);
        motor2 = new CANSparkMax(RobotMap.CAN.HOTMESS_MOTOR2, MotorType.kBrushless);

        encoder1 = motor1.getEncoder();
        encoder2 = motor2.getEncoder();

        motorGroup = new SpeedControllerGroup(motor1, motor2);

    }

    public void climb(){

        motorGroup.set(1);

    }

    public void stop(){

        motorGroup.set(0);

    }

    public double getEncoderVal(){

        return (encoder1.getPosition() + encoder2.getPosition()) / 2;

    }

    @Override
    public void initDefaultCommand() {
        // setDefaultCommand(new MySpecialCommand());
    }
}
