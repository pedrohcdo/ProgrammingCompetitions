import java.io.BufferedInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Pedro H.
 * <pedrohcd@hotmail.com>
 */
public class ProblemG {

    static class Custom implements Comparable<Custom>{
        int id;
        BigInteger arrives;
        BigInteger oTime;
        BigInteger dTime;
        Character chef;

        public Custom(int id, BigInteger arrives, BigInteger oTime) {
            super();
            this.id = id;
            this.arrives = arrives;
            this.oTime = oTime;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + id;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            return ((Custom)obj).id == this.id;
        }

        @Override
        public int compareTo(Custom o) {
            int res = this.arrives.compareTo(o.arrives);
            return res != 0 ? res : this.id - o.id;
        }

        @Override
        public String toString() {
            return String.format("%s %s", chef, dTime);
        }       
        
        
    }

    public static void main(String[] args) throws IOException {
        try (Scanner sc = new Scanner(new BufferedInputStream(System.in))) {
            int n = sc.nextInt();
            BigInteger k = sc.nextBigInteger();

            Custom[] Customs = new Custom[n];
            Custom[] CustomsOrdered = new Custom[n];
            for (int i = 0; i < n; ++i) {
                Customs[i] = CustomsOrdered[i] = new Custom(i, sc.nextBigInteger(), sc.nextBigInteger());
            }
            
            Arrays.sort(CustomsOrdered);
            
            BigInteger aQueue = BigInteger.ZERO;
            BigInteger bQueue = BigInteger.ZERO;
            
            ArrayDeque<Custom> aDeque = new ArrayDeque<>();
            ArrayDeque<Custom> bDeque = new ArrayDeque<>();
            
            for (int i = 0; i < n; ++i) {
                Custom curr = CustomsOrdered[i];
                
                while (!aDeque.isEmpty() && aDeque.peek().dTime.compareTo(curr.arrives) <= 0) {
                    aDeque.poll();
                }
                
                while (!bDeque.isEmpty() && bDeque.peek().dTime.compareTo(curr.arrives) <= 0) {
                    bDeque.poll();
                }
                
                if (aDeque.size() <= bDeque.size()) {
                    aQueue = aQueue.max(curr.arrives);
                    aQueue = aQueue.add(curr.oTime);
                    curr.chef = 'A';
                    curr.dTime = aQueue;
                    aDeque.add(curr);
                } else {
                    bQueue = bQueue.max(curr.arrives);
                    bQueue = bQueue.add(curr.oTime.multiply(k));
                    CustomsOrdered[i].chef = 'B';
                    CustomsOrdered[i].dTime = bQueue;
                    bDeque.add(curr);
                }
            }
            
            for (int i = 0; i < n; ++i) {
                System.out.println(Customs[i]);
            }
        }
    }
}