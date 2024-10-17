package com.learning.java.dsa;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class TreeMaker {
    List<String> colors = Arrays.asList("red", "yellow", "black");
    List<String> length = Arrays.asList("short", "mid", "high");
    List<String> stemType = Arrays.asList("creepers", "solid", "shrubs");

    Map<Integer, Map<String, String>> mapMap;

    Set<Tree> trees = new HashSet<>();
    Tree createTree() {
        Tree tree = new Tree();
        Random random =new Random();


        int colorIndex= Math.abs(random.nextInt()) % colors.size();
        int lengthIndex=  Math.abs(random.nextInt()) % length.size();
        int stemIndex= Math.abs(random.nextInt()) % stemType.size();

        tree.setColor(colors.get(colorIndex));
        tree.setLength(length.get(lengthIndex));
        tree.setStemType(stemType.get(stemIndex));
        return tree;
    }

    public void generateUniqueTrees() {
        for(int i=0;i<100;i++) {
            Tree tree =  generateUniqueTree();
            System.out.print("Unique Tree: " + tree.getColor() + "," + tree.getLength() + "," + tree.getStemType() + " :by " + Thread.currentThread().getName());
            System.out.println();
        }
    }
    public synchronized Tree generateUniqueTree() {
        Tree tree = createTree();
        System.out.println("Size : " + trees.size());
        while(trees.contains(tree)) {
            tree = createTree();
        }
        trees.add(tree);
        return tree;
    }
}
class Tree {
    private String length;
    private String color;
    private String stemType;

    public void setColor(String color) {
        this.color = color;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setStemType(String type) {
        this.stemType = type;
    }

    public String getColor() {
        return color;
    }

    public String getStemType() {
        return stemType;
    }

    public String getLength() {
        return length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.color, this.length, this.length);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Tree) {
             Tree tree1 = (Tree) obj;
             return this.length == tree1.getLength() &&
                     this.stemType == tree1.getStemType() &&
                     this.color == tree1.getColor();

        } else return false;
    }
}
public class TreeRandomObjectCreation {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        TreeMaker treeMaker = new TreeMaker();

        executorService.submit(() -> {
            treeMaker.generateUniqueTrees();
        });
        executorService.submit(() -> {
            treeMaker.generateUniqueTrees();
        });
        executorService.submit(() -> {
            treeMaker.generateUniqueTrees();
        });
    }
}


/*
Task is comming as stream
1:2, 2:3, 3:4 ......
- Create solution to listen tasks from queue and run  it
- Task can be preemted if any dependee comes
- There would be dependent task which would be waiting in your queue.
- There would be a run queue

- How are you going to run the task to parallel services
- At some point if you got hey, this task is dependent on other, you have to terminate this task and run your tasks

 */