package com.hoaxify.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//production ile ilgili kodlarımız scr/main/java altında yer alır.

// SpringBootApplication annotationı (main içinde de run edilir) burada önemlidir. SpringBootApplication içine gittiğimiz zaman @SpringBootConfiguration-@EnableAutoConfiguration
// ve @ComponentScan olmak üzere 3 adet annotation görürüz.

//->EnableAutoConfiguration (pom.xml içinde görebiliriz) uygulamayı create ederken eklediğimiz security, jpa vs gibi dependencylerin beraberinde getirdiği dependenciyler vardır, 
// bunun yardımı ile enableAutoConfiguration ile bu dependencylerin configurationları sağlanırker bunun yanında uygulamanın da belli bir configurasyon ile çalışması sağlanır.

//->ComponentScan annotation ise bizim src/main/java altında bulunan com.hoaxify.ws(Aslında uygulamamızın adı) paketi içinde bulanacak diğer classların taranıp oradada bulunan 
// configurasyon ile ilgili applicationun nihayi configurasyon ayarlarının yapıldığı yerdir.


//->src/main/resources klasörü altında bulunan application.properties de diğer bir önemli dosyamızdır. Bu properties dosyası içinde application içinde kullanacağımız global 
// değişkenlerimizi ya da belirli configuration parametrelerimizi codun dışına taşıma imkanı sağlayan, böylece appin bu tarz parametrelerin değişimlerine bağlı olarak verdiği 
// tepkilerin gözlenmesini sağlayan bir özelliktir. Örneğin server.port=9090 yaparak default 8080 portunu değişebiliriz.
// Buranın iki tip çalışma tarzı vardır.
   //-> 1) server.copression.enable=true gibi tek satırda yazdığımız,
   //-> 2) ikinci tipte ise application.properties dosyasının uzantısını yaml olarak değiştirip (application.yaml) json benzeri merdivenli şekilde yazmamızı sağlar.


// Önemli olan diğer bir klasör ise src/test/java klasörüdür. Burada test classları olur.
// pom.xlm dosyası içinde kullandığımız spring versiyonu ya da dependencyleri değiştirebilir ya da yenilerini ekleyebiliriz.


// (exclude = SecurityAutoConfiguration.class) yani exclude etmek bir configuration dosyasını hariç tut demektir. 
// (Yapmazsak 401 hatası alırız postmande, console da farklı bir hata codu)
@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
public class Application {

	// aşağısı basit bir java classıdır.
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
