package FillData;

import java.util.ArrayList;
import java.util.Random;

public class Data {

    private final ArrayList<Object> data;

    public Data(ArrayList<Object> data) {
        this.data = data;
    }

    public ArrayList<Object> getData() { return this.data; }

    public Object get(int i) { return this.data.get(i);}

    public Object getRandom() {
        return this.data.get((new Random().nextInt(this.data.size())));
    }

    public int size() { return this.data.size(); }
}
