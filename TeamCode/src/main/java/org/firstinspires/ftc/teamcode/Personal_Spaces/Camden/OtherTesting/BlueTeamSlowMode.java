package org.firstinspires.ftc.teamcode.Personal_Spaces.Camden.OtherTesting;

public class BlueTeamSlowMode {

    //-----------------------------


    // below is just some random stuff to use for the example itself
    boolean placeHolder = false;

    public double power = 1.0;

    public void driveForward(double motorPower) {

    }

    //-----------------------------


    // below is the code we would use, this is just an example to show the concept
    public double speedMultiply = 1;

    public boolean slowMode = false;

    public void speedControl() {

        // we would replace 'placeHolder' with a control, for example: 'gamepad1.a'
        if (placeHolder) {

            slowMode = true;

        } else {

            slowMode = false;

        }

        if (slowMode) {

            // this value will determine how slow the robot is during slow mode. In this example, the robot is at half speed. if the value was set to 0.25, it would be one quarter of total speed.
            speedMultiply = 0.5;

        } else {

            speedMultiply = 1.0;

        }

    }

    // below is just an example of how we would multiply motor power by the speed multipier to actually use slow mode
    public void exampleDrive() {

        driveForward(power * speedMultiply);

    }

}
