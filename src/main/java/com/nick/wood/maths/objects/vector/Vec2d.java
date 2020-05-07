package com.nick.wood.maths.objects.vector;

import com.nick.wood.maths.objects.matrix.Matrix2d;

import java.util.Objects;

public class Vec2d implements Vecd {

	public static final Vec2d ZERO = new Vec2d(0.0, 0.0);
	public static final Vec2d X = new Vec2d(1.0, 0.0);
	public static final Vec2d Y = new Vec2d(0.0, 1.0);

	private final double x;
	private final double y;

	public Vec2d(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Vec2d add(Vecd vec) {
		assert vec instanceof Vec2d;
		Vec2d vec2d = (Vec2d) vec;
		return new Vec2d(
				this.x + vec2d.x,
				this.y + vec2d.y);
	}

	public Vec2d subtract(Vecd vec) {
		assert vec instanceof Vec2d;
		Vec2d vec2d = (Vec2d) vec;
		return new Vec2d(
				this.x - vec2d.x,
				this.y - vec2d.y);
	}

	public Vec2d scale(double s) {
		return new Vec2d(
				this.x * s,
				this.y * s);
	}

	public double dot(Vecd vec) {
		assert vec instanceof Vec2d;
		Vec2d vec2d = (Vec2d) vec;
		return
				this.x * vec2d.getX() +
				this.y * vec2d.getY();
	}

	public double length2() {
		return
				(this.x * this.x) +
				(this.y * this.y);
	}

	public double length() {
		return Math.sqrt(length2());
	}

	public Vec2d normalise() {
		if (this.length() == 0.0 ) {
			return Vec2d.ZERO;
		}
		return this.scale(1.0/this.length());
	}

	public double[] getValues() {
		return new double[] {x, y};
	}

	public Matrix2d outerProduct(Vecd vec) {
		assert vec instanceof Vec2d;
		Vec2d vec2d = (Vec2d) vec;

		double[] elements = new double[4];

		for (int thisVecIndex = 0; thisVecIndex < this.getValues().length; thisVecIndex++) {
			for (int otherVecIndex = 0; otherVecIndex < vec2d.getValues().length; otherVecIndex++) {

				elements[thisVecIndex * 2 + otherVecIndex] = this.getValues()[thisVecIndex] * vec2d.getValues()[otherVecIndex];

			}
		}

		return new Matrix2d(elements);
	}

	public Vec2d neg() {
		return new Vec2d(
				-this.x,
				-this.y
		);
	}

	@Override
	public Vec2d multiply(Vecd vec) {
		assert vec instanceof Vec2d;
		Vec2d vec2d = (Vec2d) vec;
		return new Vec2d(x * vec2d.getX(), y * vec2d.getY());
	}

	public Vec2d cross(Vecd vec) {
		assert vec instanceof Vec2d;
		Vec2d vec2d = (Vec2d) vec;
		return new Vec2d(this.x * vec2d.y, vec2d.x * this.y);
	}

	public Vec2d midpoint(Vec2d vec2d) {
		double x = (vec2d.x + this.x)/2;
		double y = (vec2d.y + this.y)/2;
		return new Vec2d(x, y);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vec2d vec2d = (Vec2d) o;
		return Double.compare(vec2d.x, x) == 0 &&
				Double.compare(vec2d.y, y) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

    public double get(int i) {
        return (i == 0) ? x : y;
    }

    @Override
    public Vec2f toVecf() {
        return new Vec2f((float) x, (float) y);
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}
