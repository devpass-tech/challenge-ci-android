#!/bin/bash

ROOT_FOLDER=$(git rev-parse --show-toplevel)

if [[ ! -f "$ROOT_FOLDER/.detekt/detekt-formatting-$1.jar" ]]; then
  cd $ROOT_FOLDER
  mkdir .detekt
  cd .detekt
  echo "You don't have the latest version of this repository's detekt-formatting, downloading..."
  curl -sSLO https://github.com/detekt/detekt/releases/download/v$1/detekt-formatting-$1.jar
fi
