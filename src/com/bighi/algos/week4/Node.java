package com.bighi.algos.week4;

import java.util.ArrayList;

public class Node implements Comparable<Node>{
	private int value;
	private ArrayList<Edge> outGoingEdges = new ArrayList<Edge>();
	private ArrayList<Edge> incomingEdges = new ArrayList<Edge>();
	private int finishingTime;
	private boolean explored;
	private Node leader;
	
	@Override
	public String toString() {
		/*return new StringBuilder().append(value).append("(")
				.append(finishingTime)
				.append(")")
				.toString();*/
		return new StringBuilder().append(value)
				.append("(")
				.append(finishingTime)
				.append(")")
				.append("{{")
				.append("OutgoingEdges: ")
				.append(outGoingEdges)
				.append("}}")
				.append("{{")
				.append("IncomingEdges: ")
				.append(incomingEdges)
				.append("}}")
				.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		Node n = (Node) obj;
		return value == n.value;
	}
	
	@Override
	public int hashCode() {
		return new Integer(value).hashCode();
	}
	
	public Node(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void setFinishingTime(int finishingTime) {
		this.finishingTime = finishingTime;
	}
	
	public int getFinishingTime() {
		return finishingTime;
	}
	
	public void addOutgoingEdge(Edge e) {
		outGoingEdges.add(e);
	}
	
	public void removeOutgoingEdge(Edge e) {
		outGoingEdges.remove(e);
	}
	
	public void addIncomingEdge(Edge e) {
		incomingEdges.add(e);
	}
	
	public void removeIncomingEdge(Edge e) {
		incomingEdges.remove(e);
	}

	public ArrayList<Edge> getOutGoingEdges() {
		return outGoingEdges;
	}
	
	public boolean isExplored() {
		return explored;
	}

	public void markExplored() {
		this.explored = true;
	}
	
	public void unmarkExplored() {
		this.explored = false;
	}
	
	public Node getLeader() {
		return leader;
	}

	public void setLeader(Node leader) {
		this.leader = leader;
	}
	
	@Override
	public int compareTo(Node o) {
		return Integer.compare(o.getValue(), value);		
	}
}
