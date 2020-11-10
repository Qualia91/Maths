package com.boc_dev.maths.points_on_a_sphere;

import com.boc_dev.maths.objects.vector.Vec2d;
import com.boc_dev.maths.objects.vector.Vec3d;
import com.boc_dev.maths.objects.vector.Vec3f;

public class SpiralAlgorithms {


    public Vec3d[] fibonacciSphereD(int samples) {

        Vec3d[] points = new Vec3d[samples];
        double sampleMinusOne = samples - 1.0f;
        double goldenRatio = Math.PI * (3.0 - Math.sqrt(5.0));

        for (int i = 0; i < samples; i++) {

            double y = 1.0 - (i / sampleMinusOne) * 2.0; // 1 -> -1

            double r = Math.sqrt(1.0 - y * y); // radius at y

            double theta = goldenRatio * i; // golden ratio increment

            double x = Math.cos(theta) * r;
            double z = Math.sin(theta) * r;

            points[i] = new Vec3d(x, y, z);

        }

        return points;
    }

    public Vec3f[] fibonacciSphereF(int samples) {

        Vec3f[] points = new Vec3f[samples];
        float sampleMinusOne = samples - 1.0f;
        double goldenRatio = Math.PI * (3.0 - Math.sqrt(5.0));

        for (int i = 0; i < samples; i++) {

            float y = 1.0f - (i / sampleMinusOne) * 2.0f; // 1 -> -1

            double r = Math.sqrt(1.0 - y * y); // radius at y

            double theta = goldenRatio * i; // golden ratio increment

            float x = (float) (Math.cos(theta) * r);
            float z = (float) (Math.sin(theta) * r);

            points[i] = new Vec3f(x, y, z);

        }

        return points;
    }

    // based on the fact that golden ration is the most irrational number
    // if you start at a point, turn a golden ration angle and walk
    // a distance, you will naturally construct a spiral.
    public Vec2d[] sunflowerDiskSpiral(int samples) {

        double goldenRatio = (1.0 + Math.sqrt(5.0)) / 2.0;

        Vec2d[] points = new Vec2d[samples];

        for (int i = 0; i < samples; i++) {

            double r = Math.sqrt((double)i / samples);

            double theta = Math.PI * (1.0 + Math.sqrt(5.0)) * i;

            points[i] = new Vec2d(r*Math.cos(theta), r*Math.sin(theta));

        }

        return points;

    }

    public Vec3d[] sunflowerSphereSpiral(int samples) {

        double goldenRatio = Math.PI * (1.0 + Math.sqrt(5.0));

        Vec3d[] points = new Vec3d[samples];

        for (int i = 0; i < samples; i++) {

            double phi = Math.acos(1.0 - 2.0*i/samples);
            double theta = goldenRatio * i;

            points[i] = new Vec3d(
                    Math.cos(theta) * Math.sin(phi),
                    Math.sin(theta) * Math.sin(phi),
                    Math.cos(phi));

        }

        return points;

    }


}
