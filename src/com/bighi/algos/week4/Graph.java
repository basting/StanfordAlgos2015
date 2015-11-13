package com.bighi.algos.week4;

import java.util.Arrays;
import java.util.HashSet;

public class Graph {
	private HashSet<Node> allNodes = new HashSet<>();
	private HashSet<Edge> allEdges = new HashSet<>();
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(Edge e: allEdges) {
			builder.append(e);
			builder.append(",");
		}
		return builder.toString();
	}
	
	public void addNode(Node n) {
		allNodes.add(n);
	}
	
	public void addEdge(Edge e) {
		allEdges.add(e);
	}
	
	public void addEdges(Edge... es) {
		allEdges.addAll(Arrays.asList(es));
	}
	
	public void addNodes(Node... ns) {
		allNodes.addAll(Arrays.asList(ns));
	}
}
