package by.khamenka.andersenlab.services.calculator;

import java.util.Stack;

/**
 * In this class, the postfix record is read and
 * calculate mathematical result.
 */

public class PostfixCalculator {

    private String input;
    private Stack<Double> stackDouble;


    public PostfixCalculator(String in) {
        this.stackDouble = new Stack<Double>();
        this.input = in;
    }

    public String Calculate() {
        char ch;
        int pipeWaiting = 0; // Waiting for a meeting with the sign '|'
        String inDouble;
        Double num1, num2;
        Double findAns;
        String answer;

        for (int j = 0; j < input.length(); j++) {
            ch = input.charAt(j);

            if (ch >= '0' && ch <= '9') { // if number
                inDouble = "";
                pipeWaiting++;
                // add number Double in stack --------------
                if (pipeWaiting == 1) {
                    for (int i = j; i < input.length(); i++) {
                        if (input.charAt(i) == '|') {
                            break;
                        }
                        inDouble = inDouble + input.charAt(i);
                    }
                    stackDouble.push(Double.parseDouble(inDouble));
                }
                //---------------------------------------------
            } else if (ch == '|') {
                pipeWaiting = 0;
            } else if (ch == '.') {
                continue;
            } else { // if operator
                num2 = stackDouble.pop();
                num1 = stackDouble.pop();

                switch (ch) {
                    case '+':
                        findAns = num1 + num2;
                        break;
                    case '-':
                        findAns = num1 - num2;
                        break;
                    case '*':
                        findAns = num1 * num2;
                        break;
                    case '/':
                        findAns = num1 / num2;
                        break;
                    default:
                        findAns = (double) 0;
                }
                // add Intermediate value to stack
                stackDouble.push(findAns);
            }
        }
        findAns = stackDouble.pop();
        answer = "" + findAns;
        return answer;
    }
}

