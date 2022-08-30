#!/usr/bin/env sh

git fetch origin $CI_MERGE_REQUEST_TARGET_BRANCH_NAME:$CI_MERGE_REQUEST_TARGET_BRANCH_NAME $CI_MERGE_REQUEST_SOURCE_BRANCH_NAME:$CI_MERGE_REQUEST_SOURCE_BRANCH_NAME --no-tags

MODULES_TO_ANALYZE=$(paste -s -d '|' quality/detekt/valid_modules)
FILES_TO_ANALYZE=$(git diff --diff-filter=ACMRd --name-only $CI_MERGE_REQUEST_TARGET_BRANCH_NAME...$CI_MERGE_REQUEST_SOURCE_BRANCH_NAME | grep '\.kt[s"]\?$' | egrep "$MODULES_TO_ANALYZE" | xargs | sed 's/ /,/g')

if [ -z "$FILES_TO_ANALYZE" ]; then
	# In case there are no files to analyze, we create
	# the build/reports/detekt/detekt.xml file, so Danger doesn't fail because of missing file
	mkdir -p build/reports/detekt
	touch build/reports/detekt/detekt.xml
else
	quality/detekt/detekt.sh $FILES_TO_ANALYZE
fi
