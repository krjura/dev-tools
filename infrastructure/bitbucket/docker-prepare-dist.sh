#!/bin/bash

DIST_FOLDER=dist

rm -rf $DIST_FOLDER
mkdir -p $DIST_FOLDER
mkdir -p $DIST_FOLDER/etc

cp -r build/libs/devtools-root-boot.jar $DIST_FOLDER/devtools-root-boot.jar
cp -r frontend/angular-web/dist/angular-web $DIST_FOLDER/web-resources

chmod -R a-w,o-rwx $DIST_FOLDER