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
	<acme:form-textbox code="authenticated.forum.form.label.title" path="title" />
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="authenticated.forum.form.label.ticker" path="investmentRound.ticker" readonly="true" />
	</jstl:if>

	<acme:form-submit test="${command == 'show' }" code="authenticated.forum.form.buttom.message" action="/authenticated/message/list-mine?forumid=${id}" method="get" />
	<acme:form-submit test="${command == 'show' }" code="authenticated.forum.form.buttom.create.message" action="/authenticated/message/create?forumid=${id}" method="get" />

	
	<acme:form-return code="authenticated.forum.form.button.return" />
</acme:form>