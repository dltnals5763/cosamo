package project.vo_mypage;

public class Board3 {
	private int bnum;
	private Board board;
	public Board3() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Board3(int bnum, Board board) {
		super();
		this.bnum = bnum;
		this.board = board;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	
	
}
