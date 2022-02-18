$(document).ready(function () {
  var form = $("#reset-password-form");
  var formMessage = $(form).find(".form-message");
  var submitBtn = $(form).find(":submit");
  var searchParams = new URLSearchParams(window.location.search);
  var token = searchParams.get("token");
  token=decodeURI(token);
  var errorElements = $(form).find(".error");
  $(form).submit(function (e) {
    e.preventDefault();
    const resetPasswordRequest = form.serializeObject();
    resetPasswordRequest.token = token;
    $.ajax({
      type: "post",
      url: api.host + api.student_role.user.resetPassword,
      data: JSON.stringify(resetPasswordRequest),
      contentType: "application/json; charset=utf-8",
      beforeSend: function () {
        submitBtn.prop("disabled", true);
      },
      success: function (response) {
        if (response.toLowerCase() === "success") {
          showFormMessage(formMessage, "Thay đổi mật khẩu thành công", true);
          errorElements.text("");
          setTimeout(function () {
            window.location.href = "";
          }, 1000);
          return;
        }
        if (response.toLowerCase() === "failed") {
          showFormMessage(formMessage, "Đã có lỗi xảy ra trên server", false);
          return;
        }
      },
      error: function (xhr, status, error) {
        var response = xhr.responseJSON;
        errorElements.text("");
        if (xhr.status === 422) {
          for (var propertyName in response) {
            console.log(propertyName);
            if (propertyName === "token") {
              showFormMessage(formMessage,'Mã để thực hiện reset mật khẩu của bạn không hợp lệ', false);
              return;
            }
            var input = $("input[name=" + propertyName + "]");
            var errorEle = input.next();
            errorEle.text(response[propertyName]);
          }
          return;
        }
        showFormMessage(
          formMessage,
          "Đã có lỗi xảy ra khi gửi dữ liệu tới server",
          false
        );
      },
      complete: function () {
        submitBtn.prop("disabled", false);
      },
    });
  });
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
});
