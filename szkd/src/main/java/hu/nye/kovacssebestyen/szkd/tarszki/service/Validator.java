package hu.nye.kovacssebestyen.szkd.tarszki.service;

import hu.nye.kovacssebestyen.szkd.tarszki.data.model.*;

public class Validator {

    private TarskiData tarskiData;

    public Validator(TarskiData tarskiData) {
        this.tarskiData = tarskiData;
    }

    public Validator() {
    }

    public TarskiData getTarskiData() {
        return tarskiData;
    }

    public String check(String formula){
        System.out.println(formula);

        if(formula.contains(" ")) {
            formula.replace(" ", "");
        }
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
        if (formula.contains("-")) {
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

        //'A' keresése
        if (formula.contains("A")) {
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
                if(formulaChar[i] == 'A' && depth == 0) {
                    index = i;
                    foundAtZeroDepth = true;
                    i = formulaChar.length;
                }
            }
            if (foundAtZeroDepth) {
                char variant = formulaChar[index + 1];
                boolean all = true;
                String help = "";
                for (int i = 0; i < tarskiData.getShapes().length; i++) {
                    help = formula.replace(variant + "", i + "" ); //Ax(Blue(0)\/Small(0))
                    if(check(help.substring(index + 2)) == "(false)") {
                        all = false;
                        i = formulaChar.length;
                    }
                }
                if(all){
                    return "(true)";
                } else {
                    return "(false)";
                }
            }
        }

        //'E' keresése
        if (formula.contains("E")) {
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
                if(formulaChar[i] == 'E' && depth == 0) {
                    index = i;
                    foundAtZeroDepth = true;
                    i = formulaChar.length;
                }
            }
            if (foundAtZeroDepth) {
                char variant = formulaChar[index + 1];
                boolean any = false;
                String help = "";
                for (int i = 0; i < tarskiData.getShapes().length; i++) {
                    help = formula.replace(variant + "", i + "");
                    if(check(help.substring(index + 2)) == "(true)") {
                        any = true;
                        i = formulaChar.length;
                    }
                }
                if(any){
                    return "(true)";
                } else {
                    return "(false)";
                }
            }
        }

        //MÉRETEK
        //'Small()' keresése
        if (formula.contains("Small(")) {
            String help = small(formula.substring(6, formulaChar.length - 1));
            if(help == "(true)") {
                return "(true)";
            } else if(help == "(false)") {
                return "(false)";
            } else {
                return "Syntax error";
            }
        }

        //'Medium()' keresése
        if (formula.contains("Medium(")) {
            String help = medium(formula.substring(7, formulaChar.length - 1));
            if(help == "(true)") {
                return "(true)";
            } else if(help == "(false)") {
                return "(false)";
            } else {
                return "Syntax error";
            }
        }

        //'Large()' keresése
        if (formula.contains("Large(")) {
            String help = large(formula.substring(6, formulaChar.length - 1));
            if(help == "(true)") {
                return "(true)";
            } else if(help == "(false)"){
                return "(false)";
            } else {
                return "Syntax error";
            }
        }

        //SZÍNEK
        //'Red()' keresése
        if (formula.contains("Red(")) {
            String help = red(formula.substring(4, formulaChar.length - 1));
            if(help == "(true)") {
                return "(true)";
            } else if(help == "(false)") {
                return "(false)";
            } else {
                return "Syntax error";
            }
        }

        //'Green()' keresése
        if (formula.contains("Green(")) {
            String help = green(formula.substring(6, formulaChar.length - 1));
            if(help == "(true)") {
                return "(true)";
            } else if(help == "(false)") {
                return "(false)";
            } else {
                return "Syntax error";
            }
        }

        //'Blue()' keresése
        if (formula.contains("Blue(")) {
            String help = blue(formula.substring(5, formulaChar.length - 1));
            if(help == "(true)") {
                return "(true)";
            } else if(help == "(false)") {
                return "(false)";
            } else {
                return "Syntax error";
            }
        }

        //ALAKZATOK
        //'Triangle()' keresése
        if (formula.contains("Triangle(")) {
            String help = triangle(formula.substring(9, formulaChar.length - 1));
            if(help == "(true)") {
                return "(true)";
            } else if(help == "(false)") {
                return "(false)";
            } else {
                return "Syntax error";
            }
        }

        //'Square()' keresése
        if (formula.contains("Square(")) {
            String help = square(formula.substring(7, formulaChar.length - 1));
            if(help == "(true)") {
                return "(true)";
            } else if(help == "(false)") {
                return "(false)";
            } else {
                return "Syntax error";
            }
        }

        //'Circle()' keresése
        if (formula.contains("Circle(")) {
            String help = circle(formula.substring(7, formulaChar.length - 1));
            if(help == "(true)") {
                return "(true)";
            } else if(help == "(false)") {
                return "(false)";
            } else {
                return "Syntax error";
            }
        }

        //'LeftOf()' keresése - LeftOf(A,B) is true if 'A' x position < 'B' x position
        if(formula.contains("LeftOf(")) {
            String help = leftOf(formula.substring(7, formulaChar.length - 1));
            if(help == "(true)") {
                return "(true)";
            } else if(help == "(false)") {
                return "(false)";
            } else {
                return "Syntax error";
            }
        }

        //'RightOf()' keresése - RightOf(A,B) is true if 'A' x position > 'B' x position
        if(formula.contains("RightOf(")) {
            String help = rightOf(formula.substring(8, formulaChar.length - 1));
            if(help == "(true)") {
                return "(true)";
            } else if(help == "(false)") {
                return "(false)";
            } else {
                return "Syntax error";
            }
        }

        //'Between()' keresése - Between(A,B,C) is true if 'A' x position < 'B' x position < 'C' x position
        if(formula.contains("Between(")) {
            String help = between(formula.substring(8, formulaChar.length - 1));
            if(help == "(true)") {
                return "(true)";
            } else if(help == "(false)") {
                return "(false)";
            } else {
                return "Syntax error";
            }
        }

        return "Syntax error";
    }


    public String small(String shapeName) {
        boolean exist = false;
        boolean isNumber = true;
        int number = 0;
        int index = 0;
        Shape[] shapes = tarskiData.getShapes();

        try{
            number = Integer.parseInt(shapeName);
        } catch (Exception e) {
            isNumber = false;
        }

        if (isNumber) {
            exist = true;
            index = number;
        } else {
            for (int i = 0; i < shapes.length; i++) {
                if (shapes[i].getName().equals(shapeName)) {
                    exist = true;
                    index = i;
                }
            }
        }

        if(exist) {
            if(shapes[index].getSize() == Size.Small) {
                return "(true)";
            } else {
                return "(false)";
            }
        }
        return "Syntax error";
    }


    public String medium(String shapeName) {
        boolean exist = false;
        boolean isNumber = true;
        int number = 0;
        int index = 0;
        Shape[] shapes = tarskiData.getShapes();

        try{
            number = Integer.parseInt(shapeName);
        } catch (Exception e) {
            isNumber = false;
        }

        if (isNumber) {
            exist = true;
            index = number;
        } else {
            for (int i = 0; i < shapes.length; i++) {
                if (shapes[i].getName().equals(shapeName)) {
                    exist = true;
                    index = i;
                }
            }
        }

        if(exist) {
            if(shapes[index].getSize() == Size.Medium) {
                return "(true)";
            } else {
                return "(false)";
            }
        }
        return "Syntax error";
    }


    public String large(String shapeName) {
        boolean exist = false;
        boolean isNumber = true;
        int number = 0;
        int index = 0;
        Shape[] shapes = tarskiData.getShapes();

        try{
            number = Integer.parseInt(shapeName);
        } catch (Exception e) {
            isNumber = false;
        }

        if (isNumber) {
            exist = true;
            index = number;
        } else {
            for (int i = 0; i < shapes.length; i++) {
                if (shapes[i].getName().equals(shapeName)) {
                    exist = true;
                    index = i;
                }
            }
        }

        if(exist) {
            if(shapes[index].getSize() == Size.Large) {
                return "(true)";
            } else {
                return "(false)";
            }
        }
        return "Syntax error";
    }


    public String red(String shapeName) {
        boolean exist = false;
        boolean isNumber = true;
        int number = 0;
        int index = 0;
        Shape[] shapes = tarskiData.getShapes();

        try{
            number = Integer.parseInt(shapeName);
        } catch (Exception e) {
            isNumber = false;
        }

        if (isNumber) {
            exist = true;
            index = number;
        } else {
            for (int i = 0; i < shapes.length; i++) {
                if (shapes[i].getName().equals(shapeName)) {
                    exist = true;
                    index = i;
                }
            }
        }

        if(exist) {
            if(shapes[index].getColor() == Color.Red) {
                return "(true)";
            } else {
                return "(false)";
            }
        }
        return "Syntax error";
    }


    public String green(String shapeName) {
        boolean exist = false;
        boolean isNumber = true;
        int number = 0;
        int index = 0;
        Shape[] shapes = tarskiData.getShapes();

        try{
            number = Integer.parseInt(shapeName);
        } catch (Exception e) {
            isNumber = false;
        }

        if (isNumber) {
            exist = true;
            index = number;
        } else {
            for (int i = 0; i < shapes.length; i++) {
                if (shapes[i].getName().equals(shapeName)) {
                    exist = true;
                    index = i;
                }
            }
        }

        if(exist) {
            if(shapes[index].getColor() == Color.Green) {
                return "(true)";
            } else {
                return "(false)";
            }
        }
        return "Syntax error";
    }


    public String blue(String shapeName) {
        boolean exist = false;
        boolean isNumber = true;
        int number = 0;
        int index = 0;
        Shape[] shapes = tarskiData.getShapes();

        try{
            number = Integer.parseInt(shapeName);
        } catch (Exception e) {
            isNumber = false;
        }

        if (isNumber) {
            exist = true;
            index = number;
        } else {
            for (int i = 0; i < shapes.length; i++) {
                if (shapes[i].getName().equals(shapeName)) {
                    exist = true;
                    index = i;
                }
            }
        }

        if(exist) {
            if(shapes[index].getColor() == Color.Blue) {
                return "(true)";
            } else {
                return "(false)";
            }
        }
        return "Syntax error";
    }


    public String triangle(String shapeName) {
        boolean exist = false;
        boolean isNumber = true;
        int number = 0;
        int index = 0;
        Shape[] shapes = tarskiData.getShapes();

        try{
            number = Integer.parseInt(shapeName);
        } catch (Exception e) {
            isNumber = false;
        }

        if (isNumber) {
            exist = true;
            index = number;
        } else {
            for (int i = 0; i < shapes.length; i++) {
                if (shapes[i].getName().equals(shapeName)) {
                    exist = true;
                    index = i;
                }
            }
        }

        if(exist) {
            if(shapes[index].getShape() == Type.Triangle) {
                return "(true)";
            } else {
                return "(false)";
            }
        }
        return "Syntax error";
    }


    public String square(String shapeName) {
        boolean exist = false;
        boolean isNumber = true;
        int number = 0;
        int index = 0;
        Shape[] shapes = tarskiData.getShapes();

        try{
            number = Integer.parseInt(shapeName);
        } catch (Exception e) {
            isNumber = false;
        }

        if (isNumber) {
            exist = true;
            index = number;
        } else {
            for (int i = 0; i < shapes.length; i++) {
                if (shapes[i].getName().equals(shapeName)) {
                    exist = true;
                    index = i;
                }
            }
        }

        if(exist) {
            if(shapes[index].getShape() == Type.Square) {
                return "(true)";
            } else {
                return "(false)";
            }
        }
        return "Syntax error";
    }


    public String circle(String shapeName) {
        boolean exist = false;
        boolean isNumber = true;
        int number = 0;
        int index = 0;
        Shape[] shapes = tarskiData.getShapes();

        try{
            number = Integer.parseInt(shapeName);
        } catch (Exception e) {
            isNumber = false;
        }

        if (isNumber) {
            exist = true;
            index = number;
        } else {
            for (int i = 0; i < shapes.length; i++) {
                if (shapes[i].getName().equals(shapeName)) {
                    exist = true;
                    index = i;
                }
            }
        }

        if(exist) {
            if(shapes[index].getShape() == Type.Circle) {
                return "(true)";
            } else {
                return "(false)";
            }
        }
        return "Syntax error";
    }


    public String leftOf(String shapeNames) {
        char[] shapeNamesChar = shapeNames.toCharArray();
        String shapeName1 = "";
        String shapeName2 = "";
        boolean isShapeName1IsNumber = true;
        boolean isShapeName2IsNumber = true;
        boolean exist1 = false;
        boolean exist2 = false;
        int cut = 0;
        int shapeName1Number = 0;
        int shapeName2Number = 0;
        int index1 = 0;
        int index2 = 0;
        Shape[] shapes = tarskiData.getShapes();

        for (int i = 0; i < shapeNamesChar.length; i++){
            if(shapeNamesChar[i] == ',') {
                cut = i;
                i = shapeNamesChar.length;
            }
        }

        shapeName1 = shapeNames.substring(0,cut);
        shapeName2 = shapeNames.substring(cut+1);

        try {
            shapeName1Number = Integer.parseInt(shapeName1);
        }  catch (Exception e){
            isShapeName1IsNumber = false;
        }

        if(isShapeName1IsNumber) {
            exist1 = true;
            index1 = shapeName1Number;
        } else {
            for (int i = 0; i < shapes.length; i++) {
                if(shapes[i].getName().equals(shapeName1)) {
                    exist1 = true;
                    index1 = i;
                }
            }
        }

        try {
            shapeName2Number = Integer.parseInt(shapeName2);
        } catch (Exception e) {
            isShapeName2IsNumber = false;
        }

        if(isShapeName2IsNumber) {
            exist2 = true;
            index2 = shapeName2Number;
        } else {
            for (int i = 0; i < shapes.length; i++) {
                if(shapes[i].getName().equals(shapeName2)) {
                    exist2 = true;
                    index2 = i;
                }
            }
        }

        if(exist1&&exist2) {
            if(shapes[index1].getPos_x() < shapes[index2].getPos_x()) {
                return "(true)";
            } else {
                return "(false)";
            }
        }
        return "Syntax error";
    }


    public String rightOf(String shapeNames) {
        char[] shapeNamesChar = shapeNames.toCharArray();
        String shapeName1 = "";
        String shapeName2 = "";
        boolean isShapeName1IsNumber = true;
        boolean isShapeName2IsNumber = true;
        boolean exist1 = false;
        boolean exist2 = false;
        int cut = 0;
        int shapeName1Number = 0;
        int shapeName2Number = 0;
        int index1 = 0;
        int index2 = 0;
        Shape[] shapes = tarskiData.getShapes();

        for (int i = 0; i < shapeNamesChar.length; i++){
            if(shapeNamesChar[i] == ',') {
                cut = i;
                i = shapeNamesChar.length;
            }
        }

        shapeName1 = shapeNames.substring(0,cut);
        shapeName2 = shapeNames.substring(cut+1);

        try {
            shapeName1Number = Integer.parseInt(shapeName1);
        }  catch (Exception e){
            isShapeName1IsNumber = false;
        }

        if(isShapeName1IsNumber) {
            exist1 = true;
            index1 = shapeName1Number;
        } else {
            for (int i = 0; i < shapes.length; i++) {
                if(shapes[i].getName().equals(shapeName1)) {
                    exist1 = true;
                    index1 = i;
                }
            }
        }

        try {
            shapeName2Number = Integer.parseInt(shapeName2);
        } catch (Exception e) {
            isShapeName2IsNumber = false;
        }

        if(isShapeName2IsNumber) {
            exist2 = true;
            index2 = shapeName2Number;
        } else {
            for (int i = 0; i < shapes.length; i++) {
                if(shapes[i].getName().equals(shapeName2)) {
                    exist2 = true;
                    index2 = i;
                }
            }
        }

        if(exist1&&exist2) {
            if(shapes[index1].getPos_x() > shapes[index2].getPos_x()) {
                return "(true)";
            } else {
                return "(false)";
            }
        }
        return "Syntax error";
    }


    public String between(String shapeNames) {
        char[] shapeNamesChar = shapeNames.toCharArray();
        String shapeName1 = "";
        String shapeName2 = "";
        String shapeName3 = "";
        boolean isShapeName1IsNumber = true;
        boolean isShapeName2IsNumber = true;
        boolean isShapeName3IsNumber = true;
        boolean exist1 = false;
        boolean exist2 = false;
        boolean exist3 = false;
        boolean split = true;
        int cut = 1;
        int shapeName1Number = 0;
        int shapeName2Number = 0;
        int shapeName3Number = 0;
        int index1 = 0;
        int index2 = 0;
        int index3 = 0;
        Shape[] shapes = tarskiData.getShapes();

        for (int i = 0; i < shapeNamesChar.length; i++){
            if(shapeNamesChar[i] == ',' && split) {
                cut = i + 1;
                shapeName1 = shapeNames.substring(0,i);
            }
            if(shapeNamesChar[i] == ',' && !split) {
                shapeName2 = shapeNames.substring(cut,i);
                shapeName3 = shapeNames.substring(i+1);
                i = shapeNamesChar.length;
            }
            if(shapeName1 != ""){
                split = false;
            }
        }

        try {
            shapeName1Number = Integer.parseInt(shapeName1);
        }  catch (Exception e){
            isShapeName1IsNumber = false;
        }

        if(isShapeName1IsNumber) {
            exist1 = true;
            index1 = shapeName1Number;
        } else {
            for (int i = 0; i < shapes.length; i++) {
                if(shapes[i].getName().equals(shapeName1)) {
                    exist1 = true;
                    index1 = i;
                }
            }
        }

        try {
            shapeName2Number = Integer.parseInt(shapeName2);
        } catch (Exception e) {
            isShapeName2IsNumber = false;
        }

        if(isShapeName2IsNumber) {
            exist2 = true;
            index2 = shapeName2Number;
        } else {
            for (int i = 0; i < shapes.length; i++) {
                if(shapes[i].getName().equals(shapeName2)) {
                    exist2 = true;
                    index2 = i;
                }
            }
        }

        try {
            shapeName3Number = Integer.parseInt(shapeName3);
        } catch (Exception e) {
            isShapeName3IsNumber = false;
        }

        if(isShapeName3IsNumber) {
            exist3 = true;
            index3 = shapeName3Number;
        } else {
            for (int i = 0; i < shapes.length; i++) {
                if(shapes[i].getName().equals(shapeName3)) {
                    exist3 = true;
                    index3 = i;
                }
            }
        }

        if(exist1&&exist2&&exist3) {
            if(shapes[index1].getPos_x() < shapes[index2].getPos_x() && shapes[index2].getPos_x() < shapes[index3].getPos_x()) {
                return "(true)";
            } else {
                return "(false)";
            }
        }
        return "Syntax error";
    }
}
