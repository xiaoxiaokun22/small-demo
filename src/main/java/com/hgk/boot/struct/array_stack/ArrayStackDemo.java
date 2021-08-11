package com.hgk.boot.struct.array_stack;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ArrayStackDemo {
    public static void main(String[] args){
        ArrayStack<Integer> stack = new ArrayStack<>(10);
        stack.push(3);
        stack.push(103);
        stack.push(21);
        stack.push(81);
        stack.push(57);
        stack.printStack();
        //中缀表达式计算
        String expression = "50+6+(8*7+4+11*3)-10/2";//101
        String centerRes = calculateCenter(expression);
        System.out.printf("中缀表达式计算 %s = %s\n", expression,centerRes);
        //中缀转后缀
        String afterExpression = centerToAfterExpression(expression);
        System.out.printf("中缀转后缀 %s => %s\n", expression,afterExpression);
        //后缀表达式（逆波兰表达式）计算
        System.out.printf("后缀表达式计算 %s = %s\n", afterExpression,calculateAfter(afterExpression));

    }

    /**
     * 中缀表达式计算
     * 例如：50+6+(8*7+4)-10/2
     * 支持：+-* /(),只讨论整数
     * 注意：这里使用java自带的栈，自己写的ArrayStack的栈没有写扩容方案，需要自定义栈大小，不太方便
     * 关键步骤：
     * 1.表达式放入list
     * 2.两个栈：一个数据栈，一个符号栈（最终结构是数据栈中）
     * 3.循环表达式，遇到数字就放入栈中。
     *  遇到符号，如果这个符号优先级小于等于栈顶的符号优先级时，就计算前面栈中的两个数据。
     *  1>符号栈为空，直接放入符号栈
     *  2>遇到左括号，左括号优先级最高，直接放入符号栈
     *  3>遇到右括号，依次pop符号栈的数据，放入数据栈中，直到pop出符号栈的左括号（其实就是左括号），括号都抛弃不入栈。
     * 5.表达式遍历完后，符号栈和数据栈还有元素，依次从符号栈栈顶pop计算数据，放入数据栈中
     * @param expression
     * @return
     */
    public static String calculateCenter(String expression){
        Stack<String> numStack = new Stack<>();
        Stack<String> symbolStack = new Stack<>();
        List<String> expressionList = expressionToList(expression);
        System.out.println( "\n表达式：" + expressionList);
        for(String s: expressionList){
            if(isSymbol(s)){
                int curPri = getPriority(s);
                String inSymbol = null;
                if(!symbolStack.isEmpty())
                    inSymbol = symbolStack.peek();
                if(inSymbol != null && !isBrackets(inSymbol) && curPri <= getPriority(inSymbol)){
                    //先运算前面的数值,num出栈两个数，symbol出栈一个符号
                    String calcuRes = calculateNum(numStack.pop(),numStack.pop(),symbolStack.pop());
                    numStack.push(calcuRes);
                }
                if(s.equals(")")){
                    String calcuRes = "";
                    while (!symbolStack.isEmpty()){
                        if(symbolStack.peek().equals("("))
                            break;
                        String num1 = numStack.pop();
                        String num2 = numStack.pop();
                        String curSymbol = symbolStack.pop();
                        if(Arrays.asList(new String[]{"-", "/"}).contains(curSymbol)){
                            calcuRes = calculateNum(num2,num1,curSymbol);
                        }else{
                            calcuRes = calculateNum(num1,num2,curSymbol);
                        }
                        numStack.push(calcuRes);
                    }
                    if(!symbolStack.isEmpty())
                        symbolStack.pop();
                }else{
                    symbolStack.push(s);
                }
            }else{
                numStack.push(s);
            }
        }
        //表达式扫描完后，栈中肯定还有元素，就按顺序pop计算栈中的元素。
        String calcuRes = "";
        while (!symbolStack.isEmpty()){
            String num1 = numStack.pop();
            String num2 = numStack.pop();
            String curSymbol = symbolStack.pop();
            if(Arrays.asList(new String[]{"-", "/"}).contains(curSymbol)){
                calcuRes = calculateNum(num2,num1,curSymbol);
            }else{
                calcuRes = calculateNum(num1,num2,curSymbol);
            }
            numStack.push(calcuRes);
        }
        return numStack.peek();
    }


    /**
     * 计算-加减乘除
     * @return
     */
    public static String calculateNum(String num1,String num2,String option){
        BigDecimal b1 = new BigDecimal(num1);
        BigDecimal b2 = new BigDecimal(num2);
        BigDecimal res = BigDecimal.ZERO;
        switch (option){
            case "+":
                res = b1.add(b2);
                break;
            case "-":
                res = b1.subtract(b2);
                break;
            case "*":
                res = b1.multiply(b2);
                break;
            case "/":
                res = b1.divide(b2);//注意 b2/b1
                break;
            default:
                break;
        }
        return String.valueOf(res);
    }

    /**
     * 将表达式转换为list
     * 主要解决数值是多位数的问题，还有表达式不规范的问题
     * @return
     */
    public static ArrayList<String> expressionToList(String expression){
         char[] chars = expression.toCharArray();
         ArrayList<String> res = new ArrayList<>();

        StringBuffer sb = new StringBuffer();
        char lastValidChar = ' ';
         for(char c: chars){
            if(c == ' ')
                continue;
            if(isSymbol(String.valueOf(c))){
                if(!sb.toString().isEmpty())
                    res.add(sb.toString());
                res.add(String.valueOf(c));
                sb = new StringBuffer();
            }else{
                //多位数
                sb.append(c);
                lastValidChar = c;
            }
         }
         res.add(String.valueOf(lastValidChar));
         return res;
    }

    /**
     * 是否为括号
     * @param str
     */
    public static boolean isBrackets(String str){
        String[] sysmbolArr = {"(",")"};
        List<String> sysmbolList = new ArrayList<>(Arrays.asList(sysmbolArr));
        return sysmbolList.contains(str);
    }

    /**
     * 是否为计算符号
     * @param str
     */
    public static boolean isSymbol(String str){
        String[] sysmbolArr = {"+","-","*","/","(",")"};
        List<String> sysmbolList = new ArrayList<>(Arrays.asList(sysmbolArr));
        return sysmbolList.contains(str);
    }

    /**
     * 获取计算符号的优先级
     * 数值越大，优先级越高
     * @return
     */
    public static int getPriority(String str){
        int priority = 0;
        switch (str){
            case ")":
                priority = 0;
                break;
            case "+":
            case "-":
                priority = 1;
                break;
            case "*":
            case "/":
                priority = 2;
                break;
            case "(":
                priority = 99;
                break;
            default:
                break;
        }
        return priority;
    }

    /**
     * 中缀转后缀表达式
     * 50+6+(8*7+4)-10/2
     * => 50 6 + 8 7 * 4 + + 10 2 / -
     * 1.表达式放入list
     * 2.两个栈：一个数据栈，一个符号栈（最终结构是数据栈的倒序）
     * 3.循环表达式，遇到数字就放入栈中。
     *  遇到符号，如果这个符号优先级小于等于栈顶的符号优先级时，把符号栈顶的元素放入数据栈，再把当前符号入符号栈。
     *  1>符号栈为空，直接放入符号栈
     *  2>遇到左括号，左括号优先级最高，直接放入符号栈
     *  3>遇到右括号，依次pop符号栈的数据，放入数据栈中，直到遇到左括号，括号都抛弃不入栈。
     * 4.表达式遍历完后，符号栈还有元素，依次从栈顶pop放入数据栈中
     * 5.数据栈中的数据倒序（栈底开始）就是最终的结果
     *
     * @return
     */
    public static String centerToAfterExpression(String expression){
        Stack<String> numStack = new Stack<>();
        Stack<String> symbolStack = new Stack<>();
        List<String> list = expressionToList(expression);
        for(String s: list){
            if(isSymbol(s)){
                if(symbolStack.isEmpty()){
                    symbolStack.push(s);
                    continue;
                }
                if(s.equals("(") || symbolStack.peek().equals("(")){
                    symbolStack.push(s);
                    continue;
                }
                if(s.equals(")")){
                    while (!symbolStack.isEmpty()){
                        if(symbolStack.peek().equals("("))
                            break;
                        numStack.push(symbolStack.pop());
                    }
                    if(!symbolStack.isEmpty())
                        symbolStack.pop();
                }else{
                    if(getPriority(s) <= getPriority(symbolStack.peek())){
                        numStack.push(symbolStack.pop());
                        symbolStack.push(s);
                    }else{
                        symbolStack.push(s);
                    }
                }
            }else{
                numStack.push(s);
            }
        }

        while (!symbolStack.isEmpty()){
            numStack.push(symbolStack.pop());
        }
        StringBuffer sb = new StringBuffer();
        numStack.stream().forEach(val ->{ sb.append(val + " "); });
        return sb.toString();
    }

    /**
     * 逆波兰表达式计算（逆波兰算法）
     * 支持：+-* /(),只讨论整数
     * 步骤：
     * 1.一个数据栈
     * 2.循环表达式
     * 3.遇到数字直接入栈，遇到符号，pop两个数据直接计算，再放入栈中
     * @param expression
     * @return
     */
    public static String calculateAfter(String expression){
        List<String> list = Arrays.asList(expression.split(" "));
        Stack<String> numStack = new Stack<>();
        String calcuRes = "";
        for(String s: list){
            if(isSymbol(s)){
                String num1 = numStack.pop();
                String num2 = numStack.pop();
                if(Arrays.asList(new String[]{"-", "/"}).contains(s)){
                    calcuRes = calculateNum(num2,num1,s);
                }else{
                    calcuRes = calculateNum(num1,num2,s);
                }
                numStack.push(calcuRes);
            }else{
                numStack.push(s);
            }
        }
        return numStack.peek();
    }


}
