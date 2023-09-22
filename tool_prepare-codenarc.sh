#!/usr/bin/env sh

# Define CodeNarc version
codenarcVersion="2.2.4"

# Build CodeNarc
cd codenarc-converter/CodeNarc
./gradlew build -x test

# Deploy to local repository
cd ..
mvn -B install:install-file -Dfile=CodeNarc/build/libs/CodeNarc-${codenarcVersion}.jar -DgroupId=org.codenarc -DartifactId=CodeNarc -Dversion=${codenarcVersion} -Dpackaging=jar

