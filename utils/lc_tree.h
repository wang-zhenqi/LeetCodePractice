#ifndef _LC_TREE_H_
#define _LC_TREE_H_

typedef struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
} TreeNode;

#endif
