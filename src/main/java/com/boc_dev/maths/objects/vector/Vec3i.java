package com.boc_dev.maths.objects.vector;

public class Vec3i {

	public static final Vec3i ZERO = new Vec3i(0, 0, 0);
	public static final Vec3i X = new Vec3i(1, 0, 0);
	public static final Vec3i Y = new Vec3i(0, 1, 0);
	public static final Vec3i Z = new Vec3i(0, 0, 1);

	private final int x;
	private final int y;
	private final int z;

	public Vec3i(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public Vec3i add(Vec3i vec3i) {
		return new Vec3i(
				this.x + vec3i.x,
				this.y + vec3i.y,
				this.z + vec3i.z);
	}

	public Vec3i subtract(Vec3i vec3i) {
		return new Vec3i(
				this.x - vec3i.x,
				this.y - vec3i.y,
				this.z - vec3i.z);
	}

	public Vec3i scale(int s) {
		return new Vec3i(
				this.x * s,
				this.y * s,
				this.z * s);
	}

	public int dot(Vec3i vec2i) {
		return
				this.x * vec2i.getX() +
				this.y * vec2i.getY() +
				this.z * vec2i.getZ();
	}

	public int length2() {
		return
				(this.x * this.x) +
				(this.y * this.y) +
				(this.z * this.z);
	}

	public int[] getValues() {
		return new int[] {x, y, z};
	}

	public Vec3i neg() {
		return new Vec3i(
				-this.x,
				-this.y,
				-this.z
		);
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vec3i vec3i = (Vec3i) o;
		return vec3i.x == x &&
				vec3i.y == y &&
				vec3i.z == z;
	}

	@Override
	public int hashCode() {
		int hashCode = 23;
		hashCode = hashCode * 7 + x;
		hashCode = hashCode * 31 + y;
		hashCode = hashCode * 17 + z;
		return hashCode;
	}

    @Override
    public String toString() {
        return x + ", " + y + ", " + z;
    }

}
