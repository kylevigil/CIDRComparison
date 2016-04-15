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
      bitMask = (1 << (32 - numBits)) - 1;

      // handle the case of the last value being 32 or 0
      if (numBits == 0) bitMask = -1; 
      if (numBits == 32) bitMask = 0;
   }

   /**
    * Determine if one CIDR contains another
    * @param  comp - CIDR to test if it falls in the range of this CIDR
    * @return      True if comp falls in the range of present CIDR, false otherwise
    */
   public boolean contains(CIDR comp) {
      // if the range of comp is larger than the present CIDR then it can't contain it
      if (comp.bitMask > bitMask && bitMask != -1)
         return false;

      return (ip | bitMask) == (comp.ip | bitMask); // test if with the bit mask the values are equal
   }

   /**
    * Easy printing of the CIDR value
    */
   public String toString() { return cidrString; }
}