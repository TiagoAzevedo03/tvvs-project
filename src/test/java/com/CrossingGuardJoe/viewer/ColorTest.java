package com.CrossingGuardJoe.viewer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    @Test
    void testGetColorHexCode() {
        assertEquals("#000000", Color.BLACK.getColorHexCode());
        assertEquals("#FF5451", Color.RED.getColorHexCode());
    }

    @Test
    void testGetCharacter() {
        assertEquals('$', Color.BLACK.getCharacter());
        assertEquals('&', Color.RED.getCharacter());
    }

    @Test
    void testGetColor() {
        assertEquals(Color.BLACK, Color.getColor('$'));
        assertEquals(Color.RED, Color.getColor('&'));
        assertNull(Color.getColor('Z'));
    }
}