package com.boc_dev.maths.noise;

import com.boc_dev.maths.objects.vector.Vec2f;

import java.util.Random;

public class Perlin2Df {

	private final float[] permutationX;
	private final float[] permutationY;
	private final int segmentSize;
	private final int randomNumberArraySize;

	Vec2f[] corners = new Vec2f[]{
			new Vec2f(0, 0),
			new Vec2f(1, 0),
			new Vec2f(0, 1),
			new Vec2f(1, 1),
	};

	public Perlin2Df(int randomNumberArraySize, int segmentSize) {
		Random random = new Random(System.currentTimeMillis());
		this.segmentSize = segmentSize;
		this.randomNumberArraySize = randomNumberArraySize;
		permutationX = new float[randomNumberArraySize];
		permutationY = new float[randomNumberArraySize];
		for (int i = 0; i < randomNumberArraySize; i++) {
			permutationX[i] = (random.nextFloat() * 2) - 1.0f;
			permutationY[i] = (random.nextFloat() * 2) - 1.0f;
		}
	}

	public float getPoint(float x, float y) {

		x = x/segmentSize;
		y = y/segmentSize;

		int boxNumberX = (int) x;
		int boxNumberY = (int) y;

		float xPos = x % 1;
		float yPos = y % 1;

		// point
		Vec2f point = new Vec2f(xPos, yPos);

		// direction vectors from corners to point
		Vec2f[] directionVectors = new Vec2f[4];
		float[] cornerValues = new float[4];

		// get corner gradient values wrt permutation grid
		Vec2f[] cornerGrad = gradients(boxNumberX, boxNumberY);

		for (int i = 0; i < corners.length; i++) {
			directionVectors[i] = point.subtract(corners[i]);
			cornerValues[i] = cornerGrad[i].dot(directionVectors[i]);
		}

		float fadeX = fade(xPos);
		float fadeY = fade(yPos);

		float x1 = lerp(cornerValues[0], cornerValues[1], fadeX);
		float x2 = lerp(cornerValues[2], cornerValues[3], fadeX);
		return lerp(x1, x2, fadeY);
	}

	private Vec2f[] gradients(int boxNumberX, int boxNumberY) {
		Vec2f[] gradients = new Vec2f[4];
		int i = (boxNumberX + (boxNumberY * segmentSize)) % randomNumberArraySize;
		gradients[0] = new Vec2f(permutationX[i], permutationY[i]).normalise();
		i = (boxNumberX + 1 + (boxNumberY * segmentSize)) % randomNumberArraySize;
		gradients[1] = new Vec2f(permutationX[i], permutationY[i]).normalise();
		i = (boxNumberX + ((boxNumberY + 1) * segmentSize)) % randomNumberArraySize;
		gradients[2] = new Vec2f(permutationX[i], permutationY[i]).normalise();
		i = (boxNumberX + 1 + ((boxNumberY + 1) * segmentSize)) % randomNumberArraySize;
		gradients[3] = new Vec2f(permutationX[i], permutationY[i]).normalise();
		return gradients;
	}

	float lerp(float v0, float v1, float t) {
		return (1.0f - t) * v0 + t * v1;
	}

	public float fade(float t) {
		// Fade function as defined by Ken Perlin.  This eases coordinate values
		// so that they will ease towards integral values.  This ends up smoothing
		// the final output.
		return t * t * t * (t * (t * 6 - 15) + 10);         // 6t^5 - 15t^4 + 10t^3
	}

}
