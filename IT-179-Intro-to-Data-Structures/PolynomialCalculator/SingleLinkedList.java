package PolynomialCalculator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleLinkedList<T> implements Iterable<T>
{
	//node for each element in the list
	private static class Node<T>
	{
		T data;
		Node<T> next;

		Node(T data)
		{
			this.data = data;
		}
	}

	//variables
	private Node<T> head;
	private int size;

	//adds an item to the end of the list
	public void add(T item)
	{
		Node<T> newNode = new Node<>(item);

		if (head == null)
		{
			head = newNode;
		}
		else
		{
			Node<T> curr = head;
			while (curr.next != null)
			{
				curr = curr.next;
			}
			curr.next = newNode;
		}
		size++;
	}

	//gets the item at index
	public T get(int index)
	{
		checkIndex(index);

		Node<T> curr = head;
		for (int x = 0; x < index; x++)
		{
			curr = curr.next;
		}
		return curr.data;
	}

	//removes and returns the item at index
	public T remove(int index)
	{
		checkIndex(index);

		if (index == 0)
		{
			T data = head.data;
			head = head.next;
			size--;
			return data;
		}

		Node<T> prev = head;
		for (int x = 0; x < index - 1; x++)
		{
			prev = prev.next;
		}

		T data = prev.next.data;
		prev.next = prev.next.next;
		size--;
		return data;
	}

	//returns the number of items in the list
	public int size()
	{
		return size;
	}

	private void checkIndex(int index)
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
	}

	public Iterator<T> iterator()
	{
		return new SingleLinkedListIterator();
	}

	private class SingleLinkedListIterator implements Iterator<T>
	{
		private Node<T> nextNode = head;
		private Node<T> lastReturned = null;
		private Node<T> beforeLastReturned = null;

		public boolean hasNext()
		{
			return nextNode != null;
		}

		public T next()
		{
			if (!hasNext())
			{
				throw new NoSuchElementException();
			}

			beforeLastReturned = lastReturned;
			lastReturned = nextNode;
			nextNode = nextNode.next;
			return lastReturned.data;
		}

		//removes the last item returned by next()
		public void remove()
		{
			if (lastReturned == null)
			{
				throw new IllegalStateException();
			}

			if (beforeLastReturned == null)
			{
				head = nextNode;
			}
			else
			{
				beforeLastReturned.next = nextNode;
			}

			lastReturned = null;
			size--;
		}
	}
}
