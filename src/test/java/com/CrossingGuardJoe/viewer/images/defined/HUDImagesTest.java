package com.CrossingGuardJoe.viewer.images.defined;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HUDImagesTest {

    @Test
    void testGetHPImage() {
        String[] hpImage = HUDImages.getHPImage();
        assertNotNull(hpImage);
        assertEquals(27, hpImage.length);
    }

    @Test
    void testGetGameHudImage() {
        String[] gameHudImage = HUDImages.getGameHudImage();
        assertNotNull(gameHudImage);
    }

    @Test
    void testGetScoreBarSliceImage() {
        String[] scoreBarSliceImage = HUDImages.getScoreBarSliceImage();
        assertNotNull(scoreBarSliceImage);
        assertEquals(37, scoreBarSliceImage.length);
    }

    @Test
    void testGetHpBarSliceImage() {
        String[] hpBarSliceImage = HUDImages.getHpBarSliceImage();
        assertNotNull(hpBarSliceImage);
        assertEquals(37, hpBarSliceImage.length);
    }
}