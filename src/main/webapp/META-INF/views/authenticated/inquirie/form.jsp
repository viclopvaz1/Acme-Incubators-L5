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
	<acme:form-textbox code="authenticated.inquirie.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.inquirie.form.label.creationMoment" path="creationMoment"/>
	<acme:form-moment code="authenticated.inquirie.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.inquirie.form.label.description" path="description"/>
	<acme:form-money code="authenticated.inquirie.form.label.maxMoney" path="maxMoney"/>
	<acme:form-money code="authenticated.inquirie.form.label.minMoney" path="minMoney"/>
	<acme:form-textbox code="authenticated.inquirie.form.label.email" path="email"/>

	
	
	<acme:form-return code="authenticated.inquirie.form.button.return"/>
</acme:form>