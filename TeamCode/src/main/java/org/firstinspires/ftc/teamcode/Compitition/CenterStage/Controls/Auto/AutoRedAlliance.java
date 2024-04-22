package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto;

import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Drivetrains.MecanumDrive;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.RoadRunner.drive.RoadrunMecanumDrive;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots.CompBot;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Vision.TeamPropPosition;
import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Vision.TeamPropPositionPipeline_Gold;

public abstract class AutoRedAlliance extends AutoMain {

        public CompBot Bot = new CompBot();

    public TeamPropPositionPipeline_Gold pipeline = new TeamPropPositionPipeline_Gold("RED", 140);

    public void CameraDetection() {
        teamPropPosition = pipeline.getAnalysis();
        telemetry.addData("Position Detected: ", teamPropPosition);
        telemetry.update();
        sleep(150);


        stopCamera();
        telemetry.addLine("Stopping Camera");
        telemetry.update();
        //sleep(150);

    }


    public void slideExtendRR(){
        Bot.linearSlideExtend(.8,350);

    }

    public void slideRetractRR (){
        Bot.linearSlideRetract(.8,200);
    }



    public void spikeMarkPlaceClose(){
        if (teamPropPosition == TeamPropPosition.RED_LEFT) {
            Bot.rotateLeft(0.3,1.25);
            sleep(1000);
            Bot.driveForward(0.5,0.5);
            sleep(500);
            Bot.leftPixelClawOpen();
            sleep(1500);
            Bot.driveBack(0.5,0.55);
            sleep(100);
            Bot.rotateRight(0.3,1.55);
            sleep(1000);
            Bot.leftPixelClawClose();
            sleep(500);
            Bot.driveBack(0.5,0.85);
            sleep(500);
            Bot.strafeRight(0.5,3);
        }
        else if (teamPropPosition == TeamPropPosition.RED_MIDDLE) {
            Bot.strafeRight(0.5, 0.3);
            sleep(100);
            Bot.rotateRight(0.3,1.2);
            sleep(100);
            Bot.driveForward(0.5, 1.6);
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
            Bot.rotateRight(.5,1.415);
            sleep(100);
            Bot.strafeLeft(0.5,1.7);
            sleep(200);
            Bot.rotateLeft(0.3,.3);
            sleep(500);
            Bot.strafeLeft(0.5,3.45);
            sleep(500);
            Bot.driveBack(0.5,1.9);
            Bot.strafeLeft(0.5,0.2);
        }
        else if (teamPropPosition == TeamPropPosition.RED_RIGHT){
            Bot.rotateRight(0.3,0.85);
            sleep(1000);
            Bot.driveForward(0.3,0.1);
            sleep(100);
            Bot.leftPixelClawOpen();
            sleep(700);
//            Bot.driveBack(0.3,0.1);
            sleep(500);
            Bot.leftPixelClawClose();
            sleep(500);
            Bot.rotateLeft(0.3,0.85);
            sleep(100);
            Bot.driveBack(0.3, 1.25);
            sleep(100);
            Bot.rotateRight(0.3, 2.35);
            sleep(100);
            Bot.driveForward(0.5,2.6);
            Bot.strafeRight(0.5,0.3);
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
//        teamPropPosition = TeamPropPosition.RED_LEFT;
        if (teamPropPosition == TeamPropPosition.RED_LEFT) {
            Bot.strafeLeft(0.5, 0.3);
            sleep(100);
            Bot.rotateLeft(0.3,0.85);
            sleep(1000);
//            Bot.driveForward(0.3,0.1);
//            sleep(100);
//            Bot.driveBack(0.3,0.055);
//            sleep(100);
//            Bot.rotateRight(.3,.15);
            sleep(100);
            Bot.leftPixelClawOpen();
            sleep(700);
//            Bot.driveBack(0.3,0.1);
            sleep(500);
            Bot.leftPixelClawClose();
            sleep(500);



        }
        else if (teamPropPosition == TeamPropPosition.RED_MIDDLE) {
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
//            Bot.driveForward(.4,.355);
//            sleep(100);
//            Bot.rotateLeft(.4,.2);
//            sleep(100);
            Bot.driveForward(0.5,0.3);
            sleep(100);
            Bot.driveBack(0.5,0.06);
            sleep(200);
//            Bot.collectorPosition();
//            sleep(250);
            Bot.leftPixelClawOpen();
            sleep(500);
            Bot.drivePosition();
            sleep(50);
            Bot.leftPixelClawClose();
            sleep(100);
            Bot.driveBack(.5,.55);
            sleep(100);
            Bot.rotateLeft(.5,1.55);
            sleep(100);

        }
        else if (teamPropPosition == TeamPropPosition.RED_RIGHT){
            Bot.rotateRight(0.3,1.4);
            sleep(1000);
            Bot.driveForward(0.5,0.4);
            sleep(500);
            Bot.leftPixelClawOpen();
            sleep(1500);
            Bot.driveBack(0.5,0.45);
            sleep(100);
            Bot.rotateLeft(0.3,1.7);
            sleep(1000);
            Bot.leftPixelClawClose();
            sleep(500);

        }
    }

//    FOR PLACING FROM AUDIANCE!!!
    public void spikeMarkPlaceFar_Accel(){
        if (teamPropPosition == TeamPropPosition.RED_LEFT) {
            Bot.speedAcceleration(3, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(SLEEP_TIME);
            Bot.gyroCorrection(GYRO_PATH_SPD, 45);
            sleep(SLEEP_GYRO);
            Bot.leftPixelClawOpen();
            Bot.speedAcceleration(.5, MED_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
            sleep(100);
            Bot.speedAcceleration(.5, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_RIGHT);
            sleep(100);
            Bot.speedAcceleration(.2, MED_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroPath(GYRO_CORRECT_SPD, 25);
            sleep(150);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 25);
            sleep(150);
            Bot.speedAcceleration(.1, MED_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.leftPixelClawClose();
            sleep(2000);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.speedAcceleration(0.13 ,    STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_LEFT); //.11
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(2000);
            Bot.speedAcceleration(4.0, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, 90);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 90);
            sleep(100);
            //            DRIVE TO BACKDROP
            Bot.speedAcceleration(13.1, FAST_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 90);
            sleep(100);
            Bot.speedAcceleration(2.5, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_LEFT);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, -90);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            sleep(100);

            dropPixelBackdrop();

//            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            sleep(100);

            Bot.speedAcceleration(1.0, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_LEFT);



        }
        else if (teamPropPosition == TeamPropPosition.RED_MIDDLE) {
            Bot.speedAcceleration(2.8, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.speedAcceleration(1.7, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_LEFT);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.speedAcceleration(3, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, -115);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, -115);
            sleep(100);
            Bot.leftPixelClawOpen();
            sleep(500);
            Bot.speedAcceleration(0.5, MED_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, -115);
            sleep(100);
            Bot.speedAcceleration(1.4, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_LEFT);
            sleep(100);
            Bot.leftPixelClawClose();
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD +.1, 90);
            sleep(100);
            Bot.gyroCorrection(.15, 90);
            sleep(100);
//            DRIVE TO BACKDROP
            Bot.speedAcceleration(15, FAST_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
            sleep(100);
            Bot.gyroCorrection(.15, 90);
            sleep(100);
            Bot.speedAcceleration(3.55, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_LEFT);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, -90);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            sleep(100);

            dropPixelBackdrop();

//            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            sleep(100);
            Bot.speedAcceleration(3.4, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_LEFT);



        }
        else if (teamPropPosition == TeamPropPosition.RED_RIGHT){
            Bot.speedAcceleration(3.7, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, -90);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            sleep(100);
            Bot.speedAcceleration(0.12, MED_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            sleep(100);
            Bot.leftPixelClawOpen();
            sleep(500);
            Bot.speedAcceleration(0.3, MED_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, 0);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.leftPixelClawClose();
            sleep(100);
            Bot.speedAcceleration(3.95, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, 90);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 90);
            sleep(100);

            //            DRIVE TO BACKDROP
            Bot.speedAcceleration(13.3, FAST_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 90);
            sleep(100);
            Bot.speedAcceleration(3.8, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_LEFT);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, -90);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            sleep(100);


            dropPixelBackdrop();

//            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            sleep(100);
            Bot.speedAcceleration(4.3, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_LEFT);

        }
    }

    public void spikeMarkPlaceBasckstage_Accel(){
//        teamPropPosition = TeamPropPosition.RED_RIGHT;
        if (teamPropPosition == TeamPropPosition.RED_LEFT) {
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
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 51);
            sleep(100);
            Bot.leftPixelClawOpen();
            sleep(500);
            Bot.speedAcceleration(0.7, MED_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, -90);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            Bot.leftPixelClawClose();
            sleep(100);
            Bot.speedAcceleration(3.8, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            sleep(100);
            Bot.speedAcceleration(2.4, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_LEFT);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            sleep(100);
            dropPixelBackdrop();
//            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            sleep(100);
            Bot.speedAcceleration(5.5, FAST_SPD, MecanumDrive.driveDirections.STRAFE_RIGHT);
            sleep(100);
            Bot.speedAcceleration(0.45, MAX_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);




        }
        else if (teamPropPosition == TeamPropPosition.RED_MIDDLE) {
            Bot.speedAcceleration(2.8, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
//            Change for how close to center line!
            Bot.speedAcceleration(1.55, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_RIGHT);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.speedAcceleration(3, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, 140);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 105);
            sleep(100);
            Bot.leftPixelClawOpen();
            sleep(500);
            Bot.speedAcceleration(0.5, MED_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, -90);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            sleep(100);
            Bot.leftPixelClawClose();

            Bot.speedAcceleration(1.4, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            sleep(100);
            Bot.speedAcceleration(1.6, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_RIGHT);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            sleep(100);

            dropPixelBackdrop();

//            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            sleep(100);
            Bot.speedAcceleration(4.3, FAST_SPD, MecanumDrive.driveDirections.STRAFE_RIGHT);
            sleep(100);
            Bot.speedAcceleration(0.45, MAX_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);







        }
        else if (teamPropPosition == TeamPropPosition.RED_RIGHT){
            Bot.speedAcceleration(1.2, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, 0);
            sleep(100);
            Bot.speedAcceleration(1.3, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_RIGHT);
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
            Bot.speedAcceleration(.5, FAST_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
            sleep(100);
            Bot.gyroPath(GYRO_PATH_SPD, -90);
            sleep(100);
            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            Bot.leftPixelClawClose();
            sleep(100);
            Bot.speedAcceleration(2.2, FAST_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);
            Bot.speedAcceleration(2.0, STRAFE_SPD, MecanumDrive.driveDirections.STRAFE_LEFT);


            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            sleep(100);

            dropPixelBackdrop();

//            Bot.gyroCorrection(GYRO_CORRECT_SPD, -90);
            sleep(100);
            Bot.speedAcceleration(3.3, FAST_SPD, MecanumDrive.driveDirections.STRAFE_RIGHT);
            sleep(100);
            Bot.speedAcceleration(0.45, MAX_SPD, MecanumDrive.driveDirections.DRIVE_FORWARD);
            sleep(100);

        }
    }
    public void driveToBackdropFar () {
        if (teamPropPosition == TeamPropPosition.RED_LEFT) {
            Bot.rotateRight(0.3,0.25);
            sleep(100);
            Bot.driveBack(0.3, 0.2);
            sleep(100);
            Bot.rotateRight(0.3, 0.24);
            sleep(100);
//            Key line to keep robot in between purple pixel & truss
            Bot.strafeRight(0.5, 0.52); //0.5
            sleep(100);
            Bot.driveForward(0.5, 3.5);
            sleep(100);
            Bot.rotateRight(.4, 2.55);
            sleep(100);
            Bot.driveForward(0.5, 6);
            sleep(100);
            Bot.strafeRight(0.5, 0.4); // added to keep on "red" side

        }
        else if (teamPropPosition == TeamPropPosition.RED_MIDDLE) {
            Bot.driveForward(.5,8.4);
            sleep(100);
            Bot.strafeRight(0.5, 0.4); // added to keep on "red" side
            sleep(100);
//            Bot.strafeLeft(.4,.3);


        }
        else if (teamPropPosition == TeamPropPosition.RED_RIGHT){



            Bot.driveForward(.5, 3.25);
            sleep(200);
            Bot.rotateRight(.4, 2.45);
            sleep(100);
            Bot.driveForward(.5, 7);
            sleep(100);
            Bot.strafeRight(0.5, 0.4); // added to keep on "red" side



        }
    }


    public void placeOnBackdrop(){
        if (teamPropPosition == TeamPropPosition.RED_LEFT) {
            Bot.driveForward(.4,2);
            sleep(100);
//            Bot.rightWormgearUp(1,600);  //480
            Bot.rightWormgearDown(1);
            sleep(700);
            Bot.rightWormgearStop();
            Bot.strafeRight(.5,1.6);
            sleep(100);
            Bot.autoPlacePosition();
            sleep(100);
            Bot.driveForward(.25);
            sleep(750);
            Bot.stopMotors();


            Bot.linearSlideExtend(.8);
            sleep(200);  //125
            Bot.stopLinearSlide();
            sleep(300);
//            Make robot turn more so yellow against backdrop
            Bot.rotateLeft(.2);
            sleep(200);
            Bot.stopMotors();
            sleep(100);
            Bot.driveForward(.25);
            sleep(200);
            Bot.stopMotors();
            sleep(200);
            Bot.rightPixelClawClose();
            sleep(1500);
            Bot.driveBack(1);
            sleep(300);
            Bot.stopMotors();
            sleep(100);
            Bot.rotateLeft(.5, .2);  //FULL SPeed reverse causes robot to end at angle.  To fix
            sleep(100);
            Bot.rightPixelClawOpen();
            sleep(100);
//            Bot.linearSlideRetract(.8);
//            sleep(100);
            Bot.strafeLeft(.5,1.5);
            sleep(100);
            Bot.driveForward(.5,.7);


        }
        else if (teamPropPosition == TeamPropPosition.RED_MIDDLE) {
            Bot.driveForward(.4,1.05);
            sleep(100);
//            Bot.rightWormgearUp(1,550);  // 480
            Bot.rightWormgearDown(1);
            sleep(700);
            Bot.rightWormgearStop();
            Bot.strafeRight(.5,2.2);   // 1.65
            sleep(100);
            Bot.autoPlacePosition();
            sleep(100);
            Bot.driveForward(.35);
            sleep(900);
            Bot.stopMotors();
            sleep(1000);
            Bot.strafeLeft(0.3,.3);
            sleep(200);
            Bot.linearSlideExtend(.8);
            sleep(200);
            Bot.stopLinearSlide();
            sleep(300);

            Bot.rightPixelClawClose();
            sleep(1500);
            Bot.driveBack(1);  //0.5
            sleep(300);
            Bot.stopMotors();
            sleep(100);
            Bot.rotateLeft(.5, .2);  //FULL SPeed reverse causes robot to end at angle.  To fix
            sleep(100);
            Bot.rightPixelClawOpen();
            sleep(100);
//            Bot.linearSlideRetract(.8);
//            sleep(100);
            Bot.strafeLeft(.5,2.1);
            sleep(100);
            Bot.driveForward(.5,.7);


        }

        else if (teamPropPosition == TeamPropPosition.RED_RIGHT) {
            Bot.driveForward(.4,1.7);
            sleep(100);
//            Bot.rightWormgearUp(1,550); //480
            Bot.rightWormgearDown(1);
            sleep(700);
            Bot.rightWormgearStop();

            Bot.strafeRight(.5,2.4);
            sleep(100);
            Bot.autoPlacePosition();
            sleep(100);
            Bot.driveForward(.35);
            sleep(950);
            Bot.stopMotors();
            sleep(100);
            Bot.rotateLeft(0.3,0.3);
            sleep(200);
            Bot.strafeLeft(0.3,.35);
            sleep(200);
            Bot.linearSlideExtend(.9);
            sleep(240);
            Bot.stopLinearSlide();
            sleep(300);

            Bot.rightPixelClawClose();
            sleep(1500);
            Bot.driveBack(1.0);
            sleep(300);
            Bot.stopMotors();
            sleep(100);
            Bot.rotateLeft(.5, .2);  //FULL SPeed reverse causes robot to end at angle.  To fix
            sleep(100);
            Bot.rightPixelClawOpen();
            sleep(100);
//            Bot.linearSlideRetract(.8);
//            sleep(100);
            Bot.strafeLeft(.5,3.05);
            sleep(100);
            Bot.driveForward(.5,.7);
        }
    }


    public void dropPixelBackdrop() {
//        Need full power or motor stalls.
        Bot.rightWormgearUp(1, 530);
        sleep(100);
        Bot.autoPlacePosition();
        Bot.driveForward(.3);
        sleep(1000);
        Bot.stopMotors();
        Bot.linearSlideExtend(.8,390);
        sleep(500);
        Bot.rightPixelClawClose();
        sleep(1500);
        Bot.linearSlideRetract(.8,200);
        sleep(500);
        Bot.speedAcceleration(0.7, MAX_SPD, MecanumDrive.driveDirections.DRIVE_BACK);
        sleep(1000);
        Bot.stopMotors();
    }



}
