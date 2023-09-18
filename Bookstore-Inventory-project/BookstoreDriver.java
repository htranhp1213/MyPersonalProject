
/**
 *
 * @author huongtran
 */
public class BookstoreDriver  {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Test the interface
        Bookstore ht = new Bookstore();
        System.out.println("Before restock");
        System.out.println(ht.getBookList());
        System.out.println();
        System.out.println(ht.getCDlist());
        System.out.println();
        System.out.println(ht.getDVDlist());
        System.out.println();
        System.out.println("After restock");
        ht.restockProduct("B1", 10);
        ht.restockProduct("C2", 3);
        ht.restockProduct("D3", 6);
        System.out.println(ht.getBookList());
        System.out.println();
        System.out.println(ht.getCDlist());
        System.out.println();
        System.out.println(ht.getDVDlist());
        System.out.println();
        System.out.println("Get value of inventory");
        System.out.println("$"+ht.inventoryValue());
    }
    
    
    
}
