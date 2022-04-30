package tclark.demo.restful.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTestIncidentSummaryController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testIncidentSummaryRepository_requestMapping() throws Exception {
        final String URL_TEMPLATE = "/api/incidents/summary";
        final String EXPECTED_JSON = "{\"id\":\"2020-01-01_AU\",\"country\":\"Australia\",\"iso2\":\"AU\",\"iso3\":\"AUS\",\"yy_mm\":\"2020-01-01\",\"monthly_count\":3,\"monthly_total\":135,\"percentage\":0.022}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URL_TEMPLATE).accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains(EXPECTED_JSON));
    }
}
