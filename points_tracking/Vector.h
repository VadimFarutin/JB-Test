#pragma once

template <typename T>
class Vector {
public:
    Vector();
    Vector(T x, T y);

    T getX() const;
    T getY() const;
    void setX(T x);
    void setY(T y);

    Vector operator+(const Vector& other) const;
    Vector operator+=(const Vector& other);
    Vector operator-(const Vector& other) const;
    Vector operator-=(const Vector& other);
    bool operator<(const Vector& other) const;
    bool operator==(const Vector& other) const;
    bool operator!=(const Vector& other) const;

    void setNext(const Vector& next);

private:
    T _x;
    T _y;
    T _length;
    T _nextAngleSin;
    bool _nextAngleCosPositive;

    void _update();
};

