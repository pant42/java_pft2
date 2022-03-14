package ru.stqa.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Point;

public class PointTest {
  @Test
  public void testDistance() {

    Point p1 = new Point(0, 3);
    Point p2 = new Point(4, 0);
    Assert.assertEquals(Point.distance(p1, p2), 5.0);
  }

  @Test
  public void testDistance2() {

    Point p1 = new Point(0, 6);
    Point p2 = new Point(8, 0);
    Assert.assertEquals(Point.distance(p1, p2), 10.0);
  }

  @Test
  public void testDistance3() {
    Point p1 = new Point(0, 3);
    Point p2 = new Point(4, 0);
    Assert.assertEquals(p2.distanceM(p1), 5);
  }

  @Test
  public void testDistance4() {
    Point p1 = new Point(1, 7);
    Point p2 = new Point(9, 1);
    Assert.assertEquals(p2.distanceM(p1), 10);
  }
}


