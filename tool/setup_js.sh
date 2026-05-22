#!/usr/bin/env bash
cd "$(dirname "$0")" || exit
BASE_PATH=$(pwd)
BUILD_PATH=../all/build/dist/js/productionLibrary

# Copy README
cd "$BASE_PATH" || exit
cp ../LICENSE $BUILD_PATH/LICENSE
cp ../docs/js/README.md $BUILD_PATH/README.md
cp ../docs/js/README_ja.md $BUILD_PATH/README_ja.md
