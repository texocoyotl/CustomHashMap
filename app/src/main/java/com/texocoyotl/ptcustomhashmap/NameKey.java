package com.texocoyotl.ptcustomhashmap;

public class NameKey {
    private final String first, last;

    public NameKey(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof NameKey){
            NameKey n = (NameKey) o;
            return n.getFirst().equals(first) && n.getLast().equals(last);
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 92821;
        int result = prime + first.hashCode();
        result = prime * result + last.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "NameKey{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }
}
