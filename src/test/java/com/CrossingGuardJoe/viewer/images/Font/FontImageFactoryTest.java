package com.CrossingGuardJoe.viewer.images.Font;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FontImageFactoryTest {

    private FontImageFactory fontImageFactory;

    @BeforeEach
    void setUp() {
        fontImageFactory = new FontImageFactory();
    }

    @Test
    void testGetImageRepresentationWithString() {
        String input = "A";
        String[] result = fontImageFactory.getImageRepresentation(input);
        assertNotNull(result);
        assertEquals(16, result.length);
    }

    @Test
    void testGetImageRepresentationWithInteger() {
        int input = 1;
        String[] result = fontImageFactory.getImageRepresentation(input);
        assertNotNull(result);
        assertEquals(16, result.length);
    }

    @Test
    void testGetImageRepresentationWithUnsupportedType() {
        assertThrows(IllegalArgumentException.class, () -> fontImageFactory.getImageRepresentation(1.0));
    }

    @Test
    void testGetTextImage() {
        String input = "A";
        String[] result = fontImageFactory.getImageRepresentation(input);
        assertNotNull(result);
        assertEquals(16, result.length);
    }

    @Test
    void testGetNumberImage() {
        int input = 1;
        String[] result = fontImageFactory.getImageRepresentation(input);
        assertNotNull(result);
        assertEquals(16, result.length);
    }
}