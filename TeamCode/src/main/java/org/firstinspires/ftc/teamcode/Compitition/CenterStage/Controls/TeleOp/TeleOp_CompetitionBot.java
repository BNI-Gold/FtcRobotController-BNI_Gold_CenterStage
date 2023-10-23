package org.firstinspires.ftc.teamcode.Compitition.CenterStage.Controls.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Compitition.CenterStage.Robots.CompBot;

@TeleOp (name = "A - Center Stage - 'RANGER RATTLE'")
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

    public boolean slowMode = false;

    public enum DriverControl {BEN, MITCHELL}
    public DriverControl driverControl = DriverControl.BEN;

    public CompBot Bot = new CompBot();

    public void init () {
        Bot.initRobot(hardwareMap);
    }
    public void init_loop() {  }

    public void start() {

    }

    public void loop(){
        speedControl();
//        driverControlChanger();
        endgameArm();
        pixelMechanismControl();
        drive();
        telemetryOutput();
    }

    public void speedControl() {

        if (gamepad1.left_trigger > 0.35) {

            slowMode = true;

        } else {

            slowMode = false;

        }

//        if (gamepad1.left_bumper) {
//
//            if (slowMode) {
//
//                slowMode = false;
//
//            } else if (!slowMode) {
//
//                slowMode = true;
//
//            }
//
//        }

        if (slowMode) {

            speedMultiply = 0.3;

        } else {

            speedMultiply = 1;

        }
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
            Bot.frontLeftMotor.setPower(frontLeftSpeed);
        } else {
            Bot.frontLeftMotor.setPower(frontLeftSpeed * speedMultiply);
        }

        if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold){
            frontRightSpeed = 0;
            Bot.frontRightMotor.setPower(frontRightSpeed);
        } else {
            Bot.frontRightMotor.setPower(frontRightSpeed * speedMultiply);
        }

        if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
            rearLeftSpeed = 0;
            Bot.rearLeftMotor.setPower(rearLeftSpeed);
        } else {
            Bot.rearLeftMotor.setPower(rearLeftSpeed * speedMultiply);
        }

        if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold){
            rearRightSpeed = 0;
            Bot.rearRightMotor.setPower(rearRightSpeed);
        } else {
            Bot.rearRightMotor.setPower(rearRightSpeed * speedMultiply);
        }
    }

    public void pixelMechanismControl() {
        switch (driverControl) {
            case BEN:


            if (gamepad2.right_trigger > 0.2) {
                Bot.linearSlideExtend(viperSlidePower);
            } else if (gamepad2.left_trigger > 0.2) {
                Bot.linearSlideRetract(viperSlidePower);
            } else {
                Bot.viperSlideRight.setPower(0);
            }

            if (Math.abs(Bot.viperSlideRight.getCurrentPosition()) > viperSlideMaxTicks) {
                Bot.viperSlideRight.setPower(0);
            } else if (Math.abs(Bot.viperSlideRight.getCurrentPosition()) <= viperSlideMinTicks) {
                Bot.viperSlideRight.setPower(0);
            }

            if (gamepad2.right_stick_y < -0.1) {
                Bot.rightWormgearUp(wormgearPower);
            } else if (gamepad2.right_stick_y > 0.1) {
                Bot.rightWormgearDown(wormgearPower);
            } else {
                Bot.wormgearRight.setPower(0);
            }

            if (gamepad2.left_bumper) {
                Bot.pixelClaw.setPosition(.35);
            } else if (gamepad2.right_bumper) {
                Bot.pixelClaw.setPosition(.9);
            }

            break;

            case MITCHELL:
                if (gamepad2.right_stick_y < -0.1) {
                    Bot.rightWormgearDown(wormgearPower);
                } else if (gamepad2.right_stick_y > 0.1) {
                    Bot.rightWormgearUp(wormgearPower);
                } else {
                    Bot.wormgearRight.setPower(0);
                }


                if (gamepad2.right_stick_x > 0.1) {
                    Bot.linearSlideExtend(viperSlidePower);
                } else if (gamepad2.right_stick_x < -0.1) {
                    Bot.linearSlideRetract(viperSlidePower);
                } else {
                    Bot.viperSlideRight.setPower(0);
                }

                if (Math.abs(Bot.viperSlideRight.getCurrentPosition()) > viperSlideMaxTicks) {
                    Bot.viperSlideRight.setPower(0);
                } else if (Math.abs(Bot.viperSlideRight.getCurrentPosition()) <= viperSlideMinTicks) {
                    Bot.viperSlideRight.setPower(0);
                }

                if (gamepad2.left_bumper) {
                    Bot.pixelClaw.setPosition(.35);
                } else if (gamepad2.right_bumper) {
                    Bot.pixelClaw.setPosition(.9);
                }

                // DPAD UP SETS SERVO TO UP POSITION | UP POSITION MAY BE 0 OR 1 IDK
                if (gamepad2.dpad_up) {

                    Bot.pixelRotator.setPosition(pixelRotationUp);

                }

                // DPAD UP SETS SERVO TO DOWN POSITION | DOWN POSITION MAY BE 0 OR 1 IDK
                if (gamepad2.dpad_down) {

                    Bot.pixelRotator.setPosition(pixelRotationDown);

                }

                // DPAD RIGHT - MIDDLE POSITION
                if (gamepad2.dpad_right) {

                    Bot.pixelRotator.setPosition(pixelRotationMiddle);

                }


                break;

        }


    }

    public void endgameArm() {
       switch (driverControl) {
           case MITCHELL:
           if (gamepad2.left_stick_y < -0.1) {
               Bot.endgameArmRetract();
           } else if (gamepad2.left_stick_y > 0.1) {
               Bot.endgameArmExtend();
           } else {
               Bot.endgameArmStop();
           }

           if (gamepad2.y) {
               Bot.endgameArmRotatorMovement(.0);
           }
           if (gamepad2.a) {
               Bot.endgameArmRotatorMovement(.8);
           }

           break;
           case BEN:
               if (gamepad2.left_stick_y < -0.1) {
                   Bot.endgameArmRetract();
               } else if (gamepad2.left_stick_y > 0.1) {
                   Bot.endgameArmExtend();
               } else {
                   Bot.endgameArmStop();
               }

               if (gamepad2.a) {
                   Bot.endgameArmRotatorMovement(.0);
               }
               if (gamepad2.y) {
                   Bot.endgameArmRotatorMovement(.8);
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

        telemetry.addData("Front Left: ", Bot.frontLeftMotor.getCurrentPosition());
        telemetry.addData("Front Right: ", Bot.frontRightMotor.getCurrentPosition());
        telemetry.addData("Rear Left: ", Bot.rearLeftMotor.getCurrentPosition());
        telemetry.addData("Rear Right: ", Bot.rearRightMotor.getCurrentPosition());
        telemetry.update();
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


