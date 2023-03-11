package timer;

public class Timer {

  long tempsDepart = 0;
  long tempsFin = 0;
  long pauseDepart = 0;
  long pauseFin = 0;
  long duree = 0;

  public void start() {
    tempsDepart = System.currentTimeMillis();
    tempsFin = 0;
    pauseDepart = 0;
    pauseFin = 0;
    duree = 0;
  }

  public void pause() {
    if (tempsDepart == 0) {
      return;
    }
    pauseDepart = System.currentTimeMillis();
  }

  public void resume() {
    if (tempsDepart == 0) {
      return;
    }
    if (pauseDepart == 0) {
      return;
    }
    pauseFin = System.currentTimeMillis();
    tempsDepart = tempsDepart + pauseFin - pauseDepart;
    tempsFin = 0;
    pauseDepart = 0;
    pauseFin = 0;
    duree = 0;
  }

  public void stop() {
    if (tempsDepart == 0) {
      return;
    }
    tempsFin = System.currentTimeMillis();
    duree = (tempsFin - tempsDepart) - (pauseFin - pauseDepart);
    tempsDepart = 0;
    tempsFin = 0;
    pauseDepart = 0;
    pauseFin = 0;
  }

  public long getDureeSec() {
    return duree / 1000;
  }

  public long getDureeMs() {
    return duree;
  }

  public String getDureeTxt() {
    return timeToHMS(getDureeSec());
  }

  public static String timeToHMS(long tempsS) {
    // IN : (long) temps en secondes
    // OUT : (String) temps au format texte : "1 h 26 min 3 s"

    int h = (int) (tempsS / 3600);
    int m = (int) ((tempsS % 3600) / 60);
    int s = (int) (tempsS % 60);

    String r = "";

    if (h > 0) {
      r += h + " h ";
    }
    if (m > 0) {
      r += m + " min ";
    }
    if (s > 0) {
      r += s + " s";
    }
    if (h <= 0 && m <= 0 && s <= 0) {
      r = "0 s";
    }

    return r;
  }

  public long getTempsDepart() {
    return this.tempsDepart;
  }

  public void setTempsDepart(long tempsDepart) {
    this.tempsDepart = tempsDepart;
  }

  public long getTempsFin() {
    return this.tempsFin;
  }

  public void setTempsFin(long tempsFin) {
    this.tempsFin = tempsFin;
  }

  public long getPauseDepart() {
    return this.pauseDepart;
  }

  public void setPauseDepart(long pauseDepart) {
    this.pauseDepart = pauseDepart;
  }

  public long getPauseFin() {
    return this.pauseFin;
  }

  public void setPauseFin(long pauseFin) {
    this.pauseFin = pauseFin;
  }

  public long getDuree() {
    return this.duree;
  }

  public void setDuree(long duree) {
    this.duree = duree;
  }
}
