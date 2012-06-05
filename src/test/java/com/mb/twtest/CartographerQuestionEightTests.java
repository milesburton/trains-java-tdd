package com.mb.twtest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.mb.twtest.domain.City;
import com.mb.twtest.domain.CitySet;
import com.mb.twtest.domain.Trip;

public class CartographerQuestionEightTests {
	private CitySet citySet;
	private Cartographer cartographer;

	@Before
	public void setUp() throws Exception {

		MapGenerator mapGenerator = new MapGenerator();
		citySet = mapGenerator
				.parseGraphString("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		cartographer = new Cartographer();
		cartographer.setCities(citySet);
	}

	@Test
	public void testGetShortestRouteBetweenAAndC() {
		City a = citySet.getCityBy("A");
		City c = citySet.getCityBy("C");
		Trip trip = cartographer.getShortestTripBetween(a, c);

		assertEquals(9, trip.getDistance());
	}
}
