import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Ventana {
    private JPanel Principal;
    private JTabbedPane tabbedPane1;
    private JComboBox cmbOrdenarPrecio;
    private JButton btmOrdenarPrecio;
    private JTextArea txtOrdenarPrecio;
    private JTextField txtBuscarPorColor;
    private JButton buscarPorColorButton;
    private JTextArea txtBuscaadorColor;
    private JTextField txtEliminarCodigo;
    private JButton eliminarMotocicletaButton;
    private JTextField txtCodigo;
    private JComboBox cmbMarca;
    private JTextField txtCilindraje;
    private JComboBox cmbColor;
    private JButton btmRegistrar;
    private JTextField txtPrecio;

    private MotocicletaManager motocicletaManager1=new MotocicletaManager();

    public Ventana(){

        btmRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(txtCodigo.getText());
                    String marca = cmbMarca.getSelectedItem().toString();
                    int cilindraje = Integer.parseInt(txtCilindraje.getText());
                    String color = cmbColor.getSelectedItem().toString();
                    float precio = Float.parseFloat(txtPrecio.getText());

                    Motocicleta nueva = new Motocicleta(codigo, marca, cilindraje, color, precio);
                    boolean agregado = motocicletaManager1.agregarOActualizarMotocicleta(nueva);

                    if (agregado) {
                        JOptionPane.showMessageDialog(null, "Motocicleta agregada exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Motocicleta actualizada exitosamente.");
                    }

                    txtOrdenarPrecio.setText(motocicletaManager1.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        btmOrdenarPrecio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Verificar la selección en el combo box
                    String ordenSeleccionado = cmbOrdenarPrecio.getSelectedItem().toString();
                    boolean ascendente = ordenSeleccionado.equalsIgnoreCase("Ascendente");

                    // Ordenar la lista en función de la selección
                    motocicletaManager1.ordenarPorPrecio(ascendente);

                    // Actualizar el área de texto con la lista ordenada
                    txtOrdenarPrecio.setText(motocicletaManager1.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al ordenar: " + ex.getMessage());
                }
            }
        });


        buscarPorColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String color = txtBuscarPorColor.getText();
                List<Motocicleta> listaFiltrada = new ArrayList<>();

                for (Motocicleta moto : motocicletaManager1.getMotocicletas()) {
                    if (moto.getColor().equalsIgnoreCase(color)) {
                        listaFiltrada.add(moto);
                    }
                }

                listaFiltrada.sort((m1, m2) -> Float.compare(m1.getPrecio(), m2.getPrecio()));

                StringBuilder resultado = new StringBuilder();
                for (Motocicleta moto : listaFiltrada) {
                    resultado.append(moto.toString()).append("\n");
                }

                txtBuscaadorColor.setText(resultado.toString());
            }
        });

        eliminarMotocicletaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(txtEliminarCodigo.getText());
                    boolean eliminado = motocicletaManager1.eliminarMotocicleta(codigo);

                    if (eliminado) {
                        JOptionPane.showMessageDialog(null, "Motocicleta eliminada exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Motocicleta no encontrada.");
                    }

                    txtOrdenarPrecio.setText(motocicletaManager1.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Código inválido.");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().Principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
