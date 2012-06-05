package com.mb.twtest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.mb.twtest.domain.City;
import com.mb.twtest.domain.CitySet;

public class GraphInputStringInterpreterTests {

	private CitySet cities;

	private GraphInputStringInterpreter interpreter;

	@Before
	public void setUp() throws Exception {
		cities = new CitySet();
		interpreter = new GraphInputStringInterpreter();
	}

	@Test
	public void testGenerateValidCity() throws Exception {
		String path = "AB2";

		interpreter.createAndAddCitiesFromPathString(cities, path);

		assertNotNull(cities.getCityBy("A"));
		assertNotNull(cities.getCityBy("B"));
	}

	@Test
	public void testContainsExistingCity() throws Exception {
		String path = "AB2";
		cities.add(new City("A"));

		interpreter.createAndAddCitiesFromPathString(cities, path);

		assertNotNull(cities.getCityBy("A"));
		assertNotNull(cities.getCityBy("B"));
		assertTrue(cities.size() == 2);
	}

	@Test(expected = GraphInputStringInterpreter.InvalidRouteException.class)
	public void testGarbagePath() throws Exception {
		String path = "asdasdasd";

		interpreter.createAndAddCitiesFromPathString(cities, path);
	}

	@Test(expected = GraphInputStringInterpreter.InvalidRouteException.class)
	public void testGarbagePathDistance() throws Exception {
		String path = "AAA";

		interpreter.createAndAddCitiesFromPathString(cities, path);
	}
}
