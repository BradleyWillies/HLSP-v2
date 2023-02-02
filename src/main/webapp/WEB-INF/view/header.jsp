<header>
	<div class="navbar navbar-dark bg-dark shadow-sm">
		<div class="container">
			<% out.println("<a href=\"" + request.getContextPath() + "/\" class=\"navbar-brand d-flex align-items-center\"><strong>Healthy-Life</strong></a>"); %>
			<form action="/logout" method="GET">
				<button class="btn btn-outline-secondary btn-lg px-4">Logout</button>
			</form>
		</div>
	</div>
</header>