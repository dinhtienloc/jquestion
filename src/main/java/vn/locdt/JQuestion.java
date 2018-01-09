package vn.locdt;

import jline.console.ConsoleReader;
import vn.locdt.exception.ConsoleNotInitializeException;
import vn.locdt.element.question.InputQuestion;
import vn.locdt.element.question.Question;
import vn.locdt.element.question.SingleChoiceQuestion;
import vn.locdt.answer.Answer;

import java.io.IOException;
import java.util.*;

public class JQuestion {
    private static ConsoleReader console;

    private List<Question> questions;

    static {
        try {
            console = new ConsoleReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JQuestion() throws IOException {
        this.questions = new ArrayList<>();
    }

    public static ConsoleReader getConsole() throws ConsoleNotInitializeException {
        if (console == null) throw new ConsoleNotInitializeException("Console is not initialized.");
        return console;
    }


    public JQuestion addInputQuestion(String title, String name) {
        this.questions.add(new InputQuestion(title, name));
        return this;
    }

    public JQuestion addInputQuestion(InputQuestion inputQuestion) {
        this.questions.add(inputQuestion);
        return this;
    }

    public JQuestion addSingleChoiceQuestion(SingleChoiceQuestion singleChoiceQuestion) {
        this.questions.add(singleChoiceQuestion);
        return this;
    }

    public Map<String, String> prompt() throws IOException, ConsoleNotInitializeException {
        Map<String, String> resultMap = new LinkedHashMap<>();

        if (questions.size() == 0)
            return resultMap;

        for (Question q : questions) {
            Answer answer = q.prompt();
            resultMap.put(answer.getName(), answer.getValue());
//            System.out.println(answer);
        }

        return resultMap;
    }
}
