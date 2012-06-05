package com.mb.twtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.mb.twtest.MapGenerator.InvalidInputException;
import com.mb.twtest.domain.City;
import com.mb.twtest.domain.CitySet;

public class MapGeneratorTests {

	MapGenerator gen = null;

	@Before
	public void setUp() throws Exception {
		gen = new MapGenerator();
	}

	@Test(expected = InvalidInputException.class)
	public void testEmptyInput() throws Exception {
		gen.parseGraphString("");
	}

	@Test(expected = InvalidInputException.class)
	public void testGarbageInput() throws Exception {
		gen.parseGraphString("asdasdsadsad");
	}

	@Test
	public void testValidCitiesAreInSet() throws Exception {
		CitySet set = gen.parseGraphString("AB2, BA1");

		assertNotNull(set.getCityBy("A"));
		assertNotNull(set.getCityBy("B"));
	}

	@Test
	public void testCityHasCorrectPaths() throws Exception {
		CitySet set = gen.parseGraphString("AB2, BA1");

		City a = set.getCityBy("A");
		City b = set.getCityBy("B");

		assertEquals(1, a.getRoutes().size());
		assertEquals(1, b.getRoutes().size());

		assertNotNull(a.getRouteTo(b));
		assertNotNull(b.getRouteTo(a));
	}

	@Test
	public void testCityHasCorrectPathsWeights() throws Exception {
		CitySet set = gen.parseGraphString("AB2, BA1");

		City a = set.getCityBy("A");
		City b = set.getCityBy("B");

		assertEquals(2, a.getRouteTo(b).getDistance());
		assertEquals(1, b.getRouteTo(a).getDistance());

	}
}
