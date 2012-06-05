package com.mb.twtest.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;

public class CityTests {

	@Test
	public void testCanAddPath() throws Exception {
		City startCity = new City("c");
		City endCity = new City("b");
		Route p = new Route(startCity, endCity, 1);

		startCity.addRoute(p);
		assertNotNull(startCity.getRouteTo(endCity));
	}

	@Test
	public void testGetRoutes() throws Exception {
		City startCity = new City("c");
		City endCity = new City("b");
		Route p = new Route(startCity, endCity, 1);

		startCity.addRoute(p);
		assertEquals(1, startCity.getRoutes().size());
	}

	@Test
	public void testGetNeighbours() throws Exception {
		City startCity = new City("c");
		City endCity = new City("b");
		Route p = new Route(startCity, endCity, 1);

		startCity.addRoute(p);
		assertEquals(startCity.getNeighbours().get(0), endCity);
	}

	@Test(expected = City.NoRouteException.class)
	public void testNoPath() throws Exception {
		City startCity = new City("c");
		City endCity = new City("b");

		startCity.getRouteTo(endCity);
	}

	@Test
	public void testCityComparison() throws Exception {
		City startCity = new City("c");
		City endCity = new City("b");

		assert (startCity.compareTo(endCity) != 0);
	}

	@Test
	public void testCityEqualityEqual() throws Exception {
		City startCity = new City("c");
		assert (startCity.equals(startCity));
	}

	@Test
	public void testCityEqualityNotEqual() throws Exception {
		City startCity = new City("c");
		City endCity = new City("b");
		assert !(startCity.equals(endCity));
	}
}
