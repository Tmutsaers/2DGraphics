import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.*;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.strategies.QuadSpaceStrategy;


public class Box2D_Example1 {

        public static void main(String s[])
        {
                JFrame frame = new JFrame("SpriteBounce example");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel panel = new BouncePanel();

                frame.getContentPane().add(panel);
                frame.pack();
                frame.setVisible(true);
        }
}


class BouncePanel extends JPanel implements ActionListener
{

        // Add World object
        private World myWorld = new World(
                                new Vector2f(0.0f, 10.0f),
                                10,
                                new QuadSpaceStrategy(20,5));

        private Body b1, b2, wall;


        /* Constructor */
        public BouncePanel()
        {
                setPreferredSize( new Dimension(640,480));

                myWorld.clear();

                b1 = new Body("b1", new Box(30.0f, 50.0f), 100000f);
                b1.setPosition(250f, 4f);
                b1.setRestitution(0.7f);

                b2 = new Body("b2", new Box(90.0f, 10.0f), 10f);
                b2.setPosition(255f, 70f);
                b2.setRestitution(0.7f);

                wall = new StaticBody("Ground", new Box(40f,20f));
                wall.setPosition(280f, 200);

                myWorld.add(wall);
                myWorld.add(b1);
                myWorld.add(b2);

                Timer timer = new Timer(1000/30, this);
                timer.start();
        }

        // Timer, action performed.
        public void actionPerformed(ActionEvent arg0)
        {
                for(int i=0; i<5; i++) {
                        myWorld.step();
                }
                repaint();
        }

        public void drawBox(Graphics2D g2, Body b)
        {
                Box box = (Box) b.getShape();
                Vector2f[] pts = box.getPoints(b.getPosition(), b.getRotation());

                Vector2f p1 = pts[0];
                Vector2f p2 = pts[1];
                Vector2f p3 = pts[2];
                Vector2f p4 = pts[3];

                g2.drawLine((int) p1.x,(int) p1.y,(int) p2.x,(int) p2.y);
                g2.drawLine((int) p2.x,(int) p2.y,(int) p3.x,(int) p3.y);
                g2.drawLine((int) p3.x,(int) p3.y,(int) p4.x,(int) p4.y);
                g2.drawLine((int) p4.x,(int) p4.y,(int) p1.x,(int) p1.y);

        }

        // Hier alleen tekenen ! Nooit een repaint() !!
        public void paintComponent(Graphics g)
        {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D)g;

                // Dikke lijn, beter zichtbaar
                g2.setStroke(new BasicStroke(2));

                // Draw body 1 en body 2
                g2.setColor(Color.black);
                drawBox(g2, b1);

                g2.setColor(Color.green);
                drawBox(g2, b2);

                g2.setColor(Color.red);
                drawBox(g2, wall);

        }
}