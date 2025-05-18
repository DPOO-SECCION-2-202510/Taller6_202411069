package uniandes.dpoo.swing.interfaz.mapa;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import uniandes.dpoo.swing.interfaz.principal.VentanaPrincipal;

@SuppressWarnings("serial")
public class VentanaMapa extends JFrame implements ActionListener {
    private static final String VISITADOS = "VISITADOS";
    private static final String TODOS = "TODOS";
    private PanelMapaVisualizar panelMapa; 
    private JRadioButton radioTodos;
    private JRadioButton radioVisitados;
    private VentanaPrincipal ventanaPrincipal;

    public VentanaMapa(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;

        panelMapa = new PanelMapaVisualizar();
        add(panelMapa, BorderLayout.CENTER);

        JPanel panelRadios = new JPanel();
        radioTodos = new JRadioButton("Todos los restaurantes");
        radioVisitados = new JRadioButton("Sólo visitados");
        radioTodos.setActionCommand(TODOS);
        radioVisitados.setActionCommand(VISITADOS);
        radioTodos.addActionListener(this);
        radioVisitados.addActionListener(this);
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(radioTodos);
        grupo.add(radioVisitados);
        radioTodos.setSelected(true);
        panelRadios.add(radioTodos);
        panelRadios.add(radioVisitados);
        add(panelRadios, BorderLayout.SOUTH);

        panelMapa.actualizarMapa(ventanaPrincipal.getRestaurantes(true));

        pack();
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (TODOS.equals(comando)) {
            panelMapa.actualizarMapa(ventanaPrincipal.getRestaurantes(true));
        } else if (VISITADOS.equals(comando)) {
            panelMapa.actualizarMapa(ventanaPrincipal.getRestaurantes(false));
        }
    }
    public void actualizarContenidoMapa() {
        if (radioTodos.isSelected()) {
            if (panelMapa != null && ventanaPrincipal != null) {
                panelMapa.actualizarMapa(ventanaPrincipal.getRestaurantes(true));
            }
        } else if (radioVisitados.isSelected()) {
            if (panelMapa != null && ventanaPrincipal != null) {
                panelMapa.actualizarMapa(ventanaPrincipal.getRestaurantes(false));
            }
        }
    }
}