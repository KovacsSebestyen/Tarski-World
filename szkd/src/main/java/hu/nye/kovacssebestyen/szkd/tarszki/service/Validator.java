package hu.nye.kovacssebestyen.szkd.tarszki.service;

public class Validator {

    //

    public Boolean check(String formula){
        char[] formulaChar = formula.toCharArray();

        for (int i=0; i < formulaChar.length; i++){

            if(formulaChar[i] == '&'){
                int depth = 0;
                int index = 0;
                for(int j=i+1; j<formulaChar.length; j++){
                    if(formulaChar[j] == '('){
                        depth++;
                    }
                    if(formulaChar[j] == ')'){
                        depth--;
                    }
                    if(depth == 0){
                        index = j;
                        j = formulaChar.length+1;
                    }
                }
                String subFormula1 = formula.substring(i + 1, index);
                for(int j=i-1; j>=0; j--){
                    if(formulaChar[j] == ')'){
                        depth++;
                    }
                    if(formulaChar[j] == '('){
                        depth--;
                    }
                    if(depth == 0){
                        index = j;
                        j = -1;
                    }
                }
                String subFormula2 = formula.substring(index, i - 1);
                //for ciklus ugorjon a kifejezés után
                return check(subFormula1) && check(subFormula2);
            }
        }
        return true;
    }

    public Boolean small(Character shapeName){

        return true;
    }
}
