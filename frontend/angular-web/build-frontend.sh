#!/usr/bin/env bash

set -x

cd frontend/angular-web

HOME=/tmp/build-user
mkdir -p $HOME
echo "Users temporary home is $HOME"

# delete dist
rm -rf dist

npm install --global npm@8.1.0
npm install @angular/cli@13.0.3
npm install
npm run ng -- build
