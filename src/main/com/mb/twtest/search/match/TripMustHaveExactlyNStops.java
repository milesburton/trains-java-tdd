package com.mb.twtest.search.match;

import java.util.Stack;

import com.mb.twtest.domain.City;
import com.mb.twtest.domain.Route;

public class TripMustHaveExactlyNStops extends TripMatcher {

	private ShouldBeDestination destinationMatcher = new ShouldBeDestination();
	private int stops;

	public TripMustHaveExactlyNStops(int stops) {
		this.stops = stops;
	}

	@Override
	public boolean isTripMatch(City destination, Stack<Route> routeVisited, Route routeToTest) {
		Stack<Route> potentialRoute = getPotentialRoute(routeVisited, routeToTest);
		return isExactlyNStops(potentialRoute) && destinationMatcher.isTripMatch(destination, routeVisited, routeToTest);
	}

	private boolean isExactlyNStops(Stack<Route> routeVisited) {
		return routeVisited.size() == stops;
	}

}
