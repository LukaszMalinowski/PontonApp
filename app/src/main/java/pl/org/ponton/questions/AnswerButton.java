package pl.org.ponton.questions;

import android.content.Context;
import android.util.AttributeSet;

public class AnswerButton extends androidx.appcompat.widget.AppCompatButton {

    private boolean isCorrect = false;

    public AnswerButton(Context context) {
        super(context);
    }

    public AnswerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnswerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
