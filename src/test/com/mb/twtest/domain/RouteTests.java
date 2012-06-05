package com.mb.twtest.domain;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class RouteTests {
	@Test
	public void testCheckEqualityTrue() throws Exception {
		City startCity = new City("c");
		City endCity = new City("b");
		Route p = new Route(startCity, endCity, 1);
		assert (p.equals(p));
	}

	@Test
	public void testCheckEqualityFalse() throws Exception {
		City startCity = new City("c");
		City endCity = new City("b");
		Route p = new Route(startCity, endCity, 1);
		Route p1 = new Route(endCity, startCity, 1);
		Route p2 = new Route(startCity, endCity, 3);
		assert (p.equals(p));
		assertFalse(p.equals(p1));
		assertFalse(p.equals(p2));
	}

}
