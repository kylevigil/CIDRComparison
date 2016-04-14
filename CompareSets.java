import java.io.*;
import java.util.*;

public class CompareSets {
   public static void main(String[] args) {
      List<List<CIDR>> sets = new ArrayList<>();
      Scanner s;

      if (args.length != 1) {
         System.out.println("Usage: java CompareSets <input file>");
         return;
      }

      try {
         s = new Scanner(new File(args[0]));
      } catch (FileNotFoundException e) {
         System.out.println("Incorrect file name: " + args[0]);
         return;
      }

      while (s.hasNextLine()) {
         List<CIDR> set = new ArrayList<>();
         Scanner c = new Scanner(s.nextLine());
         c.useDelimiter(", ");

         while (c.hasNext())
            set.add(new CIDR(c.next()));
         sets.add(set);
      }
      
      int[][] interaction = new int[sets.size()][sets.size()];

      for (int i = 0; i < sets.size(); i++) {
         for (int j = 0; j < sets.size(); j++) {
            if (i == j)
               interaction[i][j] = 2;
            else 
               interaction[i][j] = interactionSets(sets.get(i), sets.get(j));
         }
      }

      printLists(sets);
      System.out.println();

      Scanner input = new Scanner(System.in);
      while (true) {
         System.out.print("List (l) output, Matrix (m) output, or both (b): ");
         String choice = input.next();
         if (choice.equalsIgnoreCase("l")) {
            printInteractions(interaction);
            break;
         } else if (choice.equalsIgnoreCase("m")) {
            printMatrix(interaction);
            break;     
         } else if (choice.equalsIgnoreCase("b")) {
            printInteractions(interaction);
            System.out.println();
            printMatrix(interaction);
            break;
         }
            
         System.out.println("invalid choice");
      }
   }

   public static int interactionSets(List<CIDR> set, List<CIDR> compareTo) {
      List<CIDR> comp = new ArrayList<CIDR>(compareTo);
      int originalSize = comp.size();

      for (CIDR c1 : compareTo) {
         for (CIDR c2 : set) {
            if (comp.contains(c1) && c2.contains(c1))
               comp.remove(c1);
         }
      }

      if (comp.size() == 0)
         return 2;
      if (comp.size() < originalSize)
         return 1;

      return 0;
   }

   public static void printLists(List<List<CIDR>> sets) {
      System.out.println("Sets: ");

      int i = 0;
      for (List<CIDR> set : sets) {
         System.out.print("Set " + ++i + ": {");

         boolean first = false;
         for (CIDR c : set) {
            if (first) System.out.print(", ");

            first = true;
            System.out.print(c);
         }

         System.out.println("}");
      }
   }

   public static void printMatrix(int[][] interaction) {
      System.out.println("Interaction Matrix (interpreted as row <[C]ontains/[I]ntersects/[A]djacent> col): ");

      System.out.print("Set|");
      for (int i = 1; i <= interaction.length; i++)
         System.out.printf("%4d", i);

      System.out.println();
      for (int i = 0; i <= interaction.length; i++) {
         System.out.print("====");
      }

      for (int i = 0; i < interaction.length; i++) {
         System.out.println();
         System.out.printf("%-3d|", i + 1);
         for (int j = 0; j < interaction[i].length; j++) {
            String[] toPrint = {"A", "I", "C"};
            System.out.printf("%4s", toPrint[interaction[i][j]]);
         }
      }
   }

   public static void printInteractions(int[][] interaction) {
      System.out.println("List of interaction:");

      for (int i = 0; i < interaction.length; i++) {
         for (int j = 0; j < interaction[i].length; j++) {
            if (i != j) {
               String[] toPrint = {"is adjacent to", "intersects", "contains"};
               System.out.printf("set %d %s set %d\n", i + 1, toPrint[interaction[i][j]], j + 1);
            }
         }
      }
   }
}
