#ifndef __LINKED_LIST_H__
#define	__LINKED_LIST_H__
    
typedef struct linked_list_node linked_list_node;
typedef struct linked_list linked_list;
typedef struct linked_list_iterator linked_list_iterator;

/**********************
	  linked_list
***********************/
/**
Creates a new linked_list.
*/
linked_list * linked_list_new();

/**
Adds a new node with the value given in input in position pos of the list ll.
*/
void linked_list_add(linked_list * ll, int pos, void * value);

/**
Searches the node in position i of the list ll, and returns a pointer to its value, or NULL if i is out of bound.
*/
void *linked_list_get(linked_list * ll, int i);

/**
Removes from the list ll the node in position pos. Notice that the function does not free up the memory pointed by the
value of the node in position pos.
*/
void linked_list_remove(linked_list* ll, int pos);

/**
Adds to tail of the list ll a new node with value pointing at value.
*/
void linked_list_enqueue(linked_list* ll, void* value);

/**
Removes the head of the list and returns the node removed. Notice that the function does not free up memory.
*/
void *linked_list_dequeue(linked_list* ll);

/**
Returns the size of the list ll.
*/
int linked_list_size(linked_list *ll);

/**
Prints a representation of the list ll on stdout
*/
void linked_list_print(linked_list *ll);

/**
Removes all the nodes from the list ll and frees up memory. Notice that the function does not free up the memory pointed by the
value of the nodes.
*/
void linked_list_delete(linked_list *ll);


/**********************
  linked_list_iterator
***********************/
/**
Creates a new iterator over the list ll.
*/
linked_list_iterator * linked_list_iterator_new(linked_list *ll);

/**
Returns 1 if iter has next, 0 otherwise.
*/
int linked_list_iterator_hasnext(linked_list_iterator* iter);


/**
Moves the iterator one step further and returns the value pointed by the new node or NULL, if 
the iterator reached the end of the list.
*/
void * linked_list_iterator_next(linked_list_iterator * iter);

/**
Removes the node pointed by the iterator from the list.
*/
void linked_list_iterator_remove(linked_list_iterator * iter);

/**
Returns the value of the node pointed by the iterator.
*/
void * linked_list_iterator_getvalue(linked_list_iterator *iter);

/**
Deletes the iterator iter. Notice that one does not need to delete an iterator if it succesfully iterates through
all the list.
*/
void linked_list_iterator_delete(linked_list_iterator* iter);

#endif	/* __LINKED_LIST_H__ */