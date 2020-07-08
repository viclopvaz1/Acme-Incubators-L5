<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-url code="patron.banner.label.picture" path="picture"/>
	<acme:form-textbox code="patron.banner.label.slogan" path="slogan"/>
	<acme:form-url code="patron.banner.label.targetUrl" path="targetUrl"/>
	<acme:form-checkbox code="patron.banner.label.finalMode" path="finalMode"/>
	<acme:form-textbox code="patron.banner.label.patron" path="patron.identity.fullName"/>
	
	<acme:form-submit test="${numCreditCard == 1}" code="patron.banner.form.button.credit-card" action="/patron/credit-card/show?bannerid=${id}"  method="get"/>
	<acme:form-return code="patron.banner.form.button.return"/>


</acme:form> 