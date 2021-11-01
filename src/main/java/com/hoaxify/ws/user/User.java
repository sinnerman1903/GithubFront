package com.hoaxify.ws.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;



@Data

// DB bağlanmak için aşağıdaki anotation kullanırız.
@Entity
public class User {
	
	@Id
	@GeneratedValue
	public long id;

	// user olarak isimlendirdiğimiz yeni classımızın içine postman üzerinden deneme olarak gönderceğimiz (front tarafında bulunan form doğrusu form içindeki 
	// içindeki inputları) tanımlarız.
	
	// Burada belirli validationlar getirmek için bean Validation diye bir dependency kullarınız ki dosyamız içinde mevcuttur.(min,max ya da notnull olmasın gibi)
	// Notnull ile null değer girilmesinin önüne engel koyduğumuz gibi @Size ile de belirli sınırlandırmalar getirebiliriz.
	// @Pattern ile de kelime, harf vs olabilir gibi girilecek inputun modelini yapısını belirliyoruz.
	//@Pattern(regexp=) ise girdiğimiz patterni belirlememizi sağlar
	
	@NotNull
	@Size(min=4, max=15)
	public String username;

	@NotNull
	@Size(min=4, max=15)
	public String nickname;

	@NotNull
	@Size(min=4, max=15)
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
	public String password;
	

}
