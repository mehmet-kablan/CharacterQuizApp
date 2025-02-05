document.addEventListener("DOMContentLoaded", function() {
	document.addEventListener("keydown", function(event) {
		if (event.key === "Enter") {
			event.preventDefault(); 
		}
	});
});
		
let quoteDiv = document.getElementById("quoteDiv");
	if(quoteDiv.innerText.trim() == ""){
		quoteDiv.classList.add("notVisible");
	}
	else{
		quoteDiv.classList.remove("notVisible");
	}

let resultDiv = document.getElementById("result");
resultDiv.classList.add("notVisible");
let answerText;

function setTextbox(selectBox){
	document.getElementById("searchBox").value = selectBox.options[selectBox.selectedIndex].text;
}
function compareText() {

			answerText = document.getElementById("suggestions").value;
			console.log(document.getElementById("suggestions").value);
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "myServlet", true); 
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.send("answerText=" + encodeURIComponent(answerText));

            xhr.onload = function() {
                if (xhr.status === 200) {
                    var comparisonResult = xhr.responseText; 
                    var dynamicText;
                    if (comparisonResult === "equal") { 
                        dynamicText = "Congratulations!";
                    }else {
                        dynamicText = "Try again :("; 
                    }
                    
                    var resultDiv = document.getElementById("result");
                    resultDiv.textContent = dynamicText;

                } else {
                    console.error("Error: " + xhr.status);
                    var resultDiv = document.getElementById("result");
                    resultDiv.textContent = "An error occurred.";
                }
            };
			
			resultDiv.classList.remove("notVisible");
        }
        


$(document).ready(function() {
    $("#searchBox").keyup(function() {
        let searchTerm = $(this).val();

        if (searchTerm.length >= 0) {
            $.ajax({
                type: "GET",
                url: "myServlet",
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

	
	
