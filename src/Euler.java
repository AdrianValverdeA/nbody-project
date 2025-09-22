public class Euler extends Integrator
{
  public Euler(double timeStep)
  {
    super(timeStep);
  }

  @Override
  public void move(Universe universe) {
    int numBodies = universe.getNumBodies();
    // move the bodies "synchronoulsy" : freeze them, compute the new position
    // and velocities of all the bodies and *then* update them all. Otherwise
    // in computeForce() we would mix old and new body positions
    Vector[] newPositions = new Vector[numBodies];
    Vector[] newVelocities = new Vector[numBodies];
    for (int i=0; i<numBodies; i++) {
      Vector a = universe.computeForceOn(i).scale(1.0 / universe.getBodyMass(i));
      newVelocities[i] = universe.getBodyVelocity(i).plus(a.scale(timeStep));
      newPositions[i] = universe.getBodyPosition(i).plus(newVelocities[i].scale(timeStep));
    }
    for (int i=0; i<numBodies; i++) {
      universe.setBodyPosition(i, newPositions[i]);
      universe.setBodyVelocity(i, newVelocities[i]);
      // no need to set the acceleration in Euler integration
    }

  }

}
