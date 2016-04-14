import java.util.*;

public class CIDR {
   private String cidrString;
   private int ip;
   private int bitMask;

   public CIDR(String cidr) {
      cidrString = cidr;
      StringTokenizer s = new StringTokenizer(cidr, "./");

      ip = 0;
      //System.out.println(cidr);
      for (int i = 0; i < 4; i++) {
         int toInsert = Integer.parseInt(s.nextToken());
         ip |= (toInsert << (24 - (i * 8)));
      }

      bitMask = (1 << (32 - Integer.parseInt(s.nextToken()))) - 1;
      //System.out.println(String.format("0x%X", bitMask));
   }

   public boolean contains(CIDR comp) {
      return (ip | bitMask) == (comp.getIP() | bitMask);
   }

   public int getIP() { return ip; }
   public int getBitMask() { return bitMask; }

   public String toString() { return cidrString; }

}