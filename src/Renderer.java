import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class Renderer implements ListCellRenderer<Score>{

	@Override
	public Component getListCellRendererComponent(JList<? extends Score> list, Score value, int arg2, boolean arg3,
			boolean arg4) {
		JLabel l=new JLabel(value.toString());
		l.setHorizontalAlignment(JLabel.CENTER);
		l.setOpaque(false);
		l.setFont(new Font ("arial", Font.BOLD, 18));
		return l;
	}

}
