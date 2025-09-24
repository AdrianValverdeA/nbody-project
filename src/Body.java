/******************************************************************************
 *  Compilation:  javac Body.java
 *  Execution:    java Body
 *  Dependencies: Vector.java StdDraw.java
 *
 *  Implementation of a 2D Body with a position, velocity and mass.
 *
 *
 ******************************************************************************/

public class Body {
    private Vector r;           // position
    private Vector v;           // velocity
    private double mass;  // mass
    private double G = 6.67e-11;
    private Vector accel;

    public Body(Vector r, Vector v, double mass) {
        this.r = r;
        this.v = v;
        this.mass = mass;
    }

    public Body(Vector r, Vector v, double mass, double G) {
      this.r = r;
      this.v = v;
      this.mass = mass;
      this.G = G;
    }

    public void move(Vector f, double dt) {
      Vector a = f.scale(1/mass); // f = m a
      this.v = this.v.plus(a.scale(dt)); // v = a t
      this.r = this.r.plus(this.v.scale(dt)); // e = v t
    }

    public Vector forceFrom(Body b) {
        Body a = this;
        if (b.G != 1) // per fer choreography correctament
        {
          double G = 1;
        }
        Vector delta = b.r.minus(a.r);
        double dist = delta.magnitude();
        double magnitude = (G * a.mass * b.mass) / (dist * dist);
        return delta.direction().scale(magnitude);
    }

    public Vector getBodyAcceleration()
    {
      return accel;
    }

    public Vector getBodyVelocity()
    {
      return v;
    }

    public void setBodyVelocity( Vector vNew)
    {
      this.v = vNew;
    }

    public void setBodyAcceleration(Vector aNew)
    {
      this.accel=aNew;
    }

    public Vector getPosition()
    {
        return r;
    }

    public double getBodyMass()
    {
      return mass;
    }

    public void setBodyPosition(Vector r)
    {
      this.r = r;
    }
    @Override
    public String toString() {
        return "position "+r.toString()+", velocity "+v.toString() + ", mass "+mass;
    }

}
