package com.bighi.algos.week4;

import java.util.ArrayList;

public class Node {
	private int value;
	private ArrayList<Edge> outGoingEdges = new ArrayList<Edge>();
	private ArrayList<Edge> incomingEdges = new ArrayList<Edge>();
	private int order;
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
	
	public Node(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setOrder(int order) {
		this.order = order;
	}
	
	public int getOrder() {
		return order;
	}
	
	public void addOutGoingEdge(Edge e) {
		outGoingEdges.add(e);
	}
	
	public void addIncomingEdge(Edge e) {
		incomingEdges.add(e);
	}
}
