#include "min_heap.h"
#include <stdlib.h>
#include <string.h>

typedef struct _min_heap_struct {
    int size;
    min_heap_struct_entry **array;
} min_heap_struct;

min_heap * min_heap_new() {
    min_heap_struct * ret = (min_heap_struct*)malloc(sizeof(min_heap_struct));
    ret->array = (min_heap_struct_entry **)calloc(MIN_HEAP_MAX_SIZE, sizeof(min_heap_struct_entry *));
    memset(ret->array, 0, MIN_HEAP_MAX_SIZE * sizeof(min_heap_struct_entry *));
    ret->size = 0;
	return (min_heap*)ret;
}

min_heap_struct_entry * min_heap_min(min_heap * mh) {
    min_heap_struct * cast_mh = (min_heap_struct *) mh;
    return cast_mh->array[1];
}

static void min_heap_upheap(min_heap * mh, min_heap_struct_entry * e) {
    min_heap_struct * cast_mh = (min_heap_struct *) mh;
    while (e->index != 1) {
        int eIdx = e->index;
        int pIdx = eIdx / 2;
        min_heap_struct_entry * p = cast_mh->array[pIdx];
        if (e->key >= p->key)
            break;
        cast_mh->array[pIdx] = e;
        e->index = pIdx;
        cast_mh->array[eIdx] = p;
        p->index = eIdx;
    }
}

static int min_heap_has_children(min_heap * mh, min_heap_struct_entry * e) {
    min_heap_struct * cast_mh = (min_heap_struct *) mh;
    if (cast_mh->size >= 2 * e->index)
        return 1;
    else
        return 0;
}

static int min_heap_has_both_children(min_heap * mh, min_heap_struct_entry * e) {
    min_heap_struct * cast_mh = (min_heap_struct *) mh;
    if (cast_mh->size >= 2 * e->index + 1)
        return 1;
    else
        return 0;
}

static void min_heap_downheap(min_heap * mh, min_heap_struct_entry * e) {
    min_heap_struct * cast_mh = (min_heap_struct *) mh;
    while (min_heap_has_children(mh, e) == 1) {
        int eIdx = e->index;
        int lIdx = 2 * eIdx;
        int rIdx = 2 * eIdx + 1;
        min_heap_struct_entry * l = cast_mh->array[lIdx];
        if (min_heap_has_both_children(mh, e) == 1) {
            min_heap_struct_entry * r = cast_mh->array[rIdx];
            int minIdx = (r->key < l->key ? rIdx : lIdx);
            min_heap_struct_entry * min = cast_mh->array[minIdx];
            if (e->key <= min->key)
                break;
            cast_mh->array[minIdx] = e;
            cast_mh->array[eIdx] = min;
            e->index = minIdx;
            min->index = eIdx;
        } else { // e ha solo il figlio sinistro
            if (e->key <= l->key)
                break;
            cast_mh->array[lIdx] = e;
            cast_mh->array[eIdx] = l;
            e->index = lIdx;
            l->index = eIdx;
        }
    }
}

min_heap_struct_entry * min_heap_insert(min_heap * mh, int p, MIN_HEAP_DATA_TYPE value) {
    min_heap_struct * cast_mh = (min_heap_struct *) mh;
    min_heap_struct_entry * e = (min_heap_struct_entry *) malloc(sizeof(min_heap_struct_entry));
    e->value = value;
    e->index = cast_mh->size + 1;
    e->key = p;
    cast_mh->array[cast_mh->size + 1] = e;
    cast_mh->size++;
    min_heap_upheap(mh, e);
    return e;
}

int min_heap_is_empty(min_heap * mh) {
    min_heap_struct * cast_mh = (min_heap_struct *) mh;
    if (cast_mh->size == 0)
        return 1;
    else
        return 0;
}

min_heap_struct_entry * min_heap_remove_min(min_heap * mh) {
    min_heap_struct * cast_mh = (min_heap_struct *) mh;
    min_heap_struct_entry * e = min_heap_min(mh);
    min_heap_struct_entry * last = cast_mh->array[cast_mh->size]; // ultimo elemento
    cast_mh->array[1] = last;
    last->index = 1;
    cast_mh->array[cast_mh->size] = NULL;
    cast_mh->size--;
    min_heap_downheap(mh, last);
    return e;
}

void heapsort(int* v, int size) {
    min_heap * t = min_heap_new();
    int i;
    for (i = 0; i < size; i++)
        min_heap_insert(t, v[i], NULL);
    int c = 0;
    while (min_heap_is_empty(t) == 0) {
        min_heap_struct_entry * e = min_heap_remove_min(t);
        v[c++] = e->key;
        free(e);
    }
    min_heap_free(t);
}

min_heap_struct_entry * min_heap_replace_value(min_heap * mh, min_heap_struct_entry * e, MIN_HEAP_DATA_TYPE value) {
    e->value = value;
    return e;
}

min_heap_struct_entry * min_heap_replace_key(min_heap * mh, min_heap_struct_entry * e, int key) {
    int oldKey = e->key;
    e->key = key;
    if (key < oldKey)
        min_heap_upheap(mh, e);
    else
        min_heap_downheap(mh, e);
    return e;
}

min_heap_struct_entry * min_heap_remove(min_heap * mh, min_heap_struct_entry * e) {
    min_heap_struct * cast_mh = (min_heap_struct *) mh;
    min_heap_struct_entry * last = cast_mh->array[cast_mh->size]; // ultimo elemento
    cast_mh->array[e->index] = last;
    last->index = e->index;
    cast_mh->array[cast_mh->size] = NULL;
    cast_mh->size--;
    if (last->key < e->key)
        min_heap_upheap(mh, last);
    else
        min_heap_downheap(mh, last);
    return e;
}

void min_heap_free(min_heap *mh) {
    min_heap_struct * cast_mh = (min_heap_struct *) mh;
    int i;
    for (i = 0; i < MIN_HEAP_MAX_SIZE; i++) {
        if (cast_mh->array[i] != NULL)
            free(cast_mh->array[i]);
    }
    free(cast_mh->array);
    free(mh);
}

int min_heap_size(min_heap *mh) {
    min_heap_struct * cast_mh = (min_heap_struct *) mh;
    return cast_mh->size;
}