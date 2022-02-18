$(document).ready(function () {
  var form = $("#login-form");
  var formMessage = form.find(".form-message");
  $(form).submit(function (e) {
    e.preventDefault();
    var formMessage = $(".form-message");
    var submitBtn = $(this).find(".login-btn");
    var formObject=$(form).serializeObject();
    formObject.accessToken='';
    formObject.authProvider="LOCAL";
    $.ajax({
      type: "post",
      url: "login",
      data: form.serialize(),
      beforeSend: function () {
        submitBtn.prop("disabled", true);
      },
      success: function (response) {
        if (response === "success") {
          showFormMessage(formMessage, "Đăng nhập thành công", true);
          setTimeout(() => {
            window.location.href = "";
          }, 1000);
        } else if (response === "failed") {
          showFormMessage(
            formMessage,
            "Tài khoản hoặc mật khẩu của bạn không đúng",
            false
          );
        }
      },
      error: function (xhr, status, error) {
        showFormMessage(formMessage, "Đã có lỗi xảy ra với hệ thống", false);
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
  window.fbAsyncInit = function () {
    FB.init({
      appId: "354785129593007",
      cookie: true,
      xfbml: true,
      version: "v2.8",
    });
  };
  (function (d, s, id) {
    var js,
      fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) {
      return;
    }
    js = d.createElement(s);
    js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  })(document, "script", "facebook-jssdk");

  document.getElementById("facebook-login-btn").addEventListener(
    "click",
    () => {
      //do the login
      FB.login(
        (response) => {
          console.log(response);
          if (response.authResponse) {
            loginFacebook(response.authResponse.accessToken);
          }
        },
        { scope: "email,public_profile", return_scopes: true }
      );
    },
    false);
  async function loginFacebook(accessToken) {
    var submitBtn = $(form).find(".login-btn");
    submitBtn.prop("disabled", true);
    var response = await fetch("login", {
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      method: "post",
      body: new URLSearchParams({
        username:'',
        password:'',
        authProvider:'FACEBOOK',
        accessToken: accessToken,
      }),
    });
    var result = await response.text();
    if (result === "success") {
      showFormMessage(formMessage, "Đăng nhập thành công", true);
      setTimeout(() => {
        window.location.href = "";
      }, 1000);
    } else if (result === "failed") {
      showFormMessage(
        formMessage,
        "Tài khoản hoặc mật khẩu của bạn không đúng",
        false
      );
    }
    submitBtn.prop("disabled", false);
  }
});
