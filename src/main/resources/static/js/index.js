var submitPartnerRegistration = function () {
    var partnerDetails = {};
    $(".form .field").each(function(index, element) {
        partnerDetails[element.name] = element.value;
    });
    partnerDetails["datePurchased"] = new Date(partnerDetails["datePurchased"]).getTime();
    $.ajax({
        url     : "http://localhost:8080/partner/save",
        method  : "POST",
        data    : JSON.stringify(partnerDetails),
        contentType: "application/json",
        success : function(response) {
            $(".form .field").each(function(index, element) {
                var defaultValue = $(element).defaultValue;
                $(element).val(defaultValue);
            })
        },
        failure : function() {
            alert("Partner Registration Failed. Try Again");
        }
    });

};

$( document ).ready(function() {
    $("#submit_registration_form").click(submitPartnerRegistration);
});

