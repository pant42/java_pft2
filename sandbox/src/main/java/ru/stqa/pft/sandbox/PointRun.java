package ru.stqa.pft.sandbox;

public class PointRun {
  public static void main(String[] args) {
    Point p1 = new Point(5, 6);
    Point p2 = new Point(9, 10);

    System.out.println("Расстояние = " + distance(p1, p2));
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
  }
}