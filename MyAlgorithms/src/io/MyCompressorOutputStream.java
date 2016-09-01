package io;

import java.io.IOException;
import java.io.OutputStream;
/**
 * Compressor which compress the maze bytes array
 * @author Tomer, Gilad
 */
public class MyCompressorOutputStream extends OutputStream {
	
	private OutputStream out;
	private byte[] bytes;
	
	// constructor
	public MyCompressorOutputStream(OutputStream out, byte[] bytes) {
		this.out = out;
		this.bytes = bytes;
	}

	@Override
	public void write(int b) throws IOException {
		out.write(b);
	}
	
	public void write(byte[] b) throws IOException{
		
	}

}
