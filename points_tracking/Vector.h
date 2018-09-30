#pragma once

template <typename T>
class Vector {
public:
    Vector()
        : Vector<T>(0, 0) {}
    
    Vector(T x, T y)
        : _x(x), _y(y) {
        _update();
    }
    
    T getX() const {
        return _x;
    }
    
    T getY() const {
        return _y;
    }
    
    void setX(T x) {
        _x = x;
        _update();
    }
    
    void setY(T y) {
        _y = y;
        _update();
    }
    
    Vector<T> operator+(const Vector& other) const {
        return Vector(_x + other._x, _y + other._y);
    }
    
    Vector<T> operator+=(const Vector& other) {
        *this = *this + other;
        return *this;
    }
    
    Vector<T> operator-(const Vector& other) const {
        return Vector(_x - other._x, _y - other._y);
    }
    
    Vector<T> operator-=(const Vector& other) {
        *this = *this - other;
        return *this;
    }
    
    bool operator<(const Vector& other) const {
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
    
    bool operator==(const Vector& other) const {
        return _length == other._length
            && _nextAngleSin == other._nextAngleSin
            && _nextAngleCosPositive == other._nextAngleCosPositive;
    }
    
    bool operator!=(const Vector& other) const {
        return !(*this == other);
    }
    
    void setNext(const Vector& next) {
        T pseudoscalarProduct = _x * next._y - _y * next._x;
        _nextAngleSin = pseudoscalarProduct / _length / next._length;
        T scalarProduct = _x * next._x + _y * next._y;
        _nextAngleCosPositive = scalarProduct > 0;
    }

private:
    T _x;
    T _y;
    T _length;
    T _nextAngleSin;
    bool _nextAngleCosPositive;

    void _update() {
        _length = sqrt(_x * _x + _y * _y);
    }
};

