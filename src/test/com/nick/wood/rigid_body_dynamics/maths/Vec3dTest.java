package com.nick.wood.rigid_body_dynamics.maths;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vec3dTest {

	@Test
	void limitCreationTest() {
		Vec3d one = new Vec3d(new Vec3d(1.0, 3.0, 2.0), 2);
		assertEquals(one.getX(), 1);
		assertEquals(one.getY(), 2);
		assertEquals(one.getZ(), 2);
	}

	@Test
	void getXTest() {
		Vec3d one = new Vec3d(2, 3, 4);
		assertEquals(one.getX(), 2);
	}

	@Test
	void getYTest() {
		Vec3d one = new Vec3d(2, 3, 4);
		assertEquals(one.getY(), 3);
	}

	@Test
	void getZTest() {
		Vec3d one = new Vec3d(2, 3, 4);
		assertEquals(one.getZ(), 4);
	}

	@Test
	void addTest() {
		Vec3d one = new Vec3d(2, 3, 4);
		Vec3d two = new Vec3d(5, 6, 7);
		Vec3d three = one.add(two);
		assertEquals(three.getX(), 7);
		assertEquals(three.getY(), 9);
		assertEquals(three.getZ(), 11);
	}

	@Test
	void subtractTest() {
		Vec3d one = new Vec3d(2, 3, 4);
		Vec3d two = new Vec3d(5, 6, 7);
		Vec3d three = one.subtract(two);
		assertEquals(three.getX(), -3);
		assertEquals(three.getY(), -3);
		assertEquals(three.getZ(), -3);
	}

	@Test
	void scaleTest() {
		Vec3d one = new Vec3d(2, 3, 4);
		Vec3d two = one.scale(3);
		assertEquals(two.getX(), 6);
		assertEquals(two.getY(), 9);
		assertEquals(two.getZ(), 12);
	}

	@Test
	void dotTest() {
		Vec3d one = new Vec3d(9, 2, 7);
		Vec3d two = new Vec3d(4, 8, 10);
		double a = one.dot(two);
		assertEquals(a, 122, 0.0000000001);
	}

	@Test
	void length2Test() {
		Vec3d one = new Vec3d(1, 1, 1);
		assertEquals(one.length2(), 3, 0.0000000001);
	}

	@Test
	void lengthTest() {
		Vec3d one = new Vec3d(1, 1, 1);
		assertEquals(one.length(), Math.sqrt(3), 0.0000000001);
	}

	@Test
	void normaliseTest() {
		Vec3d one = new Vec3d(3, 1, 2);
		Vec3d two = one.normalise();
		assertEquals(two.getX(), 0.802, 0.001);
		assertEquals(two.getY(), 0.267, 0.001);
		assertEquals(two.getZ(), 0.534, 0.001);
	}

	@Test
	void getValuesTest() {
		Vec3d one = new Vec3d(3, 1, 2);
		double[] values = one.getValues();

		assertEquals(values[0], 3, 0.000000001);
		assertEquals(values[1], 1, 0.000000001);
		assertEquals(values[2], 2, 0.000000001);
		assertEquals(values.length, 3);
	}

	@Test
	void outerProductTest() {
		Vec3d one = new Vec3d(-2, 16, 4);
		Vec3d two = new Vec3d(13, -3, -1);
		Matrix4d three = one.outerProduct(two);

		assertEquals(three.get(0, 0), -26);
		assertEquals(three.get(1, 0), 6);
		assertEquals(three.get(2, 0), 2);

		assertEquals(three.get(0, 1), 208);
		assertEquals(three.get(1, 1), -48);
		assertEquals(three.get(2, 1), -16);

		assertEquals(three.get(0, 2), 52);
		assertEquals(three.get(1, 2), -12);
		assertEquals(three.get(2, 2), -4);

	}

	@Test
	void crossTest() {
		Vec3d one = new Vec3d(2, 3, 4);
		Vec3d two = new Vec3d(5, 6, 7);
		Vec3d three = one.cross(two);
		assertEquals(three.getX(), -3);
		assertEquals(three.getY(), 6);
		assertEquals(three.getZ(), -3);
	}

	@Test
	void negTest() {
		Vec3d one = new Vec3d(2, 3, -4);
		Vec3d three = one.neg();
		assertEquals(three.getX(), -2);
		assertEquals(three.getY(), -3);
		assertEquals(three.getZ(), 4);
	}

	@Test
	void starTest() {
		//public Matrix4d star() {
		//	return new Matrix4d(
		//			0.0, -z, y, 0.0,
		//			z, 0.0, -x, 0.0,
		//			-y, x, 0.0, 0.0,
		//			0.0, 0.0, 0.0, 1.0
		//	);
		//}
		Matrix4d one = new Vec3d(2, 3, -4).star();

		assertEquals(one.get(0, 0), 0.0, 0.000000001);
		assertEquals(one.get(1, 0), 4,   0.000000001);
		assertEquals(one.get(2, 0), 3,   0.000000001);
		assertEquals(one.get(3, 0), 0.0, 0.000000001);

		assertEquals(one.get(0, 1), -4, 0.000000001);
		assertEquals(one.get(1, 1), 0.0,0.000000001);
		assertEquals(one.get(2, 1), -2, 0.000000001);
		assertEquals(one.get(3, 1), 0.0,0.000000001);

		assertEquals(one.get(0, 2), -3, 0.000000001);
		assertEquals(one.get(1, 2), 2,  0.000000001);
		assertEquals(one.get(2, 2), 0.0,0.000000001);
		assertEquals(one.get(3, 2), 0.0,0.000000001);

		assertEquals(one.get(0, 3), 0.0, 0.000000001);
		assertEquals(one.get(1, 3), 0.0, 0.000000001);
		assertEquals(one.get(2, 3), 0.0, 0.000000001);
		assertEquals(one.get(3, 3), 1.0, 0.000000001);

	}

	@Test
	void multiplyMatrixTest() {
		Vec3d one = new Vec3d(1, -2, -3);
		Matrix4d two = new Matrix4d(
				1, 2, 3, 4,
				1, 2, 3, 4,
				1, 2, 3, 4,
				1, 2, 3, 4
		);
		Vec3d three = one.multiply(two);

		assertEquals(three.getX(), -12);
		assertEquals(three.getY(), -12);
		assertEquals(three.getZ(), -12);
	}

	@Test
	void multiplyVecTest() {
		Vec3d one = new Vec3d(1, -2, -3);
		Vec3d two = new Vec3d(-1, -2, 3);
		Vec3d three = one.multiply(two);

		assertEquals(three.getX(), -1);
		assertEquals(three.getY(), 4);
		assertEquals(three.getZ(), -9);
	}

	@Test
	void minTest() {
		Vec3d one = new Vec3d(1, 2, 1);
		Vec3d two = new Vec3d(2, 1, 1);
		Vec3d three = Vec3d.Min(one, two);

		assertEquals(three.getX(), 1);
		assertEquals(three.getY(), 1);
		assertEquals(three.getZ(), 1);
	}

	@Test
	void maxTest() {
		Vec3d one = new Vec3d(1, 2, 1);
		Vec3d two = new Vec3d(2, 1, 1);
		Vec3d three = Vec3d.Max(one, two);

		assertEquals(three.getX(), 2);
		assertEquals(three.getY(), 2);
		assertEquals(three.getZ(), 1);
	}
}