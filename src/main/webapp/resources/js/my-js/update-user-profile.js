$(document).ready(function () {
  const form = document.getElementById("update-user-profile-form");
  const formMessage=document.getElementById('msgSubmit');
  const submitBtn = form.querySelector('[type="submit"]');
  form.addEventListener("submit", async function (event) {
    event.preventDefault();
    submitBtn.disabled = true;
    const formData = new FormData(
      document.getElementById("update-user-profile-form")
    );
    addFileToForm(document.getElementById("avatar").files, formData);

    if(!formHasValue(formData)){
      submitBtn.disabled = false;
      return;
    }
    const resultFromServer= await requestUpdate(formData).catch(e => {
      return 'error';
    });
    if( typeof resultFromServer ==='string'){
      if(resultFromServer ==='failed'){
        formMessage.style.color='red';
        formMessage.innerText='Cập nhật thông tin thất bại';
      }
      if(resultFromServer ==='success'){
        formMessage.style.color='green';
        formMessage.innerText='Cập nhật thông tin thành công';
        window.location.reload();
      }
      if(resultFromServer==='error'){
        formMessage.style.color='red';
        formMessage.innerText='Đã có lỗi xảy ra ở server';
      }
    }
    if(typeof resultFromServer ==='object'){
      handleErrors(resultFromServer);
    }
    submitBtn.disabled=false;
  });


  function handleErrors(object){
    for (const [key, value] of Object.entries(object)) {
      let input = form.querySelector(`[name=${key}]`);
      let errorElement = input.nextElementSibling;
      errorElement.innerText =value;
    }
  }

  function addFileToForm(files, form) {
    if (files.length != 0) {
      form.set("avatar", files[0]);
    }
  }

  function formHasValue(formData) {
    for (var pair of formData.entries()) {
      if (pair[1].length > 0) {
        return true;
      }
    }
    return false;
  }
  async function requestUpdate(formData) {
    let response = await fetch("user/update-profile", {
      method: "post",
      body: formData,
    });
    if (response.ok) {
      return await response.text();
    }
    if (response.status === 422) {
      return await response.json();
    }
    if(response.status === 424){
      return 'error';
    }
    return 'error';

  }
});
