package com.bighi.algos.week4;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

public class SCCComputer {
    private static final String FILENAME1 = "week4/SCC.txt";

    int t = 0; // # of nodes processed so far
    Node s = null; // current source vertex
    int localSccCount;

    public static void main(String[] args) throws IOException {
        System.out.println("Start time: " + new GregorianCalendar().getTime());
        System.out.println(new SCCComputer().getTop5Sccs(FILENAME1));
        System.out.println("End time: " + new GregorianCalendar().getTime());
    }

    public List<Integer> getTop5Sccs(String fileName) throws IOException {
        Graph g = new Graph();

        File file = new File(fileName);
        Path path = file.toPath();

        List<String> allInput = Files.readAllLines(path,
                Charset.defaultCharset());

        for (String ip : allInput) {
            String[] vertices = ip.split(" ");
            int val1 = Integer.valueOf(vertices[0]);
            int val2 = Integer.valueOf(vertices[1]);

            Node n1 = g.addNode(val1);
            Node n2 = g.addNode(val2);

            Edge e = new Edge(n1, n2);

            g.addEdge(e);

            n1.addOutgoingEdge(e);
            n2.addIncomingEdge(e);
        }

        List<Integer> finalSccCounts = new SCCComputer().computeSCC(g);
        // System.out.println(finalSccCounts);
        return finalSccCounts.subList(0, 5);
    }

    public List<Integer> computeSCC(Graph g) {
        List<Integer> finalSccCounts = new ArrayList<>();

        g.reverseEdgeDirections();
        dfsLoop(g, false, finalSccCounts);
        g.unMarkExploredAllNodes();
        g.switchValuesAndFinishingTimeInNodes();
        g.reverseEdgeDirections();
        dfsLoop(g, true, finalSccCounts);
        Collections.sort(finalSccCounts);
        Collections.reverse(finalSccCounts);
        int size = finalSccCounts.size();
        while (size < 5) {
            finalSccCounts.add(0);
            size++;
        }
        // System.out.println(finalSccCounts);
        return finalSccCounts;
    }

    public void dfsLoop(Graph g, boolean countScc, List<Integer> finalSccCounts) {
        // TreeSet<Node> sortedNodes = g.getNodesInSortedOrder();
        ArrayList<Node> sortedNodes = g.getAllNodes();
        Collections.sort(sortedNodes);
        for (Node i : sortedNodes) {
            if (!i.isExplored()) {
                s = i;
                localSccCount = 1;
                dfs(g, i, countScc);
                if (countScc) {
                    finalSccCounts.add(localSccCount);
                }
            }
        }
    }

    public void dfs(Graph g, Node i, boolean countScc) {
        i.markExplored();
        i.setLeader(s);
        ArrayList<Edge> outgoingEdges = i.getOutGoingEdges();
        for (Edge e : outgoingEdges) {
            Node j = e.getEndNode();
            if (!j.isExplored()) {
                if (countScc) {
                    localSccCount++;
                }
                dfs(g, j, countScc);
            }
        }
        t++;
        i.setFinishingTime(t);
    }
}

/*
 * The file contains the edges of a directed graph. Vertices are labeled as
 * positive integers from 1 to 875714. Every row indicates an edge, the vertex
 * label in first column is the tail and the vertex label in second column is
 * the head (recall the graph is directed, and the edges are directed from the
 * first column vertex to the second column vertex). So for example, the 11th
 * row looks liks : "2 47646". This just means that the vertex with label 2 has
 * an outgoing edge to the vertex with label 47646
 * 
 * Your task is to code up the algorithm from the video lectures for computing
 * strongly connected components (SCCs), and to run this algorithm on the given
 * graph.
 * 
 * Output Format: You should output the sizes of the 5 largest SCCs in the given
 * graph, in decreasing order of sizes, separated by commas (avoid any spaces).
 * So if your algorithm computes the sizes of the five largest SCCs to be 500,
 * 400, 300, 200 and 100, then your answer should be "500,400,300,200,100". If
 * your algorithm finds less than 5 SCCs, then write 0 for the remaining terms.
 * Thus, if your algorithm computes only 3 SCCs whose sizes are 400, 300, and
 * 100, then your answer should be "400,300,100,0,0".
 * 
 * WARNING: This is the most challenging programming assignment of the course.
 * Because of the size of the graph you may have to manage memory carefully. The
 * best way to do this depends on your programming language and environment, and
 * we strongly suggest that you exchange tips for doing this on the discussion
 * forums.
 */
