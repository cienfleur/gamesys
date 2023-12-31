package base.gamesys.utils;

public class ScratchHash {

    // implement generic linear probing hash table

    public int size=0;
    public int capacity=10;
    public Object[] keys=new Object[capacity];
    public Object[] values=new Object[capacity];

public void put(Object key, Object value) {
        int index=key.hashCode()%capacity;
        if (keys[index]==null) {
            keys[index]=key;
            values[index]=value;
            size++;
        }
        else {
            while (keys[index]!=null) {
                index++;
                if (index==capacity) {
                    index=0;
                }
            }
            keys[index]=key;
            values[index]=value;
            size++;
        }
    }
}
