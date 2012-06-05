package com.mb.twtest.search;

import java.util.Stack;

import com.mb.twtest.domain.City;
import com.mb.twtest.domain.CitySet;
import com.mb.twtest.domain.Route;
import com.mb.twtest.domain.Trip;
import com.mb.twtest.search.boundary.TripBoundaryChecker;
import com.mb.twtest.search.match.TripMatcher;

public class DepthFirstSearch extends GraphSearchAlgorithm {

	private TripBoundaryChecker boundaryCheck;
	private TripMatcher tripMatcher;

	@Override
	public Stack<Trip> findRoutesToDestination(CitySet cities, City origin, City destination) {
		Stack<Route> visitedRouteList = new Stack<Route>();
		setSuccessfulTripList(new Stack<Trip>());
		setStartCity(origin);
		setEndCity(destination);
		searchForDestination(visitedRouteList);

		return getSuccessfulTripList();
	}

	private void searchForDestination(Stack<Route> visitedRouteList) {
		if (!boundaryCheck.canContinueSearch(visitedRouteList))
			return;

		City currentStop = getCurrentStop(visitedRouteList);

		checkIfNextRouteIsDestination(visitedRouteList, currentStop);
		continueSearchFromNextOpenRoute(visitedRouteList, currentStop);
	}

	private void checkIfNextRouteIsDestination(Stack<Route> visitedRouteList, City currentStop) {
		for (Route route : getOpenRoutes(visitedRouteList, currentStop)) {
			if (tripMatcher.isTripMatch(getEndCity(), visitedRouteList, route)) {
				recordTrip(route, visitedRouteList);
				break;
			}
		}
	}

	private void continueSearchFromNextOpenRoute(Stack<Route> visitedRouteList, City currentStop) {
		for (Route possibleRoute : getOpenRoutes(visitedRouteList, currentStop)) {
			visitedRouteList.add(possibleRoute);
			searchForDestination(visitedRouteList);
			visitedRouteList.pop();
		}
	}

	private void recordTrip(Route route, Stack<Route> visitedRouteList) {
		visitedRouteList.push(route);
		recordTrip(visitedRouteList);
		visitedRouteList.pop();
	}

	private Stack<Route> getOpenRoutes(Stack<Route> visitedCities, City currentCity) {
		Stack<Route> openPaths = new Stack<Route>();
		openPaths.addAll(currentCity.getRoutes());
		// openPaths.removeAll(visitedCities);
		return openPaths;
	}

	public void setBoundaryCheck(TripBoundaryChecker boundaryCheck) {
		this.boundaryCheck = boundaryCheck;
	}

	public void setTripMatcher(TripMatcher tripMatcher) {
		this.tripMatcher = tripMatcher;
	}
}
