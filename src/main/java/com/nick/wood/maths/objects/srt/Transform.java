package com.nick.wood.maths.objects.srt;

import com.nick.wood.maths.objects.QuaternionF;
import com.nick.wood.maths.objects.matrix.Matrix4f;
import com.nick.wood.maths.objects.matrix.Matrixd;
import com.nick.wood.maths.objects.vector.Vec3f;

public class Transform {

	public static final Transform Identity = new Transform(Vec3f.ONE, QuaternionF.Identity, Vec3f.ZERO);
	private Vec3f position;
	private Vec3f scale;
	private QuaternionF rotation;
	private Matrix4f SRT;
	private Matrix4f invSRT;
	private boolean changed = true;

	public Transform(Vec3f scale, QuaternionF rotation, Vec3f position) {
		this.scale = scale;
		this.rotation = rotation;
		this.position = position;
	}

//    public Matrix4f getInvSRT() {
//        if (changed) {
//            this.invSRT = Matrix4f.Transform(position, rotation.toMatrix(), scale);
//            this.changed = false;
//        }
//        return invSRT;
//    }

    public Matrix4f getSRT() {
	    if (changed) {
            this.SRT = Matrix4f.Transform(position, rotation.toMatrix(), scale);
            this.changed = false;
        }
        return SRT;
    }

    public Vec3f getPosition() {
		return position;
	}

	public void setPosition(Vec3f position) {
		this.changed = true;
		this.position = position;
	}

	public Vec3f getScale() {
		return scale;
	}

	public void setScale(Vec3f scale) {
		this.changed = true;
		this.scale = scale;
	}

	public QuaternionF getRotation() {
		return rotation;
	}

	public void setRotation(QuaternionF rotation) {
		this.changed = true;
		this.rotation = rotation;
	}

	public void resetPosition() {
		this.changed = true;
		this.position = Vec3f.ZERO;
	}

	public void resetScale() {
		this.changed = true;
		this.scale = Vec3f.ONE;
	}

	public void resetRotation() {
		this.changed = true;
		this.rotation = QuaternionF.Identity;
	}

	public void reset() {
		this.changed = true;
		this.position = Vec3f.ZERO;
		this.rotation = QuaternionF.Identity;
		this.scale = Vec3f.ONE;
	}

}
