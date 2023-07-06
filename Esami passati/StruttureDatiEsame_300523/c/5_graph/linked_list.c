#include "linked_list.h"
#include "graph.h"
#include "stdlib.h"
#include "stdio.h"

linked_list * linked_list_new() {
    linked_list * ptr = (linked_list *) malloc(sizeof(linked_list));
    ptr->head = NULL;
    ptr->tail = NULL;
    ptr->size = 0;
    return (linked_list *) ptr;
}

void * linked_list_get(linked_list * ll, int index) {

    linked_list * ptr = (linked_list *) ll;
    if (index < 0 || index >= ptr->size)
        return NULL;
    
    int i;
    linked_list_node *current = ptr->head;
    for (i = 0; i < index && current != NULL; i++) {
        current = current->next;
    }
    if (current == NULL)
        return NULL;

    return current->value;
}

void linked_list_add(linked_list * ll, void * value) {

    linked_list * ptr = (linked_list *) ll;
    linked_list_node * added = (linked_list_node *) malloc(sizeof(linked_list_node));
    added->next = NULL;
    added->value = value;

	if (ptr->tail != NULL) {
		ptr->tail->next = added;
	}
    
	if (ptr->head == NULL) {
		ptr->head = added;
	}
    ptr->tail = added;
    ptr->size++;

    /*
    printf("added node: %p - size: %d - head: %p - tail: %p\n", value, ptr->size,
        ptr->head != NULL ? ptr->head->value : NULL, 
        ptr->tail != NULL ? ptr->tail->value : NULL);
    */
}

void linked_list_remove_last(linked_list *ll) {

    linked_list * ptr = (linked_list *) ll;

    if (ptr->tail == NULL)
        return;

    linked_list_node * current = ptr->head;
    linked_list_node * previous = NULL;

    // inefficient: use a double linked list!
    while(current->next != NULL) {
        previous = current;
        current = current->next;
    }

    ptr->tail = previous;
    
    if (previous == NULL)
        ptr->head = current->next;
    else
        previous->next = current->next;
    
    ptr->size--;
    /*
    printf("removed node: %p - size: %d - head: %p - tail: %p\n", current->value, ptr->size, 
        ptr->head != NULL ? ptr->head->value : NULL, 
        ptr->tail != NULL ? ptr->tail->value : NULL);
    */

    free(current);
    return;
}

void linked_list_delete(linked_list *l) {
    if (l == NULL) {
        return;
    }
	linked_list_node * it = l->head;

	while (it != NULL) {
		linked_list_node * rem = it;
		it = it->next;
		free(rem);
	}
	//free(it);
    free(l);
    return;
}

linked_list_iterator * linked_list_iterator_new(linked_list *ll) {
    linked_list *ptr = (linked_list *) ll;
    return (linked_list_iterator *) ptr->head;
}

linked_list_iterator * linked_list_iterator_next(linked_list_iterator * iter) {
    linked_list_node *ptr = (linked_list_node *) iter;
	if(ptr!= NULL)
		return (linked_list_iterator*)ptr->next;
	return NULL;
}

void * linked_list_iterator_getvalue(linked_list_iterator *iter) {
    linked_list_node *ptr = (linked_list_node *) iter;
    return ptr->value;
}

int linked_list_size(linked_list *ll) {
    linked_list *ptr = (linked_list *) ll;
    return ptr->size;
}