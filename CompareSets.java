import java.io.*;
import java.util.*;

public class CompareSets {
   public static void main(String[] args) {
      List<List<String>> sets = new ArrayList<>();
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
         List<String> set = new ArrayList<>();
         set.add(s.nextLine());
         sets.add(set);
      }

      printLists(sets);
   }

   public static void printLists(List<List<String>> sets) {
      int i = 0;
      for (List<String> set : sets) {
         System.out.print("Set " + ++i + ": {");
         for (String s : set)
            System.out.print(s);
         System.out.println("}");
      }
   }
}