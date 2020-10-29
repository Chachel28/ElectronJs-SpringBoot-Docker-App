function validate_login() {
    $("#result").text("");
    var emailaddress = $("#email").val();
    if (validateEmailAddress(emailaddress)) {
        $("#result").text(emailaddress + " is valid :)");
        $("#result").css("color", "green");
    } else {
        $("#result").text(emailaddress + " is not correct, please retry:(");
        $("#result").css("color", "red");
    }
    return false;
}
$("#validate").bind("click", validate_login);