package com.company;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CryptoPanel extends JPanel {
    private JTextField key1Field; //pole na nazwę
    private JTextField messageField; //pole na hasło
    private JButton cryptoButton; //przycisk logowania
    private CryptoListener listener;

    /**
     * @return wprowadzona nazwa użytkownika
     */
    public String getKey1() {
        return key1Field.getText();
    }

    /**
     * @return wprowadzone przez użytkownika hasło
     */
    public String getMessage() {
        return messageField.getText();
    }

    public CryptoPanel(CryptoListener listener) {
        super();
        // ustawiamy layout
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        gridBag.setConstraints(this, constraints);
        setLayout(gridBag);
        // tworzymy komponenty logowania
        this.listener = listener;
        this.listener.setPanel(this);
        createComponents();
    }

    /**
     * Metoda, która tworzy etykiety i pola do wprowadzania danych.
     */
    private void createComponents() {
        JLabel key1 = new JLabel("Key 1: ");
        JLabel message = new JLabel("Message: ");
        key1Field = new JTextField();
        messageField = new JTextField();

        //pomocniczy panel do wprowadzania danych
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        inputPanel.add(key1);
        inputPanel.add(key1Field);
        inputPanel.add(message);
        inputPanel.add(messageField);
        cryptoButton = new JButton("Encrypt/Decrypt");
        cryptoButton.addActionListener(listener);

        //pomocniczy panel do wyśrodkowania elementów
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());
        parentPanel.add(inputPanel, BorderLayout.CENTER);
        parentPanel.add(cryptoButton, BorderLayout.SOUTH);

        // dodajemy do głównego panelu
        this.add(parentPanel);
    }
}