package com.mb.twtest.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.mb.twtest.MapGenerator;
import com.mb.twtest.domain.CitySet.CityNotFoundException;

public class CitySetTests {

	CitySet cities;

	@Before
	public void setUp() throws Exception {
		cities = new CitySet();
	}

	@Test
	public void testUniquePaths() throws Exception {

		MapGenerator mapGenerator = new MapGenerator();
		cities = mapGenerator.parseGraphString("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		assertEquals(9, cities.getUniqueRoutes().size());
	}

	@Test
	public void testContainsValidCity() throws Exception {
		String cityId = "test";
		cities.add(new City(cityId));
		City testCity = cities.getCityBy(cityId);

		assertNotNull(testCity);
		assertEquals(cityId, testCity.getId());
	}

	@Test(expected = CityNotFoundException.class)
	public void testContainsInvalidCity() throws Exception {
		String cityId = "test";
		cities.getCityBy(cityId);
	}

	@Test
	public void testAddNewCity() throws Exception {
		String cityId = "test";
		cities.add(new City(cityId));

		assertEquals(1, cities.size());
	}

	@Test
	public void testAddExistingCity() throws Exception {
		String cityId = "test";
		cities.add(new City(cityId));
		assertEquals(1, cities.size());

		cities.add(new City(cityId));
		assertEquals(1, cities.size());
	}
}
