

import java.util.Scanner;
import java.util.*;

/**
 *
 * @author huongtran
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Bookstore bookstore = new Bookstore();
        ArrayList<String> purchasesList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean open = true;
        
        while(open) {
            System.out.println("********** Welcome to Garden of Books **********");
            System.out.println();
            System.out.println("What can we help you today?");
            System.out.println();
            System.out.println("\t 1. Make a Purchase");
            System.out.println("\t 2. Register a New Member");
            System.out.println("\t 3. Check Membership Status");
            System.out.println("\t 4. Exit");

            int num = sc.nextInt();
            
            switch(num){
                case 1: // make a purchase
                    System.out.println("Thank you for choosing to buy a product.");
                    purchase(bookstore, sc, purchasesList);
                    
                    break;
                case 2: // register a new member
                    int numPurchase = purchasesList.size();
                    System.out.println("Thank you for being one of our members");
                    registerNewMember(bookstore, sc, purchasesList.size()); 
                    String input = sc.nextLine();
                    
                    boolean isMember = registerNewMember(bookstore, sc, purchasesList.size()); 
                    if (isMember == true) {
                        System.out.println("You are ready to get some discount");
                                
                    }
                    break;

                case 3: // check membership status
                    System.out.println("Check your status");
                    checkMemberstatus(bookstore, sc);
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        }
        
    } // end of main method
        
    
    public static boolean registerNewMember(Bookstore bookstore, Scanner sc, int numPurchases) {
        
        Scanner scanner = new Scanner(System.in);
        numPurchases = 0;
        boolean isPremium;
        System.out.println("Do you want to register for a Premium Membership? y/n");
        String input = sc.nextLine();
        if (input.equals("y")) { 
            isPremium = true;
            
            System.out.println("What's your phone number?");
            sc.nextLine();
            int id = sc.nextInt();
            
            System.out.println("Please enter your name: ");
            sc.nextLine();
            String name = sc.nextLine();
            
            System.out.println("There is a $10 monthly fee you must pay on the 10th every month.");
            System.out.println("Please enter a payment method.");
            
            bookstore.addNewMember(id, name, isPremium, numPurchases);
            
        } // end of register premium
        
        else {
            isPremium = false;
            sc.nextLine();
            System.out.println("What's your phone number?");
            int id = sc.nextInt();
            
            System.out.println("Please enter your name: ");
            sc.nextLine();
            String name = sc.nextLine();
            bookstore.addNewMember(id, name, isPremium, numPurchases);
        }
        return true;
    } // end of registerNewMember
    
    
    public static void purchase(Bookstore bookstore, Scanner sc, ArrayList<String> purchases) {
        /*print the menu - loop through the arraylists and call a tostring method on each item
        ask the user for the title of the item they want
        loop through the list of items to find the item with that title
        remove that item from the arraylist*/
        
        // print the menu - what bookstore has
        
        
        ArrayList<Book> bookInventory = bookstore.getBookList();
        ArrayList<CD> cdInventory = bookstore.getCDlist();
        ArrayList<DVD> dvdInventory = bookstore.getDVDlist();
        
        // enhaced loop print out each book object in a line
        System.out.println("Book Inventory");
        for (Book b: bookInventory) {
            System.out.println(b);
        }
        
        System.out.println();
        System.out.println();
        // enhaced loop print out each cd object in a line
        System.out.println("CD Inventory");
        for (CD cd: cdInventory) {
            System.out.println(cd);
        }
        
        System.out.println();
        System.out.println();
        // enhaced loop print out each dvd object in a line
        System.out.println("DVD Inventory");
        for (DVD dv: dvdInventory) {
            System.out.println(dv);   
        }
        
        
        
        // ask customer for the name of product they want
        System.out.println("What item do you want to add?");
        sc.nextLine();
        String productName = sc.nextLine();
        for(Book item : bookInventory) {
            if (productName.equals(item.getTitle())){
                purchases.add(bookstore.getCartItem(item.getId()));
                bookstore.decrementInventory(item.getId());
                System.out.println("You got " + purchases.size() + " item(s) in your cart. Are you done shopping?");
                System.out.println("\t 1. Yes");
                System.out.println("\t 2. No");
                int doneShopping = sc.nextInt();
                if (doneShopping == 1) {

                        checkout(bookstore, sc, purchases);

                }

                else if (doneShopping == 2) {
                        purchase(bookstore, sc, purchases);
                }

                else {
                        System.out.println("Try Again");
                }
            }
            
        }
        
        for(CD item : cdInventory) {
            if (productName.equals(item.getTitle())){
                purchases.add(bookstore.getCartItem(item.getId()));
                bookstore.decrementInventory(item.getId());
                
                System.out.println("You got " + purchases.size() + " item(s) in your cart. Are you done shopping?");
                System.out.println("\t 1. Yes");
                System.out.println("\t 2. No");
                int doneShopping = sc.nextInt();
                if (doneShopping == 1) {

                        checkout(bookstore, sc, purchases);

                }

                else if (doneShopping == 2) {
                        purchase(bookstore, sc, purchases);
                }

                else {
                        System.out.println("Try Again");
                }
               
            }
        }
        
        for(DVD item : dvdInventory) {
            if (productName.equals(item.getTitle())){
                purchases.add(bookstore.getCartItem(item.getId()));
                bookstore.decrementInventory(item.getId());
                
                System.out.println("You got " + purchases.size() + " item(s) in your cart. Are you done shopping?");
                System.out.println("\t 1. Yes");
                System.out.println("\t 2. No");
                int doneShopping = sc.nextInt();
                if (doneShopping == 1) {

                        checkout(bookstore, sc, purchases);

                }

                else if (doneShopping == 2) {
                        purchase(bookstore, sc, purchases);
                }

                else {
                        System.out.println("Try Again");
                }
                
                
            }
        }
        

    

    } // end of purchase method
    
    
    private static void checkout(Bookstore bookstore, Scanner sc, ArrayList<String> purchases){
        // print out the list of items purchased
        for(String purchase: purchases) {
            System.out.println(purchase);
        }
         // ask if customer has membership -> check membership -> to get 10% off
         //get total purchases and apply 10% discount => total cost
         
        double cost = getTotalPurchases(purchases, bookstore);
        double discounted = 0.0;
        System.out.println("Do you have a Membership? (Y/N)");
        
        String checkMem = sc.nextLine();
        if ( checkMem.equals("Y") || checkMem.equals("y")) {
            System.out.println("What is your member ID?");
            int id = sc.nextInt();
            for (PremiumMember pre: bookstore.getPremiumMembers()){
                if(pre.getId() == id){
                    discounted = 0.1;
                    cost = cost-cost*discounted; //(?? cost -= cost*discounted ??)
                    System.out.println(cost);
                }
            }
            for (Member mem: bookstore.getMembers()){
                if(mem.getId() == id){
                    discounted = 0.01;
                    cost = cost- cost*discounted;
                    System.out.println(cost);
                }
            }              
        }// end of if condition checking for discount
        
        else{
            System.out.println(cost);
        }
        
        System.out.println("Do you want to pay by card or cash?");
        System.out.println("\t 1. Card");
        System.out.println("\t 2. Cash");
        int payment = sc.nextInt();
        if(payment == 1){
            System.out.println("Please enter a 12 digit card number.");
            String card = sc.nextLine();
            if (card.length() == 12) {
                System.out.println("Transaction is successful! Thank you for shoppoing at Garden of Books!!!");

            } else {
                System.out.println("Transaction is failed. Please try again.");
            }
        } // end of card paid
        else {
            double cashPaid = sc.nextDouble();
            System.out.println("Please enter an amount");
                
            if (cashPaid > cost) {
                double change = cashPaid - cost;
                System.out.println("Your change is $" + change + ". Thank you for shoppoing at Garden of Books!!!");
                
            }
            if (cashPaid < cost) {
                System.out.println("Error, the amount entered is not enough. Please try again");
            }
            if (cashPaid == cost) {
                System.out.println("Transaction is succesful! Thank you for shoppoing at Garden of Books!!!");
                
            }
        }// end of cash paid
        
        System.out.println("Do you want a receipt?");
        String receipt = sc.nextLine();
        if(receipt.equals("Y") || receipt.equals("y")) {
            for(String purchase: purchases) {
                System.out.println(purchase);
            }
            System.out.println(cost);
        } // end of print receipt
        
        
        
        
        
         
         
         
    }// end of checkout method
    
    private static double getTotalPurchases(ArrayList<String> purchases, Bookstore bookstore) {
        
        
        // calculate total cost
        double total = 0.0;
        //make a double variable, i'm calling it x
        //use a for each loop like above and call the getPrice() method on each one
        //x += (forLoopVariable).getPrice();
        
        for (int i =0; i< purchases.size(); i++){
            for (Book item: bookstore.getBookList()){
                if(item.getTitle().equals(purchases.get(i))){
                    total+=item.getPrice();
                }
            }
            
            for(CD item: bookstore.getCDlist()){
                if (item.getTitle().equals(purchases.get(i))){
                    total+=item.getPrice();
                }
            }
            
            for(DVD item: bookstore.getDVDlist()){
                if(item.getTitle().equals(purchases.get(i))){
                    total+=item.getPrice();
                }
            }
        }
        
        
        
        return total;
    } // end of getTotalPurchases
    
    private static void checkMemberstatus(Bookstore bookstore, Scanner sc){
        ArrayList<Member> members = bookstore.getMembers();
        ArrayList<PremiumMember> premiumMembers = bookstore.getPremiumMembers();
        
        System.out.println("What's your member's id?");
        int id = sc.nextInt();
        for(PremiumMember prem: premiumMembers){
            if(id == prem.getId()){
                if(prem.isIsFeePaid() == true){
                    System.out.println("You're ready to checkout with 10% discount");
                }
                
                else{
                    System.out.println("You haven't paid monthly fee.");
                    System.out.println("Do you want to pay now and get 10% discount for today purchase? (Y/N)");
                    String response = sc.nextLine();
                    if(response.equals("Y") || response.equals("y")){
                        System.out.println("Press 1. card \t 2. cash");
                        int response2 = sc.nextInt();
                        prem.makePayment(response2);
                    }
                    else {
                        System.out.println("Oops! You will miss your discount today.");
                    }
                } // end of checking feePaid
            } // end of matching premium member id
   
        } // end of for loop for premium list
       
        
    }// end of checkMemberStatus
    

    
}
