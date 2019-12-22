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

function pull() {
    git pull -r
}

function run_unit_tests() {
    bash $ROOT_DIR/scripts/run-unit-tests.sh
}

function run_e2e_tests(){
    bash $ROOT_DIR/scripts/run-e2e-tests.sh
}

function push() {
    git push
}

function display_ascii_success_message() {
    local -r green_color_code='\033[1;32m'
    echo -e "${green_color_code}\\n$(cat $ROOT_DIR/scripts/success-ascii-art.txt)"
}

function main() {
    set_bash_error_handling
    set_project_root_directory
    pull
    run_unit_tests
#    run_e2e_tests
    push
    display_ascii_success_message
}

main
