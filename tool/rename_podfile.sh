#!/usr/bin/env bash
cd "$(dirname "$0")" || exit
BASE_PATH=$(pwd)
BUILD_PATH=../all/build

# Make Repository
cd "$BASE_PATH" || exit
mkdir -p $BUILD_PATH/cocoapods/repository/debug
mkdir -p $BUILD_PATH/cocoapods/repository/release

# Copy Podspec
cd "$BASE_PATH" || exit
cd $BUILD_PATH/cocoapods/publish/debug || exit
cp kmatrix.podspec ../../repository/kmatrix-debug.podspec
cd ../../repository/ || exit
sed -i -e "s|'kmatrix'|'kmatrix-debug'|g" kmatrix-debug.podspec
sed -i -e "s|'kmatrix.xcframework'|'debug/kmatrix.xcframework'|g" kmatrix-debug.podspec
rm *.podspec-e
cd "$BASE_PATH" || exit
cd $BUILD_PATH/cocoapods/publish/release || exit
cp kmatrix.podspec ../../repository/kmatrix-release.podspec
cd ../../repository/ || exit
sed -i -e "s|'kmatrix'|'kmatrix-release'|g" kmatrix-release.podspec
sed -i -e "s|'kmatrix.xcframework'|'release/kmatrix.xcframework'|g" kmatrix-release.podspec
rm *.podspec-e

# Copy Framework
cd "$BASE_PATH" || exit
cd $BUILD_PATH/cocoapods/publish/debug || exit
cp -r kmatrix.xcframework ../../repository/debug/kmatrix.xcframework
cd "$BASE_PATH" || exit
cd $BUILD_PATH/cocoapods/publish/release || exit
cp -r kmatrix.xcframework ../../repository/release/kmatrix.xcframework

# Copy README
cd "$BASE_PATH" || exit
cd ../ || exit
cp ./LICENSE ./all/build/cocoapods/repository/LICENSE
cp ./docs/pods/README.md ./all/build/cocoapods/repository/README.md
cp ./docs/pods/README_ja.md ./all/build/cocoapods/repository/README_ja.md
