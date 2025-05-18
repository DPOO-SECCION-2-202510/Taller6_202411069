package uniandes.dpoo.swing.interfaz.mapa;

import java.awt.Color;
import java.awt.Dimension; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image; 
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import uniandes.dpoo.swing.mundo.Restaurante;

@SuppressWarnings("serial")
public class PanelMapaVisualizar extends JPanel {
    private List<Restaurante> restaurantes;
    private Image imagenDelMapa; 
    private int anchoMapaOriginal;
    private int altoMapaOriginal;

    public PanelMapaVisualizar() {
        ImageIcon icono = new ImageIcon("./imagenes/mapa.png");
        if (icono.getIconWidth() != -1) {
            this.imagenDelMapa = icono.getImage();
            this.anchoMapaOriginal = icono.getIconWidth();
            this.altoMapaOriginal = icono.getIconHeight();
            setPreferredSize(new Dimension(this.anchoMapaOriginal, this.altoMapaOriginal));
        } else {
            System.err.println("PanelMapaVisualizar: Error al cargar la imagen del mapa.");
            this.imagenDelMapa = null;
            this.anchoMapaOriginal = 447; 
            this.altoMapaOriginal = 489;
            setPreferredSize(new Dimension(this.anchoMapaOriginal, this.altoMapaOriginal));
        }
    }

    @Override
    protected void paintComponent(Graphics g) { 
        super.paintComponent(g); 

        if (this.imagenDelMapa != null) {
            g.drawImage(this.imagenDelMapa, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.BLACK);
            g.drawString("Mapa no disponible", 50, 50);
        }

        if (restaurantes != null) {
            Graphics2D g2d = (Graphics2D) g.create(); 
            for (Restaurante r : restaurantes) {
                g2d.setColor(r.isVisitado() ? Color.GREEN : Color.RED);
                g2d.fillOval(r.getX() - 5, r.getY() - 5, 10, 10);

                g2d.setColor(Color.BLACK);
                g2d.drawString(r.getNombre(), r.getX() + 10, r.getY());
            }
            g2d.dispose();
        }
    }

    public void actualizarMapa(List<Restaurante> nuevosRestaurantes) {
        this.restaurantes = nuevosRestaurantes; 
        repaint(); 
    }
}