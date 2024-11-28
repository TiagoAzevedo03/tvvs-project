package com.CrossingGuardJoe.viewer.images.defined;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoadItemsImagesTest {

    @Test
    void testGetSignalImage() {
        String[] signalImage = RoadItemsImages.getSignalImage();
        assertNotNull(signalImage);
        assertEquals(98, signalImage.length);
    }
}