package com.learning.java.dsa;

import java.util.*;

public class TopologicalSort {

    public static List<List<Integer>> getAncestors(int n, int [][] edges) {
        int[] inDegrees = new int[n];
        Map<Integer, List<Integer>> parentsToKids = new HashMap<>();

        for(int [] e: edges) {
            parentsToKids.computeIfAbsent(e[0], l -> new ArrayList<>()).add(e[1]);
            ++inDegrees[e[1]];
        }

        Queue<Integer> q = new LinkedList<>();
        List<Set<Integer>> sets = new ArrayList<>();
        //put all the indegrres to the queue
        for(int i=0;i<n;i++) {
            sets.add(new HashSet<>());
            if(inDegrees[i] == 0) {
                q.offer(i);
            }
        }
        while(!q.isEmpty()) {
            // there is poll which removes item from queue and return
            // There is peek which just peeks the element from queue
            int parent = q.poll();
            for(int kid: parentsToKids.getOrDefault(parent, new ArrayList<>())) {
                sets.get(kid).addAll(sets.get(parent));
                sets.get(kid).add(parent);
                --inDegrees[kid];
                if(inDegrees[kid] == 0) {
                    q.offer(kid);
                }
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        for(Set<Integer> set: sets) {
            ans.add(new ArrayList<>(new TreeSet<>(set)));
        }
        return ans;
    }
    public static void main(String[] args) {
        int n = 8;
        // [[0,3],[0,4],[1,3],[2,4],[2,7],[3,5],[3,6],[3,7],[4,6]]
        int[][] edges = {{0,3}, {0,4}, {1,3},{2,4},{2,7},{3,5},{3,6},{3,7},{4,6}};
        List<List<Integer>> ans = getAncestors(n, edges);
        for(int i=0;i<n;i++) {
            System.out.print(i + " :");
            System.out.println(ans.get(i));
        }
    }
}
