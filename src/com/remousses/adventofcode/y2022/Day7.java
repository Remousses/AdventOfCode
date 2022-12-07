package com.remousses.adventofcode.y2022;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 extends AbstractDay {

    private boolean toBreak = false;

    public static void main(String[] args) throws IOException {
        new Day7().readCode("/2022/day7.txt");
    }

    @Override
    protected void writeCode(final BufferedReader br) throws NumberFormatException, IOException {
        String line;
        Pattern numPattern = Pattern.compile("\\d+");
        Pattern lsPattern = Pattern.compile("\\b(ls)\\b");
        TreeNode node = null;
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }

            Matcher matcher = lsPattern.matcher(line);
            if (matcher.find()) {
                continue;
            }
            toBreak = false;

            node = navigateIntoTreeNode(line, node);
            createFolders(line, node);
            createFiles(line, numPattern, node);
        }

        if (node != null) {
            while (node.getParentNode() != null) {
                node = node.getParentNode();
            }
            AtomicInteger i = new AtomicInteger(0);
            node.deepRead((treeNode) -> {
                int count = treeNode.getTotalSize();
                if (count <= 100000) {
                    i.addAndGet(count);
                }
            }) ;

            // 1723892 (part 1)
            System.out.println("res : " + i.get());
        }
    }

    private TreeNode navigateIntoTreeNode(String line, TreeNode node) {
        String[] cdSplit = line.split("\\b(cd)\\b");
        if (cdSplit.length >= 2) {
            String folder = cdSplit[1].trim();
            if (node == null) {
                toBreak = true;
                return new TreeNode(folder);
            } else {
                if ("..".equals(folder)) {
                    toBreak = true;
                    return node.getParentNode();
                } else {
                    Optional<TreeNode> o = node.getChildList().stream().filter(c -> c.getDataValue().equals(folder)).findFirst();
                    if (o.isPresent()) {
                        TreeNode parentNode = node;
                        node = o.get();
                        node.setParentNode(parentNode);
                        toBreak = true;
                        return node;
                    }
                }
            }
        }
        return node;
    }

    private void createFolders(String line, TreeNode node) {
        if (!toBreak) {
            String[] dirSplit = line.split("dir");
            if (dirSplit.length >= 2) {
                node.getChildList().add(new TreeNode(dirSplit[1].trim()));
                toBreak = true;
            }
        }
    }

    private void createFiles(String line, Pattern numPattern, TreeNode node) {
        if (!toBreak) {
            Matcher matcher = numPattern.matcher(line);
            if (matcher.find()) {
                node.getFilesSize().add(Integer.valueOf(matcher.group()));
                toBreak = true;
            }
        }
    }
}
@Getter
class TreeNode {
    @Setter
    private TreeNode parentNode;
    private final List<TreeNode> childList;
    private final List<Integer> filesSize;

    @Setter
    private int totalSize;
    private final String dataValue;

    public TreeNode(String dataValue) {
        this.dataValue = dataValue;
        childList = new ArrayList<>(0);
        filesSize = new ArrayList<>(0);
    }

    public  void deepRead(Consumer<TreeNode> node) {
        int size = this.filesSize.stream().mapToInt(Integer::intValue).sum();

        for(TreeNode child : this.childList) {
            child.deepRead(node);
            size += child.getTotalSize();
        }
        this.setTotalSize(size);

        if(node != null) {
            node.accept(this);
        }
    }
}