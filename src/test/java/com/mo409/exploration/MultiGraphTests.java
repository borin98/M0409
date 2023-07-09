package test.java.com.mo409.exploration;

import org.graphstream.graph.implementations.MultiGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultiGraphTests {

    private MultiGraph createGraph() {
        return new MultiGraph("mg");
    }

    private MultiGraph createMultiNodes(int n) {
        MultiGraph MultiGraph = createGraph();
        for (int i = 0; i < n; i++) {
            MultiGraph.addNode(String.valueOf(i));
        }
        return MultiGraph;
    }

    private MultiGraph createGraphOneLoop(int n) {
        MultiGraph MultiGraph = createGraph();
        MultiGraph.addNode(String.valueOf(0));

        if (n == 1) {
            MultiGraph.addEdge(String.valueOf(0), String.valueOf(0), String.valueOf(0));
            return MultiGraph;
        }

        for (int i = 1; i < n; i++) {
            MultiGraph.addNode(String.valueOf(i));
            MultiGraph.addEdge(String.valueOf(i), String.valueOf(i - 1), String.valueOf(i));
        }

        if (n > 2) {
            MultiGraph.addEdge(String.valueOf(n), String.valueOf(n - 1), String.valueOf(0));
        }

        return MultiGraph;
    }

    @Test
    void clearEmpty() {
        MultiGraph MultiGraph = this.createGraph();
        MultiGraph.clear();
        assertTrue(0 == MultiGraph.getEdgeCount() && 0 == MultiGraph.getNodeCount());
    }

    @Test
    void addSingleNode() {
        MultiGraph MultiGraph = this.createGraph();
        MultiGraph.addNode("A");
        assertTrue(0 == MultiGraph.getEdgeCount() && 1 == MultiGraph.getNodeCount());
    }

    @Test
    void addSingleEdge() {
        MultiGraph MultiGraph = this.createGraph();
        MultiGraph.addNode("A");
        MultiGraph.addNode("B");
        MultiGraph.addEdge("AB", "A", "B");
        assertTrue(1 == MultiGraph.getEdgeCount() && 2 == MultiGraph.getNodeCount());
    }

    @Test
    void addNNodes() {
        int n = (int) Math.pow(2, 10); // MultiGraph.DEFAULT_NODE_CAPACITY, MultiGraph.DEFAULT_NODE_CAPACITY);
        MultiGraph MultiGraph = this.createMultiNodes(n);
        System.out.println(MultiGraph.getNodeCount());
        assertTrue(0 == MultiGraph.getEdgeCount() && n == MultiGraph.getNodeCount());
    }

    @Test
    void addNodesAndClear() {
        int n = (int) Math.pow(2, 6); // MultiGraph.DEFAULT_NODE_CAPACITY, MultiGraph.DEFAULT_NODE_CAPACITY);
        MultiGraph MultiGraph = this.createMultiNodes(n);
        System.out.println("nodes numbers before the clear : " + MultiGraph.getNodeCount());
        MultiGraph.clear();
        System.out.println("nodes numbers after the clear : " + MultiGraph.getNodeCount());
        assertTrue(0 == MultiGraph.getEdgeCount() && 0 == MultiGraph.getNodeCount());
    }

    @Test
    void addNodesAndEdgesAndClear() {
        int n = (int) Math.pow(2, 6);
        MultiGraph MultiGraph = this.createGraphOneLoop(n);
        System.out.println("nodes numbers before the clear : " + MultiGraph.getNodeCount());
        System.out.println("edges numbers before the clear : " + MultiGraph.getEdgeCount());
        MultiGraph.clear();
        System.out.println("nodes numbers after the clear : " + MultiGraph.getNodeCount());
        System.out.println("edges numbers after the clear : " + MultiGraph.getEdgeCount());
        assertTrue(0 == MultiGraph.getEdgeCount() && 0 == MultiGraph.getNodeCount());
    }

    @Test
    void addNodesAndEdgesAndRemoveSomeNodes() {
        int n = 3;
        MultiGraph MultiGraph = this.createGraphOneLoop(n);
        System.out.println("nodes numbers before the clear : " + MultiGraph.getNodeCount());
        MultiGraph.removeNode(1);
        System.out.println("nodes numbers after the clear : " + MultiGraph.getNodeCount());
        assertEquals(n - 1, MultiGraph.getNodeCount());
    }

    @Test
    void addNodesAndEdgesAndRemoveSomeEdges() {
        int n = 3;
        MultiGraph MultiGraph = this.createGraphOneLoop(n);
        System.out.println("edges numbers before the clear : " + MultiGraph.getEdgeCount());
        MultiGraph.removeEdge(1);
        System.out.println("edges numbers after the clear : " + MultiGraph.getEdgeCount());
        assertEquals(n - 1, MultiGraph.getEdgeCount());
    }

}
