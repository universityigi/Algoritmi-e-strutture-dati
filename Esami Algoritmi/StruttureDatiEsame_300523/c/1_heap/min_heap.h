#ifndef __MIN_HEAP_H__
#define	__MIN_HEAP_H__
    
#define MIN_HEAP_DATA_TYPE void *
#define MIN_HEAP_DATA_TYPE_PRINTF "%s"
#define MIN_HEAP_MAX_SIZE 100

typedef void min_heap;
    
typedef struct _min_heap_struct_entry {
    int index;
    int key;
    MIN_HEAP_DATA_TYPE value;
} min_heap_struct_entry;

min_heap * min_heap_new();
min_heap_struct_entry * min_heap_min(min_heap * mh);
min_heap_struct_entry * min_heap_insert(min_heap * mh, int p, MIN_HEAP_DATA_TYPE value);
int min_heap_is_empty(min_heap * mh);
min_heap_struct_entry * min_heap_remove_min(min_heap * mh);
int min_heap_size(min_heap *mh);
void heapsort(int* v, int size);

min_heap_struct_entry * min_heap_replace_value(min_heap * mh, min_heap_struct_entry * e, MIN_HEAP_DATA_TYPE value);
min_heap_struct_entry * min_heap_replace_key(min_heap * mh, min_heap_struct_entry * e, int key);
min_heap_struct_entry * min_heap_remove(min_heap * mh, min_heap_struct_entry * e);

void min_heap_free(min_heap *mh);

#endif	/* __MIN_HEAP_H__ */

