import processing.core.*;

public class GravitySprings extends PApplet {
	
	Spring[] sparray = new Spring[10];
				
	boolean press = false;

	int count = 0;   // to limit number of springs
	int current;     // tracks pressed spring (index of array)
	
	double gravity = 10;

	
	public void setup() 
	{
	  size(500, 300);
	  noStroke();
	}

	public void draw() {
		display();
	}
	
	public void mousePressed() {
		
		// prevents overlapping
		for (int i = 0; i < count; i++) {
			if ((sparray[i].xval - 35 < mouseX) && (mouseX <  sparray[i].xval + 35) && (sparray[i].yval - 20 < mouseY) && (mouseY < sparray[i].yval + 20)) {
				press = true; // if you are touching a wood block
				current = i;
				sparray[current].bounce = false;
				println("current is " + current);
				/*println("k is " + sparray[current].k);
				println("displacement is " + sparray[current].displacement);
				println("yval is " + sparray[current].yval);*/
			}
		}
		if (!press) {
			if (count < 8) {
				sparray[count] = new Spring(2, mouseX, mouseY);
				sparray[count].drawSpring();
				count++;
			}
		}
	}
	
	public void mouseReleased() {
		if (press) {
			sparray[current].bounce = true;
		}
		press = false;
	}
	
	public void mouseDragged() {
		
		if (press) {
			
			sparray[current].yval = mouseY;
			// you can't "push" the spring
			if (sparray[current].yval < sparray[current].rest) {
				sparray[current].yval = sparray[current].rest;
				sparray[current].spheight = sparray[current].rest;
			}
			// spring can't go too low
			if (sparray[current].yval > height - 25) {
				sparray[current].yval = height - 25;
				sparray[current].spheight = height - 25;
			}
				sparray[current].spheight = sparray[current].yval;
				//sparray[current].spwidth -= 0.1;
				if (sparray[current].spwidth < 2) {
					sparray[current].spwidth = 2;
				}
		}
	}
	
	public void display() {
		  background(255);
		  for (int i = 0; i < count; i++) {
			  sparray[i].drawSpring();
			  sparray[i].bounceSpring();
		  }
	}

	
	class Spring {
		
		// constants, for now
		double damping = 0.92;
		
		// attributes
		double mass; // default 0.8
		int xval, yval;
		
		// variables
		double vx = 0.0;  // Velocity
		double a = 0;     // Acceleration
		double f = 0;     // Force
		boolean bounce = false;
		
		private int rest;
		double displacement;
		double mg;
		double k;
		
		// spring size
		float spwidth = 10;
		int spheight;
		
		Spring(double mass_, int xval_, int yval_) {
			mass = mass_;
			xval = xval_;
			yval = yval_;
			spheight = yval;
			rest = yval;
			displacement = yval - rest;
			
			mg = mass * gravity;
			k = mg / yval; // f == f => mg = ky
		}	
		
		void drawSpring() {
			
			// draw block
			fill(205, 170, 125);
			rectMode(CENTER);
			rect(xval, yval, 50, 20);
			
			// draw spring
			fill(102);
			rectMode(CENTER);
			rect(xval, 0, spwidth, 2 * spheight);

			// text
			 //text(Double.toString(mass), xval, yval);
			 text(Double.toString(k), xval, yval);
		}
		
		void bounceSpring() {
			
			if (bounce) {
				
				displacement = yval - rest;
	
				f = -k * displacement;
				a = f / mass;
				vx = damping * (vx + a);
				yval = (int) (yval + vx);
				spheight = yval;
			}
			if (abs((float) vx) < 0.01) {
				vx = 0.0;
				bounce = false;
			}
		}
	}
		
	
	// to run as a java "application" - full screen
	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "GravitySprings" });
	}

}
