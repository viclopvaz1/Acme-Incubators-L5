<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Provider,acme.entities.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link" action="http://www.example.com/" />
			<acme:menu-suboption code="master.menu.anonymous.favourite-linkJ" action="https://play.pokemonshowdown.com" />
			<%--josruialb--%>
			<acme:menu-suboption code="master.menu.anonymous.favourite-linkV" action="https://www.twitch.tv" />
			<%--viclopvaz1--%>
			<acme:menu-suboption code="master.menu.anonymous.favourite-linkA" action="https://www.youtube.com/?hl=es&gl=ES" />
			<%--albcorare26--%>

			<acme:menu-separator />

			<acme:menu-suboption code="master.menu.anonymous.list-notice" action="/anonymous/notice/list" />

			<acme:menu-separator />

			<acme:menu-suboption code="master.menu.anonymous.list-technology-record" action="/anonymous/technology-record/list" />

			<acme:menu-separator />

			<acme:menu-suboption code="master.menu.anonymous.list-tool-record" action="/anonymous/tool-record/list" />

		</acme:menu-option>

		<acme:menu-option code="master.menu.anonymous2" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous2.lopez-bulletin" action="/anonymous/lopez-bulletin/create" />
			<acme:menu-suboption code="master.menu.anonymous2.list-lopez-bulletin" action="/anonymous/lopez-bulletin/list" />

			<acme:menu-separator />

			<acme:menu-suboption code="master.menu.anonymous2.cordon-bulletin" action="/anonymous/cordon-bulletin/create" />
			<acme:menu-suboption code="master.menu.anonymous2.list-cordon-bulletin" action="/anonymous/cordon-bulletin/list" />

			<acme:menu-separator />

			<acme:menu-suboption code="master.menu.anonymous2.ruiz-bulletin" action="/anonymous/ruiz-bulletin/create" />
			<acme:menu-suboption code="master.menu.anonymous2.list-ruiz-bulletin" action="/anonymous/ruiz-bulletin/list" />
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">
			<acme:menu-suboption code="master.menu.patron.banner" action="/patron/banner/list-mine" />
			<acme:menu-suboption code="master.menu.patron.create-banner" action="/patron/banner/create" />
		
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.list-notice" action="/administrator/notice/list" />
			<acme:menu-suboption code="master.menu.administrator.create-notice" action="/administrator/notice/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.configuration" action="/administrator/configuration/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/dashboard/show" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.statistics" action="/administrator/statistics/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.list-challenge" action="/administrator/challenge/list" />
			<acme:menu-suboption code="master.menu.administrator.create-challenge" action="/administrator/challenge/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.create-tool-record" action="/administrator/tool-record/create" />
			<acme:menu-suboption code="master.menu.administrator.list-tool-record" action="/administrator/tool-record/list" />
		
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.configuration" action="/administrator/configuration/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/dashboard/show" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.statistics" action="/administrator/statistics/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.inquirie" action="/administrator/inquirie/list" />
			<acme:menu-suboption code="master.menu.administrator.create-inquirie" action="/administrator/inquirie/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.list-overture" action="/administrator/overture/list" />
			<acme:menu-suboption code="master.menu.administrator.create-overture" action="/administrator/overture/create" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.create-technology-record" action="/administrator/technology-record/create" />
			<acme:menu-suboption code="master.menu.administrator.list-technology-record" action="/administrator/technology-record/list" />

            <acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.list.bookkeeper" action="/administrator/bookkeeper-request/list" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">

			<acme:menu-suboption code="master.menu.authenticated.list-notice" action="/authenticated/notice/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.list-technology-record" action="/authenticated/technology-record/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.list-tool-record" action="/authenticated/tool-record/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.list-inquirie" action="/authenticated/inquirie/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.list-overture" action="/authenticated/overture/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.list-challenge" action="/authenticated/challenge/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.list-investment-round" action="/authenticated/investment-round/list" />
	<acme:menu-separator />
			<acme:menu-suboption code="master.menu.authenticated.list-forum" action="/authenticated/forum/list-mine" />

		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/" />
		</acme:menu-option>
		
		
		<acme:menu-option code="master.menu.entrepreneur" access="hasRole('Entrepreneur')">
			<acme:menu-suboption code="master.menu.entrepreneur.application.list-mine" action="/entrepreneur/application/list-mine" />
		
				<acme:menu-suboption code="master.menu.entrepreneur.investment-round.create" action="/entrepreneur/investment-round/create" />
		
		
		<acme:menu-suboption code="master.menu.entrepreneur.investment-round.list" action="/entrepreneur/investment-round/list-mine" />
				
		<acme:menu-suboption code="master.menu.entrepreneur.forum.list" action="/entrepreneur/forum/list-mine" />

	</acme:menu-option>

		<acme:menu-option code="master.menu.investor" access="hasRole('Investor')">
			<acme:menu-suboption code="master.menu.investor.application.list-mine" action="/investor/application/list-mine" />
		

	</acme:menu-option>
	
	<acme:menu-option code="master.menu.bookkeeper" access="hasRole('Bookkeeper')">
			<acme:menu-suboption code="master.menu.bookkeeper.accounting-record.list-mine" action="/bookkeeper/accounting-record/list-mine" />
			<acme:menu-suboption code="master.menu.bookkeeper.investment-round.list-mine" action="/bookkeeper/investment-round/list-mine" />
			<acme:menu-suboption code="master.menu.bookkeeper.investment-round.list-no-mine" action="/bookkeeper/investment-round/list-no-mine" />
		


	</acme:menu-option>


	
		
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()" />
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()" />

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update" />
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create"
				access="!hasRole('Provider')" />
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update"
				access="hasRole('Provider')" />
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create"
				access="!hasRole('Consumer')" />
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update"
				access="hasRole('Consumer')" />
			<acme:menu-suboption code="master.menu.user-account.become-entrepreneur" action="/authenticated/entrepreneur/create"
				access="!hasRole('Entrepreneur')" />
			<acme:menu-suboption code="master.menu.user-account.entrepreneur" action="/authenticated/entrepreneur/update"
				access="hasRole('Entrepreneur')" />
			<acme:menu-suboption code="master.menu.user-account.become-investor" action="/authenticated/investor/create"
				access="!hasRole('Investor')" />
			<acme:menu-suboption code="master.menu.user-account.investor" action="/authenticated/investor/update"
				access="hasRole('Investor')" />
				
			<acme:menu-suboption code="master.menu.user-account.become-bookkeeper" action="/authenticated/bookkeeper-request/create"
				access="!hasRole('Bookkeeper')" />
			<acme:menu-suboption code="master.menu.user-account.bookkeeper" action="/authenticated/bookkeeper/update" access="hasRole('Bookkeeper')" />
		
		<acme:menu-suboption code="master.menu.user-account.become-patron" action="/authenticated/patron/create"
				access="!hasRole('Patron')" />
			<acme:menu-suboption code="master.menu.user-account.patron" action="/authenticated/patron/update"
				access="hasRole('Patron')" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()" />
	</acme:menu-right>
</acme:menu-bar>

