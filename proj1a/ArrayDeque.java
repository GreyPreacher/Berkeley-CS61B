/*This is the second part of project 1A of UC Berkeley CS61B, which is to implement
a list to emulate a deque.
The author is @GreyPreacher
*/
public class ArrayDeque<T> {
    private T[] array;
    private int size;
    private int front;
    private int last;
    private int length;//useful when manipulate

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        front=4;
        last=4;
        length=8;
    }

    private int minus(int index){
        if(index==0){
            return length-1;
        }
        return index-1;
    }

    private int plus(int index,int length){
        index%=length;
        if(index==length-1){
            return 0;
        }
        return index+1;
    }
    private void extendArray(){
        T[] newArray=(T[]) new Object[length*2];
        int ptr1=front;
        int ptr2=length;
        while(ptr1!=last){
            newArray[ptr2]=array[ptr1];
            ptr1=plus(ptr1,length);
            ptr2=plus(ptr2,length*2);
        }
        front = length;
        last = ptr2;
        array=newArray;
        length*=2;
    }

    private void reduce(){
        T[] newArray=(T[]) new Object[length/2];
        int ptr1=front;
        int ptr2=length/4;
        while(ptr1!=last){
            newArray[ptr2]=array[ptr1];
            ptr1=plus(ptr1,length);
            ptr2=plus(ptr2,length/2);
        }
        front=length/4;
        last=ptr2;
        length/=2;
        array=newArray;
    }

    public void addFirst(T item){
        if(size==length-1){
            extendArray();
        }
        front=minus(front);
        array[front]=item;
        size++;
    }

    public void addLast(T item){
        if(size==length-1){
            extendArray();
        }
        array[last]=item;
        last=plus(last,length);
        size++;
    }

    public T removeFirst(){
        if(length>=16&&size/length<0.25){
            reduce();
        }
        if (size==0){
            return null;
        }
        T result=array[front];
        front=plus(front,length);
        size--;
        return result;
    }

    public T removeLast(){
        if(length>=16&&size/length<0.25){
            reduce();
        }
        if (size==0){
            return null;
        }
        last=minus(last);
        T result=array[last];
        size--;
        return result;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void printDeque(){
        int ptr1=front;
        while(ptr1!=last){
            System.out.println(array[ptr1]+" ");
            ptr1++;
        }
    }

    public T get(int index){
        if(index>=size){
            return null;
        }
        int ptr=front;
        for(int i=0;i<index;i++){
            ptr=plus(ptr,length);
        }
        return array[ptr];
    }
}
