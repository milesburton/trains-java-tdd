package com.mb.twtest;

import com.mb.twtest.domain.CitySet;

public class MapGenerator {

	private GraphInputStringInterpreter interpreter = new GraphInputStringInterpreter();

	public CitySet parseGraphString(String csv) {
		try {
			throwExceptionIfInvalidGraphInputSize(csv);
			String[] paths = convertCSVtoStringArray(csv);
			throwExceptionIfInvalidPathArraySize(paths);

			CitySet citySet = new CitySet();
			citySet = parseRoutes(citySet, paths);

			return citySet;
		} catch (GraphInputStringInterpreter.InvalidRouteException ex) {
			throw new InvalidInputException();
		}
	}

	private CitySet parseRoutes(CitySet citySet, String[] routeStringArray) {
		for (String routeString : routeStringArray) {
			citySet = interpreter.createAndAddCitiesFromPathString(citySet, routeString);
		}
		return citySet;
	}

	private void throwExceptionIfInvalidPathArraySize(String[] routeStringArray) {
		if (routeStringArray.length < 1)
			throw new InvalidInputException();
	}

	private String[] convertCSVtoStringArray(String csv) {
		String[] paths = csv.split(",");
		paths = trim(paths);
		return paths;
	}

	private void throwExceptionIfInvalidGraphInputSize(String csv) {
		if ("".equals(csv) || csv.length() < 3)
			throw new InvalidInputException();
	}

	private String[] trim(String[] routeStringArray) {
		for (int i = 1; i < routeStringArray.length; ++i) {
			routeStringArray[i] = routeStringArray[i].trim();
		}
		return routeStringArray;
	}

	public class InvalidInputException extends RuntimeException {
		private static final long serialVersionUID = 4169969126448245371L;
	}

}
