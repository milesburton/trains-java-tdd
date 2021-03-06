package com.mb.twtest.search.match;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import com.mb.twtest.domain.City;
import com.mb.twtest.domain.Route;

public class ShouldBeDestinationTests {
	TripMatcher tripMatcher;

	@Before
	public void setUp() throws Exception {
		ShouldBeDestination mustHaveExactlyFourStops = new ShouldBeDestination();
		tripMatcher = mustHaveExactlyFourStops;
	}

	@Test
	public void testTripWithLastRouteIsDestination() {
		Stack<Route> routeWithFourStops = new Stack<Route>();
		City originAndDestinationCity = new City("A");
		routeWithFourStops.add(new Route(originAndDestinationCity, new City("B"), 1));
		routeWithFourStops.add(new Route(new City("B"), new City("C"), 1));
		routeWithFourStops.add(new Route(new City("C"), new City("D"), 1));
		Route routeToTest = new Route(new City("D"), originAndDestinationCity, 1);

		assertTrue(tripMatcher.isTripMatch(originAndDestinationCity, routeWithFourStops, routeToTest));
	}

	@Test
	public void testTripLastRouteIsNotDestination() {
		Stack<Route> routeWithThreeStops = new Stack<Route>();
		City originAndDestinationCity = new City("A");
		routeWithThreeStops.add(new Route(originAndDestinationCity, new City("B"), 1));
		routeWithThreeStops.add(new Route(new City("B"), new City("D"), 1));

		Route routeToTest = new Route(new City("D"), new City("B"), 1);

		assertFalse(tripMatcher.isTripMatch(originAndDestinationCity, routeWithThreeStops, routeToTest));
	}
}
