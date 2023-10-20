package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots.CompBot;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

@Autonomous (name = "AutoBlueAudience")
public class AutoBlueAudience extends AutoMain {

    public static final boolean USE_WEBCAM = true;

    public TfodProcessor tFod;

    public VisionPortal visionPortal;

  public   CompBot BNIBot = new CompBot();


    @Override
    public void runOpMode() throws InterruptedException{
        BNIBot.initRobot(hardwareMap);
        BNIBot.setLinearOp(this);

        telemetry.addLine("Robot Awaiting Start Procedure");
        telemetry.update();

        waitForStart();



        while (opModeIsActive()) {



//            BNIBot.getHeading();

            telemetryUpdate("Drive FOrward");
            BNIBot.driveForward(1,3.7);
            sleep(200);
            telemetryUpdate("Gyro COrrection");
//            BNIBot.gyroCorrection(.5,0);
            telemetryUpdate("Rotate Left 1 Rotation");
            sleep(200);
            BNIBot.rotateLeft(.5,1);




            requestOpModeStop();
        }


        idle();


    }





    public void telemetryUpdate(String comment) {

        telemetry.addLine(comment);
        telemetry.addData("Front Lef Motor:", BNIBot.frontLeftMotor.getPower());
        telemetry.addData("Front Rig Motor:", BNIBot.frontRightMotor.getPower());
        telemetry.addData("Rear Lef Motor:", BNIBot.rearLeftMotor.getPower());
        telemetry.addData("Rear Rig Motor:", BNIBot.rearRightMotor.getPower());
        telemetry.addData("Encoder Count: ", BNIBot.frontLeftMotor.getCurrentPosition());
        telemetry.update();
    }
}
