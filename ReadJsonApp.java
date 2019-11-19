package com.readjson.jsonreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

public class ReadJsonApp {

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	public static void parseJsonObject(JSONObject json) {

		JSONObject pathsObj = (JSONObject) json.get("paths");

		Iterator<String> keysFromObj = pathsObj.keys();

		while (keysFromObj.hasNext()) {

			System.out.println(keysFromObj.next());

		}

	}

	public static void main(String[] args) {

		JSONObject json;
		try {
			json = readJsonFromUrl("http://petstore.swagger.io/v2/swagger.json");

			parseJsonObject(json);

		} catch (JSONException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}

