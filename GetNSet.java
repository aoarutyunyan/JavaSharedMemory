import java.util.concurrent.atomic.AtomicIntegerArray;

class GetNSet implements State {

    private byte maxval;
    private AtomicIntegerArray atomicArray;


    private void createArray(byte[] v) {
	int [] arr = new int[v.length];
	for(int i = 0; i < v.length; i++) {
	    arr[i] = v[i];
	}
	atomicArray = new AtomicIntegerArray(arr);
    }

    GetNSet(byte[] v) {
	maxval = 127;
	createArray(v);
    }

    GetNSet(byte[] v, byte m) {
	maxval = m;
	createArray(v);
    }

    public int size() {return atomicArray.length();}

    public byte[] current() {
	byte[] cur = new byte[size()];
	for(int i = 0; i < size(); i++) {
	    cur[i] = (byte) atomicArray.get(i);
	}
	return cur;
    }

    public boolean swap(int i, int j) {
	if (atomicArray.get(i) <= 0 || atomicArray.get(j) >= maxval) 
	    return false;

	atomicArray.getAndDecrement(i);
	atomicArray.getAndIncrement(j);
	return true;
    }

}
