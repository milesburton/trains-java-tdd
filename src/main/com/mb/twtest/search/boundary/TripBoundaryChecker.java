package com.mb.twtest.search.boundary;

import java.util.Stack;

import com.mb.twtest.domain.Route;

public interface TripBoundaryChecker {
	boolean canContinueSearch(Stack<Route> routeVisited);
}
