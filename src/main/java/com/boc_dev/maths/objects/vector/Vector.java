package com.boc_dev.maths.objects.vector;

public class Vector {

	public static Vecd Create(double... e) {
		switch (e.length) {
			case 2:
				return new Vec2d(e[0], e[1]);
			case 3:
				return new Vec3d(e[0], e[1], e[2]);
			default:
				throw new UnsupportedOperationException("A vector of length " + e.length + " has not been implemented yet");
		}
	}

	public static Vecd ZERO(int num) {
		switch (num) {
			case 2:
				return Vec2d.ZERO;
			case 3:
				return Vec3d.ZERO;
			default:
				throw new UnsupportedOperationException("A vector of length " + num + " has not been implemented yet");
		}
	}
}
