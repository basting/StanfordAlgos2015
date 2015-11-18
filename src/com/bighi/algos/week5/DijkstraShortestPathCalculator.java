package com.bighi.algos.week5;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class DijkstraShortestPathCalculator {

	private static final String FILENAME1 = "week5/dijkstraData.txt";
	
	private ArrayList<Node> verticesProcessedSoFar = new ArrayList<>();
	private Node sourceVertex;
	
	public static void main(String[] args) throws IOException {
		System.out.println("Start time: " + new GregorianCalendar().getTime());
		new DijkstraShortestPathCalculator().readInputAndcalculateShortestPaths(FILENAME1);
		System.out.println("End time: " + new GregorianCalendar().getTime());
	}

	public void readInputAndcalculateShortestPaths(String fileName) throws IOException {
		Graph g = new Graph();

        File file = new File(fileName);
        Path path = file.toPath();

        List<String> allInput = Files.readAllLines(path,
                Charset.defaultCharset());

        for (String ip : allInput) {
            String[] vertices = ip.split(" ");
            int startVertex = Integer.valueOf(vertices[0]);
            for(int i = 1; i < vertices.length; i++){
            	
            	String[] vertexWeight = vertices[i].split(",");
            	
	            int otherVertex = Integer.valueOf(vertexWeight[0]);
	            int weight = Integer.valueOf(vertexWeight[1]);
	
	            Node n1 = g.addNode(startVertex);
	            Node n2 = g.addNode(otherVertex);
	
	            Edge e = g.addEdge(n1, n2, weight);
	
	            n1.addRelatedEdge(e);
	            n2.addRelatedEdge(e);
            }
        }
        
        sourceVertex = g.getNodeFromGraph(1);
        sourceVertex.setShortDistanceFromSource(0);
        sourceVertex.markProcessed();
        verticesProcessedSoFar.add(sourceVertex);
        
        int targetSize = g.getAllNodes().size();
        
        calculateShortestPaths(g, targetSize);
        
		System.out.println(g);
	}

	private void calculateShortestPaths(Graph g, int targetSize) {
		while(verticesProcessedSoFar.size() < targetSize) {
			Node minNode = new Node(1, Node.DEFAULT_DIST + 1000);
			minNode.setGreedyScore(Integer.MAX_VALUE);
			int minDist = Node.DEFAULT_DIST + 1000;
			Edge minEdge = new Edge(new Node(1, Node.DEFAULT_DIST + 1000), minNode, minDist);
			
			int size = verticesProcessedSoFar.size();
			for(int i=0; i<size; i++) {
				Node currNode = verticesProcessedSoFar.get(i);
				ArrayList<Edge> relatedEdges = currNode.getRelatedEdges();
				for(Edge currEdge : relatedEdges) {
					Node oppNode = currEdge.getOppositeNode(currNode);
					if(oppNode.isProcessed()) {
						continue;
					}
					int newGreedyScore = currNode.getShortDistanceFromSource() + currEdge.getLength();
					int currMinGreedyScore = minNode.getGreedyScore();
					if(newGreedyScore < currMinGreedyScore) {
						minNode = oppNode;
						minEdge = currEdge;
						minDist = newGreedyScore;
						minNode.setGreedyScore(newGreedyScore);
					}
				}				
			}
			minNode.setShortDistanceFromSource(minDist);
			minNode.markProcessed();
			verticesProcessedSoFar.add(minNode);
		}				
	}
}

/*
 * In this programming problem you'll code up Dijkstra's shortest-path
 * algorithm. The file contains an adjacency list representation of an
 * undirected weighted graph with 200 vertices labeled 1 to 200. Each row
 * consists of the node tuples that are adjacent to that particular vertex along
 * with the length of that edge. For example, the 6th row has 6 as the first
 * entry indicating that this row corresponds to the vertex labeled 6. The next
 * entry of this row "141,8200" indicates that there is an edge between vertex 6
 * and vertex 141 that has length 8200. The rest of the pairs of this row
 * indicate the other vertices adjacent to vertex 6 and the lengths of the
 * corresponding edges.
 * 
 * Your task is to run Dijkstra's shortest-path algorithm on this graph, using 1
 * (the first vertex) as the source vertex, and to compute the shortest-path
 * distances between 1 and every other vertex of the graph. If there is no path
 * between a vertex v and vertex 1, we'll define the shortest-path distance
 * between 1 and v to be 1000000.
 * 
 * You should report the shortest-path distances to the following ten vertices,
 * in order: 7,37,59,82,99,115,133,165,188,197. You should encode the distances
 * as a comma-separated string of integers. So if you find that all ten of these
 * vertices except 115 are at distance 1000 away from vertex 1 and 115 is 2000
 * distance away, then your answer should be
 * 1000,1000,1000,1000,1000,2000,1000,1000,1000,1000. Remember the order of
 * reporting DOES MATTER, and the string should be in the same order in which
 * the above ten vertices are given. Please type your answer in the space
 * provided.
 * 
 * IMPLEMENTATION NOTES: This graph is small enough that the straightforward
 * O(mn) time implementation of Dijkstra's algorithm should work fine. OPTIONAL:
 * For those of you seeking an additional challenge, try implementing the
 * heap-based version. Note this requires a heap that supports deletions, and
 * you'll probably need to maintain some kind of mapping between vertices and
 * their positions in the heap.
 */

/*
 * Answer: 2599,2610,2947,2052,2367,2399,2029,2442,2505,3068 
 */
