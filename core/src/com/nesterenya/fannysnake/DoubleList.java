package com.nesterenya.fannysnake;

import java.util.Arrays;

/**
 * Специальная структура данных, для которой важна быстрая вставка, 
 * быстрое удаление, экономие памяти и быстрый произвольный доступ 
 * к элементам коллекции
 * 
 * Коллекция представляет собой массив, в котором повторно используются 
 * удаленные элементы. В случае нехватки места, выделяется новый массив, 
 * куда копируются элементы старого 
 * 
 * @author Igor Nesterenya
 * @version 1.0
 * 
 * @param <E>
 */
public class DoubleList<E>{

	/**
	 * Массив в котором хранятся элементы коллекции
	 */
	private transient Object[] elementData;
	
	/**
	 * Количество использованых элементов
	 */
	private int size;
	
	/**
	 * Указатель следующую свободную ячейку
	 */
	private int head = 0;
	
	/**
	 * Указатель на ячейку с мамым первым элементом 
	 */
	private int tail = 0;
	
	public DoubleList(int initialCapacity) {
		super();
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        this.elementData = new Object[initialCapacity];
	}
	
	public DoubleList() {
        this(10);
    }
	
	public int size() {
		return size;
	}
	
	/**
	 * Получение i-го элемента, относительно первого добавленного
	 * элемента 
	 * 
	 * @param index
	 * @return
	 */
	public E get(int index) {
	        rangeCheck(index);
	        int correctIndex;
	        
	        // Возможно несколько ситуаций для которых 
	        // по разному вычисляются индексы

	        // Если хвост меньше головы
	        // Значит индекс может быть вычислен линейно 
	        if(tail<head) {
	        	correctIndex = tail+index;
	        } else {
	        	// Иначе, были повторно использованы ячейки 
	        	// удаленных элементов и индекс высчитывается 
	        	// от хвоста до конца массива
	        	int tailAndIndex = tail+index;
	        	int lastIndex = elementData.length-1;
	        	// Если индекс попадает в пределах конца массива
	        	if(tailAndIndex<lastIndex) {
	        		correctIndex = tailAndIndex;
	        	} else {
	        		// Иначе, должно быть вычесленно оставшееся смещение относительно 
	        		// ночала массива
	        		correctIndex = tailAndIndex - lastIndex;
	        	}
	        }
	        return elementData(correctIndex);
	  }
	  
	/**
	 * Добавить элемент в конец
	 * @param element
	 */
	  public void addLast(E element) {
		  ensureCapacity(size+1);
		  elementData[head] = element;
		  nextHead();
		  size++;
	  }
	  
	  private void nextTail() {
		  tail++;
		  if(tail> (elementData.length-1)) {
			  tail=0;
		  }
	  }
	  
	  private void nextHead() {
		  // Проверка, не указывает ли указатель головы 
		  // на несуществующий элемент
		  if((head+1)>(elementData.length-1)) {
			  head=0;
		  } else {
			  head++;
		  }
	  }
	  
	  /**
	   * Удалить первый элемет (тот который само давно добавлен)
	   */
	  public void removeFirst() {
		  if(head!=tail) {
			  elementData[tail] = null;
			  nextTail();
			  size--;
		  } 
	  }
	
	  public E getLast() {
		  // Указатель указывает на следующую свободную ячейку
		  // поэтому нужно вернуть предудущую
		  if(head==0) {
			  return elementData(elementData.length-1);
		  } else {
			  return elementData(head-1);
		  }
	  }
	  
	  private void rangeCheck(int index) {
	     if (index >= size)
	        throw new IndexOutOfBoundsException("Out of boundary");
	  }
	
	  @SuppressWarnings("unchecked")
	  E elementData(int index) {
	     return (E) elementData[index];
	  }
	  
	  /**
	   * Гарантировать выделение памяти
	   * @param minCapacity
	   */
	  private void ensureCapacity(int minCapacity) {
	        int oldCapacity = elementData.length;

	        if (minCapacity > oldCapacity) {
	            int newCapacity = (oldCapacity * 3)/2 + 1;
	            if (newCapacity < minCapacity)
	                newCapacity = minCapacity;
	            // minCapacity is usually close to size, so this is a win:
	            
	            Object[] newArray = new Object[newCapacity];
	            // Скопировать в новый массив элементы от хвоста до головы
	            // Возможны 2 случая:
	            if(tail < head) {
	            	
	            	int j = 0;
	        		for(int i = tail; i < head; i++, j++) {
		            	newArray[j] = elementData[i];
		            }	        		
	        		head = j;
	        	} else {
	        		int j = 0;
		            for(int i = tail; i < elementData.length; i++, j++) {
		            	newArray[j] = elementData[i];
		            }
		            
		            for(int i = 0; i < head; i++, j++) {
		            	newArray[j] = elementData[i];
		            }
		            head = j;
	        	}
	            tail = 0;
	            elementData = newArray;
	        }
	    }
}
