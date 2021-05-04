package webapi.testing;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Get404 extends BaseClass {
	
	CloseableHttpClient client;
	CloseableHttpResponse response;
	
	@BeforeMethod
	public void setUp() {
		client = HttpClientBuilder.create().build();
	}
	
	@AfterMethod
	public void closeResources() throws IOException {
		client.close();
		response.close();
	}
	
	@Test
	public void nonExistingUrlReturns404() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT + "/nonexistingurl");
		response = client.execute(get);
		System.out.println("response client: " + response);
		int actualStatus = response.getStatusLine().getStatusCode();
		assertEquals(actualStatus, 404);
	}
}
