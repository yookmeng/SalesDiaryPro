<div id="navigation">
	<div class="container-fluid">
	   	<input type="hidden" value="team" name="team" />	
		<a href="#" id="brand">Sales Diary Pro</a>
		<a href="#" class="toggle-nav" rel="tooltip" data-placement="bottom" title="Toggle navigation">
			<i class="fa fa-bars"></i>
		</a>
		<ul class='main-nav'>
			<li>
				<a href="home">
					<span>Home</span>
				</a>
			</li>
			<li>
				<a href="editTeam?teamid=${team.teamid}">Team Profile</a>
			</li>
			<li>
				<a href="listMember?teamid=${team.teamid}">Member</a>
			</li>
			<li>
				<a href="listTeamTargetTL">Target</a>
			</li>
			<li>
				<a href="listReview">Notes</a>
			</li>
		</ul>
		<div class="user">
			<div class="dropdown">
				<a href="#" class='dropdown-toggle' data-toggle="dropdown">${pageContext.request.userPrincipal.name}
					<img src="img/demo/user-avatar.jpg" alt="">
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
