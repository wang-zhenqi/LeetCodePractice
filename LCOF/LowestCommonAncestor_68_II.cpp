#include <iostream>
#include <stack>
#include "../utils/lc_tree.h"
#include "../utils/ser_de_binary_tree.h"

using namespace std;

class solution
{
public:
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q)
    {
        if(root == nullptr || root->val == p->val || root->val == q->val)
        {
            return root;
        }

        TreeNode* l = lowestCommonAncestor(root->left, p, q);
        TreeNode* r = lowestCommonAncestor(root->right, p, q);

        if(l == nullptr)
        {
            return r;
        }
        if(r == nullptr)
        {
            return l;
        }
        return root;
    }
};

int main()
{
    string input;
    getline(cin, input);
    int pval, qval;
    cin >> pval >> qval;

    solution obj;
    Codec codec;

    TreeNode* root = codec.deserialize(input);
    TreeNode* p = new TreeNode(pval);
    TreeNode* q = new TreeNode(qval);
    TreeNode* result = obj.lowestCommonAncestor(root, p, q);

    string output = codec.serialize(result);
    cout << output << endl;
    return 0;
}
