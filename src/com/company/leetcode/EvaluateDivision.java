package com.company.leetcode;

import java.util.*;

public class EvaluateDivision {


    private static class Edge {

        double cost;
        String desc;

        Edge(String desc, double cost) {
            this.desc = desc;
            this.cost = cost;
        }


    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of hash tables such as those
     * provided by {@link HashMap}.
     * <p>
     * The general contract of {@code hashCode} is:
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during
     *     an execution of a Java application, the {@code hashCode} method
     *     must consistently return the same integer, provided no information
     *     used in {@code equals} comparisons on the object is modified.
     *     This integer need not remain consistent from one execution of an
     *     application to another execution of the same application.
     * <li>If two objects are equal according to the {@code equals(Object)}
     *     method, then calling the {@code hashCode} method on each of
     *     the two objects must produce the same integer result.
     * <li>It is <em>not</em> required that if two objects are unequal
     *     according to the {@link Object#equals(Object)}
     *     method, then calling the {@code hashCode} method on each of the
     *     two objects must produce distinct integer results.  However, the
     *     programmer should be aware that producing distinct integer results
     *     for unequal objects may improve the performance of hash tables.
     * </ul>
     * <p>
     * As much as is reasonably practical, the hashCode method defined by
     * class {@code Object} does return distinct integers for distinct
     * objects. (This is typically implemented by converting the internal
     * address of the object into an integer, but this implementation
     * technique is not required by the
     * Java&trade; programming language.)
     *
     * @return a hash code value for this object.
     * @see Object#equals(Object)
     * @see System#identityHashCode
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        Map<String, List<Edge>> map = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            map.computeIfAbsent(a, k -> new ArrayList<>()).add(new Edge(b, values[i]));
            map.computeIfAbsent(b, k -> new ArrayList<>()).add(new Edge(a, 1.0 / values[i]));

            map.get(a).add(new Edge(a, 1.0));
            map.get(b).add(new Edge(b, 1.0));

        }

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {

            res[i] = bfs(map, queries.get(i).get(0), queries.get(i).get(1));
            System.out.println(res[i]);
        }

        return res;
    }

    private static double bfs(Map<String, List<Edge>> map, String a, String b) {
        if (!map.containsKey(a)) {
            return -1;
        }
        Queue<Edge> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.addAll(map.get(a));
        visited.add(a);
        while (!queue.isEmpty()) {
            Edge e = queue.remove();

            if (e.desc.equals(b)) {
                return e.cost;
            }

            if(!map.containsKey(e.desc)){
                continue;
            }
            visited.add(e.desc);
            for(Edge ee: map.get(e.desc)){
                if(visited.contains(ee.desc)){
                    continue;
                }
                queue.add(new Edge(ee.desc, e.cost*ee.cost));
            }
        }

        return -1;
    }


    public static void main(String[] args) {

        double[] res = calcEquation(Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c")),
                new double[]{2.0, 3.0},
                Arrays.asList(
                        Arrays.asList("a", "c"),
                        Arrays.asList("b", "a"),
                        Arrays.asList("a", "e"),
                        Arrays.asList("a", "a"),
                        Arrays.asList("x", "x")
                ));

        for (int i = 0; i <res.length ; i++) {
            System.out.println(res[i]);
        }


    }


}
