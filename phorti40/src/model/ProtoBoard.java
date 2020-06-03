package model;

public abstract class ProtoBoard implements Cloneable{
    private Tile[][] board;

    public Object clone() {
        Object clone = null;

        try {
            clone = super.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;
    }

}
