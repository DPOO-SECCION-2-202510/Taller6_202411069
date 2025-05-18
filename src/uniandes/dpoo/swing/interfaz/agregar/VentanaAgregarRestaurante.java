package uniandes.dpoo.swing.interfaz.agregar;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import uniandes.dpoo.swing.interfaz.principal.VentanaPrincipal;

@SuppressWarnings("serial")
public class VentanaAgregarRestaurante extends JFrame {
    private PanelEditarRestaurante panelDetalles;
    private PanelBotonesAgregar panelBotones;
    private PanelMapaAgregar panelMapa; 
    private VentanaPrincipal ventanaPrincipal;

    public VentanaAgregarRestaurante(VentanaPrincipal principal) {
        this.ventanaPrincipal = principal;
        panelDetalles = new PanelEditarRestaurante();
        panelBotones = new PanelBotonesAgregar(this);
        panelMapa = new PanelMapaAgregar(); 

        setLayout(new BorderLayout());
        add(panelMapa, BorderLayout.CENTER);
        
        JPanel panelAbajo = new JPanel(new BorderLayout());
        panelAbajo.add(panelDetalles, BorderLayout.CENTER);
        panelAbajo.add(panelBotones, BorderLayout.SOUTH);
        add(panelAbajo, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    public void agregarRestaurante() {
        String nombre = panelDetalles.getNombre();
        int calificacion = panelDetalles.getCalificacion();
        boolean visitado = panelDetalles.getVisitado();
        int[] coords = panelMapa.getCoordenadas(); 
        int x = coords[0];
        int y = coords[1];


        ventanaPrincipal.agregarRestaurante(nombre, calificacion, x, y, visitado);
        dispose();
    }

    public void cerrarVentana() {
        dispose();
    }
}