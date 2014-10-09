import processing.core.*;
import org.jbox2d.util.nonconvex.*;
import org.jbox2d.dynamics.contacts.*;
import org.jbox2d.testbed.*;
import org.jbox2d.collision.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.joints.*;
import org.jbox2d.p5.*;
import org.jbox2d.dynamics.*;

public class RevoluteJoint extends PApplet {

	Physics physics;

	public void setup() {
	  size(640, 480, P3D);
	  frameRate(60);
	  initScene();
	  initJoint();
	}

	public void draw() {
		
	  background(0);
	  
	  if (keyPressed) {
	        
	    switch(key) {
	      
	      // circles
	      case '1':
	        physics.createCircle(mouseX, mouseY, random(5, 10));
	        break;
	        
	      // rectangles
	      case '2':
	        float sz = random(5, 10);
	        physics.createRect(mouseX - sz, mouseY - sz, mouseX + sz,
	                           mouseY + sz);
	        break;
	      
	      // reset everything
	      default:
	        physics.destroy();
	        initScene();
	        break;
	    }
	  }
	}
	    
	    

	void initScene() {
	  float gravX = 0.0f;
	  float gravY = -10.0f;
	  float AABBWidth = 2*width;
	  float AABBHeight = 2*height;
	  float borderBoxWidth = width;
	  float borderBoxHeight = height;
	  float pixelsPerMeter = 30;
	  physics = new Physics(this, width, height, gravX,
	                        gravY, AABBWidth, AABBHeight,
	                        borderBoxWidth, borderBoxHeight,
	                        pixelsPerMeter);
	  
	  
	}
	  
	void initJoint() {
	  
		org.jbox2d.dynamics.Body c, d;
		c = null;
		d = null;
		
		physics.setDensity(0.0f);
		c = physics.createRect(295, 200, 345, 250);
		physics.setDensity(1.0f);
		d = physics.createCircle(320, 350, 20);
		
		RevoluteJointDef myJoint = new RevoluteJointDef();
		
		Vec2 myVec = new Vec2();
		myJoint.body1 = c;
		myJoint.body2 = d;
		
		//myJoint.localAnchor1 = c.getLocalPoint(worldCenter);
		
		/*
		myVec.set(320, 225);
	  
		
		myJoint.initialize(c, d, myVec);
		myJoint.enableMotor = true;
		myJoint.motorSpeed = 10;
		myJoint.maxMotorTorque = 200;
		*/
		

		//physics.createRevoluteJoint(c, d, 320, 225);  	  
	  
	}
}
