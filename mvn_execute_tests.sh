#!/bin/bash 

mvn clean graphwalker:generate-sources compile exec:java -Dexec.mainClass=”com.path