package main;

import basket.*;
import frame.Frame;
import javax.swing.JOptionPane;
import listener.*;
import timer.*;
import view.*;

public class Main {

  public static void main(String[] args) {
    try {
      // System.out.println("Basket");

      Timer timer = new Timer();

      Joueur[] joueur1 = new Joueur[10];
      String[] name = new String[10];
      name[0] = "J1";
      name[1] = "J2";
      name[2] = "J3";
      name[3] = "J4";
      name[4] = "J5";

      name[5] = "JA1";
      name[6] = "JA2";
      name[7] = "JA3";
      name[8] = "JA4";
      name[9] = "JA5";

      for (int i = 0; i < joueur1.length; i++) {
        joueur1[i] = new Joueur(name[i]);
      }

      //  Equipe
      Equipe1 e1 = new Equipe1("ITU", joueur1);
      Equipe2 e2 = new Equipe2("ESSCA", joueur1);

      //  Field and buttons
      Basket basket = new Basket(e1, e2);

      //  Frame
      Frame frame = new Frame(basket);

      //  Listener
      ButtonListener blistener = new ButtonListener(frame, e1, e2);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("#####");
    }
  }
}
