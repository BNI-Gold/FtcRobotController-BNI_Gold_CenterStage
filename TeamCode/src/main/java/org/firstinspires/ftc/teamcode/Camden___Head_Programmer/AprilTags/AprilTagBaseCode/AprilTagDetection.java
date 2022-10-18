package org.firstinspires.ftc.teamcode.Camden___Head_Programmer.AprilTags.AprilTagBaseCode;

import org.opencv.core.Point;

public class AprilTagDetection
{
    public int id;
    public int hamming;
    public float decisionMargin;
    public Point center;
    public Point[] corners;
    public AprilTagPose pose;
}
