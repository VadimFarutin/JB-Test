#include <iostream>
#include <algorithm>
#include <vector>

#include "Vector.h"
#include "Matrix.h"

template <typename T>
std::vector<size_t> prefixFunction(const std::vector<Vector<T>>& s) {
    size_t n = s.size();
    std::vector<size_t> pi(n);

    for (size_t i = 1; i < n; i++) {
        size_t j = pi[i - 1];
        while (j > 0 && s[i] != s[j]) {
            j = pi[j - 1];
        }
        if (s[i] == s[j]) {
            ++j;
        }
        pi[i] = j;
    }
    
    return pi;
}

template <typename T>
std::vector<Vector<T>> readPoints(int n) {
    std::vector<Vector<T>> points;
    points.reserve(n);
    double x, y;

    for (size_t i = 0; i < n; i++) {
        std::cin >> x >> y;
        points.push_back(Vector<T>(x, y));
    }

    return points;
}

template <typename T>
Vector<T> getCenterOfMass(const std::vector<Vector<T>>& points) {
    Vector<T> centerOfMass;

    for (auto& point : points) {
        centerOfMass += point;
    }

    centerOfMass.setX(centerOfMass.getX() / points.size());
    centerOfMass.setY(centerOfMass.getY() / points.size());

    return centerOfMass;
}

template <typename T>
void centerPoints(std::vector<Vector<T>>& points, const Vector<T>& center) {
    for (auto& point : points) {
        point -= center;
    }
}

template <typename T>
size_t getCyclicalShift(const std::vector<Vector<T>>& startPoints, const std::vector<Vector<T>>& endPoints) {
    std::vector<Vector<T>> endPointsRepeated = endPoints;
    endPointsRepeated.insert(endPointsRepeated.end(),
        endPoints.begin(),
        endPoints.end());
    std::vector<Vector<T>> all = startPoints;
    all.push_back(Vector<T>());
    all.insert(all.end(),
        endPointsRepeated.begin(),
        endPointsRepeated.end());

    size_t n = startPoints.size();
    std::vector<size_t> pi = prefixFunction(all);

    for (size_t i = n + 1; i < 3 * n + 1; i++) {
        if (pi[i] == n) {
            return i - n - n;
        }
    }
}

template <typename T>
Matrix<T> getRotation(const Vector<T>& startPoint, const Vector<T>& endPoint) {
    T a[2][3];
    T x, y;

    a[0][0] = a[1][1] = startPoint.getX();
    a[0][1] = -startPoint.getY();
    a[1][0] = -a[0][1];
    a[0][2] = endPoint.getX();
    a[1][2] = endPoint.getY();

    if (a[0][0] == 0) {
        x = a[1][2] / a[1][0];
        y = a[0][2] / a[0][1];
    }
    else {
        T k = a[1][0] / a[0][0];
        a[1][0] -= k * a[0][0];
        a[1][1] -= k * a[0][1];
        a[1][2] -= k * a[0][2];

        y = a[1][2] / a[1][1];
        x = (a[0][2] - a[0][1] * y) / a[0][0];
    }

    T data[4] = { x, -y, y, x };

    return Matrix<T>(data);
}

template <typename T>
Matrix<T> getRotation(std::vector<Vector<T>>& startPoints, std::vector<Vector<T>>& endPoints) {
    size_t n = startPoints.size();

    sort(startPoints.begin(), startPoints.end());
    sort(endPoints.begin(), endPoints.end());

    for (size_t i = 0; i < n - 1; i++) {
        startPoints[i].setNext(startPoints[i + 1]);
    }
    startPoints[n - 1].setNext(startPoints[0]);

    for (size_t i = 0; i < n - 1; i++) {
        endPoints[i].setNext(endPoints[i + 1]);
    }
    endPoints[n - 1].setNext(endPoints[0]);

    size_t cyclicalShift = getCyclicalShift(startPoints, endPoints);
    Vector<T> firstPointImage = endPoints[cyclicalShift];

    return getRotation(startPoints[0], firstPointImage);
}

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);

    size_t n;
    std::cin >> n;

    std::vector<Vector<double>> startPoints = readPoints<double>(n);
    std::vector<Vector<double>> endPoints = readPoints<double>(n);

    Vector<double> startCenterOfMass = getCenterOfMass(startPoints);
    Vector<double> endCenterOfMass = getCenterOfMass(endPoints);

    centerPoints(startPoints, startCenterOfMass);
    centerPoints(endPoints, endCenterOfMass);

    Vector<double> shift = endCenterOfMass - startCenterOfMass;
    Matrix<double> rotation = getRotation(startPoints, endPoints);

    std::cout << "shift: \n" << shift.getX() << " " << shift.getY() << std::endl;
    std::cout << "rotation center\n" << endCenterOfMass.getX() << " " << endCenterOfMass.getY() << std::endl;
    std::cout << "rotation: " << std::endl;
    std::cout << rotation[0][0] << " " << rotation[0][1] << std::endl;
    std::cout << rotation[1][0] << " " << rotation[1][1] << std::endl;

    return 0;
}
