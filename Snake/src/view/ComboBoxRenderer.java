package view;

import java.awt.Component;
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
		int width = image.getWidth();
		int heght = image.getHeight();
//		BufferedImage resizedImg = new BufferedImage();
		setIcon(new ImageIcon(image));
		if (image != null) {
			setText(tracks);
		}
		return this;
	}
}
