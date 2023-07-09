#!/bin/bash

mvn archetype:generate -B \
-DarchetypeGroupId=org.graphwalker \
-DarchetypeArtifactId=graphwalker-maven-archetype \
-DgroupId=com.path -DartifactId=Path \
-DarchetypeVersion=LATEST

