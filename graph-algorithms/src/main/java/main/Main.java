package main;

import static java.lang.String.format;

import java.io.File;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedSubgraph;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.Startable;
import org.picocontainer.behaviors.Caching;

import fas.algorithms.ArcSetAlgorithm;
import fas.algorithms.eades.EadesAlgorithm;
import fas.algorithms.saab.SEParams;
import fas.algorithms.saab.SaabAlgorithm;
import fas.algorithms.saab.StochasticEvolution;
import fas.algorithms.sifting.KobilanskiChanasAlgorithm;

public class Main implements Startable {

    private ArcSetAlgorithm algorithm;
    private DirectedGraph<Integer, DefaultEdge> graph;
    private Set<DefaultEdge> arcSet;
    private double miliseconds;

    public Main(ArcSetAlgorithm algorithm, DirectedGraph<Integer, DefaultEdge> graph) {
        this.algorithm = algorithm;
        this.graph = graph;
    }

    @Override
    public void start() {
        long start = new Date().getTime();
        arcSet = algorithm.feedbackArcSet(graph);
        miliseconds = (new Date().getTime() - start) / 1000.0;
        DirectedSubgraph<Integer, DefaultEdge> g = new DirectedSubgraph<>(graph, graph.vertexSet(), null);
        g.removeAllEdges(arcSet);
        if (hasCicle(g))
            throw new RuntimeException("Feedback Arc Set Fail: " + algorithm);
    }

    @Override
    public void stop() {
    }

    @Override
    public String toString() {
        return String.format("%s(%s, %.3f)", algorithm, arcSet.size(), miliseconds);
    }

    public static void main(String[] args) throws InterruptedException {
        while(true)
            runGraph("P1000-25000.dat");
    }

    private static void runGraph(String name) {
        GraphLoader loader = new GraphLoader();
        String fileName = Main.class.getResource(name).getFile();
        DirectedGraph<Integer, DefaultEdge> graph = loader.loadDirectedGraph(new File(fileName));
        System.out.printf("(%s, %s) %s ", graph.vertexSet().size(), graph.edgeSet().size(), d(graph));
        Main s = runSaab(graph);
        System.out.println(s);
    }

    private static String d(DirectedGraph<Integer, DefaultEdge> g) {
        int n = g.vertexSet().size();
        int m = g.vertexSet().size();
        double maxm = (n * n + n) / 2;
        return String.format("%.3f", 100 * m / maxm);
    }

    private static Main runKC(DirectedGraph<Integer, DefaultEdge> graph) {
        DefaultPicoContainer container = new DefaultPicoContainer(new Caching());
        container.addComponent(Main.class);
        container.addComponent(KobilanskiChanasAlgorithm.class);
        container.addComponent(LinearArrangementWrapper.class);
        container.addComponent(graph);
        container.start();
        return container.getComponent(Main.class);
    }

    private static Main runELS(DirectedGraph<Integer, DefaultEdge> graph) {
        DefaultPicoContainer container = new DefaultPicoContainer(new Caching());
        container.addComponent(Main.class);
        container.addComponent(EadesAlgorithm.class);
        container.addComponent(LinearArrangementWrapper.class);
        container.addComponent(graph);
        container.start();
        return container.getComponent(Main.class);
    }

    private static Main runSaab(DirectedGraph<Integer, DefaultEdge> graph) {
        DefaultPicoContainer container = new DefaultPicoContainer(new Caching());
        container.addComponent(Main.class);
        container.addComponent(new Random());
        container.addComponent(StochasticEvolution.class);
        container.addComponent(new SEParams(1000, 10, 1));
        container.addComponent(SaabAlgorithm.class);
        container.addComponent(graph);
        container.start();
        return container.getComponent(Main.class);
    }

    private static boolean hasCicle(DirectedGraph<Integer, DefaultEdge> graph) {
        return new FASGraphWrapper(graph).hasCicle();
    }

    public static void prinDOTGraph(DirectedGraph<Integer, DefaultEdge> graph) {
        for (DefaultEdge e : graph.edgeSet())
            System.out.println(format("%s -> %s", graph.getEdgeSource(e), graph.getEdgeTarget(e)));
    }
}
