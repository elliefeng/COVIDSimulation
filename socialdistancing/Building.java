package socialdistancing;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


/* 
    Building extends JPanel so that we can override the paint method. The paint method is necessary to use the simple
    drawing tools of the library! 
    Simulator implements an ActionListener which adds the method actionPerformed. This method is invoked by the 
    animation timer every timerValue(16ms).
*/
public class Building extends Structure /*implements ActionListener*/{
    // serial suppresses warning
    private static final long serialVersionUID = 1L;
    
    /*//simulation control objects/values
    JFrame frame;
    Control control; //
    Timer timer; //Event control    
    int time = 0; //Track time as the simulation runs*/
    
    //Declares Wall sprites and positions of wall
        private Wall[] walls = new Wall[8];
        private Rectangle[] r = new Rectangle[8];
        
        
    /* constructor will setup our main Graphic User Interface - a simple Frame! */
    public Building(Control ctl, String title) {
    	super(ctl, title);
        Wall vWall1 = new Wall(550, 0, "SocialDistancingImages/wall2.png", true);
        Wall vWall2 = new Wall(200, 0, "SocialDistancingImages/wall2.png", true);
        Wall vWall3 = new Wall(550, 400, "SocialDistancingImages/wall2.png", true);
        Wall vWall4 = new Wall(200, 400, "SocialDistancingImages/wall2.png", true);
        
        Wall hWall1 = new Wall(620, 160, "SocialDistancingImages/wall1.png", false);
        Wall hWall2 = new Wall(-25, 160, "SocialDistancingImages/wall1.png", false);
        Wall hWall3 = new Wall(620, 400, "SocialDistancingImages/wall1.png", false);
        Wall hWall4 = new Wall(-25, 400, "SocialDistancingImages/wall1.png", false);
        
        
        /*walls.add(vWall1);
        walls.add(hWall1);
        walls.add(vWall2);
        walls.add(hWall2);
        walls.add(vWall3);
        walls.add(hWall3);
        walls.add(vWall4);
        walls.add(hWall4);*/
        walls[0] = vWall1;
        walls[1] = hWall1;
        walls[2] = vWall2;
        walls[3] = hWall2;
        walls[4] = vWall3;
        walls[5] = hWall3;
        walls[6] = vWall4;
        walls[7] = hWall4;
        
        
       //populate the Rectangle array
        for (int i=0; i<walls.length; i++)
        {
        	r[i] = walls[i].getBounds();
        }

        /*
        // used for Control callback
        this.control = ctl;
        
        //Setup the GUI
        frame = new JFrame(title);
        frame.setSize(ctl.frameX,ctl.frameY); //set the size
        
        //add this so that hitting the x button will actually end the program
        //the program will continue to run behind the scenes and you might end up with 10+ of them
        //without realizing it
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //make it visible
        frame.setVisible(true);
        frame.add(this); //add this class (JPanel) to the JFrame
        */
    }
    
    //Getter for ArrayList of Walls
    public Wall[] getWalls()
    {
        return walls;
    }
    
    //Getter for ArrayList of Rectangles
    public Rectangle[] getRectangles()
    {
        return r;
    }
    
    public void paintWalls(Graphics g) {

            //draws vertical walls
            for(Wall element: this.getWalls())
            {
                g.drawImage(element.getImage(),element.getX(), element.getY(),this);
             }
            
            //sets text color
            g.setColor(Color.BLACK);
            g.setFont(new Font("Roboto", Font.BOLD, 20));
            
            g.drawString("The Rubber", 610, 50);;
            g.drawString("Chicken Factory", 610, 75);
            g.drawString("Wuhan", 10, 50);
            g.drawString("College Board", 5, 440);
            g.drawString("Headquarters", 5, 465);
            g.drawString("Mr. M's House", 590, 440);
            
        }
    
    /*
    //activation of Simulator separated from Constructor 
    public void activate() {
        //Timer for animation
        //Argument 1: timerValue is a period in milliseconds to fire event
        //Argument 2:t any class that "implements ActionListener"
        timer = new Timer(control.timerValue, this); //timer constructor
        timer.restart(); //restart or start
        
        // frame becomes visible
        frame.setVisible(true);     
    }
    */
    
    /* This invoked by Timer per period in milliseconds in timerValue  */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Triggers paint call through polymorphism
        repaint();  
    }

    /* paint method for drawing the simulation and animation */
    @Override
    public void paint(Graphics g) {
        
        //tracking total time manually with the time variable
        time += control.timerValue;
        
        //events
        super.paintComponent(g); // a necessary call to the parent paint method, required for proper screen refreshing
        this.paintWalls(g);
        control.paintPersons(g); // repaint all objects in simulation
        
    } 
        
    
}