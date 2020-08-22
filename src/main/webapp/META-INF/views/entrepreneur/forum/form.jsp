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

	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="entrepreneur.forum.form.label.ticker" path="investmentRound.ticker" readonly="true" />
	</jstl:if>

	<acme:form-submit test="${command == 'show' }" code="entrepreneur.forum.form.buttom.message" action="/entrepreneur/message/list-mine?forumid=${id}" method="get" />

	<acme:form-submit test="${command == 'show' }" code="entrepreneur.forum.form.button.delete" action="/entrepreneur/forum/delete" />

	<acme:form-textbox code="entrepreneur.forum.form.label.title" path="title" />
	<acme:form-submit test="${command == 'create' }" code="entrepreneur.forum.form.button.create" action="/entrepreneur/forum/create" />
	<acme:form-submit test="${command == 'delete' }" code="entrepreneur.forum.form.button.delete" action="/entrepreneur/forum/delete" />
	
	<jstl:if test="${command == 'show'}">
<button type="button" onclick="javascript: pushReturnUrl('/authenticated/forum/show?id=${id}');
	redirect('/authenticated/participation/list?forumId=${id}')" class="btn btn-primary">
	<acme:message code="authenticated.forum.form.button.participation.list"/>
	</button>
<acme:form-submit
	code="authenticated.participation.form.button.create" 
	action="/authenticated/participation/create?forumId=${id}" method="get"/>
	
	</jstl:if>	
	
	<acme:form-return code="entrepreneur.forum.form.button.return" />
</acme:form>