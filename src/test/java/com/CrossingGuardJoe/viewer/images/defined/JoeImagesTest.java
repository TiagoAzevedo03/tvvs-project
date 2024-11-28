package com.CrossingGuardJoe.viewer.images.defined;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JoeImagesTest {

    @Test
    void testGetJoeStandImage() {
        String[] joeStandImage = JoeImages.getJoeStandImage();
        assertNotNull(joeStandImage);
        assertEquals(94, joeStandImage.length);
    }

    @Test
    void testGetJoeWalkleftImage() {
        String[] joeWalkleftImage = JoeImages.getJoeWalkleftImage();
        assertNotNull(joeWalkleftImage);
        assertEquals(81, joeWalkleftImage.length);
    }

    @Test
    void testGetJoeWalkrightImage() {
        String[] joeWalkrightImage = JoeImages.getJoeWalkrightImage();
        assertNotNull(joeWalkrightImage);
        assertEquals(81, joeWalkrightImage.length);
    }

    @Test
    void testGetJoeWalksecondhalfImage() {
        String[] joeWalksecondhalfImage = JoeImages.getJoeWalksecondhalfImage();
        assertNotNull(joeWalksecondhalfImage);
        assertEquals(94, joeWalksecondhalfImage.length);
    }

    @Test
    void testGetJoePassImage() {
        String[] joePassImage = JoeImages.getJoePassImage();
        assertNotNull(joePassImage);
        assertEquals(94, joePassImage.length);
    }

    @Test
    void testGetJoeStopImage() {
        String[] joeStopImage = JoeImages.getJoeStopImage();
        assertNotNull(joeStopImage);
        assertEquals(94, joeStopImage.length);
    }

    @Test
    void testGetJoeHitleftImage() {
        String[] joeHitleftImage = JoeImages.getJoeHitleftImage();
        assertNotNull(joeHitleftImage);
        assertEquals(76, joeHitleftImage.length);
    }

    @Test
    void testGetJoeHitrightImage() {
        String[] joeHitrightImage = JoeImages.getJoeHitrightImage();
        assertNotNull(joeHitrightImage);
        assertEquals(76, joeHitrightImage.length);
    }
}