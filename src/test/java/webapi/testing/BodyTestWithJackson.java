package webapi.testing;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import entities.NotFound;
import entities.RateLimit;
import entities.User;

public class BodyTestWithJackson extends BaseClass {
	
	@Test
	public void returnsCorrectLogin() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/mojombo");
		response = client.execute(get);
		User user = ResponseUtils.unmarshall(response, User.class);
		Assert.assertEquals(user.getLogin(), "mojombo");
	}
	
	@Test
	public void returnsCorrectId() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/mojombo");
		response = client.execute(get);
		User user = ResponseUtils.unmarshall(response, User.class);
		Assert.assertEquals(user.getId(), 1);
	}
	
	@Test
	public void notFoundMessageIsCorrect() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT + "/notexistingendpoint");
		response = client.execute(get);
		NotFound notFoundMessage = ResponseUtils.unmarshallGeneric(response, NotFound.class);
		Assert.assertEquals(notFoundMessage.getMessage(), "Not Found");
	}
	
	@Test
	public void correctRateLimitsAreSet() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");
		response = client.execute(get);
		RateLimit rateLimits = ResponseUtils.unmarshallGeneric(response, RateLimit.class);
		Assert.assertEquals(rateLimits.getCoreLimit(), 60);
		Assert.assertEquals(rateLimits.getSearchLimit(), "10");
	}

	

}
