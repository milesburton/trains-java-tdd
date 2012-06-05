package com.mb.twtest.search;

import java.util.Stack;

import com.mb.twtest.domain.Route;
import com.mb.twtest.domain.Trip;

public class TripDistanceEvaluator {
	public boolean isTripDistanceLessOrEqualtoN(Stack<Route> routeVisited, int maximumDistance) {
		Trip trip = Trip.createTripFromLinkList(routeVisited);
		return trip.getDistance() <= maximumDistance;
	}
}
