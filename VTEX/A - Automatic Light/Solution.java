import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Pedro H.
 * <pedrohcd@hotmail.com>
 *
 * Greedly Problem
 */
public class ProblemA {

    public static void main(String[] args) throws IOException {
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
        try {
            
            int[] mIn = new int[40005];
            int[] mOut = new int[40005];
            int l = -1;

            int inputs = Integer.parseInt(bi.readLine().trim());
            
            // Read values and get lim
            for (int i = 0; i < inputs; i++) {
                String[] values = bi.readLine().split("\\s");
                
                int in = Integer.parseInt(values[0]);
                int out = Integer.parseInt(values[1]);
                mIn[in]++;
                mOut[out]++;
                l = Math.max(l, out);
            }
            
            // Helper
            int[] coutHelper = new int[l + 1];
            coutHelper[0] = mIn[0];
            for (int i = 1; i < l + 1; ++i)
                coutHelper[i] = (coutHelper[i-1] + mIn[i]) - mOut[i]; 

            ///
            int time = 0;
            int max = 0;
            for (int i = 0; i < l + 1; ++i) {
                if (mIn[i] > 0) {
                    max = Math.max(max, time);
                    time = 0;
                }
                if (mOut[i] > 0) {
                    if (coutHelper[i] <= 0) {
                        max = Math.max(max, time);
                        time = 0;
                    }
                }
                if (coutHelper[i] > 0) {
                    ++time;
                }
            }

            System.out.println(max);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}