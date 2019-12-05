/**
 * 
 */
package domparser.dbcalls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import domparser.javaobjects.Code;
import domparser.javaobjects.Location;
import domparser.javaobjects.PoHeader;
import domparser.javaobjects.PoLine;
import domparser.javaobjects.PoSchedule;
import domparser.javaobjects.Status;
import domparser.utilities.DbConnection;

/**
 * @author sakkenapelly
 *
 */
public class DatabaseRetrival {

	String query = null;
	ResultSet resultSet = null;
	PoHeader poHeader = null;
	Logger logger =Logger.getLogger(DatabaseRetrival.class);
	
	
	public PoHeader getPoheaderPojo(Long poheaderId) {
		query = "SELECT * FROM  [SYNC_PURCHASE_ORDERS].[dbo].[PO_HEADER] WHERE PO_HEADER_ID=?";
		try (Connection con = DbConnection.connect(); PreparedStatement statement = con.prepareStatement(query);) {
			statement.setLong(1, poheaderId);
			resultSet = statement.executeQuery();
			poHeader = new PoHeader();
			while (resultSet.next()) {
				poHeader.setId(Integer.parseInt(resultSet.getString(1)));
				poHeader.setDocumentId(resultSet.getString(2));
				poHeader.setAccountingEntity(resultSet.getString(3));
				poHeader.setLocationName(resultSet.getString(4));
				poHeader.setLogicalId(resultSet.getString(5));
				poHeader.setVariationId(Integer.parseInt(resultSet.getString(6)));
				poHeader.setAlternateDocumentId(resultSet.getString(7));
				poHeader.setDisplayId(resultSet.getString(8));
				poHeader.setLastModificationDatetime(resultSet.getString(9));
				poHeader.setLastModificationPersonId(Integer.parseInt(resultSet.getString(10)));
				poHeader.setLastModificationPersonName(resultSet.getString(11));
				poHeader.setDocumentDatetime(resultSet.getString(12));
				poHeader.setSupplierPartyId(resultSet.getString(13));
				poHeader.setSupplierPartyName(resultSet.getString(14));
				poHeader.setShipFromPartyId(resultSet.getString(15));
				poHeader.setShipFromPartyName(resultSet.getString(16));
				poHeader.setExtendedAmount(resultSet.getLong(17));
				poHeader.setExtendedAmountCurrencyId(resultSet.getString(18));
				poHeader.setExtendedBaseAmount(resultSet.getLong(19));
				poHeader.setExtendedBaseAmountCurrencyId(resultSet.getString(20));
				poHeader.setExtendedReportAmount(resultSet.getLong(21));
				poHeader.setExtendedReportAmountCurrencyId(resultSet.getString(22));
				poHeader.setCanceledAmount(resultSet.getLong(23));
				poHeader.setCanceledBaseAmount(resultSet.getLong(24));
				poHeader.setCanceledReportingAmount(resultSet.getLong(25));
				poHeader.setPaymentTermType(resultSet.getString(26));
				poHeader.setPaymentTermId(resultSet.getString(27));
				poHeader.setPaymentTermDescription(resultSet.getString(28));
				poHeader.setPaymentTermCode(resultSet.getString(29));
				poHeader.setPaymentTermListId(resultSet.getString(30));
				poHeader.setOrderDatetime(resultSet.getString(31));
				poHeader.setBuyerId(resultSet.getString(32));
				poHeader.setUserAreaPropertyName(resultSet.getString(33));
				poHeader.setUserAreaPropertyType(resultSet.getString(34));
				poHeader.setUserAreaPropertyameValue(resultSet.getString(35));
				poHeader.setDocumentUsageCode(resultSet.getString(36));
				poHeader.setBaseCurrencyAmountType(resultSet.getString(37));
				poHeader.setBaseCurrencyAmountId(resultSet.getString(38));
				poHeader.setBaseCurrencyAmount(resultSet.getLong(39));

			}

		} catch (SQLException e) {
			logger.error("exeception raised while retriving from header table");
		}

		return poHeader;
	}
	
	public List<Location> getLocation(Long poheaderId,Long polineId,Long poscheduleId) {
	        List<Location> locationList = new ArrayList<>();
	        
	        int count =0;
			if(polineId ==null && poscheduleId==null)
			{
				query = "SELECT * FROM [SYNC_PURCHASE_ORDERS].[dbo].[PO_LOCATIONS] WHERE PO_HEADER_ID=?";	
				count=1;
			}else if(poheaderId ==null && poscheduleId==null)
			{
				query = "SELECT * FROM [SYNC_PURCHASE_ORDERS].[dbo].[PO_LOCATIONS] WHERE PO_LINE_ID=?";
				count=2;
			}else if(poheaderId==null)
			{
				query = "SELECT * FROM [SYNC_PURCHASE_ORDERS].[dbo].[PO_LOCATIONS] WHERE PO_LINE_ID=? AND PO_SCHEDULE_ID=?";
				count=3;
			}
	        
	        try (Connection con = DbConnection.connect(); PreparedStatement statement = con.prepareStatement(query);) {
	        	
	        	if(count==1)
				{
					statement.setLong(1, poheaderId);
				}
				else if(count==2)
				{
					statement.setLong(1, polineId);
				}
				else if(count==3)
				{
					statement.setLong(1, polineId);
					statement.setLong(2, poscheduleId);
				}
				
	            resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	                Location location = new Location();
	                location.setId(Integer.parseInt(resultSet.getString(1)));
	                location.setLocationId(resultSet.getString(2));
	                location.setLocationType(resultSet.getString(3));
	                location.setLocationName(resultSet.getString(4));
	                location.setAddressType(resultSet.getString(5));
	                location.setAttentionOfName(resultSet.getString(6));
	                location.setAddressLineSequence1(resultSet.getString(7));
	                location.setAddressLineSequence2(resultSet.getString(8));
	                location.setAddressLineSequence3(resultSet.getString(9));
	                location.setAddressLineSequence4(resultSet.getString(10));
	                location.setAddressLineSequence5(resultSet.getString(11));
	                location.setAddressLineSequence6(resultSet.getString(12));
	                location.setCityName(resultSet.getString(13));
	                location.setCountrySubDivisionCode(resultSet.getString(14));
	                location.setCountryCode(resultSet.getString(15));
	                location.setPostalCode(resultSet.getString(16));
	                location.setBuildingNumber(resultSet.getInt(17));
	                location.setBuildingName(resultSet.getString(18));
	                location.setStreetName(resultSet.getString(19));
	                location.setUnit(resultSet.getInt(20));
	                location.setFloor(resultSet.getInt(21));
	                location.setPostOfficeBox(resultSet.getInt(22));
	                location.setPoHeaderId(resultSet.getLong(23));
	                location.setPoLineId(resultSet.getLong(24));
	                location.setPoScheduleId(resultSet.getLong(25));
	                location.setIsCustomerParty(resultSet.getBoolean(26));
	                location.setIsSupplierParty(resultSet.getBoolean(27));
	                location.setIsShipToParty(resultSet.getBoolean(28));
	                location.setIsShipFromParty(resultSet.getBoolean(29));
	                locationList.add(location);
	            }

	        } catch (SQLException e) {
	        	logger.error("execption raised while retriving from locations table");
	        }
	        return locationList;
	    }
	
	public List<Code> getCode(Long poheaderId) {
		query = "SELECT * FROM  [SYNC_PURCHASE_ORDERS].[dbo].[CODES] WHERE PO_HEADER_ID=?";
		List<Code> codesList = new ArrayList<>();
		try (Connection con = DbConnection.connect(); PreparedStatement statement = con.prepareStatement(query);) {
			statement.setLong(1, poheaderId);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Code code = new Code();
				code.setId(resultSet.getLong(1));
				code.setListId(resultSet.getString(2));
				code.setSequence(resultSet.getLong(3));
				code.setCode(resultSet.getString(4));
				codesList.add(code);
			}

		} catch (SQLException e) {
			logger.error("exceptions raised while retriving from codes table");
		}
		return codesList;
	}

	public List<PoLine> getPolines(Long poheaderId) {
		List<PoLine> polineList = new ArrayList<>();
		query = "SELECT * FROM  [SYNC_PURCHASE_ORDERS].[dbo].[PO_LINE] WHERE PO_HEADER_ID=?";
		try (Connection con = DbConnection.connect(); PreparedStatement statement = con.prepareStatement(query);) {
			statement.setLong(1, poheaderId);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {

				PoLine poline = new PoLine();
				poline.setId(resultSet.getLong(1));
				poline.setLineNumber(resultSet.getInt(3));
				poline.setNote(resultSet.getString(4));
				poline.setItemId(resultSet.getString(5));
				poline.setServiceIndicator(resultSet.getBoolean(6));
				poline.setDescription(resultSet.getString(7));
				poline.setItemNoteLanguage(resultSet.getString(8));
				poline.setItemNote(resultSet.getString(9));
				poline.setSerializedLotId(resultSet.getString(10));
				poline.setSerializedLotSelection(resultSet.getString(11));
				poline.setUnitCode(resultSet.getString(12));
				poline.setQuantity(resultSet.getInt(13));
				poline.setBaseUomQuantity(resultSet.getInt(14));
				poline.setAmount(resultSet.getLong(15));
				poline.setAmountCurrencyId(resultSet.getString(16));
				poline.setBaseAmount(resultSet.getLong(17));
				poline.setBaseAmountCurrencyId(resultSet.getString(18));
				poline.setReportAmount(Float.parseFloat(resultSet.getString(46)));
				poline.setReportAmountCurrencyId(resultSet.getString(19));
				poline.setPerQuantity(resultSet.getInt(20));
				poline.setPerBaseUomQuantity(resultSet.getInt(21));
				poline.setExtendedAmount(resultSet.getLong(22));
				poline.setExtendedAmountCurrencyId(resultSet.getString(23));
				poline.setExtendedBaseAmount(resultSet.getLong(24));
				poline.setExtendedBaseAmountCurrencyId(Long.parseLong(resultSet.getString(25)));
				poline.setExtendedReportAmount(resultSet.getLong(26));
				poline.setExtendedReportAmountCurrencyId(resultSet.getString(27));
				poline.setTotalAmount(resultSet.getLong(28));
				poline.setTotalBaseAmount(resultSet.getLong(29));
				poline.setTotalReportAmount(resultSet.getLong(30));
				poline.setRequiredDeliveryDatetime(resultSet.getString(31));
				poline.setBackOrderedQuantity(resultSet.getInt(32));
				poline.setBackOrderedBaseUomQuantity(resultSet.getInt(33));
				poline.setReceivedQuantity(resultSet.getInt(34));
				poline.setReceivedBaseUomQuantity(resultSet.getInt(35));
				poline.setOpenQuantity(resultSet.getInt(36));
				poline.setOpenBaseUomQuantity(resultSet.getInt(37));
				poline.setContractApplicableIndicator(resultSet.getBoolean(38));
				poline.setUnitQuantityConversionFactor(resultSet.getInt(39));
				poline.setDropShipIndicator(resultSet.getBoolean(40));
				poline.setBaseCurrencyAmountType(resultSet.getString(41));
				poline.setBaseCurrencyAmountId(resultSet.getString(42));
				poline.setBaseCurrencyAmount(resultSet.getLong(43));
				polineList.add(poline);
			}

		} catch (SQLException e) {
			logger.error("exception raised while retiving from lines list");
		}
		return polineList;

	}

	public List<PoSchedule> getSchedules(Long polineId) {
		List<PoSchedule> poscheduleList = new ArrayList<>();
		query = "SELECT * FROM  [SYNC_PURCHASE_ORDERS].[dbo].[PO_SCHEDULE] WHERE PO_LINE_ID=?";
		try (Connection con = DbConnection.connect(); PreparedStatement statement = con.prepareStatement(query);) {
			statement.setLong(1, polineId);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				PoSchedule poschedule = new PoSchedule();
				poschedule.setPoScheduleNumber(resultSet.getLong(1));
				poschedule.setLineNumber(resultSet.getInt(2));
				poschedule.setQuantity(resultSet.getInt(3));
				poschedule.setBaseUomQuantity(resultSet.getInt(4));
				poschedule.setRequiredDeliveryDatetime(resultSet.getString(5));
				poschedule.setBackOrderedQuantity(resultSet.getInt(6));
				poschedule.setBackOrderedBaseUomQuantity(resultSet.getInt(7));
				poschedule.setReceivedQuantity(resultSet.getInt(8));
				poschedule.setReceivedBaseUomQuantity(resultSet.getInt(9));
				poschedule.setScheduleLineType(resultSet.getString(10));
				poscheduleList.add(poschedule);

			}
		} catch (SQLException e) {

			logger.error("exception raised while retriving from schedules table");
		}
		return poscheduleList;
	}

	public Status getStatus(Long poheaderId, Long polineId, Long poscheduleId) {
		Status headerstatus = new Status();
		int count =0;
		if(polineId ==null && poscheduleId==null)
		{
			query = "SELECT * FROM [SYNC_PURCHASE_ORDERS].[dbo].[STATUS] WHERE PO_HEADER_ID=?";	
			count=1;
		}else if(poheaderId ==null && poscheduleId==null)
		{
			query = "SELECT * FROM [SYNC_PURCHASE_ORDERS].[dbo].[STATUS] WHERE PO_LINE_ID=?";
			count=2;
		}else if(poheaderId==null)
		{
			query = "SELECT * FROM [SYNC_PURCHASE_ORDERS].[dbo].[STATUS] WHERE PO_LINE_ID=? AND PO_SCHEDULE_ID=?";
			count=3;
		}
		
		try (Connection con = DbConnection.connect(); PreparedStatement statement = con.prepareStatement(query);) {

			if(count==1)
			{
				statement.setLong(1, poheaderId);
			}
			else if(count==2)
			{
				statement.setLong(1, polineId);
			}
			else if(count==3)
			{
				statement.setLong(1, polineId);
				statement.setLong(2, poscheduleId);
			}
			
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				headerstatus.setId(Integer.parseInt(resultSet.getString(1)));
				headerstatus.setStatusCode(resultSet.getString(2));
				headerstatus.setEffectiveDatetime(resultSet.getString(3));
				headerstatus.setArchiveIndicator(resultSet.getBoolean(4));
				headerstatus.setHeaderId(resultSet.getLong(5));
				headerstatus.setLineId(resultSet.getLong(6));
				headerstatus.setScheduleId(resultSet.getLong(7));

			}

		} catch (SQLException e) {
			logger.error("exception raised while retriving from status table");
		}
		return headerstatus;
	}

	
}
