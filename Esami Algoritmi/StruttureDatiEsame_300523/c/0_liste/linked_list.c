#include "linked_list.h"
#include <stdlib.h>
#include <stdio.h>

typedef struct linked_list_node {
    int value;
    struct linked_list_node *next;
} linked_list_node;

struct linked_list{
    linked_list_node *head;
    linked_list_node *tail;
    int size;
};

int linked_list_get(linked_list * ll, int index, int *value) {
    linked_list *ptr = (linked_list *) ll;
    if (index < 0 || index >= ptr->size)
        return LINKED_LIST_NOK;
    int i;
    linked_list_node *current = ptr->head;
    for (i = 0; i < index && current != NULL; i++) {
        current = current->next;
    }
    if (current == NULL)
        return LINKED_LIST_NOK;
    *value = current->value;
    return LINKED_LIST_OK;
}

int linked_list_add(linked_list * ll, int value) {
    linked_list *ptr = (linked_list *) ll;
    linked_list_node *added = (linked_list_node *) malloc(sizeof(linked_list_node));
    added->next = NULL;
    added->value = value;
    if (ptr->tail != NULL)
        ptr->tail->next = added;
    if (ptr->head == NULL)
        ptr->head = added;
    ptr->tail = added;
    ptr->size++;
    return LINKED_LIST_OK;
}

int linked_list_remove(linked_list *ll, int index) {
    if (index < 0 || index >= ll->size)
        return LINKED_LIST_NOK;
    int i;
    linked_list_node *current = ll->head;
    linked_list_node *previous = NULL;
    for (i = 0; i < index; i++) {
        previous = current;
        current = current->next;
    }
    /* rimuovere current, preceduto da previous */
    if(previous == NULL) ll->head = ll->head->next; /* rimuovo il primo */
    else previous->next = current->next; /* rimuovo non-primo */

    if(ll->tail == current) ll->tail = previous; /* aggiornmo tail nel caso di rimozione ultimo */
    free(current);
    ll->size--;
    return LINKED_LIST_OK;
}

int print_linked_list(linked_list *ll) {

	if (ll == NULL) {
		printf("Invalid pointer to a list.\n");
		return LINKED_LIST_NOK;
	}

    linked_list *ptr = (linked_list *) ll;

    printf("[");
    int i;
    linked_list_node *current = ptr->head;
    for (i = 0; i < ptr->size || current != NULL; i++) {
        printf("%s%d", (i != 0 ? ", " : ""), current->value);
        current = current->next;
    }
    printf("]\n");

    return LINKED_LIST_OK;
}

linked_list * linked_list_new() {
    linked_list *ptr = (linked_list *) malloc(sizeof(linked_list));
    ptr->head = NULL;
    ptr->tail = NULL;
    ptr->size = 0;
    return (linked_list *) ptr;
}

int linked_list_delete(linked_list *ll) {

	if (ll == NULL) {
		return LINKED_LIST_NOK;
	}

    linked_list *ptr = (linked_list *) ll;
    int i;
    for (i = 0; ptr->size > 0; i++) {
        linked_list_remove(ll, 0);
    }
    free(ll);
    return LINKED_LIST_OK;
}

linked_list_iterator * linked_list_iterator_new(linked_list *ll) {
    linked_list *ptr = (linked_list *) ll;
    return (linked_list_iterator *) ptr->head;
}

linked_list_iterator * linked_list_iterator_next(linked_list_iterator * iter) {
    linked_list_node *ptr = (linked_list_node *) iter;
    return ptr->next;
}

int linked_list_iterator_getvalue(linked_list_iterator *iter) {
    linked_list_node *ptr = (linked_list_node *) iter;
    return ptr->value;
}