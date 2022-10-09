package org.firstinspires.ftc.teamcode.MrAcker;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "NameLessBot", group = "BNI")
public class AutoTest_Acker extends AutoMain_Acker {

    public AckerBot Bot = new AckerBot();

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        waitForStart();

        while (opModeIsActive()) {

            //Driving Movement Test
            TestAuto(Bot);

            //Cone Tester
            grabCone(Bot);
            Bot.driveBackward(.50,2);
            Bot.driveForward(.50,2);
            dropCone(Bot);

        }
        idle();
    }


}
