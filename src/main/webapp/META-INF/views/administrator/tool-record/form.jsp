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
	<acme:form-textbox code="administrator.tool-record.label.title" path="title"/>
	<acme:form-textbox code="administrator.tool-record.label.sector" path="sector"/>
	<acme:form-textbox code="administrator.tool-record.label.name" path="name"/>
	<acme:form-textbox code="administrator.tool-record.label.description" path="description"/>
	<acme:form-textbox code="administrator.tool-record.label.web" path="web"/>
	<acme:form-textbox code="administrator.tool-record.label.email" path="email"/>
	<acme:form-checkbox code="administrator.tool-record.label.indication" path="indication"/>
	<acme:form-textbox code="administrator.tool-record.label.star" path="star"/>
	
	<acme:form-submit test="${command == 'create'}" code="administrator.tool-record.form.buttom.create" action="create"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.tool-record.form.buttom.update" action="update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.tool-record.form.buttom.delete" action="delete"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.tool-record.form.buttom.update" action="update"/>
	<acme:form-submit test="${command == 'delete'}" code="administrator.tool-record.form.buttom.delete" action="delete"/>
	<acme:form-return code="administrator.tool-record.form.button.return"/>


</acme:form>