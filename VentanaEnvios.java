package interfaz;

import logica.Envio;
import logica.EnvioEstandar;
import logica.EnvioExpress;
import logica.EnvioInternacional;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VentanaEnvios extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtDestinatario;
    private JTextField txtPeso;
    private JTextField txtAdicional;
    private JLabel lblAdicional;
    private JComboBox<String> cbTipo;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    private ArrayList<Envio> envios = new ArrayList<>();

    public VentanaEnvios() {
        setTitle("Sistema de gestión de envíos");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        construirInterfaz();
        actualizarCampoAdicional();
    }

    private void construirInterfaz() {
        JPanel formulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtCodigo = new JTextField(15);
        txtDestinatario = new JTextField(15);
        txtPeso = new JTextField(15);
        txtAdicional = new JTextField(15);

        cbTipo = new JComboBox<>(new String[]{"Estándar", "Express", "Internacional"});
        cbTipo.addActionListener(e -> actualizarCampoAdicional());

        lblAdicional = new JLabel();

        agregarCampo(formulario, gbc, 0, "Código:", txtCodigo);
        agregarCampo(formulario, gbc, 1, "Destinatario:", txtDestinatario);
        agregarCampo(formulario, gbc, 2, "Peso (kg):", txtPeso);
        agregarCampo(formulario, gbc, 3, "Modalidad:", cbTipo);
        agregarCampo(formulario, gbc, 4, lblAdicional, txtAdicional);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> registrarEnvio());

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formulario.add(btnRegistrar, gbc);

        modeloTabla = new DefaultTableModel(
                new Object[]{"Código", "Destinatario", "Peso", "Tipo", "Dato adicional", "Costo"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);

        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        principal.add(formulario, BorderLayout.NORTH);
        principal.add(scroll, BorderLayout.CENTER);

        setContentPane(principal);
    }

    private void agregarCampo(JPanel panel, GridBagConstraints gbc, int fila, String etiqueta, JComponent componente) {
        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.gridwidth = 1;
        panel.add(new JLabel(etiqueta), gbc);

        gbc.gridx = 1;
        panel.add(componente, gbc);
    }

    private void actualizarCampoAdicional() {
        String tipo = (String) cbTipo.getSelectedItem();

        if ("Estándar".equals(tipo)) {
            lblAdicional.setText("Dirección:");
            txtAdicional.setVisible(true);
            lblAdicional.setVisible(true);
        } else if ("Express".equals(tipo)) {
            lblAdicional.setText("Hora límite:");
            txtAdicional.setVisible(true);
            lblAdicional.setVisible(true);
        } else {
            lblAdicional.setText("País destino:");
            txtAdicional.setVisible(true);
            lblAdicional.setVisible(true);
        }

        revalidate();
        repaint();
    }

    private void registrarEnvio() {
        try {
            String codigo = txtCodigo.getText().trim();
            String destinatario = txtDestinatario.getText().trim();
            double peso = Double.parseDouble(txtPeso.getText().trim());
            String adicional = txtAdicional.getText().trim();
            String tipo = (String) cbTipo.getSelectedItem();

            if (codigo.isEmpty() || destinatario.isEmpty() || adicional.isEmpty() || peso <= 0) {
                JOptionPane.showMessageDialog(this, "Completa todos los campos correctamente.");
                return;
            }

            Envio envio;

            if ("Estándar".equals(tipo)) {
                envio = new EnvioEstandar(codigo, destinatario, peso, adicional);
            } else if ("Express".equals(tipo)) {
                envio = new EnvioExpress(codigo, destinatario, peso, adicional);
            } else {
                envio = new EnvioInternacional(codigo, destinatario, peso, adicional);
            }

            envios.add(envio);

            modeloTabla.addRow(new Object[]{
                    envio.getCodigo(),
                    envio.getDestinatario(),
                    envio.getPeso(),
                    envio.getTipo(),
                    envio.getDatoAdicional(),
                    String.format("$%,.0f", envio.calcularCosto())
            });

            JOptionPane.showMessageDialog(this, "Envío registrado correctamente.");

            limpiarCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El peso debe ser un número válido.");
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtDestinatario.setText("");
        txtPeso.setText("");
        txtAdicional.setText("");
        txtCodigo.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaEnvios ventana = new VentanaEnvios();
            ventana.setVisible(true);
        });
    }
}
