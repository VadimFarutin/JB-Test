#!/bin/bash

set -ev

if [ -f "gradlew" ]
then
    chmod +x gradlew
    ./gradlew build
fi
