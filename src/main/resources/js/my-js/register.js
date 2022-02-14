$("#register-form").submit(function (e) {
  e.preventDefault();
  var form = $(this);
  var data=JSON.stringify(form.serializeObject());
  $.ajax({
    crossDomain: true,
    type: "post",
    url: "http://localhost:9090/register",
    data: data,
    contentType: "application/json; charset=utf-8",
    success: function (response) {
        console.log(response);
    },
  });
});
