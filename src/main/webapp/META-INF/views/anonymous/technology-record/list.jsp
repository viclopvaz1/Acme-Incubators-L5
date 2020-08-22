<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="anonymous.technology-record.list.label.title" path="title" width="20%"/>
	<acme:list-column code="anonymous.technology-record.list.label.sector" path="sector" width="60%"/>

</acme:list>