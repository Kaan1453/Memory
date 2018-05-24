
public class ThreadWait extends Thread {

	private View view;
	private int x;
	private int y;
	
	public ThreadWait(View view, int x, int y){
		this.view = view;
		this.x = x;
		this.y = y;
	}
	public void run(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.view.deleteImages(this.x, this.y);
	}
	
}
