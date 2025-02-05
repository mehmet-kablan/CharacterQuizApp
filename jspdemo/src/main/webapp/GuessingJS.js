$(document).ready(function() {
    $("#searchBox").keyup(function() {
        let searchTerm = $(this).val();

        if (searchTerm.length >= 0) {
            $.ajax({
                type: "GET",
                url: "DBServlet",
                data: { search: searchTerm },
                success: function(response) {
                    $("#suggestions").empty();
                    let items = response.split("\n");
                    for (let i = 0; i < items.length; i++) {
                        let item = items[i];
                        if (item.trim() !== "") {
                            $("#suggestions").append("<option value='" + item + "'>" + item + "</option>");
                        }
                    }
                },
                error: function(error) {
                    console.error("Error:", error);
                }
            });
        } else {
            $("#suggestions").empty();
            $("#suggestions").append("<option>          </option>");
        }
    });
});


    document.getElementById("suggestions").addEventListener("change", function () {
        let selectedOption = this.value;
        let resultDiv = document.getElementById("result");

        if (selectedOption) {
            // Create AJAX request
            let xhr = new XMLHttpRequest();
            xhr.open("GET", "getCharacter.jsp?name=" + encodeURIComponent(selectedOption), true);

            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    // Set response data inside the div
					let newDiv = document.createElement("div");

					newDiv.innerHTML = xhr.responseText;

					resultDiv.appendChild(newDiv);
                }
            };

            xhr.send(); // Send request
        } else {
            resultDiv.innerHTML = ""; // Clear if no option is selected
        }
    });

	