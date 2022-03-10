package ru.stqa.sandbox;



import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Point;

public class PointTest {
 @Test
  public void testDistance () {

    Point p1= new Point(0,3);
    Point p2= new Point(4,0);

   Assert.assertEquals( Point.distance (p1, p2),5.0);
  }
}

