package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RadioPanel extends JPanel implements ActionListener{

    private JRadioButton modeDES;
    private JRadioButton mode3DES;
    private JRadioButton modeEncrypt;
    private JRadioButton modeDecrypt;
    private JRadioButton modeAuto;
    private JRadioButton modeManual;

    public RadioPanel() {
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == modeDES) {
            System.out.println("DES");
        }
        else if(source == mode3DES)
            System.out.println("3DES");

        if(source == modeEncrypt){
            System.out.println("Encrypt");
        }
        else if(source == modeDecrypt){
            System.out.println("Decrypt");
        }

        if(source == modeAuto){
            System.out.println("Generate keys");
        }
        else if(source == modeManual){
            System.out.println("Manual keys");
        }
    }
}