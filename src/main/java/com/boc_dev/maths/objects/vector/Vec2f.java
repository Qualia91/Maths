package com.boc_dev.maths.objects.vector;

import com.boc_dev.maths.objects.matrix.Matrix2f;

import java.util.Objects;

public class Vec2f implements Vecf {

	public static final Vec2f ZERO = new Vec2f(0, 0);
	public static final Vec2f X = new Vec2f(1, 0);
	public static final Vec2f Y = new Vec2f(0, 1);

	private final float x;
	private final float y;

	public Vec2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Vec2f add(Vecf vec) {
		assert vec instanceof Vec2f;
		Vec2f vec2f = (Vec2f) vec;
		return new Vec2f(
				this.x + vec2f.x,
				this.y + vec2f.y);
	}

	public Vec2f subtract(Vecf vec) {
		assert vec instanceof Vec2f;
		Vec2f vec2f = (Vec2f) vec;
		return new Vec2f(
				this.x - vec2f.x,
				this.y - vec2f.y);
	}

	public Vec2f scale(float s) {
		return new Vec2f(
				this.x * s,
				this.y * s);
	}

	public float dot(Vecf vec) {
		assert vec instanceof Vec2f;
		Vec2f vec2f = (Vec2f) vec;
		return
				this.x * vec2f.getX() +
				this.y * vec2f.getY();
	}

	public float length2() {
		return
				(this.x * this.x) +
				(this.y * this.y);
	}

	public float length() {
		return (float) Math.sqrt(length2());
	}

	public Vec2f normalise() {
		if (this.length() == 0.0f ) {
			return Vec2f.ZERO;
		}
		return this.scale(1.0f/this.length());
	}

	public float[] getValues() {
		return new float[] {x, y};
	}

	public Matrix2f outerProduct(Vecf vec) {
		assert vec instanceof Vec2f;
		Vec2f vec2f = (Vec2f) vec;

		float[] elements = new float[4];

		for (int thisVecIndex = 0; thisVecIndex < this.getValues().length; thisVecIndex++) {
			for (int otherVecIndex = 0; otherVecIndex < vec2f.getValues().length; otherVecIndex++) {

				elements[thisVecIndex * 2 + otherVecIndex] = this.getValues()[thisVecIndex] * vec2f.getValues()[otherVecIndex];

			}
		}

		return new Matrix2f(elements);
	}

	public Vec2f neg() {
		return new Vec2f(
				-this.x,
				-this.y
		);
	}

	@Override
	public Vec2f multiply(Vecf vec) {
		assert vec instanceof Vec2f;
		Vec2f vec2f = (Vec2f) vec;
		return new Vec2f(x * vec2f.getX(), y * vec2f.getY());
	}

	public Vec2f cross(Vecf vec) {
		assert vec instanceof Vec2f;
		Vec2f vec2f = (Vec2f) vec;
		return new Vec2f(this.x * vec2f.y, vec2f.x * this.y);
	}

	public Vec2f midpoint(Vec2f vec2f) {
		float x = (vec2f.x + this.x)/2;
		float y = (vec2f.y + this.y)/2;
		return new Vec2f(x, y);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vec2f vec2f = (Vec2f) o;
		return Float.compare(vec2f.x, x) == 0 &&
				Float.compare(vec2f.y, y) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

    public float get(int i) {
        return (i == 0) ? x : y;
    }

    @Override
    public Vec2d toVecd() {
        return new Vec2d((double) x, (double) y);
    }

    @Override
    public Vecf lerp(Vecf vecb, float percent) {
        return (this.scale(1 - percent)).add(vecb.scale(percent));
    }
}
