package com.CrossingGuardJoe.viewer.images.defined;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LogoImagesTest {

    @Test
    void testGetLogoImage() {
        String[] logoImage = LogoImages.getLogoGameImage();
        assertNotNull(logoImage);
        assertEquals(102, logoImage.length);
    }
}