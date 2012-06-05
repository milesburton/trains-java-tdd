package com.mb.twtest.search;

import com.mb.twtest.ImpossibleRouteException;
import com.mb.twtest.domain.City;
import com.mb.twtest.domain.CitySet;
import com.mb.twtest.domain.Trip;

public interface ShortestTripAlgorithm {
	Trip findShortestTrip(CitySet cities, City origin,
			City destination) throws ImpossibleRouteException;
}
