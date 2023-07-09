package com.multigraph;

import org.graphstream.graph.Path;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TestPath {

    @Test
    public void test_add() {
        SingleGraph graph = new SingleGraph("sg");
        graph.addNode("1");
        graph.addNode("2");
        graph.addNode("3");
        graph.addEdge("1", "1", "2");
        graph.addEdge("2", "2", "3");
        graph.addEdge("3", "3", "1");

        Path path = new Path();
//        path.setRoot(graph.getNode("1"));
//        path.add(graph.getEdge("1"));
        path.add(graph.getNode("1"), graph.getEdge("1"));
//        path.popEdge();
        path.add(graph.getNode("2"), graph.getEdge("1"));
        path.add(graph.getNode("1"), graph.getEdge("1"));
        path.add(graph.getNode("2"), graph.getEdge("1"));
        System.out.println("Edges " + path.getEdgeCount());
        System.out.println("Nodes " + path.getNodeCount());
    }
}
