package com.bighi.algos.week4;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
	
	private ArrayList<Node> allNodes = new ArrayList<>();
	private ArrayList<Edge> allEdges = new ArrayList<>();
	
	// Maps node values with the actual node objects
	private HashMap<Integer, Node> nodeMap = new HashMap<>();
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(Edge e: allEdges) {
			builder.append(e);
			builder.append(",");
		}
		return builder.toString();
	}
	
	public ArrayList<Node> getAllNodes() {
		return allNodes;
	}
	
	public Edge addEdge(Edge e) {
		allEdges.add(e);
		return e;
	}
	
	public void addEdges(Edge... es) {
		for (Edge e: es) {
			addEdge(e);
		}
	}

	public Node addNode(int value) {
		Node n = nodeMap.get(value);
		if(n == null) {
			n = new Node(value);
			allNodes.add(n);
			
			nodeMap.put(value, n);
		}
		
		return n;
	}
	
	public void reverseEdgeDirections() {
		for(Edge e: allEdges) {
			Node newStartNode = e.getEndNode();
			Node newEndNode = e.getStartNode();
			
			e.setStartNode(newStartNode);
			e.setEndNode(newEndNode);
			
			newStartNode.removeIncomingEdge(e);
			newStartNode.addOutgoingEdge(e);
			
			newEndNode.removeOutgoingEdge(e);
			newEndNode.addIncomingEdge(e);			
		}
	}
	
	public void unMarkExploredAllNodes() {
		for(Node n: allNodes) {
			n.unmarkExplored();
		}
	}
	
	public void switchValuesAndFinishingTimeInNodes() {
		for(Node n: allNodes) {
			int temp = n.getValue();
			n.setValue(n.getFinishingTime());
			n.setFinishingTime(temp);
		}
	}
}
