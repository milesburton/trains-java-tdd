package com.mb.twtest.search;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.mb.twtest.ImpossibleRouteException;
import com.mb.twtest.MapGenerator;
import com.mb.twtest.domain.City;
import com.mb.twtest.domain.CitySet;
import com.mb.twtest.domain.Trip;

public class DijkstrasShortestPathAlgorithmTests {
	private CitySet citySet;
	private DijkstraShortestPathAlgorithm algorithm;

	@Before
	public void setup() {
		MapGenerator mapGenerator = new MapGenerator();
		citySet = mapGenerator.parseGraphString("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		algorithm = new DijkstraShortestPathAlgorithm();
	}

	@Test
	public void testShortestDistanceBetweenAAndC() {
		City a = citySet.getCityBy("A");
		City c = citySet.getCityBy("C");
		Trip trip = algorithm.findShortestTrip(citySet, a, c);

		assertEquals(9, trip.getDistance());
	}

	@Test(expected = ImpossibleRouteException.class)
	public void testShortestDistanceBetweenDAndC() {
		City a = citySet.getCityBy("C");
		City c = citySet.getCityBy("A");
		algorithm.findShortestTrip(citySet, a, c);
	}

	@Test
	public void testShortestDistanceBetweenAAndD() {
		City a = citySet.getCityBy("A");
		City d = citySet.getCityBy("D");
		Trip trip = algorithm.findShortestTrip(citySet, a, d);

		assertEquals(5, trip.getDistance());
	}

	@Test
	public void testShortestDistanceBetweenAAndE() {
		City a = citySet.getCityBy("A");
		City e = citySet.getCityBy("E");
		Trip trip = algorithm.findShortestTrip(citySet, a, e);

		assertEquals(7, trip.getDistance());
	}

	@Test
	public void testShortestDistanceBetweenBAndE() {
		City b = citySet.getCityBy("B");
		City e = citySet.getCityBy("E");
		Trip trip = algorithm.findShortestTrip(citySet, b, e);

		assertEquals(6, trip.getDistance());
	}

	@Test
	public void testShortestDistanceBetweenBAndB() {
		City b = citySet.getCityBy("B");
		Trip trip = algorithm.findShortestTrip(citySet, b, b);

		assertEquals(9, trip.getDistance());
	}

	@Test
	public void testShortestDistanceBetweenEAndE() {
		City e = citySet.getCityBy("E");
		Trip trip = algorithm.findShortestTrip(citySet, e, e);

		assertEquals(9, trip.getDistance());
	}

	@Test
	public void testShortestDistanceBetweenDAndD() {
		City d = citySet.getCityBy("D");
		Trip trip = algorithm.findShortestTrip(citySet, d, d);

		assertEquals(16, trip.getDistance());
	}
}
