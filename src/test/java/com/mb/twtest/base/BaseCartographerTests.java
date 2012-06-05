package com.mb.twtest.base;

import java.util.List;

import org.junit.Before;

import com.mb.twtest.Cartographer;
import com.mb.twtest.MapGenerator;
import com.mb.twtest.domain.City;
import com.mb.twtest.domain.CitySet;
import com.mb.twtest.domain.Trip;

public abstract class BaseCartographerTests {
	protected CitySet citySet;
	protected Cartographer cartographer;

	@Before
	public void setUp() throws Exception {

		MapGenerator mapGenerator = new MapGenerator();
		citySet = mapGenerator.parseGraphString("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		cartographer = new Cartographer();
		cartographer.setCities(citySet);
	}

	protected List<Trip> getTripsBetweenCWithMaximumOfThreeStops() {
		City c = citySet.getCityBy("C");
		List<Trip> trips = cartographer.getTripsWithMaximumStopsOf(c, c, 3);
		return trips;
	}
}
