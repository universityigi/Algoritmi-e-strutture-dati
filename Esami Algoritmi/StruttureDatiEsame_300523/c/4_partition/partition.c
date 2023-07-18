#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "partition.h"

#define talloc(type, num) (type *) malloc(sizeof(type)*(num))

partition *partition_new(int maxindex){
	partition* p;
	int i;

	p = talloc(partition, 1);
	if (p == NULL) { perror("malloc"); exit(1); }

	p->maxindex = maxindex;
	p->nsets = 0;

	p->links = talloc(int, maxindex);
	if (p->links == NULL) { perror("malloc"); exit(1); }
	for (i = 0; i < maxindex; i++) p->links[i] = -2;

	p->sizes = talloc(int, maxindex);
	if (p->sizes == NULL) { perror("malloc"); exit(1); }
	for (i = 0; i < maxindex; i++) p->sizes[i] = -1;

	p->ranks = talloc(int, maxindex);
	if (p->ranks == NULL) { perror("malloc"); exit(1); }
	for (i = 0; i < maxindex; i++) p->ranks[i] = -1;
	return p;
}

void partition_delete(partition *p){
	free(p->links);
	free(p->sizes);
	free(p->ranks);
	free(p);
}

void partition_makeset(partition *p, int index){
	if (p->links[index] != -2) {
		fprintf(stderr, "partition_makeset called on an already made set %d\n", index);
		exit(1);
	}
	p->links[index] = -1;
	p->nsets++;
	p->sizes[index] = 1;
	p->ranks[index] = 1;
}

int partition_union(partition *p, int s1, int s2){
	if (p->links[s1] != -1) {
		fprintf(stderr, "partition_union called on a non-root node %d\n", s1);
		exit(1);
	}
	if (p->links[s2] != -1) {
		fprintf(stderr, "partition_union called on a non-root node %d\n", s2);
		exit(1);
	}
	if (s1 == s2) return s1;

	p->nsets--;

	if (p->ranks[s1] < p->ranks[s2]) {
		p->links[s1] = s2;
		p->sizes[s2] += p->sizes[s1];
		return s2;
	}
	else if (p->ranks[s1] > p->ranks[s2]) {
		p->links[s2] = s1;
		p->sizes[s1] += p->sizes[s2];
		return s1;
	}
	else {
		p->links[s2] = s1;
		p->sizes[s1] += p->sizes[s2];
		p->ranks[s1]++;
		return s1;
	}
}

int partition_find(partition *p, int index){
	int id;

	if (p->links[index] == -1) return index;

	if (p->links[p->links[index]] == -1) return p->links[index];

	id = partition_find(p, p->links[index]);
	p->links[index] = id;
	return id;
}