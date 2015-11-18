package com.bighi.algos.week5;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {

	class EndNodes {
		Node n1;
		Node n2;

		public EndNodes(Node node1, Node node2) {
			n1 = node1;
			n2 = node2;
		}

		@Override
		public boolean equals(Object obj) {
			EndNodes endNodes = (EndNodes) obj;
			if (n1.getValue() == endNodes.n1.getValue() && n2.getValue() == endNodes.n2.getValue()) {
				return true;
			}
			if (n1.getValue() == endNodes.n2.getValue() && n2.getValue() == endNodes.n1.getValue()) {
				return true;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return new Integer(n1.getValue() + n2.getValue()).hashCode();
		}
	}

	private ArrayList<Node> allNodes = new ArrayList<>();
	private ArrayList<Edge> allEdges = new ArrayList<>();

	// Maps node values with the actual node objects
	private HashMap<Integer, Node> nodeMap = new HashMap<>();
	private HashMap<EndNodes, Edge> edgeMap = new HashMap<>();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Edge e : allEdges) {
			builder.append(e);
			builder.append(",");
		}
		return builder.toString();
	}

	public ArrayList<Node> getAllNodes() {
		return allNodes;
	}

	public Edge addEdge(Node node1, Node node2, int length) {
		EndNodes endNodes = new EndNodes(node1, node2);
		Edge e = edgeMap.get(endNodes);
		if (e == null) {
			e = new Edge(node1, node2, length);
			allEdges.add(e);
			edgeMap.put(endNodes, e);
		}
		return e;
	}

	public Node addNode(int value) {
		Node n = nodeMap.get(value);
		if (n == null) {
			n = new Node(value);
			allNodes.add(n);
			nodeMap.put(value, n);
		}

		return n;
	}
}