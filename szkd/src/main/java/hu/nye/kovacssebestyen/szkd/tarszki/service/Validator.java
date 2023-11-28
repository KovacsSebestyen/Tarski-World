package hu.nye.kovacssebestyen.szkd.tarszki.service;

public class Validator {

    //

    public String check(String formula){
        if (formula.equals("(true)")) {
            return "(true)";
        }
        if (formula.equals("(false)")) {
           return "(false)";
        }

        char[] formulaChar = formula.toCharArray();

        //fölösleges zárójelek eltüntetése
        if (formulaChar[0] == '(' && formulaChar[formulaChar.length-1] == ')') {
            int depth = 0;
            boolean useless = true;
            for (int i = 0; i < formulaChar.length; i++) {
                if (formulaChar[i] == '(') {
                    depth++;
                }
                if (formulaChar[i] == ')') {
                    depth--;
                }
                if (depth == 0 && i != formulaChar.length - 1) {
                    i = formulaChar.length + 1;
                    useless = false;
                }
            }
            if (useless) {
                return check(formula.substring(1, formulaChar.length-1));       // (d(s) V s(f) V a(f)) --> d(s) V s(f) V a(f)
            }
        }

        //'<=>' keresése
        if (formula.contains("<=>")) {
            int depth = 0;
            int index = 0;
            boolean foundAtZeroDepth = false;
            for (int i = 0; i < formulaChar.length-2; i++) {
                if (formulaChar[i] == '(') {
                    depth++;
                }
                if (formulaChar[i] == ')') {
                    depth--;
                }
                if(formulaChar[i] == '<' && formulaChar[i] == '=' && formulaChar[i + 1] == '>' && depth == 0) {
                    index = i;
                    foundAtZeroDepth = true;
                    i = formulaChar.length;
                }
            }
            if (foundAtZeroDepth) {
                boolean left = false;
                boolean right = false;
                if (check(formula.substring(0, index)).equals("(true)")) {
                    left = true;
                }
                if (check(formula.substring(index + 3)) == "(true)") {
                    right = true;
                }
                if (left && right) {
                    return "(true)";
                }
                if (left && !right) {
                    return "(false)";
                }
                if (!left && right) {
                    return "(false)";
                }
                if (!left && !right) {
                    return "(true)";
                }
            }
        }

        //'=>' keresése
        if (formula.contains("=>")) {
            int depth = 0;
            int index = 0;
            boolean foundAtZeroDepth = false;
            for (int i = 0; i < formulaChar.length-1; i++) {
                if (formulaChar[i] == '(') {
                    depth++;
                }
                if (formulaChar[i] == ')') {
                    depth--;
                }
                if(formulaChar[i] == '=' && formulaChar[i + 1] == '>' && depth == 0) {
                    index = i;
                    foundAtZeroDepth = true;
                    i = formulaChar.length;
                }
            }
            if (foundAtZeroDepth) {
                boolean left = false;
                boolean right = false;
                if (check(formula.substring(0, index)).equals("(true)")) {
                    left = true;
                }
                if (check(formula.substring(index + 2)) == "(true)") {
                    right = true;
                }
                if (left && right) {
                    return "(true)";
                }
                if (left && !right) {
                    return "(false)";
                }
                if (!left && right) {
                    return "(true)";
                }
                if (!left && !right) {
                    return "(true)";
                }
            }
        }

        //'\/' keresése
        if (formula.contains("\\/")) {
            int depth = 0;
            int index = 0;
            boolean foundAtZeroDepth = false;
            for (int i = 0; i < formulaChar.length-1; i++) {
                if (formulaChar[i] == '(') {
                    depth++;
                }
                if (formulaChar[i] == ')') {
                    depth--;
                }
                if(formulaChar[i] == '\\' && formulaChar[i + 1] == '/' && depth == 0) {
                    index = i;
                    foundAtZeroDepth = true;
                    i = formulaChar.length;
                }
            }
            if (foundAtZeroDepth) {
                boolean left = false;
                boolean right = false;
                if (check(formula.substring(0, index)).equals("(true)")) {
                    left = true;
                }
                if (check(formula.substring(index + 2)) == "(true)") {
                    right = true;
                }
                if (left && right) {
                    return "(true)";
                }
                if (left && !right) {
                    return "(true)";
                }
                if (!left && right) {
                    return "(true)";
                }
                if (!left && !right) {
                    return "(false)";
                }
            }
        }

        //'/\' keresése
        if (formula.contains("/\\")) {
            int depth = 0;
            int index = 0;
            boolean foundAtZeroDepth = false;
            for (int i = 0; i < formulaChar.length-1; i++) {
                if (formulaChar[i] == '(') {
                    depth++;
                }
                if (formulaChar[i] == ')') {
                    depth--;
                }
                if(formulaChar[i] == '/' && formulaChar[i + 1] == '\\' && depth == 0) {
                    index = i;
                    foundAtZeroDepth = true;
                    i = formulaChar.length;
                }
            }
            if (foundAtZeroDepth) {
                boolean left = false;
                boolean right = false;
                if (check(formula.substring(0, index)).equals("(true)")) {
                    left = true;
                }
                if (check(formula.substring(index + 2)) == "(true)") {
                    right = true;
                }
                if (left && right) {
                    return "(true)";
                }
                if (left && !right) {
                    return "(false)";
                }
                if (!left && right) {
                    return "(false)";
                }
                if (!left && !right) {
                    return "(false)";
                }
            }
        }

        //'-' keresése
        if(formula.contains("-")) {
            int depth = 0;
            int index = 0;
            boolean foundAtZeroDepth = false;
            for (int i = 0; i < formulaChar.length; i++) {
                if (formulaChar[i] == '(') {
                    depth++;
                }
                if (formulaChar[i] == ')') {
                    depth--;
                }
                if(formulaChar[i] == '-' && depth == 0) {
                    index = i;
                    foundAtZeroDepth = true;
                    i = formulaChar.length;
                }
            }
            if (foundAtZeroDepth) {
                boolean negation = false;
                if (check(formula.substring(index + 1)).equals("(true)")) {
                    negation = true;
                }
                if (!negation) {
                    return "(true)";
                }
                if (negation) {
                    return "(false)";
                }
            }
        }

        return "";
    }

    public String small(Character shapeName){

        return "(true)";
    }
}
