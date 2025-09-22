public abstract class Integrator
{
  protected double timeStep;

  public Integrator(double timeStep)
  {
    this.timeStep = timeStep;
  }

  public abstract void move(Universe universe);

}
