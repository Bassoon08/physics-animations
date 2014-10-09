import processing.core.PApplet;

public class AngrySlingshot extends PApplet {

	boolean drag = false;
	boolean release = false;
	
	// for ball movement
	int x;
	int y;
	float vi;
	float theta;
	float vx = vi * cos(theta);
	float vy = vi * sin(theta);
	
	
	public void setup() {
		smooth();
		size(200, 200);
		frameRate(20);
	}
	
	public void draw() {
		
		background(220);
		
		slingshot();
		stretch();
		fly();
	}
	
	void slingshot() {

		noStroke();
		fill(139, 90, 43);
		beginShape();
		curveVertex(90, 200);
		curveVertex(90, 200);
		curveVertex(91, 125);
		curveVertex(90, 125);
		curveVertex(77, 109);
		curveVertex(72, 97);
		curveVertex(70, 60);
		curveVertex(75, 54);
		curveVertex(81, 56);
		curveVertex(84, 92);
		curveVertex(93, 109);
		curveVertex(95, 114);
		curveVertex(97, 114);
		curveVertex(104, 103);
		curveVertex(111, 87);
		curveVertex(113, 53);
		curveVertex(121, 51);
		curveVertex(125, 57);
		curveVertex(123, 73);
		curveVertex(116, 104);
		curveVertex(109, 119);
		curveVertex(101, 125);
		curveVertex(101, 125);
		curveVertex(102, 200);
		curveVertex(102, 200);
		endShape();
	}
	
	void stretch() {
		if (!drag) {
			stroke(0);
			noFill();
			beginShape();
			curveVertex(74, 65);
			curveVertex(74, 65);
			curveVertex(119, 60);
			curveVertex(119, 60);
			endShape();		
		}
		
		else {
			stroke(0);
			noFill();
			beginShape();
			curveVertex(74, 65);
			curveVertex(74, 65);
			curveVertex(mouseX, mouseY);
			curveVertex(119, 60);
			curveVertex(119, 60);
			endShape();
			
			fill(0, 255, 0);
			ellipse(mouseX, mouseY, 14, 14);
		}
	}
	
	void fly() {
		if (release) {

			x = (int) (x + vx);
			y = (int) (y - vy);
			
			fill(0, 255, 0);
			ellipse(x, y, 14, 14);
		}

	}
	
	void getPosition() {
		if (mousePressed) {
			println(mouseX + " " + mouseY);
		}
	}

	public void mouseDragged() {
		drag = true;
		release = false;

	}
	
	public void mouseReleased() {
		release = true;
		drag = false;
		x = mouseX;
		y = mouseY;
		println("released, " + mouseX + " " + mouseY);

	}
	
}
