package task3;

import java.util.Date;
import java.util.Optional;

public class OptionalsTasks {
    public static void main(String[] args) {
        TestCase testCase = new TestCase();
        testCase.setWord("some text..");

        Optional<String> word = testCase.getWord();

        boolean IsPresent = word.isPresent();

        word.ifPresent(System.out::println);

        boolean empty = word.isEmpty();
    }
}

class TestCase {
    private Optional<String> word;

    public TestCase(){
        word = Optional.empty();
    }

    public Optional<String> getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = Optional.of(word);
    }
}


