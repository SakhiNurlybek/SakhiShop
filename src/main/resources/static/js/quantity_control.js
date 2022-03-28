$(document).ready(function (){
    $(".minusButton").on("click",function (evt){
        evt.preventDefault();
        phoneId = $(this).attr("pid");
        qtyInput = $("#quantity" + phoneId);
        newQty = parseInt(qtyInput.val()) - 1;
        if(newQty > 0) qtyInput.val(newQty);
    });

    $(".plusButton").on("click",function (evt){
        evt.preventDefault();
        phoneId = $(this).attr("pid");
        qtyInput = $("#quantity" + phoneId);
        newQty = parseInt(qtyInput.val()) + 1;
        if(newQty < 10) qtyInput.val(newQty);
    });
});