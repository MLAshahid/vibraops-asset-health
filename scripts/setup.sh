#!/bin/bash
#
# Build the VibraOps Docker images and run the entire stack using docker-compose.
# This script requires Docker and docker-compose to be installed.  It will
# automatically spin up Postgres, the API and the web application.

set -e
DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$DIR/../infra"
echo "Building and launching VibraOps via docker compose..."
docker compose up --build