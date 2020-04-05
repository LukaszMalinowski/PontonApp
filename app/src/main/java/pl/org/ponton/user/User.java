package pl.org.ponton.user;

public class User {

    private static User user;

    private int score;

    private User () {
        this(0);
    }

    private User (int score) {
        this.score = score;
    }

    public static User loadUser() {
        //TODO wczytaj usera z pliku czy czegos tam
        if(user == null)
            user = new User();

        return user;
    }

    public static User getUser() {
        return user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if(score < 0)
            score = 0;
        this.score = score;
    }
}
