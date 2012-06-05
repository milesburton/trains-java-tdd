package com.mb.twtest.domain;

import java.util.LinkedList;
import java.util.Stack;

public class Trip extends LinkedList<Route> {

	private static final long serialVersionUID = 9194022120959282300L;
	private City start = null;
	private City end = null;

	public int getDistance() {
		int distance = 0;
		for (Route p : this) {
			distance += p.getDistance();
		}
		return distance;
	}

	public static Trip createTripFromLinkList(Stack<Route> routeVisited) {
		Trip trip = new Trip();
		trip.addAll(routeVisited);
		trip.setStartCity(routeVisited.get(0).getStart());
		trip.setEndCity(routeVisited.peek().getEnd());
		return trip;
	}

	public int getStops() {
		return this.size();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Departing: " + start.getId() + " for " + end.getId() + "\n");
		for (Route route : this) {
			sb.append("\tRoute: " + route + "\n");
		}
		sb.append("Total Distance: " + getDistance());
		return sb.toString();
	}

	public String getTripAsString() {
		StringBuilder sb = new StringBuilder();
		for (Route route : this) {
			sb.append(route.getStart().getId());
		}
		sb.append(this.getLast().getEnd().getId());

		return sb.toString();
	}

	public void setStartCity(City start) {
		this.start = start;
	}

	public void setEndCity(City end) {
		this.end = end;
	}

}
