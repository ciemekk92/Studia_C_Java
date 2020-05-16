package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

class MatrixIndexException extends IndexOutOfBoundsException {
    public MatrixIndexException() {}
    public MatrixIndexException(String message) {
        super(message);
    }
}

class MatrixOperationException extends UnsupportedOperationException {
    public MatrixOperationException() {}
    public MatrixOperationException(String message) {
        super(message);
    }
}

public class Matrix implements Iterable<Double> {
    private int m, n;
    double[][] data;

    public Matrix(int m, int n) {
        this.m = m;
        this.n = n;
        this.data = new double[m][n];
    }

    public int getNumberOfRows() {
        return m;
    }

    public int getNumberOfCols() {
        return n;
    }

    public void set(int row, int col, double value) {
        if (row < 0 || col < 0 || row >= m || col >= n) {
            throw new MatrixIndexException();
        }
        data[row][col] = value;
    }

    public double get(int row, int col) {
        if (row < 0 || col < 0 || row >= m || col >= n) {
            throw new MatrixIndexException();
        }
        return data[row][col];
    }

    public Iterator<Double> iterator() {
        return new Iterator<Double>() {
            int i, j = 0;

            @Override
            public boolean hasNext() {
                if (i >= data.length)
                    return false;
                if (j >= data[i].length && (i >= data.length || i == data.length -1))
                    return false;
                return true;
            }

            @Override
            public Double next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (j >= data[i].length) {
                    i++;
                    j = 0;
                }
                return data[i][j++];
            }
        };
    }

    public Matrix add(Matrix matrix) {
        int row, col;
        if (matrix.m != m || matrix.n != n) {
            throw new MatrixOperationException("Macierze nie maja tych samych rozmiarow.");
        }
        Matrix result = new Matrix(matrix.m, matrix.n);
        Iterator<Double> firstIt = iterator();
        Iterator<Double> secondIt = matrix.iterator();

        for (row = 0; row < m; row++) {
            for ( col = 0; col < n; col++) {
                result.data[row][col] = firstIt.next() + secondIt.next();
            }
        }
        return result;
    }

    double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
        double cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }

    public Matrix multiply(Matrix matrix) {
        if (matrix.m != n) {
            throw new MatrixOperationException("Liczba kolumn pierwszej macierzy musi byc rowna liczbie wierszy drugiej");
        }

        Matrix result = new Matrix(m, matrix.n);
        for (int row = 0; row < result.data.length; row++) {
            for (int col = 0; col < result.data[row].length; col++) {
                result.data[row][col] = multiplyMatricesCell(data, matrix.data, row, col);
            }
        }
        return result;
    }

    public Iterable<double[]> rows() {
        return new Iterable<double[]>() {
            @Override
            public Iterator<double[]> iterator() {
                return new Iterator<>() {
                    int i = 0;

                    public boolean hasNext() {
                        if (data.length <= i)
                            return false;
                        return true;
                    }

                    public double[] next() {
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }
                        double[] result = data[i];
                        i++;
                        return result;
                    }
                };
            }
        };
    }

    public Iterable<double[]> cols() {
        return new Iterable<double[]>() {
            @Override
            public Iterator<double[]> iterator() {
                return new Iterator<double[]>() {
                    int i = 0;
                    @Override
                    public boolean hasNext() {
                        if (data.length <= i)
                            return false;
                        return true;
                    }

                    @Override
                    public double[] next() {
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }
                        double[] result = new double[m];

                        for (int j = 0; j < data.length; j++) {
                            result[j] = data[j][i];
                        }
                        i++;
                        return result;
                    }
                };
            }
        };
    }
}
