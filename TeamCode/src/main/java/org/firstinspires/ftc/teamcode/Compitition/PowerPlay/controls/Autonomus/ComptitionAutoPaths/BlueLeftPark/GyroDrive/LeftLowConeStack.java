package org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.BlueLeftPark.GyroDrive;

import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.CompetionBot;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.Robots.StraferBot;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.AutoMain;
import org.firstinspires.ftc.teamcode.Compitition.PowerPlay.controls.Autonomus.ComptitionAutoPaths.AutoTargetZone;

public abstract class LeftLowConeStack extends AutoMain {
    public void parkplace (CompetionBot Bot, AutoTargetZone target) throws InterruptedException{
        switch (target) {
            case A:

                break;

            case B:

                break;

            case C:

                break;

            case None:

                fiveConeAuto(Bot);

                break;
        }
    }

    public void fiveConeAuto(CompetionBot Bot) throws InterruptedException {

        sleep(sleepTime);
        //puts everything together

        //scores preloaded cone
        scoreLow(Bot);

        //stacks first cone
        moveToStack(Bot);
        grabConeOne(Bot);
        coneStackLow(Bot);

        //stacks second cone
        moveToStack(Bot);
        grabConeTwo(Bot);
        coneStackLow(Bot);

        //stacks third cone
        moveToStack(Bot);
        grabConeThree(Bot);
        coneStackLow(Bot);

        //stacks fourth cone
        moveToStack(Bot);
        grabConeFour(Bot);
        coneStackLow(Bot);

        //stacks fifth cone
        moveToStack(Bot);
        grabConeFive(Bot);
        coneStackLow(Bot);

        //prepares for parking
        parkFromLow(Bot);

    }

    public void scoreLow(CompetionBot Bot) throws InterruptedException {

        //start of auto - goes to low goal, scores preloaded cone, gets in position for stacking on low

        Bot.driveForwardGyro(0.4, 5.1);
        sleep(sleepTime);

        Bot.driveGyroStrafeAngle(0.4, 1, "right", 180);
        sleep(sleepTime);

    }

    public void moveToStack(CompetionBot Bot) {

        //moves from position of scoring on low to the stack

    }

    public void coneStackLow(CompetionBot Bot) {

        //moves to low goal, scores

    }

    public void parkFromLow(CompetionBot Bot) {

        //moves from position of stacking final cone --> parking position 1 (can go from there into zone 2 or 3)

    }

    public void grabConeOne(CompetionBot Bot) {

        //grabs first cone (highest cone)

    }

    public void grabConeTwo(CompetionBot Bot) {

        //grabs second cone

    }

    public void grabConeThree(CompetionBot Bot) {

        //grabs third cone

    }

    public void grabConeFour(CompetionBot Bot) {

        //grabs fourth cone

    }

    public void grabConeFive(CompetionBot Bot) {

        //grabs fifth cone

    }

}
