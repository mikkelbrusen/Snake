package view;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ComboBoxRenderer extends JLabel implements ListCellRenderer{
	
	private OptionsMenu options;
	
	public ComboBoxRenderer(OptionsMenu options) {
		this.options = options;
		setOpaque(true);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
	}
	
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		int selectedBox = ((Integer) value).intValue();
		
		//Check which box is selected
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		}
		else {
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
		if (image != null) {
			setText(tracks);
		}
		return this;
	}
}
