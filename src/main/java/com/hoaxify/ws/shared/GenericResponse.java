package com.hoaxify.ws.shared;

import lombok.Data;

@Data
// Direkt @Data dediğimiz zaman lombok bize arguman almayan bir constructor verir, bu nedenle controller kısmında yazdığımız
// fonksiyon için ayrıca mesaj satırı yazmamız gerekir ancak kodu daha okunur yapmak için @AllArgsConstructor da yazabiliriz.
// böylece controller tarafında yazdığımız kodu kısaltarabiliriz.
public class GenericResponse {
	
	// Gönderdiğimiz verinin create edilip edilmediğini görmek için de buradan bir mesaj yazdırabiliriz.
	public String message;

}
