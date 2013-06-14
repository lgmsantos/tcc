package main;

import static java.lang.String.format;
import static main.LinearArrangementWrapper.wrap;

import java.io.File;
import java.util.Random;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import fas.algorithms.eades.EadesAlgorithm;
import fas.algorithms.eades.LinearArrangementAlgorithm;
import fas.algorithms.saab.ArcSetAlgorithm;
import fas.algorithms.saab.SaabAlgorithm;

public class Main {

    public static void main(String[] args) {
//        run(new KwiksortAlgorithm1(new Random()), 10);
//        run(new KwiksortAlgorithm2(new Random()), 10);
//        run(new MultipleSiftingAlgorithm(), 1);
//        run(new KobilanskiChanasAlgorithm(), 1);
        run(new EadesAlgorithm(), 1);
        run(new SaabAlgorithm(new Random()), 1);
    }
    
    private static void run(LinearArrangementAlgorithm alg, int iteration) {
        run(wrap(alg), iteration);
    }

    private static void run(ArcSetAlgorithm alg, int iteration) {
        System.out.println(alg);
        while (iteration-- > 0)
            iterate(alg);
    }

    private static void iterate(ArcSetAlgorithm alg) {
        GraphLoader graphLoader = new GraphLoader();
        File file = new File("bin/main/P50-100.dat").getAbsoluteFile();
        //File file = new File("bin/main/P1000-25000.dat").getAbsoluteFile();
        DirectedGraph<Integer, DefaultEdge> graph = graphLoader.loadDirectedGraph(file);
        Set<DefaultEdge> fas = alg.feedbackArcSet(graph);
        System.out.printf("%s, %s, Score %s ", hasCicle(graph), graph.edgeSet().size(), fas.size());
        graph.removeAllEdges(fas);
        System.out.println(hasCicle(graph));
    }

    private static boolean hasCicle(DirectedGraph<Integer, DefaultEdge> graph) {
        return new FASGraphWrapper(graph).hasCicle();
    }

    public static void prinDOTGraph(DirectedGraph<Integer, DefaultEdge> graph) {
        for (DefaultEdge e : graph.edgeSet())
            System.out.println(format("%s -> %s", graph.getEdgeSource(e), graph.getEdgeTarget(e)));
    }
}
