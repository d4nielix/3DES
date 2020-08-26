package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CryptoListener implements ActionListener {
    //Główna ramka programu
    private final JFrame frame;
    //Panel logowania, potrzebny do pobrania loginu i hasła
    private CryptoPanel cryptoPanel;

    public void setPanel(CryptoPanel loginPanel) {
        this.cryptoPanel = loginPanel;
    }

    public CryptoListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("TEST");
            }
        });
    }
}