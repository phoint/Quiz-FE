$(document).ready(function () {
  const updateUserBtn = document.querySelectorAll(".update-user-btn");
  const editModal = document.getElementById("editModal");
  const editForm = document.getElementById('edit-user-form');
  const editSubmitBtn = editForm.querySelector("[type=submit]");
  const errorElements = editForm.querySelector(".text-danger");
  const searchForm = document.getElementById("search-form");
  const formMessage=editForm.querySelector('.form-message');
  var currentURLParams = new URLSearchParams(window.location.search);
  searchForm.addEventListener("submit", function (e) {
    e.preventDefault();
    const sortByInput = searchForm.querySelector('input[name="sortBy"]');
    sortByInput.value = currentURLParams.get("sortBy");
    searchForm.submit();
  });
  editForm.addEventListener("submit", async function (event) {
    event.preventDefault();
    editSubmitBtn.disabled=true;
    const formData = new FormData(event.target);
    const formObject = Object.fromEntries(formData.entries());
    filterRequestProperties(formObject);
    let fetchResult = await updateUser(formObject);
    console.log(fetchResult);
    if (typeof fetchResult === "string") {
      if (fetchResult === "success") {
          formMessage.style.color='green'; 
          formMessage.innerText='Thay đổi thành công';
          setTimeout(function(){
            window.location.reload();
          },1000);
      }
      if (fetchResult === "failed") {
        formMessage.style.color='red';
        formMessage.innerText='Thay đôi thất bại';
      }
      if (fetchResult === "error") {
        formMessage.style.color='red';
        formMessage.innerText='Đã có lỗi xảy ra trên server';
      }
    }
    if (typeof fetchResult === "object") {
      handleError(fetchResult);
    }
    editSubmitBtn.disabled=false;

  });
  const currentPathUrl = Array.from(updateUserBtn).forEach((button) => {
    button.addEventListener("click", async function (e) {
      e.preventDefault();
      const userID = e.target.dataset.id;
      let userObj = await getUser(userID);
      console.log(userObj);
      updateEditModal(userObj);
      $("#editModal").modal("show");
    });
  });
  function handleError(invalidResponse) {
    for (const [key, value] of Object.entries(invalidResponse)) {
      let input = editForm.querySelector(`[name=${key}]`);
      if(!input){
        formMessage.style.color='red';
        formMessage.innerText='User không tồn tại';
      }
      let errorElement = input.nextElementSibling;
      errorElement.innerText =value;
    }
  }
  function filterRequestProperties(formObject) {
    delete formObject["email"];
    delete formObject["name"];
  }

  async function updateUser(formObject) {
    let response = await fetch("user/update", {
      method: "post",
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
      body: JSON.stringify(formObject),
    });
    if (response.ok) {
      return await response.text();
    }
    if (response.status === 422) {
      return await response.json();
    }
    if (response.status === 424) {
      return "error";
    }
  }

  async function getUser(userID) {
    let response = await fetch("user/get/" + userID);
    let json = await response.json();
    return json;
  }
  function updateEditModal(userObj) {
    for (var propertyName in userObj) {
      if (propertyName === "password") continue;
      const input = editModal.querySelector(`[name=${propertyName}]`);
      if (!input) continue;
      input.value = userObj[propertyName];
    }
  }
});
