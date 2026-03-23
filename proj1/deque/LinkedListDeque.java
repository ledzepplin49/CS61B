package deque;

public class LinkedListDeque<BleepBlorp> {
    private IntNode sentinel;
    private int size;
    public class IntNode {
        public BleepBlorp item;
        public IntNode back;
        public IntNode front;
        public IntNode(BleepBlorp item) {
            this.item = item;
        }
        public IntNode(BleepBlorp item, IntNode Back,IntNode Front) {
            this.item = item;
            this.back=Back;
            this.front=Front;
        }
        public IntNode() {
            this.item = null;
        }
    }
    public LinkedListDeque(){
        sentinel = new IntNode();
        sentinel.back=sentinel;
        sentinel.front=sentinel;
        size = 0;
    };
    public void addFirst(BleepBlorp item){
        IntNode newnode =new IntNode(item,sentinel.back,sentinel);
        sentinel.back.front=newnode;
        sentinel.back=newnode;
        size++;
    }
    public void addLast(BleepBlorp item){
        IntNode newnode=new IntNode(item,sentinel,sentinel.front);
        sentinel.front.back=newnode;
        sentinel.front=newnode;
        size++;
    }
    public boolean isEmpty(){
        if(sentinel.back==sentinel){
            return true;
        }else {
            return false;
        }
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        IntNode temp=sentinel.back;
        while(temp!=sentinel){
            System.out.print(temp.item+" ");
            temp=temp.back;
        }
        System.out.println("\n");
    }
    public BleepBlorp removeFirst(){
        if (sentinel.front==sentinel){
            return null;
        }
        IntNode temp=sentinel.back;
        sentinel.back.back.front=sentinel;
        sentinel.back.front=null;
        sentinel.back=sentinel.back.back;
        size--;
        return temp.item;
    }
    public BleepBlorp removeLast(){
        if (sentinel.front==sentinel){
            return null;
        }
        IntNode temp=sentinel.front;
        temp.front.back=sentinel;
        sentinel.front=temp.front;
        size--;
        return temp.item;
    }
    public BleepBlorp get(int index){
        int i=0;
        IntNode temp=sentinel;
        if(index>size){
            return null;
        }
        while(i<index){
            temp=temp.back;
            i++;
        }
        return temp.item;
    }
    public BleepBlorp getRecursive(int index){
        IntNode temp=sentinel.back;
        BleepBlorp value;
        if(index==1){
            return sentinel.back.item;
        }else{
            sentinel.back=sentinel.back.back;
            value=getRecursive(index-1);
        }
        sentinel.back=temp;
        return value;
    }
}
