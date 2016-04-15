import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Class to test out the interaction matrix finding methods. Takes in a file where each line is a set and 
 * CIDR values are comma deliminated.
 */
public class TestCompare {
   public static void main(String[] args) {
      List<List<CIDR>> sets = new ArrayList<>();
      Scanner s;

      if (args.length != 1) {
         System.out.println("Usage: java CompareSets <input file>");
         return;
      }

      // open file
      try {
         s = new Scanner(new File(args[0]));
      } catch (FileNotFoundException e) {
         System.out.println("Incorrect file name: " + args[0]);
         return;
      }

      // read in file
      while (s.hasNextLine()) {
         List<CIDR> set = new ArrayList<>();
         Scanner c = new Scanner(s.nextLine());
         c.useDelimiter(", ");

         while (c.hasNext())
            set.add(new CIDR(c.next()));
         sets.add(set); // add the created set to the sets
      }
      
      int[][] interaction = CompareSets.returnInteractions(sets);

      CompareSets.printLists(sets);
      System.out.println();

      // menu of desired interaction matrix output
      Scanner input = new Scanner(System.in);
      while (true) {
         System.out.print("List (l) output, Matrix (m) output, or both (b): ");
         String choice = input.next();
         if (choice.equalsIgnoreCase("l")) {
            CompareSets.printInteractions(interaction);
            break;
         } else if (choice.equalsIgnoreCase("m")) {
            CompareSets.printMatrix(interaction);
            break;     
         } else if (choice.equalsIgnoreCase("b")) {
            CompareSets.printInteractions(interaction);
            System.out.println();
            CompareSets.printMatrix(interaction);
            break;
         }
            
         System.out.println("invalid choice");
      }
   }
}