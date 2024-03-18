function searchByName(param) {
	var toDo = param.value;
	$.ajax({
		url: "/todo-app/search-by-ajax",
		type: "get",
		data: {
			txt: toDo
		},
		success: function(data) {
			var row = document.getElementById("toDoAjax");
			row.innerHTML = data;
		},
		error: function(xhr) {
		}
	});
}
function searchByNameAdmin(param) {
	var toDo = param.value;
	$.ajax({
		url: "/todo-app/search-by-ajax-admin",
		type: "get",
		data: {
			txt: toDo
		},
		success: function(data) {
			var row = document.getElementById("toDoAjaxAdmin");
			row.innerHTML = data;
		},
		error: function(xhr) {
		}
	});
}

function searchUByName(param) {
	var user = param.value;
	$.ajax({
		url: "/todo-app/search-u-by-ajax",
		type: "get",
		data: {
			txt: user
		},
		success: function(data) {
			var row = document.getElementById("UAjax");
			row.innerHTML = data;
		},
		error: function(xhr) {
		}
	});
}