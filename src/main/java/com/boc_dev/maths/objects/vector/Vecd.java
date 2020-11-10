package com.boc_dev.maths.objects.vector;

public interface Vecd extends Vec<Vecd> {
    Vecd scale(double s);
    double dot(Vecd v);
    double length2();
    double length();
    double[] getValues();
    double get(int i);
	Vecf toVecf();
    Vecd lerp(Vecd vecb, double percent);
}
