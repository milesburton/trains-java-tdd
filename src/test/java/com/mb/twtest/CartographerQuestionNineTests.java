package com.mb.twtest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mb.twtest.base.BaseCartographerTests;
import com.mb.twtest.domain.City;
import com.mb.twtest.domain.Trip;

public class CartographerQuestionNineTests extends BaseCartographerTests {

	@Test
	public void questionNineShortestRouteBetweenBAndB() {
		City originAndDestination = citySet.getCityBy("B");
		Trip trip = cartographer.getShortestTripBetween(originAndDestination, originAndDestination);
		assertEquals(9, trip.getDistance());
	}
}
