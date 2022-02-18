$(document).ready(function () {
  var updatePasswordForm = document.getElementById("update-password-form");
  const submitBtn = updatePasswordForm.querySelector("#submit");
  const submitMessage = updatePasswordForm.querySelector("#submitMessage");
  const errorElements=updatePasswordForm.querySelectorAll('.error');
  updatePasswordForm.addEventListener("submit", async function (event) {
    event.preventDefault();
    errorElements.forEach((errorElement=>{
      errorElement.innerText='';
    }))
    const formData = new FormData(event.target);
    const formObject = Object.fromEntries(formData.entries());
    formObject.username = "";
    let result = await sendRequest(formObject).catch((err) => {
      console.log(err);
      return err;
    });
    console.log(result);
    if (typeof result === "string") {
      if (result === "success") {
        submitMessage.style.color='green';
        submitMessage.innerText = "Thay đổi mật khẩu thành công";
        updatePasswordForm.reset();
      }
      if (result === "failed") {
        submitMessage.style.color='red';
        submitMessage.innerText =
          "Thay đổi mật khẩu thất bại vì đã có lỗi xảy ra ỏ server";
      }
    }
    if (typeof result === "object") {
      handleError(result);
    }
    submitBtn.disabled = false;
  });
  function handleError(invalidResponse) {
    for (const [key, value] of Object.entries(invalidResponse)) {
      let input = updatePasswordForm.querySelector(`input[name=${key}]`);
      let errorElement = input.nextElementSibling;
      errorElement.innerText =value;
    }
  }
  async function sendRequest(formObject) {
    submitBtn.disabled = true;
    let res = await fetch("update-password", {
      method: "post",
      headers: {
        "Content-Type": "application/json; charset=UTF-8",
      },
      body: JSON.stringify(formObject),
    });
    if (res.ok) {
      return await res.text();
    }
    if (res.status === 422) {
      return await res.json();
    }
  }
});
