package com.nick.wood.maths.utils;

import com.nick.wood.maths.objects.vector.Vec3f;
import com.nick.wood.maths.objects.vector.Vec4f;

public class Reflection {

	public static Vec3f ReflectionOverPlane(Vec3f point, Vec4f plane) {

		// find point on plane
		// find normal
		Vec3f n = new Vec3f(plane.getX(), plane.getY(), plane.getZ());

		// point on above type defined plane is the normal time s by the scale away from 0
		Vec3f pointOnPlane = n.scale(plane.getS());

		// find Point we want to reflect - point on plane
		Vec3f D = point.subtract(pointOnPlane);

		// Find component of D that is normal to plane
		Vec3f Dn = n.scale(D.dot(n));

		// find the part of D parallel to the plane normal
		Vec3f Dp = D.subtract(Dn);

		// find D reflected
		Vec3f Dref = D.subtract(Dn.scale(2));

		// now add in point on quad to find the reflected point
		return pointOnPlane.add(Dref);

	}
}
