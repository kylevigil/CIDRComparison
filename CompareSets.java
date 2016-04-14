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

      CIDR test = new CIDR("10.10.0.0/8");
      System.out.println(test.contains(new CIDR("10.20.0.0/16")));

      printLists(sets);
   }

   public static void printLists(List<List<CIDR>> sets) {
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
}