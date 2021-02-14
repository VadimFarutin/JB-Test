cmake_minimum_required (VERSION 3.8)

project(lock_free_stack)

set(CMAKE_CXX_STANDARD 20)

add_executable(tests "src/tests.cpp" "src/lock_free_stack.hpp")
