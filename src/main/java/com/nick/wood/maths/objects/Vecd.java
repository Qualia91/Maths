package com.nick.wood.maths.objects;

public interface Vecd {
	Vecd add(Vecd v);
	Vecd subtract(Vecd v);
	Vecd scale(double s);
	double dot(Vecd v);
	double length2();
	double length();
	Vecd normalise();
	double[] getValues();
	Vecd cross(Vecd v);
	Vecd neg();
	Vecd multiply(Vecd n);
	double get(int i);
}
