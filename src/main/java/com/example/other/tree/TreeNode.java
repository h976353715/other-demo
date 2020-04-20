package com.example.other.tree;

import lombok.*;

/**
 * @author huangqi
 * @Package com.example.other.tree
 * @Description: 树
 * @date 2020/4/15 4:08 下午
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

    private String data;

    private TreeNode lChild;

    private TreeNode rChild;


    /**
     * 节点平衡因子。平衡二叉树用，值为该节点左子树深度减去右子树的深度 其绝对值小于1才算平衡
     */
    private Integer bf;

}
