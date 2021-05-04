package webapi.testing;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static entities.User.*;

public class BodyTestWithSimpleMap extends BaseClass{
	
	@Test
	public void returnCorrectLogin() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/mojombo");
		response = client.execute(get);
		String jsonBody = EntityUtils.toString(response.getEntity());
		System.out.println(jsonBody);
		JSONObject jsonObject = new JSONObject(jsonBody);
		System.out.println(jsonObject);
		String loginValue = getValueFor(jsonObject, LOGIN).toString();
		assertEquals(loginValue, "mojombo");
		
	}
	
	@Test
	public void returnCorrectId() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/mojombo");
		response = client.execute(get);
		String jsonBody = EntityUtils.toString(response.getEntity());
		JSONObject jsonObject = new JSONObject(jsonBody);
		Integer loginValue = (Integer) getValueFor(jsonObject, ID);
		assertEquals(loginValue, Integer.valueOf(1));
		
	}

	private Object getValueFor(JSONObject jsonObject, String key) {
		// TODO Auto-generated method stub
		return jsonObject.get(key);
	}
}
