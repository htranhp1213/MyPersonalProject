
import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.util.InputMismatchException;
//import static project1.Main.registerNewMember;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author huongtran
 */
public class StoreManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Bookstore bookstore = new Bookstore();
        ArrayList<String> purchasesList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean open = true;
        manageStore();
        
        
        
    }
    
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
    
    public static boolean registerNewMember(Bookstore bookstore, Scanner sc, int numPurchases) throws InputMismatchException {

        Scanner scanner = new Scanner(System.in);
        numPurchases = 0;
        boolean isPremium;
        System.out.println("Do you want to register for a Premium Membership? y/n");
        String input = sc.nextLine();
        try {
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

        } catch(InputMismatchException e) {
            System.out.println("Please enter required input");
        }

        return true;
    } // end of registerNewMember
    
    public static void manageStore() {
       
        //run menu system
        Bookstore bookstore = new Bookstore();
        ArrayList<String> purchasesList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean open = true;
        
        
         System.out.println("********** Welcome to work at Garden of Books **********");
         System.out.println("");
        
        boolean isBreakTime = false;
        
        
        while(isBreakTime == false) {
            // see what the user wants to do
            System.out.println("Please select your task for today:");
            System.out.println("\t 1. Store Front");
            System.out.println("\t 2. Administration");
            System.out.println("\t 3. Exit Store");

            try {
                int num = sc.nextInt();
                switch (num) {
                    //Main menu switch case 1
                    case 1:
                        boolean exitStoreFront = false;

                        while(exitStoreFront == false){
                            System.out.println("\t 1. Register a New Member");
                            System.out.println("\t 2. Check Membership Status");
                            System.out.println("\t 3. Exit Store Front");

                            int storeFrontOption = sc.nextInt();
                            switch (storeFrontOption) {
                                case 1:
                                    int numPurchase = purchasesList.size();
                                    System.out.println("Thank you for being one of our members");
                                    registerNewMember(bookstore, sc, purchasesList.size()); 
                                    String input = sc.nextLine();

                                    boolean isMember = registerNewMember(bookstore, sc, purchasesList.size()); 
                                    if (isMember == true) {
                                        System.out.println("You are ready to get some discount");

                                    }
                                    break;

                                case 2:
                                    System.out.println("Check your status");
                                    checkMemberstatus(bookstore, sc);
                                    break;
                              
                                case 3:
                                    exitStoreFront = true;
                            }
                        }
                        break;

                    //main menu switch case 2
                    case 2:  
                        boolean exitAdmin = false;

                        while(exitAdmin == false) {
                            System.out.println("\t 1. Restock Product Products");
                            System.out.println("\t 2. Check Inventory");                      
                            System.out.println("\t 3. Exit Administration");

                            int storeAdmin = sc.nextInt();
                            switch (storeAdmin) {
                                case 1:
                                    boolean done = false;
                                    while (done == false) {
                                        System.out.println("Please enter product ID");
                                        sc.nextLine();
                                        String productID = sc.nextLine();
                                        System.out.println("Please enter quantity");
                                        sc.nextLine();
                                        int num1 = sc.nextInt();
                                        bookstore.restockProduct(productID, num1);
                                        System.out.println("Are you done restocking? y/n");
                                        sc.nextLine();
                                        String doneRe = sc.nextLine();
                                        if(doneRe.equals("y")) {
                                            done = true;
                                        }
                                    }
                                    break;

                                case 2:
                                    bookstore.inventoryValue();
                                    break;


                                case 3:
                                    exitAdmin = true;
                            }
                        }
                        break;

                    //main menu switch case 3
                    case 3:
                        isBreakTime = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid selection.");
                sc.nextLine();
            }
            
        }
        
        
    
    }
}
