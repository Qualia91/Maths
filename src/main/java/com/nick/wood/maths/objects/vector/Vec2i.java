package com.nick.wood.maths.objects.vector;

import java.util.Objects;

public class Vec2i {

	public static final Vec2i ZERO = new Vec2i(0, 0);
	public static final Vec2i X = new Vec2i(1, 0);
	public static final Vec2i Y = new Vec2i(0, 1);

	private final int x;
	private final int y;

	public Vec2i(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Vec2i add(Vec2i vec2i) {
		return new Vec2i(
				this.x + vec2i.x,
				this.y + vec2i.y);
	}

	public Vec2i subtract(Vec2i vec2i) {
		return new Vec2i(
				this.x - vec2i.x,
				this.y - vec2i.y);
	}

	public Vec2i scale(int s) {
		return new Vec2i(
				this.x * s,
				this.y * s);
	}

	public int dot(Vec2i vec2i) {
		return
				this.x * vec2i.getX() +
				this.y * vec2i.getY();
	}

	public int length2() {
		return
				(this.x * this.x) +
				(this.y * this.y);
	}

	public int[] getValues() {
		return new int[] {x, y};
	}

	public Vec2i neg() {
		return new Vec2i(
				-this.x,
				-this.y
		);
	}

	public Vec2i multiply(Vec2i vec2i) {
		return new Vec2i(x * vec2i.getX(), y * vec2i.getY());
	}

	public Vec2i cross(Vec2i vec2i) {
		return new Vec2i(this.x * vec2i.y, vec2i.x * this.y);
	}

	public Vec2i midpoint(Vec2i vec2i) {
		int x = (vec2i.x + this.x)/2;
		int y = (vec2i.y + this.y)/2;
		return new Vec2i(x, y);
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vec2i vec2i = (Vec2i) o;
		return vec2i.x == x &&
				vec2i.y == y;
	}

	public int distance2AwayFrom(Vec2i chunkIndex) {
		int xDiff = x - chunkIndex.getX();
		int yDiff = y - chunkIndex.getY();
		return (xDiff * xDiff) + (yDiff * yDiff);
	}

	@Override
	public int hashCode() {
		int hashCode = 23;
		hashCode = hashCode * 31 + x;
		hashCode = hashCode * 31 + y;
		return hashCode;
	}

    public int get(int i) {
        return (i == 0) ? x : y;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }

}
