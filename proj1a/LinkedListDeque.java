/*This is the first part of project 1A of UC Berkeley CS61B, which is to implement
a linked list to emulate a deque.
The author is @GreyPreacher
*/
public class LinkedListDeque<T> {
    public class Node{
        private T item;//data that stored in the node
        private Node pre;//the precedessor node
        private Node next;//the next node of this node

        public Node(T n,Node pree,Node nextt){//for a node
            item=n;
            pre=pree;
            next=nextt;
        }

        public Node(Node pree,Node nextt){//especially for the sentinel node
            pre=pree;
            next=nextt;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque(){//initiate the deque
        sentinel=new Node(null,null);
        sentinel.next=sentinel;
        sentinel.pre=sentinel;
        size=0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void addFirst(T item){
        Node first=new Node(item,sentinel,sentinel.next);
        sentinel.next.pre=first;
        sentinel.next=first;
        size++;
    }

    public void addLast(T item){
        Node last=new Node(item,sentinel.pre,sentinel);
        sentinel.pre.next=last;
        sentinel.pre=last;
        size++;
    }

    public void printDeque(){//print the deque and don't forget the
        Node p=sentinel.next;//the print command is System.out.println()
        while(p!=sentinel){
            System.out.println(p.item+" ");
            p=p.next;
        }
    }

    public T removeFirst(){//remove the first item of the deque and resize the deque
        if(size==0){
            return null;
        }
        T item=sentinel.next.item;
        sentinel.next=sentinel.next.next;
        sentinel.next.pre=sentinel;
        size--;
        return item;
    }

    public T removeLast(){//remove the last item of the deque and resize the deque
        if(size==0){
            return null;
        }
        T item=sentinel.pre.item;
        sentinel.pre=sentinel.pre.pre;
        sentinel.pre.next=sentinel;
        size--;
        return item;
    }

    public T get(int index){//get the index-th item of the deque using iteration
        if(index>=size){
            return null;
        }
        Node p=sentinel;
        for(int i=0;i <= index;i++){
            p=p.next;
        }
        return p.item;
    }

    private T getRecursiveHelp(Node start, int index) {
        if (index == 0) {
            return start.item;
        } else {
            return getRecursiveHelp(start.next, index - 1);
        }
    }
    public T getRecursive(int index){//get the index-th item of
        if (index >= size){//the deque using recursion
            return null;
        }
        return getRecursiveHelp(sentinel.next, index);
    }
}
