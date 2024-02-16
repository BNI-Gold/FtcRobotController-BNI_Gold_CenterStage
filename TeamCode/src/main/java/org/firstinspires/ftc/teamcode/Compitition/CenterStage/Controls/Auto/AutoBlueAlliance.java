package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto;

import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Drivetrains.MecanumDrive;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.RoadRunner.drive.RoadrunMecanumDrive;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots.CompBot;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots.ProgrammingBot;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Vision.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Vision.TeamPropPositionPipeline_Gold;

public abstract class AutoBlueAlliance extends AutoMain{

    public CompBot Bot = new CompBot();

    public TeamPropPositionPipeline_Gold pipeline = new TeamPropPositionPipeline_Gold("BLUE", 140);

    public void CameraDetection () {
        teamPropPosition = pipeline.getAnalysis();
        telemetry.addData("Position Detected: ", teamPropPosition);
        telemetry.update();
        sleep(150);



        stopCamera();
        telemetry.addLine("Stopping Camera");
        telemetry.update();
        //sleep(150);

    }



    public void spikeMarkPlaceClose(){

        if (teamPropPosition == TeamPropPosition.BLUE_LEFT) {
//            Bot.strafeLeft(0.5, 0.3);
//            sleep(100);
            Bot.rotateLeft(0.3,0.85);
            sleep(1000);
            Bot.driveForward(0.3,0.1);
            sleep(100);
            Bot.leftPixelClawOpen();
            sleep(700);
//            Bot.driveBack(0.3,0.1);
            sleep(500);
            Bot.leftPixelClawClose();
            sleep(500);
            Bot.rotateRight(0.3,0.85);
            sleep(100);
            Bot.driveBack(0.3, 1.25);
            sleep(100);
            Bot.rotateLeft(0.3, 2.35);
            sleep(100);
            Bot.driveForward(0.5,2.6);
            Bot.strafeLeft(0.5,0.3);

        }
        else if (teamPropPosition == TeamPropPosition.BLUE_MIDDLE) {
            Bot.strafeLeft(0.5, 0.3);
            sleep(100);
            Bot.rotateLeft(0.3,1.2);
            sleep(100);
            Bot.driveForward(0.5, 1.6);
            sleep(100);
            Bot.rotateRight(0.3, 1.5);
            sleep(100);
            Bot.driveForward(.5,1.7);
            sleep(100);
            Bot.rotateRight(.4,3.5);
            sleep(100);
            Bot.driveForward(.4,.355);
            sleep(100);
            Bot.rotateLeft(.4,.2);
            sleep(100);
            Bot.driveBack(0.5,0.15);
//            sleep(100);
//            Bot.collectorPosition();
            sleep(250);
            Bot.leftPixelClawOpen();
            sleep(500);
            Bot.drivePosition();
            sleep(50);
            Bot.leftPixelClawClose();
            sleep(100);
            Bot.driveBack(.5,.55);
            sleep(100);
            Bot.rotateLeft(.5,1.415);
            sleep(100);
            Bot.strafeRight(0.5,1.7);
            sleep(200);
            Bot.rotateRight(0.3,.3);
            sleep(500);
            Bot.strafeRight(0.5,3.45);
            sleep(500);
            Bot.driveBack(0.5,1.9);
            Bot.strafeRight(0.5,0.2);
        }
        else if (teamPropPosition == TeamPropPosition.BLUE_RIGHT){
            Bot.rotateRight(0.3,1.25);
            sleep(1000);
            Bot.driveForward(0.5,0.5);
            sleep(500);
            Bot.leftPixelClawOpen();
            sleep(1500);
            Bot.driveBack(0.5,0.55);
            sleep(100);
            Bot.rotateLeft(0.3,1.55);
            sleep(1000);
            Bot.leftPixelClawClose();
            sleep(500);
            Bot.driveBack(0.5,0.85);
            sleep(500);
            Bot.strafeLeft(0.5,3);

        }
//            else {
//                telemetryUpdate("No Position Detected");
//                Bot.driveForward(0.5,0.55);
//            sleep(1000);
//            Bot.rotateLeft(0.4,2.2);
//            sleep(1000);
//            Bot.driveForward(0.5,3.0);
//            sleep(1000);
////            }

    }


    public void spikeMarkPlaceFar(){
//        teamPropPosition = TeamPropPosition.BLUE_RIGHT;
        if (teamPropPosition == TeamPropPosition.BLUE_LEFT) {
            Bot.rotateLeft(0.3,1.15);
            sleep(1000);
            Bot.driveForward(0.5,0.26);
            sleep(500);
            Bot.leftPixelClawOpen();
            sleep(1500);
            Bot.driveBack(0.5,0.51);
            sleep(100);
            Bot.rotateRight(0.3,1.3);
            sleep(1000);
            Bot.leftPixelClawClose();
            sleep(500);




        }
        else if (teamPropPosition == TeamPropPosition.BLUE_MIDDLE) {

//              CONNOR'S CODE
//            Bot.driveForward(0.5,0.8);
//            sleep(1000);
//            Bot.leftPixelClawOpen();
//            sleep(1500);
//            Bot.leftPixelClawClose();
//            sleep(500);




/*  DUVAL'S CODE */
            Bot.strafeRight(0.5, 0.3);
            sleep(100);
            Bot.rotateRight(0.3,1.2);
            sleep(100);
            Bot.driveForward(0.5, 1.8);
            sleep(100);
            Bot.rotateLeft(0.3, 1.5);
            sleep(100);
            Bot.driveForward(.5,1.7);
            sleep(100);
            Bot.rotateLeft(.4,3.5);
            sleep(100);
            Bot.driveForward(.4,.355);
            sleep(100);
            Bot.rotateRight(.4,.2);
//            sleep(100);
//            Bot.collectorPosition();
            sleep(250);
            Bot.leftPixelClawOpen();
            sleep(500);
            Bot.drivePosition();
            sleep(50);
            Bot.leftPixelClawClose();
            sleep(100);
            Bot.driveBack(.5,.25);
            sleep(100);
            Bot.rotateRight(.5,1.15);
            sleep(100);




        }
        else if (teamPropPosition == TeamPropPosition.BLUE_RIGHT){
            Bot.strafeRight(0.5, 0.3);
            sleep(100);
            Bot.rotateRight(0.3,1.2);
            sleep(1000);
            Bot.driveForward(0.3,0.2);
            sleep(100);
            Bot.driveBack(0.3,0.06);
            sleep(100);
            Bot.leftPixelClawOpen();
            sleep(700);
//            Bot.driveBack(0.3,0.1);
            sleep(500);
            Bot.leftPixelClawClose();
            sleep(500);




        }
    }

    public void spikeMarkPlaceFar_Accel(){
//        teamPropPosition = TeamPropPosition.BLUE_RIGHT;
        if (teamPropPosition == TeamPropPosition.BLUE_LEFT) {

           // Drop Pixel Purple Sequence
            Bot.speedAcceleration(3.4, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, 70);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 70);
            sleep(100);
            Bot.speedAcceleration(0.5, MED_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 60);
            sleep(100);
            Bot.leftPixelClawOpen();
            sleep(500);
            Bot.speedAcceleration(0.7, MED_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, 0);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.leftPixelClawClose();
            sleep(100);

            // Drive Away from Pixel Purple Drop, Rotate, and Drive Backwards
            Bot.speedAcceleration(4.5, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, -91);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, -91);
            sleep(100);
            Bot.speedAcceleration(14.3, FAST_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            sleep(100);
            Bot.speedAcceleration(4.5, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_RIGHT);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, 90);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 90);
            sleep(100);
            dropPixelBackdrop();
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 90);
            sleep(100);
            Bot.speedAcceleration(3.9, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_RIGHT);
            sleep(100);

        }
        else if (teamPropPosition == TeamPropPosition.BLUE_MIDDLE) {

            /*  RIP-OFF ROADRUNNER CODE */
            // Drop Pixel Purple Sequence
            Bot.speedAcceleration(2.8, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.speedAcceleration(1.8, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_RIGHT);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.speedAcceleration(3, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, 115);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 115);
            sleep(100);
            Bot.leftPixelClawOpen();
            sleep(500);
            Bot.speedAcceleration(0.5, MED_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 115);
            sleep(100);
            Bot.speedAcceleration(1.5, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_RIGHT);
            sleep(100);
            Bot.leftPixelClawClose();
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD +.1, -90);
            sleep(100);
            Bot.gyroCorrection(.15, -90);
            sleep(100);

            // Drive Away from Pixel Purple Drop, Rotate, and Drive Backwards
            Bot.speedAcceleration(16, FAST_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
            sleep(100);
            Bot.gyroCorrection(.15, -90);
            sleep(100);
            Bot.speedAcceleration(3.4, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_RIGHT);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, 90);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 90);
            sleep(100);
            dropPixelBackdrop();
            sleep(100);
            Bot.speedAcceleration(3.0, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_RIGHT);
            sleep(100);

        }
        else if (teamPropPosition == TeamPropPosition.BLUE_RIGHT){

            Bot.speedAcceleration(1, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.speedAcceleration(0.38, STRAFE_SPD-.05, MecanumDrive.driveDirections.STRAFE_LEFT);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.speedAcceleration(2.2, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.speedAcceleration(1.8, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_RIGHT);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.leftPixelClawOpen();
            sleep(500);
            Bot.speedAcceleration(.2, STRAFE_SPD-.05, MecanumDrive.driveDirections.STRAFE_LEFT);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.speedAcceleration(4.05, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, -90);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            sleep(100);


            // Drive Away from Pixel Purple Drop, Rotate, and Drive Backwards
            Bot.speedAcceleration(15, FAST_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
            sleep(100);
            Bot.gyroCorrection(.15, -90);
            sleep(100);
            Bot.speedAcceleration(2.5, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_RIGHT);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, 90);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 90);
            sleep(100);
            dropPixelBackdrop();
            sleep(100);
            Bot.speedAcceleration(2.1, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_RIGHT);
            sleep(100);



        }
    }


    public void spikeMarkPlaceFar_Backstage(){
        //teamPropPosition = TeamPropPosition.BLUE_RIGHT;
        if (teamPropPosition == TeamPropPosition.BLUE_LEFT) {

            Bot.speedAcceleration(1.2, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.speedAcceleration(1.1, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_LEFT);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.speedAcceleration(1.0, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.speedAcceleration(.2, FAST_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.leftPixelClawOpen();
            sleep(500);

//
            Bot.speedAcceleration(.5, FAST_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, 90);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 90);
            Bot.leftPixelClawClose();
            sleep(100);
            Bot.speedAcceleration(2.5, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.speedAcceleration(0.8, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_RIGHT);


            Bot.gyroCorrection(GYRO_CORRECT_SPD, 90);
            sleep(100);
//
            dropPixelBackdrop();
//
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 90);
            sleep(100);
            Bot.speedAcceleration(2, FAST_SPD, MecanumDrive.driveDirections.STRAFE_LEFT);
            sleep(100);

        }
        else if (teamPropPosition == TeamPropPosition.BLUE_MIDDLE) {

            /*  RIP-OFF ROADRUNNER CODE */
            Bot.speedAcceleration(2.8, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.speedAcceleration(1.5, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_LEFT);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.speedAcceleration(3, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, -140);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, -105);
            sleep(100);
            Bot.leftPixelClawOpen();
            sleep(500);
            Bot.speedAcceleration(0.5, MED_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, 90);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 90);
            Bot.leftPixelClawClose();
            sleep(100);
            Bot.speedAcceleration(1.5, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 90);
            sleep(100);
            Bot.speedAcceleration(2.5, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_LEFT);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 90);
            sleep(100);

            dropPixelBackdrop();

            Bot.gyroCorrection(GYRO_CORRECT_SPD, 90);
            sleep(100);
            Bot.speedAcceleration(3.5, FAST_SPD, MecanumDrive.driveDirections.STRAFE_LEFT);
            sleep(100);

        }
        else if (teamPropPosition == TeamPropPosition.BLUE_RIGHT){

            Bot.speedAcceleration(3.4, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, -70);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, -70);
            sleep(100);
            Bot.speedAcceleration(0.5, MED_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, -51);
            sleep(100);
            Bot.leftPixelClawOpen();
            sleep(500);


            Bot.speedAcceleration(0.7, MED_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, 90);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 90);
            Bot.leftPixelClawClose();
            sleep(100);
            Bot.speedAcceleration(4.1, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 90);
            sleep(100);
            Bot.speedAcceleration(1.2, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_RIGHT);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 90);
            sleep(100);
            dropPixelBackdrop();
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 90);
            sleep(100);
            Bot.speedAcceleration(4.5, FAST_SPD, MecanumDrive.driveDirections.STRAFE_LEFT);
            sleep(100);



        }
    }

    public void driveToBackdropFar () {
        if (teamPropPosition == TeamPropPosition.BLUE_LEFT) {
            Bot.driveForward(.5, 3.2);
            sleep(200);
            Bot.rotateLeft(.4, 2.5);
            sleep(100);
            Bot.driveForward(.5, 7);
            sleep(100);



        }
        else if (teamPropPosition == TeamPropPosition.BLUE_MIDDLE) {
           Bot.driveForward(.5,8.4);
           sleep(50);
        //   Bot.strafeLeft(.4,.3);


        }
        else if (teamPropPosition == TeamPropPosition.BLUE_RIGHT){
            Bot.rotateLeft(0.3,0.7);
            sleep(100);
            Bot.driveBack(0.3, 0.2);
            sleep(100);
            Bot.rotateLeft(0.3, 0.65);
            sleep(100);
            Bot.strafeLeft(0.5, 0.25);
            sleep(100);
            Bot.driveForward(0.5, 3.3);
            sleep(100);
            Bot.rotateLeft(.4, 2.35);
            sleep(100);
            Bot.driveForward(0.5, 6);
            sleep(100);


        }
    }


    public void placeOnBackdrop(){
        if (teamPropPosition == TeamPropPosition.BLUE_LEFT) {
            Bot.driveForward(.4,1.7);
            sleep(100);
            Bot.rightWormgearUp(1,600);
            sleep(100);
            Bot.strafeLeft(.5,4.55);
            sleep(100);
            Bot.autoPlacePosition();
            sleep(100);
            Bot.rotateLeft(0.3,.2);
            sleep(400);
            Bot.driveForward(.25);
            sleep(750);
            Bot.stopMotors();
            sleep(100);
            Bot.linearSlideExtend(.8);
            sleep(75);
            Bot.stopLinearSlide();
            sleep(300);

            Bot.rightPixelClawClose();
            sleep(1500);
            Bot.driveBack(.5);
            sleep(300);
            Bot.stopMotors();
            Bot.rightPixelClawOpen();
            sleep(100);
//            Bot.linearSlideRetract(.8);
//            sleep(100);
            Bot.strafeRight(.5,3.6);
            sleep(100);
            Bot.driveForward(.5,.7);


        }
        else if (teamPropPosition == TeamPropPosition.BLUE_MIDDLE) {
            Bot.driveForward(.4,1.25);
            sleep(100);
            Bot.rightWormgearUp(1,600);
            sleep(100);
            Bot.strafeLeft(.5,3.3);
            sleep(100);
            Bot.autoPlacePosition();
            sleep(100);
            Bot.driveForward(.25);
            sleep(750);
            Bot.stopMotors();
            sleep(1000);
            Bot.linearSlideExtend(.8);
            sleep(75);
            Bot.stopLinearSlide();
            sleep(300);

            Bot.rightPixelClawClose();
            sleep(1500);
            Bot.driveBack(.5);
            sleep(300);
            Bot.stopMotors();
            Bot.rightPixelClawOpen();
            sleep(100);
//            Bot.linearSlideRetract(.8);
//            sleep(100);
            Bot.strafeRight(.5,3);
            sleep(100);
            Bot.driveForward(.5,.7);


        }

        else if (teamPropPosition == TeamPropPosition.BLUE_RIGHT) {
            Bot.driveForward(.4,2);
            sleep(100);
            Bot.rightWormgearUp(1,600);
            sleep(100);
            Bot.strafeLeft(.5,2.05);
            sleep(100);
            Bot.autoPlacePosition();
            sleep(100);
            Bot.driveForward(.25);
            sleep(750);
            Bot.stopMotors();

            Bot.linearSlideExtend(.8);
            sleep(90);
            Bot.stopLinearSlide();
            sleep(300);

            Bot.rightPixelClawClose();
            sleep(1500);
            Bot.driveBack(.5);
            sleep(300);
            Bot.stopMotors();
            Bot.rightPixelClawOpen();
            sleep(100);
//            Bot.linearSlideRetract(.8);
//            sleep(100);
            Bot.strafeRight(.5,2.1);
            sleep(100);
            Bot.driveForward(.5,.7);

        }
    }


    public void dropPixelBackdrop() {
//        Need full power or motor stalls.
        Bot.rightWormgearUp(1, 430);
        sleep(100);
        Bot.driveForward(.3);
        sleep(1000);
        Bot.stopMotors();
        Bot.linearSlideExtend(.8,390);
        sleep(500);
        Bot.rightPixelClawClose();
        sleep(1500);
        Bot.linearSlideRetract(.8,200);
        sleep(500);
        Bot.speedAcceleration(0.8, MAX_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
        sleep(1000);
        Bot.stopMotors();
    }



}
