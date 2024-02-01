package hu.nye.kovacssebestyen.szkd.tarszki;

import hu.nye.kovacssebestyen.szkd.tarszki.data.model.*;
import hu.nye.kovacssebestyen.szkd.tarszki.service.Validator;

public class TestMain {
    public static void main(String[] args) {
        Shape[] shapesArray = {new Shape(1,2, Type.Circle, Color.Blue, Size.Medium,"alma"),
                               new Shape(2,2, Type.Triangle, Color.Green, Size.Medium,"korte"),
                               new Shape(3,4, Type.Square, Color.Blue, Size.Large,"szilva")};

        //Formula megadása
        String formula = "Ax(Blue(x))";


        TarskiData tarski = new TarskiData(shapesArray, formula);

        System.out.println(new Validator(tarski).check(tarski.getFormula()));
    }
}
