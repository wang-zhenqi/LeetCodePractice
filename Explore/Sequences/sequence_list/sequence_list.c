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
    if((pos < 0 || pos > p->length) || (p->length == p->capacity && !expand(p)))
        return 0;

    for(int cur_pos = p->length; cur_pos > pos; cur_pos--)
        p->data[cur_pos] = p->data[cur_pos - 1];

    p->data[pos] = val;
    p->length++;
    return 1;
}

int erase(SeqList* p, int pos) {
    if(pos < 0 || pos >= p->length || p->length == 0)
        return 0;
    for(int cur_pos = pos; cur_pos < p->length - 1; cur_pos++)
        p->data[cur_pos] = p->data[cur_pos + 1];
    p->data[p->length - 1] = 0;
    p->length--;
    return 1;
}

int expand(SeqList *p) {
  	if(p == NULL)
        return 0;
    int* new_loc = (int *) realloc(p->data, sizeof(int) * 2 * p->capacity);
    if(new_loc == NULL)
      	return 0;
    p->data = new_loc;
    p->capacity *= 2;
    return 1;
}
