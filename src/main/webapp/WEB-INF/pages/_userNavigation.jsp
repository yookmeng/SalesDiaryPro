<div id="navigation">
	<div class="container-fluid">
		<a href="#" id="brand">Sales Diary Pro</a>
		<a class="toggle-nav" rel="tooltip" data-placement="bottom" title="Toggle navigation">
			<i class="fa fa-bars"></i>
		</a>
		<ul class='main-nav'>
			<li>
				<a href="home">
					<span>Home</span>
				</a>
			</li>
			<li>
				<a href="listTeamTargetUser">
					<span>Target</span>
				</a>
			</li>
			<li>
				<a href="calendar">
					<span>Calendar</span>
				</a>
			</li>
			<li>
				<a href="listProspect">
					<span>Prospect</span>
				</a>
			</li>
			<li>
				<a href="listContact">
					<span>Contact</span>
				</a>
			</li>
		</ul>
		<div class="user">
			<div class="dropdown">
				<a href="#" class='dropdown-toggle' data-toggle="dropdown">${pageContext.request.userPrincipal.name}
					<img src="resources/img/demo/user-avatar.jpg" alt="">
				</a>
				<ul class="dropdown-menu pull-right">
					<li>
						<a href="editUser?username=${pageContext.request.userPrincipal.name}">Edit profile</a>
					</li>
					<li>
						<a href="login">Sign out</a>
					</li>
				</ul>
			</div>			
		</div>
	</div>
</div>