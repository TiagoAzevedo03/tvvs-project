package com.CrossingGuardJoe.viewer.images.defined;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarImageTest {

    @Test
    void testGetCarImage() {
        String[] carImage = CarImage.getCarImage();
        assertNotNull(carImage);
        assertEquals(104, carImage.length);
        assertEquals("                                    $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$                                    ", carImage[0]);
        assertEquals("                      $$$$$$$$$$$$$                                                                      $$$$$$$$$$$$$                      ", carImage[103]);
    }
}