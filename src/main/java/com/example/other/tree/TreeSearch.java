package com.example.other.tree;

/**
 * @author huangqi
 * @Package com.example.other.tree
 * @Description: 树遍历
 * @date 2020/4/15 4:10 下午
 */
public class TreeSearch {

    /**
     * 先初始化树
     *      A
     *   B     C
     *  D  E   F  G
     * H      I    J
     *  K
     *
     * @return
     */
    private static TreeNode initTree() {
        TreeNode kNode = TreeNode.builder().data("K").build();
        TreeNode hNode = TreeNode.builder().data("H").rChild(kNode).build();
        TreeNode jNode = TreeNode.builder().data("J").build();
        TreeNode gNode = TreeNode.builder().data("G").rChild(jNode).build();
        TreeNode iNode = TreeNode.builder().data("I").build();
        TreeNode fNode = TreeNode.builder().data("F").lChild(iNode).build();
        TreeNode eNode = TreeNode.builder().data("E").build();
        TreeNode dNode = TreeNode.builder().data("D").lChild(hNode).build();
        TreeNode cNode = TreeNode.builder().data("C").lChild(fNode).rChild(gNode).build();
        TreeNode bNode = TreeNode.builder().data("B").lChild(dNode).rChild(eNode).build();
        TreeNode aNode = TreeNode.builder().data("A").lChild(bNode).rChild(cNode).build();
        return aNode;
    }

    /**
     * 前序遍历
     *
     * @param tree
     */
    public static void preOrderTraverse(TreeNode tree) {
        if (tree == null) {
            return;
        }
        System.out.print(tree.getData() + "(" + tree.getBf() + ")");
        preOrderTraverse(tree.getLChild());
        preOrderTraverse(tree.getRChild());
    }

    /**
     * 中序遍历
     *
     * @param tree
     */
    public static void inOrderTraverse(TreeNode tree) {
        if (tree == null) {
            return;
        }
        inOrderTraverse(tree.getLChild());
        System.out.print(tree.getData());
        inOrderTraverse(tree.getRChild());
    }

    /**
     * 后序遍历
     *
     * @param tree
     */
    public static void postOrderTraverse(TreeNode tree) {
        if (tree == null) {
            return;
        }
        postOrderTraverse(tree.getLChild());
        postOrderTraverse(tree.getRChild());
        System.out.print(tree.getData());
    }

    public static void main(String[] args) {
        TreeNode tree = initTree();
        preOrderTraverse(tree);
        System.out.println();
        inOrderTraverse(tree);
        System.out.println();
        postOrderTraverse(tree);
    }


}
