package com.nick.wood.maths.objects.srt;

import com.nick.wood.maths.objects.QuaternionF;
import com.nick.wood.maths.objects.vector.Vec3f;

public class TransformBuilder {

	private Vec3f position = Vec3f.ZERO;
	private Vec3f scale = Vec3f.ONE;
	private QuaternionF rotation = QuaternionF.Identity;

	public TransformBuilder() {
	}

	public TransformBuilder(Vec3f position, Vec3f scale, QuaternionF rotation) {
		this.position = position;
		this.scale = scale;
		this.rotation = rotation;
	}

	public TransformBuilder setPosition(Vec3f position) {
		this.position = position;
		return this;
	}

	public TransformBuilder setScale(Vec3f scale) {
		this.scale = scale;
		return this;
	}

	public TransformBuilder setScale(float scale) {
		this.scale = new Vec3f(scale, scale, scale);
		return this;
	}

	public TransformBuilder setRotation(QuaternionF rotation) {
		this.rotation = rotation;
		return this;
	}

	public Transform build() {
		return new Transform(scale, rotation, position);
	}

	public TransformBuilder resetPosition() {
		this.position = Vec3f.ZERO;
		return this;
	}

	public TransformBuilder resetScale() {
		this.scale = Vec3f.ONE;
		return this;
	}

	public TransformBuilder resetRotation() {
		this.rotation = QuaternionF.Identity;
		return this;
	}

	public TransformBuilder reset() {
		this.position = Vec3f.ZERO;
		this.rotation = QuaternionF.Identity;
		this.scale = Vec3f.ONE;
		return this;
	}
}
