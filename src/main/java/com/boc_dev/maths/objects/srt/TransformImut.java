package com.boc_dev.maths.objects.srt;

import com.boc_dev.maths.objects.QuaternionF;
import com.boc_dev.maths.objects.vector.Vec3f;
import com.boc_dev.maths.objects.matrix.Matrix4f;

public class TransformImut {

	public static final TransformImut Identity = new TransformImut(Vec3f.ONE, QuaternionF.Identity, Vec3f.ZERO);
	private final Vec3f position;
	private final Vec3f scale;
	private final QuaternionF rotation;
	private final Matrix4f SRT;

	public TransformImut(Vec3f scale, QuaternionF rotation, Vec3f position) {
		this.scale = scale;
		this.rotation = rotation;
		this.position = position;
		this.SRT = Matrix4f.Transform(position, rotation.toMatrix(), scale);
	}

	public TransformImut(Transform transform) {
		this.scale = transform.getScale();
		this.rotation = transform.getRotation();
		this.position = transform.getPosition();
		this.SRT = Matrix4f.Transform(position, rotation.toMatrix(), scale);
	}

	public Vec3f getPosition() {
		return position;
	}

	public Vec3f getScale() {
		return scale;
	}

	public QuaternionF getRotation() {
		return rotation;
	}

	public Matrix4f getSRT() {
		return SRT;
	}
}
