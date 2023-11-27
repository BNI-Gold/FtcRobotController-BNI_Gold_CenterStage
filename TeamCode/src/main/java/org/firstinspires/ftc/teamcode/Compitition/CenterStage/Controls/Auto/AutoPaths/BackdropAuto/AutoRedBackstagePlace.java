package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoPaths.BackdropAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots.CompBot;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

@Autonomous (name = "AAA - Red Backstage Auto")
public class AutoRedBackstagePlace extends AutoMain {

    public static final boolean USE_WEBCAM = true;

    public static int oneSecond = 1000;

    public TfodProcessor tFod;

    public VisionPortal visionPortal;

    public   CompBot Bot = new CompBot();


    @Override
    public void runOpMode() throws InterruptedException{
        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        telemetry.addLine("Robot Awaiting Start Procedure");
        telemetry.update();

        Bot.endgameArmRotator.setPosition(0.8);

        Bot.automousPosition();


        waitForStart();



        while (opModeIsActive()) {

            telemetry.addLine("Robot Autonomous Control Initialized");


            sleep(oneSecond);
            Bot.driveForward(0.5, 0.5);
            sleep(oneSecond);
            Bot.rotateRight(0.4,2.4);
            sleep(oneSecond);
            Bot.driveForward(0.5,2.8);
            sleep(oneSecond);
            Bot.strafeLeft(0.5, 2.5);
            sleep(oneSecond);
            Bot.driveForward(0.15, 1);
            sleep(oneSecond);

            Bot.viperSlideRight.setPower(-1);
            sleep(600);
            Bot.viperSlideRight.setPower(-0.2);
            sleep(oneSecond);

            Bot.autoPlacePosition();
            sleep(oneSecond);

            Bot.rightPixelClawOpen();
            sleep(oneSecond * 2);


            Bot.rightPixelClawClose();
            sleep(oneSecond);

            Bot.rightPixelClawOpen();
            sleep(oneSecond * 2);

            Bot.viperSlideRight.setPower(0.75);
            sleep(600);

            Bot.viperSlideRight.setPower(0);
            sleep(oneSecond);

            Bot.rightPixelClawClose();
            sleep(oneSecond);

            sleep(oneSecond);

            telemetry.addLine("Robot Autonomous Control Complete");




            requestOpModeStop();
        }

        idle();


    }





    public void telemetryUpdate(String comment) {

        telemetry.addLine(comment);
        telemetry.addData("Front Lef Motor:", Bot.frontLeftMotor.getPower());
        telemetry.addData("Front Rig Motor:", Bot.frontRightMotor.getPower());
        telemetry.addData("Rear Lef Motor:", Bot.rearLeftMotor.getPower());
        telemetry.addData("Rear Rig Motor:", Bot.rearRightMotor.getPower());
        telemetry.addData("Encoder Count: ", Bot.frontLeftMotor.getCurrentPosition());
        telemetry.update();
    }
}
