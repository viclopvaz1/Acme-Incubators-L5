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


	<jstl:if test="${finalMode == true}">
		<acme:form readonly="true">
			<acme:form-url code="patron.banner.label.picture" path="picture"/>
			<acme:form-textbox code="patron.banner.label.slogan" path="slogan"/>
			<acme:form-url code="patron.banner.label.targetUrl" path="targetUrl"/>
			<acme:form-checkbox code="patron.banner.label.finalMode" path="finalMode"/>
			
			<acme:form-submit test="${(command == 'show' || command == 'update') && numCreditCard == 1}" code="patron.banner.form.button.credit-card" action="/patron/credit-card/show?bannerid=${id}&finalmodel=${finalMode}"  method="get"/>
			<acme:form-submit test="${command == 'delete'}" code="patron.banner.form.button.delete" action="delete"/>
			<acme:form-submit test="${command == 'show'}" code="patron.banner.form.button.delete" action="delete"/>
			
			<acme:form-return code="patron.banner.form.button.return"/>
		</acme:form> 
	</jstl:if>
	
	<jstl:if test="${finalMode == false || command == 'create' || command == 'update'}">
		<acme:form>
			<acme:form-url code="patron.banner.label.picture" path="picture"/>
			<acme:form-textbox code="patron.banner.label.slogan" path="slogan"/>
			<acme:form-url code="patron.banner.label.targetUrl" path="targetUrl"/>
				<jstl:if test="${command != 'create'}">
					<acme:form-checkbox code="patron.banner.label.finalMode" path="finalMode"/>
				</jstl:if>
		
			<acme:form-submit test="${command == 'create'}" code="patron.banner.form.button.create" action="create"/>
			<acme:form-submit test="${command == 'show'}" code="patron.banner.form.button.update" action="update"/>
			<acme:form-submit test="${command == 'update'}" code="patron.banner.form.button.update" action="update"/>
				
			<acme:form-submit test="${(command == 'show' || command == 'update') && numCreditCard == 0}" code="patron.banner.form.button.create-credit-card" action="/patron/credit-card/create?bannerid=${id}" method="get"/>
			
			
			
			<acme:form-submit test="${(command == 'show' || command == 'update') && numCreditCard == 1}" code="patron.banner.form.button.credit-card" action="/patron/credit-card/show?bannerid=${id}&finalmodel=${finalMode}"  method="get"/>
			<acme:form-submit test="${command == 'delete'}" code="patron.banner.form.button.delete" action="delete"/>
			<acme:form-submit test="${command == 'show'}" code="patron.banner.form.button.delete" action="delete"/>
			
			<acme:form-return code="patron.banner.form.button.return"/>

		</acme:form> 
	</jstl:if>
	

