package com.learning.java.lld.filesystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem statement - Design an in memory file system , where you
 * 1. Add a directory
 * 2. Add a file
 * 3. Delete a file
 * 3. Count number of files in current directory
 * 4. Count number of files in current current and recursively for its subdirectories
 */

abstract class Block {
    protected String name;
    protected long created;
    protected Directory parent;
    protected boolean isRootDirectory  = false;

    public Block(String name) {
        this.name = name;
        this.isRootDirectory = true;
    }
    public Block(String name, Directory parentDirectory) {
        this.name = name;
        this.parent = parentDirectory;
    }

    public String getName() {
        return name;
    }

    public abstract void ls();
}

class File extends Block{
    private String content;
    private long size;

    public File(String name, String content, Directory parentDirectory) {
        super(name, parentDirectory);
        this.content = content;
        this.size = content.length();
    }
    public  String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content  =content;
    }

    @Override
    public void ls() {
        System.out.println(this.getName());
    }

};
class Directory extends Block {
    List<Block> blocks;

    public Directory(String rootDirectoryName) {
        super(rootDirectoryName);
        blocks = new ArrayList<>();
    }
    public Directory(String name, Directory p) {
        super(name, p);
        this.blocks = new ArrayList<>();
    }

    @Override
    public void ls() {
        for(Block b: blocks) {
            System.out.println(b.getName());
        }
    }

    public void addBlock(Block b) {
        // We need to validate if b
        blocks.add(b);
    }

    public void deleteBlock(Block b) {
        blocks.remove(b);
    }
    public int size() {
       return blocks.size();
    }
    public int numberOfFiles() {
         return blocks.stream().map(b -> {
            if(b instanceof Directory) {
                return ((Directory) b).numberOfFiles();
            } else return 1;
        }).mapToInt(x -> x).sum();
        // Using reduce API
//        blocks.stream().map(b -> {
//            if(b instanceof Directory) {
//                return ((Directory) b).numberOfFiles();
//            } else return 1;
//        }).reduce(0, (a,b) -> a + b);
    }
};

// Implement CD, LS, Touch
// with fulll path name and relative paths starting with ~./
// search based on a string pattern
//result of pattern matching against all file. Folder names and file contents(if they are text based)
//Ordering should be based on any heuritstic logic.

public class FileSystem {

    public static void main(String[] args) {
        Directory directory = new Directory("/");
        directory.addBlock(new File("first.txt", "helllo", directory));

        directory.addBlock(new Directory("/second", directory));
        int fileCount  = directory.numberOfFiles();
        directory.ls();

    }

}
