<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
<acme:list-column code="anonymous.lopez-bulletin.list.label.nombre" path="nombre" width="20%"/>
<acme:list-column code="anonymous.lopez-bulletin.list.label.dni" path="dni" width="20%"/>
<acme:list-column code="anonymous.lopez-bulletin.list.label.descripcion" path="descripcion" width="60%"/>

</acme:list>