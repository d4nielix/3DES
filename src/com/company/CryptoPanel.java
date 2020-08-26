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
    private JTextField key1Field;
    private JTextField messageField;
    private JButton cryptoButton;
    private CryptoListener listener;

    public String getKey1() {
        return key1Field.getText();
    }

    public String getMessage() {
        return messageField.getText();
    }

    public CryptoPanel(CryptoListener listener) {
        super();

        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        gridBag.setConstraints(this, constraints);
        setLayout(gridBag);

        this.listener = listener;
        this.listener.setPanel(this);
        createComponents();
    }

    private void createComponents() {
        JLabel key1 = new JLabel("Key 1: ");
        JLabel message = new JLabel("Message: ");
        key1Field = new JTextField();
        messageField = new JTextField();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        inputPanel.add(key1);
        inputPanel.add(key1Field);
        inputPanel.add(message);
        inputPanel.add(messageField);
        cryptoButton = new JButton("Encrypt/Decrypt");
        cryptoButton.addActionListener(listener);

        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());
        parentPanel.add(inputPanel, BorderLayout.CENTER);
        parentPanel.add(cryptoButton, BorderLayout.SOUTH);

        this.add(parentPanel);
    }
}