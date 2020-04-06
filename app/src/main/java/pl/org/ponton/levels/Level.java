package pl.org.ponton.levels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import pl.org.ponton.questions.QuestionWrapper;

public class Level {

    private static Level level = new Level();

    private Random random;

    public enum LevelType {LEVEL1, LEVEL2, LEVEL3}

    private List<QuestionWrapper> questions;

    private Level() {
        random = new Random();
    }

    public static Level getInstance() {
        return level;
    }

    public void loadLevel(LevelType levelType) {
        switch (levelType) {
            case LEVEL1:
                loadLevel1();
                break;
            case LEVEL2:
                loadLevel2();
                break;
            case LEVEL3:
                loadLevel3();
                break;
        }
    }

    private void loadLevel3() {
        questions = new ArrayList<>();
        questions.add(new QuestionWrapper("Jak nazywa się obecny prezydent Polski?",
                Arrays.asList("TAndrzej Duda","FAdrian Dupa","FBronisław Komorowski","FJarosław Kaczyński")));
        questions.add(new QuestionWrapper("Najlepszy energetyk to - ",
                Arrays.asList("TMonster","FTiger","FBlack","FBurn")));
        questions.add(new QuestionWrapper("Kto jest dyrektorem artystycznym Teatru Muzycznego Roma?",
                Arrays.asList("TWojciech Kępczyński","FSebastian Gonciarz","FEwa Bara","FEwelina Kulawiuk")));
        questions.add(new QuestionWrapper("Do których z poniższych teamów NIE należał Dylan Rieder?",
                Arrays.asList("TNike SB","FHuf","FFucking Awesome","FSpitfire")));
    }

    private void loadLevel2() {
        questions = new ArrayList<>();
        questions.add(new QuestionWrapper("2 * 2 = ?",
                Arrays.asList("F1","F6","T4","F22")));
        questions.add(new QuestionWrapper("Wonsz żeczny -",
                Arrays.asList("FW kolory tęczy","TJest niebezpieczny","FJest bardzo beczny","FMa ząbek mleczny")));
        questions.add(new QuestionWrapper("Nie będziemy tu wpierdalać z jednej wazy -",
                Arrays.asList("FBo szanujesz płazy","FZjadłbym sobie zrazy","FPotrzebuję metrowej gazy","TBo nie czaisz bazy")));
        questions.add(new QuestionWrapper("Jak nazywała się postać grana w spektaklu \"Piloci\" przez Zofię Nowakowską?",
                Arrays.asList("FBasia","FZofia","FGrażyna","TNina")));
    }

    private void loadLevel1() {
        //TODO ladowanie z pliku
        questions = new ArrayList<>();
        questions.add(new QuestionWrapper("1 + 1 = ?",
                Arrays.asList("F3","F11","T2","F0")));
        questions.add(new QuestionWrapper("Które z miast jest stolicą Polski?",
                Arrays.asList("FKraków","TWarszawa","FWrocław","FGniezno")));
        questions.add(new QuestionWrapper("Dokończ zdanie: \"Mamy tradycję...\"",
                Arrays.asList("FKochać Policję","FLubię delicje","FJeść pizzę","TJebać Policję")));
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
