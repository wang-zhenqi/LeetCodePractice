#include <queue>
#include <string>
#include <istream>
#include <sstream>
#include "./lc_tree.h"
#include "./ser_de_binary_tree.h"

const std::string Codec::serialize(const TreeNode* const root) const
{
    if(root == nullptr)
    {
        return "";
    }
    std::queue<TreeNode*> q;
    q.push(const_cast<TreeNode*> (root));

    std::string output = "";

    while(!q.empty())
    {
        TreeNode* curNode = q.front();
        q.pop();

        if(curNode == nullptr)
        {
            output += "null,";
            continue;
        }

        output += std::to_string(curNode->val) + ",";
        q.push(curNode->left);
        q.push(curNode->right);
    }
    if(output[output.length() - 1] == ',')
    {
        output = output.substr(0, output.length() - 1);
    }
    return output;
}

TreeNode* Codec::deserialize(const std::string data) const
{
    if(data.empty() || data == "null")
    {
        return nullptr;
    }

    std::string item;
    std::stringstream ss;
    ss.str(data);
    std::getline(ss, item, ',');

    TreeNode* root = new TreeNode(stoi(item));

    std::queue<TreeNode*> q;
    q.push(root);

    while(!q.empty())
    {
        TreeNode* curNode = q.front();
        q.pop();

        if(!std::getline(ss, item, ','))
        {
            break;
        }

        if(item != "null")
        {
            curNode->left = new TreeNode(stoi(item));
            q.push(curNode->left);
        }

        if(!std::getline(ss, item, ','))
        {
            break;
        }

        if(item != "null")
        {
            curNode->right = new TreeNode(stoi(item));
            q.push(curNode->right);
        }
    }

    return root;
}
