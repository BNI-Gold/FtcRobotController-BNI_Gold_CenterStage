package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Drivetrains.MecanumDrive;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots.CompBot;

//@Disabled
@Autonomous (name = "Auto Path Tester")
public class AutoTester extends LinearOpMode {

    public CompBot testBot = new CompBot();
    @Override
    public void runOpMode(){
        testBot.initRobot(hardwareMap);
        testBot.setLinearOp(this);


        telemetry.addLine("Robot Awaiting Start Procedure");
        telemetry.update();


        waitForStart();



        while (opModeIsActive()) {
            telemetryUpdate("Drive Forward");
            testBot.speedAcceleration(4,1, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(200);
            testBot.speedAcceleration(3, 1, MecanumDrive.driveDirections.DRIVE_BACK);
            sleep(200);
            testBot.speedAcceleration(2, .6, MecanumDrive.driveDirections.STRAFE_LEFT);
            sleep(200);
            testBot.speedAcceleration(2, .6, MecanumDrive.driveDirections.STRAFE_RIGHT);
            sleep(150);
            testBot.gyroPath(.6, 90);
            sleep(150);
            testBot.gyroPath(.6, -90);
            sleep(150);
            testBot.gyroCorrection(.15, -90);
            sleep(1000);
            requestOpModeStop();
        }

    }

    public void telemetryUpdate(String comment) {

        telemetry.addLine(comment);
        telemetry.addData("Front Lef Motor:", testBot.frontLeftMotor.getPower());
        telemetry.addData("Front Rig Motor:", testBot.frontRightMotor.getPower());
        telemetry.addData("Rear Lef Motor:", testBot.rearLeftMotor.getPower());
        telemetry.addData("Rear Rig Motor:", testBot.rearRightMotor.getPower());
        telemetry.addData("Encoder Count: ", testBot.frontLeftMotor.getCurrentPosition());
        telemetry.update();
    }
}
