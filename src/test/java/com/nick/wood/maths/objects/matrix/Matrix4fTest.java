package com.nick.wood.maths.objects.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Matrix4fTest {

	@Test
	void inverse() {
		Matrix4f matrix4f = new Matrix4f(
				0, 0, 1, 5,
				0, 3, 0, 3,
				-1, 0, 0, 2,
				0, 0, 0, 1
		);

		Matrix4f answer = new Matrix4f(
				0, 0, -1, 2,
				0, 1.0f/3.0f, 0, -1,
				1, 0, 0, -5,
				0, 0, 0, 1
		);

		Matrix4f inverse = matrix4f.invert();

		assertEquals(answer.get(0, 0), inverse.get(0, 0), 0.0000000001);
		assertEquals(answer.get(0, 1), inverse.get(0, 1), 0.0000000001);
		assertEquals(answer.get(0, 2), inverse.get(0, 2), 0.0000000001);
		assertEquals(answer.get(0, 3), inverse.get(0, 3), 0.0000000001);
		assertEquals(answer.get(1, 0), inverse.get(1, 0), 0.0000000001);
		assertEquals(answer.get(1, 1), inverse.get(1, 1), 0.0000000001);
		assertEquals(answer.get(1, 2), inverse.get(1, 2), 0.0000000001);
		assertEquals(answer.get(1, 3), inverse.get(1, 3), 0.0000000001);
		assertEquals(answer.get(2, 0), inverse.get(2, 0), 0.0000000001);
		assertEquals(answer.get(2, 1), inverse.get(2, 1), 0.0000000001);
		assertEquals(answer.get(2, 2), inverse.get(2, 2), 0.0000000001);
		assertEquals(answer.get(2, 3), inverse.get(2, 3), 0.0000000001);
		assertEquals(answer.get(3, 0), inverse.get(3, 0), 0.0000000001);
		assertEquals(answer.get(3, 1), inverse.get(3, 1), 0.0000000001);
		assertEquals(answer.get(3, 2), inverse.get(3, 2), 0.0000000001);
		assertEquals(answer.get(3, 3), inverse.get(3, 3), 0.0000000001);
	}


	@Test
	void translation() {
    }

	@Test
	void rotation() {
	}

	@Test
	void add() {
	}

	@Test
	void testAdd() {
	}

	@Test
	void multiply() {
	}

	@Test
	void testMultiply() {
	}

	@Test
	void scale() {
	}

	@Test
	void get() {
	}

	@Test
	void getValuesF() {
	}

	@Test
	void transform() {
	}

	@Test
	void inverseTransformation() {
	}

	@Test
	void projection() {
	}

	@Test
	void view() {
	}

	@Test
	void trace() {
	}

	@Test
	void toQuaternion() {
	}

	@Test
	void transpose() {
	}

	@Test
	void getXVec() {
	}

	@Test
	void getYVec() {
	}

	@Test
	void getZVec() {
	}
}