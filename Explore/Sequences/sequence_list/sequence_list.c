#include <stdio.h>
#include <stdlib.h>

#include "sequence_list.h"

SeqList *initSeqList(int capacity) {
    SeqList * seq_list = (SeqList *)malloc(sizeof(SeqList));
    seq_list->capacity = capacity;
    seq_list->length = 0;
    seq_list->data = (int *)malloc(sizeof(int) * capacity);
    return seq_list;
}

void clear(SeqList* p) {
    if(p == NULL) return;

    free(p->data);
    p->data = NULL;
    p->capacity = 0;
    p->length = 0;
    free(p);
}

int insert(SeqList* p, int pos, int val) {
    if(pos < 0 || pos > p->length || p->length >= p->capacity)
      	return 0;

    for(int cur_pos = p->length; cur_pos > pos; cur_pos--)
        p->data[cur_pos] = p->data[cur_pos - 1];

    p->data[pos] = val;
    p->length++;
    return 1;
}