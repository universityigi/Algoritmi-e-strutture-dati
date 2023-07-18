#ifndef __PARTITION_H__
#define __PARTITION_H__

typedef struct {
	int *links;
	int *sizes;
	int *ranks;
	int maxindex;
	int nsets;
} partition;

partition *partition_new(int maxindex);
void partition_delete(partition *p);
void partition_makeset(partition *p, int index);
int partition_union(partition *p, int s1, int s2);
int partition_find(partition *p, int index);

#endif /* __PARTITION_H__ */