package com.shstu.calculator;


/**
 * 中缀表达式的计算
 * 使用的栈为链表栈
 * 支持正多位数,括号,加减乘除
 * 不支持负数,其他符号
 */
public class ListStack {
    private ListNode top = null;

    public void push(ListNode node) {
        if (top != null) {
            node.next = top;
        }
        top = node;
    }

    public void list() {
        ListNode curNode = top;
        while (curNode != null) {
            System.out.print(curNode.value + "\t");
            curNode = curNode.next;
        }
        System.out.println();
    }

    public String pop() {
        if (top == null) {
            return null;
        }
        String op = top.value;
        top = top.next;
        return op;
    }

    public static boolean isOperator(char ch) {
        return ch < 48 || ch > 57;
    }

    public static int compute(int num1, int num2, String operator) {
        int res = 0;
        switch (operator) {
            case "+":
                res = num1 + num2;
                break;
            case "-":
                res = num2 - num1;
                break;
            case "*":
                res = num1 * num2;
                break;
            case "/":
                res = num2 / num1;
                break;
        }
        return res;
    }

    public static boolean priority(String o1, String o2) {
        int p1 = 0;
        int p2 = 0;
        if (o1.equals("+") || o1.equals("-")) {
            p1 = 1;
        } else if (o1.equals("/") || o1.equals("*")) {
            p1 = 2;
        }
        if (o2.equals("+") || o2.equals("-")) {
            p2 = 1;
        } else if (o2.equals("/") || o2.equals("*")) {
            p2 = 2;
        }
        return p1 <= p2;
    }

    public static void main(String[] args) {

        String expression = "1-2*(3-1*2)+1";
        ListStack numStack = new ListStack();
        ListStack operatorStack = new ListStack();
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int res = 0;
        String operator = "";
        char ch = ' ';
        String num = "";
        while (index < expression.length()) {
            ch = expression.charAt(index);
            if (isOperator(ch)) {
                if (operatorStack.top == null || ch == '(') {
                    operatorStack.push(new ListNode(ch + ""));
                } else if (ch == ')') {
                    while (!operatorStack.top.value.equals("(")) {
                        num1 = Integer.parseInt(numStack.pop());
                        num2 = Integer.parseInt(numStack.pop());
                        operator = operatorStack.pop();
                        res = compute(num1, num2, operator);
                        numStack.push(new ListNode(res + ""));
                    }
                    operatorStack.pop();
                } else if (priority(ch + "", operatorStack.top.value)) {
                    while (operatorStack.top != null && priority(ch + "", operatorStack.top.value)) {
                        num1 = Integer.parseInt(numStack.pop());
                        num2 = Integer.parseInt(numStack.pop());
                        operator = operatorStack.pop();
                        res = compute(num1, num2, operator);
                        numStack.push(new ListNode(res + ""));
                    }
                    operatorStack.push(new ListNode(ch + ""));
                } else {
                    operatorStack.push(new ListNode(ch + ""));
                }
            } else {
                num += ch;
                if (index + 1 >= expression.length() || isOperator(expression.charAt(index + 1))) {
                    numStack.push(new ListNode(num));
                    num = "";
                }
            }
            index++;
        }
        while (operatorStack.top != null) {
            num1 = Integer.parseInt(numStack.pop());
            num2 = Integer.parseInt(numStack.pop());
            operator = operatorStack.pop();
            res = compute(num1, num2, operator);
            numStack.push(new ListNode(res + ""));
        }
        System.out.println(numStack.pop());
    }
}

class ListNode {
    public String value;
    public ListNode next;

    public ListNode(String value) {
        this.value = value;
    }
}
