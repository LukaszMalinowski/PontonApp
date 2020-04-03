package pl.org.ponton.levels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import pl.org.ponton.questions.QuestionWrapper;

public class Level1 {

    private static Level1 object = new Level1();

    private Random random;

    private List<QuestionWrapper> questions;

    private Level1() {
        loadLevel();
        random = new Random();
    }

    public static Level1 getInstance() {
        return object;
    }

    public void loadLevel() {
        questions = new ArrayList<>();
        questions.add(new QuestionWrapper("1 + 1 = ?", Arrays.asList("F3","F11","T2","F0")));
        questions.add(new QuestionWrapper("Które z miast jest stolicą Polski?", Arrays.asList("FKraków","TWarszawa","FWrocław","FGniezno")));
        questions.add(new QuestionWrapper("Dokończ zdanie: \"Mamy tradycję...\"", Arrays.asList("FKochać Policję","FLubię delicje","FJeść pizzę","TJebać Policję")));
        questions.add(new QuestionWrapper("Dokończ zdanie: \"To niespotykane, każdy chciałby mnie dla siebie, a ja wolę...\"",
                Arrays.asList("FMoją mamę","FMonogamię","FJeść Komara","TPoligamię")));
    }

    public QuestionWrapper getQuestion() throws Exception{
        if(questions == null || questions.isEmpty())
            throw new Exception("Brak pytań");

        int randIndex = random.nextInt(questions.size());

        QuestionWrapper questionWrapper = questions.get(randIndex);

        questions.remove(randIndex);

        return questionWrapper;
    }
}
