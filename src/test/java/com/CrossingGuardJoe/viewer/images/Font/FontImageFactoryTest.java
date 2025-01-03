package com.CrossingGuardJoe.viewer.images.Font;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

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
    void testGetImageRepresentationWithLong() {
        Long input = 1L;
        assertThrows(ClassCastException.class,
                () -> fontImageFactory.getImageRepresentation(input));
    }

    @Test
    void testGetImageRepresentationWithShort() {
        Short input = 1;
        assertThrows(ClassCastException.class,
                () -> fontImageFactory.getImageRepresentation(input));
    }


    @Test
    void testGetTextImage() {
        String input = "A";
        String[] result = fontImageFactory.getImageRepresentation(input);
        assertNotNull(result);
        assertEquals(16, result.length);

        input = "AB";
        result = fontImageFactory.getImageRepresentation(input);
        assertNotNull(result);
        assertEquals(16, result.length);

        input = "A B";
        result = fontImageFactory.getImageRepresentation(input);
        assertNotNull(result);
        assertEquals(16, result.length);

        input = "";
        result = fontImageFactory.getImageRepresentation(input);
        assertNotNull(result);
        assertEquals(16, result.length);

        input = "A";
        result = fontImageFactory.getImageRepresentation(input);
        assertNotNull(result);
        assertEquals(16, result.length);

        input = "Z";
        result = fontImageFactory.getImageRepresentation(input);
        assertNotNull(result);
        assertEquals(16, result.length);

        String finalInput = "1";
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> fontImageFactory.getImageRepresentation(finalInput));

        String finalInput2 = "123";
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> fontImageFactory.getImageRepresentation(finalInput2));
    }

    @Test
    void testGetNumberImage() {
        int input = 1;
        String[] result = fontImageFactory.getImageRepresentation(input);
        assertNotNull(result);
        assertEquals(16, result.length);

        input = 123;
        result = fontImageFactory.getImageRepresentation(input);
        assertNotNull(result);
        assertEquals(16, result.length);

        input = 0;
        result = fontImageFactory.getImageRepresentation(input);
        assertNotNull(result);
        assertEquals(16, result.length);

        input = 1;
        result = fontImageFactory.getImageRepresentation(input);
        assertNotNull(result);
        assertEquals(16, result.length);

        input = 9;
        result = fontImageFactory.getImageRepresentation(input);
        assertNotNull(result);
        assertEquals(16, result.length);

        int finalInput = -1;
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> fontImageFactory.getImageRepresentation(finalInput));
    }

    @Test
    void testGetImageRepresentationWithSpaceCharacter() {
        String input = " ";
        String[] result = fontImageFactory.getImageRepresentation(input);
        assertNotNull(result);
        assertEquals(16, result.length);
    }

    @Test
    void testGetAlphabetIndexWithLetter() throws Exception {
        char input = 'A';
        Method method = FontImageFactory.class.getDeclaredMethod("getAlphabetIndex", char.class);
        method.setAccessible(true);
        int index = (int) method.invoke(fontImageFactory, input);
        assertEquals(0, index);

        input = 'Z';
        index = (int) method.invoke(fontImageFactory, input);
        assertEquals(25, index);
    }

    @Test
    void testGetAlphabetIndexWithNonLetter() throws Exception {
        char input = '1';
        Method method = FontImageFactory.class.getDeclaredMethod("getAlphabetIndex", char.class);
        method.setAccessible(true);
        int index = (int) method.invoke(fontImageFactory, input);
        assertEquals(-16, index);

        input = '9';
        index = (int) method.invoke(fontImageFactory, input);
        assertEquals(-8, index);
    }

    @Test
    void testGetAlphabetIndexWithUnsupportedCharacter() throws Exception {
        char input = '@';
        Method method = FontImageFactory.class.getDeclaredMethod("getAlphabetIndex", char.class);
        method.setAccessible(true);
        int index = (int) method.invoke(fontImageFactory, input);
        assertEquals(-1, index);

        input = '#';
        index = (int) method.invoke(fontImageFactory, input);
        assertEquals(-30, index);

        input = '!';
        index = (int) method.invoke(fontImageFactory, input);
        assertEquals(-32, index);
    }
}