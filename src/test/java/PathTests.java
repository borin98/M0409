package test.java;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PathTests {

    private SingleGraph createGraph() {
        return new SingleGraph("sg");
    }

    private SingleGraph createMultiNodes(int n) {
        SingleGraph singleGraph = createGraph();
        for (int i = 0; i < n; i++) {
            singleGraph.addNode(String.valueOf(i));
        }
        return singleGraph;
    }

    private SingleGraph createGraphSimpleLoop(int n) {
        SingleGraph singleGraph = createGraph();
        singleGraph.addNode(String.valueOf(0));

        if (n == 1) {
            singleGraph.addEdge(String.valueOf(0), String.valueOf(0), String.valueOf(0));
            return singleGraph;
        }

        for (int i = 1; i < n; i++) {
            singleGraph.addNode(String.valueOf(i));
            singleGraph.addEdge(String.valueOf(i), String.valueOf(i - 1), String.valueOf(i));
        }

        if (n > 2) {
            singleGraph.addEdge(String.valueOf(n), String.valueOf(n - 1), String.valueOf(0));
        }

        return singleGraph;
    }

    private SingleGraph createGraphMultiLoop(int n) {
        SingleGraph singleGraph = createGraph();
        singleGraph.addNode(String.valueOf(0));
        singleGraph.addEdge(0 + "_loop", String.valueOf(0), String.valueOf(0));

        if (n == 1) {
            return singleGraph;
        }

        for (int i = 1; i < n; i++) {
            singleGraph.addNode(String.valueOf(i));
            singleGraph.addEdge(i + "_loop", String.valueOf(i), String.valueOf(i));
            singleGraph.addEdge(String.valueOf(i), String.valueOf(i - 1), String.valueOf(i));
        }

        if (n > 2) {
            singleGraph.addEdge(String.valueOf(n), String.valueOf(n - 1), String.valueOf(0));
        }

        return singleGraph;
    }


    private Path createPath(SingleGraph Graph) {
        Path Mypath = new Path();
        int totalNodes = Graph.getNodeCount();

        for (int i = 1; i < totalNodes; i++) {
            Edge myEdge = Graph.getEdge(String.valueOf(i));
            Mypath.add(myEdge.getNode0(), myEdge);
        }

        return Mypath;

    }

    private Path createPathWithSimpleLooping(SingleGraph Graph) {
        Path Mypath = new Path();
        int totalNodes = Graph.getNodeCount();

        for (int i = 1; i < totalNodes; i++) {
            Edge myEdge = Graph.getEdge(String.valueOf(i));
            Mypath.add(myEdge.getNode0(), myEdge);
        }

        Edge myEdge = Graph.getEdge(String.valueOf(totalNodes));
        Mypath.add(myEdge.getNode0(), myEdge);

        return Mypath;

    }

    private Path createPathWithMultiLooping(SingleGraph Graph) {
        Path Mypath = new Path();
        int totalNodes = Graph.getNodeCount();
        Edge myEdge = Graph.getEdge(0);
        Mypath.add(myEdge.getNode0(), myEdge);

        for (int i = 1; i < totalNodes; i++) {
            myEdge = Graph.getEdge(String.valueOf(i));
            Edge myEdgeLooping = Graph.getEdge(i + "_loop");
            Mypath.add(myEdge.getNode0(), myEdge);
            Mypath.add(myEdgeLooping.getNode0(), myEdgeLooping);
        }

//        myEdge = Graph.getEdge(String.valueOf(totalNodes));
//        Mypath.add(myEdge.getNode0(), myEdge);

        return Mypath;

    }

    @Test
    void addSingleNode() {
        Path path = new Path();

        Graph graph = new SingleGraph("Path");
        graph.addNode("A");
        graph.addNode("B");
        graph.addEdge("AB", "A", "B");

        path.add(graph.getNode("A"), graph.getEdge("AB"));

        assertEquals(1, path.getEdgeCount());
    }

    @Test
    void clearEmpty() {
        Path path = new Path();
        path.clear();
        assertTrue(path.empty());
    }

    @Test
    void removeNodesOnPath() {
        int maxNodeSize = 10;
        int nNodesRemove = 3;
        SingleGraph MyGraph = this.createGraphSimpleLoop(maxNodeSize);
        Path MyPath = this.createPath(MyGraph);

        for (int i = 0; i < nNodesRemove; i++) {
            MyPath.popNode();
        }

        assertEquals(MyPath.getNodeCount(), maxNodeSize - nNodesRemove);

    }

    @Test
    void removeEdgesOnPath() {
        int maxNodeSize = 10;
        int nEdgesRemove = 3;
        SingleGraph MyGraph = this.createGraphSimpleLoop(maxNodeSize);
        Path MyPath = this.createPath(MyGraph);

        for (int i = 0; i < nEdgesRemove; i++) {
            MyPath.popEdge();
        }

        assertEquals(MyPath.getNodeCount(), maxNodeSize - nEdgesRemove);

    }

    @Test
    void removeRootLooping() {
        int maxNodeSize = 3;
        SingleGraph MyGraph = this.createGraphSimpleLoop(maxNodeSize);
        Path MyPath = this.createPathWithSimpleLooping(MyGraph);
        MyPath.removeLoops();

        assertEquals(1, MyPath.getNodeCount());

    }

    @Test
    void removeGraphLooping() {
        int maxNodeSize = 1069;
        SingleGraph MyGraph = this.createGraphMultiLoop(maxNodeSize);
        Path MyPath = this.createPathWithMultiLooping(MyGraph);
        MyPath.removeLoops();

        assertEquals(maxNodeSize, MyPath.getNodeCount());

    }

}
