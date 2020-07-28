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
	<input name="forumid" id="forumid" type="hidden" value="${param.forumid}" />
	
	<acme:form-textbox code="authenticated.forum.form.label.title" path="title" />

<jstl:if test="${command != 'create'}">
	     <acme:form-moment code="authenticated.message.form.label.creationMoment"
	      path="creationMoment" 
	      readonly="true"/>     
		<acme:form-textarea code="authenticated.message.form.label.user.identity.fullName" path="authenticated.identity.fullName" />
	</jstl:if>

	<acme:form-textarea code="authenticated.message.form.label.body" path="body" />
	<acme:form-textarea code="authenticated.message.form.label.tags" path="tags" />
	<jstl:if test="${command == 'create'}">
	<acme:form-checkbox code="authenticated.message.form.label.check" path="check" />
	</jstl:if>
	
	<acme:form-return code="authenticated.message.form.button.return" />
	
	<acme:form-submit test="${command == 'create' }" code="authenticated.message.form.button.create" action="/authenticated/message/create?forumId=${forumId}" method ="post" />
</acme:form>