package listener;

import basket.*;
import frame.*;
import frame.Frame;
import java.awt.Color;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import trtmt.Treatement;
import view.*;

public class ButtonListener implements ActionListener, KeyListener {

  Frame frame;
  Equipe1 e1;
  Equipe2 e2;
  int possession = 0;
  int lastp = 0;
  long lastTimer = 0;
  long currentTimer = System.currentTimeMillis();
  long duree;
  Treatement trtmt = new Treatement();
  String id;
  String valReb = null;

  public ButtonListener(Frame frame, Equipe1 e1, Equipe2 e2) {
    this.frame = frame;
    this.id = frame.getId();
    this.e1 = e1;
    this.e2 = e2;
    for (int index = 0; index < 10; index++) {
      this.duree = e1.getJoueur()[index].getTimerJ();
    }
    addActionListn();
  }

  public void actionPerformed(ActionEvent e) {
    JButton button = frame.getBasket().getButtons();
    JButton buttonPlay = frame.getBasket().getButtonsPlay();
    JButton[] button1 = frame.getBasket().getButtons1();

    //  Equipe 1
    //  Joueur 1
    lastp = possession;
    lastTimer = currentTimer;
    for (int i = 0; i < 10; i++) {
      if (e.getSource() == button1[i]) {
        possession = i;
        currentTimer = System.currentTimeMillis();
        e1.getJoueur()[lastp].setTimerJ(currentTimer - lastTimer);
        duree = e1.getJoueur()[lastp].getTimerJ();
        System.out.println(
          e1.getJoueur()[lastp].getName() +
          " passe la balle a " +
          e1.getJoueur()[possession].getName()
        );
        System.out.println(
          e1.getJoueur()[possession].getName() + " a la balle"
        );
        System.out.println(
          "Le joueur " +
          e1.getJoueur()[lastp].getName() +
          " a une possession de " +
          e1.getJoueur()[lastp].getTimerJ() /
          1000 +
          " secondes"
        );
        try {
          int qt = frame
            .getBasket()
            .changeQuart_tps(trtmt.getdureeTotal(null, id));
          String[] val = {
            String.valueOf(possession),
            String.valueOf(lastp),
            "Simple",
            id,
            String.valueOf(qt),
          };
          String[] value = {
            String.valueOf(lastp),
            String.valueOf(duree),
            id,
            String.valueOf(qt),
          };
          trtmt.inserToTable("passe", val, null);
          trtmt.inserToTable("duree", value, null);
        } catch (Exception except) {
          // TODO: handle exception
        }
      }
    }

    //  PAUSE
    if (e.getSource() == button) {
      currentTimer = System.currentTimeMillis();
      e1.getJoueur()[possession].setTimerJ(currentTimer - lastTimer);
      duree = e1.getJoueur()[possession].getTimerJ();
      JButton[] buttons2 = new JButton[10];
      for (int i = 0; i < buttons2.length; i++) {
        buttons2[i] = new JButton("Pause");
        // buttons2[i].setBackground(Color.black);
        frame.getBasket().getButtons1()[i].setVisible(false);
        frame.add(buttons2[i]);
      }
      buttons2[0].setBounds(20, 0, 150, 50);
      buttons2[1].setBounds(20, 80, 150, 50);
      buttons2[2].setBounds(20, 180, 150, 50);
      buttons2[3].setBounds(20, 280, 150, 50);
      buttons2[4].setBounds(20, 370, 150, 50);
      buttons2[5].setBounds(1230, 0, 150, 50);
      buttons2[6].setBounds(1230, 80, 150, 50);
      buttons2[7].setBounds(1230, 180, 150, 50);
      buttons2[8].setBounds(1230, 280, 150, 50);
      buttons2[9].setBounds(1230, 370, 150, 50);

      System.out.println("PAUSE");
      try {
        int qt = frame
          .getBasket()
          .changeQuart_tps(trtmt.getdureeTotal(null, id));
        String[] val = {
          String.valueOf(possession),
          String.valueOf(lastp),
          "AV Pause",
          id,
          String.valueOf(qt),
        };
        String[] value = {
          String.valueOf(possession),
          String.valueOf(duree),
          id,
          String.valueOf(qt),
        };
        trtmt.inserToTable("passe", val, null);
        trtmt.inserToTable("duree", value, null);
      } catch (Exception except) {
        // TODO: handle exception
      }
      System.out.println(System.currentTimeMillis());
    }

    //  PLAY
    if (e.getSource() == buttonPlay) {
      currentTimer = System.currentTimeMillis();

      JButton[] buttons2 = new JButton[10];
      for (int i = 0; i < buttons2.length; i++) {
        frame.getBasket().getButtons1()[i].setVisible(true);
      }
      System.out.println("Play");

      System.out.println(System.currentTimeMillis());
    }
  }

  @Override
  public void keyPressed(KeyEvent arg0) {
    //  Ball
    if (arg0.getKeyCode() == KeyEvent.VK_B) {
      for (int i = 0; i < 5; i++) {
        if (frame.getInit() == i) {
          System.out.println(
            frame.getBasket().getE1().getJoueur()[i].getName() + " E1 balle"
          );
        }
      }

      for (int i = 5; i < 10; i++) {
        if (frame.getInit() == i) {
          System.out.println(
            frame.getBasket().getE2().getJoueur()[i].getName() + " EA2 balle"
          );
        }
      }
    }

    //  Shoot
    if (arg0.getKeyCode() == KeyEvent.VK_0) {
      lastTimer = currentTimer;
      currentTimer = System.currentTimeMillis();
      if (possession < 5) {
        e1.getJoueur()[possession].setTimerJ(currentTimer - lastTimer);
        duree = e1.getJoueur()[possession].getTimerJ();
        System.out.println(e1.getJoueur()[possession].getName() + " tire 0pts");
      } else {
        e2.getJoueur()[possession].setTimerJ(currentTimer - lastTimer);
        duree = e1.getJoueur()[possession].getTimerJ();

        System.out.println(e2.getJoueur()[possession].getName() + " tire 0pts");
      }
      try {
        int qt = frame
          .getBasket()
          .changeQuart_tps(trtmt.getdureeTotal(null, id));
        String[] val = {
          String.valueOf(possession),
          "0",
          id,
          String.valueOf(qt),
        };
        String[] value = {
          String.valueOf(possession),
          String.valueOf(duree),
          id,
          String.valueOf(qt),
        };
        trtmt.inserToTable("tir", val, null);
        trtmt.inserToTable("duree", value, null);
      } catch (Exception e) {
        // TODO: handle exception
      }
    }

    if (arg0.getKeyCode() == KeyEvent.VK_R) {
      try {
        int qt = frame
          .getBasket()
          .changeQuart_tps(trtmt.getdureeTotal(null, id));
        if (possession < 5) {
          if (lastp >= 5) {
            String[] val = {
              String.valueOf(possession),
              "D",
              id,
              String.valueOf(qt),
            };
            trtmt.inserToTable("rebond", val, null);
            valReb = "D";
            System.out.println(
              e1.getJoueur()[possession].getName() + " rebond D"
            );
          } else {
            String[] val = {
              String.valueOf(possession),
              "O",
              id,
              String.valueOf(qt),
            };
            trtmt.inserToTable("rebond", val, null);
            valReb = null;
            System.out.println(
              e1.getJoueur()[possession].getName() + " rebond O"
            );
          }
        } else {
          if (lastp < 5) {
            String[] val = {
              String.valueOf(possession),
              "D",
              id,
              String.valueOf(qt),
            };
            trtmt.inserToTable("rebond", val, null);
            valReb = "D";
            System.out.println(
              e2.getJoueur()[possession].getName() + " rebond D"
            );
          } else {
            String[] val = {
              String.valueOf(possession),
              "O",
              id,
              String.valueOf(qt),
            };
            trtmt.inserToTable("rebond", val, null);
            valReb = null;
            System.out.println(
              e2.getJoueur()[possession].getName() + " rebond O"
            );
          }
        }
      } catch (Exception e) {
        // TODO: handle exception
      }
    }

    if (arg0.getKeyCode() == KeyEvent.VK_1) {
      try {
        int qt = frame
          .getBasket()
          .changeQuart_tps(trtmt.getdureeTotal(null, id));
        lastTimer = currentTimer;
        currentTimer = System.currentTimeMillis();
        if (possession < 5) {
          if (valReb == "D") {
            valReb = null;
            throw new Exception("IRREALISTE");
          }
          e1.getJoueur()[possession].setTimerJ(currentTimer - lastTimer);
          duree = e1.getJoueur()[possession].getTimerJ();
          // System.out.println(e1.getJoueur()[possession].getName()+" tire 1pts");
          if (lastp < 5) {
            System.out.println(
              "Duree du J " +
              e1.getJoueur()[possession].getName() +
              " qui a tire: " +
              e1.getJoueur()[possession].getTimerJ() /
              1000
            );
            e1.getJoueur()[lastp].setPd(e1.getJoueur()[lastp].getPd() + 1);
            String[] val = {
              String.valueOf(possession),
              String.valueOf(lastp),
              "Decisive",
              id,
              String.valueOf(qt),
            };
            // possession = 5;
            System.out.println("JA1 a la balle");
            trtmt.inserToTable("passe", val, null);
          }
        } else {
          if (valReb == "D") {
            valReb = null;
            throw new Exception("IRREALISTE");
          }
          e2.getJoueur()[possession].setTimerJ(currentTimer - lastTimer);
          duree = e2.getJoueur()[possession].getTimerJ();

          // System.out.println(e2.getJoueur()[possession].getName()+" tire 1pts");
          if (lastp >= 5) {
            System.out.println(
              "Duree du J " +
              e2.getJoueur()[possession].getName() +
              " qui a tire: " +
              e2.getJoueur()[possession].getTimerJ() /
              1000
            );
            e2.getJoueur()[lastp].setPd(e2.getJoueur()[lastp].getPd() + 1);
            String[] val = {
              String.valueOf(possession),
              String.valueOf(lastp),
              "Decisive",
              id,
              String.valueOf(qt),
            };
            // possession = 0;
            System.out.println("J1 a la balle");

            trtmt.inserToTable("passe", val, null);
          }
        }
        String[] val = {
          String.valueOf(possession),
          "1",
          id,
          String.valueOf(qt),
        };
        String[] value = {
          String.valueOf(possession),
          String.valueOf(duree),
          id,
          String.valueOf(qt),
        };
        trtmt.inserToTable("tir", val, null);
        trtmt.inserToTable("duree", value, null);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }

    if (arg0.getKeyCode() == KeyEvent.VK_2) {
      try {
        int qt = frame
          .getBasket()
          .changeQuart_tps(trtmt.getdureeTotal(null, id));
        lastTimer = currentTimer;
        currentTimer = System.currentTimeMillis();
        if (possession < 5) {
          if (valReb == "D") {
            valReb = null;
            throw new Exception("IRREALISTE");
          }
          e1.getJoueur()[possession].setTimerJ(currentTimer - lastTimer);
          duree = e1.getJoueur()[possession].getTimerJ();
          // System.out.println(e1.getJoueur()[possession].getName()+" tire 1pts");
          if (lastp < 5) {
            System.out.println(
              "Duree du J " +
              e1.getJoueur()[possession].getName() +
              " qui a tire: " +
              e1.getJoueur()[possession].getTimerJ() /
              1000
            );
            e1.getJoueur()[lastp].setPd(e1.getJoueur()[lastp].getPd() + 2);
            String[] val = {
              String.valueOf(possession),
              String.valueOf(lastp),
              "Decisive",
              id,
              String.valueOf(qt),
            };
            // possession = 5;
            System.out.println("JA1 a la balle");
            trtmt.inserToTable("passe", val, null);
          }
        } else {
          if (valReb == "D") {
            valReb = null;
            throw new Exception("IRREALISTE");
          }
          e2.getJoueur()[possession].setTimerJ(currentTimer - lastTimer);
          duree = e2.getJoueur()[possession].getTimerJ();

          // System.out.println(e2.getJoueur()[possession].getName()+" tire 1pts");
          if (lastp >= 5) {
            System.out.println(
              "Duree du J " +
              e2.getJoueur()[possession].getName() +
              " qui a tire: " +
              e2.getJoueur()[possession].getTimerJ() /
              1000
            );
            e2.getJoueur()[lastp].setPd(e2.getJoueur()[lastp].getPd() + 2);
            String[] val = {
              String.valueOf(possession),
              String.valueOf(lastp),
              "Decisive",
              id,
              String.valueOf(qt),
            };
            // possession = 0;
            System.out.println("J1 a la balle");

            trtmt.inserToTable("passe", val, null);
          }
        }
        String[] val = {
          String.valueOf(possession),
          "2",
          id,
          String.valueOf(qt),
        };
        String[] value = {
          String.valueOf(possession),
          String.valueOf(duree),
          id,
          String.valueOf(qt),
        };
        trtmt.inserToTable("tir", val, null);
        trtmt.inserToTable("duree", value, null);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }

    if (arg0.getKeyCode() == KeyEvent.VK_3) {
      try {
        int qt = frame
          .getBasket()
          .changeQuart_tps(trtmt.getdureeTotal(null, id));
        lastTimer = currentTimer;
        currentTimer = System.currentTimeMillis();
        if (possession < 5) {
          if (valReb == "D") {
            valReb = null;
            throw new Exception("IRREALISTE");
          }
          e1.getJoueur()[possession].setTimerJ(currentTimer - lastTimer);
          duree = e1.getJoueur()[possession].getTimerJ();
          // System.out.println(e1.getJoueur()[possession].getName()+" tire 1pts");
          if (lastp < 5) {
            System.out.println(
              "Duree du J " +
              e1.getJoueur()[possession].getName() +
              " qui a tire: " +
              e1.getJoueur()[possession].getTimerJ() /
              1000
            );
            e1.getJoueur()[lastp].setPd(e1.getJoueur()[lastp].getPd() + 3);
            String[] val = {
              String.valueOf(possession),
              String.valueOf(lastp),
              "Decisive",
              id,
              String.valueOf(qt),
            };
            // possession = 5;
            System.out.println("JA1 a la balle");
            trtmt.inserToTable("passe", val, null);
          }
        } else {
          if (valReb == "D") {
            valReb = null;
            throw new Exception("IRREALISTE");
          }
          e2.getJoueur()[possession].setTimerJ(currentTimer - lastTimer);
          duree = e2.getJoueur()[possession].getTimerJ();

          // System.out.println(e2.getJoueur()[possession].getName()+" tire 1pts");
          if (lastp >= 5) {
            System.out.println(
              "Duree du J " +
              e2.getJoueur()[possession].getName() +
              " qui a tire: " +
              e2.getJoueur()[possession].getTimerJ() /
              1000
            );
            e2.getJoueur()[lastp].setPd(e2.getJoueur()[lastp].getPd() + 3);
            String[] val = {
              String.valueOf(possession),
              String.valueOf(lastp),
              "Decisive",
              id,
              String.valueOf(qt),
            };
            // possession = 0;
            System.out.println("J1 a la balle");

            trtmt.inserToTable("passe", val, null);
          }
        }
        String[] val = {
          String.valueOf(possession),
          "3",
          id,
          String.valueOf(qt),
        };
        String[] value = {
          String.valueOf(possession),
          String.valueOf(duree),
          id,
          String.valueOf(qt),
        };
        trtmt.inserToTable("tir", val, null);
        trtmt.inserToTable("duree", value, null);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  @Override
  public void keyTyped(KeyEvent arg0) {
    // TODO Auto-generated method stub
  }

  @Override
  public void keyReleased(KeyEvent arg0) {
    // TODO Auto-generated method stub
  }

  public void addActionListn() {
    frame.getBasket().addKeyListener(this);
    //  Pause
    JButton button = frame.getBasket().getButtons();
    button.addActionListener(this);

    //  Play
    JButton buttonPlay = frame.getBasket().getButtonsPlay();
    buttonPlay.addActionListener(this);

    //  Equipe 1
    JButton[] button1 = frame.getBasket().getButtons1();
    for (int i = 0; i < 10; i++) {
      button1[i].addActionListener(this);
      button1[i].addKeyListener(this);
    }
  }

  public Frame getFrame() {
    return this.frame;
  }

  public void setFrame(Frame frame) {
    this.frame = frame;
  }
}
