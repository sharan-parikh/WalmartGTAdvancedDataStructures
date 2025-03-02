import javax.naming.OperationNotSupportedException;

public class Driver {

  public static void main(String args[]) {
    PowerOfTwoHeap<Integer> pq = new PowerOfTwoHeap<>(10);
    pq.insert(10);
    pq.insert(20);
    pq.insert(30);

    try {
      System.out.println(pq.popMax());
      System.out.println(pq.popMax());
      System.out.println(pq.popMax());
    } catch (OperationNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }
}
