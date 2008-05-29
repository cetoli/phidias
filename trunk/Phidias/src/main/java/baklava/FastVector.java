package baklava;

class FastVector {
	Object array[];
	final int initial = 16;
	int used;
	FastVector() {
		array = new Object[initial];
		used = 0;
	}
	final void addElement(Object o) {
		extend();
		array[used++] = o;
	}
	final void extend() {
		if (used >= array.length) {
			int space = array.length;
			while (used >= space) {
				space <<= 1;
			}
			Object array2[] = new Object[space];
			int i;
			for (i = 0; (i < used); i++) {
				array2[i] = array[i];
			}		
			for (i = used; (i < array2.length); i++) {
				array2[i] = null;
			}		
			array = array2;
		}
	}
	final int size() {
		return used;
	}
	final void setSize(int s) {
		if (array.length < s) {
			array = new Object[s];
		}
		used = s;
	}
	final Object elementAt(int at) {
		return array[at];
	}
	final Object firstElement() {
		if (used > 0) {
			return array[0];
		} else {
			return null;
		}
	}
	final void removeElement() {
		removeElementAt(0);
	}	
	final void removeElement(Object o) {
		int i;
		for (i = 0; (i < used); i++) {
			if (array[i] == o) {
				removeElementAt(i);
				return;
			}
		}
	}	
	final void removeElementAt(int at) {
		int i;
		for (i = at; (i < (used - 1)); i++) { 
			array[i] = array[i + 1];
		}
		used--;	
	}
	final void insertElementAt(Object o, int at) {
		int i;
		extend();
		for (i = used; (i > at); i--) { 
			array[i] = array[i - 1];
		}
		array[at] = o;
		used++;	
	}
}

