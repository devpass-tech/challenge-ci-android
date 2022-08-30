#!/bin/bash

ROOT_FOLDER=$(git rev-parse --show-toplevel)

if [[ ! -d "$ROOT_FOLDER/.detekt/detekt-cli-$1" ]]; then
  rm -f $ROOT_FOLDER/.detekt
  cd $ROOT_FOLDER
  mkdir .detekt
  cd .detekt
  echo "You don't have the latest version of this repository's detekt, downloading..."
  curl -sSLO https://github.com/detekt/detekt/releases/download/v$1/detekt-cli-$1.zip && unzip detekt-cli-$1.zip
fi
