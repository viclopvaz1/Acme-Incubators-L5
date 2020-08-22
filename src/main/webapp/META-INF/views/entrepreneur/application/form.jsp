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
	<jstl:if test="${command!='update'}">
		<acme:form-textbox code="entrepreneur.application.form.label.ticker" path="ticker" readonly="true"/>
		<acme:form-moment code="entrepreneur.application.form.label.creationMoment" path="creationMoment" readonly="true"/>
		<acme:form-textbox code="entrepreneur.application.form.label.statement" path="statement" readonly="true"/>
		<acme:form-textbox code="entrepreneur.application.form.label.moneyOffer" path="moneyOffer" readonly="true"/>
		<acme:form-textbox code="entrepreneur.application.form.label.tickerInvestmentRound" path="investmentRound.ticker" readonly="true"/>
		<acme:form-textbox code="entrepreneur.application.form.label.investor" path="investor.identity.fullName" readonly="true"/>
	</jstl:if>
	
	<acme:form-select code="entrepreneur.application.form.label.status" path="status">
    	<jstl:if test="${command!='update'}"> 
    		<option value="pending" <jstl:if test="${status =='pending'}">selected</jstl:if>>pending</option>
    	</jstl:if>
    		<option value="accepted" <jstl:if test="${status =='accepted'}">selected</jstl:if>>accepted</option>
    		<option value="rejected" <jstl:if test="${status =='rejected'}">selected</jstl:if>>rejected</option>
	</acme:form-select>
	<jstl:if test="${status=='rejected' || command=='update'}">
		<acme:form-textarea code="entrepreneur.application.form.label.reason" path="reason"/>
	</jstl:if>
	<acme:form-submit test="${command == 'show' && status=='pending'}" code="entrepreneur.application.form.button.status" action="/entrepreneur/application/update?id=${id}" method = "get"/>
	<acme:form-submit test="${command == 'update'}" code="entrepreneur.application.form.button.update" action="/entrepreneur/application/update"/>
		
	
	
	<acme:form-return code="entrepreneur.application.form.button.return"/>
</acme:form>