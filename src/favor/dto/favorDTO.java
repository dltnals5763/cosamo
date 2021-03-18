package favor.dto;

public class favorDTO {
	private String id;
	private int num;
	public favorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public favorDTO(String id, int num) {
		super();
		this.id = id;
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
