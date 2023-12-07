//package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.VisionAuto;
//
//import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.Auto.AutoMain;
//import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots.CompBot;
////import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Vision.TeamPropPosition;
//
//public abstract class BackstagePlace extends AutoMain {
//
//    private final int oneSecond = 1000;
//    public void visionPlace(CompBot Bot, TeamPropPositionPipeline.TeamPropPosition target) {
//
//        switch (target) {
//
//            case ONE:
//
//                Bot.strafeLeft(0.3, 1);
//
//                sleep(oneSecond);
//
//                placePixels(Bot);
//
//                sleep(oneSecond);
//
//            case TWO:
//
//                placePixels(Bot);
//
//                sleep(oneSecond);
//
//            case THREE:
//
//                Bot.strafeRight(0.3, 1);
//
//                sleep(oneSecond);
//
//                placePixels(Bot);
//
//                sleep(oneSecond);
//
//        }
//
//    }
//
//    public void placePixels(CompBot Bot) {
//
//        sleep(oneSecond);
//
//        // extend arm
//
//        sleep(oneSecond);
//
//        // place pixels
//
//        sleep(oneSecond);
//
//        //retract arm
//
//        sleep(oneSecond);
//
//        //back up
//
//        sleep(oneSecond);
//
//    }
//
//    public void stopDetectionSystem() {
//
//        webcam.stopStreaming();
//        webcam.stopRecordingPipeline();
//
//    }
//
//}
