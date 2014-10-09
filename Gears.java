import processing.core.*;

public class Gears extends PApplet{

	private static final long serialVersionUID = 1L;
	Gear myGear1;
	Gear myGear2;

	private float back = 255;
	
	public void setup() {
		size(500, 500);
		background(back);
		myGear1 = new Gear(200, 250, 300, color(0, 0, 255));
		myGear2 = new Gear(400, 250, 100, color(255, 0, 255));
		for (int i = 0; i < 180; i++) {
			myGear2.rotateright();
		}
	}
	
	public void draw() {
		background(255);
		myGear1.rotateleft();
		myGear2.rotateright();
		myGear2.calculate();
	}
	
	class Gear {
		private float x, y, r;
		private float theta;
		private int c;
		private int count;
		
		Gear(float x_, float y_, float r_, int c_) {
			x = x_;
			y = y_;
			r = r_;
			c = c_;
			theta = 0;
			count = 0;
		}
		
		void display() {
			smooth();
			ellipseMode(CENTER);
			fill(c);
			
			ellipse(x, y, r, r);
			// little circle
			smooth();
			fill(back);
			ellipse((float) (x + r/2.0*0.8), y, (float) (r*0.2), (float) (r*0.2));
		}
		
		void rotateleft() {
			ellipseMode(CENTER);
			smooth();
			fill(c);
			pushMatrix();
			translate(x, y);
			rotate(theta);
			theta -= radians((float) (100.0 / r));
			translate(-x, -y);
			ellipse(x, y, r, r);
			// little circle
			fill(back);
			ellipse((float) (x + r/2.0*0.8), y, (float) (r*0.2), (float) (r*0.2));
			popMatrix();
		}
		
		void rotateright() {
			ellipseMode(CENTER);
			smooth();
			fill(c);
			pushMatrix();
			translate(x, y);
			rotate(theta);
			theta += radians((float) (100.0 / r));
			translate(-x, -y);
			ellipse(x, y, r, r);
			// little circle
			fill(back);
			ellipse((float) (x + r/2.0*0.8), y, (float) (r*0.2), (float) (r*0.2));
			popMatrix();
		}
		
		void calculate() {
			if (degrees(theta) > 360 + 180) {
				count++;
				theta = PI;
				println("count is: " + count);
			}
		}
	}
	
	// to run as a java "application" - full screen
	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "Gears" });
	}
}
