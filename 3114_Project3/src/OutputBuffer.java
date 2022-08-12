import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

/**
 * Output buffer
 * 
 * @author hadenlee
 * @version 2022.08.10
 */
public class OutputBuffer {

    // field
    private Record[] records = new Record[512];
    private int size;
    private int capacity = 512;
    private RandomAccessFile file;


    /**
     * constructor
     * 
     * @param fileName file name
     * @throws FileNotFoundException
     */
    public OutputBuffer(String fileName) throws FileNotFoundException {
        File runFile = new File(fileName);
        this.file = new RandomAccessFile(runFile, "rw");
        this.size = 0;
    }

    /**
     * get size
     * 
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * return the last record in the output buffer
     * @return the last record
     */
    public Record lastRecord() {
        return records[size - 1];
    }


    /**
     * add record
     * 
     * @param record record to be added 
     * @throws IOException when error
     */
    public void addRecord(Record record) throws IOException {
        if (isFull()) {

            write();
            size = 0;
        }

        records[size] = record;
        size++;
    }

    /**
     * returns if the file is full;
     * @return if file is full
     */
    public boolean isFull() {
        return (size == capacity);
    }

    /**
     * closes file
     * @throws IOException when error
     */
    public void closeFile() throws IOException {
        this.file.close();
    }


    /**
     * Creates an array of this full OutputBuffer's Records in byte form
     * 
     * @return An array of Records in byte form
     */
    private byte[] convertRecsToByteForm() {
        byte[] recsByteForm = new byte[8192];
        int byteArrIndex = 0;
        // Loop through all Record objects, storing their byte form
        for (int i = 0; i < 512; i++) {
            Record nextRec = records[i];
            byte[] byteForm = nextRec.toBinary();
            // Loop through byte form, storing in recsByteForm
            for (int j = 0; j < 16; j++) {
                recsByteForm[byteArrIndex] = byteForm[j];
                byteArrIndex++;
            }
        }
        return recsByteForm;
    }

    /**
     * writes records as bytes into binary file
     * @throws IOException when there is error
     */
    public void write() throws IOException {
        byte[] recsByteForm = convertRecsToByteForm();
        file.write(recsByteForm);
    }

}
