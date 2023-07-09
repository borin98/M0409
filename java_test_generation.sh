#!/bin/bash

java -jar ../lib/graphwalker-cli-*.jar offline \
-m src/main/resources/com/path/PathModel.json \
'a_star(reached_vertex(v_NonEmptyPath))' | jq '.currentElementName'
# 'quick_random(edge_coverage(100))' 
# 'a_star(reached_vertex(v_MaxNodesAndEdges))'

