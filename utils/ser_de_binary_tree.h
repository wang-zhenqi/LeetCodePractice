#ifndef _SER_DE_BINARY_TREE_H_
#define _SER_DE_BINARY_TREE_H_

struct TreeNode;

class Codec
{
public:
    const std::string serialize(const TreeNode* const root) const;

    TreeNode* deserialize(const std::string data) const;
};

#endif
