 <%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
<acme:list-column code="administrator.configuration.list.label.spamWords" path="spamWords" width="50%"/>
<acme:list-column code="administrator.configuration.list.label.sectors" path="sectors" width="50%"/>

</acme:list>