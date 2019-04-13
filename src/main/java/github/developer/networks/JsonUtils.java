package github.developer.networks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;;

/**
 * A Utility class for parsing and formatting JSON data.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class JsonUtils {

	/** The Constant GSON. */
	private static Gson GSON = new Gson();

	/**
	 * From json.
	 *
	 * @param <T> the generic type
	 * @param json the json
	 * @param clazz the clazz
	 * @return the t
	 */
	public static <T> T fromJson(final String json, final Class<T> clazz) {
		return GSON.fromJson(json, clazz);
	}

	/**
	 * To json.
	 *
	 * @param object the object
	 * @return the string
	 */
	public static String toJson(Object object) {
		return GSON.toJson(object);
	}

	/**
	 * From edge json.
	 *
	 * @param json the json
	 * @param player the player
	 * @return the human
	 */
	public static Edge fromEdgeJson(final String json, Class<Edge> edge) {
		return GSON.fromJson(json, edge);
	}
}
