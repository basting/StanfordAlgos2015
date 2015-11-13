package com.bighi.algos.week4;

public class Edge {
	private Node startNode;
	private Node endNode;
	
	@Override
	public String toString() {
		return startNode + "-->" + endNode;
	}
	
	public Edge(Node startNode, Node endNode) {
		this.startNode = startNode;
		this.endNode = endNode;
	}
	
	public Node getStartNode() {
		return startNode;
	}

	public void setStartNode(Node startNode) {
		this.startNode = startNode;
	}

	public Node getEndNode() {
		return endNode;
	}

	public void setEndNode(Node endNode) {
		this.endNode = endNode;
	}
}
