package com.javarush.task.task34.task3404;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Рекурсия для мат. выражения
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); // Expected output: 0.5 6*/
//        solution.recurse("tan(2025 ^ 0.5)", 0); // Expected output: 1 2
//        solution.recurse("1+(1+(1+1)*(1+1))*(1+1)+1", 0); // Expected output: 12 8
//        solution.recurse("-2^(-2)", 0); // Expected output: -0.25 3
//        solution.recurse("-(-2^(-2))+2+(-(-2^(-2)))", 0); // Expected output: 2.5 10
//        solution.recurse("(-2)*(-2)", 0); // Expected output: 4 3
//        solution.recurse("sin(-30)", 0); // Expected output: -0.5 2
//        solution.recurse("cos(-30)", 0); // Expected output: 0.87 2
//        solution.recurse("tan(-30)", 0); // Expected output: -0.58 2
//        solution.recurse("2+8*(9/4-1.5)^(1+1)", 0); // Expected output: 6.48 6
//        solution.recurse("tan(44+sin(89-cos(180)^2))", 0); // Expected output: 1 6
//        solution.recurse("-cos(180)^2", 0); // Expected output: -1 3
//        solution.recurse("0+0.304", 0); // Expected output: 0.3 1
//        solution.recurse("-0", 0); // Expected output: 0 1
//        solution.recurse("sin(3.14/2)^2 + cos(3.14/2)^2", 0); // Expected output: 1 7
//        solution.recurse("sin(2*55) - 2*sin(55)*cos(55)", 0); // Expected output: 0 7

    }

    public void recurse(final String expression, int countOperation) {
        String text = expression.replaceAll("\\s", "");

        String numberPattern = "[-]?\\d+[.]?\\d*";
        // если осталось только число, печатаем результат и выходим
        Pattern pattern = Pattern.compile(numberPattern);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find() && matcherText(matcher, text).length() == text.length()) {
            System.out.println(String.format("%s %d", text, countOperation));
            return;
        }

        //если одна их тригонометрических функций, и аргумент - число, вычисляем ее
        String sinPattern = "(sin|tan|cos)\\(" + numberPattern + "\\)";
        pattern = Pattern.compile(sinPattern);
        matcher = pattern.matcher(text);
        if (matcher.find()) {
            if (matcherText(matcher, text).charAt(4) == '-') {
                countOperation++;
            }
            recurse(replaceText(text, matcher, calcSin(matcherText(matcher, text))), ++countOperation);
            return;
        }

        //если есть скобки, берем самые вложенные и считаем результат
        String parenthesesPattern = "\\([^()]+\\)";
        pattern = Pattern.compile(parenthesesPattern);
        matcher = pattern.matcher(text);
        if (matcher.find()) {
            //если в скобках готовое число, просто убираем скобки
            String expr = matcherText(matcher, text);
            if (expr.substring(1, expr.length() - 1).matches(numberPattern)) {
                recurse(replaceText(text, matcher, expr.substring(1, expr.length() - 1)), countOperation);
                return;
            }
            List<Integer> opers = new ArrayList<>();
            String newExpression = makeOperation(expr, opers);
            countOperation += opers.get(0);
            recurse(replaceText(text, matcher, newExpression), countOperation);
            return;
        }

        //иначе выполняем одну из арифметических операций
        List<Integer> opers = new ArrayList<>();
        String newExpression = makeOperation(text, opers);
        countOperation += opers.get(0);
        recurse(newExpression, countOperation);
    }

    private String matcherText(Matcher matcher, String text) {
        return text.substring(matcher.start(), matcher.end());
    }

    private String replaceText(String sourceText, Matcher matcher, String substText) {
        StringBuilder result = new StringBuilder(sourceText);
        result.replace(matcher.start(), matcher.end(), substText);
        return result.toString();
    }

    private String formatResult(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        return df.format(value);
    }

    private String calcSin(String text) {
        String result = null;
        String functionName = text.toLowerCase().substring(0, 3);
        double degrees = Double.parseDouble(text.substring(4, text.length() - 1));
        switch (functionName) {
            case "sin":
                result = formatResult(Math.sin(Math.toRadians(degrees)));
                break;
            case "cos":
                result = formatResult(Math.cos(Math.toRadians(degrees)));
                break;
            case "tan":
                result = formatResult(Math.tan(Math.toRadians(degrees)));
                break;
        }
        return result;
    }

    private String makeOperation(String expression, List<Integer> countOperations) {
        String numberPattern = "[-]?\\d+[.]?\\d*";

        String powPattern = numberPattern + "(\\^)" + numberPattern;
        String result = makeOperationByPattern(expression, powPattern, "^", countOperations);
        if (result != null) {
            return result;
        }

        String multDivPattern = numberPattern + "(\\*|\\/)" + numberPattern;
        result = makeOperationByPattern(expression, multDivPattern, "*/", countOperations);
        if (result != null) {
            return result;
        }

        String twoMinusesPattern = "--";
        // два минуса заменяем на плюс
        Pattern pattern = Pattern.compile(twoMinusesPattern);
        Matcher matcher = pattern.matcher(expression);
        if (matcher.find()) {
            countOperations.add(1);
            result = replaceText(expression, matcher, "");
            return result;
        }

        String plusMinusPatterb = numberPattern + "(\\+|-)" + numberPattern;
        result = makeOperationByPattern(expression, plusMinusPatterb, "+-", countOperations);
        if (result != null) {
            return result;
        }
        return expression;
    }

    private String makeOperationByPattern(String expression, String patternString, String oper, List<Integer> countOperations) {
        String result = null;
        int counter = 0;
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(expression);
        if (matcher.find()) {
            String splitter = "";
            switch (oper) {
                case "^":
                    splitter = "\\^";
                    break;
                case "*/":
                    splitter = (matcherText(matcher, expression).contains("*")) ? "\\*" : "/";
                    break;
                case "+-":
                    splitter = (matcherText(matcher, expression).contains("+")) ? "\\+" : "-";
                    break;
            }

            String[] args = matcherText(matcher, expression).split(splitter);
            double arg1 = Double.parseDouble(args[0]);
            double arg2 = Double.parseDouble(args[1]);
            int powSign = 1;
            if (arg1 < 0) {
                counter++;
                powSign = -1;
            }
            if (arg2 < 0) {
                counter++;
            }
            countOperations.add(++counter);
            switch (splitter) {
                case "\\^":
                    return replaceText(expression, matcher, formatResult(Math.pow(arg1, arg2) * powSign));
                case "\\*":
                    return replaceText(expression, matcher, formatResult(arg1 * arg2));
                case "/":
                    return replaceText(expression, matcher, formatResult(arg1 / arg2));
                case "\\+":
                    return replaceText(expression, matcher, formatResult(arg1 + arg2));
                case "-":
                    return replaceText(expression, matcher, formatResult(arg1 - arg2));
            }
        }
        return result;
    }

    public Solution() {
        //don't delete
    }
}
