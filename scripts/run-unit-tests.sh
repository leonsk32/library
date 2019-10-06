#!/usr/bin/env bash

declare ROOT_DIR=""

function set_bash_error_handling() {
    set -euo pipefail
}

function set_project_root_directory() {
    local -r script_dir=$( dirname "${BASH_SOURCE[0]}")

    cd "$script_dir/.."

    ROOT_DIR=$(pwd)
}

function run_unit_tests() {
    cd "$ROOT_DIR/library-backend-service"
    ./gradlew test
}

function main() {
    set_bash_error_handling
    set_project_root_directory
    run_unit_tests
}

main
