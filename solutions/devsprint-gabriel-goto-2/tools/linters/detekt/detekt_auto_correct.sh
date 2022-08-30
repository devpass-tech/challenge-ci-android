#!/usr/bin/env sh

VERSION=$(cat tools/linters/detekt/detekt_version)

tools/linters/detekt/detekt-downloader.sh $VERSION
tools/linters/detekt/detekt-formatter-downloader.sh $VERSION

./.detekt/detekt-cli-$VERSION/bin/detekt-cli \
  --config  tools/linters/detekt/default-config.yml \
  --plugins .detekt/detekt-formatting-$VERSION.jar \
  --jvm-target 11 \
  --language-version 1.5 \
  --auto-correct \
  --parallel \
  --build-upon-default-config \
  --excludes "**/build/**" \
  --report txt:build/reports/detekt/detekt.txt \
  --report html:build/reports/detekt/detekt \
  --report xml:build/reports/detekt/detekt.xml
