package com.mb.twtest;

import java.util.Stack;

import com.mb.twtest.domain.City;
import com.mb.twtest.domain.CitySet;
import com.mb.twtest.domain.Trip;
import com.mb.twtest.search.DepthFirstSearch;
import com.mb.twtest.search.DijkstraShortestPathAlgorithm;
import com.mb.twtest.search.ShortestTripAlgorithm;
import com.mb.twtest.search.boundary.DistanceMustBeLessOrEqualToN;
import com.mb.twtest.search.boundary.MustContainUpToAndIncludingNStops;
import com.mb.twtest.search.match.ShouldBeDestination;
import com.mb.twtest.search.match.TripDistanceMustBeLessOrEqualToN;
import com.mb.twtest.search.match.TripMustHaveExactlyNStops;

public class Cartographer {

	private CitySet cities;

	public Stack<Trip> getTripsWithMaximumStopsOf(City origin,
			City destination, int stops) {
		DepthFirstSearch searchAlgorithm = new DepthFirstSearch();
		searchAlgorithm.setBoundaryCheck(new MustContainUpToAndIncludingNStops(
				stops - 1));
		searchAlgorithm.setTripMatcher(new ShouldBeDestination());
		return searchAlgorithm.findRoutesToDestination(cities, origin,
				destination);
	}

	public Stack<Trip> getTripsWithExactlyStops(City origin, City destination,
			int stops) {
		DepthFirstSearch searchAlgorithm = new DepthFirstSearch();
		searchAlgorithm.setBoundaryCheck(new MustContainUpToAndIncludingNStops(
				stops));
		searchAlgorithm.setTripMatcher(new TripMustHaveExactlyNStops(stops));
		return searchAlgorithm.findRoutesToDestination(cities, origin,
				destination);
	}

	public Trip getShortestTripBetween(City origin, City destination) {
		ShortestTripAlgorithm shortestTripAlgorithm = new DijkstraShortestPathAlgorithm();
		return shortestTripAlgorithm.findShortestTrip(cities, origin,
				destination);
	}

	public Stack<Trip> getTripsWithMaximumDistance(City origin,
			City destination, int maximumDistance) {
		DepthFirstSearch searchAlgorithm = new DepthFirstSearch();
		searchAlgorithm.setBoundaryCheck(new DistanceMustBeLessOrEqualToN(
				maximumDistance));
		searchAlgorithm.setTripMatcher(new TripDistanceMustBeLessOrEqualToN(
				maximumDistance));
		return searchAlgorithm.findRoutesToDestination(cities, origin,
				destination);
	}

	public void setCities(CitySet cities) {
		this.cities = cities;
	}

}
