

import java.util.ArrayList;

/**
 *
 * @author huongtran
 */
public class Bookstore implements BookStoreSpecification {
    private ArrayList<Book> bookList;
    private ArrayList<CD> CDlist;
    private ArrayList<DVD> DVDlist;
    private ArrayList<Member> members;
    private ArrayList<PremiumMember> premiumMembers;
    private static int nextIDavail = 0;
    
//    public static int getNextIDavail() {
//        return nextIDavail;
//    }
    
    // this is an independent method which is no need object to generate
//    public static void incrementNextIDavail() {
//        nextIDavail++;
//    }

    //constructor

    public Bookstore() {
        createInventory();
        generateMember();
    }
    
    
    // helper methods
    // helper method
    private void createInventory(){

        
        bookList = new ArrayList<>();
        Book b1 = new Book("B1", "The design of Everyday Things", 25.99, 10);
        Book b2 = new Book("B2", "A History of World in 6 Glasses", 35.58, 20);
        Book b3 = new Book("B3", "Java Data Structure & Algorithm", 89.97, 5);
        bookList.add(b1);
        bookList.add(b2);
        bookList.add(b3);
        
        CDlist = new ArrayList<>();
        CD c1 = new CD("C1", "folklore", "Taylor Swift", 13.31, 10);
        CD c2 = new CD("C2", "Always In Between", "Jess Glynne", 15.59, 20);
        CD c3 = new CD("C3", "+", "Ed Sheeran", 11.99, 15);
        CDlist.add(c1);
        CDlist.add(c2);
        CDlist.add(c3);
        
        DVDlist = new ArrayList<>();
        DVD vd1 = new DVD("D1", "Harry Porter", "Daniel Rafcliffe", 3.66, 10 );
        DVD vd2 = new DVD("D2", "Avengers: Endgame", "Marvel", 8.99, 20);
        DVD vd3 = new DVD("D3", "Jurassic World Dominion", "Chris Pratt", 7.89, 5);
        DVDlist.add(vd1);
        DVDlist.add(vd2);
        DVDlist.add(vd3);
        
    }//end of createIntventory
    
    private void generateMember(){
        members = new ArrayList<Member>();
        premiumMembers = new ArrayList<>();
    }// end of generateMember
    
    public void addNewMember(int id, String name, boolean premium, int numPurchases) {
//        int id = getNextIDavail();
        if (premium == false) {
            Member newMem = new Member(id, name, numPurchases);
            members.add(newMem);
        }
        
        else {
            boolean isFeePaid = false;
            PremiumMember newPremium = new PremiumMember(id, name, numPurchases, isFeePaid);
            premiumMembers.add(newPremium);
            
        }
//        incrementNextIDavail();
        
        
    }
    
    //getter
    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public ArrayList<CD> getCDlist() {
        return CDlist;
    }

    public ArrayList<DVD> getDVDlist() {
        return DVDlist;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public ArrayList<PremiumMember> getPremiumMembers() {
        return premiumMembers;
    }
    
    // update inventory - subtract 1 from current system currently 
    // allows customer to add items to cart one at a time
    public void decrementInventory(String id){
        for(Book item: bookList){
            if(item.getId().equals(id)){
                int stockDown = item.getStock();
                stockDown--;
                item.setStock(stockDown);
            }
        }
        
        for(CD item: CDlist){
            if(item.getId().equals(id)){
                int stockDown = item.getStock();
                stockDown--;
                item.setStock(stockDown);
            }
        }
        
        for(DVD item: DVDlist){
            if(item.getId().equals(id)){
                int stockDown = item.getStock();
                stockDown--;
                item.setStock(stockDown);
            }
        }
    }// end of decrement method
    
    //method that creates the cart entry for a selected product
    public String getCartItem(String id){
        String name = "";
        double price = 0.0;
        for(Book item : bookList){
            if(item.getId().equals(id)){ // .equals() is to compare 2 Strings
                name = item.getTitle();
                price = item.getPrice();
            }
                
            return name + price;
        }
        
        for(CD item: CDlist){
            if(item.getId().equals(id)){
                name = item.getTitle();
                price = item.getPrice();
            } 
        }
        
        for(DVD item: DVDlist){
           if(item.getId().equals(id)){
                name = item.getTitle();
                price = item.getPrice();
            } 
        }
        
        return name +"\t-\t"+price;
    } // end of getCartItem
    
    //method to check and display member profile
    public void displayMemberStatus(int memberID){
        for(Member mem: members) {
            if(mem.getId() == memberID){ // == to compare 2 
                mem.toString();
            }
        }
        
        
        for(PremiumMember premium: premiumMembers) {
            if(premium.getId() == memberID){
                premium.toString();
            }
        }
    }// end of display member status

    @Override
    public String toString() {
        return "Bookstore{" + "bookList=" + bookList 
                +  "\n CDlist=" + CDlist 
                + "\n DVDlist=" + DVDlist 
                + "\n members=" + members 
                + "\n premiumMembers=" + premiumMembers + '}';
    }

    @Override
    public int restockProduct(String productID, int amount) {
        
        //search in each product list for the product id
        for (int i = 0; i < bookList.size(); i++){
            Product p = bookList.get(i);
            
            if (p.getId().equals(productID)){
                p.setStock(p.getStock() + amount);
                
                System.out.println("Your new stock for " + p.getId()+ ". " + p.getTitle()+ " is: " + p.getStock());
                return p.getStock();
            }
        }
        
        for (int i = 0; i < CDlist.size(); i++){
            Product p = CDlist.get(i);
            
            if (p.getId().equals(productID)){
                p.setStock(p.getStock() + amount);
                
                System.out.println("Your new stock for " + p.getId()+ ". " + p.getTitle()+ " is: " + p.getStock());
                return p.getStock();
            }
        }
        
        for (int i = 0; i < DVDlist.size(); i++){
            Product p = DVDlist.get(i);
            
            if (p.getId().equals(productID)){
                p.setStock(p.getStock() + amount);
                
                System.out.println("Your new stock for " + p.getId()+ ". " + p.getTitle()+ " is: " + p.getStock());
                return p.getStock();
            }
        }
        return 1;
        
    }

    @Override
    public double inventoryValue() {
        double value = 0.0;
        for(int i =0; i< bookList.size(); i++){
            value += (bookList.get(i).getPrice())* (bookList.get(i).getStock());
        }
        for(int i =0; i<CDlist.size(); i++){
            value += (CDlist.get(i).getPrice())*(CDlist.get(i).getStock());
        }
        for(int i =0; i<DVDlist.size(); i++){
            value += (DVDlist.get(i).getPrice())*(DVDlist.get(i).getStock());
        }
        return value;
    }
    
    
    
    
}
