package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.*;

public class MainPanel extends JPanel implements ActionListener {

    private final JRadioButton modeDES;
    private final JRadioButton mode3DES;
    private final JRadioButton modeEncrypt;
    private final JRadioButton modeDecrypt;
    private final JRadioButton modeAuto;
    private final JRadioButton modeManual;
    private final JButton start;

    private final JTextField plainText;
    private final JTextField key1Field;
    private final JTextField key2Field;
    private final JTextField key3Field;

    private final JTextField cipherText;

    private final DES des = new DES();
    String key1 = "1";
    String key2 = "2";
    String key3 = "3";
    String plain = "DEFAULT_TEXT";
    String cipher = null;
    String result = null;
    boolean generateKeys = true;
    boolean encMode = true;
    boolean des3Mode = false;

    public void dewIt() {
        try {
            String s;
            String keySize = "10000000";
            int keyNumber = des.getDes3();
            if (keyNumber == 1) {
                des3Mode = false;
            } else if (keyNumber == 3) {
                des3Mode = true;
            }
            if (generateKeys) {
                Process p = Runtime.getRuntime().exec("python src\\com\\company\\rng.py --sequence " + keyNumber + " " + keySize);
                BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                if (!des3Mode) {
                    key1 = in.readLine();
                } else {
                    for (int i = 0; i < 6; i++) {
                        s = in.readLine();
                        if (i == 0 & s != null) {
                            key1 = s;
                        } else if (i == 2 & s != null) {
                            key2 = s;
                        } else if (i == 4 & s != null) {
                            key3 = s;
                        }
                    }
                }
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }

        System.out.println(key1 + " " + key2 + " " + key3);

        if (!des3Mode) {
            if (encMode) {
                System.out.println("DES Encrypt + Decrypt Mode");
                cipher = des.crypto(key1, DES.utfToBin(plain), true);
                result = DES.binToHex(cipher);
                System.out.println("Encrypted message: " + result);
            } else {
                System.out.println("DES Decrypt only Mode");
                cipher = DES.hexToBin(plain);
                result = DES.binToUTF(des.crypto(key1, cipher, false));
            }
            System.out.println("Decrypted message: " + DES.binToUTF(des.crypto(key1, cipher, false)));
        } else {
            if (encMode) {
                System.out.println("3DES Encrypt + Decrypt Mode");
                cipher = des.crypto(key3, des.crypto(key2, des.crypto(key1, DES.utfToBin(plain), true), false), true);
                result = DES.binToHex(cipher);
                System.out.println("Encrypted message: " + result);
            } else {
                System.out.println("3DES Decrypt only Mode");
                cipher = DES.hexToBin(plain);
                result = DES.binToUTF(des.crypto(key1, des.crypto(key2, des.crypto(key3, cipher, false), true), false));
            }
            System.out.println("Decrypted message: " + DES.binToUTF(des.crypto(key1, des.crypto(key2, des.crypto(key3, cipher, false), true), false)));
        }
    }

    public MainPanel() {
        JLabel empty1Label = new JLabel("   ");
        JLabel empty2Label = new JLabel("   ");

        modeDES = new JRadioButton("DES");
        modeDES.setSelected(true);
        mode3DES = new JRadioButton("3DES");
        ButtonGroup desMode = new ButtonGroup();
        desMode.add(modeDES);
        desMode.add(mode3DES);
        modeDES.addActionListener(this);
        mode3DES.addActionListener(this);
        add(modeDES);
        add(mode3DES);
        add(empty1Label);

        modeEncrypt = new JRadioButton("Encrypt");
        modeEncrypt.setSelected(true);
        modeDecrypt = new JRadioButton("Decrypt");
        ButtonGroup encryptMode = new ButtonGroup();
        encryptMode.add(modeEncrypt);
        encryptMode.add(modeDecrypt);
        modeEncrypt.addActionListener(this);
        modeDecrypt.addActionListener(this);
        add(modeEncrypt);
        add(modeDecrypt);
        add(empty2Label);

        modeAuto = new JRadioButton("Generate keys");
        modeAuto.setSelected(true);
        modeManual = new JRadioButton("Manual keys");
        ButtonGroup keysMode = new ButtonGroup();
        keysMode.add(modeAuto);
        keysMode.add(modeManual);
        modeAuto.addActionListener(this);
        modeManual.addActionListener(this);
        add(modeAuto);
        add(modeManual);

        JLabel plainTextLabel = new JLabel("Message: ");
        plainText = new JTextField("", 40);
        add(plainTextLabel);
        add(plainText);

        JLabel key1Label = new JLabel("Key 1: ");
        key1Field = new JTextField("", 42);
        key1Field.setEditable(false);
        add(key1Label);
        add(key1Field);
        JLabel key2Label = new JLabel("Key 2: ");
        key2Field = new JTextField("", 42);
        key2Field.setEditable(false);
        add(key2Label);
        add(key2Field);
        JLabel key3Label = new JLabel("Key 3: ");
        key3Field = new JTextField("", 42);
        key3Field.setEditable(false);
        add(key3Label);
        add(key3Field);

        JLabel cipherTextLabel = new JLabel("Result: ");
        cipherText = new JTextField("", 41);
        cipherText.setEditable(false);
        add(cipherTextLabel);
        add(cipherText);

        start = new JButton("START");
        start.addActionListener(this);
        add(start);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == modeDES) {
            System.out.println("DES");
            des.setDes3(1);

            if (!generateKeys) {
                key1Field.setEditable(true);
                key2Field.setEditable(false);
                key3Field.setEditable(false);
            }
        } else if (source == mode3DES) {
            System.out.println("3DES");
            des.setDes3(3);

            if (!generateKeys) {
                key1Field.setEditable(true);
                key2Field.setEditable(true);
                key3Field.setEditable(true);
            }
        }

        if (source == modeEncrypt) {
            System.out.println("Encrypt");
            encMode = true;
        } else if (source == modeDecrypt) {
            System.out.println("Decrypt");
            encMode = false;
        }

        if (source == modeAuto) {
            System.out.println("Generate keys");
            generateKeys = true;

            key1Field.setEditable(false);
            key2Field.setEditable(false);
            key3Field.setEditable(false);
        } else if (source == modeManual) {
            System.out.println("Manual keys");
            generateKeys = false;

            key1Field.setEditable(true);
            if (des.getDes3() == 3) {
                key2Field.setEditable(true);
                key3Field.setEditable(true);
            }
        }

        if (source == start) {
            System.out.println("STARTING NOW");
            if (!plainText.getText().equals("")) {
                plain = plainText.getText();
            }
            if (!key1Field.getText().equals("")) {
                key1 = key1Field.getText();
            }
            if (!key2Field.getText().equals("")) {
                key2 = key2Field.getText();
            }
            if (!key3Field.getText().equals("")) {
                key3 = key3Field.getText();
            }
            dewIt();

            key1Field.setText(key1);
            if (des.getDes3() == 3) {
                key2Field.setText(key2);
                key3Field.setText(key3);
            } else {
                key2Field.setText("");
                key3Field.setText("");
            }
            cipherText.setText(result);
        }
    }

    private static void createAndShowGUI() {
        MainPanel mainPanel = new MainPanel();

        JFrame jFrame = new JFrame("DES");
        jFrame.setSize(550, 220);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.getContentPane().add(mainPanel);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}