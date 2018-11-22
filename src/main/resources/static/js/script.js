
checkItem = function(orderId, itemId, check) {
    console.log(check)
    fetch(`/order/${orderId}/item/${itemId}`, {
        method: 'PUT', 
        body:  JSON.stringify(check.checked),
        headers: new Headers({
            'Content-Type': 'application/json'
          })
    }).then(location.reload());

}

addItem = (orderId, name_input) => {
    fetch(`/order/${orderId}/item`, {
        method: 'PUT', 
        body:  JSON.stringify({
                
                    "name":name_input.value
                }),
        headers: new Headers({
            'Content-Type': 'application/json'
          })
    }).then(location.reload());
    name_input.value = '';
}

deleteOrder = (id) => {

    fetch(`/order/${id}`, {
        method: 'DELETE'
    }).then(window.location.replace(`/`));
}

deleteItem = (orderId, itemId) => {
    fetch(`/order/${orderId}/item/${itemId}`, {
        method: 'DELETE'
    }).then(location.reload());
}