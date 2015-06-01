package lab4;

public class RobotTest {

  public static void main(String[] args) {
    // TODO: construct a robot and move it around. Make it run out of
    // battery power, then recharge it and continue. Make the robot end 
    // where it begins. Call the method print below (repeatedly) to 
    // monitor the state of your robot.
	  Robot r = new Robot(0.0,0.0);
	  System.out.println("Begin:");
	  print(r);
	  r.goForward(8.0);
	  print(r);
	  r.turnLeft();
	  r.recharge(2.0);
	  r.goForward(6.0);
	  r.turnLeft();
	  print(r);
	  r.recharge(1.0);
	  r.goForward(6.0);
	  r.turnLeft();
	  r.turnLeft();
	  r.turnLeft();
	  print(r);
	  r.recharge(2.0);
	  r.turnRight();
	  r.goForward(6.0);
	  r.turnRight();
	  print(r);
	  r.goForward(2.0);
	  print(r);
  }

  private static void print(Robot r) {
    // TODO: print the state of the specified robot to the console.
    // This method calls methods on the specified robot object r.
    // For example, r.getX() gets the X coordinate of the robot r,
    // which this method can then print, along with other such info.
	  System.out.println("x: "+r.getX());
	  System.out.println("y: "+r.getY());
	  System.out.println("heading: "+r.getHeading());
	  System.out.println("battery meter: "+r.readBatteryMeter());
	  System.out.println("distance: "+r.distance()+"\n");	  
  }
}
