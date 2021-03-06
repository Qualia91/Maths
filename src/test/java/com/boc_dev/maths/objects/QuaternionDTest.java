package com.boc_dev.maths.objects;

import com.boc_dev.maths.objects.vector.Vec3d;
import com.boc_dev.maths.objects.matrix.Matrix4d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuaternionDTest {

	@Test
	void rotAroundZTest() {

		Matrix4d rotationMatrix = new Matrix4d(
				0, -1, 0.0, 0.0,
				1, 0, 0.0, 0.0,
				0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, 0.0, 1.0
		);

		double trace = rotationMatrix.trace();

		assertEquals(trace, 1.0, 0.000000001);

		QuaternionD quaternionD = rotationMatrix.toQuaternion();

		assertEquals(quaternionD.getS(), Math.sqrt(2)/2, 0.0000001);
		assertEquals(quaternionD.getI(), 0.0, 0.0000001);
		assertEquals(quaternionD.getJ(), 0.0, 0.0000001);
		assertEquals(quaternionD.getK(), Math.sqrt(2)/2, 0.0000001);

		QuaternionD rotationFromAngle = QuaternionD.RotationZ(Math.toRadians(90));

		assertEquals(rotationFromAngle.getS(), quaternionD.getS(), 0.0000001);
		assertEquals(rotationFromAngle.getI(), quaternionD.getI(), 0.0000001);
		assertEquals(rotationFromAngle.getJ(), quaternionD.getJ(), 0.0000001);
		assertEquals(rotationFromAngle.getK(), quaternionD.getK(), 0.0000001);

		Vec3d vectorToRotate = new Vec3d(1, 0, 0);

		QuaternionD vector = new QuaternionD(vectorToRotate);

		assertEquals(vector.getS(), 0.0, 0.000001);
		assertEquals(vector.getI(), 1.0, 0.000001);
		assertEquals(vector.getJ(), 0.0, 0.000001);
		assertEquals(vector.getJ(), 0.0, 0.000001);

		QuaternionD rotatedVector = rotationFromAngle.rotateVector(vector);
		Vec3d rotatedVec3d = rotatedVector.toVec3d();
		Vec3d expectedRotatedVector = new Vec3d(0.0, 1.0, 0.0);

		assertArrayEquals(rotatedVec3d.getValues(), expectedRotatedVector.getValues(), 0.000001);

	}

	@Test
	void fromVec() {
	}

	@Test
	void rotation() {
	}

	@Test
	void rotationX() {
	}

	@Test
	void rotationY() {
	}

	@Test
	void rotationZ() {
	}

	@Test
	void normalise() {
	}

	@Test
	void getS() {
	}

	@Test
	void getI() {
	}

	@Test
	void getJ() {
	}

	@Test
	void getK() {
	}

	@Test
	void rotateVector() {
	}

	@Test
	void add() {
	}

	@Test
	void multiply() {
	}

	@Test
	void toMatrix() {
	}

	@Test
	void scale() {
	}

	@Test
	void conjugate() {
	}

	@Test
	void len2() {
	}

	@Test
	void len() {
	}

	@Test
	void inverse() {
	}

	@Test
	void toVec3d() {
	}
}