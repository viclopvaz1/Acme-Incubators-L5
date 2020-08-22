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
	<acme:form-hidden path="investmentRoundid"/>
	<acme:form-textbox code="investor.application.form.label.ticker" placeholder="SSS-YY-NNNNNN" path="ticker"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="investor.application.form.label.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>
	<acme:form-textbox code="investor.application.form.label.statement" path="statement"/>
	<acme:form-textbox code="investor.application.form.label.moneyOffer" path="moneyOffer"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="investor.application.form.label.tickerInvestmentRound" path="investmentRound.ticker" readonly="true"/>
		<acme:form-textbox code="investor.application.form.label.investor" path="investor.identity.fullName" readonly="true"/>
		<acme:form-textbox code="investor.application.form.label.status" path="status" readonly="true"/>
		<jstl:if test="${status=='rejected'}">
			<acme:form-textarea code="investor.application.form.label.reason" path="reason" readonly="true"/>
		</jstl:if>
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}"
		code="investor.application.form.button.create" 
		action="/investor/application/create"/>
	
	<acme:form-return code="investor.application.form.button.return"/>
</acme:form>