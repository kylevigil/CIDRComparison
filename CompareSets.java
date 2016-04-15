import java.util.List;
import java.util.ArrayList;

/**
 * Class containing functions in order to return back a matrix of comparison of sets of CIDR values.
 */
public class CompareSets {
   /**
    * Function to return the matrix of levels of interaction between sets of CIDR values
    * @param  sets - List of lists of CIDR values. Represents lists of sets
    * @return      square int[][] matrix that contains numbers to represent level of interaction. 0-adjacent, 1-intersecting, 2-contains
    */
   public static int[][] returnInteractions(List<List<CIDR>> sets) {
      int[][] interaction = new int[sets.size()][sets.size()];

      // loops through all sets twice to compare all set pairs
      for (int i = 0; i < sets.size(); i++) {
         for (int j = 0; j < sets.size(); j++) {
            if (i == j)
               interaction[i][j] = 2; // all values down the diagonal are contains because every set contains itself
            else 
               interaction[i][j] = interactionSets(sets.get(i), sets.get(j));
         }
      }
      return interaction;
   }

   /**
    * Compare two sets to determine the level of interaction between the two.
    * @param  set       - First set, is the set that is testing if the second set fits in it.
    * @param  compareTo - Second set, is testing if it fits inside first set
    * @return           - 0-adjacent, 1-intersecting, 2-contains
    */
   private static int interactionSets(List<CIDR> set, List<CIDR> compareTo) {
      List<CIDR> comp = new ArrayList<CIDR>(compareTo);
      boolean intersects = false;

      // loop through compare to set and original set
      for (CIDR c1 : compareTo) {
         for (CIDR c2 : set) {
            if (comp.contains(c1) && c2.contains(c1) == 2)
               comp.remove(c1); // if it is contained, remove from temp list
            if (c2.contains(c1) > 0)
               intersects = true;
         }
      }

      if (comp.size() == 0) // if the size of the temp list is 0, all the compareTo values are contained
         return 2;
      if (intersects)
         return 1;

      return 0; // otherwise they are adjacent
   }

   /**
    * Prints out a list of all of the sets.
    * @param sets - the sets to print out
    */
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

   /**
    * Print out the interactions in matrix form.
    * @param interaction - interaction matrix to print
    */
   public static void printMatrix(int[][] interaction) {
      System.out.println("Interaction Matrix (Row <[C]ontains/[I]ntersects/[A]djacent to> Col): ");

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
            String[] toPrint = {"A", "I", "C"}; // index of list is the interaction number
            System.out.printf("%4s", toPrint[interaction[i][j]]);
         }
      }
   }

   /**
    * Print out the interactions in list form. Skips comparison between sets and itself
    * @param interaction - interaction matrix to print
    */
   public static void printInteractions(int[][] interaction) {
      System.out.println("List of interaction:");

      for (int i = 0; i < interaction.length; i++) {
         for (int j = 0; j < interaction[i].length; j++) {
            if (i != j) {
               String[] toPrint = {"is adjacent to", "intersects", "contains"}; // index of list is the interaction number
               System.out.printf("set %d %s set %d\n", i + 1, toPrint[interaction[i][j]], j + 1);
            }
         }
      }
   }
}
