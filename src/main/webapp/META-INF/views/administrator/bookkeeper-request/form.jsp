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
	<acme:form-textbox code="administrator.bookkeeper.form.label.firm" path="firm" readonly="true"/>
    
	<acme:form-textarea code="administrator.bookkeeper.form.label.responsabilityStatement" path="responsabilityStatement" readonly="true"/>


    <acme:form-submit code="administrator.bookkeeper.form.button.accept" action="/administrator/bookkeeper/create?id=${id}"/>

    <acme:form-submit code="administrator.bookkeeper.form.button.reject" action="/administrator/bookkeeper-request/delete"/>


  	<acme:form-return code="administrator.bookkeeper.form.button.return"/>
</acme:form>