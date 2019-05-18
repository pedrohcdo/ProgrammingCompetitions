import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Pedro H.
 * <pedrohcd@hotmail.com>
 */
public class ProblemF {

    public static class Box {
        long[][] rotations;
        
        public Box(long a, long b, long c) {
            rotations = new long[6][3];
            rotations[0] = new long[] {a, b, c};
            rotations[1] = new long[] {a, c, b};
            rotations[2] = new long[] {b, a, c};
            rotations[3] = new long[] {b, c, a};
            rotations[4] = new long[] {c, a, b};
            rotations[5] = new long[] {c, b, a};
        }
        
        public int compare(Box b) {
            for(long[] dim1 : rotations) {
                for(long[] dim2 : b.rotations) {
                    if(dim1[0] == dim2[0] && dim1[1] == dim2[1] && dim1[2] == dim2[2]) {
                        return 0;
                    } else if(dim1[0] < dim2[0] && dim1[1] < dim2[1] && dim1[2] < dim2[2]) {
                        return -1;
                    } else if(dim1[0] > dim2[0] && dim1[1] > dim2[1] && dim1[2] > dim2[2]) {
                        return 1;
                    }
                }
            }
            throw new IllegalArgumentException();
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
        
        int boxes = Integer.parseInt(bi.readLine().trim());
        Box[] boxesObj = new Box[boxes];
        
        for(int i=0; i<boxes; i++) {
            String[] line = bi.readLine().split("\\s");
            
            Box box = new Box(Integer.parseInt(line[0].trim()),
                    Integer.parseInt(line[1].trim()),
                    Integer.parseInt(line[2].trim()));
            boxesObj[i] = box;
        }
        
        try {
            Arrays.sort(boxesObj, new Comparator<Box>() {
                
                @Override
                public int compare(Box o1, Box o2) {
                    return o1.compare(o2);
                }
            });
            
            for(int i=0; i<boxes-1; i++) {
                if(boxesObj[i].compare(boxesObj[i+1]) == 1) {
                    System.out.println("N");
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("N");
            return;
        }

        

        
        System.out.println("S");
        
    }
}
