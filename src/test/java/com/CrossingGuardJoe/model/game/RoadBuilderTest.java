package com.CrossingGuardJoe.model.game;

import com.CrossingGuardJoe.model.game.elements.Car;
import com.CrossingGuardJoe.model.game.elements.Kid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoadBuilderTest {
    private RoadBuilder roadBuilder;

    @BeforeEach
    public void setUp() {
        roadBuilder = new RoadBuilder();
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
}