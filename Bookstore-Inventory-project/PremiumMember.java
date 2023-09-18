

import java.util.Scanner;

/**
 *
 * @author huongtran
 */
public class PremiumMember extends Member {
    private boolean isFeePaid;
    private String paymentMethod;
    

    public PremiumMember(int id, String name, int numPurchases, boolean isFeePaid) {
        super(id, name, numPurchases);
        this.isFeePaid = isFeePaid;
        //this.paymentMethod = paymentMethod;
    }


    public boolean isIsFeePaid() {
        return isFeePaid;
    }

    public void setIsFeePaid(boolean isFeePaid) {
        this.isFeePaid = isFeePaid;
    }

    /*public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }*/
    
    
   
    
    public void makePayment(int method) {
        Scanner sc = new Scanner(System.in);
        boolean status = false;
        // pay by card is 1, pay by cash is 2.
        // premiumMembership fee is $10
        
        while (status == false) {
            if (method == 1) {
                System.out.println("Please enter a 12 digit card number.");
                String card = sc.nextLine();
                if (card.length() == 12) {
                    System.out.println("Transaction is successful! You are now a premium member");
                    status = true;
                } else {
                    System.out.println("Transaction is failed. Please try again.");
                }
            }
            if (method == 2) {
                System.out.println("Please enter an amount");
                double cash = sc.nextDouble();
                if (cash > 10) {
                    double change = cash - 10;
                    System.out.println("Thank you, your change is $" + change + ". You are now a member");
                    status = true;
                }
                if (cash < 10) {
                    System.out.println("Error, the amount entered is not enough. Please try again");
                }
                if (cash == 10) {
                    System.out.println("Transaction successful! You are now a premium member");
                    status = true;
                }
            }
        }
    }
    
    

    @Override
    public String toString() {
        super.toString();
        return "PremiumMember{" +super.toString() + "isFeePaid=" + isFeePaid + ", paymentMethod=" + paymentMethod + '}';
    }

    
    
    
    
    
    
}
