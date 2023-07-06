#ifndef LINKED_LIST_H
#define	LINKED_LIST_H
    
#define LINKED_LIST_OK 0
#define LINKED_LIST_NOK -1
    
typedef struct linked_list linked_list;
typedef void linked_list_iterator;
    
/*
 * Write in the position denoted by the value pointer the element in the i-th
 * position. Returns LINKED_LIST_OK in case of success and LINKED_LIST_NOK in case
 * of failure.
 */
int linked_list_get(linked_list * ll, int i, int *value);

/*
 * Add value at the end of the list. Return LINKED_LIST_NOK in case of failure or 
 * the position of the element in case of success
 */
int linked_list_add(linked_list * ll, int value);

/*
 * Remove the element in the i-th position. Return LINKED_LIST_OK in case of success
 * and LINKED_LIST_NOK in case of failure
 */
int linked_list_remove(linked_list *ll, int i);

/*
 * Print the list to stdout.
 * Returns LINKED_LIST_OK in case of success and LINKED_LIST_NOK in case of failure
 */
int print_linked_list(linked_list *ll);

/*
 * Create an empty linked list and return a pointer or NULL in case of insuccess
 */
linked_list * linked_list_new();

/*
 * Destroy the linked_list and its content. Returns LINKED_LIST_OK in case of success
 * and LINKED_LIST_NOK in case of insuccess
 */
int linked_list_delete(linked_list *ll);

/*
 * Returns a pointer to an iterator for the list or NULL in case of error or if the list is empty
 */
linked_list_iterator * linked_list_iterator_new(linked_list *ll);

/*
 * Returns a pointer to the next element in the list or NULL is there is no more elements
 */
linked_list_iterator * linked_list_iterator_next(linked_list_iterator * iter);

/*
 * Returns the value of list in correspondence of the iterator
 */
int linked_list_iterator_getvalue(linked_list_iterator *iter);

#endif	/* LINKED_LIST_H */

