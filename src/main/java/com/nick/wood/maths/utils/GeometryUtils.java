package com.nick.wood.maths.utils;

import com.nick.wood.maths.objects.vector.Vec3d;
import com.nick.wood.maths.objects.vector.Vec3f;

public class GeometryUtils {

    /**
     *
     * @param planeNormal
     * @param pointOnPlane
     * @param point
     * @return
     */
    public static float HeightAbovePlane(Vec3f planeNormal, Vec3f pointOnPlane, Vec3f point) {
        return (point.subtract(pointOnPlane)).dot(planeNormal);
    }

    public static double HeightAbovePlane(Vec3d planeNormal, Vec3d pointOnPlane, Vec3d point) {
        return (point.subtract(pointOnPlane)).dot(planeNormal);
    }


    /**
     *   B ------- D
     *    \         \
     *     \         \
     *      A ------- C
     *
     * @return
     */
    public static float AreaOfParallelogram(Vec3f A, Vec3f B, Vec3f C) {

        return (B.subtract(A)).cross(C.subtract(A)).length();

    }

    public static double AreaOfParallelogram(Vec3d A, Vec3d B, Vec3d C) {

        return (B.subtract(A)).cross(C.subtract(A)).length();

    }

}
