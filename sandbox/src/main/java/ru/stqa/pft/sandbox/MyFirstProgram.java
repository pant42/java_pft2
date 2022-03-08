package ru.stqa.pft.sandbox;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.sql.SQLOutput;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("everybody everywhere");


    double l = 5;
    System.out.println("Площадь квадрата со стороной " + l + "= " + area(l));

  }
  public static void hello (String somebody) {

    System.out.println("Hello " + somebody + "!");
  }

  public static double area(double len) {
    return len * len;
  }
}