/**
 * 
 */
package domparser.javaobjects;

/**
 * @author sakkenapelly
 *
 */
public class Location {
	private long id;
	private String locationId;
	private String locationType;
	private String locationName;
	private String addressType;
	private String attentionOfName;
	private String addressLineSequence1 = null;
	private String addressLineSequence2 = null;
	private String addressLineSequence3 = null;
	private String addressLineSequence4 = null;
	private String addressLineSequence5 = null;
	private String addressLineSequence6 = null;
	private String cityName;
	private String countrySubDivisionCode;
	private String countryCode;
	private String postalCode;
	private Integer buildingNumber = null;
	private String buildingName = null;
	private String streetName;
	private int unit;
	private int floor;
	private int postOfficeBox;
	private Long poHeaderId = null;
	private Long PoLineId = null;
	private Long PoScheduleId = null;
	private boolean IsCustomerParty = false;
	private boolean IsSupplierParty = false;
	private boolean IsShipToParty = false;
	private boolean IsShipFromParty = false;
	private boolean IsPoSchedule = false;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getAttentionOfName() {
		return attentionOfName;
	}

	public void setAttentionOfName(String attentionOfName) {
		this.attentionOfName = attentionOfName;
	}

	public String getAddressLineSequence1() {
		return addressLineSequence1;
	}

	public void setAddressLineSequence1(String addressLineSequence1) {
		this.addressLineSequence1 = addressLineSequence1;
	}

	public String getAddressLineSequence2() {
		return addressLineSequence2;
	}

	public void setAddressLineSequence2(String addressLineSequence2) {
		this.addressLineSequence2 = addressLineSequence2;
	}

	public String getAddressLineSequence3() {
		return addressLineSequence3;
	}

	public void setAddressLineSequence3(String addressLineSequence3) {
		this.addressLineSequence3 = addressLineSequence3;
	}

	public String getAddressLineSequence4() {
		return addressLineSequence4;
	}

	public void setAddressLineSequence4(String addressLineSequence4) {
		this.addressLineSequence4 = addressLineSequence4;
	}

	public String getAddressLineSequence5() {
		return addressLineSequence5;
	}

	public void setAddressLineSequence5(String addressLineSequence5) {
		this.addressLineSequence5 = addressLineSequence5;
	}

	public String getAddressLineSequence6() {
		return addressLineSequence6;
	}

	public void setAddressLineSequence6(String addressLineSequence6) {
		this.addressLineSequence6 = addressLineSequence6;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountrySubDivisionCode() {
		return countrySubDivisionCode;
	}

	public void setCountrySubDivisionCode(String countrySubDivisionCode) {
		this.countrySubDivisionCode = countrySubDivisionCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Integer getBuildingNumber() {
		return buildingNumber;
	}

	public void setBuildingNumber(Integer buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getPostOfficeBox() {
		return postOfficeBox;
	}

	public void setPostOfficeBox(int postOfficeBox) {
		this.postOfficeBox = postOfficeBox;
	}

	public Long getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoHeaderId(Long poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	public Long getPoLineId() {
		return PoLineId;
	}

	public void setPoLineId(Long poLineId) {
		PoLineId = poLineId;
	}

	public Long getPoScheduleId() {
		return PoScheduleId;
	}

	public void setPoScheduleId(Long poScheduleId) {
		PoScheduleId = poScheduleId;
	}

	public boolean isIsCustomerParty() {
		return IsCustomerParty;
	}

	public void setIsCustomerParty(boolean isCustomerParty) {
		IsCustomerParty = isCustomerParty;
	}

	public boolean isIsSupplierParty() {
		return IsSupplierParty;
	}

	public void setIsSupplierParty(boolean isSupplierParty) {
		IsSupplierParty = isSupplierParty;
	}

	public boolean isIsShipToParty() {
		return IsShipToParty;
	}

	public void setIsShipToParty(boolean isShipToParty) {
		IsShipToParty = isShipToParty;
	}

	public boolean isIsShipFromParty() {
		return IsShipFromParty;
	}

	public void setIsShipFromParty(boolean isShipFromParty) {
		IsShipFromParty = isShipFromParty;
	}

	public boolean isIsPoSchedule() {
		return IsPoSchedule;
	}

	public void setIsPoSchedule(boolean isPoSchedule) {
		IsPoSchedule = isPoSchedule;
	}

}
