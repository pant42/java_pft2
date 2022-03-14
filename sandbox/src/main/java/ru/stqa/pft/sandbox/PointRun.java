package ru.stqa.pft.sandbox;

public class PointRun {
  public static void main(String[] args) {
    Point p1 = new Point(5, 6);
    Point p2 = new Point(9, 10);

    System.out.println("Расстояние = " + Point.distance(p1, p2));
    System.out.println("Расстояние = " + p2.distanceM(p1));
  }
}


