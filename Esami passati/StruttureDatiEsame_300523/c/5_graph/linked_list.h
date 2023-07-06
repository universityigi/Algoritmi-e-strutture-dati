#ifndef LINKED_LIST_H
#define	LINKED_LIST_H

#ifdef	__cplusplus
extern "C" {
#endif
    
typedef struct linked_list_node {
	void * value;
	struct linked_list_node * next;
} linked_list_node;

typedef struct linked_list {
	linked_list_node *head;
	linked_list_node *tail;
	int size;
} linked_list;

typedef struct linked_list_iterator linked_list_iterator;

// linked list
linked_list * linked_list_new();
void linked_list_add(linked_list * ll, void * value);    
void * linked_list_get(linked_list * ll, int i);
void linked_list_remove_last(linked_list *ll);
void linked_list_delete(linked_list *ll);
int linked_list_size(linked_list *ll);

// linked list iterator
linked_list_iterator * linked_list_iterator_new(linked_list *ll);
linked_list_iterator * linked_list_iterator_next(linked_list_iterator * iter);
void * linked_list_iterator_getvalue(linked_list_iterator *iter);

#ifdef	__cplusplus
}
#endif

#endif	/* LINKED_LIST_H */

