import processing.core.*;
import org.jbox2d.util.nonconvex.*;
import org.jbox2d.dynamics.contacts.*;
import org.jbox2d.testbed.*;
import org.jbox2d.collision.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.joints.*;
import org.jbox2d.p5.*;
import org.jbox2d.dynamics.*;

public class BodyRevolute extends PApplet {

	Physics physics;
			
		public void setup() {
			size(640, 480, P3D);
			frameRate(60);
			initScene();
			makeBody();
		}
		
		public void draw() {
			background(0);
			
			if (keyPressed) {
				physics.destroy();
				initScene();
				makeBody();
			}
			
			
		}
		
		void makeBody() {
			
			org.jbox2d.dynamics.Body head, body, leftarm, rightarm, leftleg, rightleg;

			head = physics.createCircle(300, 110, 20);
			body = physics.createRect(280, 140, 320, 220);
			leftarm = physics.createRect(255, 140, 270, 190);
			rightarm = physics.createRect(330, 140, 345, 190);
			leftleg = physics.createRect(280, 235, 295, 300);
			rightleg = physics.createRect(305, 235, 320, 300);
			
			// neck
			//physics.createDistanceJoint(head, body, 300, 130, 300, 160);
			
			physics.createRevoluteJoint(head, body, 300, 110);
			physics.createRevoluteJoint(body, leftarm, 260, 200);
			physics.createRevoluteJoint(body, rightarm, 340, 200);
			physics.createRevoluteJoint(body, leftleg, 290, 240);
			physics.createRevoluteJoint(body, rightleg, 310, 240);
		}
		
		
		void initScene() {
			float gravX = 0.0f;
			float gravY = -10.0f;
			
			float AABBWidth = 2*width;
			float AABBHeight = 2*height;
			float borderBoxWidth = width;
			float borderBoxHeight = height;
			float pixelsPerMeter = 30;
			physics = new Physics(this, width, height, gravX, gravY, AABBWidth, AABBHeight, borderBoxWidth, borderBoxHeight, pixelsPerMeter);
			physics.setDensity(1.0f);
		}
		

}
