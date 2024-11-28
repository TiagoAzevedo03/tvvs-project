package com.CrossingGuardJoe.viewer.images.defined;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ToolImagesTest {

    @Test
    void testGetArrowDownImage() {
        String[] arrowDownImage = ToolImages.getArrowDownImage();
        assertNotNull(arrowDownImage);
        assertEquals(15, arrowDownImage.length);
    }

    @Test
    void testGetArrowRightImage() {
        String[] arrowRightImage = ToolImages.getArrowRightImage();
        assertNotNull(arrowRightImage);
        assertEquals(16, arrowRightImage.length);
    }

    @Test
    void testGetKeyUpImage() {
        String[] keyUpImage = ToolImages.getKeyUpImage();
        assertNotNull(keyUpImage);
        assertEquals(51, keyUpImage.length);
    }

    @Test
    void testGetKeyDownImage() {
        String[] keyDownImage = ToolImages.getKeyDownImage();
        assertNotNull(keyDownImage);
        assertEquals(51, keyDownImage.length);
    }

    @Test
    void testGetKeyRightImage() {
        String[] keyRightImage = ToolImages.getKeyRightImage();
        assertNotNull(keyRightImage);
        assertEquals(51, keyRightImage.length);
    }

    @Test
    void testGetKeyLeftImage() {
        String[] keyLeftImage = ToolImages.getKeyLeftImage();
        assertNotNull(keyLeftImage);
        assertEquals(51, keyLeftImage.length);
    }

    @Test
    void testGetKeyEscImage() {
        String[] keyEscImage = ToolImages.getKeyEscImage();
        assertNotNull(keyEscImage);
        assertEquals(29, keyEscImage.length);
    }
}