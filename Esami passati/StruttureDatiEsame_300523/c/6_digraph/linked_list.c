#include "linked_list.h"
#include "stdlib.h"
#include "stdio.h"

struct linked_list_node {
	void *value;
	linked_list_node *next;
};

struct linked_list {
	linked_list_node *head;
	linked_list_node *tail;
	int size;
};

struct linked_list_iterator {
	linked_list *ll;
	linked_list_node *current;
	linked_list_node *previous;
	int just_removed;
};

linked_list * linked_list_new() {
    linked_list * ptr = (linked_list *) malloc(sizeof(linked_list));
    ptr->head = NULL;
    ptr->tail = NULL;
    ptr->size = 0;
    return ptr;
}

void linked_list_add(linked_list * ll, int pos, void * value){
	if (pos<0 || pos > (ll->size)) {
		printf("linked_list indexOutOfBound Exception in linked_list_add for pos:%d\n", pos);
		return;
	}
	if (pos == ll->size)
		linked_list_enqueue(ll, value);
	else {
		linked_list_node *to_put = (linked_list_node *)malloc(sizeof(linked_list_node));
		to_put->value = value;
		if (pos == 0) {
			to_put->next = ll->head;
			ll->head = to_put;
			(ll->size)++;
		}
		else {
			int i;
			linked_list_node *cur = ll->head;
			for (i = 0; i < pos - 1; i++) {
				cur = cur->next;
			}
			to_put->next = cur->next;
			cur->next = to_put;
			(ll->size)++;
		}
	}
}

void * linked_list_get(linked_list * ll, int pos) {
	if (pos<0 || pos > (ll->size) - 1) {
		printf("linked_list indexOutOfBound Exception in linked_list_get for pos:%d\n", pos);
		return NULL;
	}
    
    int i;
    linked_list_node *current = ll->head;
    for (i = 0; i < pos && current != NULL; i++) {
        current = current->next;
    }
    if (current == NULL)
        return NULL;

    return current->value;
}

void linked_list_remove(linked_list * ll, int pos){
	if (pos<0 || pos >(ll->size) - 1) {
		printf("linked_list indexOutOfBound Exception in linked_list_remove for pos:%d\n", pos);
		return;
	}
	if (pos == 0)
		free(linked_list_dequeue(ll));
	else {
		linked_list_node *cur = ll->head;
		int i;
		for (i = 0; i < pos - 1; i++) {
			cur = cur->next;
		}
		linked_list_node *to_rem = cur->next;
		cur->next = cur->next->next;
		if (pos == (ll->size) - 1)
			ll->tail = cur;
		free(to_rem);
		(ll->size)--;
	}
}

void linked_list_enqueue(linked_list * ll, void * value){
	linked_list_node * added = (linked_list_node *)malloc(sizeof(linked_list_node));
	added->next = NULL;
	added->value = value;

	if (ll->tail != NULL) {
		ll->tail->next = added;
	}

	if (ll->head == NULL) {
		ll->head = added;
	}
	ll->tail = added;
	ll->size++;
}

void * linked_list_dequeue(linked_list * ll){
	if (ll->size <= 0)
		return NULL;
	linked_list_node *to_rem = ll->head;
	ll->head = ll->head->next;
	if (ll->size == 1)
		ll->tail = NULL;
	(ll->size)--;
	return to_rem;
}

int linked_list_size(linked_list *ll) {
	return ll->size;
}

void linked_list_print(linked_list * ll){
	if(ll == NULL)
		printf("NullPointerException in linked_list_print\n");
	linked_list_node *cur = ll->head;
	printf("[ ");
	while (cur) {
		(cur->next) ? printf("%p, ", (cur->value)) : printf("%p", (cur->value));
		cur = cur->next;
	}
	printf(" ]\n");
	printf("size: %d - head: %p - tail: %p\n", ll->size,
		ll->head != NULL ? ll->head->value : NULL,
		ll->tail != NULL ? ll->tail->value : NULL);
	/*if (ll->head != NULL && ll->head->next != NULL)
		printf("nxtHead: %p \n", ll->head->next->value);
	if (ll->tail != NULL && ll->tail->next != NULL)
		printf("nxtTail: %p \n", ll->tail->next->value);*/
}

void linked_list_delete(linked_list *l) {
    if (l == NULL) {
        return;
    }
	linked_list_node * it = l->head;

	while (it) {
		linked_list_node * rem = it;
		it = it->next;
		free(rem);
	}
    free(l);
    return;
}

linked_list_iterator * linked_list_iterator_new(linked_list *ll) {
	if (ll->size == 0)
		return NULL;
	linked_list_iterator * to_ret = (linked_list_iterator *)malloc(sizeof(linked_list_iterator));
	to_ret->ll = ll;
	to_ret->just_removed = 0;
	to_ret->current = ll->head;
	to_ret->previous = NULL;
	return to_ret;
}

int linked_list_iterator_hasnext(linked_list_iterator * iter){
	return 1 - (iter == NULL || iter->current == NULL);
}

void * linked_list_iterator_next(linked_list_iterator * iter) {
	if (!iter->current) {
		return NULL;
	}
	if (iter->just_removed && iter->previous == NULL) {
		iter->current = iter->ll->head;
		iter->just_removed = 0;
		return (iter->current) ? iter->current->value : NULL;
	}
	if (iter->just_removed) {
		iter->current = iter->previous->next;
		if (iter->current) {
			iter->just_removed = 0;
			return iter->current->value;
		}
		else {
			iter->just_removed = 0;
			return NULL;
		}
	}
	iter->previous = iter->current;
	iter->current = iter->current->next;
	iter->just_removed = 0;
	return (iter->current) ? iter->current->value : NULL;
}

void linked_list_iterator_remove(linked_list_iterator * iter){
	if (iter->just_removed)
		return;
	if (iter->current == iter->ll->head) {
		iter->just_removed = 1;
		linked_list_remove(iter->ll, 0);
	}
	else {
		iter->previous->next = iter->current->next;
		(iter->ll->size)--;
		if (iter->current == iter->ll->tail) {
			iter->ll->tail = iter->previous;
			iter->ll->tail->next = NULL;
		}
		iter->just_removed = 1;
		free(iter->current);
	}
}

void * linked_list_iterator_getvalue(linked_list_iterator *iter) {
	if (!iter || !iter->current)
		return NULL;
	if (iter->just_removed)
		return NULL;
	return iter->current->value;
}

void linked_list_iterator_delete(linked_list_iterator * iter){
	free(iter);
}
