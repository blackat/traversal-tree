package com.contrastofbeauty.algo.ttf;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: black@t
 */
public class TraversalTreeForestTest {
    @Test
    public void testPutRootForest() throws Exception {
        TraversalTreeForest<Integer, Integer> ttf = new TraversalTreeForest();
        ttf.put(1, 200, null);
        ttf.put(2, 200, null);
        ttf.put(3, 200, null);
        ttf.put(4, 200, null);

        List<Integer> list = ttf.getPath(ttf.getRoot(), new ArrayList<Integer>());
        assertThat(list.get(0), equalTo(1));
        assertThat(list.get(1), equalTo(2));
        assertThat(list.get(2), equalTo(3));
        assertThat(list.get(3), equalTo(4));
    }

    @Test
    public void testPutLeftChildrenOneRoot() throws Exception {
        TraversalTreeForest<Integer, Integer> ttf = new TraversalTreeForest();
        ttf.put(1, 200, null);
        ttf.put(2, 200, 1);
        ttf.put(3, 200, 2);
        ttf.put(4, 200, 3);

        List<Integer> list = ttf.getPath(ttf.getRoot(), new ArrayList<Integer>());
        assertThat(list.get(0), equalTo(4));
        assertThat(list.get(1), equalTo(3));
        assertThat(list.get(2), equalTo(2));
        assertThat(list.get(3), equalTo(1));
    }

    @Test
    public void testPutLeftChildMultipleRoots() throws Exception {
        TraversalTreeForest<Integer, Integer> ttf = new TraversalTreeForest();
        ttf.put(1, 200, null);
        ttf.put(2, 200, 1);
        ttf.put(3, 200, null);
        ttf.put(4, 200, 3);
        ttf.put(5, 200, null);
        ttf.put(6, 200, 5);

        List<Integer> list = ttf.getPath(ttf.getRoot(), new ArrayList<Integer>());
        assertThat(list.get(0), equalTo(2));
        assertThat(list.get(1), equalTo(1));
        assertThat(list.get(2), equalTo(4));
        assertThat(list.get(3), equalTo(3));
        assertThat(list.get(4), equalTo(6));
        assertThat(list.get(5), equalTo(5));

    }

    @Test
    public void testPutLeftChildAndSiblingsOneRoot() throws Exception {
        TraversalTreeForest<Integer, Integer> ttf = new TraversalTreeForest();
        ttf.put(1, 200, null);
        ttf.put(2, 200, 1);
        ttf.put(3, 200, 1);
        ttf.put(4, 200, 1);
        ttf.put(5, 200, 1);
        ttf.put(6, 200, 1);

        List<Integer> list = ttf.getPath(ttf.getRoot(), new ArrayList<Integer>());
        assertThat(list.get(0), equalTo(2));
        assertThat(list.get(1), equalTo(3));
        assertThat(list.get(2), equalTo(4));
        assertThat(list.get(3), equalTo(5));
        assertThat(list.get(4), equalTo(6));
        assertThat(list.get(5), equalTo(1));
    }

    @Test
    public void testPutLeftChildAndSiblingMultipleRoots() throws Exception {
        TraversalTreeForest<Integer, Integer> ttf = new TraversalTreeForest();
        ttf.put(1, 200, null);
        ttf.put(2, 200, 1);
        ttf.put(3, 200, 1);
        ttf.put(4, 200, null);
        ttf.put(5, 200, 4);
        ttf.put(6, 200, 4);

        List<Integer> list = ttf.getPath(ttf.getRoot(), new ArrayList<Integer>());
        assertThat(list.get(0), equalTo(2));
        assertThat(list.get(1), equalTo(3));
        assertThat(list.get(2), equalTo(1));
        assertThat(list.get(3), equalTo(5));
        assertThat(list.get(4), equalTo(6));
        assertThat(list.get(5), equalTo(4));
    }

    @Test
    public void testPutLeftChildAndSiblingDifferentDepthOneRoot() throws Exception {
        TraversalTreeForest<Integer, Integer> ttf = new TraversalTreeForest();
        ttf.put(1, 200, null);
        ttf.put(2, 200, 1);
        ttf.put(3, 200, 2);
        ttf.put(4, 200, 1);
        ttf.put(5, 200, 2);

        List<Integer> list = ttf.getPath(ttf.getRoot(), new ArrayList<Integer>());
        assertThat(list.get(0), equalTo(3));
        assertThat(list.get(1), equalTo(5));
        assertThat(list.get(2), equalTo(2));
        assertThat(list.get(3), equalTo(4));
        assertThat(list.get(4), equalTo(1));
    }

    @Test
    public void testPutLeftChildAndSiblingDifferentDepthMultipleRoots() throws Exception {
        TraversalTreeForest<Integer, Integer> ttf = new TraversalTreeForest();
        ttf.put(1, 200, null);
        ttf.put(2, 200, null);
        ttf.put(3, 200, null);
        ttf.put(4, 200, 1);
        ttf.put(5, 200, 1);
        ttf.put(6, 200, 1);
        ttf.put(7, 200, 2);
        ttf.put(8, 200, 2);
        ttf.put(9, 200, 2);
        ttf.put(10, 200, 3);
        ttf.put(11, 200, 10);
        ttf.put(12, 200, 11);
        ttf.put(13, 200, 3);

        List<Integer> list = ttf.getPath(ttf.getRoot(), new ArrayList<Integer>());
        assertThat(list.get(0), equalTo(4));
        assertThat(list.get(1), equalTo(5));
        assertThat(list.get(2), equalTo(6));
        assertThat(list.get(3), equalTo(1));
        assertThat(list.get(4), equalTo(7));
        assertThat(list.get(5), equalTo(8));
        assertThat(list.get(6), equalTo(9));
        assertThat(list.get(7), equalTo(2));
        assertThat(list.get(8), equalTo(12));
        assertThat(list.get(9), equalTo(11));
        assertThat(list.get(10), equalTo(10));
        assertThat(list.get(11), equalTo(13));
        assertThat(list.get(12), equalTo(3));
    }

    @Test
    public void testGetNode() throws Exception {

    }
}
