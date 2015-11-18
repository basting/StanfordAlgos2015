package com.bighi.algos.week5;

import java.util.ArrayList;

public class Node implements Comparable<Node> {
    private int value;
    private ArrayList<Edge> relatedEdges = new ArrayList<Edge>();
    
    /*@Override
    public String toString() {
        return new StringBuilder().append(value).append("(")
                .append(finishingTime).append(")").append("{{")
                .append("OutgoingEdges: ").append(outGoingEdges).append("}}")
                .append("{{").append("IncomingEdges: ").append(incomingEdges)
                .append("}}").toString();
    }*/

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

    public void addRelatedEdge(Edge e) {
    	relatedEdges.add(e);
    }
    
    @Override
    public int compareTo(Node o) {
        return Integer.compare(o.getValue(), value);
    }
}