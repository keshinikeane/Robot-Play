package lab4;

import static java.lang.Math.*;

/**
 * A simple robot.
 * @author kkeane, Colorado School of Mines
 */
public class Robot {

  /**
   * Constructs a robot with a fully charged battery.
   * The initial heading is zero degrees (north).
   * @param x initial x coordinate, in meters.
   * @param y initial y coordinate, in meters.
   */
  public Robot(double x, double y) {
    // TODO
	  this.x = x;
	  this.y = y;
	  xinit = x;
	  yinit = y;
	  batteryMeter = BCW;
  }

  /**
   * Gets the x-coordinate of this robot.
   * @return the x-coordinate, in meters.
   */
  public double getX() {
	  return x;
  }

  /**
   * Gets the y-coordinate of this robot.
   * @return the y-coordinate, in meters.
   */
  public double getY() {
    return y;
  }

  /**
   * Gets the heading of this robot. The heading is greater than or
   * equal to 0 degrees (north) and less than 360 degrees.
   * @return the heading, in degrees.
   */
  public double getHeading() {
	  if (heading>=360.0) {
		  heading-=360.0;
	  } else if (heading<0.0) {
		  heading+=360.0;
	  }
	  return heading; 
  }

  /**
   * Gets the battery capacity for this robot.
   * @return the battery capacity, in watts.
   */
  public double getBatteryCapacity() {
	  return BCW; 
  }

  /**
   * Returns the current battery power for this robot.
   * @return current battery power, in watts.
   */
  public double readBatteryMeter() {
	  return batteryMeter; 
  }

  /**
   * Returns the distance between this robot's initial and current locations.
   * Because this robot may have turned, this distance may be less than the 
   * total number of meters traveled. For example, if the robot has returned
   * to it's initial location, this distance is zero.
   * @return the distance, in meters.
   */
  public double distance() {
	  double dx = x-xinit;
	  double dy = y-yinit;
	  return sqrt(dx*dx+dy*dy); 
  }

  /**
   * Turns this robot 90 degrees to the right (clockwise).
   * Power required to turn 90 degrees is the same as that required to go 
   * one meter. If insufficient power is available to complete the turn,
   * the robot consumes all remaining power but does not turn at all.
   */
  public void turnRight() {
	  if (batteryMeter>=1.0/MPW) {
		  heading += 90.0;
		  getHeading();
		  batteryMeter-=1.0/MPW;
	  } else {
		  batteryMeter = 0;
	  }  
  }

  /**
   * Turns this robot 90 degrees to the left (counter-clockwise).
   * Power required to turn 90 degrees is the same as that required to go 
   * one meter. If insufficient power is available to complete the turn,
   * the robot consumes all remaining power but does not turn at all.
   */
  public void turnLeft() {
	  if (batteryMeter>=1.0/MPW) { 
		  heading -= 90.0;
		  getHeading();
		  batteryMeter-=1.0/MPW;
	  } else {
		  batteryMeter = 0;
	  }
	 
  }

  /**
   * Recharges the battery for this robot for the specified amount of time.
   * Recharging has no effect after the battery's capacity is reached.
   * In other words, battery power must never exceed battery capacity.
   * @param seconds recharging time, in seconds.
   */
  public void recharge(double seconds) {
	  batteryMeter += seconds/SPW;
	  if (batteryMeter>BCW) {
		  batteryMeter = BCW;
	  }
  }

  /**
   * Trys to make this robot go forward the specified distance. 
   * The robot goes only as far as possible with available battery power.
   * @param distance the distance to go, in meters.
   * @return the distance actually gone.
   */
  public double goForward(double distance) {
	  if (batteryMeter<distance/MPW) {
		  distance = batteryMeter*MPW;
	  }
	  
	  if (heading==0.0) {
		  y+=distance;
	  } else if (heading==90.0) {
		  x+=distance;
	  } else if (heading==180.0) {
		  y-=distance;
	  } else if (heading==270.0) {
		  x-=distance;
	  }
	  
	  batteryMeter-=distance/MPW;
	  return distance;
  }

  ///////////////////////////////////////////////////////////////////////////
  // Private fields declared here describe completely the state of this robot.
  // TODO: add private fields (neither static nor final).
  private double heading;
  private double batteryMeter;
  private double x,y;
  private double xinit,yinit;
  
  
  // Static fields are shared by all robots of this class of robots.
  // These fields are private, because they are accessed only by methods 
  // within this class. They are also final, because they are constants.
  // That is, they cannot be modified after they have been initialized.
  private static final double MPW = 10.0; // meters of go per watt
  private static final double SPW = 2.0; // seconds of charge per watt
  private static final double BCW = 1.0; // battery capacity, in watts
}
