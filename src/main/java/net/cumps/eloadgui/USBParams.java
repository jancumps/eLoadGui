/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cumps.eloadgui;

/**
 *
 * @author jancu
 */
public class USBParams {

    private String connectionName;
    private int baud;

    USBParams() {
        connectionName= null;
        baud = 0;
    }

    void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    String getConnectionName() {
        return connectionName;
    }

    void setBaud(int baud) {
        this.baud = baud;
    }

    int getBaud() {
        return baud;
    }

}
