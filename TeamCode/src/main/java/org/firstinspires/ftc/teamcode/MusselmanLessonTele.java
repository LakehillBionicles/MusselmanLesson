package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.MusselmanLessonHardware.*;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.MusselmanLessonHardware;

//@Disabled
//is this class available to run?
@Config
//connects to dashboard
@TeleOp
//what type of opMode is this?
public class MusselmanLessonTele extends LinearOpMode {
    MusselmanLessonHardware robot = new MusselmanLessonHardware();
    //robot = this instance of MusselmanLessonHardware

    public double fingerPosPort = fingerPortClosed, fingerPosStar = fingerStarClosed,
            wristPosPort, wristPosStar, aimerPos = aimerDown, triggerPos = triggerUp, intakePower = 0;
    //init variables

    public boolean intakeOn = false, intakeSpit = false;
    //init more variables

    Gamepad currentGamepad1 = new Gamepad();
    Gamepad currentGamepad2 = new Gamepad();
    Gamepad previousGamepad1 = new Gamepad();
    Gamepad previousGamepad2 = new Gamepad();
    //init current and previous gamepads, allows us to compare what buttons are pressed now and what buttons were pressed last time
    double pulseArmTimer = 0;
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        //allows us to send information between android and dashboard

        robot.init(hardwareMap);
        //init hardware map
        waitForStart();
        //don't continue until start is pressed

        while (opModeIsActive()) {
            previousGamepad1.copy(currentGamepad1);
            previousGamepad2.copy(currentGamepad2);
            currentGamepad1.copy(gamepad1);
            currentGamepad2.copy(gamepad2);
            //copy current and previous gamepad variables, allows us to compare gamepad currently to gamepad last time

            if(gamepad2.dpad_up){
                pulseArmTimer = getRuntime();
                robot.elbow.setPosition(elbowScore);
                robot.wrist.setPosition(wristScore);
            }
            if(pulseArmTimer+0.5>getRuntime()){
                setArmPower((1));
            }else{
                setArmPower((-gamepad2.left_stick_y));
            }

            robot.fpd.setPower(-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);
            robot.bpd.setPower(-gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x);
            robot.fsd.setPower(-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x);
            robot.bsd.setPower(-gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x);
            //set motor powers

            if(!previousGamepad1.back && gamepad1.back){ intakeOn = !intakeOn;}
            if(!previousGamepad1.x && gamepad1.x){ intakeSpit = !intakeSpit;}
            //intake and spit if last time the button wasn't pressed and this time it is

            robot.intake.setPower(getHandPower());
            setArmPower((-gamepad2.left_stick_y));
            //set arm power
            /*
            if(gamepad2.left_bumper){
                robot.wrist.setPosition(wristScore);
                robot.elbow.setPosition(elbowScore);
            } else if(gamepad2.right_bumper){
                robot.elbow.setPosition(elbowDown);
                robot.wrist.setPosition(wristDown);
            }

             */
            intakeSpit = gamepad1.x;
            if(gamepad2.left_bumper){
                robot.wrist.setPosition(0.6);
            }
            if(gamepad2.right_bumper){
                robot.elbow.setPosition(elbowScore);
                robot.wrist.setPosition(wristScore);
            }
            if(gamepad2.left_trigger>0){
                robot.wrist.setPosition(0.6);
            }
            if(gamepad2.right_trigger>0){
                robot.elbow.setPosition(elbowDown);
                robot.wrist.setPosition(wristDown);
            }


            if(gamepad1.a){
                robot.fingerPort.setPosition(fingerPortClosed);
                robot.fingerStar.setPosition(fingerStarClosed);
            }
            if(gamepad1.b){
                robot.fingerPort.setPosition(fingerPortOpen);
                robot.fingerStar.setPosition(fingerStarOpen);
            }
            if(gamepad2.a){
                robot.fingerPort.setPosition(fingerPortClosed);
                robot.fingerStar.setPosition(fingerStarClosed);
            }
            if(gamepad2.b){
                robot.fingerPort.setPosition(fingerPortOpen);
                robot.fingerStar.setPosition(fingerStarOpen);
            }

            if(gamepad2.x){
                robot.wrist.setPosition(wristLift);
                robot.elbow.setPosition(elbowLift);
            }

            if(gamepad1.dpad_up){
                robot.aimer.setPower(.6);
            } else if(gamepad1.dpad_down){
                robot.aimer.setPower(-.3);
            } else {
                robot.aimer.setPower(0);
            }

            robot.trigger.setPosition(getTriggerPos());
            //set positions, line 102 - line 150

            telemetry.addData("BOW", robot.bpd.getCurrentPosition());
            telemetry.addData("POW", robot.fpd.getCurrentPosition());
            telemetry.addData("SOW", robot.fsd.getCurrentPosition());
            telemetry.addData("portArm", robot.portArm.getCurrentPosition());
            telemetry.addData("starArm", robot.starArm.getCurrentPosition());
            telemetry.update();
        }
    }
    public double getFingerPosPort(){
        if (gamepad2.left_bumper){
            fingerPosPort = fingerPortClosed;
        }
        if (gamepad2.left_trigger>0){
            fingerPosPort = fingerPortOpen;
        }
        return fingerPosPort;
    }
    //where did we last send fingerPort?

    public double getFingerPosStar(){
        if (gamepad2.right_bumper){
            fingerPosStar = fingerStarClosed;
        }
        if (gamepad2.right_trigger>0){
            fingerPosStar = fingerStarOpen;
        }
        return fingerPosStar;
    }
    //where did we last send fingerStar?

    public double getAimerPos(){
        if (gamepad1.left_bumper){
            aimerPos = aimerUp;
        }
        if (gamepad1.left_trigger>0){
            aimerPos = aimerDown;
        }
        return aimerPos;
    }
    //where did we last send Aimer?

    public double getTriggerPos(){
        if (gamepad1.left_bumper && gamepad1.right_bumper){
            triggerPos = triggerDown;
        }
        if (gamepad1.right_trigger>0){
            triggerPos = triggerUp;
        }
        return triggerPos;
    }
    //where did we last send trigger?

    public double getHandPower(){
        if(intakeOn){
            intakePower = 1;
        } else if(intakeSpit){
            intakePower = -1;
        }else{
            intakePower = 0;
        }
        return intakePower;
    }
    //what is the intake power set to?

    public void setArmPower(double power) {
        robot.portArm.setPower(power);
        robot.starArm.setPower(power);
    }
    //what is the arm power set to?
}