package pl.org.ponton.user;

public class User {

    private static User object;

    private int score;

    private User () {
        this(0);
    }

    private User (int score) {
        this.score = score;
    }

    public static User loadUser() {
        //TODO wczytaj usera z pliku czy czegos tam
        if(object == null)
            object = new User();

        return object;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
