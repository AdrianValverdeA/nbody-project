public class Main {
  public static void main(String[] args) {
    int numargs = args.length;
    assert numargs == 4 : "invalid number of arguments";
    double dt = Double.parseDouble(args[0]);
    int pauseTime = Integer.parseInt(args[1]);
    boolean trace = args[2].toLowerCase().equals("trace");
    System.out.println(trace);
    String fname = args[3];
    //Universe universe = new Universe(fname); // we need to be modifying the entry parameters
    Universe universe = UniverseFactory.makeChoreography(1);
    //Universe universe = UniverseFactory.makeUniverseFromFile(fname);
    //Universe universe = UniverseFactory.makeCentralConfiguration(10, Math.PI/4 );
    //Universe universe = UniverseFactory.makePlanetaryConfiguration(7); //well done



    Integrator integrator = new Euler(10);
    //universe.setIntegrator(integrator);

    //NBodySimulator simulator = new NBodySimulator(universe, dt, pauseTime, trace);
      NBodySimulator simulator = new NBodySimulator(universe, dt, pauseTime, trace, integrator);

    //System.out.println(universe.getNumBodies());
    simulator.simulate();
  }
}