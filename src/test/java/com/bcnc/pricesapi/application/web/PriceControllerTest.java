package com.bcnc.pricesapi.application.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class PriceControllerTest {
    private final PriceController controller = new PriceController();

    @Test
    void testValidRequest() {
        ResponseEntity<String> response = controller.prices("2023-08-02T10:30:00Z", "35455", "1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK request", response.getBody());
    }

    @Test
    void testMissingParameter() {
        Exception exception = assertThrows(RuntimeException.class, () ->
                controller.prices(null, "35455", "1")
        );
        assertTrue(exception.getMessage().contains("The request must be /prices/?date=yyyy-mm-ddThh:mi:ssZ&productid=xxxxxx&brandid=zz"));
    }

    @Test
    void testInvalidDateFormat() {
        Exception exception = assertThrows(RuntimeException.class, () ->
                controller.prices("2023/08/02 10:30:00", "35455", "1")
        );
        assertTrue(exception.getMessage().contains("The format of the date parameter must be: yyyy-mm-ddThh:mi:ssZ"));
    }

    @Test
    void testInvalidProductId() {
        Exception exception = assertThrows(RuntimeException.class, () ->
                controller.prices("2023-08-02T10:30:00Z", "abc", "1")
        );
        assertTrue(exception.getMessage().contains("The productid parameter must be numeric"));
    }

    @Test
    void testInvalidBrandId() {
        Exception exception = assertThrows(RuntimeException.class, () ->
                controller.prices("2023-08-02T10:30:00Z", "35455", "xyz")
        );
        assertTrue(exception.getMessage().contains("The brandid parameter must be numeric"));
    }
}