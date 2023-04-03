<nav class="navbar navbar-expand-lg navbar-dark bg-warning">
	<a class="navbar-brand" href="#"> <img src="../img/bookicon.png"
		width="60" height="40" class="d-inline-block align-top" alt="">
		BookStop
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="../userLibrary"><i class="fa-solid fa-book-open"></i>
					Library</a></li>
			<li class="nav-item active"><a class="nav-link"
				href="../userHistory"><i class="fas fa-history"></i> History</a></li>
			<li class="nav-item active"><a class="nav-link"
				href="../wishlist"><i class="fa-solid fa-bookmark"></i> Wishlist</a></li>



		</ul>

		<ul class="navbar-nav ml-auto">
			<li class="nav-item dropdown ">
				<div class="btn-group">
					<button type="button" class="btn btn-outline-light">
						<i class="fas fa-user"></i> Hello, ${user.firstName}
						${user.lastName}
					</button>
					<button type="button"
						class="btn btn-outline-light dropdown-toggle dropdown-toggle-split"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<span class="sr-only">Toggle Dropdown</span>
					</button>
					<div class="dropdown-menu  dropdown-menu-right">
						<a class="dropdown-item" href="../userProfile">Profile</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="../userLogout">Logout</a>
					</div>


				</div>
			</li>
		</ul>
	</div>
</nav>

