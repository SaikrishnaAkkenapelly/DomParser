/**
 * 
 */
package domparser.pojos;

/**
 * @author sakkenapelly
 *
 */
public class Code {
	private long id;
	private String listId;
	private long sequence;
	private String code;
	private Long headerNumber;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getHeaderNumber() {
		return headerNumber;
	}

	public void setHeaderNumber(Long headerNumber) {
		this.headerNumber = headerNumber;
	}

}
