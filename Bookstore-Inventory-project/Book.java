

/**
 *
 * @author huongtran
 */
public class Book extends Product{

    
    
    //constructor
    public Book(String id, String title, double price, int stock) {
        super(id, title, price, stock);
        
    }

    @Override
    public String toString() {
        return "Book" + super.toString() + '}';
    }

   


    
    
    
    
    
}
