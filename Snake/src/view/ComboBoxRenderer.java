package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class ComboBoxRenderer extends JLabel implements ListCellRenderer {

    private final OptionsMenu options;

    public ComboBoxRenderer(OptionsMenu options) {
        this.options = options;
        setOpaque(true);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        int selectedBox = (Integer) value;

        //Check which box is selected
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        BufferedImage image = options.getImages()[selectedBox];
        String tracks = options.getTracks()[selectedBox];
        BufferedImage resizedImg = new BufferedImage(32, 18, image.getType());
        Graphics2D g = resizedImg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(image, 0, 0, 32, 18, 0, 0, image.getWidth(), image.getHeight(), null);
        g.dispose();
        setIcon(new ImageIcon(image));

        //Set track names
        setText(tracks);

        return this;
    }
}
