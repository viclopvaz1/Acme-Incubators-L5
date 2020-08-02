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
	<jstl:if test="${command == 'create'}">
			<acme:form-textbox code="entrepreneur.investment-round.form.label.ticker" placeholder="SSS-YY-NNNNNN" path="ticker"/>
	</jstl:if>
	<jstl:if test="${command != 'create'}">
			<acme:form-textbox code="entrepreneur.investment-round.form.label.ticker" placeholder="SSS-YY-NNNNNN" path="ticker" readonly="true"/>
	</jstl:if>
	<acme:form-textbox code="entrepreneur.investment-round.form.label.title" path="title"/>
	<acme:form-money code="entrepreneur.investment-round.form.label.amountMoney" path="amountMoney"/>
	<acme:form-textarea code="entrepreneur.investment-round.form.label.description" path="description"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="entrepreneur.investment-round.form.label.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>
	<acme:form-textbox code="entrepreneur.investment-round.form.label.round" path="round"/>
	<acme:form-textbox code="entrepreneur.investment-round.form.label.moreInfo" path="moreInfo"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="entrepreneur.investment-round.form.label.entrepreneur" path="entrepreneur.identity.fullName" readonly="true"/>
		<acme:form-checkbox code="entrepreneur.investment-round.form.label.status" path="status"/>
	</jstl:if>
	
	<acme:form-submit test="${(command == 'show' || command == 'update') && status == false}"
		code="entrepreneur.investment-round.form.button.update" 
		action="/entrepreneur/investment-round/update"/>
	<acme:form-submit test="${(command == 'show' || command == 'delete') && numApplication == 0}"
		code="entrepreneur.investment-round.form.button.delete" 
		action="/entrepreneur/investment-round/delete"/>
	<acme:form-submit test="${command == 'create'}"
		code="entrepreneur.investment-round.form.button.create" 
		action="/entrepreneur/investment-round/create"/>
	
	<acme:form-submit test="${command == 'show' || command == 'update' }" code="entrepreneur.investment-round.form.button.work-programme" action="/entrepreneur/work-programme/list-mine?investmentRoundid=${id}"  method="get"/>
	<acme:form-submit test="${(command == 'show' || command == 'update') && status == false }" code="entrepreneur.investment-round.form.button.create-work-programme" action="/entrepreneur/work-programme/create?investmentRoundid=${id}&status=${status}" method="get"/>
	<acme:form-submit test="${(command == 'show' || command == 'update') && status == true && numAccountingRecord > 0}" code="entrepreneur.investment-round.form.button.accounting-record" action="/entrepreneur/accounting-record/list-mine?investmentRoundid=${id}"  method="get"/>
	<acme:form-submit test="${(command == 'show' || command == 'update') && status == true}" code="entrepreneur.investment-round.form.button.forum" action="/entrepreneur/forum/create?investmentRoundid=${id}"  method="get"/>
	<acme:form-return code="entrepreneur.investment-round.form.button.return"/>
</acme:form>