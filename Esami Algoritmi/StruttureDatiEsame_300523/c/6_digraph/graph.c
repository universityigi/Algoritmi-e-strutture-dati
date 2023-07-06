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

linked_list * graph_get_out_neighbors(graph_node * n) {
    return n->out_edges;
}

linked_list * graph_get_in_neighbors(graph_node * n) {
    return n->in_edges;
}

graph_node * graph_add_node(graph * g, void * value) {
	if (g && value) {
		graph_node* to_put = (graph_node*)malloc(sizeof(graph_node));
		to_put->value = value;
		to_put->out_edges = linked_list_new();
		to_put->in_edges = linked_list_new();
		to_put->status = UNEXPLORED;
		to_put->timestamp = 0;
		linked_list_enqueue(g->nodes, (void*)to_put);
		g->properties->n_vertices++;
		return to_put;
	}
	else
		return NULL;
}

void graph_add_edge(graph * g, graph_node * from, graph_node * to) {
	if (g && from && to) {
		linked_list_iterator *it = linked_list_iterator_new(g->nodes);
		graph_node* current = (graph_node*)linked_list_iterator_getvalue(it);
		while (linked_list_iterator_hasnext(it)) {
			if (current == from) {
				linked_list_enqueue(from->out_edges, to);
				linked_list_enqueue(to->in_edges, from);
				g->properties->n_edges++;
				linked_list_iterator_delete(it);
				return;
			}
			current = (graph_node*)linked_list_iterator_next(it);
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
				graph_node* search = (graph_node*)linked_list_iterator_getvalue(it2);
				while (linked_list_iterator_hasnext(it2)) {
					if (search == v2) {
						linked_list_iterator *it3 = linked_list_iterator_new(v2->in_edges);
						graph_node* nd = (graph_node*)linked_list_iterator_getvalue(it3);
						while (linked_list_iterator_hasnext(it3)) {
							if (nd == v1) {
								linked_list_iterator_remove(it3);
								linked_list_iterator_delete(it3);
							}
							nd = (graph_node*)linked_list_iterator_next(it3);
						}
				
						linked_list_iterator_remove(it2);
						linked_list_iterator_delete(it2);
						linked_list_iterator_delete(it);
						g->properties->n_edges--;
						return;
					}
					search = (graph_node*)linked_list_iterator_next(it2);
				}
				linked_list_iterator_delete(it2);
			}
			current = (graph_node*)linked_list_iterator_next(it);
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
		for (; linked_list_iterator_hasnext(it); cur = (graph_node*)linked_list_iterator_next(it)) {
			//case I found v in g->nodes
			if (cur == v) {
				linked_list_iterator *it2 = linked_list_iterator_new(cur->out_edges);
				for (; linked_list_iterator_hasnext(it2); linked_list_iterator_next(it2)) {
					linked_list_iterator_remove(it2);
				}
				linked_list_iterator_delete(it2);

				linked_list_iterator *it3 = linked_list_iterator_new(cur->in_edges);
				for (; linked_list_iterator_hasnext(it3); linked_list_iterator_next(it3)) {
					linked_list_iterator_remove(it3);
				}
				linked_list_iterator_delete(it3);

				linked_list_iterator_remove(it);
			}
			//case I am on a generic node
			else {
				linked_list_iterator *it2 = linked_list_iterator_new(cur->out_edges);
				graph_node *to = (graph_node*)linked_list_iterator_getvalue(it2);
				for (; linked_list_iterator_hasnext(it2); to = (graph_node*)linked_list_iterator_next(it2)) {
					if(to == v)
						linked_list_iterator_remove(it2);
				}
				linked_list_iterator_delete(it2);

				linked_list_iterator *it3 = linked_list_iterator_new(cur->in_edges);
				graph_node *nd = (graph_node*)linked_list_iterator_getvalue(it3);
				for (; linked_list_iterator_hasnext(it3); to = (graph_node*)linked_list_iterator_next(it3)) {
					if(nd == v)
						linked_list_iterator_remove(it3);
				}
				linked_list_iterator_delete(it3);
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
			graph_node* to = (graph_node*)linked_list_iterator_getvalue(it2);
			//printf("Out-edges of %p\n", cur);
			//linked_list_print(cur->out_edges);
			while (linked_list_iterator_hasnext(it2)) {
				printf("%s ", (char*)(to->value));
				to = (graph_node*)linked_list_iterator_next(it2);
			}
			printf("\n");
			linked_list_iterator_delete(it2);
			cur = (graph_node*)linked_list_iterator_next(it);
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
		for (; linked_list_iterator_hasnext(it); cur = (graph_node*)linked_list_iterator_next(it)) {
			linked_list_iterator *it2 = linked_list_iterator_new(cur->out_edges);
			for (; linked_list_iterator_hasnext(it2); linked_list_iterator_next(it2)) {
				linked_list_iterator_remove(it2);
			}
			linked_list_iterator_delete(it2);
		}
		linked_list_iterator_delete(it);
		it = linked_list_iterator_new(g->nodes);
		cur = (graph_node*)linked_list_iterator_getvalue(it);
		for (; linked_list_iterator_hasnext(it); cur = (graph_node*)linked_list_iterator_next(it)) {
			free(cur->out_edges);
			free(cur->in_edges);
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