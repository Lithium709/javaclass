package com.google.foobar;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class Task4 {


    private static double e = 0.0001;

    static class Point {

        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Segment {

        Point a;
        Point b;

        public Segment(Point a, Point b) {
            this.a = a;
            this.b = b;
        }

    }

    static class Vector {

        double x0;
        double y0;

        double x;
        double y;

        public Vector(double x, double y) {
            this.x0 = this.x = x;
            this.y0 = this.y = y;
        }

        void reflect(int[] r) {
            this.x = r[0] * this.x;
            this.y = r[1] * this.y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Vector vector = (Vector) o;

            int sx1 = vector.x0 > 0 ? 1 : -1;
            int sy1 = vector.y0 > 0 ? 1 : -1;
            int sx2 = x0 > 0 ? 1 : -1;
            int sy2 = y0 > 0 ? 1 : -1;
            if (Math.abs(y0) < e || Math.abs(vector.y0) < e) {
                return Double.compare(vector.x0, x0) == 0 && sx1 == sx2;
            }
            if (Math.abs(x0) < e || Math.abs(vector.x0) < e) {
                return Double.compare(vector.x0, x0) == 0 && sx1 == sx2;
            }
            return Double.compare(vector.x0, x0) == 0 &&
                    Double.compare(vector.y0, y0) == 0
                    ||
                    Double.compare(vector.x0 / vector.y0, x0 / y0) == 0 && sx1 == sx2 && sy1 == sy2;
        }

        @Override
        public int hashCode() {
            if (Math.abs(x0) < e) {
                return Objects.hash(x0, x0 > 0 ? 1 : -1, "s");
            }
            if (Math.abs(y0) < e) {
                return Objects.hash(y0, y0 > 0 ? 1 : -1, "t");
            }
            return Objects.hash(x0 / y0, x0 > 0 ? 1 : -1, y0 > 0 ? 1 : -1);
        }
    }

    static double distanceSquared(Point a, Point b) {

        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);

    }

    static double distance(Point a, Point b) {

        return Math.sqrt(distanceSquared(a, b));

    }

    static boolean onSegment(Segment segment, Point point) {

        // exclude start point
        if (Math.abs(segment.a.x - point.x) < e && Math.abs(segment.a.y - point.y) < e) {
            return false;
        }

        double crossproduct = (point.y - segment.a.y) * (segment.b.x - segment.a.x)
                - (point.x - segment.a.x) * (segment.b.y - segment.a.y);
        if (Math.abs(crossproduct) > e) {
            return false;
        }

        double dotproduct = (point.x - segment.a.x) * (segment.b.x - segment.a.x)
                + (point.y - segment.a.y) * (segment.b.y - segment.a.y);
        if (dotproduct < 0) {
            return false;
        }
        double lengthSquared = (segment.b.x - segment.a.x) * (segment.b.x - segment.a.x)
                + (segment.b.y - segment.a.y) * (segment.b.y - segment.a.y);

        if (dotproduct > lengthSquared) {
            return false;
        }
        return true;
    }

    static int orientation(Segment segment, Point point) {

        double val = (segment.b.y - segment.a.y) * (point.x - segment.b.x)
                - (segment.b.x - segment.a.x) * (point.y - segment.b.y);

        if (Math.abs(val) < 0) {
            return 0;
        }

        return (val > 0) ? 1 : 2;

    }

    static Point intersect(Segment s1, Segment s2) {

        int o1 = orientation(s1, s2.a);
        int o2 = orientation(s1, s2.b);
        int o3 = orientation(s2, s1.a);
        int o4 = orientation(s2, s1.b);

        if (o1 != o2 && o3 != o4) {
            double d = (s2.b.y - s2.a.y) * (s1.b.x - s1.a.x) - (s2.b.x - s2.a.x) * (s1.b.y - s1.a.y);

            // no intersection - parallel lines
            if (d == 0) {
                return null;
            }

            double n1 = (s2.b.x - s2.a.x) * (s1.a.y - s2.a.y) - (s2.b.y - s2.a.y) * (s1.a.x - s2.a.x);
            double u1 = n1 / d;

            double x = s1.a.x + u1 * (s1.b.x - s1.a.x);
            double y = s1.a.y + u1 * (s1.b.y - s1.a.y);

            return new Point(x, y);
        }
        return null;
    }

    static class Wall {

        Segment segment;
        int[] reflection;

        public Wall(Segment segment, int[] reflection) {
            this.segment = segment;
            this.reflection = reflection;
        }
    }


    static Segment getSegment(Point a, Vector v, double length) {

        // return new Segment(a, new Point(a.x + v.x * Math.sqrt(length), a.y + v.y * Math.sqrt(length)));

        double x1 = 0, y1 = 0;

        if (Math.abs(v.x) < e || Math.abs(v.y) < e) {

            if (Math.abs(v.x) < e) {
                x1 = a.x;
                y1 = a.y + (v.y > 0 ? 1 : -1) * length;
            } else if (Math.abs(v.y) < e) {
                x1 = a.x + (v.x > 0 ? 1 : -1) * length;
                y1 = a.y;
            }
        } else {
            final double d = (length * length) / ((v.x * v.x) / (v.y * v.y) + 1);
            if (d < 0) {
                return null;
            }
            y1 = a.y + (v.y > 0 ? 1 : -1) * Math.sqrt(d);
            x1 = a.x + v.x / v.y * (y1 - a.y);
        }

        final Point b = new Point(x1, y1);
        return new Segment(a, b);

    }


    public static int solution(int[] dimensions, int[] your_position, int[] guard_position, int distance) {

        // borders
        Point bottomLeft = new Point(0, 0);
        final int height = dimensions[1];
        Point topLeft = new Point(0, height);
        final int width = dimensions[0];
        Point bottomRight = new Point(width, 0);
        Point topRight = new Point(width, height);

        Queue<Wall> walls = new LinkedList<>();
        walls.add(new Wall(new Segment(bottomLeft, topLeft), new int[]{-1, 1}));
        walls.add(new Wall(new Segment(topLeft, topRight), new int[]{1, -1}));
        walls.add(new Wall(new Segment(bottomRight, topRight), new int[]{-1, 1}));
        walls.add(new Wall(new Segment(bottomRight, bottomLeft), new int[]{1, -1}));

        Set<Vector> tans = new HashSet<>();

        final Point you = new Point(your_position[0], your_position[1]);
        final Point guard = new Point(guard_position[0], guard_position[1]);

        int range = Math.max(width, height);

        for (int i = -range; i <= range; i++) {

            for (int j = -range; j <= range; j++) {

                if ((i == 0 && j == 0) || (i == 0 && Math.abs(j) > 1) || (j == 0 && Math.abs(i) > 1)) {
                    continue;
                }
                Vector v = new Vector(i, j);
                if (tans.contains(v)) {
                    continue;
                }
                double d = distance;
                double traveled = 0;
                Point start = you;
                while (d > 0) {
                    Segment beam = getSegment(start, v, d);
                    if (beam == null) {
                        break;
                    }
                    // self hit
                    if (onSegment(beam, you)) {
                        break;
                    }

                    // guard hit
                    if (onSegment(beam, guard)) {
                        if (tans.add(v)) {
                            traveled +=distance(beam.a, guard);
                            System.out.println("[" + v.x0 + "," + v.y0 + "]" +" distance = "  + (traveled));
                        }
                        break;
                    }
                    boolean isCorner = Math.abs(beam.a.x) < e && Math.abs(beam.a.y) < e
                            || Math.abs(beam.a.x - width) < e && Math.abs(beam.a.y) < e
                            || Math.abs(beam.a.x - width) < e && Math.abs(beam.a.y - height) < e
                            || Math.abs(beam.a.x) < e && Math.abs(beam.a.y - height) < e;

                    final boolean isInner = !(beam.b.x < 0 || beam.b.x > width || beam.b.y < 0 || beam.b.y > height);

                    //(beam.b.x < 0 && beam.b.y < 0 || beam.b.x < 0 && beam.b.y > height
                    //                            || beam.b.x > width && beam.b.y > height || beam.b.x > width && beam.b.y < 0)

                    if (isInner) {
                        break;
                    }

                    if (isCorner) {
                        break;
                    }

                    while (true) {
                        Wall wall = walls.remove();
                        Point wallHit = intersect(beam, wall.segment);
                        walls.add(wall);
                        if (wallHit != null) {
                            v.reflect(wall.reflection);
                            start = wallHit;
                            final double segment_length = distance(beam.a, wallHit);
                            d -= segment_length;
                            traveled+=segment_length;
                            break;
                        }
                    }
                }
            }

        }

        return tans.size();
    }

    public static void main(String[] args) {

    //    System.out.println(solution(new int[]{3, 2}, new int[]{1, 1}, new int[]{2, 1}, 4));

        System.out.println(solution(new int[]{300, 275}, new int[]{150, 150}, new int[]{185, 100}, 500));

        System.out.println(solution(new int[]{23, 10}, new int[]{6, 4}, new int[]{3, 2}, 23)); // 8

     //   System.out.println(solution(new int[]{10, 10}, new int[]{4, 4}, new int[]{3, 3}, 5000));

    //    System.out.println(solution(new int[]{2, 5}, new int[]{1, 2}, new int[]{1, 4}, 11));


   //     System.out.println(solution(new int[]{42, 59}, new int[]{34, 44}, new int[]{6, 34}, 5000));
        // Add more test cases
        // 4/10 so far

       // https://stackoverflow.com/questions/42792375/google-foobar-bringing-a-gun-to-a-guard-fight

    //   int g =  gcd(1362, 9988);

    //   System.out.println("" + 1362/g + ", " + 9988/g);

    }


    public static int gcd(int denumerator, int numerator) {
        if (denumerator == 0) {
            return numerator;
        }
        return gcd(numerator % denumerator, denumerator);
    }

}
