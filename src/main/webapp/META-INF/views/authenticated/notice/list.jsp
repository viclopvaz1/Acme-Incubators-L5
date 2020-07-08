<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
<acme:list-column code="authenticated.notice.list.label.header" path="header" width="10%"/>
<acme:list-column code="authenticated.notice.list.label.body" path="body" width="25%"/>

</acme:list>