package org.firstinspires.ftc.teamcode.Compitition.ZCompititionUltimateGoal.Controls.Autonomous.CHIRPGyroTesting;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.CompetionBot;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.StraferBot;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.AutoTargetZone;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.BlueRightPark.BlueRightPark;
import org.firstinspires.ftc.teamcode.Compitition.ZCompititionUltimateGoal.Robots.StraferKit;

@Autonomous(name = "gyro drive test chirp")
public class gyroDriveTest extends BlueRightPark {

    public StraferKit Bot = new StraferKit();

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        while (opModeIsActive()) {

            sleep(sleepTime);

            Bot.rotateLeft(0.3, 1);
            sleep(sleepTime);

            Bot.gyroCorrection(0.5, 45);
            sleep(sleepTime);

//            Bot.driveGyroStraight(1000, 0.5);
//            sleep(sleepTime);

//            Bot.driveGyroBackward(0.5, 3);
//            sleep(sleepTime);

     }

            idle();
            requestOpModeStop();
        }
    }
