package test.java;

import org.graphstream.graph.implementations.SingleGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SingleGraphTests {

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

    private SingleGraph createGraphOneLoop(int n) {
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

    @Test
    void clearEmpty() {
        SingleGraph singleGraph = this.createGraph();
        singleGraph.clear();
        assertTrue(0 == singleGraph.getEdgeCount() && 0 == singleGraph.getNodeCount());
    }

    @Test
    void addSingleNode() {
        SingleGraph singleGraph = this.createGraph();
        singleGraph.addNode("A");
        assertTrue(0 == singleGraph.getEdgeCount() && 1 == singleGraph.getNodeCount());
    }

    @Test
    void addSingleEdge() {
        SingleGraph singleGraph = this.createGraph();
        singleGraph.addNode("A");
        singleGraph.addNode("B");
        singleGraph.addEdge("AB", "A", "B");
        assertTrue(1 == singleGraph.getEdgeCount() && 2 == singleGraph.getNodeCount());
    }

    @Test
    void addNNodes() {
        int n = (int) Math.pow(2, 10); // SingleGraph.DEFAULT_NODE_CAPACITY, SingleGraph.DEFAULT_NODE_CAPACITY);
        SingleGraph singleGraph = this.createMultiNodes(n);
        System.out.println(singleGraph.getNodeCount());
        assertTrue(0 == singleGraph.getEdgeCount() && n == singleGraph.getNodeCount());
    }

    @Test
    void addNodesAndClear() {
        int n = (int) Math.pow(2, 6); // SingleGraph.DEFAULT_NODE_CAPACITY, SingleGraph.DEFAULT_NODE_CAPACITY);
        SingleGraph singleGraph = this.createMultiNodes(n);
        System.out.println("nodes numbers before the clear : " + singleGraph.getNodeCount());
        singleGraph.clear();
        System.out.println("nodes numbers after the clear : " + singleGraph.getNodeCount());
        assertTrue(0 == singleGraph.getEdgeCount() && 0 == singleGraph.getNodeCount());
    }

    @Test
    void addNodesAndEdgesAndClear() {
        int n = (int) Math.pow(2, 6);
        ; // SingleGraph.DEFAULT_NODE_CAPACITY, SingleGraph.DEFAULT_NODE_CAPACITY);
        SingleGraph singleGraph = this.createGraphOneLoop(n);
        System.out.println("nodes numbers before the clear : " + singleGraph.getNodeCount());
        System.out.println("edges numbers before the clear : " + singleGraph.getEdgeCount());
        singleGraph.clear();
        System.out.println("nodes numbers after the clear : " + singleGraph.getNodeCount());
        System.out.println("edges numbers after the clear : " + singleGraph.getEdgeCount());
        assertTrue(0 == singleGraph.getEdgeCount() && 0 == singleGraph.getNodeCount());
    }

    @Test
    void addNodesAndEdgesAndRemoveSomeNodes() {
        int n = 3;
        ; // SingleGraph.DEFAULT_NODE_CAPACITY, SingleGraph.DEFAULT_NODE_CAPACITY);
        SingleGraph singleGraph = this.createGraphOneLoop(n);
        System.out.println("nodes numbers before the clear : " + singleGraph.getNodeCount());
        singleGraph.removeNode(1);
        System.out.println("nodes numbers after the clear : " + singleGraph.getNodeCount());
        assertEquals(n - 1, singleGraph.getNodeCount());
    }

    @Test
    void addNodesAndEdgesAndRemoveSomeEdges() {
        int n = 3;
        ; // SingleGraph.DEFAULT_NODE_CAPACITY, SingleGraph.DEFAULT_NODE_CAPACITY);
        SingleGraph singleGraph = this.createGraphOneLoop(n);
        System.out.println("edges numbers before the clear : " + singleGraph.getEdgeCount());
        singleGraph.removeEdge(1);
        System.out.println("edges numbers after the clear : " + singleGraph.getEdgeCount());
        assertEquals(n - 1, singleGraph.getEdgeCount());
    }

}
