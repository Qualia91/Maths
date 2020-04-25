package com.nick.wood.maths.objects.vector;

public interface Vecf extends Vec<Vecf> {
	Vecf scale(float s);
	float dot(Vecf v);
    float length2();
    float length();
    float[] getValues();
    float get(int i);
	Vecd toVecd();
}
