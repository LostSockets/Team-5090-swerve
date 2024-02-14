package frc.robot;

// Import constants
import static frc.robot.Constants.ControllerPorts.*;

// Command imports
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.SwerveDriveCommand;

import frc.robot.subsystems.drivetrain.SwerveDrivetrain;

// Other imports
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotContainer {
    private final XboxController driverController = new XboxController(DRIVER_PORT);
    //private final XboxController operatorController = new XboxController(OPERATOR_PORT);

    private final SwerveDrivetrain drivetrain = new SwerveDrivetrain();


    private final SendableChooser<Command> autonChooser = new SendableChooser<Command>();    

    /** Constructs a RobotContainer */
    public RobotContainer() {
        initChooser();
        
        setDriverControls();
        setOperatorControls();
        setDefaultCommands();
    }

    /** Initialize the auton selector on the dashboard */
    private void initChooser() {
    }


    /** Use this to pass the autonomous command to the main {@link Robot} class.
     *  @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return autonChooser.getSelected();
    }

    /** Configures a set of commands that will run by default without human operation */
    private void setDefaultCommands() {
        // Set the intake to always be intaking by default
    }

    /** Configures a set of control bindings for the robot's operator */
    private void setDriverControls() {
        // Drives the robot with the joysticks
        drivetrain.setDefaultCommand(new SwerveDriveCommand(drivetrain, 
        () -> driverController.getLeftX(),
        () -> driverController.getLeftY(),
        () -> driverController.getRightX()));

        // PRESS START -> Reset the heading of the robot so the currently faced direction becomes 0
        Trigger resetHeadingBtn = new Trigger(() -> driverController.getStartButton());
        resetHeadingBtn.onTrue(new InstantCommand(() -> drivetrain.resetHeading()));
        // PRESS BACK -> Switch the robot between field-centric and robot-centric mode
        Trigger toggleOrientationBtn = new Trigger(() -> driverController.getBackButton());
        toggleOrientationBtn.onTrue(new InstantCommand(() -> drivetrain.toggleFieldCentric()));
    }

    /** Configures a set of control bindings for the robot's operator */
    private void setOperatorControls() {
    }
}