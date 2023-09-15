// Function to update user information
function updateUserInformation() {
  const firstname = document.getElementById("firstname").value;
  const lastname = document.getElementById("lastname").value;
  const email = document.getElementById("email").value;
  const bio = document.getElementById("bio").value;

  // Update user information on the left
  document.getElementById("userName").textContent = firstname + lastname;
  document.getElementById("userEmail").textContent = `Email: ${email}`;
  document.getElementById("userBio").textContent = `Bio: ${bio}`;
}

// Add a click event listener to the "Save Changes" button
document.getElementById("saveChanges").addEventListener("click", function () {
  // Call the function to update user information
  updateUserInformation();
});

const profileImageInput = document.getElementById("profileImage");
profileImageInput.addEventListener("change", function () {
  const selectedFile = profileImageInput.files[0];
  if (selectedFile) {
    // You can handle the selected image file here (e.g., upload it to a server)
    // For now, we'll just display the selected file name
    alert(`Selected Profile Image: ${selectedFile.name}`);
  }
});
