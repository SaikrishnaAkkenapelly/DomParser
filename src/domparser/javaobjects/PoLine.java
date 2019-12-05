/**
 * 
 */
package domparser.javaobjects;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sakkenapelly
 *
 */
public class PoLine {

	private long id;
	private long poHeaderId;
	private int lineNumber;
	private String note;
	private long statusId;
	private String itemId;
	private boolean serviceIndicator;
	private String description;
	private String itemNoteLanguage;
	private String itemNote;
	private String serializedLotId;
	private String serializedLotSelection;
	private String unitCode;
	private int quantity;
	private int baseUomQuantity;
	private long amount;
	private String amountCurrencyId;
	private long baseAmount;
	private String baseAmountCurrencyId;
	private Float reportAmount;
	private String reportAmountCurrencyId;
	private int perQuantity;
	private int perBaseUomQuantity;
	private long extendedAmount;
	private String extendedAmountCurrencyId;
	private long extendedBaseAmount;
	private long extendedBaseAmountCurrencyId;
	private long extendedReportAmount;
	private String extendedReportAmountCurrencyId;
	private long totalAmount;
	private long totalBaseAmount;
	private long totalReportAmount;
	private String requiredDeliveryDatetime;
	private long shipToParty;
	private int backOrderedQuantity;
	private int backOrderedBaseUomQuantity;
	private int receivedQuantity;
	private int receivedBaseUomQuantity;
	private int openQuantity;
	private int openBaseUomQuantity;
	private String shipFromPartyId;
	private String shipFromPartyName;
	private Long baseCurrencyAmount;
	private String baseCurrencyAmountType;
	private String baseCurrencyAmountId;
	private boolean contractApplicableIndicator;
	private int unitQuantityConversionFactor;
	private boolean dropShipIndicator;
	private Status status;
	private List<Location> locations = new ArrayList<>();
	private List<PoSchedule> schedules = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long poLineId) {
		this.id = poLineId;
	}

	public long getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoHeaderId(long poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public boolean isServiceIndicator() {
		return serviceIndicator;
	}

	public void setServiceIndicator(boolean serviceIndicator) {
		this.serviceIndicator = serviceIndicator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getItemNoteLanguage() {
		return itemNoteLanguage;
	}

	public void setItemNoteLanguage(String itemNoteLanguage) {
		this.itemNoteLanguage = itemNoteLanguage;
	}

	public String getItemNote() {
		return itemNote;
	}

	public void setItemNote(String itemNote) {
		this.itemNote = itemNote;
	}

	public String getSerializedLotId() {
		return serializedLotId;
	}

	public void setSerializedLotId(String serializedLotId) {
		this.serializedLotId = serializedLotId;
	}

	public String getSerializedLotSelection() {
		return serializedLotSelection;
	}

	public void setSerializedLotSelection(String serializedLotSelection) {
		this.serializedLotSelection = serializedLotSelection;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
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

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getAmountCurrencyId() {
		return amountCurrencyId;
	}

	public void setAmountCurrencyId(String amountCurrencyId) {
		this.amountCurrencyId = amountCurrencyId;
	}

	public long getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(long baseAmount) {
		this.baseAmount = baseAmount;
	}

	public String getBaseAmountCurrencyId() {
		return baseAmountCurrencyId;
	}

	public void setBaseAmountCurrencyId(String baseAmountCurrencyId) {
		this.baseAmountCurrencyId = baseAmountCurrencyId;
	}

	public Float getReportAmount() {
		return reportAmount;
	}

	public void setReportAmount(Float reportAmount) {
		this.reportAmount = reportAmount;
	}

	public String getReportAmountCurrencyId() {
		return reportAmountCurrencyId;
	}

	public void setReportAmountCurrencyId(String reportAmountCurrencyId) {
		this.reportAmountCurrencyId = reportAmountCurrencyId;
	}

	public int getPerQuantity() {
		return perQuantity;
	}

	public void setPerQuantity(int perQuantity) {
		this.perQuantity = perQuantity;
	}

	public int getPerBaseUomQuantity() {
		return perBaseUomQuantity;
	}

	public void setPerBaseUomQuantity(int perBaseUomQuantity) {
		this.perBaseUomQuantity = perBaseUomQuantity;
	}

	public long getExtendedAmount() {
		return extendedAmount;
	}

	public void setExtendedAmount(long extendedAmount) {
		this.extendedAmount = extendedAmount;
	}

	public String getExtendedAmountCurrencyId() {
		return extendedAmountCurrencyId;
	}

	public void setExtendedAmountCurrencyId(String extendedAmountCurrencyId) {
		this.extendedAmountCurrencyId = extendedAmountCurrencyId;
	}

	public long getExtendedBaseAmount() {
		return extendedBaseAmount;
	}

	public void setExtendedBaseAmount(long extendedBaseAmount) {
		this.extendedBaseAmount = extendedBaseAmount;
	}

	public long getExtendedBaseAmountCurrencyId() {
		return extendedBaseAmountCurrencyId;
	}

	public void setExtendedBaseAmountCurrencyId(long extendedBaseAmountCurrencyId) {
		this.extendedBaseAmountCurrencyId = extendedBaseAmountCurrencyId;
	}

	public long getExtendedReportAmount() {
		return extendedReportAmount;
	}

	public void setExtendedReportAmount(long extendedReportAmount) {
		this.extendedReportAmount = extendedReportAmount;
	}

	public String getExtendedReportAmountCurrencyId() {
		return extendedReportAmountCurrencyId;
	}

	public void setExtendedReportAmountCurrencyId(String extendedReportAmountCurrencyId) {
		this.extendedReportAmountCurrencyId = extendedReportAmountCurrencyId;
	}

	public long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public long getTotalBaseAmount() {
		return totalBaseAmount;
	}

	public void setTotalBaseAmount(long totalBaseAmount) {
		this.totalBaseAmount = totalBaseAmount;
	}

	public long getTotalReportAmount() {
		return totalReportAmount;
	}

	public void setTotalReportAmount(long totalReportAmount) {
		this.totalReportAmount = totalReportAmount;
	}

	public String getRequiredDeliveryDatetime() {
		return requiredDeliveryDatetime;
	}

	public void setRequiredDeliveryDatetime(String requiredDeliveryDatetime) {
		this.requiredDeliveryDatetime = requiredDeliveryDatetime;
	}

	public long getShipToParty() {
		return shipToParty;
	}

	public void setShipToParty(long shipToParty) {
		this.shipToParty = shipToParty;
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

	public int getOpenQuantity() {
		return openQuantity;
	}

	public void setOpenQuantity(int openQuantity) {
		this.openQuantity = openQuantity;
	}

	public int getOpenBaseUomQuantity() {
		return openBaseUomQuantity;
	}

	public void setOpenBaseUomQuantity(int openBaseUomQuantity) {
		this.openBaseUomQuantity = openBaseUomQuantity;
	}

	public String getShipFromPartyId() {
		return shipFromPartyId;
	}

	public void setShipFromPartyId(String shipFromPartyId) {
		this.shipFromPartyId = shipFromPartyId;
	}

	public String getShipFromPartyName() {
		return shipFromPartyName;
	}

	public void setShipFromPartyName(String shipFromPartyName) {
		this.shipFromPartyName = shipFromPartyName;
	}

	public Long getBaseCurrencyAmount() {
		return baseCurrencyAmount;
	}

	public void setBaseCurrencyAmount(Long baseCurrencyAmount) {
		this.baseCurrencyAmount = baseCurrencyAmount;
	}

	public String getBaseCurrencyAmountType() {
		return baseCurrencyAmountType;
	}

	public void setBaseCurrencyAmountType(String baseCurrencyAmountType) {
		this.baseCurrencyAmountType = baseCurrencyAmountType;
	}

	public String getBaseCurrencyAmountId() {
		return baseCurrencyAmountId;
	}

	public void setBaseCurrencyAmountId(String baseCurrencyAmountId) {
		this.baseCurrencyAmountId = baseCurrencyAmountId;
	}

	public boolean isContractApplicableIndicator() {
		return contractApplicableIndicator;
	}

	public void setContractApplicableIndicator(boolean contractApplicableIndicator) {
		this.contractApplicableIndicator = contractApplicableIndicator;
	}

	public int getUnitQuantityConversionFactor() {
		return unitQuantityConversionFactor;
	}

	public void setUnitQuantityConversionFactor(int unitQuantityConversionFactor) {
		this.unitQuantityConversionFactor = unitQuantityConversionFactor;
	}

	public boolean isDropShipIndicator() {
		return dropShipIndicator;
	}

	public void setDropShipIndicator(boolean dropShipIndicator) {
		this.dropShipIndicator = dropShipIndicator;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public List<PoSchedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<PoSchedule> schedules) {
		this.schedules = schedules;
	}

}
