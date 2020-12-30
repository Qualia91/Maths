package com.boc_dev.maths.utils;

import com.boc_dev.maths.objects.matrix.Matrix4d;

public class MatrixAlgebra {

	void diagonalize(Matrix4d matrix, double[] returnMatrix) {

		for (int ii = 0; ii < 36; ii++) {

			if (ii % 6 == 0) {

				returnMatrix[ii] = matrix.getValuesF()[ii];

			}

		}

	}

	void inverseOfDiagonalMatrix(double[] matrix) {

		for (int ii = 0; ii < 36; ii++) {

			if ((ii / 7.0f) == (int)(ii / 7.0f)) {

				matrix[ii] = 1/matrix[ii];

			}

			else matrix[ii] = 0;

		}

	}

	/* Cholesky Decomposition and inversion section
	*
	*/

	double daigonals(int i, double a_ii, double[] r) {

		double summation = 0;

		for (int rCounter = 0; rCounter < i; rCounter++) {

			summation += r[rCounter + (i * 6)] * r[rCounter + (i * 6)];

		}

		return Math.sqrt(a_ii - summation);
	}

	double offDaigonals(int i, int j, double a_ij, double r_ii, double[] r) {

		double summation = 0;

		for (int rCounter = 0; rCounter < i; rCounter++) {

			summation += r[rCounter + (6 * i)] * r[rCounter + (6 * j)];

		}

		return (1 / r_ii) * (a_ij - summation);
	}

	boolean getCholeskyFactorMatrix(double[] values, Matrix4d matrix) {

		double[] diagonals = new double[6];

		for (int jCount = 0; jCount < 6; jCount++) {

			for (int iCount = 0; iCount < 6; iCount++) {

				if (iCount > jCount) {

					values[(jCount * 6) + iCount] = 0;

				}

				else if (iCount == jCount) {

					double diagonalsElement = daigonals(iCount, matrix.get(iCount, jCount), values);

					if ((diagonalsElement == 0) || (!Double.isFinite(diagonalsElement))) return false;

					diagonals[jCount] = diagonalsElement;

					values[(jCount * 6) + iCount] = diagonals[jCount];

				}

				else {

					values[(jCount * 6) + iCount] = offDaigonals(iCount, jCount, matrix.get(iCount, jCount), diagonals[iCount], values);

				}

			}

		}

		return true;

	}

	Matrix4d forwardSubstitution(Matrix4d a) {

		Matrix4d i = Matrix4d.Identity;

		double[] x = new double[36];

		for (int row = 0; row < 6; row++) {

			for (int col = 0; col <= row; col++) {

				double summation = i.get(col, row);

				for (int idx = 0; idx <= row; idx++) {

					if (row != idx) {

						summation -= (a.get(idx, row) * x[(idx * 6) + col]);

					}

				}

				x[col + (row * 6)] = (double) summation / a.get(row, row);

			}

		}



		return new Matrix4d(x);

	}

	Matrix4d inverseMatrix(Matrix4d matrix) {

		double[] lowerTriangularMatrixTemp = {
				0,0,0,0,
				0,0,0,0,
				0,0,0,0,
				0,0,0,0};

		boolean diagonalizable = getCholeskyFactorMatrix(lowerTriangularMatrixTemp, matrix);

		Matrix4d inverseMatrix;

		if (diagonalizable) {

			Matrix4d lowerTriangularMatrix = new Matrix4d(lowerTriangularMatrixTemp);

			Matrix4d inverseLowerTriangularMatrix = forwardSubstitution(lowerTriangularMatrix);

			Matrix4d inverseUpperTriangularMatrix = inverseLowerTriangularMatrix.transpose();

			inverseMatrix = inverseLowerTriangularMatrix.multiply(inverseUpperTriangularMatrix);

		}
		else {

			inverseOfDiagonalMatrix(lowerTriangularMatrixTemp);

			inverseMatrix = new Matrix4d(lowerTriangularMatrixTemp);
		}

		return inverseMatrix;

	}

/* todo Moore-Penrose pseudoinverse
*
*/

}
