package com.mb.twtest.domain;

public class Route implements Comparable<Route> {
	private City start;
	private City end;
	private int distance;

	public Route(City startCity, City endCity, int distance) {
		start = startCity;
		end = endCity;
		this.distance = distance;
	}

	@Override
	public boolean equals(Object anotherObject) {
		Route path = (Route) anotherObject;
		return start.equals(path.getStart()) && end.equals(path.getEnd()) && distance == path.getDistance();
	}

	@Override
	public int compareTo(Route o) {
		if (this.equals(o))
			return 0;
		return -1;
	}

	@Override
	public String toString() {
		return "Leaving " + start.getId() + " to " + end.getId() + " distance " + distance;
	}

	public City getStart() {
		return start;
	}

	public City getEnd() {
		return end;
	}

	public int getDistance() {
		return distance;
	}

}
