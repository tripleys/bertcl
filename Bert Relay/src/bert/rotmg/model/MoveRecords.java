package bert.rotmg.model;

import java.util.ArrayList;

public class MoveRecords {

	public int lastClearTime = -1;
	
	public ArrayList<MoveRecord> records = new ArrayList<MoveRecord>();
	
	public void addRecord(int time, float x, float y) {
		if (this.lastClearTime < 0) {
			return;
		}
		int id = this.getId(time);
		if (id < 1 || id > 10) {
			return;
		}
		if (records.size() == 0) {
			this.records.add(new MoveRecord(time, x, y));
			return;
		}
		MoveRecord last = records.get(records.size() - 1);
		int lastId = this.getId(last.time);
		if (id != lastId) {
			this.records.add(new MoveRecord(time, x, y));
			return;
		}
		int score = this.getScore(id, time);
		int lastScore = this.getScore(id, last.time);
		if (score < lastScore) {
			last.time = time;
			last.x = x;
			last.y = y;
		}
	}
	
	private int getId(int time) {
		return (time - this.lastClearTime + 50) / 100;
	}
	
	private int getScore(int time1, int time2) {
		return Math.abs(time2 - this.lastClearTime - time1 * 100);
	}
	
	public void clear(int lastClearTime) {
		this.records.clear();
		this.lastClearTime = lastClearTime;
	}
}
