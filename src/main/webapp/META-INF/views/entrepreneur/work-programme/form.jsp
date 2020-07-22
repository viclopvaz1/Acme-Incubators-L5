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
	<acme:form-textbox code="entrepreneur.work-programme.form.label.title" path="title"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="entrepreneur.work-programme.form.label.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>
	<acme:form-moment code="entrepreneur.work-programme.form.label.deadline" path="deadline"/>
	<acme:form-money code="entrepreneur.work-programme.form.label.budget" path="budget"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="entrepreneur.work-programme.form.label.investmentRound" path="investmentRound.ticker" readonly="true"/>
	</jstl:if>
	<acme:form-hidden path="investmentRoundid"/>
	
	<acme:form-submit test="${command == 'show' }"
		code="entrepreneur.work-programme.form.button.update" 
		action="/entrepreneur/work-programme/update"/>
		
	<acme:form-submit test="${command == 'show' }"
		code="entrepreneur.work-programme.form.button.delete" 
		action="/entrepreneur/work-programme/delete"/>
		
	<acme:form-submit test="${command == 'create' }"
		code="entrepreneur.work-programme.form.button.create" 
		action="/entrepreneur/work-programme/create"/>
		
	<acme:form-submit test="${command == 'update' }"
		code="entrepreneur.work-programme.form.button.update" 
		action="/entrepreneur/work-programme/update"/>
		
	<acme:form-submit test="${command == 'delete' }"
		code="entrepreneur.work-programme.form.button.delete" 
		action="/entrepreneur/work-programme/delete"/>
	
	
	<acme:form-return code="entrepreneur.work-programme.form.button.return"/>
</acme:form>