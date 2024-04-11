package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
//this is dashboard stuff
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class MusselmanLessonHardware extends LinearOpMode {
        //public class names this class that can be used elsewere aka publicly
        //extends LinearOpMode means it runs top to bottom once
        public DcMotor fpd = null, bpd = null, fsd = null, bsd = null, intake = null, aimer = null;
        public DcMotor portArm = null, starArm = null;
        // creates variables that are recognized as DcMotors
        public Servo elbow = null, wrist = null, trigger = null, fingerStar = null, fingerPort = null;

        //creates variables for servos.
        public static double fingerPortOpen = 0.4, fingerPortClosed = 0;
        public static double fingerStarOpen = 0.6, fingerStarClosed = 1;

        public static double wristDown = 0.45, wristScore = 0.45 + .26, wristLift = 0.45 + .13, wristTape = 0.6;

        public static double elbowDown = 0.1, elbowScore = 0.36, elbowLift = 0.23, elbowTape = 0.5;

        public static double triggerUp = .3, triggerDown = 0;
        public static int aimerDown = 0, aimerUp = -180;

        //THIS IS SUPER USEFUL!!! create variables here that you'll want everywhere like
        // common positions
        HardwareMap hwMap = null;
        //says variable hwMap is a hardwaremap that we will tell it about later.


    public MusselmanLessonHardware() {}
        //this creates a method that will assign variables to places in the configuration
        //configuration is set up on driver station
        @Override
        //lets child class do a specific implimentation from a method of a parent class.
        // in this example we are able to create a version of runopmode from linear opmode
        //musselman still doesn't get it but will some day in the future
        //just put it in and don't worry about it. *shrug*
        public void runOpMode() {}
        //when it is selected on driverstation this starts running?
        // tbh not fulley sure again. just leave it *shrug*
        public void init(HardwareMap ahwMap) {
            //init, means when selected but before op mode is run. (when you press init on driver station)
            //the "HardwareMap" part of the parameter means what you put in must be a hardwaremap
            hwMap = ahwMap;
            //whatever parameter you put in will become known as the variable hwMap.
            fpd = hwMap.get(DcMotor.class, "fpd" );
            bpd = hwMap.get(DcMotor.class, "bpd");
            fsd = hwMap.get(DcMotor.class, "fsd");
            bsd = hwMap.get(DcMotor.class, "bsd");

            portArm = hwMap.get(DcMotor.class, "portArm");
            starArm = hwMap.get(DcMotor.class,"starArm");

            intake = hwMap.get(DcMotor.class, "intake");

            aimer = hwMap.get(DcMotor.class, "aimer");

            elbow = hwMap.get(Servo.class, "elbow");
            wrist = hwMap.get(Servo.class, "wrist");
            fingerStar = hwMap.get(Servo.class, "fingerStar");
            fingerPort = hwMap.get(Servo.class, "fingerPort");

            trigger = hwMap.get(Servo.class, "trigger");
            //this connects the string typed into the config in the driver station to the
            // variable you write and also says what class to use when opperating it?
            fpd.setDirection(DcMotorSimple.Direction.REVERSE);
            bpd.setDirection(DcMotorSimple.Direction.FORWARD);
            fsd.setDirection(DcMotorSimple.Direction.REVERSE);
            bsd.setDirection(DcMotorSimple.Direction.REVERSE);

            portArm.setDirection(DcMotorSimple.Direction.FORWARD);
            starArm.setDirection(DcMotorSimple.Direction.REVERSE);

            intake.setDirection(DcMotorSimple.Direction.FORWARD);

            aimer.setDirection(DcMotorSimple.Direction.REVERSE);

            fpd.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            bpd.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            fsd.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            bsd.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            portArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            starArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            aimer.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            fpd.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            bpd.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            fsd.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            bsd.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            portArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            starArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

            aimer.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }



}
