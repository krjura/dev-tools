#!/usr/bin/env bash

cd frontend/angular-web

HOME=/tmp/build-user
mkdir -p $HOME
echo "Users temporary home is $HOME"

# delete dist
rm -rf dist

npm install
npm run ng -- build --prod --base-href /portal --deploy-url=/portal/
