import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Animacion extends JFrame {
    private JLabel etiquetaImagen;
    private Timer temporizador;
    private BufferedImage[] imagenes;
    private int indiceImagen;
    private int velocidad = 500;

    public Animacion() {
        super("Animación");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        // Carga las imágenes en un arreglo
        imagenes = new BufferedImage[3];
        try {
            imagenes[0] = ImageIO.read(new File("imagen1.png"));
            imagenes[1] = ImageIO.read(new File("imagen2.png"));
            imagenes[2] = ImageIO.read(new File("imagen3.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Crea una etiqueta para mostrar las imágenes
        etiquetaImagen = new JLabel(new ImageIcon(imagenes[0]));
        add(etiquetaImagen);

        // Crea un control deslizante para ajustar la velocidad
        JSlider sliderVelocidad = new JSlider(JSlider.HORIZONTAL, 100, 1000, velocidad);
        sliderVelocidad.setMajorTickSpacing(100);
        sliderVelocidad.setPaintTicks(true);
        sliderVelocidad.setPaintLabels(true);
        sliderVelocidad.addChangeListener(e -> {
            velocidad = sliderVelocidad.getValue();
            temporizador.setDelay(velocidad);
        });
        add(sliderVelocidad, "South");

        // Crea un temporizador para mostrar las imágenes
        temporizador = new Timer(velocidad, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambia la imagen actual
                indiceImagen++;
                if (indiceImagen >= imagenes.length) {
                    indiceImagen = 0;
                }
                etiquetaImagen.setIcon(new ImageIcon(imagenes[indiceImagen]));
            }
        });
        temporizador.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Animacion animacion = new Animacion();
            animacion.setVisible(true);
        });
    }
}
