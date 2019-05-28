package by.khamenka.andersenlab.services.calculator;

import java.util.EmptyStackException;

public class Calculator {
    private Redactor redactor;
    private InToPostfix inToPost;
    private PostfixCalculator postfixCalc;
    private String answer;

    public String calculate(String input) {
        redactor = new Redactor(input);
        answer = redactor.RedactInput();

        inToPost = new InToPostfix(answer);
        answer = inToPost.doTrans();

        postfixCalc = new PostfixCalculator(answer);
        try {
            answer = postfixCalc.Calculate();
        } catch (EmptyStackException e) {
            return "This expression can not be calculated!";
        }

        return answer;
    }

}

