package com.CrossingGuardJoe.viewer.images.defined;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KidImagesTest {

    @Test
    void testGetKidStandImage() {
        String[] kidStandImage = KidImages.getKidStandImage();
        assertNotNull(kidStandImage);
        assertEquals(67, kidStandImage.length);
    }

    @Test
    void testGetKidWalkImage() {
        String[] kidWalkImage = KidImages.getKidWalkImage();
        assertNotNull(kidWalkImage);
        assertEquals(66, kidWalkImage.length);
    }

    @Test
    void testGetKidHitImage() {
        String[] kidHitImage = KidImages.getKidHitImage();
        assertNotNull(kidHitImage);
        assertEquals(40, kidHitImage.length);
    }
}