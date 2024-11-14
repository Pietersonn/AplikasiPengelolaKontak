package aplikasipengelolakontak;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {

    // Method untuk menambahkan kontak baru
    public static void addContact(Contact contact) throws SQLException {
        if (contact == null) {
            throw new IllegalArgumentException("Contact tidak boleh null.");
        }

        String sql = "INSERT INTO kontak (nama, nomor_telepon, kategori) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contact.getNama());
            stmt.setString(2, contact.getNomorTelepon());
            stmt.setString(3, contact.getKategori());
            stmt.executeUpdate();
        }
    }

    // Method untuk mendapatkan semua kontak
    public static List<Contact> getAllContacts() throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM kontak";
        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                contacts.add(new Contact(
                        rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getString("nomor_telepon"),
                        rs.getString("kategori")
                ));
            }
        }
        return contacts;
    }

    // Method untuk memperbarui data kontak berdasarkan ID
    public static void updateContact(Contact contact, int id) throws SQLException {
        if (contact == null) {
            throw new IllegalArgumentException("Contact tidak boleh null.");
        }

        String sql = "UPDATE kontak SET nama = ?, nomor_telepon = ?, kategori = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contact.getNama());
            stmt.setString(2, contact.getNomorTelepon());
            stmt.setString(3, contact.getKategori());
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
    }

    // Method untuk menghapus kontak berdasarkan ID
    public static void deleteContact(int id) throws SQLException {
        String sql = "DELETE FROM kontak WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Method untuk mencari kontak berdasarkan query (nama atau nomor telepon)
    public static List<Contact> searchContacts(String nama, String nomorTelepon, String kategori) throws SQLException {
        List<Contact> contacts = new ArrayList<>();

        // SQL dasar dengan kondisi dinamis
        StringBuilder sql = new StringBuilder("SELECT * FROM kontak WHERE 1=1");

        // Tambahkan kondisi berdasarkan parameter yang diisi
        if (nama != null && !nama.trim().isEmpty()) {
            sql.append(" AND nama LIKE ?");
        }
        if (nomorTelepon != null && !nomorTelepon.trim().isEmpty()) {
            sql.append(" AND nomor_telepon LIKE ?");
        }
        if (kategori != null && !kategori.trim().isEmpty()) {
            sql.append(" AND kategori = ?");
        }

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            // Set nilai parameter sesuai urutan
            int index = 1;
            if (nama != null && !nama.trim().isEmpty()) {
                stmt.setString(index++, "%" + nama + "%");
            }
            if (nomorTelepon != null && !nomorTelepon.trim().isEmpty()) {
                stmt.setString(index++, "%" + nomorTelepon + "%");
            }
            if (kategori != null && !kategori.trim().isEmpty()) {
                stmt.setString(index++, kategori);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    contacts.add(new Contact(
                            rs.getInt("id"),
                            rs.getString("nama"),
                            rs.getString("nomor_telepon"),
                            rs.getString("kategori")
                    ));
                }
            }
        }
        return contacts;
    }

    // Method tambahan untuk mendapatkan kontak berdasarkan ID
    public static Contact getContactById(int id) throws SQLException {
        String sql = "SELECT * FROM kontak WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Contact(
                            rs.getInt("id"),
                            rs.getString("nama"),
                            rs.getString("nomor_telepon"),
                            rs.getString("kategori")
                    );
                }
            }
        }
        return null; // Jika kontak tidak ditemukan
    }
}
