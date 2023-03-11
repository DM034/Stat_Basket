package basket;

public class Equipe2 {
    
    int score2 = 0;
    String name;
    Joueur[] joueur;
    int send;
    int receive;
    int timerE2;
    
    public Equipe2(String name, Joueur[] joueur) {
        this.name = name;
        this.joueur = joueur;
    }

    public Joueur[] getJoueur() {
        return this.joueur;
    }

    public void setJoueur(Joueur[] joueur) {
        this.joueur = joueur;
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

    public Equipe2(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getScore2() {
        return this.score2;
    }
    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public int getTimerE2() {
        return this.timerE2;
    }

    public void setTimerE2(int timerE2) {
        this.timerE2 = timerE2;
    }

}
