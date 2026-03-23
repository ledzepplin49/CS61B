package deque;

public class ArrayDeque <T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    public ArrayDeque(){
        this.items=(T[]) new Object[10];;
        this.size=0;
        this.nextFirst=4;
        this.nextLast=5;
    }

    public void addFirst(T item){
        if(size== items.length){
            resize(2*size());
        }
        items[nextFirst]=item;
        nextFirst=(nextFirst-1+items.length)%items.length;
        size++;
    };
    /**
    public void resize(int newSize){
        T[] newItems = (T[]) new Object[newSize];
        if(newSize>items.length){
            if(nextFirst<nextLast){
                System.arraycopy(items,nextFirst+1,newItems,1,size);
                nextFirst=0;
                nextLast=size+1;
                items=newItems;
            }else{
                if(nextFirst==items.length-1){
                    System.arraycopy(items,0,newItems,0,size);
                    nextFirst=newSize-1;
                } else if (nextLast==0) {
                    System.arraycopy(items,nextFirst+1,newItems,0,size);
                    nextFirst=newSize-1;
                    nextLast=1+size;
                }else{
                    int front=nextLast;
                    int rest=size-front;
                    int back=newSize-rest;
                    System.arraycopy(items,0,newItems,0,front);
                    System.arraycopy(items,nextFirst+1,newItems,back,rest);
                    nextFirst=back-1;
                }
            }
            items=newItems;
            System.out.println(newSize);
        }
    }
     */
    public void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];

        // 按逻辑顺序从头到尾遍历现有元素，依次放入新数组的 0, 1, 2... 位置
        for (int i = 0; i < size; i++) {
            // (nextFirst + 1 + i) 就是原来数组中第 i 个元素的真实索引
            newItems[i] = items[(nextFirst + 1 + i + items.length) % items.length];
        }

        items = newItems;
        nextFirst = capacity - 1; // 新数组的头部在末尾
        nextLast = size;          // 新数组的尾部在 size 处
    }
    public void addLast(T item){
        if(size==items.length){
            resize(2*size());
        }
        items[nextLast]=item;
        nextLast=(nextLast+1)%items.length;
        size++;
    }
    public boolean isEmpty(){
        if(size==0){
            return true;
        }else{
            return false;
        }
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        int i=nextFirst-1;
        while(items[i]!=null){
            System.out.print(items[i]+" ");
            i=(i+1)%items.length;
        }
        System.out.println("\n");
    }
    public T removeFirst(){
        if((size<items.length/4)&&(size>1)){
            resize((items.length)/2);
        }
        if(size==0){
            return null;
        }
        T item=items[(nextFirst+1)%items.length];
        nextFirst=(nextFirst+1)%items.length;
        items[nextFirst]=null;
        size--;
        return item;
    }
    public T removeLast(){
        if((size<items.length/4)&&(size>1)){
            resize((items.length)/2);
        }
        if(size==0){
            return null;
        }
        T item=items[(nextLast-1)%items.length];
        nextLast=(nextLast-1)%items.length;
        items[nextLast]=null;
        size--;
        return item;
    }
    public T get(int index){
        int i=(nextFirst-1+index+ items.length)%items.length;
        return items[i];
    }
}
