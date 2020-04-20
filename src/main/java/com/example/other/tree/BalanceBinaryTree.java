package com.example.other.tree;

import java.util.Arrays;

/**
 * @author huangqi
 * @Package com.example.other.tree
 * @Description: 平衡二叉树. 是一种二叉排序树，其中每一个节点的左子树和右子树高度差至多等于1
 * @date 2020/4/17 10:39 上午
 */
public class BalanceBinaryTree {

    /**
     * 树
     */
    private TreeNode tree;

    /**
     * 右旋转
     *
     * @param treeNode
     * @return
     */
    public TreeNode rightRotate(TreeNode treeNode) {
        //右旋转说明该节点左子树一定存在
        //取出左子树取名为newRoot
        TreeNode newRoot = treeNode.getLChild();
        //newRoot的右子树挂接为旧双亲节点treeNode的左子树
        treeNode.setLChild(newRoot.getRChild());
        //newRoot为新的双亲节点,newRoot的右子树挂接treeNode
        newRoot.setRChild(treeNode);
        //重置平衡因子 返回新的树
        treeNode.setBf(balanceFactor(treeNode));
        newRoot.setBf(balanceFactor(newRoot));
        return newRoot;
    }

    /**
     * 左旋转
     *
     * @param treeNode
     * @return
     */
    public TreeNode leftRotate(TreeNode treeNode) {
        //左旋转说明该节点右子树一定存在
        //取出右子树取名为newRoot
        TreeNode newRoot = treeNode.getRChild();
        //newRoot的左子树挂接为旧双亲节点treeNode的右子树
        treeNode.setRChild(newRoot.getLChild());
        //newRoot成为新的双亲节点，newRoot的左子树挂接treeNode
        newRoot.setLChild(treeNode);
        //重置平衡因子 返回新的树
        treeNode.setBf(balanceFactor(treeNode));
        newRoot.setBf(balanceFactor(newRoot));
        return newRoot;
    }


    /**
     * 先右旋在左旋 在右子树的左节点添加时候用
     *
     * @param node
     * @return
     */
    public TreeNode rightThenLeftRotate(TreeNode node) {
        //右旋是以该节点的右孩子节点为根节点进行右旋 最后以该节点进行左旋
        node.setRChild(rightRotate(node.getRChild()));
        return leftRotate(node);
    }

    /**
     * 先左旋在右旋 在左子树的右节点添加时候用
     *
     * @param node
     * @return
     */
    public TreeNode leftThenRightRotate(TreeNode node) {
        //左旋是以该节点的左孩子节点为根节点进行左旋 最后以该节点进行右旋
        node.setLChild(leftRotate(node.getLChild()));
        return rightRotate(node);
    }

    /**
     * 重平衡树
     *
     * @param node
     * @return
     */
    public TreeNode reBalance(TreeNode node) {
        //更新平衡因子
        node.setBf(balanceFactor(node));
        //右边子树高于左子树
        if (node.getBf() <= -2) {
            //右孩子的平衡因子和node的平衡因子符号不一致则双旋
            if (balanceFactor(node.getRChild()) > 0) {
                return rightThenLeftRotate(node);
            } else {
                return leftRotate(node);
            }
        }
        //左边子树高于右子树
        else if (node.getBf() >= 2) {
            //左孩子的平衡因子和node的平衡因子符号不一致则双旋
            if (balanceFactor(node.getLChild()) < 0) {
                return leftThenRightRotate(node);
            } else {
                return rightRotate(node);
            }
        }
        return node;
    }

    /**
     * 计算平衡因子
     *
     * @param node
     * @return
     */
    public int balanceFactor(TreeNode node) {
        return height(node.getLChild()) - height(node.getRChild());
    }

    /**
     * 计算高度
     *
     * @param node
     * @return
     */
    private int height(TreeNode node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(height(node.getLChild()), height(node.getRChild()));
    }

    /**
     * 添加节点
     *
     * @param data
     * @return
     */
    private boolean addTreeNode(String data) {
        TreeNode node = TreeNode.builder().data(data).bf(0).build();
        if (tree == null) {
            tree = node;
            return true;
        }
        TreeNode temp = tree;
        while (data.compareTo(temp.getData()) != 0) {
            if (data.compareTo(temp.getData()) > 0) {
                if (temp.getRChild() != null) {
                    temp = temp.getRChild();
                } else {
                    temp.setRChild(node);
                    break;
                }
            }
            if (data.compareTo(temp.getData()) < 0) {
                if (temp.getLChild() != null) {
                    temp = temp.getLChild();
                } else {
                    temp.setLChild(node);
                    break;
                }
            }
        }
        tree = reBalance(tree);
        return true;
    }

    /**
     * 创建树
     *
     * @param dataArray
     * @return
     */
    public TreeNode createTree(String[] dataArray) {
        if (dataArray == null || dataArray.length == 0) {
            return null;
        }
        Arrays.stream(dataArray).forEach(this::addTreeNode);
        return this.tree;
    }


    /**
     * 获取到树
     *
     * @return
     */
    public TreeNode getTree() {
        return this.tree;
    }

    /**
     * 构造一个可以右旋的树
     *
     * @return
     */
    private static TreeNode initCanBeRightRotateTree() {
        TreeNode fNode = TreeNode.builder().data("F").build();
        TreeNode dNode = TreeNode.builder().data("D").lChild(fNode).build();
        TreeNode eNode = TreeNode.builder().data("E").build();
        TreeNode cNode = TreeNode.builder().data("C").build();
        TreeNode bNode = TreeNode.builder().data("B").lChild(dNode).rChild(eNode).build();
        TreeNode aNode = TreeNode.builder().data("A").lChild(bNode).rChild(cNode).build();
        return aNode;
    }

    /**
     * 构造一个可以左旋的树
     *
     * @return
     */
    private static TreeNode initCanBeLeftRotateTree() {
        TreeNode fNode = TreeNode.builder().data("F").build();
        TreeNode dNode = TreeNode.builder().data("D").build();
        TreeNode eNode = TreeNode.builder().data("E").rChild(fNode).build();
        TreeNode cNode = TreeNode.builder().data("C").lChild(dNode).rChild(eNode).build();
        TreeNode bNode = TreeNode.builder().data("B").build();
        TreeNode aNode = TreeNode.builder().data("A").lChild(bNode).rChild(cNode).build();
        return aNode;
    }

    public static void main(String[] args) {
        BalanceBinaryTree factory = new BalanceBinaryTree();
        TreeNode canBeRightRotateTree = initCanBeRightRotateTree();
        System.out.print("右旋前的树：");
        TreeSearch.preOrderTraverse(canBeRightRotateTree);
        System.out.print("\n右旋后的树：");
        TreeSearch.preOrderTraverse(factory.rightRotate(initCanBeRightRotateTree()));
        TreeNode canBeLeftRotateTree = initCanBeLeftRotateTree();
        System.out.print("\n左旋前的树：");
        TreeSearch.preOrderTraverse(canBeLeftRotateTree);
        System.out.print("\n左旋后的树：");
        TreeSearch.preOrderTraverse(factory.leftRotate(canBeLeftRotateTree));
        factory.addTreeNode("50");
        factory.addTreeNode("60");
        factory.addTreeNode("40");
        factory.addTreeNode("30");
        factory.addTreeNode("45");
        factory.addTreeNode("47");
        System.out.print("\n生成树：");
        TreeSearch.preOrderTraverse(factory.getTree());
        System.out.println();
        System.out.print("生成树1：");
        TreeSearch.preOrderTraverse(factory.createTree(new String[]{"50", "60", "40", "30", "45", "47"}));
    }

}
