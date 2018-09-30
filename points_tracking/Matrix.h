#pragma once

template <typename T>
class Matrix {
public:
    Matrix(T* data);

    T* operator[](size_t n);

private:
    static const size_t DIMENSION = 2;
    T _data[DIMENSION][DIMENSION];
};
