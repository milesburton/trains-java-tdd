package com.mb.twtest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.mb.twtest.base.BaseCartographerTests;
import com.mb.twtest.domain.Route;
import com.mb.twtest.domain.Trip;

public class CartographerQuestionSix extends BaseCartographerTests {

	@Test
	public void testNumberOfTripsBetweenCWith3OrLessStopsShouldBeTwo() {
		List<Trip> trips = getTripsBetweenCWithMaximumOfThreeStops();

		assertEquals(2, trips.size());
	}

	@Test
	public void testTripCEBCHasADistanceOf9() {
		List<Trip> trips = getTripsBetweenCWithMaximumOfThreeStops();
		Trip secondTrip = trips.get(1);

		assertEquals(9, secondTrip.getDistance());
	}

	@Test
	public void testTripCDCHasADistanceOf16() {
		List<Trip> trips = getTripsBetweenCWithMaximumOfThreeStops();
		Trip firstTrip = trips.get(0);

		assertEquals(16, firstTrip.getDistance());
	}

	@Test
	public void testTripCEDCHasThreeStops() {
		List<Trip> trips = getTripsBetweenCWithMaximumOfThreeStops();
		Trip secondTrip = trips.get(1);

		assertEquals(3, secondTrip.getStops());
	}

	@Test
	public void testFirstRouteHasCorrectStops() {
		List<Trip> trips = getTripsBetweenCWithMaximumOfThreeStops();

		Trip firstRoute = trips.get(0);
		Route firstPath = firstRoute.pop();

		// First path between C and D
		assertEquals(citySet.getCityBy("C"), firstPath.getStart());
		assertEquals(citySet.getCityBy("D"), firstPath.getEnd());
		assertEquals(8, firstPath.getDistance());

		// Second/final path between D and C
		Route finalPath = firstRoute.pop();
		assertEquals(citySet.getCityBy("D"), finalPath.getStart());
		assertEquals(citySet.getCityBy("C"), finalPath.getEnd());
		assertEquals(8, firstPath.getDistance());
	}

	@Test
	public void testTripCEBCHasCorrectStops() {
		List<Trip> trips = getTripsBetweenCWithMaximumOfThreeStops();

		Trip secondTrip = trips.get(1);

		// First path between C and D
		Route firstRoute = secondTrip.pop();
		assertEquals(citySet.getCityBy("C"), firstRoute.getStart());
		assertEquals(citySet.getCityBy("E"), firstRoute.getEnd());
		assertEquals(2, firstRoute.getDistance());

		// Second path between D and E
		Route secondRoute = secondTrip.pop();
		assertEquals(citySet.getCityBy("E"), secondRoute.getStart());
		assertEquals(citySet.getCityBy("B"), secondRoute.getEnd());
		assertEquals(3, secondRoute.getDistance());

		// third path between E and B
		Route thirdRoute = secondTrip.pop();
		assertEquals(citySet.getCityBy("B"), thirdRoute.getStart());
		assertEquals(citySet.getCityBy("C"), thirdRoute.getEnd());
		assertEquals(4, thirdRoute.getDistance());
	}

}
