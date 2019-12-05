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
public class PoHeader {

	private long id;
	private String documentId;
	private String accountingEntity;
	private String locationName;
	private String logicalId;
	private int variationId;
	private String alternateDocumentId;
	private String displayId;
	private String lastModificationDatetime;
	private int lastModificationPersonId;
	private String lastModificationPersonName;
	private String documentDatetime;
	private long customerParty;
	private String supplierPartyId;
	private String supplierPartyName;
	private long supplierPartyLocation;
	private long shipToParty;
	private String shipFromPartyId;
	private String shipFromPartyName;
	private long shipFromPartyLocation;
	private long extendedAmount;
	private String extendedAmountCurrencyId;
	private long extendedBaseAmount;
	private String extendedBaseAmountCurrencyId;
	private long extendedReportAmount;
	private String extendedReportAmountCurrencyId;
	private long canceledAmount;
	private long canceledBaseAmount;
	private long canceledReportingAmount;
	private String paymentTermType;
	private String paymentTermId;
	private String paymentTermDescription;
	private String paymentTermCode;
	private String paymentTermListId;
	private String orderDatetime;
	private String buyerId = null;
	private String userAreaPropertyName;
	private String userAreaPropertyType;
	private String userAreaPropertyameValue;
	private long classificationCode;
	private String documentUsageCode;
	private String baseCurrencyAmountType;
	private String baseCurrencyAmountId;
	private long baseCurrencyAmount;
	private List<PoLine> lines = new ArrayList<>();
	private Status status;
	private List<Location> locations = new ArrayList<>();
	private List<Code> codes = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getAccountingEntity() {
		return accountingEntity;
	}

	public void setAccountingEntity(String accountingEntity) {
		this.accountingEntity = accountingEntity;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLogicalId() {
		return logicalId;
	}

	public void setLogicalId(String logicalId) {
		this.logicalId = logicalId;
	}

	public int getVariationId() {
		return variationId;
	}

	public void setVariationId(int variationId) {
		this.variationId = variationId;
	}

	public String getAlternateDocumentId() {
		return alternateDocumentId;
	}

	public void setAlternateDocumentId(String alternateDocumentId) {
		this.alternateDocumentId = alternateDocumentId;
	}

	public String getDisplayId() {
		return displayId;
	}

	public void setDisplayId(String displayId) {
		this.displayId = displayId;
	}

	public String getLastModificationDatetime() {
		return lastModificationDatetime;
	}

	public void setLastModificationDatetime(String lastModificationDatetime) {
		this.lastModificationDatetime = lastModificationDatetime;
	}

	public int getLastModificationPersonId() {
		return lastModificationPersonId;
	}

	public void setLastModificationPersonId(int lastModificationPersonId) {
		this.lastModificationPersonId = lastModificationPersonId;
	}

	public String getLastModificationPersonName() {
		return lastModificationPersonName;
	}

	public void setLastModificationPersonName(String lastModificationPersonName) {
		this.lastModificationPersonName = lastModificationPersonName;
	}

	public String getDocumentDatetime() {
		return documentDatetime;
	}

	public void setDocumentDatetime(String documentDatetime) {
		this.documentDatetime = documentDatetime;
	}

	public long getCustomerParty() {
		return customerParty;
	}

	public void setCustomerParty(long customerParty) {
		this.customerParty = customerParty;
	}

	public String getSupplierPartyId() {
		return supplierPartyId;
	}

	public void setSupplierPartyId(String supplierPartyId) {
		this.supplierPartyId = supplierPartyId;
	}

	public String getSupplierPartyName() {
		return supplierPartyName;
	}

	public void setSupplierPartyName(String supplierPartyName) {
		this.supplierPartyName = supplierPartyName;
	}

	public long getSupplierPartyLocation() {
		return supplierPartyLocation;
	}

	public void setSupplierPartyLocation(long supplierPartyLocation) {
		this.supplierPartyLocation = supplierPartyLocation;
	}

	public long getShipToParty() {
		return shipToParty;
	}

	public void setShipToParty(long shipToParty) {
		this.shipToParty = shipToParty;
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

	public long getShipFromPartyLocation() {
		return shipFromPartyLocation;
	}

	public void setShipFromPartyLocation(long shipFromPartyLocation) {
		this.shipFromPartyLocation = shipFromPartyLocation;
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

	public String getExtendedBaseAmountCurrencyId() {
		return extendedBaseAmountCurrencyId;
	}

	public void setExtendedBaseAmountCurrencyId(String extendedBaseAmountCurrencyId) {
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

	public long getCanceledAmount() {
		return canceledAmount;
	}

	public void setCanceledAmount(long canceledAmount) {
		this.canceledAmount = canceledAmount;
	}

	public long getCanceledBaseAmount() {
		return canceledBaseAmount;
	}

	public void setCanceledBaseAmount(long canceledBaseAmount) {
		this.canceledBaseAmount = canceledBaseAmount;
	}

	public long getCanceledReportingAmount() {
		return canceledReportingAmount;
	}

	public void setCanceledReportingAmount(long canceledReportingAmount) {
		this.canceledReportingAmount = canceledReportingAmount;
	}

	public String getPaymentTermType() {
		return paymentTermType;
	}

	public void setPaymentTermType(String paymentTermType) {
		this.paymentTermType = paymentTermType;
	}

	public String getPaymentTermId() {
		return paymentTermId;
	}

	public void setPaymentTermId(String paymentTermId) {
		this.paymentTermId = paymentTermId;
	}

	public String getPaymentTermDescription() {
		return paymentTermDescription;
	}

	public void setPaymentTermDescription(String paymentTermDescription) {
		this.paymentTermDescription = paymentTermDescription;
	}

	public String getPaymentTermCode() {
		return paymentTermCode;
	}

	public void setPaymentTermCode(String paymentTermCode) {
		this.paymentTermCode = paymentTermCode;
	}

	public String getPaymentTermListId() {
		return paymentTermListId;
	}

	public void setPaymentTermListId(String paymentTermListId) {
		this.paymentTermListId = paymentTermListId;
	}

	public String getOrderDatetime() {
		return orderDatetime;
	}

	public void setOrderDatetime(String orderDatetime) {
		this.orderDatetime = orderDatetime;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getUserAreaPropertyName() {
		return userAreaPropertyName;
	}

	public void setUserAreaPropertyName(String userAreaPropertyName) {
		this.userAreaPropertyName = userAreaPropertyName;
	}

	public String getUserAreaPropertyType() {
		return userAreaPropertyType;
	}

	public void setUserAreaPropertyType(String userAreaPropertyType) {
		this.userAreaPropertyType = userAreaPropertyType;
	}

	public String getUserAreaPropertyameValue() {
		return userAreaPropertyameValue;
	}

	public void setUserAreaPropertyameValue(String userAreaPropertyameValue) {
		this.userAreaPropertyameValue = userAreaPropertyameValue;
	}

	public long getClassificationCode() {
		return classificationCode;
	}

	public void setClassificationCode(long classificationCode) {
		this.classificationCode = classificationCode;
	}

	public String getDocumentUsageCode() {
		return documentUsageCode;
	}

	public void setDocumentUsageCode(String documentUsageCode) {
		this.documentUsageCode = documentUsageCode;
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

	public long getBaseCurrencyAmount() {
		return baseCurrencyAmount;
	}

	public void setBaseCurrencyAmount(long baseCurrencyAmount) {
		this.baseCurrencyAmount = baseCurrencyAmount;
	}

	public List<PoLine> getLines() {
		return lines;
	}

	public void setLines(List<PoLine> lines) {
		this.lines = lines;
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

	public List<Code> getCodes() {
		return codes;
	}

	public void setCodes(List<Code> codes) {
		this.codes = codes;
	}

}
