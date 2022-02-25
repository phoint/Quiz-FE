$(document).ready(function () {
  /* htmlelement element  : the element carry the message
String text : the content of the message
boolean isSuccess: is a success message */
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
  $("#register-form").submit(function (e) {
    e.preventDefault();
    const form = $(this);
    let formMessage = form.find(".form-message");
    var submitBtn = $(this).find(":submit");
    // all error elements of the form
    var errorEles = $(form.find(".error"));
    var data = JSON.stringify(form.serializeObject());
    console.log(api.host + api.student_role.user.register);
    $.ajax({
      crossDomain: true,
      type: "post",
      url: api.host + api.student_role.user.register,
      data: data,
      contentType: "application/json; charset=utf-8",
      beforeSend: function () {
        submitBtn.prop("disabled", true);
      },
      success: function (response) {
        if (response === "success") {
          showFormMessage(formMessage, "Đăng ký thành công", true);
          errorEles.text("");
          setTimeout(() => {
            $("#createAccountModel").modal("hide");
            $("#signInModel").modal("show");
          }, 1000);
        } else {
          showFormMessage(
            formMessage,
            "Đã có lỗi xảy ra khi thêm user vào database",
            false
          );
        }
      },
      error: function (xhr, status, error) {
        console.log(xhr.responseText);
        var response = xhr.responseJSON;
        console.log(response);
        if (xhr.status === 422) {
          $.each(form.serializeArray(), function (i, field) {
            
            var input = $("input[name=" + field.name + "]");
            var errorEle = input.next();
            if (response.hasOwnProperty(field.name)) {
              errorEle.text(response[field.name]);
            } else {
              errorEle.text("");
            }
          });
          return;
        }
        showFormMessage(formMessage,"");
      },
      complete: function () {
        submitBtn.prop("disabled", false);
      },
    });
  });
});
