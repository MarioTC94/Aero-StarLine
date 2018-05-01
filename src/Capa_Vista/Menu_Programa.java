package Capa_Vista;

import Capa_Entidades.Avion;
import Capa_Negocios.Colas_Aterrizaje;
import Capa_Negocios.Colas_Despegue;
import Capa_Negocios.ListaDoble_Vuelo;
import Capa_Negocios.Pilas_Hangar;
import Capa_Datos.Array_DatosAvion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Random;
import javax.swing.*;

public class Menu_Programa extends JFrame {

    private final Pilas_Hangar lsHangar = new Pilas_Hangar();
    private final Colas_Despegue lsDespegue = new Colas_Despegue();
    private final ListaDoble_Vuelo lsVuelo = new ListaDoble_Vuelo();
    private final Colas_Aterrizaje lsAterrizaje = new Colas_Aterrizaje();
    private final Array_DatosAvion arrayDatos = new Array_DatosAvion();

    Menu_Programa() {
        super("Aero Starline");
        setComponents();
        setEvents();
        RandomAviones();
    }

    public final void setEvents() { /*Validaciones*/
        btninsertar.addActionListener((ActionEvent ae) -> {
            if (txtModelo.getText().trim().equals("") || txtCantP.getText().trim().equals("") || txtCantSob.getText().trim().equals("") || txtNomPiloto.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Escriba los datos del avión", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (txtCantP.getText().matches("[a-zA-z]*")) {
                JOptionPane.showMessageDialog(this, "Solo se permiten números en los campos de información de Pasajeros", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (txtCantSob.getText().matches("[a-zA-z]*")) {
                JOptionPane.showMessageDialog(this, "Solo se permiten números en los campos de información de Cantidad de Sobrecargos", "Error", JOptionPane.ERROR_MESSAGE);
                txtCantSob.setText("");
                return;
            }

            if (!txtNomPiloto.getText().matches("[a-zA-z]*")) {
                JOptionPane.showMessageDialog(this, "Solo se permiten letras en el campo de información del Nombre del Piloto", "Error", JOptionPane.ERROR_MESSAGE);
                txtNomPiloto.setText("");
                return;
            }

            int acum = lsHangar.getLength() + lsDespegue.getLength() + lsVuelo.getLength() + lsAterrizaje.getLength();
            int acum1 = acum + num;
            if (acum1<=26 -1 ) {
                acum1++;             
            }else{
                 JOptionPane.showMessageDialog(this, "Solo se permite el ingreso de 25 aviones", "Error", JOptionPane.ERROR_MESSAGE);
                 return;
            }

            switch (cboListas.getSelectedItem().toString()) {
                case "Hangar":
                    lsHangar.InsertarAvion(txtModelo.getText(), Integer.parseInt(txtCantP.getText()), Integer.parseInt(txtCantSob.getText()), txtNomPiloto.getText());
                    JOptionPane.showMessageDialog(this, "Avión insertado correctamente");
                    actualizarlistaHangar();
                    txtModelo.setText("");
                    txtCantP.setText("");
                    txtCantSob.setText("");
                    txtNomPiloto.setText("");
                    break;
                case "Pista de Despegue":
                    lsDespegue.InsertarAvion(txtModelo.getText(), Integer.parseInt(txtCantP.getText()), Integer.parseInt(txtCantSob.getText()), txtNomPiloto.getText());
                    JOptionPane.showMessageDialog(this, "Avión insertado correctamente");
                    actualizarlistardespegue();
                    txtModelo.setText("");
                    txtCantP.setText("");
                    txtCantSob.setText("");
                    txtNomPiloto.setText("");
                    break;
                case "Vuelo":
                    lsVuelo.InsertarAvion(txtModelo.getText(), Integer.parseInt(txtCantP.getText()), Integer.parseInt(txtCantSob.getText()), txtNomPiloto.getText());
                    JOptionPane.showMessageDialog(this, "Avión insertado correctamente");
                    lsVuelo.OrdenarNomPiloto();
                    actualizarlistavuelo();
                    txtModelo.setText("");
                    txtCantP.setText("");
                    txtCantSob.setText("");
                    txtNomPiloto.setText("");
                    break;
                case "Pista de Aterrizaje":
                    lsAterrizaje.InsertarAvion(txtModelo.getText(), Integer.parseInt(txtCantP.getText()), Integer.parseInt(txtCantSob.getText()), txtNomPiloto.getText());
                    JOptionPane.showMessageDialog(this, "Avión insertado correctamente");
                    actualizarlistaAterrizaje();
                    txtModelo.setText("");
                    txtCantP.setText("");
                    txtCantSob.setText("");
                    txtNomPiloto.setText("");
                    break;

                default:
            }
        });

        btndespegue.addActionListener((ActionEvent ae) -> {
            Avion aux = lsHangar.ExtraerAvion();
            if (aux != null) {
                lsDespegue.InsertarAvion(aux);
                actualizarlistaHangar();
                actualizarlistardespegue();
            } else {
                JOptionPane.showMessageDialog(this, "No hay Aviones en el Hangar", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });
        btnvuelo.addActionListener((ActionEvent ae) -> {
            Avion aux = lsDespegue.ExtraerAvion();
            if (aux != null) {
                lsVuelo.InsertarAvion(aux);
                lsVuelo.OrdenarNomPiloto();
                actualizarlistardespegue();
                actualizarlistavuelo();
            } else {
                JOptionPane.showMessageDialog(this, "No hay Aviones en Pista de Despegue", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnaterrizaje.addActionListener((ActionEvent ae) -> {

            if (listavuelo.getSelectedIndex() != -1) {
                Avion aux = lsVuelo.ExtraerAvion(listavuelo.getSelectedIndex());
                if (aux != null) {
                    lsAterrizaje.InsertarAvion(aux);
                    actualizarlistavuelo();
                    actualizarlistaAterrizaje();
                }

            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un avión primero", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        btnguardaravion.addActionListener((ActionEvent ae) -> {
            Avion aux = lsAterrizaje.ExtraerAvion();

            if (aux != null) {
                lsHangar.InsertarAvion(aux);
                actualizarlistaAterrizaje();
                actualizarlistaHangar();
            } else {
                JOptionPane.showMessageDialog(this, "No hay Aviones en Pista de Aterrizaje", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        //Eventos del doble click en las listas
        listahangar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    Avion aux = lsHangar.get(list.getSelectedIndex());
                    String value = "\nCantidad pasajeros: " + aux.getCantPasajeros() + "\nSobrecargos: " + aux.getCantSobrecargos() + "\nNombre del piloto: " + aux.getNomPiloto();
                    JOptionPane.showMessageDialog(null, value, "Avión", JOptionPane.INFORMATION_MESSAGE);
                }

            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
        listapistadespegue.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    Avion aux = lsDespegue.get(list.getSelectedIndex());
                    String value = "\nCantidad pasajeros: " + aux.getCantPasajeros() + "\nSobrecargos: " + aux.getCantSobrecargos() + "\nNombre del piloto: " + aux.getNomPiloto();
                    JOptionPane.showMessageDialog(null, value, "Avión", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
        listavuelo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    Avion aux = lsVuelo.get(list.getSelectedIndex());
                    String value = "\nCantidad pasajeros: " + aux.getCantPasajeros() + "\nSobrecargos: " + aux.getCantSobrecargos();
                    JOptionPane.showMessageDialog(null, value, "Avión", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
        listapistaaterrizaje.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    Avion aux = lsAterrizaje.get(list.getSelectedIndex());
                    String value = "\nCantidad pasajeros: " + aux.getCantPasajeros() + "\nSobrecargos: " + aux.getCantSobrecargos() + "\nNombre del Pilot: " + aux.getNomPiloto();
                    JOptionPane.showMessageDialog(null, value, "Avión", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
    }
    //Componentes del programa

    public final void setComponents() {

        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Creación del Icono del programa
        icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Icono_avion.png"));
        setIconImage(icon);

        //Creación del panel, menu del Programa
        panelMenu = new JPanel(new GridLayout(2, 1));
        panelMenu.setBorder(BorderFactory.createTitledBorder("Menú"));

        panelimg = new JPanel(new BorderLayout());

        url = getClass().getResource("/Imagenes/img_avionpanel.png");
        img = new ImageIcon(url);
        lbimagen = new JLabel(img);
        panelimg.add(lbimagen, BorderLayout.CENTER);

        //Creación del panel insertar avión
        panelInsertar = new JPanel(new GridLayout(5, 2));
        panelInsertar.setBorder(BorderFactory.createTitledBorder("Insertar Avión"));

        //Elementos del Panel InsertarAvion
        lbModelo = new JLabel("Modelo");
        txtModelo = new JTextField(10);
        lbCantP = new JLabel("Cantidad de Pasajeros");
        txtCantP = new JTextField(10);
        lbCantSob = new JLabel("Cantidad de Sobrecargos");
        txtCantSob = new JTextField(10);
        lbNomPiloto = new JLabel("Nombre del Piloto");
        txtNomPiloto = new JTextField(10);
        cboListas = new JComboBox(new String[]{"Hangar", "Pista de Despegue", "Pista de Aterrizaje", "Vuelo"});
        btninsertar = new JButton("Insertar Avión");

        //Añadir los elementos al panel insertar
        JPanel p1 = new JPanel(new GridBagLayout());
        p1.add(lbModelo);
        panelInsertar.add(p1);

        JPanel p2 = new JPanel(new GridBagLayout());
        p2.add(txtModelo);
        panelInsertar.add(p2);

        JPanel p3 = new JPanel(new GridBagLayout());
        p3.add(lbCantP);
        panelInsertar.add(p3);

        JPanel p4 = new JPanel(new GridBagLayout());
        p4.add(txtCantP);
        panelInsertar.add(p4);

        JPanel p5 = new JPanel(new GridBagLayout());
        p5.add(lbCantSob);
        panelInsertar.add(p5);

        JPanel p6 = new JPanel(new GridBagLayout());
        p6.add(txtCantSob);
        panelInsertar.add(p6);

        JPanel p7 = new JPanel(new GridBagLayout());
        p7.add(lbNomPiloto);
        panelInsertar.add(p7);

        JPanel p8 = new JPanel(new GridBagLayout());
        p8.add(txtNomPiloto);
        panelInsertar.add(p8);

        JPanel p9 = new JPanel(new GridBagLayout());
        p9.add(cboListas);
        panelInsertar.add(p9);

        JPanel p10 = new JPanel(new GridBagLayout());
        p10.add(btninsertar);
        panelInsertar.add(p10);

        //Creacion del panel main con los Jlist y los botones .
        panelmain = new JPanel(new GridLayout(2, 2));
        panelmain.setBorder(BorderFactory.createTitledBorder("Vista de los Aviones"));

        modelohangar = new DefaultListModel();
        modelopistdespegue = new DefaultListModel();
        modelovuelo = new DefaultListModel();
        modelopistaaterrizaje = new DefaultListModel();

        listahangar = new JList(modelohangar);
        listapistadespegue = new JList(modelopistdespegue);
        listavuelo = new JList(modelovuelo);
        listapistaaterrizaje = new JList(modelopistaaterrizaje);

        JPanel p15 = new JPanel(new BorderLayout());
        p15.setBorder(BorderFactory.createTitledBorder("Hangar"));
        p15.add(listahangar, BorderLayout.CENTER);
        panelmain.add(p15);

        JPanel p16 = new JPanel(new BorderLayout());
        p16.setBorder(BorderFactory.createTitledBorder("Pista de Despegue"));
        p16.add(listapistadespegue, BorderLayout.CENTER);
        panelmain.add(p16);

        JPanel p17 = new JPanel(new BorderLayout());
        p17.setBorder(BorderFactory.createTitledBorder("Avión en Vuelo"));
        p17.add(listavuelo, BorderLayout.CENTER);
        panelmain.add(p17);

        JPanel p18 = new JPanel(new BorderLayout());
        p18.setBorder(BorderFactory.createTitledBorder("Pista de Aterrizaje"));
        p18.add(listapistaaterrizaje, BorderLayout.CENTER);
        panelmain.add(p18);

        btndespegue = new JButton("Mover Avión a Pista de Despegue");
        btnvuelo = new JButton("Despegar Avión");
        btnaterrizaje = new JButton("Aterrizar Avión");
        btnguardaravion = new JButton("Guardar Avión en el Hangar");

        //Añadir los elementos, paneles, botones, textfield etc.
        panelMenu.add(panelInsertar);
        panelMenu.add(panelimg);
        p15.add(btndespegue, BorderLayout.SOUTH);
        p16.add(btnvuelo, BorderLayout.SOUTH);
        p17.add(btnaterrizaje, BorderLayout.SOUTH);
        p18.add(btnguardaravion, BorderLayout.SOUTH);
        add(panelMenu, BorderLayout.WEST);
        add(panelmain, BorderLayout.CENTER);

    }

    //Métodos para actualizar cada lista
    private void actualizarlistaHangar() { // Metodos para Actualizar Listas
        modelohangar.clear();
        for (int i = 0; i < lsHangar.getLength(); i++) {
            modelohangar.addElement(" Modelo Avión: " + lsHangar.get(i).getModeloAvion());
        }
    }

    private void actualizarlistardespegue() {
        modelopistdespegue.clear();
        for (int i = 0; i < lsDespegue.getLength(); i++) {
            modelopistdespegue.addElement(" Modelo Avión: " + lsDespegue.get(i).getModeloAvion());
        }
    }

    private void actualizarlistavuelo() {
        modelovuelo.clear();
        for (int i = 0; i < lsVuelo.getLength(); i++) {
            modelovuelo.addElement("Modelo Avión: " + lsVuelo.get(i).getModeloAvion() + "." + " < > " + " Nombre del Piloto: " + lsVuelo.get(i).getNomPiloto() + ".");
        }
    }

    private void actualizarlistaAterrizaje() {
        modelopistaaterrizaje.clear();
        for (int i = 0; i < lsAterrizaje.getLength(); i++) {
            modelopistaaterrizaje.addElement("Modelo Avión: " + lsAterrizaje.get(i).getModeloAvion());
        }
    }

    public final void RandomAviones() {
        random = new Random();

        for (int i = 0; i < 10; i++) {
            num = random.nextInt(4);
            Avion aux = new Avion(arrayDatos.getModAvion(), arrayDatos.getCantPas(), arrayDatos.getCantSob(), arrayDatos.getNomPil());
            switch (num) {

                case 0:
                    lsHangar.InsertarAvion(aux);
                    actualizarlistaHangar();
                    break;

                case 1:
                    lsDespegue.InsertarAvion(aux);
                    actualizarlistardespegue();
                    break;

                case 2:
                    lsVuelo.InsertarAvion(aux);
                    lsVuelo.OrdenarNomPiloto();
                    actualizarlistavuelo();
                    break;

                case 3:
                    lsAterrizaje.InsertarAvion(aux);
                    actualizarlistaAterrizaje();
                    break;
                default:

            }

        }

    }

    private JPanel panelMenu, panelmain, panelInsertar, panelimg;
    private JLabel lbModelo, lbCantP, lbCantSob, lbNomPiloto, lbimagen;
    private JTextField txtModelo, txtCantP, txtCantSob, txtNomPiloto;
    private JComboBox cboListas;
    private DefaultListModel modelohangar, modelovuelo, modelopistdespegue, modelopistaaterrizaje;
    private JList listahangar, listavuelo, listapistadespegue, listapistaaterrizaje;
    private JButton btninsertar, btndespegue, btnvuelo, btnaterrizaje, btnguardaravion;
    private URL url;
    private Image icon;
    private ImageIcon img;
    private Random random;
    private int num;
}
