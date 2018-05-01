package Capa_Vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Presentacion_Programa extends JFrame {
    
    private JPanel panel;
    private JLabel titulo, img;
    private Timer timer;
   
    public Presentacion_Programa() {
        
        setComponents();
        setEvents();
        setSize(550, 350);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        timer.start();
        timer.setRepeats(false);
    }

    public final void setComponents() {

        panel = new JPanel();
        panel.setBackground(new Color(9, 1, 56));
        panel.setLayout(null);

        titulo = new JLabel("Aero StarLine");
        titulo.setFont(new Font("Harrington", Font.BOLD, 50));
        titulo.setLocation(110, 50);
        titulo.setForeground(Color.white);
        titulo.setSize(titulo.getMinimumSize());

        img = new JLabel(new ImageIcon("src/Imagenes/img_avionpresentacion.png"));
        img.setSize(img.getMaximumSize());
        img.setLocation(80, 80);

        panel.add(titulo);
        panel.add(img);
        add(panel);

    }

    public final void setEvents() {

        timer = new Timer(3000, (ActionEvent ae) -> {
            Presentacion_Programa.this.dispose();
            new Menu_Programa().setVisible(true);
        });

    }
}
