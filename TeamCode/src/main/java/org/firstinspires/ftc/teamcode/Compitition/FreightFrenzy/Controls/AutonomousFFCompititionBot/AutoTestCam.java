package org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Controls.AutonomousFFCompititionBot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Compitition.FreightFrenzy.Robots.EightWheelBot;

@Autonomous(name = "Auto Cam Test", group = "Programming Bot")
public class AutoTestCam extends AutoMain {

    EightWheelBot Bot = new EightWheelBot();
    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
        Bot.initWebcam();
        Bot.setLinearOp(this);

        waitForStart();
        while (opModeIsActive()) {
            Bot.detectBarcode();
            collectTSE(Bot);
        }
    }
}
