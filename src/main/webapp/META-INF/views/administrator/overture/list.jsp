<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.overture.list.label.title" path="title"  width="10%"/>
	<acme:list-column code="administrator.overture.list.label.description" path="description"  width="25%"/>
</acme:list>