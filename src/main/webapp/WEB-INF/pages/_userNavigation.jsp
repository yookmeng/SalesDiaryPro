<div id="navigation">
	<div class="container-fluid">
		<a onclick="toggle_visibility()" class="toggle-nav" rel="tooltip" data-placement="bottom" title="Toggle navigation">
			<i class="fa fa-bars"></i>
		</a>
		<a href="#" id="brand">Sales Diary Pro</a>
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
<script type="text/javascript">
	function toggle_visibility() {
		var c = document.getElementById("content");
		var m = document.getElementById("main");
		if(c.style.display == 'block'){
			c.style.display = 'none';
 			m.style.display = 'inline';    	   
		}
		else {
			c.style.display = 'block';
			m.style.display = 'inline';    	   
       }
   }
</script>