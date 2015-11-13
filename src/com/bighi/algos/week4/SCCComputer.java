package com.bighi.algos.week4;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SCCComputer {
	private static final String FILENAME1 = "week4/small1.txt";
	
	public static void main(String[] args) throws IOException {
		Graph g = new Graph();
		
		File file = new File(FILENAME1);
		Path path = file.toPath();
		
		List<String> allInput = Files.readAllLines(path, Charset.defaultCharset());
		
		for(String ip: allInput) {
			String[] vertices = ip.split(" ");
			int val1 = Integer.valueOf(vertices[0]);
			int val2 = Integer.valueOf(vertices[1]);
			
			Node n1 = new Node(val1);
			Node n2 = new Node(val2);
			
			Edge e = new Edge(n1,n2);
			
			n1.addOutGoingEdge(e);
			n2.addIncomingEdge(e);
			
			g.addEdge(e);
			g.addNodes(n1, n2);
		}
		
		new SCCComputer().computeSCC(g);
	}
	
	public void computeSCC(Graph g) {
		
	}
	
	public void dfsLoop() {
		
	}
	
	public int dfs() {
		return 1;
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
