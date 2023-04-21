package com.shstu.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 将中缀表达式转为后缀表达式并计算结果
 * 使用Java的栈
 * 支持多位数的括号加减乘除
 * 不支持负数和其他符号
 */
public class PolandNotation {
    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        //将中缀表达式存到infixExpressionList里
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList);

        //将infixExpressionList里的中缀表达式转为后缀表达式并存到suffixExpressionList里
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println(suffixExpressionList);

        //下面进行后缀表达式计算
        System.out.println(calculator(suffixExpressionList));
    }

    public static int calculator(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            if (s.matches("\\d+")) {
                stack.push(s);
            } else {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                if (s.equals("+")) {
                    stack.push(num2 + num1 + "");
                } else if (s.equals("-")) {
                    stack.push(num2 - num1 + "");
                } else if (s.equals("*")) {
                    stack.push(num2 * num1 + "");
                }else if (s.equals("/")) {
                    stack.push(num2 / num1 + "");
                }
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static List<String> toInfixExpressionList(String str) {
        List<String> list = new ArrayList<>();
        int index = 0;
        String num = "";
        while (index < str.length()) {
            if (isOperator(str.charAt(index))) {
                list.add(str.charAt(index) + "");
            } else {
                num += str.charAt(index);
                if (index + 1 >= str.length() || isOperator(str.charAt(index + 1))) {
                    list.add(num);
                    num = "";
                }
            }
            index++;
        }
        return list;
    }

    public static boolean isOperator(char ch) {
        return ch < 48 || ch > 57;
    }

    public static List<String> parseSuffixExpressionList(List<String> list) {
        List<String> resultList = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            if (s.matches("\\d+")) {
                resultList.add(s);
            } else if (s.equals("(")) {
                stack.push(s);
            } else if (s.equals(")")) {
                while (!stack.peek().equals("(")) {
                    resultList.add(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.empty() && priority(s, stack.peek())) {
                    resultList.add(stack.pop());
                }
                stack.push(s);
            }
        }
        while (!stack.empty()) {
            resultList.add(stack.pop());
        }
        return resultList;
    }

    public static boolean priority(String o1, String o2) {
        int p1 = 0;
        int p2 = 0;
        if (o1.equals("+") || o1.equals("-")) {
            p1 = 2;
        } else if (o1.equals("/") || o1.equals("*")) {
            p1 = 3;
        }
        if (o2.equals("+") || o2.equals("-")) {
            p2 = 2;
        } else if (o2.equals("/") || o2.equals("*")) {
            p2 = 3;
        }
        return p1 <= p2;
    }
}
