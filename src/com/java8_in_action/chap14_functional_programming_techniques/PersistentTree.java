package com.java8_in_action.chap14_functional_programming_techniques;

/**
 * Created by sofia on 12/23/16.
 */
public class PersistentTree {

    static class Tree {
        private String key;
        private int val;
        private Tree left, right;

        public Tree(String key, int val, Tree left, Tree right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public static int lookup(String k, int defaultVal, Tree t) {
        if (t == null) {
            return defaultVal;
        }
        if (k.equals(t.key)) {
            return t.val;
        }
        return lookup(k, defaultVal, k.compareTo(t.key) < 0 ? t.left : t.right);
    }

    public static Tree update(String k, int newVal, Tree t) {
        if (t == null) {
            t = new Tree(k, newVal, null, null);
        } else if (k.equals(t.key)) {
            t.val = newVal;
        } else if (k.compareTo(t.key) < 0) {
            t.left = update(k, newVal, t.left);
        } else {
            t.right = update(k, newVal, t.right);
        }
        return t;
    }

    public static Tree fUpdate(String k, int newVal, Tree t) {
        return (t == null) ?
                new Tree(k, newVal, null, null) :
                k.equals(t.key) ?
                        new Tree(k, newVal, t.left, t.right) :
                        k.compareTo(t.key) < 0 ?
                                new Tree(t.key, t.val, fUpdate(k, newVal, t.left), t.right) :
                                new Tree(t.key, t.val, t.left, fUpdate(k, newVal, t.right));
    }


    public static void main(String... args) {
        Tree t = new Tree("Mary", 22,
                new Tree("Emily", 20,
                        new Tree("Alan", 50, null, null),
                        new Tree("George", 23, null, null)
                ),
                new Tree("Tian", 29,
                        new Tree("Tom", 23, null, null),
                        null
                )
        );

        System.out.println(lookup("Tom", -1, t));
        System.out.println(lookup("Jeff", -1, t));

        Tree f = fUpdate("Jeff", 80, t);
        System.out.println(lookup("Jeff", -1, f));

        Tree u = update("Jim", 40, t);
        System.out.println(lookup("Jeff", -1, u));
        System.out.println(lookup("Jim", -1, u));

        Tree f2 = fUpdate("Jeff", 80, t);
        System.out.println(lookup("Jeff", -1, f2));
        System.out.println(lookup("Jim", -1, f2));
    }

}
