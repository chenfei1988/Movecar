package xinyiyun.chenfei.com.movecar.requestmodel

import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.NamespaceList
import org.simpleframework.xml.Root

/**
 * Created by Administrator on 2018/1/12.
 */

@Root(name = "soap:Envelope")
@NamespaceList(Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"), Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"), Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap"))
class ClientInfoRequestEnvelope(@field:Element(name = "soap:Body", required = true)
					  var body: ClientInfoRequestBody)

@Root(name = "soap:Body", strict = false)
class ClientInfoRequestBody(@field:Element(name = "GetClientInfo", required = true)
				  var model: ClientInfoRequestModel)

@Root(name = "GetClientInfo", strict = false)
@Namespace(reference = "http://kingdonsoft.com/")
class ClientInfoRequestModel(@field:Element(name = "UserID", required = true)
				   var UserID: String, @field:Element(name = "pageIndex", required = true)
				   var pageIndex: Int, @field:Element(name = "pageSize", required = true)
							 var pageSize: Int)


@Root(name = "soap:Envelope")
@NamespaceList(Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"), Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"), Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap"))
class ClientInfoCountRequestEnvelope(@field:Element(name = "soap:Body", required = true)
									 var body: ClientInfoCountRequestBody)

@Root(name = "soap:Body", strict = false)
class ClientInfoCountRequestBody(@field:Element(name = "GetClientInfoCount", required = true)
								 var model: ClientInfoCountRequestModel)

@Root(name = "GetClientInfoCount", strict = false)
@Namespace(reference = "http://kingdonsoft.com/")
class ClientInfoCountRequestModel(@field:Element(name = "UserID", required = true)
								  var UserID: String)


@Root(name = "soap:Envelope")
@NamespaceList(Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"), Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"), Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap"))
class TeamNoInfoRequestEnvelope(@field:Element(name = "soap:Body", required = true)
									 var body: TeamNoInfoRequestBody)

@Root(name = "soap:Body", strict = false)
class TeamNoInfoRequestBody(@field:Element(name = "GetTeamNoInfo", required = true)
								 var model: TeamNoInfoRequestModel)

@Root(name = "GetTeamNoInfo", strict = false)
@Namespace(reference = "http://kingdonsoft.com/")
class TeamNoInfoRequestModel(@field:Element(name = "Companyid", required = true)
								  var Companyid: Int)



@Root(name = "soap:Envelope")
@NamespaceList(Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"), Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"), Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap"))
class VehicleInfoCountRequestEnvelope(@field:Element(name = "soap:Body", required = true)
									 var body: VehicleInfoCountRequestBody)

@Root(name = "soap:Body", strict = false)
class VehicleInfoCountRequestBody(@field:Element(name = "GetvehicleInfoCount", required = true)
								 var model: VehicleInfoCountRequestModel)

@Root(name = "GetvehicleInfoCount", strict = false)
@Namespace(reference = "http://kingdonsoft.com/")
class VehicleInfoCountRequestModel(@field:Element(name = "UserID", required = true)
								  var UserID: String)


@Root(name = "soap:Envelope")
@NamespaceList(Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"), Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"), Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap"))
class VehicleInfoRequestEnvelope(@field:Element(name = "soap:Body", required = true)
								var body: VehicleInfoRequestBody)

@Root(name = "soap:Body", strict = false)
class VehicleInfoRequestBody(@field:Element(name = "GetvehicleInfo", required = true)
							var model: VehicleInfoRequestModel)

@Root(name = "GetvehicleInfo", strict = false)
@Namespace(reference = "http://kingdonsoft.com/")
class VehicleInfoRequestModel(@field:Element(name = "UserID", required = true)
							 var UserID: String, @field:Element(name = "pageIndex", required = true)
							 var pageIndex: Int, @field:Element(name = "pageSize", required = true)
							 var pageSize: Int)


@Root(name = "soap:Envelope")
@NamespaceList(Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"), Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"), Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap"))
class VehicleDateRequestEnvelope(@field:Element(name = "soap:Body", required = true)
								 var body: VehicleDateRequestBody)

@Root(name = "soap:Body", strict = false)
class VehicleDateRequestBody(@field:Element(name = "GetTpVeDate", required = true)
							 var model: VehicleDateRequestModel)

@Root(name = "GetTpVeDate", strict = false)
@Namespace(reference = "http://kingdonsoft.com/")
class VehicleDateRequestModel(@field:Element(name = "UserID", required = true)
							  var UserID: String, @field:Element(name = "pageIndex", required = true)
							  var pageIndex: Int, @field:Element(name = "pageSize", required = true)
							  var pageSize: Int,@field:Element(name = "cph", required = true) var cph: String)


@Root(name = "soap:Envelope")
@NamespaceList(Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"), Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"), Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap"))
class GetTonJiInfoCountRequestEnvelope(@field:Element(name = "soap:Body", required = true)
								 var body: GetTonJiInfoCountRequestBody)

@Root(name = "soap:Body", strict = false)
class GetTonJiInfoCountRequestBody(@field:Element(name = "GetTonJiInfoCount", required = true)
							 var model: GetTonJiInfoCountRequestModel)

@Root(name = "GetTonJiInfoCount", strict = false)
@Namespace(reference = "http://kingdonsoft.com/")
class GetTonJiInfoCountRequestModel(@field:Element(name = "UserID", required = true)
							  var UserID: String, @field:Element(name = "StarTime", required = true)
							  var StarTime: String, @field:Element(name = "EndTime", required = true)
							  var EndTime: String)

@Root(name = "soap:Envelope")
@NamespaceList(Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"), Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"), Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap"))
class GetTonJiInfoRequestEnvelope(@field:Element(name = "soap:Body", required = true)
									   var body: GetTonJiInfoRequestBody)

@Root(name = "soap:Body", strict = false)
class GetTonJiInfoRequestBody(@field:Element(name = "GetTonJiInfo", required = true)
								   var model: GetTonJiInfoRequestModel)

@Root(name = "GetTonJiInfo", strict = false)
@Namespace(reference = "http://kingdonsoft.com/")
class GetTonJiInfoRequestModel(@field:Element(name = "UserID", required = true)
									var UserID: String,@field:Element(name = "pageIndex", required = true)
                                    var pageIndex: Int, @field:Element(name = "pageSize", required = true)
							        var pageSize: Int, @field:Element(name = "StarTime", required = true)
									var StarTime: String, @field:Element(name = "EndTime", required = true)
									var EndTime: String)


@Root(name = "soap:Envelope")
@NamespaceList(Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"), Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"), Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap"))
class GetTonJiTeamNoInfoCountRequestEnvelope(@field:Element(name = "soap:Body", required = true)
									  var body: GetTonJiTeamNoInfoCountRequestBody)

@Root(name = "soap:Body", strict = false)
class GetTonJiTeamNoInfoCountRequestBody(@field:Element(name = "GetTonJiTeamNoInfoCount", required = true)
								  var model: GetTonJiTeamNoInfoCountRequestModel)

@Root(name = "GetTonJiTeamNoInfoCount", strict = false)
@Namespace(reference = "http://kingdonsoft.com/")
class GetTonJiTeamNoInfoCountRequestModel(@field:Element(name = "UserID", required = true)
								   var UserID: String)


@Root(name = "soap:Envelope")
@NamespaceList(Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"), Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"), Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap"))
class GetTonJiTeamNoInfoRequestEnvelope(@field:Element(name = "soap:Body", required = true)
								var body: GetTonJiTeamNoInfoRequestBody)

@Root(name = "soap:Body", strict = false)
class GetTonJiTeamNoInfoRequestBody(@field:Element(name = "GetTonJiTeamNoInfo", required = true)
							var model: GetTonJiTeamNoInfoRequestModel)

@Root(name = "GetTonJiTeamNoInfo", strict = false)
@Namespace(reference = "http://kingdonsoft.com/")
class GetTonJiTeamNoInfoRequestModel(@field:Element(name = "UserID", required = true)
							 var UserID: String, @field:Element(name = "pageIndex", required = true)
							 var pageIndex: Int, @field:Element(name = "pageSize", required = true)
							 var pageSize: Int)



@Root(name = "soap:Envelope")
@NamespaceList(Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"), Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"), Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap"))
class GetTonJiAlarmInfoCountRequestEnvelope(@field:Element(name = "soap:Body", required = true)
									   var body: GetTonJiAlarmInfoCountRequestBody)

@Root(name = "soap:Body", strict = false)
class GetTonJiAlarmInfoCountRequestBody(@field:Element(name = "GetTonJiAlarmInfoCount", required = true)
								   var model: GetTonJiAlarmInfoCountRequestModel)

@Root(name = "GetTonJiAlarmInfoCount", strict = false)
@Namespace(reference = "http://kingdonsoft.com/")
class GetTonJiAlarmInfoCountRequestModel(@field:Element(name = "UserID", required = true)
									var UserID: String, @field:Element(name = "StarTime", required = true)
									var StarTime: String, @field:Element(name = "EndTime", required = true)
									var EndTime: String)

@Root(name = "soap:Envelope")
@NamespaceList(Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"), Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"), Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap"))
class GetTonJiAlarmInfoRequestEnvelope(@field:Element(name = "soap:Body", required = true)
								  var body: GetTonJiAlarmInfoRequestBody)

@Root(name = "soap:Body", strict = false)
class GetTonJiAlarmInfoRequestBody(@field:Element(name = "GetTonJiAlarmInfo", required = true)
							  var model: GetTonJiAlarmInfoRequestModel)

@Root(name = "GetTonJiAlarmInfo", strict = false)
@Namespace(reference = "http://kingdonsoft.com/")
class GetTonJiAlarmInfoRequestModel(@field:Element(name = "UserID", required = true)
							   var UserID: String,@field:Element(name = "pageIndex", required = true)
							   var pageIndex: Int, @field:Element(name = "pageSize", required = true)
							   var pageSize: Int, @field:Element(name = "StarTime", required = true)
							   var StarTime: String, @field:Element(name = "EndTime", required = true)
							   var EndTime: String)


@Root(name = "soap:Envelope")
@NamespaceList(Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"), Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"), Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap"))
class GetTonJiAlarmTypeInfoCountRequestEnvelope(@field:Element(name = "soap:Body", required = true)
											var body: GetTonJiAlarmTypeInfoCountRequestBody)

@Root(name = "soap:Body", strict = false)
class GetTonJiAlarmTypeInfoCountRequestBody(@field:Element(name = "GetTonJiAlarmTypeInfoCount", required = true)
										var model: GetTonJiAlarmTypeInfoCountRequestModel)

@Root(name = "GetTonJiAlarmTypeInfoCount", strict = false)
@Namespace(reference = "http://kingdonsoft.com/")
class GetTonJiAlarmTypeInfoCountRequestModel(@field:Element(name = "UserID", required = true)
										 var UserID: String, @field:Element(name = "StarTime", required = true)
										 var StarTime: String, @field:Element(name = "EndTime", required = true)
										 var EndTime: String)

@Root(name = "soap:Envelope")
@NamespaceList(Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"), Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"), Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap"))
class GetTonJiAlarmTypeInfoRequestEnvelope(@field:Element(name = "soap:Body", required = true)
									   var body:GetTonJiAlarmTypeInfoRequestBody)

@Root(name = "soap:Body", strict = false)
class GetTonJiAlarmTypeInfoRequestBody(@field:Element(name = "GetTonJiAlarmTypeInfo", required = true)
								   var model: GetTonJiAlarmTypeInfoRequestModel)

@Root(name = "GetTonJiAlarmTypeInfo", strict = false)
@Namespace(reference = "http://kingdonsoft.com/")
class GetTonJiAlarmTypeInfoRequestModel(@field:Element(name = "UserID", required = true)
									var UserID: String,@field:Element(name = "pageIndex", required = true)
									var pageIndex: Int, @field:Element(name = "pageSize", required = true)
									var pageSize: Int, @field:Element(name = "StarTime", required = true)
									var StarTime: String, @field:Element(name = "EndTime", required = true)
									var EndTime: String)