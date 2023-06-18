#!/bin/bash

# Function to build Docker Compose
function build() {
  echo "Building Docker Compose..."
  docker-compose build
}

# Function to start Docker Compose
function start() {
  echo "Starting Docker Compose..."
  docker-compose up
}

function stop() {
  echo "Starting Docker Compose..."
  docker-compose down
}

# Check the argument passed to the script
if [ "$1" == "build" ]; then
  build
elif [ "$1" == "start" ]; then
  start
elif [ "$1" == "stop" ]; then
  stop
else
  echo "Invalid argument. Please provide 'build, 'start' or 'stop'."
fi