#!/bin/sh

BINTRAY_USERNAME=*****
BINTRAY_KEY=********************

./gradlew clean -Puser=$BINTRAY_USERNAME -Pkey=$BINTRAY_KEY bintrayUpload 