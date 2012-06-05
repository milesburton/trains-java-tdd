package com.mb.twtest.search.boundary;

import java.util.Stack;

import com.mb.twtest.domain.Route;

public class MustContainUpToAndIncludingNStops implements TripBoundaryChecker {
	private int maximumStopsPossible;

	public MustContainUpToAndIncludingNStops(int maximumStopsPossible) {
		this.maximumStopsPossible = maximumStopsPossible;
	}

	@Override
	public boolean canContinueSearch(Stack<Route> routeVisited) {
		return isLessOrEqualToMaximumStopsPossible(routeVisited);
	}

	private boolean isLessOrEqualToMaximumStopsPossible(Stack<Route> routeVisited) {
		return routeVisited.size() <= maximumStopsPossible;
	}

}
