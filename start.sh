#!/bin/bash

SECONDS=0

build_and_restart_all() {
    echo "Running gradlew build..."
    ./gradlew build

    if [ $? -ne 0 ]; then
        echo "Gradle build failed. Exiting..."
        exit 1
    fi

    echo "Building Docker images for all services..."
    docker compose build

    if [ $? -ne 0 ]; then
        echo "Docker compose build failed. Exiting..."
        exit 1
    fi

    echo "Starting all Docker containers..."
    docker compose up -d

    if [ $? -ne 0 ]; then
        echo "Docker compose up failed."
        exit 1
    fi

    echo "All services are up and running."
}

build_and_restart_service() {
    local service=$1
    echo "Running gradlew build for service $service..."
    ./gradlew build ":$service:build"

    if [ $? -ne 0 ]; then
        echo "Gradle build failed for service $service. Exiting..."
        exit 1
    fi

    echo "Building Docker image for service $service..."
    docker compose build "$service"

    if [ $? -ne 0 ]; then
        echo "Docker compose build failed for service $service. Exiting..."
        exit 1
    fi

    echo "Restarting Docker container for service $service..."
    docker compose up -d "$service"

    if [ $? -ne 0 ]; then
        echo "Docker compose up failed for service $service."
        exit 1
    fi

    echo "Service $service is up and running."
}

if [ -n "$1" ]; then
    build_and_restart_service "$1"
else
    build_and_restart_all
fi

duration=$SECONDS
echo "Total execution time: $((""$duration / 60)) minutes and $(($duration % 60)) seconds."