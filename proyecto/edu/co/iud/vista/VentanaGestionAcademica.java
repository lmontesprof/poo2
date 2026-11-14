package proyecto.edu.co.iud.vista;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;

import proyecto.edu.co.iud.excepciones.ReglaNegocioException;
import proyecto.edu.co.iud.modelo.Estudiante;
import proyecto.edu.co.iud.servicio.IEstudianteServiceable;
import proyecto.edu.co.iud.servicio.impl.EstudianteServiceImpl;
import proyecto.edu.co.iud.util.ExcelExporter;

public class VentanaGestionAcademica extends JFrame {
    private final IEstudianteServiceable estudianteService;

    // Componentes del formulario
    private JTextField txtId;
    private JTextField txtNombre;
    private JComboBox<String> cbPrograma;
    private JTextField txtNota;
    private JTextField txtCreditos;

    // Componentes de tabla
    private DefaultTableModel tableModel;
    private JTable tablaEstudiantes;
    private JLabel lblPromedioGeneral;
    private JLabel lblFiltroActual;

    public VentanaGestionAcademica(IEstudianteServiceable estudianteService) {
        this.estudianteService = estudianteService;
        configurarVentana();
        construirMenuPrincipal();
        inicializarComponentes();
        refrescarTabla(estudianteService.listarTodos(), "Todos los Estudiantes");
    }

    public void configurarVentana() {
        setTitle("Gestión Académica");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
    }

    public void construirMenuPrincipal() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        menuArchivo.setMnemonic('A');

        JMenuItem itemExportar = new JMenuItem("Exportar a Excel ");
        itemExportar.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        itemExportar.addActionListener(e -> ejecutarExportacionExcel());

        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        itemSalir.addActionListener(e -> System.exit(0));

        menuArchivo.add(itemExportar);
        menuArchivo.addSeparator();
        menuArchivo.add(itemSalir);

        JMenu menuFiltros = new JMenu("Filtros");
        menuFiltros.setMnemonic('F');

        JMenuItem itemTodos = new JMenuItem("Listar Todos los Estudiantes");
        itemTodos.addActionListener(e -> refrescarTabla(estudianteService.listarTodos(), "Todos los Estudiantes"));

        JMenuItem itemReprobados = new JMenuItem("Listar Estudiantes Reprobados");
        itemReprobados.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

        itemReprobados.addActionListener(
                e -> refrescarTabla(estudianteService.obtenerReprobados(), "Estudiantes Reprobados"));

        menuFiltros.add(itemTodos);
        menuFiltros.add(itemReprobados);
        menuFiltros.addSeparator();


        JMenu menuAyuda = new JMenu("Ayuda");
        menuAyuda.setMnemonic('H');
        JMenuItem itemAcercaDe = new JMenuItem("Acerca de");
        itemAcercaDe.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Sistema de Gestión Académica - IUD\nVersión 1.0\nDesarrollado por el equipo de desarrollo",
                "Acerca de", JOptionPane.INFORMATION_MESSAGE));

         menuAyuda.add(itemAcercaDe);
        menuBar.add(menuArchivo);
        menuBar.add(menuFiltros);
        menuBar.add(menuAyuda);
        setJMenuBar(menuBar);
    }

    public void inicializarComponentes() {
        JPanel panelHeader = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // RGB(0, 0, 255)
        panelHeader.setBackground(new Color(0, 43, 73));
        JLabel lblTitulo = new JLabel("Gestión Académica-IUD");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        panelHeader.add(lblTitulo);
        add(panelHeader, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Estudiante"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        txtId = new JTextField(12);
        panelFormulario.add(txtId, gbc);

        // Nombre
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(12);
        panelFormulario.add(txtNombre, gbc);

        // Programa Académico
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFormulario.add(new JLabel("Programa Académico:"), gbc);
        gbc.gridx = 1;
        String[] programas = { "Ingeniería de Sistemas", "Ingeniería Industrial",
                "Administración de Empresas", "Contaduría Pública" };
        cbPrograma = new JComboBox<>(programas);
        panelFormulario.add(cbPrograma, gbc);

        // Notas
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelFormulario.add(new JLabel("Nota Definitiva:"), gbc);
        gbc.gridx = 1;
        txtNota = new JTextField(12);
        panelFormulario.add(txtNota, gbc);

        // Botón Registrar
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton btnRegistrar = new JButton("Registrar Estudiante");
        btnRegistrar.setBackground(new Color(0, 168, 150));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRegistrar.setFocusPainted(false);
        panelFormulario.add(btnRegistrar, gbc);

        add(panelFormulario, BorderLayout.WEST);

        // Tabla de estudiantes

        String[] columnas = { "ID", "Nombre", "Programa Académico", "Nota Definitiva", "Estado" };
        tableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que las celdas no sean editables
            }
        };
        tablaEstudiantes = new JTable(tableModel);
        tablaEstudiantes.setRowHeight(24);
        tablaEstudiantes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tablaEstudiantes.getTableHeader().setBackground(new Color(0, 43, 73));
        tablaEstudiantes.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollTabla = new JScrollPane(tablaEstudiantes);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Lista de Estudiantes"));
        add(scrollTabla, BorderLayout.CENTER);



              // Panel inferior para promedio general
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        panelInferior.setBackground(new Color(240, 240, 240));
        lblPromedioGeneral = new JLabel("Promedio General: 0.00");
        lblPromedioGeneral.setFont(new Font("Segoe UI", Font.BOLD, 14));

        lblFiltroActual = new JLabel("Filtro Actual: Todos los Estudiantes");
        lblFiltroActual.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblFiltroActual.setForeground(Color.DARK_GRAY);




        JButton btnExportarExcel = new JButton("Exportar a Excel");
        btnExportarExcel.setBackground(new Color(0, 168, 150));
        btnExportarExcel.setForeground(Color.WHITE);
        btnExportarExcel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnExportarExcel.setFocusPainted(false);
        btnExportarExcel.addActionListener(e -> ejecutarExportacionExcel());

        JPanel panelDer= new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        panelDer.add(btnExportarExcel);


        panelInferior.add(lblPromedioGeneral);
        panelInferior.add(lblFiltroActual);
        add(panelInferior, BorderLayout.SOUTH);

        btnRegistrar.addActionListener(e -> ejecutarRegistro());
    }

    private void ejecutarRegistro() {
        try {
            String id = txtId.getText();
            String nombre = txtNombre.getText();
            String programa = (String) cbPrograma.getSelectedItem();
            double notaTxt = Double.parseDouble(txtNota.getText());
            int creditos=12;

            Estudiante estudiante = new Estudiante(id, nombre, programa, creditos, notaTxt);

            estudianteService.registrarEstudiante(estudiante);
            refrescarTabla(estudianteService.listarTodos(), "Todos los Estudiantes");

        } catch (ReglaNegocioException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Validación",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refrescarTabla(List<Estudiante> estudiantes, String filtro) {
        tableModel.setRowCount(0);

        for (Estudiante est : estudiantes) {

            Object[] fila = {
                    est.id(),
                    est.nombre(),
                    est.programa(),
                    String.format("%.2f", est.notaDefinitiva()),
                    est.isAprobado() ? "Aprobado" : "Reprobado"
            };
            tableModel.addRow(fila);
        }
        double promedio = estudianteService.calcularPromedioGeneral();
        lblPromedioGeneral.setText(String.format("Promedio General: %.2f", promedio));
        lblFiltroActual.setText("Filtro Actual: " + filtro );
    }
    public void ejecutarExportacionExcel(){
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Guardar archivo Excel");
        chooser.setSelectedFile(new File("Reportar_Notas.csv"));

        int userSelection = chooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File destino = chooser.getSelectedFile();
            try{
                ExcelExporter.exportarEstudiantes(destino, estudianteService.listarTodos());
                JOptionPane.showMessageDialog(this, "Exportación exitosa a: " + destino.getAbsolutePath(), "Exportación Exitosa", JOptionPane.INFORMATION_MESSAGE); 

            }catch(Exception e){
                JOptionPane.showMessageDialog(this, "Error al exportar: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            IEstudianteServiceable estudianteService = new EstudianteServiceImpl();
            VentanaGestionAcademica ventana = new VentanaGestionAcademica(estudianteService);
            ventana.setVisible(true);
        });
    }

}
