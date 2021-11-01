package error;

import java.util.Date;
import java.util.Map;

import lombok.Data;

@Data
public class ApiError {
	
	// Standart bir hata objesinde belirli değerler olabilir ancak en çok istenenler aşağıdakilerdir.
	
	public int status;
	
	public String message;
	
	public String path;
	
	public long timestamp = new Date().getTime();
	
	public Map<String, String> validationErrors;
	
	
	public ApiError(int status, String messages, String path) {
		this.status = status;
		this.message = messages;
		this.path = path;
	}

}
