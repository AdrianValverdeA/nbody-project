public class Main {
  public static void main(String[] args) {
    int numargs = args.length;
    assert numargs == 4 : "invalid number of arguments";
    double dt = Double.parseDouble(args[0]);
    int pauseTime = Integer.parseInt(args[1]);
    boolean trace = args[2].toLowerCase().equals("trace");
    String fname = args[3];
    //Universe universe = new Universe(fname); // pas extension refactorization
    Universe universe = UniverseFactory.makeChoreography(1); //1e-4 0 trace data/3body.txt
    //Universe universe = UniverseFactory.makeUniverseFromFile(fname);  //1000 0 trace data/3body.txt
    //Universe universe = UniverseFactory.makeCentralConfiguration(10, Math.PI/4 ); // 100 10 trace data/4body.txt
    //Universe universe = UniverseFactory.makePlanetaryConfiguration(7); // 8 2 trace data/4body.txt

    Integrator integrator = new Euler(dt);
    //Integrator integrator = new Leapfrog(dt);

    //NBodySimulator simulator = new NBodySimulator(universe, dt, pauseTime, trace); // pas extension configurations
      NBodySimulator simulator = new NBodySimulator(universe, dt, pauseTime, trace, integrator);

    simulator.simulate();
  }
}