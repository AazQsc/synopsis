package by.khamenka.andersenlab.services.calculator;

import java.util.ArrayList;

/**
 * In this class, some modification is made
 * to the entry entered by the user:
 * the spaces and the '=' character are deleted,
 * the comma is changed to a point, the '/' character changes to '\',
 * if the number is before the bracket, the multiplication sign is added,
 * if the first number in the expression is negative it conclude to brackets.
 *
 * The recording is not converted to postfix.
 */

public class Redactor {
    private String input;
    private ArrayList<Character> list;

    public Redactor(String in) {
        this.list = new ArrayList<Character>();
        this.input = in;
    }

    public String RedactInput() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            switch (ch) {
                case ',':
                    list.add('.');
                    break;
                case ' ':
                case '=':
                    break;
                case '/':
                case '\\':
                    list.add('/');
                    break;
                case '(':
                    if (i != 0 && input.charAt(i - 1) >= '0' && input.charAt(i - 1) <= '9') {
                        list.add('*');
                        list.add('(');
                        break;
                    } else {
                        list.add('(');
                        break;
                    }
                case '-':
                    int count;
                    if (i == 0 || input.charAt(i - 1) == '(') {
                        list.add('(');
                        list.add('0');
                        list.add('-');
                        count = 0;
                        for (int j = i + 1; j < input.length(); j++) {
                            ch = input.charAt(j);
                            if (ch >= '0' && ch <= '9') {
                                list.add(ch);
                                count++;
                            } else if (ch == ',' || ch == '.') {
                                list.add('.');
                                count++;
                            } else {
                                i = i + count;
                                break;
                            }
                        }
                        list.add(')');
                    } else {
                        list.add(ch);
                        break;
                    }
                    break;
                default:
                    list.add(ch);
                    break;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            output.append(list.get(i));
        }
        return output.toString();
    }

}

