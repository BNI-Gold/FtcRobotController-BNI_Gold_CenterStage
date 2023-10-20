package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots.CompBot;

@TeleOp (name = "A_TeleOp_CompBot_Centerstage")
public class TeleOp_CompetitionBot extends OpMode {

    public double rotationPos = 0.5;
    double incValue = 0.05;

    double pixelRotationUp = 1.0;

    double pixelRotationMiddle = 0.5;

    double pixelRotationDown = 0.0;

    double leftStickYVal;
    double leftStickXVal;
    double rightStickXVal;
    double rightStickYVal;

    double frontLeftSpeed;
    double frontRightSpeed;
    double rearLeftSpeed;
    double rearRightSpeed;

    double powerThreshold = 0;
    double speedMultiply = 1;

    public double   wormgearPower = 1;

    public double viperSlidePower = .7;

    public double viperSlideMaxTicks = 250;
    public double viperSlideMinTicks = 1;
    public double wormgearMaxTicks = 100;
    public double wormgearMinTicks = 1;

    public enum DriverControl {BEN, MITCHELL}
    public DriverControl driverControl = DriverControl.BEN;





    public CompBot CompetitionBot = new CompBot();

    public void init () {
        CompetitionBot.initRobot(hardwareMap);
    }
    public void init_loop() {  }


    public void start() {

    }

    public void loop(){
        speedControl();
        driverControlChanger();
        endgameArm();
        pixelMechanismControl();
        drive();
        telemetryOutput();
    }

    public void drive() {

        leftStickYVal = gamepad1.left_stick_y;
        leftStickYVal = Range.clip(leftStickYVal, -1, 1);
        leftStickXVal = -gamepad1.left_stick_x;
        leftStickXVal = Range.clip(leftStickXVal, -1, 1);
        rightStickXVal = -gamepad1.right_stick_x;
        rightStickXVal = Range.clip(rightStickXVal, -1, 1);

        frontLeftSpeed = leftStickYVal + leftStickXVal + rightStickXVal;
        frontLeftSpeed = Range.clip(frontLeftSpeed, -1, 1);

        frontRightSpeed = leftStickYVal - leftStickXVal - rightStickXVal;
        frontRightSpeed = Range.clip(frontRightSpeed, -1, 1);

        rearLeftSpeed = leftStickYVal - leftStickXVal + rightStickXVal;
        rearLeftSpeed = Range.clip(rearLeftSpeed, -1, 1);

        rearRightSpeed = leftStickYVal + leftStickXVal - rightStickXVal;
        rearRightSpeed = Range.clip(rearRightSpeed, -1, 1);

        if (frontLeftSpeed <= powerThreshold && frontLeftSpeed >= -powerThreshold) {
            frontLeftSpeed = 0;
            CompetitionBot.frontLeftMotor.setPower(frontLeftSpeed);
        } else {
            CompetitionBot.frontLeftMotor.setPower(frontLeftSpeed * speedMultiply);
        }

        if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold){
            frontRightSpeed = 0;
            CompetitionBot.frontRightMotor.setPower(frontRightSpeed);
        } else {
            CompetitionBot.frontRightMotor.setPower(frontRightSpeed * speedMultiply);
        }

        if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
            rearLeftSpeed = 0;
            CompetitionBot.rearLeftMotor.setPower(rearLeftSpeed);
        } else {
            CompetitionBot.rearLeftMotor.setPower(rearLeftSpeed * speedMultiply);
        }

        if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold){
            rearRightSpeed = 0;
            CompetitionBot.rearRightMotor.setPower(rearRightSpeed);
        } else {
            CompetitionBot.rearRightMotor.setPower(rearRightSpeed * speedMultiply);
        }
    }

    public void pixelMechanismControl() {
        switch (driverControl) {
            case BEN:


            if (gamepad2.right_trigger > 0.2) {
                CompetitionBot.linearSlideExtend(viperSlidePower);
            } else if (gamepad2.left_trigger > 0.2) {
                CompetitionBot.linearSlideRetract(viperSlidePower);
            } else {
                CompetitionBot.viperSlideRight.setPower(0);
            }

            if (Math.abs(CompetitionBot.viperSlideRight.getCurrentPosition()) > viperSlideMaxTicks) {
                CompetitionBot.viperSlideRight.setPower(0);
            } else if (Math.abs(CompetitionBot.viperSlideRight.getCurrentPosition()) <= viperSlideMinTicks) {
                CompetitionBot.viperSlideRight.setPower(0);
            }

            if (gamepad2.left_stick_y < -0.1) {
                CompetitionBot.rightWormgearUp(wormgearPower);
            } else if (gamepad2.left_stick_y > 0.1) {
                CompetitionBot.rightWormgearDown(wormgearPower);
            } else {
                CompetitionBot.wormgearRight.setPower(0);
            }

            if (gamepad2.left_bumper) {
                CompetitionBot.pixelClaw.setPosition(.35);
            } else if (gamepad2.right_bumper) {
                CompetitionBot.pixelClaw.setPosition(.9);
            }

            break;

            case MITCHELL:
                if (gamepad2.right_stick_y < -0.1) {
                    CompetitionBot.rightWormgearDown(wormgearPower);
                } else if (gamepad2.right_stick_y > 0.1) {
                    CompetitionBot.rightWormgearUp(wormgearPower);
                } else {
                    CompetitionBot.wormgearRight.setPower(0);
                }


                if (gamepad2.right_stick_x > 0.1) {
                    CompetitionBot.linearSlideExtend(viperSlidePower);
                } else if (gamepad2.right_stick_x < -0.1) {
                    CompetitionBot.linearSlideRetract(viperSlidePower);
                } else {
                    CompetitionBot.viperSlideRight.setPower(0);
                }

                if (Math.abs(CompetitionBot.viperSlideRight.getCurrentPosition()) > viperSlideMaxTicks) {
                    CompetitionBot.viperSlideRight.setPower(0);
                } else if (Math.abs(CompetitionBot.viperSlideRight.getCurrentPosition()) <= viperSlideMinTicks) {
                    CompetitionBot.viperSlideRight.setPower(0);
                }

                if (gamepad2.left_bumper) {
                    CompetitionBot.pixelClaw.setPosition(.35);
                } else if (gamepad2.right_bumper) {
                    CompetitionBot.pixelClaw.setPosition(.9);
                }

                // DPAD UP SETS SERVO TO UP POSITION | UP POSITION MAY BE 0 OR 1 IDK
                if (gamepad2.dpad_up) {

                    CompetitionBot.pixelRotator.setPosition(pixelRotationUp);

                }

                // DPAD UP SETS SERVO TO DOWN POSITION | DOWN POSITION MAY BE 0 OR 1 IDK
                if (gamepad2.dpad_down) {

                    CompetitionBot.pixelRotator.setPosition(pixelRotationDown);

                }

                // DPAD RIGHT - MIDDLE POSITION
                if (gamepad2.dpad_right) {

                    CompetitionBot.pixelRotator.setPosition(pixelRotationMiddle);

                }


                break;

        }


    }

    public void endgameArm() {
       switch (driverControl) {
           case MITCHELL:
           if (gamepad2.left_stick_y < -0.1) {
               CompetitionBot.endgameArmRetract();
           } else if (gamepad2.left_stick_y > 0.1) {
               CompetitionBot.endgameArmExtend();
           } else {
               CompetitionBot.endgameArmStop();
           }

           if (gamepad2.y) {
               CompetitionBot.endgameArmRotatorMovement(.3);
           }
           if (gamepad2.a) {
               CompetitionBot.endgameArmRotatorMovement(.8);
           }

           break;
           case BEN:
               if (gamepad2.right_stick_y < -0.1) {
                   CompetitionBot.endgameArmExtend();
               } else if (gamepad2.right_stick_y > 0.1) {
                   CompetitionBot.endgameArmRetract();
               } else {
                   CompetitionBot.endgameArmStop();
               }

               if (gamepad2.y) {
                   CompetitionBot.endgameArmRotatorMovement(.2);
               }
               if (gamepad2.b) {
                   CompetitionBot.endgameArmRotatorMovement(.8);
               }


//        if (gamepad2.a) {
//            CompetitionBot.upTimer.reset();
//            if (CompetitionBot.upTimer.seconds() >= 2.5) {
//                CompetitionBot.endgameArmStop();
//            } else if (CompetitionBot.upTimer.seconds() < 2.5) {
//                CompetitionBot.endgameArmExtend();
//
//            }
//
//        } else if (gamepad2.b) {
//            CompetitionBot.downTimer.reset();
//            if (CompetitionBot.downTimer.seconds() >= 2.4) {
//                CompetitionBot.endgameArmStop();
//            } else if (CompetitionBot.downTimer.seconds() < 2.4) {
//                CompetitionBot.endgameArmRetract();
//            }
//        }
               break;
       }

    }
    public void telemetryOutput() {
        if (driverControl == driverControl.BEN) {
            telemetry.addLine("Driver Control = BEN");
        }
        else if (driverControl == driverControl.MITCHELL) {
            telemetry.addLine("Driver Control = MITCHELL");
        }
        telemetry.addData("pwr", "FL mtr: " + frontLeftSpeed);
        telemetry.addData("pwr", "FR mtr: " + frontRightSpeed);
        telemetry.addData("pwr", "RL mtr: " + rearLeftSpeed);
        telemetry.addData("pwr", "RR mtr: " + rearRightSpeed);
        telemetry.update();
    }

    public void speedControl() {

        if (gamepad1.dpad_up) {
            speedMultiply = 0.5;
        }

        else if (gamepad1.dpad_down) {
            speedMultiply = 1;
        }
    }


    public void driverControlChanger(){
        if (gamepad2.b) {
            driverControl = driverControl.BEN;
        }
        else if (gamepad2.x) {
            driverControl = driverControl.MITCHELL;
        }
    }



}


