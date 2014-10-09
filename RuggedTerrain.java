import java.util.ArrayList;

import processing.core.*;

import pbox2d.*;
import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;

public class RuggedTerrain extends PApplet {
	
	PBox2D box2d;
	
	BuildGround surface;
	ArrayList<Circle> circles = new ArrayList<Circle>();

	
	public void setup() {
		size(600, 400);
		smooth();
		
		initScene();
		
	}
	
	public void draw() {
		
		background(255);
		surface.display();
		
		box2d.step();
	
		
		if (mousePressed) {
			circles.add(new Circle(mouseX, mouseY, 6));
			
		}
		
		if (keyPressed) {
			circles.clear();
			initScene();
		}
		
		for (Circle c: circles) {
			c.display();
		}
		
	}
	
	
	void initScene() {
		
		background(255);
				
		box2d = new PBox2D(this);
		box2d.createWorld();
		box2d.setGravity(0, -10);
		
		surface = new BuildGround();

				
	}
	
	
	class Circle  {

		  // We need to keep track of a Body and a width and height
		  Body body;
		  float r;

		  // Constructor
		  Circle(float x, float y, float r_) {
			  r = r_;
			  makeBody(x, y, r);
		  }

		  // This function removes the particle from the box2d world
		  void killBody() {
		    box2d.destroyBody(body);
		  }

		  // Is the particle ready for deletion?
		  boolean done() {
		    // Let's find the screen position of the particle
		    Vec2 pos = box2d.getBodyPixelCoord(body);
		    // Is it off the bottom of the screen?
		    if (pos.y > height+ r*2) {
		      killBody();
		      return true;
		    }
		    return false;
		  }

		  // Drawing the box
		  void display() {
		    // We look at each body and get its screen position
		    Vec2 pos = box2d.getBodyPixelCoord(body);
		    // Get its angle of rotation
		    float a = body.getAngle();

		    strokeWeight(1);
		    pushMatrix();
		    translate(pos.x,pos.y);
		    rotate(-a);
		    fill(175);
		    stroke(0);
		    ellipse(0, 0, r*2, r*2);
		    line(0, 0, r, 0);
		    popMatrix();
		  }

		  // This function adds the circle to the box2d world
		  void makeBody(float x, float y, float r) {
			  
			  BodyDef bd = new BodyDef();
			  bd.position = box2d.coordPixelsToWorld(x, y);
			  body = box2d.world.createBody(bd);

		    CircleDef cd = new CircleDef();
		    cd.radius = box2d.scalarPixelsToWorld(r);

		    // Parameters that affect physics
		    cd.density = 1.0f;
		    cd.friction = 0.01f;
		    cd.restitution = 0.3f;

		    body.createShape(cd);
		    body.setMassFromShapes();

		    // Give it some initial random velocity
		    body.setLinearVelocity(new Vec2(random(-5,5),random(2,5)));
		    body.setAngularVelocity(random(-5,5));

		  }
	}


	
	public class BuildGround {
		
		// track ground points
		ArrayList<Vec2> ground;
		
		// constructor
		BuildGround() {
			ground = new ArrayList<Vec2>();
			
			// put ground in world
			EdgeChainDef edges = new EdgeChainDef();
			
			// perlin noise
			float time = 0.0f;
			
			// MUST BE BACKWARDS or they won't bounce off the surface
			for (float x = width + 10; x > -10; x -= 18) {
				
				float y;
				y = noise(time) * height * 1.2f;

				
				
				// edge point in window
				Vec2 screenEdge = new Vec2(x, y);
				ground.add(screenEdge);
				
				// convert to box2d world
				Vec2 edge = box2d.coordPixelsToWorld(screenEdge);
				edges.addVertex(edge);
				
				// increment
				time += 0.1f;
			}
			
			
			edges.setIsLoop(false);
			edges.friction = 2.0f;
			edges.restitution = 0.3f;
			
			// make the edge chain a body
			BodyDef bd = new BodyDef();
			bd.position.set(0.0f, 0.0f);
			Body body = box2d.world.createBody(bd);
			body.createShape(edges);
			
		}
		
		void display() {
			strokeWeight(2);
			stroke(0);
			noFill();
			beginShape();
			for (Vec2 v: ground) {
				vertex(v.x, v.y);
			}
			endShape();
		}
	}
}