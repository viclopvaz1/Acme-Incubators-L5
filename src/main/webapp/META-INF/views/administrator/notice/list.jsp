<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
<acme:list-column code="administrator.notice.list.label.creationMoment" path="creationMoment" width="20%"/>
<acme:list-column code="administrator.notice.list.label.body" path="body" width="60%"/>

</acme:list>