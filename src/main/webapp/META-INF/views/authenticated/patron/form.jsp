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

<acme:form readonly="false">
	<acme:form-textbox code="authenticated.patron.form.label.organisationName" path="organisationName"/>

	<acme:form-submit test="${command == 'create' }"
		code="authenticated.patron.form.label.create" 
		action="/authenticated/patron/create"/>
	<acme:form-submit test="${command == 'update' }"
		code="authenticated.patron.form.label.update" 
		action="/authenticated/patron/update"/>
		
	<acme:form-return code="authenticated.overture.form.button.return"/>
</acme:form>