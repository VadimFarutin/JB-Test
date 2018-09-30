#pragma once

#include <stddef.h>

template <typename T>
class Matrix {
public:
    Matrix(T* data) {
        for (size_t i = 0; i < _DIMENSION; i++) {
            for (size_t j = 0; j < _DIMENSION; j++) {
                _data[i][j] = data[i * _DIMENSION + j];
            }
        }
    }

    T* operator[](size_t n) {
        return _data[n];
    }

private:
    static const size_t _DIMENSION = 2;
    T _data[_DIMENSION][_DIMENSION];
};
