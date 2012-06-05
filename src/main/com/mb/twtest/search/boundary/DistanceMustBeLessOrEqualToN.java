package com.mb.twtest.search.boundary;

import java.util.Stack;

import com.mb.twtest.domain.Route;
import com.mb.twtest.search.TripDistanceEvaluator;

public class DistanceMustBeLessOrEqualToN implements TripBoundaryChecker {

	private int maximumDistance;
	private TripDistanceEvaluator distanceEvaluator = new TripDistanceEvaluator();

	public DistanceMustBeLessOrEqualToN(int maximumDistance) {
		this.maximumDistance = maximumDistance;
	}

	@Override
	public boolean canContinueSearch(Stack<Route> routeVisited) {
		if (routeVisited.size() == 0)
			return true;

		return distanceEvaluator.isTripDistanceLessOrEqualtoN(routeVisited, maximumDistance);
	}

}
