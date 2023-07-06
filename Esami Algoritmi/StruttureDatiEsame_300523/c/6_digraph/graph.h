#ifndef __GRAPH_H__
#define	__GRAPH_H__

#include "linked_list.h"

typedef enum { UNEXPLORED, EXPLORED, EXPLORING } STATUS;

typedef struct graph_node {
	void *value;
	linked_list *out_edges;
	linked_list *in_edges;

	int dist; // Indica la distanza dal nodo di partenza in sssp
	int map; // Indica il mapping nodo->int per la partition
	STATUS status; // tiene traccia dello stato di esplorazione
	int timestamp; // utile per associare valori interi ai vertici
} graph_node;

typedef struct graph_prop {
	int n_vertices;
	int n_edges;
} graph_prop;

typedef struct graph {
	linked_list* nodes;
	graph_prop* properties;
} graph;

/**
Create a new graph.
*/
graph * graph_new();

/**
Returns the list of vertices of g.
*/
linked_list * graph_get_nodes(graph * g);

/**
Returns a list containing all the outgoing edges from n in g.
*/
linked_list * graph_get_out_neighbors(graph_node * n);

/**
Returns a list containing all the ingoing edges from n in g.
*/
linked_list * graph_get_in_neighbors(graph_node * n);

/**
Adds and returns a new node with the value given in input to the graph g.
*/
graph_node * graph_add_node(graph * g, void * value);

/**
Adds an edge connecting from->to in the directed graph g.
*/
void graph_add_edge(graph * g, graph_node * from, graph_node * to);

/**
Returns a pointer to the value of the node n.
*/
void * graph_get_node_value(graph_node * n);

/**
Removes edge v1->v2 from the graph g.
*/
void graph_remove_edge(graph* g, graph_node* v1, graph_node* v2);

/**
Removes the node v from the graph g.
*/
void graph_remove_node(graph* g, graph_node* v);

/**
Prints a representation of the graph g on stdout.
*/
void graph_print(graph* g);

/**
Deletes the graph g.
*/
void graph_delete(graph * g);


#endif	/* __GRAPH_H__ */

