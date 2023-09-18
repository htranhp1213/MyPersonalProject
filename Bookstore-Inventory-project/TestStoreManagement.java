

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author huongtran
 */
public class TestStoreManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws LowStockException {
        Bookstore bookstore = new Bookstore();
        ArrayList<String> purchasesList = new ArrayList<>();
        
        
        
        try {
            // write book inventory
            FileOutputStream fs = new FileOutputStream("BookInventory.csv");
            PrintWriter pw = new PrintWriter(fs);
            pw.println("ProductID\t Title\t\t\t\t Price\t Stock\t Artist");

            ArrayList<Book> bookInventory = bookstore.getBookList();
            ArrayList<CD> cdInventory = bookstore.getCDlist();
            ArrayList<DVD> dvdInventory = bookstore.getDVDlist();
            /*
            loop through each list of item and print out their information
            */

            for (Book b: bookInventory) {
                pw.println(b.getId() + "\t" + b.getTitle() + "\t" + b.getPrice()+ "\t" + b.getStock());
                if (b.getStock() < 5) {
                    throw new LowStockException();
                }
            }

            for(CD c: cdInventory) {
                pw.println(c.getId() + "\t" + c.getTitle() + "\t" + c.getPrice()+ "\t" + c.getStock() + "\t" + c.getArtist());
                if (c.getStock() < 5) {
                    throw new LowStockException();
                }
            }

            for (DVD d: dvdInventory) {
                pw.println(d.getId() + "\t" + d.getTitle() + "\t" + d.getPrice()+ "\t" + d.getStock() + "\t" + d.getArtist());
                if (d.getStock() < 5) {
                    throw new LowStockException();
                }
            }

            ArrayList<Member> memberlist = bookstore.getMembers();
            ArrayList<PremiumMember> premiumMemberList = bookstore.getPremiumMembers();
            FileOutputStream fs2 = new FileOutputStream("DailyReport.txt");
            PrintWriter pw2 = new PrintWriter(fs2);
            pw2.println("*********** Daily Report**********");
            
            pw2.println("ID\t Name\t numPurchases\t isFeePaid");
            pw2.println();
            int totalPurchases = 0;

            for (Member m: memberlist) {
                pw2.println(m.getId()+"\t"+ m.getName()+"\t"+ m.getNumPurchases() );
                totalPurchases += m.getNumPurchases();
            }
            for (PremiumMember p: premiumMemberList) {
                pw2.println(p.getId()+"\t"+ p.getName()+"\t"+ p.getNumPurchases()+ "\t" + p.isIsFeePaid() );
                totalPurchases += p.getNumPurchases();
            }
            pw2.println("Total purchases: " + totalPurchases);
            pw2.println("Items purchased: " );
            pw2.println(purchasesList);


            pw.close();
            fs.close();
            pw2.close();
            fs2.close();
        }
        catch (FileNotFoundException ex){
            System.out.println("Caught FileNotFoundException. Please insert right name or create a new file");
        }
        catch (IOException ex) {
            System.out.println("Caught IOException when closing output stream. Try again.");
        }
        catch (LowStockException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
