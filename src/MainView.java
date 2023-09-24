import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainView {
    private JFrame frame;
    private JComboBox<String> sedeComboBox;
    private JComboBox<String> campusComboBox;
    private ArbolAVL arbolAVL;
    private List<NodoAVL> librosAlmacenados;

    

    public MainView() {
        frame = new JFrame("Menú Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);

        arbolAVL = new ArbolAVL();
        librosAlmacenados = new ArrayList<>();

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        JButton almacenarLibroButton = new JButton("Crear Libros");
        JButton verLibrosButton = new JButton("Buscar Libros");
        JButton eliminarLibroButton = new JButton("Eliminar Libro");


        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(almacenarLibroButton, constraints);

        constraints.gridy = 1;
        panel.add(verLibrosButton, constraints);

        constraints.gridy = 2;
        panel.add(eliminarLibroButton, constraints);




        almacenarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaAlmacenar();
            }
        });

        verLibrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaVerLibros();
            }
        });

        eliminarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaEliminarLibro();
            }
        });

        JButton listarTodosLosLibrosButton = new JButton("Listar Todos los Libros");

        constraints.gridy = 4;
        panel.add(listarTodosLosLibrosButton, constraints);

        listarTodosLosLibrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarTodosLosLibros();
            }
        });

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private void abrirVentanaAlmacenar() {
        JFrame almacenarFrame = new JFrame("Almacenar Libro");
        almacenarFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        almacenarFrame.setSize(1000, 1000);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel sedeLabel = new JLabel("Seleccione la sede:");

        sedeComboBox = new JComboBox<>(new String[]{"Tunja", "Duitama"});

        JLabel campusLabel = new JLabel("Seleccione el campus:");

        campusComboBox = new JComboBox<>(new String[]{""});

        sedeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sedeSeleccionada = (String) sedeComboBox.getSelectedItem();
                actualizarCampusDisponibles(campusComboBox, sedeSeleccionada);
            }
        });

        JLabel tituloLabel = new JLabel("Título:");
        JLabel isbnLabel = new JLabel("ISBN:");
        JLabel autorLabel = new JLabel("Autor:");
        JLabel editorialLabel = new JLabel("Editorial:");
        JLabel volumenLabel = new JLabel("Volumen:");
        JLabel biografiaAutorLabel = new JLabel("Biografía del Autor:");

        JTextField tituloField = new JTextField();
        JTextField isbnField = new JTextField();
        JTextField autorField = new JTextField();
        JTextField editorialField = new JTextField();
        JTextField volumenField = new JTextField();
        JTextField biografiaAutorField = new JTextField();

        JButton almacenarButton = new JButton("Almacenar Libro");

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(sedeLabel, constraints);

        constraints.gridx = 1;
        panel.add(sedeComboBox, constraints);

        constraints.gridy = 1;
        constraints.gridx = 0;
        panel.add(campusLabel, constraints);

        constraints.gridx = 1;
        panel.add(campusComboBox, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        panel.add(tituloLabel, constraints);

        constraints.gridx = 1;
        panel.add(tituloField, constraints);

        constraints.gridy = 3;
        constraints.gridx = 0;
        panel.add(isbnLabel, constraints);

        constraints.gridx = 1;
        panel.add(isbnField, constraints);

        constraints.gridy = 4;
        constraints.gridx = 0;
        panel.add(autorLabel, constraints);

        constraints.gridx = 1;
        panel.add(autorField, constraints);

        constraints.gridy = 5;
        constraints.gridx = 0;
        panel.add(editorialLabel, constraints);

        constraints.gridx = 1;
        panel.add(editorialField, constraints);

        constraints.gridy = 6;
        constraints.gridx = 0;
        panel.add(volumenLabel, constraints);

        constraints.gridx = 1;
        panel.add(volumenField, constraints);

        constraints.gridy = 7;
        constraints.gridx = 0;
        panel.add(biografiaAutorLabel, constraints);

        constraints.gridx = 1;
        panel.add(biografiaAutorField, constraints);

        almacenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sedeSeleccionada = (String) sedeComboBox.getSelectedItem();
                String campusSeleccionado = (String) campusComboBox.getSelectedItem();
                String titulo = tituloField.getText();
                int isbn = Integer.parseInt(isbnField.getText());
                String autor = autorField.getText();
                String editorial = editorialField.getText();
                String volumen = volumenField.getText();
                String biografiaAutor = biografiaAutorField.getText();

                arbolAVL.insertar(isbn, titulo, volumen, editorial, campusSeleccionado, autor, biografiaAutor);
                NodoAVL nuevoLibro = new NodoAVL(isbn, titulo, volumen, editorial, campusSeleccionado, autor, biografiaAutor);
                librosAlmacenados.add(nuevoLibro);
                JOptionPane.showMessageDialog(almacenarFrame, "Libro almacenado con éxito en '" + sedeSeleccionada + "' - '" + campusSeleccionado + "'.");
            }
        });

        constraints.gridy = 8;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        panel.add(almacenarButton, constraints);

        almacenarFrame.getContentPane().add(panel);
        almacenarFrame.setVisible(true);
    }

    private void abrirVentanaVerLibros() {
        JFrame verLibrosFrame = new JFrame("Ver Libros");
        verLibrosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        verLibrosFrame.setSize(1000, 1000);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel sedeLabelVer = new JLabel("Seleccione la sede:");

        JComboBox<String> sedeComboBoxVer = new JComboBox<>(new String[]{"Tunja", "Duitama"});

        JLabel campusLabelVer = new JLabel("Seleccione el campus:");

        JComboBox<String> campusComboBoxVer = new JComboBox<>(new String[]{""});

        sedeComboBoxVer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sedeSeleccionada = (String) sedeComboBoxVer.getSelectedItem();
                actualizarCampusDisponibles(campusComboBoxVer, sedeSeleccionada);
            }
        });

        JButton verButton = new JButton("Ver Libros");

        JTextArea resultadosTextArea = new JTextArea(20, 60);
        resultadosTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadosTextArea);

        JLabel copiasLabel = new JLabel("Seleccione número de copias por ISBN:");
        JComboBox<Integer> copiasComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});

        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sedeSeleccionada = (String) sedeComboBoxVer.getSelectedItem();
                String campusSeleccionado = (String) campusComboBoxVer.getSelectedItem();
                int copiasSeleccionadas = (int) copiasComboBox.getSelectedItem();
                mostrarLibrosPorSedeYFacultad(sedeSeleccionada, campusSeleccionado, copiasSeleccionadas, resultadosTextArea);
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(sedeLabelVer, constraints);

        constraints.gridx = 1;
        panel.add(sedeComboBoxVer, constraints);

        constraints.gridy = 1;
        constraints.gridx = 0;
        panel.add(campusLabelVer, constraints);

        constraints.gridx = 1;
        panel.add(campusComboBoxVer, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        panel.add(copiasLabel, constraints);

        constraints.gridx = 1;
        panel.add(copiasComboBox, constraints);

        constraints.gridy = 3;
        constraints.gridx = 0;
        panel.add(verButton, constraints);

        constraints.gridy = 4;
        constraints.gridx = 0;
        panel.add(scrollPane, constraints);

        verLibrosFrame.getContentPane().add(panel);
        verLibrosFrame.setVisible(true);
    }

    private void mostrarLibrosPorSedeYFacultad(String sede, String campus, int copiasSeleccionadas, JTextArea resultadosTextArea) {
        resultadosTextArea.setText("");
        for (NodoAVL libro : librosAlmacenados) {
            if (libro.biblioteca.equals(campus) && arbolAVL.obtenerCopiasPorISBN(libro.isbn) >= copiasSeleccionadas) {
                resultadosTextArea.append("Título: " + libro.titulo + "\n");
                resultadosTextArea.append("ISBN: " + libro.isbn + "\n");
                resultadosTextArea.append("Autor: " + libro.autor + "\n");
                resultadosTextArea.append("Editorial: " + libro.editorial + "\n\n");
            }
        }
    }

    private void actualizarCampusDisponibles(JComboBox<String> campusComboBox, String sedeSeleccionada) {
        campusComboBox.removeAllItems();
        if (sedeSeleccionada != null) {
            if (sedeSeleccionada.equals("Tunja")) {
                campusComboBox.addItem("Facultad de medicina");
                campusComboBox.addItem("Edificio Central – FESAD");
                campusComboBox.addItem("Facultad de estudios a distancia");
            } else if (sedeSeleccionada.equals("Duitama")) {
                campusComboBox.addItem("Centro Regional");
            }
        }
    }

    private void abrirVentanaEliminarLibro() {
        JFrame eliminarLibroFrame = new JFrame("Eliminar Libro");
        eliminarLibroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        eliminarLibroFrame.setSize(1000, 1000);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel isbnLabel = new JLabel("ISBN del libro a eliminar:");
        JTextField isbnField = new JTextField();
        isbnField.setColumns(15);

        JButton eliminarButton = new JButton("Eliminar Libro");

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(isbnLabel, constraints);

        constraints.gridx = 1;
        panel.add(isbnField, constraints);

        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        panel.add(eliminarButton, constraints);

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int isbn = Integer.parseInt(isbnField.getText());

                    NodoAVL libroAEliminar = buscarLibroPorISBN(isbn);

                    if (libroAEliminar != null) {
                        arbolAVL.raiz = arbolAVL.eliminar(arbolAVL.raiz, isbn);

                        librosAlmacenados.remove(libroAEliminar);

                        JOptionPane.showMessageDialog(eliminarLibroFrame, "Libro eliminado con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(eliminarLibroFrame, "No se encontró un libro con ese ISBN.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(eliminarLibroFrame, "Por favor, ingresa un ISBN válido.");
                }
            }
        });

        eliminarLibroFrame.getContentPane().add(panel);
        eliminarLibroFrame.setVisible(true);
    }

    private NodoAVL buscarLibroPorISBN(int isbn) {
        for (NodoAVL libro : librosAlmacenados) {
            if (libro.isbn == isbn) {
                return libro;
            }
        }
        return null;
    }

    private void listarTodosLosLibros() {
        JFrame listarLibrosFrame = new JFrame("Listar Libros");
        listarLibrosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        listarLibrosFrame.setSize(1000, 1000);

        JTextArea resultadosTextArea = new JTextArea(20, 60);
        resultadosTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadosTextArea);

        for (NodoAVL libro : librosAlmacenados) {
            resultadosTextArea.append("Facultad: " + libro.biblioteca + "\n");
            resultadosTextArea.append("Título: " + libro.titulo + "\n");
            resultadosTextArea.append("ISBN: " + libro.isbn + "\n");
            resultadosTextArea.append("Autor: " + libro.autor + "\n");
            resultadosTextArea.append("Editorial: " + libro.editorial + "\n\n");
        }

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(scrollPane, constraints);

        listarLibrosFrame.getContentPane().add(panel);
        listarLibrosFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainView();
            }
        });
    }
}

