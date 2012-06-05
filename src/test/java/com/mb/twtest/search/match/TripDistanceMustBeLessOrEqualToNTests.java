package com.mb.twtest.search.match;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import com.mb.twtest.domain.City;
import com.mb.twtest.domain.Route;

public class TripDistanceMustBeLessOrEqualToNTests {
	TripMatcher checker;

	@Before
	public void setUp() throws Exception {
		checker = new TripDistanceMustBeLessOrEqualToN(4);
	}

	@Test
	public void testRouteWithDistanceOfThree() {
		Stack<Route> routeWithDistanceOfTwo = new Stack<Route>();
		routeWithDistanceOfTwo.add(new Route(new City("A"), new City("E"), 1));
		routeWithDistanceOfTwo.add(new Route(new City("F"), new City("G"), 1));
		City destination = new City("B");
		Route routeToTest = new Route(new City("H"), destination, 1);

		assertTrue(checker.isTripMatch(destination, routeWithDistanceOfTwo, routeToTest));
	}

	@Test
	public void testRouteWithDistanceOfFour() {
		Stack<Route> routeWithDistanceOfThree = new Stack<Route>();
		routeWithDistanceOfThree.add(new Route(new City("A"), new City("E"), 1));
		routeWithDistanceOfThree.add(new Route(new City("F"), new City("G"), 1));
		routeWithDistanceOfThree.add(new Route(new City("H"), new City("I"), 1));
		City destination = new City("B");
		Route routeToTest = new Route(new City("H"), destination, 1);

		assertTrue(checker.isTripMatch(destination, routeWithDistanceOfThree, routeToTest));
	}

	@Test
	public void testRouteWithDistanceOfFive() {
		Stack<Route> routeWithDistanceOfFour = new Stack<Route>();
		routeWithDistanceOfFour.add(new Route(new City("A"), new City("E"), 1));
		routeWithDistanceOfFour.add(new Route(new City("F"), new City("G"), 2));
		routeWithDistanceOfFour.add(new Route(new City("H"), new City("I"), 1));
		City destination = new City("B");
		Route routeToTest = new Route(new City("H"), destination, 1);

		assertFalse(checker.isTripMatch(destination, routeWithDistanceOfFour, routeToTest));
	}

}
