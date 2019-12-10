/**
 * 
 */
package domparser.utilities;

import java.io.File;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import domparser.pojos.PoHeader;

/**
 * @author sakkenapelly
 *
 */
public class XmlFormation {
	public void formXml(PoHeader poheader, Properties properties) {

		Node childNode;
		Node subChildNode;
		Node parentNode;
		Node node;
		Node rootNode;
		Node postSubChildNode;
		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
		final Logger logger = Logger.getLogger(XmlFormation.class);
		try {
			DocumentBuilder docbuilder = dbfactory.newDocumentBuilder();
			Document doc = docbuilder.newDocument();
			Element root = doc.createElement("SyncPurchaseOrder");
			root.setAttribute("xmlns", properties.getProperty("xmlns"));
			root.setAttribute("xmlns:xsi", properties.getProperty("xmlns:xsi"));
			root.setAttribute("xsi:schemaLocation", properties.getProperty("xsi:schemaLocation"));
			root.setAttribute("xmlns:xsd", properties.getProperty("xmlns:xsd"));
			root.setAttribute("releaseID", properties.getProperty("releaseID"));
			root.setAttribute("versionID", properties.getProperty("versionID"));
			doc.appendChild(root);
			parentNode = root.appendChild(doc.createElement("ApplicationArea"));
			childNode = parentNode.appendChild(doc.createElement("Sender"));
			subChildNode = childNode.appendChild(doc.createElement("LogicalID"));
			subChildNode.setTextContent(properties.getProperty("LogicalID"));
			subChildNode = childNode.appendChild(doc.createElement("ComponentID"));
			subChildNode.setTextContent(properties.getProperty("ComponentID"));
			subChildNode = childNode.appendChild(doc.createElement("ConfirmationCode"));
			subChildNode.setTextContent(properties.getProperty("ConfirmationCode"));
			childNode = parentNode.appendChild(doc.createElement("CreationDateTime"));
			childNode.setTextContent(properties.getProperty("CreationDateTime"));
			childNode = parentNode.appendChild(doc.createElement("BODID"));
			childNode.setTextContent(properties.getProperty("BODID"));
			parentNode = root.appendChild(doc.createElement("DataArea"));
			childNode = parentNode.appendChild(doc.createElement("Sync"));
			subChildNode = childNode.appendChild(doc.createElement("TenantID"));
			subChildNode.setTextContent(properties.getProperty("TenantID"));
			subChildNode = childNode.appendChild(doc.createElement("AccountingEntityID"));
			subChildNode.setTextContent(properties.getProperty("AccountingEntityID"));
			subChildNode = childNode.appendChild(doc.createElement("LocationID"));
			subChildNode.setTextContent(properties.getProperty("LocationID"));
			subChildNode = childNode.appendChild(doc.createElement("ActionCriteria"));
			postSubChildNode = subChildNode.appendChild(doc.createElement("ActionExpression"));
			((Element) postSubChildNode).setAttribute("actionCode", properties.getProperty("actionCode"));
			Element element = doc.createElement("PurchaseOrder");
			parentNode.appendChild(element);
			rootNode = doc.createElement("PurchaseOrderHeader");
			element.appendChild(rootNode);
			node = doc.createElement("DocumentId");
			rootNode.appendChild(node);
			childNode = node.appendChild(doc.createElement("ID"));
			childNode.setTextContent(poheader.getDocumentId());
			((Element) childNode).setAttribute("AccountingEntity", poheader.getAccountingEntity());
			((Element) childNode).setAttribute("location", poheader.getLocationName());
			((Element) childNode).setAttribute("lid", poheader.getLogicalId());
			((Element) childNode).setAttribute("variationID", new Integer(poheader.getVariationId()).toString());
			node = doc.createElement("AlternateDocumentID");
			rootNode.appendChild(node);
			childNode = node.appendChild(doc.createElement("ID"));
			node = doc.createElement("DisplayID");
			node.setTextContent(poheader.getDisplayId());
			rootNode.appendChild(node);
			node = doc.createElement("LastModificationDateTime");
			node.setTextContent(poheader.getLastModificationDatetime());
			rootNode.appendChild(node);
			node = doc.createElement("LastModificationPerson");
			childNode = node.appendChild(doc.createElement("IDs"));
			subChildNode = childNode.appendChild(doc.createElement("ID"));
			subChildNode.setTextContent(new Integer(poheader.getLastModificationPersonId()).toString());
			((Element) subChildNode).setAttribute("AccountingEntity", poheader.getAccountingEntity());
			((Element) subChildNode).setAttribute("lid", poheader.getLogicalId());
			subChildNode = childNode.appendChild(doc.createElement("Name"));
			subChildNode.setTextContent(poheader.getLastModificationPersonName());
			rootNode.appendChild(node);
			node = doc.createElement("DocumentDateTime");
			node.setTextContent(poheader.getDocumentDatetime());
			rootNode.appendChild(node);
			node = doc.createElement("Status");
			childNode = node.appendChild(doc.createElement("code"));
			childNode.setTextContent(poheader.getStatus().getStatusCode());
			childNode = node.appendChild(doc.createElement("EffectiveDateTime"));
			childNode.setTextContent(poheader.getStatus().getEffectiveDatetime());
			childNode = node.appendChild(doc.createElement("ArchiveIndicator"));
			childNode.setTextContent(new Boolean(poheader.getStatus().isArchiveIndicator()).toString());
			rootNode.appendChild(node);
			node = doc.createElement("CustomerParty");
			childNode = node.appendChild(doc.createElement("Location"));
			((Element) childNode).setAttribute("type", poheader.getLocations().get(0).getAddressType());
			subChildNode = childNode.appendChild(doc.createElement("ID"));
			((Element) subChildNode).setAttribute("AccountingEntity", poheader.getAccountingEntity());
			((Element) subChildNode).setAttribute("lid", poheader.getLogicalId());
			subChildNode.setTextContent(poheader.getLocations().get(0).getLocationId());
			subChildNode = childNode.appendChild(doc.createElement("Name"));
			subChildNode.setTextContent(poheader.getLocations().get(0).getLocationName());
			rootNode.appendChild(node);
			node = rootNode.appendChild(doc.createElement("SupplierParty"));
			childNode = doc.createElement("PartyIDs");
			subChildNode = childNode.appendChild(doc.createElement("ID"));
			((Element) subChildNode).setAttribute("AccountingEntity", poheader.getAccountingEntity());
			((Element) subChildNode).setAttribute("lid", poheader.getLogicalId());
			subChildNode.setTextContent(poheader.getSupplierPartyId());
			node.appendChild(childNode);
			childNode = node.appendChild(doc.createElement("Name"));
			childNode.setTextContent(poheader.getSupplierPartyName());
			childNode = doc.createElement("Location");
			node.appendChild(childNode);
			subChildNode = childNode.appendChild(doc.createElement("Address"));
			((Element) subChildNode).setAttribute("type", poheader.getLocations().get(1).getAddressType());
			postSubChildNode = subChildNode.appendChild(doc.createElement("AttentionOfName"));
			postSubChildNode.setTextContent(poheader.getLocations().get(1).getAttentionOfName());
			postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
			((Element) postSubChildNode).setAttribute("sequence", "1");
			postSubChildNode.setTextContent(poheader.getLocations().get(1).getAddressLineSequence1());
			postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
			((Element) postSubChildNode).setAttribute("sequence", "2");
			postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
			((Element) postSubChildNode).setAttribute("sequence", "3");
			postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
			((Element) postSubChildNode).setAttribute("sequence", "4");
			;
			postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
			((Element) postSubChildNode).setAttribute("sequence", "5");
			postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
			((Element) postSubChildNode).setAttribute("sequence", "6");
			postSubChildNode = subChildNode.appendChild(doc.createElement("CityName"));
			postSubChildNode.setTextContent(poheader.getLocations().get(1).getCityName());
			postSubChildNode = subChildNode.appendChild(doc.createElement("CountrySubDivisionCode"));
			postSubChildNode.setTextContent(poheader.getLocations().get(1).getCountrySubDivisionCode());
			postSubChildNode = subChildNode.appendChild(doc.createElement("CountryCode"));
			postSubChildNode.setTextContent(poheader.getLocations().get(1).getCountryCode());
			postSubChildNode = subChildNode.appendChild(doc.createElement("PostalCode"));
			postSubChildNode.setTextContent(poheader.getLocations().get(1).getPostalCode());
			subChildNode = childNode.appendChild(doc.createElement("Address"));
			((Element) subChildNode).setAttribute("type", poheader.getLocations().get(2).getAddressType());
			postSubChildNode = subChildNode.appendChild(doc.createElement("AttentionOfName"));
			postSubChildNode.setTextContent(poheader.getLocations().get(2).getAttentionOfName());
			postSubChildNode = subChildNode.appendChild(doc.createElement("BuildingNumber"));
			postSubChildNode.setTextContent(new Integer(poheader.getLocations().get(2).getBuildingNumber()).toString());
			subChildNode.appendChild(doc.createElement("BuildingName"));
			postSubChildNode = subChildNode.appendChild(doc.createElement("StreetName"));
			postSubChildNode.setTextContent(poheader.getLocations().get(2).getStreetName());
			subChildNode.appendChild(doc.createElement("Unit"));
			subChildNode.appendChild(doc.createElement("Floor"));
			subChildNode.appendChild(doc.createElement("PostOfficeBox"));
			postSubChildNode = subChildNode.appendChild(doc.createElement("CityName"));
			postSubChildNode.setTextContent(poheader.getLocations().get(2).getCityName());
			postSubChildNode = subChildNode.appendChild(doc.createElement("CountrySubDivisionCode"));
			postSubChildNode.setTextContent(poheader.getLocations().get(2).getCountrySubDivisionCode());
			postSubChildNode = subChildNode.appendChild(doc.createElement("CountryCode"));
			postSubChildNode.setTextContent(poheader.getLocations().get(2).getCountryCode());
			postSubChildNode = subChildNode.appendChild(doc.createElement("PostalCode"));
			postSubChildNode.setTextContent(poheader.getLocations().get(2).getPostalCode());
			node = rootNode.appendChild(doc.createElement("ShipToParty"));
			childNode = doc.createElement("Location");
			node.appendChild(childNode);
			((Element) childNode).setAttribute("type", poheader.getLocations().get(3).getLocationType());
			subChildNode = childNode.appendChild(doc.createElement("ID"));
			((Element) subChildNode).setAttribute("AccountingEntity", poheader.getAccountingEntity());
			((Element) subChildNode).setAttribute("lid", poheader.getLogicalId());
			subChildNode.setTextContent(poheader.getLocations().get(3).getLocationId());
			subChildNode = childNode.appendChild(doc.createElement("Name"));
			subChildNode.setTextContent(poheader.getLocations().get(3).getLocationName());
			subChildNode = childNode.appendChild(doc.createElement("Address"));
			((Element) subChildNode).setAttribute("type", poheader.getLocations().get(3).getAddressType());
			postSubChildNode = subChildNode.appendChild(doc.createElement("AttentionOfName"));
			postSubChildNode.setTextContent(poheader.getLocations().get(3).getAttentionOfName());
			postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
			((Element) postSubChildNode).setAttribute("sequence", "1");
			postSubChildNode.setTextContent(poheader.getLocations().get(3).getAddressLineSequence1());
			postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
			((Element) postSubChildNode).setAttribute("sequence", "2");
			postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
			((Element) postSubChildNode).setAttribute("sequence", "3");
			postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
			((Element) postSubChildNode).setAttribute("sequence", "4");
			;
			postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
			((Element) postSubChildNode).setAttribute("sequence", "5");
			postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
			((Element) postSubChildNode).setAttribute("sequence", "6");
			postSubChildNode = subChildNode.appendChild(doc.createElement("CityName"));
			postSubChildNode.setTextContent(poheader.getLocations().get(3).getCityName());
			postSubChildNode = subChildNode.appendChild(doc.createElement("CountrySubDivisionCode"));
			postSubChildNode.setTextContent(poheader.getLocations().get(3).getCountrySubDivisionCode());
			postSubChildNode = subChildNode.appendChild(doc.createElement("CountryCode"));
			postSubChildNode.setTextContent(poheader.getLocations().get(3).getCountryCode());
			postSubChildNode = subChildNode.appendChild(doc.createElement("PostalCode"));
			postSubChildNode.setTextContent(poheader.getLocations().get(3).getPostalCode());
			subChildNode = childNode.appendChild(doc.createElement("Address"));
			((Element) subChildNode).setAttribute("type", poheader.getLocations().get(4).getAddressType());
			postSubChildNode = subChildNode.appendChild(doc.createElement("AttentionOfName"));
			postSubChildNode.setTextContent(poheader.getLocations().get(4).getAttentionOfName());
			postSubChildNode = subChildNode.appendChild(doc.createElement("BuildingNumber"));
			postSubChildNode.setTextContent(new Integer(poheader.getLocations().get(4).getBuildingNumber()).toString());
			subChildNode.appendChild(doc.createElement("BuildingName"));
			postSubChildNode = subChildNode.appendChild(doc.createElement("StreetName"));
			postSubChildNode.setTextContent(poheader.getLocations().get(4).getStreetName());
			subChildNode.appendChild(doc.createElement("Unit"));
			subChildNode.appendChild(doc.createElement("Floor"));
			subChildNode.appendChild(doc.createElement("PostOfficeBox"));
			postSubChildNode = subChildNode.appendChild(doc.createElement("CityName"));
			postSubChildNode.setTextContent(poheader.getLocations().get(4).getCityName());
			postSubChildNode = subChildNode.appendChild(doc.createElement("CountrySubDivisionCode"));
			postSubChildNode.setTextContent(poheader.getLocations().get(4).getCountrySubDivisionCode());
			postSubChildNode = subChildNode.appendChild(doc.createElement("CountryCode"));
			postSubChildNode.setTextContent(poheader.getLocations().get(4).getCountryCode());
			postSubChildNode = subChildNode.appendChild(doc.createElement("PostalCode"));
			postSubChildNode.setTextContent(poheader.getLocations().get(4).getPostalCode());
			node = rootNode.appendChild(doc.createElement("ShipFromParty"));
			childNode = doc.createElement("PartyIDs");
			subChildNode = childNode.appendChild(doc.createElement("ID"));
			subChildNode.setTextContent(poheader.getShipFromPartyId());
			node.appendChild(childNode);
			((Element) subChildNode).setAttribute("AccountingEntity", poheader.getAccountingEntity());
			((Element) subChildNode).setAttribute("lid", poheader.getLogicalId());
			childNode = node.appendChild(doc.createElement("Name"));
			childNode.setTextContent(poheader.getShipFromPartyName());
			childNode = doc.createElement("Location");
			node.appendChild(childNode);
			subChildNode = childNode.appendChild(doc.createElement("Address"));
			((Element) subChildNode).setAttribute("type", poheader.getLocations().get(5).getAddressType());
			postSubChildNode = subChildNode.appendChild(doc.createElement("AttentionOfName"));
			postSubChildNode.setTextContent(poheader.getLocations().get(5).getAttentionOfName());
			postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
			((Element) postSubChildNode).setAttribute("sequence", "1");
			postSubChildNode.setTextContent(poheader.getLocations().get(5).getAddressLineSequence1());
			postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
			((Element) postSubChildNode).setAttribute("sequence", "2");
			postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
			((Element) postSubChildNode).setAttribute("sequence", "3");
			postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
			((Element) postSubChildNode).setAttribute("sequence", "4");
			;
			postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
			((Element) postSubChildNode).setAttribute("sequence", "5");
			postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
			((Element) postSubChildNode).setAttribute("sequence", "6");
			postSubChildNode = subChildNode.appendChild(doc.createElement("CityName"));
			postSubChildNode.setTextContent(poheader.getLocations().get(5).getCityName());
			postSubChildNode = subChildNode.appendChild(doc.createElement("CountrySubDivisionCode"));
			postSubChildNode.setTextContent(poheader.getLocations().get(5).getCountrySubDivisionCode());
			postSubChildNode = subChildNode.appendChild(doc.createElement("CountryCode"));
			postSubChildNode.setTextContent(poheader.getLocations().get(5).getCountryCode());
			postSubChildNode = subChildNode.appendChild(doc.createElement("PostalCode"));
			postSubChildNode.setTextContent(poheader.getLocations().get(5).getPostalCode());
			subChildNode = childNode.appendChild(doc.createElement("Address"));
			((Element) subChildNode).setAttribute("type", poheader.getLocations().get(6).getAddressType());
			postSubChildNode = subChildNode.appendChild(doc.createElement("AttentionOfName"));
			postSubChildNode.setTextContent(poheader.getLocations().get(6).getAttentionOfName());
			postSubChildNode = subChildNode.appendChild(doc.createElement("BuildingNumber"));
			postSubChildNode.setTextContent(new Integer(poheader.getLocations().get(6).getBuildingNumber()).toString());
			subChildNode.appendChild(doc.createElement("BuildingName"));
			postSubChildNode = subChildNode.appendChild(doc.createElement("StreetName"));
			postSubChildNode.setTextContent(poheader.getLocations().get(6).getStreetName());
			subChildNode.appendChild(doc.createElement("Unit"));
			subChildNode.appendChild(doc.createElement("Floor"));
			subChildNode.appendChild(doc.createElement("PostOfficeBox"));
			postSubChildNode = subChildNode.appendChild(doc.createElement("CityName"));
			postSubChildNode.setTextContent(poheader.getLocations().get(6).getCityName());
			postSubChildNode = subChildNode.appendChild(doc.createElement("CountrySubDivisionCode"));
			postSubChildNode.setTextContent(poheader.getLocations().get(6).getCountrySubDivisionCode());
			postSubChildNode = subChildNode.appendChild(doc.createElement("CountryCode"));
			postSubChildNode.setTextContent(poheader.getLocations().get(6).getCountryCode());
			postSubChildNode = subChildNode.appendChild(doc.createElement("PostalCode"));
			postSubChildNode.setTextContent(poheader.getLocations().get(6).getPostalCode());
			node = doc.createElement("ExtendedAmount");
			((Element) node).setAttribute("currencyID", poheader.getExtendedAmountCurrencyId());
			node.setTextContent(new Long(poheader.getExtendedAmount()).toString());
			rootNode.appendChild(node);
			node = doc.createElement("ExtendedBaseAmount");
			((Element) node).setAttribute("currencyID", poheader.getExtendedBaseAmountCurrencyId());
			node.setTextContent(new Long(poheader.getExtendedBaseAmount()).toString());
			rootNode.appendChild(node);
			node = doc.createElement("ExtendedReportAmount");
			((Element) node).setAttribute("currencyID", poheader.getExtendedReportAmountCurrencyId());
			node.setTextContent(new Long(poheader.getExtendedReportAmount()).toString());
			rootNode.appendChild(node);
			node = doc.createElement("CanceledAmount");
			((Element) node).setAttribute("currencyID", poheader.getExtendedBaseAmountCurrencyId());
			node.setTextContent(new Long(poheader.getCanceledAmount()).toString());
			rootNode.appendChild(node);
			node = doc.createElement("CanceledBaseAmount");
			((Element) node).setAttribute("currencyID", poheader.getExtendedBaseAmountCurrencyId());
			node.setTextContent(new Long(poheader.getCanceledBaseAmount()).toString());
			rootNode.appendChild(node);
			node = doc.createElement("CanceledReportingAmount");
			((Element) node).setAttribute("currencyID", poheader.getExtendedReportAmountCurrencyId());
			node.setTextContent(new Long(poheader.getCanceledReportingAmount()).toString());
			rootNode.appendChild(node);
			node = doc.createElement("PaymentTerm");
			((Element) node).setAttribute("type", poheader.getPaymentTermType());
			childNode = node.appendChild(doc.createElement("IDs"));
			subChildNode = childNode.appendChild(doc.createElement("ID"));
			subChildNode.setTextContent(poheader.getPaymentTermId());
			childNode = node.appendChild(doc.createElement("Description"));
			childNode.setTextContent(poheader.getPaymentTermDescription());
			childNode = node.appendChild(doc.createElement("PaymentTermCode"));
			((Element) childNode).setAttribute("listID", poheader.getPaymentTermListId());
			((Element) childNode).setAttribute("accountingEntity", poheader.getAccountingEntity());
			childNode.setTextContent(poheader.getPaymentTermCode());
			rootNode.appendChild(node);
			node = doc.createElement("OrderDateTime");
			node.setTextContent(poheader.getOrderDatetime());
			rootNode.appendChild(node);
			node = doc.createElement("BuyerParty");
			childNode = node.appendChild(doc.createElement("Contact"));
			subChildNode = childNode.appendChild(doc.createElement("ID"));
			rootNode.appendChild(node);
			node = doc.createElement("UserArea");
			childNode = node.appendChild(doc.createElement("Property"));
			subChildNode = childNode.appendChild(doc.createElement("NameValue"));
			((Element) subChildNode).setAttribute("name", poheader.getUserAreaPropertyameValue());
			((Element) subChildNode).setAttribute("type", poheader.getUserAreaPropertyType());
			subChildNode.setTextContent(poheader.getUserAreaPropertyameValue());
			rootNode.appendChild(node);
			node = doc.createElement("Classification");
			childNode = node.appendChild(doc.createElement("Code"));
			subChildNode = childNode.appendChild(doc.createElement("Code"));
			((Element) subChildNode).setAttribute("listID", poheader.getCodes().get(0).getListId());
			((Element) subChildNode).setAttribute("sequence",
					new Long(poheader.getCodes().get(0).getSequence()).toString());
			((Element) subChildNode).setAttribute("accountingEntity", poheader.getAccountingEntity());
			subChildNode.setTextContent(poheader.getCodes().get(0).getCode());
			subChildNode = childNode.appendChild(doc.createElement("Code"));
			((Element) subChildNode).setAttribute("listID", poheader.getCodes().get(1).getListId());
			((Element) subChildNode).setAttribute("sequence",
					new Long(poheader.getCodes().get(1).getSequence()).toString());
			subChildNode.setTextContent(poheader.getCodes().get(1).getCode());
			rootNode.appendChild(node);
			node = doc.createElement("DocumentUsageCode");
			node.setTextContent(poheader.getDocumentUsageCode());
			rootNode.appendChild(node);
			node = doc.createElement("BaseCurrencyAmount");
			((Element) node).setAttribute("type", poheader.getBaseCurrencyAmountType());
			childNode = node.appendChild(doc.createElement("Amount"));
			((Element) childNode).setAttribute("currencyID", poheader.getExtendedBaseAmountCurrencyId());
			childNode.setTextContent(new Long(poheader.getBaseCurrencyAmount()).toString());
			rootNode.appendChild(node);
			// purchase order line
			for (int k = 0; k < poheader.getLines().size(); k++) {
				rootNode = doc.createElement("PurchaseOrderLine");
				element.appendChild(rootNode);
				poheader.getLines().get(k).getId();
				node = doc.createElement("LineNumber");
				node.setTextContent(new Integer(poheader.getLines().get(k).getLineNumber()).toString());
				rootNode.appendChild(node);
				rootNode.appendChild(doc.createElement("Note"));
				node = rootNode.appendChild(doc.createElement("Status"));
				childNode = node.appendChild(doc.createElement("code"));
				childNode.setTextContent(poheader.getLines().get(k).getStatus().getStatusCode());
				childNode = node.appendChild(doc.createElement("EffectiveDateTime"));
				childNode.setTextContent(poheader.getLines().get(k).getStatus().getEffectiveDatetime());
				childNode = node.appendChild(doc.createElement("ArchiveIndicator"));
				childNode.setTextContent(
						new Boolean(poheader.getLines().get(k).getStatus().isArchiveIndicator()).toString());

				// item
				node = doc.createElement("Item");
				childNode = node.appendChild(doc.createElement("ItemID"));
				subChildNode = childNode.appendChild(doc.createElement("ID"));
				((Element) subChildNode).setAttribute("accountingEntity", poheader.getAccountingEntity());
				((Element) subChildNode).setAttribute("lid", poheader.getLogicalId());
				subChildNode.setTextContent(poheader.getLines().get(k).getItemId());
				childNode = node.appendChild(doc.createElement("ServiceIndicator"));
				childNode.setTextContent(new Boolean(poheader.getLines().get(k).isServiceIndicator()).toString());
				childNode = node.appendChild(doc.createElement("Description"));
				childNode.setTextContent(poheader.getLines().get(k).getDescription());
				childNode = node.appendChild(doc.createElement("Note"));
				((Element) childNode).setAttribute("languageID", poheader.getLines().get(k).getItemNoteLanguage());
				childNode.setTextContent(poheader.getLines().get(k).getItemNote());
				childNode = node.appendChild(doc.createElement("SerializedLot"));
				subChildNode = childNode.appendChild(doc.createElement("Lot"));
				postSubChildNode = subChildNode.appendChild(doc.createElement("LotIDs"));
				postSubChildNode.appendChild(doc.createElement("ID"));
				subChildNode = childNode.appendChild(doc.createElement("LotSelection"));
				subChildNode.setTextContent(poheader.getLines().get(k).getSerializedLotSelection());
				rootNode.appendChild(node);
				node = rootNode.appendChild(doc.createElement("Quantity"));
				((Element) node).setAttribute("unitCode", poheader.getLines().get(k).getUnitCode());
				node.setTextContent(new Integer(poheader.getLines().get(k).getQuantity()).toString());
				node = rootNode.appendChild(doc.createElement("BaseUOMQuantity"));
				((Element) node).setAttribute("unitCode", poheader.getLines().get(k).getUnitCode());
				node.setTextContent(new Integer(poheader.getLines().get(k).getBaseUomQuantity()).toString());
				node = rootNode.appendChild(doc.createElement("UnitPrice"));
				childNode = node.appendChild(doc.createElement("Amount"));
				((Element) childNode).setAttribute("currencyID", poheader.getLines().get(k).getAmountCurrencyId());
				childNode.setTextContent(new Long(poheader.getLines().get(k).getAmount()).toString());
				childNode = node.appendChild(doc.createElement("BaseAmount"));
				((Element) childNode).setAttribute("currencyID", poheader.getLines().get(k).getAmountCurrencyId());
				childNode.setTextContent(new Long(poheader.getLines().get(k).getBaseAmount()).toString());
				childNode = node.appendChild(doc.createElement("ReportAmount"));
				((Element) childNode).setAttribute("currencyID",
						poheader.getLines().get(k).getReportAmountCurrencyId());
				childNode.setTextContent(new Float(poheader.getLines().get(k).getReportAmount()).toString());
				childNode = node.appendChild(doc.createElement("PerQuantity"));
				((Element) childNode).setAttribute("unitcode", poheader.getLines().get(k).getUnitCode());
				childNode.setTextContent(new Integer(poheader.getLines().get(k).getPerQuantity()).toString());
				childNode = node.appendChild(doc.createElement("PerBaseUOMQuantity"));
				((Element) childNode).setAttribute("unitcode", poheader.getLines().get(k).getUnitCode());
				childNode.setTextContent(new Integer(poheader.getLines().get(k).getPerBaseUomQuantity()).toString());
				node = rootNode.appendChild(doc.createElement("ExtendedAmount"));
				((Element) node).setAttribute("currencyID", poheader.getLines().get(k).getExtendedAmountCurrencyId());
				node.setTextContent(new Long(poheader.getLines().get(k).getExtendedAmount()).toString());
				node = rootNode.appendChild(doc.createElement("ExtendedBaseAmount"));
				((Element) node).setAttribute("currencyID", poheader.getLines().get(k).getExtendedAmountCurrencyId());
				node.setTextContent(new Long(poheader.getLines().get(k).getExtendedBaseAmount()).toString());
				node = rootNode.appendChild(doc.createElement("ExtendedReportAmount"));
				((Element) node).setAttribute("currencyID",
						poheader.getLines().get(k).getExtendedReportAmountCurrencyId());
				node.setTextContent(new Long(poheader.getLines().get(k).getExtendedReportAmount()).toString());
				node = rootNode.appendChild(doc.createElement("TotalAmount"));
				((Element) node).setAttribute("currencyID", poheader.getLines().get(k).getExtendedAmountCurrencyId());
				node.setTextContent(new Long(poheader.getLines().get(k).getTotalAmount()).toString());
				node = rootNode.appendChild(doc.createElement("TotalBaseAmount"));
				((Element) node).setAttribute("currencyID", poheader.getLines().get(k).getExtendedAmountCurrencyId());
				node.setTextContent(new Long(poheader.getLines().get(k).getTotalBaseAmount()).toString());
				node = rootNode.appendChild(doc.createElement("TotalReportAmount"));
				((Element) node).setAttribute("currencyID",
						poheader.getLines().get(k).getExtendedReportAmountCurrencyId());
				node.setTextContent(new Long(poheader.getLines().get(k).getTotalReportAmount()).toString());
				node = rootNode.appendChild(doc.createElement("RequiredDeliveryDateTime"));
				node.setTextContent(poheader.getLines().get(k).getRequiredDeliveryDatetime());
				node = rootNode.appendChild(doc.createElement("ShipToParty"));
				childNode = node.appendChild(doc.createElement("Location"));
				((Element) childNode).setAttribute("type",
						poheader.getLines().get(k).getLocations().get(0).getLocationType());
				subChildNode = childNode.appendChild(doc.createElement("ID"));
				((Element) subChildNode).setAttribute("AccountingEntity", poheader.getAccountingEntity());
				((Element) subChildNode).setAttribute("lid", poheader.getLogicalId());
				subChildNode.setTextContent(poheader.getLines().get(k).getLocations().get(0).getLocationId());
				subChildNode = childNode.appendChild(doc.createElement("Name"));
				subChildNode.setTextContent(poheader.getLines().get(k).getLocations().get(0).getLocationName());
				subChildNode = childNode.appendChild(doc.createElement("Address"));
				((Element) subChildNode).setAttribute("type",
						poheader.getLines().get(k).getLocations().get(0).getAddressType());
				postSubChildNode = subChildNode.appendChild(doc.createElement("AttentionOfName"));
				postSubChildNode.setTextContent(poheader.getLines().get(k).getLocations().get(0).getAttentionOfName());
				postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
				((Element) postSubChildNode).setAttribute("sequence", "1");
				postSubChildNode
						.setTextContent(poheader.getLines().get(k).getLocations().get(0).getAddressLineSequence1());
				postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
				((Element) postSubChildNode).setAttribute("sequence", "2");
				postSubChildNode
						.setTextContent(poheader.getLines().get(k).getLocations().get(0).getAddressLineSequence2());
				postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
				((Element) postSubChildNode).setAttribute("sequence", "3");
				postSubChildNode
						.setTextContent(poheader.getLines().get(k).getLocations().get(0).getAddressLineSequence3());
				postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
				((Element) postSubChildNode).setAttribute("sequence", "4");
				;
				postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
				((Element) postSubChildNode).setAttribute("sequence", "5");
				postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
				((Element) postSubChildNode).setAttribute("sequence", "6");
				postSubChildNode = subChildNode.appendChild(doc.createElement("CityName"));
				postSubChildNode.setTextContent(poheader.getLines().get(k).getLocations().get(1).getCityName());
				postSubChildNode = subChildNode.appendChild(doc.createElement("CountrySubDivisionCode"));
				postSubChildNode
						.setTextContent(poheader.getLines().get(k).getLocations().get(1).getCountrySubDivisionCode());
				postSubChildNode = subChildNode.appendChild(doc.createElement("CountryCode"));
				postSubChildNode.setTextContent(poheader.getLines().get(k).getLocations().get(1).getCountryCode());
				postSubChildNode = subChildNode.appendChild(doc.createElement("PostalCode"));
				postSubChildNode.setTextContent(poheader.getLines().get(k).getLocations().get(1).getPostalCode());
				subChildNode = childNode.appendChild(doc.createElement("Address"));
				((Element) subChildNode).setAttribute("type",
						poheader.getLines().get(k).getLocations().get(1).getAddressType());
				postSubChildNode = subChildNode.appendChild(doc.createElement("AttentionOfName"));
				postSubChildNode.setTextContent(poheader.getLines().get(k).getLocations().get(1).getAttentionOfName());
				postSubChildNode = subChildNode.appendChild(doc.createElement("BuildingNumber"));
				postSubChildNode.setTextContent(
						new Integer(poheader.getLines().get(k).getLocations().get(1).getBuildingNumber()).toString());
				subChildNode.appendChild(doc.createElement("BuildingName"));
				postSubChildNode = subChildNode.appendChild(doc.createElement("StreetName"));
				postSubChildNode.setTextContent(poheader.getLines().get(k).getLocations().get(1).getStreetName());
				subChildNode.appendChild(doc.createElement("Unit"));
				subChildNode.appendChild(doc.createElement("Floor"));
				subChildNode.appendChild(doc.createElement("PostOfficeBox"));
				postSubChildNode = subChildNode.appendChild(doc.createElement("CityName"));
				postSubChildNode.setTextContent(poheader.getLines().get(k).getLocations().get(1).getCityName());
				postSubChildNode = subChildNode.appendChild(doc.createElement("CountrySubDivisionCode"));
				postSubChildNode
						.setTextContent(poheader.getLines().get(k).getLocations().get(1).getCountrySubDivisionCode());
				postSubChildNode = subChildNode.appendChild(doc.createElement("CountryCode"));
				postSubChildNode.setTextContent(poheader.getLines().get(k).getLocations().get(1).getCountryCode());
				postSubChildNode = subChildNode.appendChild(doc.createElement("PostalCode"));
				postSubChildNode.setTextContent(poheader.getLines().get(k).getLocations().get(1).getPostalCode());
				node = rootNode.appendChild(doc.createElement("BackOrderedQuantity"));
				((Element) node).setAttribute("unitcode", poheader.getLines().get(k).getUnitCode());
				node.setTextContent(new Integer(poheader.getLines().get(k).getBackOrderedQuantity()).toString());
				node = rootNode.appendChild(doc.createElement("BackOrderedBaseUOMQuantity"));
				((Element) node).setAttribute("unitcode", poheader.getLines().get(k).getUnitCode());
				node.setTextContent(new Integer(poheader.getLines().get(k).getBackOrderedBaseUomQuantity()).toString());
				node = rootNode.appendChild(doc.createElement("ReceivedQuantity"));
				((Element) node).setAttribute("unitcode", poheader.getLines().get(k).getUnitCode());
				node.setTextContent(new Integer(poheader.getLines().get(k).getReceivedQuantity()).toString());
				node = rootNode.appendChild(doc.createElement("ReceivedBaseUOMQuantity"));
				((Element) node).setAttribute("unitcode", poheader.getLines().get(k).getUnitCode());
				node.setTextContent(new Integer(poheader.getLines().get(k).getReceivedBaseUomQuantity()).toString());
				node = rootNode.appendChild(doc.createElement("OpenQuantity"));
				((Element) node).setAttribute("unitcode", poheader.getLines().get(k).getUnitCode());
				node.setTextContent(new Integer(poheader.getLines().get(k).getOpenQuantity()).toString());
				node = rootNode.appendChild(doc.createElement("OpenBaseUOMQuantity"));
				((Element) node).setAttribute("unitcode", poheader.getLines().get(k).getUnitCode());
				node.setTextContent(new Integer(poheader.getLines().get(k).getOpenBaseUomQuantity()).toString());

				// purchase order schedule
				for (int i = 0; i < poheader.getLines().get(k).getSchedules().size(); i++) {
					node = rootNode.appendChild(doc.createElement("PurchaseOrderSchedule"));
					childNode = node.appendChild(doc.createElement("LineNumber"));

					childNode.setTextContent(
							new Integer(poheader.getLines().get(k).getSchedules().get(i).getLineNumber()).toString());
					childNode = node.appendChild(doc.createElement("Quantity"));
					((Element) childNode).setAttribute("unitcode", poheader.getLines().get(k).getUnitCode());
					childNode.setTextContent(
							new Integer(poheader.getLines().get(k).getSchedules().get(i).getQuantity()).toString());
					childNode = node.appendChild(doc.createElement("BaseUOMQuantity"));
					((Element) childNode).setAttribute("unitcode", poheader.getLines().get(k).getUnitCode());
					childNode.setTextContent(
							new Integer(poheader.getLines().get(k).getSchedules().get(i).getBaseUomQuantity())
									.toString());
					childNode = node.appendChild(doc.createElement("RequiredDeliveryDateTime"));
					childNode.setTextContent(
							poheader.getLines().get(k).getSchedules().get(i).getRequiredDeliveryDatetime());
					poheader.getLines().get(k).getSchedules().get(i).getPoScheduleNumber();
					childNode = node.appendChild(doc.createElement("Status"));
					subChildNode = childNode.appendChild(doc.createElement("code"));
					subChildNode.setTextContent(
							poheader.getLines().get(k).getSchedules().get(i).getStatus().getStatusCode());
					subChildNode = childNode.appendChild(doc.createElement("EffectiveDateTime"));
					subChildNode.setTextContent(
							poheader.getLines().get(k).getSchedules().get(i).getStatus().getEffectiveDatetime());
					subChildNode = childNode.appendChild(doc.createElement("ArchiveIndicator"));
					subChildNode.setTextContent(new Boolean(
							poheader.getLines().get(k).getSchedules().get(i).getStatus().isArchiveIndicator())
									.toString());
					childNode = node.appendChild(doc.createElement("ShipToParty"));
					subChildNode = childNode.appendChild(doc.createElement("Location"));
					((Element) subChildNode).setAttribute("type",
							poheader.getLines().get(k).getSchedules().get(i).getLocation().getLocationType());
					postSubChildNode = subChildNode.appendChild(doc.createElement("ID"));
					((Element) postSubChildNode).setAttribute("accountingEntity", poheader.getAccountingEntity());
					((Element) postSubChildNode).setAttribute("lid", poheader.getLogicalId());
					postSubChildNode.setTextContent(
							poheader.getLines().get(k).getSchedules().get(i).getLocation().getLocationId());
					postSubChildNode = subChildNode.appendChild(doc.createElement("Name"));
					postSubChildNode.setTextContent(
							poheader.getLines().get(k).getSchedules().get(i).getLocation().getLocationName());
					postSubChildNode = subChildNode.appendChild(doc.createElement("Address"));
					((Element) postSubChildNode).setAttribute("type",
							poheader.getLines().get(k).getSchedules().get(i).getLocation().getAddressType());
					postSubChildNode = subChildNode.appendChild(doc.createElement("Address"));
					((Element) postSubChildNode).setAttribute("type",
							poheader.getLines().get(k).getSchedules().get(i).getLocation().getAddressType());
					childNode = node.appendChild(doc.createElement("BackOrderedQuantity"));
					((Element) childNode).setAttribute("unitcode", poheader.getLines().get(k).getUnitCode());
					childNode.setTextContent(
							new Integer(poheader.getLines().get(k).getSchedules().get(i).getBackOrderedQuantity())
									.toString());
					childNode = node.appendChild(doc.createElement("BackOrderedBaseUOMQuantity"));
					((Element) childNode).setAttribute("unitcode", poheader.getLines().get(k).getUnitCode());
					childNode.setTextContent(new Integer(
							poheader.getLines().get(k).getSchedules().get(i).getBackOrderedBaseUomQuantity())
									.toString());
					childNode = node.appendChild(doc.createElement("ReceivedQuantity"));
					((Element) childNode).setAttribute("unitcode", poheader.getLines().get(k).getUnitCode());
					childNode.setTextContent(
							new Integer(poheader.getLines().get(k).getSchedules().get(i).getReceivedQuantity())
									.toString());
					childNode = node.appendChild(doc.createElement("ReceivedBaseUOMQuantity"));
					((Element) childNode).setAttribute("unitcode", poheader.getLines().get(k).getUnitCode());
					childNode.setTextContent(
							new Integer(poheader.getLines().get(k).getSchedules().get(i).getReceivedBaseUomQuantity())
									.toString());
					childNode = node.appendChild(doc.createElement("ScheduleLineType"));
					childNode.setTextContent(poheader.getLines().get(k).getSchedules().get(i).getScheduleLineType());
				}
				node = rootNode.appendChild(doc.createElement("ContractApplicableIndicator"));
				node.setTextContent(new Boolean(poheader.getLines().get(k).isContractApplicableIndicator()).toString());
				node = rootNode.appendChild(doc.createElement("UnitQuantityConversionFactor"));
				node.setTextContent(
						new Integer(poheader.getLines().get(k).getUnitQuantityConversionFactor()).toString());
				node = rootNode.appendChild(doc.createElement("ShipFromParty"));
				childNode = doc.createElement("PartyIDs");
				subChildNode = childNode.appendChild(doc.createElement("ID"));
				subChildNode.setTextContent(poheader.getLines().get(k).getShipFromPartyId());
				node.appendChild(childNode);
				((Element) subChildNode).setAttribute("AccountingEntity", poheader.getAccountingEntity());
				((Element) subChildNode).setAttribute("lid", poheader.getLogicalId());
				childNode = node.appendChild(doc.createElement("Name"));
				childNode.setTextContent(poheader.getLines().get(k).getShipFromPartyName());
				childNode = node.appendChild(doc.createElement("Location"));
				subChildNode = childNode.appendChild(doc.createElement("Address"));
				((Element) subChildNode).setAttribute("type",
						poheader.getLines().get(k).getLocations().get(4).getAddressType());
				postSubChildNode = subChildNode.appendChild(doc.createElement("AttentionOfName"));
				postSubChildNode.setTextContent(poheader.getLines().get(k).getLocations().get(4).getAttentionOfName());
				postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
				((Element) postSubChildNode).setAttribute("sequence", "1");
				postSubChildNode
						.setTextContent(poheader.getLines().get(k).getLocations().get(4).getAddressLineSequence1());
				postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
				((Element) postSubChildNode).setAttribute("sequence", "2");
				postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
				((Element) postSubChildNode).setAttribute("sequence", "3");
				postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
				((Element) postSubChildNode).setAttribute("sequence", "4");
				;
				postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
				((Element) postSubChildNode).setAttribute("sequence", "5");
				postSubChildNode = subChildNode.appendChild(doc.createElement("AddressLine"));
				((Element) postSubChildNode).setAttribute("sequence", "6");
				postSubChildNode = subChildNode.appendChild(doc.createElement("CityName"));
				postSubChildNode.setTextContent(poheader.getLines().get(k).getLocations().get(4).getCityName());
				postSubChildNode = subChildNode.appendChild(doc.createElement("CountrySubDivisionCode"));
				postSubChildNode
						.setTextContent(poheader.getLines().get(k).getLocations().get(4).getCountrySubDivisionCode());
				postSubChildNode = subChildNode.appendChild(doc.createElement("CountryCode"));
				postSubChildNode.setTextContent(poheader.getLines().get(k).getLocations().get(4).getCountryCode());
				postSubChildNode = subChildNode.appendChild(doc.createElement("PostalCode"));
				postSubChildNode.setTextContent(poheader.getLines().get(k).getLocations().get(4).getPostalCode());
				subChildNode = childNode.appendChild(doc.createElement("Address"));
				((Element) subChildNode).setAttribute("type",
						poheader.getLines().get(k).getLocations().get(5).getAddressType());
				postSubChildNode = subChildNode.appendChild(doc.createElement("AttentionOfName"));
				postSubChildNode.setTextContent(poheader.getLines().get(k).getLocations().get(5).getAttentionOfName());
				postSubChildNode = subChildNode.appendChild(doc.createElement("BuildingNumber"));
				postSubChildNode.setTextContent(
						new Integer(poheader.getLines().get(k).getLocations().get(5).getBuildingNumber()).toString());
				subChildNode.appendChild(doc.createElement("BuildingName"));
				postSubChildNode = subChildNode.appendChild(doc.createElement("StreetName"));
				postSubChildNode.setTextContent(poheader.getLines().get(k).getLocations().get(5).getStreetName());
				subChildNode.appendChild(doc.createElement("Unit"));
				subChildNode.appendChild(doc.createElement("Floor"));
				subChildNode.appendChild(doc.createElement("PostOfficeBox"));
				postSubChildNode = subChildNode.appendChild(doc.createElement("CityName"));
				postSubChildNode.setTextContent(poheader.getLines().get(k).getLocations().get(5).getCityName());
				postSubChildNode = subChildNode.appendChild(doc.createElement("CountrySubDivisionCode"));
				postSubChildNode
						.setTextContent(poheader.getLines().get(k).getLocations().get(5).getCountrySubDivisionCode());
				postSubChildNode = subChildNode.appendChild(doc.createElement("CountryCode"));
				postSubChildNode.setTextContent(poheader.getLines().get(k).getLocations().get(5).getCountryCode());
				postSubChildNode = subChildNode.appendChild(doc.createElement("PostalCode"));
				postSubChildNode.setTextContent(poheader.getLines().get(k).getLocations().get(5).getPostalCode());
				node = rootNode.appendChild(doc.createElement("BaseCurrencyAmount"));
				((Element) node).setAttribute("type", poheader.getLines().get(k).getBaseCurrencyAmountType());
				childNode = node.appendChild(doc.createElement("Amount"));
				((Element) childNode).setAttribute("currencyID", poheader.getLines().get(k).getBaseAmountCurrencyId());
				childNode.setTextContent(new Long(poheader.getLines().get(k).getAmount()).toString());
				node = rootNode.appendChild(doc.createElement("DropShipIndicator"));
				node.setTextContent(new Boolean(poheader.getLines().get(k).isDropShipIndicator()).toString());

			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("C:\\dev\\eclispeMarsWorkSpace\\DomParser\\output.xml"));
			transformer.transform(source, result);

		} catch (Exception e) {
			logger.error("exception in pojos to xml class ");
		}
	}

}
