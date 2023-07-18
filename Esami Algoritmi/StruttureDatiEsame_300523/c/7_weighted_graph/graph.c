#include "graph.h"
#include <stdlib.h>
#include <stdio.h>

graph * graph_new() {
	graph *to_ret = (graph*)malloc(sizeof(graph));
	to_ret->nodes = linked_list_new();
	to_ret->properties = (graph_prop*)malloc(sizeof(graph_prop));
	to_ret->properties->n_edges = 0;
	to_ret->properties->n_vertices = 0;
	return to_ret;
}

linked_list * graph_get_nodes(graph * g) {
	if (g)
		return(g->nodes);
	return NULL;
}

linked_list * graph_get_neighbors(graph * g, graph_node * n) {
	if (g && n) {
		linked_list_iterator *it = linked_list_iterator_new(g->nodes);
		graph_node* cur = linked_list_iterator_getvalue(it);
		while (linked_list_iterator_hasnext(it)) {
			if (cur == n){
				linked_list_iterator_delete(it);
				return n->out_edges;
			}
			cur = linked_list_iterator_next(it);
		}
		linked_list_iterator_delete(it);
		return NULL;
	}
	else
		return NULL;
}

graph_node * graph_add_node(graph * g, void * value) {
	if (g && value) {
		graph_node* to_put = (graph_node*)malloc(sizeof(graph_node));
		to_put->value = value;
		to_put->out_edges = linked_list_new();
		to_put->status = UNEXPLORED;
		to_put->timestamp = 0;
		linked_list_enqueue(g->nodes, (void*)to_put);
		g->properties->n_vertices++;
		return to_put;
	}
	else
		return NULL;
}

void graph_add_edge(graph * g, graph_node * from, graph_node * to, int weight) {
	if (g && from && to) {
		linked_list_iterator *it = linked_list_iterator_new(g->nodes);
		graph_node* current = (graph_node*)linked_list_iterator_getvalue(it);
		while (linked_list_iterator_hasnext(it)) {
			if (current == from) {
				graph_edge* to_insert = (graph_edge*)malloc(sizeof(graph_edge));
				to_insert->source = from;
				to_insert->target = to;
				to_insert->weight = weight;
				linked_list_enqueue(from->out_edges, to_insert);
				g->properties->n_edges++;
				linked_list_iterator_delete(it);
				return;
			}
			current = linked_list_iterator_next(it);
		}
		linked_list_iterator_delete(it);
	}
	else
		return;
}

void * graph_get_node_value(graph_node * n) {
	if (n) {
		return n->value;
	}
	else
		return NULL;
}

void graph_remove_edge(graph * g, graph_node * v1, graph_node * v2) {
	if (g && v1 && v2) {
		linked_list_iterator *it = linked_list_iterator_new(g->nodes);
		graph_node* current = (graph_node*)linked_list_iterator_getvalue(it);
		while (linked_list_iterator_hasnext(it)) {
			if (current == v1) {
				linked_list_iterator *it2 = linked_list_iterator_new(v1->out_edges);
				graph_edge* search = linked_list_iterator_getvalue(it2);
				while (linked_list_iterator_hasnext(it2)) {
					if (search->target == v2) {
						free(linked_list_iterator_getvalue(it2));
						linked_list_iterator_remove(it2);
						linked_list_iterator_delete(it2);
						linked_list_iterator_delete(it);
						g->properties->n_edges--;
						return;
					}
					search = linked_list_iterator_next(it2);
				}
				linked_list_iterator_delete(it2);
			}
			current = linked_list_iterator_next(it);
		}
		linked_list_iterator_delete(it);
	}
	else
		return;
}

void graph_remove_node(graph * g, graph_node * v) {
	if (g && v) {
		linked_list_iterator *it = linked_list_iterator_new(g->nodes);
		graph_node *cur = (graph_node*)linked_list_iterator_getvalue(it);
		for (; linked_list_iterator_hasnext(it); cur = linked_list_iterator_next(it)) {
			//case I found v in g->nodes
			if (cur == v) {
				linked_list_iterator *it2 = linked_list_iterator_new(cur->out_edges);
				for (; linked_list_iterator_hasnext(it2); linked_list_iterator_next(it2)) {
					free(linked_list_iterator_getvalue(it2));
					linked_list_iterator_remove(it2);
				}
				linked_list_iterator_delete(it2);
				linked_list_iterator_remove(it);
			}
			//case I am on a generic node
			else {
				linked_list_iterator *it2 = linked_list_iterator_new(cur->out_edges);
				graph_edge *to = linked_list_iterator_getvalue(it2);
				for (; linked_list_iterator_hasnext(it2); to = linked_list_iterator_next(it2)) {
					if (to->target == v) {
						free(linked_list_iterator_getvalue(it2));
						linked_list_iterator_remove(it2);
					}
				}
				linked_list_iterator_delete(it2);
			}
		}
		g->properties->n_vertices--;
		linked_list_iterator_delete(it);
		linked_list_delete(v->out_edges);
		free(v);
	}
	else
		return;
}

void graph_print(graph * g) {
	if (g) {
		//linked_list_print(g->nodes);
		//printf("\n");
		linked_list_iterator *it = linked_list_iterator_new(g->nodes);
		graph_node* cur = (graph_node*)linked_list_iterator_getvalue(it);
		while (linked_list_iterator_hasnext(it)) {
			printf("%s: ", (char*)(cur->value));
			linked_list_iterator *it2 = linked_list_iterator_new(cur->out_edges);
			graph_edge* to = linked_list_iterator_getvalue(it2);
			//printf("Out-edges of %p\n", cur);
			//linked_list_print(cur->out_edges);
			while (linked_list_iterator_hasnext(it2)) {
				printf("(%s:%d) ", (char*)(to->target->value), to->weight);
				to = linked_list_iterator_next(it2);
			}
			printf("\n");
			linked_list_iterator_delete(it2);
			cur = linked_list_iterator_next(it);
		}
		linked_list_iterator_delete(it);
	}
	else
		return;
}

void graph_delete(graph * g) {
	if (g) {
		linked_list_iterator *it = linked_list_iterator_new(g->nodes);
		graph_node *cur = (graph_node*)linked_list_iterator_getvalue(it);
		for (; linked_list_iterator_hasnext(it); cur = linked_list_iterator_next(it)) {
			linked_list_iterator *it2 = linked_list_iterator_new(cur->out_edges);
			for (; linked_list_iterator_hasnext(it2); linked_list_iterator_next(it2)) {
				free(linked_list_iterator_getvalue(it2));
				linked_list_iterator_remove(it2);
			}
			linked_list_iterator_delete(it2);
		}
		linked_list_iterator_delete(it);
		it = linked_list_iterator_new(g->nodes);
		cur = (graph_node*)linked_list_iterator_getvalue(it);
		for (; linked_list_iterator_hasnext(it); cur = linked_list_iterator_next(it)) {
			free(cur->out_edges);
			free(cur);
		}
		linked_list_iterator_delete(it);
		linked_list_delete(g->nodes);
		free(g->properties);
		free(g);
	}
	else
		return;
}