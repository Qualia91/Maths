package com.boc_dev.maths.points_on_a_sphere;

import com.boc_dev.maths.objects.vector.Vec2d;
import com.boc_dev.maths.objects.vector.Vec3d;
import org.junit.jupiter.api.Test;

class SpiralAlgorithmsTest {

    @Test
    void fibonacciSphereD() {

        SpiralAlgorithms spiralAlgorithms = new SpiralAlgorithms();

        Vec3d[] vec3ds = spiralAlgorithms.fibonacciSphereD(100);

        for (Vec3d vec3d : vec3ds) {
            System.out.println(vec3d);
        }

    }

    @Test
    void fibonacciSphereF() {
    }

    @Test
    void sunflowerDiskSpiral() {

        SpiralAlgorithms spiralAlgorithms = new SpiralAlgorithms();

        Vec2d[] vec2ds = spiralAlgorithms.sunflowerDiskSpiral(1000);

        for (Vec2d vec2d : vec2ds) {
            System.out.println(vec2d);
        }

    }
}