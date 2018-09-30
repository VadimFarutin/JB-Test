#include "Matrix.h"

template <typename T>
Matrix<T>::Matrix(T* data) {
    for (size_t i = 0; i < DIMENSION; i++) {
        for (size_t j = 0; j < DIMENSION; j++) {
            _data[i][j] = data[i * DIMENSION + j];
        }
    }
}

template <typename T>
T* Matrix<T>::operator[](size_t n) {
    return _data[n];
}
