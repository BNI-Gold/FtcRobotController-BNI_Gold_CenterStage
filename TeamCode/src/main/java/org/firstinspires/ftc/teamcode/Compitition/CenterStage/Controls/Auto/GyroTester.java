package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Drivetrains.MecanumDrive;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots.CompBot;

//@Disabled
@Disabled
@Autonomous (name = "Gyro Tester")
public class GyroTester extends LinearOpMode {

    public CompBot testBot = new CompBot();
    @Override
    public void runOpMode(){
        testBot.initRobot(hardwareMap);
        testBot.setLinearOp(this);


        telemetry.addLine("Robot Awaiting Start Procedure");
        telemetry.update();


        waitForStart();



        while (opModeIsActive()) {
            testBot.resetHeading();
            telemetryUpdate("Drive Forward");
            testBot.speedAcceleration(10,.7, MecanumDrive.driveDirections.DRIVE_FORWARD);
            telemetryUpdate("Drive Forward");
            testBot.gyroCorrection(.6,0);
            sleep(2000);

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
