package org.firstinspires.ftc.teamcode.CompititionUltimateGoal.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class AutoMain extends LinearOpMode {

    public TargetZone zone = null;

    public TargetZone detectStarterStack () {
        zone = TargetZone.A;
        return zone;
    }

}
