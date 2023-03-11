package view;

import basket.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import timer.*;

public class Basket extends JPanel {

  JButton buttons;
  JButton buttonsPlay;
  JButton buttonsStart;
  JButton buttonsStop;
  JButton[] buttons1;
  Equipe1 e1;
  Equipe2 e2;
  long timer;
  int quart_tps = 0;

  public Basket(Equipe1 e1, Equipe2 e2) {
    this.e1 = e1;
    this.e2 = e2;
    this.timer = System.currentTimeMillis();

    //  Pause
    buttons = new JButton("Pause");
    buttons.setBackground(Color.red);
    this.add(buttons);
    buttons.setBounds(650, 0, 100, 50);

    //  Play
    buttonsPlay = new JButton("Play");
    buttonsPlay.setBackground(Color.green);
    this.add(buttonsPlay);
    buttonsPlay.setBounds(650, 75, 100, 50);

    //  Equipe1
    buttons1 = new JButton[10];
    for (int i = 0; i < buttons1.length; i++) {
      buttons1[i] = new JButton(e1.getJoueur()[i].getName());
      buttons1[i].setBackground(Color.white);
      this.add(buttons1[i]);
    }
    buttons1[0].setBounds(20, 0, 150, 50);
    buttons1[1].setBounds(20, 80, 150, 50);
    buttons1[2].setBounds(20, 180, 150, 50);
    buttons1[3].setBounds(20, 280, 150, 50);
    buttons1[4].setBounds(20, 370, 150, 50);
    buttons1[5].setBounds(1230, 0, 150, 50);
    buttons1[6].setBounds(1230, 80, 150, 50);
    buttons1[7].setBounds(1230, 180, 150, 50);
    buttons1[8].setBounds(1230, 280, 150, 50);
    buttons1[9].setBounds(1230, 370, 150, 50);

    setLayout(null);
    setBounds(0, 0, 1400, 500);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    //  Field
    g.setColor(Color.blue);
    g.fillRect(200, 0, 1000, 500);

    //  Line and circle
    g.setColor(Color.white);
    g.drawOval(625, 150, 150, 150);
    g.fillRect(698, 0, 6, 500);
  }

  public JButton getButtons() {
    return this.buttons;
  }

  public void setButtons(JButton buttons) {
    this.buttons = buttons;
  }

  public JButton[] getButtons1() {
    return this.buttons1;
  }

  public void setButtons1(JButton[] buttons1) {
    this.buttons1 = buttons1;
  }

  public Equipe1 getE1() {
    return this.e1;
  }

  public void setE1(Equipe1 e1) {
    this.e1 = e1;
  }

  public Equipe2 getE2() {
    return this.e2;
  }

  public void setE2(Equipe2 e2) {
    this.e2 = e2;
  }

  public JButton getButtonsPlay() {
    return this.buttonsPlay;
  }

  public void setButtonsPlay(JButton buttonsPlay) {
    this.buttonsPlay = buttonsPlay;
  }

  public long getTimer() {
    return this.timer;
  }

  public void setTimer(long timer) {
    this.timer = timer;
  }

  public void setQuart_tps(int quart_tps) {
    this.quart_tps = quart_tps;
  }

  public int changeQuart_tps(int tps) {
    int qt = 0;
    if (tps <= 20000) {
      qt = 1;
    } else if (tps > 20000 && tps <= 40000) {
      qt = 2;
    } else if (tps > 40000 && tps <= 60000) {
      qt = 3;
    } else if (tps > 60000 && tps <= 80000) {
      qt = 4;
    }
    return qt;
  }
}
