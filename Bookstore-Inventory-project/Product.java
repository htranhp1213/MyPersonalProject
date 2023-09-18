

/**
 *
 * @author huongtran
 */
public abstract class Product implements Comparable<Product>  {
    private String id; 
    private String title;
    private double price;
    private int stock;

    public Product(String id, String title, double price, int stock) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    

    @Override
    public String toString() {
        return "{" + "id: " + id + ", title: " + title + ", price: " + price + ", stock: " + stock ;
    }
    
    @Override
    public int compareTo(Product pd) {      // compare product based on price
      if(this.stock == pd.getStock())       // if(this.price == pd.getPrice())
          return 0;                         //      return 0;
      else if(this.stock > pd.getStock())   //  else if(this.price > pd.getStock())
          return 1;                         //      return 1;
      else                                  //  else
          return -1;                        //      return -1;
    }
    
}
