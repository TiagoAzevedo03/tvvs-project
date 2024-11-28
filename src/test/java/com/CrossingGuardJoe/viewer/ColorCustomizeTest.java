package com.CrossingGuardJoe.viewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorCustomizeTest {

    private ColorCustomize colorCustomize;

    @BeforeEach
    void setUp() {
        colorCustomize = ColorCustomize.getInstance();
    }

    @Test
    void testAddMapping() {
        colorCustomize.addMapping('a', 'b');
        assertEquals('b', colorCustomize.getMappedCharacter('a'));
    }

    @Test
    void testGetMappedCharacter() {
        colorCustomize.addMapping('x', 'y');
        assertEquals('y', colorCustomize.getMappedCharacter('x'));
        assertEquals('z', colorCustomize.getMappedCharacter('z'));
    }
}