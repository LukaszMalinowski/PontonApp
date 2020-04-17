package pl.org.ponton.exceptions;

public class NoMoreWrongQuestionsException extends Exception {

    public NoMoreWrongQuestionsException() {
    }

    public NoMoreWrongQuestionsException(String message) {
        super(message);
    }

    public NoMoreWrongQuestionsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMoreWrongQuestionsException(Throwable cause) {
        super(cause);
    }
}
