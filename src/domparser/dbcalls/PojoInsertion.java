/**
 * 
 */
package domparser.dbcalls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import domparser.pojos.Code;
import domparser.pojos.Location;
import domparser.pojos.PoHeader;
import domparser.pojos.PoLine;
import domparser.pojos.PoSchedule;
import domparser.pojos.Status;
import domparser.utilities.DbConnection;

/**
 * @author sakkenapelly
 *
 */
public class PojoInsertion {

	private String sql = null;

	public long insertStatusPojo(Status status) {
		long status_id = 0;
		sql = "INSERT INTO STATUS(STATUS_CODE,EFFECTIVE_DATETIME,ARCHIVE_INDICATOR,PO_HEADER_ID,PO_LINE_ID,PO_SCHEDULE_ID) VALUES(?,?,?,?,?,?)";
		try (Connection con = DbConnection.connect();
				PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, status.getStatusCode());
			statement.setString(2, status.getEffectiveDatetime());
			if (status.isArchiveIndicator()) {
				statement.setByte(3, (byte) 1);
			} else {
				statement.setByte(3, (byte) 0);
			}
			if (status.getHeaderId() == null) {
				statement.setNull(4, Types.NULL);
			} else {
				statement.setLong(4, status.getHeaderId());
			}
			if (status.getLineId() == null) {
				statement.setNull(5, Types.NULL);
			} else {
				statement.setLong(5, status.getLineId());
			}
			if (status.getScheduleId() == null) {
				statement.setNull(6, Types.NULL);
			} else {
				statement.setLong(6, status.getScheduleId());
			}
			statement.executeUpdate();
			try (ResultSet resultSet = statement.getGeneratedKeys()) {
				while (resultSet.next()) {
					status_id = resultSet.getLong(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status_id;
	}

	public long insertCodesPojo(Code code) {
		long codeId = 0;
		sql = "insert into codes(list_id,sequence,code,PO_HEADER_ID) values(?,?,?,?)";
		try (Connection con = DbConnection.connect();
				PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			statement.setString(1, code.getListId());
			statement.setLong(2, code.getSequence());
			statement.setString(3, code.getCode());
			if (code.getHeaderNumber() == null) {
				statement.setNull(4, Types.NULL);
			} else {
				statement.setLong(4, code.getHeaderNumber());
			}
			statement.executeUpdate();
			try (ResultSet resultSet = statement.getGeneratedKeys()) {
				while (resultSet.next()) {
					codeId = resultSet.getLong(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codeId;
	}

	public long insertLocationsPojo(Location location) {
		long location_number = 0;
		sql = "INSERT INTO PO_LOCATIONS(LOCATION_ID,LOCATION_TYPE,LOCATION_NAME,ADDRESS_TYPE,ATTENTION_OF_NAME,ADDRESS_LINE_SEQUENCE_1,ADDRESS_LINE_SEQUENCE_2,ADDRESS_LINE_SEQUENCE_3,ADDRESS_LINE_SEQUENCE_4,ADDRESS_LINE_SEQUENCE_5,"
				+ "ADDRESS_LINE_SEQUENCE_6,CITY_NAME,COUNTRY_SUB_DIVISION_CODE,COUNTRY_CODE,POSTAL_CODE,"
				+ "BUILDING_NUMBER,BUILDING_NAME,STREET_NAME,UNIT,FLOOR,POST_OFFICE_BOX,PO_HEADER_ID,PO_LINE_ID,PO_SCHEDULE_ID,"
				+ "IS_CUSTOMER_PARTY,IS_SUPPLIER_PARTY,IS_SHIP_TO_PARTY,IS_SHIP_FROM_PARTY) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try (Connection con = DbConnection.connect();
				PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			statement.setString(1, location.getLocationId());
			statement.setString(2, location.getLocationType());
			statement.setString(3, location.getLocationName());
			statement.setString(4, location.getAddressType());
			statement.setString(5, location.getAttentionOfName());
			statement.setString(6, location.getAddressLineSequence1());
			statement.setString(7, location.getAddressLineSequence2());
			statement.setString(8, location.getAddressLineSequence3());
			statement.setString(9, location.getAddressLineSequence4());
			statement.setString(10, location.getAddressLineSequence5());
			statement.setString(11, location.getAddressLineSequence6());
			statement.setString(12, location.getCityName());
			statement.setString(13, location.getCountrySubDivisionCode());
			statement.setString(14, location.getCountryCode());
			statement.setString(15, location.getPostalCode());
			if (location.getBuildingNumber() == null) {
				statement.setNull(16, Types.NULL);
			} else {
				statement.setInt(16, location.getBuildingNumber());
			}
			statement.setString(17, location.getBuildingName());
			statement.setString(18, location.getStreetName());
			statement.setInt(19, location.getUnit());
			statement.setInt(20, location.getFloor());
			statement.setInt(21, location.getPostOfficeBox());
			if(location.getPoHeaderId()== null)
			{
				statement.setNull(22, Types.NULL);	
			}else
			{
				statement.setLong(22, location.getPoHeaderId());
			}
			
			if (location.getPoLineId() == null) {
				statement.setNull(23, Types.NULL);
			} else {
				statement.setLong(23, location.getPoLineId());
			}

			if (location.getPoScheduleId() == null) {
				statement.setNull(24, Types.NULL);
			} else {
				statement.setLong(24, location.getPoScheduleId());
			}

			if (location.isIsCustomerParty()) {
				statement.setByte(25, (byte) 1);
			} else {
				statement.setByte(25, (byte) 0);
			}
			if (location.isIsSupplierParty()) {
				statement.setByte(26, (byte) 1);
			} else {
				statement.setByte(26, (byte) 0);
			}
			if (location.isIsShipToParty()) {
				statement.setByte(27, (byte) 1);
			} else {
				statement.setByte(27, (byte) 0);
			}
			if (location.isIsShipFromParty()) {
				statement.setByte(28, (byte) 1);
			} else {
				statement.setByte(28, (byte) 0);
			}
			statement.executeUpdate();
			try (ResultSet resultSet = statement.getGeneratedKeys()) {
				while (resultSet.next()) {
					location_number = resultSet.getLong(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return location_number;
	}

	public long insertPoSchedulePojo(PoSchedule poschedule) {
		long po_schedule_number = 0;
		sql = "insert into po_schedule(line_number,quantity,base_uom_quantity,required_delivery_datetime,"
				+ "back_ordered_quantity,back_ordered_base_uom_quantity,received_quantity,received_base_uom_quantity,schedule_line_type,po_line_id)"
				+ " values(?,?,?,?,?,?,?,?,?,?)";
		try (Connection con = DbConnection.connect();
				PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

			statement.setInt(1, poschedule.getLineNumber());
			statement.setInt(2, poschedule.getQuantity());
			statement.setInt(3, poschedule.getBaseUomQuantity());
			statement.setString(4, poschedule.getRequiredDeliveryDatetime());
			statement.setInt(5, poschedule.getBackOrderedQuantity());
			statement.setInt(6, poschedule.getBackOrderedBaseUomQuantity());
			statement.setInt(7, poschedule.getReceivedQuantity());
			statement.setInt(8, poschedule.getReceivedBaseUomQuantity());
			statement.setString(9, poschedule.getScheduleLineType());
			statement.setLong(10, poschedule.getPolineNumberFk());
			statement.executeUpdate();
			try (ResultSet resultSet = statement.getGeneratedKeys()) {
				while (resultSet.next()) {
					po_schedule_number = resultSet.getLong(1);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return po_schedule_number;

	}

	
	public long insertPoLinePojo(PoLine line){
		long poline_number=0;
		sql="INSERT INTO PO_LINE(PO_HEADER_ID,LINE_NUMBER,NOTE,ITEM_ID,SERVICE_INDICATOR,DESCRIPTION,ITEM_NOTE_LANGUAGE,"
				+ "ITEM_NOTE,SERIALIZED_LOT,SERIALIZED_LOT_SECLECTION,UNIT_CODE,QUANTITY,BASE_UOM_QUANTITY,AMOUNT,AMOUNT_CURRENCY_ID,"
				+ "BASE_AMOUNT,BASE_AMOUNT_CURRENCY_ID,REPORT_AMOUNT,REPORT_AMOUNT_CURRENCY_ID,PER_QUNATITY,PER_BASE_UOM_QUANTITY,EXTENDED_AMOUNT,"
				+ "EXTENDED_AMOUNT_CURRENCY_ID,EXTENDED_BASE_AMOUNT,EXTENDED_BASE_AMOUNT_CURRENCY_ID,EXTENDED_REPORT_AMOUNT,EXTENDED_REPORT_AMOUNT_CURRENCY_ID,"
				+ "TOTAL_AMOUNT,TOTAL_BASE_AMOUNT,TOTAL_REPORT_AMOUNT,REQUIRED_DELIVERY_DATETIME,BACK_ORDERED_QUANTITY,BACK_ORDERED_BASE_UOM_QUANTITY,"
				+ "RECEIVED_QUANTITY,RECEIVED_BASE_UOM_QUANTITY,OPEN_QUANTITY,OPEN_BASE_UOM_QUANTITY,CONTRACT_APPLICABLE_INDICATOR,"
				+ "UNIT_QUANTITY_CONVERSION_FACTOR,DROP_SHIP_INDICATOR,BASE_CURRENCY_AMOUNT_TYPE,BASE_CURRENCY_AMOUNT_ID,BASE_CURRENCY_AMOUNT) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try (Connection con = DbConnection.connect(); PreparedStatement statement = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
			statement.setLong(1, line.getPoHeaderId());
			statement.setInt(2, line.getLineNumber());
			statement.setString(3,line.getNote());
			statement.setString(4,line.getItemId());
			if (line.isServiceIndicator()) {
				statement.setByte(5, (byte) 1);
			} else {
				statement.setByte(5, (byte) 0);
			}
			statement.setString(6, line.getDescription());
			statement.setString(7,line.getItemNoteLanguage());
			statement.setString(8,line.getItemNote());
			statement.setString(9,line.getSerializedLotId());
			statement.setString(10,line.getSerializedLotSelection());
			statement.setString(11,line.getUnitCode());
			statement.setInt(12, line.getQuantity());
			statement.setInt(13, line.getBaseUomQuantity());
			statement.setLong(14,line.getAmount());
			statement.setString(15, line.getAmountCurrencyId());
			statement.setLong(16,line.getBaseAmount());
			statement.setString(17, line.getBaseAmountCurrencyId());
			if(line.getReportAmount()==null)
			{
				
				statement.setNull(18,Types.FLOAT);
			}else
			{
				statement.setFloat(18,line.getReportAmount());	
			}
			
			statement.setString(19, line.getReportAmountCurrencyId());
			statement.setInt(20, line.getPerQuantity());
			statement.setInt(21, line.getPerBaseUomQuantity());
			statement.setLong(22,line.getExtendedAmount());
			statement.setString(23,line.getExtendedAmountCurrencyId());
			statement.setLong(24, line.getExtendedBaseAmount());
			statement.setLong(25, line.getExtendedBaseAmountCurrencyId());
			statement.setLong(26, line.getExtendedReportAmount());
			statement.setString(27,line.getExtendedReportAmountCurrencyId());
			statement.setLong(28,line.getTotalAmount());
			statement.setLong(29, line.getTotalBaseAmount());
			statement.setLong(30, line.getTotalReportAmount());
			statement.setString(31,line.getRequiredDeliveryDatetime());
			statement.setInt(32, line.getBackOrderedQuantity());
			statement.setInt(33, line.getBackOrderedBaseUomQuantity());
			statement.setInt(34, line.getReceivedQuantity());
			statement.setInt(35, line.getReceivedBaseUomQuantity());
			statement.setInt(36, line.getOpenQuantity());
			statement.setInt(37, line.getOpenBaseUomQuantity());
			if(line.isContractApplicableIndicator()){
				statement.setByte(38, (byte)1);
			}else{
				statement.setByte(38,(byte)0);
			}
			statement.setInt(39, line.getUnitQuantityConversionFactor());
			if (line.isDropShipIndicator()) {
				statement.setByte(40, (byte) 1);
			} else {
				statement.setByte(40, (byte) 0);
			}
			statement.setString(41,line.getBaseCurrencyAmountType());
			statement.setString(42,line.getBaseCurrencyAmountId() );
			statement.setLong(43, line.getBaseAmount());
			statement.executeUpdate();
			ResultSet resultSet=statement.getGeneratedKeys();
			while(resultSet.next())
			{
				poline_number=resultSet.getLong(1);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return poline_number;
	}
	
	
	
	
	public long insertPoHeaderPojo(PoHeader poheader) {
		long headerId = 0;
		sql = "INSERT INTO PO_HEADER(DOCUMENT_ID,ACCOUNTING_ENTITY,LOCATION,LOGICAL_ID,VARIATION_ID,ALTERNATE_DOCUMENT_ID,DISPLAY_ID,LAST_MODIFICATION_DATETIME,"
				+ "LAST_MODIFICATION_PERSON_ID,LAST_MODIFICATION_PERSON_NAME,DOCUMENT_DATETIME,SUPPLIER_PARTY_ID,SUPPLIER_PARTY_NAME,"
				+ "SHIP_FROM_PARTY_ID,SHIP_FROM_PARTY_NAME,EXTENDED_AMOUNT,EXTENDED_AMOUNT_CURRENCY_ID,EXTENDED_BASE_AMOUNT,EXTENDED_BASE_AMOUNT_CURRENCY_ID,"
				+ "EXTENDED_REPORT_AMOUNT,EXTENDED_REPORT_AMOUNT_CURRENCY_ID,CANCELED_AMOUNT,CANCELED_BASE_AMOUNT,CANCELED_REPORTING_AMOUNT,PAYMENT_TERM_TYPE,PAYMENT_TERM_ID,PAYMENT_TERM_DESCRIPTION,"
				+ "PAYMENT_TERM_CODE,PAYMENT_TERM_LIST_ID,ORDER_DATETIME,BUYER_ID,USER_AREA_PROPERTY_NAME,USER_AREA_PROPERTY_TYPE,"
				+ "USER_AREA_PROPERTY_NAME_VALUE,DOCUMENT_USAGE_CODE,BASE_CURRENCY_AMOUNT_TYPE,BASE_CURRENCY_AMOUNT_ID,BASE_CURRENCY_AMOUNT) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try (Connection con = DbConnection.connect();
				PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			statement.setString(1, poheader.getDocumentId());
			statement.setString(2, poheader.getAccountingEntity());
			statement.setString(3, poheader.getLocationName());
			statement.setString(4, poheader.getLogicalId());
			statement.setInt(5, poheader.getVariationId());
			statement.setString(6, poheader.getAlternateDocumentId());
			statement.setString(7, poheader.getDisplayId());
			statement.setString(8, poheader.getLastModificationDatetime());
			statement.setInt(9, poheader.getLastModificationPersonId());
			statement.setString(10, poheader.getLastModificationPersonName());
			statement.setString(11, poheader.getDocumentDatetime());
			statement.setString(12, poheader.getSupplierPartyId());
			statement.setString(13, poheader.getSupplierPartyName());
			statement.setString(14, poheader.getShipFromPartyId());
			statement.setString(15, poheader.getShipFromPartyName());
			statement.setLong(16, poheader.getExtendedAmount());
			statement.setString(17, poheader.getExtendedAmountCurrencyId());
			statement.setLong(18, poheader.getExtendedBaseAmount());
			statement.setString(19, poheader.getExtendedBaseAmountCurrencyId());
			statement.setLong(20, poheader.getExtendedReportAmount());
			statement.setString(21, poheader.getExtendedReportAmountCurrencyId());
			statement.setLong(22, poheader.getCanceledAmount());
			statement.setLong(23, poheader.getCanceledBaseAmount());
			statement.setLong(24, poheader.getCanceledReportingAmount());
			statement.setString(25, poheader.getPaymentTermType());
			statement.setString(26, poheader.getPaymentTermId());
			statement.setString(27, poheader.getPaymentTermDescription());
			statement.setString(28, poheader.getPaymentTermCode());
			statement.setString(29, poheader.getPaymentTermListId());
			statement.setString(30, poheader.getOrderDatetime());
			statement.setString(31, poheader.getBuyerId());
			statement.setString(32, poheader.getUserAreaPropertyName());
			statement.setString(33, poheader.getUserAreaPropertyType());
			statement.setString(34, poheader.getUserAreaPropertyameValue());
			statement.setString(35, poheader.getDocumentUsageCode());
			statement.setString(36, poheader.getBaseCurrencyAmountType());
			statement.setString(37, poheader.getBaseCurrencyAmountId());
			statement.setLong(38, poheader.getBaseCurrencyAmount());
			statement.executeUpdate();
			try (ResultSet resultSet = statement.getGeneratedKeys()) {
				while (resultSet.next()) {
					headerId = resultSet.getLong(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return headerId;
	}

}
