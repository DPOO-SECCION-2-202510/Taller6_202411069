package uniandes.dpoo.swing.interfaz.agregar;

import java.awt.Color;
import java.awt.Dimension; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelMapaAgregar extends JPanel implements MouseListener {
    private Image imagenMapa; 
    private int x_clic;
    private int y_clic;
    private int anchoImagenOriginal;
    private int altoImagenOriginal;

    public PanelMapaAgregar() {
        ImageIcon iconoMapa = null;
        try {
            iconoMapa = new ImageIcon("./imagenes/mapa.png");
            if (iconoMapa.getIconWidth() == -1) {
                this.imagenMapa = null;
                this.anchoImagenOriginal = 200; 
                this.altoImagenOriginal = 200;
            } else {
                this.imagenMapa = iconoMapa.getImage();
                this.anchoImagenOriginal = iconoMapa.getIconWidth();
                this.altoImagenOriginal = iconoMapa.getIconHeight();
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            this.imagenMapa = null;
            this.anchoImagenOriginal = 200;
            this.altoImagenOriginal = 200;
        }

        setPreferredSize(new Dimension(this.anchoImagenOriginal, this.altoImagenOriginal));

        addMouseListener(this);

        this.x_clic = -1; 
        this.y_clic = -1;
    }

    public int[] getCoordenadas() {
        return new int[]{this.x_clic, this.y_clic};
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 

        if (this.imagenMapa != null) {
            g.drawImage(this.imagenMapa, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.BLACK);
            g.drawString("Error al cargar imagen del mapa", 10, 20);
        }
        if (this.x_clic != -1 && this.y_clic != -1) {
            Graphics2D g2d = (Graphics2D) g; 
            g2d.setColor(Color.RED);

            int diametroCirculo = 7;   
            int offsetParaCentrar = 3; 
            g2d.fillOval(this.x_clic - offsetParaCentrar, this.y_clic - offsetParaCentrar, diametroCirculo, diametroCirculo);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.x_clic = e.getX();
        this.y_clic = e.getY();
        this.repaint(); 
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public int getXSeleccionado() {
        return x_clic;
    }

    public int getYSeleccionado() {
        return y_clic;
    }
}