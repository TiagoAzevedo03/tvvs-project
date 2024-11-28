package com.CrossingGuardJoe.viewer.images.generator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShapeTest {

    @Test
    void testRectangleFilledGenerator() {
        String[] result = Shape.RectangleFilledGenerator(4, 5, '*', 1, '#');
        String[] expected = {
                "######",
                "#****#",
                "#****#",
                "#****#",
                "######"
        };
        assertArrayEquals(expected, result);
    }

    @Test
    void testRectangleFilledGeneratorWithDifferentCharacters() {
        String[] result = Shape.RectangleFilledGenerator(3, 4, 'X', 2, 'O');
        String[] expected = {
                "OOOOOOO",
                "OOXXXOO",
                "OOXXXOO",
                "OOOOOOO"
        };
        assertArrayEquals(expected, result);
    }

    @Test
    void testRectangleFilledGeneratorWithNoBorder() {
        String[] result = Shape.RectangleFilledGenerator(3, 3, 'A', 0, 'B');
        String[] expected = {
                "BBB",
                "AAA",
                "BBB"
        };
        assertArrayEquals(expected, result);
    }
}