package com.mb.twtest.search;

import java.util.Stack;

import com.mb.twtest.domain.City;
import com.mb.twtest.domain.Route;
import com.mb.twtest.domain.Trip;

public abstract class GraphSearchAlgorithm implements SearchAlgorithm {
	private City startCity;
	private City endCity;
	private Stack<Trip> successfulTripList;

	protected City getCurrentStop(Stack<Route> routeVisited) {
		if (routeVisited.size() == 0) {
			return startCity;
		} else {
			return routeVisited.peek().getEnd();
		}
	}

	protected Trip recordTrip(Stack<Route> visitedCities) {
		Trip successfulTrip = createTrip();
		successfulTrip.addAll(visitedCities);
		successfulTripList.add(successfulTrip);
		return successfulTrip;
	}

	protected Trip createTrip() {
		Trip trip = new Trip();
		trip.setStartCity(startCity);
		trip.setEndCity(endCity);
		return trip;
	}

	protected City getStartCity() {
		return startCity;
	}

	protected void setStartCity(City startCity) {
		this.startCity = startCity;
	}

	protected City getEndCity() {
		return endCity;
	}

	protected void setEndCity(City endCity) {
		this.endCity = endCity;
	}

	protected Stack<Trip> getSuccessfulTripList() {
		return successfulTripList;
	}

	protected void setSuccessfulTripList(Stack<Trip> successfulTripList) {
		this.successfulTripList = successfulTripList;
	}

}
