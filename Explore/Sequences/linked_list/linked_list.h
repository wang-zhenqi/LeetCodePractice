#ifndef LINKED_LIST_H
#define LINKED_LIST_H

typedef struct Node
{
    int val;
    struct Node *next;
} Node;

Node *create_node(int val);

typedef struct LinkedList
{
    Node *node;
    int length;
} LinkedList;

LinkedList *init(Node* head_node);

void clear(LinkedList *head);

int insert(LinkedList *head, int pos, int val);

int erase(LinkedList *head, int pos);
#endif
