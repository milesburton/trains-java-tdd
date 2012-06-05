package com.mb.twtest.domain;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class TripTests {

	private Trip trip;

	@Before
	public void setup() {
		trip = new Trip();
		trip.add(new Route(new City("A"), new City("B"), 1));
		trip.add(new Route(new City("C"), new City("D"), 2));
		trip.add(new Route(new City("D"), new City("E"), 3));
	}

	@Test
	public void testDistanceAsString() throws Exception {
		assertEquals("ACDE", trip.getTripAsString());
	}

	@Test
	public void testTripSize() throws Exception {
		assertEquals(3, trip.getStops());
	}

	@Test
	public void testDistance() throws Exception {
		assertEquals(6, trip.getDistance());
	}

	@Test
	public void testCreateTripFromLinkedList() {
		Stack<Route> routeList = new Stack<Route>();
		routeList.add(new Route(new City("A"), new City("B"), 1));
		routeList.add(new Route(new City("B"), new City("C"), 1));
		routeList.add(new Route(new City("D"), new City("E"), 1));
		Trip trip = Trip.createTripFromLinkList(routeList);
		assertEquals(3, trip.size());
		assertEquals(routeList.get(0), trip.get(0));
		assertEquals(routeList.peek(), trip.get(2));
	}
}
