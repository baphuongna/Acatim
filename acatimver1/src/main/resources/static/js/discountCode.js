/* function checkCode(){
			var showCodeDiscount = document.querySelectorAll('.showCodeDiscount');
			
			showCodeDiscount.forEach(function(p){
				if (p.value != null) {
					const btnGetCode = document.querySelectorAll('.buttonGetCode');
					btnGetCode.forEach(p => p.style.pointerEvents = "none");
					btnGetCode.forEach(p => p.style.opacity = "0.5");
					console.log(p.value);
				} else {
					const btnGetCode = document.querySelectorAll('.buttonGetCode');
					btnGetCode.forEach(p => p.style.pointerEvents = "all");
					btnGetCode.forEach(p => p.style.opacity = "1");
					console.log("bang NULLL!!!");
				}
			});
			
			/* myArray.forEach(function(element) {
		        if (element !== myArray[0]) {
		            passing = false;
		        }
		    });
			
			if (showCodeDiscount != null) {
				const btnGetCode = document.querySelectorAll('.buttonGetCode');
				btnGetCode.forEach(p => p.style.pointerEvents = "none");
				btnGetCode.forEach(p => p.style.opacity = "0.5");
				console.log("khac NULLL!!!");
			} else {
				const btnGetCode = document.querySelectorAll('.buttonGetCode');
				btnGetCode.forEach(p => p.style.pointerEvents = "all");
				btnGetCode.forEach(p => p.style.opacity = "1");
				console.log("bang NULLL!!!");
			} */
		/*} */
		
/*		function makeid() {
			var text = "";
			var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
			for (var i = 0; i < 15; i++){
				text += possible.charAt(Math.floor(Math.random() * possible.length));
			}
			console.log(text);
			
			const paragraphs = document.querySelectorAll('.codeDiscount');
			paragraphs.forEach(p => p.style.display = "block");
			paragraphs.forEach(p => p.value = text);
			
			const btnGetCode = document.querySelectorAll('.buttonGetCode');
			btnGetCode.forEach(p => p.style.pointerEvents = "none");
			btnGetCode.forEach(p => p.style.opacity = "0.5");
		}
		
		function remove(){
			const paragraphs = document.querySelectorAll('.codeDiscount');
			paragraphs.forEach(p => p.style.display = "none");
			
			const btnGetCode = document.querySelectorAll('.buttonGetCode');
			btnGetCode.forEach(p => p.style.pointerEvents = "all");
			btnGetCode.forEach(p => p.style.opacity = "1");
		}*/