import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.compare;
import static java.lang.String.format;
import static java.util.Collections.emptyList;
import static java.util.Collections.min;
import static java.util.Collections.sort;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Main {

    public static int BRANCH = 0;

    static class Solver {

        private List<Integer> target;
        private List<Node> nodes;
        private List<Integer> start;

        class Node implements Comparable<Node> {

            private int i;
            private int j;
            private Map<Integer, Integer> changes;
            public int value;
            private boolean finished;
            private Node parent;
            public int estimate;
            private int hammingDistance;

            public Node(List<Integer> values) {
                parent = null;
                value = 0;
                i = -1;
                j = -1;
                finished = false;
            }

            public Node(Node parent, int i, int j) {
                this.parent = parent;
                this.i = i;
                this.j = j;
                changes = new HashMap<Integer, Integer>();
                changes.put(i, parent.get(j));
                changes.put(j, parent.get(i));
                value = parent.value + parent.get(i) + parent.get(j);
                estimate = value;
                hammingDistance = 0;
                for (int k = 0; k < target.size(); k++)
                    if (get(k) != target.get(k)) {
                        estimate += get(k);
                        hammingDistance++;
                    }
                finished = hammingDistance == 0;
            }

            private Integer get(int index) {
                if (parent == null)
                    return start.get(index);
                if (changes.containsKey(index))
                    return changes.get(index);
                return parent.get(index);
            }

            public List<Node> branch() {
                if (++BRANCH % 100 == 0)
                    System.out.println(BRANCH);
                if (finished)
                    return emptyList();

                List<Node> childs = new ArrayList<Node>();
                for (int k = 0; k < start.size(); k++)
                    for (int l = k + 1; l < start.size(); l++)
                        if (validate(k, l))
                            childs.add(new Node(this, k, l));
                return childs;
            }

            private boolean validate(int k, int l) {
                return (k != i || l != j) && (parent == null || parent.validate(k, l));
            }

            @Override
            public String toString() {
                if (i == -1 && j == -1)
                    return "";
                return format("%s(%s-%s)", parent, i, j);
            }

            @Override
            public int compareTo(Node node) {
                int e = compare(estimate, node.estimate);
                if (e != 0)
                    return e;
                return compare(hammingDistance, node.hammingDistance);
            }

        }

        public Solver(List<Integer> values) {
            start = values;
            target = new ArrayList<Integer>(values);
            sort(target);
            nodes = new ArrayList<Node>();
            nodes.add(new Node(values));
        }

        public int solve() {
            while (!finished()) {
                branch();
                cut();
            }

            if (nodes.isEmpty())
                return 0;
            System.out.println(bestFinished());
            return bestFinished().value;
        }

        private void cut() {
            Node bestNode = bestFinished();
            if (bestNode == null)
                return;

            for (Iterator<Node> it = nodes.iterator(); it.hasNext();)
                if (it.next().estimate < bestNode.estimate)
                    it.remove();
        }

        private Node bestFinished() {
            int bestValue = MAX_VALUE;
            Node bestNode = null;
            for (Node node : nodes)
                if (node.estimate < bestValue && node.finished) {
                    bestValue = node.estimate;
                    bestNode = node;
                }
            return bestNode;
        }

        private void branch() {
            Node bestNode = bestNode();
            nodes.remove(bestNode);
            nodes.addAll(bestNode.branch());
        }

        private boolean finished() {
            return nodes.isEmpty() || bestNode().finished;
        }

        private Node bestNode() {
            return min(nodes);
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        // System.out.println(solve(Arrays.asList(1, 198, 56, 26, 129, 185, 47,
        // 69, 195, 16, 28, 83, 154, 100, 118, 9, 13, 109,
        // 149, 10, 140, 161, 152, 19, 98, 184, 73, 63, 90, 97, 144, 38, 151,
        // 131, 139, 134, 125, 166, 88, 33, 36,
        // 123, 17, 180, 148, 68, 22, 14, 7, 65, 105, 71, 85, 132, 95, 126, 141,
        // 55, 64, 128, 32, 146, 61, 21,
        // 172, 34, 89, 2, 127, 167, 49, 143, 174, 24, 66, 76, 163, 25, 60, 92,
        // 15, 171, 101, 77, 186, 67, 6, 35,
        // 62, 157, 142, 46, 45, 41, 197, 188, 31, 74, 164, 37)));

        // System.out.println(solve(Arrays.asList(89, 30, 26, 132, 126, 82, 68,
        // 98, 178, 52, 189, 41, 190, 167, 161, 168,
        // 92, 160, 49, 44, 77, 69, 97, 107, 156, 5, 0, 139, 191, 38, 135, 31,
        // 90, 42, 86, 136, 153, 106, 63, 53,
        // 55, 121, 2, 181, 11, 61, 17, 188, 184, 171)));
        // System.out.println(solve(Arrays.asList(1, 8, 9, 7, 6)));

        int i = 1;
        for (List<Integer> problemCase : loadCases(System.in))
            // for (List<Integer> problemCase : loadCases(new
            // ByteArrayInputStream("5 1 8 9 7 6 0".getBytes())))
            // for (List<Integer> problemCase : loadCases(new
            // ByteArrayInputStream(
            // "3 3 2 1 4 8 1 2 4 5 1 8 9 7 6 6 8 4 5 3 2 7 0 ".getBytes())))
            System.out.printf("case %s: %s\n\n", i++, solve(problemCase));
    }

    private static int solve(List<Integer> problemCase) {
        if (problemCase.size() > 750)
            throw new RuntimeException();
        return 0;
        // return new Solver(problemCase).solve();
    }

    private static List<List<Integer>> loadCases(InputStream in) {
        List<List<Integer>> caseList = new ArrayList<List<Integer>>();
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(in);
        while (scanner.hasNext()) {
            List<Integer> list = new ArrayList<Integer>();
            int c = scanner.nextInt();
            if (c == 0)
                break;
            while (c-- > 0)
                list.add(scanner.nextInt());
            caseList.add(list);
        }
        return caseList;
    }

}
