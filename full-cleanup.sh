#!/bin/bash

echo "Removing build jars..."
./gradlew clean

echo "Stopping and removing containers..."
docker compose down

echo "Removing associated images..."
docker rmi $(docker images -q) --force

echo "Removing associated volumes..."
docker volume prune -f

echo "Cleanup complete."