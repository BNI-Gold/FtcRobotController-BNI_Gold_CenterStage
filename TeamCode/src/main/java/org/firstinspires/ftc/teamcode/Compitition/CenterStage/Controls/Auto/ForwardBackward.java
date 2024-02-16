package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.vision.tfod.TfodProcessor;
@Disabled
@Autonomous(name = "A - Forwards Backwards")
public class ForwardBackward extends AutoBlueAlliance {

    public static final boolean USE_WEBCAM = true;

    public static int oneSecond = 1000;

    public TfodProcessor tFod;









    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);

        Bot.setLinearOp(this);

        Bot.tuckPosition();

        telemetry.addLine("Robot Awaiting Start Procedure");
        telemetry.update();



        waitForStart();



        while (opModeIsActive()) {


            telemetry.addLine("Robot Autonomous Control Initialized");






//            Bot.driveForward(0.5,0.7);
//            sleep(1000);
//            Bot.rightWormgearDown(1);
//            sleep(700);
//            Bot.rightWormgearStop();

//            sleep(800);
//            Bot.rotateLeft(0.2,0.15);



            Bot.driveForward(0.5,1.5); //1.1
            sleep(500);
            Bot.driveBack(0.5,1.5);
            sleep(500);
            Bot.driveForward(0.5,1.5); //1.1
            sleep(500);
            Bot.driveBack(0.5,1.5);
            sleep(500);
            Bot.driveForward(0.5,1.5); //1.1
            sleep(500);
            Bot.driveBack(0.5,1.5);
            sleep(500);
            Bot.driveForward(0.5,1.5); //1.1
            sleep(500);
            Bot.driveBack(0.5,1.5);
            sleep(500);
            Bot.driveForward(0.5,1.5); //1.1
            sleep(500);
            Bot.driveBack(0.5,1.5);
            sleep(500);











//
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
