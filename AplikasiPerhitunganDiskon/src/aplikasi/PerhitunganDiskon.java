package aplikasi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PerhitunganDiskon extends JFrame {
    private JTextField txtHargaAsli, txtHargaAkhir, txtHemat;
    private JComboBox<String> comboDiskon;
    private JTextArea areaRiwayat;
    private JButton btnHitung, btnClear;

    public PerhitunganDiskon() {
        setTitle("Aplikasi Perhitungan Diskon");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout(10,10));

        JPanel panelInput = new JPanel(new GridLayout(3,2,5,5));
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Harga dan Diskon"));

        panelInput.add(new JLabel("Harga Asli:"));
        txtHargaAsli = new JTextField();
        panelInput.add(txtHargaAsli);

        panelInput.add(new JLabel("Persentase Diskon:"));
        comboDiskon = new JComboBox<>(new String[]{"5%", "10%", "15%", "20%", "25%", "50%"});
        panelInput.add(comboDiskon);

        panelInput.add(new JLabel("Harga Akhir:"));
        txtHargaAkhir = new JTextField();
        txtHargaAkhir.setEditable(false);
        panelInput.add(txtHargaAkhir);

        add(panelInput, BorderLayout.NORTH);

        JPanel panelHasil = new JPanel(new GridLayout(1,2,5,5));
        panelHasil.setBorder(BorderFactory.createTitledBorder("Hasil Penghematan"));

        panelHasil.add(new JLabel("Jumlah Hemat:"));
        txtHemat = new JTextField();
        txtHemat.setEditable(false);
        panelHasil.add(txtHemat);

        add(panelHasil, BorderLayout.CENTER);

        JPanel panelBawah = new JPanel(new BorderLayout(5,5));

        JPanel panelTombol = new JPanel();
        btnHitung = new JButton("Hitung");
        btnClear = new JButton("Clear");
        panelTombol.add(btnHitung);
        panelTombol.add(btnClear);
        panelBawah.add(panelTombol, BorderLayout.NORTH);

        areaRiwayat = new JTextArea();
        areaRiwayat.setBorder(BorderFactory.createTitledBorder("Riwayat Perhitungan"));
        areaRiwayat.setEditable(false);
        panelBawah.add(new JScrollPane(areaRiwayat), BorderLayout.CENTER);

        add(panelBawah, BorderLayout.SOUTH);

        btnHitung.addActionListener(e -> hitungDiskon());
        btnClear.addActionListener(e -> {
            txtHargaAsli.setText("");
            txtHargaAkhir.setText("");
            txtHemat.setText("");
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void hitungDiskon() {
        try {
            double hargaAsli = Double.parseDouble(txtHargaAsli.getText());
            String diskonStr = comboDiskon.getSelectedItem().toString().replace("%", "");
            double diskon = Double.parseDouble(diskonStr);
            double hemat = hargaAsli * diskon / 100;
            double hargaAkhir = hargaAsli - hemat;

            txtHargaAkhir.setText(String.format("%.2f", hargaAkhir));
            txtHemat.setText(String.format("%.2f", hemat));

            areaRiwayat.append("Harga Asli: Rp" + hargaAsli + 
                               " | Diskon: " + diskon + "%" +
                               " | Harga Akhir: Rp" + hargaAkhir + "\n");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Masukkan harga asli dalam angka!", 
                "Error Input", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PerhitunganDiskon());
    }
}
