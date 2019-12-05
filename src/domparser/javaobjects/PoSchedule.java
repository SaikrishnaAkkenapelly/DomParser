/**
 * 
 */
package domparser.javaobjects;

/**
 * @author sakkenapelly
 *
 */
public class PoSchedule {
	private long poScheduleNumber;
	private int lineNumber;
	private int quantity;
	private int baseUomQuantity;
	private String requiredDeliveryDatetime;
	private long shipToPartyLocation;
	private int backOrderedQuantity;
	private int backOrderedBaseUomQuantity;
	private int receivedQuantity;
	private int receivedBaseUomQuantity;
	private String scheduleLineType;
	private long polineNumberFk;
	private Status status;
	private Location location;

	public long getPoScheduleNumber() {
		return poScheduleNumber;
	}

	public void setPoScheduleNumber(long poScheduleNumber) {
		this.poScheduleNumber = poScheduleNumber;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getBaseUomQuantity() {
		return baseUomQuantity;
	}

	public void setBaseUomQuantity(int baseUomQuantity) {
		this.baseUomQuantity = baseUomQuantity;
	}

	public String getRequiredDeliveryDatetime() {
		return requiredDeliveryDatetime;
	}

	public void setRequiredDeliveryDatetime(String requiredDeliveryDatetime) {
		this.requiredDeliveryDatetime = requiredDeliveryDatetime;
	}

	public long getShipToPartyLocation() {
		return shipToPartyLocation;
	}

	public void setShipToPartyLocation(long shipToPartyLocation) {
		this.shipToPartyLocation = shipToPartyLocation;
	}

	public int getBackOrderedQuantity() {
		return backOrderedQuantity;
	}

	public void setBackOrderedQuantity(int backOrderedQuantity) {
		this.backOrderedQuantity = backOrderedQuantity;
	}

	public int getBackOrderedBaseUomQuantity() {
		return backOrderedBaseUomQuantity;
	}

	public void setBackOrderedBaseUomQuantity(int backOrderedBaseUomQuantity) {
		this.backOrderedBaseUomQuantity = backOrderedBaseUomQuantity;
	}

	public int getReceivedQuantity() {
		return receivedQuantity;
	}

	public void setReceivedQuantity(int receivedQuantity) {
		this.receivedQuantity = receivedQuantity;
	}

	public int getReceivedBaseUomQuantity() {
		return receivedBaseUomQuantity;
	}

	public void setReceivedBaseUomQuantity(int receivedBaseUomQuantity) {
		this.receivedBaseUomQuantity = receivedBaseUomQuantity;
	}

	public String getScheduleLineType() {
		return scheduleLineType;
	}

	public void setScheduleLineType(String scheduleLineType) {
		this.scheduleLineType = scheduleLineType;
	}

	public long getPolineNumberFk() {
		return polineNumberFk;
	}

	public void setPolineNumberFk(long polineNumberFk) {
		this.polineNumberFk = polineNumberFk;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
