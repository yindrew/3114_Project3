import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Generate a data file. The size is a multiple of 8192 bytes.
 * Each record is one long and one double.
 * 
 * @author given
 * @version 2022.08.10
 * 
 */
public class GenBinaryDataFile {
    /**
     * number of records per block
     */
    static final int RECS_PER_BLOCK = 512;
    /**
     * number of bytes per record
     */
    static final int BYTES_PER_RECORD = Long.BYTES + Double.BYTES; // should be
                                                                   // 8 + 8

    /**
     * generates a random data file
     * 
     * @param filename
     *            input file
     * @param numBlocks
     *            number of blocks
     * @throws IOException
     *             in case wrong input
     */
    public static void generateRandom(String filename, int numBlocks)
        throws IOException {

        Random randGen = new Random();
        int numRecords = numBlocks * RECS_PER_BLOCK;
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(
            new FileOutputStream(filename)));

        long value;
        double key;
        for (int i = 0; i < numRecords; i++) {
            // note that the value comes first, the key comes second
            value = randGen.nextLong();
            dos.writeLong(value);
            key = randGen.nextDouble();
            dos.writeDouble(key);
        }
        dos.flush();
        dos.close();
    }


    /**
     * generates a sorted data file
     * 
     * @param filename
     *            input file
     * @param numBlocks
     *            number of blocks
     * @throws IOException
     *             in case wrong input
     */
    public static void generateSorted(String filename, int numBlocks)
        throws IOException {
        int numRecords = numBlocks * RECS_PER_BLOCK;
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(
            new FileOutputStream(filename)));
        Random randGen = new Random();

        for (int i = 0; i < numRecords; i++) {
            long value = randGen.nextLong();
            double key = (double)i;

            dos.writeLong(value);
            dos.writeDouble(key);
        }

        dos.flush();
        dos.close();

    }


    /**
     * generates a reverse sorted data file
     * 
     * @param filename
     *            input file
     * @param numBlocks
     *            number of blocks
     * @throws IOException
     *             in case wrong input
     */
    public static void generateReverseSorted(String filename, int numBlocks)
        throws IOException {
        int numRecords = numBlocks * RECS_PER_BLOCK;

        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(
            new FileOutputStream(filename)));
        Random randGen = new Random();

        for (int i = numRecords; i > 0; i--) {
            long value = Long.valueOf(i);
            double key = (double)i;

            dos.writeLong(value);
            dos.writeDouble(key);
        }

        dos.flush();
        dos.close();
    }

}
