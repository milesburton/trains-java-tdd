package com.mb.twtest.search.boundary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import com.mb.twtest.domain.City;
import com.mb.twtest.domain.Route;

public class DistanceMustBeLessOrEqualToNTests {
	TripBoundaryChecker checker;

	@Before
	public void setUp() throws Exception {
		checker = new DistanceMustBeLessOrEqualToN(4);
	}

	@Test
	public void testSetOfRoutesWithDistanceOfThree() {
		Stack<Route> routeWithThreeStops = new Stack<Route>();
		routeWithThreeStops.add(new Route(new City("A"), new City("B"), 1));
		routeWithThreeStops.add(new Route(new City("A"), new City("B"), 1));
		routeWithThreeStops.add(new Route(new City("A"), new City("B"), 1));

		assertTrue(checker.canContinueSearch(routeWithThreeStops));
	}

	@Test
	public void testSetOfRoutesWithDistanceOfFour() {
		Stack<Route> routeWithFourStops = new Stack<Route>();
		routeWithFourStops.add(new Route(new City("A"), new City("B"), 1));
		routeWithFourStops.add(new Route(new City("A"), new City("B"), 1));
		routeWithFourStops.add(new Route(new City("A"), new City("B"), 1));
		routeWithFourStops.add(new Route(new City("A"), new City("B"), 1));

		assertTrue(checker.canContinueSearch(routeWithFourStops));
	}

	@Test
	public void testSetOfRoutesWithDistanceOfFive() {
		Stack<Route> routeWithFiveStops = new Stack<Route>();
		routeWithFiveStops.add(new Route(new City("A"), new City("B"), 2));
		routeWithFiveStops.add(new Route(new City("A"), new City("B"), 0));
		routeWithFiveStops.add(new Route(new City("A"), new City("B"), 1));
		routeWithFiveStops.add(new Route(new City("A"), new City("B"), 1));
		routeWithFiveStops.add(new Route(new City("A"), new City("B"), 1));

		assertFalse(checker.canContinueSearch(routeWithFiveStops));
	}

}
