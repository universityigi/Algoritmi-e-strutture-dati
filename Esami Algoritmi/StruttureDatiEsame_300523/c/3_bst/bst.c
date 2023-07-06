#include <stdlib.h>
#include <stdio.h>
#include "bst.h"

typedef struct _bst_node {
	int key;
    void * value;
    struct _bst_node * left;
    struct _bst_node * right;
} _bst_node;

struct bst {
	_bst_node * root;
};

static _bst_node * bst_node_new(int k, void * v) {

	_bst_node * b = (_bst_node*)malloc(sizeof(_bst_node));
	b->key = k;
	b->value = v;
	b->left = NULL;
	b->right = NULL;

	return b;
}

bst * bst_new(int k, void * v) {

	bst * b = (bst*)malloc(sizeof(bst));
	b->root = bst_node_new(k, v);

	return b;
}

static void bst_insert_aux(_bst_node * t, int k, void * v) {

	if (t->key == k) {
	
		t->value = v;
	
	} else if (k < t->key) {

		if (t->left == NULL)
			t->left = bst_node_new(k, v);
		else
			bst_insert_aux(t->left, k, v);
	
	} else {

		if (t->right == NULL)
			t->right = bst_node_new(k, v);
		else
			bst_insert_aux(t->right, k, v);

	}
}

void bst_insert(bst * b, int k, void * v) {

	bst * t = b;
	if (t->root == NULL) 
		t->root = bst_node_new(k, v);
	
	else 
		bst_insert_aux(t->root, k, v);
}

static void * bst_find_aux(_bst_node * t, int k) {

	if (t == NULL)
		return NULL;
	
	else if (t->key == k) 
		return t->value;
	
	else if (k < t->key)
		return bst_find_aux(t->left, k);
	
	else 
		return bst_find_aux(t->right, k);
}

void * bst_find(bst * b, int k) {
	bst * t = b;
	if (t == NULL)
		return NULL;
	else
		return bst_find_aux(t->root, k);
}

static _bst_node * bst_find_min_aux(_bst_node * t) {

	if (t->left == NULL)
        return t;

    return bst_find_min_aux(t->left);
}

int bst_find_min(bst * b) {

	bst * t = b;
	if (b == NULL || t->root == NULL)
		return -1;

    return bst_find_min_aux(t->root)->key;
}

static _bst_node * bst_remove_min_aux(_bst_node * t) {

	// we are on the min key
	if (t->left == NULL) { 
		_bst_node * tr = t->right;
		free(t);
        return tr;
	}

    t->left = bst_remove_min_aux(t->left);
    return t;
}

void bst_remove_min(bst * b) {

	bst * t = b;
	if (b == NULL || t->root == NULL)
		return;

    t->root = bst_remove_min_aux(t->root);
}


static _bst_node * bst_remove_aux(_bst_node * n, int k) {

	if (n == NULL) return NULL;
    
    else if (k < n->key) 
    	n->left = bst_remove_aux(n->left, k);
    
    else if (k > n->key) 
    	n->right = bst_remove_aux(n->right, k);
    
    else {

        if (n->right == NULL) {
            _bst_node * to_return = n->left;
            free(n);
            return to_return;
        }

        if (n->left == NULL) {
            _bst_node * to_return = n->right;
            free(n);
            return to_return;
        }

        // two children case
        _bst_node * to_remove = n;
        
        n = bst_find_min_aux(to_remove->right);
        _bst_node * nn = bst_node_new(n->key, n->value);
        nn->right = bst_remove_min_aux(to_remove->right);
        nn->left = to_remove->left;
        free(to_remove);
        n = nn;
    }

    return n;
}

void bst_remove(bst * b, int k) {

	bst * t = b;
	if (b == NULL || t->root == NULL)
		return;

    t->root = bst_remove_aux(t->root, k);
}

static void bst_delete_aux(_bst_node * t) {

	if (t == NULL)
		return;

	bst_delete_aux(t->left);
	bst_delete_aux(t->right);
	free(t);
}

void bst_delete(bst * b) {

	if (b == NULL)
		return;

	bst * t = b;
	bst_delete_aux(t->root);
	free(t);
}

void bst_print_aux(_bst_node * t, int level) {
    
	if (t == NULL)
		return;

    int i;
    for (i = 0; i < level - 1; i++) {
    	printf("   ");
    }

    if (level > 0) {
    	printf(" |--");
    }

    printf("%d\n", t->key);

    bst_print_aux(t->left, level + 1);
    bst_print_aux(t->right, level + 1);
}

void bst_print(bst * b){
	bst * t = b;
    return bst_print_aux(t->root, 0);
}


static _bst_node * bst_predecessor_aux(_bst_node * n, int k) {
    
    if (n == NULL) 
    	return NULL;
    
    else if (k <= n->key)
        return bst_predecessor_aux(n->left, k);
    
    _bst_node * t = bst_predecessor_aux(n->right, k);
    if (t == NULL)
        return n;
    
    return t;
}

int bst_predecessor(bst * b, int k) {
    
	if (b == NULL)
		return -1;

    bst * t = b;
    _bst_node * predecessor = bst_predecessor_aux(t->root, k);
    if (predecessor == NULL)
        return -1;
    else
        return predecessor->key;
}