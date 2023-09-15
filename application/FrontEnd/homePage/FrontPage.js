let jsonData = null; // Initialize jsonData as null

// JavaScript code to handle "Import" button click
document.getElementById("importButton").addEventListener("click", function () {
    document.getElementById("jsonFileInput").click();
});

// JavaScript code to handle file input change
document.getElementById("jsonFileInput").addEventListener("change", function () {
    const uploadedFile = this.files[0];

    if (!uploadedFile) {
        alert("Please select a JSON file.");
        return;
    }

    // Read and process the JSON file content here
    const reader = new FileReader();
    reader.onload = function (event) {
        jsonData = JSON.parse(event.target.result);
        alert("JSON file imported successfully!");
    };

    reader.readAsText(uploadedFile);
});

// JavaScript code to handle login button click
document.getElementById("loginButton").addEventListener("click", function () {
    if (!jsonData) {
        alert("Please import a JSON file first.");
        return;
    }

    // Get the username and password entered by the user
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    // Check if the entered username and password match any user data in jsonData
    const user = jsonData.find(user => user.username === username && user.password === password);

    if (user) {
        // Login successful
        document.getElementById("loginMessage").innerText = "Login successful!";
    } else {
        // Invalid credentials
        document.getElementById("loginMessage").innerText = "Invalid username or password. Please try again.";
    }
});

// JavaScript code to clear the username and password fields when the modal is closed
$('#loginModal').on('hidden.bs.modal', function () {
    document.getElementById("username").value = "";
    document.getElementById("password").value = "";
});

