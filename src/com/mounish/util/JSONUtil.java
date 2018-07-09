package com.mounish.util;

import org.json.simple.JSONArray;


import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Class to convert JSON string returned by REST End point to JSON array
 * @author Mounish Savier
 *
 */

public class JSONUtil {
    /**
     * Method convert JSOn string to JSONArray
     * 
     * @param json
     * @return
     */
	public static JSONArray getJSONArray(String json) {

		JSONParser parser = new JSONParser();
		JSONArray result = null;
		try {
			Object object = parser.parse(json);
			result = (JSONArray) object;

		} catch (ParseException pe) {

		}
		return result;
	}

}
