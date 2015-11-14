package com.bighi.algos.week4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class Graph {
	private ArrayList<Node> allNodes = new ArrayList<>();
	private ArrayList<Edge> allEdges = new ArrayList<>();
	
	private TreeSet<Node> nodesInSortedOrder = new TreeSet<>();
	
	private Node dummyNode = new Node(0);
	
	public TreeSet<Node> getNodesInSortedOrder() {
		return nodesInSortedOrder;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(Edge e: allEdges) {
			builder.append(e);
			builder.append(",");
		}
		return builder.toString();
	}
	
	public Edge addEdge(Edge e) {
		int idx = allEdges.indexOf(e);
		if (idx == -1) {
			allEdges.add(e);
			return e;
		}
		return allEdges.get(idx);
	}
	
	public void addEdges(Edge... es) {
		for (Edge e: es) {
			addEdge(e);
		}
	}

	public Node addNode(int value) {
		dummyNode.setValue(value);
		
		return addNode(dummyNode);
	}
	
	private Node addNode(Node n) {
		int idx = allNodes.indexOf(n);
		if(idx == -1){
			n = new Node(n.getValue());
			allNodes.add(n);
			addNodeToSortedList(n);
			return n;
		}
		return allNodes.get(idx);
	}
	
	public void addNodes(Node... ns) {
		for (Node n: ns) {
			addNode(n);
		}
	}
	
	private void addNodeToSortedList(Node... n) {
		nodesInSortedOrder.addAll(Arrays.asList(n));
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
		nodesInSortedOrder.clear();
		for(Node n: allNodes) {
			int temp = n.getValue();
			n.setValue(n.getFinishingTime());
			n.setFinishingTime(temp);
			nodesInSortedOrder.add(n);
		}
	}
}
