

/**
 *
 * @author huongtran
 */
public interface BookStoreSpecification {
     /**
     * given a product id and a product quantity, update
     * stock by adding amount to existing product quantity
     * 
     * @param productID -> String since I already assigned product id to string type
     * @param amount
     * @return 
     */
    public int restockProduct(String productID, int amount);
    
    /**
     * calculate and return dollar amount for current inventory of products
     * that is in stock
     * 
     * @return 
     */
    public double inventoryValue();
    
}
