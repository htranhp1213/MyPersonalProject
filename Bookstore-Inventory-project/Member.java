

/**
 *
 * @author huongtran
 */
public class Member {
    private String name;
    private int numPurchases;
    private int id;
//    private static int count = 0;

    public Member(int id, String name, int numPurchases) {
        
        
        this.name = name;
        this.id = id;
        this.numPurchases = numPurchases;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    

    public int getNumPurchases() {
        return numPurchases;
    }

    public void setNumPurchases(int numPurchases) {
        this.numPurchases = numPurchases;
    }
    
    // a method to increment the number of purchases by a set amount
    public void incrementPurchases​(int numPurchases) {
        this.numPurchases += numPurchases;
    }



    @Override
    public String toString() {
        return "Member{" + "id:" + id  + ", name: " + name + ", numPurchases: " + numPurchases +'}';
    }

    
    
    
    
}
