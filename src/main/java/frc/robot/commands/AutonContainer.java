package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;

/** A container that stores various procedures for the autonomous portion of the game */
public class AutonContainer {
    RobotContainer robot;

    /** Constructs an AutonContainer object */ 
    public AutonContainer(RobotContainer robot) {
        this.robot = robot;
    }

    /** Auton that does nothing */
    public Command doNothing() {
        return new WaitCommand(0);
    }


    /** Auton that shoots one piece into the speaker and moves backwards*/
    public Command onePieceAuton() {
        return new SequentialCommandGroup(
            new InstantCommand(() -> robot.shooter.shoot(.4)),
            new WaitCommand(2),
            new InstantCommand(() -> robot.intake.eject(.5)),
            new WaitCommand(.25),
            new LimeDrive(robot.drivetrain, robot.shooterLimelight, -4.5)
        );
    }
}
