package com.mb.twtest.domain;

import java.util.ArrayList;
import java.util.List;

public class City implements Comparable<City> {
	private List<Route> paths = new ArrayList<Route>();
	private final String id;

	public City(String id) {
		this.id = id;
	}

	public Route getRouteTo(City city) {
		Route actualPath = null;
		for (Route possiblePath : paths) {
			if (possiblePath.getEnd().equals(city)) {
				actualPath = possiblePath;
			}
		}

		if (actualPath == null)
			throw new NoRouteException();

		return actualPath;
	}

	@Override
	public String toString() {
		return "ID: " + id + " Paths: " + paths.size();
	}

	@Override
	public int compareTo(City arg0) {
		return getId().compareTo(arg0.getId());
	}

	@Override
	public boolean equals(Object city) {
		return getId().equals(((City) city).getId());
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	public void addRoute(Route path) {
		paths.add(path);
	}

	public List<Route> getRoutes() {
		return paths;
	}

	public List<City> getNeighbours() {
		List<City> neighbours = new ArrayList<City>();
		for (Route route : getRoutes()) {
			neighbours.add(route.getEnd());
		}

		return neighbours;
	}

	public String getId() {
		return id;
	}

	public static class NoRouteException extends RuntimeException {

		private static final long serialVersionUID = 2706315388318501533L;

	}
}
