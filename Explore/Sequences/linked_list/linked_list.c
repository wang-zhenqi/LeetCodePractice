#include <stdio.h>
#include <stdlib.h>

#include "linked_list.h"

Node *create_node(int val)
{
    Node* n = (Node *)malloc(sizeof(Node));
    n->val = val;
    n->next = NULL;
    return n;
}

LinkedList *init(Node* head_node) {
    LinkedList * head = (LinkedList *)malloc(sizeof(LinkedList));
    head->node = head_node;
    head->length = head->node == NULL ? 0 : 1;

    return head;
}

void clear(LinkedList *head) {
    for(Node *p = head->node, *next; p != NULL; p = next)
    {
        next = p->next;
        free(p);
        head->length--;
    }
}

int insert(LinkedList* head, int pos, int val) {
  return 0;
}

int erase(LinkedList* head, int pos) {
  return 0;
}
