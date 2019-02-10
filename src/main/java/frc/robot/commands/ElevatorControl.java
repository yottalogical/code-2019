/**
 *  _____    _____     _____     _____   
 * |___  \  |___  \   /  _  \   /  _  \
 *  ___|  |  ___|  | |__| |  | |__| |  |
 * |___   | |___   |     /  /      /  /
 *  ___|  |  ___|  |   /  /__    /  /__
 * |_____/  |_____/   |______|  |______|
 *
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;
import static frc.robot.Robot.elevator;
import static frc.robot.Robot.oi;

public class ElevatorControl extends Command {

    private static int level=1;
    
    private final int UP_AXIS;
    private final int DOWN_AXIS;

    /*
  	public ElevatorControl(boolean upOrDown) {

		requires(elevator);

		//level += (upOrDown) ? ((level == 3) ? 0 : 1) : ((level == 1) ? 0 : -1);

    }
     */

	public ElevatorControl(){
        requires(elevator);
        
        this.UP_AXIS = RobotMap.XBOX.TRIGGER_L_AXIS;
        this.DOWN_AXIS = RobotMap.XBOX.TRIGGER_R_AXIS;
	
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
        double moveInput = oi.upperChassis.getRawAxis(UP_AXIS) - oi.upperChassis.getRawAxis(DOWN_AXIS) * elevator.downSpeedModifier;
        
        elevator.move(moveInput * 0.5);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		elevator.stop();
	}

	@Override
	protected void interrupted() {
		super.interrupted();
	}
}
