import java.util.StringTokenizer;

/**
 * Class to model a CIDR value. Contains basic operations to test if one CIDR value contains another.
 */
public class CIDR {
   private String cidrString;
   private int ip;
   private int bitMask;

   /**
    * Constructor. Takes in a string in the form of "(0-255).(0-255).(0-255).(0-255)/(0-32)" for example "192.168.0.1/28". 
    * Initializes the representative string, the ip address stored into a single int to conserve space, and the bit mask of the last value of the CIDR. 
    * @param  cidr - String to represent the CIDR
    */
   public CIDR(String cidr) {
      cidrString = cidr;
      StringTokenizer s = new StringTokenizer(cidr, "./");

      // Shift sections of the ip address in order to store them in a single int
      ip = 0;
      for (int i = 0; i < 4; i++) {
         int toInsert = Integer.parseInt(s.nextToken());
         ip |= (toInsert << (24 - (i * 8)));
      }

      int numBits = Integer.parseInt(s.nextToken()); // number of bits in bitMask

      // Creating the bit mask based off the last cidr value
      bitMask = 0xffffffff << (32 - numBits); 
      if (numBits == 0) bitMask = 0;
      // System.out.println(Integer.toHexString(bitMask));
   }

   /**
    * Determine if one CIDR contains, intersects, or is adjacent to another
    * @param  comp - CIDR to test if it falls in the range of this CIDR
    * @return        0-adjacent, 1-intersecting, 2-containse
    */
   public int contains(CIDR comp) {
      int lower = ip & bitMask;
      int upper = lower + ~bitMask;
      int compLower = comp.ip & comp.bitMask;
      int compUpper = compLower + ~comp.bitMask;

      // using unsigned comparisons test if compare CIDR is outside or within range of CIDR
      if (Integer.compareUnsigned(upper, compUpper) >= 0 && Integer.compareUnsigned(lower, compLower) <= 0)
         return 2;
      if (Integer.compareUnsigned(compUpper, lower) < 0 || Integer.compareUnsigned(compLower, upper) > 0)
         return 0;
      return 1;
   }

   /**
    * Easy printing of the CIDR value
    */
   public String toString() { return cidrString; }
}