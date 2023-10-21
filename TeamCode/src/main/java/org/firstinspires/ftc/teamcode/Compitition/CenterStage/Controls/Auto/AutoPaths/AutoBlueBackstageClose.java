package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots.CompBot;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

@Autonomous (name = "Blue Backstage Close")
public class AutoBlueBackstageClose extends AutoMain {

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
        Bot.endgameArmRotatorMovement(0.2);

        waitForStart();



        while (opModeIsActive()) {

            Bot.endgameArmRotatorMovement(0.2);
            Bot.driveForward(0.5, 0.3);
            sleep(oneSecond);
            Bot.rotateLeft(0.4,2.2);
            sleep(oneSecond);
            Bot.driveForward(0.5,2.8);

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
