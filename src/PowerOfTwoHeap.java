import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.OperationNotSupportedException;

public class PowerOfTwoHeap<T extends Comparable<T>> {

  private List<T> heap;

  private final int power;

  private final Integer branchingPower;

  public PowerOfTwoHeap(int power) {
    this.power = power;
    if(power > 31) {
      throw new IllegalArgumentException("Power value more than 31 is not supported");
    }
    this.branchingPower = 1 << power; // equivalent to 2^power
    heap = new ArrayList<>();
  }

  public Integer getKthChildIndex(int index, int k) {
    return (index << branchingPower) + k;
  }

  public Integer getParentIndex(int index) {
    return (index - 1) >> power;
  }

  public void insert(T key) {
    heap.add(key);
    int i = heap.size() - 1;
    int parentIndex = getParentIndex(i);
    while(i >= 1 && heap.get(parentIndex).compareTo(heap.get(i)) < 0) {
      Collections.swap(heap, parentIndex, i);
      i = parentIndex;
    }
  }

  private void maxHeapify(int index) {
    int largest = index;
    for(int i = 1; i <= branchingPower; i++) {
      int childIndex = getKthChildIndex(index, i);
      if(childIndex < heap.size() && heap.get(largest).compareTo(heap.get(childIndex)) < 0) {
        largest = childIndex;
      }
    }
    if(largest != index) {
      Collections.swap(heap, largest, index);
      maxHeapify(largest);
    }
  }

  public T popMax() throws OperationNotSupportedException {
    if(heap.size() == 0) {
      throw new OperationNotSupportedException("There are no elements in the priority queue!");
    }
    T maxKey = heap.get(0);
    int i = heap.size() - 1;
    heap.set(0, heap.get(i));
    heap.remove(i);
    if(!heap.isEmpty()) {
      maxHeapify(0);
    }
    return maxKey;
  }
}
