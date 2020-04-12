/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cumps.eloadgui;

import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jancu
 */
public class Instrument {

    private static final Logger log = Logger.getLogger(Instrument.class.getName());
    private SerialPort comPort = null;
    private final char[] buffer;

    public Instrument() {
        buffer = new char[100];
    }

    public void init() {
    }

    public boolean connect(USBParams usbParams) {
        comPort = SerialPort.getCommPort​(usbParams.getConnectionName());
        comPort.setBaudRate(usbParams.getBaud());
//        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 1000, 0);
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 150, 0);
        boolean bResult = false;
        if (comPort.openPort()) {
            bResult = true;
            log.log(Level.INFO, "Open port succeeeded");
        } else {
            comPort = null;
            log.log(Level.SEVERE, "Open port failed");
        }
        return bResult;
    }

    public void disconnect() {
        if (comPort != null) {
            if (comPort.closePort()) {
                log.log(Level.INFO, "Closing port succeeeded");
            } else {
                log.log(Level.INFO, "Closing port failed");
            }
            comPort = null;
        }
    }

    public void stream() {
//        try {
//            write("*IDN?\n");
//            int i = read(buffer);
//            if (i != -1) {
//                System.out.print(buffer);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public String read() {

        Reader in = new InputStreamReader(comPort.getInputStream());
        String s = null;
        try {
            int len = in.read(buffer, 0, buffer.length);
            s = new String(buffer, 0, len);
            in.close();
        } catch (IOException e) {
            s = null;
//            e.printStackTrace();
        }
        return s;
    }

    public int write(String s) {
        OutputStream out = comPort.getOutputStream();
        int retval = -1;

        try {
            out.write(s.getBytes());
            out.close();
            retval = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retval;
    }

}
