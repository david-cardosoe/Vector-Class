import java.util.Arrays;
import java.util.Iterator;

/**
 *  Program #5
 *  This program is about creating a vector class that
 *  acts as a vector, similar to a list with methods like insert,
 *  erase, and etc. 
 *  CS160-3
 *  4/06/2022
 *  @author  David Cardoso
 */

public class Vector<E> extends AbstractListADT<E> implements Iterable<E> {
	
	//Here I create the fields we will need for this class that we will
	//update depending on the method used.
	protected Object[] array;
	protected int capacity;
	
	
	/*
	 * Here we have our 3 constructors 
	 * The first one takes in no parameters and assigns the default values
	 * The second takes in a parameter that specifies the capacity
	 * The third takes in another vector as a parameter and copies it. 
	 */
	@SuppressWarnings("unchecked")
    public Vector() {
        array = (E[]) new Object[10]; 
        size = 0;
        capacity = DEFAULT_CAPACITY;
    }
	
	@SuppressWarnings("unchecked")
    public Vector(int inCap) {
        array = (E[]) new Object[inCap]; 
        size = 0;
        capacity = inCap;
    }
	
	@SuppressWarnings("unchecked")
    public Vector(Vector<E> v) {
        array = (E[]) new Object[v.capacity]; 
        size = v.size;
        capacity = v.capacity;
        
        for(int i = 0; i < v.size; i++) {
        	this.array[i] = v.at(i);
        }
    }
	
	
	//This method is meant to just return our capacity field.
	@Override
	public int capacity() {
		return this.capacity;
	}
	
	
	//This method grabs the first element of the vector, the start or front of it
	@SuppressWarnings("unchecked")
	@Override
	public E front() {
		// TODO Auto-generated method stub
		return (E) array[0];
	}
	
	
	//returns last element of vector, the back of it
	@SuppressWarnings("unchecked")
	@Override
	public E back() {
		return (E) array[size - 1];
	}
	
	
	//returns the raw data of our vector
	@SuppressWarnings("unchecked")
	@Override
	public E[] data() {
		// TODO Auto-generated method stub
		return (E[]) this.array;
	}
	
	
	//Adds an element to the back of our vector, this is usually called add but 
	// in our case we have called it pushback
	//The method works depending on 3 scenerios, if adding a new element does not exceed the capacity
	//then it will simply add the element but if it does, then the capacity is increased by * 2
	// From there a temp object is created and the new element is added.
	@SuppressWarnings("unchecked")
	@Override
	public void pushback(E element) {
		// TODO Auto-generated method stub
		
		if(array[0] == null) {
			E[] temp = (E[]) new Object[array.length];
			temp[0] = element;
			size++;
			array = temp;
		}
		else if(array[0] != null && size < capacity) {
			E[] temp = (E[]) new Object[array.length];
			for(int i = 0; i < size; i++) {
				temp[i] = (E) array[i];
			}
			temp[size] = element;
			size++;
			array = temp;
		}
		else if(array[0] != null && size >= capacity) {
			capacity = array.length * 2;
			E[] temp = (E[]) new Object[capacity];
			for(int i = 0; i < size; i++) {
				temp[i] = (E) array[i];
			}
			temp[size] = element;
			size++;
			array = temp;
		}
	}

	
	/*
	 * popback returns the last element of the array.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E popback() {
		// TODO Auto-generated method stub
		if(array.length > 0) {
			return (E) array[--size];
		}
		return null;
	}
	
	
	/*
	 * insert will insert an element given depending on the position given
	 * We have two situations that could take place, if the size == array.length then 
	 * the insert method will increase the capacity by * 2 before inserting the element with a
	 * for loop
	 * If that is not the case then we simply insert the element using a for loop,
	 * the for loop loops through the array until the insertPosition, stops and 
	 * inserts the element, and continues on after the position to copy the rest of the 
	 * elements into a temp array
	 * At the end we make sure to set our array to the temp array and also
	 * update the size and capacity
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void insert(int insertPosition, E element) {
		// TODO Auto-generated method stub
		
		E[] temp;

		if(size == array.length) {
			temp = (E[]) new Object[array.length * 2];
			
			for(int i = 0; i < insertPosition; i++) {
				temp[i] = (E) array[i];
			}
			
			temp[insertPosition] = element;
			
			for(int i = insertPosition + 1; i < array.length + 1; i++) {
				temp[i] = (E) array[i - 1];
			}
			size++;
			array = temp;
			this.capacity = temp.length;
		}
		else {
			temp = (E[]) new Object[array.length];
			
			for(int i = 0; i < insertPosition; i++) {
				temp[i] = (E) array[i];
			}
			
			temp[insertPosition] = element;
			
			for(int i = insertPosition + 1; i < array.length; i++) {
				temp[i] = (E) array[i - 1];
			}
			size++;
			array = temp;
			this.capacity = temp.length;
		}
		
		
		
	}
	
	
	/*
	 * erase works like the insert method but instead of the break adding a new element
	 * this time we completly skip over it and add the rest to the temp array we created
	 * at the end again we set our array = temp and update the size;
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void erase(int position) {
		// TODO Auto-generated method stub
		E[] temp = (E[]) new Object[array.length];
		
		for(int i = 0; i < position - 1; i++) {
			temp[i] = (E) array[i];
		}
		for(int i = position + 1; i < temp.length; i++) {
			temp[i - 1] = (E) array[i];
		}
		
		array = temp;
		size = size - 1;
	}
	
	
	/*
	 * This erase method works exactly like the prevoius one expect that the break between our
	 * for loops is a range rather than a single break in the array.
	 * we copy the elements into our temp array the same way we did in the other erase method.
	 * Again we update our array and size.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void erase(int startRangePosition, int endRangePosition) {
		// TODO Auto-generated method stub
		E[] temp = (E[]) new Object[array.length];
		int elementsRemoved = endRangePosition - startRangePosition;
		
		for(int i = 0; i < startRangePosition; i++) {
			temp[i] = (E) array[i];
		}
		
		for(int i = endRangePosition; i < temp.length; i++) {
			temp[i - elementsRemoved] = (E) array[i];
		}
		
		size = size - elementsRemoved;
		this.array = temp;
	}
	
	
	/*
	 * The swap method creates a temp array to store the other vector we recieve.
	 * From here we can simply copy our this.array to the other vector.
	 * Now that our this.array and other are the same we set our this.array to the temp vector
	 * using for loops to copy the data, size, and capacity
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void swap(Vector<E> other) {
		// TODO Auto-generated method stub
		int otherSize = other.size;
		int otherCap = other.capacity;
		
		E[] temp = (E[]) new Object[otherCap];
		
		for(int i = 0; i < otherSize; i++) {
			temp[i] = other.at(i);
		}
		
		
		other.capacity = this.capacity;
		for(int i = 0; i < this.size; i++) {
			other.pushback((E) array[i]);
		}
		
		this.capacity = otherCap;
		this.size = otherSize;
		for(int i = 0; i < this.size; i++) {
			array[i] = temp[i];
		}
		
	}
	
	
	/*
	 * ShrinkToFit shrinks the capacity to the size of our vector by creating a new temp vector with
	 * it's capacity of our size and setting it equal to our this.array
	 * After that we make sure to update out capacity
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void shrinkToFit() {
		// TODO Auto-generated method stub
		E[] temp = (E[]) new Object[this.size];
		
		for(int i = 0; i < this.size; i++) {
			temp[i] = (E) this.array[i];
		}
		
		this.array = temp;
		this.capacity = size;
	}
	
	
	/*
	 * resize simply sets the capacity to the newSize we are given
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void resize(int newSize) {
		E[] temp = (E[]) new Object[newSize];
		
		for(int i = 0; i < newSize; i++) {
			temp[i] = (E) this.array[i];
		}
		
		this.array = temp;
		this.capacity = newSize;
		size = newSize;
	}
	
	/*
	 * This returns an iterator that has two methods
	 * First is the hasNext which just checks if the vector has another element.
	 * The second is the next, which grabs the next data value, uses hasNext() to make sure
	 * there exists a next value.
	 */
	@Override
	public Iterator<E> begin() {
		// TODO Auto-generated method stub
		
		return new Iterator<E>() {
			
			int index = 0;

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return index < size;
			}

			@SuppressWarnings("unchecked")
			@Override
			public E next() {
				// TODO Auto-generated method stub
				if (hasNext())
	            {
	                Object value = array[index];
	                index++;
	                return (E) value;
	            }
				else {
					return null;
				}
			}
			
		};
	}
	
	
	
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<E>() {
			
			int index = 0;

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return index < size;
			}

			@SuppressWarnings("unchecked")
			@Override
			public E next() {
				// TODO Auto-generated method stub
				if (hasNext())
	            {
	                Object value = array[index];
	                index++;
	                return (E) value;
	            }
				else {
					return null;
				}
			}
			
		};
	}
	
	
	//returns the data held at the index given
	@SuppressWarnings("unchecked")
	@Override
	public E at(int index) {
		// TODO Auto-generated method stub
		return (E) array[index];
	}

	/*
	 * This is very similar to by begin iterator.
	 * The difference is my index now starts with my size.
	 * The hasNext method returns a boolean, if the index is bigger or equal to 
	 * 0 then it returns true
	 * as you can see I did index-1 since in an array starts its count with 0.
	 * The next method works buy using hasNext to check if a next value exists and then
	 * grabbing the value at the index and subracting 1 from the index to move onto 
	 * the next value, finally returning the value.
	 */
	public Iterator<E> rbegin() {
		return new Iterator<E>() {
			int index = size;

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				if(index - 1 >= 0) {
					return true;
				}
				else {
					return false;
				}
			}

			@SuppressWarnings("unchecked")
			@Override
			public E next() {
				// TODO Auto-generated method stub
				if (hasNext())
	            {
	                Object value = array[index - 1];
	                index--;
	                return (E) value;
	            }
				else {
					return null;
				}
			}
		};
	}
	
	
	/*
	 * My reserve method works by just checking if the reserveSize is bigger than my capacity.
	 * If it is bigger than it will resize the vector to be the capacity of the reserveSize, if
	 * not bigger than the capacity is left alone.
	 */
	@SuppressWarnings("unchecked")
	public void reserve(int reserveSize) {
		// TODO Auto-generated method stub
		
		if(reserveSize > this.capacity) {
			E[] temp = (E[]) new Object[reserveSize];
			
			for(int i = 0; i < array.length; i++) {
				temp[i] = (E) this.array[i];
			}
			
			this.array = temp;
			this.capacity = reserveSize;
		}
		
	}
	
	
}
