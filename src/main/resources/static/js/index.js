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
        error : function() {
            alert("Partner Registration Failed. Please Try Again");
        }
    });

};

var search = function(event) {

};

var importUberId = function(event) {

    event.preventDefault();
    var form = $('#import_form')[0];
    var data = new FormData(form);
    $("#import_button").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "http://localhost:8080/partner/importUberId",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            alert("Imported successfully");
            $("#import_button").prop("disabled", false);
        },
        error: function (e) {
            alert("Import failed. Please try again");
            $("#import_button").prop("disabled", false);
        }
    });

};

var downloadCollectibles = function() {
    $("#download_collectibles").prop("disabled", true);
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/download/partnerLedger",
        cache: false,
        timeout: 600000,
        success: function (data) {
            $("#download_collectibles").prop("disabled", false);
        },
        error: function (e) {
            $("#download_collectibles").prop("disabled", false);
            alert("Download failed. Please try again");
        }
    });

};

var downloadPartnersListWithourUberId = function() {
    $("#download_partners_without_uber_id").prop("disabled", true);
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/download/partnersWithoutUberId",
        cache: false,
        timeout: 600000,
        success: function (data) {
            $("#download_partners_without_uber_id").prop("disabled", false);
        },
        error: function (e) {
            $("#download_partners_without_uber_id").prop("disabled", false);
            alert("Download failed. Please try again");
        }
    });
}

$( document ).ready(function() {
    $("#submit_registration_form").click(submitPartnerRegistration);
    $("#import_button").click(importUberId);
    $("#search_button").click(search);
    $("#export_import_button").click(function () {
        window.location = "export_import.html";
    });

    $("#download_collectibles").click(downloadCollectibles);
    $("#download_partners_without_uber_id").click(downloadPartnersListWithourUberId);
});

