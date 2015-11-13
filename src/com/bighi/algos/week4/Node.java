package com.bighi.algos.week4;

import java.util.ArrayList;

public class Node {
	public int value;
	public ArrayList<Edge> outGoingEdges = new ArrayList<Edge>();
	public ArrayList<Edge> incomingEdges = new ArrayList<Edge>();
	public int order;
}
