package com.nick.wood.maths.noise;

import com.nick.wood.maths.objects.vector.Vec2d;

import java.util.Random;

public class Perlin2D {

	private final double[] permutationX;
	private final double[] permutationY;

	Vec2d[] corners = new Vec2d[]{
			new Vec2d(0, 0),
			new Vec2d(1, 0),
			new Vec2d(0, 1),
			new Vec2d(1, 1),
	};

	public Perlin2D(int size) {
		Random random = new Random(System.currentTimeMillis());
		permutationX = new double[size];
		permutationY = new double[size];
		for (int i = 0; i < size; i++) {
			permutationX[i] = (random.nextDouble() * 2) - 1.0;
			permutationY[i] = (random.nextDouble() * 2) - 1.0;
		}
	}

	public double getPoint(double x, double y) {

		int boxNumberX = (int) x;
		int boxNumberY = (int) y;

		double xPos = x % 1;
		double yPos = y % 1;

		// point
		Vec2d point = new Vec2d(xPos, yPos);

		// direction vectors from corners to point
		Vec2d[] directionVectors = new Vec2d[4];
		double[] cornerValues = new double[4];

		// get corner gradient values wrt permutation grid
		Vec2d[] cornerGrad = gradients(boxNumberX, boxNumberY);

		for (int i = 0; i < corners.length; i++) {
			directionVectors[i] = point.subtract(corners[i]);
			cornerValues[i] = cornerGrad[i].dot(directionVectors[i]);
		}
		double x1 = lerp(cornerValues[0], cornerValues[1], xPos);
		double x2 = lerp(cornerValues[2], cornerValues[3], xPos);
		return fade(lerp(x1, x2, yPos));
	}

	private Vec2d[] gradients(int boxNumberX, int boxNumberY) {
		Vec2d[] gradients = new Vec2d[4];
		gradients[0] = new Vec2d(permutationX[boxNumberX + (boxNumberY * 16)], permutationY[boxNumberX + (boxNumberY * 16)]);
		gradients[1] = new Vec2d(permutationX[boxNumberX + 1 + (boxNumberY * 16)], permutationY[boxNumberX + 1 + (boxNumberY * 16)]);
		gradients[2] = new Vec2d(permutationX[boxNumberX + ((boxNumberY + 1) * 16)], permutationY[boxNumberX + ((boxNumberY + 1) * 16)]);
		gradients[3] = new Vec2d(permutationX[boxNumberX + 1 + ((boxNumberY + 1) * 16)], permutationY[boxNumberX + 1 + ((boxNumberY + 1) * 16)]);
		return gradients;
	}

	double lerp(double v0, double v1, double t) {
		return (1.0 - t) * v0 + t * v1;
	}

	public static double fade(double t) {
		// Fade function as defined by Ken Perlin.  This eases coordinate values
		// so that they will ease towards integral values.  This ends up smoothing
		// the final output.
		return t * t * t * (t * (t * 6 - 15) + 10);         // 6t^5 - 15t^4 + 10t^3
	}

}
