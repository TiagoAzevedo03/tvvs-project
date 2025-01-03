package com.CrossingGuardJoe.model.game;

import com.CrossingGuardJoe.model.game.elements.Car;
import com.CrossingGuardJoe.model.game.elements.Kid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class RoadBuilderTest {
    private RoadBuilder roadBuilder;
    private Random mockRandom;

    @BeforeEach
    public void setUp() {
        roadBuilder = new RoadBuilder();
        mockRandom = mock(Random.class);
    }

    @Test
    public void testCreateRoad() {
        Road road = roadBuilder.createRoad();
        assertNotNull(road);
        assertNotNull(road.getJoe());
        assertNotNull(road.getKids());
        assertNotNull(road.getCars());
    }

    @Test
    public void testCreateKidsNextLevel() {
        List<Kid> kids = roadBuilder.createKidsNextLevel(5);
        assertNotNull(kids);
        assertEquals(5, kids.size());
    }

    @Test
    void testCreateCars() throws Exception {
        Method method = RoadBuilder.class.getDeclaredMethod("createCars");
        method.setAccessible(true);
        List<Car> cars = (List<Car>) method.invoke(roadBuilder);

        assertNotNull(cars);
        assertEquals(3, cars.size()); // Assuming NUMBER_OF_CARS is 3

        for (Car car : cars) {
            assertTrue(car.getPosition().getY() < 0);
            assertTrue(car.getPosition().getY() >= -1000); // Assuming RANDOM_BOUND is 500
            assertTrue(car.getPosition().getX() == 85 || car.getPosition().getX() == 172 || car.getPosition().getX() == 259 || car.getPosition().getX() == 346);
        }
    }


    @Test
    void testCreateCarsWhenCarYPositionExceedsMaxYDistance() throws Exception {
        RoadBuilder roadBuilder = new RoadBuilder();
        Method method = RoadBuilder.class.getDeclaredMethod("createCars");
        method.setAccessible(true);
        List<Car> cars = (List<Car>) method.invoke(roadBuilder);

        Car car = cars.get(0);
        car.getPosition().setY(600);

        Thread.sleep(200);

        assertTrue(car.getPosition().getY() < 0);
    }

    @Test
    void testCreateCarsWithMockedRandom() throws Exception {
        when(mockRandom.nextInt(anyInt())).thenReturn(100, 200, 300, 400, 500, 600);

        Method method = RoadBuilder.class.getDeclaredMethod("createCars");
        method.setAccessible(true);
        List<Car> cars = (List<Car>) method.invoke(roadBuilder);

        assertNotNull(cars);
        assertEquals(3, cars.size()); // Assuming NUMBER_OF_CARS is 3

        for (Car car : cars) {
            assertTrue(car.getPosition().getY() < 0);
            assertTrue(car.getPosition().getY() >= -1000); // Assuming RANDOM_BOUND is 500
            assertTrue(car.getPosition().getX() == 85 || car.getPosition().getX() == 172 || car.getPosition().getX() == 259 || car.getPosition().getX() == 346);
        }
    }

    @Test
    void testCreateCarsWithOverlappingYPositions() throws Exception {
        when(mockRandom.nextInt(anyInt())).thenReturn(100, 100, 200, 200, 300, 300);

        Method method = RoadBuilder.class.getDeclaredMethod("createCars");
        method.setAccessible(true);
        List<Car> cars = (List<Car>) method.invoke(roadBuilder);

        assertNotNull(cars);
        assertEquals(3, cars.size()); // Assuming NUMBER_OF_CARS is 3

        for (Car car : cars) {
            assertTrue(car.getPosition().getY() < 0);
            assertTrue(car.getPosition().getY() >= -1000); // Assuming RANDOM_BOUND is 500
            assertTrue(car.getPosition().getX() == 85 || car.getPosition().getX() == 172 || car.getPosition().getX() == 259 || car.getPosition().getX() == 346);
        }
    }

    @Test
    public void testCreateKids() throws Exception {
        RoadBuilder roadBuilder = new RoadBuilder();
        Method method = RoadBuilder.class.getDeclaredMethod("createKids");
        method.setAccessible(true);
        List<Kid> kids = (List<Kid>) method.invoke(roadBuilder);
        assertNotNull(kids);
        assertFalse(kids.isEmpty());
        assertEquals(3, kids.size());
    }

    @Test
    void testGetKids() {
        int numberKids = 3;
        List<Kid> kids = roadBuilder.createKidsNextLevel(numberKids);

        assertNotNull(kids);
        assertEquals(numberKids, kids.size());

        int expectedX = 490;
        for (Kid kid : kids) {
            Kid kidMock = spy(kid);
            assertEquals(expectedX, kidMock.getPosition().getX());
            assertEquals(330, kidMock.getPosition().getY());
            expectedX += 9;
        }
    }
}