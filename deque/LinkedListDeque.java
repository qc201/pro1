package deque;

public class LinkedListDeque<T> {
    private MyNode dummy;
    private int size;

    // a single node item in the linked list
    public class MyNode {
        public T val;
        public MyNode prev;
        public MyNode next;
        public MyNode(T x, MyNode prevNode, MyNode nextNode) {
            val = x;
            prev = prevNode;
            next = nextNode;
        }
    }
    // construct a new empty linked list with dummy head
    // dummy.prev = tail
    // dummy.next = head
    public LinkedListDeque(T x) {
        dummy = new MyNode(x, null, null);
        MyNode newNode = new MyNode(x, dummy, dummy);
        dummy.next = newNode;
        dummy.prev = newNode;
        size = 1;
    }

    public LinkedListDeque() {
        dummy = new MyNode(null, null, null);
        dummy.prev = dummy;
        dummy.next = dummy;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other) {
        dummy = new MyNode(null, null, null);
        dummy.prev = dummy;
        dummy.next = dummy;
        size = 0;
        for (int i=0; i < other.size; i++) {
            addLast((T) other.get(i));
        }
    }


    public void addLast(T n) {
        MyNode prevNode = dummy.prev;
        MyNode newNode = new MyNode(n, prevNode, dummy);
        prevNode.next = newNode;
        dummy.prev = newNode;
        size += 1;
    }

    public void addFirst(T n) {
        MyNode nextNode = dummy.next;
        MyNode newNode = new MyNode(n, dummy, nextNode);
        dummy.next = newNode;
        nextNode.prev = newNode;
        size += 1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        MyNode curNode = dummy.next;
        dummy.next = curNode.next;
        curNode.next.prev = dummy;
        size -= 1;
        return curNode.val;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        MyNode lastNode = dummy.prev;
        lastNode.prev.next = dummy;
        dummy.prev = lastNode.prev;
        size -= 1;
        return lastNode.val;
    }

    public T get(int index) {
        MyNode pointer = dummy.next;
        int counter = 0;
        while (pointer != dummy && counter < index) {
            pointer = pointer.next;
            counter += 1;
        }
        if (counter < index) {
            return null;
        } else {
            return pointer.val;
        }
    }

    public T getRecursive(int index) {
        return get(index);
    }

    public void printDeque() {
        MyNode pointer = dummy.next;
        while (pointer != dummy) {
            System.out.print(pointer.val + " ");
            pointer = pointer.next;
        }
        System.out.println(" ");
    }




//    public static void main(String[] args) {
//        LinkedListDeque<Integer> myNum = new LinkedListDeque<Integer>();
//        myNum.addLast(0);
//        myNum.addLast(1);
//        myNum.addLast(2);
//        myNum.addLast(3);
//        myNum.addFirst(-1);
//        myNum.removeFirst();
//        myNum.printDeque();
//        LinkedListDeque newNums = new LinkedListDeque(myNum);
//        newNums.removeFirst();
//        newNums.printDeque();
//        System.out.println("original list:");
//        myNum.printDeque();
//    }

}
