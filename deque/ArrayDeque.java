package deque;


public class ArrayDeque<T> {
    public T[] items;
    public int size;
    public int listLength;
    public int start;
    public int end;
    public int firstPartS;
    public int secondPartE;

    // reached capacity
    private void resize(int headS, int headE, int tailS, int tailE, int count, int curLength) {
            T[] tempArray = (T[]) new Object[curLength*2];
            System.arraycopy(items, tailS+1, tempArray, 0, tailE-tailS);
            System.arraycopy(items, headS, tempArray, tailE-tailS, headE-headS);
            items = tempArray;
            start = curLength*2-1;
            end = count;
            size = count;
            listLength = curLength*2;
            firstPartS = 0;
            secondPartE = curLength*2-1;
    }
    private void downSize(int headS, int headE, int tailS, int tailE, int count, int curLength) {
        T[] tempArray = (T[]) new Object[count*4];
        System.arraycopy(items, tailS+1, tempArray, 0, tailE-tailS);
        System.arraycopy(items, headS, tempArray, tailE-tailS, headE-headS);
        items = tempArray;
        start = count*4-1;
        end = count;
        size = count;
        listLength = count*4;
        firstPartS = 0;
        secondPartE = count*4-1;
    }

    public ArrayDeque(T x) {
        items = (T[]) new Object[8];
        items[0] = x;
        size = 1;
        start = 7;
        end = 1;
        listLength = 8;
        firstPartS = 0;
        secondPartE = 7;
    }

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        start = 7;
        end = 0;
        listLength = 8;
        firstPartS = 0;
        secondPartE = 7;
    }

    public ArrayDeque(ArrayDeque other) {
        T[] temp = (T[]) new Object[other.listLength];
        System.arraycopy(other.items, 0, temp, 0, other.listLength);
        items = temp;
        start = other.start;
        end = other.end;
        listLength = other.listLength;
        size = other.size;
        firstPartS = other.firstPartS;
        secondPartE = other.secondPartE;
    }


    public void addLast(T n) {
        if (size+1 == listLength) {
            resize(firstPartS, end, start, secondPartE, size, listLength);
        }
        items[end] = n;
        size += 1;
        end += 1;
    }

    public void addFirst(T n) {
        if (size+1 == listLength) {
            resize(firstPartS, end, start, secondPartE, size, listLength);
        }
        items[start] = n;
        start -= 1;
        size += 1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T removeFirst() {
        if (size <= 0) {
            return null;
        }
        if (listLength >= 16 && (float) (size-1)/listLength < 0.25) {
            downSize(firstPartS, end, start, secondPartE, size, listLength);
        }
        if (start >= listLength-1) {
            T firstItem = items[firstPartS];
            items[firstPartS] = null;
            firstPartS += 1;
            size -= 1;
            return firstItem;
        } else {
            T firstItem = items[start+1];
            items[start+1] = null;
            start += 1;
            size -= 1;
            return firstItem;
        }
    }

    public T removeLast() {
        if (size <= 0) {
            return null;
        }
        if (listLength >= 16 && (float) (size-1)/listLength < 0.25) {
            downSize(firstPartS, end, start, secondPartE, size, listLength);
        }
        if (end == firstPartS) {
            T lastItem = items[secondPartE];
            items[secondPartE] = null;
            secondPartE -= 1;
            size -= 1;
            return lastItem;
        } else {
            T lastItem = items[end-1];
            items[end-1] = null;
            end -= 1;
            size -= 1;
            return lastItem;
        }
    }

    public T get(int index) {
        if (index < listLength && index >= 0) {
            return items[index];
        }
        return null;
    }

    public T getRecursive(int index) {
        return get(index);
    }

    public void printDeque() {
        for (int i = start; i <= secondPartE; i++) {
            if (items[i] != null) {
                System.out.print(items[i] + " ");
            }
        }
        for (int j = firstPartS; j < end; j++) {
            if (items[j] != null) {
                System.out.print(items[j]+ " ");
            }
        }
        System.out.println(" ");
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> L = new ArrayDeque<>(3);
        L.addFirst(0);
        L.addFirst(9);
        L.addLast(7);
        L.addLast(6);
        L.addFirst(3);
        L.resize(L.firstPartS, L.end, L.start, L.secondPartE, L.size, L.listLength);
        L.removeFirst();
        L.removeFirst();
        L.removeFirst();
        L.removeFirst();
        L.removeFirst();
        L.addFirst(6);
        L.removeLast();
        L.removeLast();
        L.addFirst(7);
        L.addLast(9);
        L.resize(L.firstPartS, L.end, L.start, L.secondPartE, L.size, L.listLength);
        L.addFirst(4);
        L.removeFirst();
        L.addFirst(3);
        L.downSize(L.firstPartS, L.end, L.start, L.secondPartE, L.size, L.listLength);
        L.printDeque();
        ArrayDeque<Integer> L2 = new ArrayDeque(L);
        L2.removeLast();
        L2.removeLast();
        L2.removeLast();
        L2.removeLast();
        L2.printDeque();
        L.printDeque();
    }
}