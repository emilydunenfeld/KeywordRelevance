package com.smartbrief.keywords;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WatsonHttpClient {
	public WatsonResponse watsonResponse = new WatsonResponse(null);
	private final String url;
	public final static String URL = "https://gateway.watsonplatform.net/natural-language-understanding/api/v1/analyze?version=2018-03-16";

	public WatsonHttpClient(@Value("#{systemProperties['url'] ?: T(com.smartbrief.keywords.WatsonHttpClient).URL}") String url) {
		this.url = url;
	}

	public WatsonResponse execute(WatsonRequest watsonRequest) throws IOException, AuthenticationException {
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url);
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(watsonRequest);
			UsernamePasswordCredentials creds = new UsernamePasswordCredentials("a9be6a2c-5024-4737-a83d-f6058aa8e4a5","M1LEiPws6WVD");
			httpPost.addHeader(new BasicScheme().authenticate(creds, httpPost, null));
			StringEntity entity = new StringEntity(json);
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");

			try (CloseableHttpResponse response = client.execute(httpPost)) {
				if (response.getStatusLine().getStatusCode() != 200) {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(response.getEntity().getContent()));
					reader.lines().forEach(System.out::println);
					throw new RuntimeException();
				}
				watsonResponse = mapper.readValue(response.getEntity().getContent(), WatsonResponse.class);
			}
		} finally {
			client.close();
		}
		return watsonResponse;
	}
}