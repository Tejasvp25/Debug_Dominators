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


