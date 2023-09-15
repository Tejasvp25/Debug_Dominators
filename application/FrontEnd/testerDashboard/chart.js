document.addEventListener("DOMContentLoaded", function () {
    // Sample JSON data (replace with your data)
    const bugProgressData = {
        bugs: 10,         // Total number of bugs
        progress: 70,     // Progress percentage (0-100)
        remaining: 30,    // Remaining work percentage (0-100)
    };

    // Calculate the completed percentage
    const completedPercentage = bugProgressData.progress;
    const remainingPercentage = bugProgressData.remaining;
    const blueShades = [
        "rgba(0, 123, 255, 0.2)", // Light blue
        "rgba(0, 123, 255, 0.5)", // Medium blue
    ];


    // Create a pie chart
    const ctx = document.getElementById("bugProgressChart").getContext("2d");
    const myPieChart = new Chart(ctx, {
        type: "pie",
        data: {
            labels: ["Completed", "Remaining"],
            datasets: [{
                data: [completedPercentage, remainingPercentage],
                backgroundColor: blueShades, // Use the blue shades
                borderColor: blueShades,     // Use the same blue shades for border
                borderWidth: 1,
            }],
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
        },
    });
});
