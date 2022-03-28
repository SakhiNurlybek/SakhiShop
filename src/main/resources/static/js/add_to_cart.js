$(document).ready(function (){
    $("#addToCart").on("click",function (e){
        addToCart();
    });
});

function addToCart(phoneId){
    quantity = $("#quantity" + phoneId).val();
    url = contextPath + "cart/add/" + phoneId + "/" + quantity;

    $.ajax({
        type: "POST",
        url :  url,
        beforeSend : function (xhr){
            xhr.setRequestHeader(csrfHeaderName,csrfValue);
        }
    }).done(function (response){
        $("#modalTitle").text("Shopping Cart");
        $("#modalBody").text(response);
        $("#MyModal").modal();
    }).fail(function (){
        $("#modalTitle").text("Shopping Cart");
        $("#modalBody").text("Error while adding product to shopping cart.");
        $("#MyModal").modal();
    });
}