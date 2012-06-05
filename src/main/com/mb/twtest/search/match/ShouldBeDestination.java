package com.mb.twtest.search.match;

import java.util.Stack;

import com.mb.twtest.domain.City;
import com.mb.twtest.domain.Route;

public class ShouldBeDestination extends TripMatcher {

	@Override
	public boolean isTripMatch(City destination, Stack<Route> routeVisited, Route pathToTest) {
		Stack<Route> potentialRoute = getPotentialRoute(routeVisited, pathToTest);
		return isCityMatch(destination, potentialRoute);
	}

	private boolean isCityMatch(City destination, Stack<Route> routeVisited) {
		if (routeVisited.size() > 0) {
			City lastCity = getLastCity(routeVisited);
			return destination.equals(lastCity);

		} else {
			return false;
		}
	}

	private City getLastCity(Stack<Route> routeVisited) {
		return routeVisited.peek().getEnd();
	}

}
