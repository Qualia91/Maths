package com.nick.wood.maths.noise;

import com.nick.wood.maths.objects.vector.Vec2d;
import com.nick.wood.maths.objects.vector.Vec3d;

import java.util.Random;

public class Perlin3D {

	private final double[] permutationX;
	private final double[] permutationY;
	private final double[] permutationZ;
	private final int randomNumberArraySize;

	Vec3d[] corners = new Vec3d[]{
			new Vec3d(0, 0, 0),
			new Vec3d(1, 0, 0),
			new Vec3d(0, 1, 0),
			new Vec3d(1, 1, 0),
			new Vec3d(0, 0, 1),
			new Vec3d(1, 0, 1),
			new Vec3d(0, 1, 1),
			new Vec3d(1, 1, 1),
	};
	private int segmentSize;

	public Perlin3D(int randomNumberArraySize, int segmentSize) {
		Random random = new Random(System.currentTimeMillis());
		this.segmentSize = segmentSize;
		this.randomNumberArraySize = randomNumberArraySize;
		permutationX = new double[randomNumberArraySize];
		permutationY = new double[randomNumberArraySize];
		permutationZ = new double[randomNumberArraySize];
		for (int i = 0; i < randomNumberArraySize; i++) {
			permutationX[i] = (random.nextDouble() * 2) - 1.0;
			permutationY[i] = (random.nextDouble() * 2) - 1.0;
			permutationZ[i] = (random.nextDouble() * 2) - 1.0;
		}
	}

	public double getPoint(double x, double y, double z) {

		x = x/ (double) segmentSize;
		y = y/ (double) segmentSize;
		z = z/ (double) segmentSize;

		int boxNumberX = (int) x;
		int boxNumberY = (int) y;
		int boxNumberZ = (int) z;

		double xPos = x % 1;
		double yPos = y % 1;
		double zPos = z % 1;

		// point
		Vec3d point = new Vec3d(xPos, yPos, zPos);

		// direction vectors from corners to point
		Vec3d[] directionVectors = new Vec3d[8];
		double[] cornerValues = new double[8];

		// get corner gradient values wrt permutation grid
		Vec3d[] cornerGrad = gradients(boxNumberX, boxNumberY, boxNumberZ);

		for (int i = 0; i < corners.length; i++) {
			directionVectors[i] = point.subtract(corners[i]);
			cornerValues[i] = cornerGrad[i].dot(directionVectors[i]);
		}

		double fadeX = fade(xPos);
		double fadeY = fade(yPos);
		double fadeZ = fade(zPos);

		double x1 = lerp(cornerValues[0], cornerValues[1], fadeX);
		double x2 = lerp(cornerValues[2], cornerValues[3], fadeX);
		double x3 = lerp(cornerValues[4], cornerValues[5], fadeX);
		double x4 = lerp(cornerValues[6], cornerValues[7], fadeX);
		double y1 = lerp(x1, x2, fadeY);
		double y2 = lerp(x3, x4, fadeY);
		return lerp(y1, y2, fadeZ);
	}

	private Vec3d[] gradients(int boxNumberX, int boxNumberY, int boxNumberZ) {
		Vec3d[] gradients = new Vec3d[8];

		gradients[0] = new Vec3d(
				permutationX[(boxNumberX + (boxNumberY * segmentSize) + (boxNumberZ * segmentSize * segmentSize)) % randomNumberArraySize],
				permutationY[(boxNumberX + (boxNumberY * segmentSize) + (boxNumberZ * segmentSize * segmentSize)) % randomNumberArraySize],
				permutationZ[(boxNumberX + (boxNumberY * segmentSize) + (boxNumberZ * segmentSize * segmentSize)) % randomNumberArraySize]).normalise();
		gradients[1] = new Vec3d(
				permutationX[(boxNumberX + 1 + (boxNumberY * segmentSize) + (boxNumberZ * segmentSize * segmentSize)) % randomNumberArraySize],
				permutationY[(boxNumberX + 1 + (boxNumberY * segmentSize) + (boxNumberZ * segmentSize * segmentSize)) % randomNumberArraySize],
				permutationZ[(boxNumberX + 1 + (boxNumberY * segmentSize) + (boxNumberZ * segmentSize * segmentSize)) % randomNumberArraySize]).normalise();
		gradients[2] = new Vec3d(
				permutationX[(boxNumberX + ((boxNumberY + 1) * segmentSize) + (boxNumberZ * segmentSize * segmentSize)) % randomNumberArraySize],
				permutationY[(boxNumberX + ((boxNumberY + 1) * segmentSize) + (boxNumberZ * segmentSize * segmentSize)) % randomNumberArraySize],
				permutationZ[(boxNumberX + ((boxNumberY + 1) * segmentSize) + (boxNumberZ * segmentSize * segmentSize)) % randomNumberArraySize]).normalise();
		gradients[3] = new Vec3d(
				permutationX[(boxNumberX + 1 + ((boxNumberY + 1) * segmentSize) + (boxNumberZ * segmentSize * segmentSize)) % randomNumberArraySize],
				permutationY[(boxNumberX + 1 + ((boxNumberY + 1) * segmentSize) + (boxNumberZ * segmentSize * segmentSize)) % randomNumberArraySize],
				permutationZ[(boxNumberX + 1 + ((boxNumberY + 1) * segmentSize) + (boxNumberZ * segmentSize * segmentSize)) % randomNumberArraySize]).normalise();
		gradients[4] = new Vec3d(
				permutationX[(boxNumberX + (boxNumberY * segmentSize) + ((boxNumberZ + 1) * segmentSize * segmentSize)) % randomNumberArraySize],
				permutationY[(boxNumberX + (boxNumberY * segmentSize) + ((boxNumberZ + 1) * segmentSize * segmentSize)) % randomNumberArraySize],
				permutationZ[(boxNumberX + (boxNumberY * segmentSize) + ((boxNumberZ + 1) * segmentSize * segmentSize)) % randomNumberArraySize]).normalise();
		gradients[5] = new Vec3d(
				permutationX[(boxNumberX + 1 + (boxNumberY * segmentSize) + ((boxNumberZ + 1) * segmentSize * segmentSize)) % randomNumberArraySize],
				permutationY[(boxNumberX + 1 + (boxNumberY * segmentSize) + ((boxNumberZ + 1) * segmentSize * segmentSize)) % randomNumberArraySize],
				permutationZ[(boxNumberX + 1 + (boxNumberY * segmentSize) + ((boxNumberZ + 1) * segmentSize * segmentSize)) % randomNumberArraySize]).normalise();
		gradients[6] = new Vec3d(
				permutationX[(boxNumberX + ((boxNumberY + 1) * segmentSize) + ((boxNumberZ + 1) * segmentSize * segmentSize)) % randomNumberArraySize],
				permutationY[(boxNumberX + ((boxNumberY + 1) * segmentSize) + ((boxNumberZ + 1) * segmentSize * segmentSize)) % randomNumberArraySize],
				permutationZ[(boxNumberX + ((boxNumberY + 1) * segmentSize) + ((boxNumberZ + 1) * segmentSize * segmentSize)) % randomNumberArraySize]).normalise();
		gradients[7] = new Vec3d(
				permutationX[(boxNumberX + 1 + ((boxNumberY + 1) * segmentSize) + ((boxNumberZ + 1) * segmentSize * segmentSize)) % randomNumberArraySize],
				permutationY[(boxNumberX + 1 + ((boxNumberY + 1) * segmentSize) + ((boxNumberZ + 1) * segmentSize * segmentSize)) % randomNumberArraySize],
				permutationZ[(boxNumberX + 1 + ((boxNumberY + 1) * segmentSize) + ((boxNumberZ + 1) * segmentSize * segmentSize)) % randomNumberArraySize]).normalise();

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
