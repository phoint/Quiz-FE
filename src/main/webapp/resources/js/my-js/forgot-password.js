$(document).ready(function () {
  var form = $("#forgot-password-form");
  var formMessage = form.find(".form-message");
  function showFormMessage(element, text, isSuccess) {
    if (isSuccess) {
      if ($(element).hasClass("text-danger")) {
        $(element).removeClass("text-danger");
      }
      if (!$(element).hasClass("text-success")) {
        $(element).addClass("text-success");
      }
    } else {
      if (!$(element).hasClass("text-danger")) {
        $(element).addClass("text-danger");
      }
      if ($(element).hasClass("text-success")) {
        $(element).removeClass("text-success");
      }
    }
    $(element).text(text);
  }
  var submitBtn = $(this).find(":submit");
  $(form).submit(function (e) {
    e.preventDefault();
    var formObject = form.serializeObject();
    $.ajax({
      type: "get",
      url: api.host + api.student_role.user.forgotPassword,
      data: { email: formObject.email },
      beforeSend: function () {
        submitBtn.prop("disabled", true);
      },
      success: function (response) {
        if (response === "success") {
          showFormMessage(formMessage, "Hãy kiểm tra email của bạn", true);
          return;
        }
        if (response === "email is not found") {
          showFormMessage(
            formMessage,
            "Email của bạn chưa được đăng ký",
            false
          );
          return;
        }
      },
      error: function (xhr, status, error) {
        showFormMessage(formMessage, "Email của bạn không hợp lệ", false);
        console.log(xhr.responseText);
      },
      complete: function () {
        submitBtn.prop("disabled", false);
      },
    });
  });
});
