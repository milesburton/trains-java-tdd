package com.mb.twtest;

import java.io.IOException;
import java.util.List;

import com.mb.twtest.domain.City;
import com.mb.twtest.domain.CitySet;
import com.mb.twtest.domain.PossibleTrip;
import com.mb.twtest.domain.Trip;

public class ThoughtWorksTestEntryPoint {

	MapGenerator mapGenerator = new MapGenerator();
	Cartographer cartographer = new Cartographer();
	CitySet citySet;

	public static void main(String[] args) throws IOException {
		new ThoughtWorksTestEntryPoint().startDemonstartion();
	}

	public void startDemonstartion() {
		parseProvidedString();
		questionOneDistanceOfRouteABC();
		questionTwoDistanceOfRouteAD();
		questionThreeDistanceOfRouteADC();
		questionFourDistanceOfRouteAEBCD();
		questionFiveDistanceOfRouteAED();
		questionSixNumberOfTripsStartingAndEndingAtCWithAMaximumOfThreeStops();
		questionSevenNumberOfTripsBetweenAAndCWithExactlyFourStops();
		questionEightShortestRouteBetweenAAndC();
		questionNineShortestRouteBetweenBAndB();
		questionTenNumberOfRoutesBetweenCAndCWithADistanceLessThan30();
	}

	private void questionOneDistanceOfRouteABC() {
		PossibleTrip possibleTrip = new PossibleTrip();
		possibleTrip.add(citySet.getCityBy("A"));
		possibleTrip.add(citySet.getCityBy("B"));
		possibleTrip.add(citySet.getCityBy("C"));

		int distance = possibleTrip.getAsTrip().getDistance();

		print("Output #1: " + distance + " ");
	}

	private void questionTwoDistanceOfRouteAD() {
		PossibleTrip possibleTrip = new PossibleTrip();
		possibleTrip.add(citySet.getCityBy("A"));
		possibleTrip.add(citySet.getCityBy("D"));

		int distance = possibleTrip.getAsTrip().getDistance();

		print("Output #2: " + distance + " ");
	}

	private void questionThreeDistanceOfRouteADC() {
		PossibleTrip possibleTrip = new PossibleTrip();
		possibleTrip.add(citySet.getCityBy("A"));
		possibleTrip.add(citySet.getCityBy("D"));
		possibleTrip.add(citySet.getCityBy("C"));

		int distance = possibleTrip.getAsTrip().getDistance();

		print("Output #3: " + distance + " ");
	}

	private void questionFourDistanceOfRouteAEBCD() {
		PossibleTrip possibleTrip = new PossibleTrip();
		possibleTrip.add(citySet.getCityBy("A"));
		possibleTrip.add(citySet.getCityBy("E"));
		possibleTrip.add(citySet.getCityBy("B"));
		possibleTrip.add(citySet.getCityBy("C"));
		possibleTrip.add(citySet.getCityBy("D"));

		int distance = possibleTrip.getAsTrip().getDistance();

		print("Output #4: " + distance + " ");
	}

	private void questionFiveDistanceOfRouteAED() {
		try {
			PossibleTrip possibleTrip = new PossibleTrip();
			possibleTrip.add(citySet.getCityBy("A"));
			possibleTrip.add(citySet.getCityBy("E"));
			possibleTrip.add(citySet.getCityBy("D"));

			int distance = possibleTrip.getAsTrip().getDistance();

			print("Output #5: " + distance + " ");
		} catch (ImpossibleRouteException ex) {
			print("Output #5: NO SUCH ROUTE ");
		}
	}

	private void questionSixNumberOfTripsStartingAndEndingAtCWithAMaximumOfThreeStops() {
		City originAndDestination = citySet.getCityBy("C");
		int maximumStops = 3;
		List<Trip> trips = cartographer.getTripsWithMaximumStopsOf(originAndDestination, originAndDestination, maximumStops);
		print("Output #6: " + trips.size() + " ");
	}

	private void questionSevenNumberOfTripsBetweenAAndCWithExactlyFourStops() {
		City origin = citySet.getCityBy("A");
		City destination = citySet.getCityBy("C");
		int maximumStops = 4;
		List<Trip> trips = cartographer.getTripsWithExactlyStops(origin, destination, maximumStops);

		print("Output #7: " + trips.size() + " ");
	}

	private void questionEightShortestRouteBetweenAAndC() {
		City origin = citySet.getCityBy("A");
		City destination = citySet.getCityBy("C");
		Trip trip = cartographer.getShortestTripBetween(origin, destination);
		print("Output #8: " + trip.getDistance() + " ");
	}

	private void questionNineShortestRouteBetweenBAndB() {
		City originAndDestination = citySet.getCityBy("B");
		Trip trip = cartographer.getShortestTripBetween(originAndDestination, originAndDestination);
		print("Output #9: " + trip.getDistance() + " ");
	}

	private void questionTenNumberOfRoutesBetweenCAndCWithADistanceLessThan30() {
		City originAndDestination = citySet.getCityBy("C");
		int maximumDistance = 29;
		List<Trip> trips = cartographer.getTripsWithMaximumDistance(originAndDestination, originAndDestination, maximumDistance);
		print("Output #10: " + trips.size() + " ");
	}

	private void parseProvidedString() {
		citySet = mapGenerator.parseGraphString("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		cartographer.setCities(citySet);
	}

	private void print(String string) {
		System.out.print(string);
	}
}