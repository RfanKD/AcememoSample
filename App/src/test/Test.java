package test;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Test {

	public static void main(String[] args) throws JSONException {
		String data = "{personId: 619501043, personName: \"Emir\","
				+ "personImage: \"http://facebook.com/person/emir.jpg\","
				+ "likeName: \"Pizza\", likeImage: \"http://facebook.com/like/pizza.jpg\"}";
		JSONArray x = new JSONArray(data);
		System.out.println(x.get(0));
		
	}
	
}
