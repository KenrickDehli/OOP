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
                //Polygon poly3 = subdivide(poly2);
                //Polygon poly4 = subdivide(poly3);
                //Polygon poly5 = subdivide(poly4);
                g.drawPolygon(poly);
                System.out.println("npoints " + poly2.npoints);
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
            System.out.println("points " + points);
            System.out.println("i " + i);
            System.out.println("i+1 "+ (i +1));
            System.out.println("i + 1 % points "+(i+1) % points);

        Vector2 vectorA = new Vector2(polygon.xpoints[i], polygon.ypoints[i]);
        Vector2 vectorE = new Vector2(polygon.xpoints[(i + 1) % points ], polygon.ypoints[(i + 1) % points]);
        Vector2 connectionVector = new Vector2(vectorE.x - vectorA.x, vectorE.y - vectorA.y);
        double length = connectionVector.length();
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

        Vector2 verticalVector = new Vector2(connectionVector.y, connectionVector.x * - 1.0);
        Vector2 normalizedVerticalVector = verticalVector.normalized();

        double lengthb = (1.0/3.0) * length;
        System.out.println("this is the length"+length);
        double lengthe = (1.0/6.0) * length;
        System.out.println("this is the length b"+lengthb);
        System.out.println("this is the length e"+lengthe);
        // d2 = b2-e2
        double lengthd = Math.sqrt((lengthb * lengthb) - (lengthe *lengthe));
        System.out.println("this is the length d "+lengthd);
        System.out.println("this is the length d small "+ lengthd/length);
        double verticalX = middlePointX + verticalVector.x;
        double verticalY = middlePointY + verticalVector.y;

        Vector2 vec = new Vector2(verticalX, verticalY);
        Vector2 vectorC = middlePoint.linearInterpolation(vec, lengthd/length);



        /* Polygon ret = new Polygon();
        for (int i = 0; i < polygon.npoints; i++) {
            ret.addPoint(po, i);
        } */

        ret.addPoint((int)vectorA.x, (int)vectorA.y);
        ret.addPoint((int)vectorB.x, (int)vectorB.y);
        ret.addPoint((int)vectorC.x, (int)vectorC.y);
        ret.addPoint((int)vectorD.x, (int)vectorD.y);



        System.out.println("Vector A "+vectorA);
        System.out.println("Vector B "+vectorB);
        System.out.println("Vector D "+vectorD);
        System.out.println("Vector E "+vectorE);
        System.out.println("Vector C "+vectorC);
        System.out.println("Connection Vector "+connectionVector);
        System.out.println("VerticalVec "+verticalVector);
        System.out.println(" NormVec "+normalizedVerticalVector);

        }

     
     return ret;
    }
}
