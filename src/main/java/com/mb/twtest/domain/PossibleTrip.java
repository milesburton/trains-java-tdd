package com.mb.twtest.domain;

import java.util.ArrayList;

import com.mb.twtest.ImpossibleRouteException;
import com.mb.twtest.domain.City.NoRouteException;

public class PossibleTrip extends ArrayList<City> {

	private static final long serialVersionUID = 4134761561000772971L;

	public Trip getAsTrip() {
		try {
			Trip actualRoute = new Trip();
			for (int i = 0; i < getTotalCitiesExcludingDestination(); ++i) {
				City currentLocation = get(i);
				City destination = get(i + 1);

				Route pathBetweenCities = currentLocation
						.getRouteTo(destination);
				actualRoute.add(pathBetweenCities);
			}

			return actualRoute;
		} catch (NoRouteException ex) {
			throw new ImpossibleRouteException();
		}
	}

	private int getTotalCitiesExcludingDestination() {
		if (size() == 0)
			return 0;

		return size() - 1;
	}

}
