package com.mb.twtest.search.match;

import java.util.Stack;

import com.mb.twtest.domain.City;
import com.mb.twtest.domain.Route;
import com.mb.twtest.search.TripDistanceEvaluator;

public class TripDistanceMustBeLessOrEqualToN extends TripMatcher {
	private TripDistanceEvaluator distanceEvaluator = new TripDistanceEvaluator();
	private ShouldBeDestination destinationMatcher = new ShouldBeDestination();
	private int maximumDistance;

	public TripDistanceMustBeLessOrEqualToN(int maximumDistance) {
		this.maximumDistance = maximumDistance;
	}

	@Override
	public boolean isTripMatch(City destination, Stack<Route> routeVisited, Route pathToTest) {

		Stack<Route> potentialRoute = getPotentialRoute(routeVisited, pathToTest);

		return destinationMatcher.isTripMatch(destination, routeVisited, pathToTest) && distanceEvaluator.isTripDistanceLessOrEqualtoN(potentialRoute, maximumDistance);
	}

}
