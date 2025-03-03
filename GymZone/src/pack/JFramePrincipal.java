package pack;


import com.mysql.cj.api.jdbc.Statement;
import java.awt.Color;
import java.awt.Component;
import java.net.URL;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Samuel
 */
public class JFramePrincipal extends javax.swing.JFrame {

    private static final String URL = "jdbc:mysql://localhost:3306/gym_zone";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    private static final String REGEZ_DNI="\\d{8}[A-HJ-NP-TV-Z]";
    private static final String REGEZ_CORREO="^(?=.*\\d.*\\d)[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(es|com)$";
    private static final String REGEZ_NOMBRE_USUARIO="^(?=.*[a-zA-Z]{6,})(?=.*\\d{2,}).*$";
    private static final String REGEZ_CONTRASENNA="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d.*\\d.*\\d).{8,}$";
    private static final String REGEZ_NOMBRE_APELLIDOS = "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü]{2,} [A-Za-zÁÉÍÓÚáéíóúÑñÜü]{2,} [A-Za-zÁÉÍÓÚáéíóúÑñÜü]{2,}$";
    private static final String REGEZ_NUMERO_CUENTA="^[0-9]{4}-?[0-9]{4}-?[0-9]{2}-?[0-9]{10}$";
    private static final String REGEZ_CADENA="^[a-zA-Z ]+$";
    
    private static final String SERVICIO_RESERVA="Gimnasio";
    private static final String SERVICIO_RESERVA_MONITOR="Entrenamiento Monitoreado";
    private static final String SERVICIO_RUTINA="Consulta Rutina";
    private static final String SERVICIO_DIETA="Consulta Dieta";
    
    private static Connection con;
    
    public static String rolUsuario="";
    public static String dniUsuario="";
    public static int idUsuario=0;
    
    public static int idRutina=0;
    public static int diasRutina=0;
    public static int contadorDias=1;
    public static String nombreRutina="";
    public static String tipoObjetivo="";
    
    public static int idDieta=0;
    public static int idComida=0;
    public static int contadorComida=1;
    public static int diasDieta=1;
    public static String nombreDieta="";
    public static String tipoObjetivoDieta="";
    public static String descripcionDieta="";
    
    public static int idProducto=0, idServicio=0, idSala=0;
    
    HashMap<Integer, ArrayList<String>> mapaRutina = new HashMap<Integer, ArrayList<String>>();
    
    private static HashMap<String, ArrayList<String>> mapaComida = new HashMap<String, ArrayList<String>>();
    
    private static HashMap<Integer, HashMap<String, ArrayList<String>>> mapaDieta = new HashMap<Integer, HashMap<String, ArrayList<String>>>();
    
    private static ArrayList<String> ejerciciosSeleccionados = new ArrayList<>();
    private static ArrayList<String> alimentosSeleccionados = new ArrayList<>();
    
    
    
    /**
     * Creates new form JFramePrincipal
     */
    public JFramePrincipal() {
        
        
        
        initComponents();
        
        // Definición de las vistas

        this.setTitle("Gym Zone | Seleccion Rol");
        this.setResizable(false);
        this.jDialog_Inicio.setLocationRelativeTo(null);
        
        this.jDialog_Inicio.setTitle("Gym Zone | Inicio");
        this.jDialog_Inicio.setResizable(false);
        this.jDialog_Inicio.setLocationRelativeTo(null);
        this.jDialog_Inicio.setSize(900, 500);
        
        this.jDialog_IniciarSesion.setTitle("Gym Zone | Iniciar Sesión");
        this.jDialog_IniciarSesion.setResizable(false);
        this.jDialog_IniciarSesion.setLocationRelativeTo(null);
        this.jDialog_IniciarSesion.setSize(900, 500);
        
        this.jDialog_Registrarse.setTitle("Gym Zone | Registrarse");
        this.jDialog_Registrarse.setResizable(false);
        this.jDialog_Registrarse.setLocationRelativeTo(null);
        this.jDialog_Registrarse.setSize(900, 500);
        
        this.jDialog_RegistrarseCliente.setTitle("Gym Zone | Registro Cliente");
        this.jDialog_RegistrarseCliente.setResizable(false);
        this.jDialog_RegistrarseCliente.setLocationRelativeTo(null);
        this.jDialog_RegistrarseCliente.setSize(900, 500);
        
        this.jDialog_RegistrarseNutricionista.setTitle("Gym Zone | Registro Nutricionista");
        this.jDialog_RegistrarseNutricionista.setResizable(false);
        this.jDialog_RegistrarseNutricionista.setLocationRelativeTo(null);
        this.jDialog_RegistrarseNutricionista.setSize(900, 500);
        
        this.jDialog_RegistrarseMonitor.setTitle("Gym Zone | Registro Monitor");
        this.jDialog_RegistrarseMonitor.setResizable(false);
        this.jDialog_RegistrarseMonitor.setLocationRelativeTo(null);
        this.jDialog_RegistrarseMonitor.setSize(900, 500);
        
        this.jDialog_GestionCuentaBancaria.setTitle("Gym Zone | Gestión Cuenta Bancaria");
        this.jDialog_GestionCuentaBancaria.setResizable(false);
        this.jDialog_GestionCuentaBancaria.setLocationRelativeTo(null);
        this.jDialog_GestionCuentaBancaria.setSize(900, 250);
        
        this.jDialog_RegistrarCuentaBancaria.setTitle("Gym Zone | Registrar Cuenta Bancaria");
        this.jDialog_RegistrarCuentaBancaria.setResizable(false);
        this.jDialog_RegistrarCuentaBancaria.setLocationRelativeTo(null);
        this.jDialog_RegistrarCuentaBancaria.setSize(900, 500);
        
        this.jDialog_ConsultaCuentaBancaria.setTitle("Gym Zone | Consulta Cuenta Bancaria");
        this.jDialog_ConsultaCuentaBancaria.setResizable(false);
        this.jDialog_ConsultaCuentaBancaria.setLocationRelativeTo(null);
        this.jDialog_ConsultaCuentaBancaria.setSize(900, 500);
        
        this.jDialog_ModificarCuentaBancaria.setTitle("Gym Zone | Modificar Cuenta Bancaria");
        this.jDialog_ModificarCuentaBancaria.setResizable(false);
        this.jDialog_ModificarCuentaBancaria.setLocationRelativeTo(null);
        this.jDialog_ModificarCuentaBancaria.setSize(900, 500);
        
        
        this.jDialog_FuncionalidadCliente.setTitle("Gym Zone | Funcionalidades Cliente");
        this.jDialog_FuncionalidadCliente.setResizable(false);
        this.jDialog_FuncionalidadCliente.setLocationRelativeTo(null);
        this.jDialog_FuncionalidadCliente.setSize(900, 500);
        
        this.jDialog_FuncionalidadNutricionista.setTitle("Gym Zone | Funcionalidades Nutricionista");
        this.jDialog_FuncionalidadNutricionista.setResizable(false);
        this.jDialog_FuncionalidadNutricionista.setLocationRelativeTo(null);
        this.jDialog_FuncionalidadNutricionista.setSize(900, 500);
        
        this.jDialog_FuncionalidadMonitor.setTitle("Gym Zone | Funcionalidades Monitor");
        this.jDialog_FuncionalidadMonitor.setResizable(false);
        this.jDialog_FuncionalidadMonitor.setLocationRelativeTo(null);
        this.jDialog_FuncionalidadMonitor.setSize(900, 500);
        
        
        this.jDialog_FuncionalidadAdministrador.setTitle("Gym Zone | Funcionalidades Administrador");
        this.jDialog_FuncionalidadAdministrador.setResizable(false);
        this.jDialog_FuncionalidadAdministrador.setLocationRelativeTo(null);
        this.jDialog_FuncionalidadAdministrador.setSize(900, 500);
        
        this.jDialog_PerfilUsuario.setTitle("Gym Zone | Perfil Usuario");
        this.jDialog_PerfilUsuario.setResizable(false);
        this.jDialog_PerfilUsuario.setLocationRelativeTo(null);
        this.jDialog_PerfilUsuario.setSize(900, 500);
        
        this.jDialog_AltaRutina.setTitle("Gym Zone | Alta Rutina");
        this.jDialog_AltaRutina.setResizable(false);
        this.jDialog_AltaRutina.setLocationRelativeTo(null);
        this.jDialog_AltaRutina.setSize(900, 500);

        this.jDialog_RutinaDia.setTitle("Gym Zone | Alta Rutina Dias");
        this.jDialog_RutinaDia.setResizable(false);
        this.jDialog_RutinaDia.setLocationRelativeTo(null);
        this.jDialog_RutinaDia.setSize(900, 500);
        
        this.jDialog_ConsultaRutina.setTitle("Gym Zone | Consulta Rutina");
        this.jDialog_ConsultaRutina.setResizable(false);
        this.jDialog_ConsultaRutina.setLocationRelativeTo(null);
        this.jDialog_ConsultaRutina.setSize(900, 500);
        
        this.jDialog_ConsultaRutina_VerEjercicios.setTitle("Gym Zone | Visualizar Ejercicios Rutina");
        this.jDialog_ConsultaRutina_VerEjercicios.setResizable(false);
        this.jDialog_ConsultaRutina_VerEjercicios.setLocationRelativeTo(null);
        this.jDialog_ConsultaRutina_VerEjercicios.setSize(900, 500);
        
        this.jDialog_ConsultaRutina_ModificarRutina.setTitle("Gym Zone | Modificar Ejercicios Rutina");
        this.jDialog_ConsultaRutina_ModificarRutina.setResizable(false);
        this.jDialog_ConsultaRutina_ModificarRutina.setLocationRelativeTo(null);
        this.jDialog_ConsultaRutina_ModificarRutina.setSize(900, 500);
        
        this.jDialog_AltaDieta.setTitle("Gym Zone | Alta Dieta");
        this.jDialog_AltaDieta.setResizable(false);
        this.jDialog_AltaDieta.setLocationRelativeTo(null);
        this.jDialog_AltaDieta.setSize(900, 500);
        
        this.jDialog_DietaDia.setTitle("Gym Zone | Alta Dieta Dias");
        this.jDialog_DietaDia.setResizable(false);
        this.jDialog_DietaDia.setLocationRelativeTo(null);
        this.jDialog_DietaDia.setSize(900, 500);
        
        this.jDialog_ConsultaDieta.setTitle("Gym Zone | Consulta Dieta");
        this.jDialog_ConsultaDieta.setResizable(false);
        this.jDialog_ConsultaDieta.setLocationRelativeTo(null);
        this.jDialog_ConsultaDieta.setSize(900, 500);
        
        this.jDialog_ConsultaDieta_VisualizarAlimentos.setTitle("Gym Zone | Visualizar Comidas Dieta");
        this.jDialog_ConsultaDieta_VisualizarAlimentos.setResizable(false);
        this.jDialog_ConsultaDieta_VisualizarAlimentos.setLocationRelativeTo(null);
        this.jDialog_ConsultaDieta_VisualizarAlimentos.setSize(900, 500);
        
        this.jDialog_ModificarDieta.setTitle("Gym Zone | Modificar Dieta");
        this.jDialog_ModificarDieta.setResizable(false);
        this.jDialog_ModificarDieta.setLocationRelativeTo(null);
        this.jDialog_ModificarDieta.setSize(900, 500);
       
        
        this.jDialog_OperacionesRutinaAdministrador.setTitle("Gym Zone | Operaciones Rutina");
        this.jDialog_OperacionesRutinaAdministrador.setResizable(false);
        this.jDialog_OperacionesRutinaAdministrador.setLocationRelativeTo(null);
        this.jDialog_OperacionesRutinaAdministrador.setSize(900, 500);
        
        this.jDialog_OperacionesServicioAdministrador.setTitle("Gym Zone | Operaciones Servicio");
        this.jDialog_OperacionesServicioAdministrador.setResizable(false);
        this.jDialog_OperacionesServicioAdministrador.setLocationRelativeTo(null);
        this.jDialog_OperacionesServicioAdministrador.setSize(900, 500);
        
        
        this.jDialog_OperacionesProductoAdministrador.setTitle("Gym Zone | Operaciones Producto");
        this.jDialog_OperacionesProductoAdministrador.setResizable(false);
        this.jDialog_OperacionesProductoAdministrador.setLocationRelativeTo(null);
        this.jDialog_OperacionesProductoAdministrador.setSize(900, 500);
        
        
        this.jDialog_OperacionesSalaAdministrador.setTitle("Gym Zone | Operaciones Sala");
        this.jDialog_OperacionesSalaAdministrador.setResizable(false);
        this.jDialog_OperacionesSalaAdministrador.setLocationRelativeTo(null);
        this.jDialog_OperacionesSalaAdministrador.setSize(900, 500);
        
        this.jDialog_AltaServicio.setTitle("Gym Zone | Alta Servicio");
        this.jDialog_AltaServicio.setResizable(false);
        this.jDialog_AltaServicio.setLocationRelativeTo(null);
        this.jDialog_AltaServicio.setSize(900, 500);
        
        
        this.jDialog_AltaSala.setTitle("Gym Zone | Alta Sala");
        this.jDialog_AltaSala.setResizable(false);
        this.jDialog_AltaSala.setLocationRelativeTo(null);
        this.jDialog_AltaSala.setSize(900, 500);
        
        
        this.jDialog_AltaProducto.setTitle("Gym Zone | Alta Producto");
        this.jDialog_AltaProducto.setResizable(false);
        this.jDialog_AltaProducto.setLocationRelativeTo(null);
        this.jDialog_AltaProducto.setSize(900, 500);

        this.jDialog_ModificarProducto.setTitle("Gym Zone | Modificar Producto");
        this.jDialog_ModificarProducto.setResizable(false);
        this.jDialog_ModificarProducto.setLocationRelativeTo(null);
        this.jDialog_ModificarProducto.setSize(900, 500);
        
        this.jDialog_ConsultaProducto.setTitle("Gym Zone | Consulta Producto");
        this.jDialog_ConsultaProducto.setResizable(false);
        this.jDialog_ConsultaProducto.setLocationRelativeTo(null);
        this.jDialog_ConsultaProducto.setSize(900, 500);
        
        this.jDialog_ModificarServicio.setTitle("Gym Zone | Modificar Servicio");
        this.jDialog_ModificarServicio.setResizable(false);
        this.jDialog_ModificarServicio.setLocationRelativeTo(null);
        this.jDialog_ModificarServicio.setSize(900, 500);
        
        this.jDialog_ConsultaServicio.setTitle("Gym Zone | Consulta Servicio");
        this.jDialog_ConsultaServicio.setResizable(false);
        this.jDialog_ConsultaServicio.setLocationRelativeTo(null);
        this.jDialog_ConsultaServicio.setSize(900, 500);
        
        this.jDialog_ModificarSala.setTitle("Gym Zone | Modificar Sala");
        this.jDialog_ModificarSala.setResizable(false);
        this.jDialog_ModificarSala.setLocationRelativeTo(null);
        this.jDialog_ModificarSala.setSize(900, 500);
        
        this.jDialog_ConsultaSala.setTitle("Gym Zone | Consulta Sala");
        this.jDialog_ConsultaSala.setResizable(false);
        this.jDialog_ConsultaSala.setLocationRelativeTo(null);
        this.jDialog_ConsultaSala.setSize(900, 500);

        this.jDialog_ConsultaVenta.setTitle("Gym Zone | Consulta Venta");
        this.jDialog_ConsultaVenta.setResizable(false);
        this.jDialog_ConsultaVenta.setLocationRelativeTo(null);
        this.jDialog_ConsultaVenta.setSize(900, 500);
        
        this.jDialog_ConsultaReserva.setTitle("Gym Zone | Consulta Reserva");
        this.jDialog_ConsultaReserva.setResizable(false);
        this.jDialog_ConsultaReserva.setLocationRelativeTo(null);
        this.jDialog_ConsultaReserva.setSize(900, 500);
        
        this.jDialog_ConsultaPagosServicios.setTitle("Gym Zone | Consulta Pagos Servicios");
        this.jDialog_ConsultaPagosServicios.setResizable(false);
        this.jDialog_ConsultaPagosServicios.setLocationRelativeTo(null);
        this.jDialog_ConsultaPagosServicios.setSize(900, 500);
        
        this.jDialog_OperacionesPagosCliente.setTitle("Gym Zone | Pagos Productos y Servicios");
        this.jDialog_OperacionesPagosCliente.setResizable(false);
        this.jDialog_OperacionesPagosCliente.setLocationRelativeTo(null);
        this.jDialog_OperacionesPagosCliente.setSize(900, 500);
        
        
        this.jDialog_PagoProducto.setTitle("Gym Zone | Pagos Productos");
        this.jDialog_PagoProducto.setResizable(false);
        this.jDialog_PagoProducto.setLocationRelativeTo(null);
        this.jDialog_PagoProducto.setSize(900, 500);
        
        
        this.jDialog_PagoServicio.setTitle("Gym Zone | Pagos Servicios");
        this.jDialog_PagoServicio.setResizable(false);
        this.jDialog_PagoServicio.setLocationRelativeTo(null);
        this.jDialog_PagoServicio.setSize(900, 500);
        
        this.jDialog_OperacionesReservasCliente.setTitle("Gym Zone | Gestion Reservas");
        this.jDialog_OperacionesReservasCliente.setResizable(false);
        this.jDialog_OperacionesReservasCliente.setLocationRelativeTo(null);
        this.jDialog_OperacionesReservasCliente.setSize(900, 500);
        
        this.jDialog_AltaReserva.setTitle("Gym Zone | Alta Reserva");
        this.jDialog_AltaReserva.setResizable(false);
        this.jDialog_AltaReserva.setLocationRelativeTo(null);
        this.jDialog_AltaReserva.setSize(900, 500);
        
        this.jDialog_GestionReserva.setTitle("Gym Zone | Gestionar Reservas");
        this.jDialog_GestionReserva.setResizable(false);
        this.jDialog_GestionReserva.setLocationRelativeTo(null);
        this.jDialog_GestionReserva.setSize(900, 500);
        
        this.jDialog_PerfilCliente.setTitle("Gym Zone | Perfil Cliente");
        this.jDialog_PerfilCliente.setResizable(false);
        this.jDialog_PerfilCliente.setLocationRelativeTo(null);
        this.jDialog_PerfilCliente.setSize(900, 500);
        
        
        this.jTextField1.setVisible(false);
        this.jTextField1.setEnabled(false);
        
        jButton_PU_AccederPerCliente.setVisible(false);
        jButton_PU_AccederPerCliente.setEnabled(false);
                            
        
        // Definición de las imágenes utilizadas
        
        URL url_fondoRol = getClass().getResource("/img/fondoRol.jpg");
        ImageIcon img_fondoRol = new ImageIcon(url_fondoRol);
        jLabelFondoRol.setIcon(img_fondoRol);
        
        URL url_fondoCliente = getClass().getResource("/img/clienteRol.jpg");
        ImageIcon img_fondoCliente = new ImageIcon(url_fondoCliente);
        jButton2.setIcon(img_fondoCliente);
        
        URL url_fondoNutricionista = getClass().getResource("/img/nutricionistaRol.jpg");
        ImageIcon img_fondoNutricionista = new ImageIcon(url_fondoNutricionista);
        jButton3.setIcon(img_fondoNutricionista);
        
        URL url_fondoMonitor = getClass().getResource("/img/monitorRol.jpg");
        ImageIcon img_fondoMonitor = new ImageIcon(url_fondoMonitor);
        jButton4.setIcon(img_fondoMonitor);
        
        URL url_fondoAdministrador = getClass().getResource("/img/administradorRol.jpg");
        ImageIcon img_fondoAdministrador = new ImageIcon(url_fondoAdministrador);
        jButton1.setIcon(img_fondoAdministrador);
        
        URL url_fondoInicio = getClass().getResource("/img/fondoInicio.jpg");
        ImageIcon img_fondoInicio = new ImageIcon(url_fondoInicio);
        jLabel_FondoInicioSesion.setIcon(img_fondoInicio);
        
        URL url_fondoIniciarSesion = getClass().getResource("/img/fondoInicioSesion_Registrarse.jpg");
        ImageIcon img_fondoIniciarSesion = new ImageIcon(url_fondoIniciarSesion);
        jLabel_IniciarSesion.setIcon(img_fondoIniciarSesion);

        URL url_fondoRegistrarse = getClass().getResource("/img/fondoRegistrarse4.jpg");
        ImageIcon img_fondoRegistrarse = new ImageIcon(url_fondoRegistrarse);
        jLabel_Registrarse.setIcon(img_fondoRegistrarse);
        
        URL url_fondoRegistrarseCliente = getClass().getResource("/img/fondoRegistrarse4.jpg");
        ImageIcon img_fondoRegistrarseCliente = new ImageIcon(url_fondoRegistrarseCliente);
        jLabel_RegistrarseCliente.setIcon(img_fondoRegistrarseCliente);
        
        URL url_fondoRegistrarseNutriconista = getClass().getResource("/img/fondoRegistrarse4.jpg");
        ImageIcon img_fondoRegistrarseNutriconista = new ImageIcon(url_fondoRegistrarseNutriconista);
        jLabel_RegistrarseNutricionista.setIcon(img_fondoRegistrarseNutriconista);
        
        URL url_fondoDieta1 = getClass().getResource("/img/dieta_deportiva.jpg");
        ImageIcon img_fondoDieta1 = new ImageIcon(url_fondoDieta1);
        jButton16.setIcon(img_fondoDieta1);
        
        URL url_fondoDieta2 = getClass().getResource("/img/dieta_clinica.jpg");
        ImageIcon img_fondoDieta2 = new ImageIcon(url_fondoDieta2);
        jButton17.setIcon(img_fondoDieta2);
        
        URL url_fondoDieta3 = getClass().getResource("/img/dieta_exclusion.jpg");
        ImageIcon img_fondoDieta3 = new ImageIcon(url_fondoDieta3);
        jButton15.setIcon(img_fondoDieta3);

        URL url_fondoRegistrarseMonitor = getClass().getResource("/img/fondoRegistrarse4.jpg");
        ImageIcon img_fondoRegistrarseMonitor = new ImageIcon(url_fondoRegistrarseMonitor);
        jLabel_RegistrarseMonitor.setIcon(img_fondoRegistrarseMonitor);
        
        URL url_fondoRutina1 = getClass().getResource("/img/rutina_gimnasio.jpg");
        ImageIcon img_fondoRutina1 = new ImageIcon(url_fondoRutina1);
        jButton19.setIcon(img_fondoRutina1);
        
        URL url_fondoRutina2 = getClass().getResource("/img/rutina_cardio.jpg");
        ImageIcon img_fondoRutina2 = new ImageIcon(url_fondoRutina2);
        jButton20.setIcon(img_fondoRutina2);
        
        URL url_fondoRutina3 = getClass().getResource("/img/rutina_crossfit.jpg");
        ImageIcon img_fondoRutina3 = new ImageIcon(url_fondoRutina3);
        jButton18.setIcon(img_fondoRutina3);
        
        URL url_verContrasenna1 = getClass().getResource("/img/ojoVisible.png");
        ImageIcon img_verContrasenna1 = new ImageIcon(url_verContrasenna1);
        jLabel41.setIcon(img_verContrasenna1);
        
        
        URL url_verContrasenna2 = getClass().getResource("/img/ojoOcultado.png");
        ImageIcon img_verContrasenna2 = new ImageIcon(url_verContrasenna2);
        jLabel42.setIcon(img_verContrasenna2);
 
        jLabel42.setVisible(false);

        jLabel43.setIcon(img_verContrasenna1);

        jLabel45.setIcon(img_verContrasenna2);
        
        jLabel45.setVisible(false);
        
        URL url_Ingresar = getClass().getResource("/img/ingresar.png");
        ImageIcon img_Ingresar = new ImageIcon(url_Ingresar);
        jLabel56.setIcon(img_Ingresar);
        
        URL url_Retirar = getClass().getResource("/img/retirar.png");
        ImageIcon img_Retirar = new ImageIcon(url_Retirar);
        jLabel55.setIcon(img_Retirar);
        
        URL url_GestionCuentaBancaria = getClass().getResource("/img/fondoGestionCuentaBancaria.jpg");
        ImageIcon img_GestionCuentaBancaria = new ImageIcon(url_GestionCuentaBancaria);
        jLabel_GestionCuentaBancaria.setIcon(img_GestionCuentaBancaria);
        
        URL url_RegistrarCuentaBancaria = getClass().getResource("/img/fondoRegistrarCuentaBancaria.jpg");
        ImageIcon img_RegistrarCuentaBancaria = new ImageIcon(url_RegistrarCuentaBancaria);
        jLabel_RegistrarCuentaBancaria.setIcon(img_RegistrarCuentaBancaria);
        
        URL url_FondoPredeterminado = getClass().getResource("/img/fondoPredeterminado.jpg");
        ImageIcon img_FondoPredeterminado = new ImageIcon(url_FondoPredeterminado);
        
        URL url_EditarCampo = getClass().getResource("/img/editarCampo.png");
        ImageIcon img_EditarCampo = new ImageIcon(url_EditarCampo);
        
        jLabel_PU_EditarNombreUsuario.setIcon(img_EditarCampo);
        jLabel_PU_EditarCorreo.setIcon(img_EditarCampo);
        jLabel_PU_EditarContrasenna.setIcon(img_EditarCampo);
        
        jLabel_PC_EditarObjetivo.setIcon(img_EditarCampo);
        jLabel_PC_EditarAltura.setIcon(img_EditarCampo);
        jLabel_PC_EditarPeso.setIcon(img_EditarCampo);
        
        jLabel_ConsultaCuentaBancaria.setIcon(img_FondoPredeterminado);
        jLabel_ModificarCuentaBancaria.setIcon(img_FondoPredeterminado);

        jLabel_FondoFuncionalidadesMonitor.setIcon(img_FondoPredeterminado);
        jLabel_FondoFuncionalidadesAdministrador.setIcon(img_FondoPredeterminado);
        jLabel_PU_Fondo.setIcon(img_FondoPredeterminado);
        jLabel_FondoAltaRutina.setIcon(img_FondoPredeterminado);
        jLabel_FondoRutinaDia.setIcon(img_FondoPredeterminado);
        jLabel_FondoConsultaRutina.setIcon(img_FondoPredeterminado);
        jLabel_FondoVerEjercicios.setIcon(img_FondoPredeterminado);
        jLabel_FondoFuncionalidadesCliente.setIcon(img_FondoPredeterminado);
        
        jLabel_FondoModificarRutina.setIcon(img_FondoPredeterminado);
        jLabel_FondoConsultaDieta.setIcon(img_FondoPredeterminado);
        
        jLabel_FondoModificarServicio.setIcon(img_FondoPredeterminado);
        jLabel_FondoConsultaServicio.setIcon(img_FondoPredeterminado);
        
        jLabel_FondoOperacionesSalaAdministrador.setIcon(img_FondoPredeterminado);
        jLabel_FondoOperacionesProductoAdministrador.setIcon(img_FondoPredeterminado);
        jLabel_FondoOperacionesRutinaAdministrador.setIcon(img_FondoPredeterminado);
        jLabel_FondoOperacionesServicioAdministrador.setIcon(img_FondoPredeterminado);
        jLabel_FondoAltaProducto.setIcon(img_FondoPredeterminado);
        jLabel_FondoAltaServicio.setIcon(img_FondoPredeterminado);
        jLabel_FondoAltaSala.setIcon(img_FondoPredeterminado);
        
        jLabel_FondoAltaDieta.setIcon(img_FondoPredeterminado);
        jLabel_FondoDietaDia.setIcon(img_FondoPredeterminado);
        jLabel_FondoModificarDieta.setIcon(img_FondoPredeterminado);
        
        jLabel_FondoFuncionalidadesNutricionista.setIcon(img_FondoPredeterminado);
        jLabel_FondoAltaDieta.setIcon(img_FondoPredeterminado);
        jLabel_FondoConsultaEjercicios.setIcon(img_FondoPredeterminado);
        jLabel_FondoConsultaProducto.setIcon(img_FondoPredeterminado);
        jLabel_FondoConsultaSala.setIcon(img_FondoPredeterminado);
        jLabel_FondoModificarSala.setIcon(img_FondoPredeterminado);
        
        jLabel_FondoConsultaVenta.setIcon(img_FondoPredeterminado);
        
        jLabel_FondoConsultaReserva.setIcon(img_FondoPredeterminado);
        
        jLabel_FondoConsultaPagosServicio.setIcon(img_FondoPredeterminado);
        
        jLabel_FondoConsultaPagarProducto.setIcon(img_FondoPredeterminado);
        
        jLabel_FondoOperacionesPagosCliente.setIcon(img_FondoPredeterminado);
        
        jLabel_FondoConsultaPagarServicio.setIcon(img_FondoPredeterminado);
        
        jLabel_FondoOperacionesReservasCliente.setIcon(img_FondoPredeterminado);
        
        jLabel_FondoAltaReserva.setIcon(img_FondoPredeterminado);
        
        jLabel_FondoCancelarReserva.setIcon(img_FondoPredeterminado);
        
        jLabel_PC_Fondo.setIcon(img_FondoPredeterminado);
        
        jLabel_FondoModificarProducto.setIcon(img_FondoPredeterminado);
        
        URL url_AltaRutina = getClass().getResource("/img/altaRutina.jpg");
        ImageIcon img_AltaRutina = new ImageIcon(url_AltaRutina);
        
        URL url_ModificarRutina = getClass().getResource("/img/modificarRutina.jpg");
        ImageIcon img_ModificarRutina = new ImageIcon(url_ModificarRutina);

        jButton32.setIcon(img_AltaRutina);
        jButton_AltaRutina.setIcon(img_AltaRutina);
        jButton33.setIcon(img_ModificarRutina);
        jButton_AdministrarRutina.setIcon(img_ModificarRutina);
        
        URL url_Pierna = getClass().getResource("/img/pierna.png");
        ImageIcon img_Pierna = new ImageIcon(url_Pierna);
        
        URL url_Pecho = getClass().getResource("/img/pecho.png");
        ImageIcon img_Pecho = new ImageIcon(url_Pecho);
        
        URL url_Abs = getClass().getResource("/img/abdominales.png");
        ImageIcon img_Abs = new ImageIcon(url_Abs);
        
        URL url_Brazo = getClass().getResource("/img/brazo.png");
        ImageIcon img_Brazo = new ImageIcon(url_Brazo);
        
        URL url_Espalda = getClass().getResource("/img/espalda.png");
        ImageIcon img_Espalda = new ImageIcon(url_Espalda);
        
        URL url_Reiniciar = getClass().getResource("/img/reiniciar.png");
        ImageIcon img_Reiniciar = new ImageIcon(url_Reiniciar);
        
        URL url_Buscar = getClass().getResource("/img/buscar.png");
        ImageIcon img_Buscar = new ImageIcon(url_Buscar);
        
        jLabel_FotoAbs.setIcon(img_Abs);
        jLabel_FotoPierna.setIcon(img_Pierna);
        jLabel_FotoPecho.setIcon(img_Pecho);
        jLabel_FotoBrazo.setIcon(img_Brazo);
        jLabel_FotoEspalda.setIcon(img_Espalda);
        jLabel_FotoReiniciar.setIcon(img_Reiniciar);
        
        jLabel_BuscarProducto.setIcon(img_Buscar);
        jLabel_ReiniciarBusquedaProducto.setIcon(img_Reiniciar);
        
        jLabel_BuscarServicio.setIcon(img_Buscar);
        jLabel_ReiniciarBusquedaServicio.setIcon(img_Reiniciar);
        
        jLabel_BuscarRutina.setIcon(img_Buscar);
        jLabel_ReiniciarBusquedaRutina.setIcon(img_Reiniciar);
        
        jLabel_BuscarDieta.setIcon(img_Buscar);
        jLabel_ReiniciarBusquedaDieta.setIcon(img_Reiniciar);
        
        jLabel_BuscarSala.setIcon(img_Buscar);
        jLabel_ReiniciarBusquedaSala.setIcon(img_Reiniciar);
        
        jLabel_BuscarVenta.setIcon(img_Buscar);
        jLabel_ReiniciarBusquedaVenta.setIcon(img_Reiniciar);
        
        jLabel_BuscarReserva.setIcon(img_Buscar);
        jLabel_ReiniciarBusquedaReserva.setIcon(img_Reiniciar);
        
        jLabel_BuscarPagosServicio.setIcon(img_Buscar);
        jLabel_ReiniciarBusquedaPagosServicio.setIcon(img_Reiniciar);
        
        jLabel_BuscarPagarProducto.setIcon(img_Buscar);
        jLabel_ReiniciarBusquedaPagarProducto.setIcon(img_Reiniciar);
        
        jLabel_BuscarPagarServicio.setIcon(img_Buscar);
        jLabel_ReiniciarBusquedaPagarServicio.setIcon(img_Reiniciar);
        
        
        URL url_AltaDieta = getClass().getResource("/img/altaDieta.jpg");
        ImageIcon img_AltaDieta = new ImageIcon(url_AltaDieta);
        
        jButton45.setIcon(img_AltaDieta);
        
        URL url_AdministrarDieta = getClass().getResource("/img/administrarDieta.jpg");
        ImageIcon img_AdministrarDieta = new ImageIcon(url_AdministrarDieta);
        
        jButton46.setIcon(img_AdministrarDieta);
        
        URL url_FotoFruta = getClass().getResource("/img/frutas.png");
        ImageIcon img_FotoFruta  = new ImageIcon(url_FotoFruta);
        
        URL url_FotoVerdura = getClass().getResource("/img/verduras.png");
        ImageIcon img_FotoVerdura  = new ImageIcon(url_FotoVerdura);
        
        URL url_FotoProteina = getClass().getResource("/img/proteinas.png");
        ImageIcon img_FotoProteina  = new ImageIcon(url_FotoProteina);
        
        URL url_FotoLacteos = getClass().getResource("/img/productos_lacteos.png");
        ImageIcon img_FotoLacteos  = new ImageIcon(url_FotoLacteos);
        
        URL url_FotoDulces = getClass().getResource("/img/dulces.png");
        ImageIcon img_FotoDulces  = new ImageIcon(url_FotoDulces);
        
        URL url_FotoCarbohidratos = getClass().getResource("/img/carbohidratos.png");
        ImageIcon img_FotoCarbohidratos  = new ImageIcon(url_FotoCarbohidratos);
        
        URL url_FotoAceites = getClass().getResource("/img/aceites.png");
        ImageIcon img_FotoAceites  = new ImageIcon(url_FotoAceites);
        
        jLabel_FotoFruta.setIcon(img_FotoFruta);
        jLabel_FotoVerdura.setIcon(img_FotoVerdura);
        jLabel_FotoProteina.setIcon(img_FotoProteina);
        jLabel_FotoLacteo.setIcon(img_FotoLacteos);
        jLabel_FotoDulces.setIcon(img_FotoDulces);
        jLabel_FotoCarbohidratos.setIcon(img_FotoCarbohidratos);
        jLabel_FotoAceites.setIcon(img_FotoAceites);
        jLabel_FotoReiniciarDieta.setIcon(img_Reiniciar);
        
        URL url_FotoDesayuno = getClass().getResource("/img/desayuno.png");
        ImageIcon img_FotoDesayuno  = new ImageIcon(url_FotoDesayuno);
        
        URL url_FotoAlmuerzo = getClass().getResource("/img/almuerzo.png");
        ImageIcon img_FotoAlmuerzo  = new ImageIcon(url_FotoAlmuerzo);

        URL url_FotoMerienda = getClass().getResource("/img/merienda.png");
        ImageIcon img_FotoMerienda  = new ImageIcon(url_FotoMerienda);
        
        URL url_FotoCena = getClass().getResource("/img/cena.png");
        ImageIcon img_FotoCena  = new ImageIcon(url_FotoCena);
        
        jLabel_FotoDesayuno.setIcon(img_FotoDesayuno);
        jLabel_FotoAlmuerzo.setIcon(img_FotoAlmuerzo);
        jLabel_FotoMerienda.setIcon(img_FotoMerienda);
        jLabel_FotoCena.setIcon(img_FotoCena);
        
        URL url_GestionRutinas = getClass().getResource("/img/gestionRutinas.jpg");
        ImageIcon img_GestionRutinas = new ImageIcon(url_GestionRutinas);
        
        URL url_GestionProductos = getClass().getResource("/img/gestionProductos.jpg");
        ImageIcon img_GestionProductos = new ImageIcon(url_GestionProductos);
        
        URL url_GestionServicios = getClass().getResource("/img/gestionServicios.jpg");
        ImageIcon img_GestionServicios = new ImageIcon(url_GestionServicios);
        
        URL url_GestionSalas = getClass().getResource("/img/gestionSalas.jpg");
        ImageIcon img_GestionSalas = new ImageIcon(url_GestionSalas);
        
        jButton59.setIcon(img_GestionRutinas);
        
        jButton60.setIcon(img_GestionSalas);
        
        jButton61.setIcon(img_GestionProductos);
                
        jButton57.setIcon(img_GestionServicios);
        
        URL url_AltaProducto = getClass().getResource("/img/altaProducto.jpg");
        ImageIcon img_AltaProducto = new ImageIcon(url_AltaProducto);
        
        URL url_AltaSala = getClass().getResource("/img/altaSala.jpg");
        ImageIcon img_AltaSala = new ImageIcon(url_AltaSala);
        
        URL url_AltaServicio = getClass().getResource("/img/altaServicio.jpg");
        ImageIcon img_AltaServicio = new ImageIcon(url_AltaServicio);
        
        URL url_AdministrarProducto = getClass().getResource("/img/administrarProducto.jpg");
        ImageIcon img_AdministrarProducto = new ImageIcon(url_AdministrarProducto);
        
        URL url_AdministrarSala = getClass().getResource("/img/administrarSala.jpg");
        ImageIcon img_AdministrarSala = new ImageIcon(url_AdministrarSala);
        
        URL url_AdministrarServicio = getClass().getResource("/img/administrarServicio.jpg");
        ImageIcon img_AdministrarServicio = new ImageIcon(url_AdministrarServicio);
        
        URL url_ConsultaProducto = getClass().getResource("/img/consultaVenta.jpg");
        ImageIcon img_ConsultaProducto = new ImageIcon(url_ConsultaProducto);
        
        URL url_ConsultaServicio = getClass().getResource("/img/consultaCuota.jpg");
        ImageIcon img_ConsultaServicio = new ImageIcon(url_ConsultaServicio);
        
        URL url_ConsultaSala = getClass().getResource("/img/consultaReserva.jpg");
        ImageIcon img_ConsultaSala = new ImageIcon(url_ConsultaSala);
        
        jButton_AltaProducto.setIcon(img_AltaProducto);
        jButton_AltaSala.setIcon(img_AltaSala);
        jButton_AltaServicio.setIcon(img_AltaServicio);
        
        jButton_AdministrarProducto.setIcon(img_AdministrarProducto);
        jButton_AdministrarSala.setIcon(img_AdministrarSala);
        jButton_AdministrarServicio.setIcon(img_AdministrarServicio);
        
        jButton_ConsultaProducto.setIcon(img_ConsultaProducto);
        jButton_ConsultaSala.setIcon(img_ConsultaSala);
        jButton_ConsultaServicio.setIcon(img_ConsultaServicio);
        
        
        URL url_FotoPagosProductosServicio = getClass().getResource("/img/pagosProductoServicio.jpg");
        ImageIcon img_FotoPagosProductosServicio  = new ImageIcon(url_FotoPagosProductosServicio);
        
        jButton81.setIcon(img_FotoPagosProductosServicio);
        
        URL url_GestionReserva = getClass().getResource("/img/gestionReservas.jpg");
        ImageIcon img_GestionReserva  = new ImageIcon(url_GestionReserva);
        
        jButton79.setIcon(img_GestionReserva);
        
        URL url_ConsultaRutinaCliente = getClass().getResource("/img/consultarRutinaCliente.jpg");
        ImageIcon img_ConsultaRutinaCliente  = new ImageIcon(url_ConsultaRutinaCliente);
        
        jButton80.setIcon(img_ConsultaRutinaCliente);
        
        URL url_ConsultaDietaCliente = getClass().getResource("/img/consultarDietaCliente.jpg");
        ImageIcon img_ConsultaDietaCliente  = new ImageIcon(url_ConsultaDietaCliente);
        
        jButton82.setIcon(img_ConsultaDietaCliente);
        
        
        URL url_PagarProducto = getClass().getResource("/img/pagarProducto.jpg");
        ImageIcon img_PagarProducto  = new ImageIcon(url_PagarProducto);
        
        jButton_PagarProducto.setIcon(img_PagarProducto);
        
        URL url_PagarServicio = getClass().getResource("/img/pagarServicio.jpg");
        ImageIcon img_PagarServicio  = new ImageIcon(url_PagarServicio);
        
        jButton_PagarServicio.setIcon(img_PagarServicio);
        
        URL url_AltaReserva = getClass().getResource("/img/altaReserva.jpg");
        ImageIcon img_AltaReserva  = new ImageIcon(url_AltaReserva);
        
        jButton_AltaReserva.setIcon(img_AltaReserva);
        
        URL url_CancelarReserva = getClass().getResource("/img/cancelarReserva.jpg");
        ImageIcon img_CancelarReserva  = new ImageIcon(url_CancelarReserva);
        
        jButton_CancelarReserva.setIcon(img_CancelarReserva);
        
        URL url_AltaReservaDia = getClass().getResource("/img/reservaTurnoDia.jpg");
        ImageIcon img_AltaReservaDia  = new ImageIcon(url_AltaReservaDia);
        
        jButton_AltaReservaDia.setIcon(img_AltaReservaDia);
        
        URL url_AltaReservaTarde = getClass().getResource("/img/reservaTurnoTarde.jpg");
        ImageIcon img_AltaReservaTarde  = new ImageIcon(url_AltaReservaTarde);
        
        jButton_AltaReservaTarde.setIcon(img_AltaReservaTarde);

        URL url_LogoApp = getClass().getResource("/img/logo_app_gym_zone.png");
        ImageIcon img_LogoApp  = new ImageIcon(url_LogoApp);
        
        setIconImage(img_LogoApp.getImage());
        
        this.setDefaultCloseOperation(JFramePrincipal.EXIT_ON_CLOSE);
        
        
        // Inicio de conexión con la base de datos
        
        try {
            con = DriverManager.getConnection(URL,USER, PASSWORD);
     
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog_Inicio = new javax.swing.JDialog();
        jLabel6 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel_FondoInicioSesion = new javax.swing.JLabel();
        jDialog_IniciarSesion = new javax.swing.JDialog();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jPasswordField2 = new javax.swing.JPasswordField();
        jLabel43 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel_IniciarSesion = new javax.swing.JLabel();
        jDialog_Registrarse = new javax.swing.JDialog();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel_Registrarse = new javax.swing.JLabel();
        jDialog_RegistrarseCliente = new javax.swing.JDialog();
        jLabel11 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jLabel_RegistrarseCliente = new javax.swing.JLabel();
        jDialog_RegistrarseNutricionista = new javax.swing.JDialog();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel_RegistrarseNutricionista = new javax.swing.JLabel();
        jDialog_RegistrarseMonitor = new javax.swing.JDialog();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jLabel_RegistrarseMonitor = new javax.swing.JLabel();
        jDialog_FuncionalidadMonitor = new javax.swing.JDialog();
        jLabel51 = new javax.swing.JLabel();
        jLabel_FCC_Monitor = new javax.swing.JLabel();
        jLabel_FG_Monitor = new javax.swing.JLabel();
        jLabel_NU_Monitor = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jLabel_FondoFuncionalidadesMonitor = new javax.swing.JLabel();
        jDialog_GestionCuentaBancaria = new javax.swing.JDialog();
        jLabel44 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jLabel_GestionCuentaBancaria = new javax.swing.JLabel();
        jDialog_RegistrarCuentaBancaria = new javax.swing.JDialog();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jButton21 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel_RegistrarCuentaBancaria = new javax.swing.JLabel();
        jDialog_ConsultaCuentaBancaria = new javax.swing.JDialog();
        jLabel_Banco = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jButton26 = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton27 = new javax.swing.JButton();
        jLabel178 = new javax.swing.JLabel();
        jLabel179 = new javax.swing.JLabel();
        jLabel180 = new javax.swing.JLabel();
        jLabel_ConsultaCuentaBancaria = new javax.swing.JLabel();
        jDialog_ModificarCuentaBancaria = new javax.swing.JDialog();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel_ModificarCuentaBancaria = new javax.swing.JLabel();
        jDialog_PerfilUsuario = new javax.swing.JDialog();
        jTextField3 = new javax.swing.JTextField();
        jLabel_PU_FotoPerfil = new javax.swing.JLabel();
        jLabel_PU_NombreApellidos = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel_PU_NombreUsuario = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jButton_PU_Editar = new javax.swing.JButton();
        jLabel_PU_EditarNombreUsuario = new javax.swing.JLabel();
        jLabel_PU_EditarContrasenna = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel_PU_EditarCorreo = new javax.swing.JLabel();
        jLabel_PU_Correo = new javax.swing.JLabel();
        jLabel_PU_Contrasenna = new javax.swing.JLabel();
        jButton_PU_Volver = new javax.swing.JButton();
        jButton_PU_AccederPerCliente = new javax.swing.JButton();
        jLabel_PU_Fondo = new javax.swing.JLabel();
        jDialog_AltaRutina = new javax.swing.JDialog();
        jLabel58 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jButton35 = new javax.swing.JButton();
        jLabel65 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox7 = new javax.swing.JComboBox<>();
        jButton36 = new javax.swing.JButton();
        jLabel_FondoAltaRutina = new javax.swing.JLabel();
        jDialog_RutinaDia = new javax.swing.JDialog();
        jLabel_FotoPecho = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel_FotoEspalda = new javax.swing.JLabel();
        jLabel_FotoAbs = new javax.swing.JLabel();
        jLabel_FotoReiniciar = new javax.swing.JLabel();
        jLabel_FotoBrazo = new javax.swing.JLabel();
        jLabel_FotoPierna = new javax.swing.JLabel();
        jButton38 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel_FondoRutinaDia = new javax.swing.JLabel();
        jDialog_ConsultaRutina = new javax.swing.JDialog();
        jTextField16 = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel_ReiniciarBusquedaRutina = new javax.swing.JLabel();
        jLabel_BuscarRutina = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox8 = new javax.swing.JComboBox<>();
        jButton40 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel_FondoConsultaRutina = new javax.swing.JLabel();
        jDialog_ConsultaRutina_VerEjercicios = new javax.swing.JDialog();
        jLabel76 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton39 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jLabel_FondoVerEjercicios = new javax.swing.JLabel();
        jDialog_ConsultaRutina_ModificarRutina = new javax.swing.JDialog();
        jLabel78 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jComboBox9 = new javax.swing.JComboBox<>();
        jComboBox10 = new javax.swing.JComboBox<>();
        jLabel_FondoModificarRutina = new javax.swing.JLabel();
        jDialog_FuncionalidadNutricionista = new javax.swing.JDialog();
        jLabel81 = new javax.swing.JLabel();
        jLabel_FCC_Nutricionista = new javax.swing.JLabel();
        jLabel_FG_Nutricionista = new javax.swing.JLabel();
        jLabel_NU_Nutricionista = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jLabel_FondoFuncionalidadesNutricionista = new javax.swing.JLabel();
        jDialog_AltaDieta = new javax.swing.JDialog();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jButton47 = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox<>();
        jComboBox12 = new javax.swing.JComboBox<>();
        jButton48 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel89 = new javax.swing.JLabel();
        jLabel_FondoAltaDieta = new javax.swing.JLabel();
        jDialog_DietaDia = new javax.swing.JDialog();
        jLabel_FotoVerdura = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel_FotoCarbohidratos = new javax.swing.JLabel();
        jLabel_FotoProteina = new javax.swing.JLabel();
        jLabel_FotoReiniciarDieta = new javax.swing.JLabel();
        jLabel_FotoLacteo = new javax.swing.JLabel();
        jLabel_FotoFruta = new javax.swing.JLabel();
        jLabel_FotoAceites = new javax.swing.JLabel();
        jLabel_FotoDulces = new javax.swing.JLabel();
        jButton49 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel_FondoDietaDia = new javax.swing.JLabel();
        jDialog_ConsultaDieta = new javax.swing.JDialog();
        jTextField19 = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel_ReiniciarBusquedaDieta = new javax.swing.JLabel();
        jLabel_BuscarDieta = new javax.swing.JLabel();
        jComboBox13 = new javax.swing.JComboBox<>();
        jComboBox14 = new javax.swing.JComboBox<>();
        jButton51 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel_FondoConsultaDieta = new javax.swing.JLabel();
        jDialog_ConsultaDieta_VisualizarAlimentos = new javax.swing.JDialog();
        jLabel94 = new javax.swing.JLabel();
        jLabel_FotoAlmuerzo = new javax.swing.JLabel();
        jLabel_FotoCena = new javax.swing.JLabel();
        jLabel_FotoMerienda = new javax.swing.JLabel();
        jLabel_FotoDesayuno = new javax.swing.JLabel();
        jButton54 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel_FondoConsultaEjercicios = new javax.swing.JLabel();
        jDialog_ModificarDieta = new javax.swing.JDialog();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jButton55 = new javax.swing.JButton();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jComboBox15 = new javax.swing.JComboBox<>();
        jComboBox16 = new javax.swing.JComboBox<>();
        jButton56 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel99 = new javax.swing.JLabel();
        jLabel_FondoModificarDieta = new javax.swing.JLabel();
        jDialog_FuncionalidadAdministrador = new javax.swing.JDialog();
        jLabel100 = new javax.swing.JLabel();
        jLabel_FCC_Administrador = new javax.swing.JLabel();
        jLabel_FG_Administrador = new javax.swing.JLabel();
        jLabel_NU_Administrador = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jButton57 = new javax.swing.JButton();
        jButton59 = new javax.swing.JButton();
        jButton60 = new javax.swing.JButton();
        jButton61 = new javax.swing.JButton();
        jLabel_FondoFuncionalidadesAdministrador = new javax.swing.JLabel();
        jDialog_OperacionesRutinaAdministrador = new javax.swing.JDialog();
        jLabel101 = new javax.swing.JLabel();
        jLabel_AltaRutina = new javax.swing.JLabel();
        jButton_AltaRutina = new javax.swing.JButton();
        jLabel_AdministrarRutina = new javax.swing.JLabel();
        jButton_AdministrarRutina = new javax.swing.JButton();
        jLabel_FondoOperacionesRutinaAdministrador = new javax.swing.JLabel();
        jDialog_OperacionesProductoAdministrador = new javax.swing.JDialog();
        jLabel106 = new javax.swing.JLabel();
        jLabel_AltaProducto = new javax.swing.JLabel();
        jButton_AltaProducto = new javax.swing.JButton();
        jLabel_AdministrarProducto = new javax.swing.JLabel();
        jButton_AdministrarProducto = new javax.swing.JButton();
        jLabel_ConsultaProducto = new javax.swing.JLabel();
        jButton_ConsultaProducto = new javax.swing.JButton();
        jLabel_FondoOperacionesProductoAdministrador = new javax.swing.JLabel();
        jDialog_OperacionesSalaAdministrador = new javax.swing.JDialog();
        jLabel107 = new javax.swing.JLabel();
        jLabel_AltaSala = new javax.swing.JLabel();
        jButton_AltaSala = new javax.swing.JButton();
        jLabel_AdministrarSala = new javax.swing.JLabel();
        jButton_AdministrarSala = new javax.swing.JButton();
        jLabel_ConsultaSala = new javax.swing.JLabel();
        jButton_ConsultaSala = new javax.swing.JButton();
        jLabel_FondoOperacionesSalaAdministrador = new javax.swing.JLabel();
        jDialog_OperacionesServicioAdministrador = new javax.swing.JDialog();
        jLabel108 = new javax.swing.JLabel();
        jLabel_AltaServicio = new javax.swing.JLabel();
        jButton_AltaServicio = new javax.swing.JButton();
        jLabel_AdministrarServicio = new javax.swing.JLabel();
        jButton_AdministrarServicio = new javax.swing.JButton();
        jLabel_ConsultaServicio = new javax.swing.JLabel();
        jButton_ConsultaServicio = new javax.swing.JButton();
        jLabel_FondoOperacionesServicioAdministrador = new javax.swing.JLabel();
        jDialog_AltaProducto = new javax.swing.JDialog();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jButton58 = new javax.swing.JButton();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jButton62 = new javax.swing.JButton();
        jLabel_FondoAltaProducto = new javax.swing.JLabel();
        jDialog_AltaServicio = new javax.swing.JDialog();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jButton63 = new javax.swing.JButton();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jButton64 = new javax.swing.JButton();
        jLabel_FondoAltaServicio = new javax.swing.JLabel();
        jDialog_AltaSala = new javax.swing.JDialog();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jButton65 = new javax.swing.JButton();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jComboBox17 = new javax.swing.JComboBox<>();
        jButton66 = new javax.swing.JButton();
        jLabel_FondoAltaSala = new javax.swing.JLabel();
        jDialog_ModificarProducto = new javax.swing.JDialog();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        jTextField30 = new javax.swing.JTextField();
        jTextField31 = new javax.swing.JTextField();
        jButton67 = new javax.swing.JButton();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jButton68 = new javax.swing.JButton();
        jLabel_FondoModificarProducto = new javax.swing.JLabel();
        jDialog_ConsultaProducto = new javax.swing.JDialog();
        jTextField32 = new javax.swing.JTextField();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabel_ReiniciarBusquedaProducto = new javax.swing.JLabel();
        jLabel_BuscarProducto = new javax.swing.JLabel();
        jComboBox18 = new javax.swing.JComboBox<>();
        jComboBox19 = new javax.swing.JComboBox<>();
        jButton69 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jLabel_FondoConsultaProducto = new javax.swing.JLabel();
        jDialog_ModificarServicio = new javax.swing.JDialog();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jTextField33 = new javax.swing.JTextField();
        jTextField34 = new javax.swing.JTextField();
        jTextField35 = new javax.swing.JTextField();
        jButton70 = new javax.swing.JButton();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jButton71 = new javax.swing.JButton();
        jLabel_FondoModificarServicio = new javax.swing.JLabel();
        jDialog_ConsultaServicio = new javax.swing.JDialog();
        jTextField36 = new javax.swing.JTextField();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel_ReiniciarBusquedaServicio = new javax.swing.JLabel();
        jLabel_BuscarServicio = new javax.swing.JLabel();
        jComboBox20 = new javax.swing.JComboBox<>();
        jComboBox21 = new javax.swing.JComboBox<>();
        jButton72 = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jLabel_FondoConsultaServicio = new javax.swing.JLabel();
        jDialog_ModificarSala = new javax.swing.JDialog();
        jLabel137 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jTextField37 = new javax.swing.JTextField();
        jTextField38 = new javax.swing.JTextField();
        jButton73 = new javax.swing.JButton();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jComboBox22 = new javax.swing.JComboBox<>();
        jButton74 = new javax.swing.JButton();
        jLabel_FondoModificarSala = new javax.swing.JLabel();
        jDialog_ConsultaSala = new javax.swing.JDialog();
        jTextField39 = new javax.swing.JTextField();
        jLabel141 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        jLabel_ReiniciarBusquedaSala = new javax.swing.JLabel();
        jLabel_BuscarSala = new javax.swing.JLabel();
        jComboBox23 = new javax.swing.JComboBox<>();
        jComboBox24 = new javax.swing.JComboBox<>();
        jButton75 = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jLabel_FondoConsultaSala = new javax.swing.JLabel();
        jDialog_ConsultaVenta = new javax.swing.JDialog();
        jTextField40 = new javax.swing.JTextField();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        jLabel_ReiniciarBusquedaVenta = new javax.swing.JLabel();
        jLabel_BuscarVenta = new javax.swing.JLabel();
        jComboBox25 = new javax.swing.JComboBox<>();
        jComboBox26 = new javax.swing.JComboBox<>();
        jButton76 = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jLabel_FondoConsultaVenta = new javax.swing.JLabel();
        jDialog_ConsultaReserva = new javax.swing.JDialog();
        jLabel149 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        jLabel_ReiniciarBusquedaReserva = new javax.swing.JLabel();
        jLabel_BuscarReserva = new javax.swing.JLabel();
        jComboBox27 = new javax.swing.JComboBox<>();
        jComboBox28 = new javax.swing.JComboBox<>();
        jComboBox29 = new javax.swing.JComboBox<>();
        jComboBox30 = new javax.swing.JComboBox<>();
        jButton77 = new javax.swing.JButton();
        jLabel153 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jLabel_FondoConsultaReserva = new javax.swing.JLabel();
        jDialog_ConsultaPagosServicios = new javax.swing.JDialog();
        jLabel154 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        jLabel_ReiniciarBusquedaPagosServicio = new javax.swing.JLabel();
        jLabel_BuscarPagosServicio = new javax.swing.JLabel();
        jComboBox31 = new javax.swing.JComboBox<>();
        jComboBox32 = new javax.swing.JComboBox<>();
        jComboBox34 = new javax.swing.JComboBox<>();
        jButton78 = new javax.swing.JButton();
        jLabel158 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable12 = new javax.swing.JTable();
        jLabel_FondoConsultaPagosServicio = new javax.swing.JLabel();
        jDialog_FuncionalidadCliente = new javax.swing.JDialog();
        jLabel157 = new javax.swing.JLabel();
        jLabel_FCC_Cliente = new javax.swing.JLabel();
        jLabel_FG_Cliente = new javax.swing.JLabel();
        jLabel_NU_Cliente = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        jLabel162 = new javax.swing.JLabel();
        jButton79 = new javax.swing.JButton();
        jButton80 = new javax.swing.JButton();
        jButton81 = new javax.swing.JButton();
        jButton82 = new javax.swing.JButton();
        jLabel_FondoFuncionalidadesCliente = new javax.swing.JLabel();
        jDialog_OperacionesPagosCliente = new javax.swing.JDialog();
        jLabel163 = new javax.swing.JLabel();
        jLabel_PagarProducto = new javax.swing.JLabel();
        jButton_PagarProducto = new javax.swing.JButton();
        jLabel_PagarServicio = new javax.swing.JLabel();
        jButton_PagarServicio = new javax.swing.JButton();
        jLabel_FondoOperacionesPagosCliente = new javax.swing.JLabel();
        jDialog_PagoProducto = new javax.swing.JDialog();
        jTextField41 = new javax.swing.JTextField();
        jLabel164 = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        jLabel167 = new javax.swing.JLabel();
        jLabel_ReiniciarBusquedaPagarProducto = new javax.swing.JLabel();
        jLabel_BuscarPagarProducto = new javax.swing.JLabel();
        jComboBox35 = new javax.swing.JComboBox<>();
        jButton83 = new javax.swing.JButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable13 = new javax.swing.JTable();
        jLabel_FondoConsultaPagarProducto = new javax.swing.JLabel();
        jDialog_PagoServicio = new javax.swing.JDialog();
        jTextField42 = new javax.swing.JTextField();
        jLabel165 = new javax.swing.JLabel();
        jLabel169 = new javax.swing.JLabel();
        jLabel170 = new javax.swing.JLabel();
        jLabel_ReiniciarBusquedaPagarServicio = new javax.swing.JLabel();
        jLabel_BuscarPagarServicio = new javax.swing.JLabel();
        jComboBox36 = new javax.swing.JComboBox<>();
        jButton84 = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTable14 = new javax.swing.JTable();
        jLabel_FondoConsultaPagarServicio = new javax.swing.JLabel();
        jDialog_OperacionesReservasCliente = new javax.swing.JDialog();
        jLabel168 = new javax.swing.JLabel();
        jLabel_AltaReserva = new javax.swing.JLabel();
        jButton_AltaReserva = new javax.swing.JButton();
        jLabel_CancelarReserva = new javax.swing.JLabel();
        jButton_CancelarReserva = new javax.swing.JButton();
        jLabel_FondoOperacionesReservasCliente = new javax.swing.JLabel();
        jDialog_AltaReserva = new javax.swing.JDialog();
        jLabel171 = new javax.swing.JLabel();
        jLabel_AltaReservaDia = new javax.swing.JLabel();
        jLabel_AltaReservaTarde = new javax.swing.JLabel();
        jButton_AltaReservaTarde = new javax.swing.JButton();
        jButton_AltaReservaDia = new javax.swing.JButton();
        jLabel172 = new javax.swing.JLabel();
        jButton85 = new javax.swing.JButton();
        jComboBox33 = new javax.swing.JComboBox<>();
        jButton86 = new javax.swing.JButton();
        jLabel173 = new javax.swing.JLabel();
        jComboBox37 = new javax.swing.JComboBox<>();
        jLabel_FondoAltaReserva = new javax.swing.JLabel();
        jDialog_GestionReserva = new javax.swing.JDialog();
        jLabel174 = new javax.swing.JLabel();
        jButton87 = new javax.swing.JButton();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTable15 = new javax.swing.JTable();
        jLabel_FondoCancelarReserva = new javax.swing.JLabel();
        jDialog_PerfilCliente = new javax.swing.JDialog();
        jTextField43 = new javax.swing.JTextField();
        jComboBox38 = new javax.swing.JComboBox<>();
        jLabel_PC_FotoPerfil = new javax.swing.JLabel();
        jLabel_PC_NombreApellidos = new javax.swing.JLabel();
        jLabel_PC_Edad = new javax.swing.JLabel();
        jLabel175 = new javax.swing.JLabel();
        jLabel_PC_Objetivo = new javax.swing.JLabel();
        jLabel176 = new javax.swing.JLabel();
        jButton_PC_Editar = new javax.swing.JButton();
        jLabel_PC_EditarObjetivo = new javax.swing.JLabel();
        jLabel_PC_EditarPeso = new javax.swing.JLabel();
        jLabel177 = new javax.swing.JLabel();
        jLabel_PC_EditarAltura = new javax.swing.JLabel();
        jLabel_PC_Altura = new javax.swing.JLabel();
        jLabel_PC_Peso = new javax.swing.JLabel();
        jButton_PC_Volver = new javax.swing.JButton();
        jLabel_PC_Fondo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabelFondoRol = new javax.swing.JLabel();

        jDialog_Inicio.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_Inicio.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("GYMZONE");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel6MouseExited(evt);
            }
        });
        jDialog_Inicio.getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, 30, 230, 50));

        jButton5.setBackground(new java.awt.Color(0, 0, 0));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Registrarse");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton5MouseExited(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jDialog_Inicio.getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 180, -1));

        jButton6.setBackground(new java.awt.Color(0, 0, 0));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Iniciar Sesión");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton6MouseExited(evt);
            }
        });
        jDialog_Inicio.getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel_FondoInicioSesion.setPreferredSize(new java.awt.Dimension(900, 500));
        jDialog_Inicio.getContentPane().add(jLabel_FondoInicioSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_IniciarSesion.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_IniciarSesion.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("INICIAR SESIÓN");
        jDialog_IniciarSesion.getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 900, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Introduzca su DNI / Correo Electrónico / Nombre Usuario:");
        jDialog_IniciarSesion.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 560, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("¿No tienes cuenta de usuario? Registrate");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel9MouseExited(evt);
            }
        });
        jDialog_IniciarSesion.getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 500, 30));
        jDialog_IniciarSesion.getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 500, 30));

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton7.setText("Volver");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton7MouseExited(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jDialog_IniciarSesion.getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 400, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel17.setText("Introduzca su contraseña:");
        jDialog_IniciarSesion.getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 500, 30));

        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton9.setText("Enviar");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton9MouseExited(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jDialog_IniciarSesion.getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 400, -1, -1));

        jPasswordField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField2ActionPerformed(evt);
            }
        });
        jDialog_IniciarSesion.getContentPane().add(jPasswordField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 500, 30));

        jLabel43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel43MouseClicked(evt);
            }
        });
        jDialog_IniciarSesion.getContentPane().add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 60, 50));

        jLabel45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel45MouseClicked(evt);
            }
        });
        jDialog_IniciarSesion.getContentPane().add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 60, 50));
        jDialog_IniciarSesion.getContentPane().add(jLabel_IniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_Registrarse.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("REGISTRARSE");
        jDialog_Registrarse.getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 900, 50));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(92, 188, 224));
        jLabel12.setText("Introduzca su DNI:");
        jDialog_Registrarse.getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 270, -1));
        jDialog_Registrarse.getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 270, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(92, 188, 224));
        jLabel13.setText("<html>Introduzca su nombre y <br>apellidos:</html>");
        jDialog_Registrarse.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 270, 50));
        jDialog_Registrarse.getContentPane().add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 270, 30));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel14.setText("Introduzca su nombre de usuario:");
        jDialog_Registrarse.getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 80, 320, -1));
        jDialog_Registrarse.getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 120, 300, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel15.setText("Introduzca su correo electrónico:");
        jDialog_Registrarse.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 180, 320, -1));
        jDialog_Registrarse.getContentPane().add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 220, 300, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel16.setText("Introduzca su contraseña:");
        jDialog_Registrarse.getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 280, 300, -1));

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton8.setText("Volver");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton8MouseExited(evt);
            }
        });
        jDialog_Registrarse.getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 410, -1, -1));

        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton10.setText("Enviar");
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton10MouseExited(evt);
            }
        });
        jDialog_Registrarse.getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 410, -1, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("¿Tienes cuenta de usuario? Inicia sesión");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel18MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel18MouseExited(evt);
            }
        });
        jDialog_Registrarse.getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 390, -1));
        jDialog_Registrarse.getContentPane().add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 320, 300, 30));

        jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel41MouseClicked(evt);
            }
        });
        jDialog_Registrarse.getContentPane().add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 320, 50, 30));

        jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel42MouseClicked(evt);
            }
        });
        jDialog_Registrarse.getContentPane().add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 320, 50, 30));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(92, 188, 224));
        jLabel38.setText("Introduzca su género:");
        jDialog_Registrarse.getContentPane().add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 220, -1));

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Femenino" }));
        jDialog_Registrarse.getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 120, -1));
        jDialog_Registrarse.getContentPane().add(jLabel_Registrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(-80, 0, 980, 500));

        jDialog_RegistrarseCliente.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("REGISTRARSE");
        jDialog_RegistrarseCliente.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 900, 50));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel20.setText("Introduzca su objetivo corporal:");
        jDialog_RegistrarseCliente.getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 250, 310, 30));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel21.setText("Introduzca su altura (cm):");
        jDialog_RegistrarseCliente.getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 80, 300, -1));
        jDialog_RegistrarseCliente.getContentPane().add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 120, 310, 30));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel22.setText("Introduzca su peso (kg):");
        jDialog_RegistrarseCliente.getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 170, 300, -1));
        jDialog_RegistrarseCliente.getContentPane().add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 210, 310, 30));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(92, 188, 224));
        jLabel23.setText("Introduzca su fecha de nacimiento:");
        jDialog_RegistrarseCliente.getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 340, -1));

        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton12.setText("Enviar");
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton12MouseExited(evt);
            }
        });
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jDialog_RegistrarseCliente.getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 400, -1, -1));

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mantenimiento", "Subida peso", "Bajada peso", "Recomposicion Corporal" }));
        jDialog_RegistrarseCliente.getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 290, 310, -1));
        jDialog_RegistrarseCliente.getContentPane().add(jCalendar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 410, 210));
        jDialog_RegistrarseCliente.getContentPane().add(jLabel_RegistrarseCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(-80, 0, 980, 500));

        jDialog_RegistrarseNutricionista.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("DIETA DEPORTIVA");
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel26MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel26MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel26MouseExited(evt);
            }
        });
        jDialog_RegistrarseNutricionista.getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 210, 30));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("DIETA CLÍNICA");
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel27MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel27MouseExited(evt);
            }
        });
        jDialog_RegistrarseNutricionista.getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, 210, 30));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("DIETA EXCLUSIÓN");
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel29MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel29MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel29MouseExited(evt);
            }
        });
        jDialog_RegistrarseNutricionista.getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 230, 210, 30));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("REGISTRARSE");
        jDialog_RegistrarseNutricionista.getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 900, 50));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Selecciona la especialidad del nutricionista en dietas:");
        jDialog_RegistrarseNutricionista.getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 520, -1));

        jButton15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton15MouseClicked(evt);
            }
        });
        jDialog_RegistrarseNutricionista.getContentPane().add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 160, 210, 190));

        jButton16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton16MouseClicked(evt);
            }
        });
        jDialog_RegistrarseNutricionista.getContentPane().add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 210, 190));

        jButton17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton17MouseClicked(evt);
            }
        });
        jDialog_RegistrarseNutricionista.getContentPane().add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 210, 190));
        jDialog_RegistrarseNutricionista.getContentPane().add(jLabel_RegistrarseNutricionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(-80, 0, 980, 500));

        jDialog_RegistrarseMonitor.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("RUTINA DE GIMNASIO");
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel30MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel30MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel30MouseExited(evt);
            }
        });
        jDialog_RegistrarseMonitor.getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 210, 30));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("RUTINA DE CARDIO");
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel32MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel32MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel32MouseExited(evt);
            }
        });
        jDialog_RegistrarseMonitor.getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, 210, 30));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("RUTINA DE CROSSFIT");
        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel33MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel33MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel33MouseExited(evt);
            }
        });
        jDialog_RegistrarseMonitor.getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 230, 210, 30));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("REGISTRARSE");
        jDialog_RegistrarseMonitor.getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 900, 50));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Selecciona la función del monitor en rutinas:");
        jDialog_RegistrarseMonitor.getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 440, -1));

        jButton18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton18MouseClicked(evt);
            }
        });
        jDialog_RegistrarseMonitor.getContentPane().add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 160, 210, 190));

        jButton19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton19MouseClicked(evt);
            }
        });
        jDialog_RegistrarseMonitor.getContentPane().add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 210, 190));

        jButton20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton20MouseClicked(evt);
            }
        });
        jDialog_RegistrarseMonitor.getContentPane().add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 210, 190));
        jDialog_RegistrarseMonitor.getContentPane().add(jLabel_RegistrarseMonitor, new org.netbeans.lib.awtextra.AbsoluteConstraints(-80, 0, 980, 500));

        jDialog_FuncionalidadMonitor.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_FuncionalidadMonitor.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("GYMZONE");
        jLabel51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel51MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel51MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel51MouseExited(evt);
            }
        });
        jDialog_FuncionalidadMonitor.getContentPane().add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 230, 50));

        jLabel_FCC_Monitor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FCC_MonitorMouseClicked(evt);
            }
        });
        jDialog_FuncionalidadMonitor.getContentPane().add(jLabel_FCC_Monitor, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 80, 100, 100));

        jLabel_FG_Monitor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FG_MonitorMouseClicked(evt);
            }
        });
        jDialog_FuncionalidadMonitor.getContentPane().add(jLabel_FG_Monitor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 100, 100));

        jLabel_NU_Monitor.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_NU_Monitor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_NU_Monitor.setText("USUARIO");
        jLabel_NU_Monitor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_NU_MonitorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_NU_MonitorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_NU_MonitorMouseExited(evt);
            }
        });
        jDialog_FuncionalidadMonitor.getContentPane().add(jLabel_NU_Monitor, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 270, 30));

        jLabel68.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setText("<html>ADMINISTRAR<br>RUTINAS</html>");
        jLabel68.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel68MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel68MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel68MouseExited(evt);
            }
        });
        jDialog_FuncionalidadMonitor.getContentPane().add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, 200, 70));

        jLabel71.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setText("<html>ALTA<br>RUTINA</html>");
        jLabel71.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel71MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel71MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel71MouseExited(evt);
            }
        });
        jDialog_FuncionalidadMonitor.getContentPane().add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 200, 70));

        jButton32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton32MouseClicked(evt);
            }
        });
        jDialog_FuncionalidadMonitor.getContentPane().add(jButton32, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 200, 200));

        jButton33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton33MouseClicked(evt);
            }
        });
        jDialog_FuncionalidadMonitor.getContentPane().add(jButton33, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 240, 200, 200));

        jLabel_FondoFuncionalidadesMonitor.setMaximumSize(new java.awt.Dimension(900, 500));
        jLabel_FondoFuncionalidadesMonitor.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_FuncionalidadMonitor.getContentPane().add(jLabel_FondoFuncionalidadesMonitor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 500));

        jDialog_GestionCuentaBancaria.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_GestionCuentaBancaria.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("GESTIÓN CUENTA BANCARIA");
        jLabel44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel44MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel44MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel44MouseExited(evt);
            }
        });
        jDialog_GestionCuentaBancaria.getContentPane().add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, 30, 900, 50));

        jButton22.setBackground(new java.awt.Color(0, 0, 0));
        jButton22.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton22.setForeground(new java.awt.Color(255, 255, 255));
        jButton22.setText("Registrar");
        jButton22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton22MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton22MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton22MouseExited(evt);
            }
        });
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jDialog_GestionCuentaBancaria.getContentPane().add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 180, -1));

        jButton23.setBackground(new java.awt.Color(0, 0, 0));
        jButton23.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton23.setForeground(new java.awt.Color(255, 255, 255));
        jButton23.setText("Acceder");
        jButton23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton23MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton23MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton23MouseExited(evt);
            }
        });
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jDialog_GestionCuentaBancaria.getContentPane().add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 180, -1));

        jButton24.setBackground(new java.awt.Color(0, 0, 0));
        jButton24.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton24.setForeground(new java.awt.Color(255, 255, 255));
        jButton24.setText("Borrar");
        jButton24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton24MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton24MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton24MouseExited(evt);
            }
        });
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jDialog_GestionCuentaBancaria.getContentPane().add(jButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, 180, -1));

        jButton34.setBackground(new java.awt.Color(0, 0, 0));
        jButton34.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton34.setForeground(new java.awt.Color(255, 255, 255));
        jButton34.setText("Volver");
        jButton34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton34MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton34MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton34MouseExited(evt);
            }
        });
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        jDialog_GestionCuentaBancaria.getContentPane().add(jButton34, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 350, 180, -1));
        jDialog_GestionCuentaBancaria.getContentPane().add(jLabel_GestionCuentaBancaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));
        jLabel_GestionCuentaBancaria.getAccessibleContext().setAccessibleName("jLabel_FondoGestionCuentaBancaria");

        jDialog_RegistrarCuentaBancaria.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_RegistrarCuentaBancaria.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("REGISTRARSE");
        jDialog_RegistrarCuentaBancaria.getContentPane().add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 900, 50));

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel47.setText("Introduzca el nombre del titular:");
        jDialog_RegistrarCuentaBancaria.getContentPane().add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 320, -1));
        jDialog_RegistrarCuentaBancaria.getContentPane().add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 320, 30));

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel48.setText("Introduzca su número de cuenta: ");
        jDialog_RegistrarCuentaBancaria.getContentPane().add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 320, 30));
        jDialog_RegistrarCuentaBancaria.getContentPane().add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 320, 30));

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel49.setText("Selecciona el banco:");
        jDialog_RegistrarCuentaBancaria.getContentPane().add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 90, 320, -1));

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel50.setText("Introduzca su saldo inicial:");
        jDialog_RegistrarCuentaBancaria.getContentPane().add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 230, 310, -1));
        jDialog_RegistrarCuentaBancaria.getContentPane().add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, 310, 30));

        jButton21.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton21.setText("Volver");
        jButton21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton21MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton21MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton21MouseExited(evt);
            }
        });
        jDialog_RegistrarCuentaBancaria.getContentPane().add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 410, -1, -1));

        jButton25.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton25.setText("Enviar");
        jButton25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton25MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton25MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton25MouseExited(evt);
            }
        });
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jDialog_RegistrarCuentaBancaria.getContentPane().add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 410, -1, -1));

        jComboBox3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Santander", "BBVA", "CaixaBank" }));
        jDialog_RegistrarCuentaBancaria.getContentPane().add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 130, 230, 30));
        jDialog_RegistrarCuentaBancaria.getContentPane().add(jLabel_RegistrarCuentaBancaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_ConsultaCuentaBancaria.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_Banco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_BancoMouseClicked(evt);
            }
        });
        jDialog_ConsultaCuentaBancaria.getContentPane().add(jLabel_Banco, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 500, 100));

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("Numero Cuenta");
        jDialog_ConsultaCuentaBancaria.getContentPane().add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 320, 30));

        jLabel53.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("Saldo");
        jDialog_ConsultaCuentaBancaria.getContentPane().add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 320, 30));

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("Selecciona la operación a realizar:");
        jDialog_ConsultaCuentaBancaria.getContentPane().add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 330, 30));

        jButton26.setBackground(new java.awt.Color(0, 0, 0));
        jButton26.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton26.setForeground(new java.awt.Color(255, 255, 255));
        jButton26.setText("Enviar");
        jButton26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton26MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton26MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton26MouseExited(evt);
            }
        });
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jDialog_ConsultaCuentaBancaria.getContentPane().add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 240, -1, -1));

        jLabel55.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel55MouseClicked(evt);
            }
        });
        jDialog_ConsultaCuentaBancaria.getContentPane().add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, 130, 80));

        jLabel56.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel56MouseClicked(evt);
            }
        });
        jDialog_ConsultaCuentaBancaria.getContentPane().add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 130, 80));

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("Nombre Titular");
        jDialog_ConsultaCuentaBancaria.getContentPane().add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 320, 30));
        jDialog_ConsultaCuentaBancaria.getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 190, 330, 30));

        jButton27.setBackground(new java.awt.Color(0, 0, 0));
        jButton27.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton27.setForeground(new java.awt.Color(255, 255, 255));
        jButton27.setText("Volver");
        jButton27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton27MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton27MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton27MouseExited(evt);
            }
        });
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jDialog_ConsultaCuentaBancaria.getContentPane().add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 410, -1, -1));

        jLabel178.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel178.setForeground(new java.awt.Color(255, 255, 255));
        jLabel178.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel178.setText("Nombre Titular:");
        jDialog_ConsultaCuentaBancaria.getContentPane().add(jLabel178, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 160, 30));

        jLabel179.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel179.setForeground(new java.awt.Color(255, 255, 255));
        jLabel179.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel179.setText("Saldo Actual:");
        jDialog_ConsultaCuentaBancaria.getContentPane().add(jLabel179, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 240, 30));

        jLabel180.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel180.setForeground(new java.awt.Color(255, 255, 255));
        jLabel180.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel180.setText("Numero Cuenta:");
        jDialog_ConsultaCuentaBancaria.getContentPane().add(jLabel180, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 160, 30));
        jDialog_ConsultaCuentaBancaria.getContentPane().add(jLabel_ConsultaCuentaBancaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_ModificarCuentaBancaria.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_ModificarCuentaBancaria.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel59.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("MODIFICAR CUENTA BANCARIA");
        jDialog_ModificarCuentaBancaria.getContentPane().add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 900, 50));

        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel60.setText("Introduzca el nombre del titular:");
        jDialog_ModificarCuentaBancaria.getContentPane().add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 320, -1));
        jDialog_ModificarCuentaBancaria.getContentPane().add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 320, 30));

        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel62.setText("Selecciona el banco:");
        jDialog_ModificarCuentaBancaria.getContentPane().add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 170, 230, -1));

        jButton28.setBackground(new java.awt.Color(0, 0, 0));
        jButton28.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton28.setForeground(new java.awt.Color(255, 255, 255));
        jButton28.setText("Volver");
        jButton28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton28MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton28MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton28MouseExited(evt);
            }
        });
        jDialog_ModificarCuentaBancaria.getContentPane().add(jButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 410, -1, -1));

        jButton29.setBackground(new java.awt.Color(0, 0, 0));
        jButton29.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton29.setForeground(new java.awt.Color(255, 255, 255));
        jButton29.setText("Enviar");
        jButton29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton29MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton29MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton29MouseExited(evt);
            }
        });
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        jDialog_ModificarCuentaBancaria.getContentPane().add(jButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 410, -1, -1));

        jComboBox4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Santander", "BBVA", "CaixaBank" }));
        jDialog_ModificarCuentaBancaria.getContentPane().add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 210, 230, 30));
        jDialog_ModificarCuentaBancaria.getContentPane().add(jLabel_ModificarCuentaBancaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_PerfilUsuario.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jDialog_PerfilUsuario.getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 350, 30));
        jDialog_PerfilUsuario.getContentPane().add(jLabel_PU_FotoPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 350, 350));

        jLabel_PU_NombreApellidos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_PU_NombreApellidos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_PU_NombreApellidos.setText("jLabel58");
        jDialog_PerfilUsuario.getContentPane().add(jLabel_PU_NombreApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 390, 330, -1));

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setText("Contraseña:");
        jDialog_PerfilUsuario.getContentPane().add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, 360, 30));

        jLabel_PU_NombreUsuario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_PU_NombreUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_PU_NombreUsuario.setText("Nombre de usuario:");
        jDialog_PerfilUsuario.getContentPane().add(jLabel_PU_NombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 370, 30));

        jLabel66.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("Correo electrónico:");
        jDialog_PerfilUsuario.getContentPane().add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 360, 30));

        jButton_PU_Editar.setBackground(new java.awt.Color(0, 0, 0));
        jButton_PU_Editar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_PU_Editar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_PU_Editar.setText("Editar");
        jButton_PU_Editar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_PU_EditarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_PU_EditarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_PU_EditarMouseExited(evt);
            }
        });
        jButton_PU_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PU_EditarActionPerformed(evt);
            }
        });
        jDialog_PerfilUsuario.getContentPane().add(jButton_PU_Editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 140, 50));

        jLabel_PU_EditarNombreUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_PU_EditarNombreUsuarioMouseClicked(evt);
            }
        });
        jDialog_PerfilUsuario.getContentPane().add(jLabel_PU_EditarNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 40, 50, 50));

        jLabel_PU_EditarContrasenna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_PU_EditarContrasennaMouseClicked(evt);
            }
        });
        jDialog_PerfilUsuario.getContentPane().add(jLabel_PU_EditarContrasenna, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 300, 50, 50));

        jLabel73.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel73.setText("Nombre de usuario:");
        jDialog_PerfilUsuario.getContentPane().add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 370, 30));

        jLabel_PU_EditarCorreo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_PU_EditarCorreoMouseClicked(evt);
            }
        });
        jDialog_PerfilUsuario.getContentPane().add(jLabel_PU_EditarCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 170, 50, 50));

        jLabel_PU_Correo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_PU_Correo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_PU_Correo.setText("Correo electrónico:");
        jDialog_PerfilUsuario.getContentPane().add(jLabel_PU_Correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, 360, 30));

        jLabel_PU_Contrasenna.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_PU_Contrasenna.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_PU_Contrasenna.setText("Contraseña:");
        jDialog_PerfilUsuario.getContentPane().add(jLabel_PU_Contrasenna, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, 360, 30));

        jButton_PU_Volver.setBackground(new java.awt.Color(0, 0, 0));
        jButton_PU_Volver.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_PU_Volver.setForeground(new java.awt.Color(255, 255, 255));
        jButton_PU_Volver.setText("Volver");
        jButton_PU_Volver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_PU_VolverMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_PU_VolverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_PU_VolverMouseExited(evt);
            }
        });
        jDialog_PerfilUsuario.getContentPane().add(jButton_PU_Volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 400, 140, 50));

        jButton_PU_AccederPerCliente.setBackground(new java.awt.Color(0, 0, 0));
        jButton_PU_AccederPerCliente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_PU_AccederPerCliente.setForeground(new java.awt.Color(255, 255, 255));
        jButton_PU_AccederPerCliente.setText("<html>Ver Datos <br>Corporales</html>");
        jButton_PU_AccederPerCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_PU_AccederPerClienteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_PU_AccederPerClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_PU_AccederPerClienteMouseExited(evt);
            }
        });
        jButton_PU_AccederPerCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PU_AccederPerClienteActionPerformed(evt);
            }
        });
        jDialog_PerfilUsuario.getContentPane().add(jButton_PU_AccederPerCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, 140, 50));
        jDialog_PerfilUsuario.getContentPane().add(jLabel_PU_Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_AltaRutina.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_AltaRutina.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("ALTA RUTINA");
        jDialog_AltaRutina.getContentPane().add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel63.setText("Selecciona los días:");
        jDialog_AltaRutina.getContentPane().add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, 330, -1));
        jDialog_AltaRutina.getContentPane().add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 330, 30));

        jButton35.setBackground(new java.awt.Color(0, 0, 0));
        jButton35.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton35.setForeground(new java.awt.Color(255, 255, 255));
        jButton35.setText("Continuar");
        jButton35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton35MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton35MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton35MouseExited(evt);
            }
        });
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        jDialog_AltaRutina.getContentPane().add(jButton35, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, -1, 50));

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel65.setText("Introduzca el nombre de la rutina:");
        jDialog_AltaRutina.getContentPane().add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 330, -1));

        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel69.setText("Selecciona el objetivo:");
        jDialog_AltaRutina.getContentPane().add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 330, -1));

        jComboBox6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3 días", "4 días", "5 días" }));
        jDialog_AltaRutina.getContentPane().add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 150, 190, 30));

        jComboBox7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Subida peso", "Bajada peso", "Recomposicion Corporal", "Mantenimiento" }));
        jDialog_AltaRutina.getContentPane().add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 330, 30));

        jButton36.setBackground(new java.awt.Color(0, 0, 0));
        jButton36.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton36.setForeground(new java.awt.Color(255, 255, 255));
        jButton36.setText("Volver");
        jButton36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton36MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton36MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton36MouseExited(evt);
            }
        });
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        jDialog_AltaRutina.getContentPane().add(jButton36, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, -1, 50));
        jDialog_AltaRutina.getContentPane().add(jLabel_FondoAltaRutina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_RutinaDia.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_RutinaDia.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_FotoPecho.setMaximumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoPecho.setMinimumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoPecho.setPreferredSize(new java.awt.Dimension(50, 15));
        jLabel_FotoPecho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FotoPechoMouseClicked(evt);
            }
        });
        jDialog_RutinaDia.getContentPane().add(jLabel_FotoPecho, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 50, 50));

        jLabel72.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel72.setText("DIA");
        jDialog_RutinaDia.getContentPane().add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel_FotoEspalda.setMaximumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoEspalda.setMinimumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoEspalda.setPreferredSize(new java.awt.Dimension(50, 15));
        jLabel_FotoEspalda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FotoEspaldaMouseClicked(evt);
            }
        });
        jDialog_RutinaDia.getContentPane().add(jLabel_FotoEspalda, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 50, 50));

        jLabel_FotoAbs.setToolTipText("");
        jLabel_FotoAbs.setMaximumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoAbs.setMinimumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoAbs.setPreferredSize(new java.awt.Dimension(50, 15));
        jLabel_FotoAbs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FotoAbsMouseClicked(evt);
            }
        });
        jDialog_RutinaDia.getContentPane().add(jLabel_FotoAbs, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 50, 50));

        jLabel_FotoReiniciar.setMaximumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoReiniciar.setMinimumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoReiniciar.setPreferredSize(new java.awt.Dimension(50, 15));
        jLabel_FotoReiniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FotoReiniciarMouseClicked(evt);
            }
        });
        jDialog_RutinaDia.getContentPane().add(jLabel_FotoReiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 70, 50, 50));

        jLabel_FotoBrazo.setMaximumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoBrazo.setMinimumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoBrazo.setPreferredSize(new java.awt.Dimension(50, 15));
        jLabel_FotoBrazo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FotoBrazoMouseClicked(evt);
            }
        });
        jDialog_RutinaDia.getContentPane().add(jLabel_FotoBrazo, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 50, 50));

        jLabel_FotoPierna.setMaximumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoPierna.setMinimumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoPierna.setPreferredSize(new java.awt.Dimension(50, 15));
        jLabel_FotoPierna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FotoPiernaMouseClicked(evt);
            }
        });
        jDialog_RutinaDia.getContentPane().add(jLabel_FotoPierna, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 50, 50));

        jButton38.setBackground(new java.awt.Color(0, 0, 0));
        jButton38.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton38.setForeground(new java.awt.Color(255, 255, 255));
        jButton38.setText("Continuar");
        jButton38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton38MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton38MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton38MouseExited(evt);
            }
        });
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });
        jDialog_RutinaDia.getContentPane().add(jButton38, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, -1, 50));

        jButton37.setBackground(new java.awt.Color(0, 0, 0));
        jButton37.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton37.setForeground(new java.awt.Color(255, 255, 255));
        jButton37.setText("Volver");
        jButton37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton37MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton37MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton37MouseExited(evt);
            }
        });
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        jDialog_RutinaDia.getContentPane().add(jButton37, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, -1, 50));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Descripcion", "Musculos Implicados", "Seleccionar ejercicio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        jDialog_RutinaDia.getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 870, 170));
        jDialog_RutinaDia.getContentPane().add(jLabel_FondoRutinaDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_ConsultaRutina.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jDialog_ConsultaRutina.getContentPane().add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 230, 30));

        jLabel74.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("CONSULTA RUTINA");
        jDialog_ConsultaRutina.getContentPane().add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel67.setText("<html>Selecciona el autor<br> a buscar:</html>");
        jDialog_ConsultaRutina.getContentPane().add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 140, -1));

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel70.setText("<html>Introduzca el nombre de la rutina <br>a buscar:</html>");
        jDialog_ConsultaRutina.getContentPane().add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 230, -1));

        jLabel75.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel75.setText("<html>Selecciona el tipo de objetivo<br> a buscar:</html>");
        jDialog_ConsultaRutina.getContentPane().add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 210, -1));

        jLabel_ReiniciarBusquedaRutina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_ReiniciarBusquedaRutinaMouseClicked(evt);
            }
        });
        jDialog_ConsultaRutina.getContentPane().add(jLabel_ReiniciarBusquedaRutina, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 120, 50, 50));

        jLabel_BuscarRutina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_BuscarRutinaMouseClicked(evt);
            }
        });
        jDialog_ConsultaRutina.getContentPane().add(jLabel_BuscarRutina, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 120, 50, 50));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Subida peso", "Bajada peso", "Recomposicion Corporal", "Mantenimiento" }));
        jDialog_ConsultaRutina.getContentPane().add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 200, 30));

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Subida peso", "Bajada peso", "Recomposicion Corporal", "Mantenimiento" }));
        jDialog_ConsultaRutina.getContentPane().add(jComboBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 200, 30));

        jButton40.setBackground(new java.awt.Color(0, 0, 0));
        jButton40.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton40.setForeground(new java.awt.Color(255, 255, 255));
        jButton40.setText("Volver");
        jButton40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton40MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton40MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton40MouseExited(evt);
            }
        });
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });
        jDialog_ConsultaRutina.getContentPane().add(jButton40, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, -1, 50));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Objetivo", "Autor", "Visualizar ejercicios", "Modificar rutina", "Eliminar rutina"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jDialog_ConsultaRutina.getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 870, 170));
        jDialog_ConsultaRutina.getContentPane().add(jLabel_FondoConsultaRutina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_ConsultaRutina_VerEjercicios.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel76.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel76.setText(" RUTINA");
        jDialog_ConsultaRutina_VerEjercicios.getContentPane().add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 60));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Descripcion", "Musculos Implicados"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jDialog_ConsultaRutina_VerEjercicios.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 870, 260));

        jButton39.setBackground(new java.awt.Color(0, 0, 0));
        jButton39.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton39.setForeground(new java.awt.Color(255, 255, 255));
        jButton39.setText("Siguiente");
        jButton39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton39MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton39MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton39MouseExited(evt);
            }
        });
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });
        jDialog_ConsultaRutina_VerEjercicios.getContentPane().add(jButton39, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 390, -1, 50));

        jButton41.setBackground(new java.awt.Color(0, 0, 0));
        jButton41.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton41.setForeground(new java.awt.Color(255, 255, 255));
        jButton41.setText("Anterior");
        jButton41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton41MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton41MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton41MouseExited(evt);
            }
        });
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });
        jDialog_ConsultaRutina_VerEjercicios.getContentPane().add(jButton41, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 390, -1, 50));

        jButton42.setBackground(new java.awt.Color(0, 0, 0));
        jButton42.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton42.setForeground(new java.awt.Color(255, 255, 255));
        jButton42.setText("Volver");
        jButton42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton42MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton42MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton42MouseExited(evt);
            }
        });
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });
        jDialog_ConsultaRutina_VerEjercicios.getContentPane().add(jButton42, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, -1, 50));
        jDialog_ConsultaRutina_VerEjercicios.getContentPane().add(jLabel_FondoVerEjercicios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_ConsultaRutina_ModificarRutina.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel78.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText("Selecciona el tipo de objetivo a modificar:");
        jDialog_ConsultaRutina_ModificarRutina.getContentPane().add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 400, -1));
        jDialog_ConsultaRutina_ModificarRutina.getContentPane().add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 390, 30));

        jLabel77.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel77.setText("MODIFICAR RUTINA");
        jDialog_ConsultaRutina_ModificarRutina.getContentPane().add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel79.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel79.setText("Selecciona el autor a modificar:");
        jDialog_ConsultaRutina_ModificarRutina.getContentPane().add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 280, -1));

        jLabel80.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel80.setText("Introduzca el nombre de la rutina a modificar:");
        jDialog_ConsultaRutina_ModificarRutina.getContentPane().add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 400, -1));

        jButton43.setBackground(new java.awt.Color(0, 0, 0));
        jButton43.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton43.setForeground(new java.awt.Color(255, 255, 255));
        jButton43.setText("Enviar");
        jButton43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton43MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton43MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton43MouseExited(evt);
            }
        });
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });
        jDialog_ConsultaRutina_ModificarRutina.getContentPane().add(jButton43, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 390, -1, 50));

        jButton44.setBackground(new java.awt.Color(0, 0, 0));
        jButton44.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton44.setForeground(new java.awt.Color(255, 255, 255));
        jButton44.setText("Volver");
        jButton44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton44MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton44MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton44MouseExited(evt);
            }
        });
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });
        jDialog_ConsultaRutina_ModificarRutina.getContentPane().add(jButton44, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 390, -1, 50));

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Subida peso", "Bajada peso", "Recomposicion Corporal", "Mantenimiento" }));
        jDialog_ConsultaRutina_ModificarRutina.getContentPane().add(jComboBox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 200, 30));

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jDialog_ConsultaRutina_ModificarRutina.getContentPane().add(jComboBox10, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, 270, 30));
        jDialog_ConsultaRutina_ModificarRutina.getContentPane().add(jLabel_FondoModificarRutina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_FuncionalidadNutricionista.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_FuncionalidadNutricionista.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel81.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel81.setText("GYMZONE");
        jLabel81.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel81MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel81MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel81MouseExited(evt);
            }
        });
        jDialog_FuncionalidadNutricionista.getContentPane().add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 230, 50));

        jLabel_FCC_Nutricionista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FCC_NutricionistaMouseClicked(evt);
            }
        });
        jDialog_FuncionalidadNutricionista.getContentPane().add(jLabel_FCC_Nutricionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 80, 100, 100));

        jLabel_FG_Nutricionista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FG_NutricionistaMouseClicked(evt);
            }
        });
        jDialog_FuncionalidadNutricionista.getContentPane().add(jLabel_FG_Nutricionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 100, 100));

        jLabel_NU_Nutricionista.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_NU_Nutricionista.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_NU_Nutricionista.setText("USUARIO");
        jLabel_NU_Nutricionista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_NU_NutricionistaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_NU_NutricionistaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_NU_NutricionistaMouseExited(evt);
            }
        });
        jDialog_FuncionalidadNutricionista.getContentPane().add(jLabel_NU_Nutricionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 270, 30));

        jLabel82.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("<html>ADMINISTRAR<br>DIETAS</html>");
        jLabel82.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel82MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel82MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel82MouseExited(evt);
            }
        });
        jDialog_FuncionalidadNutricionista.getContentPane().add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, 200, 70));

        jLabel83.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText("<html>ALTA<br>DIETA</html>");
        jLabel83.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel83MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel83MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel83MouseExited(evt);
            }
        });
        jDialog_FuncionalidadNutricionista.getContentPane().add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 200, 70));

        jButton45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton45MouseClicked(evt);
            }
        });
        jDialog_FuncionalidadNutricionista.getContentPane().add(jButton45, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 200, 200));

        jButton46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton46MouseClicked(evt);
            }
        });
        jDialog_FuncionalidadNutricionista.getContentPane().add(jButton46, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 240, 200, 200));

        jLabel_FondoFuncionalidadesNutricionista.setMaximumSize(new java.awt.Dimension(900, 500));
        jLabel_FondoFuncionalidadesNutricionista.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_FuncionalidadNutricionista.getContentPane().add(jLabel_FondoFuncionalidadesNutricionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 500));

        jDialog_AltaDieta.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel84.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(255, 255, 255));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("ALTA DIETA");
        jDialog_AltaDieta.getContentPane().add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel85.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel85.setText("Introduzca la descripcion:");
        jDialog_AltaDieta.getContentPane().add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 200, 250, -1));
        jDialog_AltaDieta.getContentPane().add(jTextField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 330, 30));

        jButton47.setBackground(new java.awt.Color(0, 0, 0));
        jButton47.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton47.setForeground(new java.awt.Color(255, 255, 255));
        jButton47.setText("Continuar");
        jButton47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton47MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton47MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton47MouseExited(evt);
            }
        });
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });
        jDialog_AltaDieta.getContentPane().add(jButton47, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, -1, 50));

        jLabel86.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel86.setText("Introduzca el nombre de la dieta:");
        jDialog_AltaDieta.getContentPane().add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 330, -1));

        jLabel87.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel87.setText("Selecciona el tipo de la dieta:");
        jDialog_AltaDieta.getContentPane().add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 330, -1));

        jComboBox11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3 días", "4 días", "5 días" }));
        jDialog_AltaDieta.getContentPane().add(jComboBox11, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, 190, 30));

        jComboBox12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Subida peso", "Bajada peso", "Recomposicion Corporal", "Mantenimiento" }));
        jDialog_AltaDieta.getContentPane().add(jComboBox12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 330, 30));

        jButton48.setBackground(new java.awt.Color(0, 0, 0));
        jButton48.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton48.setForeground(new java.awt.Color(255, 255, 255));
        jButton48.setText("Volver");
        jButton48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton48MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton48MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton48MouseExited(evt);
            }
        });
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });
        jDialog_AltaDieta.getContentPane().add(jButton48, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, -1, 50));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane5.setViewportView(jTextArea1);

        jDialog_AltaDieta.getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 240, 250, -1));

        jLabel89.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel89.setText("Selecciona los días:");
        jDialog_AltaDieta.getContentPane().add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, 330, -1));
        jDialog_AltaDieta.getContentPane().add(jLabel_FondoAltaDieta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_DietaDia.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_FotoVerdura.setMaximumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoVerdura.setMinimumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoVerdura.setPreferredSize(new java.awt.Dimension(50, 15));
        jLabel_FotoVerdura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FotoVerduraMouseClicked(evt);
            }
        });
        jDialog_DietaDia.getContentPane().add(jLabel_FotoVerdura, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 50, 50));

        jLabel88.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel88.setText("DIA");
        jDialog_DietaDia.getContentPane().add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel_FotoCarbohidratos.setMaximumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoCarbohidratos.setMinimumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoCarbohidratos.setPreferredSize(new java.awt.Dimension(50, 15));
        jLabel_FotoCarbohidratos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FotoCarbohidratosMouseClicked(evt);
            }
        });
        jDialog_DietaDia.getContentPane().add(jLabel_FotoCarbohidratos, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 50, 50));

        jLabel_FotoProteina.setToolTipText("");
        jLabel_FotoProteina.setMaximumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoProteina.setMinimumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoProteina.setPreferredSize(new java.awt.Dimension(50, 15));
        jLabel_FotoProteina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FotoProteinaMouseClicked(evt);
            }
        });
        jDialog_DietaDia.getContentPane().add(jLabel_FotoProteina, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 50, 50));

        jLabel_FotoReiniciarDieta.setMaximumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoReiniciarDieta.setMinimumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoReiniciarDieta.setPreferredSize(new java.awt.Dimension(50, 15));
        jLabel_FotoReiniciarDieta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FotoReiniciarDietaMouseClicked(evt);
            }
        });
        jDialog_DietaDia.getContentPane().add(jLabel_FotoReiniciarDieta, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 70, 50, 50));

        jLabel_FotoLacteo.setMaximumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoLacteo.setMinimumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoLacteo.setPreferredSize(new java.awt.Dimension(50, 15));
        jLabel_FotoLacteo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FotoLacteoMouseClicked(evt);
            }
        });
        jDialog_DietaDia.getContentPane().add(jLabel_FotoLacteo, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, 50, 50));

        jLabel_FotoFruta.setMaximumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoFruta.setMinimumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoFruta.setPreferredSize(new java.awt.Dimension(50, 15));
        jLabel_FotoFruta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FotoFrutaMouseClicked(evt);
            }
        });
        jDialog_DietaDia.getContentPane().add(jLabel_FotoFruta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 50, 50));

        jLabel_FotoAceites.setMaximumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoAceites.setMinimumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoAceites.setPreferredSize(new java.awt.Dimension(50, 15));
        jLabel_FotoAceites.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FotoAceitesMouseClicked(evt);
            }
        });
        jDialog_DietaDia.getContentPane().add(jLabel_FotoAceites, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, 50, 50));

        jLabel_FotoDulces.setMaximumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoDulces.setMinimumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoDulces.setPreferredSize(new java.awt.Dimension(50, 15));
        jLabel_FotoDulces.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FotoDulcesMouseClicked(evt);
            }
        });
        jDialog_DietaDia.getContentPane().add(jLabel_FotoDulces, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 70, 50, 50));

        jButton49.setBackground(new java.awt.Color(0, 0, 0));
        jButton49.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton49.setForeground(new java.awt.Color(255, 255, 255));
        jButton49.setText("Continuar");
        jButton49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton49MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton49MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton49MouseExited(evt);
            }
        });
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });
        jDialog_DietaDia.getContentPane().add(jButton49, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, -1, 50));

        jButton50.setBackground(new java.awt.Color(0, 0, 0));
        jButton50.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton50.setForeground(new java.awt.Color(255, 255, 255));
        jButton50.setText("Volver");
        jButton50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton50MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton50MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton50MouseExited(evt);
            }
        });
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });
        jDialog_DietaDia.getContentPane().add(jButton50, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, -1, 50));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Calorias por porcion", "Proteinas por porcion", "Carbohidratos por porcion", "Grasas por porcion", "Seleccionar alimento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(3).setResizable(false);
            jTable4.getColumnModel().getColumn(5).setHeaderValue("Seleccionar alimento");
        }

        jDialog_DietaDia.getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 880, 170));
        jDialog_DietaDia.getContentPane().add(jLabel_FondoDietaDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_ConsultaDieta.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jDialog_ConsultaDieta.getContentPane().add(jTextField19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 230, 30));

        jLabel90.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel90.setText("CONSULTA DIETA");
        jDialog_ConsultaDieta.getContentPane().add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel91.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel91.setText("<html>Selecciona el autor<br> a buscar:</html>");
        jDialog_ConsultaDieta.getContentPane().add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 140, -1));

        jLabel92.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel92.setText("<html>Introduzca el nombre de la dieta <br>a buscar:</html>");
        jDialog_ConsultaDieta.getContentPane().add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 230, -1));

        jLabel93.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel93.setText("<html>Selecciona el tipo de objetivo<br> a buscar:</html>");
        jDialog_ConsultaDieta.getContentPane().add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 210, -1));

        jLabel_ReiniciarBusquedaDieta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_ReiniciarBusquedaDietaMouseClicked(evt);
            }
        });
        jDialog_ConsultaDieta.getContentPane().add(jLabel_ReiniciarBusquedaDieta, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 120, 50, 50));

        jLabel_BuscarDieta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_BuscarDietaMouseClicked(evt);
            }
        });
        jDialog_ConsultaDieta.getContentPane().add(jLabel_BuscarDieta, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 120, 50, 50));

        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Subida peso", "Bajada peso", "Recomposicion Corporal", "Mantenimiento" }));
        jDialog_ConsultaDieta.getContentPane().add(jComboBox13, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 200, 30));

        jComboBox14.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Subida peso", "Bajada peso", "Recomposicion Corporal", "Mantenimiento" }));
        jDialog_ConsultaDieta.getContentPane().add(jComboBox14, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 200, 30));

        jButton51.setBackground(new java.awt.Color(0, 0, 0));
        jButton51.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton51.setForeground(new java.awt.Color(255, 255, 255));
        jButton51.setText("Volver");
        jButton51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton51MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton51MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton51MouseExited(evt);
            }
        });
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });
        jDialog_ConsultaDieta.getContentPane().add(jButton51, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, -1, 50));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Objetivo", "Autor", "Visualizar alimentos", "Modificar dieta", "Eliminar dieta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTable5);

        jDialog_ConsultaDieta.getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 870, 170));
        jDialog_ConsultaDieta.getContentPane().add(jLabel_FondoConsultaDieta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_ConsultaDieta_VisualizarAlimentos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel94.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(255, 255, 255));
        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel94.setText("DIETA");
        jDialog_ConsultaDieta_VisualizarAlimentos.getContentPane().add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel_FotoAlmuerzo.setMaximumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoAlmuerzo.setMinimumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoAlmuerzo.setPreferredSize(new java.awt.Dimension(50, 15));
        jLabel_FotoAlmuerzo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FotoAlmuerzoMouseClicked(evt);
            }
        });
        jDialog_ConsultaDieta_VisualizarAlimentos.getContentPane().add(jLabel_FotoAlmuerzo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 50, 50));

        jLabel_FotoCena.setMaximumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoCena.setMinimumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoCena.setPreferredSize(new java.awt.Dimension(50, 15));
        jLabel_FotoCena.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FotoCenaMouseClicked(evt);
            }
        });
        jDialog_ConsultaDieta_VisualizarAlimentos.getContentPane().add(jLabel_FotoCena, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 70, 50, 50));

        jLabel_FotoMerienda.setMaximumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoMerienda.setMinimumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoMerienda.setPreferredSize(new java.awt.Dimension(50, 15));
        jLabel_FotoMerienda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FotoMeriendaMouseClicked(evt);
            }
        });
        jDialog_ConsultaDieta_VisualizarAlimentos.getContentPane().add(jLabel_FotoMerienda, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, 50, 50));

        jLabel_FotoDesayuno.setMaximumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoDesayuno.setMinimumSize(new java.awt.Dimension(50, 15));
        jLabel_FotoDesayuno.setPreferredSize(new java.awt.Dimension(50, 15));
        jLabel_FotoDesayuno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FotoDesayunoMouseClicked(evt);
            }
        });
        jDialog_ConsultaDieta_VisualizarAlimentos.getContentPane().add(jLabel_FotoDesayuno, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 50, 50));

        jButton54.setBackground(new java.awt.Color(0, 0, 0));
        jButton54.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton54.setForeground(new java.awt.Color(255, 255, 255));
        jButton54.setText("Siguiente");
        jButton54.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton54MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton54MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton54MouseExited(evt);
            }
        });
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });
        jDialog_ConsultaDieta_VisualizarAlimentos.getContentPane().add(jButton54, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 380, -1, 50));

        jButton52.setBackground(new java.awt.Color(0, 0, 0));
        jButton52.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton52.setForeground(new java.awt.Color(255, 255, 255));
        jButton52.setText("Anterior");
        jButton52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton52MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton52MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton52MouseExited(evt);
            }
        });
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });
        jDialog_ConsultaDieta_VisualizarAlimentos.getContentPane().add(jButton52, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 380, -1, 50));

        jButton53.setBackground(new java.awt.Color(0, 0, 0));
        jButton53.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton53.setForeground(new java.awt.Color(255, 255, 255));
        jButton53.setText("Volver");
        jButton53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton53MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton53MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton53MouseExited(evt);
            }
        });
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });
        jDialog_ConsultaDieta_VisualizarAlimentos.getContentPane().add(jButton53, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, -1, 50));

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Calorias por porcion", "Proteinas por porcion", "Carbohidratos por porcion", "Grasas por porcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTable6);
        if (jTable6.getColumnModel().getColumnCount() > 0) {
            jTable6.getColumnModel().getColumn(3).setResizable(false);
        }

        jDialog_ConsultaDieta_VisualizarAlimentos.getContentPane().add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 870, 190));
        jDialog_ConsultaDieta_VisualizarAlimentos.getContentPane().add(jLabel_FondoConsultaEjercicios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_ModificarDieta.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel95.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(255, 255, 255));
        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel95.setText("MODIFICAR DIETA");
        jDialog_ModificarDieta.getContentPane().add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel96.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel96.setText("Introduzca la descripcion:");
        jDialog_ModificarDieta.getContentPane().add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 200, 250, -1));
        jDialog_ModificarDieta.getContentPane().add(jTextField20, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 330, 30));

        jButton55.setBackground(new java.awt.Color(0, 0, 0));
        jButton55.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton55.setForeground(new java.awt.Color(255, 255, 255));
        jButton55.setText("Continuar");
        jButton55.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton55MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton55MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton55MouseExited(evt);
            }
        });
        jButton55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton55ActionPerformed(evt);
            }
        });
        jDialog_ModificarDieta.getContentPane().add(jButton55, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, -1, 50));

        jLabel97.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel97.setText("Introduzca el nombre de la dieta:");
        jDialog_ModificarDieta.getContentPane().add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 330, -1));

        jLabel98.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel98.setText("Selecciona el tipo de la dieta:");
        jDialog_ModificarDieta.getContentPane().add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 330, -1));

        jComboBox15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox15.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3 días", "4 días", "5 días" }));
        jDialog_ModificarDieta.getContentPane().add(jComboBox15, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, 250, 30));

        jComboBox16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox16.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Subida peso", "Bajada peso", "Recomposicion Corporal", "Mantenimiento" }));
        jDialog_ModificarDieta.getContentPane().add(jComboBox16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 330, 30));

        jButton56.setBackground(new java.awt.Color(0, 0, 0));
        jButton56.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton56.setForeground(new java.awt.Color(255, 255, 255));
        jButton56.setText("Volver");
        jButton56.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton56MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton56MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton56MouseExited(evt);
            }
        });
        jButton56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton56ActionPerformed(evt);
            }
        });
        jDialog_ModificarDieta.getContentPane().add(jButton56, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, -1, 50));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane8.setViewportView(jTextArea2);

        jDialog_ModificarDieta.getContentPane().add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 240, 250, -1));

        jLabel99.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel99.setText("Selecciona el autor a modificar:");
        jDialog_ModificarDieta.getContentPane().add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, 330, -1));
        jDialog_ModificarDieta.getContentPane().add(jLabel_FondoModificarDieta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_FuncionalidadAdministrador.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_FuncionalidadAdministrador.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel100.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel100.setText("GYMZONE");
        jLabel100.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel100MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel100MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel100MouseExited(evt);
            }
        });
        jDialog_FuncionalidadAdministrador.getContentPane().add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 230, 50));

        jLabel_FCC_Administrador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FCC_AdministradorMouseClicked(evt);
            }
        });
        jDialog_FuncionalidadAdministrador.getContentPane().add(jLabel_FCC_Administrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 80, 100, 100));

        jLabel_FG_Administrador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FG_AdministradorMouseClicked(evt);
            }
        });
        jDialog_FuncionalidadAdministrador.getContentPane().add(jLabel_FG_Administrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 100, 100));

        jLabel_NU_Administrador.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_NU_Administrador.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_NU_Administrador.setText("USUARIO");
        jLabel_NU_Administrador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_NU_AdministradorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_NU_AdministradorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_NU_AdministradorMouseExited(evt);
            }
        });
        jDialog_FuncionalidadAdministrador.getContentPane().add(jLabel_NU_Administrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 290, 30));

        jLabel102.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel102.setText("<html>GESTIÓN<br>SALAS</html>");
        jLabel102.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel102MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel102MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel102MouseExited(evt);
            }
        });
        jDialog_FuncionalidadAdministrador.getContentPane().add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 200, 70));

        jLabel103.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel103.setText("<html>GESTIÓN<br>PRODUCTOS</html>");
        jLabel103.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel103MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel103MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel103MouseExited(evt);
            }
        });
        jDialog_FuncionalidadAdministrador.getContentPane().add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 200, 70));

        jLabel104.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel104.setText("<html>GESTIÓN<br>SERVICIOS</html>");
        jLabel104.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel104MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel104MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel104MouseExited(evt);
            }
        });
        jDialog_FuncionalidadAdministrador.getContentPane().add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 300, 200, 70));

        jLabel105.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel105.setText("<html>GESTIÓN<br>RUTINAS</html>");
        jLabel105.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel105MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel105MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel105MouseExited(evt);
            }
        });
        jDialog_FuncionalidadAdministrador.getContentPane().add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 200, 70));

        jButton57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton57MouseClicked(evt);
            }
        });
        jDialog_FuncionalidadAdministrador.getContentPane().add(jButton57, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 240, 200, 200));

        jButton59.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton59MouseClicked(evt);
            }
        });
        jDialog_FuncionalidadAdministrador.getContentPane().add(jButton59, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 200, 200));

        jButton60.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton60MouseClicked(evt);
            }
        });
        jDialog_FuncionalidadAdministrador.getContentPane().add(jButton60, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 200, 200));

        jButton61.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton61MouseClicked(evt);
            }
        });
        jButton61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton61ActionPerformed(evt);
            }
        });
        jDialog_FuncionalidadAdministrador.getContentPane().add(jButton61, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, 200, 200));

        jLabel_FondoFuncionalidadesAdministrador.setMaximumSize(new java.awt.Dimension(900, 500));
        jLabel_FondoFuncionalidadesAdministrador.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_FuncionalidadAdministrador.getContentPane().add(jLabel_FondoFuncionalidadesAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 500));

        jDialog_OperacionesRutinaAdministrador.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_OperacionesRutinaAdministrador.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel101.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel101.setText("OPERACIONES RUTINAS");
        jLabel101.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel101MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel101MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel101MouseExited(evt);
            }
        });
        jDialog_OperacionesRutinaAdministrador.getContentPane().add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 900, 50));

        jLabel_AltaRutina.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_AltaRutina.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_AltaRutina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_AltaRutina.setText("<html>ALTA<br>RUTINA</html>");
        jLabel_AltaRutina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_AltaRutinaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_AltaRutinaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_AltaRutinaMouseExited(evt);
            }
        });
        jDialog_OperacionesRutinaAdministrador.getContentPane().add(jLabel_AltaRutina, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 200, 70));

        jButton_AltaRutina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_AltaRutinaMouseClicked(evt);
            }
        });
        jDialog_OperacionesRutinaAdministrador.getContentPane().add(jButton_AltaRutina, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 200, 200));

        jLabel_AdministrarRutina.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_AdministrarRutina.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_AdministrarRutina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_AdministrarRutina.setText("<html>ADMINISTRAR<br>RUTINAS</html>");
        jLabel_AdministrarRutina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_AdministrarRutinaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_AdministrarRutinaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_AdministrarRutinaMouseExited(evt);
            }
        });
        jDialog_OperacionesRutinaAdministrador.getContentPane().add(jLabel_AdministrarRutina, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 300, 200, 70));

        jButton_AdministrarRutina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_AdministrarRutinaMouseClicked(evt);
            }
        });
        jButton_AdministrarRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AdministrarRutinaActionPerformed(evt);
            }
        });
        jDialog_OperacionesRutinaAdministrador.getContentPane().add(jButton_AdministrarRutina, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 200, 200));

        jLabel_FondoOperacionesRutinaAdministrador.setMaximumSize(new java.awt.Dimension(900, 500));
        jLabel_FondoOperacionesRutinaAdministrador.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_OperacionesRutinaAdministrador.getContentPane().add(jLabel_FondoOperacionesRutinaAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 500));

        jDialog_OperacionesProductoAdministrador.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_OperacionesProductoAdministrador.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel106.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel106.setText("OPERACIONES PRODUCTOS");
        jLabel106.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel106MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel106MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel106MouseExited(evt);
            }
        });
        jDialog_OperacionesProductoAdministrador.getContentPane().add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 900, 50));

        jLabel_AltaProducto.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_AltaProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_AltaProducto.setText("<html>ALTA<br>PRODUCTO</html>");
        jLabel_AltaProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_AltaProductoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_AltaProductoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_AltaProductoMouseExited(evt);
            }
        });
        jDialog_OperacionesProductoAdministrador.getContentPane().add(jLabel_AltaProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 200, 70));

        jButton_AltaProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_AltaProductoMouseClicked(evt);
            }
        });
        jDialog_OperacionesProductoAdministrador.getContentPane().add(jButton_AltaProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 200, 200));

        jLabel_AdministrarProducto.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_AdministrarProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_AdministrarProducto.setText("<html>ADMINISTRAR<br>PRODUCTOS</html>");
        jLabel_AdministrarProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_AdministrarProductoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_AdministrarProductoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_AdministrarProductoMouseExited(evt);
            }
        });
        jDialog_OperacionesProductoAdministrador.getContentPane().add(jLabel_AdministrarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 300, 200, 70));

        jButton_AdministrarProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_AdministrarProductoMouseClicked(evt);
            }
        });
        jDialog_OperacionesProductoAdministrador.getContentPane().add(jButton_AdministrarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, 200, 200));

        jLabel_ConsultaProducto.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_ConsultaProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_ConsultaProducto.setText("<html>CONSULTA<br>VENTAS</html>");
        jLabel_ConsultaProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_ConsultaProductoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_ConsultaProductoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_ConsultaProductoMouseExited(evt);
            }
        });
        jDialog_OperacionesProductoAdministrador.getContentPane().add(jLabel_ConsultaProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 300, 200, 70));

        jButton_ConsultaProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ConsultaProductoMouseClicked(evt);
            }
        });
        jButton_ConsultaProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ConsultaProductoActionPerformed(evt);
            }
        });
        jDialog_OperacionesProductoAdministrador.getContentPane().add(jButton_ConsultaProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 240, 200, 200));

        jLabel_FondoOperacionesProductoAdministrador.setMaximumSize(new java.awt.Dimension(900, 500));
        jLabel_FondoOperacionesProductoAdministrador.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_OperacionesProductoAdministrador.getContentPane().add(jLabel_FondoOperacionesProductoAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 500));

        jDialog_OperacionesSalaAdministrador.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_OperacionesSalaAdministrador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDialog_OperacionesSalaAdministradorMouseClicked(evt);
            }
        });
        jDialog_OperacionesSalaAdministrador.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel107.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel107.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel107.setText("OPERACIONES SALAS");
        jLabel107.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel107MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel107MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel107MouseExited(evt);
            }
        });
        jDialog_OperacionesSalaAdministrador.getContentPane().add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 900, 50));

        jLabel_AltaSala.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_AltaSala.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_AltaSala.setText("<html>ALTA<br>SALA</html>");
        jLabel_AltaSala.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_AltaSalaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_AltaSalaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_AltaSalaMouseExited(evt);
            }
        });
        jDialog_OperacionesSalaAdministrador.getContentPane().add(jLabel_AltaSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 200, 70));

        jButton_AltaSala.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_AltaSalaMouseClicked(evt);
            }
        });
        jDialog_OperacionesSalaAdministrador.getContentPane().add(jButton_AltaSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 200, 200));

        jLabel_AdministrarSala.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_AdministrarSala.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_AdministrarSala.setText("<html>ADMINISTRAR<br>SALAS</html>");
        jLabel_AdministrarSala.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_AdministrarSalaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_AdministrarSalaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_AdministrarSalaMouseExited(evt);
            }
        });
        jDialog_OperacionesSalaAdministrador.getContentPane().add(jLabel_AdministrarSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, 200, 70));

        jButton_AdministrarSala.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_AdministrarSalaMouseClicked(evt);
            }
        });
        jDialog_OperacionesSalaAdministrador.getContentPane().add(jButton_AdministrarSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, 200, 200));

        jLabel_ConsultaSala.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_ConsultaSala.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_ConsultaSala.setText("<html>CONSULTA<br>RESERVAS</html>");
        jLabel_ConsultaSala.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_ConsultaSalaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_ConsultaSalaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_ConsultaSalaMouseExited(evt);
            }
        });
        jDialog_OperacionesSalaAdministrador.getContentPane().add(jLabel_ConsultaSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 300, 200, 70));

        jButton_ConsultaSala.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ConsultaSalaMouseClicked(evt);
            }
        });
        jButton_ConsultaSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ConsultaSalaActionPerformed(evt);
            }
        });
        jDialog_OperacionesSalaAdministrador.getContentPane().add(jButton_ConsultaSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 240, 200, 200));

        jLabel_FondoOperacionesSalaAdministrador.setMaximumSize(new java.awt.Dimension(900, 500));
        jLabel_FondoOperacionesSalaAdministrador.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_OperacionesSalaAdministrador.getContentPane().add(jLabel_FondoOperacionesSalaAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 500));

        jDialog_OperacionesServicioAdministrador.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_OperacionesServicioAdministrador.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel108.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel108.setText("OPERACIONES SERVICIOS");
        jLabel108.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel108MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel108MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel108MouseExited(evt);
            }
        });
        jDialog_OperacionesServicioAdministrador.getContentPane().add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 900, 50));

        jLabel_AltaServicio.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_AltaServicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_AltaServicio.setText("<html>ALTA<br>SERVICIO</html>");
        jLabel_AltaServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_AltaServicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_AltaServicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_AltaServicioMouseExited(evt);
            }
        });
        jDialog_OperacionesServicioAdministrador.getContentPane().add(jLabel_AltaServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 200, 70));

        jButton_AltaServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_AltaServicioMouseClicked(evt);
            }
        });
        jDialog_OperacionesServicioAdministrador.getContentPane().add(jButton_AltaServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 200, 200));

        jLabel_AdministrarServicio.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_AdministrarServicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_AdministrarServicio.setText("<html>ADMINISTRAR<br>SERVICIOS</html>");
        jLabel_AdministrarServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_AdministrarServicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_AdministrarServicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_AdministrarServicioMouseExited(evt);
            }
        });
        jDialog_OperacionesServicioAdministrador.getContentPane().add(jLabel_AdministrarServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, 200, 70));

        jButton_AdministrarServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_AdministrarServicioMouseClicked(evt);
            }
        });
        jDialog_OperacionesServicioAdministrador.getContentPane().add(jButton_AdministrarServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, 200, 200));

        jLabel_ConsultaServicio.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_ConsultaServicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_ConsultaServicio.setText("<html>CONSULTA<br>PAGOS</html>");
        jLabel_ConsultaServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_ConsultaServicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_ConsultaServicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_ConsultaServicioMouseExited(evt);
            }
        });
        jDialog_OperacionesServicioAdministrador.getContentPane().add(jLabel_ConsultaServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 300, 200, 70));

        jButton_ConsultaServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ConsultaServicioMouseClicked(evt);
            }
        });
        jButton_ConsultaServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ConsultaServicioActionPerformed(evt);
            }
        });
        jDialog_OperacionesServicioAdministrador.getContentPane().add(jButton_ConsultaServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 240, 200, 200));

        jLabel_FondoOperacionesServicioAdministrador.setMaximumSize(new java.awt.Dimension(900, 500));
        jLabel_FondoOperacionesServicioAdministrador.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_OperacionesServicioAdministrador.getContentPane().add(jLabel_FondoOperacionesServicioAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 500));

        jDialog_AltaProducto.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel109.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(255, 255, 255));
        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel109.setText("ALTA PRODUCTO");
        jDialog_AltaProducto.getContentPane().add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel110.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel110.setText("Indica la cantidad inicial:");
        jDialog_AltaProducto.getContentPane().add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, 240, -1));
        jDialog_AltaProducto.getContentPane().add(jTextField22, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 330, 30));
        jDialog_AltaProducto.getContentPane().add(jTextField21, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, 260, 30));
        jDialog_AltaProducto.getContentPane().add(jTextField23, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 190, 30));

        jButton58.setBackground(new java.awt.Color(0, 0, 0));
        jButton58.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton58.setForeground(new java.awt.Color(255, 255, 255));
        jButton58.setText("Continuar");
        jButton58.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton58MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton58MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton58MouseExited(evt);
            }
        });
        jButton58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton58ActionPerformed(evt);
            }
        });
        jDialog_AltaProducto.getContentPane().add(jButton58, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, -1, 50));

        jLabel111.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel111.setText("Introduzca el nombre del producto:");
        jDialog_AltaProducto.getContentPane().add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 350, -1));

        jLabel112.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel112.setText("Indica su precio:");
        jDialog_AltaProducto.getContentPane().add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 330, -1));

        jButton62.setBackground(new java.awt.Color(0, 0, 0));
        jButton62.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton62.setForeground(new java.awt.Color(255, 255, 255));
        jButton62.setText("Volver");
        jButton62.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton62MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton62MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton62MouseExited(evt);
            }
        });
        jButton62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton62ActionPerformed(evt);
            }
        });
        jDialog_AltaProducto.getContentPane().add(jButton62, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, -1, 50));
        jDialog_AltaProducto.getContentPane().add(jLabel_FondoAltaProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_AltaServicio.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel113.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(255, 255, 255));
        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel113.setText("ALTA SERVICIO");
        jDialog_AltaServicio.getContentPane().add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel114.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel114.setText("<html>Indica la cantidad de <br>dias de duracion:</html>");
        jDialog_AltaServicio.getContentPane().add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, 220, -1));
        jDialog_AltaServicio.getContentPane().add(jTextField24, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 330, 30));
        jDialog_AltaServicio.getContentPane().add(jTextField25, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, 200, 30));
        jDialog_AltaServicio.getContentPane().add(jTextField26, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 190, 30));

        jButton63.setBackground(new java.awt.Color(0, 0, 0));
        jButton63.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton63.setForeground(new java.awt.Color(255, 255, 255));
        jButton63.setText("Continuar");
        jButton63.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton63MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton63MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton63MouseExited(evt);
            }
        });
        jButton63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton63ActionPerformed(evt);
            }
        });
        jDialog_AltaServicio.getContentPane().add(jButton63, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 390, -1, 50));

        jLabel115.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel115.setText("Introduzca el nombre del servicio:");
        jDialog_AltaServicio.getContentPane().add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 350, -1));

        jLabel116.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel116.setText("Indica su precio:");
        jDialog_AltaServicio.getContentPane().add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 330, -1));

        jButton64.setBackground(new java.awt.Color(0, 0, 0));
        jButton64.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton64.setForeground(new java.awt.Color(255, 255, 255));
        jButton64.setText("Volver");
        jButton64.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton64MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton64MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton64MouseExited(evt);
            }
        });
        jButton64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton64ActionPerformed(evt);
            }
        });
        jDialog_AltaServicio.getContentPane().add(jButton64, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, -1, 50));
        jDialog_AltaServicio.getContentPane().add(jLabel_FondoAltaServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_AltaSala.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel117.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(255, 255, 255));
        jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel117.setText("ALTA SALA");
        jDialog_AltaSala.getContentPane().add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel118.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel118.setText("Selecciona el monitor:");
        jDialog_AltaSala.getContentPane().add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, 220, -1));
        jDialog_AltaSala.getContentPane().add(jTextField27, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 330, 30));
        jDialog_AltaSala.getContentPane().add(jTextField28, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 330, 30));

        jButton65.setBackground(new java.awt.Color(0, 0, 0));
        jButton65.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton65.setForeground(new java.awt.Color(255, 255, 255));
        jButton65.setText("Continuar");
        jButton65.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton65MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton65MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton65MouseExited(evt);
            }
        });
        jButton65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton65ActionPerformed(evt);
            }
        });
        jDialog_AltaSala.getContentPane().add(jButton65, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, -1, 50));

        jLabel119.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel119.setText("Introduzca el nombre de la sala:");
        jDialog_AltaSala.getContentPane().add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 330, -1));

        jLabel120.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel120.setText("Indica la capacidad de personas:");
        jDialog_AltaSala.getContentPane().add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 330, -1));

        jComboBox17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox17.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3 días", "4 días", "5 días" }));
        jDialog_AltaSala.getContentPane().add(jComboBox17, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 150, 190, 30));

        jButton66.setBackground(new java.awt.Color(0, 0, 0));
        jButton66.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton66.setForeground(new java.awt.Color(255, 255, 255));
        jButton66.setText("Volver");
        jButton66.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton66MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton66MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton66MouseExited(evt);
            }
        });
        jButton66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton66ActionPerformed(evt);
            }
        });
        jDialog_AltaSala.getContentPane().add(jButton66, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, -1, 50));
        jDialog_AltaSala.getContentPane().add(jLabel_FondoAltaSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_ModificarProducto.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel121.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(255, 255, 255));
        jLabel121.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel121.setText("MODIFICAR PRODUCTO");
        jDialog_ModificarProducto.getContentPane().add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel122.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel122.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel122.setText("Indica la cantidad inicial:");
        jDialog_ModificarProducto.getContentPane().add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, 240, -1));
        jDialog_ModificarProducto.getContentPane().add(jTextField29, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 330, 30));
        jDialog_ModificarProducto.getContentPane().add(jTextField30, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, 260, 30));
        jDialog_ModificarProducto.getContentPane().add(jTextField31, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 190, 30));

        jButton67.setBackground(new java.awt.Color(0, 0, 0));
        jButton67.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton67.setForeground(new java.awt.Color(255, 255, 255));
        jButton67.setText("Continuar");
        jButton67.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton67MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton67MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton67MouseExited(evt);
            }
        });
        jButton67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton67ActionPerformed(evt);
            }
        });
        jDialog_ModificarProducto.getContentPane().add(jButton67, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, -1, 50));

        jLabel123.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel123.setText("Introduzca el nombre del producto:");
        jDialog_ModificarProducto.getContentPane().add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 350, -1));

        jLabel124.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel124.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel124.setText("Indica su precio:");
        jDialog_ModificarProducto.getContentPane().add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 330, -1));

        jButton68.setBackground(new java.awt.Color(0, 0, 0));
        jButton68.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton68.setForeground(new java.awt.Color(255, 255, 255));
        jButton68.setText("Volver");
        jButton68.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton68MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton68MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton68MouseExited(evt);
            }
        });
        jButton68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton68ActionPerformed(evt);
            }
        });
        jDialog_ModificarProducto.getContentPane().add(jButton68, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, -1, 50));
        jDialog_ModificarProducto.getContentPane().add(jLabel_FondoModificarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_ConsultaProducto.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jDialog_ConsultaProducto.getContentPane().add(jTextField32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 230, 30));

        jLabel125.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel125.setForeground(new java.awt.Color(255, 255, 255));
        jLabel125.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel125.setText("CONSULTA PRODUCTO");
        jDialog_ConsultaProducto.getContentPane().add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel126.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel126.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel126.setText("<html>Selecciona el orden de busqueda <br>segun la cantidad disponible:</html>");
        jDialog_ConsultaProducto.getContentPane().add(jLabel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 230, -1));

        jLabel127.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel127.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel127.setText("<html>Introduzca el nombre del producto a buscar:</html>");
        jDialog_ConsultaProducto.getContentPane().add(jLabel127, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 230, -1));

        jLabel128.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel128.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel128.setText("<html>Selecciona el orden de busqueda segun el precio:</html>");
        jDialog_ConsultaProducto.getContentPane().add(jLabel128, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 210, -1));

        jLabel_ReiniciarBusquedaProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_ReiniciarBusquedaProductoMouseClicked(evt);
            }
        });
        jDialog_ConsultaProducto.getContentPane().add(jLabel_ReiniciarBusquedaProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 120, 50, 50));

        jLabel_BuscarProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_BuscarProductoMouseClicked(evt);
            }
        });
        jDialog_ConsultaProducto.getContentPane().add(jLabel_BuscarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 120, 50, 50));

        jComboBox18.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Mayor cantidad", "Menor cantidad" }));
        jDialog_ConsultaProducto.getContentPane().add(jComboBox18, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 200, 30));

        jComboBox19.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Mas caro", "Mas barato" }));
        jDialog_ConsultaProducto.getContentPane().add(jComboBox19, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 200, 30));

        jButton69.setBackground(new java.awt.Color(0, 0, 0));
        jButton69.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton69.setForeground(new java.awt.Color(255, 255, 255));
        jButton69.setText("Volver");
        jButton69.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton69MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton69MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton69MouseExited(evt);
            }
        });
        jButton69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton69ActionPerformed(evt);
            }
        });
        jDialog_ConsultaProducto.getContentPane().add(jButton69, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, -1, 50));

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Objetivo", "Autor", "Visualizar alimentos", "Modificar dieta", "Eliminar dieta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(jTable7);

        jDialog_ConsultaProducto.getContentPane().add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 870, 170));
        jDialog_ConsultaProducto.getContentPane().add(jLabel_FondoConsultaProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_ModificarServicio.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel129.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel129.setForeground(new java.awt.Color(255, 255, 255));
        jLabel129.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel129.setText("MODIFICAR SERVICIO");
        jDialog_ModificarServicio.getContentPane().add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel130.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel130.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel130.setText("<html>Indica la cantidad de <br>dias de duracion:</html>");
        jDialog_ModificarServicio.getContentPane().add(jLabel130, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, 220, -1));
        jDialog_ModificarServicio.getContentPane().add(jTextField33, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 330, 30));
        jDialog_ModificarServicio.getContentPane().add(jTextField34, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, 200, 30));
        jDialog_ModificarServicio.getContentPane().add(jTextField35, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 190, 30));

        jButton70.setBackground(new java.awt.Color(0, 0, 0));
        jButton70.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton70.setForeground(new java.awt.Color(255, 255, 255));
        jButton70.setText("Continuar");
        jButton70.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton70MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton70MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton70MouseExited(evt);
            }
        });
        jButton70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton70ActionPerformed(evt);
            }
        });
        jDialog_ModificarServicio.getContentPane().add(jButton70, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 390, -1, 50));

        jLabel131.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel131.setText("Introduzca el nombre del servicio:");
        jDialog_ModificarServicio.getContentPane().add(jLabel131, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 350, -1));

        jLabel132.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel132.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel132.setText("Indica su precio:");
        jDialog_ModificarServicio.getContentPane().add(jLabel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 330, -1));

        jButton71.setBackground(new java.awt.Color(0, 0, 0));
        jButton71.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton71.setForeground(new java.awt.Color(255, 255, 255));
        jButton71.setText("Volver");
        jButton71.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton71MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton71MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton71MouseExited(evt);
            }
        });
        jButton71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton71ActionPerformed(evt);
            }
        });
        jDialog_ModificarServicio.getContentPane().add(jButton71, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, -1, 50));
        jDialog_ModificarServicio.getContentPane().add(jLabel_FondoModificarServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_ConsultaServicio.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jDialog_ConsultaServicio.getContentPane().add(jTextField36, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 230, 30));

        jLabel133.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel133.setForeground(new java.awt.Color(255, 255, 255));
        jLabel133.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel133.setText("CONSULTA SERVICIO");
        jDialog_ConsultaServicio.getContentPane().add(jLabel133, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel134.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel134.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel134.setText("<html>Selecciona el orden de busqueda <br>segun la cantidad dias:</html>");
        jDialog_ConsultaServicio.getContentPane().add(jLabel134, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 230, -1));

        jLabel135.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel135.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel135.setText("<html>Introduzca el nombre del servicio a buscar:</html>");
        jDialog_ConsultaServicio.getContentPane().add(jLabel135, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 230, -1));

        jLabel136.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel136.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel136.setText("<html>Selecciona el orden de busqueda segun el precio:</html>");
        jDialog_ConsultaServicio.getContentPane().add(jLabel136, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 210, -1));

        jLabel_ReiniciarBusquedaServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_ReiniciarBusquedaServicioMouseClicked(evt);
            }
        });
        jDialog_ConsultaServicio.getContentPane().add(jLabel_ReiniciarBusquedaServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 120, 50, 50));

        jLabel_BuscarServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_BuscarServicioMouseClicked(evt);
            }
        });
        jDialog_ConsultaServicio.getContentPane().add(jLabel_BuscarServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 120, 50, 50));

        jComboBox20.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Mayor dias", "Menor dias" }));
        jDialog_ConsultaServicio.getContentPane().add(jComboBox20, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 200, 30));

        jComboBox21.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Mas caro", "Mas barato" }));
        jDialog_ConsultaServicio.getContentPane().add(jComboBox21, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 200, 30));

        jButton72.setBackground(new java.awt.Color(0, 0, 0));
        jButton72.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton72.setForeground(new java.awt.Color(255, 255, 255));
        jButton72.setText("Volver");
        jButton72.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton72MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton72MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton72MouseExited(evt);
            }
        });
        jButton72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton72ActionPerformed(evt);
            }
        });
        jDialog_ConsultaServicio.getContentPane().add(jButton72, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, -1, 50));

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Objetivo", "Autor", "Visualizar alimentos", "Modificar dieta", "Eliminar dieta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(jTable8);

        jDialog_ConsultaServicio.getContentPane().add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 870, 170));
        jDialog_ConsultaServicio.getContentPane().add(jLabel_FondoConsultaServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_ModificarSala.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel137.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel137.setForeground(new java.awt.Color(255, 255, 255));
        jLabel137.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel137.setText("MODIFICAR SALA");
        jDialog_ModificarSala.getContentPane().add(jLabel137, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel138.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel138.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel138.setText("Selecciona el monitor:");
        jDialog_ModificarSala.getContentPane().add(jLabel138, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, 220, -1));
        jDialog_ModificarSala.getContentPane().add(jTextField37, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 330, 30));
        jDialog_ModificarSala.getContentPane().add(jTextField38, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 330, 30));

        jButton73.setBackground(new java.awt.Color(0, 0, 0));
        jButton73.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton73.setForeground(new java.awt.Color(255, 255, 255));
        jButton73.setText("Continuar");
        jButton73.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton73MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton73MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton73MouseExited(evt);
            }
        });
        jButton73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton73ActionPerformed(evt);
            }
        });
        jDialog_ModificarSala.getContentPane().add(jButton73, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, -1, 50));

        jLabel139.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel139.setText("Introduzca el nombre de la sala:");
        jDialog_ModificarSala.getContentPane().add(jLabel139, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 330, -1));

        jLabel140.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel140.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel140.setText("Indica la capacidad de personas:");
        jDialog_ModificarSala.getContentPane().add(jLabel140, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 330, -1));

        jComboBox22.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox22.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3 días", "4 días", "5 días" }));
        jDialog_ModificarSala.getContentPane().add(jComboBox22, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 150, 190, 30));

        jButton74.setBackground(new java.awt.Color(0, 0, 0));
        jButton74.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton74.setForeground(new java.awt.Color(255, 255, 255));
        jButton74.setText("Volver");
        jButton74.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton74MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton74MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton74MouseExited(evt);
            }
        });
        jButton74.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton74ActionPerformed(evt);
            }
        });
        jDialog_ModificarSala.getContentPane().add(jButton74, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, -1, 50));
        jDialog_ModificarSala.getContentPane().add(jLabel_FondoModificarSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_ConsultaSala.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jDialog_ConsultaSala.getContentPane().add(jTextField39, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 230, 30));

        jLabel141.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel141.setForeground(new java.awt.Color(255, 255, 255));
        jLabel141.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel141.setText("CONSULTA SALA");
        jDialog_ConsultaSala.getContentPane().add(jLabel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel142.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel142.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel142.setText("<html>Selecciona la busqueda <br>segun el monitor:</html>");
        jDialog_ConsultaSala.getContentPane().add(jLabel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 170, -1));

        jLabel143.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel143.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel143.setText("<html>Introduzca el nombre del sala a buscar:</html>");
        jDialog_ConsultaSala.getContentPane().add(jLabel143, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 230, -1));

        jLabel144.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel144.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel144.setText("<html>Selecciona el orden de busqueda segun la capacidad de personas:</html>");
        jDialog_ConsultaSala.getContentPane().add(jLabel144, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 230, -1));

        jLabel_ReiniciarBusquedaSala.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_ReiniciarBusquedaSalaMouseClicked(evt);
            }
        });
        jDialog_ConsultaSala.getContentPane().add(jLabel_ReiniciarBusquedaSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 120, 50, 50));

        jLabel_BuscarSala.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_BuscarSalaMouseClicked(evt);
            }
        });
        jDialog_ConsultaSala.getContentPane().add(jLabel_BuscarSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 120, 50, 50));

        jComboBox23.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos" }));
        jDialog_ConsultaSala.getContentPane().add(jComboBox23, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 200, 30));

        jComboBox24.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Mayor capacidad", "Menor capacidad" }));
        jDialog_ConsultaSala.getContentPane().add(jComboBox24, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 200, 30));

        jButton75.setBackground(new java.awt.Color(0, 0, 0));
        jButton75.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton75.setForeground(new java.awt.Color(255, 255, 255));
        jButton75.setText("Volver");
        jButton75.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton75MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton75MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton75MouseExited(evt);
            }
        });
        jButton75.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton75ActionPerformed(evt);
            }
        });
        jDialog_ConsultaSala.getContentPane().add(jButton75, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, -1, 50));

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Objetivo", "Autor", "Visualizar alimentos", "Modificar dieta", "Eliminar dieta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane11.setViewportView(jTable9);

        jDialog_ConsultaSala.getContentPane().add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 870, 170));
        jDialog_ConsultaSala.getContentPane().add(jLabel_FondoConsultaSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_ConsultaVenta.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jDialog_ConsultaVenta.getContentPane().add(jTextField40, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 230, 30));

        jLabel145.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel145.setForeground(new java.awt.Color(255, 255, 255));
        jLabel145.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel145.setText("CONSULTA VENTAS");
        jDialog_ConsultaVenta.getContentPane().add(jLabel145, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel146.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel146.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel146.setText("<html>Selecciona el orden de <br>busqueda segun el cliente:</html>");
        jDialog_ConsultaVenta.getContentPane().add(jLabel146, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 190, -1));

        jLabel147.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel147.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel147.setText("<html>Introduzca el nombre del producto a buscar:</html>");
        jDialog_ConsultaVenta.getContentPane().add(jLabel147, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 230, -1));

        jLabel148.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel148.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel148.setText("<html>Selecciona el orden de busqueda segun la fecha:</html>");
        jDialog_ConsultaVenta.getContentPane().add(jLabel148, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 210, -1));

        jLabel_ReiniciarBusquedaVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_ReiniciarBusquedaVentaMouseClicked(evt);
            }
        });
        jDialog_ConsultaVenta.getContentPane().add(jLabel_ReiniciarBusquedaVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 120, 50, 50));

        jLabel_BuscarVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_BuscarVentaMouseClicked(evt);
            }
        });
        jDialog_ConsultaVenta.getContentPane().add(jLabel_BuscarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 120, 50, 50));

        jComboBox25.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos" }));
        jComboBox25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox25ActionPerformed(evt);
            }
        });
        jDialog_ConsultaVenta.getContentPane().add(jComboBox25, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 200, 30));

        jComboBox26.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Mas recientes", "Mas antigüas" }));
        jDialog_ConsultaVenta.getContentPane().add(jComboBox26, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 200, 30));

        jButton76.setBackground(new java.awt.Color(0, 0, 0));
        jButton76.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton76.setForeground(new java.awt.Color(255, 255, 255));
        jButton76.setText("Volver");
        jButton76.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton76MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton76MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton76MouseExited(evt);
            }
        });
        jButton76.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton76ActionPerformed(evt);
            }
        });
        jDialog_ConsultaVenta.getContentPane().add(jButton76, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, -1, 50));

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Objetivo", "Autor", "Visualizar alimentos", "Modificar dieta", "Eliminar dieta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane12.setViewportView(jTable10);

        jDialog_ConsultaVenta.getContentPane().add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 870, 170));
        jDialog_ConsultaVenta.getContentPane().add(jLabel_FondoConsultaVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_ConsultaReserva.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel149.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel149.setForeground(new java.awt.Color(255, 255, 255));
        jLabel149.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel149.setText("CONSULTA RESERVAS");
        jDialog_ConsultaReserva.getContentPane().add(jLabel149, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel150.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel150.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel150.setText("<html>Selecciona el orden de <br>busqueda segun el cliente:</html>");
        jDialog_ConsultaReserva.getContentPane().add(jLabel150, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 190, -1));

        jLabel151.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel151.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel151.setText("<html>Selecciona la sala a buscar:</html>");
        jDialog_ConsultaReserva.getContentPane().add(jLabel151, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 100, -1));

        jLabel152.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel152.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel152.setText("<html>Selecciona el turno <br>de horario:</html>");
        jDialog_ConsultaReserva.getContentPane().add(jLabel152, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 130, -1));

        jLabel_ReiniciarBusquedaReserva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_ReiniciarBusquedaReservaMouseClicked(evt);
            }
        });
        jDialog_ConsultaReserva.getContentPane().add(jLabel_ReiniciarBusquedaReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 120, 50, 50));

        jLabel_BuscarReserva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_BuscarReservaMouseClicked(evt);
            }
        });
        jDialog_ConsultaReserva.getContentPane().add(jLabel_BuscarReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 120, 50, 50));

        jComboBox27.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos" }));
        jComboBox27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox27ActionPerformed(evt);
            }
        });
        jDialog_ConsultaReserva.getContentPane().add(jComboBox27, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 200, 30));

        jComboBox28.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos" }));
        jComboBox28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox28ActionPerformed(evt);
            }
        });
        jDialog_ConsultaReserva.getContentPane().add(jComboBox28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 140, 30));

        jComboBox29.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Mañana", "Tarde" }));
        jDialog_ConsultaReserva.getContentPane().add(jComboBox29, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 130, 30));

        jComboBox30.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Mas recientes", "Mas antigüas" }));
        jDialog_ConsultaReserva.getContentPane().add(jComboBox30, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 170, 30));

        jButton77.setBackground(new java.awt.Color(0, 0, 0));
        jButton77.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton77.setForeground(new java.awt.Color(255, 255, 255));
        jButton77.setText("Volver");
        jButton77.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton77MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton77MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton77MouseExited(evt);
            }
        });
        jButton77.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton77ActionPerformed(evt);
            }
        });
        jDialog_ConsultaReserva.getContentPane().add(jButton77, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, -1, 50));

        jLabel153.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel153.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel153.setText("<html>Selecciona el orden de busqueda segun la fecha:</html>");
        jDialog_ConsultaReserva.getContentPane().add(jLabel153, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 170, -1));

        jTable11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Objetivo", "Autor", "Visualizar alimentos", "Modificar dieta", "Eliminar dieta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane13.setViewportView(jTable11);

        jDialog_ConsultaReserva.getContentPane().add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 870, 170));
        jDialog_ConsultaReserva.getContentPane().add(jLabel_FondoConsultaReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_ConsultaPagosServicios.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel154.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel154.setForeground(new java.awt.Color(255, 255, 255));
        jLabel154.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel154.setText("CONSULTA PAGOS SERVICIOS");
        jDialog_ConsultaPagosServicios.getContentPane().add(jLabel154, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel155.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel155.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel155.setText("<html>Selecciona el orden de <br>busqueda segun el cliente:</html>");
        jDialog_ConsultaPagosServicios.getContentPane().add(jLabel155, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 190, -1));

        jLabel156.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel156.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel156.setText("<html>Selecciona el servicio a buscar:</html>");
        jDialog_ConsultaPagosServicios.getContentPane().add(jLabel156, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 140, -1));

        jLabel_ReiniciarBusquedaPagosServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_ReiniciarBusquedaPagosServicioMouseClicked(evt);
            }
        });
        jDialog_ConsultaPagosServicios.getContentPane().add(jLabel_ReiniciarBusquedaPagosServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 120, 50, 50));

        jLabel_BuscarPagosServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_BuscarPagosServicioMouseClicked(evt);
            }
        });
        jDialog_ConsultaPagosServicios.getContentPane().add(jLabel_BuscarPagosServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 120, 50, 50));

        jComboBox31.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos" }));
        jComboBox31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox31ActionPerformed(evt);
            }
        });
        jDialog_ConsultaPagosServicios.getContentPane().add(jComboBox31, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 200, 30));

        jComboBox32.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos" }));
        jComboBox32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox32ActionPerformed(evt);
            }
        });
        jDialog_ConsultaPagosServicios.getContentPane().add(jComboBox32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 140, 30));

        jComboBox34.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Mas recientes", "Mas antigüas" }));
        jDialog_ConsultaPagosServicios.getContentPane().add(jComboBox34, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 170, 30));

        jButton78.setBackground(new java.awt.Color(0, 0, 0));
        jButton78.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton78.setForeground(new java.awt.Color(255, 255, 255));
        jButton78.setText("Volver");
        jButton78.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton78MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton78MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton78MouseExited(evt);
            }
        });
        jButton78.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton78ActionPerformed(evt);
            }
        });
        jDialog_ConsultaPagosServicios.getContentPane().add(jButton78, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, -1, 50));

        jLabel158.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel158.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel158.setText("<html>Selecciona el orden de busqueda segun la fecha:</html>");
        jDialog_ConsultaPagosServicios.getContentPane().add(jLabel158, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 170, -1));

        jTable12.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Objetivo", "Autor", "Visualizar alimentos", "Modificar dieta", "Eliminar dieta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane14.setViewportView(jTable12);

        jDialog_ConsultaPagosServicios.getContentPane().add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 870, 170));
        jDialog_ConsultaPagosServicios.getContentPane().add(jLabel_FondoConsultaPagosServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_FuncionalidadCliente.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_FuncionalidadCliente.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel157.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel157.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel157.setText("GYMZONE");
        jLabel157.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel157MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel157MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel157MouseExited(evt);
            }
        });
        jDialog_FuncionalidadCliente.getContentPane().add(jLabel157, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 230, 50));

        jLabel_FCC_Cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FCC_ClienteMouseClicked(evt);
            }
        });
        jDialog_FuncionalidadCliente.getContentPane().add(jLabel_FCC_Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 80, 100, 100));

        jLabel_FG_Cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_FG_ClienteMouseClicked(evt);
            }
        });
        jDialog_FuncionalidadCliente.getContentPane().add(jLabel_FG_Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 100, 100));

        jLabel_NU_Cliente.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_NU_Cliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_NU_Cliente.setText("USUARIO");
        jLabel_NU_Cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_NU_ClienteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_NU_ClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_NU_ClienteMouseExited(evt);
            }
        });
        jDialog_FuncionalidadCliente.getContentPane().add(jLabel_NU_Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 270, 30));

        jLabel159.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel159.setForeground(new java.awt.Color(255, 255, 255));
        jLabel159.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel159.setText("<html>CONSULTA<br>RUTINA</html>");
        jLabel159.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel159MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel159MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel159MouseExited(evt);
            }
        });
        jDialog_FuncionalidadCliente.getContentPane().add(jLabel159, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 200, 70));

        jLabel160.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel160.setForeground(new java.awt.Color(255, 255, 255));
        jLabel160.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel160.setText("<html>GESTIÓN<br>RESERVAS</html>");
        jLabel160.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel160MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel160MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel160MouseExited(evt);
            }
        });
        jDialog_FuncionalidadCliente.getContentPane().add(jLabel160, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 200, 70));

        jLabel161.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel161.setForeground(new java.awt.Color(255, 255, 255));
        jLabel161.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel161.setText("<html>PAGOS PRODUCTOS <br>Y SERVICIOS</html>");
        jLabel161.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel161MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel161MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel161MouseExited(evt);
            }
        });
        jDialog_FuncionalidadCliente.getContentPane().add(jLabel161, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 200, 90));

        jLabel162.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel162.setForeground(new java.awt.Color(255, 255, 255));
        jLabel162.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel162.setText("<html>CONSULTA<br>DIETA</html>");
        jLabel162.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel162MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel162MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel162MouseExited(evt);
            }
        });
        jDialog_FuncionalidadCliente.getContentPane().add(jLabel162, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 300, 200, 70));

        jButton79.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton79MouseClicked(evt);
            }
        });
        jDialog_FuncionalidadCliente.getContentPane().add(jButton79, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 200, 200));

        jButton80.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton80MouseClicked(evt);
            }
        });
        jDialog_FuncionalidadCliente.getContentPane().add(jButton80, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, 200, 200));

        jButton81.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton81MouseClicked(evt);
            }
        });
        jDialog_FuncionalidadCliente.getContentPane().add(jButton81, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 200, 200));

        jButton82.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton82MouseClicked(evt);
            }
        });
        jDialog_FuncionalidadCliente.getContentPane().add(jButton82, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 240, 200, 200));

        jLabel_FondoFuncionalidadesCliente.setMaximumSize(new java.awt.Dimension(900, 500));
        jLabel_FondoFuncionalidadesCliente.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_FuncionalidadCliente.getContentPane().add(jLabel_FondoFuncionalidadesCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 500));

        jDialog_OperacionesPagosCliente.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_OperacionesPagosCliente.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel163.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel163.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel163.setText("PAGOS PRODUCTOS Y SERVICIOS");
        jLabel163.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel163MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel163MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel163MouseExited(evt);
            }
        });
        jDialog_OperacionesPagosCliente.getContentPane().add(jLabel163, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 900, 50));

        jLabel_PagarProducto.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_PagarProducto.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_PagarProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_PagarProducto.setText("<html>PAGAR<br>PRODUCTOS</html>");
        jLabel_PagarProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_PagarProductoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_PagarProductoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_PagarProductoMouseExited(evt);
            }
        });
        jDialog_OperacionesPagosCliente.getContentPane().add(jLabel_PagarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 200, 70));

        jButton_PagarProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_PagarProductoMouseClicked(evt);
            }
        });
        jDialog_OperacionesPagosCliente.getContentPane().add(jButton_PagarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 200, 200));

        jLabel_PagarServicio.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_PagarServicio.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_PagarServicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_PagarServicio.setText("<html>PAGAR<br>SERVICIOS</html>");
        jLabel_PagarServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_PagarServicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_PagarServicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_PagarServicioMouseExited(evt);
            }
        });
        jDialog_OperacionesPagosCliente.getContentPane().add(jLabel_PagarServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, 200, 70));

        jButton_PagarServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_PagarServicioMouseClicked(evt);
            }
        });
        jDialog_OperacionesPagosCliente.getContentPane().add(jButton_PagarServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, 200, 200));

        jLabel_FondoOperacionesPagosCliente.setMaximumSize(new java.awt.Dimension(900, 500));
        jLabel_FondoOperacionesPagosCliente.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_OperacionesPagosCliente.getContentPane().add(jLabel_FondoOperacionesPagosCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 500));

        jDialog_PagoProducto.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jDialog_PagoProducto.getContentPane().add(jTextField41, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 230, 30));

        jLabel164.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel164.setForeground(new java.awt.Color(255, 255, 255));
        jLabel164.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel164.setText("PAGOS PRODUCTOS");
        jDialog_PagoProducto.getContentPane().add(jLabel164, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel166.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel166.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel166.setText("<html>Introduzca el nombre del producto a buscar:</html>");
        jDialog_PagoProducto.getContentPane().add(jLabel166, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 230, -1));

        jLabel167.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel167.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel167.setText("<html>Selecciona el orden de busqueda segun el precio:</html>");
        jDialog_PagoProducto.getContentPane().add(jLabel167, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 210, -1));

        jLabel_ReiniciarBusquedaPagarProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_ReiniciarBusquedaPagarProductoMouseClicked(evt);
            }
        });
        jDialog_PagoProducto.getContentPane().add(jLabel_ReiniciarBusquedaPagarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 120, 50, 50));

        jLabel_BuscarPagarProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_BuscarPagarProductoMouseClicked(evt);
            }
        });
        jDialog_PagoProducto.getContentPane().add(jLabel_BuscarPagarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 120, 50, 50));

        jComboBox35.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Mas caro", "Mas barato" }));
        jDialog_PagoProducto.getContentPane().add(jComboBox35, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, 200, 30));

        jButton83.setBackground(new java.awt.Color(0, 0, 0));
        jButton83.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton83.setForeground(new java.awt.Color(255, 255, 255));
        jButton83.setText("Volver");
        jButton83.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton83MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton83MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton83MouseExited(evt);
            }
        });
        jButton83.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton83ActionPerformed(evt);
            }
        });
        jDialog_PagoProducto.getContentPane().add(jButton83, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, -1, 50));

        jTable13.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Precio por Unidad", "Cantidad Disponible", "Cantidad A Comprar", "Pagar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane15.setViewportView(jTable13);

        jDialog_PagoProducto.getContentPane().add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 870, 170));
        jDialog_PagoProducto.getContentPane().add(jLabel_FondoConsultaPagarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_PagoServicio.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jDialog_PagoServicio.getContentPane().add(jTextField42, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 230, 30));

        jLabel165.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel165.setForeground(new java.awt.Color(255, 255, 255));
        jLabel165.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel165.setText("PAGOS SERVICIOS");
        jDialog_PagoServicio.getContentPane().add(jLabel165, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel169.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel169.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel169.setText("<html>Introduzca el nombre del servicio a buscar:</html>");
        jDialog_PagoServicio.getContentPane().add(jLabel169, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 230, -1));

        jLabel170.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel170.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel170.setText("<html>Selecciona el orden de busqueda segun el precio:</html>");
        jDialog_PagoServicio.getContentPane().add(jLabel170, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, 210, -1));

        jLabel_ReiniciarBusquedaPagarServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_ReiniciarBusquedaPagarServicioMouseClicked(evt);
            }
        });
        jDialog_PagoServicio.getContentPane().add(jLabel_ReiniciarBusquedaPagarServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 120, 50, 50));

        jLabel_BuscarPagarServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_BuscarPagarServicioMouseClicked(evt);
            }
        });
        jDialog_PagoServicio.getContentPane().add(jLabel_BuscarPagarServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 120, 50, 50));

        jComboBox36.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Mas caro", "Mas barato" }));
        jDialog_PagoServicio.getContentPane().add(jComboBox36, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 200, 30));

        jButton84.setBackground(new java.awt.Color(0, 0, 0));
        jButton84.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton84.setForeground(new java.awt.Color(255, 255, 255));
        jButton84.setText("Volver");
        jButton84.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton84MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton84MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton84MouseExited(evt);
            }
        });
        jButton84.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton84ActionPerformed(evt);
            }
        });
        jDialog_PagoServicio.getContentPane().add(jButton84, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, -1, 50));

        jTable14.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Objetivo", "Autor", "Visualizar alimentos", "Modificar dieta", "Eliminar dieta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane16.setViewportView(jTable14);

        jDialog_PagoServicio.getContentPane().add(jScrollPane16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 870, 170));
        jDialog_PagoServicio.getContentPane().add(jLabel_FondoConsultaPagarServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_OperacionesReservasCliente.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_OperacionesReservasCliente.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel168.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel168.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel168.setText("RESERVAS");
        jLabel168.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel168MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel168MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel168MouseExited(evt);
            }
        });
        jDialog_OperacionesReservasCliente.getContentPane().add(jLabel168, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 900, 50));

        jLabel_AltaReserva.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_AltaReserva.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_AltaReserva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_AltaReserva.setText("<html>ALTA<br>RESERVA</html>");
        jLabel_AltaReserva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_AltaReservaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_AltaReservaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_AltaReservaMouseExited(evt);
            }
        });
        jDialog_OperacionesReservasCliente.getContentPane().add(jLabel_AltaReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 200, 70));

        jButton_AltaReserva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_AltaReservaMouseClicked(evt);
            }
        });
        jButton_AltaReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AltaReservaActionPerformed(evt);
            }
        });
        jDialog_OperacionesReservasCliente.getContentPane().add(jButton_AltaReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 200, 200));

        jLabel_CancelarReserva.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_CancelarReserva.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_CancelarReserva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_CancelarReserva.setText("<html>GESTION<br>RESERVAS</html>");
        jLabel_CancelarReserva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_CancelarReservaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_CancelarReservaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_CancelarReservaMouseExited(evt);
            }
        });
        jDialog_OperacionesReservasCliente.getContentPane().add(jLabel_CancelarReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 230, 200, 70));

        jButton_CancelarReserva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_CancelarReservaMouseClicked(evt);
            }
        });
        jDialog_OperacionesReservasCliente.getContentPane().add(jButton_CancelarReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, 200, 200));

        jLabel_FondoOperacionesReservasCliente.setMaximumSize(new java.awt.Dimension(900, 500));
        jLabel_FondoOperacionesReservasCliente.setMinimumSize(new java.awt.Dimension(900, 500));
        jDialog_OperacionesReservasCliente.getContentPane().add(jLabel_FondoOperacionesReservasCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 500));

        jDialog_AltaReserva.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel171.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel171.setForeground(new java.awt.Color(255, 255, 255));
        jLabel171.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel171.setText("ALTA RESERVA");
        jDialog_AltaReserva.getContentPane().add(jLabel171, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jLabel_AltaReservaDia.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_AltaReservaDia.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_AltaReservaDia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_AltaReservaDia.setText("<html>TURNO<br>MAÑANA</html>");
        jLabel_AltaReservaDia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_AltaReservaDiaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_AltaReservaDiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_AltaReservaDiaMouseExited(evt);
            }
        });
        jDialog_AltaReserva.getContentPane().add(jLabel_AltaReservaDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 200, 70));

        jLabel_AltaReservaTarde.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel_AltaReservaTarde.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_AltaReservaTarde.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_AltaReservaTarde.setText("<html>TURNO<br>TARDE</html>");
        jLabel_AltaReservaTarde.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_AltaReservaTardeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_AltaReservaTardeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_AltaReservaTardeMouseExited(evt);
            }
        });
        jDialog_AltaReserva.getContentPane().add(jLabel_AltaReservaTarde, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 200, 200, 70));

        jButton_AltaReservaTarde.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_AltaReservaTardeMouseClicked(evt);
            }
        });
        jDialog_AltaReserva.getContentPane().add(jButton_AltaReservaTarde, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 140, 200, 200));

        jButton_AltaReservaDia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_AltaReservaDiaMouseClicked(evt);
            }
        });
        jDialog_AltaReserva.getContentPane().add(jButton_AltaReservaDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 200, 200));

        jLabel172.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel172.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel172.setText("Selecciona la franja horaria:");
        jDialog_AltaReserva.getContentPane().add(jLabel172, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 270, -1));

        jButton85.setBackground(new java.awt.Color(0, 0, 0));
        jButton85.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton85.setForeground(new java.awt.Color(255, 255, 255));
        jButton85.setText("Continuar");
        jButton85.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton85MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton85MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton85MouseExited(evt);
            }
        });
        jButton85.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton85ActionPerformed(evt);
            }
        });
        jDialog_AltaReserva.getContentPane().add(jButton85, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 400, -1, 50));

        jComboBox33.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox33.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3 días", "4 días", "5 días" }));
        jDialog_AltaReserva.getContentPane().add(jComboBox33, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 260, 30));

        jButton86.setBackground(new java.awt.Color(0, 0, 0));
        jButton86.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton86.setForeground(new java.awt.Color(255, 255, 255));
        jButton86.setText("Volver");
        jButton86.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton86MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton86MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton86MouseExited(evt);
            }
        });
        jButton86.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton86ActionPerformed(evt);
            }
        });
        jDialog_AltaReserva.getContentPane().add(jButton86, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 400, -1, 50));

        jLabel173.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel173.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel173.setText("Selecciona la sala:");
        jDialog_AltaReserva.getContentPane().add(jLabel173, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 270, -1));

        jComboBox37.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox37.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3 días", "4 días", "5 días" }));
        jDialog_AltaReserva.getContentPane().add(jComboBox37, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 250, 30));
        jDialog_AltaReserva.getContentPane().add(jLabel_FondoAltaReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        jDialog_GestionReserva.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel174.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel174.setForeground(new java.awt.Color(255, 255, 255));
        jLabel174.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel174.setText("GESTION RESERVAS");
        jDialog_GestionReserva.getContentPane().add(jLabel174, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 50));

        jButton87.setBackground(new java.awt.Color(0, 0, 0));
        jButton87.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton87.setForeground(new java.awt.Color(255, 255, 255));
        jButton87.setText("Volver");
        jButton87.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton87MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton87MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton87MouseExited(evt);
            }
        });
        jButton87.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton87ActionPerformed(evt);
            }
        });
        jDialog_GestionReserva.getContentPane().add(jButton87, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 390, -1, 50));

        jTable15.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Objetivo", "Autor", "Visualizar alimentos", "Modificar dieta", "Eliminar dieta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane17.setViewportView(jTable15);

        jDialog_GestionReserva.getContentPane().add(jScrollPane17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 870, 250));
        jDialog_GestionReserva.getContentPane().add(jLabel_FondoCancelarReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -20, 900, 500));

        jDialog_PerfilCliente.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jDialog_PerfilCliente.getContentPane().add(jTextField43, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 350, 30));

        jComboBox38.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox38.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mantenimiento", "Subida peso", "Bajada peso", "Recomposicion Corporal" }));
        jDialog_PerfilCliente.getContentPane().add(jComboBox38, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 310, -1));
        jDialog_PerfilCliente.getContentPane().add(jLabel_PC_FotoPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 350, 350));

        jLabel_PC_NombreApellidos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_PC_NombreApellidos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_PC_NombreApellidos.setText("jLabel58");
        jDialog_PerfilCliente.getContentPane().add(jLabel_PC_NombreApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 330, -1));

        jLabel_PC_Edad.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_PC_Edad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_PC_Edad.setText("jLabel58");
        jDialog_PerfilCliente.getContentPane().add(jLabel_PC_Edad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 330, -1));

        jLabel175.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel175.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel175.setText("Peso:");
        jDialog_PerfilCliente.getContentPane().add(jLabel175, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, 360, 30));

        jLabel_PC_Objetivo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_PC_Objetivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_PC_Objetivo.setText("Objetivo");
        jDialog_PerfilCliente.getContentPane().add(jLabel_PC_Objetivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 370, 30));

        jLabel176.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel176.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel176.setText("Altura:");
        jDialog_PerfilCliente.getContentPane().add(jLabel176, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 360, 30));

        jButton_PC_Editar.setBackground(new java.awt.Color(0, 0, 0));
        jButton_PC_Editar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_PC_Editar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_PC_Editar.setText("Editar");
        jButton_PC_Editar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_PC_EditarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_PC_EditarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_PC_EditarMouseExited(evt);
            }
        });
        jButton_PC_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PC_EditarActionPerformed(evt);
            }
        });
        jDialog_PerfilCliente.getContentPane().add(jButton_PC_Editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 200, 140, 50));

        jLabel_PC_EditarObjetivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_PC_EditarObjetivoMouseClicked(evt);
            }
        });
        jDialog_PerfilCliente.getContentPane().add(jLabel_PC_EditarObjetivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 40, 50, 50));

        jLabel_PC_EditarPeso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_PC_EditarPesoMouseClicked(evt);
            }
        });
        jDialog_PerfilCliente.getContentPane().add(jLabel_PC_EditarPeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 300, 50, 50));

        jLabel177.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel177.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel177.setText("Objetivo corporal:");
        jDialog_PerfilCliente.getContentPane().add(jLabel177, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 370, 30));

        jLabel_PC_EditarAltura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_PC_EditarAlturaMouseClicked(evt);
            }
        });
        jDialog_PerfilCliente.getContentPane().add(jLabel_PC_EditarAltura, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 170, 50, 50));

        jLabel_PC_Altura.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_PC_Altura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_PC_Altura.setText("Altura");
        jDialog_PerfilCliente.getContentPane().add(jLabel_PC_Altura, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, 360, 30));

        jLabel_PC_Peso.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_PC_Peso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_PC_Peso.setText("Peso");
        jDialog_PerfilCliente.getContentPane().add(jLabel_PC_Peso, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, 360, 30));

        jButton_PC_Volver.setBackground(new java.awt.Color(0, 0, 0));
        jButton_PC_Volver.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_PC_Volver.setForeground(new java.awt.Color(255, 255, 255));
        jButton_PC_Volver.setText("Volver");
        jButton_PC_Volver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_PC_VolverMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton_PC_VolverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton_PC_VolverMouseExited(evt);
            }
        });
        jDialog_PerfilCliente.getContentPane().add(jButton_PC_Volver, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 390, 140, 50));
        jDialog_PerfilCliente.getContentPane().add(jLabel_PC_Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ADMINISTRADOR");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 290, 210, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CLIENTE");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 220, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("NUTRICIONISTA");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel4MouseExited(evt);
            }
        });
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, 210, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("MONITOR");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, 210, -1));

        jButton1.setToolTipText("");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 140, 210, 350));

        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 220, 350));

        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 210, 350));

        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, 210, 350));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("<html>¿QUIÉN DESEAS SER?<br>SELECCIONA TU ROL</html>");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, -1, -1));
        jLabel2.getAccessibleContext().setAccessibleName("¿QUIÉN DESEAS SER? \nSELECCIONA TU ROL");

        getContentPane().add(jLabelFondoRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        
        jLabel3.setForeground(new Color(0x5CBCE0));
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        // TODO add your handling code here:
        
         jLabel3.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel3MouseExited

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
        // TODO add your handling code here:
        
        jLabel4.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel4MouseEntered

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
        // TODO add your handling code here:
        
        jLabel4.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel4MouseExited

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        // TODO add your handling code here:
        
        jLabel5.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        // TODO add your handling code here:
        
        jLabel5.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
        
        jLabel1.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        // TODO add your handling code here:
        
        jLabel1.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel1MouseExited

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked

        irAInicio();
        
        rolUsuario="Cliente";
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked


        irAInicio();
        
        rolUsuario="Cliente";


        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        
        irAInicio();
        
        rolUsuario="Nutricionista";
        
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        
        irAInicio();
        
        rolUsuario="Nutricionista";
        
    }//GEN-LAST:event_jButton3MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked

        irAInicio();
        
        rolUsuario="Monitor";

        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        
        irAInicio();
        
        rolUsuario="Monitor";
        
    }//GEN-LAST:event_jButton4MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        
        irAInicio();
        
        rolUsuario="Administrador";
        
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        
        
        irAInicio();
        
        rolUsuario="Administrador";

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
        // TODO add your handling code here:
        
        jButton6.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseExited
        // TODO add your handling code here:
        
         jButton6.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton6MouseExited

    // En este metodo reiniciamos los campos de texto a vacío y el comboBox del género a Masculino que es la primera opción 
    // del formulario donde se registraria cualquier usuario.
    
    private void reiniciarCamposRegistro() {
        
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jPasswordField1.setText("");
        jComboBox2.setSelectedIndex(0);
        
    }
    
    // En este metodo reiniciamos los campos de texto a vacío y el comboBox del objetivo corporal a Mantenimiento que es la primera opción 
    // del formulario donde se registraria cualquier cliente.
    
    private void reiniciarCamposRegistroCliente() {
        
        jTextField11.setText("");
        jTextField12.setText("");
        jComboBox1.setSelectedIndex(0);
        
    }
    
    // En este metodo reiniciamos los campos de texto a vacío del formulario donde iniciaria sesión cualquier usuario.
    
    private void reiniciarCamposInicioSesion() {
        
        jTextField2.setText("");
        jPasswordField2.setText("");
        
    }
    
    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        // TODO add your handling code here:
        
        reiniciarCamposInicioSesion();
        
        this.jDialog_Inicio.setVisible(false);
        this.jDialog_IniciarSesion.setVisible(true);
        
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        
        reiniciarCamposRegistro();
        
        this.jDialog_Inicio.setVisible(false);
        this.jDialog_Registrarse.setVisible(true);
        
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseEntered
        // TODO add your handling code here:
        
        jButton5.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton5MouseEntered

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
        // TODO add your handling code here:
        
        jButton5.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton5MouseExited

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        
        this.setVisible(true);
        this.jDialog_Inicio.setVisible(false);
        
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseExited
        // TODO add your handling code here:
        
        jLabel6.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel6MouseExited

    private void jLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseEntered
        // TODO add your handling code here:
        
         jLabel6.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel6MouseEntered

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
       
        this.jDialog_IniciarSesion.setVisible(false);
        this.jDialog_Registrarse.setVisible(true);
        
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        // TODO add your handling code here:
        
        String campoClave, contrasenna;
        
        campoClave=jTextField2.getText();
        
        contrasenna=jPasswordField2.getText();
        
        if(comprobarCampoVacio(campoClave) || comprobarCampoVacio(contrasenna)) {
            
           JOptionPane.showMessageDialog(null, "Hay campos vacíos. Completa los campos para iniciar sesión"); 
            
        } else {
            
            
            if(!esCampoClave(campoClave)) {
                
                JOptionPane.showMessageDialog(null, "El campo clave para iniciar sesión no cumple con el formato requerido. Recuerda:"
                        + "\n-DNI: debe contener 8 letras y un número (XXXXXXXX0).\n-Correo Electrónico: debe contener 8 letras, 2 números, el '@' y terminar en '.es' o '.com'\n"
                        + "-Nombre Usuario: debe contener 6 letras y 2 números");
                
            } else {
                
                if(!esContrasenna(contrasenna)) {
                    
                    JOptionPane.showMessageDialog(null, "El formato de la contraseña introducida es incorrecta.\n"
                            + "Recuerda que la contraseña debe contener una máyuscula, una minúscula y 3 números.");
                    
                } else {
                    
                    realizarInicioSesion(campoClave, contrasenna);
                    
                    if(comprobarReservasDiasAnteriores()) {
                        
                        borrarReservasDiasAnteriores();
                        
                    }
            
                }
        
            }
            
        }
         
        
    }//GEN-LAST:event_jButton9MouseClicked

    
    private void jButton9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseExited
        // TODO add your handling code here:
        
        this.jButton9.setBackground(Color.white);
        
    }//GEN-LAST:event_jButton9MouseExited

    private void jButton9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseEntered
        // TODO add your handling code here:
        
        this.jButton9.setBackground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton9MouseEntered

    private void jButton7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseExited
        // TODO add your handling code here:
        
        this.jButton7.setBackground(Color.white);
        
    }//GEN-LAST:event_jButton7MouseExited

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
        // TODO add your handling code here:
        
        this.jButton7.setBackground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton7MouseEntered

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked

        this.jDialog_IniciarSesion.setVisible(false);
        this.jDialog_Inicio.setVisible(true);
        
    }//GEN-LAST:event_jButton7MouseClicked

    private void jLabel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseEntered
        // TODO add your handling code here:
        
        this.jLabel9.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel9MouseEntered

    private void jLabel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseExited
        // TODO add your handling code here:
        
        this.jLabel9.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel9MouseExited

    private void jLabel18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseExited
        // TODO add your handling code here:
        
        this.jLabel18.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel18MouseExited

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // TODO add your handling code here:
        
        this.jDialog_IniciarSesion.setVisible(true);
        this.jDialog_Registrarse.setVisible(false);
        
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jLabel18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseEntered
        // TODO add your handling code here:
        
        this.jLabel18.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel18MouseEntered

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        // TODO add your handling code here:
        
        this.jDialog_Inicio.setVisible(true);
        this.jDialog_Registrarse.setVisible(false);
        
    }//GEN-LAST:event_jButton8MouseClicked

    private void jButton8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseEntered
        // TODO add your handling code here:
        
        this.jButton8.setBackground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton8MouseEntered

    private void jButton8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseExited
        // TODO add your handling code here:
        
        this.jButton8.setBackground(Color.white);
        
    }//GEN-LAST:event_jButton8MouseExited

    private void jButton10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseClicked
        // TODO add your handling code here:
        
        String dni, nombreApellidos, genero, nombreUsuario, correo, contrasenna;
        
        dni=jTextField4.getText().toUpperCase();
        nombreApellidos=jTextField5.getText();
        nombreUsuario=jTextField6.getText();
        correo=jTextField7.getText();
        contrasenna=jPasswordField1.getText();
        genero=jComboBox2.getSelectedItem().toString();
        
        
        if(comprobarCampoVacio(dni) || comprobarCampoVacio(nombreApellidos) || comprobarCampoVacio(nombreUsuario) || comprobarCampoVacio(correo) || comprobarCampoVacio(contrasenna)) {
            
            JOptionPane.showMessageDialog(null, "Hay campos vacíos. Completa los campos para registrarte");
            
        } else {
            
            
            if(!esDNI(dni)) {
                
                JOptionPane.showMessageDialog(null, "DNI introducido incorrectamente. Recuerda que el formato es 8 letras y un número (XXXXXXXX0)");
                
            }
            
            else if(!esNombreApellidos(nombreApellidos)) {
                
                JOptionPane.showMessageDialog(null, "Nombre y apellidos introducido incorrectamente. Recuerda que el formato debe contener 3 palabaras separadas por 2 espacios (XXXX XXXX XXXX)");
            }
            
            else if(!esNombreUsuario(nombreUsuario)) {
                 
                JOptionPane.showMessageDialog(null, "Nombre de usuario introducido incorrectamente. Recuerda que el formato debe contener 6 letras y 2 numeros (XXXXXX00)");
                
            }
            
            else if(!esCorreoElectronico(correo)) {
                
                JOptionPane.showMessageDialog(null, "Correo electrónico introducido incorrectamente. Recuerda que el formato debe contener 8 letras, 2 números, el '@' y terminar en '.es' o '.com'");
            }
            
            else if(!esContrasenna(contrasenna)) {
                
                JOptionPane.showMessageDialog(null, "Contraseña introducida incorrectamente. Recuerda que el formato debe tener una longitud de 8 caracteres y\ndebe contener una máyuscula y mínuscula y 3 números ");
                
            } else {
                
                dniUsuario=dni;
                
                if(!comprobarUsuarioRegistrado(dniUsuario, correo, nombreUsuario)) {
                    
                    realizarRegistroUsuario(nombreApellidos, nombreUsuario, genero, correo, contrasenna);
                    
                } else {
                    
                    JOptionPane.showMessageDialog(null, "El usuario está ya registrado en el sistema");
                    
                }
                
                
            }
            
            
        }
        
             
        
    }//GEN-LAST:event_jButton10MouseClicked

    private void jButton10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseEntered
        // TODO add your handling code here:
        
        this.jButton10.setBackground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton10MouseEntered

    private void jButton10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseExited
        // TODO add your handling code here:
        
        this.jButton10.setBackground(Color.white);
        
        
    }//GEN-LAST:event_jButton10MouseExited

    private void jButton12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseClicked
        // TODO add your handling code here:
        
        String objetivo;
        int altura;
        double peso;
        Date fechaNacimiento;
        
        String textoPeso=jTextField12.getText().replace(',','.');
        
        fechaNacimiento=jCalendar1.getCalendar().getTime();
                
        String fechaNacimientoFormato=formatearFecha(fechaNacimiento);

        if(comprobarCampoVacio(jTextField11.getText()) || comprobarCampoVacio(jTextField12.getText()) || jCalendar1.getCalendar()==null) {
            
            JOptionPane.showMessageDialog(null, "Hay campos vacíos. Completa los campos para registrarte");
            
        } else {
            
            
            if(!comprobarEnteroPositivo(jTextField11.getText())) {
                
                JOptionPane.showMessageDialog(null, "Altura introducida incorrectamente. Recuerda que debe ser un número entero positivo.");
                
            } else if(!comprobarDoblePositivo(textoPeso)) {
                
                JOptionPane.showMessageDialog(null, "Peso introcido incorrectamente. Recuerda que debe ser un número decimal positivo.");
            
            } else if(!comprobarEdadValida(fechaNacimientoFormato)) {
                
                JOptionPane.showMessageDialog(null, "No se permite registrar clientes menores de 16 años.");

            } else {
                
                objetivo=jComboBox1.getSelectedItem().toString();
            
                altura=Integer.parseInt(jTextField11.getText());
                peso=Double.parseDouble(textoPeso);

                registrarCliente(fechaNacimientoFormato, altura, peso, objetivo);
                
                this.jDialog_Inicio.setVisible(true);
                this.jDialog_RegistrarseCliente.setVisible(false);
                
                
            }
            
        }
        
        
    }//GEN-LAST:event_jButton12MouseClicked

    
    // En este método comprobamos que la edad del cliente sea mayor o igual a 16 años para que se pueda 
    // registrar si cumple esta condición.
    private boolean comprobarEdadValida(String fechaNacimiento) {
        
        boolean esEdadValida = false;

        try {
            
            LocalDate fechaNac = LocalDate.parse(fechaNacimiento);
            
            int annoNacimiento = fechaNac.getYear();
            int annoActual = LocalDate.now().getYear();
            
            int edad = annoActual - annoNacimiento;
            
            if (edad > 16 || (edad == 16 && LocalDate.now().isAfter(fechaNac.plusYears(16)))) {
                
                esEdadValida = true;
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }

        return esEdadValida;
    }
    
    private void jButton12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseEntered
        // TODO add your handling code here:
        
         this.jButton12.setBackground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton12MouseEntered

    private void jButton12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseExited
        // TODO add your handling code here:
        
         this.jButton12.setBackground(Color.white);
        
    }//GEN-LAST:event_jButton12MouseExited

    private void jButton16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseClicked
        // TODO add your handling code here:
        
        registrarNutricionista("Deportiva");
        
        jDialog_RegistrarseNutricionista.setVisible(false);
        jDialog_Inicio.setVisible(true);
        
    }//GEN-LAST:event_jButton16MouseClicked

    private void jButton17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton17MouseClicked
        // TODO add your handling code here:
        
        registrarNutricionista("Clinica");
        
        jDialog_RegistrarseNutricionista.setVisible(false);
        jDialog_Inicio.setVisible(true);
        
    }//GEN-LAST:event_jButton17MouseClicked

    private void jButton15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseClicked
        // TODO add your handling code here:
        
        registrarNutricionista("Exclusion");
        
        jDialog_RegistrarseNutricionista.setVisible(false);
        jDialog_Inicio.setVisible(true);
        
        
    }//GEN-LAST:event_jButton15MouseClicked

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseClicked
        // TODO add your handling code here:
        
        registrarNutricionista("Deportiva");
        
        jDialog_RegistrarseNutricionista.setVisible(false);
        jDialog_Inicio.setVisible(true);
        
    }//GEN-LAST:event_jLabel26MouseClicked

    private void jLabel26MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseEntered
        // TODO add your handling code here:
        
        this.jLabel26.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel26MouseEntered

    private void jLabel26MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel26MouseExited
        // TODO add your handling code here:
        
        this.jLabel26.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel26MouseExited

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        // TODO add your handling code here:
        
         registrarNutricionista("Clinica");
        
        jDialog_RegistrarseNutricionista.setVisible(false);
        jDialog_Inicio.setVisible(true);
        
    }//GEN-LAST:event_jLabel27MouseClicked

    private void jLabel27MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseEntered
        // TODO add your handling code here:
        
        this.jLabel27.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel27MouseEntered

    private void jLabel27MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseExited
        // TODO add your handling code here:
        
        this.jLabel27.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel27MouseExited

    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseClicked
        // TODO add your handling code here:
        
        registrarNutricionista("Exclusion");
        
        jDialog_RegistrarseNutricionista.setVisible(false);
        jDialog_Inicio.setVisible(true);
        
    }//GEN-LAST:event_jLabel29MouseClicked

    private void jLabel29MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseEntered
        // TODO add your handling code here:
        
        this.jLabel29.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel29MouseEntered

    private void jLabel29MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseExited
        // TODO add your handling code here:
        
        this.jLabel29.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel29MouseExited

    private void jLabel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseClicked
        // TODO add your handling code here:
        
        registrarMonitor(SERVICIO_RESERVA);
        
        this.jDialog_RegistrarseMonitor.setVisible(false);
        this.jDialog_Inicio.setVisible(true);
        
    }//GEN-LAST:event_jLabel30MouseClicked

    private void jLabel30MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseEntered
        // TODO add your handling code here:
        
        this.jLabel30.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel30MouseEntered

    private void jLabel30MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseExited
        // TODO add your handling code here:
        
        this.jLabel30.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel30MouseExited

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
        // TODO add your handling code here:
        
        registrarMonitor("Cardio");
        
        this.jDialog_RegistrarseMonitor.setVisible(false);
        this.jDialog_Inicio.setVisible(true);
        
    }//GEN-LAST:event_jLabel32MouseClicked

    private void jLabel32MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseEntered
        // TODO add your handling code here:
        
         this.jLabel32.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel32MouseEntered

    private void jLabel32MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseExited
        // TODO add your handling code here:
        
        this.jLabel32.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel32MouseExited

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        // TODO add your handling code here:
        
        registrarMonitor("Crossfit");
        
        this.jDialog_RegistrarseMonitor.setVisible(false);
        this.jDialog_Inicio.setVisible(true);
        
    }//GEN-LAST:event_jLabel33MouseClicked

    private void jLabel33MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseEntered
        // TODO add your handling code here:
        
         this.jLabel33.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel33MouseEntered

    private void jLabel33MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseExited
        // TODO add your handling code here:
        
         this.jLabel33.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel33MouseExited

    private void jButton18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton18MouseClicked
        // TODO add your handling code here:
        
        registrarMonitor("Crossfit");
        
        this.jDialog_RegistrarseMonitor.setVisible(false);
        this.jDialog_Inicio.setVisible(true);
        
    }//GEN-LAST:event_jButton18MouseClicked

    private void jButton19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton19MouseClicked
        // TODO add your handling code here:
        
        registrarMonitor(SERVICIO_RESERVA);
        
        this.jDialog_RegistrarseMonitor.setVisible(false);
        this.jDialog_Inicio.setVisible(true);
        
    }//GEN-LAST:event_jButton19MouseClicked

    private void jButton20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton20MouseClicked
        // TODO add your handling code here:
        
        registrarMonitor("Cardio");
        
        this.jDialog_RegistrarseMonitor.setVisible(false);
        this.jDialog_Inicio.setVisible(true);
        
    }//GEN-LAST:event_jButton20MouseClicked

    private void jPasswordField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField2ActionPerformed

    private void jLabel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseClicked
        // TODO add your handling code here:
        
        this.jLabel42.setVisible(true);
        this.jLabel42.setEnabled(true);
        
        cambiarVisibilidadTexto(jPasswordField1, true);
        
        this.jLabel41.setVisible(false);
        this.jLabel41.setEnabled(false);
        
        
        
        
    }//GEN-LAST:event_jLabel41MouseClicked

    private void jLabel42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseClicked
        // TODO add your handling code here:
        
        this.jLabel41.setVisible(true);
        this.jLabel41.setEnabled(true);

        cambiarVisibilidadTexto(jPasswordField1, false);
        
        this.jLabel42.setVisible(false);
        this.jLabel42.setEnabled(false);
        
        
    }//GEN-LAST:event_jLabel42MouseClicked

    private void jLabel43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseClicked
        // TODO add your handling code here:
        
        this.jLabel45.setVisible(true);
        this.jLabel45.setEnabled(true);
        
        cambiarVisibilidadTexto(jPasswordField2, true);
        
        this.jLabel43.setVisible(false);
        this.jLabel43.setEnabled(false);
        
        
    }//GEN-LAST:event_jLabel43MouseClicked

    private void jLabel45MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel45MouseClicked
        // TODO add your handling code here:
        
        this.jLabel43.setVisible(true);
        this.jLabel43.setEnabled(true);
        
        cambiarVisibilidadTexto(jPasswordField2, false);
        
        this.jLabel45.setVisible(false);
        this.jLabel45.setEnabled(false);
        
    }//GEN-LAST:event_jLabel45MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jLabel44MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel44MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel44MouseClicked

    private void jLabel44MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel44MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel44MouseEntered

    private void jLabel44MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel44MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel44MouseExited

    private void jButton22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton22MouseClicked
        // TODO add your handling code here:
        
        this.jDialog_GestionCuentaBancaria.setVisible(false);
        this.jDialog_RegistrarCuentaBancaria.setVisible(true);
        
        
    }//GEN-LAST:event_jButton22MouseClicked

    private void jButton22MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton22MouseEntered
        // TODO add your handling code here:
        
        this.jButton22.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton22MouseEntered

    private void jButton22MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton22MouseExited
        // TODO add your handling code here:
        
         this.jButton22.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton22MouseExited

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton23MouseClicked
       
        
        if(comprobarHayCuentaBancaria()) {
            
            mostrarDatosBancarios();
            
            this.jDialog_ConsultaCuentaBancaria.setVisible(true);
            this.jDialog_GestionCuentaBancaria.setVisible(false);
            
        }
        
        
        
        
        
    }//GEN-LAST:event_jButton23MouseClicked

    private void jButton23MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton23MouseEntered
        // TODO add your handling code here:
        
        this.jButton23.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton23MouseEntered

    private void jButton23MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton23MouseExited
        // TODO add your handling code here:
        
         this.jButton23.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton23MouseExited

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton23ActionPerformed

    
    
    private void jButton24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton24MouseClicked
        
        if(!comprobarHayCuentaBancaria()) {
            
            JOptionPane.showMessageDialog(null, "No tienes cuenta bancaria registrada para ser borrada.");

        } else {
            
            borrarCuentaBancaria();
            
            ocultarBotonesSinCuenta();
            
            
        }
        
    }//GEN-LAST:event_jButton24MouseClicked

    
    
    private void jButton24MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton24MouseEntered
        // TODO add your handling code here:
        
        this.jButton24.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton24MouseEntered

    private void jButton24MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton24MouseExited
        // TODO add your handling code here:
        
        this.jButton24.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton24MouseExited

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MouseClicked
        // TODO add your handling code here:
        
        this.jDialog_GestionCuentaBancaria.setVisible(true);
        this.jDialog_RegistrarCuentaBancaria.setVisible(false);
        
    }//GEN-LAST:event_jButton21MouseClicked

    private void jButton21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MouseEntered

        this.jButton21.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton21MouseEntered

    private void jButton21MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MouseExited
        // TODO add your handling code here:
        
        this.jButton21.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton21MouseExited

    private void jButton25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton25MouseClicked
        // TODO add your handling code here:

        String textoNombreTitular, textoNumeroCuenta, textoSaldoInicial;
        
        String nombreTitular, numeroCuenta, banco;
        double saldoInicial;
        
        textoNombreTitular=jTextField8.getText();
        textoNumeroCuenta=jTextField9.getText();
        banco=jComboBox3.getSelectedItem().toString();
        textoSaldoInicial=jTextField13.getText();
        
        if(comprobarCampoVacio(textoNombreTitular) || comprobarCampoVacio(textoNumeroCuenta) || comprobarCampoVacio(textoSaldoInicial)) {
            
             JOptionPane.showMessageDialog(null, "Hay campos vacíos. Completa los campos para registrar la cuenta bancaria.");
             
        } else {
            
            if(!esNombreApellidos( textoNombreTitular)) {
                
                JOptionPane.showMessageDialog(null, "Nombre del titular introducido incorrectamente. Recuerda que el formato debe contener 3 palabaras separadas por 2 espacios (XXXX XXXX XXXX)");
                
            } else if (!esNumeroCuenta(textoNumeroCuenta)) {
                
                JOptionPane.showMessageDialog(null, "Número de cuenta introducido incorrectamente. Recuerda que el formato puede ser:\n"
                        + "- 1º Formato (XXXX-XXXX-XX-XXXXXXXXXX).\n"
                        + "- 2º Formato (XXXXXXXXXXXXXXXXXXXXX).\n"
                        + "- 3º Formato (XXXX-XXXX-XX XXXXXXXXXX).");

                
            } else if (!comprobarDoblePositivo(textoSaldoInicial)) {
                
                JOptionPane.showMessageDialog(null, "Saldo inical introducido correctamente. Recuerda que debe ser un número decimal positivo.");
                
            } else {
                
                nombreTitular=textoNombreTitular;
                numeroCuenta=textoNumeroCuenta;
                saldoInicial=Double.parseDouble(textoSaldoInicial.replace(",","."));
                
                registrarCuentaBancaria(nombreTitular, numeroCuenta, banco, saldoInicial);
                
                ocultarBotonesHayCuenta();
                
                jTextField8.setText("");
                jTextField9.setText("");
                jComboBox3.setSelectedIndex(0);
                jTextField13.setText("");
                
                this.jDialog_RegistrarCuentaBancaria.setVisible(false);
                this.jDialog_GestionCuentaBancaria.setVisible(true);
                
            }
            
            
        }
        
       
        
    }//GEN-LAST:event_jButton25MouseClicked

    
    
    private void jButton25MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton25MouseEntered
        // TODO add your handling code here:
        
        this.jButton25.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton25MouseEntered

    private void jButton25MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton25MouseExited
        // TODO add your handling code here:
        
        this.jButton25.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton25MouseExited

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton25ActionPerformed

    
    
    private void jButton26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton26MouseClicked
        // TODO add your handling code here:
        
        String textoSaldo="";
        double dineroIntroducido;
        
        textoSaldo=jTextField1.getText().replace(",",".");
        
        if(comprobarCampoVacio(textoSaldo)) {
            
            JOptionPane.showMessageDialog(null, "No has introcido el dinero para realizar la opeación.");
            
        } else if(!comprobarDoblePositivo(textoSaldo)) {
            
            JOptionPane.showMessageDialog(null, "Dinero introducido incorrectamente, por favor introduce un número decimal positivo.");
            
        } else {
            
            dineroIntroducido=Double.parseDouble(textoSaldo.replace(",","."));
            
            if(jLabel54.getText().equals("Introduzca el dinero a ingresar:")) {
            
                realizarIngresoSaldo(dineroIntroducido);
            
            } else {
            
                realizarRetiroSaldo(dineroIntroducido);
            
            }
            
            reiniciarOperacionesSaldo();
            
            
        }
         
    }//GEN-LAST:event_jButton26MouseClicked

    private void jButton26MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton26MouseEntered
        // TODO add your handling code here:
        
         this.jButton26.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton26MouseEntered

    private void jButton26MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton26MouseExited
        // TODO add your handling code here:
        
        this.jButton26.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton26MouseExited

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton27MouseClicked
        // TODO add your handling code here:
        
        this.jDialog_ConsultaCuentaBancaria.setVisible(false);
        this.jDialog_GestionCuentaBancaria.setVisible(true);
        
        reiniciarOperacionesSaldo();
        
    }//GEN-LAST:event_jButton27MouseClicked

    private void jButton27MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton27MouseEntered
        // TODO add your handling code here:
        
        this.jButton27.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton27MouseEntered

    private void jButton27MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton27MouseExited
        // TODO add your handling code here:
        
        this.jButton27.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton27MouseExited

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton28MouseClicked
        // TODO add your handling code here:
        
        this.jDialog_ModificarCuentaBancaria.setVisible(false);
        this.jDialog_ConsultaCuentaBancaria.setVisible(true);
        
    }//GEN-LAST:event_jButton28MouseClicked

    private void jButton28MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton28MouseEntered
        // TODO add your handling code here:
        
        this.jButton28.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton28MouseEntered

    private void jButton28MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton28MouseExited
        // TODO add your handling code here:
        
        this.jButton28.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton28MouseExited

    private void jButton29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton29MouseClicked
        // TODO add your handling code here:
        
        String textoNombreTitular, bancoSeleccionado, titularModificar="";
        
        textoNombreTitular=jTextField10.getText();
        
        bancoSeleccionado=jComboBox4.getSelectedItem().toString();
        
        
        if(comprobarCampoVacio(textoNombreTitular)) {
            
             JOptionPane.showMessageDialog(null, "Hay campos vacíos. Rellena los campos para modificar la cuenta bancaria.");
            
        } else {
            
            if(!esNombreApellidos(textoNombreTitular)) {
                
                JOptionPane.showMessageDialog(null, "Nombre del titular introducido incorrectamente. Recuerda que el formato debe contener 3 palabaras separadas por 2 espacios (XXXX XXXX XXXX).");
                
            
            } else {
                
                titularModificar=textoNombreTitular;
                
                modificarDatosCuentaBancaria(titularModificar, bancoSeleccionado);
                
                jLabel57.setText(titularModificar);
                
                mostrarImagenBanco(bancoSeleccionado);
                
                this.jDialog_ModificarCuentaBancaria.setVisible(false);
                this.jDialog_ConsultaCuentaBancaria.setVisible(true);
                
            }
            
        }
        
        
        
        
    }//GEN-LAST:event_jButton29MouseClicked

    private void jButton29MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton29MouseEntered
        // TODO add your handling code here:
        
        this.jButton29.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton29MouseEntered

    private void jButton29MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton29MouseExited
        // TODO add your handling code here:
        
        this.jButton29.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton29MouseExited

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jLabel_BancoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_BancoMouseClicked
        // TODO add your handling code here:
        
        
        jTextField10.setText(jLabel57.getText());

        jComboBox4.setSelectedIndex(0);

        this.jDialog_ModificarCuentaBancaria.setVisible(true);
        this.jDialog_ConsultaCuentaBancaria.setVisible(false);
        
    }//GEN-LAST:event_jLabel_BancoMouseClicked

    private void jLabel56MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel56MouseClicked
        // TODO add your handling code here:
        
        mostrarOperacionesSaldo("Introduzca el dinero a ingresar:");
        
    }//GEN-LAST:event_jLabel56MouseClicked

    private void jLabel55MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel55MouseClicked
        // TODO add your handling code here:
        
        mostrarOperacionesSaldo("Introduzca el dinero a retirar:");
        
    }//GEN-LAST:event_jLabel55MouseClicked

    private void jLabel51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel51MouseClicked
        // TODO add your handling code here:
        
        cerrarSesion();
        
    }//GEN-LAST:event_jLabel51MouseClicked

    private void jLabel51MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel51MouseEntered
        // TODO add your handling code here:
       
         this.jLabel51.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel51MouseEntered

    private void jLabel51MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel51MouseExited
        // TODO add your handling code here:
        
         this.jLabel51.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel51MouseExited

    private void jLabel_NU_MonitorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_NU_MonitorMouseClicked
        // TODO add your handling code here:
        
        jDialog_FuncionalidadMonitor.setVisible(false);
        jDialog_PerfilUsuario.setVisible(true);
        reiniciarPerfilUsuario();
        
    }//GEN-LAST:event_jLabel_NU_MonitorMouseClicked

    private void jLabel_NU_MonitorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_NU_MonitorMouseEntered
        // TODO add your handling code here:
        
        this.jLabel_NU_Monitor.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel_NU_MonitorMouseEntered

    private void jLabel_NU_MonitorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_NU_MonitorMouseExited
        // TODO add your handling code here:
        
        this.jLabel_NU_Monitor.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel_NU_MonitorMouseExited

    private void jLabel68MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel68MouseClicked
        // TODO add your handling code here:
        
        
        if(!hayRutinas()) {
            
            JOptionPane.showMessageDialog(null, "No hay rutinas creadas en el sistema. Para poder administrar las rutinas, debes crear alguna de ellas.");

        } else {
            
            this.jDialog_FuncionalidadMonitor.setVisible(false);
            this.jDialog_ConsultaRutina.setVisible(true);
        
            pintarTablaRutinas();
        
            mostrarListaMonitores(this.jComboBox5);
            this.jComboBox5.insertItemAt("Todos", 0);
            jComboBox5.setSelectedIndex(0);
 
        }
        
        
        
        
    }//GEN-LAST:event_jLabel68MouseClicked

    private void jLabel68MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel68MouseEntered
        // TODO add your handling code here:
        
         this.jLabel68.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel68MouseEntered

    private void jLabel68MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel68MouseExited
        // TODO add your handling code here:
        
        this.jLabel68.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel68MouseExited

    private void jLabel71MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel71MouseClicked
        // TODO add your handling code here:
        
        this.jDialog_FuncionalidadMonitor.setVisible(false);
        this.jDialog_AltaRutina.setVisible(true);
        
    }//GEN-LAST:event_jLabel71MouseClicked

    private void jLabel71MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel71MouseEntered
        // TODO add your handling code here:
        
         this.jLabel71.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel71MouseEntered

    private void jLabel71MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel71MouseExited
        // TODO add your handling code here:
        
        this.jLabel71.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel71MouseExited

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jLabel_FCC_MonitorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FCC_MonitorMouseClicked
       
       if(comprobarHayCuentaBancaria()) {
           
           ocultarBotonesHayCuenta();
           
       } else {
           
           ocultarBotonesSinCuenta();
       }
        
        reiniciarOperacionesSaldo();
        irCuentaBancaria();
        
    }//GEN-LAST:event_jLabel_FCC_MonitorMouseClicked

    private void jButton34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton34MouseClicked
        // TODO add your handling code here:
        
        salirCuentaBancaria();
        
    }//GEN-LAST:event_jButton34MouseClicked

    private void jButton34MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton34MouseEntered
        // TODO add your handling code here:
        
        this.jButton34.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton34MouseEntered

    private void jButton34MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton34MouseExited
        // TODO add your handling code here:
        
        this.jButton34.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton34MouseExited

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton_PU_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PU_EditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_PU_EditarActionPerformed

    private void jLabel_PU_EditarNombreUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_PU_EditarNombreUsuarioMouseClicked
        // TODO add your handling code here:
        
        editarCampo("Introduzca el nombre de usuario nuevo:");
        
    }//GEN-LAST:event_jLabel_PU_EditarNombreUsuarioMouseClicked

    private void jLabel_PU_EditarContrasennaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_PU_EditarContrasennaMouseClicked
        // TODO add your handling code here:
        
        editarCampo("Introduzca la contraseña nueva:");
        
    }//GEN-LAST:event_jLabel_PU_EditarContrasennaMouseClicked

    private void jLabel_PU_EditarCorreoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_PU_EditarCorreoMouseClicked
        // TODO add your handling code here:
        
        editarCampo("Introduzca el correo nuevo:");
        
    }//GEN-LAST:event_jLabel_PU_EditarCorreoMouseClicked

    private void jButton_PU_VolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_PU_VolverMouseClicked
        // TODO add your handling code here:
        
        this.jDialog_PerfilUsuario.setVisible(false);
        
        seleccionarVentanaFuncionalidadUsuario();
        
        mostrarCredencialesUsuario();
        
    }//GEN-LAST:event_jButton_PU_VolverMouseClicked

    private void jButton_PU_VolverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_PU_VolverMouseEntered
        // TODO add your handling code here:
        
        jButton_PU_Volver.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton_PU_VolverMouseEntered

    private void jButton_PU_VolverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_PU_VolverMouseExited
        // TODO add your handling code here:
        
         jButton_PU_Volver.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton_PU_VolverMouseExited

    // En este método comprobamos que el nombre_usuario que introduzca el usuario existe ya en el sistema
    // para realizar validaciones como el modificado de este campo, el cúal no puede ser repetido.
    public boolean comprobarNombreUsuarioExiste(String nombreUsuario) {
        
        String sql = "SELECT nombre_usuario FROM usuarios WHERE nombre_usuario = ?";
        
        boolean usuarioExiste=false;
        
        PreparedStatement st=null;
        
        try {
            
            st = con.prepareStatement(sql);
            
            st.setString(1, nombreUsuario);
            
            ResultSet rs = st.executeQuery();
             
            if (rs.next()) {
                usuarioExiste= true; 
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return usuarioExiste;
    }
    
    // En este método comprobamos que el correo que introduzca el usuario existe ya en el sistema
    // para realizar validaciones como el modificado de este campo, el cúal no puede ser repetido.
    public boolean comprobarCorreoExiste(String correoUsuario) {
        
        String sql = "SELECT correo FROM usuarios WHERE correo = ?";
        
        boolean correoExiste=false;
        
        PreparedStatement st=null;
        
        try {
            
            st = con.prepareStatement(sql);
            
            st.setString(1, correoUsuario);
            
            ResultSet rs = st.executeQuery();
             
            if (rs.next()) {
                correoExiste= true; 
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return correoExiste;
    }
    
    private void jButton_PU_EditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_PU_EditarMouseClicked
        // TODO add your handling code here:
        
        String mensajeError="", campoBD="";
        
        boolean campoValido=false;
        
        String campoEditar=jTextField3.getText();
        
        switch(jLabel73.getText()) {
            
            case "Introduzca el nombre de usuario nuevo:" -> {
                
                if(!esNombreUsuario(campoEditar)) {
                    
                     mensajeError="Nombre de usuario introducido incorrectamente. Recuerda que el formato debe contener 6 letras y 2 numeros (XXXXXX00)";
                    
                } else if(devolverNombreUsuario().equals(campoEditar)) {
                    
                    mensajeError="El nombre de usuario a modificar no puede ser que el mismo ya existente.";
                
                    
                } else if(comprobarNombreUsuarioExiste(campoEditar)) {
                    
                    mensajeError="El nombre de usuario a modificar ya existe en el sistema.";
                    
                } else {
                    
                    campoValido=true;
                    campoBD="nombre_usuario";
                    
                }
                
            }
            
            case "Introduzca el correo nuevo:" -> {
                
                
                if(!esCorreoElectronico(campoEditar)) {
                    
                     mensajeError="Correo electrónico introducido incorrectamente. Recuerda que el formato debe contener 8 letras, 2 números, el '@' y terminar en '.es' o '.com'";
                    
                } else if(devolverCorreo().equals(campoEditar)) {
                    
                    mensajeError="El correo electrónico a modificar no puede ser que el mismo ya existente.";
                
                 } else if(comprobarCorreoExiste(campoEditar)) {
                    
                    mensajeError="El correo electrónico a modificar ya existe en el sistema.";    
                    
                    
                } else {
                    
                    campoValido=true;
                    campoBD="correo";
                    
                }
                
            }
            
            
            case "Introduzca la contraseña nueva:" -> {
                
                
                if(!esContrasenna(campoEditar)) {
                    
                     mensajeError="Contraseña introducida incorrectamente. Recuerda que el formato debe tener una longitud de 8 caracteres y\ndebe contener una máyuscula y mínuscula y 3 números)";

                    
                } else if(devolverCorreo().equals(campoEditar)) {
                    
                    mensajeError="La contraseña a modificar no puede ser la misma de la que ya existe.";
                    
                } else {
                    
                    campoValido=true;
                    campoBD="contrasenna";
                    
                }
                
            }
 
            
        }
        
           
        if(!campoValido) {
            
            JOptionPane.showMessageDialog(null, mensajeError);
            
        } else {
            
            modificarCampo(campoBD, campoEditar);
            reiniciarPerfilUsuario();
        }
        
    }//GEN-LAST:event_jButton_PU_EditarMouseClicked

    private void jButton_PU_EditarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_PU_EditarMouseEntered
        // TODO add your handling code here:
        
        jButton_PU_Editar.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton_PU_EditarMouseEntered

    private void jButton_PU_EditarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_PU_EditarMouseExited
        // TODO add your handling code here:
        
        jButton_PU_Editar.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton_PU_EditarMouseExited

    private void jLabel_FG_MonitorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FG_MonitorMouseClicked
        // TODO add your handling code here:
        
        reiniciarPerfilUsuario();
        
        jDialog_FuncionalidadMonitor.setVisible(false);
        jDialog_PerfilUsuario.setVisible(true);
        
        
    }//GEN-LAST:event_jLabel_FG_MonitorMouseClicked

    private void jButton35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton35MouseClicked
        // TODO add your handling code here:
        
        String textoNombreRutina=jTextField15.getText();
        String textoTipoObjetivo="", dias="";
        
        if(!comprobarCadena(textoNombreRutina)) {
            
            JOptionPane.showMessageDialog(null, "Nombre rutina introducido incorrectamente. Sólo se permiten caracteres");
        
        } else if (comprobarRutinaExistente(textoNombreRutina)) {
            
            JOptionPane.showMessageDialog(null, "La rutina existe ya en el sistema.");

            
        } else {
     
            textoTipoObjetivo=jComboBox7.getSelectedItem().toString();
            dias=jComboBox6.getSelectedItem().toString();
            
            diasRutina=Integer.parseInt(dias.substring(0, 1));
            
            nombreRutina=textoNombreRutina;
            tipoObjetivo=textoTipoObjetivo;
            
            contadorDias=1;

            jLabel72.setText("DIA "+contadorDias);
            
            pintarTablaEjercicios(jTable1);
            
            this.jDialog_AltaRutina.setVisible(false);
            this.jDialog_RutinaDia.setVisible(true);
            
        }
        
    }//GEN-LAST:event_jButton35MouseClicked

    private void jButton35MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton35MouseEntered
        // TODO add your handling code here:
        
        jButton35.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton35MouseEntered

    private void jButton35MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton35MouseExited
        // TODO add your handling code here:
        
        jButton35.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton35MouseExited

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton32MouseClicked
        // TODO add your handling code here:
        
        this.jDialog_FuncionalidadMonitor.setVisible(false);
        this.jDialog_AltaRutina.setVisible(true);
        
    }//GEN-LAST:event_jButton32MouseClicked

    private void jButton36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton36MouseClicked
        // TODO add your handling code here:
        
        this.jDialog_AltaRutina.setVisible(false);
        
        if(rolUsuario.equals("Monitor")) {
            
            this.jDialog_FuncionalidadMonitor.setVisible(true);
            
        } else {
            
            this.jDialog_OperacionesRutinaAdministrador.setVisible(true);
        }
        
        
        
        
    }//GEN-LAST:event_jButton36MouseClicked

    private void jButton36MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton36MouseEntered
        // TODO add your handling code here:
        
        jButton36.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton36MouseEntered

    private void jButton36MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton36MouseExited
        // TODO add your handling code here:
        
        jButton36.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton36MouseExited

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton37MouseClicked
        // TODO add your handling code here:
        
        reiniciarRutinaDias();
        
    }//GEN-LAST:event_jButton37MouseClicked

    private void jButton37MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton37MouseEntered
        // TODO add your handling code here:
        
        jButton37.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton37MouseEntered

    private void jButton37MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton37MouseExited
        // TODO add your handling code here:
        
        jButton37.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton37MouseExited

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton38MouseClicked
        // TODO add your handling code here:
        
        if(!(ejerciciosSeleccionados.size()>=5)) {
            
            JOptionPane.showMessageDialog(null, "Debes seleccionar como mínimo 5 ejercicios para realizar la rutina del dia.");
            
        } else {

            ArrayList<String> ejerciciosSeleccionadosRutina = new ArrayList<>(ejerciciosSeleccionados);
            
            mapaRutina.put(contadorDias, ejerciciosSeleccionadosRutina);
            ejerciciosSeleccionados.clear();
            
            pintarTablaEjercicios(jTable1);
            
            contadorDias++;

            if(contadorDias>diasRutina) {

              realizarRutina();
 
              realizarDiasRutinas();
              
              reiniciarRutinaDias();
              
            } else {
                
              jLabel72.setText("DIA "+contadorDias);
                
            }
        }
        
    }//GEN-LAST:event_jButton38MouseClicked

    // Este método se encarga de reiniciar la pantalla donde el monitor selecciona los ejercicios de la rutina
    // donde reinicia el contador de días y borra tanto la rutina hecha como los ejercicios seleccionados
    // para que cuando vuelva a entrar para realizar otra rutina, puede hacerla completamente de nuevo.
    private void reiniciarRutinaDias() {
        
        this.jDialog_RutinaDia.setVisible(false);
        this.jDialog_FuncionalidadMonitor.setVisible(true);
        ejerciciosSeleccionados.clear();
        contadorDias=1;
        jTextField15.setText("");
        jComboBox6.setSelectedIndex(0);
        jComboBox7.setSelectedIndex(0);
        
        mapaRutina.clear();
        
    }
    
    // Al igual que el anterior método, se encarga de reiniciar la pantalla donde el nutricionista selecciona los alimentos para cada comida de la dieta
    // donde reinicia el contador de días y comidas y borra tanto la dieta hecha como los alimentos seleccionados en las comidas
    // para que cuando vuelva a entrar para realizar otra dieta, puede hacerla completamente de nuevo.
    
    private void reiniciarDietaDias() {
        
        this.jDialog_DietaDia.setVisible(false);
        this.jDialog_FuncionalidadNutricionista.setVisible(true);
        alimentosSeleccionados.clear();
        contadorDias=1;
        contadorComida=1;
        jTextField18.setText("");
        jComboBox11.setSelectedIndex(0);
        jComboBox12.setSelectedIndex(0);
        
        mapaComida.clear();
        mapaDieta.clear();
        
    }
    
    
    private void jButton38MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton38MouseEntered
        // TODO add your handling code here:
        
        jButton38.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton38MouseEntered

    private void jButton38MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton38MouseExited
        // TODO add your handling code here:
        
        jButton38.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton38MouseExited

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jLabel_FotoPechoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FotoPechoMouseClicked
        // TODO add your handling code here:
        
        pintarTablaEjerciciosConGM(jTable1, "Pecho");
        
    }//GEN-LAST:event_jLabel_FotoPechoMouseClicked

    private void jLabel_FotoEspaldaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FotoEspaldaMouseClicked
        // TODO add your handling code here:
        
        pintarTablaEjerciciosConGM(jTable1, "Espalda");
        
        
    }//GEN-LAST:event_jLabel_FotoEspaldaMouseClicked

    private void jLabel_FotoAbsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FotoAbsMouseClicked
        // TODO add your handling code here:
        
        pintarTablaEjerciciosConGM(jTable1, "Abdominales");
    }//GEN-LAST:event_jLabel_FotoAbsMouseClicked

    private void jLabel_FotoReiniciarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FotoReiniciarMouseClicked
        // TODO add your handling code here:
        
        pintarTablaEjercicios(jTable1);
        
    }//GEN-LAST:event_jLabel_FotoReiniciarMouseClicked

    private void jLabel_FotoBrazoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FotoBrazoMouseClicked
        // TODO add your handling code here:
        
        pintarTablaEjerciciosConGM(jTable1, "Brazo");
        
    }//GEN-LAST:event_jLabel_FotoBrazoMouseClicked

    private void jLabel_FotoPiernaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FotoPiernaMouseClicked
        // TODO add your handling code here:
        
        pintarTablaEjerciciosConGM(jTable1, "Pierna");
        
    }//GEN-LAST:event_jLabel_FotoPiernaMouseClicked

    private void jButton40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton40MouseClicked
        // TODO add your handling code here:
        
        this.jDialog_ConsultaRutina.setVisible(false);
        
        if(rolUsuario.equals("Monitor")) {
            
            this.jDialog_FuncionalidadMonitor.setVisible(true);
            
        } else {
            
            this.jDialog_OperacionesRutinaAdministrador.setVisible(true);
        }
        
        
        reiniciarBusquedaRutina();
        
    }//GEN-LAST:event_jButton40MouseClicked

    private void jButton40MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton40MouseEntered
        // TODO add your handling code here:
        
        jButton40.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton40MouseEntered

    private void jButton40MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton40MouseExited
        // TODO add your handling code here:
        
        jButton40.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton40MouseExited

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton33MouseClicked
        // TODO add your handling code here:
        
        if(!hayRutinas()) {
            
            JOptionPane.showMessageDialog(null, "No hay rutinas creadas en el sistema. Para poder administrar las rutinas, debes crear alguna de ellas.");

        } else {
            
            this.jDialog_FuncionalidadMonitor.setVisible(false);
            this.jDialog_ConsultaRutina.setVisible(true);
        
            pintarTablaRutinas();
        
            mostrarListaMonitores(this.jComboBox5);
            this.jComboBox5.insertItemAt("Todos", 0);
            jComboBox5.setSelectedIndex(0);
 
        }
        
    }//GEN-LAST:event_jButton33MouseClicked

    private void jButton39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton39MouseClicked
        // TODO add your handling code here:
        
        String textoRutina=jLabel76.getText();
        
        char letraDiaRutina = textoRutina.charAt(textoRutina.length() - 1);

        int diaRutinaSiguiente = Character.getNumericValue(letraDiaRutina) + 1;
        
        pintarEjerciciosRutinaVisualizar(diaRutinaSiguiente);
        
        if(diaRutinaSiguiente==devolverDiaMaximoRutina()) {
            
            jButton39.setVisible(false);
            jButton39.setEnabled(false);
        }        
        
        jButton41.setVisible(true);
        jButton41.setEnabled(true);
        
    }//GEN-LAST:event_jButton39MouseClicked

    private void jButton39MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton39MouseEntered
        // TODO add your handling code here:
        
        jButton39.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton39MouseEntered

    private void jButton39MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton39MouseExited
        // TODO add your handling code here:
        
        jButton39.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton39MouseExited

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton41MouseClicked
        // TODO add your handling code here:
        
        String textoRutina=jLabel76.getText();
        
        char letraDiaRutina = textoRutina.charAt(textoRutina.length() - 1);

        int diaRutinaAnterior = Character.getNumericValue(letraDiaRutina) - 1;
        
        pintarEjerciciosRutinaVisualizar(diaRutinaAnterior);
        
        if(diaRutinaAnterior==1) {
            
            jButton41.setVisible(false);
            jButton41.setEnabled(false);
        }
        
        jButton39.setVisible(true);
        jButton39.setEnabled(true);
        
    }//GEN-LAST:event_jButton41MouseClicked

    private void jButton41MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton41MouseEntered
        // TODO add your handling code here:
        
        jButton41.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton41MouseEntered

    private void jButton41MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton41MouseExited
        // TODO add your handling code here:
        
        jButton41.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton41MouseExited

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton42MouseClicked
        // TODO add your handling code here:
        
        this.jDialog_ConsultaRutina_VerEjercicios.setVisible(false);
        
        if(!rolUsuario.equals("Cliente")) {
            
            this.jDialog_ConsultaRutina.setVisible(true);
            
        } else {
            
            jDialog_FuncionalidadCliente.setVisible(true);
            
        }
        
        
        
        
    }//GEN-LAST:event_jButton42MouseClicked

    private void jButton42MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton42MouseEntered
        // TODO add your handling code here:
        
        jButton42.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton42MouseEntered

    private void jButton42MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton42MouseExited
        // TODO add your handling code here:
        
        jButton42.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton42MouseExited

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton43MouseClicked
        // TODO add your handling code here:
        
       String nombreRutinaMod=jTextField17.getText();
       String objetivoMod=jComboBox9.getSelectedItem().toString();
       String autorMod=jComboBox10.getSelectedItem().toString();
       
       if(autorMod.isEmpty() || !comprobarCadena(autorMod)) {
           
           JOptionPane.showMessageDialog(null, "Nombre autor a modificar introducido incorrectamente. Debe contener solo caracteres y espacios");

          
       } else if (nombreRutinaMod.isEmpty() || !comprobarCadena(nombreRutinaMod) || comprobarRutinaExistente(nombreRutinaMod)){
            
            JOptionPane.showMessageDialog(null, "Nombre rutina a modificar introducido incorrectamente o existe en el sistema. Debe contener solo caracteres y espacios");

            
       } else {
           
           
           modificarRutina(nombreRutinaMod, objetivoMod, autorMod);
           
           pintarTablaRutinas();
           this.jDialog_ConsultaRutina_ModificarRutina.setVisible(false);
           this.jDialog_ConsultaRutina.setVisible(true);
           
       }
        
    }//GEN-LAST:event_jButton43MouseClicked

    private void jButton43MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton43MouseEntered
        // TODO add your handling code here:
        
        jButton43.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton43MouseEntered

    private void jButton43MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton43MouseExited
        // TODO add your handling code here:
        
        jButton43.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton43MouseExited

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jLabel_ReiniciarBusquedaRutinaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ReiniciarBusquedaRutinaMouseClicked
        // TODO add your handling code here:
        
        reiniciarBusquedaRutina();
        
    }//GEN-LAST:event_jLabel_ReiniciarBusquedaRutinaMouseClicked

    private void jLabel_BuscarRutinaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_BuscarRutinaMouseClicked
        // TODO add your handling code here:
        
        String nombreRutinaBuscar=jTextField16.getText();
        String tipoObjetivoBuscar=jComboBox8.getSelectedItem().toString();
        String autorBuscar=jComboBox5.getSelectedItem().toString();
        
        filtrarBusquedaRutinas(nombreRutinaBuscar, tipoObjetivoBuscar, autorBuscar);
        
    }//GEN-LAST:event_jLabel_BuscarRutinaMouseClicked

    private void jButton44MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton44MouseClicked
        // TODO add your handling code here:
        
        this.jDialog_ConsultaRutina.setVisible(true);
        this.jDialog_ConsultaRutina_ModificarRutina.setVisible(false);
        
    }//GEN-LAST:event_jButton44MouseClicked

    private void jButton44MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton44MouseEntered
        // TODO add your handling code here:
        
        jButton44.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton44MouseEntered

    private void jButton44MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton44MouseExited
        // TODO add your handling code here:
        
        jButton44.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton44MouseExited

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jLabel81MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel81MouseClicked
        // TODO add your handling code here:
        
        jDialog_FuncionalidadNutricionista.setVisible(false);
        jDialog_Inicio.setVisible(true);
        
    }//GEN-LAST:event_jLabel81MouseClicked

    private void jLabel81MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel81MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel81MouseEntered

    private void jLabel81MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel81MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel81MouseExited

    private void jLabel_FCC_NutricionistaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FCC_NutricionistaMouseClicked
        // TODO add your handling code here:
        
       if(comprobarHayCuentaBancaria()) {
           
           ocultarBotonesHayCuenta();
           
       } else {
           
           ocultarBotonesSinCuenta();
       }
        
        reiniciarOperacionesSaldo();
        irCuentaBancaria();
        
    }//GEN-LAST:event_jLabel_FCC_NutricionistaMouseClicked

    private void jLabel_FG_NutricionistaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FG_NutricionistaMouseClicked
        // TODO add your handling code here:
        
        jDialog_FuncionalidadNutricionista.setVisible(false);
        jDialog_PerfilUsuario.setVisible(true);
        reiniciarPerfilUsuario();
        
        
        
    }//GEN-LAST:event_jLabel_FG_NutricionistaMouseClicked

    private void jLabel_NU_NutricionistaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_NU_NutricionistaMouseClicked
        // TODO add your handling code here:
        
        jDialog_FuncionalidadNutricionista.setVisible(false);
        jDialog_PerfilUsuario.setVisible(true);
        reiniciarPerfilUsuario();
        
    }//GEN-LAST:event_jLabel_NU_NutricionistaMouseClicked

    private void jLabel_NU_NutricionistaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_NU_NutricionistaMouseEntered
        // TODO add your handling code here:
        
        jLabel_NU_Nutricionista.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel_NU_NutricionistaMouseEntered

    private void jLabel_NU_NutricionistaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_NU_NutricionistaMouseExited
        // TODO add your handling code here:
        
         jLabel_NU_Nutricionista.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel_NU_NutricionistaMouseExited

    private void jLabel82MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel82MouseClicked
        // TODO add your handling code here:
        
        
        if(!hayDietas()) {
            
            JOptionPane.showMessageDialog(null, "No hay dietas creadas en el sistema. Para poder administrar las dietas, debes crear alguna de ellas.");

        } else {
            
            jDialog_FuncionalidadNutricionista.setVisible(false);
            jDialog_ConsultaDieta.setVisible(true);
        
            pintarTablaDietas();
        
            mostrarListaNutricionistas(this.jComboBox13);
            this.jComboBox13.insertItemAt("Todos", 0);
            jComboBox13.setSelectedIndex(0);
 
        }

    }//GEN-LAST:event_jLabel82MouseClicked

    private void jLabel82MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel82MouseEntered
        // TODO add your handling code here:
        
        jLabel82.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel82MouseEntered

    private void jLabel82MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel82MouseExited
        // TODO add your handling code here:
        
        jLabel82.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel82MouseExited

    private void jLabel83MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel83MouseClicked
        
        this.jDialog_FuncionalidadNutricionista.setVisible(false);
        this.jDialog_AltaDieta.setVisible(true);
        
    }//GEN-LAST:event_jLabel83MouseClicked

    private void jLabel83MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel83MouseEntered
        // TODO add your handling code here:
        
        this.jLabel83.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel83MouseEntered

    private void jLabel83MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel83MouseExited
        // TODO add your handling code here:
        
        this.jLabel83.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel83MouseExited

    private void jButton45MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton45MouseClicked
        // TODO add your handling code here:
        
        this.jDialog_FuncionalidadNutricionista.setVisible(false);
        this.jDialog_AltaDieta.setVisible(true);
        
    }//GEN-LAST:event_jButton45MouseClicked

    private void jButton46MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton46MouseClicked
        // TODO add your handling code here:
        
        if(!hayDietas()) {
            
            JOptionPane.showMessageDialog(null, "No hay dietas creadas en el sistema. Para poder administrar las dietas, debes crear alguna de ellas.");

        } else {
            
            jDialog_FuncionalidadNutricionista.setVisible(false);
            jDialog_ConsultaDieta.setVisible(true);
        
            pintarTablaDietas();
        
            mostrarListaNutricionistas(this.jComboBox13);
            this.jComboBox13.insertItemAt("Todos", 0);
            jComboBox13.setSelectedIndex(0);
 
        }
        
    }//GEN-LAST:event_jButton46MouseClicked

    private void jButton47MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton47MouseClicked
        // TODO add your handling code here:
        
        String textoNombreDieta=jTextField18.getText();
        String textoDescripcionDieta=jTextArea1.getText();
        
        String textoTipoObjetivoDieta="", textoDiasDieta="";
        
        if(!comprobarCadena(textoNombreDieta)) {
            
            JOptionPane.showMessageDialog(null, "Nombre dieta introducido incorrectamente. Sólo se permiten caracteres");
        
         } else if (!comprobarCadena(textoDescripcionDieta)) {
            
            JOptionPane.showMessageDialog(null, "Descripcion dieta introducido incorrectamente. Sólo se permiten caracteres");    
            
            
        } else if (comprobarDietaExistente(textoNombreDieta)) {
            
            JOptionPane.showMessageDialog(null, "La dieta existe ya en el sistema.");

            
        } else {
     
            textoTipoObjetivoDieta=jComboBox12.getSelectedItem().toString();
            textoDiasDieta=jComboBox11.getSelectedItem().toString();
            
            diasDieta=Integer.parseInt(textoDiasDieta.substring(0, 1));
            
            nombreDieta=textoNombreDieta;
            tipoObjetivoDieta=textoTipoObjetivoDieta;
            
            descripcionDieta=textoDescripcionDieta;
            
            contadorDias=1;
            contadorComida=1;

            jLabel88.setText("DIA "+contadorDias+" | " +devolverTipoComida(contadorComida));
   
            pintarTablaAlimentos(jTable4);
            
            this.jDialog_AltaDieta.setVisible(false);
            this.jDialog_DietaDia.setVisible(true);
            
        }
        
        
    }//GEN-LAST:event_jButton47MouseClicked

    private String devolverTipoComida(int numeroComida) {
        
        String tipoComida="";
        
        switch(numeroComida) {
            
            case 1 ->  tipoComida="Desayuno";
            
            case 2 ->  tipoComida="Almuerzo";
            
            case 3 ->  tipoComida="Merienda";
            
            case 4 ->  tipoComida="Cena";
            
        }
        
        return tipoComida;
    }
    
    
    private void jButton47MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton47MouseEntered
        // TODO add your handling code here:
        
        jButton47.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton47MouseEntered

    private void jButton47MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton47MouseExited
        // TODO add your handling code here:
        
        jButton47.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton47MouseExited

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton48MouseClicked
        // TODO add your handling code here:
        
        jDialog_FuncionalidadNutricionista.setVisible(true);
        jDialog_AltaDieta.setVisible(false);
        
        
    }//GEN-LAST:event_jButton48MouseClicked

    private void jButton48MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton48MouseEntered
        // TODO add your handling code here:
        
        jButton48.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton48MouseEntered

    private void jButton48MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton48MouseExited
        // TODO add your handling code here:
        
        jButton48.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton48MouseExited

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jLabel_FotoVerduraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FotoVerduraMouseClicked
        // TODO add your handling code here:
        
        pintarTablaAlimentosGA(jTable4, "Verduras");
        
    }//GEN-LAST:event_jLabel_FotoVerduraMouseClicked

    private void jLabel_FotoCarbohidratosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FotoCarbohidratosMouseClicked
        // TODO add your handling code here:
        
        pintarTablaAlimentosGA(jTable4, "Carbohidratos");
        
    }//GEN-LAST:event_jLabel_FotoCarbohidratosMouseClicked

    private void jLabel_FotoProteinaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FotoProteinaMouseClicked
        // TODO add your handling code here:
        
        pintarTablaAlimentosGA(jTable4, "Proteinas");
        
    }//GEN-LAST:event_jLabel_FotoProteinaMouseClicked

    private void jLabel_FotoReiniciarDietaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FotoReiniciarDietaMouseClicked
        // TODO add your handling code here:
        
        pintarTablaAlimentos(jTable4);
        
    }//GEN-LAST:event_jLabel_FotoReiniciarDietaMouseClicked

    private void jLabel_FotoLacteoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FotoLacteoMouseClicked
        // TODO add your handling code here:
        
        pintarTablaAlimentosGA(jTable4, "Productos Lacteos");
        
    }//GEN-LAST:event_jLabel_FotoLacteoMouseClicked

    private void jLabel_FotoFrutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FotoFrutaMouseClicked
        // TODO add your handling code here:
        
        pintarTablaAlimentosGA(jTable4, "Frutas");
        
    }//GEN-LAST:event_jLabel_FotoFrutaMouseClicked

    private void jButton49MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton49MouseClicked
        // TODO add your handling code here:
        
        if(!(alimentosSeleccionados.size()>=2)) {
            
            JOptionPane.showMessageDialog(null, "Debes seleccionar como mínimo 2 alimentos para realizar la comida del dia.");
            
        } else {

            ArrayList<String> alimentosSeleccionadosDieta = new ArrayList<>(alimentosSeleccionados);
            
            mapaComida.put(devolverTipoComida(contadorComida), alimentosSeleccionadosDieta);
            
            alimentosSeleccionados.clear();
            
            pintarTablaAlimentos(jTable4);
            
            contadorComida++;
            
            if(contadorComida>4) {

                mapaDieta.put(contadorDias, new HashMap<>(mapaComida));
                mapaComida.clear(); 
                
                contadorComida=1;
                contadorDias++;
                
            }

            if(contadorDias>diasDieta) {
                
              realizarDieta();
              
              realizarDiasDietas();
              
              reiniciarDietaDias();
              
            } else {
                
              jLabel88.setText("DIA "+contadorDias+" | " +devolverTipoComida(contadorComida));
                
            }
        }
        
    }//GEN-LAST:event_jButton49MouseClicked

    private void jButton49MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton49MouseEntered
        // TODO add your handling code here:
        
        jButton49.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton49MouseEntered

    private void jButton49MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton49MouseExited
        // TODO add your handling code here:
        
        jButton49.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton49MouseExited

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton50MouseClicked
        // TODO add your handling code here:
        
        jDialog_DietaDia.setVisible(false);
        jDialog_AltaDieta.setVisible(true);
        
    }//GEN-LAST:event_jButton50MouseClicked

    private void jButton50MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton50MouseEntered
        // TODO add your handling code here:
        
        jButton50.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton50MouseEntered

    private void jButton50MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton50MouseExited
        // TODO add your handling code here:
        
        jButton49.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton50MouseExited

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton50ActionPerformed

    private void jLabel_FotoAceitesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FotoAceitesMouseClicked
        // TODO add your handling code here:
        
        pintarTablaAlimentosGA(jTable4, "Grasas y aceites");
        
    }//GEN-LAST:event_jLabel_FotoAceitesMouseClicked

    private void jLabel_FotoDulcesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FotoDulcesMouseClicked
        // TODO add your handling code here:
        
        pintarTablaAlimentosGA(jTable4, "Azucares y dulces");
        
    }//GEN-LAST:event_jLabel_FotoDulcesMouseClicked

    private void jLabel_ReiniciarBusquedaDietaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ReiniciarBusquedaDietaMouseClicked
        // TODO add your handling code here:
        
        reiniciarBusquedaDieta();
        
    }//GEN-LAST:event_jLabel_ReiniciarBusquedaDietaMouseClicked

    private void jLabel_BuscarDietaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_BuscarDietaMouseClicked
        // TODO add your handling code here:
        
        String nombreDietaBuscar=jTextField19.getText();
        String tipoObjetivoDietaBuscar=jComboBox14.getSelectedItem().toString();
        String autorDietaBuscar=jComboBox13.getSelectedItem().toString();
        
        filtrarBusquedaDietas(nombreDietaBuscar, tipoObjetivoDietaBuscar, autorDietaBuscar);
        
    }//GEN-LAST:event_jLabel_BuscarDietaMouseClicked

    private void jButton51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton51MouseClicked
        // TODO add your handling code here:
        
        jDialog_FuncionalidadNutricionista.setVisible(true);
        jDialog_ConsultaDieta.setVisible(false);
        reiniciarBusquedaDieta();
        
    }//GEN-LAST:event_jButton51MouseClicked

    private void jButton51MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton51MouseEntered
        // TODO add your handling code here:
        
        jButton51.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton51MouseEntered

    private void jButton51MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton51MouseExited
        // TODO add your handling code here:
        
        jButton51.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton51MouseExited

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton51ActionPerformed

    private void jLabel_FotoAlmuerzoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FotoAlmuerzoMouseClicked
        // TODO add your handling code here:
        
         String textoDieta=jLabel94.getText();
        
        char letraDiaDieta = textoDieta.charAt(textoDieta.length() - 1);

        int diaDieta = Character.getNumericValue(letraDiaDieta);
        
        pintarAlimentosDietaVisualizar(diaDieta, "Almuerzo");
        
    }//GEN-LAST:event_jLabel_FotoAlmuerzoMouseClicked

    private void jLabel_FotoCenaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FotoCenaMouseClicked
        // TODO add your handling code here:
        
        String textoDieta=jLabel94.getText();
        
        char letraDiaDieta = textoDieta.charAt(textoDieta.length() - 1);

        int diaDieta = Character.getNumericValue(letraDiaDieta);
        
        pintarAlimentosDietaVisualizar(diaDieta, "Cena");
        
    }//GEN-LAST:event_jLabel_FotoCenaMouseClicked

    private void jLabel_FotoDesayunoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FotoDesayunoMouseClicked
        // TODO add your handling code here:
        
        
        String textoDieta=jLabel94.getText();
        
        char letraDiaDieta = textoDieta.charAt(textoDieta.length() - 1);

        int diaDieta = Character.getNumericValue(letraDiaDieta);
        
        pintarAlimentosDietaVisualizar(diaDieta, "Desayuno");
        
        
    }//GEN-LAST:event_jLabel_FotoDesayunoMouseClicked

    private void jButton52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton52MouseClicked
        // TODO add your handling code here:
        
        String textoDieta=jLabel94.getText();
        
        char letraDiaDieta = textoDieta.charAt(textoDieta.length() - 1);

        int diaDietaAnterior = Character.getNumericValue(letraDiaDieta) - 1;
        
        pintarAlimentosDietaVisualizar(diaDietaAnterior, "Desayuno");
        
        if(diaDietaAnterior==1) {
            
            jButton52.setVisible(false);
            jButton52.setEnabled(false);
        }
        
        jButton54.setVisible(true);
        jButton54.setEnabled(true);
        
        
    }//GEN-LAST:event_jButton52MouseClicked

    private void jButton52MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton52MouseEntered
        // TODO add your handling code here:
        
        jButton52.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton52MouseEntered

    private void jButton52MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton52MouseExited
        // TODO add your handling code here:
        
        jButton52.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton52MouseExited

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton53MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton53MouseClicked
        // TODO add your handling code here:
        
        reiniciarDietaVisualizar();

        jDialog_ConsultaDieta_VisualizarAlimentos.setVisible(false);
        
        if(!rolUsuario.equals("Cliente")) {
            
            jDialog_ConsultaDieta.setVisible(true);
            
        } else {
            
            jDialog_FuncionalidadCliente.setVisible(true);
            
        }
        
    }//GEN-LAST:event_jButton53MouseClicked

    private void jButton53MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton53MouseEntered
        // TODO add your handling code here:
        
        jButton53.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton53MouseEntered

    private void jButton53MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton53MouseExited
        // TODO add your handling code here:
        
        jButton53.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton53MouseExited

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton53ActionPerformed

    private void jLabel_FotoMeriendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FotoMeriendaMouseClicked
        // TODO add your handling code here:
        
        String textoDieta=jLabel94.getText();
        
        char letraDiaDieta = textoDieta.charAt(textoDieta.length() - 1);

        int diaDieta = Character.getNumericValue(letraDiaDieta);
        
        pintarAlimentosDietaVisualizar(diaDieta, "Merienda");
        
    }//GEN-LAST:event_jLabel_FotoMeriendaMouseClicked

    private void jButton54MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton54MouseClicked
        // TODO add your handling code here:
        
        
        String textoDieta=jLabel94.getText();
        
        char letraDiaDieta = textoDieta.charAt(textoDieta.length() - 1);

        int diaDietaSiguiente = Character.getNumericValue(letraDiaDieta) + 1;
        
        pintarAlimentosDietaVisualizar(diaDietaSiguiente, "Desayuno");
        
        if(diaDietaSiguiente==devolverDiaMaximoDieta()) {
            
            jButton54.setVisible(false);
            jButton54.setEnabled(false);
        }        
        
        jButton52.setVisible(true);
        jButton52.setEnabled(true);
        
        
    }//GEN-LAST:event_jButton54MouseClicked

    private void jButton54MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton54MouseEntered
        // TODO add your handling code here:
        
        
        jButton54.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton54MouseEntered

    private void jButton54MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton54MouseExited
        // TODO add your handling code here:
        
        jButton54.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton54MouseExited

    private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton54ActionPerformed

    private void jButton55MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton55MouseClicked
        // TODO add your handling code here:
        
        
       String nombreDietaMod=jTextField20.getText();
       String objetivoDietaMod=jComboBox16.getSelectedItem().toString();
       String autorDietaMod=jComboBox15.getSelectedItem().toString(); 
       String desDieta=jTextArea2.getText();
       
       if(autorDietaMod.isEmpty() || !comprobarCadena(autorDietaMod)) {
           
           JOptionPane.showMessageDialog(null, "Nombre autor a modificar introducido incorrectamente. Debe contener solo caracteres y espacios");

           
        } else if (desDieta.isEmpty() || !comprobarCadena(desDieta)){
            
            JOptionPane.showMessageDialog(null, "Descripcion dieta a modificar introducido incorrectamente o existe en el sistema. Debe contener solo caracteres y espacios");   
           
          
       } else if (nombreDietaMod.isEmpty() || !comprobarCadena(nombreDietaMod) || comprobarDietaExistente(nombreDietaMod)){
            
            JOptionPane.showMessageDialog(null, "Nombre dieta a modificar introducido incorrectamente o existe en el sistema. Debe contener solo caracteres y espacios");

            
       } else {
           
           
           modificarDieta(nombreDietaMod, objetivoDietaMod, autorDietaMod, desDieta);
           
           pintarTablaDietas();
           this.jDialog_ModificarDieta.setVisible(false);
           this.jDialog_ConsultaDieta.setVisible(true);
           
       }
       
        
    }//GEN-LAST:event_jButton55MouseClicked

    private void jButton55MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton55MouseEntered
        // TODO add your handling code here:
        
         jButton55.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton55MouseEntered

    private void jButton55MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton55MouseExited
        // TODO add your handling code here:
        
         jButton55.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton55MouseExited

    private void jButton55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton55ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton55ActionPerformed

    private void jButton56MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton56MouseClicked
        // TODO add your handling code here:
        
        jDialog_ModificarDieta.setVisible(false);
        jDialog_ConsultaDieta.setVisible(true);
        
     
        
    }//GEN-LAST:event_jButton56MouseClicked

    private void jButton56MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton56MouseEntered
        // TODO add your handling code here:
        
        jButton56.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton56MouseEntered

    private void jButton56MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton56MouseExited
        // TODO add your handling code here:
        
        jButton56.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton56MouseExited

    private void jButton56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton56ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton56ActionPerformed

    private void jLabel100MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel100MouseClicked
        // TODO add your handling code here:
        
       jDialog_Inicio.setVisible(true);
       jDialog_FuncionalidadAdministrador.setVisible(false);
        
    }//GEN-LAST:event_jLabel100MouseClicked

    private void jLabel100MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel100MouseEntered
        // TODO add your handling code here:
        
         jLabel100.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel100MouseEntered

    private void jLabel100MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel100MouseExited
        // TODO add your handling code here:
        
        jLabel100.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel100MouseExited

    private void jLabel_FCC_AdministradorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FCC_AdministradorMouseClicked
        // TODO add your handling code here:
        
       if(comprobarHayCuentaBancaria()) {
           
           ocultarBotonesHayCuenta();
           
       } else {
           
           ocultarBotonesSinCuenta();
       }
        
        reiniciarOperacionesSaldo();
        irCuentaBancaria();
        
        
    }//GEN-LAST:event_jLabel_FCC_AdministradorMouseClicked

    private void jLabel_FG_AdministradorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FG_AdministradorMouseClicked
        // TODO add your handling code here:
        
        jDialog_FuncionalidadAdministrador.setVisible(false);
        jDialog_PerfilUsuario.setVisible(true);
        reiniciarPerfilUsuario();
        
    }//GEN-LAST:event_jLabel_FG_AdministradorMouseClicked

    private void jLabel_NU_AdministradorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_NU_AdministradorMouseClicked
        // TODO add your handling code here:
        
        jDialog_FuncionalidadAdministrador.setVisible(false);
        jDialog_PerfilUsuario.setVisible(true);
        reiniciarPerfilUsuario();
        
    }//GEN-LAST:event_jLabel_NU_AdministradorMouseClicked

    private void jLabel_NU_AdministradorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_NU_AdministradorMouseEntered
        // TODO add your handling code here:
        
        jLabel_NU_Administrador.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel_NU_AdministradorMouseEntered

    private void jLabel_NU_AdministradorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_NU_AdministradorMouseExited
        // TODO add your handling code here:
        
        jLabel_NU_Administrador.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel_NU_AdministradorMouseExited

    private void jLabel102MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel102MouseClicked
        // TODO add your handling code here:
        
        
        jDialog_FuncionalidadAdministrador.setVisible(false);
        jDialog_OperacionesSalaAdministrador.setVisible(true);
        
        
    }//GEN-LAST:event_jLabel102MouseClicked

    private void jLabel102MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel102MouseEntered
        // TODO add your handling code here:
       
        
        jLabel102.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel102MouseEntered

    
    
    private void jLabel102MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel102MouseExited
        // TODO add your handling code here:
        
        jLabel102.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel102MouseExited

    private void jButton57MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton57MouseClicked
        // TODO add your handling code here:
        
        jDialog_FuncionalidadAdministrador.setVisible(false);
        jDialog_OperacionesServicioAdministrador.setVisible(true);
        
    }//GEN-LAST:event_jButton57MouseClicked

    
    
    private void jButton59MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton59MouseClicked
        // TODO add your handling code here:
        
        jDialog_FuncionalidadAdministrador.setVisible(false);
        jDialog_OperacionesRutinaAdministrador.setVisible(true);
        
    }//GEN-LAST:event_jButton59MouseClicked

    private void jButton60MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton60MouseClicked
        // TODO add your handling code here:
        
        
        jDialog_FuncionalidadAdministrador.setVisible(false);
        jDialog_OperacionesSalaAdministrador.setVisible(true);
        
    }//GEN-LAST:event_jButton60MouseClicked

    private void jButton61MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton61MouseClicked
        // TODO add your handling code here:
        
        
        jDialog_FuncionalidadAdministrador.setVisible(false);
        jDialog_OperacionesProductoAdministrador.setVisible(true);
        
        
    }//GEN-LAST:event_jButton61MouseClicked

    private void jLabel103MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel103MouseClicked
        // TODO add your handling code here:
        
        jDialog_FuncionalidadAdministrador.setVisible(false);
        jDialog_OperacionesProductoAdministrador.setVisible(true);
        
    }//GEN-LAST:event_jLabel103MouseClicked

    private void jLabel103MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel103MouseEntered
        // TODO add your handling code here:
        
        
        jLabel103.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel103MouseEntered

    private void jLabel103MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel103MouseExited
        // TODO add your handling code here:
        
         jLabel103.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel103MouseExited

    private void jLabel104MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel104MouseClicked
        // TODO add your handling code here:
        
        
       jDialog_FuncionalidadAdministrador.setVisible(false);
       jDialog_OperacionesServicioAdministrador.setVisible(true);
        
        
    }//GEN-LAST:event_jLabel104MouseClicked

    private void jLabel104MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel104MouseEntered
        // TODO add your handling code here:
        
         
         jLabel104.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel104MouseEntered

    private void jLabel104MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel104MouseExited
        // TODO add your handling code here:
        
        jLabel104.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel104MouseExited

    private void jButton61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton61ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton61ActionPerformed

    private void jLabel105MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel105MouseClicked
        // TODO add your handling code here:
        
        
        jDialog_FuncionalidadAdministrador.setVisible(false);
        jDialog_OperacionesRutinaAdministrador.setVisible(true);
      
      
        
    }//GEN-LAST:event_jLabel105MouseClicked

    private void jLabel105MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel105MouseEntered
        // TODO add your handling code here:
        
        
        jLabel105.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel105MouseEntered

    private void jLabel105MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel105MouseExited
        // TODO add your handling code here:
        
        jLabel105.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel105MouseExited

    private void jLabel101MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel101MouseClicked
        // TODO add your handling code here:
        
        jDialog_OperacionesRutinaAdministrador.setVisible(false);
        
        jDialog_FuncionalidadAdministrador.setVisible(true);
    }//GEN-LAST:event_jLabel101MouseClicked

    private void jLabel101MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel101MouseEntered
        // TODO add your handling code here:
        
        jLabel101.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel101MouseEntered

    private void jLabel101MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel101MouseExited
        // TODO add your handling code here:
        
        jLabel101.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel101MouseExited

    private void jLabel_AdministrarRutinaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AdministrarRutinaMouseClicked
        // TODO add your handling code here:
        
        if(!hayRutinas()) {
            
            JOptionPane.showMessageDialog(null, "No hay rutinas creadas en el sistema. Para poder administrar las rutinas, debes crear alguna de ellas.");

        } else {
            
            this.jDialog_OperacionesRutinaAdministrador.setVisible(false);
            this.jDialog_ConsultaRutina.setVisible(true);
        
            pintarTablaRutinas();
        
            mostrarListaMonitores(this.jComboBox5);
            this.jComboBox5.insertItemAt("Todos", 0);
            jComboBox5.setSelectedIndex(0);
 
        }
        
    }//GEN-LAST:event_jLabel_AdministrarRutinaMouseClicked

    private void jLabel_AdministrarRutinaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AdministrarRutinaMouseEntered
        // TODO add your handling code here:
        
        jLabel_AdministrarRutina.setForeground(new Color(0x5CBCE0));
      
        
        
    }//GEN-LAST:event_jLabel_AdministrarRutinaMouseEntered

    private void jLabel_AdministrarRutinaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AdministrarRutinaMouseExited
        // TODO add your handling code here:
        
        jLabel_AdministrarRutina.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel_AdministrarRutinaMouseExited

    private void jLabel_AltaRutinaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AltaRutinaMouseClicked
        // TODO add your handling code here:
        
        this.jDialog_OperacionesRutinaAdministrador.setVisible(false);
        this.jDialog_AltaRutina.setVisible(true);
        
    }//GEN-LAST:event_jLabel_AltaRutinaMouseClicked

    private void jLabel_AltaRutinaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AltaRutinaMouseEntered
        // TODO add your handling code here:
        
        jLabel_AltaRutina.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel_AltaRutinaMouseEntered

    private void jLabel_AltaRutinaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AltaRutinaMouseExited
        // TODO add your handling code here:
        
        jLabel_AltaRutina.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel_AltaRutinaMouseExited

    private void jButton_AltaRutinaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_AltaRutinaMouseClicked
        // TODO add your handling code here:
        
        this.jDialog_OperacionesRutinaAdministrador.setVisible(false);
        this.jDialog_AltaRutina.setVisible(true);
        
    }//GEN-LAST:event_jButton_AltaRutinaMouseClicked

    private void jButton_AdministrarRutinaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_AdministrarRutinaMouseClicked
        // TODO add your handling code here:
        
        if(!hayRutinas()) {
            
            JOptionPane.showMessageDialog(null, "No hay rutinas creadas en el sistema. Para poder administrar las rutinas, debes crear alguna de ellas.");

        } else {
            
            this.jDialog_OperacionesRutinaAdministrador.setVisible(false);
            this.jDialog_ConsultaRutina.setVisible(true);
        
            pintarTablaRutinas();
        
            mostrarListaMonitores(this.jComboBox5);
            this.jComboBox5.insertItemAt("Todos", 0);
            jComboBox5.setSelectedIndex(0);
 
        }
        
    }//GEN-LAST:event_jButton_AdministrarRutinaMouseClicked

    private void jButton_AdministrarRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AdministrarRutinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_AdministrarRutinaActionPerformed

    private void jLabel106MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel106MouseClicked
        // TODO add your handling code here:
        
        jDialog_OperacionesProductoAdministrador.setVisible(false);
        
        jDialog_FuncionalidadAdministrador.setVisible(true);
        
    }//GEN-LAST:event_jLabel106MouseClicked

    private void jLabel106MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel106MouseEntered
        // TODO add your handling code here:
        
        jLabel106.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel106MouseEntered

    private void jLabel106MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel106MouseExited
        // TODO add your handling code here:
        
        jLabel106.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel106MouseExited

    private void jLabel_AltaProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AltaProductoMouseClicked
        // TODO add your handling code here:
        
        jDialog_OperacionesProductoAdministrador.setVisible(false);
        jDialog_AltaProducto.setVisible(true);
        
        
    }//GEN-LAST:event_jLabel_AltaProductoMouseClicked

    private void jLabel_AltaProductoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AltaProductoMouseEntered
        // TODO add your handling code here:
        
        jLabel_AltaProducto.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel_AltaProductoMouseEntered

    private void jLabel_AltaProductoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AltaProductoMouseExited
        // TODO add your handling code here:
        
        jLabel_AltaProducto.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel_AltaProductoMouseExited

    private void jButton_AltaProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_AltaProductoMouseClicked
        // TODO add your handling code here:
        
        jDialog_OperacionesProductoAdministrador.setVisible(false);
        jDialog_AltaProducto.setVisible(true);
        
    }//GEN-LAST:event_jButton_AltaProductoMouseClicked

    private void jLabel_ConsultaProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ConsultaProductoMouseClicked
        // TODO add your handling code here:
        
        
        if(!hayVentas()) {
            
            JOptionPane.showMessageDialog(null, "No se han realizado ninguna venta.");

        } else {
            
            jDialog_OperacionesProductoAdministrador.setVisible(false);
            this.jDialog_ConsultaVenta.setVisible(true);
            pintarTablaVentas();
            mostrarListaClientesVenta(jComboBox25);
        
            jComboBox25.insertItemAt("Todos", 0);
            jComboBox25.setSelectedIndex(0);
        
            
        }

        
    }//GEN-LAST:event_jLabel_ConsultaProductoMouseClicked

    private void jLabel_ConsultaProductoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ConsultaProductoMouseEntered
        // TODO add your handling code here:
        
        jLabel_ConsultaProducto.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel_ConsultaProductoMouseEntered

    private void jLabel_ConsultaProductoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ConsultaProductoMouseExited
        // TODO add your handling code here:
        
        jLabel_ConsultaProducto.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel_ConsultaProductoMouseExited

    private void jButton_ConsultaProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_ConsultaProductoMouseClicked
        // TODO add your handling code here:
        
        if(!hayVentas()) {
            
            JOptionPane.showMessageDialog(null, "No se han realizado ninguna venta.");

        } else {
            
            jDialog_OperacionesProductoAdministrador.setVisible(false);
            this.jDialog_ConsultaVenta.setVisible(true);
            pintarTablaVentas();
            mostrarListaClientesVenta(jComboBox25);
        
            jComboBox25.insertItemAt("Todos", 0);
            jComboBox25.setSelectedIndex(0);
        
            
        }
        
    }//GEN-LAST:event_jButton_ConsultaProductoMouseClicked

    private void jButton_ConsultaProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ConsultaProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_ConsultaProductoActionPerformed

    private void jButton_AdministrarProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_AdministrarProductoMouseClicked
        // TODO add your handling code here:
        
        if(!hayProductos()) {
            
            JOptionPane.showMessageDialog(null, "No hay productos en el sistema. Debes crear algun producto para poder administrarlos.");

        } else {
            
            jDialog_OperacionesProductoAdministrador.setVisible(false);
            jDialog_ConsultaProducto.setVisible(true);
        
            pintarTablaProductos();
            
        }
        
        
    }//GEN-LAST:event_jButton_AdministrarProductoMouseClicked

    private void jLabel107MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel107MouseClicked

        // TODO add your handling code here:
        
        
        jDialog_OperacionesSalaAdministrador.setVisible(false);
        
        jDialog_FuncionalidadAdministrador.setVisible(true);
        
    }//GEN-LAST:event_jLabel107MouseClicked

    private void jLabel107MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel107MouseEntered
        // TODO add your handling code here:
        
        jLabel107.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel107MouseEntered

    private void jLabel107MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel107MouseExited
        // TODO add your handling code here:
        
        jLabel107.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel107MouseExited

    private void jLabel_AltaSalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AltaSalaMouseClicked
        // TODO add your handling code here:
        
        jDialog_AltaSala.setVisible(true);
        jDialog_OperacionesSalaAdministrador.setVisible(false);
        
        mostrarListaMonitoresSala(jComboBox17);
        
        
    }//GEN-LAST:event_jLabel_AltaSalaMouseClicked

    private void jLabel_AltaSalaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AltaSalaMouseEntered
        // TODO add your handling code here:
        
        jLabel_AltaSala.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel_AltaSalaMouseEntered

    private void jLabel_AltaSalaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AltaSalaMouseExited
        // TODO add your handling code here:
        
        jLabel_AltaSala.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel_AltaSalaMouseExited

    private void jButton_AltaSalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_AltaSalaMouseClicked
        // TODO add your handling code here:
        
        jDialog_AltaSala.setVisible(true);
        jDialog_OperacionesSalaAdministrador.setVisible(false);
        
        mostrarListaMonitoresSala(jComboBox17);
        
        
    }//GEN-LAST:event_jButton_AltaSalaMouseClicked

    private void jLabel_ConsultaSalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ConsultaSalaMouseClicked
        // TODO add your handling code here:
        
        if(!hayReservas()) {
            
            JOptionPane.showMessageDialog(null, "No se han realizado reservados.");

            
        } else {
            
            mostrarListaClientesReservas(jComboBox27);
        
            jComboBox27.insertItemAt("Todos", 0);
        
            jComboBox27.setSelectedIndex(0);
        
            mostrarListaSalasReservas(jComboBox28);
        
            jComboBox28.insertItemAt("Todos", 0);
        
            jComboBox28.setSelectedIndex(0);
        
            pintarTablaReservas();
        
            jDialog_OperacionesSalaAdministrador.setVisible(false);
            jDialog_ConsultaReserva.setVisible(true);
            
        }
        
        
        
        
        
        
    }//GEN-LAST:event_jLabel_ConsultaSalaMouseClicked

    // Este método realiza una búsqueda de las rutinas que se han realizado por los monitores donde se utiliza
    // para comprobar si se puede gestionar o no las rutinas, ya que si no se ha realizado ninguna rutina el monitor no puede gestionar las rutinas.
    private boolean hayRutinas() {
        
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean hayRutina=false;

        try {
            
            st = con.prepareStatement("SELECT * FROM gym_zone.rutinas");

            rs = st.executeQuery();

            if (rs.next()) {
                hayRutina=true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return hayRutina;
    }
    
    // Este método realiza una búsqueda de las dietas que se han realizado por los nutricionistas donde se utiliza
    // para comprobar si se puede gestionar o no las dietas, ya que si no se ha realizado ninguna dieta el nutricionista no puede gestionar las dietas.
    private boolean hayDietas() {
        
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean hayDieta=false;

        try {
            
            st = con.prepareStatement("SELECT * FROM gym_zone.dietas");

            rs = st.executeQuery();

            if (rs.next()) {
                hayDieta=true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return hayDieta;
    }
    
    // Este método realiza una búsqueda de las reservas que se han realizado por los clientes donde se utiliza
    // para comprobar si el administrador puede visualizar o no las reservas hechas.
    private boolean hayReservas() {
        
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean hayReserva=false;

        try {
            
            st = con.prepareStatement("SELECT * FROM gym_zone.reservas");

            rs = st.executeQuery();

            if (rs.next()) {
                hayReserva=true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return hayReserva;
    }
    
    private void jLabel_ConsultaSalaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ConsultaSalaMouseEntered
        // TODO add your handling code here:
        
        jLabel_ConsultaSala.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel_ConsultaSalaMouseEntered

    private void jLabel_ConsultaSalaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ConsultaSalaMouseExited
        // TODO add your handling code here:
        
        jLabel_ConsultaSala.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel_ConsultaSalaMouseExited

    private void jButton_ConsultaSalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_ConsultaSalaMouseClicked
        // TODO add your handling code here:
        
        if(!hayReservas()) {
            
            JOptionPane.showMessageDialog(null, "No se han realizado reservas.");

            
        } else {
            
            mostrarListaClientesReservas(jComboBox27);
        
            jComboBox27.insertItemAt("Todos", 0);
        
            jComboBox27.setSelectedIndex(0);
        
            mostrarListaSalasReservas(jComboBox28);
        
            jComboBox28.insertItemAt("Todos", 0);
        
            jComboBox28.setSelectedIndex(0);
        
            pintarTablaReservas();
        
            jDialog_OperacionesSalaAdministrador.setVisible(false);
            jDialog_ConsultaReserva.setVisible(true);
            
        }
        
    }//GEN-LAST:event_jButton_ConsultaSalaMouseClicked

    private void jButton_ConsultaSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ConsultaSalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_ConsultaSalaActionPerformed

    private void jLabel_AdministrarSalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AdministrarSalaMouseClicked
        // TODO add your handling code here:
        
        if(!comprobarSalasConMonitor() &&  !comprobarSalasSinMonitor()) {
            
            JOptionPane.showMessageDialog(null, "No hay salas creadas en el sistema. Para administrar las salas, debes crear algunas de ellas.");

        } else {
            
            jDialog_OperacionesSalaAdministrador.setVisible(false);
        
            jDialog_ConsultaSala.setVisible(true);
        
            pintarTablaSalas();

            mostrarListaMonitoresAsignadosSala(jComboBox23);
        
            jComboBox23.insertItemAt("Todos", 0);
        
            jComboBox23.setSelectedItem("Todos");
            
            
        }
        
       
        
    }//GEN-LAST:event_jLabel_AdministrarSalaMouseClicked

    private void jLabel_AdministrarSalaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AdministrarSalaMouseEntered
        // TODO add your handling code here:
        
        jLabel_AdministrarSala.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel_AdministrarSalaMouseEntered

    private void jLabel_AdministrarSalaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AdministrarSalaMouseExited
        // TODO add your handling code here:
        
        jLabel_AdministrarSala.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel_AdministrarSalaMouseExited

    private void jButton_AdministrarSalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_AdministrarSalaMouseClicked
        // TODO add your handling code here:
        
        if(!comprobarSalasConMonitor() &&  !comprobarSalasSinMonitor()) {
            
            JOptionPane.showMessageDialog(null, "No hay salas creadas en el sistema. Para administrar las salas, debes crear algunas de ellas.");

        } else {
            
             jDialog_OperacionesSalaAdministrador.setVisible(false);
        
            jDialog_ConsultaSala.setVisible(true);
        
            pintarTablaSalas();

            mostrarListaMonitoresAsignadosSala(jComboBox23);
        
            jComboBox23.insertItemAt("Todos", 0);
        
            jComboBox23.setSelectedItem("Todos");
            
            
        }
        
    }//GEN-LAST:event_jButton_AdministrarSalaMouseClicked

    private void jLabel108MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel108MouseClicked
        // TODO add your handling code here:
        
        jDialog_OperacionesServicioAdministrador.setVisible(false);
        
        jDialog_FuncionalidadAdministrador.setVisible(true);
        
    }//GEN-LAST:event_jLabel108MouseClicked

    private void jLabel108MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel108MouseEntered
        // TODO add your handling code here:
        
        jLabel108.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel108MouseEntered

    private void jLabel108MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel108MouseExited
        // TODO add your handling code here:
        
        jLabel108.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel108MouseExited

    private void jLabel_AltaServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AltaServicioMouseClicked
        // TODO add your handling code here:
        
        jDialog_AltaServicio.setVisible(true);
        jDialog_OperacionesServicioAdministrador.setVisible(false);
        
    }//GEN-LAST:event_jLabel_AltaServicioMouseClicked

    private void jLabel_AltaServicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AltaServicioMouseEntered
        // TODO add your handling code here:
        
        jLabel_AltaServicio.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel_AltaServicioMouseEntered

    private void jLabel_AltaServicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AltaServicioMouseExited
        // TODO add your handling code here:
        
        
        jLabel_AltaServicio.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel_AltaServicioMouseExited

    private void jButton_AltaServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_AltaServicioMouseClicked
        // TODO add your handling code here:
        
        jDialog_AltaServicio.setVisible(true);
        jDialog_OperacionesServicioAdministrador.setVisible(false);
        
    }//GEN-LAST:event_jButton_AltaServicioMouseClicked

    private void jLabel_ConsultaServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ConsultaServicioMouseClicked
        // TODO add your handling code here:
        
        if(!hayPagosServicios()) {
            
            JOptionPane.showMessageDialog(null, "No se han realizado ningún pago por algún servicio.");

        } else {
            
            pintarTablaPagosServicio();
        
            mostrarListaClientesPagosServicio(jComboBox31);
        
            jComboBox31.insertItemAt("Todos", 0);
        
            jComboBox31.setSelectedIndex(0);
        
        
            mostrarListaServiciosPagos(jComboBox32);
        
            jComboBox32.insertItemAt("Todos", 0);
        
            jComboBox32.setSelectedIndex(0);
        
            jDialog_OperacionesServicioAdministrador.setVisible(false);
            jDialog_ConsultaPagosServicios.setVisible(true);
            
        }
        
        
        
    }//GEN-LAST:event_jLabel_ConsultaServicioMouseClicked

    // Este método realiza una búsqueda de los pagos por los servicios que se han realizado por los clientes donde se utiliza
    // para comprobar si el administrador puede visualizar o no los pagos de los servicios realizados.
    private boolean hayPagosServicios() {
        
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean hayPagosServicio=false;

        try {
            
            st = con.prepareStatement("SELECT * FROM gym_zone.pagos_servicios");

            rs = st.executeQuery();

            if (rs.next()) {
                hayPagosServicio=true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return hayPagosServicio;
    }
    
    // Este método realiza una búsqueda de las ventas de los prouctos que se han realizado por los clientes donde se utiliza
    // para comprobar si el administrador puede visualizar o no las ventas realizas.
    private boolean hayVentas() {
        
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean hayVentas=false;

        try {
            
            st = con.prepareStatement("SELECT * FROM gym_zone.ventas");

            rs = st.executeQuery();

            if (rs.next()) {
                hayVentas=true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return hayVentas;
    }
    
    // Este método realiza una búsqueda de los productos registrados por los administradores donde se utiliza
    // para comprobar si el administrador puede gestionar o no los productos, ya que si no hay ningun producto no podrá gestionarlos.
    private boolean hayProductos() {
        
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean hayProducto=false;

        try {
            
            st = con.prepareStatement("SELECT * FROM gym_zone.productos");

            rs = st.executeQuery();

            if (rs.next()) {
                hayProducto=true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return hayProducto;
    }
    
    private void jLabel_ConsultaServicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ConsultaServicioMouseEntered
        // TODO add your handling code here:
        
        jLabel_ConsultaServicio.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel_ConsultaServicioMouseEntered

    private void jLabel_ConsultaServicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ConsultaServicioMouseExited
        // TODO add your handling code here:
        
        jLabel_ConsultaServicio.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel_ConsultaServicioMouseExited

    private void jButton_ConsultaServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_ConsultaServicioMouseClicked
        // TODO add your handling code here:
        
        if(!hayPagosServicios()) {
            
            JOptionPane.showMessageDialog(null, "No se han realizado ningún pago por algún servicio.");

        } else {
            
            pintarTablaPagosServicio();
        
            mostrarListaClientesPagosServicio(jComboBox31);
        
            jComboBox31.insertItemAt("Todos", 0);
        
            jComboBox31.setSelectedIndex(0);
        
        
            mostrarListaServiciosPagos(jComboBox32);
        
            jComboBox32.insertItemAt("Todos", 0);
        
            jComboBox32.setSelectedIndex(0);
        
            jDialog_OperacionesServicioAdministrador.setVisible(false);
            jDialog_ConsultaPagosServicios.setVisible(true);
            
        }
        
    }//GEN-LAST:event_jButton_ConsultaServicioMouseClicked

    private void jButton_ConsultaServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ConsultaServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_ConsultaServicioActionPerformed

    private void jLabel_AdministrarServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AdministrarServicioMouseClicked
        // TODO add your handling code here:
        
        jDialog_OperacionesServicioAdministrador.setVisible(false);
        jDialog_ConsultaServicio.setVisible(true);
        pintarTablaServicios();
        
    }//GEN-LAST:event_jLabel_AdministrarServicioMouseClicked

    private void jLabel_AdministrarServicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AdministrarServicioMouseEntered
        // TODO add your handling code here:
        
        jLabel_AdministrarServicio.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel_AdministrarServicioMouseEntered

    private void jLabel_AdministrarServicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AdministrarServicioMouseExited
        // TODO add your handling code here:
        
        jLabel_AdministrarServicio.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel_AdministrarServicioMouseExited

    private void jButton_AdministrarServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_AdministrarServicioMouseClicked
        // TODO add your handling code here:
        
        jDialog_OperacionesServicioAdministrador.setVisible(false);
        jDialog_ConsultaServicio.setVisible(true);
        pintarTablaServicios();
        
    }//GEN-LAST:event_jButton_AdministrarServicioMouseClicked

    private void jLabel_AdministrarProductoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AdministrarProductoMouseExited
        // TODO add your handling code here:
        
         jLabel_AdministrarProducto.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel_AdministrarProductoMouseExited

    private void jLabel_AdministrarProductoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AdministrarProductoMouseEntered
        // TODO add your handling code here:
        
        jLabel_AdministrarProducto.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel_AdministrarProductoMouseEntered

    private void jLabel_AdministrarProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AdministrarProductoMouseClicked
        // TODO add your handling code here:
        
        if(!hayProductos()) {
            
            JOptionPane.showMessageDialog(null, "No hay productos en el sistema. Debes crear algun producto para poder administrarlos.");

        } else {
            
            jDialog_OperacionesProductoAdministrador.setVisible(false);
            jDialog_ConsultaProducto.setVisible(true);
        
            pintarTablaProductos();
            
        }
        
    }//GEN-LAST:event_jLabel_AdministrarProductoMouseClicked

    private void jDialog_OperacionesSalaAdministradorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDialog_OperacionesSalaAdministradorMouseClicked
        // TODO add your handling code here:
        
        jDialog_OperacionesSalaAdministrador.setVisible(false);
        jDialog_FuncionalidadAdministrador.setVisible(true);
        
    }//GEN-LAST:event_jDialog_OperacionesSalaAdministradorMouseClicked

    private void jButton58MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton58MouseClicked
        // TODO add your handling code here:
        
        String textoNombreProducto=jTextField22.getText();
        String textoPrecioProducto=jTextField23.getText();
        String textoCantidadProducto=jTextField21.getText();
        
        if(textoNombreProducto.isEmpty() || !comprobarCadena(textoNombreProducto)) {
            
           JOptionPane.showMessageDialog(null, "Nombre producto introducido incorrectamente. Solo debe contener caracteres.");

            
        } else if (textoPrecioProducto.isEmpty() || !comprobarDoblePositivo(textoPrecioProducto)) {
            
            JOptionPane.showMessageDialog(null, "Precio producto introducido incorrectamente. Solo debe ser un numero decimal positivo.");
            
        } else if (textoCantidadProducto.isEmpty() || !comprobarEnteroPositivo(textoCantidadProducto)) {
            
            JOptionPane.showMessageDialog(null, "Cantidad producto introducido incorrectamente. Solo debe ser un numero entero positivo.");
            

         } else if (comprobarProductoExistente(textoNombreProducto)) {
            
            JOptionPane.showMessageDialog(null, "El producto existe ya en el sistema.");
            
        } else {

            String nombreProducto=textoNombreProducto;
            double precioProducto=Double.parseDouble(textoPrecioProducto.replace(",","."));
            int cantidadProducto=Integer.parseInt(textoCantidadProducto);
            
            altaProducto(nombreProducto, precioProducto, cantidadProducto);
            
            jDialog_AltaProducto.setVisible(false);
            jDialog_OperacionesProductoAdministrador.setVisible(true);
            
            jTextField22.setText("");
            jTextField23.setText("");
            jTextField21.setText("");
            
        }
        
        
        
        
    }//GEN-LAST:event_jButton58MouseClicked

    private void jButton58MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton58MouseEntered
        // TODO add your handling code here:
        
        this.jButton58.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton58MouseEntered

    private void jButton58MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton58MouseExited
        // TODO add your handling code here:
        
        jButton58.setForeground(Color.white);
        
        
    }//GEN-LAST:event_jButton58MouseExited

    private void jButton58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton58ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton58ActionPerformed

    private void jButton62MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton62MouseClicked
        // TODO add your handling code here:
        
        jDialog_AltaProducto.setVisible(false);
        jDialog_OperacionesProductoAdministrador.setVisible(true);
        
    }//GEN-LAST:event_jButton62MouseClicked

    private void jButton62MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton62MouseEntered
        // TODO add your handling code here:
        
        this.jButton62.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton62MouseEntered

    private void jButton62MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton62MouseExited
        // TODO add your handling code here:
        
        jButton62.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton62MouseExited

    private void jButton62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton62ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton62ActionPerformed

    private void jButton63MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton63MouseClicked
        // TODO add your handling code here:
        
        String textoNombreServicio=jTextField24.getText();
        String textoPrecioServicio=jTextField26.getText();
        String textoDiasServicio=jTextField25.getText();
        
        if(textoNombreServicio.isEmpty() || !comprobarCadena(textoNombreServicio)) {
            
           JOptionPane.showMessageDialog(null, "Nombre servicio introducido incorrectamente. Solo debe contener caracteres.");

            
        } else if (textoPrecioServicio.isEmpty() || !comprobarDoblePositivo(textoPrecioServicio)) {
            
            JOptionPane.showMessageDialog(null, "Precio servicio introducido incorrectamente. Solo debe ser un numero decimal positivo.");
            
        } else if (textoDiasServicio.isEmpty() || !comprobarEnteroPositivo(textoDiasServicio)) {
            
            JOptionPane.showMessageDialog(null, "Cantidad dias de duracion del servicio introducido incorrectamente. Solo debe ser un numero entero positivo.");
            
        } else {

            String nombreServicio=textoNombreServicio;
            double precioServicio=Double.parseDouble(textoPrecioServicio.replace(",","."));
            int diasProducto=Integer.parseInt(textoDiasServicio);
            
            altaServicio(nombreServicio, precioServicio, diasProducto);

            jDialog_AltaServicio.setVisible(false);
            jDialog_OperacionesServicioAdministrador.setVisible(true);
            
            jTextField24.setText("");
            jTextField25.setText("");
            jTextField26.setText("");
            
        }
        
        
    }//GEN-LAST:event_jButton63MouseClicked

    private void jButton63MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton63MouseEntered
        // TODO add your handling code here:
        
        this.jButton63.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton63MouseEntered

    private void jButton63MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton63MouseExited
        // TODO add your handling code here:
        
        jButton63.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton63MouseExited

    private void jButton63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton63ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton63ActionPerformed

    private void jButton64MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton64MouseClicked
        // TODO add your handling code here:
        
        jDialog_AltaServicio.setVisible(false);
        jDialog_OperacionesServicioAdministrador.setVisible(true);
        
    }//GEN-LAST:event_jButton64MouseClicked

    private void jButton64MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton64MouseEntered
        // TODO add your handling code here:
        
        this.jButton64.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton64MouseEntered

    private void jButton64MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton64MouseExited
        // TODO add your handling code here:
        
        jButton64.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton64MouseExited

    private void jButton64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton64ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton64ActionPerformed

    private void jButton65MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton65MouseClicked
        // TODO add your handling code here:
        
        String textoNombreSala=jTextField28.getText();
        String textoPersonasSala=jTextField27.getText();
        String textoNombreMonitor=jComboBox17.getSelectedItem().toString();
        
        if(textoNombreSala.isEmpty() || !comprobarCadena(textoNombreSala)) {
            
           JOptionPane.showMessageDialog(null, "Nombre sala introducido incorrectamente. Solo debe contener caracteres.");

            
        } else if (textoPersonasSala.isEmpty() || !comprobarEnteroPositivo(textoPersonasSala)) {
            
            JOptionPane.showMessageDialog(null, "Capacidad de personas introducido incorrectamente. Solo debe ser un numero entero positivo.");
 
        } else if (Integer.parseInt(textoPersonasSala)<10) {
            
            JOptionPane.showMessageDialog(null, "Capacidad de personas introducido incorrectamente. Debe albergar mas de 10 personas.");  
            
            
        } else if (comprobarSalaExistente(textoNombreSala)) {
            
            JOptionPane.showMessageDialog(null, "La sala ya existe en el sistema");     
            
        } else {

            String nombreSala=textoNombreSala;
            int personasSala=Integer.parseInt(textoPersonasSala);
            String nombreMonitor=textoNombreMonitor;
            
            if(nombreMonitor.equals("Ninguno")) {
                
                nombreMonitor=null;
            }
            
            altaSala(nombreSala, personasSala, nombreMonitor);

            jDialog_AltaSala.setVisible(false);
            jDialog_OperacionesSalaAdministrador.setVisible(true);
            
            jTextField28.setText("");
            jTextField26.setText("");
            jComboBox17.setSelectedItem(0);
            
        }
        
    }//GEN-LAST:event_jButton65MouseClicked

    private void jButton65MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton65MouseEntered
        // TODO add your handling code here:
        
        this.jButton65.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton65MouseEntered

    private void jButton65MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton65MouseExited
        // TODO add your handling code here:
        
        jButton65.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton65MouseExited

    private void jButton65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton65ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton65ActionPerformed

    private void jButton66MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton66MouseClicked
        // TODO add your handling code here:
        
         jDialog_AltaSala.setVisible(false);
         jDialog_OperacionesSalaAdministrador.setVisible(true);
            
        
        
    }//GEN-LAST:event_jButton66MouseClicked

    private void jButton66MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton66MouseEntered
        // TODO add your handling code here:
        
        this.jButton66.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton66MouseEntered

    private void jButton66MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton66MouseExited
        // TODO add your handling code here:
        
        jButton66.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton66MouseExited

    private void jButton66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton66ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton66ActionPerformed

    private void jButton67MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton67MouseClicked
        // TODO add your handling code here:
        
        String textoNombreProducto=jTextField29.getText();
        String textoPrecioProducto=jTextField31.getText();
        String textoCantidadProducto=jTextField30.getText();
        
        if(textoNombreProducto.isEmpty() || !comprobarCadena(textoNombreProducto)) {
            
           JOptionPane.showMessageDialog(null, "Nombre producto introducido incorrectamente. Solo debe contener caracteres.");

            
        } else if (textoPrecioProducto.isEmpty() || !comprobarDoblePositivo(textoPrecioProducto)) {
            
            JOptionPane.showMessageDialog(null, "Precio producto introducido incorrectamente. Solo debe ser un numero decimal positivo.");
            
        } else if (textoCantidadProducto.isEmpty() || !comprobarEnteroPositivo(textoCantidadProducto)) {
            
            JOptionPane.showMessageDialog(null, "Cantidad producto introducido incorrectamente. Solo debe ser un numero entero positivo.");
            
        } else {

            String nombreProducto=textoNombreProducto;
            double precioProducto=Double.parseDouble(textoPrecioProducto.replace(",","."));
            int cantidadProducto=Integer.parseInt(textoCantidadProducto);
            
            modificarProducto(nombreProducto, precioProducto, cantidadProducto);
            
            jDialog_ModificarProducto.setVisible(false);
            jDialog_ConsultaProducto.setVisible(true);
            
            pintarTablaProductos();
            
        }
        
        
    }//GEN-LAST:event_jButton67MouseClicked

    private void jButton67MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton67MouseEntered
        // TODO add your handling code here:
        
        this.jButton67.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton67MouseEntered

    private void jButton67MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton67MouseExited
        // TODO add your handling code here:
        
        jButton67.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton67MouseExited

    private void jButton67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton67ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton67ActionPerformed

    private void jButton68MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton68MouseClicked
        // TODO add your handling code here:
        
        jDialog_ModificarProducto.setVisible(false);
        jDialog_ConsultaProducto.setVisible(true);
        
    }//GEN-LAST:event_jButton68MouseClicked

    private void jButton68MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton68MouseEntered
        // TODO add your handling code here:
        
        this.jButton68.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton68MouseEntered

    private void jButton68MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton68MouseExited
        // TODO add your handling code here:
        
        jButton68.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton68MouseExited

    private void jButton68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton68ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton68ActionPerformed

    private void jLabel_ReiniciarBusquedaProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ReiniciarBusquedaProductoMouseClicked
        // TODO add your handling code here:
        
        pintarTablaProductos();
        jTextField32.setText("");
        jComboBox18.setSelectedItem(0);
        jComboBox19.setSelectedItem(0);
        
    }//GEN-LAST:event_jLabel_ReiniciarBusquedaProductoMouseClicked

    private void jLabel_BuscarProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_BuscarProductoMouseClicked
        // TODO add your handling code here:
        
        
        String nombreProductoBuscar=jTextField32.getText();
        
        String tipoPrecioBuscar=jComboBox19.getSelectedItem().toString();
        
        String tipoCantidadBuscar=jComboBox18.getSelectedItem().toString();
        
        filtrarBusquedaProductos(nombreProductoBuscar, tipoPrecioBuscar, tipoCantidadBuscar);
        
    }//GEN-LAST:event_jLabel_BuscarProductoMouseClicked

    private void jButton69MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton69MouseClicked
        // TODO add your handling code here:
        
       jDialog_OperacionesProductoAdministrador.setVisible(true);
       
       jDialog_ConsultaProducto.setVisible(false);
        
    }//GEN-LAST:event_jButton69MouseClicked

    private void jButton69MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton69MouseEntered
        // TODO add your handling code here:
        
        jButton69.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton69MouseEntered

    private void jButton69MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton69MouseExited
        // TODO add your handling code here:
        
         jButton69.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton69MouseExited

    private void jButton69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton69ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton69ActionPerformed

    private void jButton70MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton70MouseClicked
        // TODO add your handling code here:
        
        String textoNombreServicio=jTextField33.getText();
        String textoPrecioServicio=jTextField35.getText();
        String textoDiasServicio=jTextField34.getText();
        
        if(textoNombreServicio.isEmpty() || !comprobarCadena(textoNombreServicio)) {
            
           JOptionPane.showMessageDialog(null, "Nombre servicio introducido incorrectamente. Solo debe contener caracteres.");

            
        } else if (textoPrecioServicio.isEmpty() || !comprobarDoblePositivo(textoPrecioServicio)) {
            
            JOptionPane.showMessageDialog(null, "Precio servicio introducido incorrectamente. Solo debe ser un numero decimal positivo.");
            
        } else if (textoDiasServicio.isEmpty() || !comprobarEnteroPositivo(textoDiasServicio)) {
            
            JOptionPane.showMessageDialog(null, "Cantidad dias de duracion del servicio introducido incorrectamente. Solo debe ser un numero entero positivo.");
            
        } else {

            String nombreServicio=textoNombreServicio;
            double precioServicio=Double.parseDouble(textoPrecioServicio.replace(",","."));
            int diasProducto=Integer.parseInt(textoDiasServicio);
            
            modificarServicio(nombreServicio, precioServicio, diasProducto);

            jDialog_ModificarServicio.setVisible(false);
            jDialog_ConsultaServicio.setVisible(true);
            
            jTextField24.setText("");
            jTextField25.setText("");
            jTextField26.setText("");
            
            pintarTablaServicios();
            
        }
        
        
        
        
    }//GEN-LAST:event_jButton70MouseClicked

    private void jButton70MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton70MouseEntered
        // TODO add your handling code here:
        
       
        jButton70.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton70MouseEntered

    private void jButton70MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton70MouseExited
        // TODO add your handling code here:
        
        jButton70.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton70MouseExited

    private void jButton70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton70ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton70ActionPerformed

    private void jButton71MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton71MouseClicked
        // TODO add your handling code here:
        
        jDialog_ConsultaServicio.setVisible(true);
        jDialog_ModificarServicio.setVisible(false);
        
    }//GEN-LAST:event_jButton71MouseClicked

    private void jButton71MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton71MouseEntered
        // TODO add your handling code here:
        
        jButton71.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton71MouseEntered

    private void jButton71MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton71MouseExited
        // TODO add your handling code here:
        
        jButton71.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton71MouseExited

    private void jButton71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton71ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton71ActionPerformed

    private void jLabel_ReiniciarBusquedaServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ReiniciarBusquedaServicioMouseClicked
        // TODO add your handling code here:
        
        pintarTablaServicios();
        jTextField36.setText("");
        jComboBox20.setSelectedItem(0);
        jComboBox21.setSelectedItem(0);
        
    }//GEN-LAST:event_jLabel_ReiniciarBusquedaServicioMouseClicked

    private void jLabel_BuscarServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_BuscarServicioMouseClicked
        // TODO add your handling code here:
        
        String nombreServicioBuscar=jTextField36.getText();
        String tipoPrecioBuscar=jComboBox21.getSelectedItem().toString();
        String tipoDiasBuscar=jComboBox20.getSelectedItem().toString();
        
        filtrarBusquedaServicios(nombreServicioBuscar, tipoPrecioBuscar, tipoDiasBuscar);
        
    }//GEN-LAST:event_jLabel_BuscarServicioMouseClicked

    private void jButton72MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton72MouseClicked
        // TODO add your handling code here:
        
        jDialog_OperacionesServicioAdministrador.setVisible(true);
        jDialog_ConsultaServicio.setVisible(false);
        
    }//GEN-LAST:event_jButton72MouseClicked

    private void jButton72MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton72MouseEntered
        // TODO add your handling code here:
        
        
        jButton72.setForeground(new Color(0x5CBCE0));
        
        
    }//GEN-LAST:event_jButton72MouseEntered

    private void jButton72MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton72MouseExited
        // TODO add your handling code here:
        
        jButton72.setForeground(Color.white);
        
        
    }//GEN-LAST:event_jButton72MouseExited

    private void jButton72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton72ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton72ActionPerformed

    private void jButton73MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton73MouseClicked
        // TODO add your handling code here:
        
        String textoNombreSala=jTextField38.getText();
        String textoPersonasSala=jTextField37.getText();
        String textoNombreMonitor=jComboBox22.getSelectedItem().toString();
        
        if(textoNombreSala.isEmpty() || !comprobarCadena(textoNombreSala)) {
            
           JOptionPane.showMessageDialog(null, "Nombre sala introducido incorrectamente. Solo debe contener caracteres.");

            
        } else if (textoPersonasSala.isEmpty() || !comprobarEnteroPositivo(textoPersonasSala)) {
            
            JOptionPane.showMessageDialog(null, "Capacidad de personas introducido incorrectamente. Solo debe ser un numero entero positivo.");
 
        } else if (Integer.parseInt(textoPersonasSala)<10) {
            
            JOptionPane.showMessageDialog(null, "Capacidad de personas introducido incorrectamente. Debe albergar mas de 10 personas.");  
            
            
        } else if (comprobarSalaExistente(textoNombreSala)) {
            
            JOptionPane.showMessageDialog(null, "La sala ya existe en el sistema");     
            
        } else {

            String nombreSala=textoNombreSala;
            int personasSala=Integer.parseInt(textoPersonasSala);
            String nombreMonitor=textoNombreMonitor;
            
            if(nombreMonitor.equals("Ninguno")) {
                
                nombreMonitor=null;
            }
            
            modificarSala(nombreSala, personasSala, nombreMonitor);

            pintarTablaSalas();
            
            jDialog_ModificarSala.setVisible(false);
            jDialog_ConsultaSala.setVisible(true);
           
        }
        
    }//GEN-LAST:event_jButton73MouseClicked

    private void jButton73MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton73MouseEntered
        // TODO add your handling code here:
        
        jButton73.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton73MouseEntered

    private void jButton73MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton73MouseExited
        // TODO add your handling code here:
        
        jButton73.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton73MouseExited

    private void jButton73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton73ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton73ActionPerformed

    private void jButton74MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton74MouseClicked
        // TODO add your handling code here:
        
        
        jDialog_ConsultaSala.setVisible(true);
        jDialog_ModificarSala.setVisible(false);
        
    }//GEN-LAST:event_jButton74MouseClicked

    private void jButton74MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton74MouseEntered
        // TODO add your handling code here:
        
        jButton74.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton74MouseEntered

    private void jButton74MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton74MouseExited
        // TODO add your handling code here:
        
        jButton74.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton74MouseExited

    private void jButton74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton74ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton74ActionPerformed

    private void jLabel_ReiniciarBusquedaSalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ReiniciarBusquedaSalaMouseClicked
        // TODO add your handling code here:
        
        
        pintarTablaSalas();
        
        jTextField39.setText("");
        jComboBox24.setSelectedItem(0);
        jComboBox23.setSelectedItem(0);
        
    }//GEN-LAST:event_jLabel_ReiniciarBusquedaSalaMouseClicked

    private void jLabel_BuscarSalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_BuscarSalaMouseClicked
        // TODO add your handling code here:
        
        String nombreSalaBuscar=jTextField39.getText();
        String tipoCapacidadBuscar=jComboBox24.getSelectedItem().toString();
        String nombreMonitorBuscar=jComboBox23.getSelectedItem().toString();
        
        filtrarBusquedaSalas(nombreSalaBuscar, tipoCapacidadBuscar, nombreMonitorBuscar);
        
    }//GEN-LAST:event_jLabel_BuscarSalaMouseClicked

    private void jButton75MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton75MouseClicked
        // TODO add your handling code here:
        
        jDialog_ConsultaSala.setVisible(false);
        jDialog_OperacionesSalaAdministrador.setVisible(true);
        
    }//GEN-LAST:event_jButton75MouseClicked

    private void jButton75MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton75MouseEntered
        // TODO add your handling code here:
        
        
        jButton75.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton75MouseEntered

    private void jButton75MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton75MouseExited
        // TODO add your handling code here:
        
        jButton75.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton75MouseExited

    private void jButton75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton75ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton75ActionPerformed

    private void jLabel_ReiniciarBusquedaVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ReiniciarBusquedaVentaMouseClicked
        // TODO add your handling code here:
        
        pintarTablaVentas();
        jTextField40.setText("");
        jComboBox25.setSelectedItem(0);
        jComboBox26.setSelectedItem(0);
        
        
    }//GEN-LAST:event_jLabel_ReiniciarBusquedaVentaMouseClicked

    private void jLabel_BuscarVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_BuscarVentaMouseClicked
        // TODO add your handling code here:
        
        
        String productoBuscarVenta=jTextField40.getText();
        
        String fechaBuscarVenta=jComboBox26.getSelectedItem().toString();
        
        String clienteBuscarCliente=jComboBox25.getSelectedItem().toString();
        
        filtrarBusquedaVentas(productoBuscarVenta, fechaBuscarVenta, clienteBuscarCliente);
        
        
    }//GEN-LAST:event_jLabel_BuscarVentaMouseClicked

    private void jButton76MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton76MouseClicked
        // TODO add your handling code here:
        
        jDialog_OperacionesProductoAdministrador.setVisible(true);
        jDialog_ConsultaVenta.setVisible(false);
        
        
    }//GEN-LAST:event_jButton76MouseClicked

    private void jButton76MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton76MouseEntered
        // TODO add your handling code here:
        
        jButton76.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton76MouseEntered

    private void jButton76MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton76MouseExited
        // TODO add your handling code here:
        
        jButton76.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton76MouseExited

    private void jButton76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton76ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton76ActionPerformed

    private void jComboBox25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox25ActionPerformed

    private void jLabel_ReiniciarBusquedaReservaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ReiniciarBusquedaReservaMouseClicked
        // TODO add your handling code here:
        
        pintarTablaReservas();
        jComboBox27.setSelectedIndex(0);
        jComboBox28.setSelectedIndex(0);
        jComboBox29.setSelectedIndex(0);
        jComboBox30.setSelectedIndex(0);
        
        
        
    }//GEN-LAST:event_jLabel_ReiniciarBusquedaReservaMouseClicked

    private void jLabel_BuscarReservaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_BuscarReservaMouseClicked
        // TODO add your handling code here:
        
        String salaBuscarReserva=jComboBox28.getSelectedItem().toString();
        String tipoFechaBuscarReserva=jComboBox30.getSelectedItem().toString();
        String tipoTurnoBuscarReserva=jComboBox29.getSelectedItem().toString();
        String clienteBuscarReserva=jComboBox27.getSelectedItem().toString();
        
        filtrarBusquedaReservas(salaBuscarReserva, tipoFechaBuscarReserva, tipoTurnoBuscarReserva, clienteBuscarReserva);
        
    }//GEN-LAST:event_jLabel_BuscarReservaMouseClicked

    private void jComboBox27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox27ActionPerformed

    private void jButton77MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton77MouseClicked
        // TODO add your handling code here:
        
        jDialog_ConsultaReserva.setVisible(false);
        jDialog_OperacionesSalaAdministrador.setVisible(true);
        
        
    }//GEN-LAST:event_jButton77MouseClicked

    private void jButton77MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton77MouseEntered
        // TODO add your handling code here:
        
        jButton77.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton77MouseEntered

    private void jButton77MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton77MouseExited
        // TODO add your handling code here:
        
        jButton77.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton77MouseExited

    private void jButton77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton77ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton77ActionPerformed

    private void jComboBox28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox28ActionPerformed

    private void jLabel_ReiniciarBusquedaPagosServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ReiniciarBusquedaPagosServicioMouseClicked
        // TODO add your handling code here:
        
        pintarTablaPagosServicio();
        
        jComboBox32.setSelectedIndex(0);
        jComboBox31.setSelectedIndex(0);
        jComboBox34.setSelectedIndex(0);
        
        
    }//GEN-LAST:event_jLabel_ReiniciarBusquedaPagosServicioMouseClicked

    private void jLabel_BuscarPagosServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_BuscarPagosServicioMouseClicked
        // TODO add your handling code here:
        
        String nombreServicio=jComboBox32.getSelectedItem().toString();
        String nombreCliente=jComboBox31.getSelectedItem().toString();
        String tipoFecha=jComboBox34.getSelectedItem().toString();
        
        filtrarBusquedaPagosServicio(nombreServicio, tipoFecha, nombreCliente);
        
    }//GEN-LAST:event_jLabel_BuscarPagosServicioMouseClicked

    private void jComboBox31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox31ActionPerformed

    private void jComboBox32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox32ActionPerformed

    private void jButton78MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton78MouseClicked
        // TODO add your handling code here:
        
        jDialog_OperacionesServicioAdministrador.setVisible(true);
        jDialog_ConsultaPagosServicios.setVisible(false);
        
        
    }//GEN-LAST:event_jButton78MouseClicked

    private void jButton78MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton78MouseEntered
        // TODO add your handling code here:
        
        jButton78.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton78MouseEntered

    private void jButton78MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton78MouseExited
        // TODO add your handling code here:
        
        jButton78.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton78MouseExited

    private void jButton78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton78ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton78ActionPerformed

    private void jLabel157MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel157MouseClicked
        // TODO add your handling code here:
        
        cerrarSesion();
        
    }//GEN-LAST:event_jLabel157MouseClicked

    private void jLabel157MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel157MouseEntered
        // TODO add your handling code here:
        
        this.jLabel157.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel157MouseEntered

    private void jLabel157MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel157MouseExited
        // TODO add your handling code here:
        
        this.jLabel157.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel157MouseExited

    private void jLabel_FCC_ClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FCC_ClienteMouseClicked
        // TODO add your handling code here:
        
        
        if(comprobarHayCuentaBancaria()) {
           
           ocultarBotonesHayCuenta();
           
       } else {
           
           ocultarBotonesSinCuenta();
       }
        
        reiniciarOperacionesSaldo();
        irCuentaBancaria();
        
        
    }//GEN-LAST:event_jLabel_FCC_ClienteMouseClicked

    private void jLabel_FG_ClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_FG_ClienteMouseClicked
        // TODO add your handling code here:
        
        jButton_PU_AccederPerCliente.setVisible(true);
        jButton_PU_AccederPerCliente.setEnabled(true);
        
        jDialog_FuncionalidadCliente.setVisible(false);
        jDialog_PerfilUsuario.setVisible(true);
        reiniciarPerfilUsuario();
        
        
    }//GEN-LAST:event_jLabel_FG_ClienteMouseClicked

    private void jLabel_NU_ClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_NU_ClienteMouseClicked
        // TODO add your handling code here:
        
        jButton_PU_AccederPerCliente.setVisible(true);
        jButton_PU_AccederPerCliente.setEnabled(true);
        
        jDialog_FuncionalidadCliente.setVisible(false);
        jDialog_PerfilUsuario.setVisible(true);
        reiniciarPerfilUsuario();
        
    }//GEN-LAST:event_jLabel_NU_ClienteMouseClicked

    private void jLabel_NU_ClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_NU_ClienteMouseEntered
        // TODO add your handling code here:
        
        this.jLabel_NU_Cliente.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel_NU_ClienteMouseEntered

    private void jLabel_NU_ClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_NU_ClienteMouseExited
        // TODO add your handling code here:
        
        this.jLabel_NU_Cliente.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel_NU_ClienteMouseExited

    private void jLabel159MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel159MouseClicked
        // TODO add your handling code here:

        verificarExpiracionServicios();
        
        if(!comprobarServicioPagado("Consulta Rutina")) {
            
            JOptionPane.showMessageDialog(null, "No puedes consultar la rutina asignada porque no has pagado el servicio Consulta Rutina.");

            return;
            
        } else {
            
            mostrarRutinaClienteAsignada();
            
        }
        
        
        
    }//GEN-LAST:event_jLabel159MouseClicked

    private void jLabel159MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel159MouseEntered
        // TODO add your handling code here:
        
        jLabel159.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel159MouseEntered

    private void jLabel159MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel159MouseExited
        // TODO add your handling code here:
        
        jLabel159.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel159MouseExited

    private void jLabel160MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel160MouseClicked
        // TODO add your handling code here:
        
        verificarExpiracionServicios();
        
        if(!comprobarServicioPagado(SERVICIO_RESERVA) && !comprobarServicioPagado(SERVICIO_RESERVA_MONITOR)) {
            
            JOptionPane.showMessageDialog(null, "No puedes realizar reservas porque no has pagado el servicio Gimnasio, ni el servicio Entrenamiento Monitoreado.");
 
            return;
            
        } else {
            
            if (comprobarCierreReserva()) {
            
                JOptionPane.showMessageDialog(null, "No puedes realizar reservas entre las 22:00 y las 23:59. Vuelve mañana para realizar reservas.");
 
            } else if(!comprobarSalasSinMonitor() && comprobarServicioPagado(SERVICIO_RESERVA) || (!comprobarSalasConMonitor() && comprobarServicioPagado(SERVICIO_RESERVA_MONITOR))) {  
                
                JOptionPane.showMessageDialog(null, "En estos momentos no existen salas para reservar con este servicio. Espere unos minutos, perdonen las molestias.");
                
            } else {
                
                jDialog_OperacionesReservasCliente.setVisible(true);
        
                jDialog_FuncionalidadCliente.setVisible(false);
            
            }
            
        }
        
        
         
        
    }//GEN-LAST:event_jLabel160MouseClicked

    // En este método, comprobamos que hay productos con unidades sufientes para ser mostrados en la tabla donde
    // el cliente realiza sus determinados pagos por ellos, si no hay no se mostraría en la tabla.
    private boolean hayProductosDisponibles() {
        
        boolean hayProductos = false;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            
            String query = "SELECT COUNT(*) AS cantidad_productos FROM gym_zone.productos WHERE cantidad_disponible > 0;";

            st = con.prepareStatement(query);
            rs = st.executeQuery();

            if (rs.next()) {
                int cantidadProductos = rs.getInt("cantidad_productos");
                if (cantidadProductos > 0) {
                    hayProductos = true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hayProductos;
    }
    
    // En este método, comprobamos si hay salas sin monitor asignados para que pueda reservar o no el cliente.
    // En el caso de que no hubiese, se le notificaría al cliente.
    private boolean comprobarSalasSinMonitor() {
        
        boolean haySalasSinMonitor = false;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
        
            String query = "SELECT DISTINCT id_sala FROM gym_zone.salas WHERE id_monitor IS NULL;";

            st = con.prepareStatement(query);
            rs = st.executeQuery();

            if (rs.next()) {
                haySalasSinMonitor = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } 

        return haySalasSinMonitor;
    }
    
    // En este método, comprobamos si hay salas con monitor asignados para que pueda reservar o no el cliente.
    // En el caso de que no hubiese, se le notificaría al cliente.
    private boolean comprobarSalasConMonitor() {
        
        boolean haySalasConMonitor = false;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            
            String query = "SELECT DISTINCT id_sala FROM gym_zone.salas WHERE id_monitor IS NOT NULL;";

            st = con.prepareStatement(query);
            rs = st.executeQuery();

            if (rs.next()) {
                haySalasConMonitor = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return haySalasConMonitor;
    }
    
    
    
    private void jLabel160MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel160MouseEntered
        // TODO add your handling code here:
        
        jLabel160.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel160MouseEntered

    private void jLabel160MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel160MouseExited
        // TODO add your handling code here:
        
        jLabel160.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel160MouseExited

    private void jButton79MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton79MouseClicked
        // TODO add your handling code here:
        
       verificarExpiracionServicios();
       
        if(!comprobarServicioPagado(SERVICIO_RESERVA) && !comprobarServicioPagado(SERVICIO_RESERVA_MONITOR)) {
            
            JOptionPane.showMessageDialog(null, "No puedes realizar reservas porque no has pagado el servicio Gimnasio, ni el servicio Entrenamiento Monitoreado.");
 
            return;
            
        } else {
            
            if (comprobarCierreReserva()) {
            
                JOptionPane.showMessageDialog(null, "No puedes realizar reservas entre las 22:00 y las 23:59. Vuelve mañana para realizar reservas.");
 
            } else if(!comprobarSalasSinMonitor() && comprobarServicioPagado(SERVICIO_RESERVA) || (!comprobarSalasConMonitor() && comprobarServicioPagado(SERVICIO_RESERVA_MONITOR))) {  
                
                JOptionPane.showMessageDialog(null, "En estos momentos no existen salas para reservar con este servicio. Espere unos minutos, perdonen las molestias.");
                
            } else {
                
                jDialog_OperacionesReservasCliente.setVisible(true);
        
                jDialog_FuncionalidadCliente.setVisible(false);
            
            }
            
        }
        
    }//GEN-LAST:event_jButton79MouseClicked

    private void jButton80MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton80MouseClicked
        // TODO add your handling code here:
        
        verificarExpiracionServicios();
        
        if(!comprobarServicioPagado("Consulta Rutina")) {
            
            JOptionPane.showMessageDialog(null, "No puedes consultar la rutina asignada porque no has pagado el servicio Consulta Rutina.");

            return;
            
        } else {
            
            mostrarRutinaClienteAsignada();
            
        }
        
        
        
    }//GEN-LAST:event_jButton80MouseClicked

    private void jButton81MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton81MouseClicked
        // TODO add your handling code here:
        
        if(comprobarHayCuentaBancaria()) {
            
            jDialog_OperacionesPagosCliente.setVisible(true);
            jDialog_FuncionalidadCliente.setVisible(false);
            
        } else {
            
            JOptionPane.showMessageDialog(null, "No dispones de cuenta bancaria para pagar los determinados servicios o productos");

        }
        
    }//GEN-LAST:event_jButton81MouseClicked

    private void jLabel161MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel161MouseClicked
        // TODO add your handling code here:
        
        if(comprobarHayCuentaBancaria()) {
            
            jDialog_OperacionesPagosCliente.setVisible(true);
            jDialog_FuncionalidadCliente.setVisible(false);
            
        } else {
            
            JOptionPane.showMessageDialog(null, "No dispones de cuenta bancaria para pagar los determinados servicios o productos");

        }
                
 
        
    }//GEN-LAST:event_jLabel161MouseClicked

    private void jLabel161MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel161MouseEntered
        // TODO add your handling code here:
        
        jLabel161.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel161MouseEntered

    private void jLabel161MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel161MouseExited
        // TODO add your handling code here:
        
        jLabel161.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel161MouseExited

    private void jButton82MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton82MouseClicked
        // TODO add your handling code here:
        
        verificarExpiracionServicios();

        if(!comprobarServicioPagado("Consulta Dieta")) {
            
            JOptionPane.showMessageDialog(null, "No puedes consultar la dieta asignada porque no has pagado el servicio Consulta Dieta.");

            return;
            
            
        } else {
            
            mostrarDietaClienteAsignada();
        }
        
    }//GEN-LAST:event_jButton82MouseClicked

    private void jLabel162MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel162MouseClicked
        // TODO add your handling code here:
        
        verificarExpiracionServicios();

        if(!comprobarServicioPagado("Consulta Dieta")) {
            
            JOptionPane.showMessageDialog(null, "No puedes consultar la dieta asignada porque no has pagado el servicio Consulta Dieta.");

            return;
            
        } else {
            
            mostrarDietaClienteAsignada();
        }
        
    }//GEN-LAST:event_jLabel162MouseClicked

    private void jLabel162MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel162MouseEntered
        // TODO add your handling code here:
        
        jLabel162.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel162MouseEntered

    private void jLabel162MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel162MouseExited
        // TODO add your handling code here:
        
        jLabel162.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel162MouseExited

    private void jLabel163MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel163MouseClicked
        // TODO add your handling code here:
        
        jDialog_FuncionalidadCliente.setVisible(true);
        
        jDialog_OperacionesPagosCliente.setVisible(false);
        
    }//GEN-LAST:event_jLabel163MouseClicked

    private void jLabel163MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel163MouseEntered
        // TODO add your handling code here:
        
        
         jLabel163.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel163MouseEntered

    private void jLabel163MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel163MouseExited
        // TODO add your handling code here:
        
        jLabel163.setForeground(Color.black);
        
    }//GEN-LAST:event_jLabel163MouseExited

    private void jLabel_PagarProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_PagarProductoMouseClicked
        // TODO add your handling code here:
        
        if(!hayProductosDisponibles()) {
            
            JOptionPane.showMessageDialog(null, "No hay productos disponibles en estos momentos. Espere unos minutos, perdonen las molestias.");

        } else {
            
            jDialog_PagoProducto.setVisible(true);
            jDialog_OperacionesPagosCliente.setVisible(false);
        
            pintarTablaPagosProducto();
            
        }
        
        
        
    }//GEN-LAST:event_jLabel_PagarProductoMouseClicked

    private void jLabel_PagarProductoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_PagarProductoMouseEntered
        // TODO add your handling code here:
        
        jLabel_PagarProducto.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel_PagarProductoMouseEntered

    private void jLabel_PagarProductoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_PagarProductoMouseExited
        // TODO add your handling code here:
        
        
        jLabel_PagarProducto.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel_PagarProductoMouseExited

    private void jButton_PagarProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_PagarProductoMouseClicked
        // TODO add your handling code here:
        
        if(!hayProductosDisponibles()) {
            
            JOptionPane.showMessageDialog(null, "No hay productos disponibles en estos momentos. Espere unos minutos, perdonen las molestias.");

        } else {
            
            jDialog_PagoProducto.setVisible(true);
            jDialog_OperacionesPagosCliente.setVisible(false);
        
            pintarTablaPagosProducto();
            
        }
        
    }//GEN-LAST:event_jButton_PagarProductoMouseClicked

    private void jLabel_PagarServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_PagarServicioMouseClicked
        // TODO add your handling code here:
        
        pintarTablaServiciosCliente();
        jDialog_OperacionesPagosCliente.setVisible(false);
        jDialog_PagoServicio.setVisible(true);
        
    }//GEN-LAST:event_jLabel_PagarServicioMouseClicked

    private void jLabel_PagarServicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_PagarServicioMouseEntered
        // TODO add your handling code here:
        
        jLabel_PagarServicio.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel_PagarServicioMouseEntered

    private void jLabel_PagarServicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_PagarServicioMouseExited
        // TODO add your handling code here:
        
        jLabel_PagarServicio.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel_PagarServicioMouseExited

    private void jButton_PagarServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_PagarServicioMouseClicked
        // TODO add your handling code here:
        
        pintarTablaServiciosCliente();
        jDialog_OperacionesPagosCliente.setVisible(false);
        jDialog_PagoServicio.setVisible(true);
        
    }//GEN-LAST:event_jButton_PagarServicioMouseClicked

    private void jLabel_ReiniciarBusquedaPagarProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ReiniciarBusquedaPagarProductoMouseClicked
        // TODO add your handling code here:
        
        pintarTablaPagosProducto();
        
        jTextField41.setText("");
        jComboBox35.setSelectedIndex(0);
        
    }//GEN-LAST:event_jLabel_ReiniciarBusquedaPagarProductoMouseClicked

    private void jLabel_BuscarPagarProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_BuscarPagarProductoMouseClicked
        // TODO add your handling code here:
        
        String nombreProductoBuscar=jTextField41.getText();
        String tipoPrecio=jComboBox35.getSelectedItem().toString();
        
        filtrarBusquedaProductosCliente(nombreProductoBuscar, tipoPrecio);
        
    }//GEN-LAST:event_jLabel_BuscarPagarProductoMouseClicked

    private void jButton83MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton83MouseClicked
        // TODO add your handling code here:
        
        jDialog_PagoProducto.setVisible(false);
        jDialog_OperacionesPagosCliente.setVisible(true);
        
    }//GEN-LAST:event_jButton83MouseClicked

    private void jButton83MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton83MouseEntered
        // TODO add your handling code here:
        
        jButton83.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton83MouseEntered

    private void jButton83MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton83MouseExited
        // TODO add your handling code here:
        
        jButton83.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton83MouseExited

    private void jButton83ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton83ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton83ActionPerformed

    private void jLabel_ReiniciarBusquedaPagarServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_ReiniciarBusquedaPagarServicioMouseClicked
        // TODO add your handling code here:
        
        jTextField42.setText("");
        jComboBox36.setSelectedItem(0);
        
        pintarTablaServiciosCliente();
        
    }//GEN-LAST:event_jLabel_ReiniciarBusquedaPagarServicioMouseClicked

    private void jLabel_BuscarPagarServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_BuscarPagarServicioMouseClicked
        // TODO add your handling code here:
        
        String nombreServicioBuscar=jTextField42.getText();
        String tipoPrecioBuscar=jComboBox36.getSelectedItem().toString();
        
        filtrarBusquedaServiciosCliente(nombreServicioBuscar, tipoPrecioBuscar);
        
    }//GEN-LAST:event_jLabel_BuscarPagarServicioMouseClicked

    private void jButton84MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton84MouseClicked
        // TODO add your handling code here:
        
        jDialog_OperacionesPagosCliente.setVisible(true);
        jDialog_PagoServicio.setVisible(false);
        
    }//GEN-LAST:event_jButton84MouseClicked

    private void jButton84MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton84MouseEntered
        // TODO add your handling code here:
        
        jButton84.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton84MouseEntered

    private void jButton84MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton84MouseExited
        // TODO add your handling code here:
        
        jButton84.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton84MouseExited

    private void jButton84ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton84ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton84ActionPerformed

    private void jLabel168MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel168MouseClicked
        // TODO add your handling code here:
        
        jDialog_OperacionesReservasCliente.setVisible(false);
        jDialog_FuncionalidadCliente.setVisible(true);
        
    }//GEN-LAST:event_jLabel168MouseClicked

    private void jLabel168MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel168MouseEntered
        // TODO add your handling code here:
        
        jLabel168.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel168MouseEntered

    private void jLabel168MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel168MouseExited
        // TODO add your handling code here:
        
        jLabel168.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel168MouseExited

    private void jLabel_AltaReservaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AltaReservaMouseClicked
        // TODO add your handling code here:
       
        if(comprobarReservasDiasAnteriores()) {
                        
            borrarReservasDiasAnteriores();
        }
                
        if(comprobarCierreReserva()) {
            
            JOptionPane.showMessageDialog(null, "No puedes realizar reservas, ya que se ha terminado los turnos. \n Puedes realizar reservas mañana.");
            
        } else {
            
            if((comprobarReservaDia() || comprobarCierreReservaDia()) && (comprobarReservaTarde() || comprobarCierreReserva())) {
            
                JOptionPane.showMessageDialog(null, "Has realizado todas las reservas para hoy. Cancela alguna reserva realizada para crear una nueva reserva.");
            
            } else {
            
                jDialog_AltaReserva.setVisible(true);
                ocultarListasReservas();
                jDialog_OperacionesReservasCliente.setVisible(false);
                mostrarBotonesReserva();
            }
            
        }
        
        
    }//GEN-LAST:event_jLabel_AltaReservaMouseClicked

    // En esté metodo, comprobamos si ha pasado la posibilidad de realizar reservas durante el turno de mañana,
    // ya que la última hora en las franjas horarias es a las 14:00 que es cuando termina el turno de mañana.
    private boolean comprobarCierreReservaDia() {
        
        LocalTime horaActual = LocalTime.now();
        LocalTime horaLimite = LocalTime.of(14, 0);

        return horaActual.isAfter(horaLimite);
    }
    
    // En esté metodo, comprobamos si ha pasado la posibilidad de realizar reservas,
    // ya que la última hora en las franjas horarias es a las 22:00 que es cuando termina el turno de tarde.
    private boolean comprobarCierreReserva() {
        
        LocalTime horaActual = LocalTime.now();
        LocalTime horaInicio = LocalTime.of(22, 0);
        LocalTime horaFin = LocalTime.of(23, 59, 59);

        return !horaActual.isBefore(horaInicio) && !horaActual.isAfter(horaFin);
    }
    
    
    private void jLabel_AltaReservaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AltaReservaMouseEntered
        // TODO add your handling code here:
        
        jLabel_AltaReserva.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel_AltaReservaMouseEntered

    private void jLabel_AltaReservaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AltaReservaMouseExited
        // TODO add your handling code here:
        
        jLabel_AltaReserva.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel_AltaReservaMouseExited

    private void jButton_AltaReservaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_AltaReservaMouseClicked
        // TODO add your handling code here:

        if(comprobarReservasDiasAnteriores()) {
                        
            borrarReservasDiasAnteriores();
        }
                
        if(comprobarCierreReserva()) {
            
            JOptionPane.showMessageDialog(null, "No puedes realizar reservas, ya que se ha terminado los turnos. \n Puedes realizar reservas mañana.");
            
        } else {
            
            if((comprobarReservaDia() || comprobarCierreReservaDia()) && (comprobarReservaTarde() || comprobarCierreReserva())) {
            
                JOptionPane.showMessageDialog(null, "Has realizado todas las reservas para hoy. Cancela alguna reserva realizada para crear una nueva reserva.");
            
            } else {
            
                jDialog_AltaReserva.setVisible(true);
                ocultarListasReservas();
                jDialog_OperacionesReservasCliente.setVisible(false);
                mostrarBotonesReserva();
            }
            
        }
        
    }//GEN-LAST:event_jButton_AltaReservaMouseClicked

    private void jLabel_CancelarReservaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_CancelarReservaMouseClicked
        // TODO add your handling code here:
        
        if(comprobarReservasDiasAnteriores()) {
                        
            borrarReservasDiasAnteriores();
        }
        
        if(!hayReservaCliente(idUsuario)) {
            
            JOptionPane.showMessageDialog(null, "No has realizado ninguna reserva para hoy. Reliza alguna reserva para poder cancelarla.");

        } else {
            
            pintarTablaReservasCliente();
            
            jDialog_OperacionesReservasCliente.setVisible(false);
            jDialog_GestionReserva.setVisible(true);
            
        }
        
    }//GEN-LAST:event_jLabel_CancelarReservaMouseClicked

    // En esté metodo, comprobamos si el cliente ha realizado alguna reserva, para comprobar si puede o no gestionar las
    // reservas que haya realizado
    private boolean hayReservaCliente(int idCliente) {
        
        boolean hayReserva = false;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            // Consulta SQL para verificar si el cliente tiene alguna reserva
            String query = "SELECT COUNT(*) AS num_reservas FROM reservas WHERE id_cliente = ?";
            st = con.prepareStatement(query);
            st.setInt(1, idCliente);

            rs = st.executeQuery();

            if (rs.next()) {
                int numReservas = rs.getInt("num_reservas");
                hayReserva = (numReservas > 0);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return hayReserva;
    }
    
    private void jLabel_CancelarReservaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_CancelarReservaMouseEntered
        // TODO add your handling code here:
        
        jLabel_CancelarReserva.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel_CancelarReservaMouseEntered

    private void jLabel_CancelarReservaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_CancelarReservaMouseExited
        // TODO add your handling code here:
        
        jLabel_CancelarReserva.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel_CancelarReservaMouseExited

    private void jButton_CancelarReservaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_CancelarReservaMouseClicked
        // TODO add your handling code here:
        
        if(comprobarReservasDiasAnteriores()) {
                        
            borrarReservasDiasAnteriores();
        }
        
        if(!hayReservaCliente(idUsuario)) {
            
            JOptionPane.showMessageDialog(null, "No has realizado ninguna reserva para hoy. Reliza alguna reserva para poder cancelarla.");

        } else {
            
            pintarTablaReservasCliente();
            
            jDialog_OperacionesReservasCliente.setVisible(false);
            jDialog_GestionReserva.setVisible(true);
            
        }
        
        
    }//GEN-LAST:event_jButton_CancelarReservaMouseClicked

    private void jButton85MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton85MouseClicked
        // TODO add your handling code here:
        
        String nombreSala=jComboBox37.getSelectedItem().toString();
        String horaSeleccionada=jComboBox33.getSelectedItem().toString();
        
        LocalDate fechaHoy = LocalDate.now();
       
        String[] partesHora = horaSeleccionada.split(" - ");
    
        String horaInicioStr = partesHora[0].trim(); 
        String horaFinStr = partesHora[1].trim();    

        String[] partesInicio = horaInicioStr.split(":");
        String[] partesFin = horaFinStr.split(":");

        int horaInicio = Integer.parseInt(partesInicio[0]);
        int horaFin = Integer.parseInt(partesFin[0]);

        // Crear LocalDateTime según la franja horaria seleccionada
        LocalDateTime fechaHoraInicio = fechaHoy.atTime(horaInicio, 0, 0);
        LocalDateTime fechaHoraFin = fechaHoy.atTime(horaFin, 0, 0);
        
        
        int idSalaReserva=devolverIdSala(nombreSala);
        
        
        if(!comprobarHayReserva(idSalaReserva, fechaHoraInicio, fechaHoraFin)) {
            
            
            if(comprobarRealizarReserva(idSalaReserva, fechaHoraInicio, fechaHoraFin)) {
            
                insertarReserva(idSalaReserva, fechaHoraInicio, fechaHoraFin);

                jDialog_AltaReserva.setVisible(false);
                jDialog_OperacionesReservasCliente.setVisible(true);

                jLabel_AltaReservaDia.setVisible(true);
                jLabel_AltaReservaDia.setEnabled(true);
                jButton_AltaReservaDia.setVisible(true);
                jButton_AltaReservaDia.setEnabled(true);
                jLabel_AltaReservaTarde.setVisible(true);
                jLabel_AltaReservaTarde.setEnabled(true);
                jButton_AltaReservaTarde.setVisible(true);
                jButton_AltaReservaTarde.setEnabled(true);
            
            } else {

                 JOptionPane.showMessageDialog(null, "No se puede realizar la reserva, la sala esta llena a la hora seleccionada.\n"
                         + "Selecciona otra franja horaria o sala para realizarla correctamente.");
            }
            
            
        } else {
            
            JOptionPane.showMessageDialog(null, "No se puede realizar la reserva, existe ya la reserva a realizar.\n"
                         + "Selecciona otra franja horaria o sala para realizarla correctamente o vuelve a reservarla.");
            
        }
            
        
        
        
        
        
    }//GEN-LAST:event_jButton85MouseClicked

    // En este método, comprobamos si el cliente ha realizado o no una reserva en una sala y franja horaria específica
    // donde notificaremos al cliente de que no puede realizar la misma reserva, deberá volver a reservarla en gestión de reservas.
    private boolean comprobarHayReserva(int idSalaReserva, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        
        PreparedStatement st;
        ResultSet rs;
        boolean hayReserva = false;

        try {
            String query = "SELECT id_cliente FROM reservas " +
                           "WHERE id_sala = ? " +
                           "AND ((fecha_entrada < ? AND fecha_salida > ?) " +  // Inicio en medio de una reserva existente
                           "OR (fecha_entrada < ? AND fecha_salida > ?) " +  // Fin en medio de una reserva existente
                           "OR (fecha_entrada >= ? AND fecha_salida <= ?))"; // Intervalo completamente dentro de una reserva existente

            st = con.prepareStatement(query);
            st.setInt(1, idSalaReserva);
            st.setTimestamp(2, Timestamp.valueOf(fechaHoraFin));
            st.setTimestamp(3, Timestamp.valueOf(fechaHoraInicio));
            st.setTimestamp(4, Timestamp.valueOf(fechaHoraInicio));
            st.setTimestamp(5, Timestamp.valueOf(fechaHoraFin));
            st.setTimestamp(6, Timestamp.valueOf(fechaHoraInicio));
            st.setTimestamp(7, Timestamp.valueOf(fechaHoraFin));

            rs = st.executeQuery();

            if (rs.next()) {
                hayReserva = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hayReserva;

    }
    
    // En este método, comprobamos si el cliente puede realizar la reserva con la sala a la franja horaria deseada, ya que
    // se verifica que la sala no supere el límite de la capacidad que puede albergar.
    private boolean comprobarRealizarReserva(int idSalaReserva, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        
        boolean reservaDisponible = false;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            // Consulta para contar las reservas que coinciden con los parámetros
            String query = "SELECT COUNT(*) AS num_reservas " +
                           "FROM reservas " +
                           "WHERE id_sala = ? " +
                           "AND estado = 'Realizada' " +
                           "AND fecha_entrada = ? " +
                           "AND fecha_salida = ?";

            st = con.prepareStatement(query);
            st.setInt(1, idSalaReserva);
            st.setTimestamp(2, Timestamp.valueOf(fechaHoraInicio)); 
            st.setTimestamp(3, Timestamp.valueOf(fechaHoraFin));

            rs = st.executeQuery();

            if (rs.next()) {
                int numReservas = rs.getInt("num_reservas");

                // Obtener la capacidad de personas de la sala
                int capacidadPersonas = obtenerCapacidadSala(idSalaReserva);

                // Verificar si la reserva es posible comparando con la capacidad de la sala
                if (numReservas < capacidadPersonas) {
                    reservaDisponible = true;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } 

        return reservaDisponible;
    }
    
    // En este método, obtenemos la capacidad de cualquier sala para realizar la validación del anterior método 
    // para realizar la reserva.
    private int obtenerCapacidadSala(int idSala) {
        
        int capacidad = 0;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String query = "SELECT capacidad_personas FROM salas WHERE id_sala = ?";
            st = con.prepareStatement(query);
            st.setInt(1, idSala);

            rs = st.executeQuery();

            if (rs.next()) {
                capacidad = rs.getInt("capacidad_personas");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return capacidad;
    }
    
    private void jButton85MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton85MouseEntered
        // TODO add your handling code here:
        
        jButton85.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton85MouseEntered

    private void jButton85MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton85MouseExited
        // TODO add your handling code here:
        
        jButton85.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton85MouseExited

    private void jButton85ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton85ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton85ActionPerformed

    private void jButton86MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton86MouseClicked
        // TODO add your handling code here:
        
        jDialog_OperacionesReservasCliente.setVisible(true);
        jDialog_AltaReserva.setVisible(false);
        jLabel_AltaReservaDia.setVisible(true);
        jLabel_AltaReservaDia.setEnabled(true);
        jButton_AltaReservaDia.setVisible(true);
        jButton_AltaReservaDia.setEnabled(true);
        jLabel_AltaReservaTarde.setVisible(true);
        jLabel_AltaReservaTarde.setEnabled(true);
        jButton_AltaReservaTarde.setVisible(true);
        jButton_AltaReservaTarde.setEnabled(true);
        
    }//GEN-LAST:event_jButton86MouseClicked

    private void jButton86MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton86MouseEntered
        // TODO add your handling code here:
        
        jButton86.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton86MouseEntered

    private void jButton86MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton86MouseExited
        // TODO add your handling code here:
        
         jButton86.setForeground(Color.white);
        
    }//GEN-LAST:event_jButton86MouseExited

    private void jButton86ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton86ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton86ActionPerformed

    private void jButton_AltaReservaTardeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_AltaReservaTardeMouseClicked
        // TODO add your handling code here:
        
        mostrarListasReservas();
        
        mostrarListaHorasTarde(jComboBox33);
        
        mostrarListaSalasReserva(jComboBox37);
        
        ocultarBotonesAltaReservas();
        
    }//GEN-LAST:event_jButton_AltaReservaTardeMouseClicked

    private void jButton_AltaReservaDiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_AltaReservaDiaMouseClicked
        // TODO add your handling code here:
        
        mostrarListasReservas();
        
        mostrarListaHorasDia(jComboBox33);
        
        mostrarListaSalasReserva(jComboBox37);
        
        ocultarBotonesAltaReservas();
        
    }//GEN-LAST:event_jButton_AltaReservaDiaMouseClicked

    private void jLabel_AltaReservaDiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AltaReservaDiaMouseClicked
        // TODO add your handling code here:
        
        mostrarListasReservas();
        
        mostrarListaHorasDia(jComboBox33);
        
        mostrarListaSalasReserva(jComboBox37);
        
        ocultarBotonesAltaReservas();
        
        
        
    }//GEN-LAST:event_jLabel_AltaReservaDiaMouseClicked

    // Este método, ocultamos el formulario para realizar la reserva, ya que primero tendriamos que elegir
    // el turno de mañana o tarde para mostrar posteriormente sus salas y franjas horarias.
    private void ocultarListasReservas() {
        
        jLabel172.setVisible(false);
        jLabel173.setVisible(false);
        
        jLabel172.setEnabled(false);
        jLabel173.setEnabled(false);
        
        jComboBox33.setVisible(false);
        jComboBox37.setVisible(false);
        
        jComboBox33.setEnabled(false);
        jComboBox37.setEnabled(false);
        
    }
    
    // Este método, mostramos el formulario para realizar la reserva, después de elegir el turno de mañana o tarde.
    private void mostrarListasReservas() {
        
        jLabel172.setVisible(true);
        jLabel173.setVisible(true);
        
        jLabel172.setEnabled(true);
        jLabel173.setEnabled(true);
        
        jComboBox33.setVisible(true);
        jComboBox37.setVisible(true);
        
        jComboBox33.setEnabled(true);
        jComboBox37.setEnabled(true);
        
    }
    
    private void jLabel_AltaReservaDiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AltaReservaDiaMouseEntered
        // TODO add your handling code here:
        
        jLabel_AltaReservaDia.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel_AltaReservaDiaMouseEntered

    private void jLabel_AltaReservaDiaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AltaReservaDiaMouseExited
        // TODO add your handling code here:
        
        jLabel_AltaReservaDia.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel_AltaReservaDiaMouseExited

    private void jLabel_AltaReservaTardeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AltaReservaTardeMouseClicked
        // TODO add your handling code here:
        
        mostrarListasReservas();
        
        mostrarListaHorasTarde(jComboBox33);
        
        mostrarListaSalasReserva(jComboBox37);
        
       ocultarBotonesAltaReservas();
        
        
    }//GEN-LAST:event_jLabel_AltaReservaTardeMouseClicked

    // En este metodo, ocultamos los botones para elegir el turno de mañana o tarde, ya que el cliente ya lo ha seleccionado.
    private void ocultarBotonesAltaReservas() {
        
        jLabel_AltaReservaDia.setVisible(false);
        jLabel_AltaReservaDia.setEnabled(false);
        
        jButton_AltaReservaDia.setVisible(false);
        jButton_AltaReservaDia.setEnabled(false);
        
        jLabel_AltaReservaTarde.setVisible(false);
        jLabel_AltaReservaTarde.setEnabled(false);
        
        jButton_AltaReservaTarde.setVisible(false);
        jButton_AltaReservaTarde.setEnabled(false);
        
        jButton85.setVisible(true);
        jButton85.setEnabled(true);
        
    }
    
    private void jLabel_AltaReservaTardeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AltaReservaTardeMouseEntered
        // TODO add your handling code here:
        
        jLabel_AltaReservaTarde.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jLabel_AltaReservaTardeMouseEntered

    private void jLabel_AltaReservaTardeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_AltaReservaTardeMouseExited
        // TODO add your handling code here:
        
        jLabel_AltaReservaTarde.setForeground(Color.white);
        
    }//GEN-LAST:event_jLabel_AltaReservaTardeMouseExited

    private void jButton_AltaReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AltaReservaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_AltaReservaActionPerformed

    private void jButton87MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton87MouseClicked
        // TODO add your handling code here:
        
        jDialog_GestionReserva.setVisible(false);
        
        jDialog_OperacionesReservasCliente.setVisible(true);
        
        
        
    }//GEN-LAST:event_jButton87MouseClicked

    private void jButton87MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton87MouseEntered
        // TODO add your handling code here:
        
       jButton87.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton87MouseEntered

    private void jButton87MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton87MouseExited
        // TODO add your handling code here:
        
       jButton87.setForeground(Color.white); 
        
    }//GEN-LAST:event_jButton87MouseExited

    private void jButton87ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton87ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton87ActionPerformed

    private void jButton_PU_AccederPerClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_PU_AccederPerClienteMouseClicked
        // TODO add your handling code here:
        
        jDialog_PerfilCliente.setVisible(true);
        jDialog_PerfilUsuario.setVisible(false);
        
        reiniciarPerfilCliente();
        
    }//GEN-LAST:event_jButton_PU_AccederPerClienteMouseClicked

    private void jButton_PU_AccederPerClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_PU_AccederPerClienteMouseEntered
        // TODO add your handling code here:
        
        jButton_PU_AccederPerCliente.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton_PU_AccederPerClienteMouseEntered

    private void jButton_PU_AccederPerClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_PU_AccederPerClienteMouseExited
        // TODO add your handling code here:
        
        jButton_PU_AccederPerCliente.setForeground(Color.white); 
        
    }//GEN-LAST:event_jButton_PU_AccederPerClienteMouseExited

    private void jButton_PU_AccederPerClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PU_AccederPerClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_PU_AccederPerClienteActionPerformed

    // En este método, modificamos el objetivo corporal del cliente según el objetivo seleccionado por él.
    private void editarObjetivoCorporal(String objetivoCorporalNuevo) {
        
        PreparedStatement st = null;
        
        try {
            String query = "UPDATE clientes SET objetivo_corporal = ? WHERE id_cliente = ?";

            st = con.prepareStatement(query);
            st.setString(1, objetivoCorporalNuevo);
            st.setInt(2, idUsuario); 

            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    // En este método, modificamos la altura nueva del cliente.
    private void editarAltura(int alturaNueva) {
        
        PreparedStatement st = null;
        
        try {
            String query = "UPDATE clientes SET altura = ? WHERE id_cliente = ?";

            st = con.prepareStatement(query);
            st.setInt(1, alturaNueva);
            st.setInt(2, idUsuario); 

            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
     // En este método, modificamos el peso nuevo del cliente.
    private void editarPeso(double pesoNuevo) {
        
        PreparedStatement st = null;
        
        try {
            String query = "UPDATE clientes SET peso = ? WHERE id_cliente = ?";

            st = con.prepareStatement(query);
            st.setDouble(1, pesoNuevo);
            st.setInt(2, idUsuario); 

            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private void jButton_PC_EditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_PC_EditarMouseClicked
        // TODO add your handling code here:
        
        
        String mensajeError="", campoBD="", campoEditar="";
        
        boolean campoValido=true;
        
        
        if(jLabel177.getText().equals("Introduzca el objetivo corporal nuevo:")) {
           
            campoEditar=jComboBox38.getSelectedItem().toString();
            
            if(devolverObjetivoCliente().equals(campoEditar)) {
                
                JOptionPane.showMessageDialog(null, "No puedes modificar el mismo objetivo corporal que tienes actualmente");

            } else {
                
                editarObjetivoCorporal(campoEditar);
                reiniciarPerfilCliente();
                
            }
            
            
        } else {

            campoEditar=jTextField43.getText();
        
            switch(jLabel177.getText()) {

                case "Introduzca el peso nuevo:" -> {

                    campoEditar=campoEditar.replace(',','.');
              
                    if(!comprobarDoblePositivo(campoEditar)) {

                        JOptionPane.showMessageDialog(null, "Peso nuevo introducido incorrectamente. El peso debe ser un numero decimal positivo");

                    } else if(devolverPesoCliente()==Double.parseDouble(campoEditar)) {

                        JOptionPane.showMessageDialog(null, "No puedes modificar el mismo peso que tienes actualmente");
                        
                    } else {

                        editarPeso(Double.parseDouble(campoEditar));
                        reiniciarPerfilCliente();

                    }

                }

                case "Introduzca la altura nueva:" -> {

                    if(!comprobarEnteroPositivo(campoEditar)) {

                        JOptionPane.showMessageDialog(null, "Altura nueva introducida incorrectamente. La altura debe ser un numero entero positivo");
 
                    } else if(devolverAlturaCliente()==Integer.parseInt(campoEditar)) {

                        JOptionPane.showMessageDialog(null, "No puedes modificar la misma altura que tienes actualmente");

                    } else {

                        editarAltura(Integer.parseInt(campoEditar));
                        reiniciarPerfilCliente();

                    }

                }

            }
            
        }
        
    }//GEN-LAST:event_jButton_PC_EditarMouseClicked

    private void jButton_PC_EditarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_PC_EditarMouseEntered
        // TODO add your handling code here:
        
        jButton_PC_Editar.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton_PC_EditarMouseEntered

    private void jButton_PC_EditarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_PC_EditarMouseExited
        // TODO add your handling code here:
        
        jButton_PC_Editar.setForeground(Color.white); 
        
    }//GEN-LAST:event_jButton_PC_EditarMouseExited

    private void jButton_PC_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PC_EditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_PC_EditarActionPerformed

    private void jLabel_PC_EditarObjetivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_PC_EditarObjetivoMouseClicked
        // TODO add your handling code here:
        
        editarCampoCliente("Introduzca el objetivo corporal nuevo:");
        
        jComboBox38.setVisible(true);
        jComboBox38.setEnabled(true);
        
    }//GEN-LAST:event_jLabel_PC_EditarObjetivoMouseClicked

    private void jLabel_PC_EditarPesoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_PC_EditarPesoMouseClicked
        // TODO add your handling code here:
        
        editarCampoCliente("Introduzca el peso nuevo:");
        
        jTextField43.setVisible(true);
        jTextField43.setEnabled(true);
        
    }//GEN-LAST:event_jLabel_PC_EditarPesoMouseClicked

    private void jLabel_PC_EditarAlturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_PC_EditarAlturaMouseClicked
        // TODO add your handling code here:
        
        editarCampoCliente("Introduzca la altura nueva:");
        
        jTextField43.setVisible(true);
        jTextField43.setEnabled(true);
        
    }//GEN-LAST:event_jLabel_PC_EditarAlturaMouseClicked

    private void jButton_PC_VolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_PC_VolverMouseClicked
        // TODO add your handling code here:
        
        jDialog_PerfilCliente.setVisible(false);
        jDialog_PerfilUsuario.setVisible(true);
        
    }//GEN-LAST:event_jButton_PC_VolverMouseClicked

    private void jButton_PC_VolverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_PC_VolverMouseEntered
        // TODO add your handling code here:
        
        jButton_PC_Volver.setForeground(new Color(0x5CBCE0));
        
    }//GEN-LAST:event_jButton_PC_VolverMouseEntered

    private void jButton_PC_VolverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_PC_VolverMouseExited
        // TODO add your handling code here:
        
        jButton_PC_Volver.setForeground(Color.white); 
        
    }//GEN-LAST:event_jButton_PC_VolverMouseExited

    // En este método, reiniciamos la busqueda reiniciando los filtros seleccionados y mostrando en la tabla 
    // todas las rutinas.
    private void reiniciarBusquedaRutina() {
        
        jTextField16.setText("");
        jComboBox5.setSelectedIndex(0);
        jComboBox8.setSelectedIndex(0);
        pintarTablaRutinas();
    }
    
    // En este método, reiniciamos la busqueda reiniciando los filtros seleccionados y mostrando en la tabla 
    // todas las dietas.
    private void reiniciarBusquedaDieta() {
        
        jTextField19.setText("");
        jComboBox13.setSelectedIndex(0);
        jComboBox14.setSelectedIndex(0);
        pintarTablaDietas();
    }
    
    // En este método, filtramos en la tabla de las rutinas según los filtros seleccionados y llamamos al 
    // método agregarFilaTablaRutinas() para agregar las filas de la tabla.
    private void filtrarBusquedaRutinas(String nombreRutina, String tipoObjetivo, String autor) {
        
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Objetivo");
        modelo.addColumn("Autor");
        modelo.addColumn("Visualizar ejercicios");
        modelo.addColumn("Modificar rutina");
        modelo.addColumn("Eliminar rutina");
        jTable3.setModel(modelo);
        
        PreparedStatement st;
        
        
        StringBuilder busquedaRutina = new StringBuilder("SELECT r.nombre, r.objetivo, CONCAT(u.nombre, ' ', u.apellidos) AS nombre_monitor ");
        busquedaRutina.append("FROM rutinas r ");
        busquedaRutina.append("INNER JOIN monitores m ON r.id_monitor = m.id_monitor ");
        busquedaRutina.append("INNER JOIN usuarios u ON m.dni = u.dni ");

        
        if (!nombreRutina.isEmpty() && comprobarCadena(nombreRutina)) {
            
            busquedaRutina.append("AND r.nombre LIKE ? ");
        }
        if (tipoObjetivo != null && !tipoObjetivo.isEmpty() && !tipoObjetivo.equals("Todos")) {
            
            busquedaRutina.append("AND r.objetivo = ? ");
        }
        
        if (autor != null && !autor.isEmpty() && !autor.equals("Todos")) {
               
            busquedaRutina.append("AND m.dni = (SELECT dni FROM usuarios WHERE nombre = ? AND apellidos = ?) ");
               
        }
        
        try {
            
            int paramIndex = 1;
            st = con.prepareStatement(busquedaRutina.toString());

             if (!nombreRutina.isEmpty() && comprobarCadena(nombreRutina)) {
                st.setString(paramIndex, "%" + nombreRutina + "%");
                
                paramIndex++;
                
            }
            if (tipoObjetivo != null && !tipoObjetivo.isEmpty() && !tipoObjetivo.equals("Todos")) {
                st.setString(paramIndex, tipoObjetivo);
                
                paramIndex++;
                
            }
            if (autor != null && !autor.isEmpty() && !autor.equals("Todos")) {
                
                String[] nombreYApellidos = autor.split(" ");
                String nombre = nombreYApellidos[0];
                String apellidos = nombreYApellidos[1]+" "+nombreYApellidos[2];

                st.setString(paramIndex, nombre);
                
                paramIndex++;
                
                st.setString(paramIndex, apellidos);
                
            }
            
            ResultSet rs = st.executeQuery();

            agregarFilaTablaRutinas(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    // En este método, filtramos en la tabla de las dietas según los filtros seleccionados y llamamos al 
    // método agregarFilaTablaDietas() para agregar las filas de la tabla.
    private void filtrarBusquedaDietas(String nombreDieta, String tipoDieta, String autorDieta) {
        
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Objetivo");
        modelo.addColumn("Autor");
        modelo.addColumn("Visualizar ejercicios");
        modelo.addColumn("Modificar rutina");
        modelo.addColumn("Eliminar rutina");
        jTable3.setModel(modelo);
        
        PreparedStatement st;
        
        
        StringBuilder busquedaDieta = new StringBuilder("SELECT d.nombre, d.tipo, CONCAT(u.nombre, ' ', u.apellidos) AS nombre_nutricionista ");
        busquedaDieta.append("FROM dietas d ");
        busquedaDieta.append("INNER JOIN nutricionistas n ON d.id_nutricionista = n.id_nutricionista ");
        busquedaDieta.append("INNER JOIN usuarios u ON n.dni = u.dni ");

        
        if (!nombreDieta.isEmpty() && comprobarCadena(nombreDieta)) {
            
            busquedaDieta.append("AND d.nombre LIKE ? ");
            
        }
        if (tipoDieta != null && !tipoDieta.isEmpty() && !tipoDieta.equals("Todos")) {
            
            busquedaDieta.append("AND d.tipo = ? ");
        }
        
        if (autorDieta != null && !autorDieta.isEmpty() && !autorDieta.equals("Todos")) {
               
            busquedaDieta.append("AND n.dni = (SELECT dni FROM usuarios WHERE nombre = ? AND apellidos = ?) ");
               
        }
        
        try {
            
            int paramIndex = 1;
            st = con.prepareStatement(busquedaDieta.toString());

             if (!nombreDieta.isEmpty() && comprobarCadena(nombreDieta)) {
                st.setString(paramIndex, "%" + nombreDieta + "%");
                
                paramIndex++;
                
            }
            if (tipoDieta != null && !tipoDieta.isEmpty() && !tipoDieta.equals("Todos")) {
                st.setString(paramIndex, tipoDieta);
                
                paramIndex++;
                
            }
            if (autorDieta != null && !autorDieta.isEmpty() && !autorDieta.equals("Todos")) {
                
                String[] nombreYApellidos = autorDieta.split(" ");
                String nombre = nombreYApellidos[0];
                String apellidos = nombreYApellidos[1]+" "+nombreYApellidos[2];

                st.setString(paramIndex, nombre);
                
                paramIndex++;
                
                st.setString(paramIndex, apellidos);
                
            }
            
            ResultSet rs = st.executeQuery();

            agregarFilaTablaDietas(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    // En este método, filtramos en la tabla de los productos según los filtros seleccionados y llamamos al 
    // método agregarFilaTablaProductos() para agregar las filas de la tabla.
    private void filtrarBusquedaProductos(String nombreProducto, String tipoPrecio, String tipoCantidad) {
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio por Unidad");
        modelo.addColumn("Cantidad Disponible");
        modelo.addColumn("Modificar");
        modelo.addColumn("Eliminar");
        jTable7.setModel(modelo);
        
        PreparedStatement st;
        StringBuilder query = new StringBuilder("SELECT nombre, precio_unidad, cantidad_disponible FROM productos WHERE 1=1 ");

        // Construir la cláusula WHERE
        if (nombreProducto != null && !nombreProducto.isEmpty()) {
            query.append("AND nombre LIKE ? ");
        }

       
        if (tipoPrecio != null && !tipoPrecio.equals("Todos")) {
            
            if (tipoPrecio.equals("Mas caro")) {
                
                query.append("ORDER BY precio_unidad DESC ");
                
            } else if (tipoPrecio.equals("Mas barato")) {
                
                query.append("ORDER BY precio_unidad ASC ");
                
            }
            
        } else if (tipoCantidad != null && !tipoCantidad.equals("Todos")) {
            
            if (tipoCantidad.equals("Mayor cantidad")) {
                
                query.append("ORDER BY cantidad_disponible DESC ");
                
            } else if (tipoCantidad.equals("Menor cantidad")) {
                
                query.append("ORDER BY cantidad_disponible ASC ");
            }
        }

        try {
            int paramIndex = 1;
            st = con.prepareStatement(query.toString());

            // Asignar parámetros de búsqueda
            if (nombreProducto != null && !nombreProducto.isEmpty()) {
                st.setString(paramIndex, "%" + nombreProducto + "%");
                paramIndex++;
            }
            
            ResultSet rs = st.executeQuery();

            agregarFilaTablaProductos(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, filtramos en la tabla de las productos a la venta para el cliente según los filtros seleccionados y llamamos al 
    // método agregarFilaTablaPagosProductos() para agregar las filas de la tabla.
    private void filtrarBusquedaProductosCliente(String nombreProducto, String tipoPrecio) {
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio por Unidad");
        modelo.addColumn("Cantidad Disponible");
        modelo.addColumn("Cantidad A Comprar");
        modelo.addColumn("Pagar");
        jTable13.setModel(modelo);
        
        PreparedStatement st;
        StringBuilder query = new StringBuilder("SELECT nombre, precio_unidad, cantidad_disponible FROM productos WHERE 1=1 ");

        // Construir la cláusula WHERE
        if (nombreProducto != null && !nombreProducto.isEmpty()) {
            query.append("AND nombre LIKE ? ");
        }

       
        if (tipoPrecio != null && !tipoPrecio.equals("Todos")) {
            
            if (tipoPrecio.equals("Mas caro")) {
                
                query.append("ORDER BY precio_unidad DESC ");
                
            } else if (tipoPrecio.equals("Mas barato")) {
                
                query.append("ORDER BY precio_unidad ASC ");
                
            }
            
        } 

        try {
            int paramIndex = 1;
            st = con.prepareStatement(query.toString());

            
            if (nombreProducto != null && !nombreProducto.isEmpty()) {
                st.setString(paramIndex, "%" + nombreProducto + "%");
                paramIndex++;
            }
            
            ResultSet rs = st.executeQuery();

            agregarFilaTablaPagosProductos(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, filtramos en la tabla de las ventas del producto realizados por los clientes según los filtros seleccionados y llamamos al 
    // método agregarFilaTablaVentas() para agregar las filas de la tabla.
    private void filtrarBusquedaVentas(String productoVenta, String tipoFecha, String nombreCliente) {
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre Producto");
        modelo.addColumn("Nombre Cliente");
        modelo.addColumn("Cantidad Vendida");
        modelo.addColumn("Fecha Venta");
        jTable10.setModel(modelo);
        
        PreparedStatement st;
        
        StringBuilder query = new StringBuilder("SELECT p.nombre AS nombre_producto, CONCAT(u.nombre, ' ', u.apellidos) AS nombre_cliente,\n" +
        " v.cantidad_vendida, v.fecha_venta FROM ventas v JOIN productos p ON v.id_producto = p.id_producto \n" +
        " JOIN clientes c ON v.id_cliente = c.id_cliente JOIN usuarios u ON c.dni = u.dni WHERE 1=1 ");


        ArrayList<String> condiciones = new ArrayList<>();

        if (!productoVenta.isEmpty()) {
            condiciones.add("p.nombre LIKE ?");
        }

        if (nombreCliente != null && !nombreCliente.isEmpty() && !nombreCliente.equalsIgnoreCase("Todos")) {
            condiciones.add("CONCAT(u.nombre, ' ', u.apellidos) LIKE ?");
        }

        for (String condicion : condiciones) {
            query.append(" AND ").append(condicion);
        }
        
        
        if (!tipoFecha.equalsIgnoreCase("Todos")) {
            if (tipoFecha.equals("Mas recientes")) {
                query.append(" ORDER BY v.fecha_venta DESC");
            } else {
                query.append(" ORDER BY v.fecha_venta ASC");
            }
        }

        try {
            
            int paramIndex = 1;
            st = con.prepareStatement(query.toString());

       
            if (!productoVenta.isEmpty()) {
                st.setString(paramIndex++, "%" + productoVenta + "%");
                
            }
            
            if (nombreCliente != null && !nombreCliente.isEmpty() && !nombreCliente.equalsIgnoreCase("Todos")) {
                st.setString(paramIndex++, "%" + nombreCliente + "%");
                
            }

            ResultSet rs = st.executeQuery();

            agregarFilaTablaVentas(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, filtramos en la tabla de las reservas realizadas por los clientes según los filtros seleccionados y llamamos al 
    // método agregarFilaTablaReservas() para agregar las filas de la tabla.
    private void filtrarBusquedaReservas(String nombreSala, String tipoFecha, String tipoTurno, String nombreCliente) {
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre Cliente");
        modelo.addColumn("Nombre Sala");
        modelo.addColumn("Fecha Reserva");
        modelo.addColumn("Hora Inicio");
        modelo.addColumn("Hora Salida");
        modelo.addColumn("Estado");
        jTable11.setModel(modelo);
        
        PreparedStatement st;
        
        StringBuilder query = new StringBuilder(
        "SELECT CONCAT(u.nombre, ' ', u.apellidos) AS nombre_cliente, " +
        "s.nombre AS nombre_sala, DATE(r.fecha_entrada) AS fecha, " +
        "TIME(r.fecha_entrada) AS hora_entrada, TIME(r.fecha_salida) AS hora_salida, " +
        "r.estado FROM reservas r JOIN clientes c ON r.id_cliente = c.id_cliente " +
        "JOIN usuarios u ON c.dni = u.dni JOIN salas s ON r.id_sala = s.id_sala WHERE 1=1 ");

        ArrayList<String> condiciones = new ArrayList<>();

        if (!nombreCliente.equalsIgnoreCase("Todos")) {
            condiciones.add("CONCAT(u.nombre, ' ', u.apellidos) LIKE ?");
        }
        
        if (!nombreSala.equalsIgnoreCase("Todos")) {
            condiciones.add("s.nombre LIKE ?");
        }

        

        
        if (!tipoTurno.equalsIgnoreCase("Todos")) {
            if (tipoTurno.equalsIgnoreCase("Mañana")) {
                condiciones.add("TIME(r.fecha_entrada) IN ('08:00:00', '10:00:00', '12:00:00')");
            } else if (tipoTurno.equalsIgnoreCase("Tarde")) {
                condiciones.add("TIME(r.fecha_entrada) IN ('16:00:00', '18:00:00', '20:00:00')");
            }
        }

        for (String condicion : condiciones) {
            query.append(" AND ").append(condicion);
        }

        if (!tipoFecha.equalsIgnoreCase("Todos")) {
            if (tipoFecha.equals("Mas recientes")) {
                query.append(" ORDER BY r.fecha_entrada DESC");
            } else {
                query.append(" ORDER BY r.fecha_entrada ASC");
            }
        }

        try {
            int paramIndex = 1;
            st = con.prepareStatement(query.toString());

            if (!nombreCliente.equalsIgnoreCase("Todos")) {
                st.setString(paramIndex++, "%" + nombreCliente + "%");
            }
            
            if (!nombreSala.equalsIgnoreCase("Todos")) {
                st.setString(paramIndex++, "%" + nombreSala + "%");
            }

            

            ResultSet rs = st.executeQuery();
            agregarFilaTablaReservas(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, filtramos en la tabla de las pagos de los servicios realizados por los clientes según los filtros seleccionados y llamamos al 
    // método agregarFilaTablaPagosServicio() para agregar las filas de la tabla.
    private void filtrarBusquedaPagosServicio(String nombreServicio, String tipoFecha, String nombreCliente) {
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre Cliente");
        modelo.addColumn("Nombre Servicio");
        modelo.addColumn("Fecha Pago");
        modelo.addColumn("Estado");
        jTable11.setModel(modelo);
        
        PreparedStatement st;
        
        StringBuilder query = new StringBuilder(
        "SELECT CONCAT(u.nombre, ' ', u.apellidos) AS nombre_cliente, " +
        "s.nombre AS nombre_servicio, ps.fecha_pagado, ps.estado " +
        "FROM pagos_servicios ps " +
        "JOIN clientes c ON ps.id_cliente = c.id_cliente " +
        "JOIN usuarios u ON c.dni = u.dni " +
        "JOIN servicios s ON ps.id_servicio = s.id_servicio WHERE 1=1 ");
    
        ArrayList<String> condiciones = new ArrayList<>();

        if (!nombreCliente.equalsIgnoreCase("Todos")) {
            condiciones.add("CONCAT(u.nombre, ' ', u.apellidos) LIKE ?");
        }

        if (!nombreServicio.equalsIgnoreCase("Todos")) {
            condiciones.add("s.nombre LIKE ?");
        }

        for (String condicion : condiciones) {
            query.append(" AND ").append(condicion);
        }

        if (!tipoFecha.equalsIgnoreCase("Todos")) {
            if (tipoFecha.equals("Mas recientes")) {
                query.append(" ORDER BY ps.fecha_pagado DESC");
            } else {
                query.append(" ORDER BY ps.fecha_pagado ASC");
            }
        }

        try {
            int paramIndex = 1;
            st = con.prepareStatement(query.toString());

            if (!nombreCliente.equalsIgnoreCase("Todos")) {
                st.setString(paramIndex++, "%" + nombreCliente + "%");
            }

            if (!nombreServicio.equalsIgnoreCase("Todos")) {
                st.setString(paramIndex++, "%" + nombreServicio + "%");
            }

            ResultSet rs = st.executeQuery();
            agregarFilaTablaPagosServicio(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, filtramos en la tabla de los servicios según los filtros seleccionados y llamamos al 
    // método agregarFilaTablaServicios() para agregar las filas de la tabla.
    private void filtrarBusquedaServicios(String nombreServicio, String tipoPrecio, String tipoDias) {
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Dias de duracion");
        modelo.addColumn("Modificar");
        modelo.addColumn("Eliminar");
        jTable8.setModel(modelo);
        
        PreparedStatement st;
        StringBuilder query = new StringBuilder("SELECT nombre, precio, dias_duracion FROM servicios WHERE 1=1 ");

        // Construir la cláusula WHERE
        if (nombreServicio != null && !nombreServicio.isEmpty()) {
            query.append("AND nombre LIKE ? ");
        }

       
        if (tipoPrecio != null && !tipoPrecio.equals("Todos")) {
            
            if (tipoPrecio.equals("Mas caro")) {
                
                query.append("ORDER BY precio DESC ");
                
            } else if (tipoPrecio.equals("Mas barato")) {
                
                query.append("ORDER BY precio ASC ");
                
            }
            
        } else if (tipoDias != null && !tipoDias.equals("Todos")) {
            
            if (tipoDias.equals("Mayor dias")) {
                
                query.append("ORDER BY dias_duracion DESC ");
                
            } else if (tipoDias.equals("Menor dias")) {
                
                query.append("ORDER BY dias_duracion ASC ");
            }
        }

        try {
            int paramIndex = 1;
            st = con.prepareStatement(query.toString());

            // Asignar parámetros de búsqueda
            if (nombreServicio != null && !nombreServicio.isEmpty()) {
                st.setString(paramIndex, "%" + nombreServicio + "%");
                paramIndex++;
            }
            
            ResultSet rs = st.executeQuery();

            agregarFilaTablaServicios(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, filtramos en la tabla de los servicios que serán mostrados para los clientes según los filtros seleccionados y llamamos al 
    // método agregarFilaTablaServiciosCliente() para agregar las filas de la tabla.
    private void filtrarBusquedaServiciosCliente(String nombreServicio, String tipoPrecio) {
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Dias de duracion");
        modelo.addColumn("Pagar");
        modelo.addColumn("Cancelar");
        jTable14.setModel(modelo);
        
        PreparedStatement st;
        StringBuilder query = new StringBuilder("SELECT nombre, precio, dias_duracion FROM servicios WHERE 1=1 ");

        // Construir la cláusula WHERE
        if (nombreServicio != null && !nombreServicio.isEmpty()) {
            query.append("AND nombre LIKE ? ");
        }

       
        if (tipoPrecio != null && !tipoPrecio.equals("Todos")) {
            
            if (tipoPrecio.equals("Mas caro")) {
                
                query.append("ORDER BY precio DESC ");
                
            } else if (tipoPrecio.equals("Mas barato")) {
                
                query.append("ORDER BY precio ASC ");
                
            }
            
        }

        try {
            int paramIndex = 1;
            st = con.prepareStatement(query.toString());

            // Asignar parámetros de búsqueda
            if (nombreServicio != null && !nombreServicio.isEmpty()) {
                st.setString(paramIndex, "%" + nombreServicio + "%");
                paramIndex++;
            }
            
            ResultSet rs = st.executeQuery();

            agregarFilaTablaServiciosCliente(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, filtramos en la tabla de las salas según los filtros seleccionados y llamamos al 
    // método agregarFilaTablaSalas() para agregar las filas de la tabla.
    private void filtrarBusquedaSalas(String nombreSala, String tipoCapacidad, String monitorAsignado) {
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Capacidad de personas");
        modelo.addColumn("Monitor asignado");
        modelo.addColumn("Modificar");
        modelo.addColumn("Eliminar");
        jTable9.setModel(modelo);
        
        PreparedStatement st;
        
        StringBuilder query = new StringBuilder("SELECT s.nombre AS nombre_sala, s.capacidad_personas, COALESCE(CONCAT(u.nombre, ' ', u.apellidos), 'Ninguno') AS nombre_monitor ");
        query.append("FROM salas s ");
        query.append("LEFT JOIN monitores m ON s.id_monitor = m.id_monitor ");
        query.append("LEFT JOIN usuarios u ON m.dni = u.dni ");
        query.append("WHERE 1=1 ");

        
        if (nombreSala != null && !nombreSala.isEmpty()) {
            query.append("AND s.nombre LIKE ? ");
        }

      
        if (monitorAsignado != null && !monitorAsignado.equals("Todos")) {
            if (monitorAsignado.equals("Ninguno")) {
                query.append("AND s.id_monitor IS NULL ");
            } else {
                query.append("AND CONCAT(u.nombre, ' ', u.apellidos) LIKE ? ");
            }
        }

      
        if (tipoCapacidad != null && !tipoCapacidad.equals("Todos")) {
            if (tipoCapacidad.equals("Mayor capacidad")) {
                query.append("ORDER BY s.capacidad_personas DESC ");
            } else if (tipoCapacidad.equals("Menor capacidad")) {
                query.append("ORDER BY s.capacidad_personas ASC ");
            }
        }

        try {
            int paramIndex = 1;
            st = con.prepareStatement(query.toString());

           
            if (nombreSala != null && !nombreSala.isEmpty()) {
                st.setString(paramIndex, "%" + nombreSala + "%");
                paramIndex++;
            }

            if (monitorAsignado != null && !monitorAsignado.equals("Todos") && !monitorAsignado.equals("Ninguno")) {
                st.setString(paramIndex, "%" + monitorAsignado + "%");
                paramIndex++;
            }

            ResultSet rs = st.executeQuery();

            agregarFilaTablaSalas(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    // Este método se encarga de la navegación hacia la pantalla de la cuenta bancaria donde ocultaría la respectiva
    // pantalla de las funcionalidades de cada rol.
    private void irCuentaBancaria() {
        
        switch(rolUsuario) {
            
            case "Cliente" -> this.jDialog_FuncionalidadCliente.setVisible(false);
            
            case "Monitor" -> this.jDialog_FuncionalidadMonitor.setVisible(false);
            
            case "Nutricionista" -> this.jDialog_FuncionalidadNutricionista.setVisible(false);
            
            case "Administrador" -> this.jDialog_FuncionalidadAdministrador.setVisible(false);
        }
        
        this.jDialog_GestionCuentaBancaria.setVisible(true);
        
    }
    
    // Este método se encarga de la navegación hacia la pantalla de las funcionalidades de cada rol donde ocultaría la
    // pantalla de la cuenta bancaria.
    private void salirCuentaBancaria() {
        
        switch(rolUsuario) {
            
            case "Cliente" -> this.jDialog_FuncionalidadCliente.setVisible(true);
            
            case "Monitor" -> this.jDialog_FuncionalidadMonitor.setVisible(true);
            
            case "Nutricionista" -> this.jDialog_FuncionalidadNutricionista.setVisible(true);
            
            case "Administrador" -> this.jDialog_FuncionalidadAdministrador.setVisible(true);
        }
        
        this.jDialog_GestionCuentaBancaria.setVisible(false);
        
    }
    
    // Este método se encarga de mostrar el formulario para que el usuario introduzca el dinero a retirar o introducir
    // según lo que haya seleccionado clicando en el botón correspondiente para realizar las operaciones.
    private void mostrarOperacionesSaldo(String mensaje) {
        
        jLabel56.setVisible(false);
        jLabel56.setEnabled(false);
        
        jLabel55.setVisible(false);
        jLabel55.setEnabled(false);
        
        jLabel54.setText(mensaje);
        
        jTextField1.setVisible(true);
        jTextField1.setEnabled(true);
        
        jButton26.setVisible(true);
        jButton26.setEnabled(true);
        
    }
    
    // En este método, ocultamos el botón que da posibilidad de registrar una cuenta bancaria ya que el usuario, ya dispone de ella
    // y mostramos los botones para que pueda consultarla o borrarla.
    private void ocultarBotonesHayCuenta() {
        
        jButton24.setVisible(true);
        jButton24.setEnabled(true);
            
        jButton23.setVisible(true);
        jButton23.setEnabled(true);
            
        jButton22.setVisible(false);
        jButton22.setEnabled(false);
        
    }
    
    // En este método, mostramos el botón que da posibilidad de registrar una cuenta bancaria ya que el usuario, no dispone de ella
    // y ocultamos los botones para no que pueda ni consultar o borrar la cuenta bancaria.
    
    private void ocultarBotonesSinCuenta() {
        
        jButton24.setVisible(false);
        jButton24.setEnabled(false);
            
        jButton23.setVisible(false);
        jButton23.setEnabled(false);
            
        jButton22.setVisible(true);
        jButton22.setEnabled(true);
        
    }
    
    // Este método se encarga de ir a la pantalla de inicio.
    private void irAInicio() {
        
        this.setVisible(false);
        this.jDialog_Inicio.setVisible(true);
        
    }
    
    // En este método, comprobamos que el campo que se pase por parámetro es un campo clave(DNI, Correo electrónico o Nombre usuario)
    // donde debe cumplir su respectivo formato.
    private boolean esCampoClave(String campo) {
        
        boolean esCampoClave=false;
        
        if(esDNI(campo) || esCorreoElectronico(campo) || esNombreUsuario(campo)) {
            
            esCampoClave=true;
            
        } 
        
        return esCampoClave;
        
    }
    
    // Este método se encarga de que el campo pasado es una contraseña, donde debe cumplir el formato.
    private boolean esContrasenna(String campo) {
        
        boolean esContrasenna=false;
        
        
        if(campo.matches(REGEZ_CONTRASENNA)) {
            
            esContrasenna=true;
            
        }
        
        return esContrasenna;
    }
    
    // Este método se encarga de buscar al usuario en el sistema segun el campo clave pasado por paramétro.
    private boolean buscarCampoClave (String campo) {
        
        boolean encontrado=false;
        
        String campoBD;
        
        if(esDNI(campo)) {
            
            campoBD="dni";
            
        } else if(esCorreoElectronico(campo)) {
            
            campoBD="correo";
            
        } else {
            
            campoBD="nombre_usuario";
        }
        
         PreparedStatement st;
        
        try {
            st = con.prepareStatement("select * from gym_zone.usuarios where "+campoBD+"=?");

            st.setString(1, campo);

            ResultSet rs = st.executeQuery();

            if(rs.next()){

                encontrado=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return encontrado;
        
    }
    
    // Este método se encarga de recoger el dni del usuario segun el campo clave pasado por parámetro.
    private String buscarDNI (String campo) {
        
        String dniEncontrado="";
        
        String campoBD;

        if(esCorreoElectronico(campo)) {
            
            campoBD="correo";
            
        } else {
            
            campoBD="nombre_usuario";
        }
        
         PreparedStatement st;
        
        try {
            st = con.prepareStatement("select * from gym_zone.usuarios where "+campoBD+"=?");

            st.setString(1, campo);

            ResultSet rs = st.executeQuery();

            if(rs.next()){

                dniEncontrado=rs.getString(1);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return dniEncontrado;
        
    }

    // En este método, comprobamos que el campo pasado esté vacío.
    private boolean comprobarCampoVacio(String campo) {
        
        boolean esVacio=false;
        
        if(campo.equals("")) {
            
            esVacio=true;
            
        }
        
        return esVacio;
    }
    
    // Este método se encarga de que el campo pasado es un DNI, donde debe cumplir el formato.
    private boolean esDNI(String campo) {
        
        boolean esDNI=false;
        
        if(campo.matches(REGEZ_DNI)) {
            
            esDNI=true;
            
        }
        
        return esDNI;
        
    }
    
    // Este método se encarga de que el campo pasado es un correo electrónico, donde debe cumplir el formato. 
    private boolean esCorreoElectronico(String campo) {
        
        boolean esCorreo=false;
        
        if(campo.matches(REGEZ_CORREO)) {
            
            esCorreo=true;
            
        }
        
        return esCorreo;
        
    }
    
    // Este método se encarga de que el campo pasado es un nombre de usuario, donde debe cumplir el formato.
    private boolean esNombreUsuario(String campo) {
        
        boolean esNombreUsuario=false;
        
        if(campo.matches(REGEZ_NOMBRE_USUARIO)) {
            
            esNombreUsuario=true;
            
        }
        
        return esNombreUsuario;
        
    }
    
    // Este método se encarga de que el campo pasado es nombre y apellidos de la persona, donde debe cumplir el formato.
    private boolean esNombreApellidos(String campo) {
        
        
        boolean esNombreApellidos=false;
        
        if(campo.matches(REGEZ_NOMBRE_APELLIDOS)) {
            
            esNombreApellidos=true;
            
        }
        
        return esNombreApellidos;
        
        
    }
 
    // En este método, modificamos los datos nuevos de la cuenta bancaria del usuario.
    private void modificarDatosCuentaBancaria(String titularModificar, String bancoSeleccionado) {
        
        
        try {
                
            PreparedStatement st = con.prepareStatement("UPDATE gym_zone.cuentas_bancarias SET nombre_titular=?, banco=? WHERE dni=?;");

            st.setString(1, titularModificar);
            st.setString(2, bancoSeleccionado);
            st.setString(3, dniUsuario);


            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, comprobamos si el usuario puede realizar el inicio de sesión, en caso de que pueda recogemos su
    // determinado dni y id. También, mostramos sus credenciales y el sistema navega hacia su respectiva pantalla según el rol.
    private void realizarInicioSesion(String campoClave, String contrasenna) {
        
        
        if(!buscarCampoClave(campoClave)) {
                    
            JOptionPane.showMessageDialog(null, "El usuario no existe. Registrate para iniciar sesión.");
                    
        } else if(!comprobarUsuarioIniciarSesion(campoClave, contrasenna)) {
                        
            JOptionPane.showMessageDialog(null, "El campo clave y la contraseña no coincide. Por favor, introduce los campos de nuevo corractamente.");            
                      
        } else {
        
            this.jDialog_IniciarSesion.setVisible(false);
            
            recogerDNI(campoClave);
            
            recibirIdUsuario();
            
            mostrarCredencialesUsuario();
            
            seleccionarVentanaFuncionalidadUsuario();
            
            jTextField2.setText("");
        
            jPasswordField2.setText("");
                
        }

    }
    
    // Este método realiza el ingreso con el dinero introducido por el usuario, donde esté se le sumará a su saldo.
    private void realizarIngresoSaldo(double dineroIntroducido) {
        

        String textoSaldoActual=jLabel53.getText().substring(0, jLabel53.getText().length()-1);
        
        double saldoActual=Double.parseDouble(textoSaldoActual);
        
        saldoActual+=dineroIntroducido;
        
        jLabel53.setText(saldoActual+"€");
        
        try {
                
            PreparedStatement st = con.prepareStatement("UPDATE gym_zone.cuentas_bancarias SET saldo=? WHERE dni=?;");

            st.setDouble(1, saldoActual);
            st.setString(2, dniUsuario);

            

            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }


        
    }
    
    // Este método realiza el retiro del saldo con el dinero introducido por el usuario, donde esté se le restará a su saldo.
    private void realizarRetiroSaldo(double dineroIntroducido) {
        
         String textoSaldoActual=jLabel53.getText().substring(0, jLabel53.getText().length()-1);
        
        double saldoActual=Double.parseDouble(textoSaldoActual);
        
        double comprobarSaldo=saldoActual-dineroIntroducido;
        
        if((comprobarSaldo)<0) {
            
             JOptionPane.showMessageDialog(null, "No tienes suficiente saldo para retirar el dinero solicitado.");
            
        } else {
            
            saldoActual-=dineroIntroducido;
        
            jLabel53.setText(saldoActual+"€");
        
            try {
                
                PreparedStatement st = con.prepareStatement("UPDATE gym_zone.cuentas_bancarias SET saldo=? WHERE dni=?;");

                st.setDouble(1, saldoActual);
                st.setString(2, dniUsuario);

            

                st.executeUpdate();
            
            } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    // En este método se comprueba si el usuario puede iniciar sesión donde debe aparecer registrado en el sistema.
    private boolean comprobarUsuarioIniciarSesion(String campoClave, String contrasenna) {
        
        boolean encontrado=false;
        
        String campoBD;
        
        if(esDNI(campoClave)) {
            
            campoBD="dni";
            
        } else if(esCorreoElectronico(campoClave)) {
            
            campoBD="correo";
            
        } else {
            
            campoBD="nombre_usuario";
        }
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("select * from gym_zone.usuarios where "+campoBD+"=? AND contrasenna=? AND tipo=?");

            st.setString(1, campoClave);
            st.setString(2, contrasenna);
            st.setString(3, rolUsuario);

            ResultSet rs = st.executeQuery();

            if(rs.next()){

                encontrado=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return encontrado;
        
      
    }
    
    // En este método devolvemos el objetivo corporal del cliente que este registrado en el sistema.
    private String devolverObjetivoCliente() {
        
        String objetivoCliente="";
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("select objetivo_corporal from clientes where id_cliente=?");

            st.setInt(1, idUsuario);
            ResultSet rs = st.executeQuery();

            if(rs.next()){

               objetivoCliente=rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return objetivoCliente;
        
    }
    
    // En este método devolvemos la altura del cliente que este registrado en el sistema.
    private int devolverAlturaCliente() {
        
        int alturaCliente=0;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("select altura from clientes where id_cliente=?");

            st.setInt(1, idUsuario);
            ResultSet rs = st.executeQuery();

            if(rs.next()){

               alturaCliente=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return alturaCliente;
        
    }
    
    // En este método devolvemos el peso del cliente que este registrado en el sistema.
    private double devolverPesoCliente() {
        
        double pesoCliente=0;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("select peso from clientes where id_cliente=?");

            st.setInt(1, idUsuario);
            ResultSet rs = st.executeQuery();

            if(rs.next()){

               pesoCliente=rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pesoCliente;
        
    }
    
    // En este método devolvemos la edad cliente que este registrado en el sistema, según la fecha de nacimiento.
    private int devolverEdadCliente() {
        
        int edadCliente = 0;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            String query = "SELECT fecha_nacimiento FROM clientes WHERE id_cliente = ?";

            st = con.prepareStatement(query);
            st.setInt(1, idUsuario);

            rs = st.executeQuery();

            if (rs.next()) {
                
                java.sql.Date fechaNacimiento = rs.getDate("fecha_nacimiento");
                LocalDate fechaNac = fechaNacimiento.toLocalDate();
                LocalDate fechaActual = LocalDate.now();

                edadCliente = fechaActual.getYear() - fechaNac.getYear();

            }
        } catch (SQLException ex) {
            
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return edadCliente;
    }
    
    // En este método devolvemos el genéro del usuario que este registrado en el sistema.
    private String devolverGenero() {
        
        String generoUsuario="";
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("select genero from usuarios where dni=?");

            st.setString(1, dniUsuario);
            ResultSet rs = st.executeQuery();

            if(rs.next()){

               generoUsuario=rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return generoUsuario;
        
    }
    
    // En este método devolvemos el nombre_usuario del usuario que este registrado en el sistema.
    private String devolverNombreUsuario() {
        
        String nombreUsuario="";
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("select nombre_usuario from usuarios where dni=?");

            st.setString(1, dniUsuario);
            ResultSet rs = st.executeQuery();

            if(rs.next()){

               nombreUsuario=rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return nombreUsuario;
        
    }
    
    // En este método devolvemos el nombre y apellidos del usuario que este registrado en el sistema.
    private String devolverNombreApellidos() {
        
        String nombre="", apellidos="";
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("select nombre, apellidos from usuarios where dni=?");

            st.setString(1, dniUsuario);
            ResultSet rs = st.executeQuery();

            if(rs.next()){

               nombre=rs.getString(1);
               apellidos=rs.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return nombre+" "+apellidos;
        
    }
    
    // En este método devolvemos el correo electrónico del usuario que este registrado en el sistema.
    private String devolverCorreo() {
        
        String correoUsuario="";
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("select correo from usuarios where dni=?");

            st.setString(1, dniUsuario);
            ResultSet rs = st.executeQuery();

            if(rs.next()){

               correoUsuario=rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return correoUsuario;
        
    }
    
    // En este método devolvemos la contraseña del usuario que este registrado en el sistema.
    private String devolverContrasenna() {
        
        String contrasennaUsuario="";
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("select contrasenna from usuarios where dni=?");

            st.setString(1, dniUsuario);
            ResultSet rs = st.executeQuery();

            if(rs.next()){

               contrasennaUsuario=rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return contrasennaUsuario;
        
    }
    
    // En este método registramos en el sistema al cliente con sus respectivos datos introducidos.
    private void registrarCliente(String fechaNacimiento, int altura, double peso, String objetivo) {
        
        
        try {
                
            PreparedStatement st = con.prepareStatement("INSERT INTO gym_zone.clientes (dni, fecha_nacimiento, altura, peso, objetivo_corporal, id_dieta, id_rutina) VALUES(?, ?, ?, ?, ?, NULL, NULL);");

            st.setString(1, dniUsuario);
            st.setDate(2, java.sql.Date.valueOf(fechaNacimiento));
            st.setInt(3, altura);
            st.setDouble(4, peso);
            st.setString(5, objetivo);
            

            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
       
 
    }
    
    // Este método se encarga de navegar hacia la pantalla de inicio.
    private void cerrarSesion() {
        
        switch(rolUsuario) {
            
            case "Cliente" -> this.jDialog_FuncionalidadCliente.setVisible(false);
            
            case "Monitor" -> this.jDialog_FuncionalidadMonitor.setVisible(false);
            
            case "Nutricionista" -> this.jDialog_FuncionalidadNutricionista.setVisible(false);
            
            case "Administrador" -> this.jDialog_FuncionalidadAdministrador.setVisible(false);
        }
        
        this.jDialog_Inicio.setVisible(true);
        
    }
    
    // En este método, comprobamos de que el usuario este registrado para que cuando intente registrarse de nuevo no pueda.
    private boolean comprobarUsuarioRegistrado(String dniUsuario, String correo, String nombreUsuario) {
        
        boolean hayUsuarioRegistrado=false;
        
        if(buscarCampoClave(dniUsuario) || buscarCampoClave(correo) || buscarCampoClave(nombreUsuario)) {
            
            hayUsuarioRegistrado=true;
            
        }
        
        return hayUsuarioRegistrado;
    }
    
    // En este método, borramos la cuenta bancaria del usuario asociada y registrada en el sistema.
    private void borrarCuentaBancaria() {
        
        try {
            PreparedStatement st = con.prepareStatement("DELETE FROM gym_zone.cuentas_bancarias WHERE dni=?;");

            st.setString(1, dniUsuario);

            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // Este método se encarga de navegar hacia la pantalla de funcionalidad según el rol del usuario.
    private void seleccionarVentanaFuncionalidadUsuario() {
        
       
        
        switch(rolUsuario) {
            
            case "Cliente" -> this.jDialog_FuncionalidadCliente.setVisible(true);
                
            case "Nutricionista" -> this.jDialog_FuncionalidadNutricionista.setVisible(true);
                
            case "Monitor" -> this.jDialog_FuncionalidadMonitor.setVisible(true);
                
            case "Administrador" -> this.jDialog_FuncionalidadAdministrador.setVisible(true);
            
        }
        
    }
    
    //  En este método mostramos los datos respectivos a la cuenta bancaria registrada del usuario.
    private void mostrarDatosBancarios() {
        
        PreparedStatement st;
        
        String nombreTitular="", numeroCuenta="", nombreBanco="";
        double saldo=0;
        
        try {
            st = con.prepareStatement("select * from gym_zone.cuentas_bancarias where dni=?");

            st.setString(1, dniUsuario);

            ResultSet rs = st.executeQuery();

            if(rs.next()){

                nombreTitular=rs.getString(2);
                numeroCuenta=rs.getString(3);
                nombreBanco=rs.getString(4);
                saldo=rs.getDouble(5);
                
                
            }
            
            
            mostrarImagenBanco(nombreBanco);
            
            jLabel57.setText(nombreTitular);
            jLabel52.setText(numeroCuenta);
            jLabel53.setText(saldo+"€");
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    // Este método mostramos la imagen del banco seleccionado por el usuario.
    private void mostrarImagenBanco(String nombreBanco) {
        
        URL url_Banco = getClass().getResource("/img/banco"+nombreBanco+".png");
        ImageIcon img_Banco = new ImageIcon(url_Banco);
        jLabel_Banco.setIcon(img_Banco);
        
    }
    
    // En este método, registramos al nutricionista en el sistema con sus respectivos datos introducidos.
    private void registrarNutricionista(String tipoDieta) {
 
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO gym_zone.nutricionistas (dni, especialidad) VALUES(?, ?);");

            st.setString(1, dniUsuario);
            st.setString(2, tipoDieta);

            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
        
    }
    
    // En este método, realizamos la alta del producto con sus respectivos datos introducidos por el administrador.
    private void altaProducto(String nombreProducto, double precioProducto, int cantidadProducto) {
       
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("INSERT INTO gym_zone.productos (nombre, precio_unidad, cantidad_disponible, id_monitor) VALUES(?, ?, ?, ?);");

            st.setString(1, nombreProducto);
            st.setDouble(2, precioProducto);
            st.setInt(3, cantidadProducto);
            st.setInt(4, idUsuario);

            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, modificamos el producto deseado con todos sus determinados datos nuevos introducidos.
    private void modificarProducto(String nombreProducto, double precioProducto, int cantidadProducto) {
       
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("UPDATE gym_zone.productos SET nombre=?, precio_unidad=?, cantidad_disponible=? WHERE id_producto=?;");

            st.setString(1, nombreProducto);
            st.setDouble(2, precioProducto);
            st.setInt(3, cantidadProducto);
            st.setInt(4, idProducto);

            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, modificamos el servicio deseado con todos sus determinados datos nuevos introducidos.
    private void modificarServicio(String nombreServicio, double precioServicio, int diasDuracion) {
       
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("UPDATE gym_zone.servicios SET nombre=?, precio=?, dias_duracion=? WHERE id_servicio=?;");

            st.setString(1, nombreServicio);
            st.setDouble(2, precioServicio);
            st.setInt(3, diasDuracion);
            st.setInt(4, idServicio);

            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, realizamos la alta del servicio con sus respectivos datos introducidos por el administrador.
    private void altaServicio(String nombreServicio, double precioServicio, int diasServicio) {
       
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("INSERT INTO gym_zone.servicios (nombre, precio, dias_duracion, id_monitor) VALUES(?, ?, ?, ?);");

            st.setString(1, nombreServicio);
            st.setDouble(2, precioServicio);
            st.setInt(3, diasServicio);
            st.setInt(4, idUsuario);

            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, realizamos la alta de la sala con sus determinados datos introducidos donde asiganmos o no su respectivo monitor seleccionado.
    private void altaSala(String nombreSala, int personasSala, String nombreMonitor) {
        
        int idMonitor=0;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("INSERT INTO gym_zone.salas (nombre, capacidad_personas, id_monitor) VALUES(?, ?, ?);");

            st.setString(1, nombreSala);
            st.setInt(2, personasSala);
            
            
            if(nombreMonitor!=null) {
            
                idMonitor=devolverIdMonitor(nombreMonitor.split(" ")[0], nombreMonitor.split(" ")[1]+" "+nombreMonitor.split(" ")[2]);
            
                st.setInt(3, idMonitor);
                
            } else {
                
                st.setNull(3, java.sql.Types.INTEGER);
                
            }

            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    
    // En este método, modificamos la sala deseada con todos sus determinados datos nuevos introducidos.
    private void modificarSala(String nombreSala, int personasSala, String nombreMonitor) {
        
        int idMonitor=0;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("UPDATE gym_zone.salas SET nombre=?, capacidad_personas=?, id_monitor=? WHERE id_sala=?;");

            st.setString(1, nombreSala);
            st.setInt(2, personasSala);
            
            
            if(nombreMonitor!=null) {
            
                idMonitor=devolverIdMonitor(nombreMonitor.split(" ")[0], nombreMonitor.split(" ")[1]+" "+nombreMonitor.split(" ")[2]);
            
                st.setInt(3, idMonitor);
                
            } else {
                
                st.setNull(3, java.sql.Types.INTEGER);
                
            }
            
            st.setInt(4, idSala);

            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    
    // En este método, registramos el monitor al sistema con sus respectivos datos introducidos.
    private void registrarMonitor(String tipoRutina) {
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("INSERT INTO gym_zone.monitores (dni, funcion) VALUES(?, ?);");

            st.setString(1, dniUsuario);
            st.setString(2, tipoRutina);

            st.executeUpdate();
 
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    // En este método, realizamos el registro del usuario al sistema con sus respectivos datos introducidos.
    private void realizarRegistroUsuario(String nombreApellidos, String nombreUsuario, String genero, String correo, String contrasenna) {
        
       
        String nombre=nombreApellidos.split(" ")[0];
        String apellidos=nombreApellidos.split(" ")[1]+" "+nombreApellidos.split(" ")[2];
        
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO gym_zone.usuarios (dni, tipo, nombre, apellidos, genero, correo, nombre_usuario, contrasenna) VALUES(?, ?, ?, ?, ?, ?, ?, ?);");

            st.setString(1, dniUsuario);
            st.setString(2, rolUsuario);
            st.setString(3, nombre);
            st.setString(4, apellidos);
            st.setString(5, genero);
            st.setString(6, correo);
            st.setString(7, nombreUsuario);
            st.setString(8, contrasenna);
            
            st.executeUpdate();
      

           
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
        seleccionarVentanaRegistroUsuario();
    }
    
    // Este método se encarga de navegar hacia la pantalla de registro según el rol.
    private void seleccionarVentanaRegistroUsuario() {
        
        this.jDialog_Registrarse.setVisible(false);
        
        switch(rolUsuario) {
            
            case "Cliente" -> { 
                
                reiniciarCamposRegistroCliente();
                
                this.jDialog_RegistrarseCliente.setVisible(true);
   
                }
            
            case "Nutricionista" -> this.jDialog_RegistrarseNutricionista.setVisible(true);
            
            case "Monitor" -> this.jDialog_RegistrarseMonitor.setVisible(true); 
                
            case "Administrador" -> {
                
                this.jDialog_Inicio.setVisible(true);
                
                registrarMonitor("Administracion");
            }
            
        }
        
    }
    
    // Este método comprueba de que el campo de numero_cuenta tenga el formato de ello.
    private boolean esNumeroCuenta(String numeroCuenta) {
        
        boolean esNumeroCuenta=false;
        
        if(numeroCuenta.matches(REGEZ_NUMERO_CUENTA)) {
            
            esNumeroCuenta=true;
            
        }
        
        return esNumeroCuenta;
        
    }
    
    // Este método comprueba de que el campo es un número entero y positivo.
    private boolean comprobarEnteroPositivo(String campo) {
        
        boolean esEntero=true;
        
        for (int i = 0; i < campo.length() && esEntero; i++) {
            char c = campo.charAt(i);
            
            if (!Character.isDigit(c)) {
                esEntero= false;
            }
        }
        
        return esEntero;
        
    }
    
    // Este método comprueba de que el campo es un número decimal y positivo.
    private boolean comprobarDoblePositivo(String campo) {
        
        boolean esDoble=true;
        boolean puntoDecimalEncontrado = false;
        
        for (int i = 0; i < campo.length() && esDoble; i++) {
            char c = campo.charAt(i);
            
            if (c == '.') {
                
                if (puntoDecimalEncontrado) {
                    
                    esDoble = false; 
                   
                }
                
                puntoDecimalEncontrado = true;
                
            } else if (!Character.isDigit(c)) {
                
                esDoble = false;
           
            }
        }
        
        return esDoble;
        
    }
    
    // Este método se encarga de mostrar los datos del usuario y los botones para que pueda modificarlos.
    private void reiniciarPerfilUsuario() {
        
        String nombreApellidos=devolverNombreApellidos();
        String correo=devolverCorreo();
        String contrasenna=devolverContrasenna();
        String nombreUsuario=devolverNombreUsuario();
        
        jLabel_PU_NombreApellidos.setText(nombreApellidos);
        
        jLabel_PU_NombreUsuario.setText(nombreUsuario);
        
        jTextField3.setText("");
        jTextField3.setVisible(false);
        jTextField3.setEnabled(false);
        
        jLabel73.setText("Nombre de usuario:");
        
        jButton_PU_Editar.setVisible(false);
        jButton_PU_Editar.setEnabled(false);
        
        jLabel_PU_EditarNombreUsuario.setVisible(true);
        jLabel_PU_EditarNombreUsuario.setEnabled(true);
        
        jLabel_PU_EditarCorreo.setVisible(true);
        jLabel_PU_EditarCorreo.setEnabled(true);
        
        jLabel_PU_EditarContrasenna.setVisible(true);
        jLabel_PU_EditarContrasenna.setEnabled(true);
        
        jLabel66.setVisible(true);
        jLabel66.setEnabled(true);
        
        jLabel_PU_Correo.setText(correo);
        jLabel_PU_Correo.setVisible(true);
        jLabel_PU_Correo.setEnabled(true);
        
        jLabel64.setVisible(true);
        jLabel64.setEnabled(true);
        
        jLabel_PU_Contrasenna.setText(contrasenna);
        jLabel_PU_Contrasenna.setVisible(true);
        jLabel_PU_Contrasenna.setEnabled(true);
        
    }
    
    // Este método se encarga de mostrar los datos del cliente y los botones para que pueda modificarlos.
    private void reiniciarPerfilCliente() {
       
        String objetivoCliente=devolverObjetivoCliente();
        int alturaCliente=devolverAlturaCliente();
        double pesoCliente=devolverPesoCliente();
        int edadCliente=devolverEdadCliente();
        
        String nombreApellidosCliente=devolverNombreApellidos();
        
        jLabel_PC_NombreApellidos.setText(nombreApellidosCliente);
        
        jLabel_PC_Edad.setText(edadCliente+" años");
        
        jLabel_PC_Objetivo.setVisible(true);
        jLabel_PC_Objetivo.setEnabled(true);
        jLabel_PC_Objetivo.setText(objetivoCliente);
        
        jTextField43.setText("");
        jTextField43.setVisible(false);
        jTextField43.setEnabled(false);
        
        jComboBox38.setSelectedIndex(0);
        jComboBox38.setVisible(false);
        jComboBox38.setEnabled(false);
        
        jLabel177.setText("Objetivo corporal:");

        jButton_PC_Editar.setVisible(false);
        jButton_PC_Editar.setEnabled(false);
        
        jLabel_PC_EditarObjetivo.setVisible(true);
        jLabel_PC_EditarObjetivo.setEnabled(true);
        
        jLabel_PC_EditarAltura.setVisible(true);
        jLabel_PC_EditarAltura.setEnabled(true);
        
        jLabel_PC_EditarPeso.setVisible(true);
        jLabel_PC_EditarPeso.setEnabled(true);
        
        jLabel176.setVisible(true);
        jLabel176.setEnabled(true);
        
        jLabel_PC_Altura.setText(alturaCliente+" cm");
        jLabel_PC_Altura.setEnabled(true);
        jLabel_PC_Altura.setVisible(true);
        
        jLabel175.setVisible(true);
        jLabel175.setEnabled(true);
        
        jLabel_PC_Peso.setText(pesoCliente+" kg");
        jLabel_PC_Peso.setEnabled(true);
        jLabel_PC_Peso.setVisible(true);
        
        
    }
    
    // Este método se encarga de modificar el campo nuevo del usuario, los cuáles son los campos clave.
    private void modificarCampo(String campoBD, String campoModificar) {
        
        
        try {
            PreparedStatement st = con.prepareStatement("UPDATE gym_zone.usuarios SET "+campoBD+"=? WHERE dni=?;");

            st.setString(1, campoModificar);
            st.setString(2, dniUsuario);
            
            st.executeUpdate();
      

           
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    // Este método se encarga de devolver el id del usuario, según el campo clave pasado por el parámetro.
    private void recibirIdUsuario() {
        
        String campoBD=devolverCampoUsuarioBD();
        String campoID=devolverCampoIDUsuarioBD();
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT "+campoID+" FROM gym_zone."+campoBD+" where dni=?");

            st.setString(1, dniUsuario);

            ResultSet rs = st.executeQuery();

            if(rs.next()){

                idUsuario=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, pintamos en la tabla todos los productos registrados en el sistema.
    private void pintarTablaProductos() {
        
       DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio por Unidad");
        modelo.addColumn("Cantidad Disponible");
        modelo.addColumn("Modificar");
        modelo.addColumn("Eliminar");
        jTable7.setModel(modelo);
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT nombre, precio_unidad, cantidad_disponible FROM gym_zone.productos");

            ResultSet rs = st.executeQuery();

            agregarFilaTablaProductos(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    // En este método, pintamos en la tabla todos los pagos de los productos registrados en el sistema.
    private void pintarTablaPagosProducto() {
        
       DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio por Unidad");
        modelo.addColumn("Cantidad Disponible");
        modelo.addColumn("Cantidad A Comprar");
        modelo.addColumn("Pagar");
        jTable13.setModel(modelo);
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT nombre, precio_unidad, cantidad_disponible FROM productos WHERE cantidad_disponible > 0;");

            ResultSet rs = st.executeQuery();

            agregarFilaTablaPagosProductos(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    // En este método, pintamos en la tabla todas las ventas registrados en el sistema.
    private void pintarTablaVentas() {
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre Producto");
        modelo.addColumn("Nombre Cliente");
        modelo.addColumn("Cantidad Vendida");
        modelo.addColumn("Fecha Venta");
        jTable10.setModel(modelo);
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT p.nombre AS nombre_producto, CONCAT(u.nombre, ' ', u.apellidos) AS nombre_cliente,\n" +
            " v.cantidad_vendida, v.fecha_venta FROM ventas v JOIN productos p ON v.id_producto = p.id_producto JOIN " +
            "clientes c ON v.id_cliente = c.id_cliente JOIN usuarios u ON c.dni = u.dni;");

            ResultSet rs = st.executeQuery();

            agregarFilaTablaVentas(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    // En este método, pintamos en la tabla todos los pagos de las reservas registradas en el sistema.
    private void pintarTablaReservas() {
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre Cliente");
        modelo.addColumn("Nombre Sala");
        modelo.addColumn("Fecha Reserva");
        modelo.addColumn("Hora Inicio");
        modelo.addColumn("Hora Salida");
        modelo.addColumn("Estado");
        jTable11.setModel(modelo);
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT CONCAT(u.nombre, ' ', u.apellidos) AS nombre_completo, s.nombre AS nombre_sala,\n" +
            " DATE(r.fecha_entrada) AS fecha, TIME(r.fecha_entrada) AS hora_entrada, TIME(r.fecha_salida) AS hora_salida,\n" +
            " r.estado FROM reservas r JOIN clientes c ON r.id_cliente = c.id_cliente JOIN usuarios u ON c.dni = u.dni\n" +
            " JOIN salas s ON r.id_sala = s.id_sala;");

            ResultSet rs = st.executeQuery();

            agregarFilaTablaReservas(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    // En este método, pintamos en la tabla todos los pagos de los servicios registrados en el sistema.
    private void pintarTablaPagosServicio() {
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre Cliente");
        modelo.addColumn("Nombre Servicio");
        modelo.addColumn("Fecha Pago");
        modelo.addColumn("Estado");
        jTable12.setModel(modelo);
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT CONCAT(u.nombre, ' ', u.apellidos) AS nombre_completo, s.nombre AS nombre_servicio,\n" +
            " ps.fecha_pagado, ps.estado FROM pagos_servicios ps JOIN clientes c ON ps.id_cliente = c.id_cliente JOIN usuarios u ON c.dni = u.dni\n" +
            " JOIN servicios s ON ps.id_servicio = s.id_servicio;");

            ResultSet rs = st.executeQuery();

            agregarFilaTablaPagosServicio(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    // En este método, pintamos en la tabla todos los servicios registrados en el sistema.
    private void pintarTablaServicios() {
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Dias de duracion");
        modelo.addColumn("Modificar");
        modelo.addColumn("Eliminar");
        jTable8.setModel(modelo);
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT nombre, precio, dias_duracion FROM gym_zone.servicios");

            ResultSet rs = st.executeQuery();

            agregarFilaTablaServicios(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    // En este método, pintamos en la tabla todos los servicios para mostrar a los clientes registrados en el sistema.
    private void pintarTablaServiciosCliente() {
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Dias de duracion");
        modelo.addColumn("Pagar");
        modelo.addColumn("Cancelar");
        jTable8.setModel(modelo);
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT nombre, precio, dias_duracion, id_monitor FROM gym_zone.servicios;");

            ResultSet rs = st.executeQuery();

            agregarFilaTablaServiciosCliente(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    // En este método, pintamos en la tabla de todos los datos acerca de las reservas para que puede registrar reservas el cliente en el sistema.
    private void pintarTablaReservasCliente() {
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Sala");
        modelo.addColumn("Hora Entrada");
        modelo.addColumn("Hora Salida");
        modelo.addColumn("Estado");
        modelo.addColumn("Cancelar");
        modelo.addColumn("Volver a Reservar");
        jTable15.setModel(modelo);
        
        PreparedStatement st;
        ResultSet rs;
        
        try {
            
            String query = "SELECT s.nombre AS nombre_sala, " +
                       "DATE_FORMAT(r.fecha_entrada, '%H:%i') AS horaEntrada, " +
                       "DATE_FORMAT(r.fecha_salida, '%H:%i') AS horaSalida, " +
                       "r.estado " +
                       "FROM reservas r " +
                       "JOIN salas s ON r.id_sala = s.id_sala " +
                       "WHERE r.id_cliente = ?";

            st = con.prepareStatement(query);
            st.setInt(1, idUsuario);
            rs = st.executeQuery();

            agregarFilaTablaReservasCliente(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    // En este método, pintamos en la tabla todas las salas registradas en el sistema.
    private void pintarTablaSalas() {
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Capacidad de personas");
        modelo.addColumn("Monitor asignado");
        modelo.addColumn("Modificar");
        modelo.addColumn("Eliminar");
        jTable8.setModel(modelo);
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT s.nombre AS nombre_sala, s.capacidad_personas, COALESCE(CONCAT(u.nombre, ' ', u.apellidos), 'Ninguno') AS nombre_monitor\n" +
            "FROM salas s LEFT JOIN monitores m ON s.id_monitor = m.id_monitor LEFT JOIN usuarios u ON m.dni = u.dni;");

            ResultSet rs = st.executeQuery();

            agregarFilaTablaSalas(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    // En este método, agregamos todos los datos acerca de las venta por cada fila de la tabla. 
    private void agregarFilaTablaVentas(DefaultTableModel modelo, ResultSet rs) throws SQLException {
        
        Object[] fila;

        if (!rs.next()) {
                    // Si no hay resultados, mostrar un mensaje en un JDialog
                    JOptionPane.showMessageDialog(null, "No se ha encontrado ninguna venta", "Resultado de la búsqueda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            
            do {
                
                fila = new Object[4];
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getInt(3);
                fila[3]= rs.getDate(4);

                modelo.addRow(fila);
 
            } while (rs.next());
        }

        jTable10.setModel(modelo);

        // Centrar las columnas después de agregar las filas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable10.getColumnCount(); i++) {
            jTable10.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        
    }
    
    // En este método, agregamos todos los datos acerca de las reservas por cada fila de la tabla. 
    private void agregarFilaTablaReservas(DefaultTableModel modelo, ResultSet rs) throws SQLException {
        
        Object[] fila;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        if (!rs.next()) {
                    // Si no hay resultados, mostrar un mensaje en un JDialog
                    JOptionPane.showMessageDialog(null, "No se ha encontrado ninguna reserva", "Resultado de la búsqueda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            
            do {
                
                fila = new Object[6];
               
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2); 
                fila[2] = rs.getDate(3); 
                fila[3] = rs.getTime(4).toLocalTime().format(formatter);
                fila[4] = rs.getTime(5).toLocalTime().format(formatter);
                fila[5] = rs.getString(6);

                modelo.addRow(fila);
 
            } while (rs.next());
        }

        jTable11.setModel(modelo);

        // Centrar las columnas después de agregar las filas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable11.getColumnCount(); i++) {
            jTable11.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        
    }
    
    // En este método, agregamos todos los datos acerca de los pagos de servicios por cada fila de la tabla. 
    private void agregarFilaTablaPagosServicio(DefaultTableModel modelo, ResultSet rs) throws SQLException {
        
        Object[] fila;
        

        if (!rs.next()) {
                    
            JOptionPane.showMessageDialog(null, "No se ha encontrado ningun pago de servicio", "Resultado de la búsqueda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            
            do {
                
                fila = new Object[6];
               
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2); 
                fila[2] = rs.getDate(3); 
                fila[3] = rs.getString(4);

                modelo.addRow(fila);
 
            } while (rs.next());
        }

        jTable12.setModel(modelo);

        // Centrar las columnas después de agregar las filas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable12.getColumnCount(); i++) {
            jTable12.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        
    }
    
    // En este método, agregamos todos los datos acerca de los productos por cada fila de la tabla,
    // donde da la posibilidad de modificarlo o eliminarlo a través de un botón. 
    private void agregarFilaTablaProductos(DefaultTableModel modelo, ResultSet rs) throws SQLException {
        
        Object[] fila;

        if (!rs.next()) {
                    // Si no hay resultados, mostrar un mensaje en un JDialog
                    JOptionPane.showMessageDialog(null, "No se ha encontrado ningun producto", "Resultado de la búsqueda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            
            do {
                
                fila = new Object[5];
                fila[0] = rs.getString(1);
                fila[1] = rs.getDouble(2);
                fila[2] = rs.getInt(3);
                fila[3]= "Modificar";
                fila[4]= "Eliminar";

                modelo.addRow(fila);
 
            } while (rs.next());
        }

        jTable7.setModel(modelo);

        // Centrar las columnas después de agregar las filas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable7.getColumnCount(); i++) {
            jTable7.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        jTable7.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JTextField()) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                    JButton btn = new JButton("Modificar");

                    btn.addActionListener(e -> {

                        String nombreProductoMod = (String) table.getValueAt(row, 0);
                        Double precioProductoMod = (Double) table.getValueAt(row, 1);
                        int cantidadProductoMod = (int) table.getValueAt(row, 2);

                        jDialog_ConsultaProducto.setVisible(false);
                        jDialog_ModificarProducto.setVisible(true);
                       
                        jTextField29.setText(nombreProductoMod);
                        jTextField30.setText(cantidadProductoMod+"");
                        jTextField31.setText(precioProductoMod+"");
                        
                        idProducto=devolverIdProducto(nombreProductoMod);

                    });
                    return btn;

                }   

                @Override
                public Object getCellEditorValue() {
                    return "Modificar"; 
                }

        });
        
        jTable7.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JTextField()) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                    JButton btn = new JButton("Eliminar");

                    
                    btn.addActionListener(e -> {
                        
                    if (row >= 0 && row < table.getRowCount()) {
                        
                        String nombreProductoEliminar = (String) table.getValueAt(row, 0);

                        eliminarProducto(devolverIdProducto(nombreProductoEliminar));
                        
                        JOptionPane.showMessageDialog(null, "Se ha eliminado el producto correctamente.");


                        if (table.isEditing()) {
                            
                            table.getCellEditor().stopCellEditing();
                        }

                        SwingUtilities.invokeLater(() -> {
                            DefaultTableModel model = (DefaultTableModel) table.getModel();
                            model.removeRow(row);

                            model.fireTableDataChanged();
                        });
                    }
                    });
                    return btn;
                }
                    

                

                @Override
                public Object getCellEditorValue() {
                    return "Eliminar"; 
                }

        });
        
    
        
    }
    
    // En este método, agregamos todos los datos acerca de los pagos de productos por cada fila de la tabla,
    // donde da la posibilidad de pagarlos a través de un botón. 
    private void agregarFilaTablaPagosProductos(DefaultTableModel modelo, ResultSet rs) throws SQLException {
        
        Object[] fila;

        if (!rs.next()) {
                    // Si no hay resultados, mostrar un mensaje en un JDialog
                    JOptionPane.showMessageDialog(null, "No se ha encontrado ningun producto", "Resultado de la búsqueda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            
            do {
                
                fila = new Object[5];
                fila[0] = rs.getString(1);
                fila[1] = rs.getDouble(2);
                fila[2] = rs.getInt(3);
                fila[4]= "Pagar";

                modelo.addRow(fila);
 
            } while (rs.next());
        }

        jTable13.setModel(modelo);

        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable13.getColumnCount(); i++) {
            jTable13.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        jTable13.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JTextField()) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                    JButton btn = new JButton("Pagar");

                    btn.addActionListener(e -> {

                        
                       
                            Object valor = table.getValueAt(row, 3); 
                            
                            if (valor == null || !(valor instanceof String) || !comprobarEnteroPositivo((String) valor)) {
                                
                                JOptionPane.showMessageDialog(null, "Debes introducir la cantidad a comprar deseada. Recuerda que debe ser un número entero positivo");
                                
                            } else {
                            
                            String textoCantidad=(String)valor;
                            
                            String nombreProducto = (String) table.getValueAt(row, 0);
                        
                            double precioProducto = (double) table.getValueAt(row, 1);
                            
                            int cantidadComprar = Integer.parseInt(textoCantidad);
          
                            double precioFinal=precioProducto*cantidadComprar;
                        
                            realizarCompraProducto(cantidadComprar, precioFinal, devolverIdProducto(nombreProducto));
                        
                            pintarTablaPagosProducto();
                        
                            
                        }
                        
                       
                    });
                    return btn;

                }   

                @Override
                public Object getCellEditorValue() {
                    return "Pagar"; 
                }

        });
        
    
        
    }
    
    // En este método, agregamos todos los datos acerca de los servicios por cada fila de la tabla,
    // donde da la posibilidad de modificarlo o eliminarlo a través de un botón. 
    private void agregarFilaTablaServicios(DefaultTableModel modelo, ResultSet rs) throws SQLException {
        
        Object[] fila;

        if (!rs.next()) {
                    // Si no hay resultados, mostrar un mensaje en un JDialog
                    JOptionPane.showMessageDialog(null, "No se ha encontrado ningun servicio", "Resultado de la búsqueda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            
            do {
                
                fila = new Object[5];
                fila[0] = rs.getString(1);
                fila[1] = rs.getDouble(2);
                fila[2] = rs.getInt(3);
                fila[3]= "Modificar";
                fila[4]= "Eliminar";

                modelo.addRow(fila);
 
            } while (rs.next());
        }

        jTable8.setModel(modelo);

        // Centrar las columnas después de agregar las filas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable8.getColumnCount(); i++) {
            jTable8.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        jTable8.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JTextField()) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                    JButton btn = new JButton("Modificar");

                    btn.addActionListener(e -> {

                        String nombreServicioMod = (String) table.getValueAt(row, 0);

                        int idServicioMod=devolverIdServicio(nombreServicioMod);
                            
                        if (puedeModificarServicio(idServicioMod)) {
                            
                            Double precioServicioMod = (Double) table.getValueAt(row, 1);
                            int cantidadDiasServicioMod = (int) table.getValueAt(row, 2);

                            jDialog_ConsultaServicio.setVisible(false);
                            jDialog_ModificarServicio.setVisible(true);
                       
                            jTextField33.setText(nombreServicioMod);
                            jTextField35.setText(precioServicioMod+"");
                            jTextField34.setText(cantidadDiasServicioMod+"");
                        
                            idServicio=idServicioMod;
                            
                        } else {
                            
                            JOptionPane.showMessageDialog(null, "No se puede modificar los principales servicios del sistema.", "Aviso", JOptionPane.WARNING_MESSAGE);

                        }

                    });
                    return btn;

                }   

                @Override
                public Object getCellEditorValue() {
                    return "Modificar"; 
                }

        });
        
        jTable8.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JTextField()) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                    JButton btn = new JButton("Eliminar");

            
                    btn.addActionListener(e -> {
                        
                    if (row >= 0 && row < table.getRowCount()) {
                        
                        
                        String nombreServicioEliminar = (String) table.getValueAt(row, 0);

                        int idServicioEliminar=devolverIdServicio(nombreServicioEliminar);
                        
                        if (puedeModificarServicio(idServicioEliminar)) {
                            
                            eliminarServicio(idServicioEliminar);
                            
                            JOptionPane.showMessageDialog(null, "Se ha eliminado el servicio correctamente.");
                            
                            if (table.isEditing()) {

                                table.getCellEditor().stopCellEditing();
                            }

                            SwingUtilities.invokeLater(() -> {
                                DefaultTableModel model = (DefaultTableModel) table.getModel();
                                model.removeRow(row);

                                model.fireTableDataChanged();
                            });
                            
                            
                        } else {
                            JOptionPane.showMessageDialog(null, "No se puede eliminar los principales servicios del sistema.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        }
  
                    }
                    });
                    return btn;
                }
            
                @Override
                public Object getCellEditorValue() {
                    return "Eliminar"; 
                }

        });
        
    
        
    }
    
    // Este método permite comprobar si se puede modificar el servicio, ya que si es un servicio principal,
    // esté no puede ser modificado para que no interrumpa el funcionamiento de la aplicación.
    private boolean puedeModificarServicio(int idServicio) {
        
        return !(idServicio >= 1 && idServicio <= 4);
    }
    
    // En este método, agregamos todos los datos acerca de los servicios que se muestran a los clientes por cada fila de la tabla,
    // donde da la posibilidad de pagarlos o cancelarlos a través de un botón. 
    private void agregarFilaTablaServiciosCliente(DefaultTableModel modelo, ResultSet rs) throws SQLException {
        
        Object[] fila;

        if (!rs.next()) {
                    // Si no hay resultados, mostrar un mensaje en un JDialog
                    JOptionPane.showMessageDialog(null, "No se ha encontrado ningun servicio", "Resultado de la búsqueda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            
            do {
                
                fila = new Object[5];
                fila[0] = rs.getString(1);
                fila[1] = rs.getDouble(2);
                fila[2] = rs.getInt(3);
                fila[3]= "Pagar";
                fila[4]= "Cancelar";

                modelo.addRow(fila);
 
            } while (rs.next());
        }

        jTable14.setModel(modelo);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable14.getColumnCount(); i++) {
            jTable14.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        jTable14.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JTextField()) {
            
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                    JButton btn = new JButton("Pagar");

                    btn.addActionListener(e -> {

                        String nombreServicio= (String) table.getValueAt(row, 0);
                        
                        double precioServicio=(double) table.getValueAt(row, 1);
                        
                        int diasServicio=(int) table.getValueAt(row, 2);
                        
                        idServicio=devolverIdServicio(nombreServicio);
                        
                        if(comprobarServicioPagado(idServicio)) {
                            
                            JOptionPane.showMessageDialog(null, "El servicio ya está pagado.");
                            
                        } else if(comprobarServicioPagado(SERVICIO_RESERVA_MONITOR) && nombreServicio.equals(SERVICIO_RESERVA)) {
                            
                            JOptionPane.showMessageDialog(null, "No puedes pagar el servicio de Gimnasio, porque ya has pagado el servicio de Entrenamiento Monitoreado.\n Si deseas pagar este servicio, debes de cancelar el servicio de Entrenamiento Monitoreado");
 
                        } else if(comprobarServicioPagado(SERVICIO_RESERVA) && nombreServicio.equals(SERVICIO_RESERVA_MONITOR)) {
                            
                            JOptionPane.showMessageDialog(null, "No puedes pagar el servicio de Entrenamiento Monitoreado, porque ya has pagado el servicio de Gimnasio.\n Si deseas pagar este servicio, debes de cancelar el servicio de Gimnasio");
                            
                        } else {
                            
                            realizarPagoServicio(idServicio, precioServicio, diasServicio);

                            pintarTablaServiciosCliente();
                            
                        }
                        

                    });
                    return btn;

                }   

                @Override
                public Object getCellEditorValue() {
                    return "Pagar"; 
                }

        });
        
        jTable14.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JTextField()) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                    JButton btn = new JButton("Cancelar");

                    btn.addActionListener(e -> {

                        String nombreServicioEliminar = (String) table.getValueAt(row, 0);

                        idServicio=devolverIdServicio(nombreServicioEliminar);
                        
                        double precioServicio=(double) table.getValueAt(row, 1);
                        
                        if(comprobarServicioCancelado(idServicio)) {
                            
                            JOptionPane.showMessageDialog(null, "El servicio ya está cancelado");

                        } else {
                            
                            realizarCancelarServicio(idServicio, precioServicio);
                            
                            JOptionPane.showMessageDialog(null, "El servicio se ha cancelado correctamente.");
                            
                            if ( (nombreServicioEliminar.equals(SERVICIO_RESERVA_MONITOR)) || nombreServicioEliminar.equals(SERVICIO_RESERVA) ) {
                                
                                borrarReservasCliente();
                            }
                            
                            
                            pintarTablaServiciosCliente();
                        }
                                        
                        
                    });
                    return btn;

                }   

                @Override
                public Object getCellEditorValue() {
                    return "Cancelar"; 
                }

        });
        
    
        
    }
    
    // En este método, comprobamos si el servicio escogido por el cliente ha sido pagado.
    private boolean comprobarServicioPagado(String nombreServicio) {
        
        PreparedStatement st;
        ResultSet rs;
        boolean servicioPagado = false;

        String query = "SELECT ps.id_servicio " +
                       "FROM pagos_servicios ps " +
                       "JOIN servicios s ON ps.id_servicio = s.id_servicio " +
                       "WHERE ps.id_cliente = ? AND s.nombre = ? AND ps.estado = 'Pagada'";

        try {
            st = con.prepareStatement(query);
            st.setInt(1, idUsuario);
            st.setString(2, nombreServicio);
            rs = st.executeQuery();

            if (rs.next()) {
               servicioPagado = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return servicioPagado;
    }
    
    // En este método, agregamos todos los datos acerca de las reservas hechas por el cliente por cada fila de la tabla,
    // donde da la posibilidad de cancelar o volver a reservar dicha reserva a través de un botón.
    private void agregarFilaTablaReservasCliente(DefaultTableModel modelo, ResultSet rs) throws SQLException {
        
        Object[] fila;

        if (!rs.next()) {
                    // Si no hay resultados, mostrar un mensaje en un JDialog
                    JOptionPane.showMessageDialog(null, "No se ha encontrado ningun servicio", "Resultado de la búsqueda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            
            do {
                
   
                fila = new Object[6];
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getString(4);
                fila[4]= "Cancelar";
                fila[5]= "Reservar";

                modelo.addRow(fila);
 
            } while (rs.next());
        }

        jTable15.setModel(modelo);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable14.getColumnCount(); i++) {
            jTable15.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        jTable15.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JTextField()) {
            
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                    JButton btn = new JButton("Cancelar");

                    btn.addActionListener(e -> {

                        
                        
                        String nombreSalaCancelar = (String) table.getValueAt(row, 0);
                        String horaInicioCancelar = (String) table.getValueAt(row, 1);
                        String horaSalidaCancelar = (String) table.getValueAt(row, 2);
                        
                        LocalDate fechaHoy = LocalDate.now();
                        
                        String[] partesInicio = horaInicioCancelar.split(":");
                        String[] partesFin = horaSalidaCancelar.split(":");

                        int horaInicio = Integer.parseInt(partesInicio[0]);
                        int horaFin = Integer.parseInt(partesFin[0]);

                        LocalDateTime fechaHoraInicio = fechaHoy.atTime(horaInicio, 0, 0);
                        LocalDateTime fechaHoraFin = fechaHoy.atTime(horaFin, 0, 0);
                        
                        if (comprobarFranjaHorariaPasada(fechaHoraFin)) {
                             
                            JOptionPane.showMessageDialog(null, "Ha pasado la franja horaria, no puedes cancelar la reserva.");
            
                        } else {
                             
                            int idSala=devolverIdSala(nombreSalaCancelar);
                        
                            if(comprobarReservaRealizada(idSala,  fechaHoraInicio, fechaHoraFin)) {
                        
                                cambiarEstadoReserva(idSala,  fechaHoraInicio, fechaHoraFin, "Cancelada");
                                pintarTablaReservasCliente();
                            
                            } else {
                            
                                JOptionPane.showMessageDialog(null, "La reserva ya esta cancelada");
                            
                            }
   
                        }
                  
                    });
                    return btn;

                }   

                @Override
                public Object getCellEditorValue() {
                    return "Cancelar"; 
                }

        });
        
        jTable15.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(new JTextField()) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                    JButton btn = new JButton("Reservar");

                    btn.addActionListener(e -> {

                        String nombreSalaCancelar = (String) table.getValueAt(row, 0);
                        String horaInicioCancelar = (String) table.getValueAt(row, 1);
                        String horaSalidaCancelar = (String) table.getValueAt(row, 2);
                        
                        LocalDate fechaHoy = LocalDate.now();
                        
                        String[] partesInicio = horaInicioCancelar.split(":");
                        String[] partesFin = horaSalidaCancelar.split(":");

                        int horaInicio = Integer.parseInt(partesInicio[0]);
                        int horaFin = Integer.parseInt(partesFin[0]);

                        LocalDateTime fechaHoraInicio = fechaHoy.atTime(horaInicio, 0, 0);
                        LocalDateTime fechaHoraFin = fechaHoy.atTime(horaFin, 0, 0);
                        
                        if (comprobarFranjaHorariaPasada(fechaHoraFin)) {
                             
                            JOptionPane.showMessageDialog(null, "Ha pasado la franja horaria, no puedes volver a hacer la reserva.");
            
                        } else {
                            
                            
                            int idSala=devolverIdSala(nombreSalaCancelar);
                        
                            if(comprobarReservaRealizada(idSala,  fechaHoraInicio, fechaHoraFin)) {

                                JOptionPane.showMessageDialog(null, "La reserva ya esta realizada");


                            } else {


                                if(esTurnoDia(horaInicioCancelar) && comprobarReservaDia()) {

                                    JOptionPane.showMessageDialog(null, "Tienes una reserva ya realizada en turno de mañana.\n Cancela dicha reserva para poder volver a realizar la reserva.");

                                } else if(esTurnoTarde(horaInicioCancelar) && comprobarReservaTarde()) {

                                    JOptionPane.showMessageDialog(null, "Tienes una reserva ya realizada en turno de tarde.\n Cancela dicha reserva para poder volver a realizar la reserva.");

                                } else {

                                    cambiarEstadoReserva(idSala, fechaHoraInicio, fechaHoraFin, "Realizada");
                                    pintarTablaReservasCliente();

                                }

                            }
                            
                        }
                        
                    });
                    return btn;

                }   

                @Override
                public Object getCellEditorValue() {
                    return "Reservar"; 
                }

        });
        
    
        
    }
    
    // En este método, comprobamos si el servicio se ha expirado, en el que si la fecha actual es igual a la fecha pagado,
    // la cúal es la fecha de expiración del servicio.
    private boolean comprobarServicioExpirado(String nombreServicio) {
        
        PreparedStatement st;
        ResultSet rs;
        boolean expirado = false;

        String query = "SELECT ps.fecha_pagado " +
                       "FROM pagos_servicios ps " +
                       "JOIN servicios s ON ps.id_servicio = s.id_servicio " +
                       "WHERE s.nombre = ? AND ps.id_cliente = ?";

        try {
            st = con.prepareStatement(query);
            st.setString(1, nombreServicio);
            st.setInt(2, idUsuario);
            rs = st.executeQuery();

            if (rs.next()) {
                LocalDate fechaExpiracion = rs.getDate("fecha_pagado").toLocalDate();
                LocalDate fechaActual = LocalDate.now();

                expirado = fechaActual.isAfter(fechaExpiracion);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return expirado;
    }
    
    // En este método se comprueba si la franja horaria ha terminado, es decir, la hora de salida es igual a la hora actual.
    private boolean comprobarFranjaHorariaPasada(LocalDateTime fechaHoraFin) {
        
        LocalDateTime fechaHoraActual = LocalDateTime.now();

        return fechaHoraActual.isAfter(fechaHoraFin);
    
    } 
    
    // En este método comprobamos que el turno es el turno de día, donde la hora de entrada es igual a las predeterminadas.
    private boolean esTurnoDia(String horaEntrada) {
        
        boolean esTurnoDia=false;
        
        if(horaEntrada.equals("8:00") || horaEntrada.equals("10:00") || horaEntrada.equals("12:00")) {
            
            esTurnoDia=true;
        }
        
        return esTurnoDia;
    } 
    
    // En este método comprobamos que el turno es el turno de tarde, donde la hora de entrada es igual a las predeterminadas.
    private boolean esTurnoTarde(String horaEntrada) {
        
        boolean esTurnoTarde=false;
        
        if(horaEntrada.equals("16:00") || horaEntrada.equals("18:00") || horaEntrada.equals("20:00")) {
            
            esTurnoTarde=true;
        }
        
        return esTurnoTarde;
    } 
    
    // En este método, cambiamos el estado de reserva deseada según es el estado que se requiere.
    private void cambiarEstadoReserva(int idSala, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, String estadoCambiar) {
        
        PreparedStatement st = null;

        try {
            String query = "UPDATE reservas " +
                           "SET estado = ? " +
                           "WHERE id_sala = ? AND id_cliente = ? AND fecha_entrada = ? AND fecha_salida = ?";

            st = con.prepareStatement(query);
            st.setString(1, estadoCambiar);
            st.setInt(2, idSala);
            st.setInt(3, idUsuario);
            st.setTimestamp(4, Timestamp.valueOf(fechaHoraInicio));
            st.setTimestamp(5, Timestamp.valueOf(fechaHoraFin));

            int rowsUpdated = st.executeUpdate();

            if (rowsUpdated > 0) {
                
                JOptionPane.showMessageDialog(null, "La reserva ha sido actualizada, se ha cambiado al estado " + estadoCambiar+" correctamente.");
                
            } else {
                
                JOptionPane.showMessageDialog(null,"No se encontró ninguna reserva para actualizar.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }
    
    // En este método, se comprueba la reserva si se ha realizado y esta registrado en el sistema.
    private boolean comprobarReservaRealizada(int idSala, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        
        boolean reservaRealizada = false;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            String query = "SELECT r.id_cliente " +
                           "FROM reservas r " +
                           "WHERE r.id_sala = ? AND r.fecha_entrada = ? AND r.fecha_salida = ? AND r.estado = 'Realizada' AND r.id_cliente = ?";

            st = con.prepareStatement(query);
            st.setInt(1, idSala);
            st.setTimestamp(2, Timestamp.valueOf(fechaHoraInicio));
            st.setTimestamp(3, Timestamp.valueOf(fechaHoraFin));
            st.setInt(4, idUsuario);

            rs = st.executeQuery();

            if (rs.next()) {
                reservaRealizada = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 

        return reservaRealizada;
    }
    
    // En este método, agregamos todos los datos acerca de las salas por cada fila de la tabla,
    // donde puede modificar o eliminar el servicio seleccionado a través de un botón.
    private void agregarFilaTablaSalas(DefaultTableModel modelo, ResultSet rs) throws SQLException {
        
        Object[] fila;

        if (!rs.next()) {
                    // Si no hay resultados, mostrar un mensaje en un JDialog
                    JOptionPane.showMessageDialog(null, "No se ha encontrado ninguna sala", "Resultado de la búsqueda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            
            do {
                
                fila = new Object[5];
                fila[0] = rs.getString(1);
                fila[1] = rs.getInt(2);
                fila[2] = rs.getString(3);
                fila[3]= "Modificar";
                fila[4]= "Eliminar";

                modelo.addRow(fila);
 
            } while (rs.next());
        }

        jTable9.setModel(modelo);

        // Centrar las columnas después de agregar las filas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable9.getColumnCount(); i++) {
            jTable9.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        jTable9.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JTextField()) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                    JButton btn = new JButton("Modificar");

                    btn.addActionListener(e -> {

                        String nombreSalaMod = (String) table.getValueAt(row, 0);
                        int capacidadSalaMod = (int) table.getValueAt(row, 1);
                        String nombreMonitorSalaMod = (String) table.getValueAt(row, 2);

                        jDialog_ConsultaSala.setVisible(false);
                        jDialog_ModificarSala.setVisible(true);
                       
                        jTextField38.setText(nombreSalaMod);
                        jTextField37.setText(capacidadSalaMod+"");
                        
                        mostrarListaMonitoresSala(jComboBox22);
                        
                        jComboBox22.setSelectedItem(nombreMonitorSalaMod);
                        
                        idSala=devolverIdSala(nombreSalaMod);

                    });
                    return btn;

                }   

                @Override
                public Object getCellEditorValue() {
                    return "Modificar"; 
                }

        });
        
        jTable9.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JTextField()) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                    JButton btn = new JButton("Eliminar");

                    btn.addActionListener(e -> {

                        if (row >= 0 && row < table.getRowCount()) {

                            String nombreSalaEliminar = (String) table.getValueAt(row, 0);

                            eliminarSala(devolverIdSala(nombreSalaEliminar));

                            JOptionPane.showMessageDialog(null, "Se ha eliminado la sala correctamente.");

                            if (table.isEditing()) {

                                table.getCellEditor().stopCellEditing();
                            }

                            SwingUtilities.invokeLater(() -> {
                                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                                    model.removeRow(row);

                                    model.fireTableDataChanged();
                            });

                        }
                        });
                        return btn;
                    }
            

                @Override
                public Object getCellEditorValue() {
                    return "Eliminar"; 
                }

        });
        
    
        
    }
    
    // Este método se encarga de borrar las reservas realizadas de todos los clientes en los días anteriores frente al día actual.
    private void borrarReservasDiasAnteriores() {
        
        PreparedStatement st;

        try {
 
            String query = "DELETE FROM reservas WHERE fecha_entrada < CURDATE()";

            st = con.prepareStatement(query);

            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Este método borrar las reservas del cliente, cuando esté cancele el servicio de las reservas.
    private void borrarReservasCliente() {
        
         PreparedStatement st;

        try {
 
            String query = "DELETE FROM reservas WHERE id_cliente=?";

            st = con.prepareStatement(query);

            st.setInt(1, idUsuario);
            
            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // Este método, comprueba si hay reservas de días anteriores.
    private boolean comprobarReservasDiasAnteriores() {
        
        PreparedStatement st;
        
        boolean hayReservaAnterior=false;

        try {
 
            String query = "SELECT * FROM reservas WHERE fecha_entrada < CURDATE()";

            st = con.prepareStatement(query);

            ResultSet rs = st.executeQuery();
            
            if(rs.next()) {
                
                hayReservaAnterior=true;
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return hayReservaAnterior;
    }
    
    // En este método, pintamos en la tabla todos los datos acerca de los ejercicios según el grupo muscular deseado por cada fila de la tabla.
    // Además, da la posibilidad de seleccionar el ejercicio deseado a través de un botón.
    private void pintarTablaEjerciciosConGM(JTable tabla, String grupoMuscular) {
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Musculos implicados");
        modelo.addColumn("Seleccionar ejercicio");
        tabla.setModel(modelo);
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT e.nombre AS nombre_ejercicio, e.descripcion AS descripcion_ejercicio,\n" +
"GROUP_CONCAT(m.nombre) AS musculos_implicados FROM ejercicios e INNER JOIN  ejercicios_musculos em ON e.id_ejercicio = em.id_ejercicio\n" +
"INNER JOIN musculos m ON em.id_musculo = m.id_musculo INNER JOIN grupos_musculares gm ON m.id_grupo_muscular = gm.id_grupo_muscular\n" +
"WHERE gm.nombre = ? GROUP BY e.nombre, e.descripcion;");

            st.setString(1, grupoMuscular);

            ResultSet rs = st.executeQuery();

            agregarFilaTablaEjercicios(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, pintamos en la tabla todos los datos acerca de los ejercicios deseado por cada fila de la tabla.
    // Además, da la posibilidad de seleccionar el ejercicio deseado a través de un botón.
    private void agregarFilaTablaEjercicios(DefaultTableModel modelo, ResultSet rs) throws SQLException {
        
        Object[] fila;

        while (rs.next()) {

            fila = new Object[4];
            fila[0] = rs.getString(1);
            fila[1] = rs.getString(2);
            fila[2] = rs.getString(3);
            fila[3]= "Seleccionar";

            modelo.addRow(fila);
        }


        jTable1.setModel(modelo);

        // Centrar las columnas después de agregar las filas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Agregar ActionListener directamente al editor de celdas
        jTable1.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JTextField()) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                if(!table.getValueAt(row, 3).equals("Seleccionado")) {

                    JButton btn = new JButton("Seleccionar");

                    btn.addActionListener(e -> {

                        if (!btn.getText().equals("Seleccionado")) {

                            String nombre = (String) table.getValueAt(row, 0);

                            // Verificar si el ejercicio ya ha sido seleccionado previamente
                            if (!ejerciciosSeleccionados.contains(nombre)) {
                                ejerciciosSeleccionados.add(nombre);
                            }

                            btn.setText("Seleccionado");
                            btn.setEnabled(false);
                            // Cambiar el texto de la celda
                            table.setValueAt("Ejercicio Seleccionado", row, 3);

                        }
                    });
                    return btn;
                }

                JLabel label = new JLabel("Ejercicio Seleccionado");
                return label;
                }   

                @Override
                public Object getCellEditorValue() {
                    return "Seleccionado"; // Devuelve el texto del botón
                }

        });
    }

    // En este método, pintamos en la tabla todos los datos acerca de los alimentos por cada fila de la tabla.
    // Además, da la posibilidad de seleccionar el alimento deseado a través de un botón.
    private void agregarFilaTablaAlimentos(DefaultTableModel modelo, ResultSet rs) throws SQLException {
        
        Object[] fila;

        while (rs.next()) {

            fila = new Object[6];
            fila[0] = rs.getString(1);
            fila[1] = rs.getString(2)+" kcal";
            fila[2] = rs.getString(3) +" g";
            fila[3] = rs.getString(4) +" g";
            fila[4] = rs.getString(5) +" g";
            fila[5]= "Seleccionar";

            modelo.addRow(fila);
        }


        jTable4.setModel(modelo);

        // Centrar las columnas después de agregar las filas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable4.getColumnCount(); i++) {
            jTable4.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Agregar ActionListener directamente al editor de celdas
        jTable4.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(new JTextField()) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                if(!table.getValueAt(row, 5).equals("Seleccionado")) {

                    JButton btn = new JButton("Seleccionar");

                    btn.addActionListener(e -> {

                        if (!btn.getText().equals("Seleccionado")) {

                            String nombre = (String) table.getValueAt(row, 0);

                           
                            if (!alimentosSeleccionados.contains(nombre)) {
                                alimentosSeleccionados.add(nombre);
                            }

                            btn.setText("Seleccionado");
                            btn.setEnabled(false);
                            
                            table.setValueAt("Seleccionado", row, 5);

                        }
                    });
                    return btn;
                }

                JLabel label = new JLabel("Seleccionado");
                return label;
                }   

                @Override
                public Object getCellEditorValue() {
                    return "Seleccionado"; 
                }

        });
    }
    
    // En este método, pintamos en la tabla todos los datos acerca de los ejercicios por cada fila de la tabla.
    // Además, da la posibilidad de seleccionar el ejercicio deseado a través de un botón.
    
    private void pintarTablaEjercicios(JTable tabla) {
        
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Musculos implicados");
        modelo.addColumn("Seleccionar ejercicio");
        tabla.setModel(modelo);
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT e.nombre AS nombre_ejercicio, e.descripcion AS descripcion_ejercicio,\n" +
"GROUP_CONCAT(m.nombre) AS musculos_implicados FROM ejercicios e INNER JOIN  ejercicios_musculos em ON e.id_ejercicio = em.id_ejercicio\n" +
"INNER JOIN musculos m ON em.id_musculo = m.id_musculo INNER JOIN grupos_musculares gm ON m.id_grupo_muscular = gm.id_grupo_muscular\n" +
"GROUP BY e.nombre, e.descripcion;");

            ResultSet rs = st.executeQuery();

            agregarFilaTablaEjercicios(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
    }
    
    // En este método, pintamos en la tabla todos los datos acerca de los alimentos según el grupo alimentario deseado por cada fila de la tabla.
    // Además, da la posibilidad de seleccionar el alimento deseado a través de un botón.
    private void pintarTablaAlimentosGA(JTable tabla, String grupoAlimentario) {
        
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Calorias por porcion");
        modelo.addColumn("Proteinas por porcion");
        modelo.addColumn("Carbohidratos por porcion");
        modelo.addColumn("Grasas por porcion");
        modelo.addColumn("Seleccionar alimento");
        tabla.setModel(modelo);
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT nombre, calorias_porcion, proteinas_porcion, "
                    + "carbohidratos_porcion, grasas_porcion FROM gym_zone.alimentos WHERE grupo_alimentacion=?;");

            st.setString(1, grupoAlimentario);
            
            ResultSet rs = st.executeQuery();

            agregarFilaTablaAlimentos(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
    }
    
    // En este método, pintamos en la tabla todos los datos acerca de los ejercicios según el grupo muscular deseado por cada fila de la tabla.
    // Además, da la posibilidad de seleccionar el alimento deseado a través de un botón.
    private void pintarTablaAlimentos(JTable tabla) {
        
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Calorias por porcion");
        modelo.addColumn("Proteinas por porcion");
        modelo.addColumn("Carbohidratos por porcion");
        modelo.addColumn("Grasas por porcion");
        modelo.addColumn("Seleccionar alimento");
        tabla.setModel(modelo);
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT nombre, calorias_porcion, proteinas_porcion, "
                    + "carbohidratos_porcion, grasas_porcion FROM gym_zone.alimentos;");

            ResultSet rs = st.executeQuery();

            agregarFilaTablaAlimentos(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
    }
    
    // En este método, devolvemos el número del último día de la rutina realizada.
    private int devolverDiaMaximoRutina() {
        
        int diaMax=0;
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT MAX(dia) AS dia_mayor FROM rutinas_ejercicios WHERE id_rutina=?;");

            st.setInt(1, idRutina);
            
            ResultSet rs = st.executeQuery();
            
            if(rs.next()) {
                
                diaMax=rs.getInt(1);
                
            }

            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return diaMax;
    }
    
    // En este método, devolvemos el número del último día de la dieta realizada.
    private int devolverDiaMaximoDieta() {
        
        int diaMax=0;
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT MAX(dia) AS dia_mayor FROM comidas WHERE id_dieta=?;");

            st.setInt(1, idDieta);
            
            ResultSet rs = st.executeQuery();
            
            if(rs.next()) {
                
                diaMax=rs.getInt(1);
                
            }

            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return diaMax;
    }
    
    // En este método, pintamos todos los datos en la tabla acerca de las rutinas por cada fila de la tabla,
    // donde podrá modificarla o eliminarla a través de un botón.
    private void pintarTablaRutinas() {
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Objetivo");
        modelo.addColumn("Autor");
        modelo.addColumn("Visualizar ejercicios");
        modelo.addColumn("Modificar rutina");
        modelo.addColumn("Eliminar rutina");
        jTable3.setModel(modelo);
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT rutinas.nombre AS nombre_rutina, rutinas.objetivo,\n" +
"       CONCAT(usuarios.nombre, ' ', usuarios.apellidos) AS nombre_monitor FROM rutinas INNER JOIN monitores ON "
        + "rutinas.id_monitor = monitores.id_monitor INNER JOIN usuarios ON monitores.dni = usuarios.dni;");

            ResultSet rs = st.executeQuery();

            agregarFilaTablaRutinas(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, pintamos todos los datos en la tabla acerca de las dietas por cada fila de la tabla,
    // donde podrá modificarla o eliminarla a través de un botón.
    private void pintarTablaDietas() {
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Objetivo");
        modelo.addColumn("Autor");
        modelo.addColumn("Visualizar alimentos");
        modelo.addColumn("Modificar dieta");
        modelo.addColumn("Eliminar dieta");
        jTable5.setModel(modelo);
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT dietas.nombre AS nombre_dieta, dietas.tipo, \n" +
        "CONCAT(usuarios.nombre, ' ', usuarios.apellidos) AS nombre_nutricionista FROM dietas \n" +
        "INNER JOIN nutricionistas ON dietas.id_nutricionista = nutricionistas.id_nutricionista \n" +
        "INNER JOIN usuarios ON nutricionistas.dni = usuarios.dni;");

            ResultSet rs = st.executeQuery();

            agregarFilaTablaDietas(modelo, rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, modificamos la rutina deseada con los nuevos datos a cambiar.
    private void modificarRutina(String nombreRutinaMod, String objetivoMod, String autorMod) {
        
        String[] nombreAp=autorMod.split(" ");

        int idMonitorSeleccionado=devolverIdMonitor(nombreAp[0], nombreAp[1] + " "+ nombreAp[2]);
        
        try {
            
            PreparedStatement st = con.prepareStatement("UPDATE gym_zone.rutinas SET nombre=?, objetivo=?, id_monitor=? WHERE id_rutina=?;");

            st.setString(1, nombreRutinaMod);
            st.setString(2, objetivoMod);
            st.setInt(3, idMonitorSeleccionado);
            st.setInt(4, idRutina);
            
            st.executeUpdate();

            
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
    }
    
    // En este método, modificamos la dieta deseada con los nuevos datos a cambiar.
    private void modificarDieta(String nombreDietaMod, String objetivoDietaMod, String autorDietaMod, String desDietaMod) {
        
        String[] nombreAp=autorDietaMod.split(" ");

        int idNutricionistaSeleccionado=devolverIdNutricionista(nombreAp[0], nombreAp[1] + " "+ nombreAp[2]);
        
        try {
            
            PreparedStatement st = con.prepareStatement("UPDATE gym_zone.dietas SET nombre=?, tipo=?, descripcion=?, id_nutricionista=? WHERE id_dieta=?;");

            st.setString(1, nombreDietaMod);
            st.setString(2, objetivoDietaMod);
            st.setString(3, desDietaMod);
            st.setInt(4, idNutricionistaSeleccionado);
            st.setInt(5, idDieta);
            
            st.executeUpdate();

            
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
    }
    
    // En este método, recibimimos el id del monitor a través de su nombre y apellidos.
    private int devolverIdMonitor(String nombre, String apellidos) {
        
        int idMonitor=0;

        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT m.id_monitor FROM monitores m JOIN usuarios u ON m.dni = u.dni WHERE u.nombre=? AND u.apellidos=?");

            st.setString(1, nombre);
            st.setString(2, apellidos);
            
            ResultSet rs = st.executeQuery();

             while (rs.next()) {
                idMonitor = rs.getInt(1);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return idMonitor;
    }
    
    // En este método, recibimimos el id del nutricionista a través de su nombre y apellidos.
    private int devolverIdNutricionista(String nombre, String apellidos) {
        
        int idNutricionista=0;

        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT n.id_nutricionista FROM nutricionistas n JOIN usuarios u ON n.dni = u.dni WHERE u.nombre=? AND u.apellidos=?");

            st.setString(1, nombre);
            st.setString(2, apellidos);
            
            ResultSet rs = st.executeQuery();

             while (rs.next()) {
                idNutricionista = rs.getInt(1);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return idNutricionista;
    }
    
    // En este método, agregamos todos los datos acerca de las rutinas registradas en el sistema por cada fila,
    // donde da la posibilidad de visualizarla, modificarla o eliminarla a través de un botón.
    private void agregarFilaTablaRutinas(DefaultTableModel modelo, ResultSet rs) throws SQLException {
        
        Object[] fila;

        if (!rs.next()) {
                    // Si no hay resultados, mostrar un mensaje en un JDialog
                    JOptionPane.showMessageDialog(null, "No se ha encontrado la rutina", "Resultado de la búsqueda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            
            do {
                
                fila = new Object[6];
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3]= "Visualizar";
                fila[4]= "Modificar";
                fila[5]= "Eliminar";

                modelo.addRow(fila);
 
            } while (rs.next());
        }

        jTable3.setModel(modelo);

        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable3.getColumnCount(); i++) {
            jTable3.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        
        jTable3.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JTextField()) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                    JButton btn = new JButton("Visualizar");

                    btn.addActionListener(e -> {
                        
                        String nombre = (String) table.getValueAt(row, 0);

                        idRutina=devolverIdRutina(nombre);
                        
                        jLabel76.setText("RUTINA "+nombre+ " | DIA"+1);
                        
                        pintarEjerciciosRutinaVisualizar(1);
                        
                        jDialog_ConsultaRutina.setVisible(false);
                        jDialog_ConsultaRutina_VerEjercicios.setVisible(true);
                        
                        jButton41.setVisible(false);
                        jButton41.setEnabled(false);
                        
                        jButton39.setVisible(true);
                        jButton39.setEnabled(true);
                        
                    });
                    return btn;

                }   

                @Override
                public Object getCellEditorValue() {
                    return "Visualizar"; 
                }

        });
        
        jTable3.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JTextField()) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                    JButton btn = new JButton("Modificar");

                    btn.addActionListener(e -> {

                        String nombreRutinaMod = (String) table.getValueAt(row, 0);
                        String objetivoMod = (String) table.getValueAt(row, 1);
                        String autorMod = (String) table.getValueAt(row, 2);

                        jDialog_ConsultaRutina.setVisible(false);
                        jDialog_ConsultaRutina_ModificarRutina.setVisible(true);
                       
                        jTextField17.setText(nombreRutinaMod);
                        
                        idRutina=devolverIdRutina(nombreRutinaMod);

                        jComboBox9.setSelectedItem(objetivoMod);
                        
                        mostrarListaMonitores(jComboBox10);
                        
                        jComboBox9.setSelectedItem(autorMod);
                        
                    });
                    return btn;

                }   

                @Override
                public Object getCellEditorValue() {
                    return "Modificar"; 
                }

        });
        
        jTable3.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(new JTextField()) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                JButton btn = new JButton("Eliminar");

                btn.addActionListener(e -> {
                        
                    if (row >= 0 && row < table.getRowCount()) {
                        String nombreRutinaEliminar = (String) table.getValueAt(row, 0);

                        eliminarRutina(devolverIdRutina(nombreRutinaEliminar));
                        
                        JOptionPane.showMessageDialog(null, "Se ha eliminado la rutina correctamente.");

                        if (table.isEditing()) {
                            
                            table.getCellEditor().stopCellEditing();
                        }

                        SwingUtilities.invokeLater(() -> {
                            DefaultTableModel model = (DefaultTableModel) table.getModel();
                            model.removeRow(row);

                            model.fireTableDataChanged();
                        });
                    }
                    });
                    return btn;
                }

                @Override
                public Object getCellEditorValue() {
                    return "Eliminar"; 
                }

        });
        
    }
    
    // En este método, agregamos todos los datos acerca de las dietas registradas en el sistema por cada fila,
    // donde da la posibilidad de visualizarla, modificarla o eliminarla a través de un botón.
    private void agregarFilaTablaDietas(DefaultTableModel modelo, ResultSet rs) throws SQLException {
        
        Object[] fila;

        if (!rs.next()) {
                    // Si no hay resultados, mostrar un mensaje en un JDialog
                    JOptionPane.showMessageDialog(null, "No se ha encontrado la dieta", "Resultado de la búsqueda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            
            do {
                
                fila = new Object[6];
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3]= "Visualizar";
                fila[4]= "Modificar";
                fila[5]= "Eliminar";

                modelo.addRow(fila);
 
            } while (rs.next());
        }

        jTable5.setModel(modelo);

        // Centrar las columnas después de agregar las filas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable5.getColumnCount(); i++) {
            jTable5.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        
        jTable5.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JTextField()) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                    JButton btn = new JButton("Visualizar");

                    btn.addActionListener(e -> {

                        String nombre = (String) table.getValueAt(row, 0);

                        idDieta=devolverIdDieta(nombre);
                        
                        jLabel94.setText("DIETA "+nombre+ " | DIA"+1);
                        
                        reiniciarDietaVisualizar();
                        
                        jDialog_ConsultaDieta.setVisible(false);
                        jDialog_ConsultaDieta_VisualizarAlimentos.setVisible(true);
                        
                        
                    });
                    return btn;

                }   

                @Override
                public Object getCellEditorValue() {
                    return "Visualizar"; 
                }

        });
        
        jTable5.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JTextField()) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                    JButton btn = new JButton("Modificar");

                    btn.addActionListener(e -> {

                        String nombreDietaMod = (String) table.getValueAt(row, 0);
                        String objetivoDietaMod = (String) table.getValueAt(row, 1);
                        String autorDietaMod = (String) table.getValueAt(row, 2);

                        jDialog_ConsultaDieta.setVisible(false);
                        jDialog_ModificarDieta.setVisible(true);
                       
                        jTextField20.setText(nombreDietaMod);
                        
                        idDieta=devolverIdDieta(nombreDietaMod);

                        jTextArea2.setText(devolverDesDieta());
                        
                        jComboBox16.setSelectedItem(objetivoDietaMod);
                        
                        mostrarListaNutricionistas(jComboBox15);
                        
                        jComboBox15.setSelectedItem(autorDietaMod);
                        
                    });
                    return btn;

                }   

                @Override
                public Object getCellEditorValue() {
                    return "Modificar"; 
                }

        });
        
        jTable5.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(new JTextField()) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

                    JButton btn = new JButton("Eliminar");

                    btn.addActionListener(e -> {

                            if (row >= 0 && row < table.getRowCount()) {

                                String nombreDietaEliminar = (String) table.getValueAt(row, 0);

                                eliminarDieta(devolverIdDieta(nombreDietaEliminar));

                                JOptionPane.showMessageDialog(null, "Se ha eliminado la dieta correctamente.");
                                
                                if (table.isEditing()) {

                                    table.getCellEditor().stopCellEditing();
                                }

                                SwingUtilities.invokeLater(() -> {
                                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                                        model.removeRow(row);

                                        model.fireTableDataChanged();
                                });

                            }
                            });
                            return btn;
                        }

                @Override
                public Object getCellEditorValue() {
                    return "Eliminar"; 
                }

        });
        
    }
    
    // En este método, reiniciamos el contador de días de las dietas y la tabla para que muestre la primera comida del primer dia.
    private void reiniciarDietaVisualizar() {
        
        diasDieta=1;
                        
        pintarAlimentosDietaVisualizar(diasDieta, "Desayuno");
                        
        jButton52.setVisible(false);
        jButton52.setEnabled(false);
        
    }
    
    // En este método, eliminamos la rutina deseada con sus respectivos ejercicios escogidos.
    private void eliminarRutina(int idRutinaEliminar) {
        
        eliminarEjerciciosRutina(idRutinaEliminar);
        
        PreparedStatement st, st2;
        
        try {
            
            st = con.prepareStatement("UPDATE gym_zone.clientes SET id_rutina = NULL WHERE id_rutina = ?;");
            st.setInt(1, idRutinaEliminar);
            st.executeUpdate();
            
            st2 = con.prepareStatement("DELETE FROM gym_zone.rutinas WHERE id_rutina=?;");

            st2.setInt(1, idRutinaEliminar);
            
            st2.executeUpdate();   
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, eliminamos los ejercicios de la rutina deseada.
    private void eliminarEjerciciosRutina(int idRutinaEliminar) {
  
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("DELETE FROM gym_zone.rutinas_ejercicios WHERE id_rutina=?;");

            st.setInt(1, idRutinaEliminar);
            
            st.executeUpdate();   
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, eliminamos la dieta deseada con sus respectivos alimentos y comidas escogidas.
    private void eliminarDieta(int idDietaEliminar) {
        
        eliminarAlimentosComida(idDietaEliminar);
        eliminarComidaDieta(idDietaEliminar);
        
        PreparedStatement st, st2;
        
        try {
            
            st = con.prepareStatement("UPDATE gym_zone.clientes SET id_dieta = NULL WHERE id_dieta = ?;");
            st.setInt(1, idDietaEliminar);
            st.executeUpdate();
            
            st2 = con.prepareStatement("DELETE FROM gym_zone.dietas WHERE id_dieta=?;");

            st2.setInt(1, idDietaEliminar);
            
            st2.executeUpdate();   
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, eliminamos el producto deseado con sus determinadas ventas.
    private void eliminarProducto(int idProductoEliminar) {
        
        PreparedStatement st;
        
        eliminarVentasProducto(idProductoEliminar);
        
        try {
            st = con.prepareStatement("DELETE FROM gym_zone.productos WHERE id_producto=?;");

            st.setInt(1, idProductoEliminar);
            
            st.executeUpdate();   
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    // En este método, eliminamos las ventas del producto asociado.
    private void eliminarVentasProducto(int idProductoEliminar){
        
        PreparedStatement st = null;
    
        try {
       
            st = con.prepareStatement("DELETE FROM gym_zone.ventas WHERE id_producto = ?;");
            st.setInt(1, idProductoEliminar);
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // En este método, eliminamos la sala con sus determinadas reservas hechas.
    private void eliminarSala(int idSalaEliminar) {
        
        PreparedStatement st;
        
        eliminarReservasSala(idSalaEliminar);
        
        try {
            st = con.prepareStatement("DELETE FROM gym_zone.salas WHERE id_sala=?;");

            st.setInt(1, idSalaEliminar);
            
            st.executeUpdate();   
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, eliminamos las reservas de la sala seleccionada.
    private void eliminarReservasSala(int idSalaEliminar){
        
        PreparedStatement st = null;

        try {
            
            st = con.prepareStatement("DELETE FROM gym_zone.reservas WHERE id_sala = ?;");
            st.setInt(1, idSalaEliminar);
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // En este método, eliminamos el servicio con sus determinados pagos realizados.
    private void eliminarServicio(int idServicioEliminar) {
        
        eliminarPagosServicio(idServicioEliminar);
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("DELETE FROM gym_zone.servicios WHERE id_servicio=?;");

            st.setInt(1, idServicioEliminar);
            
            st.executeUpdate();   
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, eliminamos los pagos de los servicios del servicio seleccionado.
    private void eliminarPagosServicio(int idServicioEliminar){
        
        PreparedStatement st = null;

        try {
            
            st = con.prepareStatement("DELETE FROM gym_zone.pagos_servicios WHERE id_servicio = ?;");
            st.setInt(1, idServicioEliminar);
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // En este método, eliminamos los alimentos en las comidas seleccionadas de la dieta elegida.
    private void eliminarAlimentosComida(int idDietaEliminar) {

        PreparedStatement st;
        
        try {
            st = con.prepareStatement("DELETE FROM alimentos_comidas WHERE id_comida IN (SELECT id_comida FROM comidas WHERE id_dieta=?);");

            st.setInt(1, idDietaEliminar);
            
            st.executeUpdate();   
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, eliminamos las comidas registradas de la dieta seleccionada.
    private void eliminarComidaDieta(int idDietaEliminar) {

        PreparedStatement st;
        
        try {
            st = con.prepareStatement("DELETE FROM comidas WHERE id_dieta=?;");

            st.setInt(1, idDietaEliminar);
            
            st.executeUpdate();   
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // En este método, insertamos la reserva del cliente con la sala y franja horaria seleccionada.
    private void insertarReserva(int idSala, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        PreparedStatement st = null;

        try {
            
            st = con.prepareStatement("INSERT INTO reservas (id_cliente, id_sala, fecha_entrada, fecha_salida, estado) VALUES (?, ?, ?, ?, ?);");

            String estado = "Realizada"; 
 
            st.setInt(1, idUsuario);
            st.setInt(2, idSala);
            st.setTimestamp(3, Timestamp.valueOf(fechaHoraInicio));
            st.setTimestamp(4, Timestamp.valueOf(fechaHoraFin));
            st.setString(5, estado);

            st.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Reserva realizada correctamente.");

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    // En este método, pintamos la tabla con todos los datos acerca de la rutina seleccionada.
    private void pintarEjerciciosRutinaVisualizar(int diaRutina) {
        
  
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Musculos Implicados");
        jTable2.setModel(modelo);
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT e.nombre AS nombre_ejercicio, e.descripcion AS descripcion_ejercicio, GROUP_CONCAT(m.nombre SEPARATOR ', ') AS musculos_implicados\n" +
            "FROM rutinas r JOIN rutinas_ejercicios re ON r.id_rutina = re.id_rutina JOIN ejercicios e ON re.id_ejercicio = e.id_ejercicio\n" +
            "JOIN ejercicios_musculos em ON e.id_ejercicio = em.id_ejercicio JOIN musculos m ON em.id_musculo = m.id_musculo\n" +
            "WHERE r.id_rutina = ? AND re.dia = ? GROUP BY e.id_ejercicio;");

            st.setInt(1, idRutina);
            
            st.setInt(2, diaRutina);
            
            ResultSet rs = st.executeQuery();

            agregarFilaTablaRutinasEjerciciosVer(modelo, rs);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       String textoNombreRutina = jLabel76.getText();
       char[] caracteres = textoNombreRutina.toCharArray(); 
       
       char letraDia=devolverLetraDia(diaRutina);
       
       caracteres[caracteres.length - 1] = letraDia;
       textoNombreRutina = String.valueOf(caracteres); 
        
       jLabel76.setText(textoNombreRutina);
        
    }
    
    // En este método, pintamos la tabla con todos los datos acerca de la dieta seleccionada, segun la comida escogida (Desayuno, Almuerzo, Merienda, Cena).
    private void pintarAlimentosDietaVisualizar(int diaDieta, String tipoComida) {
        
  
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Calorias por porcion");
        modelo.addColumn("Proteinas por porcion");
        modelo.addColumn("Carbohidratos por porcion");
        modelo.addColumn("Grasas por porcion");
        jTable6.setModel(modelo);
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT alimentos.nombre, alimentos.calorias_porcion, alimentos.proteinas_porcion, alimentos.carbohidratos_porcion, alimentos.grasas_porcion FROM alimentos WHERE alimentos.id_alimento IN (SELECT alimentos_comidas.id_alimento\n" +
            " FROM alimentos_comidas WHERE alimentos_comidas.id_comida = (SELECT comidas.id_comida FROM comidas\n" +
            " WHERE comidas.tipo = ? AND comidas.dia = ? AND comidas.id_dieta = ?));");

            st.setString(1, tipoComida);
            
            st.setInt(2, diaDieta);
            
            st.setInt(3, idDieta);
            
            ResultSet rs = st.executeQuery();
            
            agregarFilaTablaDietasEjerciciosVer(modelo, rs);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       String textoNombreDieta = jLabel94.getText();
       char[] caracteres = textoNombreDieta.toCharArray(); 
       
       char letraDia=devolverLetraDia(diaDieta);
       
       caracteres[caracteres.length - 1] = letraDia;
       textoNombreDieta = String.valueOf(caracteres); 
        
       jLabel94.setText(textoNombreDieta);
        
    }
    
    // En este método, devolvemos el dia correspondiente de la dieta en forma de caracter para añadirlo al texto mostrando el dia correspondiente.
    private char devolverLetraDia(int dia) {
        
    char diaCaracter=0;
    
    switch (dia) {
        case 1 -> diaCaracter = '1';
        case 2 -> diaCaracter = '2';
        case 3 -> diaCaracter = '3';
            
        case 4 -> diaCaracter = '4';
            
        case 5 -> diaCaracter = '5';
        
    }
       return diaCaracter; 
    }
    
    // En este método, agregamos todos los datos acerca de la rutina seleccionada por cada fila de la tabla.
    private void agregarFilaTablaRutinasEjerciciosVer(DefaultTableModel modelo, ResultSet rs) throws SQLException {
        
        Object[] fila;

        while (rs.next()) {

            fila = new Object[3];
            fila[0] = rs.getString(1);
            fila[1] = rs.getString(2);
            fila[2] = rs.getString(3);

            modelo.addRow(fila);
        }


        jTable2.setModel(modelo);

        // Centrar las columnas después de agregar las filas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable2.getColumnCount(); i++) {
            jTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }


    }
    
    // En este método, agregamos todos los datos acerca de la dieta seleccionada por cada fila de la tabla.
    private void agregarFilaTablaDietasEjerciciosVer(DefaultTableModel modelo, ResultSet rs) throws SQLException {
        
        Object[] fila;

        while (rs.next()) {

            fila = new Object[5];
            fila[0] = rs.getString(1);
            fila[1] = rs.getString(2) +" kcal";
            fila[2] = rs.getString(3)+" g";
            fila[3] = rs.getString(4)+" g";
            fila[4] = rs.getString(5)+" g";

            modelo.addRow(fila);
        }


        jTable6.setModel(modelo);

        // Centrar las columnas después de agregar las filas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable6.getColumnCount(); i++) {
            jTable6.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }


    }
    
    // Este método se encarga de realizar la rutina con los datos introducidos.
    private void realizarRutina() {
        
        ResultSet rs;
        
        try {
            
            PreparedStatement st = con.prepareStatement("INSERT INTO gym_zone.rutinas (nombre, objetivo, id_monitor) VALUES(?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, nombreRutina);
            st.setString(2, tipoObjetivo);
            st.setInt(3, idUsuario);
            
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La inserción falló, no se creó ningún registro.");
            }

            rs = st.getGeneratedKeys();
            
            if (rs.next()) {
                
                idRutina = rs.getInt(1);   
                
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    
     // Este método se encarga de realizar la dieta con los datos introducidos.
    private void realizarDieta() {
        
        ResultSet rs;
        
        try {
            
            PreparedStatement st = con.prepareStatement("INSERT INTO gym_zone.dietas (nombre, tipo, descripcion, id_nutricionista) VALUES(?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, nombreDieta);
            st.setString(2, tipoObjetivoDieta);
            st.setString(3, descripcionDieta);
            st.setInt(4, idUsuario);
            
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La inserción falló, no se creó ningún registro.");
            }

            rs = st.getGeneratedKeys();
            
            if (rs.next()) {
                
                idDieta= rs.getInt(1);   
                
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    

    // Este método permite mostrar el formulario para que el usuario según el campo que haya deseado editar
    // pueda modificar con datos nuevos el campo seleccionado.
    private void editarCampo(String mensajeIntroducir) {
  
        jTextField3.setVisible(true);
        jTextField3.setEnabled(true);
        
        jLabel73.setText(mensajeIntroducir);
        
        jButton_PU_Editar.setVisible(true);
        jButton_PU_Editar.setEnabled(true);
        
        jLabel_PU_EditarNombreUsuario.setVisible(false);
        jLabel_PU_EditarNombreUsuario.setEnabled(false);
        
        jLabel_PU_EditarCorreo.setVisible(false);
        jLabel_PU_EditarCorreo.setEnabled(false);
        
        jLabel_PU_EditarContrasenna.setVisible(false);
        jLabel_PU_EditarContrasenna.setEnabled(false);
        
        jLabel66.setVisible(false);
        jLabel66.setEnabled(false);

        jLabel_PU_Correo.setVisible(false);
        jLabel_PU_Correo.setEnabled(false);
        
        jLabel64.setVisible(false);
        jLabel64.setEnabled(false);
        
        jLabel_PU_Contrasenna.setVisible(false);
        jLabel_PU_Contrasenna.setEnabled(false);
        
    }
    
    // Este método permite mostrar el formulario para que el cliente según el campo que haya deseado editar
    // pueda modificar con datos nuevos el campo seleccionado.
    private void editarCampoCliente(String mensajeIntroducir) {
  
        if(mensajeIntroducir.equals("Introduzca el objetivo corporal nuevo:")) {
            
            jComboBox38.setVisible(true);
            jTextField43.setEnabled(true);
            
        } else {
            
            jTextField43.setVisible(true);
            jTextField43.setEnabled(true);
            
        }
        
        jLabel177.setText(mensajeIntroducir);
        
        jButton_PC_Editar.setVisible(true);
        jButton_PC_Editar.setEnabled(true);
        
        jLabel_PC_EditarObjetivo.setVisible(false);
        jLabel_PC_EditarObjetivo.setEnabled(false);
        
        jLabel_PC_EditarAltura.setVisible(false);
        jLabel_PC_EditarAltura.setEnabled(false);
        
        jLabel_PC_EditarPeso.setVisible(false);
        jLabel_PC_EditarPeso.setEnabled(false);
        
        jLabel_PC_Objetivo.setVisible(false);
        jLabel_PC_Objetivo.setEnabled(false);
        
        jLabel_PC_Altura.setVisible(false);
        jLabel_PC_Altura.setEnabled(false);
        
        jLabel_PC_Peso.setVisible(false);
        jLabel_PC_Peso.setEnabled(false);
        
        jLabel176.setVisible(false);
        jLabel176.setEnabled(false);
        
        jLabel175.setVisible(false);
        jLabel175.setEnabled(false);
        
        
        
    }
    
    
    // Este método se encarga de ocultar el formulario y mostrar los botones para que puede realizar la operacion requerida.
    private void reiniciarOperacionesSaldo() {
        
        this.jButton26.setVisible(false);
        this.jButton26.setEnabled(false);

        jTextField1.setText("");
        
        jTextField1.setVisible(false);
        jTextField1.setEnabled(false);
        
        jLabel55.setVisible(true);
        jLabel55.setEnabled(true);
        
        jLabel56.setVisible(true);
        jLabel56.setEnabled(true);
        
        jLabel54.setText("Selecciona la operación a realizar:");
        
    }
    
    // Este metodo se encarga de devolver el nombre de la tabla según el rol del usuario
    private String devolverCampoUsuarioBD() {
        
        String campoUsuarioBD="";
        
        switch(rolUsuario) {
            
            case "Cliente" -> campoUsuarioBD="clientes";
            case "Monitor" -> campoUsuarioBD="monitores";
            case "Nutricionista" -> campoUsuarioBD="nutricionistas";
            case "Administrador" -> campoUsuarioBD="monitores";
        }
        
        return campoUsuarioBD;
        
    }
    
    // Este metodo se encarga de devolver el nombre de la clave primaria de la tabla según el rol del usuario
    private String devolverCampoIDUsuarioBD() {
        
        String campoUsuarioBD="";
        
        switch(rolUsuario) {
            
            case "Cliente" -> campoUsuarioBD="id_cliente";
            case "Monitor" -> campoUsuarioBD="id_monitor";
            case "Nutricionista" -> campoUsuarioBD="id_nutricionista";
            case "Administrador" -> campoUsuarioBD="id_monitor";
        }
        
        return campoUsuarioBD;
        
    }
    
    // En este método, recogemos todos los nombre y apellidos de los nutricionistas para mostrarlo en forma de listado en un JComboBox
    private void mostrarListaNutricionistas(JComboBox<String> comboBox) {
        
        comboBox.removeAllItems();
        
        boolean hayNutricionista=false;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT DISTINCT CONCAT(u.nombre, ' ', u.apellidos) AS nombre_completo \n" +
"FROM nutricionistas n \n" +
"JOIN usuarios u ON n.dni = u.dni \n" +
"JOIN dietas d ON n.id_nutricionista = d.id_nutricionista;");

            ResultSet rs = st.executeQuery();

             while (rs.next()) {
                String nombreNutricionista = rs.getString(1);
                comboBox.addItem(nombreNutricionista);
                hayNutricionista = true;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!hayNutricionista) {
            
            JOptionPane.showMessageDialog(null, "No se encontraron monitores.", "Información Monitores", JOptionPane.INFORMATION_MESSAGE);
        }
        
           
    }
    
    // Este método se encarga de devolver la descripción de una determinada dieta
    private String devolverDesDieta() {
        
        String descripcionDieta="";

        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT descripcion FROM gym_zone.dietas where id_dieta=?");

            st.setInt(1, idDieta);
            
            ResultSet rs = st.executeQuery();

             if (rs.next()) {
                 
                descripcionDieta = rs.getString(1);

            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return descripcionDieta;
        
    }
    
    // En este método, recogemos todos los nombre y apellidos de los monitores para mostrarlo en forma de listado en un JComboBox
    private void mostrarListaMonitores(JComboBox<String> comboBox) {
        
        comboBox.removeAllItems();
        
        boolean hayMonitor=false;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT DISTINCT CONCAT(u.nombre, ' ', u.apellidos) AS nombre_completo \n" +
"FROM monitores m \n" +
"JOIN usuarios u ON m.dni = u.dni\n" +
"JOIN rutinas r ON m.id_monitor = r.id_monitor");

            ResultSet rs = st.executeQuery();

             while (rs.next()) {
                String nombreMonitor = rs.getString(1);
                comboBox.addItem(nombreMonitor);
                hayMonitor = true;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!hayMonitor) {
            
            JOptionPane.showMessageDialog(null, "No se encontraron monitores.", "Información Monitores", JOptionPane.INFORMATION_MESSAGE);
        }
        
           
    }
    
    // En este método, recogemos todos los nombre y apellidos de los clientes que han realizado alguna compra de un producto para mostrarlo en forma de listado en un JComboBox
    private void mostrarListaClientesVenta(JComboBox<String> comboBox) {
        
        comboBox.removeAllItems();
        
        boolean hayCliente=false;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT DISTINCT CONCAT(u.nombre, ' ', u.apellidos) AS nombre_completo FROM ventas v\n" +
            " JOIN clientes c ON v.id_cliente = c.id_cliente JOIN usuarios u ON c.dni = u.dni;");

            ResultSet rs = st.executeQuery();

             while (rs.next()) {
                String nombreCliente = rs.getString(1);
                comboBox.addItem(nombreCliente);
                hayCliente = true;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!hayCliente) {
            
            JOptionPane.showMessageDialog(null, "No se encontraron clientes.", "Información Clientes", JOptionPane.INFORMATION_MESSAGE);
        }
        
           
    }
    
    // En este método, recogemos todos los nombre y apellidos de los clientes que hayan realizado alguna reserva para mostrarlo en forma de listado en un JComboBox
    private void mostrarListaClientesReservas(JComboBox<String> comboBox) {
        
        comboBox.removeAllItems();
        
        boolean hayCliente=false;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT DISTINCT CONCAT(u.nombre, ' ', u.apellidos) AS nombre_completo FROM reservas r\n" +
            " JOIN clientes c ON r.id_cliente = c.id_cliente JOIN usuarios u ON c.dni = u.dni;");

            ResultSet rs = st.executeQuery();

             while (rs.next()) {
                String nombreCliente = rs.getString(1);
                comboBox.addItem(nombreCliente);
                hayCliente = true;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!hayCliente) {
            
            JOptionPane.showMessageDialog(null, "No se encontraron clientes.", "Información Clientes", JOptionPane.INFORMATION_MESSAGE);
        }
        
           
    }
    
    // En este método, recogemos todos los nombre y apellidos de los clientes que han realizado algun pago por un determinado servicio para mostrarlo en forma de listado en un JComboBox
    private void mostrarListaClientesPagosServicio(JComboBox<String> comboBox) {
        
        comboBox.removeAllItems();
        
        boolean hayCliente=false;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT DISTINCT CONCAT(u.nombre, ' ', u.apellidos) AS nombre_completo FROM pagos_servicios p\n" +
            " JOIN clientes c ON p.id_cliente = c.id_cliente JOIN usuarios u ON c.dni = u.dni;");

            ResultSet rs = st.executeQuery();

             while (rs.next()) {
                String nombreCliente = rs.getString(1);
                comboBox.addItem(nombreCliente);
                hayCliente = true;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!hayCliente) {
            
            JOptionPane.showMessageDialog(null, "No se encontraron clientes.", "Información Clientes", JOptionPane.INFORMATION_MESSAGE);
        }
        
           
    }
    
    // En este método, recogemos todos los nombres de los servicios, en los cuáles se ha realizado un pago en forma de listado en un JComboBox
    private void mostrarListaServiciosPagos(JComboBox<String> comboBox) {
        
        comboBox.removeAllItems();
        
        boolean hayServicio=false;
        
        PreparedStatement st;
        
        try {
            
            st = con.prepareStatement("SELECT DISTINCT s.nombre FROM pagos_servicios ps " +
            "JOIN servicios s ON ps.id_servicio = s.id_servicio;");

            ResultSet rs = st.executeQuery();

             while (rs.next()) {
                String nombreServicio = rs.getString(1);
                comboBox.addItem(nombreServicio);
                hayServicio = true;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!hayServicio) {
            
            JOptionPane.showMessageDialog(null, "No se encontraron servicios.", "Información Servicios", JOptionPane.INFORMATION_MESSAGE);
        }
        
           
    }
    
    
    // En este método, recogemos todos los nombres de las salas, en los cuáles se ha realizado una reserva en forma de listado en un JComboBox
    private void mostrarListaSalasReservas(JComboBox<String> comboBox) {
        
        comboBox.removeAllItems();
        
        boolean haySala=false;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT DISTINCT s.nombre AS nombre_sala FROM reservas r JOIN salas s ON r.id_sala = s.id_sala;");

            ResultSet rs = st.executeQuery();

             while (rs.next()) {
                String nombreSala = rs.getString(1);
                comboBox.addItem(nombreSala);
                haySala = true;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!haySala) {
            
            JOptionPane.showMessageDialog(null, "No se encontraron salas.", "Información Salas", JOptionPane.INFORMATION_MESSAGE);
        }
        
           
    }
    
    // En este método, metemos todas las franjas horarias del turno de dia que no se hayan pasado en forma de listado en un JComboBox
    private void mostrarListaHorasDia(JComboBox<String> comboBox) {
        
        comboBox.removeAllItems();

        LocalDate fechaHoy = LocalDate.now();

        LocalDateTime fechaHoraFinTurno1 = fechaHoy.atTime(10, 0, 0);
        
        if(!comprobarFranjaHorariaPasada(fechaHoraFinTurno1)) {
            
            comboBox.addItem("8:00 - 10:00");
        }
        
        LocalDateTime fechaHoraFinTurno2 = fechaHoy.atTime(12, 0, 0);
        
        if(!comprobarFranjaHorariaPasada(fechaHoraFinTurno2)) {
            
            comboBox.addItem("10:00 - 12:00");
            
        }  
        
        LocalDateTime fechaHoraFinTurno3 = fechaHoy.atTime(14, 0, 0);
        
        if(!comprobarFranjaHorariaPasada(fechaHoraFinTurno3)) {
            
            comboBox.addItem("12:00 - 14:00");
            
        }  
        
        
        
        
    }
    
    // En este método, metemos todas las salas con o sin monitor asignado dependiendo del servicio pagado en forma de listado en un JComboBox
    private void mostrarListaSalasReserva(JComboBox<String> comboBox) {
        
        comboBox.removeAllItems();

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String query = "SELECT s.nombre " +
                       "FROM salas s WHERE s.id_monitor IS ";

            if (comprobarServicioPagadoCliente(SERVICIO_RESERVA)) {
                
                query += "NULL"; // Solo salas sin monitor asignado
                
            } else if (comprobarServicioPagadoCliente(SERVICIO_RESERVA_MONITOR)) {
                
                query += "NOT NULL"; // Solo salas con monitor asignado
                
            } else {
                
                // Si ninguno de los servicios está pagado, no se muestra ninguna sala
                JOptionPane.showMessageDialog(null, "Debe pagar el servicio de reserva antes de seleccionar una sala.");
                return;
            }

            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                String nombreSala = rs.getString("nombre");
                comboBox.addItem(nombreSala);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }
    
    // Este método se encarga si el cliente ha pagado un servicio determinado
    private boolean comprobarServicioPagadoCliente(String nombreServicio) {
        
        boolean pagado = false;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            String query = "SELECT ps.estado " +
                           "FROM pagos_servicios ps " +
                           "JOIN servicios s ON ps.id_servicio = s.id_servicio " +
                           "WHERE ps.id_cliente = ? " +
                           "AND s.nombre = ? " +
                           "AND ps.estado = 'Pagada' " +  // Filtra por estado 'Pagada'
                           "ORDER BY ps.fecha_pagado DESC " + 
                           "LIMIT 1";  

            st = con.prepareStatement(query);
            st.setInt(1, idUsuario); 
            st.setString(2, nombreServicio); 

            rs = st.executeQuery();

            if (rs.next()) {
                pagado = true; 
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return pagado;
    }
    
    // Este método se encarga de verificar que todos los servicios ha cumplido su fecha de expiración
    private void verificarExpiracionServicios() {
        
        verificarYActualizarServicio(SERVICIO_RESERVA);
        verificarYActualizarServicio(SERVICIO_RESERVA_MONITOR);
        verificarYActualizarServicio(SERVICIO_DIETA);
        verificarYActualizarServicio(SERVICIO_RUTINA);
    }
    
    // Este método se encarga de verificar que el servicio determinado ha cumplido su fecha de expiración
    private void verificarYActualizarServicio(String servicio) {
        
        if (comprobarServicioPagado(servicio) && comprobarServicioExpirado(servicio)) {
            JOptionPane.showMessageDialog(null, "Se ha cumplido la fecha de expiración del servicio " + servicio + ".\n Vuelve a pagar el servicio si deseas tenerlo disponible.", 
            "Expiracion Servicio Pagado",  JOptionPane.INFORMATION_MESSAGE);
            
        cambiarEstadoServicio("No pagada", 0, devolverIdServicio(servicio));
        }
    }
    
    // En este método, metemos todas las franjas horarias del turno tarde dependiendo si ha pasado la hora o no en forma de listado en un JComboBox
    private void mostrarListaHorasTarde(JComboBox<String> comboBox) {
        
        comboBox.removeAllItems();

        LocalDate fechaHoy = LocalDate.now();

        LocalDateTime fechaHoraFinTurno1 = fechaHoy.atTime(18, 0, 0);
        
        if(!comprobarFranjaHorariaPasada(fechaHoraFinTurno1)) {
            
            comboBox.addItem("16:00 - 18:00");
        }
        
        LocalDateTime fechaHoraFinTurno2 = fechaHoy.atTime(20, 0, 0);
        
        if(!comprobarFranjaHorariaPasada(fechaHoraFinTurno2)) {
            
            comboBox.addItem("18:00 - 20:00");
            
        }  
        
        LocalDateTime fechaHoraFinTurno3 = fechaHoy.atTime(22, 0, 0);
        
        if(!comprobarFranjaHorariaPasada(fechaHoraFinTurno3)) {
            
            comboBox.addItem("20:00 - 22:00");
            
        }  
        
    }
    
    // En este método, metemos todos los nombres y apellidos de los monitores que no sean administradores en forma de listado en un JComboBox
    private void mostrarListaMonitoresSala(JComboBox<String> comboBox) {
        
        comboBox.removeAllItems();
        
        comboBox.addItem("Ninguno");
        
        boolean hayMonitor=false;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT CONCAT(u.nombre, ' ', u.apellidos) AS nombre_completo FROM monitores m JOIN usuarios u ON m.dni = u.dni WHERE m.funcion <> 'Administracion'; ");

            ResultSet rs = st.executeQuery();

             while (rs.next()) {
                String nombreMonitor = rs.getString(1);
                comboBox.addItem(nombreMonitor);
                hayMonitor = true;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!hayMonitor) {
            
            JOptionPane.showMessageDialog(null, "No se encontraron monitores.", "Información Monitores", JOptionPane.INFORMATION_MESSAGE);
        }
        
           
    }
    
    // En este método, metemos todos los nombres y apellidos de los monitores que tenga una sala asignada en forma de listado en un JComboBox
    private void mostrarListaMonitoresAsignadosSala(JComboBox<String> comboBox) {
        
        comboBox.removeAllItems();
        
        comboBox.addItem("Ninguno");
        
        boolean hayMonitor=false;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT CONCAT(u.nombre, ' ', u.apellidos) AS nombre_completo FROM usuarios u JOIN monitores m ON u.dni = m.dni\n" +
            "JOIN salas s ON m.id_monitor = s.id_monitor;");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String nombreMonitor = rs.getString(1);
                comboBox.addItem(nombreMonitor);
                hayMonitor = true;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!hayMonitor) {
            
            JOptionPane.showMessageDialog(null, "No se encontraron monitores.", "Información Monitores", JOptionPane.INFORMATION_MESSAGE);
        }
        
           
    }
    
    // Este método cambia la visibilidad del texto en un JPasswordField
    private void cambiarVisibilidadTexto(JPasswordField passwordField, boolean visible) {
        passwordField.setEchoChar(visible ? '\u0000' : '*');
    }
    
    // Este método se encargar de insertar la cuenta bancaria con los datos introducidos en el sistema
    private void registrarCuentaBancaria(String nombreTitular, String numeroCuenta, String banco, double saldoInicial) {
        
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO gym_zone.cuentas_bancarias (nombre_titular, numero_cuenta, banco, saldo, dni) VALUES(?, ?, ?, ?, ?);");

            st.setString(1, nombreTitular);
            st.setString(2, numeroCuenta);
            st.setString(3, banco);
            st.setDouble(4, saldoInicial);
            st.setString(5, dniUsuario);
            
            st.executeUpdate();
      

           
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
       

        
    }
    
    // Este método muestra los datos que posee el usuario registrados en el sistema
    private void mostrarCredencialesUsuario() {
        
        String generoUsuario=devolverGenero().toLowerCase();
        String nombreUsuario=devolverNombreUsuario();
        
        URL url_LogoGenero = getClass().getResource("/img/"+generoUsuario+".png");
        
        ImageIcon img_LogoGenero= new ImageIcon(url_LogoGenero);
        
        URL url_PU_LogoGenero = getClass().getResource("/img/PU_"+generoUsuario+".png");
        
        ImageIcon img_PU_LogoGenero= new ImageIcon(url_PU_LogoGenero);
        
        URL url_LogoCuentaBancaria = getClass().getResource("/img/bancoPredeterminado.png");
        ImageIcon img_LogoCuentaBancaria=new ImageIcon(url_LogoCuentaBancaria); 
        
        switch(rolUsuario) {
            
            case "Cliente" -> {
                
                jLabel_NU_Cliente.setText(nombreUsuario);
                jLabel_FG_Cliente.setIcon(img_LogoGenero);
                jLabel_FCC_Cliente.setIcon(img_LogoCuentaBancaria);
                
            }
            
            
            case "Monitor" -> {
                
                jLabel_NU_Monitor.setText(nombreUsuario);
                jLabel_FG_Monitor.setIcon(img_LogoGenero);
                jLabel_FCC_Monitor.setIcon(img_LogoCuentaBancaria);
                
            }
            
            
            case "Nutricionista" -> {
                
                jLabel_NU_Nutricionista.setText(nombreUsuario);
                jLabel_FG_Nutricionista.setIcon(img_LogoGenero);
                jLabel_FCC_Nutricionista.setIcon(img_LogoCuentaBancaria);
                
            }
            
            
            case "Administrador" -> {
                
                jLabel_NU_Administrador.setText(nombreUsuario);
                jLabel_FG_Administrador.setIcon(img_LogoGenero);
                jLabel_FCC_Administrador.setIcon(img_LogoCuentaBancaria);
                
            }
            
        }
        
        jLabel_PU_FotoPerfil.setIcon(img_PU_LogoGenero);
        jLabel_PC_FotoPerfil.setIcon(img_PU_LogoGenero);
        reiniciarPerfilUsuario();
        
    }
       
    // Este método comprueba si el usuario dispone de cuenta bancaria
    private boolean comprobarHayCuentaBancaria() {
        
        boolean hayCuentaBancaria=false;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("select * from gym_zone.cuentas_bancarias where dni=?");

            st.setString(1, dniUsuario);

            ResultSet rs = st.executeQuery();

            if(rs.next()){

                hayCuentaBancaria=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return hayCuentaBancaria;
        
    }
    
    // Este método formatea la fecha pasada por parámetro al formato año, mes y dia
    private String formatearFecha(Date fecha) {
       
        String fechaFormateada="";
        
        try {
           
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fechaFormateada = sdf.format(fecha);
           
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
        }
        
         return fechaFormateada;
    }
    
    // Este método se encarga de recoger el DNI, según el campo clave pasado por parámetro
    private void recogerDNI(String campoClave) {
        
        if(esDNI(campoClave)) {
            
            dniUsuario=campoClave;
            
        } else {
            
            dniUsuario=buscarDNI(campoClave);
        }
        
    }
    
    // Este método se encarga de comprobar si la cadena recibida solo contiene carácteres
    public boolean comprobarCadena(String cadena) {
        
        boolean esCadena=false;
        
        if(cadena.matches(REGEZ_CADENA)) {
            
            esCadena=true;
            
        }
        
        return esCadena;
        
    }
    
    // Este método se encarga de registrar todos los ejercicios seleccionados anteriormente en el sistema
    private void realizarDiasRutinas() {
        
        PreparedStatement st;
        String diaSemana="";
        
        try {
            st = con.prepareStatement("INSERT INTO rutinas_ejercicios (id_rutina, id_ejercicio, dia) VALUES (?, ?, ?)");

            for (HashMap.Entry<Integer, ArrayList<String>> entry : mapaRutina.entrySet()) {

                int dia = entry.getKey();
                
                ArrayList<String> nombresEjercicios = entry.getValue();

                for (String nombreEjercicio : nombresEjercicios) {


                    int idEjercicio = obtenerIdEjercicio(nombreEjercicio);
 
                    st.setInt(1, idRutina);
                    st.setInt(2, idEjercicio);
                    st.setInt(3, dia);

                    st.addBatch();

                }
            }
            
            st.executeBatch();
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
   
    // Este método se encarga de registrar todos los alimentos seleccionados anteriormente en las comidas en el sistema
    private void realizarDiasDietas() {
        
        
        PreparedStatement st;

        try {
            st = con.prepareStatement("INSERT INTO gym_zone.alimentos_comidas (id_alimento, id_comida) VALUES(?, ?);");

            for (HashMap.Entry<Integer, HashMap<String, ArrayList<String>>> entry : mapaDieta.entrySet()) {

                int diaDieta = entry.getKey();
                
                HashMap<String, ArrayList<String>> comidasDietaNut = entry.getValue();

                for (HashMap.Entry<String, ArrayList<String>> entryComida : comidasDietaNut.entrySet()) {

                    String nombreComida=entryComida.getKey();
                    
                    realizarComida(diaDieta, nombreComida);
                    
                    ArrayList<String> alimentosComidas=entryComida.getValue();
                    
                    for(String nombreAlimento: alimentosComidas) {
                        
                        int idAlimento=obtenerIdAlimento(nombreAlimento);
                        
                        st.setInt(1, idAlimento);
                        st.setInt(2, idComida);

                        st.addBatch();
                        
                    }


                }
            }
            
            st.executeBatch();
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    // En este método, se reliza el registro de la comida segun el tipo (Desayuno, Almuerzo, Merienda, Cena) en el día correspondiente
    private void realizarComida(int diaComida, String comida) {
        
        ResultSet rs;
        
        try {
            
            PreparedStatement st = con.prepareStatement("INSERT INTO gym_zone.comidas (tipo, dia, id_dieta) VALUES(?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, comida);
            st.setInt(2, diaComida);
            st.setInt(3, idDieta);
            
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La inserción falló, no se creó ningún registro.");
            }

            rs = st.getGeneratedKeys();
            
            if (rs.next()) {
                
                idComida= rs.getInt(1);   
                
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
        
    } 
    
    // Este método, se encarga de realizar el pago por el servicio, donde lo inserta si no existe o cambia el estado en el caso contrario.
    private void realizarPagoServicio(int idServicio, double precioServicio, int diasServicio) {
        
        if(!comprobarTieneDinero(precioServicio)) {
            
            JOptionPane.showMessageDialog(null, "No tienes suficiente dinero para pagar el servicio.");
            
        } else {
            
            realizarPagoCliente(precioServicio);
            
            
            if(comprobarServicioCancelado(idServicio)) {
                
                cambiarEstadoServicio("Pagada", diasServicio, idServicio);
                
            } else {
                
                insertarPagoServicioCliente(idServicio, diasServicio);
                
            }
            JOptionPane.showMessageDialog(null, "El servicio se ha pagado correctamente.");
            
        }
        
    }
    
    // Este método se encarga de cancelar el servicio determinado, 
    // donde devuelve al cliente la mitad del coste y cambia el estado a Cancelado del servicio.
    private void realizarCancelarServicio(int idServicio, double precioServicio) {
        
        precioServicio=precioServicio*0.5;
        
        realizarPagoCliente(-precioServicio);
 
        cambiarEstadoServicio("No pagada", 0, idServicio);
        
    }
    
    // Este método se encarga de mostrar los botones para realizar la reserva según si se ha realizado una reserva con el turno o haya pasado la hora.
    private void mostrarBotonesReserva() {
        
            if(comprobarReservaDia() || comprobarCierreReservaDia()) {
                
                ocultarBotonReservaDia();
                 
            } else {
                
                mostrarBotonReservaDia();
                
            }
            
            if(comprobarReservaTarde() || comprobarCierreReserva()) {
                
                ocultarBotonReservaTarde();
                
            } else {
                
                mostrarBotonReservaTarde();
            }
            
            jButton85.setVisible(false);
            jButton85.setEnabled(false);         

    }
    
    // Este método oculta el botón del turno de día, para que el cliente no pueda realizar reservas en este turno.
    private void ocultarBotonReservaDia() {
        
        jLabel_AltaReservaDia.setEnabled(false);
        jLabel_AltaReservaDia.setVisible(false);
        
        jButton_AltaReservaDia.setEnabled(false);
        jButton_AltaReservaDia.setVisible(false);
        
    }
    
    // Este método muestra el botón del turno de día, para que el cliente pueda realizar reservas en este turno.
    private void mostrarBotonReservaDia() {
        
        jLabel_AltaReservaDia.setEnabled(true);
        jLabel_AltaReservaDia.setVisible(true);
        
        jButton_AltaReservaDia.setEnabled(true);
        jButton_AltaReservaDia.setVisible(true);
        
    }
    
    // Este método oculta el botón del turno de tarde, para que el cliente no pueda realizar reservas en este turno.
    private void ocultarBotonReservaTarde() {
        
        jLabel_AltaReservaTarde.setEnabled(false);
        jLabel_AltaReservaTarde.setVisible(false);
        
        jButton_AltaReservaTarde.setEnabled(false);
        jButton_AltaReservaTarde.setVisible(false);
        
    }
    
    // Este método muestra el botón del turno de tarde, para que el cliente pueda realizar reservas en este turno.
    private void mostrarBotonReservaTarde() {
        
        jLabel_AltaReservaTarde.setEnabled(true);
        jLabel_AltaReservaTarde.setVisible(true);
        
        jButton_AltaReservaTarde.setEnabled(true);
        jButton_AltaReservaTarde.setVisible(true);
        
    }
    
    // En este método, se comprueba que el cliente hay realizado una reserva con las franjas horarias del turno de día.
    private boolean comprobarReservaDia() {
        
        boolean reservaRealizada = false;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            LocalDate fechaConsulta = LocalDate.now();

            LocalDateTime inicioManana1 = fechaConsulta.atTime(8, 0);
            LocalDateTime inicioManana2 = fechaConsulta.atTime(10, 0);
            LocalDateTime inicioManana3 = fechaConsulta.atTime(12, 0);

            String query = "SELECT * FROM reservas " +
                           "WHERE id_cliente = ? " +
                           "AND estado = 'Realizada' " +
                           "AND (fecha_entrada = ? OR fecha_entrada = ? OR fecha_entrada = ?)";

            st = con.prepareStatement(query);
            st.setInt(1, idUsuario);
            st.setTimestamp(2, Timestamp.valueOf(inicioManana1));
            st.setTimestamp(3, Timestamp.valueOf(inicioManana2));
            st.setTimestamp(4, Timestamp.valueOf(inicioManana3));

            rs = st.executeQuery();

            // Verifica si se encontraron resultados
            if (rs.next()) {
                reservaRealizada = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return reservaRealizada;
    }
    
    // En este método, se comprueba que el cliente hay realizado una reserva con las franjas horarias del turno de tarde.
    private boolean comprobarReservaTarde() {
        
        boolean reservaRealizada = false;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            LocalDate fechaConsulta = LocalDate.now();

            // Define los intervalos de tiempo para la tarde
            LocalDateTime inicioTarde1 = fechaConsulta.atTime(16, 0);
            LocalDateTime finTarde1 = fechaConsulta.atTime(18, 0);
            LocalDateTime inicioTarde2 = fechaConsulta.atTime(18, 0);
            LocalDateTime finTarde2 = fechaConsulta.atTime(20, 0);
            LocalDateTime inicioTarde3 = fechaConsulta.atTime(20, 0);
            LocalDateTime finTarde3 = fechaConsulta.atTime(22, 0);

            // Consulta SQL para buscar reservas en las franjas de la tarde
            String query = "SELECT * FROM reservas " +
                           "WHERE id_cliente = ? " +
                           "AND estado = 'Realizada' " +
                           "AND ((" +
                                "fecha_entrada BETWEEN ? AND ?) OR (" +
                                "fecha_entrada BETWEEN ? AND ?) OR (" +
                                "fecha_entrada BETWEEN ? AND ?))";

            st = con.prepareStatement(query);
            st.setInt(1, idUsuario);
            st.setTimestamp(2, Timestamp.valueOf(inicioTarde1));
            st.setTimestamp(3, Timestamp.valueOf(finTarde1));
            st.setTimestamp(4, Timestamp.valueOf(inicioTarde2));
            st.setTimestamp(5, Timestamp.valueOf(finTarde2));
            st.setTimestamp(6, Timestamp.valueOf(inicioTarde3));
            st.setTimestamp(7, Timestamp.valueOf(finTarde3));

            rs = st.executeQuery();

            if (rs.next()) {
                reservaRealizada = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
        return reservaRealizada;
    }
    
    // Este método se encarga de registrar el pago del servicio hecho por el cliente, donde a través de los días de servicio
    // se calcula la fecha en la que expirará.
    private void insertarPagoServicioCliente(int idServicio, int diasServicio) {
        
        
        PreparedStatement st = null;

        try {

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime nuevaFecha = now.plusDays(diasServicio);
            Timestamp nuevaFechaPagado = Timestamp.valueOf(nuevaFecha);

            st = con.prepareStatement("INSERT INTO pagos_servicios (id_cliente, id_servicio, estado, fecha_pagado) VALUES (?, ?, ?, ?);");
            st.setInt(1, idUsuario);
            st.setInt(2, idServicio);
            st.setString(3, "Pagada");
            st.setTimestamp(4, nuevaFechaPagado);

            // Ejecutar la inserción
            st.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    // En este método se encarga de realizar el pago del servicio, por lo que quitaría del saldo de la cuenta bancaria el precio de esté.
    private void realizarPagoCliente(double precioServicio) {
        

        PreparedStatement st = null;
        
        try {
   
            st = con.prepareStatement("UPDATE cuentas_bancarias SET saldo = saldo - ? WHERE dni = ?;");
            st.setDouble(1, precioServicio);
            st.setString(2, dniUsuario);
            st.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        
        
        }
    }
    
    // En este método, mostramos la rutina del cliete, donde puede variar según el objetivo corporal de él.
    private void mostrarRutinaClienteAsignada() {
        
        
        if (comprobarRutinaInvalidaCliente()) {
        
            if (!hayRutinasConObjetivoCliente()) {
                JOptionPane.showMessageDialog(null, "No hay rutinas con tu objetivo. Espere unos momentos, perdone las molestias.");
                return;
            }

            asignarRutinaCliente();
        }
        
        int idRutinaCliente=devolverIdRutinaCliente();
        
        if(idRutinaCliente!=-1) {

            String nombreRutinaCliente=devolverNombreRutina(idRutinaCliente);
                        
            jLabel76.setText("RUTINA "+nombreRutinaCliente+ " | DIA"+1);
                        
            idRutina=idRutinaCliente;
            
            jButton41.setVisible(false);
            jButton41.setEnabled(false);
                        
            jButton39.setVisible(true);
            jButton39.setEnabled(true);
            
            pintarEjerciciosRutinaVisualizar(1);
        
            jDialog_ConsultaRutina_VerEjercicios.setVisible(true);
            jDialog_FuncionalidadCliente.setVisible(false);
            
        }
        
    }
    
    // En este método comprobamos de que hay rutinas disponibles según el objetivo corporal del cliente
    private boolean hayRutinasConObjetivoCliente() {
        
        PreparedStatement st;
        ResultSet rs;
        boolean hayRutinas = false;

        String query = "SELECT COUNT(*) AS total " +
                       "FROM rutinas r " +
                       "JOIN clientes c ON r.objetivo = c.objetivo_corporal " +
                       "WHERE c.id_cliente = ?";

        try {
            st = con.prepareStatement(query);
            st.setInt(1, idUsuario);
            rs = st.executeQuery();

            if (rs.next() && rs.getInt("total") > 0) {
                hayRutinas = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hayRutinas;
    }
    
    // En este método, mostramos la dieta del cliete, donde puede variar según el objetivo corporal de él.
    private void mostrarDietaClienteAsignada() {
        
        if (comprobarDietaInvalidaCliente()) {
       
            if (!hayDietasConObjetivoCliente()) {
                JOptionPane.showMessageDialog(null, "No hay dietas con tu objetivo. Espere unos momentos, perdone las molestias.");
                return;
            }

            asignarDietaCliente();
        }
        
        int idDietaCliente=devolverIdDietaCliente();
        
        if(idDietaCliente!=-1) {
        
            String nombreDietaCliente=devolverNombreDieta(idDietaCliente);
                        
            jLabel94.setText("DIETA "+nombreDietaCliente+ " | DIA"+1);
            
            idDieta=idDietaCliente;
                        
            reiniciarDietaVisualizar();
            
            jDialog_ConsultaDieta_VisualizarAlimentos.setVisible(true);
            jDialog_FuncionalidadCliente.setVisible(false);
            
        }
        
        
       
        
    }
    
    // En este método comprobamos de que hay dietas disponibles según el objetivo corporal del cliente
    private boolean hayDietasConObjetivoCliente() {
        
        PreparedStatement st;
        ResultSet rs;
        boolean hayDietas = false;

        String query = "SELECT COUNT(*) AS total " +
                       "FROM dietas d " +
                       "JOIN clientes c ON d.tipo = c.objetivo_corporal " +
                       "WHERE c.id_cliente = ?";

        try {
            st = con.prepareStatement(query);
            st.setInt(1, idUsuario);
            rs = st.executeQuery();

            if (rs.next() && rs.getInt("total") > 0) {
                hayDietas = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hayDietas;
    }
    
    // En este método, devolvemos el nombre de la dieta según la seleccionada.
    private String devolverNombreDieta(int idDieta) {
        
        PreparedStatement st;
        ResultSet rs;
        String nombreDieta = null; 


        String queryNombreDieta = "SELECT nombre FROM dietas WHERE id_dieta = ?";

        try {
            st = con.prepareStatement(queryNombreDieta);
            st.setInt(1, idDieta); 
            rs = st.executeQuery();

            if (rs.next()) {
                nombreDieta = rs.getString("nombre");
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nombreDieta;
    }
    
    // En este método, devolvemos el nombre de la rutina según la seleccionada.
    private String devolverNombreRutina(int idRutina) {
        
        PreparedStatement st = null;
        ResultSet rs = null;
        String nombreRutina = null;

        try {

            String query = "SELECT nombre FROM rutinas WHERE id_rutina = ?";

            st = con.prepareStatement(query);
            st.setInt(1, idRutina);

            rs = st.executeQuery();

            if (rs.next()) {
                nombreRutina = rs.getString("nombre");
            } 

        } catch (SQLException ex) {

            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } 

        return nombreRutina;
    }
    
    // En este método, devolvemos el id de la rutina que tenga asignada el cliente.
    public int devolverIdRutinaCliente() {
        
        PreparedStatement st;
        ResultSet rs;
        int idRutina = -1; 

        String queryIdRutina = "SELECT id_rutina FROM clientes WHERE id_cliente = ?";

        try {
            st = con.prepareStatement(queryIdRutina);
            st.setInt(1, idUsuario);
            rs = st.executeQuery();

            if (rs.next()) {
                idRutina = rs.getInt("id_rutina");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return idRutina;
    }
    
    // En este método, devolvemos el id de la dieta que tenga asignada el cliente.
    public int devolverIdDietaCliente() {
        
        PreparedStatement st;
        ResultSet rs;
        int idDieta = -1; 

        String queryIdRutina = "SELECT id_dieta FROM clientes WHERE id_cliente = ?";

        try {
            st = con.prepareStatement(queryIdRutina);
            st.setInt(1, idUsuario);
            rs = st.executeQuery();

            if (rs.next()) {
                idDieta = rs.getInt("id_dieta");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return idDieta;
    }
    
    // Este método se encarga de asignar una rutina al cliente correspondiente con su objetivo corporal.
    private void asignarRutinaCliente() {
        
        PreparedStatement st;
        ResultSet rs;
        int rutinaId;
        String objetivoCliente = devolverObjetivoCliente();

        
        // Obtener una rutina random que cumpla con el objetivo del cliente
        String queryRutina = "SELECT id_rutina FROM rutinas WHERE objetivo = ? ORDER BY RAND() LIMIT 1";

        try {
            st = con.prepareStatement(queryRutina);
            st.setString(1, objetivoCliente);
            rs = st.executeQuery();

            if (rs.next()) {
                rutinaId = rs.getInt("id_rutina");
            } else {
                System.out.println("No hay rutinas disponibles para el objetivo deseado.");
                return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        // Asignar la rutina al cliente
        if (rutinaId != 0) {
            
            String queryAsignarRutina = "UPDATE clientes SET id_rutina = ? WHERE id_cliente = ?";

            try {
                st = con.prepareStatement(queryAsignarRutina);
                st.setInt(1, rutinaId);
                st.setInt(2, idUsuario);
                int rowsUpdated = st.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Rutina asignada correctamente al cliente.");
                } else {
                    System.out.println("No se pudo asignar la rutina al cliente.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // Este método se encarga de asignar una dieta al cliente correspondiente con su objetivo corporal.
    private void asignarDietaCliente() {
        
        PreparedStatement st;
        ResultSet rs;
        int dietaId = 0;
        String objetivoCliente = devolverObjetivoCliente();

        // Obtener una dieta random que cumpla con el objetivo del cliente
        String queryDieta = "SELECT id_dieta FROM dietas WHERE tipo = ? ORDER BY RAND() LIMIT 1";

        try {
            st = con.prepareStatement(queryDieta);
            st.setString(1, objetivoCliente);
            rs = st.executeQuery();

            if (rs.next()) {
                dietaId = rs.getInt("id_dieta");
            } else {
                System.out.println("No hay dietas disponibles para el objetivo deseado.");
                return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        // Asignar la dieta al cliente
        if (dietaId != 0) {
            String queryAsignarDieta = "UPDATE clientes SET id_dieta = ? WHERE id_cliente = ?";

            try {
                st = con.prepareStatement(queryAsignarDieta);
                st.setInt(1, dietaId);
                st.setInt(2, idUsuario);
                int rowsUpdated = st.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Dieta asignada correctamente al cliente.");
                } else {
                    System.out.println("No se pudo asignar la dieta al cliente.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // En este método, comprobamos de que la dieta asignada al cliente es inválida respecto a su objetivo corporal.
    private boolean comprobarDietaInvalidaCliente() {
        
        PreparedStatement st;
        ResultSet rs;
        boolean esInvalida = false;

        String query = "SELECT c.id_dieta, c.objetivo_corporal, d.tipo " +
                       "FROM clientes c " +
                       "LEFT JOIN dietas d ON c.id_dieta = d.id_dieta " +
                       "WHERE c.id_cliente = ?";

        try {
            st = con.prepareStatement(query);
            st.setInt(1, idUsuario);
            rs = st.executeQuery();

            if (rs.next()) {
                Integer idDieta = rs.getObject("id_dieta", Integer.class); // Allows null
                String objetivoCorporal = rs.getString("objetivo_corporal");
                String tipoDieta = rs.getString("tipo");

                if (idDieta == null || !objetivoCorporal.equals(tipoDieta)) {
                    esInvalida = true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return esInvalida;
    }
    
    // En este método, comprobamos de que la rutina asignada al cliente es inválida respecto a su objetivo corporal.
    private boolean comprobarRutinaInvalidaCliente() {
        
        PreparedStatement st;
        ResultSet rs;
        boolean esInvalida = false;

        String query = "SELECT c.id_rutina, c.objetivo_corporal, r.objetivo " +
                       "FROM clientes c " +
                       "LEFT JOIN rutinas r ON c.id_rutina = r.id_rutina " +
                       "WHERE c.id_cliente = ?";

        try {
            st = con.prepareStatement(query);
            st.setInt(1, idUsuario);
            rs = st.executeQuery();

            if (rs.next()) {
                Integer idRutina = rs.getObject("id_rutina", Integer.class); // Allows null
                String objetivoCorporal = rs.getString("objetivo_corporal");
                String objetivoRutina = rs.getString("objetivo");

                if (idRutina == null || !objetivoCorporal.equals(objetivoRutina)) {
                    esInvalida = true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return esInvalida;
    }
    
    
    // En este método, realizamos el cambio del estado del servicio, donde si el servicio es pagado actualizamos la fecha de expiración
    private void cambiarEstadoServicio(String estadoServicio, int diasServicio, int idServicio) {
        
        PreparedStatement st = null;
  
        try {
   
            if(estadoServicio.equals("Pagada")) {
                
                
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime nuevaFecha = now.plusDays(diasServicio);
                Timestamp nuevaFechaPagado = Timestamp.valueOf(nuevaFecha);

                st = con.prepareStatement("UPDATE pagos_servicios SET estado = ?, fecha_pagado = ? WHERE id_servicio = ? AND id_cliente = ?;");
                st.setString(1, estadoServicio);
                st.setTimestamp(2, nuevaFechaPagado);
                st.setInt(3, idServicio);
                st.setInt(4, idUsuario);
                
            } else {
                
                st = con.prepareStatement("UPDATE pagos_servicios SET estado = ? WHERE id_servicio = ? AND id_cliente = ?;");
                
                st.setString(1, estadoServicio);
                st.setInt(2, idServicio);
                st.setInt(3, idUsuario);
                
            }


            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        
        
        }
        
    }
    
    // Este método se encarga de comprobar si el servicio seleccionado está pagado.
    private boolean comprobarServicioPagado(int idServicio) {
        
        boolean esPagado = false;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = con.prepareStatement("SELECT estado FROM pagos_servicios WHERE id_servicio = ? AND id_cliente = ?;");
            st.setInt(1, idServicio);
            st.setInt(2, idUsuario);

            rs = st.executeQuery();

            if (rs.next()) {
                String estado = rs.getString("estado");
                if ("Pagada".equals(estado)) {
                    esPagado = true;
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
        return esPagado;
        
    }
    
    // Este método se encarga de comprobar si el servicio seleccionado está cancelado.
    private boolean comprobarServicioCancelado(int idServicio) {
        
        boolean noEsPagado = false;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = con.prepareStatement("SELECT estado FROM pagos_servicios WHERE id_servicio = ? AND id_cliente = ?;");
            st.setInt(1, idServicio);
            st.setInt(2, idUsuario);

            rs = st.executeQuery();

            if (rs.next()) {
                String estado = rs.getString("estado");
                if ("No pagada".equals(estado)) {
                    noEsPagado = true;
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
        return noEsPagado;
        
    }
    
  
    // Este método devuelve el id del alimento según su nombre
    private int obtenerIdAlimento(String nombreAlimento) {
        
        int idAlimento = 0;

        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT id_alimento FROM alimentos WHERE nombre = ?");

            st.setString(1, nombreAlimento);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
              
                idAlimento = rs.getInt(1);
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

       
        return idAlimento;
    }
    
    // Este método realiza la compra del producto con las unidades deseadas, donde se restará el precio final al saldo de la cuenta bancaria.
    private void realizarCompraProducto(int cantidadComprar, double precioTotal, int idProducto) {
        
        int cantidadProductoComprar=devolverCantidadDisponibleProducto(idProducto);
        
        if(cantidadProductoComprar<cantidadComprar) {
            
            JOptionPane.showMessageDialog(null, "No puedes comprar más de "+cantidadProductoComprar+ " unidades en este producto.");
       
        } else if(!comprobarTieneDinero(precioTotal)) {
            
            JOptionPane.showMessageDialog(null, "No tienes suficiente dinero para comprar el producto.");
            
        } else {
            
            PreparedStatement st;

            try {

                con.setAutoCommit(false);
                
                st = con.prepareStatement("UPDATE cuentas_bancarias SET saldo = saldo - ? WHERE dni = ?;");
                st.setDouble(1, precioTotal);
                st.setString(2, dniUsuario);
                st.executeUpdate();

                LocalDateTime now = LocalDateTime.now();
                Timestamp timestamp = Timestamp.valueOf(now);

                st = con.prepareStatement("INSERT INTO ventas (id_producto, id_cliente, cantidad_vendida, fecha_venta) VALUES (?, ?, ?, ?);");
                st.setInt(1, idProducto);
                st.setInt(2, idUsuario);  
                st.setInt(3, cantidadComprar);
                st.setTimestamp(4, timestamp); 
                st.executeUpdate();

                // Actualizar cantidad disponible del producto
                st = con.prepareStatement("UPDATE productos SET cantidad_disponible = cantidad_disponible - ? WHERE id_producto = ?;");
                st.setInt(1, cantidadComprar);
                st.setInt(2, idProducto);
                st.executeUpdate();
                
                con.commit();

                JOptionPane.showMessageDialog(null, "Compra realizada con éxito.");

            
            } catch (SQLException ex) {
                Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    
    // Este método comprueba si el cliente dispone de dinero suficiente para pagar el servicio o producto deseado.
    private boolean comprobarTieneDinero(double precioTotal) {
        
        PreparedStatement st;
        
        boolean hayDinero=false;
        
        try {
            st = con.prepareStatement("SELECT saldo FROM cuentas_bancarias WHERE dni = ?;");

            st.setString(1, dniUsuario);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
              
                double saldoCliente=rs.getDouble(1);
                
                if(saldoCliente>=precioTotal) {
                    
                    hayDinero=true;
                }
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return hayDinero;
    }
    
    // Este método devuelve la cantidad disponible de un determinado producto.
    private int devolverCantidadDisponibleProducto(int idProducto) {
        
        int cantidadProducto=0;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT cantidad_disponible FROM productos WHERE id_producto = ?;");

            st.setInt(1, idProducto);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
              
                cantidadProducto = rs.getInt(1);
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cantidadProducto;
        
    }
    
    // En este método, devuelve el id del ejercicio a través de su nombre.
    private int obtenerIdEjercicio(String nombreEjercicio) {
        
        int idEjercicio = 0;

        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT id_ejercicio FROM ejercicios WHERE nombre = ?");

            st.setString(1, nombreEjercicio);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
              
                idEjercicio = rs.getInt("id_ejercicio");
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

       
        return idEjercicio;
    }
    
    // En este método, se comprueba si la rutina seleccionada existe en el sistema
    private boolean comprobarRutinaExistente(String nombreRutina) {
        
        boolean rutinaExiste=false;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT * FROM gym_zone.rutinas WHERE nombre = ?");

            st.setString(1, nombreRutina);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
              
                rutinaExiste=true;
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rutinaExiste;
        
    }
    
    // En este método, se comprueba si el producto seleccionado existe en el sistema
    private boolean comprobarProductoExistente(String nombreProducto) {
        
        boolean productoExiste=false;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT * FROM gym_zone.productos WHERE nombre = ?");

            st.setString(1, nombreProducto);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
              
                productoExiste=true;
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productoExiste;
        
    }
    
    // En este método, se comprueba si la sala seleccionada existe en el sistema
    private boolean comprobarSalaExistente(String nombreSala) {
        
        boolean salaExiste=false;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT * FROM gym_zone.salas WHERE nombre = ?");

            st.setString(1, nombreSala);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
              
                salaExiste=true;
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return salaExiste;
        
    }
    
    // En este método, se comprueba si la dieta seleccionada existe en el sistema
    private boolean comprobarDietaExistente(String nombreDieta) {
        
        boolean dietaExiste=false;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT * FROM gym_zone.dietas WHERE nombre = ?");

            st.setString(1, nombreDieta);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
              
                dietaExiste=true;
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dietaExiste;
        
    }
    
    // En este método, devuelve el id de la rutina según el nombre
    private int devolverIdRutina(String nombreRutina) {
        
        int idRutinaBuscar=0;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT * FROM gym_zone.rutinas WHERE nombre = ?");

            st.setString(1, nombreRutina);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
              
                idRutinaBuscar=rs.getInt(1);
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return idRutinaBuscar;
        
    }
    
    // En este método, devuelve el id de la dieta según el nombre
    private int devolverIdDieta(String nombreDieta) {
        
        int idDietaBuscar=0;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT * FROM gym_zone.dietas WHERE nombre = ?");

            st.setString(1, nombreDieta);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
              
                idDietaBuscar=rs.getInt(1);
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return idDietaBuscar;
        
    }
    
    
    // En este método, devuelve el id del producto según el nombre
    private int devolverIdProducto(String nombreProducto) {
        
        int idProductoBuscar=0;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT * FROM gym_zone.productos WHERE nombre = ?");

            st.setString(1, nombreProducto);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
              
                idProductoBuscar=rs.getInt(1);
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return idProductoBuscar;
        
    }
    
    // En este método, devuelve el id del servicio según el nombre
    private int devolverIdServicio(String nombreServicio) {
        
        int idServicioBuscar=0;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT * FROM gym_zone.servicios WHERE nombre = ?");

            st.setString(1, nombreServicio);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
              
                idServicioBuscar=rs.getInt(1);
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return idServicioBuscar;
        
    }
    
    // En este método, devuelve el id de la sala según el nombre
    private int devolverIdSala(String nombreSala) {
        
        int idSalaBuscar=0;
        
        PreparedStatement st;
        
        try {
            st = con.prepareStatement("SELECT * FROM gym_zone.salas WHERE nombre = ?");

            st.setString(1, nombreSala);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
              
                idSalaBuscar=rs.getInt(1);
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JFramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return idSalaBuscar;
        
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFramePrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton63;
    private javax.swing.JButton jButton64;
    private javax.swing.JButton jButton65;
    private javax.swing.JButton jButton66;
    private javax.swing.JButton jButton67;
    private javax.swing.JButton jButton68;
    private javax.swing.JButton jButton69;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton70;
    private javax.swing.JButton jButton71;
    private javax.swing.JButton jButton72;
    private javax.swing.JButton jButton73;
    private javax.swing.JButton jButton74;
    private javax.swing.JButton jButton75;
    private javax.swing.JButton jButton76;
    private javax.swing.JButton jButton77;
    private javax.swing.JButton jButton78;
    private javax.swing.JButton jButton79;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton80;
    private javax.swing.JButton jButton81;
    private javax.swing.JButton jButton82;
    private javax.swing.JButton jButton83;
    private javax.swing.JButton jButton84;
    private javax.swing.JButton jButton85;
    private javax.swing.JButton jButton86;
    private javax.swing.JButton jButton87;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton_AdministrarProducto;
    private javax.swing.JButton jButton_AdministrarRutina;
    private javax.swing.JButton jButton_AdministrarSala;
    private javax.swing.JButton jButton_AdministrarServicio;
    private javax.swing.JButton jButton_AltaProducto;
    private javax.swing.JButton jButton_AltaReserva;
    private javax.swing.JButton jButton_AltaReservaDia;
    private javax.swing.JButton jButton_AltaReservaTarde;
    private javax.swing.JButton jButton_AltaRutina;
    private javax.swing.JButton jButton_AltaSala;
    private javax.swing.JButton jButton_AltaServicio;
    private javax.swing.JButton jButton_CancelarReserva;
    private javax.swing.JButton jButton_ConsultaProducto;
    private javax.swing.JButton jButton_ConsultaSala;
    private javax.swing.JButton jButton_ConsultaServicio;
    private javax.swing.JButton jButton_PC_Editar;
    private javax.swing.JButton jButton_PC_Volver;
    private javax.swing.JButton jButton_PU_AccederPerCliente;
    private javax.swing.JButton jButton_PU_Editar;
    private javax.swing.JButton jButton_PU_Volver;
    private javax.swing.JButton jButton_PagarProducto;
    private javax.swing.JButton jButton_PagarServicio;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox14;
    private javax.swing.JComboBox<String> jComboBox15;
    private javax.swing.JComboBox<String> jComboBox16;
    private javax.swing.JComboBox<String> jComboBox17;
    private javax.swing.JComboBox<String> jComboBox18;
    private javax.swing.JComboBox<String> jComboBox19;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox20;
    private javax.swing.JComboBox<String> jComboBox21;
    private javax.swing.JComboBox<String> jComboBox22;
    private javax.swing.JComboBox<String> jComboBox23;
    private javax.swing.JComboBox<String> jComboBox24;
    private javax.swing.JComboBox<String> jComboBox25;
    private javax.swing.JComboBox<String> jComboBox26;
    private javax.swing.JComboBox<String> jComboBox27;
    private javax.swing.JComboBox<String> jComboBox28;
    private javax.swing.JComboBox<String> jComboBox29;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox30;
    private javax.swing.JComboBox<String> jComboBox31;
    private javax.swing.JComboBox<String> jComboBox32;
    private javax.swing.JComboBox<String> jComboBox33;
    private javax.swing.JComboBox<String> jComboBox34;
    private javax.swing.JComboBox<String> jComboBox35;
    private javax.swing.JComboBox<String> jComboBox36;
    private javax.swing.JComboBox<String> jComboBox37;
    private javax.swing.JComboBox<String> jComboBox38;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JDialog jDialog_AltaDieta;
    private javax.swing.JDialog jDialog_AltaProducto;
    private javax.swing.JDialog jDialog_AltaReserva;
    private javax.swing.JDialog jDialog_AltaRutina;
    private javax.swing.JDialog jDialog_AltaSala;
    private javax.swing.JDialog jDialog_AltaServicio;
    private javax.swing.JDialog jDialog_ConsultaCuentaBancaria;
    private javax.swing.JDialog jDialog_ConsultaDieta;
    private javax.swing.JDialog jDialog_ConsultaDieta_VisualizarAlimentos;
    private javax.swing.JDialog jDialog_ConsultaPagosServicios;
    private javax.swing.JDialog jDialog_ConsultaProducto;
    private javax.swing.JDialog jDialog_ConsultaReserva;
    private javax.swing.JDialog jDialog_ConsultaRutina;
    private javax.swing.JDialog jDialog_ConsultaRutina_ModificarRutina;
    private javax.swing.JDialog jDialog_ConsultaRutina_VerEjercicios;
    private javax.swing.JDialog jDialog_ConsultaSala;
    private javax.swing.JDialog jDialog_ConsultaServicio;
    private javax.swing.JDialog jDialog_ConsultaVenta;
    private javax.swing.JDialog jDialog_DietaDia;
    private javax.swing.JDialog jDialog_FuncionalidadAdministrador;
    private javax.swing.JDialog jDialog_FuncionalidadCliente;
    private javax.swing.JDialog jDialog_FuncionalidadMonitor;
    private javax.swing.JDialog jDialog_FuncionalidadNutricionista;
    private javax.swing.JDialog jDialog_GestionCuentaBancaria;
    private javax.swing.JDialog jDialog_GestionReserva;
    private javax.swing.JDialog jDialog_IniciarSesion;
    private javax.swing.JDialog jDialog_Inicio;
    private javax.swing.JDialog jDialog_ModificarCuentaBancaria;
    private javax.swing.JDialog jDialog_ModificarDieta;
    private javax.swing.JDialog jDialog_ModificarProducto;
    private javax.swing.JDialog jDialog_ModificarSala;
    private javax.swing.JDialog jDialog_ModificarServicio;
    private javax.swing.JDialog jDialog_OperacionesPagosCliente;
    private javax.swing.JDialog jDialog_OperacionesProductoAdministrador;
    private javax.swing.JDialog jDialog_OperacionesReservasCliente;
    private javax.swing.JDialog jDialog_OperacionesRutinaAdministrador;
    private javax.swing.JDialog jDialog_OperacionesSalaAdministrador;
    private javax.swing.JDialog jDialog_OperacionesServicioAdministrador;
    private javax.swing.JDialog jDialog_PagoProducto;
    private javax.swing.JDialog jDialog_PagoServicio;
    private javax.swing.JDialog jDialog_PerfilCliente;
    private javax.swing.JDialog jDialog_PerfilUsuario;
    private javax.swing.JDialog jDialog_RegistrarCuentaBancaria;
    private javax.swing.JDialog jDialog_Registrarse;
    private javax.swing.JDialog jDialog_RegistrarseCliente;
    private javax.swing.JDialog jDialog_RegistrarseMonitor;
    private javax.swing.JDialog jDialog_RegistrarseNutricionista;
    private javax.swing.JDialog jDialog_RutinaDia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JLabel jLabelFondoRol;
    private javax.swing.JLabel jLabel_AdministrarProducto;
    private javax.swing.JLabel jLabel_AdministrarRutina;
    private javax.swing.JLabel jLabel_AdministrarSala;
    private javax.swing.JLabel jLabel_AdministrarServicio;
    private javax.swing.JLabel jLabel_AltaProducto;
    private javax.swing.JLabel jLabel_AltaReserva;
    private javax.swing.JLabel jLabel_AltaReservaDia;
    private javax.swing.JLabel jLabel_AltaReservaTarde;
    private javax.swing.JLabel jLabel_AltaRutina;
    private javax.swing.JLabel jLabel_AltaSala;
    private javax.swing.JLabel jLabel_AltaServicio;
    private javax.swing.JLabel jLabel_Banco;
    private javax.swing.JLabel jLabel_BuscarDieta;
    private javax.swing.JLabel jLabel_BuscarPagarProducto;
    private javax.swing.JLabel jLabel_BuscarPagarServicio;
    private javax.swing.JLabel jLabel_BuscarPagosServicio;
    private javax.swing.JLabel jLabel_BuscarProducto;
    private javax.swing.JLabel jLabel_BuscarReserva;
    private javax.swing.JLabel jLabel_BuscarRutina;
    private javax.swing.JLabel jLabel_BuscarSala;
    private javax.swing.JLabel jLabel_BuscarServicio;
    private javax.swing.JLabel jLabel_BuscarVenta;
    private javax.swing.JLabel jLabel_CancelarReserva;
    private javax.swing.JLabel jLabel_ConsultaCuentaBancaria;
    private javax.swing.JLabel jLabel_ConsultaProducto;
    private javax.swing.JLabel jLabel_ConsultaSala;
    private javax.swing.JLabel jLabel_ConsultaServicio;
    private javax.swing.JLabel jLabel_FCC_Administrador;
    private javax.swing.JLabel jLabel_FCC_Cliente;
    private javax.swing.JLabel jLabel_FCC_Monitor;
    private javax.swing.JLabel jLabel_FCC_Nutricionista;
    private javax.swing.JLabel jLabel_FG_Administrador;
    private javax.swing.JLabel jLabel_FG_Cliente;
    private javax.swing.JLabel jLabel_FG_Monitor;
    private javax.swing.JLabel jLabel_FG_Nutricionista;
    private javax.swing.JLabel jLabel_FondoAltaDieta;
    private javax.swing.JLabel jLabel_FondoAltaProducto;
    private javax.swing.JLabel jLabel_FondoAltaReserva;
    private javax.swing.JLabel jLabel_FondoAltaRutina;
    private javax.swing.JLabel jLabel_FondoAltaSala;
    private javax.swing.JLabel jLabel_FondoAltaServicio;
    private javax.swing.JLabel jLabel_FondoCancelarReserva;
    private javax.swing.JLabel jLabel_FondoConsultaDieta;
    private javax.swing.JLabel jLabel_FondoConsultaEjercicios;
    private javax.swing.JLabel jLabel_FondoConsultaPagarProducto;
    private javax.swing.JLabel jLabel_FondoConsultaPagarServicio;
    private javax.swing.JLabel jLabel_FondoConsultaPagosServicio;
    private javax.swing.JLabel jLabel_FondoConsultaProducto;
    private javax.swing.JLabel jLabel_FondoConsultaReserva;
    private javax.swing.JLabel jLabel_FondoConsultaRutina;
    private javax.swing.JLabel jLabel_FondoConsultaSala;
    private javax.swing.JLabel jLabel_FondoConsultaServicio;
    private javax.swing.JLabel jLabel_FondoConsultaVenta;
    private javax.swing.JLabel jLabel_FondoDietaDia;
    private javax.swing.JLabel jLabel_FondoFuncionalidadesAdministrador;
    private javax.swing.JLabel jLabel_FondoFuncionalidadesCliente;
    private javax.swing.JLabel jLabel_FondoFuncionalidadesMonitor;
    private javax.swing.JLabel jLabel_FondoFuncionalidadesNutricionista;
    private javax.swing.JLabel jLabel_FondoInicioSesion;
    private javax.swing.JLabel jLabel_FondoModificarDieta;
    private javax.swing.JLabel jLabel_FondoModificarProducto;
    private javax.swing.JLabel jLabel_FondoModificarRutina;
    private javax.swing.JLabel jLabel_FondoModificarSala;
    private javax.swing.JLabel jLabel_FondoModificarServicio;
    private javax.swing.JLabel jLabel_FondoOperacionesPagosCliente;
    private javax.swing.JLabel jLabel_FondoOperacionesProductoAdministrador;
    private javax.swing.JLabel jLabel_FondoOperacionesReservasCliente;
    private javax.swing.JLabel jLabel_FondoOperacionesRutinaAdministrador;
    private javax.swing.JLabel jLabel_FondoOperacionesSalaAdministrador;
    private javax.swing.JLabel jLabel_FondoOperacionesServicioAdministrador;
    private javax.swing.JLabel jLabel_FondoRutinaDia;
    private javax.swing.JLabel jLabel_FondoVerEjercicios;
    private javax.swing.JLabel jLabel_FotoAbs;
    private javax.swing.JLabel jLabel_FotoAceites;
    private javax.swing.JLabel jLabel_FotoAlmuerzo;
    private javax.swing.JLabel jLabel_FotoBrazo;
    private javax.swing.JLabel jLabel_FotoCarbohidratos;
    private javax.swing.JLabel jLabel_FotoCena;
    private javax.swing.JLabel jLabel_FotoDesayuno;
    private javax.swing.JLabel jLabel_FotoDulces;
    private javax.swing.JLabel jLabel_FotoEspalda;
    private javax.swing.JLabel jLabel_FotoFruta;
    private javax.swing.JLabel jLabel_FotoLacteo;
    private javax.swing.JLabel jLabel_FotoMerienda;
    private javax.swing.JLabel jLabel_FotoPecho;
    private javax.swing.JLabel jLabel_FotoPierna;
    private javax.swing.JLabel jLabel_FotoProteina;
    private javax.swing.JLabel jLabel_FotoReiniciar;
    private javax.swing.JLabel jLabel_FotoReiniciarDieta;
    private javax.swing.JLabel jLabel_FotoVerdura;
    private javax.swing.JLabel jLabel_GestionCuentaBancaria;
    private javax.swing.JLabel jLabel_IniciarSesion;
    private javax.swing.JLabel jLabel_ModificarCuentaBancaria;
    private javax.swing.JLabel jLabel_NU_Administrador;
    private javax.swing.JLabel jLabel_NU_Cliente;
    private javax.swing.JLabel jLabel_NU_Monitor;
    private javax.swing.JLabel jLabel_NU_Nutricionista;
    private javax.swing.JLabel jLabel_PC_Altura;
    private javax.swing.JLabel jLabel_PC_Edad;
    private javax.swing.JLabel jLabel_PC_EditarAltura;
    private javax.swing.JLabel jLabel_PC_EditarObjetivo;
    private javax.swing.JLabel jLabel_PC_EditarPeso;
    private javax.swing.JLabel jLabel_PC_Fondo;
    private javax.swing.JLabel jLabel_PC_FotoPerfil;
    private javax.swing.JLabel jLabel_PC_NombreApellidos;
    private javax.swing.JLabel jLabel_PC_Objetivo;
    private javax.swing.JLabel jLabel_PC_Peso;
    private javax.swing.JLabel jLabel_PU_Contrasenna;
    private javax.swing.JLabel jLabel_PU_Correo;
    private javax.swing.JLabel jLabel_PU_EditarContrasenna;
    private javax.swing.JLabel jLabel_PU_EditarCorreo;
    private javax.swing.JLabel jLabel_PU_EditarNombreUsuario;
    private javax.swing.JLabel jLabel_PU_Fondo;
    private javax.swing.JLabel jLabel_PU_FotoPerfil;
    private javax.swing.JLabel jLabel_PU_NombreApellidos;
    private javax.swing.JLabel jLabel_PU_NombreUsuario;
    private javax.swing.JLabel jLabel_PagarProducto;
    private javax.swing.JLabel jLabel_PagarServicio;
    private javax.swing.JLabel jLabel_RegistrarCuentaBancaria;
    private javax.swing.JLabel jLabel_Registrarse;
    private javax.swing.JLabel jLabel_RegistrarseCliente;
    private javax.swing.JLabel jLabel_RegistrarseMonitor;
    private javax.swing.JLabel jLabel_RegistrarseNutricionista;
    private javax.swing.JLabel jLabel_ReiniciarBusquedaDieta;
    private javax.swing.JLabel jLabel_ReiniciarBusquedaPagarProducto;
    private javax.swing.JLabel jLabel_ReiniciarBusquedaPagarServicio;
    private javax.swing.JLabel jLabel_ReiniciarBusquedaPagosServicio;
    private javax.swing.JLabel jLabel_ReiniciarBusquedaProducto;
    private javax.swing.JLabel jLabel_ReiniciarBusquedaReserva;
    private javax.swing.JLabel jLabel_ReiniciarBusquedaRutina;
    private javax.swing.JLabel jLabel_ReiniciarBusquedaSala;
    private javax.swing.JLabel jLabel_ReiniciarBusquedaServicio;
    private javax.swing.JLabel jLabel_ReiniciarBusquedaVenta;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable12;
    private javax.swing.JTable jTable13;
    private javax.swing.JTable jTable14;
    private javax.swing.JTable jTable15;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables


}
