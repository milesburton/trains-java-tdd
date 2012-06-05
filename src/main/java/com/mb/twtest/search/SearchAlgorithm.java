package com.mb.twtest.search;

import java.util.Stack;

import com.mb.twtest.domain.City;
import com.mb.twtest.domain.CitySet;
import com.mb.twtest.domain.Trip;

public interface SearchAlgorithm {
	public Stack<Trip> findRoutesToDestination(CitySet cities, City origin,
			City destination);
}
