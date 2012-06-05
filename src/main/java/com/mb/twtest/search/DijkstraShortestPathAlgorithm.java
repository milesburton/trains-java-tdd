package com.mb.twtest.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mb.twtest.ImpossibleRouteException;
import com.mb.twtest.domain.City;
import com.mb.twtest.domain.CitySet;
import com.mb.twtest.domain.Route;
import com.mb.twtest.domain.Trip;

/**
 * Ref: http://www.vogella.de/articles/JavaAlgorithmsDijkstra/article.html
 */
public class DijkstraShortestPathAlgorithm implements ShortestTripAlgorithm {
	private List<City> knownCityList;
	private List<Route> knownUniqueRouteList;
	private Set<City> visitedCitySet;
	private Set<City> unvisitedCities;
	private Map<City, Route> predecessors;
	private Map<City, Integer> distanceFromOrigin;

	private void initaliseCollections(CitySet cities) {
		knownCityList = new ArrayList<City>();
		knownUniqueRouteList = new ArrayList<Route>();

		visitedCitySet = new HashSet<City>();
		unvisitedCities = new HashSet<City>();
		distanceFromOrigin = new HashMap<City, Integer>();
		predecessors = new HashMap<City, Route>();

		knownCityList.addAll(cities);
		knownUniqueRouteList.addAll(cities.getUniqueRoutes());
	}

	@Override
	public Trip findShortestTrip(CitySet cities, City origin, City destination) {
		initaliseCollections(cities);
		City firstCity = seedFirstCity(origin, destination);
		visitAllCitiesAndRecordShortestRouteFrom();
		return getShortestTripTo(origin, firstCity, destination);
	}

	private void visitAllCitiesAndRecordShortestRouteFrom() {
		while (unvisitedCities.size() > 0) {
			City city = getCityWithShortestDistanceFromOriginWithin(unvisitedCities);
			visitCityAndCheckNeighbours(city);
		}
	}

	private void visitCityAndCheckNeighbours(City currentStop) {
		recordCityVisit(currentStop);
		List<City> neighbours = getUnvisitedNeighbours(currentStop);
		for (City neighbour : neighbours) {
			int distanceFromOriginToNeighbour = getDistanceFromOriginVia(currentStop, neighbour);
			if (isRouteViaNeighbourCloserToOrigin(neighbour, distanceFromOriginToNeighbour)) {
				recordBestDistanceToTarget(currentStop, neighbour);
			}
		}

	}

	private void recordBestDistanceToTarget(City currentStop, City target) {
		int distanceFromOriginToNeighbour = getDistanceFromOriginVia(currentStop, target);
		distanceFromOrigin.put(target, distanceFromOriginToNeighbour);
		predecessors.put(target, currentStop.getRouteTo(target));
		unvisitedCities.add(target);
	}

	private boolean isRouteViaNeighbourCloserToOrigin(City target, int potentialDistanceToTarget) {
		int bestKnownDistanceToTarget = getKnownShortestDistanceIfExists(target);
		return bestKnownDistanceToTarget > potentialDistanceToTarget;
	}

	private int getDistanceFromOriginVia(City currentStop, City target) {
		return getKnownShortestDistanceIfExists(currentStop) + getDistanceBetween(currentStop, target);
	}

	private int getDistanceBetween(City a, City b) {
		return a.getRouteTo(b).getDistance();
	}

	private List<City> getUnvisitedNeighbours(City city) {
		List<City> neighbors = new ArrayList<City>();

		for (Route route : city.getRoutes()) {
			if (!haveVisited(route.getEnd())) {
				neighbors.add(route.getEnd());
			}
		}
		return neighbors;
	}

	private City getCityWithShortestDistanceFromOriginWithin(Collection<City> citySet) {
		List<City> cityListOrderedByDistanceFromOrigin = new ArrayList<City>();
		cityListOrderedByDistanceFromOrigin.addAll(citySet);

		Comparator<City> distanceFromOriginComparator = createDistanceFromOriginComparator();
		Collections.sort(cityListOrderedByDistanceFromOrigin, distanceFromOriginComparator);

		City cityWithShortestDistanceFromOrigin = cityListOrderedByDistanceFromOrigin.get(0);
		return cityWithShortestDistanceFromOrigin;
	}

	private boolean haveVisited(City city) {
		return visitedCitySet.contains(city);

	}

	private int getKnownShortestDistanceIfExists(City destination) {
		Integer d = distanceFromOrigin.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	private Trip getShortestTripTo(City origin, City firstCity, City destination) {
		Trip trip = new Trip();

		City target = destination;
		if (!predecessors.containsKey(target)) {
			throw new ImpossibleRouteException();
		}

		while (predecessors.containsKey(target)) {
			Route route = predecessors.get(target);
			trip.add(route);
			target = route.getStart();
		}

		if (origin == destination) {
			trip.add(origin.getRouteTo(firstCity));
		}

		return trip;
	}

	private Comparator<City> createDistanceFromOriginComparator() {
		Comparator<City> distanceFromOriginComparator = new Comparator<City>() {
			@Override
			public int compare(City cityA, City cityB) {
				return getKnownShortestDistanceIfExists(cityA) - getKnownShortestDistanceIfExists(cityB);
			}
		};

		return distanceFromOriginComparator;
	}

	private City seedFirstCity(City origin, City destination) {
		City firstCity = null;
		if (origin == destination) {
			firstCity = getCityWithShortestDistanceFromOriginWithin(origin.getNeighbours());
		} else {
			firstCity = origin;
		}

		distanceFromOrigin.put(firstCity, 0);
		unvisitedCities.add(firstCity);

		return firstCity;
	}

	private void recordCityVisit(City city) {
		visitedCitySet.add(city);
		unvisitedCities.remove(city);
	}
}
