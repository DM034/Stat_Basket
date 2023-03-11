package frame;

import basket.*;
import javax.swing.*;
import timer.*;
import trtmt.*;
import view.*;

public class Frame extends JFrame {

  Basket basket;
  String id;
  Joueur[] joueur;
  Treatement trtmt = new Treatement();
  int init = 0;

  public Frame(Basket basket) throws Exception {
    this.id = trtmt.idM();
    String[] idM = { id };
    trtmt.inserToTable("Match", idM, null);

    System.out.println(basket.getTimer());

    String[] idP = new String[10];
    for (int index = 0; index < 10; index++) {
      idP[index] = String.valueOf(index);
    }

    joueur = new Joueur[10];
    joueur[0] = new Joueur("J1");
    joueur[1] = new Joueur("J2");
    joueur[2] = new Joueur("J3");
    joueur[3] = new Joueur("J4");
    joueur[4] = new Joueur("J5");
    joueur[5] = new Joueur("JA1");
    joueur[6] = new Joueur("JA2");
    joueur[7] = new Joueur("JA3");
    joueur[8] = new Joueur("JA4");
    joueur[9] = new Joueur("JA5");

    String[] value = new String[3];
    for (int i = 0; i < 10; i++) {
      value[0] = idP[i];
      value[1] = joueur[i].getName();
      value[2] = id;
      trtmt.inserToTable("Joueur", value, null);
    }

    this.basket = basket;
    add(basket);
    setTitle("Basket-Game");
    setResizable(false);
    setLocation(300, 100);
    setSize(1400, 500);
    setLayout(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }

  public Basket getBasket() {
    return this.basket;
  }

  public void setBasket(Basket basket) {
    this.basket = basket;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Treatement getTrtmt() {
    return this.trtmt;
  }

  public void setTrtmt(Treatement trtmt) {
    this.trtmt = trtmt;
  }

  public int getInit() {
    return this.init;
  }

  public void setInit(int init) {
    this.init = init;
  }
}
