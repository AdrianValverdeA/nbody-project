public class Leapfrog extends Integrator
{

  public Leapfrog(double timeStep)
  {
    super(timeStep);
  }

  @Override
  public void move(Universe universe)
  {
    int numBodies = universe.getNumBodies();
    for (int i=0; i<numBodies; i++) {
      Vector a = universe.computeForceOn(i).scale(1.0 / universe.getBodyMass(i));
      Vector x = universe.getBodyPosition(i);
      Vector v = universe.getBodyVelocity(i);
      Vector xNew = (x.plus(v.scale(timeStep))).plus((a.scale(timeStep*timeStep)).scale(0.5));
      universe.setBodyPosition(i, xNew);
      universe.setBodyAcceleration(i, a);
    }
    for (int i=0; i<numBodies; i++) {
      // now that all new positions have been computed, calculate the new accelerations
      Vector aNew = universe.computeForceOn(i).scale(1.0 / universe.getBodyMass(i));
      // and now the new velocity
      Vector v = universe.getBodyVelocity(i);
      Vector vNew = v.plus((universe.getBodyAcceleration(i).plus(aNew)).scale(0.5).scale(timeStep));
      // store new acceleration and velocity into body for the next time step
      universe.setBodyVelocity(i, vNew);
      universe.setBodyAcceleration(i, aNew);
    }

  }

}
