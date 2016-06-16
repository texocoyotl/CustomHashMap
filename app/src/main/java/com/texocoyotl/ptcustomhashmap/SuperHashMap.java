package com.texocoyotl.ptcustomhashmap;


public class SuperHashMap <K, V> {

    private Entry<K,V>[] table;   //Array of Entry.
    private int capacity= 8;  //Initial capacity of HashMap

    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    @SuppressWarnings("unchecked")
    public SuperHashMap(){
        table = new Entry[capacity];
    }

    public void put(K newKey, V data){
        if(newKey==null)
            return;    //does not allow to store null.

        //calculate hash of key.
        int hash = hash(newKey);
        System.out.println("Index used for " + newKey + " : " + hash);
        
        //create new entry.
        Entry<K,V> newEntry = new Entry<K,V>(newKey, data, null);

        //if table location does not contain any entry, store entry there.
        if(table[hash] == null){
            table[hash] = newEntry;
        }else{
            Entry<K,V> previous = null;
            Entry<K,V> current = table[hash];

            while(current != null){ //we have reached last entry of bucket.
                if(current.key.equals(newKey)){
                    if(previous==null){  //node has to be insert on first of bucket.
                        newEntry.next=current.next;
                        table[hash]=newEntry;
                        return;
                    }
                    else{
                        newEntry.next=current.next;
                        previous.next=newEntry;
                        return;
                    }
                }
                previous=current;
                current = current.next;
            }
            previous.next = newEntry;
        }
    }

    public V get(K key){
        int hash = hash(key);
        if(table[hash] == null){
            return null;
        }else{
            Entry<K,V> temp = table[hash];
            while(temp!= null){
                if(temp.key.equals(key))
                    return temp.value;
                temp = temp.next; //return value corresponding to key.
            }
            return null;   //returns null if key is not found.
        }
    }

    public boolean remove(K deleteKey){

        int hash=hash(deleteKey);

        if(table[hash] == null){
            return false;
        }else{
            Entry<K,V> previous = null;
            Entry<K,V> current = table[hash];

            while(current != null){ //we have reached last entry node of bucket.
                if(current.key.equals(deleteKey)){
                    if(previous==null){  //delete first entry node.
                        table[hash]=table[hash].next;
                        return true;
                    }
                    else{
                        previous.next=current.next;
                        return true;
                    }
                }
                previous=current;
                current = current.next;
            }
            return false;
        }

    }

    public void display() {

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                Entry<K, V> entry = table[i];
                while (entry != null) {
                    System.out.print("{" + entry.key + "=" + entry.value + "}" + " ");
                    entry = entry.next;
                }
            }
        }

    }

    static int indexFor(int h, int length) {
        return h & (length-1);
    }


    private int hash(K key){
        // return Math.abs(key.hashCode()) % capacity;
        return indexFor(key.hashCode(), capacity);
    }

    public static void main(String[] args) {
        SuperHashMap<NameKey, Integer> hashMapCustom = new SuperHashMap<NameKey, Integer>();

        NameKey a = new NameKey("Benjamin", "Molina");
        NameKey b = new NameKey("John", "Doe");
        NameKey c = new NameKey("Donald", "Trump");
        NameKey d = new NameKey("Hilary", "Clinton");

        hashMapCustom.put(a, 12);
        hashMapCustom.put(c, 151);
        hashMapCustom.put(d, 15);

        System.out.println("value corresponding to Benjamin = "
                + hashMapCustom.get(a));
        System.out.println("value corresponding to John = "
                + hashMapCustom.get(b));

        System.out.print("Displaying : ");
        hashMapCustom.display();

        System.out.println("\n\nvalue corresponding to Donald Trump removed: "
                + hashMapCustom.remove(c));
        System.out.println("value corresponding to John removed: "
                + hashMapCustom.remove(b));

        System.out.print("Displaying : ");
        hashMapCustom.display();



    }


}
