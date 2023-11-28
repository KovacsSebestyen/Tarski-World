package hu.nye.kovacssebestyen.szkd.tarszki.data.model;

import java.util.Arrays;

public class TarskiData {

    private Shape[] shapes;

    private String formula;

    public TarskiData() {
    }

    public TarskiData(Shape[] shapes, String formula) {
        this.shapes = shapes;
        this.formula = formula;
    }

    public Shape[] getShapes() {
        return shapes;
    }

    public void setShapes(Shape[] shapes) {
        this.shapes = shapes;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    @Override
    public String toString() {
        return "TarskiData{" +
                "shapes=" + Arrays.toString(shapes) +
                ", formula='" + formula + '\'' +
                '}';
    }
}
