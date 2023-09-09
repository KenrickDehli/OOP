import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Polygon;

public class KochKurve extends JFrame {

    public KochKurve(int steps) {
        //a)
        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                int[] xPoints = {250,400,100};
                int[] yPoints = {50,350,350};
                Polygon poly = new Polygon(xPoints, yPoints, xPoints.length);
                g.drawPolygon(subdivide(poly, steps));
            }
        };
        add(panel);
        pack();
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main (String args[]){
        //c)
        //Der von mir gewählte Standardwert 
        int steps = 5; 
        try {
            steps = Integer.parseInt(args[0]);
        } 
        catch (Exception ex) {
        }
        finally {
            new KochKurve(steps);
        }
    }

    //b) und c)
    static Polygon subdivide(Polygon polygon, int steps) {
        if (steps == 0) {
            return polygon;
        }
        Polygon ret = new Polygon();
        int points = polygon.npoints;
        for (int i = 0; i < points; i++ ){ 
        Vector2 vectorA = new Vector2(polygon.xpoints[i], polygon.ypoints[i]);
        Vector2 vectorE = new Vector2(polygon.xpoints[(i + 1) % points ], polygon.ypoints[(i + 1) % points]);

        Vector2 connectionVector = new Vector2(vectorE.x - vectorA.x, vectorE.y - vectorA.y);
        double length = connectionVector.length();

        double bx = (2.0/3.0) * vectorA.x + (1.0/3.0) * vectorE.x;
        double by = (2.0/3.0) * vectorA.y + (1.0/3.0) * vectorE.y;
        Vector2 vectorB = new Vector2(bx, by);

        double dx = (1.0/3.0) * vectorA.x + (2.0/3.0) * vectorE.x;
        double dy = (1.0/3.0) * vectorA.y + (2.0/3.0) * vectorE.y;
        Vector2 vectorD = new Vector2(dx, dy);

        double middlePointX = (vectorA.x + vectorE.x) / 2.0;
        double middlePointY = (vectorA.y +vectorE.y) / 2.0;
        Vector2 middlePoint = new Vector2(middlePointX, middlePointY);

        Vector2 verticalVector = new Vector2(connectionVector.y, connectionVector.x * - 1.0);

        double lengthb = (1.0/3.0) * length;
        double lengthe = (1.0/6.0) * length;
        double lengthd = Math.sqrt((lengthb * lengthb) - (lengthe *lengthe));

        double verticalX = middlePointX + verticalVector.x;
        double verticalY = middlePointY + verticalVector.y;

        Vector2 verticalVectorPoint = new Vector2(verticalX, verticalY);
        Vector2 vectorC = middlePoint.linearInterpolation(verticalVectorPoint, lengthd/length);

        ret.addPoint((int)vectorA.x, (int)vectorA.y);
        ret.addPoint((int)vectorB.x, (int)vectorB.y);
        ret.addPoint((int)vectorC.x, (int)vectorC.y);
        ret.addPoint((int)vectorD.x, (int)vectorD.y);
        }
     return subdivide(ret, steps - 1);
    }
}
