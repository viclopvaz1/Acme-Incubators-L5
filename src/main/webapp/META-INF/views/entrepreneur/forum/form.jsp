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
	<acme:form-textbox code="entrepreneur.forum.form.label.title" path="title" />
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="entrepreneur.forum.form.label.ticker" path="investmentRound.ticker" readonly="true" />
	</jstl:if>

	<acme:form-submit test="${command == 'show' }" code="entrepreneur.forum.form.buttom.message" action="/entrepreneur/message/list-mine?forumid=${id}" method="get" />

	<acme:form-submit test="${command == 'show' }" code="entrepreneur.forum.form.button.delete" action="/entrepreneur/forum/delete" />
	<acme:form-submit test="${command == 'create' }" code="entrepreneur.forum.form.button.create" action="/entrepreneur/forum/create" />
	<acme:form-submit test="${command == 'delete' }" code="entrepreneur.forum.form.button.delete" action="/entrepreneur/forum/delete" />
	<acme:form-return code="entrepreneur.forum.form.button.return" />
</acme:form>