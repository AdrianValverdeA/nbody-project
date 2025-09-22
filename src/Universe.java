import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/******************************************************************************
 *  Compilation:  javac Universe.java
 *  Execution:    java Universe dt input.txt
 *  Dependencies: Body.java Vector.java StdIn.java StdDraw.java
 *  Datafiles:    http://www.cs.princeton.edu/introcs/34nbody/2body.txt
 *                http://www.cs.princeton.edu/introcs/34nbody/3body.txt
 *                http://www.cs.princeton.edu/introcs/34nbody/4body.txt
 *                http://www.cs.princeton.edu/introcs/34nbody/2bodyTiny.txt
 *
 *  This data-driven program simulates motion in the universe defined
 *  by the standard input stream, increasing time at the rate on the
 *  command line.
 *
 *  %  java Universe 25000 4body.txt
 *
 *
 ******************************************************************************/

public class Universe {

    private int numBodies;
    private double radius;
    private Body[] bodies;

    public Universe(String fname)
    {
        try {
            Scanner in = new Scanner(new FileReader(fname));
            numBodies = Integer.parseInt(in.next());
            // the set scale to draw on the canvas
            radius = Double.parseDouble(in.next());
            // read and make the n bodies
            bodies = new Body[numBodies];
            for (int i = 0; i < numBodies; i++) {
                double rx = Double.parseDouble(in.next());
                double ry = Double.parseDouble(in.next());
                double vx = Double.parseDouble(in.next());
                double vy = Double.parseDouble(in.next());
                double mass = Double.parseDouble(in.next());
                double[] position = {rx, ry};
                double[] velocity = {vx, vy};
                Vector r = new Vector(position);
                Vector v = new Vector(velocity);
                bodies[i] = new Body(r, v, mass);
                System.out.println(bodies[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Universe(Body[] bodies, double radius) {

      this.bodies = bodies;
      this.numBodies = bodies.length;
      this.radius = radius;

      for (int i = 0; i < numBodies; i++) {

        System.out.println(bodies[i]);
      }

    }


    public void update(double dt)
    {
        Vector[] forces = new Vector[numBodies];

        for (int i = 0; i < numBodies; i++) {
            forces[i] = new Vector(2); // vector in 2D
        }
        Vector zeroVector = new Vector(2);
        for (int i=0; i<numBodies; i++)
        {
            for (int j=0; j<numBodies; j++)
            {
                if (i!=j)
                {
                    forces[i] = forces[i].plus(bodies[i].forceFrom(bodies[j]));
                    //adds the accumulation force by the others bodies in a Vector
                }
            }
             //adds the total force made by other bodies for knowing the new position and move it
        }
        for (int i = 0; i<numBodies; i++)
        {
          bodies[i].move(forces[i], dt);
        }

    }

    public Vector getBodyAcceleration(int i)
    {
      return bodies[i].getBodyAcceleration();
    }

    public Vector getBodyVelocity(int i)
    {
      return bodies[i].getBodyVelocity();
    }

    public double getBodyMass(int i)
    {
      return bodies[i].getBodyMass();
    }

    public Vector computeForceOn(int i)
    {
      return bodies[i].forceFrom(bodies[i]);
    }

    public double getRadius(){
        return radius;
    }

    public Vector getBodyPosition(int i)
    {
        return bodies[i].getPosition();
    }

    public int getNumBodies()
    {
        return numBodies;
    }

    public void setBodyVelocity(int i, Vector vNew)
    {
      bodies[i].setBodyVelocity(vNew);
    }

    public void setBodyAcceleration(int i, Vector vNew)
    {
      bodies[i].setBodyAcceleration(vNew);
    }

    public void setBodyPosition(int i, Vector r)
    {
      bodies[i].setBodyPosition(r);
    }



}
