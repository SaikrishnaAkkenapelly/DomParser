/**
 * 
 */
package domparser.utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import domparser.dbcalls.PojoInsertion;
import domparser.pojos.Code;
import domparser.pojos.Location;
import domparser.pojos.PoHeader;
import domparser.pojos.PoLine;
import domparser.pojos.PoSchedule;
import domparser.pojos.Status;

/**
 * @author sakkenapelly
 *
 */
public class XmlReader {

	NodeList nodesList = null;
	NodeList childNodes = null;
	Node node = null;
	Status status = null;
	List<Location> locations = null;
	Location location = null;
	Node subChildNode = null;
	PojoInsertion pojoInsertions = new PojoInsertion();
	XmlReader xmlReader=null;
	PoHeader poHeader = null;
	static final Logger logger = Logger.getLogger(XmlReader.class);
	public void readXml(File inputFile) {

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			poHeader = new PoHeader();
			List<Code> codesList = new ArrayList<>();
			Code code = null;
			NamedNodeMap attributesList = null;
			xmlReader = new XmlReader();
			
			/* document id accounting entity ,location , lid , variation id */
			
			logger.info("document id accounting entity ,location , lid , variation id");
			nodesList = doc.getElementsByTagName("DocumentID");
			childNodes = nodesList.item(0).getChildNodes();
			for (int position = 0; position < childNodes.getLength(); position++) {
				if (childNodes.item(position).getNodeName().equalsIgnoreCase("ID")) {
					poHeader.setDocumentId(childNodes.item(position).getTextContent());
					attributesList = childNodes.item(position).getAttributes();
					for (int index = 0; index < attributesList.getLength(); index++) {
						node = attributesList.item(index);
						if (node.getNodeName().equalsIgnoreCase("accountingEntity")) {
							poHeader.setAccountingEntity(node.getTextContent());
						} else if (node.getNodeName().equalsIgnoreCase("location")) {
							poHeader.setLocationName(node.getTextContent());
						} else if (node.getNodeName().equalsIgnoreCase("lid")) {
							poHeader.setLogicalId(node.getTextContent());

						} else if (node.getNodeName().equalsIgnoreCase("variationID")) {
							poHeader.setVariationId(Integer.parseInt(node.getTextContent()));
						}

					}
				}
			}

			/* alternate document id */
			
			logger.info("alternate document id");
			nodesList = doc.getElementsByTagName("AlternateDocumentID");
			childNodes = nodesList.item(0).getChildNodes();
			for (int position = 0; position < childNodes.getLength(); position++) {
				if (childNodes.item(position).getNodeName().equalsIgnoreCase("ID")) {
					poHeader.setAlternateDocumentId(childNodes.item(position).getTextContent());
				}
			}

			
			/* display id */
			
			logger.info("display id");
			nodesList = doc.getElementsByTagName("DisplayID");
			poHeader.setDisplayId(nodesList.item(0).getTextContent());

			/* LastModificationDateTime */

			logger.info("LastModificationDateTime");
			nodesList = doc.getElementsByTagName("LastModificationDateTime");
			poHeader.setLastModificationDatetime(nodesList.item(0).getTextContent());

			/* LastModificationPersonName , LastModificationPersonID */

			logger.info("LastModificationPersonName , LastModificationPersonID");
			nodesList = doc.getElementsByTagName("LastModificationPerson");
			childNodes = nodesList.item(0).getChildNodes();

			for (int position = 0; position < childNodes.getLength(); position++) {
				if (childNodes.item(position).getNodeName().equalsIgnoreCase("IDs")) {
					childNodes.item(position).getChildNodes();
					for (int index = 0; index < childNodes.item(position).getChildNodes().getLength(); index++) {
						if (childNodes.item(position).getChildNodes().item(index).getNodeName()
								.equalsIgnoreCase("ID")) {
							poHeader.setLastModificationPersonId(Integer
									.parseInt(childNodes.item(position).getChildNodes().item(index).getTextContent()));
							break;
						}
					}

				} else if (childNodes.item(position).getNodeName().equalsIgnoreCase("Name")) {
					poHeader.setLastModificationPersonName((childNodes.item(position).getTextContent()));
				}
			}

			/* DocumentDateTime */

			logger.info("DocumentDateTime");
			nodesList = doc.getElementsByTagName("DocumentDateTime");
			poHeader.setDocumentDatetime(nodesList.item(0).getTextContent());

			/* status */

			logger.info("status");
			status = new Status();
			nodesList = doc.getElementsByTagName("Status");
			childNodes = nodesList.item(0).getChildNodes();
			status = xmlReader.getStatusPojo(childNodes);

			/* CustomerParty */

			logger.info("CustomerParty");
			nodesList = doc.getElementsByTagName("CustomerParty");
			childNodes = nodesList.item(0).getChildNodes();
			location = new Location();
			locations = new ArrayList<Location>();
			for (int index = 0; index < childNodes.getLength(); index++) {
				if (childNodes.item(index).getNodeName().equalsIgnoreCase("Location")) {
					attributesList = childNodes.item(index).getAttributes();
					location.setLocationType(attributesList.item(0).getTextContent());
					for (int position = 0; position < childNodes.item(index).getChildNodes().getLength(); position++) {
						if (childNodes.item(index).getChildNodes().item(position).getNodeName()
								.equalsIgnoreCase("ID")) {
							location.setLocationId(
									childNodes.item(index).getChildNodes().item(position).getTextContent());
						} else if (childNodes.item(index).getChildNodes().item(position).getNodeName()
								.equalsIgnoreCase("Name")) {
							location.setLocationName(
									childNodes.item(index).getChildNodes().item(position).getTextContent());
						}
					}
				}
			}
			location.setIsCustomerParty(true);
			locations.add(location);

			/* supplier party */

			logger.info("supplier party");
			location = new Location();
			nodesList = doc.getElementsByTagName("SupplierParty");
			childNodes = nodesList.item(0).getChildNodes();
			for (int index = 0; index < childNodes.getLength(); index++) {
				if (childNodes.item(index).getNodeName().equalsIgnoreCase("PartyIDs")) {
					for (int position = 0; position < childNodes.item(index).getChildNodes().getLength(); position++) {
						if (childNodes.item(index).getChildNodes().item(position).getNodeName()
								.equalsIgnoreCase("ID")) {
							poHeader.setSupplierPartyId(
									childNodes.item(index).getChildNodes().item(position).getTextContent());
						}
					}

				} else if (childNodes.item(index).getNodeName().equalsIgnoreCase("Name")) {
					poHeader.setSupplierPartyName(childNodes.item(index).getTextContent());
				} else if (childNodes.item(index).getNodeName().equalsIgnoreCase("Location")) {

					for (int position = 0; position < childNodes.item(index).getChildNodes().getLength(); position++) {
						subChildNode = childNodes.item(index).getChildNodes().item(position);
						if (subChildNode.getNodeName().equalsIgnoreCase("Address")) {
							location.setAddressType(subChildNode.getAttributes().item(0).getTextContent());
							location = xmlReader.getUpdatedLocationPojo(location, subChildNode);
							location.setIsSupplierParty(true);
							locations.add(location);
							location = new Location();
						}

					}
				}
			}

			/* ship to party */

			logger.info("ship to party");
			location = new Location();
			nodesList = doc.getElementsByTagName("ShipToParty");
			childNodes = nodesList.item(0).getChildNodes();

			for (int index = 0; index < childNodes.getLength(); index++) {
				if (childNodes.item(index).getNodeName().equalsIgnoreCase("Location")) {
					location.setLocationType(childNodes.item(index).getAttributes().item(0).getTextContent());
					for (int position = 0; position < childNodes.item(index).getChildNodes().getLength(); position++) {
						subChildNode = childNodes.item(index).getChildNodes().item(position);
						if (subChildNode.getNodeName().equalsIgnoreCase("ID")) {
							location.setLocationId(subChildNode.getTextContent());
						} else if (subChildNode.getNodeName().equalsIgnoreCase("Name")) {
							location.setLocationName(subChildNode.getTextContent());
						} else if (subChildNode.getNodeName().equalsIgnoreCase("Address")) {
							location.setAddressType(subChildNode.getAttributes().item(0).getTextContent());
							location=xmlReader.getUpdatedLocationPojo(location, subChildNode);
							location.setIsShipToParty((true));
							locations.add(location);
							location = new Location();
						}
					}

				}
			}

			/* ship from party header */

			logger.info("ship from party header");
			location = new Location();
			nodesList = doc.getElementsByTagName("ShipFromParty");
			childNodes = nodesList.item(0).getChildNodes();
			for (int index = 0; index < childNodes.getLength(); index++) {
				if (childNodes.item(index).getNodeName().equalsIgnoreCase("PartyIDs")) {

					for (int position = 0; position < childNodes.item(index).getChildNodes().getLength(); position++) {
						if (childNodes.item(index).getChildNodes().item(position).getNodeName()
								.equalsIgnoreCase("ID")) {
							poHeader.setShipFromPartyId(
									childNodes.item(index).getChildNodes().item(position).getTextContent());
						}
					}

				} else if (childNodes.item(index).getNodeName().equalsIgnoreCase("Name")) {
					poHeader.setShipFromPartyName((childNodes.item(index).getTextContent()));
				} else if (childNodes.item(index).getNodeName().equalsIgnoreCase("Location")) {
					for (int position = 0; position < childNodes.item(index).getChildNodes().getLength(); position++) {
						subChildNode = childNodes.item(index).getChildNodes().item(position);
						if (subChildNode.getNodeName().equalsIgnoreCase("Address")) {
							location.setAddressType(subChildNode.getAttributes().item(0).getTextContent());
							location = xmlReader.getUpdatedLocationPojo(location, subChildNode);
							location.setIsShipFromParty(true);
							locations.add(location);
							location = new Location();
						}

					}
				}
			}

			/* extended amount */

			logger.info("extended amount");
			nodesList = doc.getElementsByTagName("ExtendedAmount");
			poHeader.setExtendedAmountCurrencyId(nodesList.item(0).getAttributes().item(0).getTextContent());
			poHeader.setExtendedAmount(Long.parseLong(nodesList.item(0).getTextContent()));

			/* extended base amount */

			logger.info("extended base amount");
			nodesList = doc.getElementsByTagName("ExtendedBaseAmount");
			poHeader.setExtendedBaseAmountCurrencyId(nodesList.item(0).getAttributes().item(0).getTextContent());
			poHeader.setExtendedBaseAmount(Long.parseLong(nodesList.item(0).getTextContent()));

			/* ExtendedReportAmount */

			logger.info("ExtendedReportAmount");
			nodesList = doc.getElementsByTagName("ExtendedReportAmount");
			poHeader.setExtendedReportAmount(Long.parseLong(nodesList.item(0).getTextContent()));
			poHeader.setExtendedReportAmountCurrencyId(nodesList.item(0).getAttributes().item(0).getTextContent());

			/* CanceledAmount */

			logger.info("CanceledAmount");
			nodesList = doc.getElementsByTagName("CanceledAmount");
			poHeader.setCanceledAmount(Long.parseLong(nodesList.item(0).getTextContent()));

			/* CanceledBaseAmount */

			logger.info("CanceledBaseAmount");
			nodesList = doc.getElementsByTagName("CanceledBaseAmount");
			poHeader.setCanceledBaseAmount(Long.parseLong(nodesList.item(0).getTextContent()));

			/* CanceledReportingAmount */

			logger.info("CanceledReportingAmount");
			nodesList = doc.getElementsByTagName("CanceledReportingAmount");
			poHeader.setCanceledReportingAmount(Long.parseLong(nodesList.item(0).getTextContent()));

			/* payment term */

			logger.info("payment term");
			nodesList = doc.getElementsByTagName("PaymentTerm");
			poHeader.setPaymentTermType(nodesList.item(0).getAttributes().item(0).getTextContent());
			childNodes = nodesList.item(0).getChildNodes();
			for (int index = 0; index < childNodes.getLength(); index++) {
				if (childNodes.item(index).getNodeName().equalsIgnoreCase("Description")) {
					poHeader.setPaymentTermDescription(childNodes.item(index).getTextContent());
				} else if (childNodes.item(index).getNodeName().equalsIgnoreCase("PaymentTermCode")) {
					poHeader.setPaymentTermCode(childNodes.item(index).getTextContent());
					poHeader.setPaymentTermListId(childNodes.item(index).getAttributes().item(0).getTextContent());
				} else if (childNodes.item(index).getNodeName().equalsIgnoreCase("IDs")) {
					for (int pos = 0; pos < childNodes.item(index).getChildNodes().getLength(); pos++) {
						subChildNode = childNodes.item(index).getChildNodes().item(pos);
						if (subChildNode.getNodeName().equals("ID")) {
							poHeader.setPaymentTermId(subChildNode.getTextContent());
						}
					}

				}
			}

			/* order date time */

			logger.info("order date time");
			nodesList = doc.getElementsByTagName("OrderDateTime");
			poHeader.setOrderDatetime(nodesList.item(0).getTextContent());

			/* UserArea */

			logger.info("UserArea");
			nodesList = doc.getElementsByTagName("UserArea");
			childNodes = nodesList.item(0).getChildNodes();
			for (int index = 0; index < childNodes.getLength(); index++) {
				if (childNodes.item(index).getNodeName().equalsIgnoreCase("Property")) {
					for (int pos = 0; pos < childNodes.item(index).getChildNodes().getLength(); pos++) {
						subChildNode = childNodes.item(index).getChildNodes().item(pos);
						if (subChildNode.getNodeName().equals("NameValue")) {
							poHeader.setUserAreaPropertyameValue(subChildNode.getTextContent());
							poHeader.setUserAreaPropertyName(subChildNode.getAttributes().item(0).getTextContent());

							poHeader.setUserAreaPropertyType(subChildNode.getAttributes().item(1).getTextContent());
						}
					}
				}
			}

			/* classification code */

			logger.info("classification code");
			nodesList = doc.getElementsByTagName("Classification");
			childNodes = nodesList.item(0).getChildNodes();
			for (int index = 0; index < childNodes.getLength(); index++) {
				if (childNodes.item(index).getNodeName().equalsIgnoreCase("Codes")) {
					for (int pos = 0; pos < childNodes.item(index).getChildNodes().getLength(); pos++) {
						code = new Code();
						subChildNode = childNodes.item(index).getChildNodes().item(pos);
						if (subChildNode.getNodeName().equalsIgnoreCase("Code")) {
							code.setCode(subChildNode.getTextContent());
							for (int attribute = 0; attribute < subChildNode.getAttributes().getLength(); attribute++) {
								Node attributeNode = subChildNode.getAttributes().item(attribute);
								if (attributeNode.getNodeName().equals("listID")) {
									code.setListId(attributeNode.getTextContent());
								} else if (attributeNode.getNodeName().equals("sequence")) {
									code.setSequence(Long.parseLong(attributeNode.getTextContent()));
								}
							}
							codesList.add(code);
						}
					}
				}
			}

			/* DocumentUsageCode */

			logger.info("DocumentUsageCode");
			nodesList = doc.getElementsByTagName("DocumentUsageCode");
			poHeader.setDocumentUsageCode(nodesList.item(0).getTextContent());

			/* BaseCurrencyAmount */

			logger.info("BaseCurrencyAmount");
			nodesList = doc.getElementsByTagName("BaseCurrencyAmount");
			poHeader.setBaseCurrencyAmountType(nodesList.item(0).getAttributes().item(0).getTextContent());
			childNodes = nodesList.item(0).getChildNodes();
			for (int index = 0; index < childNodes.getLength(); index++) {
				if (childNodes.item(index).getNodeName().equalsIgnoreCase("Amount")) {
					poHeader.setBaseCurrencyAmountId(childNodes.item(index).getAttributes().item(0).getTextContent());
					poHeader.setBaseCurrencyAmount(Long.parseLong(childNodes.item(index).getTextContent()));
				}
			}

			// inserting header
			
			logger.info("inserting header");
			poHeader.setId(pojoInsertions.insertPoHeaderPojo(poHeader));

			// inserting status
			logger.info("inserting status");
			status.setHeaderId(poHeader.getId());
			status.setId(pojoInsertions.insertStatusPojo(status));
			poHeader.setStatus(status);

			// inserting locations
			logger.info("inserting locations");
			for (Location loc : locations) {
				loc.setPoHeaderId(poHeader.getId());
				loc.setId(pojoInsertions.insertLocationsPojo(loc));
			}
			poHeader.setLocations(locations);

			// inserting codes
			logger.info("inserting codes");
			for (Code codeObject : codesList) {
				codeObject.setHeaderNumber(poHeader.getId());
				codeObject.setId(pojoInsertions.insertCodesPojo(codeObject));
			}
			poHeader.setCodes(codesList);

			// calling line method to insert lines
			logger.info(" calling line method to insert lines");
			xmlReader.readXmlLine(doc, poHeader);

		} catch (SAXException | IOException | ParserConfigurationException e) {
			logger.error("exception raised in xml reader while parsing header object");
		}

	}

	public PoHeader getPoHeader() {
		return poHeader;
	}

	public void readXmlLine(Document doc, PoHeader poHeader) {

		nodesList = doc.getElementsByTagName("PurchaseOrderLine");
		List<PoLine> lines = new ArrayList<>();
		xmlReader = new XmlReader();
		for (int line = 0; line < nodesList.getLength(); line++) {
			PoLine poLine = new PoLine();
			List<PoSchedule> scheduleList = new ArrayList<>();
			List<Status> statusList = new ArrayList<>();
			childNodes = nodesList.item(line).getChildNodes();

			locations = new ArrayList<>();
			for (int subNode = 0; subNode < childNodes.getLength(); subNode++) {

				if (childNodes.item(subNode).getNodeType() == Node.ELEMENT_NODE) {

					if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("LineNumber")) {
						poLine.setLineNumber(Integer.parseInt(childNodes.item(subNode).getTextContent()));
					}

					else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("Note")) {
						poLine.setNote(childNodes.item(subNode).getTextContent());
					}

					else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("Status")) {
						status = new Status();
						NodeList statusChildNodes = childNodes.item(subNode).getChildNodes();
						status = xmlReader.getStatusPojo(statusChildNodes);
						poLine.setStatus(status);
						statusList.add(status);

					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("Item")) {

						NodeList itemChildNodes = childNodes.item(subNode).getChildNodes();

						for (int position = 0; position < itemChildNodes.getLength(); position++) {

							if (itemChildNodes.item(position).getNodeType() == Node.ELEMENT_NODE) {

								if (itemChildNodes.item(position).getNodeName().equalsIgnoreCase("ItemID")) {

									NodeList itemIdChildNodes = itemChildNodes.item(position).getChildNodes();

									for (int index = 0; index < itemIdChildNodes.getLength(); index++) {

										if (itemIdChildNodes.item(index).getNodeType() == Node.ELEMENT_NODE) {
											if (itemIdChildNodes.item(index).getNodeName().equalsIgnoreCase("ID")) {
												poLine.setItemId(itemIdChildNodes.item(index).getTextContent());

											}
										}
									}

								} else if (itemChildNodes.item(position).getNodeName()
										.equalsIgnoreCase("ServiceIndicator")) {

									if (itemChildNodes.item(position).getTextContent().equalsIgnoreCase("true")) {
										poLine.setServiceIndicator(true);
									} else {
										poLine.setServiceIndicator(false);
									}

								} else
									if (itemChildNodes.item(position).getNodeName().equalsIgnoreCase("Description")) {
									poLine.setDescription(itemChildNodes.item(position).getTextContent());

								} else if (itemChildNodes.item(position).getNodeName().equalsIgnoreCase("Note")) {
									poLine.setItemNote(itemChildNodes.item(position).getTextContent());

								} else
									if (itemChildNodes.item(position).getNodeName().equalsIgnoreCase("SerializedLot")) {
									NodeList serializedLotChildNodes = itemChildNodes.item(position).getChildNodes();

									for (int index = 0; index < serializedLotChildNodes.getLength(); index++) {
										if (serializedLotChildNodes.item(index).getNodeType() == Node.ELEMENT_NODE) {
											if (serializedLotChildNodes.item(index).getNodeName()
													.equalsIgnoreCase("LotSelection")) {
												poLine.setSerializedLotSelection(
														serializedLotChildNodes.item(index).getTextContent());
											}
										}
									}
								}
							}
						}
					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("Quantity")) {
						poLine.setQuantity(Integer.parseInt(childNodes.item(subNode).getTextContent()));
						poLine.setUnitCode(childNodes.item(subNode).getAttributes().item(0).getTextContent());

					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("BaseUOMQuantity")) {
						poLine.setBaseUomQuantity(Integer.parseInt(childNodes.item(subNode).getTextContent()));
					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("UnitPrice")) {

						NodeList untiPriceChildNodes = childNodes.item(subNode).getChildNodes();
						for (int index = 0; index < untiPriceChildNodes.getLength(); index++) {
							if (untiPriceChildNodes.item(index).getNodeType() == Node.ELEMENT_NODE) {
								if (untiPriceChildNodes.item(index).getNodeName().equalsIgnoreCase("Amount")) {
									poLine.setAmount(Long.parseLong(untiPriceChildNodes.item(index).getTextContent()));
									poLine.setAmountCurrencyId(
											untiPriceChildNodes.item(index).getAttributes().item(0).getTextContent());
								} else
									if (untiPriceChildNodes.item(index).getNodeName().equalsIgnoreCase("BaseAmount")) {

									poLine.setBaseAmount(
											Long.parseLong(untiPriceChildNodes.item(index).getTextContent()));
									poLine.setBaseAmountCurrencyId(
											untiPriceChildNodes.item(index).getAttributes().item(0).getTextContent());

								} else if (untiPriceChildNodes.item(index).getNodeName()
										.equalsIgnoreCase("ReportAmount")) {
									poLine.setReportAmount(
											Float.parseFloat(untiPriceChildNodes.item(index).getTextContent()));
									poLine.setReportAmountCurrencyId(
											(untiPriceChildNodes.item(index).getAttributes().item(0).getTextContent()));
								} else
									if (untiPriceChildNodes.item(index).getNodeName().equalsIgnoreCase("PerQuantity")) {
									poLine.setPerQuantity(
											Integer.parseInt(untiPriceChildNodes.item(index).getTextContent()));

								} else if (untiPriceChildNodes.item(index).getNodeName()
										.equalsIgnoreCase("PerBaseUOMQuantity")) {
									poLine.setPerBaseUomQuantity(
											Integer.parseInt(untiPriceChildNodes.item(index).getTextContent()));
								}
							}
						}

					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("ExtendedAmount")) {
						poLine.setExtendedAmount(Long.parseLong(childNodes.item(subNode).getTextContent()));

					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("ExtendedBaseAmount")) {
						poLine.setExtendedBaseAmount(Long.parseLong(childNodes.item(subNode).getTextContent()));

					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("ExtendedReportAmount")) {
						poLine.setExtendedReportAmount(Long.parseLong(childNodes.item(subNode).getTextContent()));

					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("TotalAmount")) {
						poLine.setTotalAmount(Long.parseLong(childNodes.item(subNode).getTextContent()));

					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("TotalBaseAmount")) {
						poLine.setTotalBaseAmount(Long.parseLong(childNodes.item(subNode).getTextContent()));

					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("TotalReportAmount")) {
						poLine.setTotalReportAmount(Long.parseLong(childNodes.item(subNode).getTextContent()));

					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("RequiredDeliveryDateTime")) {
						poLine.setRequiredDeliveryDatetime(childNodes.item(subNode).getTextContent());

					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("ShipToParty")) {

						location = new Location();
						NodeList nodesChildList = childNodes.item(subNode).getChildNodes();

						for (int index = 0; index < nodesChildList.getLength(); index++) {

							if (nodesChildList.item(index).getNodeType() == Node.ELEMENT_NODE) {

								if (nodesChildList.item(index).getNodeName().equalsIgnoreCase("Location")) {
									location.setLocationType(
											nodesChildList.item(index).getAttributes().item(0).getTextContent());
									for (int position = 0; position < nodesChildList.item(index).getChildNodes()
											.getLength(); position++) {

										subChildNode = nodesChildList.item(index).getChildNodes().item(position);
										if (subChildNode.getNodeType() == Node.ELEMENT_NODE) {

											if (subChildNode.getNodeName().equalsIgnoreCase("ID")) {
												location.setLocationId(subChildNode.getTextContent());
											} else if (subChildNode.getNodeName().equalsIgnoreCase("Name")) {
												location.setLocationName(subChildNode.getTextContent());
											} else if (subChildNode.getNodeName().equalsIgnoreCase("Address")) {
												location.setAddressType(
														subChildNode.getAttributes().item(0).getTextContent());
												location = xmlReader.getUpdatedLocationPojo(location, subChildNode);

											}
										}

									}
								}
							}
						}
						location.setIsShipToParty((true));
						locations.add(location);

					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("BackOrderedQuantity")) {
						poLine.setBackOrderedQuantity(Integer.parseInt(childNodes.item(subNode).getTextContent()));

					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("BackOrderedBaseUOMQuantity")) {
						poLine.setBackOrderedBaseUomQuantity(
								Integer.parseInt(childNodes.item(subNode).getTextContent()));
					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("ReceivedQuantity")) {
						poLine.setReceivedQuantity(Integer.parseInt(childNodes.item(subNode).getTextContent()));

					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("ReceivedBaseUOMQuantity")) {
						poLine.setReceivedBaseUomQuantity(Integer.parseInt(childNodes.item(subNode).getTextContent()));

					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("OpenQuantity")) {
						poLine.setOpenQuantity(Integer.parseInt(childNodes.item(subNode).getTextContent()));

					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("OpenBaseUOMQuantity")) {
						poLine.setOpenBaseUomQuantity(Integer.parseInt(childNodes.item(subNode).getTextContent()));

					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("PurchaseOrderSchedule")) {

						PoSchedule schedule = new PoSchedule();
						NodeList subChildNodes = childNodes.item(subNode).getChildNodes();

						for (int index = 0; index < subChildNodes.getLength(); index++) {
							if (subChildNodes.item(index).getNodeName().equalsIgnoreCase("LineNumber")) {
								schedule.setLineNumber(Integer.parseInt(subChildNodes.item(index).getTextContent()));
							} else if (subChildNodes.item(index).getNodeName().equalsIgnoreCase("Quantity")) {
								schedule.setQuantity(Integer.parseInt(subChildNodes.item(index).getTextContent()));
							} else if (subChildNodes.item(index).getNodeName().equalsIgnoreCase("BaseUOMQuantity")) {
								schedule.setBaseUomQuantity(
										Integer.parseInt(subChildNodes.item(index).getTextContent()));

							} else if (subChildNodes.item(index).getNodeName()
									.equalsIgnoreCase("RequiredDeliveryDateTime")) {
								schedule.setRequiredDeliveryDatetime(subChildNodes.item(index).getTextContent());
							} else if (subChildNodes.item(index).getNodeName().equalsIgnoreCase("Status")) {

								status = new Status();

								NodeList statusNodesList = subChildNodes.item(index).getChildNodes();
								status = xmlReader.getStatusPojo(statusNodesList);
								schedule.setStatus(status);
								statusList.add(status);

							} else if (subChildNodes.item(index).getNodeName().equalsIgnoreCase("ShipToParty")) {

								location = new Location();
								NodeList shipToNodesList = subChildNodes.item(index).getChildNodes();

								for (int pos = 0; pos < shipToNodesList.getLength(); pos++) {

									if (shipToNodesList.item(pos).getNodeName().equalsIgnoreCase("Location")) {
										location.setLocationType(
												shipToNodesList.item(pos).getAttributes().item(0).getTextContent());
										for (int position = 0; position < shipToNodesList.item(pos).getChildNodes()
												.getLength(); position++) {
											subChildNode = shipToNodesList.item(pos).getChildNodes().item(position);
											if (subChildNode.getNodeName().equalsIgnoreCase("ID")) {
												location.setLocationId(subChildNode.getTextContent());
											} else if (subChildNode.getNodeName().equalsIgnoreCase("Name")) {
												location.setLocationName(subChildNode.getTextContent());
											} else if (subChildNode.getNodeName().equalsIgnoreCase("Address")) {
												location.setAddressType(
														subChildNode.getAttributes().item(0).getTextContent());
												location = xmlReader.getUpdatedLocationPojo(location, subChildNode);
												location.setIsPoSchedule(true);
												location.setIsShipToParty(true);
												schedule.setLocation(location);
												locations.add(location);
											}
										}

									}
								}

							} else
								if (subChildNodes.item(index).getNodeName().equalsIgnoreCase("BackOrderedQuantity")) {
								schedule.setBackOrderedQuantity(
										Integer.parseInt(subChildNodes.item(index).getTextContent()));

							} else if (subChildNodes.item(index).getNodeName().equalsIgnoreCase("ReceivedQuantity")) {
								schedule.setReceivedQuantity(
										Integer.parseInt(subChildNodes.item(index).getTextContent()));

							} else if (subChildNodes.item(index).getNodeName()
									.equalsIgnoreCase("ReceivedBaseUOMQuantity")) {
								schedule.setReceivedBaseUomQuantity(
										Integer.parseInt(subChildNodes.item(index).getTextContent()));

							} else if (subChildNodes.item(index).getNodeName().equalsIgnoreCase("ScheduleLineType")) {
								schedule.setScheduleLineType(subChildNodes.item(index).getTextContent());
							}

						}
						scheduleList.add(schedule);
					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("ContractApplicableIndicator")) {
						if (childNodes.item(subNode).getTextContent().equalsIgnoreCase("true")) {
							poLine.setContractApplicableIndicator(true);
						} else {
							poLine.setContractApplicableIndicator(false);
						}
					} else
						if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("UnitQuantityConversionFactor")) {
						poLine.setUnitQuantityConversionFactor(
								Integer.parseInt(childNodes.item(subNode).getTextContent()));

					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("ShipFromParty")) {

						location = new Location();
						NodeList nodesChildList = childNodes.item(subNode).getChildNodes();

						for (int index = 0; index < nodesChildList.getLength(); index++) {
							if (nodesChildList.item(index).getNodeName().equalsIgnoreCase("PartyIDs")) {

								for (int position = 0; position < nodesChildList.item(index).getChildNodes()
										.getLength(); position++) {
									if (nodesChildList.item(index).getChildNodes().item(position).getNodeName()
											.equalsIgnoreCase("ID")) {
										poLine.setShipFromPartyId(nodesChildList.item(index).getChildNodes()
												.item(position).getTextContent());
									}
								}

							} else if (nodesChildList.item(index).getNodeName().equalsIgnoreCase("Name")) {
								poLine.setShipFromPartyName((nodesChildList.item(index).getTextContent()));
							} else if (nodesChildList.item(index).getNodeName().equalsIgnoreCase("Location")) {
								for (int position = 0; position < nodesChildList.item(index).getChildNodes()
										.getLength(); position++) {
									subChildNode = nodesChildList.item(index).getChildNodes().item(position);
									if (subChildNode.getNodeName().equalsIgnoreCase("Address")) {
										location.setAddressType(subChildNode.getAttributes().item(0).getTextContent());
										xmlReader.getUpdatedLocationPojo(location, subChildNode);
										location.setIsSupplierParty(true);
										locations.add(location);
										location = new Location();
									}

								}
							}
						}

					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("BaseCurrencyAmount")) {

						poLine.setBaseCurrencyAmountType(
								childNodes.item(subNode).getAttributes().item(0).getTextContent());
						NodeList baseCurrencyAmountChildNodes = childNodes.item(subNode).getChildNodes();
						for (int index = 0; index < baseCurrencyAmountChildNodes.getLength(); index++) {
							if (baseCurrencyAmountChildNodes.item(index).getNodeName().equalsIgnoreCase("Amount")) {
								poLine.setBaseCurrencyAmount(
										Long.parseLong(baseCurrencyAmountChildNodes.item(index).getTextContent()));
								poLine.setBaseCurrencyAmountId(baseCurrencyAmountChildNodes.item(index).getAttributes()
										.item(0).getTextContent());
							}
						}

					} else if (childNodes.item(subNode).getNodeName().equalsIgnoreCase("DropShipIndicator")) {
						if (childNodes.item(subNode).getTextContent().equalsIgnoreCase("true")) {
							poLine.setDropShipIndicator(true);
						} else {
							poLine.setDropShipIndicator(false);
						}
					}
				}

			}

			// line insertion

			poLine.setPoHeaderId(poHeader.getId());
			Long poLineId = pojoInsertions.insertPoLinePojo(poLine);
			poLine.setId(poLineId);

			// schedule insertion

			for (int index = 0; index < scheduleList.size(); index++) {
				scheduleList.get(index).setPolineNumberFk(poLineId);
				long scheduleId = pojoInsertions.insertPoSchedulePojo(scheduleList.get(index));
				scheduleList.get(index).setPoScheduleNumber(scheduleId);
				if (index == 0) {
					statusList.get(1).setScheduleId(scheduleId);
					statusList.get(1).setScheduleId(scheduleId);
				} else if (index == 1) {
					statusList.get(2).setScheduleId(scheduleId);
					statusList.get(2).setScheduleId(scheduleId);
				}
			}
			poLine.setSchedules(scheduleList);

			// location insertion

			int i = 0;
			List<Location> poLineLocations = new ArrayList<>();

			for (Location loc : locations) {
				loc.setPoLineId((poLineId));
				if (loc.isIsPoSchedule() && i < scheduleList.size()) {
					loc.setPoScheduleId(scheduleList.get(i).getPoScheduleNumber());
					i++;
					pojoInsertions.insertLocationsPojo(loc);
				} else {
					pojoInsertions.insertLocationsPojo(loc);
					poLineLocations.add(loc);
				}

			}
			poLine.setLocations(poLineLocations);

			// status insertion

			for (Status statusObject : statusList) {
				statusObject.setLineId(poLineId);
				status.setId(pojoInsertions.insertStatusPojo(statusObject));
			}
			lines.add(poLine);

		}
		poHeader.setLines(lines);
		
		// proof that all objects are linked to the header 
		
		System.out.println(poHeader.getLines().get(0).getSchedules().get(1).getLocation().getLocationType());
	}

	public Status getStatusPojo(NodeList statusChildNodes) {
		Status statusPojo = new Status();
		for (int position = 0; position < statusChildNodes.getLength(); position++) {

			if (statusChildNodes.item(position).getNodeType() == Node.ELEMENT_NODE) {
				Node statusSubNode = statusChildNodes.item(position);
				if (statusSubNode.getNodeName().equalsIgnoreCase("Code")) {
					statusPojo.setStatusCode(statusSubNode.getTextContent());
				} else if (statusSubNode.getNodeName().equalsIgnoreCase("EffectiveDateTime")) {
					statusPojo.setEffectiveDatetime(statusSubNode.getTextContent());
				} else {
					if (statusSubNode.getTextContent().equalsIgnoreCase("true")) {
						statusPojo.setArchiveIndicator(true);
					} else {
						statusPojo.setArchiveIndicator(false);
					}
				}
			}

		}
		return statusPojo;
	}

	public Location getUpdatedLocationPojo(Location location, Node subChildNode) {

		for (int pos = 0; pos < subChildNode.getChildNodes().getLength(); pos++) {
			if (subChildNode.getChildNodes().item(pos).getNodeName().equalsIgnoreCase("AttentionOfName")) {
				location.setAttentionOfName(subChildNode.getChildNodes().item(pos).getTextContent());
			} else if (subChildNode.getChildNodes().item(pos).getNodeName().equalsIgnoreCase("AddressLine")
					&& subChildNode.getChildNodes().item(pos).getAttributes().item(0).getTextContent().equals("1")) {
				location.setAddressLineSequence1(subChildNode.getChildNodes().item(pos).getTextContent());
			} else if (subChildNode.getChildNodes().item(pos).getNodeName().equalsIgnoreCase("CityName")) {
				location.setCityName(subChildNode.getChildNodes().item(pos).getTextContent());
			} else
				if (subChildNode.getChildNodes().item(pos).getNodeName().equalsIgnoreCase("CountrySubDivisionCode")) {
				location.setCountrySubDivisionCode(subChildNode.getChildNodes().item(pos).getTextContent());
			} else if (subChildNode.getChildNodes().item(pos).getNodeName().equalsIgnoreCase("CountryCode")) {
				location.setCountryCode(subChildNode.getChildNodes().item(pos).getTextContent());
			} else if (subChildNode.getChildNodes().item(pos).getNodeName().equalsIgnoreCase("PostalCode")) {
				location.setPostalCode(subChildNode.getChildNodes().item(pos).getTextContent());
			} else if (subChildNode.getChildNodes().item(pos).getNodeName().equalsIgnoreCase("BuildingNumber")) {
				boolean empty =subChildNode.getChildNodes().item(pos).getTextContent().isEmpty();
				if(empty==false)
					location.setBuildingNumber(Integer.parseInt(subChildNode.getChildNodes().item(pos).getTextContent()));
			} else if (subChildNode.getChildNodes().item(pos).getNodeName().equalsIgnoreCase("StreetName")) {
				location.setStreetName(subChildNode.getChildNodes().item(pos).getTextContent());
			}
		}

		return location;
	}

}
