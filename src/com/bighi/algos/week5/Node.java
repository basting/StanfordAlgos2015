package com.bighi.algos.week5;

import java.util.ArrayList;

public class Node implements Comparable<Node> {
    private int value;
    private ArrayList<Edge> relatedEdges = new ArrayList<Edge>();
    private int shortDistanceFromSource = DEFAULT_DIST;
    private boolean processed;
    private int greedyScore;
    
   	public static final int DEFAULT_DIST = 1000000;
    
    @Override
    public String toString() {
        /*return new StringBuilder().append(value).append("(")
                .append(finishingTime).append(")").append("{{")
                .append("OutgoingEdges: ").append(outGoingEdges).append("}}")
                .append("{{").append("IncomingEdges: ").append(incomingEdges)
                .append("}}").toString();
                */
    	return String.valueOf(value);
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

    public Node(int value, int shortDistanceFromSource) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addRelatedEdge(Edge e) {
    	relatedEdges.add(e);
    }
    
    public ArrayList<Edge> getRelatedEdges() {
    	return relatedEdges;
    }
    
    public boolean isProcessed() {
		return processed;
	}

	public void markProcessed() {
		this.processed = true;
	}

	public int getShortDistanceFromSource() {
		return shortDistanceFromSource;
	}

	public void setShortDistanceFromSource(int shortDistanceFromSource) {
		this.shortDistanceFromSource = shortDistanceFromSource;
	}
    
	 public int getGreedyScore() {
			return greedyScore;
		}

		public void setGreedyScore(int greedyScore) {
			this.greedyScore = greedyScore;
		}
	
    @Override
    public int compareTo(Node o) {
        return Integer.compare(o.getValue(), value);
    }
}