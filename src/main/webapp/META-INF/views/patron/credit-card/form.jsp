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

<acme:form-hidden path="bannerid"/>
<acme:form-hidden path="finalmode"/>
<jstl:if test="${finalmode == false || command == 'create' || command == 'update'}">
<acme:form>
	<acme:form-textbox code="patron.credit-card.label.holderName" path="holderName"/>
	<acme:form-textbox code="patron.credit-card.label.number" path="number"/>
	<acme:form-textbox code="patron.credit-card.label.brand" path="brand"/>
	<acme:form-textbox code="patron.credit-card.label.monthExp" path="monthExp"/>
	<acme:form-textbox code="patron.credit-card.label.yearExp" path="yearExp"/>
	<acme:form-textbox code="patron.credit-card.label.cvv" path="cvv"/>
	<acme:form-hidden path="bannerid"/>
	
	<acme:form-submit test="${command == 'create'}" code="patron.credit-card.form.buttom.create" action="create"/>
	<acme:form-submit test="${command == 'show' || command == 'update'}" code="patron.credit-card.form.buttom.update" action="update"/>
	<acme:form-submit test="${command == 'show' || command == 'delete'}" code="patron.credit-card.form.buttom.delete" action="delete"/>
	
	<acme:form-return code="patron.credit-card.form.button.return"/>


</acme:form> 
	</jstl:if>
	
<jstl:if test="${finalmode == true}">
<acme:form readonly="true">
	<acme:form-textbox code="patron.credit-card.label.holderName" path="holderName"/>
	<acme:form-textbox code="patron.credit-card.label.number" path="number"/>
	<acme:form-textbox code="patron.credit-card.label.brand" path="brand"/>
	<acme:form-textbox code="patron.credit-card.label.monthExp" path="monthExp"/>
	<acme:form-textbox code="patron.credit-card.label.yearExp" path="yearExp"/>
	<acme:form-textbox code="patron.credit-card.label.cvv" path="cvv"/>
	
	<acme:form-return code="patron.credit-card.form.button.return"/>


</acme:form> 
	</jstl:if>