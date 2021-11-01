package com.hoaxify.ws.user;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.hoaxify.ws.shared.GenericResponse;

import error.ApiError;

//Kullanıcı ile ilgili tüm http requestlerinin ulaştığı bizim ilk application classımız Controller clasıdır. (com/hoaxify.ws altında bir package oluşturduk, 
// daha sonra onun altında da userController diye bir class)
//@RestController yazarak springin böyle bir görevi olduğunu bildiriyoruz ve böylece spring bizim yerimize bir takım kontrolleri otomatik olarak gerçekleştiriyor.

@RestController
public class UserController {
	
	// Aşağıdaki satır loglama yapmamıza yarar.
	// private static final Logger log = LoggerFactory.getLogger(UserController.class); --> ancak aşağıda kullanmadığımız için burada da yoruma aldık.
	
	
	// Dependencyleri inject etmek için springin Autowired annotation kullanıyoruz.

	// UserRepository dependency burada görülüp spring tarafından setleniyoruz. Ancak bu layered architecture yani katmanlı yapı için 
	// uygun bir durum değildir. Yani controller ile repository arasına bir katman ekleyerek buradaki işleri birbirinden ayırmamız istenir.
	// Bu katmana Service katmanı denir. 
	
	// UserRepository, UserController içinde tanımlamıştık ancak bu layered architecture uygun olmadığı için UserRepository götürüp yeni create ettiğimiz
	// UserService içinde tanımlıyoruz. UserRepository orada tanımladığımız için UserServcice gelip UserController içinde tanımlıyoruz.
		
	// --> UserRepository UserRepository; (Görme maksatlı yorum satırına alıyoruz.
	// @Autowired
	// UserService userService;
	

	// create ettiğimiz bu createUser metodunun post requestlerinde çalıştığını göstermek için aşağıya PostMapping notatitonu ekliyoruz.
	// bu mapping içinde dinlediğimiz url pathini vermemiz gerekir. Biz users (genelde istediğimiz şeyin çoğulunu vererek kullanırız) olarak kullandık.
	// Front tarafından ağ(network) üzerin CORS hatası önlemek için (Port çakışması temelli) @CrossOrigin annotation ekleriz. 
	// Bunu front tarafında package.json içine "proxy":"http://localhost:8080" yazarak da önleyebiliriz.
	// @CrossOrigin
	
	// Postman üzerinden post ettiğimiz requestler için response 200 kodu verebilir, gerekli olmamakla beraber yaptığımız işe uygun olacak şekilde bunu değişebiliriz.
	// @ResponseStatus(HttpStatus.CREATED) şeklinde yapabiliriz.
	
	// Create ettiğimiz metodun içine ise front kısmında aldığımız verilere erişebilmek için @RequestBody notationı giriyoruz. 
	// Yani gelen request içindeki bodyi bize ver diyoruz.
	
	// user sayfasında yaptığımız NotNull validation için burada @valid yazarız.
	

		// Yukarıda UserRepository set ettikten sonra aşağıdaki loglama satırına ihtiyaç kalmadığı için yoruma alıyoruz.
		// -->log.info(user.toString());
		
		// Yukarıda userService inject etmek için çağırdığımız için burada da yine userService'in save metodunu çağırmamız gerek (ilk aşamada yoktur,
		// bu nedenle bu metodu create etmemiz gerekir.)
		// --> UserRepository.save(user); (Görme maksatlı yorum satırına alıyoruz.)
		
		
// AŞAĞIDA handleValidationException METODUNU YAZDIĞIMIZ İÇİN DİĞER NİCKNAME,USERNAME VS KONTROL ETMEMİZE GEREK KALMADI. TEK SEFERDE KONTROL ETTİK
		
		// hem username hem nickname ileri de password kısmında kullancağımız controller metodlarının hepsinde olan şeyleri globale yazabiliriz.
		
		// Error package içinde tanımladığım verileri burada control ediyoruz.
		// ApiError error = new ApiError(400,"Doğrulama Hatası","/users");
		// Map<String, String> validationErrors = new HashMap<>();

		// EN AŞAĞIDA YAZDIĞIMIZ (handleValidationException) METOD SAYESİNDE AŞAĞIDAKİ GİBİ TEK TEK KONTROL ETMEMİZE GEREK KALMADI, TEK SEFERDE KONTROL ETMEYİ
		// SAĞLIYORUZ. SADECE FARKLI BİR METOD OLARAK GÖRÜLMESİ İÇİN YORUM SATIRINA ALIYORUM.
		
		// Fronttaki form verilerini girerken yapmış her input alanına belirli conditionlar getirmek için backend tarafında belirli durumlar yazarız.
		// String username = user.getUsername();
		// String nickname = user.getNickname();
		
		// if (username == null || username.isEmpty()) {
		// 	validationErrors.put("username", "Kullanıcı adı bölümü boş kalamaz.");
		// }
		
		// username için yaptığımızı diğerleri için de yapmamız gerekir. bunun için kopyasını alıp üzerinde düzenlemeler yapıyoruz.
		// if (nickname == null || nickname.isEmpty()) {
		// 	validationErrors.put("nickname", "Nickname bölümü boş kalamaz.");
		// }
		
		// burada validationErros size ını kontrol ediyoruz yani boş değilse demek ki bir şeyler yazılmıştır o zaman hatayı dön
		// if (validationErrors.size()>0) {
		// 	error.setValidationErrors(validationErrors);
			// bad request dönmesi demek hata kodu 400 dönmesi demektir client daki consolda görebiliriz.
		// 	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		// }
		
		// YUKARIDAKİ METODLARI KULLANDIĞIMIZ ZAMAN AŞAĞIDAKİ ŞEKİLDE GEREKLİYİ ANCAK ŞU AN KULLANMADIĞIMIZ İÇİN MODİFİYE EDİYORUZ.
		
		// @PostMapping("/users")
		// public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
	    // 	userService.save(user);	
		// 	GenericResponse responseMessage = new GenericResponse();
		// 	responseMessage.setMessage("Yeni kullanıcı başarıyla oluşturuldu.");
		// 	return  ResponseEntity.ok(responseMessage);
		// 	}
		
		@Autowired
		UserService userService;	
	
		@PostMapping("/users")
		public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
		     userService.save(user);	
			 GenericResponse responseMessage = new GenericResponse();
			 responseMessage.setMessage("Yeni kullanıcı başarıyla oluşturuldu.");
			return  ResponseEntity.ok(responseMessage);
		}
		
		
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleValidationException(MethodArgumentNotValidException exception){
		ApiError error = new ApiError(400,"Doğrulama Hatası","/users");
		Map<String, String> validationErrors = new HashMap<>();
		for (FieldError fieldError: exception.getBindingResult().getFieldErrors()){
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		error.setValidationErrors(validationErrors);
		return error;
	}
	
}

// Postman kullaranak deneyebiliriz. Burada get ya da post vs hangi fonksiyonu kullandığımıza dikkat etmemiz gerekir aksi halde 405 hatası alabiliriz.