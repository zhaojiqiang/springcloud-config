package jianzhioffer;

import java.util.HashMap;
import java.util.Hashtable;

public class MyHashMap<K, V> {

	private double	f		= 0.75;
	private int		size	= 16;
	Entry<K, V>[]	entry;
	private int		count;

	public MyHashMap(double f, int size) {
		this.f = f;
		this.size = size;
		entry = new Entry[size];
	}

	public MyHashMap() {
		entry = new Entry[size];
	}

	public class Entry<K, V> {
		K		key;
		V		value;
		Entry	next;
		public Entry(K key, V value, Entry next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}

	}

	public void put(K key, V value) {
		int index = getIndex(key.hashCode());
		Entry e = entry[index];
		Entry pre = e;
		if (e == null) {
			entry[index] = new Entry<K, V>(key, value, null);
		} else {
			if (e.key.equals(key) && e.next == null) {
				entry[index] = new Entry<K, V>(key, value, e.next);
				return;
			}
			while (e != null) {
				if (e.key.equals(key)) {
					e = new Entry<K, V>(key, value, e.next);
					return;
				}
				pre = e;
				e = e.next;
			}
			pre.next = new Entry<K, V>(key, value, null);
		}
		count++;
		if (count > size * f) {
			kuorong();
		}

	}

	private void kuorong() {
		int sizeE = (int) (size * 1.5 / 1);
		size = sizeE;
		int oc = count;
		Entry[] old = entry;
		entry = new Entry[sizeE];
		for (int i = 0; i < old.length; i++) {
			Entry<K, V> oe = old[i];
			if (null == oe) {
				continue;
			}
			put(oe.key, oe.value);
			while (oe.next != null) {
				oe = oe.next;
				put(oe.key, oe.value);
			}
		}
		count = oc;
	}

	public V get(K key) {
		int index = getIndex(key.hashCode());
		Entry<K, V> e = entry[index];
		if (null == e) {
			return null;
		}

		if (e.key.equals(key)) {
			return e.value;
		} else {
			while (e != null) {
				e = e.next;
				if (e != null && e.key == key) {
					return e.value;
				}
			}
		}
		return null;
	}

	private int getIndex(int hashCode) {
		return Math.abs(hashCode % size);
	}

	public static void main(String[] args) {
		System.out.println( 16345&2);
		System.out.println(  16345%2);
	}

}
