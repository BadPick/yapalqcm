function packJsonRequestsData(divQuestions) {
			var divReponses = '';
			var index = 1;
			var dataString = '{'
				dataString.append('numQuestion:');
				//numquestion
				dataString.append(',');
				dataString.append('marquee:');
				//marquee
				dataString.append(',');
				dataString.append('chrono:');
				//chrono
				dataString.append(',');
				dataString.append('reponses:[');
				//reponses					
				divReponses.each(function(){
					if (index > 1) {
						dataString.append(',');
					}	
					dataString.append('{');
					dataString.append('numReponse:');
					//numReponse
					dataString.append(',');
					dataString.append('checked:');
					//checked
					dataString.append('}');
					})
										
					dataString.append(']');
				alert('dataString');
				return JSON.stringify(dataString)	
			}
			
		