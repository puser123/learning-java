package com.learning.java.lld;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

};
class Directory extends Block{
    List<Block> blocks;


    public Directory(String rootDirectoryName) {
        super(rootDirectoryName);
    }
    public Directory(String name, Directory p) {
        super(name, p);
        this.blocks = new ArrayList<>();
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

public class FileSystem {

    Directory p = new Directory("/");
}
