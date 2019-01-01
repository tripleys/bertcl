package bert.engine;

public class Engine implements Runnable {

	private Canvas canvas;
	
	private long lastTime;
	
	private int fps;
	
	private boolean running;
	
	public Engine(Canvas canvas, int fps) {
		this.canvas = canvas;
		this.fps = fps;
	}
	
	@Override
	public void run() {
		final int required = 1000 / this.fps;
		final int sleepTime = required - 1;
		this.lastTime = System.currentTimeMillis();
		this.running = false;
		try {
			while(true) {
				long delta = System.currentTimeMillis() - this.lastTime;
				if (delta >= required) {
					if (this.running)
						try {
							this.canvas.render(delta);
						} catch(Exception e) {}
					this.lastTime = System.currentTimeMillis();
					Thread.sleep(sleepTime);
				} else {
					Thread.sleep(required - delta);
				}
				
			}
		} catch(Exception e) {}
	}

	public void setWorking(boolean running) {
		this.running = running;
	}
}
