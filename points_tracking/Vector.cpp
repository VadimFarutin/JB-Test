#include "Vector.h"

template <typename T>
Vector<T>::Vector()
    : Vector<T>(0, 0) {}

template <typename T>
Vector<T>::Vector(T x, T y)
    : _x(x), _y(y) {
    _update();
}

template <typename T>
T Vector<T>::getX() const {
    return _x;
}

template <typename T>
T Vector<T>::getY() const {
    return _y;
}

template <typename T>
void Vector<T>::setX(T x) {
    _x = x;
    _update();
}

template <typename T>
void Vector<T>::setY(T y) {
    _y = y;
    _update();
}

template <typename T>
Vector<T> Vector<T>::operator+(const Vector& other) const {
    return Vector(_x + other._x, _y + other._y);
}

template <typename T>
Vector<T> Vector<T>::operator+=(const Vector& other) {
    *this = *this + other;
    return *this;
}

template <typename T>
Vector<T> Vector<T>::operator-(const Vector& other) const {
    return Vector(_x - other._x, _y - other._y);
}

template <typename T>
Vector<T> Vector<T>::operator-=(const Vector& other) {
    *this = *this - other;
    return *this;
}

template <typename T>
bool Vector<T>::operator<(const Vector& other) const {
    if (_y == 0) {
        return _x > 0 || other._y < 0;
    }

    if (_y > 0 && other._y < 0) {
        return true;
    }

    if (_y < 0 && other._y > 0) {
        return false;
    }

    T pseudoscalarProduct = _x * other._y - _y * other._x;

    if (pseudoscalarProduct == 0) {
        return _length < other._length;
    }

    return pseudoscalarProduct > 0;
}

template <typename T>
bool Vector<T>::operator==(const Vector& other) const {
    return _length == other._length
        && _nextAngleSin == other._nextAngleSin
        && _nextAngleCosPositive == other._nextAngleCosPositive;
}

template <typename T>
bool Vector<T>::operator!=(const Vector& other) const {
    return !(*this == other);
}

template <typename T>
void Vector<T>::setNext(const Vector& next) {
    T pseudoscalarProduct = _x * next._y - _y * next._x;
    _nextAngleSin = pseudoscalarProduct / _length / next._length;
    T scalarProduct = _x * next._x + _y * next._y;
    _nextAngleCosPositive = scalarProduct > 0;
}

template <typename T>
void Vector<T>::_update() {
    _length = sqrt(_x * _x + _y * _y);
}
