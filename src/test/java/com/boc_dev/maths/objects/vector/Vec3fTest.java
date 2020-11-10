package com.boc_dev.maths.objects.vector;

import com.boc_dev.maths.objects.matrix.Matrix4f;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Vec3fTest {

	@Test
	void limitCreationTest() {
		Vec3f one = new Vec3f(new Vec3f(1, 3, 2), 2);
		assertEquals(one.getX(), 1);
		assertEquals(one.getY(), 2);
		assertEquals(one.getZ(), 2);
	}

	@Test
	void getXTest() {
		Vec3f one = new Vec3f(2, 3, 4);
		assertEquals(one.getX(), 2);
	}

	@Test
	void getYTest() {
		Vec3f one = new Vec3f(2, 3, 4);
		assertEquals(one.getY(), 3);
	}

	@Test
	void getZTest() {
		Vec3f one = new Vec3f(2, 3, 4);
		assertEquals(one.getZ(), 4);
	}

	@Test
	void addTest() {
		Vec3f one = new Vec3f(2, 3, 4);
		Vec3f two = new Vec3f(5, 6, 7);
		Vec3f three = one.add(two);
		assertEquals(three.getX(), 7);
		assertEquals(three.getY(), 9);
		assertEquals(three.getZ(), 11);
	}

	@Test
	void subtractTest() {
		Vec3f one = new Vec3f(2, 3, 4);
		Vec3f two = new Vec3f(5, 6, 7);
		Vec3f three = one.subtract(two);
		assertEquals(three.getX(), -3);
		assertEquals(three.getY(), -3);
		assertEquals(three.getZ(), -3);
	}

	@Test
	void scaleTest() {
		Vec3f one = new Vec3f(2, 3, 4);
		Vec3f two = one.scale(3);
		assertEquals(two.getX(), 6);
		assertEquals(two.getY(), 9);
		assertEquals(two.getZ(), 12);
	}

	@Test
	void dotTest() {
		Vec3f one = new Vec3f(9, 2, 7);
		Vec3f two = new Vec3f(4, 8, 10);
		float a = one.dot(two);
		assertEquals(a, 122, 0.0000000001);
	}

	@Test
	void length2Test() {
		Vec3f one = new Vec3f(1, 1, 1);
		assertEquals(one.length2(), 3, 0.0000000001);
	}

	@Test
	void lengthTest() {
		Vec3f one = new Vec3f(1, 1, 1);
		assertEquals(one.length(), (float) Math.sqrt(3), 0.0000001);
	}

	@Test
	void normaliseTest() {
		Vec3f one = new Vec3f(3, 1, 2);
		Vec3f two = one.normalise();
		assertEquals(two.getX(), 0.802, 0.001);
		assertEquals(two.getY(), 0.267, 0.001);
		assertEquals(two.getZ(), 0.534, 0.001);

        Vec3f n = Vec3f.ZERO.normalise();

        assertEquals(n.getX(), 0.0, 0.0000001);
        assertEquals(n.getY(), 0.0, 0.0000001);
        assertEquals(n.getZ(), 0.0, 0.0000001);
	}

	@Test
	void getValuesTest() {
		Vec3f one = new Vec3f(3, 1, 2);
		float[] values = one.getValues();

		assertEquals(values[0], 3, 0.000000001);
		assertEquals(values[1], 1, 0.000000001);
		assertEquals(values[2], 2, 0.000000001);
		assertEquals(values.length, 3);
	}

	@Test
	void outerProductTest() {
		Vec3f one = new Vec3f(-2, 16, 4);
		Vec3f two = new Vec3f(13, -3, -1);
		Matrix4f three = one.outerProduct(two);

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
		Vec3f one = new Vec3f(2, 3, 4);
		Vec3f two = new Vec3f(5, 6, 7);
		Vec3f three = one.cross(two);
		assertEquals(three.getX(), -3);
		assertEquals(three.getY(), 6);
		assertEquals(three.getZ(), -3);
	}

	@Test
	void negTest() {
		Vec3f one = new Vec3f(2, 3, -4);
		Vec3f three = one.neg();
		assertEquals(three.getX(), -2);
		assertEquals(three.getY(), -3);
		assertEquals(three.getZ(), 4);
	}

	@Test
	void starTest() {
		Matrix4f one = new Vec3f(2, 3, -4).star();

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
		Vec3f one = new Vec3f(1, -2, -3);
		Matrix4f two = new Matrix4f(
				1, 2, 3, 4,
				1, 2, 3, 4,
				1, 2, 3, 4,
				1, 2, 3, 4
		);
		Vec3f three = one.multiply(two);

		assertEquals(three.getX(), -12);
		assertEquals(three.getY(), -12);
		assertEquals(three.getZ(), -12);
	}

	@Test
	void multiplyVecTest() {
		Vec3f one = new Vec3f(1, -2, -3);
		Vec3f two = new Vec3f(-1, -2, 3);
		Vec3f three = one.multiply(two);

		assertEquals(three.getX(), -1);
		assertEquals(three.getY(), 4);
		assertEquals(three.getZ(), -9);
	}

	@Test
	void minTest() {
		Vec3f one = new Vec3f(1, 2, 1);
		Vec3f two = new Vec3f(2, 1, 1);
		Vec3f three = Vec3f.Min(one, two);

		assertEquals(three.getX(), 1);
		assertEquals(three.getY(), 1);
		assertEquals(three.getZ(), 1);
	}

	@Test
	void maxTest() {
		Vec3f one = new Vec3f(1, 2, 1);
		Vec3f two = new Vec3f(2, 1, 1);
		Vec3f three = Vec3f.Max(one, two);

		assertEquals(three.getX(), 2);
		assertEquals(three.getY(), 2);
		assertEquals(three.getZ(), 1);
	}

	@Test
    void equalsTest() {

        Vec3f one = new Vec3f(1, 2, 1);
        Vec3f two = new Vec3f(1, 2, 1);

        assertEquals(one, two);
    }

    @Test
    void getTest() {

        Vec3f one = new Vec3f(1, 2, 3);

        assertEquals(one.get(0), 1.0, 0.000001);
        assertEquals(one.get(1), 2.0, 0.000001);
        assertEquals(one.get(2), 3.0, 0.000001);
    }

    @Test
    void toVecdTest() {

        Vec3d one = new Vec3f(1, 2, 3).toVecd();

        assertEquals(one.get(0), 1.0, 0.000001);
        assertEquals(one.get(1), 2.0, 0.000001);
        assertEquals(one.get(2), 3.0, 0.000001);

        assertThrows(RuntimeException.class, () -> one.get(3));
    }

    @Test
    void getValuesFTest() {

        double[] one = new Vec3f(1, 2, 3).getValuesD();

        assertEquals(one[0], 1.0, 0.000001);
        assertEquals(one[1], 2.0, 0.000001);
        assertEquals(one[2], 3.0, 0.000001);
    }
}