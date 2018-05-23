import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Model {

	private int x;
	private int y;
	private int total = 8;
	private int com[][];
	private int counter;
	private boolean finished;

	public Model() {
		this.com = mashUp(new int[4][4]);
	}

	public int[][] mashUp(int[][] x) {
		List<Integer> pic = new ArrayList<>();
		for (int i = 0; i < total; i++) {
			pic.add(i);
			pic.add(i);
			

		}
		Collections.shuffle(pic);
		for (int i = 0; i < x.length; i++) {
			for (int a = 0; a < x[i].length; a++) {
				x[i][a] = pic.remove(0);
				System.out.println(x[i][a]);
			}
			

		}
		return x;
	}

	public void incrementCounter() {
		this.counter++;
		System.out.println(this.counter);

	}

	public boolean isFinished() {
		return this.counter >= this.total;
	}

	public int getNumber(int x, int y) {
		return this.com[x][y];
	}

	public void setNumbers(int x[][]) {
		this.com = x;
	}

	public int getY() {
		return this.y;
	}

	public int getX() {
		return this.x;
	}

}
