import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller extends MouseAdapter {

	private View view;
	private Model model;

	private int x;
	private int y;
	private static boolean firstClick;

	public Controller(View view, Model model, int x, int y) {
		this.view = view;
		this.model = model;
		this.x = x;
		this.y = y;
		firstClick = true;
	}

	public void mouseReleased(MouseEvent e) {
		if (firstClick) {
			this.view.setImage1(this.x, this.y);
			firstClick = false;
			

		} else {
			System.out.println("falsch");
			try {
				if (this.view.setImage2(this.x, this.y)) {
					firstClick = true;
					this.view.deleteImages(this.x, this.y);
				}
			} catch (InterruptedException e1) {
				
				e1.printStackTrace();
			}
		}
	}

}
