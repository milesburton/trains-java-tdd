package com.mb.twtest;

import com.mb.twtest.domain.City;
import com.mb.twtest.domain.CitySet;
import com.mb.twtest.domain.Route;
import com.mb.twtest.domain.CitySet.CityNotFoundException;

public class GraphInputStringInterpreter {
	public CitySet createAndAddCitiesFromPathString(CitySet citySet, String routeString) throws InvalidRouteException {
		throwExceptionIfInvalidLength(routeString);

		City startCity = getOrCreateStartCityFromPath(citySet, routeString);
		City endCity = getOrCreateEndCityFromPath(citySet, routeString);

		citySet.add(startCity);
		citySet.add(endCity);

		Route path = createRouteFromToken(startCity, endCity, routeString);
		startCity.addRoute(path);

		return citySet;
	}

	private Route createRouteFromToken(City startCity, City endCity, String routeString) {
		try {
			int distance = Integer.parseInt(routeString.substring(2));
			return new Route(startCity, endCity, distance);
		} catch (NumberFormatException ex) {
			throw new InvalidRouteException();
		}
	}

	private City getOrCreateStartCityFromPath(CitySet citySet, String routeString) {
		return getOrCreateCityFromString(citySet, routeString, 0, 1);
	}

	private City getOrCreateEndCityFromPath(CitySet citySet, String routeString) {
		return getOrCreateCityFromString(citySet, routeString, 1, 2);
	}

	private City getOrCreateCityFromString(CitySet citySet, String routeString, int startOffset, int endOffset) {
		String cityId = routeString.substring(startOffset, endOffset);
		City city = getOrCreateCity(citySet, cityId);
		return city;
	}

	private void throwExceptionIfInvalidLength(String routeString) {
		if (routeString.length() < 3)
			throw new InvalidRouteException();
	}

	private City getOrCreateCity(CitySet citySet, String cityId) {
		City city;
		try {
			city = citySet.getCityBy(cityId);
		} catch (CityNotFoundException ex) {
			city = new City(cityId);
		}

		return city;
	}

	public static class InvalidRouteException extends RuntimeException {
		private static final long serialVersionUID = -1200283884644329240L;
	}
}
