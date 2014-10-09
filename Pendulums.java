import processing.core.*;
import org.jbox2d.util.nonconvex.*;
import org.jbox2d.dynamics.contacts.*;
import org.jbox2d.testbed.*;
import org.jbox2d.collision.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.joints.*;
import org.jbox2d.p5.*;
import org.jbox2d.dynamics.*;

public class Pendulums extends PApplet {
	
	Physics physics;
	
	public void setup() {
		size(640, 480, P3D);
		frameRate(60);
		initScene();
		//create();
	}
	
	public void draw() {
		background(0);
		
		MySwitch();
		
	}
	
	void MySwitch() {
		
		if (keyPressed) {
			
			if (key == '1') {
				physics.createCircle(mouseX, mouseY, random(5, 10));
			}
				
			else if (key == '2') {
				float sz = random(5, 10);
				physics.createRect(mouseX - sz, mouseY - sz, mouseX + sz, mouseY + sz);
			}
			
			else {
				physics.destroy();
				initScene();
			}
		}
	}
	
	void initScene() {
		float gravX = 3.0f;
		float gravY = 10.0f;
		
		float AABBWidth = 2*width;
		float AABBHeight = 2*height;
		float borderBoxWidth = width;
		float borderBoxHeight = height;
		float pixelsPerMeter = 30;
		physics = new Physics(this, width, height, gravX, gravY, AABBWidth, AABBHeight, borderBoxWidth, borderBoxHeight, pixelsPerMeter);
		physics.setDensity(1.0f);
	}
	

}
