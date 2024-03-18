function handleFilter(option) {
    window.location.href = "./load-filter?status=" + option.value;
}

function handleFilterUser(option) {
    window.location.href = "./load-filter?status=" + option.value;
}

function handleFilterPriority(option) {
    window.location.href = "./user-priority?priority=" + option.value;
}

function handleFilterDue(option) {
    window.location.href = "./load-filter-due?due=" + option.value;
}


function handleFilterStatus(option) {
    window.location.href = "./user-filter?status=" + option.value;
}

function handleFilterRole(option) {
    window.location.href = "./user-filter-role?role=" + option.value;
}


