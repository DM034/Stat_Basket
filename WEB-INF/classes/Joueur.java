package basket;

public class Joueur {

  String name;
  int ro;
  int rd;
  int pd;
  long timerJ;

  public Joueur(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Joueur name(String name) {
    setName(name);
    return this;
  }

  public int getRo() {
    return this.ro;
  }

  public void setRo(int ro) {
    this.ro = ro;
  }

  public int getRd() {
    return this.rd;
  }

  public void setRd(int rd) {
    this.rd = rd;
  }

  public int getPd() {
    return this.pd;
  }

  public void setPd(int pd) {
    this.pd = pd;
  }

  public long getTimerJ() {
    return this.timerJ;
  }

  public void setTimerJ(long timerJ) {
    this.timerJ = timerJ;
  }
}
