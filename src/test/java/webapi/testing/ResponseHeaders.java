package webapi.testing;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ResponseHeaders extends BaseClass {

	@Test
	public void contentTypeIsJson() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT);
		response = client.execute(get);
		Header contentType = response.getEntity().getContentType();
		assertEquals(contentType.getValue(), "application/json; charset=utf-8");

		ContentType ct = ContentType.getOrDefault(response.getEntity());
		assertEquals(ct.getMimeType(), "application/json");
	}

	@Test
	public void serverIsGithub() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT);
		response = client.execute(get);
		String headerValue = ResponseUtils.getHeader(response, "Server");
		assertEquals(headerValue, "GitHub.com");
	}

	@Test
	public void xRateLimitIsSixty() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(BASE_ENDPOINT);
		response = client.execute(get);
		String limitVal = ResponseUtils.getHeaderJava8Way(response, "X-RateLimit-Limit");
		assertEquals(limitVal, "60");
	}

	
}
