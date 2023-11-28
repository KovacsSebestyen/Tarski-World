package hu.nye.kovacssebestyen.szkd.tarszki.data.model;

import java.util.Objects;

public class Shape {

    private Integer pos_x;
    private Integer pos_y;
    private Type shape;
    private Color color;
    private Size size;
    private Character name;

    public Shape() {
    }

    public Shape(Integer pos_x, Integer pos_y, Type shape, Color color, Size size, Character name) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.shape = shape;
        this.color = color;
        this.size = size;
        this.name = name;
    }

    public Character getName() {
        return name;
    }

    public void setName(Character name) {
        this.name = name;
    }

    public Integer getPos_x() {
        return pos_x;
    }

    public void setPos_x(Integer pos_x) {
        this.pos_x = pos_x;
    }

    public Integer getPos_y() {
        return pos_y;
    }

    public void setPos_y(Integer pos_y) {
        this.pos_y = pos_y;
    }

    public Type getShape() {
        return shape;
    }

    public void setShape(Type shape) {
        this.shape = shape;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Shapes{" + "\n" +
                ", pos_x=" + pos_x + "\n" +
                ", pos_y=" + pos_y + "\n" +
                ", shape=" + shape + "\n" +
                ", color=" + color + "\n" +
                ", size=" + size + "\n" +
                '}';
    }
}
