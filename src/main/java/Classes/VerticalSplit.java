package Classes;

import javax.swing.*;
import java.awt.*;

public class VerticalSplit extends JPanel {
    int x;
    public VerticalSplit(int x){
        setOpaque(false);
        this.x = x;
    }

    public Dimension getPreferredSize(){
        return new Dimension(0,3);
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int x = (getWidth()-65)/8;
        BasicStroke stroke = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2d.setStroke(stroke);
        g2d.drawLine( x, 0, x, getHeight());
        g2d.dispose();
    }
}
