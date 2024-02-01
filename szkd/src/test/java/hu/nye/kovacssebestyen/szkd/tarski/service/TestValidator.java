package hu.nye.kovacssebestyen.szkd.tarski.service;

import hu.nye.kovacssebestyen.szkd.tarszki.data.model.*;
import hu.nye.kovacssebestyen.szkd.tarszki.service.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestValidator {

    private Validator underTest;

    private String syntaxError = "Suare(a)";

    private String formula = "-(Square(a))";

    private String formula1 = "Ax(Triangle(x)=>Small(x))";

    private String formula2 = "Ex(Triangle(x)/\\Small(x)/\\LeftOf(x,b))";

    private String formula3 = "Ax(Circle(x)/\\Small(x)=>Ey(LeftOf(y,x)/\\Square(y)))";

    private final Shape shape1 = new Shape(1,0, Type.Circle, Color.Blue, Size.Large,"a");

    private final Shape shape2 = new Shape(4,2, Type.Square, Color.Green, Size.Small,"");

    private final Shape shape3 = new Shape(4,3, Type.Circle, Color.Red, Size.Large,"");

    private final Shape shape4 = new Shape(0,4, Type.Triangle, Color.Blue, Size.Small,"");

    private final Shape shape5 = new Shape(1,4, Type.Triangle, Color.Green, Size.Small,"b");

    private final Shape shape6 = new Shape(3,4, Type.Triangle, Color.Red, Size.Medium,"");

    private final Shape shape7 = new Shape(4,4, Type.Triangle, Color.Blue, Size.Large,"");


    private final Shape[] shapes = {shape1,shape2,shape3,shape4,shape5,shape6,shape7};

    private final TarskiData tarskiData = new TarskiData(shapes, formula1);

    @BeforeEach
    public void setUp() {
        underTest = new Validator(tarskiData);
    }

    @Test
    public void testCheckSyntaxError() {

        String result = underTest.check(syntaxError);

        assertEquals(result, "Syntax error");
    }
    @Test
    public void testCheck() {

        String result = underTest.check(formula);

        assertEquals(result, "(true)");
    }
    @Test
    public void testCheck1() {

        String result = underTest.check(formula1);

        assertEquals(result, "(false)");
    }
    @Test
    public void testCheck2() {

        String result = underTest.check(formula2);

        assertEquals(result, "(true)");
    }
    @Test
    public void testCheck3() {

        String result = underTest.check(formula3);

        assertEquals(result, "(true)");
    }
    @Test
    public void testSmallTrue() {

        String result = underTest.small("b");

        assertEquals(result, "(true)");
    }
    @Test
    public void testSmallFalse() {

        String result = underTest.small("a");

        assertEquals(result, "(false)");
    }
    @Test
    public void testMediumTrue() {

        String result = underTest.medium("5");

        assertEquals(result, "(true)");
    }
    @Test
    public void testMediumFalse() {

        String result = underTest.medium("a");

        assertEquals(result, "(false)");
    }
    @Test
    public void testLargeTrue() {

        String result = underTest.large("a");

        assertEquals(result, "(true)");
    }
    @Test
    public void testLargeFalse() {

        String result = underTest.large("b");

        assertEquals(result, "(false)");
    }
    @Test
    public void testRedTrue() {

        String result = underTest.red("2");

        assertEquals(result, "(true)");
    }
    @Test
    public void testRedFalse() {

        String result = underTest.red("b");

        assertEquals(result, "(false)");
    }
    @Test
    public void testGreenTrue() {

        String result = underTest.green("b");

        assertEquals(result, "(true)");
    }
    @Test
    public void testGreenFalse() {

        String result = underTest.green("a");

        assertEquals(result, "(false)");
    }
    @Test
    public void testBlueTrue() {

        String result = underTest.blue("a");

        assertEquals(result, "(true)");
    }
    @Test
    public void testBlueFalse() {

        String result = underTest.blue("b");

        assertEquals(result, "(false)");
    }
    @Test
    public void testTriangleTrue() {

        String result = underTest.triangle("b");

        assertEquals(result, "(true)");
    }
    @Test
    public void testTriangleFalse() {

        String result = underTest.triangle("a");

        assertEquals(result, "(false)");
    }
    @Test
    public void testSquareTrue() {

        String result = underTest.square("1");

        assertEquals(result, "(true)");
    }
    @Test
    public void testSquareFalse() {

        String result = underTest.square("b");

        assertEquals(result, "(false)");
    }
    @Test
    public void testCircleTrue() {

        String result = underTest.circle("a");

        assertEquals(result, "(true)");
    }
    @Test
    public void testCircleFalse() {

        String result = underTest.circle("b");

        assertEquals(result, "(false)");
    }
    @Test
    public void testLeftOfTrue() {

        String result = underTest.leftOf("3,b");

        assertEquals(result, "(true)");
    }
    @Test
    public void testLeftOfFalse() {

        String result = underTest.leftOf("1,a");

        assertEquals(result, "(false)");
    }
    @Test
    public void testRightOfTrue() {

        String result = underTest.rightOf("1,a");

        assertEquals(result, "(true)");
    }
    @Test
    public void testRightOfFalse() {

        String result = underTest.rightOf("3,b");

        assertEquals(result, "(false)");
    }
    @Test
    public void testBetweenTrue() {

        String result = underTest.between("a,5,1");

        assertEquals(result, "(true)");
    }
    @Test
    public void testBetweenFalse() {

        String result = underTest.between("1,a,5");

        assertEquals(result, "(false)");
    }
}
