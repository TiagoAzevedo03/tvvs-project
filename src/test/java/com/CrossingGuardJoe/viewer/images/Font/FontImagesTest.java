package com.CrossingGuardJoe.viewer.images.Font;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FontImagesTest {

    @Test
    void testGetSpaceImage() {
        String[] spaceImage = FontImages.getSpaceImage();
        assertNotNull(spaceImage);
        assertEquals(16, spaceImage.length);
        for (String line : spaceImage) {
            assertEquals("                ", line);
        }
    }

    @Test
    void testGetNumbersImage() {
        String[] numbersImage = FontImages.getNumbersImage();
        assertNotNull(numbersImage);
        assertEquals(160, numbersImage.length);
    }

    @Test
    void testGetAlphabetImage() {
        String[] alphabetImage = FontImages.getAlphabetImage();
        assertNotNull(alphabetImage);
        assertEquals(416, alphabetImage.length);
    }
}