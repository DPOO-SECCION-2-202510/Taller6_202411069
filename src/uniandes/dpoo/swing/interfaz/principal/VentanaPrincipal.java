package uniandes.dpoo.swing.interfaz.principal;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JFrame;
import uniandes.dpoo.swing.interfaz.agregar.VentanaAgregarRestaurante;
import uniandes.dpoo.swing.interfaz.mapa.VentanaMapa;
import uniandes.dpoo.swing.mundo.Diario;
import uniandes.dpoo.swing.mundo.Restaurante;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

    private Diario mundo;
    private PanelBotones pBotones;
    private PanelDetallesRestaurante pDetalles;
    private PanelLista pLista;
    private VentanaMapa ventanaMapa;
    private VentanaAgregarRestaurante ventanaAgregar;

    public VentanaPrincipal(Diario elDiario) {
        this.mundo = elDiario;
        setLayout(new BorderLayout());

        pBotones = new PanelBotones(this);
        add(pBotones, BorderLayout.NORTH);

        pLista = new PanelLista(this);
        add(pLista, BorderLayout.CENTER);

        pDetalles = new PanelDetallesRestaurante();
        add(pDetalles, BorderLayout.SOUTH);

        actualizarRestaurantes();

        setTitle("Restaurantes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void mostrarVetanaNuevoRestaurante() {
        if (ventanaAgregar == null || !ventanaAgregar.isVisible()) {
            ventanaAgregar = new VentanaAgregarRestaurante(this);
            ventanaAgregar.setVisible(true);
        } else {
            ventanaAgregar.setVisible(true); 
            ventanaAgregar.toFront();      
            ventanaAgregar.requestFocus(); 
        }
    }

    public void mostrarVentanaMapa() {
        if (ventanaMapa == null || !ventanaMapa.isVisible()) {
            ventanaMapa = new VentanaMapa(this);
            ventanaMapa.setVisible(true);
        } else {
            ventanaMapa.setVisible(true); 
            ventanaMapa.toFront();      
            ventanaMapa.requestFocus(); 
        }
    }

    public void agregarRestaurante(String nombre, int calificacion, int x, int y, boolean visitado) {
        Restaurante nuevo = new Restaurante(nombre, calificacion, x, y, visitado);
        mundo.agregarRestaurante(nuevo);
        actualizarRestaurantes();

        if (ventanaMapa != null && ventanaMapa.isVisible()) {
            ventanaMapa.actualizarContenidoMapa();
        }
    }

    public List<Restaurante> getRestaurantes(boolean completos) {
        return mundo.getRestaurantes(completos);
    }

    private void actualizarRestaurantes() {
        List<Restaurante> todos = this.mundo.getRestaurantes(true);
        if (pLista != null) {
            pLista.actualizarRestaurantes(todos);
        }

        if (!todos.isEmpty()) {
            cambiarRestauranteSeleccionado(todos.get(todos.size() - 1));
        }
    }

    public void cambiarRestauranteSeleccionado(Restaurante seleccionado) {
        if (pDetalles != null) {
            if (seleccionado != null) {
                pDetalles.actualizarRestaurante(seleccionado);
            }
        }
    }
    
    /**
     * Inicia la aplicación, creando un conjunto básico de restaurantes y luego creando la interfaz de la aplicación
     * @param args
     */
    
    public static void main(String[] args) {
        Diario elDiario = new Diario();
        elDiario.agregarRestaurante(new Restaurante("Pita Pan", 4, 30, 30, true));
        elDiario.agregarRestaurante(new Restaurante("Lord of the Wings", 5, 170, 210, true));
        elDiario.agregarRestaurante(new Restaurante("Nacho Business", 2, 350, 170, false));
        elDiario.agregarRestaurante(new Restaurante("Thai Tanic", 1, 110, 100, false));
        elDiario.agregarRestaurante(new Restaurante("Planet of the Creppes", 3, 400, 400, true));
    
        new VentanaPrincipal(elDiario);
    }
}