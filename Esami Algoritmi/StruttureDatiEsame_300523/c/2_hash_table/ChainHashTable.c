#include <string.h>
#include <stdio.h>
#include <stdlib.h>

#define prime 109345121
double maxLambda = 0.5;
int capacity = 7;
int a; 
int b;
int size;
struct Entry
{
   char* key;
   int value;
};

struct node
{
    struct Entry *data;
    struct node *next;
};


struct node** chain;

struct node** createTable(int cap)
{
    struct node** _chain = (struct node**)malloc(cap * sizeof(struct node *));
    int i;
    for(i = 0; i < cap; i++)
        _chain[i] = NULL;
    size = 0;
    a = (rand() % (prime-1)) + 1;
    b = (rand() % (prime-1)) + 1;
    return _chain;
}


unsigned long hashing_code(char *str) 
{
    unsigned long hash = 5381;
    int c;
    while (c = *str++)
        hash = ((hash << 5) + hash) + c; /* hash * 33 + c */        //BLACK MAGIC AT ITS BEST...
    return hash;
}

int hashFunction(char* k) 
{ // implementa la funzione hash (hash code + compressione)
   int hashCode = hashing_code(k);
        return (int) (abs(hashCode*a + b)%prime)%capacity;
    }


struct Entry** entrySet(struct node** _chain)
{
 struct Entry **result; //questo sarà un array di Entry
 result = (struct Entry **)malloc(size * sizeof(struct Entry *)); 
 int index = 0;
 for (int i=0;i<capacity;i++) //scorro tutta la hash table
 {
    struct node* elem = _chain[i];
    while (elem != NULL)
    {
        result[index] = elem->data;
        index++;
        elem = elem->next;
    }
 }
 return result;
}
struct node* search_list(struct node* elem, char* k) //elem should point to the first node of the linked list
{
if(elem != NULL) //devo rimuovere il primo elemento della linked list
    { 
        if(strcmp(elem->data->key, k)==0) return elem;
        else while(elem->next)
            {
                if(strcmp(elem->next->data->key, k)==0) return elem->next;
                elem = elem->next;
            }
        
    }
return NULL;
}


void _put(char* k, int value,struct node** _chain)
{
    int old = -1;
    //create a newnode with value
    struct node *newNode = (struct node *)malloc(sizeof(struct node));
    struct Entry *newEntry = (struct Entry *)malloc(sizeof(struct Entry));
    newEntry->key = k;
    newEntry->value = value; 
    newNode->data = newEntry;
    newNode->next = NULL;

    //calculate hash key
    int hash_key = hashFunction(k);

    //check if chain[key] is empty
    if(_chain[hash_key] == NULL)
    {
        _chain[hash_key] = newNode;
        size++;
    }

    //collision
    else
    {
        struct node *result = search_list(_chain[hash_key],k);
        if (result != NULL)
        {
            old = result->data->value;
            result->data = newEntry;
            //non c'e' size++ perche' c'e' gia' un vecchio valore
        }
        else
        {
        //add the node at the end of chain[key].
        struct node *temp = _chain[hash_key];
        while(temp->next)
        {
            temp = temp->next;
        }

        temp->next = newNode;
        size++;
        }
    }

}

int _get(char* k,struct node** _chain)
{
    int hash_key = hashFunction(k);
    return search_list(_chain[hash_key],k)->data->value;
}

int _remove(char* k,struct node** _chain)
{   
    int hash_key = hashFunction(k);

    struct node *elem = _chain[hash_key];
    struct node *dealloc;
    if(elem != NULL) //devo rimuovere il primo elemento della linked list
    { 
        if(strcmp(elem->data->key, k)==0)
        {
            dealloc = elem;
            int ret_value = dealloc->data->value;
            _chain[hash_key] = elem->next;
            free(dealloc->data);
            free(dealloc);
            //printf("ho rimosso al primo check");
            size--;
            return ret_value;
        }
        else
        {
            while(elem->next)
            {
                if(strcmp(elem->next->data->key, k)==0)
                {

                    dealloc = elem->next;
                    int ret_value = dealloc->data->value;
                    elem->next = elem->next->next;
                    free(dealloc->data);
                    free(dealloc);
                    size--;
                    return ret_value;
                }
                elem = elem->next;
            }
        }
    }
    printf("non ho rimosso niente");
    return NULL;
}
struct node** resize(int newCap,struct node** _chain) { // Aggiorna dimensione tabella hash 
 int oldcap = capacity;
 int oldsize = size;
 struct Entry** entry_array = entrySet(_chain);
 struct node** newChain = createTable(newCap); //ora size è diventato 0
 capacity = newCap;
 for (int i=0;i<oldsize;i++)
    _put(entry_array[i]->key,entry_array[i]->value,newChain);  
 free(_chain);
size = oldsize;
capacity = newCap;
return newChain;
}
struct node** _put_maxlamba(char* k, int value,struct node** _chain)
{
    struct node** Newchain;
    _put(k,value,_chain);
    if (size + 1 > capacity*maxLambda)
        Newchain = resize(2*capacity - 1,_chain);
    else
        Newchain = _chain;
    return Newchain;
}
void _print()
{
    int i;

    for(i = 0; i < capacity; i++)
    {
        struct node *temp = chain[i];
        printf("chain[%d]-->",i);
        while(temp)
        {
            printf("Entry:( %s, %d) -->",temp->data->key,temp->data->value);
            temp = temp->next;
        }
        printf("NULL\n");
    }
}
void printEntrySet(struct node** _chain)
{

}
int main()
{
    //createTable array of list to NULL
    chain = createTable(capacity);
    printf("attuale capacity: %d \n", capacity);
    printf("attuale size: %d \n", size);
    printf("attuale prime: %d \n", prime);
    printf("attuale a: %d \n", a);
    printf("attuale a: %d \n", b);
    _put("aa\0",7,chain);
    _put("bb\0",8,chain);
    _put("cc\0",10,chain);
    _put("dd\0",15,chain);
    _put("ee\0",21,chain);
    _put("ff\0",11,chain);
    _put("gg\0",4,chain);
    _put("hh\0",6,chain);
    _put("ii\0",7,chain);
    _print();
    printf("/////////////////////// \n");
    printf("Aggiungo la voce ll,8  con _put_maxlamba, che controlla se ho superato il carico massimo tollerabile: \n");
    chain = _put_maxlamba("ll\0",8,chain); //questo put controlla se ho superato il carico massimo tollerabile
    _print();
    printf("attuale size: %d \n", size);
    printf("attuale capacity: %d \n", capacity);
    printf("/////////////////////// \n");
    printf("Rimuovo l' elemento con chiave dd: \n");
    if(_remove("dd\0",chain))
    {
        _print();
     }
    else
        printf("Value Not Present\n");
    int val = _get("ii\0",chain);
    printf("il valore corrispondente alla chiave ii: %d \n",val);
    return 0;
}
