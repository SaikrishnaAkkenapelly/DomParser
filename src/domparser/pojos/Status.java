/**
 * 
 */
package domparser.pojos;

/**
 * @author sakkenapelly
 *
 */
public class Status {

	private String statusCode;
	private String effectiveDatetime;
	private boolean archiveIndicator;
	private long id;
	private Long headerId = null;
	private Long lineId = null;
	private Long scheduleId = null;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getEffectiveDatetime() {
		return effectiveDatetime;
	}

	public void setEffectiveDatetime(String effectiveDatetime) {
		this.effectiveDatetime = effectiveDatetime;
	}

	public boolean isArchiveIndicator() {
		return archiveIndicator;
	}

	public void setArchiveIndicator(boolean archiveIndicator) {
		this.archiveIndicator = archiveIndicator;
	}

	public long getId() {
		return id;
	}

	public void setId(long statusId) {
		this.id = statusId;
	}

	public Long getHeaderId() {
		return headerId;
	}

	public void setHeaderId(Long headerId) {
		this.headerId = headerId;
	}

	public Long getLineId() {
		return lineId;
	}

	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

}
