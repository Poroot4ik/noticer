
function showAlert2(id) {
$.ajax({
        type: "DELETE",
        url: "/main/" + id,
        data: id,
        cache: false,
        success: function(response){
            // we have the response
            /*if(response.status == "SUCCESS"){
               alert('OK');
             }else{
               alert('Not OK');
             }*/
             alert(response.status);
         },
         error: function(e){
             alert('Error: ' + e);
         }
    });
};

