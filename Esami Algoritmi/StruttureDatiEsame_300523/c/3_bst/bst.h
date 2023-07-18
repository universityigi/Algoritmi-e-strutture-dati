#ifndef BST_H
#define	BST_H

#ifdef	__cplusplus
extern "C" {
#endif
    
typedef struct bst bst;

bst * bst_new(int k, void * v);
void bst_insert(bst * t, int k, void * v);
void * bst_find(bst * t, int k);
void bst_remove(bst * t, int k);
int bst_find_min(bst * n);
void bst_remove_min(bst * n);

void bst_delete(bst * h);
void bst_print(bst * h);

int bst_predecessor(bst * t, int k);

#ifdef	__cplusplus
}
#endif

#endif	/* BST_H */

