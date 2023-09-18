// Autonomous Tester Class Learning Template for Competition 2022-2023
// Revision: 09-Oct-22
// Author: Jamie Acker

package org.firstinspires.ftc.teamcode.Personal_Spaces.MrAcker.NamelessBot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Disabled
@Autonomous(name = "NameLessBot", group = "Lab")
public class AutoTester_Nameless extends AutoMain_Nameless {

    public NamelessBot Bot = new NamelessBot();

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
