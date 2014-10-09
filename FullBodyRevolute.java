import processing.core.*;
import org.jbox2d.util.nonconvex.*;
import org.jbox2d.dynamics.contacts.*;
import org.jbox2d.testbed.*;
import org.jbox2d.collision.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.joints.*;
import org.jbox2d.p5.*;
import org.jbox2d.dynamics.*;

public class FullBodyRevolute extends PApplet {

	Physics physics;
			
		public void setup() {
			size(640, 880, P3D);
			//size(640, 680, P3D);
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
			
			org.jbox2d.dynamics.Body head, bodyTop, bodyMiddle, bodyBottom, leftArmUpper, leftArmFore, rightArmUpper, rightArmFore, leftLegUpper, leftLegLower, rightLegUpper, rightLegLower;

			
			// body parts
			head = physics.createCircle(300, 110, 20);
			bodyTop = physics.createRect(270, 140, 330, 170);
			bodyMiddle = physics.createRect(270, 165, 330, 195);
			bodyBottom = physics.createRect(270, 190, 330, 220);
			
			
			leftArmUpper = physics.createRect(250, 140, 265, 190);
			leftArmFore = physics.createRect(250, 180, 265, 230);
			

			rightArmUpper = physics.createRect(335, 140, 350, 190);
			rightArmFore = physics.createRect(335, 180, 350, 230);
			
			
			leftLegUpper = physics.createRect(280, 215, 295, 275);
			leftLegLower = physics.createRect(280, 270, 295, 340);
			
			rightLegUpper = physics.createRect(305, 215, 320, 275);
			rightLegLower = physics.createRect(305, 270, 320, 340);
			
			// joints		
			physics.createRevoluteJoint(head, bodyTop, 300, 110);
			physics.createRevoluteJoint(bodyTop, bodyMiddle, 300, 155);
			physics.createRevoluteJoint(bodyMiddle, bodyBottom, 300, 180);
			
			physics.createRevoluteJoint(leftArmUpper, bodyTop, 260, 150);
			physics.createRevoluteJoint(leftArmFore, leftArmUpper, 260, 190);
			
			physics.createRevoluteJoint(bodyTop, rightArmUpper, 300, 155);
			physics.createRevoluteJoint(rightArmFore, rightArmUpper, 340, 190);
			
			physics.createRevoluteJoint(leftLegUpper, bodyBottom, 285, 220);
			physics.createRevoluteJoint(leftLegLower, leftLegUpper, 285, 275);
			
			physics.createRevoluteJoint(rightLegUpper, bodyBottom, 310, 220);
			physics.createRevoluteJoint(rightLegLower, rightLegUpper, 310, 275);
			

		
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
			
			physics.setDensity(0.0f);
			physics.createRect(270, 380, 370, 480);
			physics.createCircle(260, 250, 20);
			physics.createRect(470, 550, 600, 630);
			physics.setDensity(1.0f);
			
			
			
		}
		

}
