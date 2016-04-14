import java.util.*;

public class CIDR {
   private String cidrString;
   private int ip;
   private int bitMask;

   public CIDR(String cidr) {
      cidrString = cidr;
      StringTokenizer s = new StringTokenizer(cidr, "./");

      ip = 0;
      for (int i = 0; i < 4; i++) {
         int toInsert = Integer.parseInt(s.nextToken());
         ip |= (toInsert << (24 - (i * 8)));
      }

      int numBits = Integer.parseInt(s.nextToken());

      bitMask = (1 << (32 - numBits)) - 1;

      if (numBits == 0) bitMask = -1;
      if (numBits == 32) bitMask = 0;
   }

   public boolean contains(CIDR comp) {
      if (comp.getBitMask() > bitMask && bitMask != -1)
         return false;
      return (ip | bitMask) == (comp.getIP() | bitMask);
   }

   public int getIP() { return ip; }
   public int getBitMask() { return bitMask; }

   public String toString() { return cidrString; }

}