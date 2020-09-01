package com.rk.tiaj;

import javax.swing.*;
import java.awt.*;

public class Form_Utama extends JFrame {

    JList listDays, listMonth;
    String days[] = {
            "Senin",
            "Selasa",
            "Rabu",
            "Kamis",
            "Jum'at",
            "Sabtu",
            "Minggu"
    };

    String months[] = {
            "Januari",
            "Februari",
            "Maret",
            "April",
            "Mei",
            "Juni",
            "Juli",
            "Agustus",
            "September",
            "Oktober",
            "November",
            "Desember"
    };

    TransferHandler handler = new PelakuTransfer_PadaJList();

    public Form_Utama() {
        super("Percobaan JList Transfer");
        Inisialisasi_Komponen();

    }

    public void Inisialisasi_Komponen() {
        setLayout(new GridLayout(1, 2, 20, 0));

        listDays = new JList(getModel(days));
        listDays.setDropMode(DropMode.INSERT);
        listDays.setDragEnabled(true);
        listDays.setTransferHandler(handler);
        add(new JScrollPane(listDays), BorderLayout.WEST);

        listMonth = new JList(getModel(months));
        listMonth.setDropMode(DropMode.INSERT);
        listMonth.setDragEnabled(true);
        listMonth.setTransferHandler(handler);
        add(new JScrollPane(listMonth), BorderLayout.EAST);

        setPreferredSize(new Dimension(250, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public DefaultListModel getModel(String datas[]) {
        DefaultListModel model = new DefaultListModel();

        for (int i = 0; i < datas.length; i++) {
            model.addElement(datas[i]);
        }

        return model;
    }

}
