/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasipengelolakontak;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ContactManagerUI extends JFrame {

    private JTable table;
    private JTextField nameField;
    private JTextField phoneField;
    private JComboBox<String> categoryBox;
    private DefaultTableModel tableModel;

    public ContactManagerUI() {
        setTitle("Contact Manager");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel nameLabel = new JLabel("Nama:");
        nameLabel.setBounds(20, 20, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 20, 200, 25);
        add(nameField);

        JLabel phoneLabel = new JLabel("Nomor Telepon:");
        phoneLabel.setBounds(20, 60, 100, 25);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(120, 60, 180, 25);
        add(phoneField);

        JLabel categoryLabel = new JLabel("Kategori:");
        categoryLabel.setBounds(20, 100, 80, 25);
        add(categoryLabel);

        categoryBox = new JComboBox<>(new String[]{"Keluarga", "Teman", "Kerja"});
        categoryBox.setBounds(100, 100, 200, 25);
        add(categoryBox);

        JButton addButton = new JButton("Tambah");
        addButton.setBounds(320, 20, 100, 25);
        add(addButton);

        JButton searchButton = new JButton("Cari");
        searchButton.setBounds(320, 60, 100, 25);
        add(searchButton);

        tableModel = new DefaultTableModel(new String[]{"Nama", "Nomor Telepon", "Kategori"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 150, 750, 350);
        add(scrollPane);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContact();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchContact();
            }
        });

        loadContactsToTable();
    }

    private void addContact() {
        String nama = nameField.getText();
        String nomorTelepon = phoneField.getText();
        String kategori = (String) categoryBox.getSelectedItem();

        if (!nomorTelepon.matches("\\d{10,13}")) {
            JOptionPane.showMessageDialog(this, "Nomor telepon harus angka dengan panjang 10-13 digit!");
            return;
        }

        Contact contact = new Contact(nama, nomorTelepon, kategori);
        try {
            ContactDAO.addContact(contact);
            JOptionPane.showMessageDialog(this, "Kontak berhasil ditambahkan.");
            loadContactsToTable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan kontak: " + e.getMessage());
        }
    }

    private void loadContactsToTable() {
        try {
            List<Contact> contacts = ContactDAO.getAllContacts();
            tableModel.setRowCount(0);
            for (Contact contact : contacts) {
                tableModel.addRow(new Object[]{contact.getNama(), contact.getNomorTelepon(), contact.getKategori()});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat kontak: " + e.getMessage());
        }
    }

    private void searchContact() {
        String nama = nameField.getText();  // Field untuk nama
        String nomorTelepon = phoneField.getText();  // Field untuk nomor telepon
        String kategori = (String) categoryBox.getSelectedItem();  // ComboBox untuk kategori

        try {
            // Pencarian dengan nama, nomor telepon, dan kategori
            List<Contact> contacts = ContactDAO.searchContacts(nama, nomorTelepon, kategori);
            tableModel.setRowCount(0);
            for (Contact contact : contacts) {
                tableModel.addRow(new Object[]{contact.getNama(), contact.getNomorTelepon(), contact.getKategori()});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mencari kontak: " + e.getMessage());
        }
    }

    private void saveToCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Simpan Kontak sebagai CSV");

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File csvFile = fileChooser.getSelectedFile();

            // Cek apakah file sudah ada
            if (csvFile.exists()) {
                int response = JOptionPane.showConfirmDialog(this,
                        "File sudah ada. Apakah Anda ingin menimpa file tersebut?",
                        "Konfirmasi", JOptionPane.YES_NO_OPTION);

                if (response != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            try (FileWriter writer = new FileWriter(csvFile)) {
                List<Contact> contacts = ContactDAO.getAllContacts();
                writer.write("Nama,Nomor Telepon,Kategori\n");
                for (Contact contact : contacts) {
                    writer.write(contact.getNama() + "," + contact.getNomorTelepon() + "," + contact.getKategori() + "\n");
                }
                JOptionPane.showMessageDialog(this, "Kontak berhasil disimpan ke " + csvFile.getAbsolutePath());
            } catch (IOException | SQLException e) {
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyimpan kontak: " + e.getMessage());
            }
        }
    }

    private List<Contact> readContactsFromCSV(File csvFile) throws IOException {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            reader.readLine(); // Lewati baris header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String nama = parts[0];
                    String nomorTelepon = parts[1];
                    String kategori = parts[2];
                    contacts.add(new Contact(nama, nomorTelepon, kategori));
                }
            }
        }
        return contacts;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ContactManagerUI ui = new ContactManagerUI();
            ui.setVisible(true);
        });
    }
}
