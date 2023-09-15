document.addEventListener("DOMContentLoaded", function () {
    // Get all buttons
    const buttons = document.querySelectorAll("#button-list button");

    // Add click event listeners to the buttons
    buttons.forEach(function (button) {
        button.addEventListener("click", function () {
            // Hide all tables
            const tables = document.querySelectorAll("table");
            tables.forEach(function (table) {
                table.style.display = "none";
            });

            // Show the selected table based on the data-target attribute
            const targetId = this.getAttribute("data-target");
            const targetTable = document.getElementById(targetId);
            if (targetTable) {
                targetTable.style.display = "table"; // Display the table
            }
        });
    });
});

// Example data for bugs (replace this with your data)
const bugs = [
    {
      "id": 1001,
      "bug_title": "Authentication Issue",
      "description": "Users are unable to log in to their accounts. This issue needs to be resolved urgently."
    },
    {
      "id": 2002,
      "bug_title": "UI Display Problem",
      "description": "The user interface is not rendering correctly on smaller screens, causing layout issues."
    },
    {
      "id": 3003,
      "bug_title": "Data Sync Error",
      "description": "Data synchronization between the client and server is failing intermittently, resulting in data inconsistencies."
    },
    {
      "id": 4004,
      "bug_title": "Performance Degradation",
      "description": "The application's performance has significantly degraded after the latest update, leading to slow response times."
    },
    {
      "id": 5005,
      "bug_title": "Broken Links",
      "description": "Several links within the application are broken, leading to 404 errors when clicked."
    },
    {
      "id": 6006,
      "bug_title": "Missing Feature",
      "description": "A critical feature that was promised to users is missing from the latest release."
    }
  ]
  

const bugTable = document.getElementById("bugTable");
const sidePanel = document.getElementById("sidePanel");

// Function to populate the side panel with bug details
function showBugDetails(bug) {
    sidePanel.innerHTML = `
        <h2>Bug Details</h2>
        <p><strong>ID:</strong> ${bug.id}</p>
        <p><strong>Title:</strong> ${bug.bug_title}</p>
        <p><strong>Description:</strong> ${bug.description}</p>
    `;
}

// Function to handle row click event
function handleRowClick(event) {
    const rowIndex = event.target.parentNode.rowIndex - 1; // -1 because of the table header
    const selectedBug = bugs[rowIndex];

    if (selectedBug) {
        showBugDetails(selectedBug);
        sidePanel.style.right = "0";
    }
}

// Add click event listener to table rows
bugTable.addEventListener("click", handleRowClick);



// Function to populate the table with bug data
function populateTable() {
    const tbody = bugTable.querySelector("tbody");

    bugs.forEach((bug) => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${bug.id}</td>
            <td>${bug.bug_title}</td>
            <td style="width: 1rem"><button class="btn btn-danger">close</button></td>
        `;
        tbody.appendChild(row);

        // Add a click event listener to each row
        row.addEventListener("click", () => {
            showBugDetails(bug);
        });
    });
}

// Function to populate the side panel with bug details
function showBugDetails(bug) {
    sidePanel.innerHTML = `
        <h2>Bug Details</h2>
        <p><strong>ID:</strong> ${bug.id}</p>
        <p><strong>Title:</strong> ${bug.bug_title}</p>
        <p><strong>Description:</strong> ${bug.description}</p>
    `;

    sidePanel.style.right = "0";
}

// Populate the table when the page loads
populateTable();
