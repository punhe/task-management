function handleDeleteByAdmin(id) {
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this action!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it !',
        input: 'text',
        inputPlaceholder: 'Please enter the reason for deletion',
        inputValidator: (value) => {
            if (!value) {
                return 'You need to enter a reason!';
            }
        },
    }).then((result) => {
        if (result.isConfirmed) {
            const reason = encodeURIComponent(result.value);
            Swal.fire({
                title: 'Deleted!',
                text: 'Todo list has been deleted :3',
                icon: 'success',
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'OK',
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = './delete?id=' + id + '&reason=' + reason;
                }
            });
        }
        if (!result.isConfirmed) {
            Swal.fire({
                title: 'Canceled',
                text: 'Todo list is safe for now :)',
                icon: 'error',
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'OK',
            });
        }
    });
}

function handleDelete(id) {
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this action!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it !',
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire({
                title: 'Deleted!',
                text: 'Todo list has been deleted :3',
                icon: 'success',
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'OK',
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = './delete?id=' + id;
                }
            });
        }
        if (!result.isConfirmed) {
            Swal.fire({
                title: 'Canceled',
                text: 'Todo list is safe for now :)',
                icon: 'error',
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'OK',
            });
        }
    });
}
