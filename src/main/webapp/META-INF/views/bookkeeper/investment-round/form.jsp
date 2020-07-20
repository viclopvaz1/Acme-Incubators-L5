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
	<acme:form-textbox code="bookkeeper.investment-round.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="bookkeeper.investment-round.form.label.title" path="title"/>
	<acme:form-money code="bookkeeper.investment-round.form.label.amountMoney" path="amountMoney"/>
	<acme:form-textarea code="bookkeeper.investment-round.form.label.description" path="description"/>
	<acme:form-moment code="bookkeeper.investment-round.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textbox code="bookkeeper.investment-round.form.label.round" path="round"/>
	<acme:form-textbox code="bookkeeper.investment-round.form.label.moreInfo" path="moreInfo"/>
	<acme:form-textbox code="bookkeeper.investment-round.form.label.entrepreneur" path="entrepreneur.identity.fullName"/>
		<acme:form-checkbox code="bookkeeper.investment-round.form.label.status" path="status"/>
	
	<acme:form-submit code="bookkeeper.investment-round.form.button.create-accounting-record" action="/bookkeeper/accounting-record/create?investmentRoundId=${id}" method="get"/>
	<acme:form-return code="bookkeeper.investment-round.form.button.return"/>
</acme:form>