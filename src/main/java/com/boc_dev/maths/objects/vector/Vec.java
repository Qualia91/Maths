package com.boc_dev.maths.objects.vector;

public interface Vec<U> {

	U add(U v);
	U subtract(U v);
	U normalise();
	U cross(U v);
	U neg();
	U multiply(U n);

}
