import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Polygon;

public class KochKurve extends JFrame {

    public KochKurve() {
        //a)
        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                int[] xPoints = {250,400,100};
                int[] yPoints = {50,350,350};
                Polygon poly = new Polygon(xPoints, yPoints, xPoints.length);
                Polygon poly2 = subdivide(poly);
                g.drawPolygon(poly2);
            }
        };
        add(panel);
        pack();
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main (String args[]){
        new KochKurve();
    }

    //b)
    static Polygon subdivide(Polygon polygon) {
        Polygon ret = new Polygon();
        int points = polygon.npoints;
        for (int i = 0; i < points; i++ ){ 
            System.out.println(i);
        Vector2 vectorA = new Vector2(polygon.xpoints[i], polygon.ypoints[i]);
        Vector2 vectorE = new Vector2(polygon.xpoints[(i + 1) % points ], polygon.ypoints[(i + 1) % points]);
        Vector2 helpVector = new Vector2(vectorE.x - vectorA.x, vectorE.y - vectorA.y);
        double length = helpVector.length();
        System.out.println("this is the length hallo "+length);


        double bx = (2.0/3.0) * vectorA.x + (1.0/3.0) * vectorE.x;
        double by = (2.0/3.0) * vectorA.y + (1.0/3.0) * vectorE.y;
        Vector2 vectorB = new Vector2(bx, by);

        double dx = (1.0/3.0) * vectorA.x + (2.0/3.0) * vectorE.x;
        double dy = (1.0/3.0) * vectorA.y + (2.0/3.0) * vectorE.y;
        Vector2 vectorD = new Vector2(dx, dy);

        double middlePointX = (vectorA.x + vectorE.x) / 2.0;
        double middlePointY = (vectorA.y +vectorE.y) / 2.0;
        System.out.println(middlePointY);
        Vector2 middlePoint = new Vector2(middlePointX, middlePointY);

        Vector2 verticalVector = new Vector2(helpVector.y, helpVector.x * - 1.0);
        Vector2 normalizedVerticalVector = verticalVector.normalized();

        double lengthb = (1.0/3.0) * length;
        System.out.println("this is the length"+length);
        double lengthe = (1.0/6.0) * length;
        System.out.println("this is the length b"+lengthb);
        System.out.println("this is the length e"+lengthe);
        // d2 = b2-e2
        double lengthd = Math.sqrt((lengthb * lengthb) - (lengthe *lengthe));
        System.out.println("this is the length d"+lengthd);
        System.out.println("this is the length d small "+ lengthd/length);
        Vector2 vectorC = middlePoint.linearInterpolation(normalizedVerticalVector, -0.5);


        /* Polygon ret = new Polygon();
        for (int i = 0; i < polygon.npoints; i++) {
            ret.addPoint(po, i);
        } */

        ret.addPoint((int)vectorA.x, (int)vectorA.y);
        ret.addPoint((int)vectorB.x, (int)vectorB.y);
        ret.addPoint((int)vectorC.x, (int)vectorC.y);
        ret.addPoint((int)vectorD.x, (int)vectorD.y);
        ret.addPoint((int)vectorE.x, (int)vectorE.y);



        System.out.println("Vector A"+vectorA);
        System.out.println("Vector B"+vectorB);
        System.out.println("Vector D"+vectorD);
        System.out.println("Vector E"+vectorE);
        System.out.println("Vector C"+vectorC);
        System.out.println("Help Vector"+helpVector);
        System.out.println("VerticalVec"+verticalVector);
        System.out.println(" NormVec"+normalizedVerticalVector);

        }

     
     return ret;
    }

}
