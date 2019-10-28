package org.jointheleague.student.mazegen;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PathFinderTest {

    @Test
    public void testFindPath() {
        MazeGenerator mazeGenerator = new MazeGenerator(100, 100);
        mazeGenerator.initialize();
        Node start = mazeGenerator.getNode(0, 0);
        Node finish = mazeGenerator.getNode(99, 99);
        List<Edge> maze = mazeGenerator.runPrim();
        PathFinder pathFinder = new PathFinder(new HashSet<>(maze), start, finish);
        List<Edge> path = pathFinder.apply();
        Node node = start;
        for (Edge edge : path) {
            assertTrue(edge.getU() == node || edge.getV() == node);
            node = edge.getU() == node ? edge.getV() : edge.getU();
        }
        assertEquals(finish, node);
    }
}
