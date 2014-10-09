import processing.core.*;

public class SlingshotBounce extends PApplet {

	boolean drag = false;
	boolean release = false;
	
	// controls where ball is dragged
	boolean ball = false;
	
	// for ball movement
	float x;
	float y;
	float xside;
	float yside;
	int xcenter;
	int ycenter;
	float theta;
	
	double vi;
	double vx;
	double vy;
	double gravity = 0.5;
	
	
	public void setup() {
		smooth();
		size(420, 330);
		frameRate(40);
		xcenter = width/2 - 3;
		ycenter = height - 144;
		x = xcenter;
		y = ycenter + 5;
	}
	
	 public void draw() {
		
		background(196, 216, 255);
				
		slingshot();
		stretch();
		fly();
		contain();
	}
	
	void slingshot() {

		noStroke();
		fill(139, 90, 43);
		beginShape();
		curveVertex(width/2 - 10, height);
		curveVertex(width/2 - 10, height);
		curveVertex(width/2 - 9, height - 75);
		curveVertex(width/2 - 10, height - 75);
		curveVertex(width/2 - 23, height - 91);
		curveVertex(width/2 - 28, height - 103);
		curveVertex(width/2 - 30, height - 140);
		curveVertex(width/2 - 25, height - 146);
		curveVertex(width/2 - 19, height - 144);
		curveVertex(width/2 - 16, height - 108);
		curveVertex(width/2 - 7, height - 91);
		curveVertex(width/2 - 5, height - 86);
		curveVertex(width/2 - 3, height - 86);
		curveVertex(width/2 + 4, height - 97);
		curveVertex(width/2 + 11, height - 113);
		curveVertex(width/2 + 13, height - 147);
		curveVertex(width/2 + 21, height - 149);
		curveVertex(width/2 + 25, height - 143);
		curveVertex(width/2 + 23, height - 127);
		curveVertex(width/2 + 16, height - 96);
		curveVertex(width/2 + 9, height - 81);
		curveVertex(width/2 + 1, height - 75);
		curveVertex(width/2 + 1, height - 75);
		curveVertex(width/2 + 2, height);
		curveVertex(width/2 + 2, height);
		endShape();
	}

	void stretch() {

		if (!ball) {
			stroke(0);
			noFill();
			beginShape();
			curveVertex(width/2 - 26, height - 135);
			curveVertex(width/2 - 26, height - 135);
			curveVertex(width/2 + 19, height - 140);
			curveVertex(width/2 + 19, height - 140);
			endShape();

			if (!release) {
				fill(0, 255, 0);
				ellipse(x, y, 14, 14);
			}

		}
		
		else {
			stroke(0);
			noFill();
			beginShape();
			curveVertex(width/2 - 26, height - 135);
			curveVertex(width/2 - 26, height - 135);
			curveVertex(mouseX, mouseY);
			curveVertex(width/2 + 19, height - 140);
			curveVertex(width/2 + 19, height - 140);
			endShape();
		
			fill(0, 255, 0);
			ellipse(mouseX, mouseY, 14, 14);
		}
	}

	
	void fly() {
		if (release) {
			
			x = (float) (x + vx);
			y = (float) (y + vy);
			vy = vy + gravity;
			
			fill(0, 255, 0);
			ellipse(x, y, 14, 14);
		}
	}
	
	void contain() {
		if (release) {
			// 7 is size of radius
			if (y > height - 7) {
				//println("floor");
				vy = -vy * 0.7;
				y = height - 6;
				// friction on ground
				vx = vx * 0.95;
			}
			if (x < 7) {
				//println("left");
				vx = -vx * 0.95;
				x = 6;
			}
			if (x > width - 7) {
				//println("right");
				vx = -vx * 0.95;
				x = width - 6;
			}
		}
	}
	
	public void mouseDragged() {
			
		if ((mouseX < width/2 +30) && (mouseX > width/2 -40) && (mouseY < height-120) && (mouseY > height-160)) {
			ball = true;
			drag = true;
			release = false;
					
			x = mouseX;
			y = mouseY;
		}
	}

	public void mouseReleased() {
		
		if (ball) {
			drag = false;
			release = true;
			
			x = mouseX;
			y = mouseY;
				
			// reversed for correct sign in quadrants
			xside = mouseX - xcenter;
			yside = ycenter - mouseY;
									
			theta = atan(yside / xside);
			
			// controls speed
			vi = sqrt(xside*xside + yside*yside) / 5.0;
			
			// direction
			if (xside > 0) {
				vi = -vi;
			}
			
			vx = vi * cos(theta);
			// negative to account for "backwards" y coordinates
			vy = - vi * sin(theta);
			
			// to fix vy being backwards
			if ((yside == 0) || (xside == 0)) {
				vy = -vy;
			}
		}
		ball = false;
	}
}
