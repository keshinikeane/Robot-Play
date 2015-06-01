package lab4;

import static java.lang.Math.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A playing field for a robot.
 * @author Dave Hale, Colorado School of Mines
 */
public class RobotPlay extends JPanel {

  /**
   * Constructs a playing fields for a simple robot.
   */
  public static void main(String[] args) {
    new RobotPlay(new Robot(0,0));
  }

  /**
   * Constructs a playing field for the specified robot.
   * @param robot the robot.
   */
  public RobotPlay(Robot robot) {
    this.robot = robot;
    makeFrame(this);
  }
  
  private void drawString(Graphics g, String text, int x, int y) {
      for (String line : text.split("\n"))
          g.drawString(line, x, y += g.getFontMetrics().getHeight());
  }
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawField(g);
  }

  ///////////////////////////////////////////////////////////////////////////
  // private

  // Bounds of playing field.
  private static final double XMIN = -10.0;
  private static final double YMIN = -10.0;
  private static final double XMAX =  10.0;
  private static final double YMAX =  10.0;

  private Robot robot;

  private void drawField(Graphics g) {
    int w = getWidth();
    int h = getHeight();
    g.drawLine(0,h/2,w,h/2);
    g.drawLine(w/2,0,w/2,h);
    double xscale =  w/(XMAX-XMIN);
    double yscale = -h/(YMAX-YMIN);
    double xshift = XMIN;
    double yshift = YMAX;
    double rx = robot.getX();
    double ry = robot.getY();
    int ix = (int)((rx-xshift)*xscale+0.5);
    int iy = (int)((ry-yshift)*yscale+0.5);
    int radius = 10;
    
    // draw robot in blue when battery power is full
    //if (robot.readBatteryMeter()==robot.getBatteryCapacity()) {
    	//g.setColor(Color.BLUE);
    //} else {
    	//g.setColor(Color.RED);
    //}
    
    // draws robot in blue when battery power is full but turns more red as power is drained
    // percentage of robot that is blue represents percentage of battery left
    double bp = robot.readBatteryMeter()/robot.getBatteryCapacity();
    int rh = (int) robot.getHeading();
    g.setColor(Color.RED);
    g.fillArc(ix-radius,iy-radius,1+2*radius,1+2*radius,120-rh,300);
    g.setColor(Color.BLUE);
    g.fillArc(ix-radius,iy-radius,1+2*radius,1+2*radius,120-rh,(int)(300*bp));
    
    // draws text on playing field to display battery power, coordinates, and distance 
    g.setColor(Color.BLACK);
    drawString(g,"battery power: "+Double.toString(robot.readBatteryMeter())
    		+"\n(x,y): ("+Double.toString(robot.getX())+","+Double.toString(robot.getY())+")"
    		+"\ndistance: "+Double.toString(robot.distance()),10,10);
  }

  private void makeFrame(JPanel panel) {
    JFrame frame = new JFrame();
    frame.add(panel);
    
    JButton rechargeButton = new JButton(
      new AbstractAction("Recharge") {
      public void actionPerformed(ActionEvent event) {
        robot.recharge(1.0);
        repaint();
      }
    });
    JButton stepButton = new JButton(
      new AbstractAction("Step") {
      public void actionPerformed(ActionEvent event) {
        robot.goForward(1.0);
        repaint();
      }
    });
    JButton turnLeftButton = new JButton(
      new AbstractAction("Left") {
      public void actionPerformed(ActionEvent event) {
        robot.turnLeft();
        repaint();
      }
    });
    JButton turnRightButton = new JButton(
      new AbstractAction("Right") {
      public void actionPerformed(ActionEvent event) {
        robot.turnRight();
        repaint();
      }
    });
    JToolBar toolBar = new JToolBar();
    toolBar.add(rechargeButton);
    toolBar.add(stepButton);
    toolBar.add(turnLeftButton);
    toolBar.add(turnRightButton);
    frame.add(toolBar,BorderLayout.NORTH);   
    frame.setSize(600,600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
