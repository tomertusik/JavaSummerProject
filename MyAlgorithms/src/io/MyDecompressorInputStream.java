package io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Decompressor which decompress information from file into byte array
 * @author Tomer, Gilad
 */
public class MyDecompressorInputStream extends InputStream {
	private InputStream in;
	private int value;
	private int count;
	

	public MyDecompressorInputStream(InputStream input) {
		super();
		this.in = input;
	}
	

	@Override
	public int read() throws IOException {
		if (count <= 0 ) {
			count = in.read();
			value = in.read();
		}
		count--;
		return value;
	}
	
	
	/**
	 * Reads integer bigger than 255
	 * @return
	 * @throws IOException
	 */
	public int readSize() throws IOException {	
	int sum = 0,currRead = 0;
	do{
		currRead = in.read();
		sum += currRead;
	}while(currRead == 255);
		
	return sum;
}

}