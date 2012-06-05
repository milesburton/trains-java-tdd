package com.mb.twtest;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import com.mb.twtest.base.BaseCartographerTests;
import com.mb.twtest.domain.City;
import com.mb.twtest.domain.Trip;

public class CartographerQuestionTenTests extends BaseCartographerTests {

	@Test
	public void testNumberOfRoutesBetweenCAndCWithADistanceLessThanThirtyShouldBeSeven() {
		City c = citySet.getCityBy("C");
		Stack<Trip> trips = cartographer.getTripsWithMaximumDistance(c, c, 29);

		for (Trip t : trips) {
			System.out.println(t.getTripAsString());
		}

		assertEquals(7, trips.size());
	}

}
