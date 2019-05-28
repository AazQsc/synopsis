package by.khamenka.andersenlab.services.calculator;

import java.util.Stack;

/**
 * In this class, the infix entry is converted to postfix and
 * the '|' add after each number
 *
 * An algorithm for converting from an infix entry to a postfix:
 * "Data structures and Java algorithms" Robert Lafore.
 * */

public class InToPostfix {
    private Stack<Character> stack;
    private String input;
    private String output;
    /* From successive numbers make a full-number and
     * separate it with the sign '|'
     * used the numCount counter and the makeNumder () method
     * */
    private int numCount;

    public InToPostfix(String in) {
        this.input = in;
        this.numCount = 0;
        this.output = "";
        this.stack = new Stack<Character>();
    }

    // in postfix
    public String doTrans() {
        for (int j = 0; j < input.length(); j++) {
            char ch = input.charAt(j);

            switch (ch) {
                case '+':
                case '-':
                    makeNumder();
                    gotOper(ch, 1); // extraction of operators
                    break; // (priority 1)
                case '*':
                case '/':
                    makeNumder();
                    gotOper(ch, 2);
                    break; // (priority 2)
                case '(':
                    makeNumder();
                    stack.push(ch);
                    break;
                case ')':
                    makeNumder();
                    gotParen(ch);
                    break;
                default:
                    output = output + ch;// write to the output line
                    numCount++; // numCount> 1, while we write the number
                    break;
            }
        }
        // extract the remaining operators
        while (!stack.isEmpty()) {
            makeNumder();
            output = output + stack.pop();
        }
        return output; // return of postfix expression
    }

    // reading an operator from the input line
    private void gotOper(char opThis, int prec1) {
        while (!stack.isEmpty()) {
            char opTop = stack.pop();
            if (opTop == '(') {
                stack.push(opTop); // Вернуть '('
                break;
            } else { // operator
                int prec2; // priority of the new operator
                if (opTop == '+' || opTop == '-') // priority definition
                    prec2 = 1;
                else
                    prec2 = 2;
                /*
                 * If the priority of the new operator
                 * is less than the priority of the old one
                 * */
                if (prec2 < prec1) {
                    stack.push(opTop);
                    break;
                } else {
                    output = output + opTop;
                }
            }
        }
        stack.push(opThis);
    }

    // The closing parenthesis was read
    private void gotParen(char ch) {
        while (!stack.isEmpty()) {
            char chx = stack.pop();
            if (chx == '(') {
                break;
            } else {
                output = output + chx;
            }
        }
    }

    /*
     * We reset the counter when the operator appears.
     * Separate the number with '|'
     * */
    private void makeNumder() {
        if (numCount != 0) {
            numCount = 0;
            output = output + '|';
        }
    }

}
