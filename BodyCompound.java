import processing.core.*;
import org.jbox2d.util.nonconvex.*;
import org.jbox2d.dynamics.contacts.*;
import org.jbox2d.testbed.*;
import org.jbox2d.collision.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.joints.*;
import org.jbox2d.p5.*;
import org.jbox2d.dynamics.*;

public class BodyCompound extends PApplet {
	
	Physics physics;
	
	public void setup() {
		size(640, 420, P3D);
		initScene();
		create();
	}
	
	public void draw() {
		background(255);
	}

	void create() {
		
		PolygonDef shape1 = new PolygonDef();
		shape1.addVertex(physics.screenToWorld(120, 120));
		shape1.addVertex(physics.screenToWorld(120, 100));
		shape1.addVertex(physics.screenToWorld(100, 100));
		 
		// set general shape properties
		shape1.density = physics.getDensity();
		shape1.friction = physics.getFriction();
		shape1.restitution = physics.getRestitution();
		shape1.isSensor = physics.getSensor();
		
		CircleDef shape2 = new CircleDef();
		shape2.radius = physics.screenToWorld(10);
		shape2.localPosition.set(physics.screenToWorld(120, 120));
		 
		// set general shape properties
		shape2.density = physics.getDensity();
		shape2.friction = physics.getFriction();
		shape2.restitution = physics.getRestitution();
		shape2.isSensor = physics.getSensor();
		
		BodyDef bd = new BodyDef();
		bd.isBullet = physics.getBullet();
		 
		org.jbox2d.dynamics.Body body = physics.getWorld().createBody(bd);
		body.createShape(shape1);
		body.createShape(shape2);
		 
		if (physics.getDensity() > 0.0f) {
		  body.setMassFromShapes();
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
		physics = new Physics(this, width, height, gravX, gravY, AABBWidth, AABBHeight, borderBoxWidth, borderBoxHeight, pixelsPerMeter);
		physics.setDensity(1.0f);
	}
}
