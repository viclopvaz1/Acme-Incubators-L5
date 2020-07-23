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

<acme:form>

<jstl:if test="${numBookkeeperRequest == 0}">
	<acme:form-textarea code="authenticated.bookkeeper-request.form.label.firm" path="firm" />
	<acme:form-textarea code="authenticated.bookkeeper-request.form.label.responsabilityStatement" path="responsabilityStatement" />

	<acme:form-submit test="${command == 'create'}" code="authenticated.bookkeeper-request.form.button.create" action="/authenticated/bookkeeper-request/create"/>
</jstl:if>

<jstl:if test="${numBookkeeperRequest > 1}">

	<acme:message code="bookkeeper.error.exist"/>
	</br>
</jstl:if>
  	<acme:form-return code="authenticated.bookkeeper-request.form.button.return" />
	
</acme:form>