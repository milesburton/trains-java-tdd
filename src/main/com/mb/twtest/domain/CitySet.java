package com.mb.twtest.domain;

import java.util.HashSet;
import java.util.Set;

public class CitySet extends HashSet<City> {

	private static final long serialVersionUID = -6625715284636273844L;

	public City getCityBy(String id) {
		City foundCity = null;
		for (City city : this) {
			if (id.equals(city.getId())) {
				foundCity = city;
				break;
			}
		}

		if (foundCity == null) {
			throw new CityNotFoundException();
		}

		return foundCity;
	}

	public Set<Route> getUniqueRoutes() {
		Set<Route> uniqueRoutes = new HashSet<Route>();
		for (City city : this) {
			uniqueRoutes.addAll(city.getRoutes());
		}

		return uniqueRoutes;
	}

	public static class CityNotFoundException extends RuntimeException {
		private static final long serialVersionUID = 4132928156968711376L;

	}
}
