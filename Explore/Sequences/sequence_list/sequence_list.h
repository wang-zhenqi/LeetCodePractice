#ifndef SEQUENCE_LIST_H
#define SEQUENCE_LIST_H

typedef struct {
    int *data;
    int length;
    int capacity;
} SeqList;

SeqList *initSeqList(int capacity);

void clear(SeqList *p);

int insert(SeqList *p, int pos, int val);

int erase(SeqList *p, int pos);

int expand(SeqList *p);

#endif
