package xinyiyun.chenfei.com.baselibrary.model


import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.NamespaceList
import org.simpleframework.xml.Root

/**
 * Created by Administrator on 2018/1/12.
 */

@Root(name = "soap:Envelope")
@NamespaceList(Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"), Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"), Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soap"))
class LoginRequestEnvelope(@field:Element(name = "soap:Body", required = true)
					  var body: LoginRequestBody)

@Root(name = "soap:Body", strict = false)
class LoginRequestBody(@field:Element(name = "validateUser", required = true)
				  var model: LoginRequestModel)

@Root(name = "validateUser", strict = false)
@Namespace(reference = "http://kingdonsoft.com/")
class LoginRequestModel(@field:Element(name = "UserName", required = true)
				   var UserName: String, @field:Element(name = "UserPwd", required = true)
				   var UserPwd: String)
