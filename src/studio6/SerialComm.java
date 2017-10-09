package studio6;

import jssc.*;

public class SerialComm {
	
	public static void main(String[] args) throws SerialPortException{
		
		SerialComm sc = new SerialComm("/dev/cu.usbserial-DN02B0FP");
		while(true){
			if(sc.available()){
				byte b = sc.readByte();
				System.out.print((char) b);
			}
		}
		
	}

	SerialPort port;

	private boolean debug;  // Indicator of "debugging mode"
	
	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	
	

	// Constructor for the SerialComm class
	public SerialComm(String name) throws SerialPortException {
		port = new SerialPort(name);		
		port.openPort();
		port.setParams(SerialPort.BAUDRATE_9600,
			SerialPort.DATABITS_8,
			SerialPort.STOPBITS_1,
			SerialPort.PARITY_NONE);
		
		debug = false; // Default is to NOT be in debug mode
	}
		
	// TODO: Add writeByte() method to write data to serial port
	
	void write(int singleByte){
		byte b = (byte) singleByte;
		
		try {
			
			port.writeByte((byte) singleByte);
			System.out.println(b);
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	boolean available(){
		try {
			return port.getInputBufferBytesCount() > 0;
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	byte readByte(){
		byte[] bites;
		try {
			bites = port.readBytes(1);
			if(debug){
				System.out.print(" " + String.format("%#x", bites[0]));
			}
			return bites[0];
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
