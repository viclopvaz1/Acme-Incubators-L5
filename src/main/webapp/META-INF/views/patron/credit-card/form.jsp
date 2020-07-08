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
	<acme:form-textbox code="patron.credit-card.label.holderName" path="holderName"/>
	<acme:form-textbox code="patron.credit-card.label.number" path="number"/>
	<acme:form-textbox code="patron.credit-card.label.brand" path="brand"/>
	<acme:form-textbox code="patron.credit-card.label.monthExp" path="monthExp"/>
	<acme:form-textbox code="patron.credit-card.label.yearExp" path="yearExp"/>
	<acme:form-textbox code="patron.credit-card.label.cvv" path="cvv"/>
	
	<acme:form-return code="patron.credit-card.form.button.return"/>


</acme:form> 