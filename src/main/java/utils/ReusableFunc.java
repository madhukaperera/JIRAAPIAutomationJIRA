package utils;

import io.restassured.path.json.JsonPath;

public class ReusableFunc {

	public static JsonPath RawToJson(String resp)
	{
		JsonPath jsonPath = new JsonPath(resp);
		return jsonPath;
	}

}