package com.nick.wood.maths.objects.vector;

public interface Vecf {

	Vecf add(Vecf v);
	Vecf subtract(Vecf v);
	Vecf scale(float s);
	float dot(Vecf v);
    float length2();
    float length();
	Vecf normalise();
    float[] getValues();
	Vecf cross(Vecf v);
	Vecf neg();
	Vecf multiply(Vecf n);
    float get(int i);
	Vecd toVecd();
}
