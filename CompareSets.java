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
      
      String[][] interaction = new String[sets.size()][sets.size()];

      for (int i = 0; i < sets.size(); i++) {
         for (int j = 0; j < sets.size(); j++) {
            interaction[i][j] = interactionSets(sets.get(i), sets.get(j));
         }
      }

      printLists(sets);
      System.out.println();
      printInteractions(interaction);
   }

   public static String interactionSets(List<CIDR> set, List<CIDR> compareTo) {
      List<CIDR> comp = new ArrayList<CIDR>(compareTo);
      int originalSize = comp.size();

      for (CIDR c1 : compareTo) {
         for (CIDR c2 : set) {
            // System.out.println(c1 + " " + c2);
            if (comp.contains(c1) && c2.contains(c1))
               comp.remove(c1);
         }
      }

      if (comp.size() == 0)
         return "Contains";
      if (comp.size() < originalSize)
         return "Intersects";

      return "Adjacent";
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

   public static void printInteractions(String[][] interaction) {
      System.out.println("Interaction Matrix (interpreted as row <Contains/Intersects/Adjacent> col): ");

      for (int i = 1; i <= interaction.length; i++)
         System.out.printf("%12s", "Set " + i);

      for (int i = 0; i < interaction.length; i++) {
         System.out.println();
         System.out.printf("Set %-3d", i + 1);
         for (int j = 0; j < interaction[i].length; j++) {
            System.out.printf("%-12s", interaction[i][j]);
         }
      }
   }
}