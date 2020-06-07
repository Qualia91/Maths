package com.nick.wood.maths.objects.srt;

import com.nick.wood.maths.objects.QuaternionF;
import com.nick.wood.maths.objects.matrix.Matrix4f;
import com.nick.wood.maths.objects.vector.Vec3f;

public class Transform {

	public static final Transform Identity = new Transform(Vec3f.ONE, QuaternionF.Identity, Vec3f.ZERO);
	private Vec3f position;
	private Vec3f scale;
	private QuaternionF rotation;

	Transform( Vec3f scale, QuaternionF rotation, Vec3f position) {
		this.scale = scale;
		this.rotation = rotation;
		this.position = position;
	}

	public Matrix4f getSRT() {
		return Matrix4f.Transform(position, rotation.toMatrix(), scale);
	}

	public Vec3f getPosition() {
		return position;
	}

	public void setPosition(Vec3f position) {
		this.position = position;
	}

	public Vec3f getScale() {
		return scale;
	}

	public void setScale(Vec3f scale) {
		this.scale = scale;
	}

	public QuaternionF getRotation() {
		return rotation;
	}

	public void setRotation(QuaternionF rotation) {
		this.rotation = rotation;
	}

}
