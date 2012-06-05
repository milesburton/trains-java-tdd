package com.mb.twtest.search.match;

import java.util.Stack;

import com.mb.twtest.domain.City;
import com.mb.twtest.domain.Route;

public abstract class TripMatcher {
	public abstract boolean isTripMatch(City destination, Stack<Route> routeVisited, Route pathToTest);

	protected Stack<Route> getPotentialRoute(Stack<Route> routeVisited, Route pathToTest) {
		Stack<Route> potentialRoute = new Stack<Route>();
		potentialRoute.addAll(routeVisited);
		potentialRoute.add(pathToTest);
		return potentialRoute;
	}
}
