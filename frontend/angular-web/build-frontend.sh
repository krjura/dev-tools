#!/usr/bin/env bash

set -x

cd frontend/angular-web

HOME=/tmp/build-user
mkdir -p $HOME
echo "Users temporary home is $HOME"

# delete dist
rm -rf dist

npm install
npm install @angular/cli
npm run ng -- build --base-href /portal --deploy-url=/portal/
