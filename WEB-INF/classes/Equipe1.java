package basket;

public class Equipe1 {
    
    int score1 = 0;
    String name;
    Joueur[] joueur;
    int send;
    int receive;
    int timerE1;

    public Equipe1(String name, Joueur[] joueur) {
        this.name = name;
        this.joueur = joueur;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Joueur[] getJoueur() {
        return this.joueur;
    }

    public void setJoueur(Joueur[] joueur) {
        this.joueur = joueur;
    }

    public int getScore1() {
        return this.score1;
    }
    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getSend() {
        return this.send;
    }

    public void setSend(int send) {
        this.send = send;
    }

    public int getReceive() {
        return this.receive;
    }

    public void setReceive(int receive) {
        this.receive = receive;
    }

    public int getTimerE1() {
        return this.timerE1;
    }

    public void setTimerE1(int timerE1) {
        this.timerE1 = timerE1;
    }

}
