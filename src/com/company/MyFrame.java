package com.company;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame() {
        super("DES");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800,600);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JPanel radioPanel = new RadioPanel();
        add(radioPanel);

        CryptoListener listener = new CryptoListener(this);
        JPanel cryptoPanel = new CryptoPanel(listener);
        add(cryptoPanel);

        setVisible(true);
    }
}