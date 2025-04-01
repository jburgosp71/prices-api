package com.bcnc.pricesapi.integration;

import com.bcnc.pricesapi.adapter.web.dto.PriceResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations = "classpath:application-integrationTest.properties")
public class PriceApiIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testPriceRequests() {
        performTest("2020-06-14T10:00:00Z", 1,
                LocalDateTime.of(2020, 6, 14, 0, 0,0),
                LocalDateTime.of(2020, 12, 31, 23, 59,59),
                new BigDecimal("35.50"));

        performTest("2020-06-14T16:00:00Z", 2,
                LocalDateTime.of(2020, 6, 14, 15, 0,0),
                LocalDateTime.of(2020, 6, 14, 18, 30,0),
                new BigDecimal("25.45"));

        performTest("2020-06-14T21:00:00Z", 1,
                LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                new BigDecimal("35.50"));

        performTest("2020-06-15T10:00:00Z", 3,
                LocalDateTime.of(2020, 6, 15, 0, 0,0),
                LocalDateTime.of(2020, 6, 15, 11, 0,0),
                new BigDecimal("30.50"));

        performTest("2020-06-16T21:00:00Z", 4,
                LocalDateTime.of(2020, 6, 15, 16, 0,0),
                LocalDateTime.of(2020, 12, 31, 23, 59,59),
                new BigDecimal("38.95"));
    }

    private void performTest(String date, Integer expectedPriceList,
                             LocalDateTime expectedStartDate, LocalDateTime expectedEndDate,
                             BigDecimal expectedPrice) {

        String url = "/prices/?date=" + date + "&productid=" + 35455L + "&brandid=" + 1;

        ResponseEntity<PriceResponse> response = restTemplate.getForEntity(url, PriceResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        PriceResponse priceResponse = response.getBody();
        assertEquals((Long) 35455L, priceResponse.getProductId());
        assertEquals((Integer) 1, priceResponse.getBrandId());
        assertEquals(expectedPriceList, priceResponse.getPriceList());
        assertEquals(expectedStartDate, priceResponse.getStartDate());
        assertEquals(expectedEndDate, priceResponse.getEndDate());
        assertEquals(expectedPrice, priceResponse.getPrice());
        assertEquals("EUR", priceResponse.getCurrency());
    }
}
