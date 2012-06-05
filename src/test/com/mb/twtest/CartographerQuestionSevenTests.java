package com.mb.twtest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.mb.twtest.base.BaseCartographerTests;
import com.mb.twtest.domain.City;
import com.mb.twtest.domain.Trip;

public class CartographerQuestionSevenTests extends BaseCartographerTests {

	@Test
	public void testNumberOfTripsWhichStartAtAAndEndAtCWithExactlyFourStops() {
		List<Trip> trips = getTripAToCWithExactlyFourStops();

		assertEquals(3, trips.size());
	}

	@Test
	public void testAToCWithFourStopsHasRouteABCD() {
		List<Trip> trips = getTripAToCWithExactlyFourStops();

		Trip abcd = trips.get(0);
		assertEquals(abcd.getTripAsString(), "ABCDC");
	}

	@Test
	public void testAToCWithFourStopsHasRouteADCD() {
		List<Trip> trips = getTripAToCWithExactlyFourStops();

		Trip abcd = trips.get(1);
		assertEquals(abcd.getTripAsString(), "ADCDC");
	}

	@Test
	public void testAToCWithFourStopsHasRouteADEB() {
		List<Trip> trips = getTripAToCWithExactlyFourStops();
		Trip adeb = trips.get(2);
		assertEquals(adeb.getTripAsString(), "ADEBC");
	}

	List<Trip> getTripAToCWithExactlyFourStops() {
		City a = citySet.getCityBy("A");
		City c = citySet.getCityBy("C");
		List<Trip> routes = cartographer.getTripsWithExactlyStops(a, c, 4);
		return routes;
	}
}
