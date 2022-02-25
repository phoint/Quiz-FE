$(document).ready(function () {
  const form = document.getElementById("update-user-profile-form");
  const submitBtn = form.querySelector('[type="submit"]');
  form.addEventListener("submit", async function (event) {
    event.preventDefault();
    submitBtn.disabled=true;
    const avatarFile=document.getElementById('avatar').files[0];
    const formData=new FormData(document.getElementById("update-user-profile-form"));
    console.log(formData);
    formData.append('avatar',avatarFile);
    const resultFromServer=requestUpdate(formData);
    submitBtn.disabled=false;
  });



  async function requestUpdate(formData) {
    let response=fetch('user/update-profile',{
        method:'post',
        body:formData
    });
    console.log(response);
  }
});
