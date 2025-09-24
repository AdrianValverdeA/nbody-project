public class NBodySimulator
{
    private double timeStep;
    private int pauseTime;
    private boolean trace;
    private Universe universe;
    private Integrator integrator;

    /*public NBodySimulator(Universe universe, double dt, int pt, boolean doTrace) {
        this.universe = universe;
        timeStep = dt;
        pauseTime = pt;
        trace = doTrace;
    }

     */ // pas extension configurations

    public NBodySimulator(Universe universe, double dt, int pt, boolean doTrace, Integrator integrator) {
        this.universe = universe;
        timeStep = dt;
        pauseTime = pt;
        trace = doTrace;
        this.integrator = integrator;
    }

    private void createCanvas()
    {
        //StdDraw.setCanvasSize(700, 700); // uncomment for a larger window
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenRadius(0.025);
        double radius = universe.getRadius();
        // read from txt file, second line
        StdDraw.setXscale(-radius, +radius);
        StdDraw.setYscale(-radius, +radius);
    }

    private void drawUniverse()
    {
        int n = universe.getNumBodies();
        Vector[] position = new Vector[n];
        double x, y;
        double[] data = {0.0, 0.0};

        for (int i=0; i < n; i++)
        {
            position[i] = universe.getBodyPosition(i);
            data[0] = position[i].cartesian(0);
            data[1] = position[i].cartesian(1);
            StdDraw.point(data[0],data[1]);
        }

    }

    public void simulate()
    {
        this.createCanvas();
        StdDraw.clear(StdDraw.GRAY);
        while(trace)
        {
          StdDraw.setPenColor(StdDraw.WHITE);
          this.drawUniverse();
          //universe.update(timeStep); // pas refactorization
          integrator.move(universe);
          StdDraw.setPenColor(StdDraw.BLACK);
          this.drawUniverse();
          StdDraw.show();
          StdDraw.pause(pauseTime);
        }
        while(!trace)
        {
          StdDraw.clear();
          //universe.update(timeStep); // pas refactorization
          integrator.move(universe);
          this.drawUniverse();
          StdDraw.show();
          StdDraw.pause(pauseTime);
        }
    }
}
