package com.mb.twtest.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.mb.twtest.ImpossibleRouteException;
import com.mb.twtest.MapGenerator;

public class PossibleTripQuestionOneThroughFiveTests {
	private CitySet citySet;

	@Before
	public void setUp() throws Exception {

		MapGenerator mapGenerator = new MapGenerator();
		citySet = mapGenerator
				.parseGraphString("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");

	}

	@Test
	public void testDistanceBetweenABC() throws Exception {
		PossibleTrip possibleRoute = new PossibleTrip();
		possibleRoute.add(citySet.getCityBy("A"));
		possibleRoute.add(citySet.getCityBy("B"));
		possibleRoute.add(citySet.getCityBy("C"));

		int distance = possibleRoute.getAsTrip().getDistance();

		assertEquals(9, distance);
	}

	@Test
	public void testDistanceBetweenAD() throws Exception {
		PossibleTrip possibleRoute = new PossibleTrip();
		possibleRoute.add(citySet.getCityBy("A"));
		possibleRoute.add(citySet.getCityBy("D"));

		int distance = possibleRoute.getAsTrip().getDistance();

		assertEquals(5, distance);
	}

	@Test
	public void testDistanceBetweenADC() throws Exception {
		PossibleTrip possibleRoute = new PossibleTrip();
		possibleRoute.add(citySet.getCityBy("A"));
		possibleRoute.add(citySet.getCityBy("D"));
		possibleRoute.add(citySet.getCityBy("C"));

		int distance = possibleRoute.getAsTrip().getDistance();

		assertEquals(13, distance);
	}

	@Test
	public void testDistanceBetweenAEBCD() throws Exception {
		PossibleTrip possibleRoute = new PossibleTrip();
		possibleRoute.add(citySet.getCityBy("A"));
		possibleRoute.add(citySet.getCityBy("E"));
		possibleRoute.add(citySet.getCityBy("B"));
		possibleRoute.add(citySet.getCityBy("C"));
		possibleRoute.add(citySet.getCityBy("D"));

		int distance = possibleRoute.getAsTrip().getDistance();

		assertEquals(22, distance);
	}

	@Test(expected = ImpossibleRouteException.class)
	public void testDistanceBetweenAED() throws Exception {
		PossibleTrip possibleRoute = new PossibleTrip();
		possibleRoute.add(citySet.getCityBy("A"));
		possibleRoute.add(citySet.getCityBy("E"));
		possibleRoute.add(citySet.getCityBy("D"));

		possibleRoute.getAsTrip().getDistance();
	}
}
