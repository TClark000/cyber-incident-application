package tclark.demo.restful.http;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTestHttpBasic {

    private CloseableHttpClient instance;

    private CloseableHttpResponse response;

    @BeforeEach
    public final void before() throws IOException {
        final String RESTFUL_URL = "http://localhost:8080/api/incidents/summary";
        instance = HttpClientBuilder.create().build();
        response = instance.execute(new HttpGet(RESTFUL_URL));
    }

    @AfterEach
    public final void after() throws IllegalStateException, IOException {
        instance.close();
    }

    @Test
    public final void httpBasicGetRequest() {
        final int statusCode = response.getStatusLine().getStatusCode();
        assertEquals(HttpStatus.SC_OK, statusCode);
    }

    @Test
    public final void httpBasicGetRequest_mimeType() {
        final String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        assertEquals(ContentType.APPLICATION_JSON.getMimeType(), mimeType);
    }

    @Test
    public final void httpBasicGetRequest_jsonBody() throws IOException {
        final String EXPECTED_JSON = "{\"id\":\"2020-01-01_AU\",\"country\":\"Australia\",\"iso2\":\"AU\",\"iso3\":\"AUS\",\"yy_mm\":\"2020-01-01\",\"monthly_count\":3,\"monthly_total\":135,\"percentage\":0.022}";
        final String jsonBody =  EntityUtils.toString(response.getEntity());
        assertNotNull(jsonBody);
        assertTrue(jsonBody.contains(EXPECTED_JSON));
    }
}
